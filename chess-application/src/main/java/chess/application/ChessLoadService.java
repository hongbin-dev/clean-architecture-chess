package chess.application;

import org.springframework.stereotype.Service;

import chess.port.in.ChessLoad;
import chess.port.in.dto.LoadResponse;
import chess.port.out.ChessPersistPort;
import chess.domain.game.ChessGame;

@Service
public class ChessLoadService implements ChessLoad {

	private final ChessPersistPort chessPersistPort;

	public ChessLoadService(ChessPersistPort chessPersistPort) {
		this.chessPersistPort = chessPersistPort;
	}

	@Override
	public LoadResponse load(Long gameId) {
		ChessGame chessGame = chessPersistPort.findByGameId(gameId);

		return LoadResponse.of(chessGame);
	};
}
