package ru.agolovin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ParallelSearch {

    private List<String> result = new CopyOnWriteArrayList<>();

    private List<String> listFiles = new CopyOnWriteArrayList<>();

    public void parallelSearch(String root, String text, List<String> ext) {
        File[] files = new File(root).listFiles();

    }

    private void searchInDirectories(File file) {
        for (final File element : file.listFiles()) {
            if (element.isDirectory()) {
                searchInDirectories(element);
            } else {
                listFiles.add(element.getAbsolutePath());
            }
        }
    }

    private void searchTextInFile(final String text, final List<String> fileList) {
        for (String element : fileList) {
            if (element != null) {
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get(element));
                    String str = new String(bytes);
                    if (str.contains(text)) {
                        this.result.add(element);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
