package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable{
    ExecutorService executorService;
    List<Simulation> simulationList;
    List<Thread> simThreads = new ArrayList<>();
    public SimulationEngine(List<Simulation> simulationList){
        this.simulationList = simulationList;
        executorService = Executors.newFixedThreadPool(4);
    }

    public void runSync(){
        for(Simulation simulation : simulationList){
            simulation.run();
        }

    }
    public void runAsync() throws InterruptedException {
        for(Simulation simulation : simulationList){
            simThreads.add(new Thread(simulation));
        }
        for (Thread simThread : simThreads){
            simThread.start();
        }
        awaitSimulationsEnd();
    }
    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread simThread : simThreads){
            simThread.join();
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

    }

    public void runAsyncInThreadPool() throws InterruptedException {
        executorService = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulationList){
            executorService.submit(simulation);
        }
    }

    @Override
    public void run() {
        System.out.println("Thread started.");
    }
}
