package chess.port.in;

import chess.port.in.dto.MoveRequest;

public interface ChessMove {
	void move(Long gameId, MoveRequest moveRequest);
}
