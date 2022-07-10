package chess;

import java.util.Map;

import org.springframework.stereotype.Service;

import chess.exception.EntityNotFoundException;
import chess.infrastructure.ChessGameDao;
import chess.infrastructure.ChessGameEntity;
import chess.port.out.ChessPersistPort;
import chess.domain.game.ChessGame;
import chess.domain.game.GameTurn;

@Service
public class ChessPersistAdapter implements ChessPersistPort {

	private final ChessGameDao chessGameDao;

	public ChessPersistAdapter(ChessGameDao chessGameDao) {
		this.chessGameDao = chessGameDao;
	}

	@Override
	public Long save(ChessGame chessGame) {
		var chessGameEntity = new ChessGameEntity(chessGame.toString(), chessGame.getGameTurn().name());

		return chessGameDao.save(chessGameEntity)
			.getId();
	}

	@Override
	public ChessGame findByGameId(Long gameId) {
		var chessGameEntity = chessGameDao.findById(gameId)
			.orElseThrow(() -> new EntityNotFoundException(gameId));

		// TODO
		return new ChessGame(Map.of(), GameTurn.valueOf(chessGameEntity.getTurn()));
	}

	@Override
	public void update(Long gameId, ChessGame chessGame) {
		var chessGameEntity = chessGameDao.findById(gameId)
			.orElseThrow(() -> new EntityNotFoundException(gameId));

		// TODO
		chessGameEntity.updateBoard(chessGame.getBoard().toString(), chessGame.getGameTurn());
	}
}
