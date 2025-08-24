package com.anhoang.shopping.online.service.impl;

import com.anhoang.shopping.online.model.UserDtls;
import com.anhoang.shopping.online.respository.UserRespository;
import com.anhoang.shopping.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDtls saveUser(UserDtls user) {
        user.setRole("ROLE_USER");
        user.setEnable(true);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        UserDtls saveUser = userRespository.save(user);
        return saveUser;
    }

    @Override
    public UserDtls getUserByEmail(String email) {
        return userRespository.findByEmail(email);
    }

    @Override
    public List<UserDtls> getUsers(String role){
        return userRespository.findByRole(role);
    }

    @Override
    public Boolean updateAccountStatus(Integer id, Boolean status) {
        Optional<UserDtls> findByUser = userRespository.findById(id);
        if(findByUser.isPresent()){
            UserDtls userDtls = findByUser.get();
            userDtls.setEnable(status);
            userRespository.save(userDtls);
            return true;
        }
        return false;
    }


}
