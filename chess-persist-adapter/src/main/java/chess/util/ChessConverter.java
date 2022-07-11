package chess.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.board.Column;
import chess.domain.board.Location;
import chess.domain.board.Row;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

public class ChessConverter {

	public static String toDatabase(ChessGame chessGame) {
		var board = chessGame.getBoard();

		List<String> results = new ArrayList<>();
		for (int i = 8; i >= 1; i--) {
			var row = Row.of(i);
			StringBuilder stringBuilder = new StringBuilder();
			for (int j = 0; j < 8; j++) {
				var column = Column.of((char)('a' + j));
				var location = Location.of(column, row);

				var piece = board.get(location);

				if (piece == null) {
					stringBuilder.append(".");
				} else {
					stringBuilder.append(piece.getName());
				}
			}
			results.add(stringBuilder.toString());
		}

		var result = String.join("\n", results);
		System.out.println(result);

		return result;
	}

	public static Map<Location, Piece> toDomain(String board) {
		Map<Location, Piece> pieces = new HashMap<>();

		var lines = board.split("\n");
		for (int i = 0; i < 8; i++) {
			var line = lines[i];
			var row = Row.of(8 - i);

			for (int j = 0; j < 8; j++) {
				var column = Column.of((char)('a' + j));
				var location = Location.of(column, row);

				var symbol = line.charAt(j);
				var piece = PieceFactory.of(symbol);

				piece.ifPresent(p -> pieces.put(location, p));
			}
		}

		return pieces;
	}
}
