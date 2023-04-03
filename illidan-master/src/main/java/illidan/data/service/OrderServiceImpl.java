package illidan.data.service;

import illidan.controller.api.AdminApiController;
import illidan.data.model.Order;
import illidan.data.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ArrayList<Order> findAll() {
        return (ArrayList<Order>) orderRepository.findAll();
    }

//    @Override
//    public List<Message> search(String term) {
//        return orderRepository.findByNameContaining(term);
//    }

    @Override
    public Order findOne(Integer id) {
        return orderRepository.findFirstById(id);
    }

    @Override
    public Order findByEmail(String email) {
        return orderRepository.findFirstByEmail(email);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
