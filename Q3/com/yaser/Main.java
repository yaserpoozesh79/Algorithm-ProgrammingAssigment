package com.yaser;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        int[] serie1 = {1, 1, 2, 2, 2, 3, 4, 4, 4};
        int[] serie2 = {1, 2, 3, 4};
        System.out.println(minWarpingDistance(serie1,serie2));
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
    
    public static int minWarpingDistance(int[] serie1, int[] serie2){
        int n = serie1.length;
        int m = serie2.length;
        int minDis;
        int[][] D = new int[n][m];
        for(int i=0 ; i<n ;i++)
            D[i][0] = (int)Math.pow(serie1[i]-serie2[0],2);
        for(int j=1 ; j<m ; j++)
            D[0][j] = (int)Math.pow(serie1[0]-serie2[j],2);

        for(int i=1 ; i<n ;i++)
            for(int j=1 ; j<m ; j++){
                minDis = D[i][j-1];
                if(D[i-1][j] < minDis)
                    minDis = D[i-1][j];
                if(D[i-1][j-1] < minDis)
                    minDis = D[i-1][j-1];    
                D[i][j] = minDis + (int)Math.pow(serie1[i]-serie2[j],2);
            }
        return D[n-1][m-1];
    }

}