package Objects;

import java.util.ArrayList;
import java.util.List;

public class Shape {
    public enum DIR {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Shape(String name) {
        this.name = name;
        this.blocks = 1;
        edgeList = new ArrayList<>();
    }

    public int edges;
    public List<Edge> edgeList;
    private int secretEdgeCount;
    public int blocks;
    public String name;

    public int getScore() {
        return edges * blocks;
    }

    public int getScorePt2() {
        for (Edge edge : edgeList) {
            if (!edge.counted) {
                secretEdgeCount++;
                checkEdge(edge);
            }
        }
        return secretEdgeCount * blocks;
    }

    private void checkEdge(Edge edge) {
        List<Edge> similarNeighbour;
        if(edge.direction == DIR.RIGHT || edge.direction == DIR.LEFT){
             similarNeighbour = edgeList.stream()
                    .filter(e -> e.direction == edge.direction)
                    .filter(e -> e.y == edge.y - 1 || e.y == edge.y + 1)
                    .filter(e -> e.x == edge.x)
                    .filter(e -> !e.counted)
                    .toList();
        }
        else {
            similarNeighbour = edgeList.stream()
                    .filter(e -> e.direction == edge.direction)
                    .filter(e -> e.y == edge.y)
                    .filter(e -> e.x == edge.x - 1 || e.x == edge.x + 1)
                    .filter(e -> !e.counted)
                    .toList();
        }
        for(Edge e : similarNeighbour){
            if(!e.counted){
                e.counted = true;
                checkEdge(e);
            }
        }
    }
}

