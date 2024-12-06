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
    private String possibleGuards = "<^>v";
    private int loops;
    public void solvePartOne() {
        map = Commons.readFileAsMap("day6ex.txt");
        prepareData();
        while(guardStep()){
//            Commons.printMap(map);
        }
//        Commons.printMap(map);
        System.out.println(part1result());
    }

    private boolean guardStep() {
        drawMovement();
        if(!markTileMap()){
            loops++;
            return false;
        };

        // check if next position is out of bounds
        int nextx = x + dirx;
        int nexty = y + diry;
        if(nextx < 0 || nextx >= map[0].length || nexty < 0 || nexty >= map.length){
            return false;
        }
        // check if he runs into anything
        else if("#O".contains(map[nexty][nextx])){
            changeDirection();
        }
        // move :)
        else {
            x = nextx; y = nexty;
        }
        return true;
    }

    private void drawMovement(){
        if(map[y][x].equals("+")){
            return;
        }

        if(dirx != 0){
            if(map[y][x].equals("|")){
                map[y][x] = "+";
            }
            else {
                map[y][x] = "-";
            }
        }
        else {
            if(map[y][x].equals("-")){
                map[y][x] = "+";
            }
            else {
                map[y][x] = "|";
            }
        }
    }

    private boolean markTileMap(){
        if(diry != 0){
            if(diry == 1){
                if(tileMap[y][x].Up){
                    return false;
                }
                tileMap[y][x].Up = true;
            } else {
                if(tileMap[y][x].Down){
                    return false;
                }
                tileMap[y][x].Down = true;
            }
        }
        else {
            if(dirx == 1){
                if(tileMap[y][x].Right){
                    return false;
                }
                tileMap[y][x].Right = true;
            }
            else {
                if(tileMap[y][x].Left){
                    return false;
                }
                tileMap[y][x].Left = true;
            }
        }
        return true;
    }

    private void prepareData(){
        tileMap = new Tile[map.length][map[0].length];

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                tileMap[i][j] = new Tile();
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
                if("+-|".contains(map[i][j])){
                    total++;
                }
            }
        }
        return  total;
    }

    public void solvePartTwo() {
        map = Commons.readFileAsMap("day6.txt");

        // because map variable will change during the session
        int mapx = map[0].length;
        int mapy = map.length;
        loops = 0;
        for(int i = 0; i < mapy; i++) {
            for (int j = 0; j < mapx; j++) {
                map = Commons.readFileAsMap("day6.txt");
                prepareData();
                if(map[i][j].equals(".")){
                    map[i][j]="O";
                    while (guardStep());
                }
            }
        }
        System.out.println("Loops: " + loops);
    }

    // wip
    private void printTileMap(){
        String result = "\n\n\n";
        for(int i = 0; i < tileMap.length; i++){
            for(int j = 0; j < tileMap[i].length; j++){
                result+=tileMap[i][j].toString();
            }
            result+="\n";
        }
        System.out.println(result);
    }
}
