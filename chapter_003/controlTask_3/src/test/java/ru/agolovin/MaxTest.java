package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class MaxTest {

    /**
     * Test Max max.
     */
    @Test
    public void whenVisitorsMaxThenResultIs() {
        final long f0 = 0;
        final long f1 = 1;
        final long f2 = 2;
        final long f3 = 3;
        final long f4 = 4;
        final long f5 = 5;
        final long f7 = 7;

        Max max = new Max();
        Visitor visitor0 = new Visitor(f2, f4);
        Visitor visitor1 = new Visitor(f3, f7);
        Visitor visitor2 = new Visitor(f0, f4);
        Visitor visitor3 = new Visitor(f1, f5);
        Visitor visitor4 = new Visitor(f0, f2);
        Visitor visitor5 = new Visitor(f1, f7);

        List<Visitor> visitors = new ArrayList<>();
        visitors.add(visitor0);
        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);
        visitors.add(visitor4);
        visitors.add(visitor5);

        max.count(visitors);

        int maxVisitor = max.getMax();
        long maxIn = max.getIn();
        long maxOut = max.getOut();

        final int answerMaxVisitor = 5;
        final long answerMaxIn = 2;
        final long answerMaxOut = 4;

        assertThat(maxVisitor, is(answerMaxVisitor));
        assertThat(maxIn, is(answerMaxIn));
        assertThat(maxOut, is(answerMaxOut));
    }
}