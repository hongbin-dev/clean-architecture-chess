package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.domain.board.Location;

public class PieceFactory {
	public static Optional<Piece> of(char symbol) {
		var team = Team.of(Character.isUpperCase(symbol));

		if (Character.toLowerCase(symbol) == 'k') {
			return Optional.of(King.of(team));
		}
		if (Character.toLowerCase(symbol) == 'n') {
			return Optional.of(Knight.of(team));
		}
		if (Character.toLowerCase(symbol) == 'r') {
			return Optional.of(Rook.of(team));
		}
		if (Character.toLowerCase(symbol) == 'b') {
			return Optional.of(Bishop.of(team));
		}
		if (Character.toLowerCase(symbol) == 'q') {
			return Optional.of(Queen.of(team));
		}
		if (Character.toLowerCase(symbol) == 'p') {
			return Optional.of(Pawn.of(team));
		}
		return Optional.empty();
	}

	public Map<Location, Piece> createPieces() {
		Map<Location, Piece> pieces = new HashMap<>();

		putNoble(pieces, 1, Team.WHITE);
		putPawns(pieces, 2, Team.WHITE);

		putPawns(pieces, 7, Team.BLACK);
		putNoble(pieces, 8, Team.BLACK);

		return pieces;
	}

	private void putPawns(Map<Location, Piece> pieces, int row, Team team) {
		for (int i = 0; i < 8; i++) {
			pieces.put(Location.of((char)(i + 'a'), row), Pawn.of(team));
		}
	}

	private void putNoble(Map<Location, Piece> pieces, int row, Team team) {
		pieces.put(Location.of('a', row), Rook.of(team));
		pieces.put(Location.of('b', row), Knight.of(team));
		pieces.put(Location.of('c', row), Bishop.of(team));
		pieces.put(Location.of('d', row), Queen.of(team));
		pieces.put(Location.of('e', row), King.of(team));
		pieces.put(Location.of('f', row), Bishop.of(team));
		pieces.put(Location.of('g', row), Knight.of(team));
		pieces.put(Location.of('h', row), Rook.of(team));
	}
}
