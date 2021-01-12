package oit.is.group7.keiji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.ArrayList;

import oit.is.group7.keiji.service.UserRegisterService;
import oit.is.group7.keiji.model.UserInfo;
import oit.is.group7.keiji.model.UserInfoMapper;

@Controller
public class RegisterController {

  @Autowired
  UserInfoMapper userInfoMapper;

  @Autowired
  UserRegisterService newUser;

  @GetMapping("/register")
  public String register() {
    return "register.html";
  }

  /**
   * @param model
   * @return
   */
  @PostMapping("/register/addUser")
  @Transactional
  public String addUser(@RequestParam String username, @RequestParam String password, ModelMap model) {
    if (userInfoMapper.selectByUser(username) == null) {
      UserInfo userInfo = newUser.setUserInfo(username, password);
      userInfoMapper.insertUserInfo(userInfo);
      model.addAttribute("userInfo", userInfo);
    } else {
      model.addAttribute("errorMessage", "そのユーザー名はすでに使用されています");
    }
    return "register.html";
  }
}
