package ru.agolovin;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class BankTest {

    /**
     * Test add user.
     */
    @Test
    public void whenAddTwoUserToBankThenMapContainsIt() {
        User user1 = new User("User1", 0);
        User user2 = new User("User2", 0);
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);
        Map<User, List<Account>> result = bank.getAll();
        Map<User, List<Account>> answer = new HashMap<>();
        answer.put(user1, new ArrayList<>());
        answer.put(user2, new ArrayList<>());
        assertThat(result, is(answer));
    }

    /**
     * Test delete user.
     */
    @Test
    public void whenAddTwoUserAndDelOneThenMapContainsIt() {
        User user1 = new User("User1", 0);
        User user2 = new User("User2", 1);
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);
        bank.deleteUser(user1);
        Map<User, List<Account>> result = bank.getAll();
        Map<User, List<Account>> answer = new HashMap<>();
        answer.put(user1, new ArrayList<>());
        answer.put(user2, new ArrayList<>());
        answer.remove(user1);
        assertThat(result, is(answer));
    }

    /**
     * Test delete user if user not exist.
     */
    @Test
    public void whenDeleteNotExistingUserThenPrint() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        User user1 = new User("User1", 0);
        User user2 = new User("User2", 1);
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.deleteUser(user2);
        boolean flag = out.toString().contains("User not found");
        assertThat(flag, is(true));
    }

    /**
     * Test add account to user.
     */
    @Test
    public void whenAddAccountToUserThenAccountAmount() {
        User user1 = new User("User1", 0);
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addAccountToUser(user1, new Account(1, "12345"));
        Map<User, List<Account>> result = bank.getAll();
        Map<User, List<Account>> answer = new HashMap<>();
        answer.put(user1, new ArrayList<>());
        answer.get(user1).add(new Account(1, "12345"));

        assertThat(result, is(answer));
    }

    /**
     * Test add account to not existing user.
     */
    @Test
    public void whenAddAccountsToNotExistingUserThenResultIs() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        User user1 = new User("User1", 0);
        User user2 = new User("User2", 1);
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addAccountToUser(user2, new Account(1, "12345"));
        boolean flag = out.toString().contains("User not found");
        assertThat(flag, is(true));
    }

    /**
     * Test delete account from user.
     */
    @Test
    public void whenDeleteAccountFromUserThenAmount1() {
        User user1 = new User("User1", 0);
        Account account1 = new Account(0, "12345");
        Account account2 = new Account(1, "123");
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addAccountToUser(user1, account1);
        bank.addAccountToUser(user1, account2);
        bank.deleteAccountFromUser(user1, account2);
        Map<User, List<Account>> result = bank.getAll();
        Map<User, List<Account>> answer = new HashMap<>();
        answer.put(user1, new ArrayList<>());
        answer.get(user1).add(account1);
        answer.get(user1).add(account2);
        answer.get(user1).remove(account2);
        assertThat(result, is(answer));
    }

    /**
     * Test delete accounts to not existing user.
     * And delete not existing account from user.
     */
    @Test
    public void whenDeleteAccountsFromNotExistingUserThenResultIs() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        User user1 = new User("User1", 0);
        User user2 = new User("User2", 1);
        Account account1 = new Account(0, "12345");
        Account account2 = new Account(1, "123");
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addAccountToUser(user1, account1);
        bank.deleteAccountFromUser(user2, account2);
        bank.deleteAccountFromUser(user1, account2);
        boolean flagUser = out.toString().contains("User not found");
        boolean flagAccount = out.toString().contains("Account not found");
        assertThat(flagUser, is(true));
        assertThat(flagAccount, is(true));
    }

    /**
     * Test get all user accounts.
     */
    @Test
    public void whenUserHave3AccountsThenGetItAll() {
        User user1 = new User("User1", 0);
        Account account1 = new Account(0, "12345");
        Account account2 = new Account(1, "123");
        Account account3 = new Account(1, "1234");
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addAccountToUser(user1, account1);
        bank.addAccountToUser(user1, account2);
        bank.addAccountToUser(user1, account3);
        List<Account> result = bank.getUserAccounts(user1);
        List<Account> answer = new ArrayList<>();
        answer.add(account1);
        answer.add(account2);
        answer.add(account3);
        assertThat(result, is(answer));
    }

    /**
     * Test transferring from users account.
     */
    @Test
    public void whenTransferValueFromUsersThenIt() {
        final double amount = 5.0;
        User user1 = new User("User1", 0);
        Account account1 = new Account(amount, "125");
        User user2 = new User("User2", 1);
        Account account2 = new Account(0, "12345");
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser(user1, account1);
        bank.addAccountToUser(user2, account2);
        boolean flag = bank.transferMoney(user1, account1, user2, account2, amount);
        boolean flagFalse = bank.transferMoney(user1, account1, user2, account2, amount + 1);
        Map<User, List<Account>> result = bank.getAll();
        assertThat(flagFalse, is(false));
        assertThat(flag, is(true));
        assertThat(0.0, is(result.get(user1).get(0).getValue()));
        assertThat(amount, is(result.get(user2).get(0).getValue()));
    }
}
