package controller;

import controller.command.FirstCommand;
import controller.command.RunningCommand;
import domain.game.ChessGame;
import domain.board.Location;
import domain.game.GameState;
import domain.piece.PieceFactory;
import view.InputView;
import view.OutputView;

public class ChessController {

	public void run() {
		var chessGame = new ChessGame(new PieceFactory().createPieces(), GameState.RUNNING_WHITE_TURN);

		start(chessGame);
		while (chessGame.isRunning()) {
			running(chessGame);
		}
	}

	private void start(ChessGame chessGame) {
		OutputView.printInformation();

		FirstCommand firstCommand = readFirstCommand();
		if (firstCommand == FirstCommand.END) {
			chessGame.end();
			return;
		}

		OutputView.printBoard(chessGame);
	}

	private FirstCommand readFirstCommand() {
		try {
			return FirstCommand.of(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return readFirstCommand();
		}
	}

	private void running(ChessGame chessGame) {
		var runningCommand = readRunningCommand();

		if (runningCommand.isMove()) {
			move(runningCommand.getNowLocation(), runningCommand.getDestinationLocation(), chessGame);
		}
		if (runningCommand.isStatus()) {
			status(chessGame);
		}
		if (readRunningCommand().isEnd()) {
			chessGame.end();
		}
	}

	private void move(String nowLocation, String destinationLocation, ChessGame chessGame) {
		try {
			chessGame.movePiece(Location.of(nowLocation), Location.of(destinationLocation));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			running(chessGame);
			return;
		}
		OutputView.printBoard(chessGame);
	}

	private void status(ChessGame chessGame) {
		OutputView.printStatus(chessGame.createStatistics());
	}

	private RunningCommand readRunningCommand() {
		try {
			return RunningCommand.from(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return readRunningCommand();
		}
	}
}
