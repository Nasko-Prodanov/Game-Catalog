import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Стартиране на приложението в Event Dispatch Thread (за правилно обновление на GUI)
        SwingUtilities.invokeLater(() -> {
            // Създаване и показване на основния прозорец на приложението
            GameCatalogApp app = new GameCatalogApp();
            app.setVisible(true);
        });
    }
}