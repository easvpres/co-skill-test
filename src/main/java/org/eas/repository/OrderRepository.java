package org.eas.repository;

import org.eas.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author eas
 */
@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    Optional<UserOrder> findOneByNumber(String number);
}
