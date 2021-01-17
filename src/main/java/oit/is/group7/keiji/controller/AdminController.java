package oit.is.group7.keiji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

import oit.is.group7.keiji.model.Comment;
import oit.is.group7.keiji.model.CommentMapper;
import oit.is.group7.keiji.model.Thread;
import oit.is.group7.keiji.model.ThreadMapper;

@Controller
public class AdminController {

  @Autowired
  CommentMapper commentMapper;

  @Autowired
  ThreadMapper threadMapper;

  /**
   * @param model
   * @return
   */
  @GetMapping("/admin")
  public String admin(ModelMap model) {
    ArrayList<Thread> allThread = threadMapper.selectAllThread();
    model.addAttribute("thread", allThread);
    return "admin.html";
  }

  /**
   * @param num
   * @param model
   * @return
   */
  @PostMapping("/admin/deleteThread")
  @Transactional
  public String deleteThread(@RequestParam String num, ModelMap model){
    Thread thread = threadMapper.selectByThreadNumber(Integer.parseInt(num));
    threadMapper.deleteByNumber(thread.getThreadNumber());
    commentMapper.deleteByThreadNumber(thread.getThreadNumber());
    return "admin.html";
  }

  /**
   * @param num
   * @param model
   * @return
   */
  @PostMapping("/admin/deleteComment")
  @Transactional
  public String deleteComment(@RequestParam String num, ModelMap model){
    Comment comment = commentMapper.selectByNumber(Integer.parseInt(num));
    model.addAttribute("deleteComment", comment);
    commentMapper.deleteByNumber(comment.getNumber());
    ArrayList<Comment> allComment = commentMapper.selectByThreadNumber(comment.getThreadNumber());
    model.addAttribute("comment", allComment);
    return "admin.html";
  }

  /**
   * @param num
   * @param model
   * @return
   */
  @PostMapping("/admin/show")
  public String show(@RequestParam String num, ModelMap model) {
    ArrayList<Comment> allComment = commentMapper.selectByThreadNumber(Integer.parseInt(num));
    model.addAttribute("comment", allComment);
    return "admin.html";
  }

}
