package Objects;

public class Block {
    public Block(int size, int fileId, int startPosition, boolean isFile){
        this.size = size;
        this.startPosition = startPosition;
        this.isFile = isFile;
        this.fileId = isFile ? fileId : -1;
    }

    public int size;
    public int fileId;
    public int startPosition;
    public boolean isFile;
    public boolean canMove = true;

    public String ToString(){
        if(!isFile){
            return "emtpy " + size;
        }
        return fileId + " " + size;
    }
}