package Classes;

public class Map {
    private int rows;
    private int columns;
    private Character[][] map;


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

    public void setMap(Enemy[][] map) {
        this.map = map;
    }

    public void setEnemy(Enemy enemy, int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            this.map[row][column] = enemy;
        } else {
            System.out.println("Position invalide !");
        }
    }

    public void setHero(Hero hero, int row, int column) {
        // Si la position précédente est différente de la nouvelle position, effacer l'ancienne position
        if (hero.posPrec()[0] != row || hero.posPrec()[1] != column) {
            this.map[hero.posPrec()[0]][hero.posPrec()[1]] = null; // Effacer la position précédente
        }

        // Si la case cible est vide, on déplace le héros
        if (map[row][column] == null) {
            this.map[row][column] = hero;
            hero.setX(row); // Mettre à jour les coordonnées du héros
            hero.setY(column);
        } else {
            System.out.println("Position déjà occupée, mouvement annulé !");
        }
    }






    public void printMap() {
        String horizontalBorder = " " + "-".repeat(columns * 4 + 1);

        System.out.println(horizontalBorder);

        for (int i = 0; i < rows; i++) {
            StringBuilder rowBuilder = new StringBuilder("|");
            String cellContent;

            for (int j = 0; j < columns; j++) {
                if (map[i][j] == null) {
                    cellContent = "   ";  // Case vide
                } else if (map[i][j] instanceof Hero) {
                    Hero hero = (Hero) map[i][j];  // Cast pour accéder aux informations du héros
                    cellContent = " " + hero.getName().charAt(0) + " ";  // Affichage du héros par son premier caractère
                } else {
                    cellContent = " E ";  // Affichage d'un ennemi ou autre objet
                }

                rowBuilder.append(cellContent).append("|");
            }

            System.out.println(rowBuilder);
            System.out.println(horizontalBorder);
        }
    }


}

