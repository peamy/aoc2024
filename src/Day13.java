import Objects.Clawmachine;

import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public Day13(){
        List<Clawmachine> clawmachines = createClawMachines();
        int totalPresses = 0;
        int winners = 0;
        for(Clawmachine clawmachine: clawmachines){
            int result = bruteForceClawMachine(clawmachine);
            if(result > 0){
                totalPresses+=result;
                winners++;
            }
        }
        System.out.println(totalPresses);
    }

    private List<Clawmachine> createClawMachines(){
        List<Clawmachine> machines = new ArrayList<>();
        List<String> input = Commons.readFileLines("d13");
        List<String> tmpList = new ArrayList<>();
        for(String s : input){
            if(s.isEmpty() && tmpList.size() == 3){
                machines.add(new Clawmachine(tmpList));
                tmpList = new ArrayList<>();
            }
            else {
                tmpList.add(s);
            }
        }
        if(tmpList.size() == 3){
            machines.add(new Clawmachine(tmpList));
        }

        return machines;
    }

    private int bruteForceClawMachine(Clawmachine clawmachine){
        int lowest = 0;
        for(int a = 1; a <= 100; a++){
            for(int b = 1; b <= 100; b++){
                clawmachine.pressA(a);
                clawmachine.pressB(b);
                if(clawmachine.won()){
                    int cost = 3*a + b;
                    if(lowest == 0 || lowest > cost){
                        lowest = cost;
                    }
                }
                clawmachine.reset();
            }
        }
        return lowest;
    }
}
