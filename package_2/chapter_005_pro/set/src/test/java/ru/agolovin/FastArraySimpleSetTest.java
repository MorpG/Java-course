package ru.agolovin;

import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static java.lang.String.format;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class FastArraySimpleSetTest {

    @Test
    public void testSetAddTime() {

        final int iterations = 10;

        Random rnd = new Random();

        long startTime = System.currentTimeMillis();
        FastArraySimpleSet<Integer> fastSet = new FastArraySimpleSet<>();
        for (int i = 0; i < iterations; i++) {
            fastSet.add(rnd.nextInt(4));
        }
        long endFastTime = System.currentTimeMillis();
        Iterator<Integer> intF = fastSet.iterator();
        while (intF.hasNext()) {
            System.out.println(intF.next());
        }
        System.out.println(format("Fast time is: %s", endFastTime - startTime));

        long startTimeSet = System.currentTimeMillis();
        ArraySimpleSet<Integer> set = new ArraySimpleSet<>(1);
        for (int i = 0; i < iterations; i++) {
            set.add(rnd.nextInt(3));
        }
        long endSetTime = System.currentTimeMillis();
        Iterator<Integer> intS = set.iterator();
        while (intS.hasNext())
            System.out.println(intS.next());
        System.out.println(format("Set time is: %s", endSetTime - startTimeSet));

    }


}