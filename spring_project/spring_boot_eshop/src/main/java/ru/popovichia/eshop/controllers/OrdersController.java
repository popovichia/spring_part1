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
    
    private final static String ORDERS_VIEWS_DIR = "orders/";
    
    @Autowired
    private DataService dataService;
    
    @GetMapping(path = "/getAllOrders")
    public String getAllOrders(Model model) {
        model.addAttribute("listOrders", dataService.getAllOrders());
        return ORDERS_VIEWS_DIR + "orders";
    }

    @GetMapping(path = "/getOrderById")
    public String getOrderById(
            Model model,
            @RequestParam(name = "id", required = false) Long id
    ) {
        Order order = null;
        Customer customer = null;
        if (id != null && id > 0) {
            order = dataService.getOrderById(id);
            customer = order.getCustomer();
        } else {
            order = new Order();
            customer = new Customer();
            order.setCustomer(customer);
        }
        model.addAttribute("orderId", order.getId());
        model.addAttribute("customer", order.getCustomer());
        model.addAttribute("listOrderItems", order.getListOrderItems());
        return ORDERS_VIEWS_DIR + "order";
    }

    @PostMapping(path = "/saveOrderById")
    public String saveOrderById(
            Model model,
            @RequestParam(name = "id", required = false) Long id,
            @Valid Customer customer,
            BindingResult bindingResult
    ) {
        model.addAttribute("orderId", id);
        if (bindingResult.hasErrors()) {
            return ORDERS_VIEWS_DIR + "order";
        }
        dataService.saveOrderById(id, customer);
        return "redirect:/getAllOrders";
    }
        
    @DeleteMapping(path = "/deleteOrderById")
    public String deleteOrderById(
            @RequestParam(name = "id", required = true) Long id
    ) {        
        dataService.deleteOrderById(id);
        return "redirect:./getAllOrders";
    }
    
    @PostMapping(path = "/addOrderItemToOrder")
    public String addOrderItemToOrder(
            @RequestParam(name = "orderId", required = true) Long orderId,
            @RequestParam(name = "productId", required = true) Long productId,
            @RequestParam(name = "productCount", required = true) Integer productCount
    ) {
        dataService.addOrderItemToOrder(orderId, productId, productCount);
        return "redirect:./getOrderById?id=" + orderId;
    }
    
    @DeleteMapping(path = "/deleteOrderItemById")
    public String deleteOrderItemById(
            @RequestParam(name = "id", required = true) Long id
    ) {
        Order order = dataService.getOrderById(id);
        dataService.deleteOrderItemById(id);
        return "redirect:./getOrderById?id=" + order.getId();
    }

}
