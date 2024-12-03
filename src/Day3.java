import java.util.Arrays;
import java.util.List;

public class Day3 {
    private final static String validNumbers="0123456789";
    public void solvePartOne(){
        List<String> input = Commons.readFileLines("day3.txt");
        int result = 0;
        for(String str : input){
            result+=getValueForString(str);
        }
        System.out.println(result);
    }

    public void solvePartTwo() {
        List<String> input = Commons.readFileLines("day3.txt");
        String totalString = "";
        for(String line : input){
           totalString+=line;
        }
        int result = 0;

        String[] dos = totalString.split("do\\(\\)");
        for(String doo : dos){
            String[] dodos = doo.split("don't");
            String actualDo = doo.split("don't")[0];

            result+=getValueForString2(actualDo);

        }

        System.out.println(result);
    }

    private int getValueForString(String row){
        String[] parts = row.split("mul\\(");
        int total = 0;
        for(String part : parts){
            String[] subparts = part.split("\\)"); // [ "213213,1421439", "...."]
            String[] commaSeperatedPart = subparts[0].split(","); // ["213213", "23231"]
            if(commaSeperatedPart.length != 2){
                continue;
            }
            try {
                int firstNumber = Integer.parseInt(commaSeperatedPart[0]);
                int secondNumber = Integer.parseInt(commaSeperatedPart[1]);
                total+= firstNumber * secondNumber;
            }
            catch(NumberFormatException e){
            }
        }
        return total;
    }

    private int getValueForString2(String row){
        String[] parts = row.split("mul\\(");
        int total = 0;
        for(String part : parts){
            String[] subparts = part.split("\\)"); // [ "213213,1421439", "...."]
            String[] commaSeperatedPart = subparts[0].split(","); // ["213213", "23231"]
            if(commaSeperatedPart.length != 2){
                continue;
            }
            try {
                int firstNumber = Integer.parseInt(commaSeperatedPart[0]);
                int secondNumber = Integer.parseInt(commaSeperatedPart[1]);
                total+= firstNumber * secondNumber;
            }
            catch(NumberFormatException e){
            }
        }
        return total;
    }


}
