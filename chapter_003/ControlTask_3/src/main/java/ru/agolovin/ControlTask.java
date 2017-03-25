package ru.agolovin;
/**
 * В течении дня в банк заходят люди, у каждого человека есть время захода в банк и время выхода.
 * Всего за день у банка было N посетителей. Банк работает с 8:00 до 20:00.
 * Человек посещает банк только один раз за день.
 * Написать программу, которая определяет периоды времени,.
 * когда в банке было максимальное количество посетителей.
 */

/**
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
    private static final int TIME_INTERVAL = 11;

    /**
     * Массив временных интервалов.
     */
    private int[] a = new int[TIME_INTERVAL];

    /**
     * Случайное число посетителей.
     */
    private int randomNumber;

    /**
     * Максимальное число для случайного числа.
     */
    private int count = MAX_PEOPLE_COUNT + 1;

    /**
     * Получение случайного числа в диапазоне.
     *
     * @param range Диапазон
     * @return Случайное число
     */
    private int getRandomNumberInRange(int range) {
        int result = (int) (Math.random() * range);
        this.randomNumber = result;
        return result;
    }

    /**
     * Базовый метод.
     */
    private void init() {
        for (int i = 0; i < a.length; i++) {
            this.a[i] = getRandomNumberInRange(this.count);
            downRange();
        }
        findMaxPeople();
    }

    /**
     * Уменьшение диапазона случайных числе.
     */
    private void downRange() {
        this.count = this.count - this.randomNumber;
    }

    /**
     * Определение и вывод максимального числа посетителей.
     */
    private void findMaxPeople() {
        final int start = 8;
        final int end = 9;

        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (max == a[i]) {
                System.out.println(
                        String.format(
                                "Больше всего людей было в промежутке от %s до %s", i + start, i + end)
                );
            }
        }

    }
}
