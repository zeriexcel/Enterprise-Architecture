/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadpoolexample;

/**
 *
 * @author User
 */
public class ThreadLifecycleExample extends Thread{
        @Override
        public void run() {
        System.out.println(Thread.currentThread().getName() + " - State: " +
        Thread.currentThread().getState());
        try {
        Thread.sleep(2000); // Simulate waiting state
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - State after sleep: " + Thread.currentThread().getState());
}
}