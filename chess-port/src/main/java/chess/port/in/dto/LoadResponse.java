package chess.port.in.dto;

import static java.util.stream.Collectors.*;

import java.util.Map;

import chess.domain.board.Location;
import chess.domain.game.ChessGame;

public class LoadResponse {

	// TODO 맵 제거 가능한지?
	private final Map<String, String> data;

	public LoadResponse(Map<String, String> data) {
		this.data = data;
	}

	public static LoadResponse of(ChessGame chessGame) {
		var response = chessGame.getBoard().entrySet().stream()
			.map(it -> Map.entry(toString(it.getKey()), it.getValue().getName()))
			.map(it -> Map.entry(it.getKey(), String.valueOf(it.getValue())))
			.collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

		return new LoadResponse(response);
	}

	private static String toString(Location location) {
		return String.valueOf(location.getColumn() + location.getRow());
	}
}
