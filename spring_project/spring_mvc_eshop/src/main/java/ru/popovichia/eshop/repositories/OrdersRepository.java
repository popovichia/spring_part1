/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.eshop.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.popovichia.eshop.entities.Customer;
import ru.popovichia.eshop.entities.Order;
import ru.popovichia.eshop.entities.OrderItem;
import ru.popovichia.eshop.entities.Product;

/**
 *
 * @author Igor Popovich, email: popovichia@gmail.com, phone: +7 913 902 36 36,
 * company: mts.ru
 */
public class OrdersRepository {
    
    @Autowired
    private EntityManager entityManager;
    
    public OrdersRepository() {
    }

    public List<Order> getAllOrders() {
        return entityManager
                .createQuery("from Order")
                .getResultList();
    }

    public List<OrderItem> getAllOrdersItems() {
        return entityManager
                .createQuery("from OrderItem")
                .getResultList();
    }
        
    public void createOrder(
            String customerFirstName,
            String customerLastName,
            String customerPhoneNumber
    ) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Customer customer = new Customer(customerFirstName, customerLastName, customerPhoneNumber);
        Order order = new Order(customer);
        entityTransaction.begin();
        entityManager.persist(customer);
        entityManager.persist(order);
        entityTransaction.commit();
    }
    
    public void updateOrderById(
            Long id,
            String customerNewFirstName,
            String customerNewLastName,
            String customerNewPhoneNumber
    ) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Order order = this.getOrderById(id);
        Customer customer = order.getCustomer();
        customer.setFirstName(customerNewFirstName);
        customer.setLastName(customerNewLastName);
        customer.setPhoneNumber(customerNewPhoneNumber);
        entityTransaction.begin();
        entityManager.persist(customer);
        entityTransaction.commit();
    }
    
    public void deleteOrderById(long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Order order = this.getOrderById(id);
        entityTransaction.begin();
        entityManager.remove(order);
        entityTransaction.commit();
    }

    public Order getOrderById(long id) {
        return entityManager
                .createQuery("from Order where id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public void addItemToOrder(
            Product product,
            Order order,
            int productCount
    ) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setProductCount(productCount);
        orderItem.setProductPrice(product.getPrice());
        entityTransaction.begin();
        entityManager.persist(orderItem);
        entityTransaction.commit();
    }
}
