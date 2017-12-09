/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

import java.util.logging.Level;
import java.util.logging.Logger;
import static mergesort.SorterProcess.doMergeSort;
import static mergesort.SorterProcess.mergeHalves;

/**
 *
 * @author jet
 */
public class Helper extends Thread{
    
    private int start,end,mid;
    private String inputString;
    private int[] indices, tempind;
    
    private Helper helper;

    //suffixes ,indices, tempString, tempIdx, 0, suffixes.length-1
    public Helper(String inputString, int[] indices,int[] tempInt,int start,int end){
        this.start = start;
        this.end = end;
        this.inputString = inputString;
        this.tempind = tempInt;
        this.indices = indices;
        this.helper = null;
        
    }
    //suffixes ,indices, tempString, tempIdx, 0, suffixes.length-1
    public Helper(String inputString, int[] indices,int[] tempInt,int start,int end, Helper helper){
        this.start = start;
        this.end = end;
        this.inputString = inputString;
        this.helper = helper;
        this.indices = indices;
        this.tempind = tempInt;
    }
    
    
    public void run(){
        if(start >=end){
            return;
        }
        
        int middle = (start + end)/2;
        //Left side
      
        //right side

        Helper h = null;
        if(Spawner.isSpawnThread()){
            //Helper h = new Helper(a ,tempString, indexes, tempIdx, 0, a.length-1);
            h = new Helper(inputString,indices,tempind,middle+1,end,this);
            h.start();
        }else{
            //String[] a,int[] idx,int start,int end,String[] tempString, int[] tempidx
            //doMergeSort(String[] a,int[] idx,int start,int end,String[] tempString, int[] tempidx
            SorterProcess.doMergeSort(inputString,indices,middle+1,end, tempind);
        }
        

        SorterProcess.doMergeSort(inputString,indices,start,middle, tempind);
        
                
        if(h != null){
            try {
                h.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        
        SorterProcess.mergeHalves(inputString,indices,start, end, tempind);
        
        
    }
    
    
    
}
