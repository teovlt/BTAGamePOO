package Classes;

public class Map {
    private int rows;
    private int columns;
    private Character[][] map;

    private static final String EMPTY_CELL = "   ";
    private static final String ENEMY_CELL = " E ";
    private static final String HERO_PREFIX = " ";

    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Character[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Character[][] getMap() {
        return map;
    }

    public void setMap(Character[][] map) {
        this.map = map;
    }

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

    public Enemy getEnemy(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (map[row][column] instanceof Enemy) {
                return (Enemy) map[row][column];
            }
        }
        return null; // Aucun ennemi trouvé
    }

    public void clearEnemy(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (map[row][column] instanceof Enemy) {
                map[row][column] = null; // Supprime l'ennemi de la case
            }
        }
    }

    public void setHero(Hero hero, int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            // Si le héros se déplace, effacer l'ancienne position
            if (hero.posPrec()[0] != row || hero.posPrec()[1] != column) {
                clearHero(hero.posPrec()[0], hero.posPrec()[1]);
            }

            // Vérifier la case cible
            if (map[row][column] == null) {
                map[row][column] = hero; // Placer le héros
                hero.setX(row);
                hero.setY(column);
            } else if (map[row][column] instanceof Enemy) {
                // System.out.println("Un ennemi est présent à cette position !");
                // Vous pouvez ajouter ici une logique pour gérer le combat
            } else {
                // System.out.println("Position déjà occupée par un autre objet !");
            }
        } else {
            System.out.println("Position invalide !");
        }
    }

    public void clearHero(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (map[row][column] instanceof Hero) {
                map[row][column] = null; // Supprime le héros de la case
            }
        }
    }

    public void printMap() {
        String horizontalBorder = " " + "-".repeat(columns * 4 + 1);

        System.out.println(horizontalBorder);

        for (int i = 0; i < rows; i++) {
            StringBuilder rowBuilder = new StringBuilder("|");

            for (int j = 0; j < columns; j++) {
                String cellContent;
                if (map[i][j] == null) {
                    cellContent = EMPTY_CELL; // Case vide
                } else if (map[i][j] instanceof Hero) {
                    cellContent = HERO_PREFIX + ((Hero) map[i][j]).getName().charAt(0) + HERO_PREFIX; // Héros
                } else {
                    cellContent = ENEMY_CELL; // Ennemi
                }

                rowBuilder.append(cellContent).append("|");
            }

            System.out.println(rowBuilder);
            System.out.println(horizontalBorder);
        }
    }
}
