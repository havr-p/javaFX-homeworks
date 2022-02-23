

public class Simulation {
    int[][] grid;
    int width, height;
    public static int DEAD = 0;
    public static int ALIVE = 1;

    public Simulation(int width, int height) {
        grid = new int[height][width];
        this.width = width;
        this.height = height;
    }

    public int getCell(int x, int y) {
        return grid[y][x];
    }

    public void setCell(int x, int y, int state) {
        grid[y][x] = state;
    }

    /**
     * Tato metoda zmaze obsah mriezky
     */

    public void clearGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = DEAD;
            }
        }
    }

    /**
     * Metoda, ktora vraca pocet zivych susedov bunky. Políčka na ľavom kraji mriežky považovať za susedov políčok na pravom kraji mriežkya podobne pre políčka na hornom a dolnom kraji mriežky.
     * Všetky políčka tak naozaj budú mať práve osem susedov.
     * Tento prístup možno chápať aj tak, že už ďalej nerobíme v rovine, ale na tore.
     * @param x suradnica x bunky (cislo stlpca)
     * @param y suradnica y bunky (cislo riadka)
     * @return pocet zivych susedov bunky
     */

    public int countNeighbours(int x, int y) {
        int count = 0;
        for (int dX = -1; dX <= 1; dX++) {
            for (int dY = -1; dY <= 1; dY++) {

                if (dX == 0 && dY == 0) continue;
                else {
                    if ((x == 0) && (y == 0)) {
                        count += getCell((width + dX) % width, (height + dY) % height);
                        //System.out.print((width + dX) % width + ", " + (height + dY) % height + "    END1    ");

                    } else if ((x > 0) && (x < width - 1) && (y == 0)) {
                        count += getCell(x + dX, (height + dY) % height);
                        // System.out.print(x + dX + ", " + (height + dY) % height + "    END2    ");

                    } else if ((x == width - 1) && (y == 0)) {
                        count += getCell((width - 1 + dX) % width, (height + dY) % height);
                        //System.out.print((width - 1 + dX) % width + ", " + (height + dY) % height + "    END3    ");

                    } else if ((x == 0) && (y > 0) && (y < height - 1)) {
                        count += getCell((width + dX) % width, y + dY);

                    } else if ((x > 0) && (x < width - 1) && (y > 0) && (y < height - 1)) {
                        count += getCell(x + dX, y + dY);

                    } else if ((x == width - 1) && (y > 0) && (y < height - 1)) {
                        count += getCell((width - 1 + dX) % width, y + dY);

                    } else if ((x == 0) && (y == height - 1)) {
                        count += getCell((width + dX) % width, (height + dY - 1) % height);

                    } else if ((x > 0) && (x < width - 1) && (y == height - 1)) {
                        count += getCell(x + dX, (height + dY - 1) % height);

                    } else if ((x == width - 1) && (y == height - 1)) {
                        count += getCell((width + dX - 1) % width, (height + dY - 1) % height);
                    }


                }
            }
        }
       // System.out.println("count of neighbours of (" + x + ", " + y + ") = " + count);
        return count;
    }

    /**
     * Tato metoda vypocita dalsiu konfiguraciu mriezky na zaklade aktualnej
     */

    public void nextStep() {
        int[][] newGrid = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == ALIVE) {
                    if ((countNeighbours(x, y) == 2) || (countNeighbours(x, y) == 3)) {
                        newGrid[y][x] = ALIVE;
                    } else {
                        newGrid[y][x] = DEAD;
                    }
                } else {
                    if (countNeighbours(x, y) == 3) {
                        newGrid[y][x] = ALIVE;
                    }
                }

            }
        }
        this.grid = newGrid;
    }

}

