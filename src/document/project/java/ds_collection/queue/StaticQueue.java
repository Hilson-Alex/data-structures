package document.project.java.ds_collection.queue;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class StaticQueue <T> {

    private Object[] line;
    private int size = 0;
    private int start;
    private int end;

    //constructor

    public StaticQueue (int maxCapacity){
        line = new Object[maxCapacity];
        start = maxCapacity - 1;
        end = maxCapacity - 1;
    }

    public StaticQueue (int maxCapacity, Collection<? extends  T> list){
        this(maxCapacity);
        addAll(list);
    }

    public StaticQueue (int maxCapacity, T... array){
        this(maxCapacity);
        addAll(array);
    }

    public StaticQueue (int maxCapacity, StaticQueue<? extends T> queue){
        this(maxCapacity);
        addAll(queue);
    }

    //public

    public boolean isEmpty (){
        return size == 0;
    }

    public boolean isFull (){
        return size >= line.length;
    }

    public int size (){
        return size;
    }

    public T get (int index){
        validateIndex(index);
        index = physicalIndex(index);
        return (T) line[index];
    }

    public int getIndex (T element){
        for (int i = 0; i < size; i++){
            if(((T) line[physicalIndex(i)]).equals(element)){
                return i;
            }
        }
        return -1;
    }

    public T remove (){
        validateUnderflow();
        start = (start >= line.length-1) ? 0 : ++start;
        size --;
        return (T) line[start];
    }

    public void add (T element){
        validateOverflow();
        end = (end >= line.length-1) ? 0 : ++end;
        line[end] = element;
        size++;
    }

    public void addAll (Collection<? extends T> list){
        list.forEach(this::add);
    }

    public void addAll (T... array){
        Arrays.stream(array).forEach(this::add);
    }

    public void addAll(StaticQueue<? extends  T> queue){
        queue.stream().forEach(this::add);
    }

    public boolean contains (T element){
        for (int i = 0; i < size; i++){
            if(line[physicalIndex(i)].equals(element)){
                return true;
            }
        }
        return false;
    }

    public boolean containsAll (Collection<? extends  T> list){
        return list.stream().allMatch(this::contains);
    }

    public boolean containsAll (T... array){
        return Arrays.stream(array).allMatch(this::contains);
    }

    public boolean containsAll (StaticQueue<? extends T> queue){
        return queue.stream().allMatch(this::contains);
    }

    public Object[] toArray (){
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++){
            array[i] = line[physicalIndex(i)];
        }
        return array;
    }

    public Stream<T> stream (){
        return (Stream<T>) Arrays.stream(toArray());
    }

    //private

    private int physicalIndex (int index){
        index += start+1;
        index = (index >= line.length) ? index - line.length : index;
        return index;
    }

    private void validateIndex (int index) {
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("INVALID INDEX");
        }
    }

    private void validateUnderflow (){
        if (isEmpty()){
            throw new IndexOutOfBoundsException("THE QUEUE IS EMPTY");
        }
    }

    private void validateOverflow (){
        if (isFull()){
            throw new IndexOutOfBoundsException("THE QUEUE IS FULL");
        }
    }

    //Override

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}