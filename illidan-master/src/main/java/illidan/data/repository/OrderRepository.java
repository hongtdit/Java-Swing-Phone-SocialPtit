package illidan.data.repository;

import illidan.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findFirstById(Integer id);

    Order findFirstByEmail(String email);

    @Transactional
    void deleteById(Integer id);
}
