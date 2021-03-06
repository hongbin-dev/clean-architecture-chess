package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.domain.piece.stategy.PawnMoveStrategy;

public class Pawn extends Piece {
	private static final char name = 'p';
	private static final double score = 1;

	private Pawn(Team team) {
		super(team, editName(name, team), new PawnMoveStrategy(team));
	}

	public static Pawn of(Team team) {
		Pawn pawn = PawnCache.pawnCache.get(team);
		Objects.requireNonNull(pawn, "폰이 존재하지 않습니다.");
		return pawn;
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	public boolean isNotJumper() {
		return true;
	}

	private static class PawnCache {
		private static Map<Team, Pawn> pawnCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				pawnCache.put(team, new Pawn(team));
			}
		}
	}
}
