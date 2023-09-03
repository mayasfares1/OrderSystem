package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private Integer quantity;

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private Product product;


    public OrderLine(Integer quantity) {
        this.quantity = quantity;
    }
}
