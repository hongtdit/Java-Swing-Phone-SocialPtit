package illidan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDataModel {

    private String name;
    private String type;
    private String description;
    private String support;
    private double price;
    private String delivery;
    private int kind;

}
