import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    // based on the data I think I need to use Long isntead of Int today
    public Day7(){
        List<String> input = Commons.readFileLines("d7");

        // part1
//        long result = 0L;
//        for (String line : input){
//            result+=getLineValue(line, false);
//        }
//        System.out.println(result);

        // part2
        long result2 = 0L;
        for (String line : input){
            result2+=getLineValue(line, true);
        }
        System.out.println(result2);
    }

    private long getLineValue(String line, boolean ispart2){
        long solution = Long.parseLong(line.split(":")[0]);
        List<Integer> numbers = new ArrayList<>();
        String[] split = line.split(" ");
        for(int i = 1; i < split.length; i++){
            numbers.add(Integer.parseInt(split[i]));
        }
        return bruteForceSolution(solution, numbers, ispart2);
    }

    private long bruteForceSolution(long solution, List<Integer> numbers, boolean ispart2){
        return bruteForce1(solution, numbers, "", 0L, "", 0, ispart2);
    }

    private long bruteForce1(long solution, List<Integer> numbers, String calcStack, long currentValue, String operation, int index, boolean ispart2){
        long calc = 0L;
        if(index == 0){
            calc = numbers.get(0);
            calcStack = calc + " ";
        }
        else {
            calcStack += operation + " " + numbers.get(index) + " ";
            switch (operation){
                case "+":
                    calc = currentValue + numbers.get(index);
                    break;
                case "*":
                    calc = currentValue * numbers.get(index);
                    break;
                case "||":
                    calc = Long.parseLong(currentValue + "" + numbers.get(index));
                    break;
            }
        }

        System.out.println(calcStack);
        // last stop
        if(index >= numbers.size() - 1){
            System.out.println(solution + ": " +calcStack + " = " + calc);
            if(calc == solution){
                return solution;
            }
            return 0L;
        }

        long addition = bruteForce1(solution, numbers, calcStack, calc, "+", index+1, ispart2);
        long multiply = bruteForce1(solution, numbers, calcStack, calc, "*", index+1, ispart2);
        long elephant = 0L;
        if(ispart2){
            elephant = bruteForce1(solution, numbers, calcStack, calc, "||", index+1, ispart2);
        }

        if(addition == solution || multiply == solution || elephant == solution){
            return solution;
        }

        return 0L;
    }
}
