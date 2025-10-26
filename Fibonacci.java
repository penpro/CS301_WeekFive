public class Fibonacci {

    public static void main(String[] args) {
        if (args.length < 1 ){
            System.out.println("Fibonacci requires an int value argument greater than 0");
            return;
        }
        long start = System.currentTimeMillis();
        if (args.length == 1) {
            int recFibN = Integer.parseInt(args[0]);
            if (recFibN > 46){
                System.out.println("This will exceed int max value and fail");
                return;
            }
            if (recFibN > 1) {
                System.out.println(recFibonacci(recFibN));
            }
        }
        long now = System.currentTimeMillis();
        System.out.println("This calculation took: " + (now - start) / 1000.0);
    }

    public static int recFibonacci(int n) {
        if (n <= 1) return n;
        return recFibonacci(n - 1) + recFibonacci(n - 2);
    }
}
