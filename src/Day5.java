import Objects.Rule;

import java.util.ArrayList;
import java.util.List;

public class Day5 {

    private List<Rule> rules  = new ArrayList<>();;
    private List<List<Integer>> data  = new ArrayList<>();;
    private List<List<Integer>> part2data  = new ArrayList<>();;
    public void solvePartOne() {
        prepData();
        int result = 0;
        for(List<Integer> nums : data){
            result += checkRowAndReturnValue(nums);
        }
        System.out.println(result);
    }

    public void solvePartTwo() {
        int result = 0;
        for(List<Integer> nums : part2data){
            result += fixBrokenRow(nums);
        }
        System.out.println(result);
    }

    private void prepData(){
        List<String> input = Commons.readFileLines("day5.txt");
        for(String row : input){
            if(row.contains("|")){
                String[] splitRule = row.split("\\|");
                rules.add(new Rule(Integer.parseInt(splitRule[0]), Integer.parseInt(splitRule[1])));
            }
            else if(row.contains(",")){
                String[] numbers = row.split(",");
                List<Integer> tmpList = new ArrayList<>();
                for(String nr : numbers){
                    tmpList.add(Integer.parseInt(nr));
                }
                data.add(tmpList);
            }
        }
    }

    private int checkRowAndReturnValue(List<Integer> numbers)
    {
        for(int i = 0; i < numbers.size() -1; i++){
            if(rules.contains(new Rule(numbers.get(i+1), numbers.get(i)))){
                part2data.add(numbers);
                return 0;
            }
        }
        return numbers.get((numbers.size()-1) / 2);
    }

    private int fixBrokenRow(List<Integer> numbers) {
        while(fixNextIssue(numbers));
        return numbers.get((numbers.size()-1) / 2);
    }

    private boolean fixNextIssue(List<Integer> numbers){
        for(int i = 0; i < numbers.size() -1; i++){
            if(rules.contains(new Rule(numbers.get(i+1), numbers.get(i)))){
                Integer value = numbers.remove(i+1);
                numbers.add(i,value);
                return true;
            }
        }
        return false;
    }
}
