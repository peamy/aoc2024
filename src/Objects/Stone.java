package Objects;

import java.util.ArrayList;
import java.util.List;

public class Stone {
    public Stone(long value, long amount){
        this.value = value;
        this.amount = amount;
    }
    public long value;
    public long amount;

    public List<Stone> Split(){
        List<Stone> stones = new ArrayList<>();

        String stringified = value+"";

        if(value == 0){
            stones.add(new Stone(1, amount));
        }
        else if(stringified.length()%2==0){
            int strlength = stringified.length();
            int part1 = Integer.parseInt(stringified.substring(0,strlength/2));
            int part2 = Integer.parseInt(stringified.substring(strlength/2));
            stones.add(new Stone(part1, amount));
            stones.add(new Stone(part2, amount));
        }
        else {
            stones.add(new Stone(value*2024, amount));
        }

        return stones;
    }
}
