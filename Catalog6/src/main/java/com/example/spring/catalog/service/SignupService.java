package com.example.spring.catalog.service;

import com.example.spring.catalog.entity.Merchant;
import com.example.spring.catalog.entity.Role;
import com.example.spring.catalog.entity.User;
import com.example.spring.catalog.model.Signup;
import com.example.spring.catalog.repository.MerchantRepository;
import com.example.spring.catalog.repository.RoleRepository;
import com.example.spring.catalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SignupService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public User registerMerchant(Signup signup) {
        Role role = roleRepository.findByName("ROLE_MERCHANT");
        User user = signup.toUser();
        user.setRoles(Stream.of(role).collect(Collectors.toSet()));
        user = userRepository.save(user);

        Merchant merchant = new Merchant();
        merchant.setUser(user);
        merchant.setName(signup.getName());
        merchant.setMerchantCode(signup.getMerchantCode());
        merchant = merchantRepository.save(merchant);
        user.setMerchant(merchant);

        return user;
    }
}
