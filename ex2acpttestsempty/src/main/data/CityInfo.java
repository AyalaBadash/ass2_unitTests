package main.data;

import java.util.HashMap;

public class CityInfo {
    String name;
    HashMap<String,Integer> halls; // name , seats

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getHalls() {
        return halls;
    }

    public void setHalls(HashMap<String, Integer> halls) {
        this.halls = halls;
    }

    public CityInfo(String name) {
        this.name = name;
        this.halls = new HashMap<>();
    }
}
