package model;

import javafx.beans.binding.BooleanBinding;

import java.util.ArrayList;

/**
 * Created by Dimitry on 18.02.17.
 */
public class test {
    public static void main(String[] args) {
        Beans beans = new Beans();


        Boolean bool = false;
        String password = "fs";

        char[] charList = password.toCharArray();
        if(charList.length >= 6){
            bool = true;
        }

        System.out.println(bool);
    }
}
