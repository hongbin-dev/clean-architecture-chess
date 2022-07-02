package domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.board.Location;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.Team;

class ChessCalculatorTest {

	// abcdefgh
	// .KR.....  8
	// P.PB....  7
	// .P..Q...  6
	// ........  5
	// .....nq.  4
	// .....p.p  3
	// .....pp.  2
	// ....rk..  1
	@DisplayName("결과 계산")
	@ParameterizedTest()
	@MethodSource("providerBoard")
	void calculateScore(Map<Location, Piece> board) {
		var chessCalculator = new ChessCalculator(board);
		var gameStatistics = chessCalculator.calculate();


		GameStatistic black = gameStatistics.stream()
			.filter(it -> it.getTeam().equalsIgnoreCase("black"))
			.findAny()
			.get();

		GameStatistic white = gameStatistics.stream()
			.filter(it -> it.getTeam().equalsIgnoreCase("white"))
			.findAny()
			.get();

		assertThat(black.getScore()).isEqualTo(20);
		assertThat(white.getScore()).isEqualTo(19.5);
	}

	private static Stream<Arguments> providerBoard() {
		return Stream.of(
			Arguments.of(
				new HashMap<Location, Piece>() {{
					put(Location.of("b8"), King.of(Team.BLACK));
					put(Location.of("c8"), Rook.of(Team.BLACK));
					put(Location.of("a7"), Pawn.of(Team.BLACK));
					put(Location.of("c7"), Pawn.of(Team.BLACK));
					put(Location.of("d7"), Bishop.of(Team.BLACK));
					put(Location.of("b6"), Pawn.of(Team.BLACK));
					put(Location.of("e6"), Queen.of(Team.BLACK));
					put(Location.of("e1"), Rook.of(Team.WHITE));
					put(Location.of("f1"), King.of(Team.WHITE));
					put(Location.of("f2"), Pawn.of(Team.WHITE));
					put(Location.of("g2"), Pawn.of(Team.WHITE));
					put(Location.of("f3"), Pawn.of(Team.WHITE));
					put(Location.of("h3"), Pawn.of(Team.WHITE));
					put(Location.of("f4"), Knight.of(Team.WHITE));
					put(Location.of("g4"), Queen.of(Team.WHITE));
				}}
			)
		);
	}
}