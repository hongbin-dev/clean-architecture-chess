package chess.domain.piece.stategy;

import java.util.List;

import chess.domain.board.Location;
import chess.domain.piece.Team;

public class BishopMoveStrategy extends MoveStrategy {
	private final List<Direction> bishopDirection = Direction.DIAGONAL_DIRECTION;

	public BishopMoveStrategy(Team team) {
		super(team);
	}

	@Override
	public void checkRange(Location now, Location destination) {
		if (!now.validEverySpaceRange(destination, bishopDirection)) {
			throw new IllegalArgumentException("유효하지 않는 범위를 입력했습니다.");
		}
	}

	@Override
	void checkStrategy(Location now, Location destination, boolean destinationHasEnemy) {

	}
}
