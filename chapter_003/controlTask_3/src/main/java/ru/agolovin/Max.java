package ru.agolovin;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Max {

    /**
     * Max amound visitors.
     */
    private int max;

    /**
     * Start time with max amount visitors.
     */
    private long in;

    /**
     * End time wih max amount visitors.
     */
    private long out;

    /**
     * Get max amount visitors.
     * @return max int.
     */
    public int getMax() {
        return max;
    }

    /**
     * Get start time with max amount visitors.
     * @return start time long.
     */
    public long getIn() {
        return in;
    }

    /**
     * Get end time with max amount visitors.
     * @return end time with max amount visitors. long
     */
    public long getOut() {
        return out;
    }

    /**
     * Count visitors.
     * @param visitors List.
     * @return count Max.
     */
    Max count(List<Visitor> visitors) {
        Max maxVisitor = new Max();

        Set<Long> times = fillTime(visitors);
        int count = 0;
        int max = 0;
        long timeIn = 0;
        long timeOut = 0;
        for (Long time : times) {
            for (Visitor visitor : visitors) {
                if (time >= visitor.getIn() && time <= visitor.getOut()) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                timeIn = time;
                timeOut = time;
            }
            if (count == max) {
                timeOut = time;
            }
            count = 0;
        }

        this.in = timeIn;
        this.out = timeOut;
        this.max = max;

        return maxVisitor;
    }

    /**
     * Fill time set.
     * @param list Visitors
     * @return fillTime TreeSet.
     */
    private Set<Long> fillTime(List<Visitor> list) {
        Set<Long> timeSet = new TreeSet<>();
        for (Visitor element : list) {
            timeSet.add(element.getIn());
            timeSet.add(element.getOut());
        }
        return timeSet;
    }

}
