import Objects.Tile;

public class Day6 {

    // visual map with strings
    private String[][] map;
    // map to keep track of which directions have been walked on each point
    // I added the tileMap later for part 2, because i needed to keep track of more than just horizontal and vertical movement
    private Tile[][] tileMap;
    // current direction
    private int dirx;
    private int diry;
    // current location
    private int x;
    private int y;
    private String guard = "^";
    private int loops;
    public void solvePartOne() {
        map = Commons.readFileAsMap("day6ex.txt");
        prepareData();
        while(guardStep()){
            printTileMap();
        }
        printTileMap();
        System.out.println(part1result());
    }

    private boolean guardStep() {
        if(!markTileMap()){
            loops++;
            return false;
        }

        // check if next position is out of bounds
        int nextx = x + dirx;
        int nexty = y + diry;
        if(nextx < 0 || nextx >= map[0].length || nexty < 0 || nexty >= map.length){
            return false;
        }
        // check if he runs into anything
        else if(tileMap[nexty][nextx].IsWall){
            changeDirection();
        }
        // move :)
        else {
            x = nextx; y = nexty;
        }
        return true;
    }

    private boolean markTileMap(){
        if(diry != 0){
            if(diry == 1){
                if(tileMap[y][x].Up){
                    return false; // LOOP
                }
                tileMap[y][x].Up = true;
            } else {
                if(tileMap[y][x].Down){
                    return false; // LOOP
                }
                tileMap[y][x].Down = true;
            }
        }
        else {
            if(dirx == 1){
                if(tileMap[y][x].Right){
                    return false; // LOOP
                }
                tileMap[y][x].Right = true;
            }
            else {
                if(tileMap[y][x].Left){
                    return false; // LOOP
                }
                tileMap[y][x].Left = true;
            }
        }
        tileMap[y][x].PlaceGuard(guard);
        return true;
    }

    private void prepareData(){
        tileMap = new Tile[map.length][map[0].length];

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                Tile tile = new Tile();
                tileMap[i][j] = tile;
                if(map[i][j].equals("#")){
                    tile.IsWall=true;
                    tile.icon = "#";
                }
                String possibleGuards = "<^>v";
                if(possibleGuards.contains(map[i][j])){
                    x = j;
                    y = i;
                    //assuming its always up :)
                    dirx = 0;
                    diry = -1;
                    guard = map[i][j];
                }
            }
        }
    }

    // 0,-1 : 1,0 : 0,1 : -1,0
    private void changeDirection(){
        if(dirx == 0) {
            if(diry == -1){
                dirx = 1;
                guard = ">";
            }
            else {
                dirx = -1;
                guard = "<";
            }
            diry = 0;
        }
        else {
            if(dirx == -1){
                diry = -1;
                guard = "^";
            }
            else {
                diry = 1;
                guard = "v";
            }
            dirx = 0;
        }
    }

    private int part1result(){
        int total = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                total+= tileMap[i][j].Value();
            }
        }
        return  total;
    }

    public void solvePartTwo() {
        map = Commons.readFileAsMap("day6ex.txt");
        loops = 0;
        for(int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                prepareData();
                if(map[i][j].equals(".")){
                    tileMap[i][j].IsWall = true;
                    tileMap[i][j].icon = "O";
                    while (guardStep());
                }
            }
        }
        System.out.println("Loops: " + loops);
    }

    // wip
    private void printTileMap(){
        StringBuilder result = new StringBuilder("\n\n\n");
        for (Tile[] tiles : tileMap) {
            for (Tile tile : tiles) {
                result.append(tile.toString());
            }
            result.append("\n");
        }
        System.out.println(result);
    }
}
