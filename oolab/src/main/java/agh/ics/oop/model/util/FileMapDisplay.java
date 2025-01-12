package agh.ics.oop.model.util;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileMapDisplay implements MapChangeListener {
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        String MapID = worldMap.getID().toString();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String fileName = MapID + ".log";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(timestamp + ":    " + message);
            writer.newLine();
            writer.write(worldMap.toString());
            writer.newLine();
            writer.write("_____________________________________________");
            writer.newLine();
        }
        catch(IOException e){
            System.err.println(e.getMessage());;
        }
    }
}
