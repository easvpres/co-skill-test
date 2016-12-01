package org.eas.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author eas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_order")
@JsonIgnoreProperties(value = "id")
public class UserOrder {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number", length = 20)
    private String number;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> products;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id", nullable=false, updatable=false)
    private User user;
}
