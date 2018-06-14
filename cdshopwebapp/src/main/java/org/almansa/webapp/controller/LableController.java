package org.almansa.webapp.controller;

import java.util.Date;
import java.util.List;

import org.almansa.app.domain.album.Lable;
import org.almansa.app.service.LableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lable")
public class LableController {

    @Autowired
    private LableService lableService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(
            @RequestParam String lableName, 
            @RequestParam(required=false) String ceoName,
            @RequestParam(required=false, name="estDate") Date establishmentDate) {
        try {
            lableService.add(lableName, ceoName, establishmentDate);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(path="/get/all", method = RequestMethod.GET)
    public ResponseEntity<List<Lable>> getAllLables(){
        return new ResponseEntity<List<Lable>>(lableService.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(path="/get/name/{lableName}", method = RequestMethod.GET)
    public ResponseEntity<List<Lable>> getByLableName(@PathVariable String lableName){
        List<Lable> lables = lableService.findByName(lableName);
        return new ResponseEntity<List<Lable>>(lables, HttpStatus.OK);
    }  
    
    @RequestMapping(path="/get/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lable> getById(@PathVariable("id") Long id){
        Lable lable = lableService.findById(id); 
        return new ResponseEntity<Lable>(lable, HttpStatus.OK);
    }
    
    @RequestMapping(path="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        try {
            lableService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(EmptyResultDataAccessException e) {
            throw e;
        }
    }
}
