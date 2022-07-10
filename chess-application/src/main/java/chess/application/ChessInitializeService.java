package chess.application;

import org.springframework.stereotype.Service;

import chess.port.in.ChessInitialize;
import chess.port.out.ChessPersistPort;
import chess.domain.game.ChessGame;
import chess.domain.game.GameTurn;
import chess.domain.piece.PieceFactory;

@Service
public class ChessInitializeService implements ChessInitialize {

	private final ChessPersistPort chessPersistPort;

	public ChessInitializeService(ChessPersistPort chessPersistPort) {
		this.chessPersistPort = chessPersistPort;
	}

	@Override
	public Long init() {
		var chessGame = new ChessGame(new PieceFactory().createPieces(), GameTurn.RUNNING_WHITE_TURN);

		return chessPersistPort.save(chessGame);
	}
}
