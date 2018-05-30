package org.almansa.webapp;

import org.almansa.app.repository.DummyDataMaker;
import org.almansa.app.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/member")
public class MemberController {
    
    @Autowired
    ApplicationUserService service;
    
    @Autowired
    DummyDataMaker dataMaker;
    
    @RequestMapping(path="/test")
    public String getTest() {
        return "hello world";
    }
    
    @RequestMapping(path="/data")
    public String getData() {
        dataMaker.makeDummies();
        return "hello world";
    }    

}
