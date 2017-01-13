package ru.agolovin;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test file sort class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class FileSortTest {

    /**
     * Line separator.
     */
    private String separator = System.getProperty("line.separator");

    /**
     * Test sorting method.
     *
     * @throws IOException Exception
     */
    @Test
    public void whenCheckExistFileThenResultTrue() throws IOException {
        FileSort fileSort = new FileSort();
        String income = "bb" + separator
                + "a" + separator + "ddd" + separator + "e";
        File source = new File("test1.txt");
        File distance = new File("distance.txt");

        RandomAccessFile rafSource = new RandomAccessFile(source, "rw");
        rafSource.seek(0);
        fileSort.write(rafSource, income);

        fileSort.sort(source, distance);

        RandomAccessFile rafDistance = new RandomAccessFile(distance, "r");
        assertThat(distance.exists(), is(true));
        assertThat(rafDistance.readLine().equals("a"), is(true));
        assertThat(rafDistance.readLine().equals("e"), is(true));
        assertThat(rafDistance.readLine().equals("bb"), is(true));
        assertThat(rafDistance.readLine().equals("ddd"), is(true));

        rafDistance.close();
        rafSource.close();
        source.delete();

    }
}