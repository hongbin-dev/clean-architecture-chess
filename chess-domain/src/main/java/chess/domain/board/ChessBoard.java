package chess.domain.board;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.domain.game.GameTurn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class ChessBoard {

	public static final int ROW_LENGTH = 8;
	public static final int COLUMN_LENGTH = 8;
	private static final int TEAM_LENGTH = 2;

	private final Map<Location, Piece> board;

	public ChessBoard(Map<Location, Piece> pieces) {
		Objects.requireNonNull(pieces, "피스 정보가 없습니다.");
		this.board = pieces;
	}

	public void move(Location now, Location destination) {
		validateLocation(now, destination);
		Piece piece = board.remove(now);
		board.put(destination, piece);
	}

	private void validateLocation(Location now, Location destination) {
		Piece piece = board.get(now);

		checkSameTeam(piece, destination);
		if (piece.isNotJumper()) {
			checkObstacle(now, destination);
		}
		piece.checkStrategy(now, destination, existEnemyInLocation(destination, piece));
	}

	private boolean existEnemyInLocation(Location location, Piece piece) {
		boolean destinationEnemy = false;
		if (board.containsKey(location)) {
			destinationEnemy = board.get(location).isNotSameTeam(piece);
		}
		return destinationEnemy;
	}

	private void checkObstacle(Location now, Location destination) {
		boolean obstacle = false;
		// 중간경로를 탐색하는 객체 구현
		Location nextLocation = now.calculateNextLocation(destination, 1);
		for (int weight = 2; weight <= 8 && nextLocation != destination; weight++) {
			if (board.containsKey(nextLocation)) {
				obstacle = true;
				break;
			}
			nextLocation = now.calculateNextLocation(destination, weight);
		}
		if (obstacle) {
			throw new IllegalArgumentException("경로 사이에 피스가 있습니다.");
		}
	}

	private void checkSameTeam(Piece piece, Location destination) {
		if (!board.containsKey(destination)) {
			return;
		}

		Piece other = board.get(destination);
		if (piece.isSameTeam(other)) {
			throw new IllegalArgumentException("같은 팀으로 이동했습니다.");
		}
	}

	public Map<Location, Piece> giveMyPiece(Team team) {
		return board.keySet().stream()
			.filter(location -> board.get(location).isSameTeam(team))
			.collect(Collectors.toMap(location -> location, board::get));
	}

	private boolean hasTwoKings(Map<Location, Piece> pieces) {
		long kingCount = pieces.values().stream()
			.filter(Piece::isKing)
			.count();

		return kingCount == 2;
	}

	public boolean hasTwoKings() {
		return hasTwoKings(board);
	}

	public boolean isNotPiece(Location now) {
		return !board.containsKey(now);
	}

	public boolean isNotSameTeam(GameTurn gameTurn, Location now) {
		Piece startingPiece = board.get(now);
		return !startingPiece.isSameTeam(gameTurn.getTeam());
	}

	public Map<Location, Piece> getBoard() {
		return board;
	}

	public Team findWinner() {
		List<Piece> kings = board.values()
			.stream()
			.filter(Piece::isKing)
			.toList();

		if (kings.size() == TEAM_LENGTH) {
			throw new IllegalArgumentException("게임이 종료되지 않았습니다.");
		}

		if (kings.get(0).isSameTeam(Team.BLACK)) {
			return Team.BLACK;
		}
		return Team.WHITE;
	}
}