package org.almansa.webapp;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.service.ApplicationUserService;
import org.almansa.app.service.dto.UserJoinRequest;
import org.almansa.webapp.dto.LoginSessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/member")
public class WebUserController {
    
    @Autowired
    ApplicationUserService userService;
    
    @RequestMapping(path="/add", method=RequestMethod.POST) 
    public void joinMember(@ModelAttribute UserJoinRequest request) { 
        // @ModelAttribute는 HTTP 바디에 Content-Type/x-www-form-urlencoded로 던져야 바인딩된다
        // Content-Type/x-www-form-urlencoded는 key/value로 값을 던져야하는 규약
        if(request == null) {
            throw new IllegalArgumentException("UserJoinRequest can't be null");
        }
        
        userService.joinUser(request);
    }
    
    @RequestMapping(path="/get/{loginId}")
    public ApplicationUser getByLoginId(@PathVariable String loginId) {
        return userService.getUserByLoginId(loginId);
    }
    
    @RequestMapping(path="/login", method=RequestMethod.POST)
    public void login(@RequestParam String loginId, @RequestParam String password, HttpSession session) {
        if(userService.isAbleToLogin(loginId, password)) {
            session.setAttribute("login", new LoginSessionModel(loginId, new Date()));
        }
    }
}
