import Objects.Robot;

import java.util.ArrayList;
import java.util.List;

public class Day14 {
    public Day14(){
        part2();
    }

    void part1(){
        List<Robot> robots = getRobots(false);
        printRobotLocations(robots);
        robots.forEach(r -> r.moveSeconds(100));
        printRobotLocations(robots);
        System.out.println(getSafetyFactor(robots));
    }

    void part2(){
        List<Robot> robots = getRobots(false);
        int solution = 0;
        for(int i = 1; i < 100000; i++) {
            robots.forEach(r -> r.moveSeconds(1));
            if(couldBeAChristmasTree(robots)){
                System.out.println(i);
                solution = i;
            }
        }
        printRobotLocations(robots);
        System.out.println(solution);
    }

    private List<Robot> getRobots(boolean example){
        List<Robot> robots = new ArrayList<>();
        List<String> input = Commons.readFileLines(example ? "d14ex" : "d14");
        int bx = example ? 11 : 101;
        int by = example ? 7 : 103;
        for(String line : input){
            int x = Integer.parseInt(line.split("=")[1].split(",")[0]);
            int y = Integer.parseInt(line.split("=")[1].split(",")[1].split(" ")[0]);
            int vx = Integer.parseInt(line.split("=")[2].split(",")[0]);
            int vy = Integer.parseInt(line.split("=")[2].split(",")[1]);
            robots.add(new Robot(x,y,vx,vy,bx,by));
        }
        return robots;
    }

    private long getSafetyFactor(List<Robot> robots){
        long lt = 0;
        long rt = 0;
        long lb = 0;
        long rb = 0;

        int bx = robots.get(0).bx;
        int by = robots.get(0).by;
        int left = (bx -1) / 2;
        int up = (by-1)/2;

        for(Robot rob : robots){
            if(rob.x < left && rob.y < up){
                lt++;
            }
            else if(rob.x > left && rob.y < up){
                rt++;
            }
            else if(rob.x < left && rob.y > up){
                rb++;
            }
            else if(rob.x > left && rob.y > up){
                lb++;
            }
        }

        return lt * lb * rt * rb;
    }

    long cbtree = Long.MAX_VALUE;
    private boolean couldBeAChristmasTree(List<Robot> robs){
//        int bx = robs.get(0).bx;
//        int by = robs.get(0).by;
//        int left = (bx -1) / 2;
//        int up = (by-1)/2;
//        long amountOfRobotsInMiddle = robs.stream().filter(r -> r.x == left || r.y == up).count();
        long score = getSafetyFactor(robs);
        if(score < cbtree){
            cbtree = score;
            return true;
        }

        return false;
    }

    private void printRobotLocations(List<Robot> robots){
        int bx = robots.get(0).bx;
        int by = robots.get(0).by;
        int[][] grid = new int[by][bx];
        for(Robot rob : robots){
            grid[rob.y][rob.x]++;
        }
        Commons.printMapHideZero(grid);
    }
}
