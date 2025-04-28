/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package threadpoolexample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author User
 */
public class ThreadPoolExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        //Activity 4
        // Create a thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // Submit tasks to the pool
        for (int i = 1; i <= 5; i++) {
        executorService.submit(new Task(i));
        }
        // Shutdown the thread pool
        executorService.shutdown();

        //Activity 5
        ThreadLifecycleExample thread = new ThreadLifecycleExample();
        System.out.println(thread.getName() + " - State before start: " +
        thread.getState());
        thread.start(); // Start the thread
        System.out.println(thread.getName() + " - State after start: " +
        thread.getState());
        
    }
    
}
