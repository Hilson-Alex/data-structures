import document.project.java.dictionary.Dictionary;

public class DictionaryTest {

    public static void main (String[] args){
        Dictionary<Integer, String> dictionary = new Dictionary<>();
        dictionary.put("Banana", 1);
        dictionary.put("Garoto", 11);
        dictionary.put("Maçã", 3);
        dictionary.put("Farofa", 6);
        System.out.println(dictionary.size());
        System.out.println(dictionary);
        dictionary.remove(3);
        System.out.println(dictionary);
    }

}
