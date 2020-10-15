package ru.popovichia.eshop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.popovichia.eshop.entities.Product;
import ru.popovichia.eshop.entities.ProductDto;
import ru.popovichia.eshop.services.DataService;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@RestController
@RequestMapping(path = "/rest/")
public class ProductsRestController {
    
    @Autowired
    private DataService dataService;
    
    @GetMapping(path = "getAllProducts")
    public List<ProductDto> getAllProducts() {
        return dataService.getAllProductDtos();
    }

//    @PostMapping(path = "/saveProductById")
//    public String saveProductById(
//            @Valid Product product,
//            @RequestParam(name = "id", required = false) Long id,
//            BindingResult bindingResult
//    ) {
//        if (bindingResult.hasErrors()) {
//            return PRODUCTS_VIEWS_DIR + "products";
//        }
//        dataService.saveProductById(id, product);
//        return "redirect:./getAllProducts";
//    }
//    
//    @DeleteMapping(path = "/deleteProductById")
//    public String deleteProductById(
//            @RequestParam(name = "id", required = true) Long id
//    ) {
//        dataService.deleteProductById(id);
//        return "redirect:./getAllProducts";
//    }

}
