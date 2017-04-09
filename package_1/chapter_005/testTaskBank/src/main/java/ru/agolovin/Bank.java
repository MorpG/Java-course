package ru.agolovin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализовать коллекцию Map для банка.
 * Необходимо создать класс User с полями name, passport.
 * Необходимо создать класс Account с полями value (кол-во денег), requisites (реквизиты счёта)
 * - это банковский счёт.
 * Реализовать коллекцию Map <User, List<Account>>, обозначающую что у каждого пользователя может
 * быть список банковских счетов.
 * Необходимо реализовать возможность перечислять деньги, как с одного счёта User на другой счёт
 * того же User, так и на счёт другого User.
 * В программе должны быть методы:
 * public void addUser(User user) {} - добавление пользователя.
 * public void deleteUser(User user) {} - удаление пользователя.
 * public void addAccountToUser(User user, Account account) {} - добавить счёт пользователю.
 * public void deleteAccountFromUser(User user, Account account) {} - удалить один счёт пользователя.
 * public List<Accounts> getUserAccounts (User user) {} - получить список счетов для пользователя.
 * public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount)
 * - метод для перечисления денег с одного счёта на другой счёт:
 * если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Bank {

    /**
     * Base map.
     */
    private HashMap<User, List<Account>> map = new HashMap<>();

    /**
     * Add user.
     *
     * @param user User
     */
    public void addUser(User user) {
        this.map.put(user, new ArrayList<>());
    }

    /**
     * delete user.
     *
     * @param user User
     */
    public void deleteUser(User user) {
        if (this.map.containsKey(user)) {
            this.map.remove(user);
        } else {
            System.out.println("User not found");
        }

    }

    /**
     * get all.
     *
     * @return map Map<User, List<account>>
     */
    public Map<User, List<Account>> getAll() {
        return this.map;
    }

    /**
     * Add account to user.
     *
     * @param user    User.
     * @param account Account
     */
    public void addAccountToUser(User user, Account account) {
        if (this.map.containsKey(user)) {
            this.map.get(user).add(account);
        } else {
            System.out.println("User not found");
        }
    }

    /**
     * Delete account From user.
     *
     * @param user    User.
     * @param account Account
     */
    public void deleteAccountFromUser(User user, Account account) {
        if (this.map.containsKey(user)) {
            if (this.map.get(user).contains(account)) {
                this.map.get(user).remove(account);
            } else {
                System.out.println("Account not found");
            }
        } else {
            System.out.println("User not found");
        }
    }

    /**
     * get User accounts.
     *
     * @param user User.
     * @return List user accounts
     */
    public List<Account> getUserAccounts(User user) {
        List list = new ArrayList();
        if (this.map.containsKey(user)) {
            list = this.map.get(user);
        } else {
            System.out.println("User not found");
        }
        return list;
    }

    /**
     * transfer money from users accounts.
     *
     * @param srcUser    source User
     * @param srcAccount Account src User
     * @param dstUser    distance User
     * @param dstAccount distance user account
     * @param amount     double
     * @return transferring result
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;
        if (this.map.containsKey(srcUser) && this.map.containsKey(dstUser)) {
            if (this.map.get(srcUser).contains(srcAccount)
                    && this.map.get(dstUser).contains(dstAccount)) {
                Account src = getAccountFromUser(srcUser, srcAccount);
                Account dst = getAccountFromUser(dstUser, dstAccount);
                double srcValue = src.getValue();
                double dstValue = dst.getValue();
                if (srcValue >= amount) {
                    src.setValue(srcValue - amount);
                    dst.setValue(dstValue + amount);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * get account from user accounts.
     *
     * @param user     User
     * @param incoming Account
     * @return Account.
     */
    private Account getAccountFromUser(User user, Account incoming) {
        Account result = null;
        for (Account element : this.map.get(user)) {
            if (element.equals(incoming)) {
                result = element;
            }
        }
        return result;
    }
}
