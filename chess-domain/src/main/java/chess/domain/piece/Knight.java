package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.domain.piece.stategy.KnightMoveStrategy;

public class Knight extends Piece {
	private static final char name = 'n';
	private static final double score = 2.5;

	private Knight(Team team) {
		super(team, editName(name, team), new KnightMoveStrategy(team));
	}

	public static Knight of(Team team) {
		Knight knight = KnightCache.knightCache.get(team);
		Objects.requireNonNull(knight, "나이트가 존재하지 않습니다.");
		return knight;
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	public boolean isNotJumper() {
		return false;
	}


	private static class KnightCache {
		private static Map<Team, Knight> knightCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				knightCache.put(team, new Knight(team));
			}
		}
	}
}
