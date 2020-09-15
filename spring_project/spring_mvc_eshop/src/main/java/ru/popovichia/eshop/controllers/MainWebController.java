package ru.popovichia.eshop.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.popovichia.eshop.services.ServiceImpl;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Controller
@RequestMapping(path = "/")
public class MainWebController {
    
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired
    private ServiceImpl serviceImpl;
    
    @GetMapping(path = "")
    public String getIndex(
            Model model
    ) {
        model.addAttribute("listOrders", serviceImpl.getAllOrders());        
        model.addAttribute("editingOrder", serviceImpl.getEditingOrder());
        model.addAttribute("listOrdersItems", serviceImpl.getAllOrdersItems());
        model.addAttribute("listProducts", serviceImpl.getAllProducts());
        model.addAttribute("editingProduct", serviceImpl.getEditingProduct());
        return "index";
    }
    
}
