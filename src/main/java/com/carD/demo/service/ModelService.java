package com.carD.demo.service;

import com.carD.demo.model.Make;
import com.carD.demo.model.Model;
import com.carD.demo.security.MyUserDetails;
import com.carD.demo.exception.InformationExistException;
import com.carD.demo.exception.InformationNotFoundException;
import com.carD.demo.repository.MakeRepository;
import com.carD.demo.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModelService {
    private ModelRepository modelRepository;
    private MakeRepository makeRepository;

    @Autowired
    public void setModelRepository(ModelRepository modelRepository){
        this.modelRepository = modelRepository;
    }
    @Autowired
    public void setMakeRepository(MakeRepository makeRepository){
        this.makeRepository = makeRepository;
    }

    public List<Model> getModels(){
        System.out.println("Service calling getModels ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Model> model = modelRepository.findByUserId(userDetails.getUser().getId());
        if (model.isEmpty()) {
            throw new InformationNotFoundException("no models found for user id " + userDetails.getUser().getId());
        } else {
            return model;
        }
    }

    public Model getModel(Long modelId){
        System.out.println("Service calling getModel ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Model model = modelRepository.findByIdAndUserId(modelId, userDetails.getUser().getId());
        if (model != null){
            return model;
        }else{
            throw new InformationNotFoundException("Song with id " + modelId + " cannot be found for this user");
        }
    }

    public Model createModel(Long makeId, Model modelObject){
        System.out.println("Service calling createModel ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Make make = makeRepository.findByIdAndUserId(makeId, userDetails.getUser().getId()).get();
        if (make == null) {
            throw new InformationNotFoundException("make with id " + makeId + " not found");
        }
        Model model = modelRepository.findByNameAndUserId(modelObject.getName(), userDetails.getUser().getId());
        if (model != null){
            throw new InformationExistException("model with name " + modelObject.getName() + " already exists from this user");
        }
        modelObject.setMake(make);
        modelObject.setUser(userDetails.getUser());
        return modelRepository.save(modelObject);
    }

    public Model updateModel(Long modelId, Model modelObject){
        System.out.println("Service calling updateModel ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Model model = modelRepository.findByIdAndUserId(modelId, userDetails.getUser().getId());
        if (model != null){
            if (modelObject.getName().equals(model.getName())) {
                throw new InformationExistException("model " + model.getName() + " is already exists");
            } else {
                model.setName(modelObject.getName());
                model.setMiles(modelObject.getMiles());
                model.setPrice(modelObject.getPrice());
                model.setUser(userDetails.getUser());
                return modelRepository.save(model);
            }
        }else{
            throw new InformationNotFoundException("Cannot update model at id " + modelId + " because it does not exist");
        }
    }

    public Model deleteModel(Long modelId){
        System.out.println("Service calling deleteModel ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Model model = modelRepository.findByIdAndUserId(modelId, userDetails.getUser().getId());
        if(model != null){
            modelRepository.deleteById(modelId);
            return model;
        } else{
            throw new InformationNotFoundException("model with id " + modelId + " not found");
        }
    }
}
