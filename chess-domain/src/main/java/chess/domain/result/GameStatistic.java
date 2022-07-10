package chess.domain.result;

import java.util.Objects;

import chess.domain.piece.Team;

public class GameStatistic {
	private final Team team;
	private final Score score;
	private final GameResult gameResult;

	public GameStatistic(Team team, Score score, GameResult gameResult) {
		Objects.requireNonNull(score, "스코어의 정보가 없습니다.");
		this.team = team;
		this.score = score;
		this.gameResult = gameResult;
	}

	public String getTeam() {
		return team.getName();
	}

	public double getScore() {
		return score.getAmount();
	}

	public String getGameResult() {
		return gameResult.getMessage();
	}
}
