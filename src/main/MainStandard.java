/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import io.StringRandomizer;
import java.util.Arrays;
import mergesort.Spawner;

/**
 *
 * @author amcan
 */
public class MainStandard {
    
    public static void main(String[] args) {
        
        
        //Hi guys StringRandomizer.createFile, creates a file with random chars A C T G with $ in the end.
        // parameter filename it will be saved and length of Stting
        
        //System.out.println("Generating");
        //StringRandomizer.createFile("input2.txt", 80000);
        //System.out.println("done");
        
        String input = StringRandomizer.readFile("input2.txt");
        //String input = "Banana$";
        
        System.out.println(input.substring(0));
        int[] indices = new int[input.length()];
        
        for(int i = 0; i < input.length(); i++) {
            indices[i] = i;
        }
        
        long startTime = System.nanoTime();
        
        Spawner.doSort(input, indices);
        long endTime = System.nanoTime();
        System.out.println("Time end In nanoseconds : "+ (endTime - startTime));
        System.out.println("Now Writing output ina file.... will take some time");
        
        //WRITEOUTPUT OUTPUTS 3GIG FILE AT 80K CHARS SO BE WARNED...
        //StringRandomizer.writeOutput("output.txt", input, indices);
        System.out.println("Done!");
        
    }
    
    private static void divide(String[] suffixes,
            int[] indices,
            int min, 
            int max) {
        
        if(min < max) {
            
            int mid = (min + max) / 2;
            
            divide(suffixes, indices, min, mid);
            divide(suffixes, indices, mid + 1, max);
            
            merge(suffixes, indices, min, mid, max);
            
        }
        
    }
    
    private static void merge(String[] suffixes,
            int[] indices,
            int min,
            int mid,
            int max) {
        
        int nMin = mid - min + 1;
        int nMax = max - mid;
        
        String[] suffixesMin = new String[nMin];
        int[] indicesMin = new int[nMin];
        String[] suffixesMax = new String[nMax];
        int[] indicesMax = new int[nMax];
        
        for(int i = 0; i < nMin; i++) {
            suffixesMin[i] = suffixes[min + i];
            indicesMin[i] = indices[min + i];
        }
        
        for(int i = 0; i < nMax; i++) {
            suffixesMax[i] = suffixes[mid + i + 1];
            indicesMax[i] = indices[mid + i + 1];
        }
        
        int minCurIndex = 0;
        int maxCurIndex = 0;
        
        int curIndex = min;
        
        while(minCurIndex < nMin && maxCurIndex < nMax) {
            
            if(suffixesMin[minCurIndex].compareToIgnoreCase(suffixesMax[maxCurIndex]) <= 0) {
                suffixes[curIndex] = suffixesMin[minCurIndex];
                indices[curIndex] = indicesMin[minCurIndex];
                minCurIndex++;
            } else {
                suffixes[curIndex] = suffixesMax[maxCurIndex];
                indices[curIndex] = indicesMax[maxCurIndex];
                maxCurIndex++;
            }
            curIndex++;
        }
        
        while(minCurIndex < nMin) {
            suffixes[curIndex] = suffixesMin[minCurIndex];
            indices[curIndex] = indicesMin[minCurIndex];
            minCurIndex++;
            curIndex++;
        }
        
        while(maxCurIndex < nMax) {
            suffixes[curIndex] = suffixesMax[maxCurIndex];
            indices[curIndex] = indicesMax[maxCurIndex];
            maxCurIndex++;
            curIndex++;
        }
        
    }
    
}
