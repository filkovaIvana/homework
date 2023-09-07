package org.example.controller;

import org.example.entity.AreaOfExpertise;
import org.example.entity.User;
import org.example.exception.ForbiddenException;
import org.example.exception.ValidationException;
import org.example.model.UserCreate;
import org.example.model.UserReturn;
import org.example.repository.AreaRepository;
import org.example.repository.UserRepository;
import org.example.security.BuiltInRightsForPreAuthorizeHavingAuthority;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ResearchController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    UserService userService;

    @GetMapping("/test/addUser")
    public ResponseEntity<String> addUser() {
        System.out.println("HELLO research gate !!!");
        User newUser = new User();
        newUser.setUserName("ivana.rancic@gmail.com");
        newUser.setActive(true);
        newUser.setBiography("Bla bla...");
        newUser.setFirstName("Ivana");
        newUser.setLastName("Filkova");
        newUser.setPhoneNumber("2390423");
        newUser.setEmail("ivana.rancic@gmail.com");
        newUser.setPassword("password");
        userRepository.save(newUser);
//        User foundUser = userRepository.findByUserName();

        return ResponseEntity.ok().body("feedback");
    }

    @GetMapping("/test/getUser")
    public ResponseEntity<UserReturn> getUser() throws NoSuchElementException {
        System.out.println("HELLO found user !!!");
        User foundUser = userRepository.findByUserName("stef_mef").get();
        if(foundUser == null){
            throw new NoSuchElementException();
        }
        System.out.println("Found user with name: " + foundUser.getFirstName() + "" + foundUser.getLastName());
        System.out.println("With id: " + foundUser.getId());
        System.out.println("With email: " + foundUser.getEmail());
        System.out.println("With password: " + foundUser.getPassword());
        foundUser.getAreasOfExpertise().forEach(expertise -> {
            System.out.println("expertise found: " + expertise.getArea());});

        UserReturn userReturn = new UserReturn(foundUser);
        return ResponseEntity.ok().body(userReturn);
    }

    @GetMapping("/test/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserCreate record) {
        System.out.println("HELLO create user !!!");
//        try {
            userService.create(record);
//        }
//        catch (ValidationException e) {
//            System.out.println("ENTER");
//            throw new ValidationException();
//        }
//        userRepository.save(foundUser);
        return ResponseEntity.ok().body("feedback");
    }


    @PreAuthorize(BuiltInRightsForPreAuthorizeHavingAuthority.EDIT)
    @PostMapping("/test/editUser")
    public ResponseEntity<String> editUser(@Valid @RequestBody UserReturn record)   {
        System.out.println("HELLO edit user !!!");
        User foundUser = userRepository.findById(record.getId()).orElseThrow(ForbiddenException::new);
        System.out.println("Found user with name: " + foundUser.getFirstName() + "" + foundUser.getLastName());
        System.out.println("With email: " + foundUser.getEmail());
        System.out.println("With password: " + foundUser.getPassword());

        foundUser.setUserName(record.getUserName());
        foundUser.setEmail(record.getEmail());
        foundUser.setBiography(record.getBiography());
        foundUser.setFirstName(record.getFirstName());
        foundUser.setLastName(record.getLastName());
        foundUser.setActive(true);
        foundUser.setPhoneNumber(record.getPhoneNumber());

        List<AreaOfExpertise> areasOfExpertiseList = new ArrayList<>();
        record.getAreaOfExpetiseReturnList().forEach(expertise -> {
            System.out.println("expertise to be searched: " + expertise.getArea());
            AreaOfExpertise currentAreaOfExpertise = areaRepository.findByArea(expertise.getArea());
            if(currentAreaOfExpertise == null) {
                AreaOfExpertise newAreaOfExpertise = new AreaOfExpertise();
                newAreaOfExpertise.setArea(expertise.getArea());
                newAreaOfExpertise.setCode(expertise.getCode());
                System.out.println("EXPERTISE WILL BE SAVED");
                areaRepository.save(newAreaOfExpertise);
            }
//            System.out.println("from database currentAreaOfExpertise area: " + currentAreaOfExpertise.getArea());
            areasOfExpertiseList.add(currentAreaOfExpertise);
            System.out.println("HERE 1");
        });

        foundUser.setAreasOfExpertise(new HashSet<>(areasOfExpertiseList));

        userRepository.save(foundUser);
        return ResponseEntity.ok().body("feedback");
    }


}
