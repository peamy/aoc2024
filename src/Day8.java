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
//        printMap(beacons, antidotes, boudnsX, boundsY);
        System.out.println(antidotes.size());

    }

    private void printMap(List<Beacon> beacons, List<Antidote> antidotes, int x, int y){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                int antiCount = countAntidotes(antidotes, i, j);
                Beacon beacon = getBeacon(beacons, i, j);
                if(antiCount > 0){
                    builder.append(antiCount + "");
                }
                else if(beacon != null && false){
                    builder.append(beacon.type);
                }
                else {
                    builder.append(".");
                }
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    private int countAntidotes(List<Antidote> antidotes, int i, int j){
        return (int) antidotes.stream().filter(a -> a.X()==i && a.Y()==j).count();
    }

    private Beacon getBeacon(List<Beacon> beacons, int x, int y){
       return beacons.stream().filter(b -> b.x == x && b.y == y).findFirst().orElse(null);
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
