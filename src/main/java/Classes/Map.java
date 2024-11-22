package Classes;

public class Map {
    private int rows;
    private int columns;
    private Enemy[][] map;

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
        String horizontalBorder = " " + "-".repeat(columns * 4 + 1);

        System.out.println(horizontalBorder);

        for (int i = 0; i < rows; i++) {
            StringBuilder rowBuilder = new StringBuilder("|");

            for (int j = 0; j < columns; j++) {
                String cellContent = (map[i][j] == null) ? "   " : " E ";
                rowBuilder.append(cellContent).append("|");
            }

            System.out.println(rowBuilder);
            System.out.println(horizontalBorder);
        }
    }
}
