package chess.domain.piece.stategy;

import java.util.List;

import chess.domain.board.Location;
import chess.domain.piece.Team;

public class QueenMoveStrategy extends MoveStrategy {
	private final List<Direction> queenDirection = Direction.EVERY_DIRECTION;

	public QueenMoveStrategy(Team team) {
		super(team);
	}

	@Override
	public void checkRange(Location now, Location destination) {
		if (!now.validEverySpaceRange(destination, queenDirection)) {
			throw new IllegalArgumentException("유효하지 않는 범위를 입력했습니다.");
		}
	}

	@Override
	void checkStrategy(Location now, Location destination, boolean destinationHasEnemy) {

	}
}
