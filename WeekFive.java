public class WeekFive {

    public static void main(String[] args) {
    int[] testArray = {1,2,3,4,5,6,7,8,9};
    int[] lower = new int[(testArray.length-1)/2];
        System.out.println(lower.length);
        // input array, input array starting index, outputarray, output array starting index, how many to fill
    System.arraycopy(testArray,0,lower, 0, lower.length);
    //for ()

    }
}
