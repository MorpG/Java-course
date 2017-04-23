package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class RoleStoreTest {

    /**
     * Test add role to store.
     */
    @Test
    public void whenAddRoleToStoreAndGetIt() {
        RoleStore roleStore = new RoleStore(2);

        String idRole1 = "idOne";
        String idRole2 = "idTwo";

        Role role1 = new Role(idRole1);
        Role role2 = new Role(idRole2);

        roleStore.add(role1);
        roleStore.add(role2);

        assertThat(roleStore.get(idRole1), is(role1));
        assertThat(roleStore.get(idRole2), is(role2));
    }

    /**
     * Test update role.
     */
    @Test
    public void whenUpdateRoleThenItUpdate() {
        RoleStore roleStore = new RoleStore(3);
        String idRole1 = "idOne";
        String idRole2 = "idTwo";
        Role role1 = new Role(idRole1);
        Role role2 = new Role(idRole2);
        roleStore.add(role1);
        roleStore.update(idRole1, role2);
        assertThat(roleStore.get(idRole2), is(role2));

    }

    /**
     * Test delete role2.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenDeleteRole2FromStoreThen() {
        RoleStore roleStore = new RoleStore(3);
        String idRole1 = "idOne";
        String idRole2 = "idTwo";
        Role role1 = new Role(idRole1);
        Role role2 = new Role(idRole2);
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.delete(idRole2);
        assertEquals(roleStore.get(idRole2), null);
    }

}