package main.data;

import java.util.HashSet;

public class UserInfo {
    String id;
    String password;
    String city;

    public UserInfo( String id, String password, String city) {
        this.id = id;
        this.password = password;
        this.city = city;
    }

    public String getId(){
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
