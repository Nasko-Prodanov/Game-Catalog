import java.io.*;
import java.util.*;

public class GameFileDatabase {

    private static final String FILE_PATH = "C:\\Users\\HP\\Desktop\\project 18\\Game.txt";

    public List<Game> loadGames() throws IOException {
        List<Game> games = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", ");
            String name = parts[0].split(":")[1];
            String genre = parts[1].split(":")[1];
            double cpu = Double.parseDouble(parts[2].split(":")[1]);
            int ram = Integer.parseInt(parts[3].split(":")[1]);
            String multiplayer = parts[4].split(":")[1];
            int available = Integer.parseInt(parts[5].split(":")[1]);
            double price = Double.parseDouble(parts[6].split(":")[1]);

            Game game = new Game(name, genre, cpu, ram, multiplayer, available, price);
            games.add(game);
        }
        reader.close();
        return games;
    }
}

