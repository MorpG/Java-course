package ru.agolovin;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreXMLTest {

    private File file = new File("StoreXMLTest.xml");

    @Test
    public void methodTest() {
        List<StoreXML.Entry> methodData = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            methodData.add(new StoreXML.Entry(i));
        }
        StoreXML storeXML = new StoreXML(file);
        storeXML.save(methodData);
        StringBuilder result = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder correctAnswer = new StringBuilder();
        correctAnswer
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<entries>")
                .append("    <entry>")
                .append("        <field>0</field>")
                .append("    </entry>")
                .append("    <entry>")
                .append("        <field>1</field>")
                .append("    </entry>")
                .append("</entries>");
        assertThat(result.toString(), is(correctAnswer.toString()));
    }


    @After
    public void delete() {
        this.file.delete();
    }

}