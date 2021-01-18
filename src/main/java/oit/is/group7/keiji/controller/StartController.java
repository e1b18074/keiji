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
import java.util.Date;
import java.text.SimpleDateFormat;

import oit.is.group7.keiji.model.Comment;
import oit.is.group7.keiji.model.CommentMapper;
import oit.is.group7.keiji.model.Thread;
import oit.is.group7.keiji.model.ThreadMapper;
import oit.is.group7.keiji.model.UserInfo;
import oit.is.group7.keiji.model.UserInfoMapper;

@Controller
public class StartController {

  @Autowired
  CommentMapper commentMapper;

  @Autowired
  ThreadMapper threadMapper;

  @Autowired
  UserInfoMapper userInfoMapper;

  /**
   * @param model
   * @param num
   * @return
   */
  @PostMapping("/keiji/update")
  @Transactional
  public String update(ModelMap model, @RequestParam String num) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();
    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    Thread thread = threadMapper.selectByThreadNumber(Integer.parseInt(num));
    model.addAttribute("thread", thread);
    ArrayList<Comment> allComment = commentMapper.selectByThreadNumber(thread.getThreadNumber());
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }

  /**
   * @param model
   * @param num
   * @return
   */
  @PostMapping("/keiji/comment")
  @Transactional
  public String comment(ModelMap model, @RequestParam String num, @RequestParam String userComment) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();
    String user = userInfo.getName();

    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    if (userComment.isEmpty()) {
      model.addAttribute("errorMessage", "コメントを入力してください");
    } else {
      Comment comment = new Comment(user, userComment, loginDate, Integer.parseInt(num));
      commentMapper.insertComment(comment);
    }

    Thread thread = threadMapper.selectByThreadNumber(Integer.parseInt(num));
    thread.setDate(loginDate);
    threadMapper.updateDate(thread);
    model.addAttribute("thread", thread);

    ArrayList<Comment> allComment = commentMapper.selectByThreadNumber(thread.getThreadNumber());
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }

  /**
   * @param model
   * @return
   */
  @GetMapping("/keiji/admin")
  public String admin(ModelMap model) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();
    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    ArrayList<Thread> allThread = threadMapper.selectAllThread();
    model.addAttribute("thread", allThread);
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
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();
    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    Comment comment = commentMapper.selectByNumber(Integer.parseInt(num));
    commentMapper.updateComment(comment);

    Thread thread = threadMapper.selectByThreadNumber(comment.getThreadNumber());
    model.addAttribute("thread", thread);
    ArrayList<Comment> allComment = commentMapper.selectByThreadNumber(thread.getThreadNumber());
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }
}
