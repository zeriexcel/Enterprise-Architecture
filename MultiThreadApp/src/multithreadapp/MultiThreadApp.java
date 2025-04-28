/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package multithreadapp;

/**
 *
 * @author User
 */
public class MultiThreadApp {

    
    public static void main(String[] args) throws InterruptedException {
        
        //Activity 1,2
        
        /*RunnableTask task1 = new RunnableTask();
        RunnableTask task2 = new RunnableTask();
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start(); // Starts thread1
        thread2.start(); // Starts thread2*/
        
        // Activity 3
        Counter counter = new Counter();
        // Create and start multiple threads
        Thread thread1 = new SynchronizedExample(counter);
        Thread thread2 = new SynchronizedExample(counter);
        thread1.start();
        thread2.start();
        // Wait for threads to finish
        thread1.join();
        thread2.join();
        System.out.println("Final counter value: " + counter.getCount());

    
    }
      
    
}
