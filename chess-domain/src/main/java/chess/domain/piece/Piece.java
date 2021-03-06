package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.domain.board.Location;
import chess.domain.piece.stategy.MoveStrategy;

public abstract class Piece {
	private final static Map<String, Piece> pieceCache = new HashMap<>();

	protected final Team team;
	protected final char name;
	protected final MoveStrategy moveStrategy;

	public Piece(Team team, char name, MoveStrategy moveStrategy) {
		this.team = team;
		this.name = name;
		this.moveStrategy = moveStrategy;
	}

	public static Piece createOnePiece(String name, Team team) {
		pieceCache.put("p", Pawn.of(team));
		pieceCache.put("k", King.of(team));
		pieceCache.put("r", Rook.of(team));
		pieceCache.put("q", Queen.of(team));
		pieceCache.put("n", Knight.of(team));
		pieceCache.put("b", Bishop.of(team));
		return pieceCache.get(name.toLowerCase());
	}

	protected static char editName(char c, Team team) {
		if (Team.BLACK == team) {
			return Character.toUpperCase(c);
		}
		return c;
	}

	public abstract double getScore();

	public abstract boolean isNotJumper();

	public boolean isSameTeam(Team team) {
		return team == this.team;
	}

	public boolean isSameTeam(Piece piece) {
		Objects.requireNonNull(piece, "piece가 존재하지않습니다.");
		return piece.team == this.team;
	}

	public void checkStrategy(Location now, Location destination, boolean destinationLocationEnemy) {
		moveStrategy.checkMove(now, destination, destinationLocationEnemy);
	}

	public boolean isNotSameTeam(Piece piece) {
		return !isSameTeam(piece);
	}

	public boolean isPawn() {
		return this instanceof Pawn;
	}

	public boolean isKing() {
		return this instanceof King;
	}

	public char getName() {
		return name;
	}

	public Team getTeam() {
		return team;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return name == piece.name &&
			team == piece.team &&
			Objects.equals(moveStrategy, piece.moveStrategy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(team, name, moveStrategy);
	}
}
