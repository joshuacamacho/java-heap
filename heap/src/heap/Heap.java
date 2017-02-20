package heap;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Josh
 */
public class Heap {

    /**
     * @param args the command line arguments
     */
    public static int heap[];
    public static int position=0;
    public static int swapCount=0;
    public static int optimalSwapCount=0;
    public static boolean CONTINUE=true;
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(CONTINUE){
            switch (option(scan)){
                case 1:
                {
                    randomGen();
                    break;
                }
                case 2:
                {
                    fixedValues();
                    break;
                }
                case 3:{
                    CONTINUE=false;
                    break;
                }
                default:{
                    System.out.println("Not an acceptable value. try again");
                    break;
                }
            }
            
            
        }
    }

    private static int option(Scanner scan) {
        System.out.print("\nPlease select how to test the program:"
        +"\n(1) 20 sets of 100 randomly generated integers"
        +"\n(2) Fixed integer values 1-100"
        +"\n(3) Exit"
        +"\nEnter Choice: ");
        return scan.nextInt();
    }

    private static void fixedValues() {
      swapCount=0;
       optimalSwapCount=0;
       

       
         //insertion method
         heap = null;
         heap = new int[100];
         position=0;
         int vals[] = new int[100];
         for(int i=0; i<100; i++){
             vals[i]=i+1;
         }         
         for(int i=0; i<vals.length; i++){
             insert(vals[i]);
         } 
         print(heap,10);
         System.out.println("\nNumber of swaps: " + swapCount);
         //optimal method
        heap = null;
        heap = vals;
        
        optimalHeapify();
        print(heap,10);
       
       
       
       //optimal method
       System.out.println("Number of swaps: "+ optimalSwapCount);
    }

    private static void randomGen() {
       swapCount=0;
       optimalSwapCount=0;
       int numTimes=20;
       // do it 20 times
       for(int j=0; j<numTimes; j++){
         //insertion method
         heap = null;
         heap = new int[100];
         position=0;
         int vals[] = new int[100];
         for(int i=0; i<100; i++){
             vals[i]=i+1;
         }
         shuffle(vals);
         

         
         for(int i=0; i<vals.length; i++){
             insert(vals[i]);
         } 
         
         
         //optimal method
        heap = null;
        heap = vals;
        optimalHeapify();
       }
       
       System.out.println("\nAverage swaps for series of insertions: " + (swapCount/numTimes));
       
       //optimal method
       System.out.println("Average swaps for optimal method: "+ (optimalSwapCount/numTimes));
    }
    
    
    static void shuffle(int[] vals)
  {
    // If running on Java 6 or older, use `new Random()` on RHS here
    Random rnd = ThreadLocalRandom.current();
    for (int i = vals.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = vals[index];
      vals[index] = vals[i];
      vals[i] = a;
    }
  }

    private static void print(int[] vals) {
        for(int i=0; i<vals.length; i++){
            System.out.print(vals[i] + " ");
        }
        System.out.print("\n");
    }
    
    private static void print(int[] vals,int size) {
        for(int i=0; i<size; i++){
            System.out.print(vals[i] + " ");
        }
        System.out.print("\n");
    }

    private static void insert(int val) {
       if(heap==null){
           heap=new int[100];
       }
       heap[position]=val;
       
       
           int parent =position;
           int current = position;
           while(parent !=0 ){
               parent = ((parent-1)/2);
               if(heap[current] > heap[parent]){
                   heap[current] = heap[current] ^ heap[parent];
                   heap[parent] = heap[current] ^ heap[parent];
                   heap[current] = heap[current] ^ heap[parent];
                   current=parent;
                   swapCount++;
               }else{
                   break;
               }
               
           }
       
       position++;
       
       
    }

    private static void optimalHeapify() {
       position = (heap.length-1)/2;
       for(int i=position; i>=0; i--){
           maxHeapify(i);
       }
       
    }

    private static void maxHeapify(int i) {
        int left = 2*i;
        int right = 2*i +1;
        int largest = i;

        if (left < heap.length && heap[left] > heap[largest])
             largest = left;
        if (right < heap.length && heap[right] > heap[largest]) 
             largest = right;

        if (largest != i){
         heap[i]= heap[i] ^ heap[largest];
         heap[largest] =  heap[i] ^ heap[largest];
         heap[i] =  heap[i] ^ heap[largest];
         optimalSwapCount++;
         maxHeapify(largest);
        }
        
    }
    
    
}
