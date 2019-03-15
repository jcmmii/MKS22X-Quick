import java.util.*;

public class Quick {

  /*Choose a random pivot element between the start and end index inclusive,
   Then modify the array such that:
   *1. Only the indices from start to end inclusive are considered in range
   *2. A random index from start to end inclusive is chosen, the corresponding
   *   element is designated the pivot element.
   *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
   *4. all elements in range that are larger than the pivot element are placed after the pivot element.
   *@return the index of the final position of the pivot element.
   */
   public static int partition (int[] data, int start, int end) {
     //choosing a pivot using the median value of start,end, and the middle element
     int middleIndex = data.length/2;
     //assuming small, medium, big
     int med = data[middleIndex];
     int small = data[start];
     int big = data[end];
     int index = start;

     //a mini sort, finds the median value of start, middle, and end elements
     if (small > med) {
       int temp = small;
       small = med;
       med = temp;
     }

     if (med > big) {
       int temp = med;
       med = big;
       big = temp;
     }

     if (small > med) {
       int temp = small;
       small = med;
       med = temp;
     }

     //the value of the index is the pivot element
     int pivot = data[index];
     //System.out.println("PIVOT VALUE AND INDEX" + pivot + ", " + index);

     //swaps the starting index's value with the pivot value
     int temp = data[start];
     data[start] = pivot;
     data[index] = temp;

     //holds original starting index
     int tempS = start;

     start = start + 1;

     //edgecase: when the original start == end, just return start(end can also work)
     if (tempS == end) return start;

     Random rng2 = new Random();
     while (start != end) {
       Random rng = new Random();
       if (data[start] > pivot || (data[start] == pivot && rng2.nextInt() %2 ==0)) { //|| (data[start] == pivot && rando == 1)) {
        //for every time the start(+1) index element is greater than the pivot element, swap with end value
        //makes sure that all the bigger numbers are moved to the end
        //end moves back one so the big numbers are not replaced
        temp = data[start];
        data[start] = data[end];
        data[end] = temp;
        end = end -1;
      } else {
        //when the number isn't greater but smaller, keep where it is
        start=start+1;
      }
    }
      //at this point start == end
      if (data[start] < pivot) {
        //if the pivot is greater than the value of data[start], swaps
        //the pivot will then take the index - every left should be less, everything right should be greater
        temp = data[tempS];
        data[tempS] = data[start];
        data[start] = temp;
        return start;
      } else {
        //this means data[start] > pivot value
        //BUT at this point this can only mean that the data[start] is on the right side of the pivot
        //so, go back one index and swap, setting that index to pivot
        temp = data[start-1];
        data[start-1] = data[tempS];
        data[tempS] = temp;
        return start-1;
      }
    }


    public static int quickselect(int[] data, int k) {
      boolean solved = false;
      int startIndex = 0;
      int partitionIndex = partition(data,0,data.length-1);
      while (!solved) {
        if (partitionIndex==k) {
          solved = true;
        } else {
          if (partitionIndex > k) {
            partitionIndex = partition(data,startIndex,partitionIndex-1);
          } else {
            startIndex++;
            partitionIndex = partition(data,startIndex,partition)
          }
        }
      }
      return data[partitionIndex];
    }


    //modies array into increasing order (left to right)
    public static void quicksort(int[] data) {
      return quicksortHelp(data,0,data.length-1);

    }
    public static void quicksortHelp(int[] data, int lo, int hi) {
      if (lo >= hi) return;
      int pivotIndex = partition(data,lo,hi);
      quicksortHelp(data,lo,pivotIndex-1);
      quicksortHelp(data,pivotIndex+1,hi);
    }




    public static String toString(int[] array) {
      String ret = "";
      for (int x = 0; x < array.length; x++) {
        ret = ret + array[x] + " ";
      }
      return ret;
    }

    public static void main(String[] args) {
      int[] test = {5,4,3,8,3,2};
      System.out.println(toString(test));
      System.out.println(partition(test,0,5));
      System.out.println(toString(test));
    }

  }
