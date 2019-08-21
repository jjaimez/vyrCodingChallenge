import java.io.*;
import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Solution {

    static int maxMoney(int n, long k) {

        if (Objects.isNull(n) || Objects.isNull(k)|| k < 1 || k > 4e+15 || n > 1e+9|| n < 1){
            System.out.println("Invalid Params - customSort");
            return -1;
        }

        if (n==1 &&  k == 1){
            return 0;
        }

        if (n==1){
            return 1;
        }

        if (k == 1) {
            return (int) sum(2, n);
        }

        long totalSum = sum(1, n);

        if (k > totalSum) {
            return (int) totalSum;
        }

        //The idea here is first to see if K is a sum of successive numbers.
        if (hasNaturalQuadraticEquationRoot(0.5, 0.5, -k,n)) {
            long i = 1;
            //If K is a sum of successive numbers it takes out one number and see if K does not appear again.
            while (isItPosible(k, i,sum(1,i-1),n) && i<n) {
                i++;
            }
            return (int) (totalSum - i);
        } else {
            return (int) totalSum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        long k = Long.parseLong(bufferedReader.readLine().trim());
        int res = maxMoney(n, k);
        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }


    //(n - m + 1) * (n + m) / 2 formula to get the sum of successive numbers from M to N;
    private static long sum(long m, long n) {
        return ((n - m + 1) * (n + m))/2;

    }


    //Remove one number of the succession and see if K doesn't appear again.
    //This formula tells us if exist a sum of successive numbers from 1 to N without adding M (1 <= M <= N) that is equal to K
    //derivatives from sum(1,m-1) + sum(m+1,n) = k
    private static boolean isItPosible(long k, long m, long c, long n) {
        double root1, root2;

        root1 = (-(sqrt(-8 * c + 8 * k + 4 * pow(m,2) - 4 * m + 1)-3)) / 2;
        root2 = (sqrt(-8 * c + 8 * k + 4 * pow(m,2) - 4 * m + 1)-3) / 2;

        Integer ar[] = new Integer[2];
        ar[0] = 0;
        ar[1] = 0;

        if ((root1 == Math.floor(root1)) && !Double.isInfinite(root1) && root1 > 0 && n >= root1 && m <= root1) {
            return true;
        }

        if ((root2 == Math.floor(root2)) && !Double.isInfinite(root2) && root2 > 0 && n >= root2 && m <= root1) {
            return true;
        }

        return false;
    }


    //If k is a sum of successive numbers from 1 to M with M <= N
    private static boolean hasNaturalQuadraticEquationRoot(double a, double b, double c, int n) {
        double root1, root2; //This is now a double, too.
        root1 = (-b + sqrt(pow(b, 2) - 4 * a * c)) / (2 * a);
        root2 = (-b - sqrt(pow(b, 2) - 4 * a * c)) / (2 * a);

        Integer ar[] = new Integer[2];
        ar[0] = 0;
        ar[1] = 0;

        if ((root1 == Math.floor(root1)) && !Double.isInfinite(root1) && root1 > 0 && n >= root1 ) {
            return true;
        }

        if ((root2 == Math.floor(root2)) && !Double.isInfinite(root2) && root2 > 0 && n >= root2) {
            return true;
        }

        return false;
    }


    static void tests(){
        System.out.println("Should be -1 Invalid Params: " + String.valueOf(maxMoney(Double.valueOf(1e+9).intValue(),(long) 4e+19) == -1));
        System.out.println("Should be -1 Invalid Params: " + String.valueOf(maxMoney(0,1) == -1));
        System.out.println("Should be -1 Invalid Params: " + String.valueOf(maxMoney(1,0) == -1));
        System.out.println("Should be -1 Invalid Params: " + String.valueOf(maxMoney(0,1) == -1));
        System.out.println("Should be 0: " + String.valueOf(maxMoney(1,1) == 0));
        System.out.println("Should be 1: " + String.valueOf(maxMoney(1,2) == 1));
        System.out.println("Should be 0: " + String.valueOf(maxMoney(100,1) == 5049));
        System.out.println("Should be 2: " + String.valueOf(maxMoney(2,1) == 2));
        System.out.println("Should be 5: " + String.valueOf(maxMoney(3,3) == 5));
        System.out.println("Should be 55: " + String.valueOf(maxMoney(10,60) == 55));
        System.out.println("Should be 54: " + String.valueOf(maxMoney(10,55) == 54));
        System.out.println("Should be 55: " + String.valueOf(maxMoney(10,54) == 55));
    }

}