import java.util.Scanner;

/**
 * Etude 6 - COSC326
 * @author Dougal Colquhoun & George Churton
 * Counting it up
 * This program takes input of two integers from command line or file piped
 * then calculates n choose k and prints the result.
 */
public class count{
    private static Scanner scan; //scanner object to get input from command line or file
    private static long n, k;
    private static String nString, kString;

    /**
     * 
     * @param n the number of rows
     * @param k the number of columns we need
     * @return the answer for n choose k
     */
    public static long triangle(long n, long k){
        if(k == n){
            return 1;
        }else if(n - k == 1 || k == 1){
            return n;
        }else if(k > n/2){
            k = 0 + (n-k);
        }else if(k == 2){
            return triangularNumber(n); // method for triangular numbers, always the 2nd element
        }
        long[] result;
        long[] previous = new long[(int)k+1];
        for(int i = 0; i <= n; i++){ //number of rows
            long[] nextRow = new long[(int)k+1]; 
            nextRow[0] = 1;
            for(int j = 1; j <= k; j++){ //number of columns
                nextRow[j] = previous[j-1] + previous[j];
            }
            previous = nextRow.clone();
        }
        result = previous;
        return result[(int)k];        
    }

    /**
     * Calculates the nth triangular number, 
     * Speeds up the program if n==2 as will only go through a single for-loop rather than a nested for-loop
     * Speed is increased if n is greater than 1 billion (1e9), as result for 1e9, 2e9, 3e9, 4e9 are already stored
     *  so only calculate from that base rather than zero, reducing complexity from O(n) to max O(b) where b = 1 billion
     * 9,223,372,036,854,775,807 -- max number in 64 bit longs
     * @param n nth number to calculate
     * @return nth triangular number
     */
    public static long triangularNumber(long n){
        long result = 0; 
       
        long b1 = 1000000000L; //1 billion
        long b2 = 2000000000L; //2 billion
        long b3 = 3000000000L; //3 billion
        long b4 = 4000000000L; //4 billion
        if(n >= b1 && n < b2){
            result = 499999999500000000L;
            for(long i = b1; i < n; i++){
                result += i;
            }
            return result;
        }else if(n >= b2 && n < b3){
            result = 1999999999000000000L;
            for(long i = b2; i < n; i++){
                result += i;
            }
            return result;
        }else if(n >= b3 && n < b4){
            result = 4499999998500000000L;
            for(long i = b3; i < n; i++){
                result += i;
            }
            return result;
        }else if(n >= b4){
            result = 7999999998000000000L;
            for(long i = b4; i < n; i++){
                result += i;
            }
            return result;
        }else{
            for(long i = 1; i < n; i++){
                result += i;
            }
        return result;
        }
    }

    /**
     * Main method where we get input from command line 
     * Checks happen to make sure n and k are appropriate numbers
     * @param args unused arguments to receive from command line  
     */
    public static void main(String[] args){
        System.err.println("Please enter two integers for n and k or \"exit\" to quit program");
        System.err.println("_______________________________________________________________");
        System.err.println("");

        scan = new Scanner(System.in); //creating scanner object
        while(scan.hasNext()){
            nString = scan.next(); //getting first input 
            if(nString.toLowerCase().equals("exit")){ //checking if input is exit
                return;
            }
            kString = scan.next(); //getting second input
            if(kString.toLowerCase().equals("exit")){ //checking if input is exit
                return;
            }
            try{ //making input long
                n = Long.parseLong(nString);
                k = Long.parseLong(kString);
                if(n < k){ //if k is larger
                    String input = nString + " " + kString;
                    System.out.println("INVALID: (" + input + ") n must be larger than k");
                }else if(n < 0 || k < 0){ //if n or k are not positive
                    String input = nString + " " + kString;
                    System.out.println("INVALID: (" + input + ") Please enter positive integers");
                }else{ //if all good then calculate and print
                    long answer = triangle(n, k);
                    System.out.println("Answer: " + answer);
                }
            }catch(Exception e){ //if error is thrown in parseLong()
                String input = nString + " " + kString;
                System.out.println("INVALID: (" + input + ") Please only input integers");
            }
        }
        scan.close();
    }
}