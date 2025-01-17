package com.lcaohoanq.samplewebapplication.repositories;

import com.lcaohoanq.samplewebapplication.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailAndPassword(String username, String password);

  Optional<User> findByEmail(String email);
  Optional<User> findByPhoneNumber(String phoneNumber);

  @Modifying
  @Query("UPDATE User u SET u.isActive = false WHERE u.id = :id")
  void softDeleteUser(Long id);

  @Modifying
  @Query("UPDATE User u SET u.isActive = true WHERE u.id = :id")
  void restoreUser(Long id);

}
