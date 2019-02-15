package com.example.springbootdemo.service;

import com.example.springbootdemo.common.BusinessException;
import com.example.springbootdemo.common.ErrorCode;
import com.example.springbootdemo.entity.MyUserPrincipal;
import com.example.springbootdemo.entity.Privilege;
import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService  implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User myUser = userService.getUser(s);
        List<Role> roles = myUser.getRoles();
        if(roles == null || roles.size() == 0){
            logger.info("[login]用户未绑定角色。{}", s);
            throw new BusinessException(ErrorCode.E_101005);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles){
            //获取角色
            List<Privilege> privileges = role.getPrivileges();
            //如果该角色的权限为空，则跳过
            if(privileges == null || privileges.size() == 0){
                continue;
            }
            for(Privilege p : privileges){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(p.getPrivilegeName());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
            }
        }
        if(grantedAuthorities.size() == 0){
            logger.info("[login]用户无任何权限。{}", myUser);
            throw new BusinessException(ErrorCode.E_101006);
        }

        return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(), grantedAuthorities);
    }
}
