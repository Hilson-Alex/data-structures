package document.project.java.ds_collection.list;

class ListValidate {

    static void index (int index, int size){
        if (index < 0 || index > size ){
            throw new IllegalArgumentException("INDEX OUT OF BOUNDS");
        }
    }

    static void occupiedIndex(int validIndex, int size){
        index(validIndex, size);
        if (validIndex == size) throw new IllegalArgumentException("INDEX OUT OF BOUNDS");
    }

}
