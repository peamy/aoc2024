package Objects;

public class Shape {
    public Shape(String name){
        this.name = name;
        this.blocks=1;
    }
    public int edges;
    public int blocks;
    public String name;

    public int getScore(){
        return edges * blocks;
    }
}
