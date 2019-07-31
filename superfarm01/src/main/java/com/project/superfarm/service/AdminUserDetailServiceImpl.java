package com.project.superfarm.service;

import com.project.superfarm.entity.MarketAdmin;
import com.project.superfarm.repository.MarketAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class AdminUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private MarketAdminRepository marketAdminRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MarketAdmin marketAdmin=marketAdminRepository.findByAdminid(username);
        if(marketAdmin==null){
            throw new UsernameNotFoundException(username);
        }
        return marketAdmin;
    }
}
