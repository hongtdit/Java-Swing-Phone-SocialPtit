package illidan.controller.api;

import illidan.data.model.Product;
import illidan.data.service.ProductService;
import illidan.model.BaseApiResult;
import illidan.model.DataApiResult;
import illidan.model.ProductDetailModel;
import illidan.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @GetMapping("/detail/{productId}")
    public BaseApiResult detailProduct(@PathVariable int productId) {
        log.info("(detailProduct)productId: {}", productId);
        DataApiResult result = new DataApiResult();
        try{
            Product existProduct = productService.findById(productId);
            if(existProduct == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                result.setSuccess(true);
                ModelMapper modelMapper = new ModelMapper();
                ProductDetailModel productDetailModel = modelMapper.map(existProduct, ProductDetailModel.class);
                result.setData(productDetailModel);
            }
        } catch (Exception e) {
            log.error("(detailProduct)ex: {}", ExceptionUtil.getFullStackTrace(e));
            result.setSuccess(false);
            result.setMessage(ExceptionUtil.getFullStackTrace(e));
        }
        return result;
    }

    @PostMapping("/add-product")
    public BaseApiResult updateProduct(@RequestBody ProductDetailModel product) {
        log.info("(updateProduct)product: {}", product);
        BaseApiResult result = new BaseApiResult();

        try {
            if(!"".equals(product.getName()) && !"".equals(product.getDescription())) {
                // check existed product
                Product newProduct = new Product();

                newProduct.setName(product.getName());
                newProduct.setType(product.getType());
                newProduct.setDescription(product.getDescription());
                newProduct.setSupport(product.getSupport());
                newProduct.setCreatedDate(new Date());
                newProduct.setPrice(product.getPrice());
                newProduct.setDelivery(product.getDelivery());
                newProduct.setKindOfService(product.getKindOfService());
                productService.save(newProduct);
                result.setSuccess(true);
                result.setMessage("Save done");
            } else {
                result.setSuccess(false);
                result.setMessage("Something was wrong");
            }
        } catch (Exception e) {
            log.error("(updateProduct)ex: {}", ExceptionUtil.getFullStackTrace(e));
            result.setSuccess(false);
            result.setMessage(ExceptionUtil.getFullStackTrace(e));
        }
        return result;
    }

    @PostMapping("/update-product/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductDetailModel product) {
        log.info("(updateProduct)productId: {}, product: {}", productId, product);
        BaseApiResult result = new BaseApiResult();

        try {
            if(!"".equals(product.getName()) && !"".equals(product.getDescription())) {
                // check existed product
                Product existProduct = productService.findById(productId);
                if(existProduct == null) {
                    result.setSuccess(false);
                    result.setMessage("Invalid model");
                } else {
                    existProduct.setId(productId);
                    existProduct.setType(product.getType());
                    existProduct.setName(product.getName());
                    existProduct.setDescription(product.getDescription());
                    existProduct.setSupport(product.getSupport());
                    existProduct.setCreatedDate(new Date());
                    existProduct.setPrice(product.getPrice());
                    existProduct.setDelivery(product.getDelivery());
                    productService.save(existProduct);
                    result.setSuccess(true);
                    result.setMessage("Update product successfully");
                }
            } else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
            log.error("(updateProduct)ex: {}", ExceptionUtil.getFullStackTrace(e));
            result.setSuccess(false);
            result.setMessage(ExceptionUtil.getFullStackTrace(e));
        }

        return result;
    }

    @PostMapping("/delete-product/{productId}")
    public BaseApiResult deleteProduct(@PathVariable int productId) {
        log.info("(deleteProduct)productId: {}", productId);
        BaseApiResult result = new BaseApiResult();

        try {
            productService.delete(productId);
            result.setSuccess(true);
            result.setMessage("Delete product successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(ExceptionUtil.getFullStackTrace(e));
            log.error("(deleteProduct)ex: {}", ExceptionUtil.getFullStackTrace(e));
        }

        return result;
    }
}
