public class Packer extends Thread {
    private Shelf sh;
    private int time;
    public String name;

    public Packer (Shelf s, int t, String n){
        this.time = t;
        this.name = n;
        this.sh = s;
    }

    public void run() {
        int m;
        sh.consume(m);
    }
}