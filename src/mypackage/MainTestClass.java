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
        int saveMax = 0;
        int saveMaxIndex = 0;
        int saveMapIndex = 0;
        boolean restartPath=false;
        
        // Write path
        //System.out.print(max);
        
        for(int j=1; j<map.size(); j++){
            
            // restart for new path 
            // if the next numbers are prime.
            if(restartPath){
                maxIndex--;   
                restartPath = false;
             }
            // prime control
            if(!checkingPrime(map.get(j).get(maxIndex)) &&
                checkingPrime(map.get(j).get(maxIndex+1))){
                // Write path
                //System.out.print(" > " +map.get(j).get(maxIndex) );
                // add to the maximum sum
                max+=map.get(j).get(maxIndex);
                
                //is prime control
            }else if(!checkingPrime(map.get(j).get(maxIndex+1)) &&
                      checkingPrime(map.get(j).get(maxIndex))){ // if index element is prime
                // Write path
                // System.out.print("");
                //System.out.print(" >" +map.get(j).get(maxIndex+1) );
                // add to the maximum sum.
                max += map.get(j).get(maxIndex+1);
                maxIndex++;    
             // 2 numbers are not prime    
            }else if(!checkingPrime(map.get(j).get(maxIndex)) && 
                     !checkingPrime(map.get(j).get(maxIndex+1))){
                
                if( !restartPath ){
                    saveMax = max;
                    saveMaxIndex = maxIndex;
                    saveMapIndex = j-1;
                }

                if( !restartPath && map.get(j).get(maxIndex)>map.get(j).get(maxIndex+1)){
                    // System.out.print("");
                    //System.out.print(" >" +map.get(j).get(maxIndex) );
                    // add to the maximum sum.
                    max += map.get(j).get(maxIndex);
                    
                }else{
                    
                    if(!restartPath){
                       
                        //System.out.print(" >" +map.get(j).get(maxIndex+1) );
                        // add to the maximum sum.
                        max += map.get(j).get(maxIndex+1);
                        maxIndex++;
                    }
                }
                     
            }else{ // 2 numbers are prime.
                
                //save values to restart for new path
                j=saveMapIndex;
                max = saveMax;
                maxIndex = saveMaxIndex;
                restartPath = true;
                    
            }
        }
        // Write the maximum sum.
        System.out.println("\nMax:" +  max); 
        
        /*
        if( restartPath
                && (!checkingPrime(map.get(j).get(maxIndex)) && !checkingPrime(map.get(j).get(maxIndex+1)))
                && (map.get(j).get(maxIndex) > map.get(j).get(maxIndex+1)))
            {
                saveMaxIndex = maxIndex+1;
                saveMax = max;
                saveMapIndex = j;
                 // Write path
                System.out.print(" >.." + map.get(j).get(maxIndex+1) );
                maxIndex++;
                restartPath =false;
                
            }
        */
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
