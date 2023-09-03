package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity

public class Customer {

    @Id
//    @Column(name = "id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "name",length = 50)
    private String name;

//    @Column(name = "email",length = 100)
    private String email;


    // Relationer 1:m

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private Set<Orders> orders = new HashSet<>();


    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @PrePersist
    public void verifyEmail()
    {
        if(!email.contains("@"))
        {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    @PreUpdate
    public void verifyEmailUpdate()
    {
        if(!validEmail())
        {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    private boolean validEmail()
    {
        return Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",email);
    }
}
