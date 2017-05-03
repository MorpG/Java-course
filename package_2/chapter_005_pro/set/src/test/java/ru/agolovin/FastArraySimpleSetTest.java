package ru.agolovin;

import org.junit.Test;

import java.util.Random;


import static java.lang.String.format;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class FastArraySimpleSetTest {

    /**
     * Test work fasSet.
     */
    @Test
    public void thenAddThenGetElement() {
        FastArraySimpleSet<Integer> fastSet = new FastArraySimpleSet<>();
        fastSet.add(22);
        fastSet.add(22);
        fastSet.add(44);
        fastSet.add(44);
        assertThat(fastSet.get(0), is(22));
        assertThat(fastSet.get(1), is(44));
    }

    /**
     * Test adding time.
     */
    @Test
    public void testSetAddTime() {

        final int iterations = 1000000;

        final int bound = 900;

        Random rnd = new Random();

        long startTime = System.currentTimeMillis();
        FastArraySimpleSet<Integer> fastSet = new FastArraySimpleSet<>();
        for (int i = 0; i < iterations; i++) {
            fastSet.add(rnd.nextInt(bound));
        }
        long endFastTime = System.currentTimeMillis();

        System.out.println(format("Fast time is: %s", endFastTime - startTime));

        long startTimeSet = System.currentTimeMillis();
        ArraySimpleSet<Integer> set = new ArraySimpleSet<>(0);
        for (int i = 0; i < iterations; i++) {
            set.add(rnd.nextInt(bound));
        }
        long endSetTime = System.currentTimeMillis();

        System.out.println(format("Set time is: %s", endSetTime - startTimeSet));

    }
}