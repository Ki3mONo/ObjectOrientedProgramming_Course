package agh.ics.oop;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine{
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private List<Simulation> simulationList;
    private List<Thread> simThreads = new ArrayList<>();
    public SimulationEngine(List<Simulation> simulationList){
        this.simulationList = simulationList;
        for(Simulation simulation : simulationList){
            simThreads.add(new Thread(simulation));
        }
    }

    public void runSync(){
        for(Simulation simulation : simulationList){
            simulation.run();
        }

    }
    public void runAsync(){
        for (Thread simThread : simThreads){
            simThread.start();
        }
    }

    public void runAsyncInThreadPool(){
        for (Thread simThread : simThreads){
            executorService.submit(simThread);
        }
    }
    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread simThread : simThreads){
            simThread.join();
        }
        executorService.shutdown();
        if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }

    }
}
