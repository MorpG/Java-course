package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaskStringTest {

    String sOne = "2222222222";
    String sTwo = "3333333333";

    @Test
    public void testTaskStringThreadOneRepeat() {
        TaskString task = new TaskString();
        task.start();
        String expect = task.getString();
        String result = sOne + sTwo;
        assertThat(expect, is(result));
    }

    @Test
    public void testTaskStringThreadFourRepeat() {
        int repeat = 4;
        int rep = repeat - 1;
        TaskString task = new TaskString();
        task.setRepeatString(repeat);
        task.start();
        String expect = task.getString();
        StringBuilder result = new StringBuilder(sOne + sTwo);
        for (int i = 0; i < rep; i++) {
            result.append(sOne).append(sTwo);
        }
        assertThat(expect, is(result.toString()));
    }
}