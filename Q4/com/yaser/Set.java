package com.yaser;

import java.util.ArrayList;

public class Set<T> {
    
    private ArrayList<T> elements;
    private int NOSS;                    // Number Of Sub Sets


    public Set(T[] elements){
        this.elements = new ArrayList<>();
        for(T i: elements)
            this.elements.add(i);
        setNOSS();
    }
    public Set(ArrayList<T> elements){
        this.elements = new ArrayList<>();
        for(T i: elements)
            this.elements.add(i);
        setNOSS();
    }
    private void setNOSS(){
        NOSS = (int)Math.pow(2, size()); 
    }
    public int getNOSS(){
        return NOSS;
    }

    public int size(){
        return elements.size();
    }

    public Set<T> subset(int subsetIndex){
        if(subsetIndex < 0 || subsetIndex>NOSS-1)
            throw new IllegalStateException("Error: input Out of range!!");
        ArrayList<T> subset = new ArrayList<>();    
        for(int i = size()-1 ; i>=0 ; i--){
            if(subsetIndex%2==1){
                subset.add(0, elements.get(i));
            }
            subsetIndex/=2;
        }    
        return new Set<T>(subset);
    }

    /**
     * in the list of {@code S} sized subsets of the Set,
     * returnes the {@code (K+1)}th Subset
     * @param S : Size of the subset
     * @param K : index of the subset
     */
    public Set<T> limitedSubset(int S, int K){
        int j=0;
        ArrayList<T> ithSubset;
        for(int i = 0 ; i<NOSS ; i++){
            ithSubset = subset(i).elements;
            if(subset(i).size() == S){
                if(K == j)
                    return new Set<T>(ithSubset);
                j++;
            }
        }
        ithSubset = new ArrayList<>();
        return new Set<T>(ithSubset);
    }

    /**
     * in the list of all subsets of the Set, findes {@code subset}
     * and returnes its index
     * @param subset : subset whose index is the target to find
     */
    public int indexOf(Set<T> subset){
        int index=0;
        for(int i=size()-1 ; i>=0 ; i--)
            if(subset.elements.indexOf(elements.get(i)) > -1)
                index+=Math.pow(2, size()-1-i);
        return index;        
    }
    
    public static <T> Set<T> subtract(Set<T> A, T b){
        ArrayList<T> result = new ArrayList<>();
        for(T i: A.elements)
            if(!i.equals(b))
                result.add(i);
        return new Set<T>(result);        
    }

    public boolean contains(T element){
        return elements.indexOf(element) != -1;
    }

}
