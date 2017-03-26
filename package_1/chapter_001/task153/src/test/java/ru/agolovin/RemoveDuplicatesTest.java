package ru.agolovin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RemoveDuplicatesTest {
    @Test
    public void whenArrContainsDuplicatesThanResultIs(){
        RemoveDuplicates rmv = new RemoveDuplicates();
        String[] orig = {"a", "b", "c", "b", "a"};
        String[] result = rmv.removeDuplicates(orig);
        String[] answer = {"c", "b", "a"};
        assertThat(result, is(answer));
    }

    @Test
    public void whenArrNotContainsDuplicatesThanResultIs(){
        RemoveDuplicates rmv = new RemoveDuplicates();
        String[] orig = {"a", "b", "c"};
        String[] result = rmv.removeDuplicates(orig);
        String[] answer = {"a", "b", "c"};
        assertThat(result, is(answer));
    }
}
