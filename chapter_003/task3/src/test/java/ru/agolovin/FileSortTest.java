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

    private String separator = System.getProperty("line.separator");
    @Test
    public void whenCheckExistFileThenResultTrue() throws IOException {
        FileSort fileSort = new FileSort();
        File source = new File("test.txt");
        System.out.println(source.length());
        File distance = new File("distance.txt");
        assertThat(source.exists(), is(true));
        fileSort.sort(source, distance);
        RandomAccessFile raf = new RandomAccessFile(distance, "rw");
        raf.seek(distance.length());
        String str = raf.readLine();
        System.out.println(str + " : lenght " + str.length());
    }
}