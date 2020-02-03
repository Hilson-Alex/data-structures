package document.project.java.ds_collection.stack;

import document.project.java.ds_collection.DSCollection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LinkedStack <T> implements DSCollection <T> {

    private Cell top;
    private int size = 0;

    public LinkedStack (){}

    public LinkedStack (DSCollection<? extends T> elements){
        this();
        addAll(elements);
    }

    public LinkedStack (Collection<? extends T> elements){
        this();
        addAll(elements);
    }

    public LinkedStack (T... elements){
        this();
        addAll(elements);
    }

    //Implements ds_collection

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clean() {
        top = null;
    }

    @Override
    public void add(T element) {
        top = new Cell(element, top);
        size++;
    }

    @Override
    public boolean contains(T element) {
        return stream().anyMatch(o -> o.equals(element));
    }

    @Override
    public int indexOf(T element) {
        if (contains(element)) {
            Cell cell = top;
            for (int i = 0; i < size; i++) {
                if (cell.element.equals(element)) {
                    return i;
                }
                cell = cell.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        Cell cell = top;
        int index = -1;
        if (contains(element)) {
            for (int i = 0; i < size; i++) {
                if (cell.element.equals(element)) {
                    index = i;
                }
                cell = cell.next;
            }
        }
        return index;
    }

    @Override
    public T remove() {
        T element = top.element;
        top = top.next;
        return element;
    }

    @Override
    public T get(int index) {
        if (index > 0 && index < size){
            Cell cell = top;
            for (int i = 0; i < index; i++) {
                cell = cell.next;
            }
            return cell.element;
        }
        return null;
    }

    @Override
    public Object[] toArray() {
        return stream().toArray();
    }

    @Override
    public List<T> toList() {
        return stream().collect(Collectors.toList());
    }

    @Override
    public Iterator iterator() {
        return new Iterator<>() {
            private Cell get = top;
            @Override
            public boolean hasNext() {
                return get != null;
            }
            @Override
            public T next() {
                T element = get.element;
                get = get.next;
                return element;
            }
        };
    }

    //private class Cell

    private class Cell {

        T element;
        Cell next;

        Cell(T element, Cell next) {
            this.element = element;
            this.next = next;
        }
    }
}
