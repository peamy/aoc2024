package Objects;

public class Tile {
    public String icon = "";
    public boolean IsWall;
    public boolean Left;
    public boolean Right;
    public boolean Up;
    public boolean Down;

    public int Value(){
        return Left || Right || Up || Down ? 1 : 0;
    }

    public void PlaceGuard(String guard){
        icon = guard;
    }

    public String toString(){
        if(IsWall){
            return icon;
        }
        if(!icon.isEmpty()) {
            String toret = icon;
            icon = "";
            return toret;
        }
        boolean horizontal = Left || Right;
        boolean vertical = Up || Down;
        if(horizontal && vertical){
            return "+";
        }
        else if(horizontal){
            return "-";
        }
        else if(vertical){
            return "|";
        }
        return ".";

    }
}
