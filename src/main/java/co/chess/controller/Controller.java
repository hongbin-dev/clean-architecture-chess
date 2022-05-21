package co.chess.controller;

import co.chess.view.InputView;
import co.chess.view.OutputView;

public class Controller {

    public void run(InputView inputView, OutputView outputView) {
        Command command = inputCommand(inputView);

        while (command.isRunnable()) {
            outputView.printBoard();

            command = inputCommand(inputView);
        }
    }

    private Command inputCommand(InputView inputView) {
        return Command.of(inputView.receiveStartCommand());
    }
}
