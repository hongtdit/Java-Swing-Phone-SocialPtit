package illidan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDataModel {
    private int id;
    private String email;
    private String urlApp;
    private String keyWord;
    private String createdDate;
    private double price;
    private String status;

}
