/*Given an array a[] of N distinct integers (positive or negative) in ascending order.
Devise an algorithm to find an index i such that a[i] = i if such an index exists.
Hint: binary search.


It helps if you read the question properly
 */

public class indexEqualsValue {
    public static void main(String[] args) {
        int[] testArray = {-5,-1,2,4,5,6,7,8,9,10,11,12};
        System.out.println(naiveIndexEqualsValue(testArray));
        System.out.println(binaryIndexEqualsValue(testArray));
    }

    public static int naiveIndexEqualsValue(int[] inputArray){
            for (int i = 0 ; i < inputArray.length ; i++){
                if (inputArray[i]==i){
                    return i;
                }
            }
        return -1;
    }
    /* // this is dumb i don't need to copy the array just to compare values
    public static int binaryIndexEqualsValue(int[] inputArray){
        //don't want to try and divide by 0
        if (inputArray.length == 0){
            return -1;
        }
        // find midpoint
        int midpointIndex = (inputArray.length-1)/2;

        // if it happens to match, cool, found it
        if (midpointIndex == inputArray[midpointIndex]){
            return midpointIndex;
        }

        // if the value at the midpoint is less than the midpoint index, look lower
        if (midpointIndex <= inputArray[midpointIndex]) {
            System.out.println("index is higher than value looking lower");
            int[] lower = new int[inputArray.length-midpointIndex];
            System.arraycopy(inputArray, 0, lower, 0, lower.length);
            return binaryIndexEqualsValue(lower);
        }

        // if the value at the midpoint is greater than the midpoint index, look higher
        if (midpointIndex > inputArray[midpointIndex]) {
            System.out.println("index is lower than value, looking higher");
            int[] upper = new int[inputArray.length-1 - midpointIndex];
            System.arraycopy(inputArray, midpointIndex, upper, 0, upper.length);
            return binaryIndexEqualsValue(upper);
        }
        return -1;
    }
    */

    public static int binaryIndexEqualsValue(int[] inputArray){

        return binIEVHelper(inputArray, 0, inputArray.length-1);


    }

    private static int binIEVHelper(int[] inputArray, int low, int hi){

        int midpoint  = low + ((hi-low)/2);
        if(low > hi ) return -1;
        if (midpoint == inputArray[midpoint]) return midpoint;
        if (inputArray[midpoint] > midpoint){
            return binIEVHelper(inputArray,low,midpoint-1);
        }
        if (inputArray[midpoint] < midpoint){
            return binIEVHelper(inputArray,midpoint+1,hi);
        }
        return -1;
    }

}
