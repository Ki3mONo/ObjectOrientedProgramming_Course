package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;


public class TextMap implements WorldNumberPositionMap<String, Integer>{
    //żeby cały czas nie czytać rozmiaru, pierwszy bedzie -1, żeby dla inicjalizacji ostatnim uzytym był 0 bez dodawania niepotrzebnej większej logiki
    int lastUsed = -1;
    private Map<Integer,String> words = new HashMap<>();

    //przydatne do testów
    public Map<Integer, String> getWords() {
        return words;
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return position>=0 && position<=lastUsed;
    }


    @Override
    public boolean place(String string) {
        //chyba należy tutaj założyc, że napisy nie powtarzają się - inaczej nie miałoby to sensu(np przy move, gdzie podajemy przecież napis - który wtedy przesunąć)
        if (words.containsValue(string)){
            return false;
        }else{
            words.put(++lastUsed, string);
            return true;
        }
    }

    @Override
    public void move(String word, MoveDirection direction) {
        Integer position = findKey(word);
        if (position != null) {
            Integer newPosition = position;
            switch (direction) {
                case FORWARD, RIGHT -> newPosition++;
                case BACKWARD, LEFT -> newPosition--;
            }

            if (canMoveTo(newPosition)) {
                swap(word, newPosition, position);
            }
        }
    }


    private void swap(String word, Integer newPosition, Integer position) {
        String temp = words.get(newPosition);
        words.put(newPosition, word);
        words.put(position, temp);
    }


    public Integer findKey(String key){
        for (Integer i : words.keySet()){
            if (this.objectAt(i).equals(key)){
                return i;
            }
        }
        return null;
    }
    @Override
    public boolean isOccupied(Integer position) {
        return words.containsKey(position);
    }

    @Override
    public String objectAt(Integer position) {
        return words.get(position);
    }
    @Override
    public String toString() {
        return words.values().toString();
    }
}
