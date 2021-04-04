// studentID: 201360672 ; sgtcioch@liverpool.ac.uk / T.A.Ciochirca@student.liverpool.ac.uk ; name: CIOCHIRCA TEODOR-AVRAM

import java.util.*;

class Shelf {  //defining the buffer
    private int capacity = 0;

    // Synchronized method to prevent simultaneous access to buffer.
    // Add a pot to the Shelf if it's not full.
    public synchronized void produce() {

        while (capacity == 5) {  //make use of the semaphores, if the shelf is full a producer will wait
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
            capacity++; //if a producer can produce, increase the shelf capacity as in put another pot on the shelf and notify
            notify();
    }

    public synchronized void consume() {

        while (capacity == 0) //make use of the semaphores, if the shelf is empty a consumer will wait
        {
            try
            {
                wait();
            }
            catch (InterruptedException e) {
                System.err.println(e);
            }
        }
            capacity--; //if a consumer can consume, decrease the shelf capacity as in remove a pot off the shelf and notify
            notify();
    }
}

class Packer extends Thread {
    private Shelf sh;
    private int time,n=0;
    public String name;

    public Packer (Shelf s, String n){  //a constructer for the consumer
        this.name = n;
        this.sh = s;
    }

    public void run() {

        while (n < 20) { //while hte packer still has pots to pack proceed
            try {
                Thread.sleep(400); //the time a packer packs a pot
            } catch (InterruptedException e){

            }

            sh.consume();
            System.out.println("The packer " + "(" +this.name + ")" +" has casted a spell and a pot was packed.");
            n++;   //more efficient for a waiting thread or process to relinquish control of CPU
            Thread.yield();
        }
    }
}

class Potter extends Thread {
    private Shelf sh;
    private int time;
    private int pots = 0;
    private String name;

    public Potter (Shelf s, int t, String n){ //constructor for a producer
        this.time = t;
        this.name = n;
        this.sh = s;
    }

    public void run() {

        while (this.pots < 10){  //while the producer X hasn't produced all of his 10 pots proceed
                System.out.println(this.name + " has started casting.");
                try {
                    Thread.sleep(this.time); //the time a producer takes to produce a pot, depends on producer
                } catch (InterruptedException e){

                }
                sh.produce();
                System.out.println(this.name + " casted a spell and a pot was produced.");
                this.pots++;
                System.out.println(this.name + " conjured a total of " +pots+ " pots");
                Thread.yield();
        }
        System.out.println(this.name + " has produced all 10 pots.");
    }
}

class Pottery {
    public static void main(String[] args) {
        Shelf s = new Shelf();
        Potter harry = new Potter(s,600, "Harry");
        Potter lily = new Potter(s, 500,"Lily");        //consumer and producers creation
        Packer voldemort = new Packer(s, "Voldemort");

        harry.start();
        lily.start();
        voldemort.start();
    }
}