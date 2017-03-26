package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
class Person {

    /**
     * Время входа.
     */
    private int entryTime;

    /**
     * Время выхода.
     */
    private int outTime;

    /**
     * Конструктор класса.
     *
     * @param entryTime время int
     * @param outTime   время выхода int
     */
    Person(int entryTime, int outTime) {
        this.entryTime = entryTime;
        this.outTime = outTime;
    }

    /**
     * Получение времени входа.
     *
     * @return время входа int
     */
    int getEntryTime() {
        return this.entryTime;
    }

    /**
     * Получение времени выхода.
     *
     * @return время выхода  int
     */
    int getOutTime() {
        return this.outTime;
    }
}
