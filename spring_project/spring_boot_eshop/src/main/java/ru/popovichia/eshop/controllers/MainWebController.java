package ru.popovichia.eshop.controllers;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    private static final Logger LOG = Logger.getLogger(MainWebController.class.getName());
    
    private Integer pageSize = 5;

    @Autowired
    private DataService dataService;
    
    @GetMapping(path = "")
    public String getMain(
            Model model,
            @RequestParam(name = "pageIndex", required = false) Integer pageIndex,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        if (pageSize != null && pageSize > 0) {
            this.setPageSize(pageSize);
        }
        pageIndex = pageIndex == null ? 1 : pageIndex;
        model.addAttribute("listOrdersItems", dataService.getAllOrdersItems(pageIndex, this.pageSize));
        model.addAttribute("pagesCount", dataService.getOrdersItemsPagesCount(this.pageSize));
        model.addAttribute("rowsCount", dataService.getOrdersItemsCount());
        model.addAttribute("pageSize", this.pageSize);
        return "index";
    }
    
    @GetMapping(path = "/searchByStringPattern")
    public String searchByStringPattern(
            Model model,
            @RequestParam(name = "inputStringPattern", required = false) String inputStringPattern
    ) {
        if (inputStringPattern.isEmpty()) {
            return "redirect:./";
        }
        model.addAttribute("listOrdersItems", dataService.getOrdersItemsLikePattern(inputStringPattern));
        model.addAttribute("pagesCount", dataService.getOrdersItemsPagesCount(this.pageSize));
        model.addAttribute("rowsCount", dataService.getOrdersItemsCount());
        model.addAttribute("pageSize", this.pageSize);
        return "index";
    }
    
    private void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
}