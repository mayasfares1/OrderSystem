package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity

public class Orders {

    @Id
//    @Column(name = "Order_id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate created;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private Set<OrderLine> orderLine = new HashSet<>();

    public Orders(LocalDate name) {
        this.created = created;
    }

     public void setCustomer(Customer customer){
        this.customer = customer;
        if (customer != null){
            customer.getOrders().add(this);
        }
    }



    public void addOrderLine(OrderLine order_line){
        this.orderLine.add(order_line);
        if (order_line != null){
            order_line.setOrders(this);
        }
    }


    public void addCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            customer.getOrders().add(this);
        }
    }
}

