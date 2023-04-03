package illidan.controller.api;

import illidan.data.model.Order;
import illidan.data.service.OrderService;
import illidan.model.BaseApiResult;
import illidan.model.DataApiResult;
import illidan.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminApiController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/change-status/{orderId}")
    public BaseApiResult changeStatus(@PathVariable int orderId) {
        log.info("(changeStatus)orderId: {}", orderId);
        DataApiResult result = new DataApiResult();
        try {
            Order order = orderService.findOne(orderId);
            if(order.getStatus().equals("false")) {
                order.setStatus("true");
            }
            orderService.save(order);
            result.setMessage("Successfully");
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("(changeStatus)ex: {}", ExceptionUtil.getFullStackTrace(e));
            result.setMessage(ExceptionUtil.getFullStackTrace(e));
            result.setSuccess(false);
        }
        return result;
    }

    @PostMapping("/delete-order/{orderId}")
    public BaseApiResult deleteProduct(@PathVariable int orderId) {
        log.info("(deleteProduct)orderId: {}", orderId);
        BaseApiResult result = new BaseApiResult();

        System.out.println(orderId);
        try {
            orderService.delete(orderId);
            result.setSuccess(true);
            result.setMessage("Delete order successfully");
        } catch (Exception e) {
            log.error("(changeStatus)ex: {}", ExceptionUtil.getFullStackTrace(e));
            result.setSuccess(false);
            result.setMessage(ExceptionUtil.getFullStackTrace(e));
        }

        return result;
    }
}
