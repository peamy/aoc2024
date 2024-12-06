import java.util.Arrays;

public class Day6 {

    private String[][] map;

    private int dirx = 0;
    private int diry = -1;
    private int x;
    private int y;
    private String guard = "^";
    private String possibleGuards = "<^>v";
    public void solvePartOne() {
        map = Commons.readFileAsMap("day6.txt");
        findGuard();
        while(guardStep()){
//            Commons.printMap(map);
        }
        Commons.printMap(map);
        System.out.println(part1result());
    }

    private boolean guardStep() {

        // check if next position is out of bounds
        int nextx = x + dirx;
        int nexty = y + diry;
        if(nextx < 0 || nextx >= map[0].length || nexty < 0 || nexty >= map.length){
            map[y][x] = "X";
            return false;
        }
        // check if he runs into anything
        else if(map[nexty][nextx].equals("#")){
            changeDirection();
            map[y][x] = guard;
        }
        // move :)
        else {
            map[y][x] = "X";
            map[nexty][nextx] = guard;
            x = nextx; y = nexty;
        }
        return true;
    }

    private void findGuard(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(possibleGuards.contains(map[i][j])){
                    x = j;
                    y = i;
                    guard = map[i][j];
                    return;
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
                if(map[i][j].equals("X")){
                    total++;
                }
            }
        }
        return  total;
    }

    public void solvePartTwo() {
    }
}
