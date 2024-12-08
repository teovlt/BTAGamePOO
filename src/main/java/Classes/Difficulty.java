package Classes;

/**
 * Enumération représentant les niveaux de difficulté d'un jeu.
 *
 * <p>
 * Chaque niveau de difficulté est défini par la taille de la carte (mapSize)
 * et le nombre d'ennemis (enemyCount). Ces valeurs influencent le gameplay
 * en modifiant la taille du terrain et le nombre d'adversaires à affronter.
 * </p>
 */
public enum Difficulty {

    /**
     * Niveau de difficulté facile.
     * <p>Caractéristiques :</p>
     * <ul>
     *   <li>Taille de la carte : 5x5</li>
     *   <li>Nombre d'ennemis : 3</li>
     * </ul>
     */
    EASY(5, 3), // Petite carte et peu de monstres

    /**
     * Niveau de difficulté moyen.
     * <p>Caractéristiques :</p>
     * <ul>
     *   <li>Taille de la carte : 7x7</li>
     *   <li>Nombre d'ennemis : 5</li>
     * </ul>
     */
    MEDIUM(7, 5), // Carte moyenne et nombre moyen de monstres

    /**
     * Niveau de difficulté difficile.
     * <p>Caractéristiques :</p>
     * <ul>
     *   <li>Taille de la carte : 10x10</li>
     *   <li>Nombre d'ennemis : 8</li>
     * </ul>
     */
    HARD(10, 8); // Grande carte et plus de monstres

    /**
     * Taille de la carte (n x n).
     */
    private final int mapSize;

    /**
     * Nombre de monstres sur la carte.
     */
    private final int enemyCount;

    /**
     * Constructeur de l'énumération {@code Difficulty}.
     *
     * @param mapSize    la taille de la carte (n x n)
     * @param enemyCount le nombre de monstres sur la carte
     */
    Difficulty(int mapSize, int enemyCount) {
        this.mapSize = mapSize;
        this.enemyCount = enemyCount;
    }

    /**
     * Retourne la taille de la carte (n x n) associée à ce niveau de difficulté.
     *
     * @return la taille de la carte
     */
    public int getMapSize() {
        return mapSize;
    }

    /**
     * Retourne le nombre de monstres associé à ce niveau de difficulté.
     *
     * @return le nombre de monstres
     */
    public int getEnemyCount() {
        return enemyCount;
    }
}
