package illidan.controller.api;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import illidan.config.PaypalPaymentIntent;
import illidan.config.PaypalPaymentMethod;
import illidan.data.model.Order;
import illidan.model.OrderDetailModel;
import illidan.service.PaypalService;
import illidan.util.ExceptionUtil;
import illidan.util.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin(origins = "https://www.paypal.com/", maxAge = 3600)
@RestController
@RequestMapping("/")
@Slf4j
public class PaypalApiController {

    private static final String PAYPAL_SUCCESS_URL = "pay/success";
    private static final String PAYPAL_CANCEL_URL = "pay/cancel";

    @Autowired
    private PaypalService paypalService;

    @RequestMapping(method = RequestMethod.POST, value = "pay")
    public String pay(HttpServletRequest request, @RequestBody OrderDetailModel orderDetailModel){
        log.info("(pay)orderDetailModel: {}", orderDetailModel);
        String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
        String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
        try {
            Payment payment = paypalService.createPayment(
                    orderDetailModel.getPrice(),
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    orderDetailModel.getName(),
                    cancelUrl,
                    successUrl);
            Order order = new Order();
            order.setEmail(orderDetailModel.getEmail());
            order.setUrlApp(orderDetailModel.getUrlApp());
            order.setKeyWord(orderDetailModel.getKeyWords());
            order.setPrice(orderDetailModel.getPrice());
            order.setCreatedDate(new Date());
            order.setStatus("false");
            request.getSession().setAttribute("order", order);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error("(pay)ex: {}", ExceptionUtil.getFullStackTrace(e));
        }
        return "/";
    }
}
