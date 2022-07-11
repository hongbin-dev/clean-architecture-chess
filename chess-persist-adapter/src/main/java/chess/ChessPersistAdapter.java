package chess;

import static chess.util.ChessConverter.*;

import org.springframework.stereotype.Service;

import chess.domain.game.ChessGame;
import chess.domain.game.GameTurn;
import chess.exception.EntityNotFoundException;
import chess.infrastructure.ChessGameDao;
import chess.infrastructure.ChessGameEntity;
import chess.port.out.ChessPersistPort;

@Service
public class ChessPersistAdapter implements ChessPersistPort {

	private final ChessGameDao chessGameDao;

	public ChessPersistAdapter(ChessGameDao chessGameDao) {
		this.chessGameDao = chessGameDao;
	}

	@Override
	public Long save(ChessGame chessGame) {
		var chessGameEntity = new ChessGameEntity(toDatabase(chessGame), chessGame.getGameTurn().name());

		return chessGameDao.save(chessGameEntity)
			.getId();
	}

	@Override
	public ChessGame findByGameId(Long gameId) {
		var chessGameEntity = chessGameDao.findById(gameId)
			.orElseThrow(() -> new EntityNotFoundException(gameId));

		return new ChessGame(toDomain(chessGameEntity.getBoard()), GameTurn.valueOf(chessGameEntity.getTurn()));
	}

	@Override
	public void update(Long gameId, ChessGame chessGame) {
		var chessGameEntity = chessGameDao.findById(gameId)
			.orElseThrow(() -> new EntityNotFoundException(gameId));

		chessGameEntity.updateBoard(toDatabase(chessGame), chessGame.getGameTurn());
	}
}
