package ru.agolovin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ParallelSearch {

    private List<String> result = new CopyOnWriteArrayList<>();

    private List<String> listFiles = new CopyOnWriteArrayList<>();

    private List<String> exts;

    public void parallelSearch(String root, String text, List<String> ext) {
        File[] files = new File(root).listFiles();

    }

    private void searchInDirectories(String directory, List<String> exts) {
        File file = new File(directory);
        for (File element : file.listFiles()) {
            if (element.isDirectory()) {
                searchInDirectories(element.getAbsolutePath(), exts);
            } else {
                for (String name : exts) {
                    if (element.getName().endsWith(name)) {
                        listFiles.add(element.getAbsolutePath());
                    }
                }
            }
        }
    }

    private void searchTextInFile(final String text, final List<String> fileList) {
        for (String element : fileList) {
            File file = new File(element);
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    if (scanner.nextLine().equals(text)) {
                        result.add(element);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
