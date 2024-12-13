package Objects;

import java.util.List;

public class Clawmachine {

    public Clawmachine(List<String> input, boolean isPart2){
        buttonAX = Integer.parseInt(input.get(0).split("\\+")[1].split(",")[0]);
        buttonAY = Integer.parseInt(input.get(0).split("\\+")[2]);
        buttonBX = Integer.parseInt(input.get(1).split("\\+")[1].split(",")[0]);
        buttonBY = Integer.parseInt(input.get(1).split("\\+")[2]);
        long addition = isPart2 ? 10000000000000L : 0L;
        prizeX = Long.parseLong(input.get(2).split("=")[1].split(",")[0]) + addition;
        prizeY = Long.parseLong(input.get(2).split("=")[2]) + addition;
    }

    public final long prizeX;
    public final long prizeY;

    public final int buttonAX;
    public final int buttonAY;
    public final int buttonBX;
    public final int buttonBY;

    private long currentX;
    private long currentY;
    public int timesAWasPressed;
    public int timesBWasPressed;

    public void reset(){
        currentY = 0;
        currentX = 0;
        timesBWasPressed = 0;
        timesAWasPressed = 0;
    }

    public void pressA(long amount){
        currentY += amount*buttonAY;
        currentX += amount*buttonAX;
        timesAWasPressed++;
    }

    public void pressB(long amount){
        currentY += amount*buttonBY;
        currentX += amount*buttonBX;
        timesBWasPressed++;
    }

    public boolean won(){
        return currentX == prizeX && currentY == prizeY;
    }

    public long calculateB(){
        return ((buttonAY*prizeX) - (buttonAX*prizeY)) / (((long) buttonAY *buttonBX)-((long) buttonAX *buttonBY));
    }

}
