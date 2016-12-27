package ru.agolovin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;


/**
 * Delete abuse word in input stream and write it in output stream.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

class DeleteAbuseWordInStream {

    /**
     * Delete abuse word in input stream and write it in output stream.
     *
     * @param in    InputStream
     * @param out   OutputStream
     * @param abuse String array
     */
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try {
            String stream;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while ((stream = reader.readLine()) != null) {
                for (String element : abuse) {
                    if (stream.contains(element)) {
                        stream = stream.replace(element, "");
                    }
                }

                out.write(stream.getBytes());
            }

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

    }
}
