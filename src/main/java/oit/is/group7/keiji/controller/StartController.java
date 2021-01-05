package oit.is.group7.keiji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;
import java.util.ArrayList;

import oit.is.group7.keiji.model.Comment;
import oit.is.group7.keiji.model.CommentMapper;
import oit.is.group7.keiji.model.UserInfo;

@Controller
public class StartController {

  @Autowired
  CommentMapper commentMapper;

  /**
   * sample21というGETリクエストがあったら，sample21()を呼び出して，sample21.htmlを返すメソッド
   * @param model
   * @return
   */
  @GetMapping("/keiji")
  @Transactional
  public String keiji(ModelMap model) {
    ArrayList<Comment> allComment = commentMapper.selectAllComment();
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }

  /**
   * @param model
   * @return
   */
  @GetMapping("/keiji/update")
  @Transactional
  public String update(ModelMap model) {
    ArrayList<Comment> allComment = commentMapper.selectAllComment();
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }

   /**
   * @param model
   * @param prin
   * @return
   */
  @PostMapping("/keiji/comment")
  @Transactional
  public String sample25(@RequestParam String userComment, Authentication authentication, ModelMap model) {
    UserInfo userInfo = (UserInfo)authentication.getPrincipal();
    String user = userInfo.getUserName();
    Comment comment = new Comment();
    comment.setUser(user);
    comment.setUserComment(userComment);
    commentMapper.insertComment(comment);
    if(comment.getNumber() >= 100){

    }
    ArrayList<Comment> allComment = commentMapper.selectAllComment();
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }

  @GetMapping("/keiji/admin")
  public String admin() {
    return "admin.html";
  }

}
