package ru.popovichia.eshop.controllers;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.popovichia.eshop.services.TerminalDataService;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Controller
@RequestMapping(path = "/terminal")
public class TerminalController {
    
    private static final Logger LOG = Logger.getLogger(TerminalController.class.getName());
    private final static String TERMINAL_VIEWS_DIR = "terminal/";
    
    @Autowired
    private TerminalDataService terminalDataService;
    
    @GetMapping(path = "")
    public String openTerminalPage(Model model) {
        model.addAttribute("listCustomers", terminalDataService.getAllCustomers().toString());
        return TERMINAL_VIEWS_DIR + "terminal";
    }

    @PostMapping(path = "")
    public void runCommand() {
        LOG.info("RUN_COMMAND");
    }
    
}
