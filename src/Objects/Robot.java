package Objects;

public class Robot {
    public Robot(int x, int y, int vx, int vy, int bx, int by){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.bx = bx;
        this.by = by;
    }

    public int x, y, vx, vy, bx, by;

    public void moveSeconds(int seconds){
        x = x+(seconds*vx);
        y = y+(seconds*vy);

        fixPosition();
    }

    public void fixPosition(){
        if(x < 0){
            x = bx-(-1*x%bx);
        }
        x = x%bx;

        if(y < 0){
            y = by-(-1*y%by);
        }
        y = y%by;
    }
}
