package document.project.java.ds_collection.list;

import document.project.java.ds_collection.DSCollection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A dynamic data structure, implementation of {@link DSList}
 * where an {@link Cell} have the address of the previous and
 * the next {@link Cell} in the list.
 *
 * @param <T> The element type that will be stored.
 *
 * @author Hilson Alexandre Wojcikiewicz Junior.
 */
public class DoubleLinkedList<T> implements DSList<T> {

    /**
     * The first Cell of the list.
     */
    private Cell start = null;

    /**
     * The last Cell of the list.
     */
    private Cell end = null;

    /**
     * The number of elements in the list.
     */
    private int size = 0;

    //constructors

    /**
     * Default constructor.
     */
    public DoubleLinkedList (){}

    /**
     * A constructor that initializes a new list with the
     * elements of a {@link DSCollection}.
     *
     * @param elements a collection that will be added.
     */
    public DoubleLinkedList (DSCollection<? extends T> elements){
        this();
        addAll(elements);
    }

    /**
     * A constructor that initializes a new list with the
     * elements of a {@link Collection}.
     *
     * @param elements a collection that will be added.
     */
    public DoubleLinkedList (Collection<? extends T> elements){
        this();
        addAll(elements);
    }

    /**
     * A constructor that initializes a new list with the
     * elements of an array or various elements.
     *
     * @param elements a collection that will be added.
     */
    @SafeVarargs
    public DoubleLinkedList (T... elements){
        this();
        addAll(elements);
    }

    //public operations

    /**
     * copy a DoubleLinkedList.
     *
     * @return the copy of the list.
     */
    @SuppressWarnings("unchecked")
    public DoubleLinkedList copy (){
        return new DoubleLinkedList(this.toArray().clone());
    }


    //private operations

    /**
     * @return half of the size number.
     */
    private int halfSize (){
        return size % 2 == 0 ? size/2 : size/2 + 1;
    }

    @SuppressWarnings("unchecked")
    private Cell getCell (int index){
        ListValidate.occupiedIndex(index, size);
        Cell cell;
        if (index < halfSize() ) {
            cell = start;
            for (int i = 0; i < index; i++) {
                cell = cell.next;
            }
        }else {
            cell = end;
            for (int i = size-1; i > index; i--){
                cell = cell.preview;
            }
        }
        return cell;
    }

    @SuppressWarnings("unchecked")
    private void addAfter (Cell cell, T element){
        Cell newCell = new Cell(cell, element, cell.next);
        if (newCell.next == null){
            end = newCell;
        }else {
            Cell nextCell = newCell.next;
            nextCell.preview = newCell;
        }
        cell.next = newCell;
        size++;
    }

    @SuppressWarnings("unchecked")
    private void removeCell (Cell remove){
        Cell before = remove.preview;
        before.next = remove.next;
        Cell after = remove.next;
        after.preview = remove.preview;
        size--;
    }

    private void removeLast(){
        end = end.preview;
        if (end != null){
            end.next = null;
        }
        size --;
    }

    //Implement DSList
    @Override
    @SuppressWarnings("unchecked")
    public void replace (T element, int index){
        ListValidate.occupiedIndex(index, size);
        Cell cell = start;
        for (int i = 1; i < index; i++){
            cell = cell.next;
        }
        cell.element = element;
    }

    @Override
    public T remove (int index){
        ListValidate.occupiedIndex(index, size);
        T element;
        if (index == 0){
            return removeFirst();
        }else  if (index == size-1){
            element = end.element;
            removeLast();
        }else {
            element = getCell(index).element;
            removeCell(getCell(index));
        }
        return element;
    }

    @Override
    public T removeFirst(){
        T element = start.element;
        start = start.next;
        if (start != null) {
            start.preview = null;
        }
        size --;
        return element;
    }

    @Override
    public void remove(T element){
        while (contains(element)){
            remove(indexOf(element));
        }
    }

    @Override
    public void addFirst (T element){
        start = new Cell(element, start);
        if (size == 0) {
            end = start;
        }
        size++;
    }

    @Override
    public void add (T element, int index){
        ListValidate.index(index, size);
        if (index == 0) {
            addFirst(element);
        }else if (index == size){
            addAfter(end, element);
        }else {
            Cell cell = getCell(index - 1);
            addAfter(cell, element);
        }
    }

    @Override
    public T get (int index){
        return getCell(index).element;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Cell cell = start; cell != null; cell = cell.next)
            result[i++] = cell.element;
        return result;
    }

    @Override
    public int size (){
        return size;
    }

    @Override
    public boolean contains (T element) {
        Cell head = start;
        Cell tail = end;
        for (int i = 0; i < halfSize(); i++){
            if(head.element.equals(element) || tail.element.equals(element)) return true;
            head = head.next;
            tail = tail.preview;
        }
        return false;
    }

    @Override
    public void clean() {
        Cell head = start;
        Cell next;
        Cell tail = end;
        Cell preview;
        for (int i = 0; i < halfSize(); i++){
            next = head.next;
            head.next = null;
            head.preview = null;
            head = next;
            preview = tail.preview;
            tail.preview = null;
            tail.next = null;
            tail = preview;
        }
        start = end = null;
    }

    public List toList(){
        List list = new ArrayList<>();
        for (int i = 0; i < size; i++){
            list.add(get(i));
        }
        return list;
    }

    @Override
    public int indexOf(T element) {
        if (contains(element)) {
            for (int i = 0; i <= size; i++) {
                if (get(i).equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        if (contains(element)) {
            for (int i = size - 1; i >= 0; i++) {
                if (get(i).equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<>() {
            private Cell get = start;
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

    //Override Object

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleLinkedList<?> that = (DoubleLinkedList<?>) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, toString());
    }

    @Override
    public String toString() {
        return "[" + stream().map(T::toString).collect(Collectors.joining(", ")) + "]" ;
    }

    //private class Cell

    @SuppressWarnings("Type parameter")
    private class Cell {

        T element;
        Cell next;
        Cell preview;

        Cell(Cell preview, T element, Cell next) {
            this.element = element;
            this.next = next;
            this.preview = preview;
        }

        Cell(T element, Cell next) {
            this.element = element;
            this.next = next;
            this.preview = null;
        }
    }

}
