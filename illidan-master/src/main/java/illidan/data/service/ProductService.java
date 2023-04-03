package illidan.data.service;

import illidan.data.model.Product;

import java.util.ArrayList;

public interface ProductService {
    ArrayList<Product> findAll();

    ArrayList<Product> search(String name);

    Product findById(int id);

    void save(Product contact);

    void delete(Integer id);
}
