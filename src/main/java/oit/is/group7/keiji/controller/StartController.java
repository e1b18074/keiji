package oit.is.group7.keiji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class StartController {

  /**
   * sample21というGETリクエストがあったら，sample21()を呼び出して，sample21.htmlを返すメソッド
   *
   * @return
   */
  @GetMapping("/keiji")
  public String keiji() {
    return "keiji.html";
  }

  /**
   *
   * @param model Thymeleafにわたすデータを保持するオブジェクト
   * @param prin  ログインユーザ情報が保持されるオブジェクト
   * @return
   */
  @GetMapping("/keiji/step2")
  public String sample32(ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    model.addAttribute("login_user", loginUser);
    return "keiji.html";
  }

   /**
   * @param username
   * @param model
   * @return
   */
  @PostMapping("/lec02")
  public String sample25(@RequestParam String usercomment, ModelMap model) {
    model.addAttribute("comment", usercomment);
    return "keiji.html";
  }



}
