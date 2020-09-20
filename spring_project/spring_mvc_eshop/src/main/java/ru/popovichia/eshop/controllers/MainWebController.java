package ru.popovichia.eshop.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.popovichia.eshop.services.DataService;

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
    private DataService dataService;
    
    @GetMapping(path = "")
    public String getIndex(
            Model model
    ) {
        model.addAttribute("listOrdersItems", dataService.getAllOrdersItems());
        return "index";
    }
    
    @PostMapping(path = "")
    public String postIndex(
            Model model,
            @RequestParam(name = "inputStringPattern", required = true) String inputStringPattern
    ) {
        model.addAttribute("listOrdersItems", dataService.getOrdersByPattern(inputStringPattern));
        return "index";
    }

}
