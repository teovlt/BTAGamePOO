package Classes;

public class Map {
    int rows;
    int columns;
    Enemy[][] map;

    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Enemy[rows][columns];
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

    public Enemy[][] getMap() {
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

    public void printMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

}
