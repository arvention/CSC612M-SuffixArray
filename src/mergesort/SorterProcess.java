/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

/**
 *
 * @author jet
 */
public class SorterProcess {
    

    public static void doMergeSort(String a,int[] idx,int start,int end,int[] tempidx){
        
        if(start >=end){
            return;
        }
        
        int middle = (start + end)/2;
        //Left side
        doMergeSort(a,idx ,start,middle, tempidx);
        //right side
        doMergeSort(a, idx,middle+1,end,tempidx);
        mergeHalves(a,idx,start, end,tempidx);
        
        
    }
    
    public static void mergeHalves(String a,int[] idx,int start,int end,int[] tempidx){
        int size = end - start +1;

        int middle = (start + end)/2;
        int left = start;
        int right = middle+1;
  
        
        for(int i=start; i <= end; i++){
            
            if(left <= middle && right <= end){
         
                if( a.substring(idx[left]).compareToIgnoreCase( a.substring(idx[right])) <= 0){
                    
              
                    tempidx[i] = idx[left];
                    left++;
                }else{
              
                    tempidx[i] = idx[right];
                    right++;
                }
                   
            }else if(left > middle){
          
                 tempidx[i] = idx[right];
                right++;
                
            }else{
         
                tempidx[i] = idx[left];
                left++;
                
            }
                
        }
        
     
        System.arraycopy(tempidx, start, idx,start,size);
    }
    
    
    
}
