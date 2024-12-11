import Objects.Stone;

import java.util.*;

public class Day11 {
    public Day11(){
        String input = Commons.readFileLines("d11").get(0);
        List<Stone> stones = createStones(input);
        int day = 1;
        for(day = day; day <= 25; day++){
            stones = blink(stones);
            System.out.println("Day " + day + " " + countingRocks(stones));
        }
        for(day = day; day <= 75; day++){
            stones = blink(stones);
            System.out.println("Day " + day + " " + countingRocks(stones));
        }
    }

    private long countingRocks(List<Stone> stones){
        long result = 0L;
        for(Stone stone : stones){
            result += stone.amount;
        }
        return result;
    }

    private List<Stone> blink(List<Stone> stones){
        List<Stone> newStones = new ArrayList<>();

        for(Stone stone : stones){
            addStonesToList(newStones, stone.Split());
        }

        return newStones;
    }

    private void addStonesToList(List<Stone> mainStones, List<Stone> subStones){
        for(Stone stoneToAdd : subStones) {
            Stone existing = mainStones.stream().filter(stone -> stone.value == stoneToAdd.value).findFirst().orElse(null);
            if(existing == null){
                mainStones.add(stoneToAdd);
            }
            else {
                existing.amount += stoneToAdd.amount;
            }
        }
    }

    private List<Stone> createStones(String input){
        List<Stone> stones = new ArrayList<>();
        for(String s : input.split(" ")) {
            stones.add(new Stone(Integer.parseInt(s), 1));
        }
        return stones;
    }
}
