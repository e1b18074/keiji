package oit.is.group7.keiji.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;

import oit.is.group7.keiji.model.UserInfo;
import oit.is.group7.keiji.model.UserInfoMapper;

public class SecurityUserDetails extends User {


  @Autowired
  UserInfoMapper userInfoMapper;

    private final UserInfo userInfo;

    public SecurityUserDetails(UserInfo userInfo, String password) {
        super(userInfo.getName(), password, Collections.emptyList());
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
