/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jet
 */
public class Spawner {
    
    public static int numThread = 0;
    public static int maxThread = 8;
    
    public synchronized static boolean isSpawnThread(){
        if(numThread < maxThread){
            numThread++;
            return true;
        }else return false;
    } 
    

    public static void doSort(String inputString, int[] indices){
        int[] tempIdx = new int[indices.length];
        
        //String[] suffixes, int[] indices,String[] tempString,int[] tempInt,int start,int end, Helper helper
        Helper h = new Helper(inputString ,indices, tempIdx, 0, inputString.length() -1 );
        h.start();
            
         try {
            h.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MergeSort.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       System.out.println("Finished with "+Spawner.numThread +" threads");  
         
         
    }
   
    
}
