package ru.agolovin;

import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FactorialTest {
    @Test
    public void whenLessZeroThenResultIs() {
        Factorial fc = new Factorial();
        BigInteger result = fc.factorial(-5);
        assertThat(result, is(BigInteger.valueOf(0)));
    }

    @Test
    public void whenEqualsZeroThenResultIs() {
        Factorial fc = new Factorial();
        BigInteger result = fc.factorial(0);
        assertThat(result, is(BigInteger.valueOf(1)));
    }

    @Test
    public void whenBiggerZeroThenResultIs() {
        Factorial fc = new Factorial();
        BigInteger result = fc.factorial(5);
        assertThat(result, is(BigInteger.valueOf(120)));
    }

}