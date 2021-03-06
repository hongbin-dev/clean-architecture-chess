package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.domain.piece.stategy.KingMoveStrategy;

public class King extends Piece {
	private static final char name = 'k';
	private static final double score = 0;

	public King(Team team) {
		super(team, editName(name, team), new KingMoveStrategy(team));
	}

	public static King of(Team team) {
		King king = KingCache.kingCache.get(team);
		Objects.requireNonNull(king, "킹이 존재하지 않습니다.");
		return king;
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	public boolean isNotJumper() {
		return true;
	}

	private static class KingCache {
		private static Map<Team, King> kingCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				kingCache.put(team, new King(team));
			}
		}
	}
}
