package Recursia6;

public class Towers {
    static int disc = 3;

    public static void main(String[] args) {
        doTowers(disc, 'A', 'B', 'C');
    }
    public static void doTowers(int topN, char from, char inter, char to) {
        if(topN == 1) System.out.println("Disc 1 from " + from + " to " + to);
        else {
            doTowers(topN -1,from, to, inter);
            System.out.println("Disc " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);
        }
    }
}
