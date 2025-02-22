package com.myblog.repository;

import com.myblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // 쿼리문으로는 From user Where email = #{email}

}
