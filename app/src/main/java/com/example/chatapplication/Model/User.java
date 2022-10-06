package com.example.chatapplication.Model;

public class User {
    String profilepic,username,password,userId,lastmessage,mail;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User(String profilepic, String username, String password, String mail, String userId, String lastmessage) {
        this.profilepic = profilepic;
        this.username=username;
        this.password=password;
        this.userId=userId;
        this.lastmessage=lastmessage;
        this.mail=mail;
    }
//for sign up
public User(String username,String password,String mail) {

    this.username=username;
    this.password=password;
    this.mail=mail;

}


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public void getUserId(String key) {

    }
}
