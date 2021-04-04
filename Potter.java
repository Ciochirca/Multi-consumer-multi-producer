public class Potter extends Thread {
    private Shelf sh;
    private int time,pots;
    public String name;

    public Potter (Shelf s, int t, String n, int p){
        this.time = t;
        this.name = n;
        this.sh = s;
        this.pots = p
    }

    public void run() {
        int m;
        sh.produce(m);
    }
}