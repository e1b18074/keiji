package oit.is.group7.keiji.controller;

import oit.is.group7.keiji.security.AuthUtil;

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
   *
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
   * @return
   */
  @PostMapping("/keiji/comment")
  @Transactional
  public String comment(@RequestParam String userComment, ModelMap model) {
    UserInfo userInfo = AuthUtil.getUserInfo();
    String user = userInfo.getName();
    Comment comment = new Comment();
    comment.setUser(user);
    comment.setUserComment(userComment);
    commentMapper.insertComment(comment);
    if (comment.getNumber() >= 100) {

    }
    ArrayList<Comment> allComment = commentMapper.selectAllComment();
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }

  /**
   * @param model
   * @return
   */
  @GetMapping("/keiji/admin")
  public String admin(ModelMap model) {
    ArrayList<Comment> allComment = commentMapper.selectAllComment();
    model.addAttribute("comment", allComment);
    return "admin.html";
  }

  /**
   * @param model
   * @param num
   * @return
   */
  @PostMapping("/keiji/good")
  @Transactional
  public String good(ModelMap model, @RequestParam String num) {
    Comment comment = commentMapper.selectByNumber(Integer.parseInt(num));
    commentMapper.updateComment(comment);
    ArrayList<Comment> allComment = commentMapper.selectAllComment();
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }
}
