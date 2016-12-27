package ru.agolovin;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class for DeleteAbuseWordInStream class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class DeleteAbuseWordInStreamTest {

    /**
     * Test delete word in inputStream.
     */
    @Test
    public final void whenDeleteAbuseInStreamThenReturn() {
        DeleteAbuseWordInStream dlt = new DeleteAbuseWordInStream();
        String word = "Test string";
        InputStream in = new ByteArrayInputStream(word.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        String[] abuse = {"string"};
        dlt.dropAbuses(in, out, abuse);
        String result = "Test ";
        assertThat(out.toString(), is(result));
    }

}