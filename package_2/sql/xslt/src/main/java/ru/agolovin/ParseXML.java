package ru.agolovin;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class ParseXML {

    private int count;
    private SAXParserFactory parserFactory;
    private SAXParser parser;
    private Handler handler;

    ParseXML(File file) {
        this.parserFactory = SAXParserFactory.newInstance();
        try {
            this.parser = this.parserFactory.newSAXParser();
            this.handler = new Handler();
            parser.parse(file, handler);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return count;
    }

    class Handler extends DefaultHandler {
        @Override
        public void startElement(String url, String localName, String name,
                                 Attributes attributes) throws SAXException {
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                String atrName = attributes.getQName(i);
                if (atrName != null && atrName.equals("field")) {
                    count += Integer.parseInt(attributes.getValue(i));
                }
            }
        }
    }
}
