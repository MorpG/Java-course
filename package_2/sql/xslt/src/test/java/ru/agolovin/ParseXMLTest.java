package ru.agolovin;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParseXMLTest {

    private File file = new File("ParseXMLTest.xml");
    private File dest = new File("destTestParseXML.xml");

    @Test
    public void methodTest() {
        File scheme = new File(getClass()
                .getClassLoader().getResource("scheme.xsl").getFile());
        List<StoreXML.Entry> methodData = new ArrayList<>();

        int testLength = 10;

        for (int i = 0; i < testLength; i++) {
            methodData.add(new StoreXML.Entry(i));
        }
        StoreXML storeXML = new StoreXML(file);
        storeXML.save(methodData);

        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(this.file, this.dest, scheme);

        ParseXML parseXML = new ParseXML(this.dest);
        int result = parseXML.getCount();
        int answer = answerNumber(testLength);
        assertThat(result, is(answer));

    }

    private int answerNumber(int testLength) {
        int res = 0;
        for (int i = 0; i < testLength; i++) {
            res += i;
        }
        return res;
    }
}
