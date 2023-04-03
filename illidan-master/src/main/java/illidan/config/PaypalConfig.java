package illidan.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import illidan.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class PaypalConfig {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        OAuthTokenCredential oAuthTokenCredential = new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
        try {
            log.info("(oAuthTokenCredential)token: {}", oAuthTokenCredential.getAccessToken());
        } catch (Exception e) {
            log.error("(oAuthTokenCredential)e: {}", ExceptionUtil.getFullStackTrace(e));
        }
        return oAuthTokenCredential;
    }

    @Bean
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
}
