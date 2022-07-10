package chess.domain.result;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.domain.board.Location;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public record ChessCalculator(Map<Location, Piece> board) {

	public List<GameStatistic> calculate() {
		Score blackTeamScore = calculateScore(Team.BLACK);
		Score whiteTeamScore = calculateScore(Team.WHITE);

		return Stream.of(
			new GameStatistic(Team.BLACK, blackTeamScore, GameResult.findResult(blackTeamScore, whiteTeamScore)),
			new GameStatistic(Team.WHITE, whiteTeamScore, GameResult.findResult(whiteTeamScore, blackTeamScore))
		).toList();
	}

	private Score calculateScore(Team team) {
		Map<Location, Piece> teamPieces = findBy(team);

		List<Location> pawnLocations = teamPieces.keySet().stream()
			.filter(location -> board.get(location).isPawn())
			.collect(Collectors.toList());

		return new Score(new ArrayList<>(teamPieces.values()), countingHalfScore(pawnLocations));
	}

	private Map<Location, Piece> findBy(Team team) {
		return board.entrySet().stream()
			.filter(pieceEntry -> pieceEntry.getValue().isSameTeam(team))
			.collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	private int countingHalfScore(List<Location> sameTeamPawns) {
		return (int)sameTeamPawns.stream()
			.filter(it -> isSameColumn(sameTeamPawns, it))
			.count();
	}

	private boolean isSameColumn(List<Location> sameTeamPawns, Location location) {
		return sameTeamPawns.stream()
			.filter(it -> it.isSameColumn(location))
			.limit(2)
			.count() == 2;
	}
}
