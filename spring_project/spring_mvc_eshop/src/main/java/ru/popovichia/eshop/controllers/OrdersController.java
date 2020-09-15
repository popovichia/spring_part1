package ru.popovichia.eshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.popovichia.eshop.entities.Order;
import ru.popovichia.eshop.services.ServiceImpl;

@Controller
@RequestMapping(path = "/")
public class OrdersController {
    
    @Autowired
    private ServiceImpl serviceImpl;
    
    @PostMapping(path = "/createOrder")
    public String createOrder(
            @RequestParam(required = true) String customerFirstName,
            @RequestParam(required = true) String customerLastName,
            @RequestParam(required = true) String customerPhoneNumber
    ) {
        serviceImpl.createOrder(customerFirstName, customerLastName, customerPhoneNumber);
        return "redirect:./";
    }
    
    @PostMapping(path = "/setEditingOrder")
    public String setEditingOrder(
            @RequestParam(name = "id", required = true) Long id
    ) {
        serviceImpl.setEditingOrder(id);
        return "redirect:./";
    }
    
    @GetMapping(path = "/getEditingOrder")
    public Order getEditingOrder() {
        return serviceImpl.getEditingOrder();
    }
    
    @PutMapping(path = "/updateOrderById")
    public String updateOrderById(
            @RequestParam(name = "id", required = true) Long id,
            @RequestParam(name = "customerNewFirstName", required = true) String customerNewFirstName,
            @RequestParam(name = "customerNewLastName", required = true) String customerNewLastName,
            @RequestParam(name = "customerNewPhoneNumber", required = true) String customerNewPhoneNumber
    ) {
        serviceImpl.updateOrderById(id, customerNewFirstName, customerNewLastName, customerNewPhoneNumber);
        serviceImpl.setEditingOrder(null);
        return "redirect:./";
    }
    
    @PostMapping(path = "/addItemToOrder")
    public String addItemToOrder(
            @RequestParam(required = true) long orderId,
            @RequestParam(required = true) long productId,
            @RequestParam(required = true) int productCount
    ) {
        serviceImpl.addItemToOrder(orderId, productId, productCount);
        return "redirect:./";
    }
    
    @DeleteMapping(path = "/deleteOrderById")
    public String deleteOrderById(
            @RequestParam(name = "id") long id
    ) {        
        serviceImpl.deleteOrderById(id);
        return "redirect:./";
    }

}
