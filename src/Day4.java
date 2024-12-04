import java.util.List;

public class Day4 {
    public void solvePartOne() {
        List<String> input = Commons.readFileLines("day4.txt");

        int result = 0;

        for (int i = 0; i < input.get(0).length(); i++) {
            for (int j = 0; j < input.size(); j++) {
                result += genericCheck(input, i, j, 1, 0, "XMAS");
                result += genericCheck(input, i, j, 0, 1, "XMAS");
                result += genericCheck(input, i, j, 1, 1, "XMAS");
                result += genericCheck(input, i, j, 1, -1, "XMAS");

            }
        }

        System.out.println(result);

    }

    public void solvePartTwo() {
        List<String> input = Commons.readFileLines("day4.txt");

        int result = 0;

        for (int i = 0; i < input.get(0).length(); i++) {
            for (int j = 0; j < input.size(); j++) {
                result += XmasCheck(input, i, j);
            }
        }
        System.out.println(result);
    }

    private int genericCheck(List<String> input, int x, int y, int offx, int offy, String word) {
        try {
            String checkWord = "";
            int xoffset = 0;
            int yoffset = 0;
            for (char c : word.toCharArray()) {
                String toAdd = input.get(y + yoffset).substring(x + xoffset, x + xoffset + 1);
                checkWord += toAdd;
                xoffset += offx;
                yoffset += offy;
            }

            return word.equals(checkWord)
                    || word.equals(new StringBuilder(checkWord).reverse().toString())
                    ? 1
                    : 0;
        } catch (IndexOutOfBoundsException ex) {
            return 0;
        }
    }

    private int XmasCheck(List<String> input, int x, int y){
        try {
            if(input.get(y).substring(x, x+1).equals("A")){
                int result = genericCheck(input, x-1, y-1, 1,1,"MAS")
                        + genericCheck(input, x-1, y+1, 1,-1,"MAS");
                return result == 2 ? 1 : 0;
            };
            return 0;
        }
        catch(IndexOutOfBoundsException ex){
            return 0;
        }
    }


}
