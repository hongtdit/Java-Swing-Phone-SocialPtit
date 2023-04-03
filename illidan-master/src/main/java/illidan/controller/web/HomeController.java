package illidan.controller.web;

import illidan.data.model.Order;
import illidan.data.model.Product;
import illidan.data.service.OrderService;
import illidan.data.service.ProductService;
import illidan.model.*;
import illidan.service.EmailService;
import illidan.util.ExceptionUtil;
import illidan.viewmodel.HomeLandingVM;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String index(Model model) {
        log.info("(index)start");
        ModelMapper modelMapper = new ModelMapper();

        ArrayList<ProductDetailModel> listProductsByIOS = new ArrayList<>();
        ArrayList<ProductDetailModel> listProductsByAndroid = new ArrayList<>();
        ArrayList<Product> products = productService.findAll();
        for(Product product : products) {
            if(product.getKindOfService() == 1) {
                ProductDetailModel productDetailModel = modelMapper.map(product, ProductDetailModel.class);
                listProductsByIOS.add(productDetailModel);
            } else if(product.getKindOfService() == 2) {
                ProductDetailModel productDetailModel = modelMapper.map(product, ProductDetailModel.class);
                listProductsByAndroid.add(productDetailModel);
            }
        }

        HomeLandingVM vm = new HomeLandingVM();

        vm.setListProductsByIOS(listProductsByIOS);
        vm.setListProductsByAndroid(listProductsByAndroid);
        model.addAttribute("vm", vm);

        return "index";
    }

    @GetMapping("/ducthangle")
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/ducthangle_admin", method = RequestMethod.POST)
    public String doLogin(Model model, @Valid @RequestParam String username, @Valid @RequestParam String password) {

        if(username.equals("admin") && password.equals("alo113")) {
            ModelMapper modelMapper = new ModelMapper();
            ArrayList<ProductDetailModel> listProductsByIOS = new ArrayList<>();
            ArrayList<ProductDetailModel> listProductsByAndroid = new ArrayList<>();
            ArrayList<Product> products = productService.findAll();
            for(Product product : products) {
                if(product.getKindOfService() == 1) {
                    ProductDetailModel productDetailModel = modelMapper.map(product, ProductDetailModel.class);
                    listProductsByIOS.add(productDetailModel);
                } else if(product.getKindOfService() == 2) {
                    ProductDetailModel productDetailModel = modelMapper.map(product, ProductDetailModel.class);
                    listProductsByAndroid.add(productDetailModel);
                }
            }

            ArrayList<OrderDataModel> listOrders = new ArrayList<>();
            ArrayList<Order> orders = orderService.findAll();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            for(Order order : orders) {
                OrderDataModel orderDataModel = modelMapper.map(order, OrderDataModel.class);
                orderDataModel.setCreatedDate(formatter.format(order.getCreatedDate()));
                listOrders.add(orderDataModel);
            }

            HomeLandingVM vm = new HomeLandingVM();

            vm.setListProductsByIOS(listProductsByIOS);
            vm.setListProductsByAndroid(listProductsByAndroid);
            vm.setListOrders(listOrders);

            log.info("Login by " + username +" at " + new Date());

            model.addAttribute("vm", vm);
            return "admin";
//            request.getSession().setAttribute("username", username);
        }

        return "login";
    }

    @PostMapping("/send-email")
    public String send(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("message") String message) {
        log.info("(send)name: {}, email: {}, message: {}", name, email, message);
        EmailDataModel emailDataModel = new EmailDataModel(name, email, message);
        try {
            emailService.sendEmail(emailDataModel);
        } catch (MailException mailException) {
            log.error("(send)ex: {}", ExceptionUtil.getFullStackTrace(mailException));
        }
        return "redirect:/";
    }

    @GetMapping(path = "/logout")
    public String logout() {
        log.info("(logout)start");
        return "login";
    }



}
