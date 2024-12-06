package Objects;

public class Tile {
    public String icon = "";
    public boolean Left;
    public boolean Right;
    public boolean Up;
    public boolean Down;

    public String toString(){
        if(icon.equals("")){
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
        return icon;
    }
}
