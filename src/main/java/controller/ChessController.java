package controller;

import java.util.Map;

import controller.command.RunningCommand;
import dao.GameStateDao;
import dao.PieceDao;
import domain.board.Location;
import domain.game.ChessGame;
import domain.game.GameState;
import domain.piece.Piece;
import domain.piece.PieceFactory;
import view.InputView;
import view.OutputView;

public record ChessController(PieceDao pieceDao, GameStateDao gameStateDao) {

	public void run() {
		var chessGame = selectInitialize();

		while (chessGame.isRunning()) {
			running(chessGame);
		}
	}

	private ChessGame selectInitialize() {
		String command = InputView.inputCommand("게임을 불러오려면 load, 새로 시작하시려면 init을 선택해주세요.");

		return switch (command.toLowerCase()) {
			case "load" -> load();
			case "init" -> init();
			default -> throw new IllegalArgumentException("잘못된 입력입니다. " + command);
		};
	}

	public ChessGame init() {
		pieceDao.deleteAll(1L);
		gameStateDao.delete(1L);
		gameStateDao.saveBy(1L, GameState.RUNNING_WHITE_TURN);
		pieceDao.saveBy(1L, new PieceFactory().createPieces());

		return new ChessGame(new PieceFactory().createPieces(), GameState.RUNNING_WHITE_TURN);
	}

	public ChessGame load() {
		Map<Location, Piece> all = pieceDao.findAll(1L);
		GameState gameState = gameStateDao.findGameState(1L);
		return new ChessGame(all, gameState);
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

	private RunningCommand readRunningCommand() {
		return RunningCommand.from(InputView.inputCommand());
	}

	public void move(String nowLocation, String destinationLocation, ChessGame chessGame) {
		try {
			chessGame.movePiece(Location.of(nowLocation), Location.of(destinationLocation));
			pieceDao.deletePiece(1L, Location.of(destinationLocation));
			pieceDao.updateLocation(1L, Location.of(nowLocation), Location.of(destinationLocation));
			gameStateDao.updateMessage(1L, chessGame.getGameState());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		OutputView.printBoard(chessGame);
	}

	public void status(ChessGame chessGame) {
		OutputView.printStatus(chessGame.createStatistics());
	}
}
