package org.almansa.webapp;

import java.util.List;

import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.service.ApplicationUserService;
import org.almansa.app.service.dto.UserJoinRequest;
import org.almansa.app.service.exception.ApplicationUserJoinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/member")
public class WebUserController {
    
    @Autowired
    ApplicationUserService userService;
    
    @RequestMapping(path="/add", method=RequestMethod.POST) 
    public ResponseEntity<Void> joinMember(@ModelAttribute UserJoinRequest request) { 
        try {
        	userService.joinUser(request);
        	
        	return new ResponseEntity<Void>(HttpStatus.OK);        	        	
        }catch(ApplicationUserJoinException e) {        	
        	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(path="/get/{loginId}")
    public ResponseEntity<ApplicationUser> getByLoginId(@PathVariable String loginId) {
        ApplicationUser user = userService.getUserByLoginId(loginId);
        
        return new ResponseEntity<ApplicationUser>(user, HttpStatus.OK);
    }
    
    @RequestMapping(path="/get")
    public ResponseEntity<List<ApplicationUser>> getAllUser() {
    	List<ApplicationUser> user = userService.findAll();
        
        return new ResponseEntity<List<ApplicationUser>>(user, HttpStatus.OK);
    }    
}
