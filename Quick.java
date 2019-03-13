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
     //chooses a random index from start to end (inclusive)
     Random rng = new Random();
     int index = rng.nextInt(data.length+1);
     //extra while loop to make sure index is valid

     //choosing a pivot using the median value of start,end, and the middle element
     int middleIndex = data.length/2;
     int middleVal = data[middleIndex];
     int index = start;

     if (start >= end && start >= middleVal) {
       if (middleVal >= end) {
         index = middleVal;
       } else {
         index = end;
       }
     }

     if (end >= start && end >= middleVal) {
       if (start >= middleVal) {
         index = start;
       } else {
         index = middleVal;
       }
     }

     if (middleVal >= start && middleVal >= end) {
       if (start >= end) {
         index = start;
       } else {
         index = end;
       }
     }

     //the value of the index is the pivot element
     int pivot = data[index];

     //swaps the starting index's value with the pivot value
     int temp = data[start];
     data[start] = pivot;
     data[index] = temp;

     //holds original starting index
     int tempS = start;

     start = start + 1;

     //edgecase: when the original start == end, just return start(end can also work)
     if (tempS == end) return start;

     while (start != end) {
      // int rando = rng.nextInt(2);

       if (data[start] > pivot) { //|| (data[start] == pivot && rando == 1)) {
        //for every time the start(+1) index is greater than the pivot element, swap with end value
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

  public static void quicksort(int[] data, int lo, int hi) {
    if (low >= hi) return;
    pivot = partition(data,lo,hi);
    int firstHalfStart = lo;
    int firstHalfEnd = pivot -1;
    int secHalfStart = pivot +1;
    int secHalfEnd = hi;
    quicksort(data,firstHalfStart,firstHalfEnd);
    quicksort(data,secHalfStart,secHalfEnd);
  }

  }




}
