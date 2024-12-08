import Objects.Antidote;
import Objects.Beacon;

import java.util.ArrayList;
import java.util.List;

public class Day8 {
    public Day8(){
        List<String> input = Commons.readFileLines("d8");
        List<Beacon> beacons = createBeacons(input);
        int boudnsX = input.get(0).length();
        int boundsY = input.size();

        List<Antidote> antidotes = new ArrayList<>();
        for(Beacon beacon : beacons){
            for(Beacon testbeacon: beacons){
                Antidote antidote = beacon.createsAntidote(testbeacon, boudnsX, boundsY);
                if(antidote != null && antidotes.stream().filter(a -> a.Overlaps(antidote)).findFirst().orElse(null) == null){
                    antidotes.add(antidote);
                }
            }
        }
        System.out.println(antidotes.size());

        //part2
        antidotes = new ArrayList<>();
        for(Beacon beacon : beacons){
            for(Beacon testbeacon: beacons){
                List<Antidote> newAntidotes = beacon.createAntidotes(testbeacon, boudnsX, boundsY);
                for(Antidote antidote : newAntidotes){
                    if(antidotes.stream().filter(a -> a.Overlaps(antidote)).findFirst().orElse(null) == null){
                        antidotes.add(antidote);
                    }
                }
            }
        }
        System.out.println(antidotes.size());

    }

    private List<Beacon> createBeacons(List<String> input){
        List<Beacon> beacons = new ArrayList<>();
        for(int i = 0; i< input.size(); i++){
            char[] line = input.get(i).toCharArray();
            for(int j = 0; j < input.get(i).length(); j++){
                char point = line[j];
                if(point != '.'){
                    beacons.add(new Beacon(point, i, j));
                }
            }
        }
        return beacons;
    }
}
