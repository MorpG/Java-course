package ru.agolovin;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConvertXSQTTest {
    private File file = new File("test.xml");

    private File dest = new File("destTest.xml");

    @Test
    public void methodTest() {
        File scheme = new File(getClass()
                .getClassLoader().getResource("scheme.xsl").getFile());
        List<StoreXML.Entry> methodData = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            methodData.add(new StoreXML.Entry(i));
        }
        StoreXML storeXML = new StoreXML(file);
        storeXML.save(methodData);

        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(this.file, this.dest, scheme);

        StringBuilder result = new StringBuilder();
        try (Scanner scan = new Scanner(this.dest)) {
            while (scan.hasNext()) {
                result.append(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries>"
                + "    <entry field=\"0\"/>"
                + "    <entry field=\"1\"/>"
                + "</entries>";
        assertThat(result.toString(), is(expect));
    }

    @After
    public void clean() {
        this.dest.delete();
        this.file.delete();
    }
}