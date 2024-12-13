package Objects;

import java.util.List;

public class Clawmachine {

    public Clawmachine(List<String> input){
        buttonAX = Integer.parseInt(input.get(0).split("\\+")[1].split(",")[0]);
        buttonAY = Integer.parseInt(input.get(0).split("\\+")[2]);
        buttonBX = Integer.parseInt(input.get(1).split("\\+")[1].split(",")[0]);
        buttonBY = Integer.parseInt(input.get(1).split("\\+")[2]);
        prizeX = Integer.parseInt(input.get(2).split("=")[1].split(",")[0]);
        prizeY = Integer.parseInt(input.get(2).split("=")[2]);
    }

    public Clawmachine(int prizex, int prizey, int ax, int ay, int bx, int by){
        prizeX = prizex;
        prizeY = prizey;
        buttonAX = ax;
        buttonAY = ay;
        buttonBX = bx;
        buttonBY = by;
    }
    public final int prizeX;
    public final int prizeY;

    public final int buttonAX;
    public final int buttonAY;
    public final int buttonBX;
    public final int buttonBY;

    private int currentX;
    private int currentY;
    public int timesAWasPressed;
    public int timesBWasPressed;

    public void reset(){
        currentY = 0;
        currentX = 0;
        timesBWasPressed = 0;
        timesAWasPressed = 0;
    }

    public void pressA(int amount){
        currentY += amount*buttonAY;
        currentX += amount*buttonAX;
        timesAWasPressed++;
    }

    public void pressB(int amount){
        currentY += amount*buttonBY;
        currentX += amount*buttonBX;
        timesBWasPressed++;
    }

    public boolean won(){
        return currentX == prizeX && currentY == prizeY;
    }

}
