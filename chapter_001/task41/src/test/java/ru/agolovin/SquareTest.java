package ru.agolovin;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SquareTest {
    @Test
    public void whenAllExistThenResult() {
        Square sq = new Square();
        sq.setParameters(5, 10, 15);
        float result = sq.calculate(5);
        assertThat(result, is(190.0F));
    }

    @Test
    public void whenShowResultThenResultIs() {
        Square sq = new Square();
        sq.setParameters(5, 10, 15);
        sq.calculate(5);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        sq.show(5, 8, 1);
        assertThat(out.toString(), is("190.0\r\n255.0\r\n330.0\r\n415.0\r\n"));
    }
}