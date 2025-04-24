public class Game {
    private String name;
    private String genre;
    private double cpuRequirement;
    private int ramRequirement;
    private String multiplayer;
    private int available;
    private double price;

    public Game(String name, String genre, double cpuRequirement, int ramRequirement, String multiplayer, int available, double price) {
        this.name = name;
        this.genre = genre;
        this.cpuRequirement = cpuRequirement;
        this.ramRequirement = ramRequirement;
        this.multiplayer = multiplayer;
        this.available = available;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public double getCpuRequirement() {
        return cpuRequirement;
    }

    public int getRamRequirement() {
        return ramRequirement;
    }

    public String getMultiplayer() {
        return multiplayer;
    }

    public int getAvailable() {
        return available;
    }

    public double getPrice() {
        return price;
    }
}
