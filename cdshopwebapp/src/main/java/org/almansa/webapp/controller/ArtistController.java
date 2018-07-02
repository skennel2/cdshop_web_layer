package org.almansa.webapp.controller;

import java.util.Date;
import java.util.List;

import org.almansa.app.domain.album.Artist;
import org.almansa.app.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    
    @RequestMapping(path = "/add")
    public ResponseEntity<Void> add( 
            @RequestParam(name = "lable", required = false) Long lableId, 
            @RequestParam(name = "name", required = true) String name, 
            @RequestParam(name = "born_date", required = false)  Date bornDate) {
        artistService.add(lableId, name, bornDate);
        return new ResponseEntity<Void>(HttpStatus.OK);            
    }
    
    @RequestMapping(path = "/get/id/{id}")
    public ResponseEntity<Artist> getById(@PathVariable(required=true) Long id){
        Artist artist = artistService.findById(id);
        return new ResponseEntity<Artist>(artist, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/get/all")
    public ResponseEntity<List<Artist>> getAll(){
        List<Artist> artists = artistService.getAll();
        return new ResponseEntity<List<Artist>>(artists, HttpStatus.OK);
    }    
}
