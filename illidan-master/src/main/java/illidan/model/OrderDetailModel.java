package illidan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailModel {
    private String name;
    private String email;
    private String urlApp;
    private String description;
    private String keyWords;
    private double price;

//    @Override
//    public String toString() {
//        return "OrderDetailModel{" +
//                "name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", urlApp='" + urlApp + '\'' +
//                ", description='" + description + '\'' +
//                ", keyWords='" + keyWords + '\'' +
//                ", price=" + price +
//                '}';
//    }

}
