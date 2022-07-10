package domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.ChessBoard;
import chess.domain.board.Location;
import chess.domain.game.GameTurn;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;

class ChessBoardTest {

	private Map<Location, Piece> pieces;

	@BeforeEach
	void setUp() {
		pieces = new HashMap<>();
		pieces.put(Location.of('d', 1), King.of(Team.BLACK));
		pieces.put(Location.of('d', 2), King.of(Team.WHITE));
	}

	@DisplayName("같은팀으로 이동하는 경우 Exception")
	@Test
	void canMove() {
		Location starting = Location.of('a', 1);
		Location destination = Location.of('a', 2);
		pieces.put(starting, Queen.of(Team.BLACK));
		pieces.put(destination, Queen.of(Team.BLACK));

		ChessBoard chessBoard = new ChessBoard(pieces);
		assertThatThrownBy(() -> chessBoard.move(starting, destination)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("체스보드에서 내 팀의 정보만 가져오는 기능")
	@Test
	void testGiveMyPiece() {
		Location bottomLocation = Location.of('a', 1);
		Location topLocation = Location.of('a', 2);
		pieces.put(bottomLocation, Queen.of(Team.BLACK));
		pieces.put(topLocation, Queen.of(Team.BLACK));

		Map<Location, Piece> expect = new HashMap<>();
		Location actualBottomLocation = Location.of('a', 1);
		Location actualTopLocation = Location.of('a', 2);
		expect.put(actualBottomLocation, Queen.of(Team.BLACK));
		expect.put(actualTopLocation, Queen.of(Team.BLACK));
		expect.put(Location.of('d', 1), King.of(Team.BLACK));

		ChessBoard chessBoard = new ChessBoard(pieces);
		Map<Location, Piece> actual = chessBoard.giveMyPiece(Team.BLACK);

		assertThat(actual).isEqualTo(expect);
	}

	@DisplayName("체스 보드에 왕이 2개 있는지 확인")
	@Test
	void hasTwoKings() {
		ChessBoard chessBoard = new ChessBoard(pieces);
		boolean actual = chessBoard.hasTwoKings();
		assertThat(actual).isTrue();
	}

	@DisplayName("체스보드에서 이동할 피스가 다른팀인지 확인")
	@Test
	void isTurn() {
		Map<Location, Piece> board = new HashMap<>();
		Location movingLocation = Location.of('a', 1);
		pieces.put(movingLocation, Queen.of(Team.WHITE));

		ChessBoard chessBoard = new ChessBoard(pieces);
		boolean actual = chessBoard.isNotSameTeam(GameTurn.RUNNING_BLACK_TURN, movingLocation);

		assertThat(actual).isTrue();
	}

}