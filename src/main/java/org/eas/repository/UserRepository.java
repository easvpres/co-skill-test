package org.eas.repository;

import org.eas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author eas
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNumber(String number);
}
