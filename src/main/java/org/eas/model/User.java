package org.eas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author eas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "balance")
    private Double balance;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name="user_id")
//    private List<UserOrder> orders;
}
