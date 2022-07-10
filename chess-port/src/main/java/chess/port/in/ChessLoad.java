package chess.port.in;

import chess.port.in.dto.LoadResponse;

public interface ChessLoad {
	LoadResponse load(Long gameId);
}
