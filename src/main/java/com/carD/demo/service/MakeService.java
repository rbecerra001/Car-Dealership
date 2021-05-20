package com.carD.demo.service;

import com.carD.demo.model.Make;
import com.carD.demo.model.Model;
import com.carD.demo.security.MyUserDetails;
import com.carD.demo.exception.InformationNotFoundException;
import com.carD.demo.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MakeService {
    private MakeRepository makeRepository;

    @Autowired // dependency injection
    public void setMakeRepository(MakeRepository makeRepository){
        this.makeRepository = makeRepository;
    }

    public List<Make> getMakes(){
        System.out.println("Calling getMakes() in service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return makeRepository.findByUserId(userDetails.getUser().getId());
    }

    public Make getMake(Long makeId){
        System.out.println("Calling getMake() in service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<Make> make = makeRepository.findByIdAndUserId(makeId,userDetails.getUser().getId());
        if(make.isPresent()){
            return make.get();
        }else{
            throw new InformationNotFoundException("The make at id " + makeId + " does not exist ");
        }
    }

    public List<Model> getMakeModels(Long makeId){
        System.out.println("Calling getMakeModels() in service");
        Make make = getMake(makeId);
        return make.getModelList();
    }

    public Model getMakeModel(Long makeId, Long modelId){
        System.out.println("Calling getMakeModel() in service");
        Optional<Model> model = getMakeModels(makeId).stream().filter(s -> s.getId().equals(modelId)).findFirst();
        if (model.isPresent()){
            return model.get();
        }else{
            throw new InformationNotFoundException("The model with id " + modelId + " does not exist with the given make id" + makeId);
        }
    }

    public Make createMake(Make makeObject){
        System.out.println("Calling createMake() in service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        makeObject.setUser(userDetails.getUser());
        return makeRepository.save(makeObject);
    }

    public Make updateMake(Long makeId, Make makeObject){
        System.out.println("Calling updateMake() in service");
        Make make = getMake(makeId);
        make.setName(makeObject.getName());
        return makeRepository.save(make);
    }

    public void deleteMake(Long makeId){
        System.out.println("Calling deleteMake() in service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<Make> make = makeRepository.findByIdAndUserId(makeId, userDetails.getUser().getId());
        if(make.isPresent()){
            makeRepository.deleteById(makeId);
        }else{
            throw new InformationNotFoundException("Make with id " + makeId + " does not exist");
        }
    }
}
