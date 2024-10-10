package org.example;

public class IsPrimeNum {

    public static boolean isPrime(int n) {

        if (n <= 1) {
            //return false;
            throw new IllegalArgumentException("n > 1");
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
