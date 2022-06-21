package com.yaser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        String[] inputs = inputValues();
        parenthesize(inputs);
    }

    public static String[] inputValues(){
        System.out.println("enter matrices' names separated by space:");
        Scanner scObj = new Scanner(System.in);
        String[] result = scObj.nextLine().split(" ");  
        scObj.close();
        return result;    
    }

    /**
     * takes an array if strings (matrices' names) and 
     * prints out all possible parenthesizations recursiveley.
     * it uses a {@link ParenthesizationMold} to save the position of 
     * {@code items} and parenthesis, and when it's filled up, one arrangement
     * is printed.
     *
     * @param items: an array of strings containing matrices' names
     */
    public static void parenthesize(String[] names){
        String[] all = parenthesize(names, true);
        for(String i:all)
            System.out.println(i);
    }
    private static String[] parenthesize(String[] names,boolean isTopCall){
        ArrayList<String> all = new ArrayList<>();
        if(names.length <= 1){
            all.add(stickTogether(names, "", ""));
            return all.toArray(new String[1]);
        }
        else if(names.length == 2){
            if(isTopCall)
                all.add(stickTogether(names, "", ""));
            else
                all.add(stickTogether(names, "(", ")"));
            return all.toArray(new String[1]);
        }
        else{
            for(int i=1 ; i<names.length ; i++)
                for(String m:parenthesize(Arrays.copyOfRange(names, 0, i), false))
                    for(String n:parenthesize(Arrays.copyOfRange(names, i, names.length), false)){
                        String[] joint = {m,n};
                        if(isTopCall)
                            all.add(stickTogether(joint, "", ""));
                        else
                            all.add(stickTogether(joint, "(", ")"));
                    }
            return all.toArray(new String[all.size()]);
        }
    }
    
    public static String stickTogether(String[] elements, String start, String end){
        StringBuilder r = new StringBuilder("");
        r.append(start);
        for(String i: elements)
            r.append(i);
        r.append(end);
        return r.toString();    
    }
}