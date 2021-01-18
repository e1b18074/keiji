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
public class ThreadController {

  @Autowired
  CommentMapper commentMapper;

  @Autowired
  ThreadMapper threadMapper;

  @Autowired
  UserInfoMapper userInfoMapper;

  /**
   * sample21というGETリクエストがあったら，sample21()を呼び出して，sample21.htmlを返すメソッド
   *
   * @param model
   * @return
   */
  @GetMapping("/thread")
  @Transactional
  public String thread(ModelMap model) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();
    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    ArrayList<Thread> allThread = threadMapper.selectAllThread();
    model.addAttribute("thread", allThread);
    return "thread.html";
  }

  /**
   * @param model
   * @return
   */
  @GetMapping("/thread/update")
  @Transactional
  public String update(ModelMap model) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();
    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    ArrayList<Thread> allThread = threadMapper.selectAllThread();
    model.addAttribute("thread", allThread);
    return "thread.html";
  }

  /**
   * @param model
   * @return
   */
  @PostMapping("/thread/build")
  @Transactional
  public String build(@RequestParam String title, @RequestParam String userComment, ModelMap model) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();

    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    if (title.isEmpty() || userComment.isEmpty()) {
      model.addAttribute("errorMessage", "スレッドのタイトルと初期コメントを入力してください");
    } else {
      String user = userInfo.getName();
      Thread thread = new Thread(user, title, loginDate);

      threadMapper.insertThread(thread);
      Comment comment = new Comment(user, userComment, loginDate, threadMapper.selectThreadNumber());
      commentMapper.insertComment(comment);
    }
    ArrayList<Thread> allThread = threadMapper.selectAllThread();
    model.addAttribute("thread", allThread);
    return "thread.html";
  }

  /**
   * @param model
   * @return
   */
  @GetMapping("/thread/admin")
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
  @GetMapping("/thread/keiji")
  @Transactional
  public String keiji(ModelMap model, @RequestParam String num) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();
    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    Thread thread = threadMapper.selectByThreadNumber(Integer.parseInt(num));
    model.addAttribute("thread", thread);
    ArrayList<Comment> allComment = commentMapper.selectByThreadNumber(Integer.parseInt(num));
    model.addAttribute("comment", allComment);
    return "keiji.html";
  }

  /**
   * @param model
   * @param num
   * @return
   */
  @PostMapping("/thread/good")
  @Transactional
  public String good(ModelMap model, @RequestParam String num) {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm");
    String loginDate = dateFormat.format(date);
    UserInfo userInfo = AuthUtil.getUserInfo();
    userInfo.setDate(loginDate);
    userInfoMapper.updateLoginDate(userInfo);

    Thread thread = threadMapper.selectByThreadNumber(Integer.parseInt(num));
    threadMapper.updateThread(thread);
    ArrayList<Thread> allThread = threadMapper.selectAllThread();
    model.addAttribute("thread", allThread);
    return "thread.html";
  }
}
