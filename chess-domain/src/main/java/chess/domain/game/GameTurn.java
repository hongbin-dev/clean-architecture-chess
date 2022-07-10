package chess.domain.game;

import java.util.Arrays;

import chess.domain.piece.Team;

public enum GameTurn {
	RUNNING_BLACK_TURN(true, Team.BLACK),
	RUNNING_WHITE_TURN(true, Team.WHITE),
	END(false);

	private final boolean gameRunning;
	private final Team team;

	GameTurn(boolean gameRunning, Team team) {
		this.gameRunning = gameRunning;
		this.team = team;
	}

	GameTurn(boolean gameRunning) {
		this.gameRunning = gameRunning;
		this.team = Team.BLACK;
	}
	
	public static GameTurn of(String message) {
		return Arrays.stream(values())
			.filter(gameState -> gameState.name().equals(message))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("해당 상태를 찾을 수 없습니다."));
	}

	public GameTurn of(boolean hasTwoKings) {
		return Arrays.stream(GameTurn.values())
			.filter(gameState -> gameState.gameRunning == hasTwoKings
				&& gameState != this)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("진행결과를 찾을 수 없습니다."));
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public Team getTeam() {
		return team;
	}
}
