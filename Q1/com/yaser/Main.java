package com.yaser;


import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    private static int counter;

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
        ParenthesizationMold mold = new ParenthesizationMold(names);
        counter = 0;
        ArrayList<ParenthesizationMold> all = new ArrayList<>();
        parenthesize(mold, all, 0, names.length-1, 0, 0);
        for(ParenthesizationMold i:all)
            System.out.println(i);
    }
    private static void parenthesize(ParenthesizationMold mold, ArrayList<ParenthesizationMold> all, int start1, int end1, int start2, int end2){
        ParenthesizationMold aCopy;
        if(start2 != end2 && start1 !=end1)
            for(int i=start1 ; i<end1 ; i++){
                for(int j=start2 ; j<end2 ; j++){
                    aCopy = mold.copy();
                    aCopy.addBreakPoint(start1, end1, i);
                    aCopy.addBreakPoint(start2, end2, j);
                    all.add(aCopy); 
                }
            }
        else{
            int start, end;
            if(start2 != end2){
                start=start2;
                end=end2;
            }else{
                start=start1;
                end=end1;
            }
            for(int i=start ; i<end ; i++){
                aCopy = mold.copy();
                aCopy.addBreakPoint(start, end, i);
                if(aCopy.isFull())
                    all.add(aCopy);
                else
                    parenthesize(aCopy, all, start, i, i+1, end);    
            }
        }
    }
}