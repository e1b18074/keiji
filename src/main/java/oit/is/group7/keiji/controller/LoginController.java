package oit.is.group7.keiji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;

import oit.is.group7.keiji.model.Comment;
import oit.is.group7.keiji.model.CommentMapper;
import oit.is.group7.keiji.model.UserInfo;

@Controller
class LoginController {

  @Autowired
  CommentMapper commentMapper;

    @GetMapping(path = "login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model, HttpSession session) {

        model.addAttribute("showErrorMsg", false);
        model.addAttribute("showLogoutedMsg", false);
        if (error != null) {
            if (session != null) {
                AuthenticationException ex = (AuthenticationException) session
                        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                if (ex != null) {
                    model.addAttribute("showErrorMsg", true);
                    model.addAttribute("errorMsg", ex.getMessage());
                }
            }
        } else if (logout != null) {
            model.addAttribute("showLogoutedMsg", true);
        }
        return "login";
    }

}
