package illidan.data.service;


import illidan.data.model.Order;

import java.util.ArrayList;

public interface OrderService {
    ArrayList<Order> findAll();

//    List<Message> search(String term);

    Order findOne(Integer id);

    Order findByEmail(String email);

    void save(Order order);

    void delete(Integer id);
}
