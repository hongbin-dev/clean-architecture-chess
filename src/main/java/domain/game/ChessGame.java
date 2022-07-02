package domain.game;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import domain.result.ChessCalculator;
import domain.board.ChessBoard;
import domain.board.Location;
import domain.piece.Piece;
import domain.result.GameStatistic;
import domain.piece.Team;

public class ChessGame {
	private final ChessBoard chessBoard;
	private GameState gameState;

	public ChessGame(Map<Location, Piece> pieces, GameState gameState) {
		Objects.requireNonNull(pieces, "pieces의 정보가 없습니다.");
		this.chessBoard = new ChessBoard(pieces);
		this.gameState = gameState;
	}

	public boolean isRunning() {
		return gameState.isGameRunning();
	}

	public void movePiece(Location now, Location destination) {
		if (!isRunning()) {
			throw new IllegalArgumentException("게임이 종료되었습니다.");
		}
		checkStarting(now);
		checkTurn(now);
		chessBoard.move(now, destination);
		gameState = gameState.of(chessBoard.hasTwoKings());
	}

	private void checkStarting(Location now) {
		if (chessBoard.isNotPiece(now)) {
			throw new IllegalArgumentException("출발지가 빈칸입니다.");
		}
	}

	private void checkTurn(Location now) {
		if (chessBoard.isNotSameTeam(gameState, now)) {
			throw new IllegalArgumentException("해당 유저의 턴이 아닙니다.");
		}
	}

	public List<GameStatistic> createStatistics() {
		Map<Location, Piece> board = chessBoard.getBoard();
		var calculator = new ChessCalculator(board);

		return calculator.calculate();
	}

	public Team findWinner() {
		return chessBoard.findWinner();
	}

	public Map<Location, Piece> getBoard() {
		return chessBoard.getBoard();
	}

	public void end() {
		gameState = GameState.END;
	}
}
