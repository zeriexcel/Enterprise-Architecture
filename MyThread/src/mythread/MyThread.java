
package mythread;


public class MyThread extends Thread{

    @Override
    public void run(){
        System.out.println("Thread is running");
    }
    
    public static void main(String[] args) {
        MyThread t = new MyThread ();
        t.start();
    }
    
}
