package org.example.model;

import org.example.entity.User;

public class UserBasics {
    private Long id;
    private String userName;
    private String firstName;
    private String middleNames;
    private String lastName;
    private String gender;
    private String email;
    private String phoneNumber;
    private String profileType;
    private String password;
    private String role;
    private String title;
    private String imageURl;
    private String qualification;
    private String position;
    private String colleges;
    private String biography;

    public UserBasics(){
    }

    public UserBasics(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.middleNames = user.getMiddleNames();
        this.lastName = user.getLastName();
        this.gender = "n/a";
        this.email = user.getEmail();
        this.phoneNumber = "n/a";
        this.profileType = "type";
        this.password = user.getPassword();
        this.qualification = user.getQualification();
        this.position = user.getPosition();
        this.role = "ROLE_ADMIN";
        this.title = user.getTitle();
        this.colleges = user.getColleges();
        this.email = user.getEmail();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getColleges() {
        return colleges;
    }

    public void setColleges(String colleges) {
        this.colleges = colleges;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
