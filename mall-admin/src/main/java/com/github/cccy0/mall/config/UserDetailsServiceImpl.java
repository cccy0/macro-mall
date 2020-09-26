package com.github.cccy0.mall.config;

import com.github.cccy0.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Zhai
 * 2020/9/26 15:53
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UmsAdminService umsAdminService;

    @Autowired
    public void setUmsAdminService(UmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return umsAdminService.loadUserByUsername(username);
    }
}
