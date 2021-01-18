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
import java.util.Date;
import java.text.SimpleDateFormat;

import oit.is.group7.keiji.service.AdminRegisterService;
import oit.is.group7.keiji.security.AuthUtil;
import oit.is.group7.keiji.model.UserInfo;
import oit.is.group7.keiji.model.UserInfoMapper;

@Controller
public class ModifyController {

  @Autowired
  UserInfoMapper userInfoMapper;

  @Autowired
  AdminRegisterService newAdmin;

  /**
   * @param model
   * @return
   */
  @GetMapping("/modify")
  @Transactional
  public String register(ModelMap model) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo loginUser = userInfoMapper.selectByUser(AuthUtil.getUserInfo().getName());
    loginUser.setDate(loginDate);
    userInfoMapper.updateLoginDate(loginUser);
    model.addAttribute("loginUser", loginUser);
    ArrayList<UserInfo> roleUsers = userInfoMapper.selectByRole("ROLE_USER");
    model.addAttribute("userInfo", roleUsers);
    ArrayList<UserInfo> AdminUserInfo = userInfoMapper.selectByRole("ROLE_ADMIN");
    model.addAttribute("adminUserInfo", AdminUserInfo);
    return "modification.html";
  }

  /**
   * @param model
   * @return
   */
  @PostMapping("/modify/nameChange")
  @Transactional
  public String nameChange(@RequestParam int number, @RequestParam String username, ModelMap model) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);

    UserInfo loginUser = userInfoMapper.selectByNumber(number);
    loginUser.setDate(loginDate);
    userInfoMapper.updateLoginDate(loginUser);

    if (loginUser.getRole().equals("ROLE_MASTER")) {
      model.addAttribute("changeMessage", "ロール名がMASTERのユーザは変更できません");

    } else if (userInfoMapper.selectByUser(username) == null) {
      if (username.isEmpty()) {
        model.addAttribute("changeMessage", "ユーザー名を入力してください");
      } else {
        loginUser.setName(username);
        userInfoMapper.updateName(loginUser);
        model.addAttribute("changeMessage", "変更しました");
      }
    } else {
      model.addAttribute("changeMessage", "そのユーザー名はすでに使用されています");
    }

    model.addAttribute("loginUser", loginUser);
    ArrayList<UserInfo> roleUsers = userInfoMapper.selectByRole("ROLE_USER");
    model.addAttribute("userInfo", roleUsers);
    ArrayList<UserInfo> AdminUserInfo = userInfoMapper.selectByRole("ROLE_ADMIN");
    model.addAttribute("adminUserInfo", AdminUserInfo);
    return "modification.html";
  }

  /**
   * @param model
   * @return
   */
  @PostMapping("/modify/deleteUser")
  @Transactional
  public String deleteUser(@RequestParam int number, ModelMap model){
    UserInfo loginUser = userInfoMapper.selectByUser(AuthUtil.getUserInfo().getName());
    model.addAttribute("loginUser", loginUser);

    if (userInfoMapper.selectByNumber(number) != null) {
      UserInfo userInfo = userInfoMapper.selectByNumber(number);
      if (userInfo.getRole().equals("ROLE_ADMIN")||userInfo.getRole().equals("ROLE_MASTER")) {
        model.addAttribute("errorMessage", "このユーザは削除できません");
      } else {
        model.addAttribute("deleteUserInfo", userInfo);
        userInfoMapper.deleteByNumber(number);
      }
    } else {
      model.addAttribute("errorMessage", "該当する番号のユーザーが存在しません");
    }

    ArrayList<UserInfo> roleUsers = userInfoMapper.selectByRole("ROLE_USER");
    if (userInfoMapper.selectByRole("ROLE_USER") != null) {
      model.addAttribute("userInfo", roleUsers);
    }
    ArrayList<UserInfo> AdminUserInfo = userInfoMapper.selectByRole("ROLE_ADMIN");
    model.addAttribute("adminUserInfo", AdminUserInfo);
    return "modification.html";
  }

  /**
   * @param model
   * @return
   */
  @PostMapping("/modify/deleteAdmin")
  @Transactional
  public String deleteAdmin(@RequestParam int number, ModelMap model){
    UserInfo loginUser = userInfoMapper.selectByUser(AuthUtil.getUserInfo().getName());
    model.addAttribute("loginUser", loginUser);

    if (userInfoMapper.selectByNumber(number) != null) {
      UserInfo userInfo = userInfoMapper.selectByNumber(number);
      if (userInfo.getRole().equals("ROLE_MASTER")) {
        model.addAttribute("errorMessage", "このユーザは削除できません");
      } else {
        model.addAttribute("deleteUserInfo", userInfo);
        userInfoMapper.deleteByNumber(number);
      }
    } else {
      model.addAttribute("errorMessage", "該当する番号のユーザーが存在しません");
    }

    ArrayList<UserInfo> roleUsers = userInfoMapper.selectByRole("ROLE_USER");
    model.addAttribute("userInfo", roleUsers);
    ArrayList<UserInfo> AdminUserInfo = userInfoMapper.selectByRole("ROLE_ADMIN");
    if (userInfoMapper.selectByRole("ROLE_ADMIN") != null) {
      model.addAttribute("adminUserInfo", AdminUserInfo);
    }
    return "modification.html";
  }

  /**
   * @param model
   * @return
   */
  @PostMapping("/modify/addAdmin")
  @Transactional
  public String addUser(@RequestParam String username, @RequestParam String password, ModelMap model) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);

    if (userInfoMapper.selectByUser(username) == null) {
      if (username.isEmpty() || password.isEmpty()) {
        model.addAttribute("errorMessage", "ユーザー名とパスワードを入力してください");
      } else {
        UserInfo userInfo = newAdmin.setAdminInfo(username, password);
        userInfo.setDate(loginDate);
        userInfoMapper.insertUserInfo(userInfo);
        model.addAttribute("userInfo", userInfo);
      }
    } else {
      model.addAttribute("errorMessage", "そのユーザー名はすでに使用されています");
    }

    UserInfo loginUser = userInfoMapper.selectByUser(AuthUtil.getUserInfo().getName());
    model.addAttribute("loginUser", loginUser);
    ArrayList<UserInfo> roleUsers = userInfoMapper.selectByRole("ROLE_USER");
    model.addAttribute("userInfo", roleUsers);
    ArrayList<UserInfo> AdminUserInfo = userInfoMapper.selectByRole("ROLE_ADMIN");
    model.addAttribute("adminUserInfo", AdminUserInfo);
    return "modification.html";
  }
}
