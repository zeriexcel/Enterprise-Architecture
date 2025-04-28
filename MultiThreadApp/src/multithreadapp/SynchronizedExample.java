/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreadapp;

/**
 *
 * @author User
 */
public class SynchronizedExample extends Thread {
    private Counter counter;
    public SynchronizedExample(Counter counter) {
        this.counter = counter;
        }
        @Override
        public void run() {
        for (int i = 0; i < 1000; i++) {
        counter.increment();
        }
    }
}
