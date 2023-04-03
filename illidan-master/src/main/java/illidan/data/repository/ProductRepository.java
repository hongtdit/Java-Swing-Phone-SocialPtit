package illidan.data.repository;

import illidan.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(@Param("id") int id);

    Product findFirstById(Integer id);

    ArrayList<Product> findAllByNameContaining(String name);

    @Transactional
    void deleteById(Integer id);

}
