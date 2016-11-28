package ru.agolovin.start;

import org.junit.Test;
import ru.agolovin.models.Task;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test methods for Task class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class TaskTest {

    /**
     * Test for Task class.
     */
    @Test
    public final void whenAddInTaskThenResultIs() {
        Task task = new Task("taskName", "taskDesc");
        String resultName = "taskName";
        String resultDesc = "taskDesc";
        assertThat(task.getName(), is(resultName));
        assertThat(task.getDescription(), is(resultDesc));

    }
}
