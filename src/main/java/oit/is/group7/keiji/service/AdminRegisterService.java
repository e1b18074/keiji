package oit.is.group7.keiji.service;

import java.util.ArrayList;
import java.util.Collection;

import oit.is.group7.keiji.model.UserInfo;
import oit.is.group7.keiji.security.seclogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository ;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class AdminRegisterService {

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserInfo setAdminInfo(String username, String password){
      UserInfo newAdmin = new UserInfo(username, passwordEncoder.encode(password), setAdminRole());
      newAdmin.setRole("ROLE_ADMIN");
      return newAdmin;
    }

    public Collection<GrantedAuthority> setAdminRole(){
      return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
    }

}
