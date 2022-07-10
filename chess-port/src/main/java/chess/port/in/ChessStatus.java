package chess.port.in;

import chess.port.in.dto.StatusResponse;

public interface ChessStatus {
	StatusResponse status(Long gameId);
}
