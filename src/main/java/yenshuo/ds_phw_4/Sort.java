/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yenshuo.ds_phw_4;
import java.util.*;

/**
 *
 * @author yenshou
 */
public class Sort {
    
    public static void mergesort(int[] input){
        
        if(input.length > 1){
            //divide into two subarrays recursively
            int M = input.length / 2;
            int[] L = new int[M];
            int[] R = new int[input.length - M];
            System.arraycopy(input, 0, L, 0, M);
            System.arraycopy(input, M, R, 0, input.length - M);
            
            mergesort(L);
            mergesort(R);
            
            int i = 0, j = 0, k = 0;
            // put sub-arrays back into the main array
            while(i<L.length && j <R.length){
                if(L[i]<R[j]){
                    input[k]=L[i];
                    i++;
                }
                else{
                    input[k]=R[j];
                    j++;
                }
                k++;
            }
            while(i<L.length){
                input[k] = L[i];
                i++;
                k++;
            }
            while(j<R.length){
                input[k] = R[j];
                j++;
                k++;
            }
        }
    }
    
    public static void swap(int[] input, int i, int j){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    
    
    public static int choosepivot(int[] input){
        // I choose three random element and find the median
        int L = input.length;
        int a = new Random().nextInt(L);
        int A = input[a];
        int b = new Random().nextInt(L);
        int B = input[b];
        int c = new Random().nextInt(L);
        int C = input[c];
        int max= A>B? A:B;
        max = C>max? C:max;
        int min= A<B? A:B;
        min = C<min? C:min;
        return (A+B+C)-max-min;
    }
    public static void quicksort(int[] input){
        if(input.length>1){
            int p = choosepivot(input);
            int i = -1;
            int j = input.length;
            while(true){
                //make the left part smaller the pivot and the right one bigger than it
                do{
                    i++;
                }while(input[i]<p);
                do{
                    j--;
                }while(input[j]>p);
                if(i<j){
                    swap(input, i, j);
                }
                else{
                    break;
                }
            }
            // recursive
            int[] L = new int[i];
            int[] R = new int[input.length - i];
            System.arraycopy(input, 0, L, 0, i);
            System.arraycopy(input, i, R, 0, input.length - i);
            quicksort(L);
            System.arraycopy(L, 0, input, 0, i);
            quicksort(R);
            System.arraycopy(R, 0, input, i, input.length - i);
            //java doesnt use reference, so we need to copy back
        }
    }
    
    
    public static int partition(int[] input, int l, int r){
        int x = input[r], i=l;
        for(int j=l; j<=r-1; j++){
            if(input[j]<=x){
                swap(input, i, j);
                i++;
            }
        }
        swap(input, i, r);
        //elements before index i would smaller than x
        return i;
    }
    public static int select(int[] input, int k){
        int l=0, r=input.length-1; 
        int pos = partition(input, l, r);
        // we only have to care about kth, don't need to care about the rest part 
        // so complexity would be O(n)
        if(pos-l == k){
            return input[pos];
        }
        else if(pos-l > k){
            int[] n = new int[pos-l];
            System.arraycopy(input, 0, n, 0, pos-l);
            return select(n, k);
        }
        else{
            int[] n = new int[r-pos];
            System.arraycopy(input, pos+1, n, 0, r-pos);
            return select(n, k-pos+l-1);
        }
    }
    public static void print(int[] arr){
        for(int z=0; z<arr.length;z++){
            if(z==arr.length-1){
                System.out.println(arr[z]);
            }
            else{
                System.out.print(arr[z]+",");
            }
        }
    }
    
    public static void main(String[] args){
        // test for mergesort
        int[] arr1 = {5,6,3,8,1};
        System.out.print("input=");
        print(arr1);
        mergesort(arr1);
        System.out.print("After mergesort, output=");
        print(arr1);
        // end mergesort test
        
        // begin quicksort test 
        int[] arr2 = {5,100,3,7,9,1,11,15,2,7,7};
        System.out.print("input=");
        print(arr2);
        quicksort(arr2);
        System.out.print("After quicksort, output=");
        print(arr2);
        // end quicksort test
        
        // begin select test
        int[] arr3 = {5,6,3,8,1};
        int k = 0;
        System.out.print("select=");
        System.out.print(select(arr3,k));
        // end select test
    }
}
