package com.halil.bookstoreapi.adapter.jpa.shopping;

import com.halil.bookstoreapi.adapter.common.DeliveryStatus;
import com.halil.bookstoreapi.adapter.jpa.customer.CustomerEntity;
import com.halil.bookstoreapi.adapter.jpa.shoppinglist.ShoppingItem;
import com.halil.bookstoreapi.adapter.common.OrderList;
import com.halil.bookstoreapi.domain.shopping.Shopp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "shopping")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ShoppingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "shoppingEntity", fetch = FetchType.EAGER)
    private List<ShoppingItem> items;


    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus deliveryStatus;

    public Shopp convertToShop(){
        Shopp shopp = new Shopp();
        shopp.setCustomerId(customer.getId());
        shopp.setTotalPrice(totalPrice);
        shopp.setDeliveryStatus(deliveryStatus);
        shopp.setOrderLists(items.stream().map(e -> {
            OrderList orderList= new OrderList();
            orderList.setProductId(e.getProductEntity().getId());
            orderList.setQuantity(e.getQuantity());
            return orderList;
        }).collect(Collectors.toList()));
        return shopp;
    }
}
