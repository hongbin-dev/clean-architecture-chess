package chess.port.in.dto;

import java.util.List;

import chess.domain.result.GameStatistic;

public record StatusResponse(List<GameStatistic> gameStatistics) {

	public List<GameStatistic> getGameStatistics() {
		return gameStatistics;
	}
}
