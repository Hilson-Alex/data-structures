package document.project.java.ds_collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StaticList<T> implements DSList<T> {

    private Object[] list;
    private int size = 0;

    public StaticList(int maxCapacity) {
        list = new Object[maxCapacity];
    }

    public StaticList(){ list = new Object[10]; }

    @SuppressWarnings("unchecked")
    public StaticList<T> copy(){
        StaticList<T> newStaticList = new StaticList<>(list.length);
        newStaticList.addAll((T)list.clone());
        return newStaticList;
    }

    public boolean isFull (){
        return size == list.length;
    }

    public boolean isValidPosition (int index){
        return index < 0 || index > size;
    }

    //Implements ds_collection

    @Override
    public void remove(T element) {
        while (contains(element)){
            remove(indexOf(element));
        }
    }

    @Override
    public void replace(T element, int index) {
        ListValidate.occupiedIndex(index, size);
        list[index] = element;

    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size-1; i >= 0; i--){
            if(list[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add (T element, int index){
        if (isValidPosition(index)){
            for (int i = size - 1; i > index; i++){
                list[i] = list[i-1];
            }
            list[index] = element;
            size ++;
        }
    }

    @Override
    public int size (){
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove (int index){
        T element = (T)list[index];
        for (int i = index; i < size - 1; i++){
            list[i] = list[i + 1];
        }
        return element;
    }

    @Override
    public boolean contains (T cont){
        for (Object obj : list){
            if (obj.equals(cont)){
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get (int index){
        if (index >= 0 && index < size){
            return (T) list[index];
        }
        return null;
    }

    @Override
    public int indexOf (T cont){
        for (int i = 0; i < size; i++) {
            if (list[i].equals(cont)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clean() {
        this.list = new Object[list.length];
        size = 0;
    }

    @Override
    public T remove() {
        return remove(size-1);
    }

    @Override
    public Object[] toArray() {
        return list.clone();
    }

    @Override
    public List<T> toList() {
        List<T> toList = new ArrayList<>();
        for (Object element : list){
            toList.add((T) element);
        }
        return toList;
    }


    //Override Object

    @Override
    public String toString() {
        return "StaticList[" + Arrays.stream(list).map(Object::toString).collect(Collectors.joining(", "))
                + "]";
    }
}
