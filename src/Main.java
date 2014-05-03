
public class Main {
    public static void main(String[] args) {
        GameOfLife gol = new GameOfLifeMatrixImpl(5,5);
        gol.setCellStatus(2,2,1);
        gol.setCellStatus(2,3,1);
        gol.setCellStatus(2,4,1);
        gol.setCellStatus(3,4,1);

        gol.printCurrentState();

        gol.nextGeneration();

        System.out.println();
        System.out.println("-------------");
        System.out.println();
        gol.printCurrentState();
    }
}