package main.data;

import java.util.HashSet;

public class UserInfo {
    protected String id;
    protected String password;
    protected HashSet<String> cities;

    public UserInfo( String id, String password, String city) {
        this.id = id;
        this.password = password;
        this.cities = new HashSet<>();
        cities.add(city);
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

    public boolean hasCity(String city){
        return cities.contains(city);
    }

    public void addCity(String city){
        cities.add(city);
    }


}
