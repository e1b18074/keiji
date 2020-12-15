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

@Controller
public class AdminController {

  @Autowired
  CommentMapper commentMapper;

  /**
   * @param model
   * @return
   */
  @PostMapping("/admin/delete")
  @Transactional
  public String delete(@RequestParam Integer number, ModelMap model){
    Comment comment = commentMapper.selectByNumber(number);
    model.addAttribute("comment", comment);

    commentMapper.deleteByNumber(number);
    return "admin.html";
  }

}
