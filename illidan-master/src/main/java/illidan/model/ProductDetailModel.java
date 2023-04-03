package illidan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailModel {
    private int id;
    private String name;
    private String type;
    private String description;
    private String support;
    private Date createdDate;
    private double price;
    private String delivery;
    private int kindOfService;

}
