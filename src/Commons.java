import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commons {
    public static List<String> readFileLines(String filename) {
        try {
            File directory = new File("./");
            String filePath = directory.getAbsolutePath() + "/src/Data/" + filename;
            return Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[][] readFileAsMap(String filename) {
        List<String> lines = readFileLines(filename);
        String[][] map = new String[lines.get(0).length()][lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for(int j = 0; j < line.length(); j++){
                map[i][j] = line.substring(j, j+1);
            }
        }
        return map;
    }

    public static int[][] readFileAsIntMap(String filename) {
        List<String> lines = readFileLines(filename);
        int[][] map = new int[lines.get(0).length()][lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for(int j = 0; j < line.length(); j++){
                map[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }
        return map;
    }

    public static void printMap(String[][] map){
        String result = "\n\n\n";
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                result+=map[i][j];
            }
            result+="\n";
        }
        System.out.println(result);
    }

    public static void printMap(int[][] map){
        String result = "\n\n\n";
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                result+=map[i][j];
            }
            result+="\n";
        }
        System.out.println(result);
    }

    public static void printMapHideZero(int[][] map){
        String result = "\n\n\n";
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                result+= map[i][j] == 0 ? "." : map[i][j];
            }
            result+="\n";
        }
        System.out.println(result);
    }

}
