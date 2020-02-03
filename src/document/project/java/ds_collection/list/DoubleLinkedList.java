package document.project.java.ds_collection.list;

import document.project.java.ds_collection.DSCollection;

import java.util.*;
import java.util.stream.Collectors;

public class DoubleLinkedList<T> implements DSList<T> {

    private Cell start = null;
    private Cell end = null;
    private int size = 0;

    //constructors

    public DoubleLinkedList (){}

    public DoubleLinkedList (DSCollection<? extends T> elements){
        this();
        addAll(elements);
    }

    public DoubleLinkedList (Collection<? extends T> elements){
        this();
        addAll(elements);
    }

    @SafeVarargs
    public DoubleLinkedList (T... elements){
        this();
        addAll(elements);
    }

    //public operations

    @SuppressWarnings("unchecked")
    public DoubleLinkedList copy (){
        return new DoubleLinkedList(this.toArray().clone());
    }


    //private operations

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
