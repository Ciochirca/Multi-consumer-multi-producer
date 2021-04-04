import java.util.*;

public class Shelf {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 5;

    // Synchronized method to prevent simultaneous access to buffer.
    // Add a pot to the Shelf if it's not full.
    public synchronized void produce(int t) {
        while (list.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Thread.sleep(t);
        list.add();
        System.out.println("Producer added a pot to the shelf.");
        notify();
    }

    public synchronized void consume() {
        while (list.size()==0)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e) { }
        }
        Thread.sleep(400);
        list.removeFirst();
        System.out.println("Packer removed a pot from the shelf.");
        notify();
    }
}

