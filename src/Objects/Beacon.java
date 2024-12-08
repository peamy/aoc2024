package Objects;

import java.util.ArrayList;
import java.util.List;

public class Beacon {
    public Beacon(char point, int i, int j) {
        this.type = point;
        this.x = j;
        this.y = i;
    }

    public char type;
    public int x;
    public int y;

    public Antidote createsAntidote(Beacon beacon, int boundsX, int boundsY){
        if(beacon.type != this.type || beacon.equals(this)){
            return null;
        }

        int antidoteX = this.x + (this.x - beacon.x);
        int antidoteY = this.y + (this.y - beacon.y);

//        System.out.println(type + " " + x + " " + y);
//        System.out.println(beacon.type + " " + beacon.x + " " + beacon.y);
//        System.out.println("@ " + antidoteX + " " + antidoteY);

        if(antidoteX < 0 || antidoteX >= boundsX || antidoteY < 0 || antidoteY >= boundsY){
            return null;
        }

        return new Antidote(antidoteX, antidoteY);
    }

    public List<Antidote> createAntidotes(Beacon beacon, int boundsX, int boundsY){
        List<Antidote> antidotes = new ArrayList<>();

        if(beacon.type != this.type || beacon.equals(this)){
            return antidotes;
        }


        int dirX = this.x - beacon.x;
        int dirY = this.y - beacon.y;

        int newAntidoteX = this.x;
        int newAntidoteY = this.y;


        while(newAntidoteX >= 0 && newAntidoteX < boundsX && newAntidoteY >= 0 && newAntidoteY < boundsY){
            antidotes.add(new Antidote(newAntidoteX, newAntidoteY));
            newAntidoteX += dirX;
            newAntidoteY += dirY;
        }

//        System.out.println(type + " " + x + " " + y);
//        System.out.println(beacon.type + " " + beacon.x + " " + beacon.y);
//        System.out.println("@ " + antidoteX + " " + antidoteY);

        return antidotes;
    }
}
