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
     int index = median(data,start,end);

     //the value of the index is the pivot element
     int pivot = data[index];

     //swaps the starting index's value with the pivot value
     data[index] = data[start];
     data[start] = pivot;

     //holds original starting index
     int startCount = start;

     //edgecase: when the original start == end, just return start(end can also work)
     if (startCount == end) return start;

     Random rng = new Random();
     while (startCount < end) {
       int val = data[startCount];
       if (val < pivot || startCount == start) {
         //when the number is smaller, keep where it is
         startCount=startCount+1;
       } else if (val > pivot) { //|| (data[start] == pivot && rando == 1)) {
        //for every time the start(+1) index element is greater than the pivot element, swap with end value
        //makes sure that all the bigger numbers are moved to the end
        //end moves back one so the big numbers are not replaced
        data[startCount] = data[end];
        data[end] = val;
        end = end -1;
      } else {
        //in the case that val is equal, 50% chance to keep it in place, 50% chance to move to back
        int hold = Math.abs(rng.nextInt()) % 2;
        if (hold == 0) {
          startCount++;
        } else {
          data[startCount] = data[end];
          data[end] = val;
          end--;
        }
      }
      }
      //at this point start == end
      //if the current start/end index element is greater than pivot, moves to the left once
      if (data[startCount] > pivot) {
          startCount--;
      }
      //swaps and places pivot back
      data[start] = data[startCount];
      data[startCount] = pivot;
      return startCount;
    }


    public static int quickselect(int[] data, int k) {
      return quickselectHelp(data,k,0,data.length-1);
    }

    public static int quickselectHelp(int[] data, int k,int start,int end) {
      int partitionIndex = partition(data,start,end);
      while (partitionIndex != k) {
        if (partitionIndex < k) {
          start = partitionIndex +1;
          partitionIndex = partition(data,start,end);
        } else {
          end = partitionIndex -1;
          partitionIndex = partition(data,start,end);
        }
      }
      return data[partitionIndex];
    }


    //modies array into increasing order (left to right)
    public static void quicksort(int[] data) {
      quicksortHelp(data,0,data.length-1);

    }
    public static void quicksortHelp(int[] data, int lo, int hi) {
      if (lo >= hi) return;
      int pivotIndex = partition(data,lo,hi);
      quicksortHelp(data,lo,pivotIndex-1);
      quicksortHelp(data,pivotIndex+1,hi);
    }


    //helper method that finds median
    private static int median(int[] data, int start, int end) {
      int mid = data.length/2;
      if (data[start] >= data[mid] && data[start] <= data[end]) {
        return start;
      } else if (data[start] >= data[end] && data[start] <= data[mid]) {
          return start;
          }
        else if (data[mid] <= data[end] && data[mid] >= data[start]) {
          return mid;
        }
        else if (data[mid] >= data[end] && data[mid] <= data[start]) {
          return mid;
        }
        else return end;
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
