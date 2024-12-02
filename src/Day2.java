import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day2 {

    public void solvePartOne() {
        List<String> input = Commons.readFileLines("day2.txt");
        long result = input.stream().filter(this::isSafe).count();
        System.out.println(result);
    }

    private Boolean isSafe(String row){
        return isSafe(Arrays.stream(row.split(" ")).map(Integer::parseInt).toList());
    }

    private boolean isSafe(List<Integer> row){
        int previous = -1;
        int ascending = 0;
        for (Integer number : row) {
            int difference = Math.abs(number - previous);
            if(previous != -1) {
                if (difference > 3) {
                    System.out.println(row + " FALSE");
                    return false;
                }
                if(ascending == 0){
                    ascending = number - previous;
                }
                if((ascending > 0 && number - previous < 0)
                        ||(ascending < 0 && number - previous > 0)
                        || number == previous){
                    System.out.println(row + " FALSE");
                    return false;
                }
            }
            previous = number;
        }
        System.out.println(row + " TRUE");
        return true;
    }

    public void solvePartTwo() {
        List<String> input = Commons.readFileLines("day2.txt");
        long result = input.stream().filter(this::isSafe2).count();
        System.out.println(result);
    }

    private Boolean isSafe2(String row){
        List<Integer> numbs = Arrays.stream(row.split(" ")).map(Integer::parseInt).toList();
        if(isSafe(numbs)){
            return true;
        }
        for(int i = 0; i < numbs.size(); i++){
            List<Integer> tmpList = new ArrayList<>();
            for(int j = 0; j < numbs.size(); j++){
                if(i != j){
                    tmpList.add(numbs.get(j));
                }
            }
            if(isSafe(tmpList)){
                return true;
            }
        }
        return false;
    }

}
