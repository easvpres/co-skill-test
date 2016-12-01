package org.eas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author eas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@JsonIgnoreProperties(value = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "article", length = 30)
    private String article;

    @Column(name = "price")
    private Double price;
}
