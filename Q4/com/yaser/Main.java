package com.yaser;


public class Main{

    public static void main(String[] args) {
        int inf = 1000;
        int[][] W = {{  0,  2,  9,inf},
                     {  1,  0,  6,  4},
                     {inf,  7,  0,  8},
                     {  6,  3,inf,  0} 
        };

        int n = W.length;
        int[][] P = new int[n][(int)Math.pow(2, n-1)];
        System.out.println(travel(n, W, P));
    }

    public static int travel(int n, int[][] W, int[][] P){
        int i,j,k,a,comb;
        Set<Integer> V = new Set<>(consequtiveNumbers(0, n-1));
        Set<Integer> V2_n = Set.subtract(V, 0);
        Set<Integer> A;
        int[][] D = new int[n][(int)Math.pow(2, n-1)];
        for(i=0 ; i<n ;i++)
            D[i][0] = W[i][0];
        for(k=1 ; k<=n-2 ; k++){
            comb = bin3(n-1,k); // a combination of k out of n-1. (number of subsets of V-{v1} with size k)
            for(a=0 ; a<comb ; a++){
                A = V2_n.limitedSubset(k, a);
                for(i=1 ; i<n ; i++){
                    if(A.contains(i))
                        continue;
                    boolean isFirstIteration = true;
                    for(j=1 ; j<n ; j++){
                        if(!A.contains(j))
                            continue;
                        if(isFirstIteration){
                            D[i][V2_n.indexOf(A)] = W[i][j] + D[j][V2_n.indexOf(Set.subtract(A, j))];
                            P[i][V2_n.indexOf(A)] = j;
                            isFirstIteration = false;
                        }else
                            if(W[i][j] + D[j][V2_n.indexOf(Set.subtract(A, j))] < D[i][V2_n.indexOf(A)]){
                                D[i][V2_n.indexOf(A)] = W[i][j] + D[j][V2_n.indexOf(Set.subtract(A, j))];
                                P[i][V2_n.indexOf(A)] = j;
                            }    
                    }    
                }
            }
        }
        for(j=1 ; j<n ; j++){
            if(j==1){
                D[0][D[0].length-1] = W[0][j] + D[j][V2_n.indexOf(Set.subtract(V2_n, j))];
                P[0][D[0].length-1] = j;
            }else
                if(W[0][j] + D[j][V2_n.indexOf(Set.subtract(V2_n, j))] < D[0][D[0].length-1]){
                    D[0][D[0].length-1] = W[0][j] + D[j][V2_n.indexOf(Set.subtract(V2_n, j))];
                    P[0][D[0].length-1] = j;
                }    
        }
        return D[0][D[0].length-1];
    }

    public static Integer[] consequtiveNumbers(int start, int end){
        Integer[] result = new Integer[Math.abs(start-end)+1];
        int i,j;
        if(start<end)
            for(i=start,j=0 ; i<=end ; i++,j++)
                result[j] = i;
        else if(start>end)
            for(i=end,j=result.length-1 ; i<=start ; i++,j--)
                result[j] = i;     
        else
            result[0] = start;           
        return result;
    }

    public static int bin3(int n, int k){
        int[] B = new int[k+1];
        for(int i=0 ; i<n ; i++)
            for(int j=Math.min(i, k) ; j>=0 ; j--){
                if(j==0 || j==i)
                    B[j]=1;
                else
                    B[j]=B[j] + B[j-1];   
            }
        return B[k] + B[k-1];    
    }
}