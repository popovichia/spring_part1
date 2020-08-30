/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.springmvc.repositories;

import java.util.ArrayList;
import java.util.List;
import ru.popovichia.springmvc.entities.Product;

/**
 *
 * @author Igor Popovich, email: popovichia@gmail.com, phone: +7 913 902 36 36,
 * company: mts.ru
 */
public class ProductsRepository {
    
    private List<Product> listProducts;
    private static long productId = 0L;
    
    public ProductsRepository() {
        addProductToRepository(new Product("Продукт1", 11.5F));
        addProductToRepository(new Product("Продукт2", 12.6F));
        addProductToRepository(new Product("Продукт3", 13.7F));
        addProductToRepository(new Product("Продукт4", 14.8F));
        addProductToRepository(new Product("Продукт5", 15.9F));        
    }
    
    public boolean addProductToRepository(Product product) {
        product.setId(productId);
        productId++;
        if (getListProducts() == null) {
            this.listProducts = new ArrayList<>();
        }
        return getListProducts().add(product);
    }
    
    public List<Product> getAllProducts() {
        return listProducts;
    }
    
    public Product getProductById(long id) {
        Product result = null;
        if (getListProducts() != null) {
            for (Product product : getListProducts()) {
                if (product.getId() == id) {
                    result = product;
                    break;
                }
            }
        }
        return result;
    }

    private List<Product> getListProducts() {
        return listProducts;
    }
}
