import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class GameCatalogApp extends JFrame {

    private GameFileDatabase gameFileDatabase;

    public GameCatalogApp() {
        gameFileDatabase = new GameFileDatabase();

        setTitle("Game Catalog");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Панел с бутони
        JPanel buttonPanel = new JPanel();
        JButton genreSummaryButton = new JButton("Genre Summary");
        JButton highestRequirementsButton = new JButton("Highest Requirements");
        JButton showAllGamesButton = new JButton("Show All Games");
        JButton helpButton = new JButton("Help");

        buttonPanel.add(genreSummaryButton);
        buttonPanel.add(highestRequirementsButton);
        buttonPanel.add(showAllGamesButton);
        buttonPanel.add(helpButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Панел за резултати
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Справка за наличния брой игри от всеки жанр и средната цена
        genreSummaryButton.addActionListener(e -> {
            try {
                List<Game> games = gameFileDatabase.loadGames();
                StringBuilder summary = new StringBuilder();

                // Групираме по жанр
                Map<String, List<Game>> genreGroups = games.stream()
                        .collect(Collectors.groupingBy(Game::getGenre));

                // За всеки жанр извеждаме брой игри и средна цена
                for (Map.Entry<String, List<Game>> entry : genreGroups.entrySet()) {
                    String genre = entry.getKey();
                    List<Game> genreGames = entry.getValue();

                    long count = genreGames.size();
                    double avgPrice = genreGames.stream()
                            .mapToDouble(Game::getPrice)
                            .average()
                            .orElse(0);

                    summary.append("Genre: ").append(genre)
                            .append(", Count: ").append(count)
                            .append(", Avg Price: ").append(avgPrice).append("\n");
                }
                resultArea.setText(summary.toString());
            } catch (IOException ex) {
                resultArea.setText("Error: " + ex.getMessage());
            }
        });

        // Справка за игрите с най-високи изисквания за процесор и памет
        highestRequirementsButton.addActionListener(e -> {
            try {
                List<Game> games = gameFileDatabase.loadGames();

                // Намираме максимални изисквания за процесор и памет
                double maxCpu = games.stream().mapToDouble(Game::getCpuRequirement).max().orElse(0);
                int maxRam = games.stream().mapToInt(Game::getRamRequirement).max().orElse(0);

                // Извеждаме игрите с най-високи изисквания
                StringBuilder highestGames = new StringBuilder();
                games.stream()
                        .filter(g -> g.getCpuRequirement() == maxCpu && g.getRamRequirement() == maxRam)
                        .forEach(g -> highestGames.append(g.getName()).append("\n"));

                resultArea.setText("Games with highest CPU and RAM requirements:\n" + highestGames);
            } catch (IOException ex) {
                resultArea.setText("Error: " + ex.getMessage());
            }
        });

        // Покажи всички игри от файла
        showAllGamesButton.addActionListener(e -> {
            try {
                List<Game> games = gameFileDatabase.loadGames();
                StringBuilder allGames = new StringBuilder();

                // Извеждаме информация за всички игри
                for (Game game : games) {
                    allGames.append("Game: ").append(game.getName())
                            .append(", Genre: ").append(game.getGenre())
                            .append(", CPU: ").append(game.getCpuRequirement())
                            .append(" GHz, RAM: ").append(game.getRamRequirement())
                            .append(" GB, Multiplayer: ").append(game.getMultiplayer())
                            .append(", Available: ").append(game.getAvailable())
                            .append(", Price: ").append(game.getPrice()).append(" BGN\n");
                }

                resultArea.setText(allGames.toString());
            } catch (IOException ex) {
                resultArea.setText("Error: " + ex.getMessage());
            }
        });

        // Добавяме бутон Help
        helpButton.addActionListener(e -> {
            // Отваря диалогов прозорец с обяснение за приложението
            String helpMessage = "Welcome to the Game Catalog!\n\n" +
                    "This application allows you to view and manage information about computer games.\n\n" +
                    "Here are the available actions:\n\n" +
                    "- Genre Summary: Shows the count and average price of games for each genre.\n" +
                    "- Highest Requirements: Shows games with the highest CPU and RAM requirements.\n" +
                    "- Show All Games: Displays all games in the catalog with details (name, genre, CPU, RAM, multiplayer, available quantity, and price).\n\n" +
                    "To use the application, simply click on the buttons in the interface and view the results in the text area.\n\n" +
                    "Enjoy browsing through your game catalog!";

            JOptionPane.showMessageDialog(this, helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}

