package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.game.GameState;

public record JdbcGameStateDao(ConnectionManager connectionManager) implements GameStateDao {

	public void saveBy(Long roomId, GameState gameState) {
		String query = "INSERT INTO gamestate(room_id, message) VALUES (?, ?)";

		try (Connection connection = connectionManager.getConnection(); PreparedStatement pstmt = connection.prepareStatement(
			query)) {

			pstmt.setLong(1, roomId);
			pstmt.setString(2, gameState.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public GameState findGameState(Long roomId) {
		String query = "SELECT * FROM gamestate WHERE room_id = ?";

		try (Connection connection = connectionManager.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setString(1, String.valueOf(roomId));

			try (ResultSet resultSet = statement.executeQuery()) {
				String message = resultSet.getString("message");
				return GameState.of(message);
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void updateMessage(Long roomId, GameState gameState) {
		String query = "UPDATE gamestate SET message = ? WHERE room_id = ?";

		try (Connection connection = connectionManager.getConnection();
		     PreparedStatement pstmt = connection.prepareStatement(query)) {

			pstmt.setString(1, gameState.toString());
			pstmt.setLong(2, roomId);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void delete(Long roomId) {
		String query = "DELETE from gamestate WHERE room_id = ?";

		try (Connection connection = connectionManager.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setLong(1, roomId);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}
}