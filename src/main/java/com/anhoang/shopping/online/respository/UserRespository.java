package com.anhoang.shopping.online.respository;

import com.anhoang.shopping.online.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRespository extends JpaRepository<UserDtls, Integer> {
    public UserDtls findByEmail(String email);

    public List<UserDtls> findByRole(String role);
    public UserDtls findByResetToken(String token);
}
