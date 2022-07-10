package chess.application;

import org.springframework.stereotype.Service;

import chess.port.in.ChessMove;
import chess.port.in.dto.MoveRequest;
import chess.port.out.ChessPersistPort;

@Service
public class ChessMoveService implements ChessMove {

	private final ChessPersistPort chessPersistPort;

	public ChessMoveService(ChessPersistPort chessPersistPort) {
		this.chessPersistPort = chessPersistPort;
	}

	@Override
	public void move(Long gameId, MoveRequest moveRequest) {
		var chessGame = chessPersistPort.findByGameId(gameId);

		chessGame.movePiece(moveRequest.getSource(), moveRequest.getTarget());
		chessPersistPort.update(gameId, chessGame);
	}
}
