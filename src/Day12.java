import Objects.Shape;

public class Day12 {

    String[][] map;
    int[][] checkMap;
    int[][] checkMap2;

    public Day12() {
        map = Commons.readFileAsMap("d12");
        checkMap = new int[map.length][map[0].length];
        checkMap2 = new int[map.length][map[0].length];

        int total = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (checkMap2[y][x] == 0) {
                    checkMap = new int[map.length][map[0].length];
                    Shape shape = new Shape(map[y][x]);
                    checkNeighbours(x, y, shape);
                    total += shape.getScore();
                }
            }
        }
        System.out.println(total);
    }

    private void checkNeighbours(int x, int y, Shape shape) {
        if (x < 0 || x >= map[0].length || y < 0 || y >= map.length
                || checkMap[y][x] == 1) {
            return;
        }
        checkMap[y][x]=1;
        checkMap2[y][x] = 1;
        checkNeighbour(x, y - 1, shape);
        checkNeighbour(x, y + 1, shape);
        checkNeighbour(x - 1, y, shape);
        checkNeighbour(x + 1, y, shape);
    }

    private void checkNeighbour(int x, int y, Shape shape) {
        if (x < 0 || x >= map[0].length || y < 0 || y >= map.length) {
            shape.edges++;
            return;
        }
        if(checkMap[y][x] == 1){
            return;
        }
        if (map[y][x].equals(shape.name)) {
            shape.blocks++;
            checkMap[y][x] = 1;
            checkMap2[y][x] = 1;
            checkNeighbour(x, y - 1, shape);
            checkNeighbour(x, y + 1, shape);
            checkNeighbour(x - 1, y, shape);
            checkNeighbour(x + 1, y, shape);
        } else {
            shape.edges++;
        }
    }
}
