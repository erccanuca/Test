/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author ercan
 */
public class MainTestClass {
    
    /**
     * Main Test 
     * @param args command lines arguments
     * @throws FileNotFoundException if file not found throw exception.
     */
    public static void main(String[] args) throws FileNotFoundException{
       
        /* Create map; index to keys and values to arraylists */
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Integer i=0;
        
        // Read from file and add to my map all values.
        try (Scanner scanner = new Scanner(new File("test.txt"))) {
            
            // read lines
            while(scanner.hasNextLine()){
                
                // split with new line
                for(String item : scanner.nextLine().split("\n")){
                    
                    // create arraylists to put my map.
                    ArrayList<Integer> arrList = new ArrayList<>();
                    Scanner s = new Scanner(item);
                    s.useDelimiter(" ");
                    while(s.hasNext()){
                        
                        String item2 = s.next();
                        // read integer and add arraylists
                        if(!item2.equals("")){
                            //System.out.println(item2);
                            arrList.add(Integer.parseInt(item2));
                        }
                    }
                    // finally put  arraylist to my map.
                    map.put(i, arrList);
                    // increase nex map index-key
                    i++;
                    
                    // close scanner integers
                    s.close();     
                }
            }
            // close scanner lines
            scanner.close();        
        }
        // Print all map
        System.out.println(map.toString());
        
        /*
        *  Find the maximum sum of the numbers from top to bottom
        */
        // take first key value of first arraylist element.
        int max = map.get(0).get(0);
        // Save first element of arraylist index.
        int maxIndex = 0;
        
        // Write path
        System.out.print(max);
        
        for(int j=1; j<map.size(); j++){
            
            // check index+1>index number and is not prime
            if(map.get(j).get(maxIndex+1) > map.get(j).get(maxIndex) &&
                    !checkingPrime(map.get(j).get(maxIndex+1))){
                // Write path
                System.out.print(" > " +map.get(j).get(maxIndex+1) );
                // add to the maximum sum
                max+=map.get(j).get(maxIndex+1);
                // increase index for next element controlling.
                maxIndex++;
                
                // check index+1<=index number
            }else{
                // is not prime control
                if(!checkingPrime(map.get(j).get(maxIndex))){
                    // Write path
                    System.out.print(" > " +map.get(j).get(maxIndex) );
                    // add to the maximum sum
                    max+=map.get(j).get(maxIndex);
                }
                else{ // if index element is prime
                    // Write path
                    System.out.print(" > " +map.get(j).get(maxIndex+1) );
                    // add to the maximum sum.
                    max+=map.get(j).get(maxIndex+1);
                }
            }
        }
        // Write the maximum sum.
        System.out.println("\nMax:" +  max);    
    }
    /**
     * Checking is prime number
     * @param n integer number
     * @return true if is prime, otherwise false
     */
    static boolean checkingPrime(int n){
        
        for(int i=2;i<=n/2;i++) {
        if(n%i==0)
            return false;
        }
        return true;
    }
    
}
