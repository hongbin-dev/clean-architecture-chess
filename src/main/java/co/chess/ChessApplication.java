package co.chess;

import co.chess.controller.Controller;
import co.chess.view.ConsoleInputView;
import co.chess.view.ConsoleOutputView;

public class ChessApplication {

    public static void main(String[] args) {
        var controller = new Controller();
        controller.run(new ConsoleInputView(), new ConsoleOutputView());
    }
}
