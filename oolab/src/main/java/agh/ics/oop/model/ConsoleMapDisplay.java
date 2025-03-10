package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {

    private int updateCount = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        updateCount++;
        System.out.println("Map: " + worldMap.getID());
        System.out.println("Update #" + updateCount + ": " + message);
        System.out.println(worldMap);
    }
    //do testów
    public int getUpdateCount(){
        return updateCount;
    }
}
