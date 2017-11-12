/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Arrays;

/**
 *
 * @author amcan
 */
public class MainStandard {
    
    public static void main(String[] args) {
        
        String input = "Banana$";
        
        String[] suffixes = new String[input.length()];
        int[] indices = new int[input.length()];
        
        for(int i = 0; i < input.length(); i++) {
            
            suffixes[i] = input.substring(i);
            indices[i] = i;
            
        }
        
        System.out.println(Arrays.toString(suffixes));
        System.out.println(Arrays.toString(indices));
        
        divide(suffixes, indices, 0, suffixes.length - 1);
        
        System.out.println(Arrays.toString(suffixes));
        System.out.println(Arrays.toString(indices));
        
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
