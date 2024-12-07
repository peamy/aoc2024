import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    // based on the data I think I need to use Long isntead of Int today
    public Day7(){
        List<String> input = Commons.readFileLines("d7");
        long result = 0L;
        for (String line : input){
            result+=getLineValue(line);
        }
        System.out.println(result);
    }

    private long getLineValue(String line){
        long solution = Long.parseLong(line.split(":")[0]);
        List<Integer> numbers = new ArrayList<>();
        String[] split = line.split(" ");
        for(int i = 1; i < split.length; i++){
            numbers.add(Integer.parseInt(split[i]));
        }
        return bruteForceSolution1(solution, numbers);
    }

    private long bruteForceSolution1(long solution, List<Integer> numbers){
        return bruteForce1(solution, numbers, "", 0L, "", 0);
    }

    private long bruteForce1(long solution, List<Integer> numbers, String calcStack, long currentValue, String operation, int index){
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

        long addition = bruteForce1(solution, numbers, calcStack, calc, "+", index+1);
        long multiply = bruteForce1(solution, numbers, calcStack, calc, "*", index+1);

        if(addition == solution || multiply == solution){
            return solution;
        }

        return 0L;
    }
}
