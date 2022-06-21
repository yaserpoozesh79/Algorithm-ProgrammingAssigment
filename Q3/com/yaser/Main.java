package com.yaser;

import java.util.Scanner;

public class Main{

    public static final int INFINITY = (int)Double.POSITIVE_INFINITY;

    public static void main(String[] args) {
        int[] serie1 = inputValues("enter numbers serie 1 separated by space:");
        int[] serie2 = inputValues("enter numbers serie 2 separated by space:");
        System.out.println("minimum Warping Distance: "+minWarpingDistance(serie1,serie2));
        if(serie1.length == serie2.length)
            System.out.println("Euclidean Distance:       "+euclidianDistance(serie1,serie2));
    }

    public static int[] inputValues(String message){
        System.out.println(message);
        String[] stringNumbers = NSO().nextLine().split(" ");  
        int[] result = new int[stringNumbers.length];
        for(int i=0 ; i<stringNumbers.length ; i++)
            result[i] = Integer.parseInt(stringNumbers[i]);
        return result;    
    }

    public static Scanner NSO(){
        return new Scanner(System.in);
    }

    public static double minWarpingDistance(int[] serie1, int[] serie2){
        int n = serie1.length;
        int m = serie2.length;
        int[][] D = new int[n+1][m+1];
        for(int i=0 ; i<=n ;i++)
            D[i][m] = INFINITY;
        for(int j=0 ; j<=m ; j++)
            D[n][j] = INFINITY;
        D[n][m] = 0;    
        for(int i=n-1 ; i>-1 ;i--)
            for(int j=m-1 ; j>-1 ; j--)
                D[i][j] = min(D[i+1][j], D[i+1][j+1], D[i][j+1])+(int)Math.pow(serie1[i]-serie2[j], 2);
        return Math.sqrt(D[0][0]);
    }

    public static int min(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }

    public static final double euclidianDistance(int[] serie1, int[] serie2){
        if(serie1.length != serie2.length)
            throw new IllegalStateException("Error: the two series MUST be the same in length!!!");
        int distance=0;
        for(int i=0 ; i<serie1.length ; i++)
            distance += (int)Math.pow(serie1[i]-serie2[i], 2);
        return Math.sqrt(distance);    
    }
}