package chess.port.out;

import chess.domain.game.ChessGame;

public interface ChessPersistPort {
	Long save(ChessGame chessGame);

	ChessGame findByGameId(Long gameId);

	void update(Long gameId, ChessGame chessGame);
}
