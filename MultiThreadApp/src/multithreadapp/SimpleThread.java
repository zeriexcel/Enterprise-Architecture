/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreadapp;

/**
 *
 * @author User
 */
public class SimpleThread extends Thread {
    @Override
    public void run() {
    System.out.println(Thread.currentThread().getId() + " is executing the thread.");
    }
    
}
