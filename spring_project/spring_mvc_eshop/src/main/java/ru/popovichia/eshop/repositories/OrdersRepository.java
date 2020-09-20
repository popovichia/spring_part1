/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.eshop.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.popovichia.eshop.entities.Order;

/**
 *
 * @author Igor Popovich, email: popovichia@gmail.com, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Repository
public interface OrdersRepository extends org.springframework.data.repository.Repository<Order, Long> {
    
    public List<Order> findAll();
    public Order findById(Long id);
    public void save(Order order);

}