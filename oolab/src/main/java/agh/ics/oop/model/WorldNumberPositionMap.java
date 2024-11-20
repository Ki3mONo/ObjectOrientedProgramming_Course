package agh.ics.oop.model;

public interface WorldNumberPositionMap<T, P extends Number> extends WorldMap<T, P> {
    //dodane żeby tak pusto tu nie było
    /**
     * Finds the key (position) of the given value in the map.
     *
     * @param key The value to find the position for.
     * @return The position (key) associated with the value, or null if not found.
     */
    P findKey(T key);
}
