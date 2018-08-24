package ru.agolovin;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class StoreXML {

    private final File file;

    public StoreXML(File file) {
        this.file = file;
    }

    void save(List<Entry> list) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), this.file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement
    public static class Entry {
        private int field;

        public Entry() {
        }

        Entry(int field) {
            this.field = field;
        }

        public int getField() {
            return this.field;
        }

        public void setField(int field) {
            this.field = field;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry entry = (Entry) o;
            return field == entry.field;
        }

        @Override
        public int hashCode() {
            return Objects.hash(field);
        }
    }

    @XmlRootElement
    public static class Entries {
        List<Entry> entry;

        public Entries() {
        }

        Entries(List<Entry> entry) {
            this.entry = entry;
        }

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entries entries = (Entries) o;
            return Objects.equals(entry, entries.entry);
        }

        @Override
        public int hashCode() {
            return Objects.hash(entry);
        }
    }
}
