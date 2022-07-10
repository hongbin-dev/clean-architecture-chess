package chess.application;

import java.util.List;

import org.springframework.stereotype.Service;

import chess.port.in.ChessStatus;
import chess.port.in.dto.StatusResponse;
import chess.port.out.ChessPersistPort;
import chess.domain.result.GameStatistic;

@Service
public class ChessStatusService implements ChessStatus {

	private final ChessPersistPort chessPersistPort;

	public ChessStatusService(ChessPersistPort chessPersistPort) {
		this.chessPersistPort = chessPersistPort;
	}

	@Override
	public StatusResponse status(Long gameId) {
		var chessGame = chessPersistPort.findByGameId(gameId);

		List<GameStatistic> statistics = chessGame.createStatistics();
		return new StatusResponse(statistics);
	}
}
