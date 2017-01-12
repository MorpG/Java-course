package ru.agolovin;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

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

    @Test
    public void whenCheckExistFileThenResultTrue() throws IOException {
        FileSort fileSort = new FileSort();

        File source = new File("test.txt");
        System.out.println(source.getAbsolutePath());
        System.out.println(source.isFile());
        System.out.println(source.exists());
        File distance = new File("distance.txt");
    //    assertThat(source.exists(), is(true));
        fileSort.sort(source, distance);
    }
}