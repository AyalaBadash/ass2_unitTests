package main.userStoriesControllers;

import main.data.ShowInfo;

import java.util.HashMap;

public class AddShowController {
    int lastShowId;
    HashMap<Integer, ShowInfo> shows ;// id, info
    private static  AddShowController instance = null;


    public static AddShowController getInstance() {
        return instance;
    }
}
