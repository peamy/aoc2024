import Objects.Clawmachine;

import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public Day13(){
        part2();
    }

    private void part1() {
        List<Clawmachine> clawmachines = createClawMachines(false);
        long totalPresses = 0;
        for(Clawmachine clawmachine: clawmachines){
            totalPresses+=bruteForceClawMachine(clawmachine);
        }
        System.out.println(totalPresses);
    }

    private void part2(){
        List<Clawmachine> clawmachines = createClawMachines(true);
        long totalPresses = 0;
        for(Clawmachine clawmachine: clawmachines){
            totalPresses+=calculateClawMachine(clawmachine);
        }
        System.out.println(totalPresses);
    }

    private List<Clawmachine> createClawMachines(boolean isPart2){
        List<Clawmachine> machines = new ArrayList<>();
        List<String> input = Commons.readFileLines("d13");
        List<String> tmpList = new ArrayList<>();
        for(String s : input){
            if(s.isEmpty() && tmpList.size() == 3){
                machines.add(new Clawmachine(tmpList,isPart2));
                tmpList = new ArrayList<>();
            }
            else {
                tmpList.add(s);
            }
        }
        if(tmpList.size() == 3){
            machines.add(new Clawmachine(tmpList,isPart2));
        }

        return machines;
    }

    private long bruteForceClawMachine(Clawmachine clawmachine){
        long lowest = 0;
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

    private long calculateClawMachine(Clawmachine clawmachine){


        long b = clawmachine.calculateB();
        long a = (clawmachine.prizeX - (clawmachine.buttonBX * b)) / clawmachine.buttonAX;

        clawmachine.pressA(a);
        clawmachine.pressB(b);
        if(clawmachine.won()){
            return 3*a + b;
        }

        return 0L;
    }


}
