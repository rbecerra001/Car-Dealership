package com.carD.demo.controller;

import com.carD.demo.model.Make;
import com.carD.demo.model.Model;
import com.carD.demo.service.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
public class MakeController {

    private MakeService makeService;

    @Autowired // dependency injection
    public void setMakeService(MakeService makeService){
        this.makeService = makeService;
    }

    // http://localhost:{PORTNUMBER}/makes
    @GetMapping(path = "/makes")
    public List<Make> getMakes(){
        System.out.println("Calling getMakes ==>");
        return makeService.getMakes();
    }

    // http://localhost:{PORTNUMBER}/makes/{makeId}
    @GetMapping(path = "/makes/{makeId}")
    public Make getMake(@PathVariable Long makeId){
        System.out.println("Calling getMake ==>");
        return makeService.getMake(makeId);
    }

    // http://localhost:{PORTNUMBER}/makes/{makeId}/models
    @GetMapping(path = "/makes/{makeId}/models")
    public List<Model> getMakeModels(@PathVariable Long makeId){
        System.out.println("Calling getMakeModels ==>");
        return makeService.getMakeModels(makeId);
    }

    // http://localhost:{PORTNUMBER}/makes/{makeId}/models/{modelId}
    @GetMapping(path = "/makes/{makeId}/models/{modelId}")
    public Model getMakeModel(@PathVariable Long makeId, @PathVariable Long modelId){
        System.out.println("Calling getMakeModel ==>");
        return makeService.getMakeModel(makeId, modelId);
    }

    // http://localhost:{PORTNUMBER}/makes
    @PostMapping(path = "/makes")
    public Make createMake(@RequestBody Make makeObject){
        System.out.println("Calling createMake ==>");
        return makeService.createMake(makeObject);
    }

    // http://localhost:{PORTNUMBER}/makes/{makeId}
    @PutMapping(path = "/makes/{makeId}")
    public Make updateMake(@PathVariable Long makeId, @RequestBody Make makeObject){
        System.out.println("Calling updateMake ==>");
        return makeService.updateMake(makeId, makeObject);
    }

    // http://localhost:{PORTNUMBER}/makes/{makeId}
    @DeleteMapping(path = "/makes/{makeId}")
    public ResponseEntity<HashMap> deleteMake(@PathVariable Long makeId){
        System.out.println("Calling deleteMake ==>");
        makeService.deleteMake(makeId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "make with id: " + makeId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }
}
