package org.almansa.webapp.controller;

import java.util.Date;

import org.almansa.app.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    
    @RequestMapping(path = "/add")
    public ResponseEntity<Void> add( 
            @RequestParam(name = "lable", required = false) Long lableId, 
            @RequestParam(name = "name", required = true) String name, 
            @RequestParam(name = "born_date", required = false)  Date bornDate) {
        try {
            artistService.add(lableId, name, bornDate);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }              
    }
}
