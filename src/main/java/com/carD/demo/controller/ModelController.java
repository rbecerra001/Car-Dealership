package com.carD.demo.controller;

import com.carD.demo.model.Model;
import com.carD.demo.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ModelController {

    private ModelService modelService;

    @Autowired
    public void setModelService(ModelService modelService){
        this.modelService = modelService;
    }

    // http://localhost:{PORTNUMBER}/helloworld
    @GetMapping(path = "/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    // http://localhost:{PORTNUMBER}/models
    @GetMapping(path = "/models")
    public List<Model> getModels(){
        System.out.println("Calling getModels ==>");
        return modelService.getModels();
    }

    // http://localhost:{PORTNUMBER}/models/{modelId}
    @GetMapping(path = "/models/{modelId}")
    public Model getModel(@PathVariable Long modelId){
        System.out.println("Calling getModel ==>");
        return modelService.getModel(modelId);
    }

    // http://localhost:{PORTNUMBER}/makes/{makeId}/models
    @PostMapping(path="/makes/{makeId}/models")
    public Model createModel(@PathVariable Long makeId, @RequestBody Model modelObject){
        System.out.println("Calling createModel ==>");
        return modelService.createModel(makeId, modelObject);
    }

    // http://localhost:{PORTNUMBER}/models/{modelId}
    @PutMapping(path="/models/{modelId}")
    public Model updateModel(@PathVariable Long modelId, @RequestBody Model modelObject){
        System.out.println("Calling updateModel ==>");
        return modelService.updateModel(modelId, modelObject);
    }

    // http://localhost:{PORTNUMBER}/models/{modelId}
    @DeleteMapping(path="/models/{modelId}")
    public Model deleteModel(@PathVariable Long modelId){
        System.out.println("Calling deleteModel ==>");
        return modelService.deleteModel(modelId);
    }
}
