package co.chess.view;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printBoard() {
        System.out.println("""
            RNBQKBNR
            PPPPPPPP
            ........
            ........
            ........
            ........
            pppppppp
            rnbqkbnr
            """);
    }
}
