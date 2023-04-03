package illidan.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String name;

    @Column(name = "type_name")
    private String type;

    @Column(name = "short_desc")
    private String description;

    private String support;

    @Column(name = "created_date")
    private Date createdDate;

    private double price;

    private String delivery;

    private int kindOfService;

}
