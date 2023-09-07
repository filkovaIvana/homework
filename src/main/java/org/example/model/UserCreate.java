package org.example.model;

import lombok.Getter;
import org.example.entity.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCreate {

    @NotNull
    private String userName;
    @NotNull
    private String firstName;
    private String middleNames;
    @NotNull
    private String lastName;
    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;

    @Getter
    private String  biography;

    private Boolean isActive;



    private List<AreaOfExpetiseReturn> areaOfExpetiseReturnList = new ArrayList<>();

    public UserCreate(){}

    public UserCreate(String userName, String firstName, String middleNames, String lastName, String email, String phoneNumber, String password, String biography, Boolean isActive, List<AreaOfExpetiseReturn> areaOfExpetiseReturnList) {
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

    public UserCreate(User user){
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
