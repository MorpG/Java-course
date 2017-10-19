package ru.agolovin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 * <p>
 * Нужно осуществлять обход файловой системы и поиск заданного текста в файловой системе.
 * public class ParallerSearch(String root, String text, List<String> exts) {
 * }
 * ,где
 * root - путь до папки откуда надо осуществлять поиск.
 * text - заданных текст.
 * exts - расширения файлов в которых нужно делать поиск.
 * <p>
 * Приложения должно искать тесты в файле и сохранять адрес файла.
 * <p>
 * List<String> result(); - возвращает список всех файлов.
 * <p>
 * Логика приложения.
 * <p>
 * 1. Запустить код.
 * 2. Внутри запустить несколько потоков. Объяснить для чего нужно делать потоки.
 * 3. Долждатся завершения поиска.
 * 4. Вывести на консоль результат.
 */

public class ParallelSearch {

    /**
     * Result list.
     */
    private List<String> result = new CopyOnWriteArrayList<>();

    /**
     * Temp file list.
     */
    private List<String> listFiles = new ArrayList<>();

    /**
     * Main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        ParallelSearch search = new ParallelSearch();
        String root = "D:\\Project\\Java-course\\package_2\\multithreading";
        String text = "check";
        List<String> ext = new ArrayList<>();
        ext.add(".txt");
        search.parallelSearch(root, text, ext);
        search.showResult();
    }

    /**
     * @param root String Start directory
     * @param text String Search text
     * @param ext  List<String> extensions to find
     */
    private void parallelSearch(String root, String text, List<String> ext) {
        searchInDirectories(root, ext);

        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < listFiles.size() / 2; i++) {
                if (searchTextInFile(text, listFiles.get(i))) {
                    result.add(listFiles.get(i));
                }
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = listFiles.size() / 2; i < listFiles.size(); i++) {
                if (searchTextInFile(text, listFiles.get(i))) {
                    result.add(listFiles.get(i));
                }
            }
        });

        threadOne.start();
        threadTwo.start();
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursive find directories with valid file
     *
     * @param directory String
     * @param ext       List<String>
     */
    private void searchInDirectories(String directory, List<String> ext) {
        File file = new File(directory);
        for (File element : file.listFiles()) {
            if (element.isDirectory()) {
                searchInDirectories(element.getAbsolutePath(), ext);
            } else {
                for (String name : ext) {
                    if (element.getName().endsWith(name)) {
                        this.listFiles.add(element.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * Search text in file.
     *
     * @param text String
     * @param path String file path
     * @return boolean result
     */
    private boolean searchTextInFile(final String text, final String path) {
        File file = new File(path);
        boolean flag = false;
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            try {
                while (scanner.hasNextLine()) {
                    if (scanner.nextLine().contains(text)) {
                        flag = true;
                        break;
                    }
                }
            } finally {
                scanner.close();
            }
        }
        return flag;
    }

    /**
     * Show work result.
     */
    private void showResult() {
        for (String str : this.result) {
            System.out.println(str);
        }
    }
}
