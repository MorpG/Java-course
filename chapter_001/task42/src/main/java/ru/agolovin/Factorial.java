package ru.agolovin;

import java.math.BigInteger;

public class Factorial {
    public BigInteger factorial(int t) {
        BigInteger result = BigInteger.valueOf(1);
        if (t < 0)
            result = result.valueOf(0);
        else if (t > 0)
            for (int i = 1; i <= t; i++)
                result = result.multiply(BigInteger.valueOf(i));
        return result;

    }
}