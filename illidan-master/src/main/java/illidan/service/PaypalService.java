package illidan.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import illidan.config.PaypalPaymentIntent;
import illidan.config.PaypalPaymentMethod;
import illidan.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PaypalService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    public OAuthTokenCredential oAuthTokenCredential() {
        OAuthTokenCredential oAuthTokenCredential = new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
        try {
            log.info("(oAuthTokenCredential)token: {}", oAuthTokenCredential.getAccessToken());
        } catch (Exception e) {
            log.error("(oAuthTokenCredential)e: {}", ExceptionUtil.getFullStackTrace(e));
        }
        return oAuthTokenCredential;
    }

    public APIContext apiContext() {
        try {
            APIContext apiContext = new APIContext(oAuthTokenCredential().getAccessToken());
            apiContext.setConfigurationMap(paypalSdkConfig());
            log.info("(apiContext)apiContext: {}", apiContext);
            return apiContext;
        } catch (PayPalRESTException e) {
            log.error("(apiContext)e: {}", ExceptionUtil.getFullStackTrace(e));
        }
        return null;
    }

    @Autowired
    private APIContext apiContext;

    public Payment createPayment(
            Double total,
            String currency,
            PaypalPaymentMethod method,
            PaypalPaymentIntent intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {

        log.info("(createPayment)total: {}, currency: {}, method: {}, intent: {}, description: {}, cancelUrl: {}, successUrl: {}", total, currency, method, intent, description, cancelUrl, successUrl);

        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
//        apiContext.setRefreshToken(paypalToken.getToken());
        try {
            return payment.create(apiContext);
        } catch (PayPalRESTException e) {
            apiContext = apiContext();
            return payment.create(apiContext);
        }
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        log.info("(executePayment)paymentId: {}, payerId: {}", paymentId, payerId);
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        try {
            return payment.execute(apiContext, paymentExecute);
        } catch (PayPalRESTException e) {
            apiContext = apiContext();
            return payment.execute(apiContext, paymentExecute);
        }
    }

}
