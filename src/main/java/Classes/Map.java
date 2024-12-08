package Classes;

/**
 * Représente une carte pour le jeu, où des personnages comme le héros et les ennemis peuvent être placés.
 * La carte est modélisée comme une grille de caractères.
 */
public class Map {
    private int rows; // Nombre de lignes dans la carte
    private int columns; // Nombre de colonnes dans la carte
    private Character[][] map; // Grille représentant les personnages et les cases

    private static final String EMPTY_CELL = "   "; // Représentation d'une case vide
    private static final String ENEMY_CELL = " E "; // Représentation d'une case contenant un ennemi
    private static final String HERO_PREFIX = " ";  // Préfixe et suffixe pour afficher un héros

    /**
     * Initialise une nouvelle carte avec le nombre de lignes et de colonnes spécifié.
     *
     * @param rows    le nombre de lignes de la carte
     * @param columns le nombre de colonnes de la carte
     */
    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Character[rows][columns];
    }

    /**
     * Obtient le nombre de lignes de la carte.
     *
     * @return le nombre de lignes
     */
    public int getRows() {
        return rows;
    }

    /**
     * Définit le nombre de lignes de la carte.
     *
     * @param rows le nouveau nombre de lignes
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Obtient le nombre de colonnes de la carte.
     *
     * @return le nombre de colonnes
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Définit le nombre de colonnes de la carte.
     *
     * @param columns le nouveau nombre de colonnes
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Obtient la grille de la carte.
     *
     * @return un tableau bidimensionnel représentant la carte
     */
    public Character[][] getMap() {
        return map;
    }

    /**
     * Définit une nouvelle grille pour la carte.
     *
     * @param map le nouveau tableau bidimensionnel de la carte
     */
    public void setMap(Character[][] map) {
        this.map = map;
    }

    /**
     * Place un ennemi sur une position donnée de la carte.
     *
     * @param enemy  l'ennemi à placer
     * @param row    la ligne de la position
     * @param column la colonne de la position
     */
    public void setEnemy(Enemy enemy, int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (map[row][column] == null) {
                this.map[row][column] = enemy;
            } else {
                System.out.println("Position déjà occupée par un autre objet !");
            }
        } else {
            System.out.println("Position invalide !");
        }
    }

    /**
     * Obtient un ennemi à une position donnée.
     *
     * @param row    la ligne de la position
     * @param column la colonne de la position
     * @return l'ennemi présent à cette position, ou {@code null} s'il n'y a pas d'ennemi
     */
    public Enemy getEnemy(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (map[row][column] instanceof Enemy) {
                return (Enemy) map[row][column];
            }
        }
        return null;
    }

    /**
     * Supprime un ennemi à une position donnée.
     *
     * @param row    la ligne de la position
     * @param column la colonne de la position
     */
    public void clearEnemy(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (map[row][column] instanceof Enemy) {
                map[row][column] = null;
            }
        }
    }

    /**
     * Place le héros sur une position donnée de la carte.
     *
     * @param hero   le héros à placer
     * @param row    la ligne de la position
     * @param column la colonne de la position
     */
    public void setHero(Hero hero, int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            // Nettoie la position précédente
            if (hero.posPrec()[0] != row || hero.posPrec()[1] != column) {
                clearHero(hero.posPrec()[0], hero.posPrec()[1]);
            }
            if (map[row][column] == null) {
                map[row][column] = hero;
                hero.setX(row);
                hero.setY(column);
            } else if (map[row][column] instanceof Enemy) {
                // Logique de combat possible ici
            }
        } else {
            System.out.println("Position invalide !");
        }
    }

    /**
     * Supprime le héros à une position donnée.
     *
     * @param row    la ligne de la position
     * @param column la colonne de la position
     */
    public void clearHero(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (map[row][column] instanceof Hero) {
                map[row][column] = null;
            }
        }
    }

    /**
     * Affiche la carte actuelle dans la console.
     */
    public void printMap() {
        String horizontalBorder = " " + "-".repeat(columns * 4 + 1);
        System.out.println(horizontalBorder);

        for (int i = 0; i < rows; i++) {
            StringBuilder rowBuilder = new StringBuilder("|");
            for (int j = 0; j < columns; j++) {
                String cellContent;
                if (map[i][j] == null) {
                    cellContent = EMPTY_CELL;
                } else if (map[i][j] instanceof Hero) {
                    cellContent = HERO_PREFIX + ((Hero) map[i][j]).getName().charAt(0) + HERO_PREFIX;
                } else {
                    cellContent = ENEMY_CELL;
                }
                rowBuilder.append(cellContent).append("|");
            }
            System.out.println(rowBuilder);
            System.out.println(horizontalBorder);
        }
    }
}
