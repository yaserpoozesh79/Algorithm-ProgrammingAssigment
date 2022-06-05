package com.yaser;

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;;

public class Main{
    private static int counter;
    public static void main(String[] args) {
        System.out.println("enter n: ");
        int n = NSO().nextInt();
        System.out.println("enter m: ");
        int m = NSO().nextInt();
        if(n<1 || m<1)
            throw new IllegalStateException("Error: minimum dimension size is 1, you entered less!!!!");
        ArrayList<Point> walk = new ArrayList<>();
        walk.add(new Point(1,1));
        counter = 0;
        printAllWalks(walk, n, m);
    }
    public static Scanner NSO(){
        return new Scanner(System.in);
    }

    public static ArrayList<Point> copyArrayList(ArrayList<Point> list){
        ArrayList<Point> newList = new ArrayList<>();
        for(Point i:list)
            newList.add(i);
        return newList;    
    }
    
    public static void printAllWalks(ArrayList<Point> walk, int n, int m){
        Point lastOneInTheWalk = walk.get(walk.size()-1);
        ArrayList<Point> copy;
        if(lastOneInTheWalk.getX()!=n || lastOneInTheWalk.getY()!=m){   // walk is not complete
            copy = copyArrayList(walk);
            if(lastOneInTheWalk.getX() == n){
                copy.add(new Point((int)lastOneInTheWalk.getX(),(int)lastOneInTheWalk.getY()+1));
                printAllWalks(copy, n, m);
            }else if(lastOneInTheWalk.getY() == m){
                copy.add(new Point((int)lastOneInTheWalk.getX()+1,(int)lastOneInTheWalk.getY()));
                printAllWalks(copy, n, m);
            }else{
                ArrayList<Point> copy2,copy3;
                copy2 = copyArrayList(walk);
                copy3 = copyArrayList(walk);
                copy.add(new Point((int)lastOneInTheWalk.getX()+1,(int)lastOneInTheWalk.getY()+1));
                copy2.add(new Point((int)lastOneInTheWalk.getX()+1,(int)lastOneInTheWalk.getY()));
                copy3.add(new Point((int)lastOneInTheWalk.getX(),(int)lastOneInTheWalk.getY()+1));
                printAllWalks(copy, n, m);
                printAllWalks(copy2, n, m);
                printAllWalks(copy3, n, m);
            }
        }
        else{                                                           // walk is complete
            counter++;
            System.out.printf("%4d:", counter);
            for(Point i:walk)
                System.out.printf(" (%d,%d)",(int)i.getX(),(int)i.getY());
            System.out.println();    
        }
    }

}