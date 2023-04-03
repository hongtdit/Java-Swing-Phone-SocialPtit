package illidan.data.service;

import illidan.data.model.Product;
import illidan.data.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ArrayList<Product> findAll() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    @Override
    public ArrayList<Product> search(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findFirstById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
