import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Day1 {
    public void solvePartOne() {
        List<String> input = Commons.readFileLines("day1.txt");
        List<Integer> left = createList(input, 0);
        List<Integer> right = createList(input, 1);
        Collections.sort(left);
        Collections.sort(right);

        Integer answer = 0;

        for (int i = 0; i < left.size(); i++) {
            Integer difference = Math.abs(left.get(i) - right.get(i));
            System.out.println(left.get(i) + "   " + right.get(i) + "   " + difference);
            answer += difference;
        }
        System.out.println(answer);
    }

    private static List<Integer> createList(List<String> input, int column) {
        List<Integer> result = new ArrayList<>();
        input.forEach(in -> {
            String s = in.split("   ")[column];
            result.add(Integer.parseInt(s));
        });
        return result;
    }

    public void solvePartTwo() {
        List<String> input = Commons.readFileLines("day1.txt");
        List<Integer> left = createList(input, 0);
        List<Integer> right = createList(input, 1);

        AtomicReference<Integer> answer = new AtomicReference<>(0);
        left.forEach(nr -> answer.updateAndGet(v -> v + nr * countNumberInList(right, nr)));

        System.out.println(answer);
    }

    private Integer countNumberInList(List<Integer> list, Integer number) {
        return (int) list.stream().filter(nr -> nr.equals(number)).count();
    }
}
