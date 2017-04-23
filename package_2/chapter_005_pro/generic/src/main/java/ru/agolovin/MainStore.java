package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 *
 * @param <T> extends Base
 */
public class MainStore<T extends Base> implements Store<Base> {

    /**
     * Container.
     */
    private SimpleArray<Base> simpleArray;

    /**
     * Constructor.
     *
     * @param size int.
     */
    MainStore(int size) {
        this.simpleArray = new SimpleArray<>(size);
    }

    @Override
    public void add(Base item) {
        this.simpleArray.add(item);
    }

    @Override
    public void delete(String id) {
        this.simpleArray.delete(getIndexById(id));
    }

    @Override
    public void update(String id, Base newItem) {
        this.simpleArray.update(getIndexById(id), newItem);

    }

    @Override
    public Base get(String id) {
        return this.simpleArray.get(getIndexById(id));
    }

    /**
     * Get index in container by identification.
     *
     * @param id String
     * @return index int
     */
    private int getIndexById(String id) {
        Base element;
        int i = 0;
        int result = -1;
        try {
            element = this.simpleArray.get(i);
            while (element != null) {
                if (id.equals(element.getId())) {
                    result = i;
                    break;
                }
                element = this.simpleArray.get(++i);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return result;
    }


}
