package chess.infrastructure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import chess.domain.game.GameTurn;

@Entity
public class ChessGameEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String board;

	private String turn;

	public ChessGameEntity() {
	}

	public ChessGameEntity(String board, String turn) {
		this.board = board;
		this.turn = turn;
	}

	public Long getId() {
		return id;
	}

	public String getBoard() {
		return board;
	}

	public String getTurn() {
		return turn;
	}

	public void updateBoard(String board, GameTurn gameTurn) {
		this.board = board;
		this.turn = gameTurn.name();
	}
}
