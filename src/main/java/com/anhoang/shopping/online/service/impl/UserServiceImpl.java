package com.anhoang.shopping.online.service.impl;

import com.anhoang.shopping.online.model.UserDtls;
import com.anhoang.shopping.online.respository.UserRespository;
import com.anhoang.shopping.online.service.UserService;
import com.anhoang.shopping.online.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
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
        user.setIsEnable(true);
        user.setAccountNonLocked(true);
        user.setFailedAttempt(0);
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
            userDtls.setIsEnable(status);
            userRespository.save(userDtls);
            return true;
        }
        return false;
    }

    @Override
    public void increaseFailedAttempt(UserDtls user) {
        int attempt = user.getFailedAttempt() + 1;
        user.setFailedAttempt(attempt);
        userRespository.save(user);
    }

    @Override
    public void userAccountLock(UserDtls user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        userRespository.save(user);
    }

    @Override
    public boolean unlockAccountTimeExpired(UserDtls user) {
        long lockTime = user.getLockTime().getTime();
        long unlockTime = lockTime + AppConstant.UNLOCK_DURATION_TIME;
        long currentTime = System.currentTimeMillis();
        if(unlockTime < currentTime) {
            user.setAccountNonLocked(true);
            user.setFailedAttempt(0);
            user.setLockTime(null);
            userRespository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void resetAttempt(int userId) {

    }

    @Override
    public void updateUserResetToken(String email, String resetToken) {
        UserDtls findByEmail = userRespository.findByEmail(email);
        findByEmail.setResetToken(resetToken);
        userRespository.save(findByEmail);
    }

    @Override
    public UserDtls getUserByToken(String token) {
        return userRespository.findByResetToken(token);
    }

    @Override
    public UserDtls updateUser(UserDtls user) {
        return userRespository.save(user);
    }

    @Override
    public UserDtls updateUserProfile(UserDtls user, MultipartFile img) {
        UserDtls dbUser = userRespository.findById(user.getId()).get();
        if(!img.isEmpty()) {
            dbUser.setProfileImage(img.getOriginalFilename());
        }
        if(!ObjectUtils.isEmpty(dbUser)) {
            dbUser.setName(user.getName());
            dbUser.setMobileNumber(user.getMobileNumber());
            dbUser.setAddress(user.getAddress());
            dbUser.setCity(user.getCity());
            dbUser.setState(user.getState());
            dbUser.setPincode(user.getPincode());
            dbUser= userRespository.save(dbUser);
        }
        try {
            if(!img.isEmpty()) {
                File saveFile = new ClassPathResource("static/img").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator
                        + img.getOriginalFilename());

                //System.out.println(path);
                Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dbUser;
    }


}
