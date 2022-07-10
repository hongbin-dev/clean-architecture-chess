package chess.domain.game;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.result.ChessCalculator;
import chess.domain.result.GameStatistic;
import chess.domain.board.ChessBoard;
import chess.domain.board.Location;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class ChessGame {
	private final ChessBoard chessBoard;
	private GameTurn gameTurn;

	public ChessGame(Map<Location, Piece> pieces, GameTurn gameTurn) {
		Objects.requireNonNull(pieces, "pieces의 정보가 없습니다.");
		this.chessBoard = new ChessBoard(pieces);
		this.gameTurn = gameTurn;
	}

	public boolean isRunning() {
		return gameTurn.isGameRunning();
	}

	public void movePiece(String source, String target) {
		movePiece(Location.of(source), Location.of(target));
	}

	public void movePiece(Location now, Location destination) {
		if (!isRunning()) {
			throw new IllegalArgumentException("게임이 종료되었습니다.");
		}
		checkStarting(now);
		checkTurn(now);
		chessBoard.move(now, destination);
		gameTurn = gameTurn.of(chessBoard.hasTwoKings());
	}

	private void checkStarting(Location now) {
		if (chessBoard.isNotPiece(now)) {
			throw new IllegalArgumentException("출발지가 빈칸입니다.");
		}
	}

	private void checkTurn(Location now) {
		if (chessBoard.isNotSameTeam(gameTurn, now)) {
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

	public GameTurn getGameTurn() {
		return gameTurn;
	}

	public void end() {
		gameTurn = GameTurn.END;
	}
}
