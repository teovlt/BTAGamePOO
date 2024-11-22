package Classes;

public enum Difficulty {
    EASY(5, 3), // Petite carte et peu de monstres
    MEDIUM(7, 5), // Carte moyenne et nombre moyen de monstres
    HARD(10, 8); // Grande carte et plus de monstres

    private final int mapSize; // Taille de la carte (n x n)
    private final int enemyCount; // Nombre de monstres

    Difficulty(int mapSize, int enemyCount) {
        this.mapSize = mapSize;
        this.enemyCount = enemyCount;
    }

    public int getMapSize() {
        return mapSize;
    }

    public int getEnemyCount() {
        return enemyCount;
    }
}
