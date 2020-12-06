package oit.is.group7.keiji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.ArrayList;

import oit.is.group7.keiji.model.Comment;
import oit.is.group7.keiji.model.CommentMapper;

@Controller
public class StartController {

  @Autowired
  CommentMapper commentMapper;

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
   * @param model
   * @param prin
   * @return
   */
  @GetMapping("/keiji/step2")
  public String sample32(ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    model.addAttribute("login_user", loginUser);
    return "keiji.html";
  }

   /**
   * @param model
   * @param prin
   * @return
   */
  @PostMapping("/lec02")
  @Transactional
  public String sample25(@RequestParam String userComment, ModelMap model, Principal prin) {
    String user = prin.getName();
    Comment comment = new Comment();
    comment.setUser(user);
    comment.setUserComment(userComment);
    commentMapper.insertComment(comment);
    model.addAttribute("comment", comment);
    return "keiji.html";
  }

}
