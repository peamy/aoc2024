public class Day10 {

    public Day10(){
        int[][] map = Commons.readFileAsIntMap("d10");
        System.out.println(getPart1Score(map));
    }

    private int getPart1Score(int[][] map){
        int total = 0;

        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                if(map[y][x] == 0){
                    checkMap = new int[map.length][map[0].length];
                    total+= getScoreForZero(map, y, x);
                }
            }
        }

        return total;
    }

    int[][] checkMap;
    private int getScoreForZero(int[][] map, int y, int x){
        int total = 0;
        total += recursiveSearch(map, y, x, -1);
        return total;
    }

    private int recursiveSearch(int[][] map, int y, int x, int prevvalue){
        try {
            int currentvalue = map[y][x];

            if(checkMap[y][x] == 1)
                return 0;
            if(currentvalue != prevvalue + 1){
                return 0;
            }

            int total = 0;
            checkMap[y][x] = 1;
            Commons.printMap(checkMap);

            total += recursiveSearch(map,y, x + 1, currentvalue);
            total += recursiveSearch(map,y, x - 1, currentvalue);
            total += recursiveSearch(map,y + 1, x, currentvalue);
            total += recursiveSearch(map,y - 1, x, currentvalue);

            if(map[y][x] == 9){
                total +=1;
            }
            return total;
        }
        catch (ArrayIndexOutOfBoundsException ex){
            return 0;
        }
    }

}
