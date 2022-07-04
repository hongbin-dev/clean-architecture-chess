package dao;

import domain.game.GameState;

public interface GameStateDao {
	void saveBy(Long roomId, GameState gameState);

	GameState findGameState(Long roomId);

	void updateMessage(Long roomId, GameState gameState);

	void delete(Long roomId);
}
