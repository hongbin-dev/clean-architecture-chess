package controller;

import domain.GameManager;
import domain.board.Location;
import domain.gamestate.GameState;
import domain.piece.PieceFactory;
import controller.command.FirstCommand;
import controller.command.RunningCommand;
import view.InputView;
import view.OutputView;

public class ChessController {

	private final GameManager gameManage = new GameManager(new PieceFactory().createPieces(), GameState.RUNNING_WHITE_TURN);

	public void run() {
		start();
		running();
		end();
	}

	private void start() {
		OutputView.printInformation();

		FirstCommand firstCommand = readFirstCommand();
		if (firstCommand == FirstCommand.END) {
			end();
		}

		OutputView.printBoard(gameManage);
	}

	private FirstCommand readFirstCommand() {
		try {
			return FirstCommand.of(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return readFirstCommand();
		}
	}

	private void running() {
		RunningCommand runningCommand;
		do {
			runningCommand = readRunningCommand();
			if (runningCommand.isMove()) {
				move(runningCommand.getNowLocation(), runningCommand.getDestinationLocation());
			}
			if (runningCommand.isStatus()) {
				status();
			}
		} while (gameManage.isRunning() && runningCommand.isNotEnd());
	}

	private void move(String nowLocation, String destinationLocation) {
		try {
			gameManage.movePiece(Location.of(nowLocation), Location.of(destinationLocation));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			running();
			return;
		}
		OutputView.printBoard(gameManage);
	}

	private void status() {
		OutputView.printStatus(gameManage.createStatistics());
	}

	private RunningCommand readRunningCommand() {
		try {
			return RunningCommand.from(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return readRunningCommand();
		}
	}

	private void end() {
		System.exit(1);
	}
}
