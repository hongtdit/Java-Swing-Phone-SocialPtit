package illidan.controller.web;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import illidan.data.model.Order;
import illidan.data.service.OrderService;
import illidan.service.PaypalService;
import illidan.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PaypalController {

    private static final String PAYPAL_SUCCESS_URL = "/success";
    private static final String PAYPAL_CANCEL_URL = "/cancel";

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaypalService paypalService;

    @RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
    public String cancelPay(){
        log.info("(cancelPay)start");
        return "cancel";
    }

    @RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request){
        log.info("(successPay)paymentId: {}, payerId: {}", paymentId, payerId);
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                Order order = (Order)request.getSession().getAttribute("order");
                orderService.save(order);
                request.getSession().removeAttribute("order");
                return "success";
            }
        } catch (PayPalRESTException e) {
            log.error("(successPay)ex: {}", ExceptionUtil.getFullStackTrace(e));
        }
        return "redirect:/";
    }

}
