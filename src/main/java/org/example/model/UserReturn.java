package org.example.model;

import lombok.Getter;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserReturn {

    private Long id;
    private String userName;
    private String firstName;
    private String middleNames;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    @Getter
    private String  biography;

    private Boolean isActive;



    private List<AreaOfExpetiseReturn> areaOfExpetiseReturnList = new ArrayList<>();

    public UserReturn(){}

    public UserReturn(Long id, String userName, String firstName, String middleNames, String lastName, String email, String phoneNumber, String password, String biography, Boolean isActive, List<AreaOfExpetiseReturn> areaOfExpetiseReturnList) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.middleNames = middleNames;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.areaOfExpetiseReturnList = areaOfExpetiseReturnList;
        this.biography = biography;
        this.isActive = isActive;
    }

    public UserReturn(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.areaOfExpetiseReturnList = user.getAreasOfExpertise().stream().map(AreaOfExpetiseReturn::new).collect(Collectors.toList());
        this.isActive = user.isActive();
        this.biography = user.getBiography();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AreaOfExpetiseReturn> getAreaOfExpetiseReturnList() {
        return areaOfExpetiseReturnList;
    }

    public void setAreaOfExpetiseReturnList(List<AreaOfExpetiseReturn> areaOfExpetiseReturnList) {
        this.areaOfExpetiseReturnList = areaOfExpetiseReturnList;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
