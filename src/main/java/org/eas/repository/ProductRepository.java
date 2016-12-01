package org.eas.repository;

import org.eas.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author eas
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByArticleIn(List<String> numbers);
}
