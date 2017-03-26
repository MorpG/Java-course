package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class PersonTest {

    /**
     * Entry Time.
     */
    private static final int ENTRY_TIME = 8;

    /**
     * Out Time.
     */
    private static final int OUT_TIME = 20;

    /**
     * Person.
     */
    private Person person;

    /**
     * Create person to test.
     *
     * @param entryTime int
     * @param outTime   int
     */
    private void createPersonToTest(int entryTime, int outTime) {
        this.person = new Person(entryTime, outTime);

    }

    /**
     * Test get entry time.
     *
     * @throws Exception exception
     */
    @Test
    public void whenGetEntryTimePersonThenResultIs() throws Exception {
        createPersonToTest(ENTRY_TIME, OUT_TIME);
        int result = this.person.getEntryTime();
        assertThat(result, is(ENTRY_TIME));
    }

    /**
     * Test get out time.
     *
     * @throws Exception exception
     */
    @Test
    public void whenGetOutTimePersonThenResultIs() throws Exception {
        createPersonToTest(ENTRY_TIME, OUT_TIME);
        int result = this.person.getOutTime();
        assertThat(result, is(OUT_TIME));
    }

}