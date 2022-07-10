package chess.port.in.dto;

import java.util.List;

import chess.domain.result.GameStatistic;

public class StatusResponse {

	private final List<GameStatistic> gameStatistics;

	public StatusResponse(List<GameStatistic> gameStatistics) {
		this.gameStatistics = gameStatistics;
	}

	public List<GameStatistic> getGameStatistics() {
		return gameStatistics;
	}
}
