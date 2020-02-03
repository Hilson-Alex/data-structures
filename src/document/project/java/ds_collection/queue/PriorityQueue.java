package document.project.java.ds_collection.queue;

import document.project.java.ds_collection.DSCollection;
import document.project.java.ds_collection.list.DoubleLinkedList;

import java.util.Arrays;
import java.util.List;

public class PriorityQueue<T>  implements DSCollection<T> {

    private DoubleLinkedList<T>[] lists;

    public PriorityQueue(int priorityNumber) {
        lists = new DoubleLinkedList[priorityNumber];
        for(int i=0; i<priorityNumber; i++){
            lists[i] = new DoubleLinkedList<>();
        }
    }

    public void add(T element, int priority){
        validatePriority(priority);
        lists[priority].add(element);
    }

    public void addAll(int priority, T... elements){ Arrays.stream(elements).forEach(element -> add(element,priority)); }

    public void addMaxPriority(T element){ lists[getPriorityNumber()-1].add(element); }

    public int getPriorityNumber(){ return lists.length; }

    @Override
    public void add(T element) { add(element,0); }

    @Override
    public T remove(){
        int i = getPriorityNumber()-1;
        while (lists[i].isEmpty() && i>0){ i--; }
        return lists[i].remove(0);
    }

    @Override
    public void clean() { Arrays.stream(lists).forEach(DoubleLinkedList::clean); }

    @Override
    public boolean contains(T element) {
        for (DoubleLinkedList<T> list : lists) {
            if(list.contains(element)) return true;
        }
        return false;
    }

    @Override
    public T get(int index) {
        //isValidIndex(index);
        for (int i = getPriorityNumber() - 1; i >= 0; i--){
            if(index >= lists[i].size()){
                index -=lists[i].size();
            }else {
                return lists[i].get(index);
            }
        }
        return null;
    }

    @Override
    public int lastIndexOf(T element) {
        //TODO implement
        return 0;
    }

    @Override
    public List<T> toList() {
        //TODO implement
        return null;
    }

    @Override
    public int indexOf(T element) {
        int index = 0;
        for (int i = getPriorityNumber() - 1; i >= 0; i--){
            if (lists[i].indexOf(element) == -1){
                index += lists[i].size();
            } else {
                return index + lists[i].indexOf(element);
            }
        }
        return -1;
    }

    @Override
    public int size() { return Arrays.stream(lists).mapToInt(DoubleLinkedList::size).sum(); }

    @Override
    public Object[] toArray() { return concat().toArray(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityQueue<?> that = (PriorityQueue<?>) o;
        return Arrays.equals(lists, that.lists);
    }

    @Override
    public int hashCode() { return Arrays.hashCode(lists); }

    @Override
    public String toString() {
        String result = "PriorityQueue{ ";
        for (int i=getPriorityNumber()-1; i>0; i--) {
            result = result.concat("Priority: "+i+" "+lists[i]+", ");
        }
        return result.concat("Priority: "+0+" "+lists[0]+" }");
    }

    private DoubleLinkedList<T> concat(){
        DoubleLinkedList<T> result = new DoubleLinkedList<>();
        for (int i=getPriorityNumber()-1; i>=0; i--) {
            result.addAll(lists[i]);
        }
        return result;
    }

    private void validatePriority(int priority){
        if (priority < 0 || priority >= getPriorityNumber()){
            throw new IllegalArgumentException("Invalid Priority! "+priority+" in bounds: 0-"+getPriorityNumber());
        }
    }
}

