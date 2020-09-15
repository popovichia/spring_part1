package ru.popovichia.eshop.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Controller
@RequestMapping(path = "/terminal")
public class TerminalController {
    
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    
    @GetMapping(path = "")
    public String openTerminalPage() {
        return "terminal";
    }

    @PostMapping(path = "")
    public void runCommand() {
        LOGGER.info("RUN_COMMAND");
    }
    
}
