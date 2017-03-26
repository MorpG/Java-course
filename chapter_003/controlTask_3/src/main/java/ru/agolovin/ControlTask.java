package ru.agolovin;

/**
 * В течении дня в банк заходят люди, у каждого человека есть время захода в банк и время выхода.
 * Всего за день у банка было N посетителей. Банк работает с 8:00 до 20:00.
 * Человек посещает банк только один раз за день.
 * Написать программу, которая определяет периоды времени,.
 * когда в банке было максимальное количество посетителей.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ControlTask {

    /**
     * Количество посетителей.
     */
    private static final int MAX_PEOPLE_COUNT = 100;

    /**
     * Число временных интревалов.
     */
    private static final int TIME_INTERVAL = 12;
    /**
     * Время входа.
     */
    private static final int TIME_ENTRY = 8;
    /**
     * Время выхода.
     */
    private static final int TIME_OUT = 20;
    /**
     * Массив числа поситителей во временных промежутках.
     */
    private int[] amountPersons = new int[TIME_INTERVAL];
    /**
     * Массив посетителей.
     */
    private Person[] persons = new Person[MAX_PEOPLE_COUNT];

    /**
     * Main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        new ControlTask().init();
    }

    /**
     * Инициализация.
     */
    private void init() {
        fillPersons();
        fillAmountPersons();
        int max = findMaxAmount(this.amountPersons);
        printTimeMaxPersonsAmount(this.amountPersons, max);
    }

    /**
     * Заполнение массива посетителей временем входа и выхода.
     */
    private void fillPersons() {
        for (int i = 0; i < this.persons.length; i++) {
            int entryTime = TIME_ENTRY + (int) (Math.random() * ((TIME_OUT - TIME_ENTRY) + 1));
            int outTime = entryTime + (int) (Math.random() * ((TIME_OUT - entryTime) + 1));
            this.persons[i] = new Person(entryTime, outTime);
        }
    }

    /**
     * Подсчет числа посетителей во временных интревалах.
     */
    private void fillAmountPersons() {
        int amount = 0;
        final int startTime = 8;
        int time = startTime;
        for (int i = 0; i < this.amountPersons.length; i++) {
            for (int j = 0; j < this.persons.length; j++) {
                if (time >= this.persons[j].getEntryTime() && time <= this.persons[j].getOutTime()) {
                    amount++;
                }
            }
            this.amountPersons[i] = amount;
            time++;
            amount = 0;
        }
    }

    /**
     * Нахождение максимального числа посетителей.
     *
     * @param array Массив
     * @return максимальное число int
     */
    private int findMaxAmount(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Вывод на экран временных промежутков с максимальным числом.
     *
     * @param array массив int[]
     * @param max   максимальное число int
     */
    private void printTimeMaxPersonsAmount(int[] array, int max) {
        final int time = 8;
        int amountMaxTime;
        int length = array.length;
        int amountMaxTimeInterval = 1;
        System.out.println("Максимальное число посетителей в следующих временых интревалах: ");
        for (int i = 0; i < length; i++) {
            if (array[i] == max) {
                amountMaxTime = i;
                for (int j = i; j < length; j++) {
                    if (array[j] == max) {
                        amountMaxTime = j;
                    } else {
                        break;
                    }
                }
                System.out.println(
                        String.format(
                                "%s: c %s до %s", amountMaxTimeInterval++, time
                                        + i, time + amountMaxTime + 1)
                );
                i = amountMaxTime;
            }

        }
    }
}
