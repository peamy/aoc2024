package Objects;

public record Antidote(int X, int Y) {

    public boolean Overlaps(Antidote antidote){
        return this.Y == antidote.Y && this.X == antidote.X;
    }

}

