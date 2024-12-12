package Objects;

public class Edge {
    public Edge(int x, int y, Shape.DIR dir){
        direction = dir;
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;
    public Shape.DIR direction;
    public boolean counted;
}
