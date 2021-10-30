package com.example.elevator;

public class Hellomodel {
    private String UserId;
    private String Password;
    private String E_mail;
    private String PhoneNumber;
    private int UserType;

    public Hellomodel(){
    }

    public String getUserId(){
        return UserId;
    }
    public void setUserId(String userId){
        this.UserId = userId;
    }

    public String getPassword(){
        return Password;
    }
    public void setPassword(String password){
        this.Password = password;
    }

    public String getE_mail(){
        return E_mail;
    }
    public void setE_mail(String e_mail){
        this.E_mail = e_mail;
    }

    public String getPhoneNumber(){
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.PhoneNumber = phoneNumber;
    }

    public int getUserType(){
        return UserType;
    }
    public void setUserType(int userType){
        this.UserType = userType;
    }
}
