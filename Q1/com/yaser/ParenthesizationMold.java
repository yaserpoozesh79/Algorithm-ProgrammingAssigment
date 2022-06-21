// algorithm has been revised. this file is no longer needed



// package com.yaser;

// public class ParenthesizationMold {
    
    
//     private final String[] mold;     // an array of strings that holds names and surrounding parenthesis.    
//     private final String[] namesSet; // array containing the names.
//     private int remainingPairs;      // number of pairs of parenthesis needed to complete the mold.

//     public ParenthesizationMold(String[] names){
//         namesSet = names;
//         int n = namesSet.length;
//         int moldSize = 2*n+1;
//         mold = new String[moldSize];
//         for(int i=0 ; i<moldSize ; i++){
//             if(i%2 == 0)
//                 mold[i] = "";
//             else    
//                 mold[i] = namesSet[i/2];
//         }
//         remainingPairs = (n-2);
//         if(remainingPairs < 0)
//             remainingPairs = 0;
//     }
    
//     public boolean isFull(){
//         return remainingPairs == 0;
//     }

//     /**
//      * breakes the range from {@code start} to {@code end}
//      * into two pieces in {@code breakPoint}. it is done by parenthesizing 
//      * the two halves. example: a b c d -> ((a b)(c d))
//      * 
//      * @param start : index of range start name in the array of names.
//      * @param end : index of range end name in the array of names.
//      * @param breakPoint : index of the name in front of which is break point.
//      */
//     public void addBreakPoint(int start, int end, int breakPoint){
//         if(end - start <=1)
//             return;
//         if(isFull())
//             throw new IllegalStateException("mold is full");
//         if(start<0 || start>=namesSet.length ||
//             end<0 || end>=namesSet.length ||
//             breakPoint<0 || breakPoint>=namesSet.length)
//             throw new IllegalStateException("Error: indices out of range!");
//         if(start>breakPoint || breakPoint >= end)
//             throw new IllegalStateException("Error: indices incorrect!");
//         else{
//             if(breakPoint == start)
//                 addAPair(start+1, end);
//             else if(breakPoint == end-1)
//                 addAPair(start, end-1);
//             else{
//                 addAPair(start, breakPoint); 
//                 addAPair(breakPoint+1, end);   
//             }    
//         }       
//     }

//     private void addAPair(int opensAt, int closesAt){
//         if(opensAt<0 || opensAt>=namesSet.length ||
//             closesAt<0 || closesAt>=namesSet.length)
//             throw new IllegalStateException("Error: indices out of range!");
//         mold[opensAt*2] += '(';
//         mold[closesAt*2+2] += ')';
//         remainingPairs -= 1;
//     }  

//     public ParenthesizationMold copy(){
//         ParenthesizationMold newObj = new ParenthesizationMold(this.namesSet);
//         String parGroup;
//         String slot;
//         for(int i=0 ; i<mold.length ; i+=2){
//             parGroup = mold[i];
//             slot = "";
//             for(char j:parGroup.toCharArray())
//                 slot+=j;
//             newObj.mold[i] = slot;   
//         }
//         newObj.remainingPairs = remainingPairs;
//         return newObj;
//     }

//     private void organizeParenthesis(){
//         String parGroup;
//         int opennings, closings;
//         for(int i=0 ; i<mold.length ; i+=2){
//             parGroup = mold[i];
//             opennings = 0;
//             closings = 0;
//             for(char j : parGroup.toCharArray()){
//                 if(j == '(')
//                     opennings++;
//                 else
//                     closings++;   
//             }
//             parGroup = "";
//             for(int j=0 ; j<closings ; j++)
//                 parGroup+=')';
//             for(int j=0 ; j<opennings ; j++)
//                 parGroup+='(';    
//             mold[i] = parGroup;    
//         }
//     }

//     @Override
//     public String toString(){
//         organizeParenthesis();
//         StringBuilder outPut = new StringBuilder("");
//         for(String i: mold)
//             outPut.append(i);
//         return outPut.toString();    
//     }
// }
