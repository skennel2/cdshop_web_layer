package org.almansa.webapp;

import java.util.Date;
import java.util.List;

import org.almansa.app.domain.album.Lable;
import org.almansa.app.service.LableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> addLable(
            @RequestParam String lableName, 
            @RequestParam(required=false) String ceoName,
            @RequestParam(required=false, name="estDate") Date establishmentDate) {
        try {
            lableService.addLable(lableName, ceoName, establishmentDate);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(path="/all", method = RequestMethod.GET)
    public ResponseEntity<List<Lable>> getAllLables(){
        return new ResponseEntity<List<Lable>>(lableService.getAll(), HttpStatus.OK);
    }
    
    @RequestMapping(path="/get/name/{lableName}", method = RequestMethod.GET)
    public ResponseEntity<List<Lable>> getByLableName(@PathVariable String lableName){
        List<Lable> lables = lableService.getByName(lableName);
        return new ResponseEntity<List<Lable>>(lables, HttpStatus.OK);
    }  
    
    //TODO not worked
    @RequestMapping(path="/get/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lable> getById(@PathVariable("id") Long id){
        try {
            Lable lable = lableService.getById(id); 
            
            return new ResponseEntity<Lable>(lable, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<Lable>(HttpStatus.BAD_REQUEST);
        }
    }     
}
