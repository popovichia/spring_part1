package ru.popovichia.eshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.popovichia.eshop.services.DataService;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Controller
@RequestMapping(path = "/")
public class CustomersController {

    private final static String CUSTOMERS_VIEWS_DIR = "/customers/";
    
    @Autowired
    private DataService dataService;
    
    @GetMapping(path = "/getAllCustomers")
    public String getAllCustomers(
            Model model
    ) {
        model.addAttribute("listCustomers", dataService.getAllCustomersSortedByFullNameAsc());
        return CUSTOMERS_VIEWS_DIR + "customers";
    }

}
