package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class NodeCycleTest {

    /**
     * Test if node has cycle.
     */
    @Test
    public void whenNodeHasCycleThenResultTrue() {
        NodeCycle nc = new NodeCycle();

        NodeCycle.Node<Integer> firstNode = new NodeCycle.Node<>(1);
        NodeCycle.Node<Integer> secondNode = new NodeCycle.Node<>(2);
        NodeCycle.Node<Integer> thirdNode = new NodeCycle.Node<>(3);
        NodeCycle.Node<Integer> fourNode = new NodeCycle.Node<>(4);

        firstNode.setNext(secondNode);
        secondNode.setNext(thirdNode);
        thirdNode.setNext(fourNode);
        fourNode.setNext(firstNode);

        boolean result = nc.hasCycle(firstNode);
        assertThat(result, is(true));
    }

    /**
     * Test if node don have cycle.
     */
    @Test
    public void whenNodeDonHasCycleThenResultTrue() {
        NodeCycle nc = new NodeCycle();

        NodeCycle.Node<Integer> firstNode = new NodeCycle.Node<>(1);
        NodeCycle.Node<Integer> secondNode = new NodeCycle.Node<>(2);
        NodeCycle.Node<Integer> thirdNode = new NodeCycle.Node<>(3);
        NodeCycle.Node<Integer> fourNode = new NodeCycle.Node<>(4);

        firstNode.setNext(secondNode);
        secondNode.setNext(thirdNode);
        thirdNode.setNext(fourNode);
        fourNode.setNext(null);

        boolean result = nc.hasCycle(firstNode);
        assertThat(result, is(false));
    }

}
