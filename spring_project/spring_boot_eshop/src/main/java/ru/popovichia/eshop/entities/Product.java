package ru.popovichia.eshop.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Igor Popovich, email: popovichia@gmail.com, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank
    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Digits(fraction = 2, integer = 6)
    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> listOrderItems;

    public Product() {
    }

    public Product(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderItem> getListOrderItems() {
        return listOrderItems;
    }

    public void setListOrderItems(List<OrderItem> listOrderItems) {
        this.listOrderItems = listOrderItems;
    }

}
