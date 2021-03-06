package chess.domain.piece.stategy;

import java.util.Objects;
import chess.domain.board.Location;
import chess.domain.piece.Team;

public abstract class MoveStrategy {
	protected final Team team;

	protected MoveStrategy(Team team) {
		this.team = team;
	}

	public void checkMove(Location now, Location destination, boolean destinationHasEnemy) {
		checkRange(now, destination);
		checkStrategy(now, destination, destinationHasEnemy);
	}

	abstract void checkRange(Location now, Location destination);

	abstract void checkStrategy(Location now, Location destination,
		boolean destinationHasEnemy);

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MoveStrategy that = (MoveStrategy)o;
		return team == that.team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(team);
	}
}