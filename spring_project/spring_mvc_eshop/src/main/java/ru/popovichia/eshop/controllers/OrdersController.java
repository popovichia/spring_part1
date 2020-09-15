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
            @RequestParam(name = "customerFirstName", required = true) String customerFirstName,
            @RequestParam(name = "customerLastName", required = true) String customerLastName,
            @RequestParam(name = "customerPhoneNumber", required = true) String customerPhoneNumber,
            @RequestParam(name = "customerEmail", required = true) String customerEmail
    ) {
        serviceImpl.createOrder(
                customerFirstName,
                customerLastName,
                customerPhoneNumber,
                customerEmail
        );
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
            @RequestParam(name = "customerNewPhoneNumber", required = true) String customerNewPhoneNumber,
            @RequestParam(name = "customerNewEmail", required = true) String customerNewEmail
    ) {
        serviceImpl.updateOrderById(
                id,
                customerNewFirstName,
                customerNewLastName,
                customerNewPhoneNumber,
                customerNewEmail
        );
        serviceImpl.setEditingOrder(null);
        return "redirect:./";
    }
    
    @PostMapping(path = "/addOrderItemToOrder")
    public String addOrderItemToOrder(
            @RequestParam(required = true) Long orderId,
            @RequestParam(required = true) Long productId,
            @RequestParam(required = true) int productCount
    ) {
        serviceImpl.addOrderItemToOrder(orderId, productId, productCount);
        return "redirect:./";
    }
    
    @DeleteMapping(path = "/deleteOrderById")
    public String deleteOrderById(
            @RequestParam(name = "id", required = true) Long id
    ) {        
        serviceImpl.deleteOrderById(id);
        return "redirect:./";
    }
    
    @DeleteMapping(path = "/deleteOrderItemById")
    public String deleteOrderItemById(
            @RequestParam(name = "id", required = true) Long id
    ) {
        serviceImpl.deleteOrderItemById(id);
        return "redirect:./";
    }

}
