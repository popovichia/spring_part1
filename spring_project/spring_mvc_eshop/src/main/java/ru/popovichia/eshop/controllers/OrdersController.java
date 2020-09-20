package ru.popovichia.eshop.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.popovichia.eshop.entities.Customer;
import ru.popovichia.eshop.entities.Order;
import ru.popovichia.eshop.services.DataService;

@Controller
@RequestMapping(path = "/")
public class OrdersController {
    
    @Autowired
    private DataService dataService;
    
    @GetMapping(path = "/getAllOrders")
    public String getAllOrders(Model model) {
        model.addAttribute("listOrders", dataService.getAllOrders());
        return "orders";
    }

    @GetMapping(path = "/getOrderById")
    public String getOrderById(
            Model model,
            @RequestParam(name = "id", required = false) Long id
    ) {
        Order order;
        Customer customer;
        if (id != null) {
            order = dataService.getOrderById(id);
            customer = order.getCustomer();
        } else {
            order = new Order();
            customer = new Customer();
            order.setCustomer(customer);
        }
        model.addAttribute("orderId", order.getId());
        model.addAttribute("customer", order.getCustomer());
        return "order";
    }

    @PostMapping(path = "/saveOrderById")
    public String saveOrderById(
            Model model,
            @RequestParam(name = "id", required = true) Long id,
            @Valid Customer customer,
            BindingResult bindingResult
    ) {
        model.addAttribute("orderId", id);
        if (bindingResult.hasErrors()) {
            return "order";
        }
        dataService.saveOrderById(id, customer);
        return "redirect:/getAllOrders";
    }
    
    @PostMapping(path = "/setEditingOrder")
    public String setEditingOrder(
            @RequestParam(name = "id", required = true) Long id
    ) {
        dataService.setEditingOrder(id);
        return "redirect:./";
    }
    
    @GetMapping(path = "/getEditingOrder")
    public Order getEditingOrder() {
        return dataService.getEditingOrder();
    }
    
    @GetMapping(path = "/editOrderById")
    public String editOrderById(
            @RequestParam(name = "id", required = true) Long id,
            @Valid Order editingOrder,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        dataService.updateOrderById(id, editingOrder);
        dataService.setEditingOrder(null);
        return "redirect:./";
    }
    
    @PostMapping(path = "/addOrderItemToOrder")
    public String addOrderItemToOrder(
            @RequestParam(required = true) Long orderId,
            @RequestParam(required = true) Long productId,
            @RequestParam(required = true) int productCount
    ) {
        dataService.addOrderItemToOrder(orderId, productId, productCount);
        return "redirect:./";
    }
    
    @DeleteMapping(path = "/deleteOrderById")
    public String deleteOrderById(
            @RequestParam(name = "id", required = true) Long id
    ) {        
        dataService.deleteOrderById(id);
        return "redirect:./";
    }
    
    @DeleteMapping(path = "/deleteOrderItemById")
    public String deleteOrderItemById(
            @RequestParam(name = "id", required = true) Long id
    ) {
        dataService.deleteOrderItemById(id);
        return "redirect:./";
    }

}
