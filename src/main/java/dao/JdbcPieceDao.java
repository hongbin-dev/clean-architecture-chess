package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domain.board.Location;
import domain.piece.Piece;
import domain.piece.Team;

public record JdbcPieceDao(ConnectionManager connectionManager) implements PieceDao {

	public void saveBy(Long roomId, Map<Location, Piece> mapper) {
		String query = "INSERT INTO piece(room_id, location, name, team) VALUES (?, ?, ?, ?)";

		try (var connection = connectionManager.getConnection(); var statement = connection.prepareStatement(query)) {

			for (Location location : mapper.keySet()) {
				Piece piece = mapper.get(location);
				statement.setLong(1, roomId);
				statement.setString(2, location.toString());
				statement.setString(3, String.valueOf(piece.getName()));
				statement.setString(4, piece.getTeam().getName());

				statement.addBatch();
			}
			statement.executeBatch();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void deletePiece(Long roomId, Location location) {
		String query = "DELETE FROM piece WHERE room_id = ? AND location = ?";

		try (Connection connection = connectionManager.getConnection(); PreparedStatement pstmt = connection.prepareStatement(
			query)) {
			pstmt.setLong(1, roomId);
			pstmt.setString(2, location.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Map<Location, Piece> findAll(Long roomId) {
		String query = "SELECT * FROM piece WHERE room_id = ?";

		try (Connection connection = connectionManager.getConnection();
		     PreparedStatement pstmt = connection.prepareStatement(query)) {

			pstmt.setLong(1, roomId);

			try (ResultSet rs = pstmt.executeQuery()) {
				Map<Location, Piece> result = new HashMap<>();

				while (rs.next()) {
					String name = rs.getString("name");
					Team team = Team.of(rs.getString("team"));
					Location location = Location.of(rs.getString("location"));

					result.put(location, Piece.createOnePiece(name, team));
				}
				return result;
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void updateLocation(Long roomId, Location now, Location destination) {
		String query = "UPDATE piece SET location = ? WHERE room_id = ? AND location = ?";

		try (Connection connection = connectionManager.getConnection();
		     PreparedStatement pstmt = connection.prepareStatement(query)) {

			pstmt.setString(1, destination.toString());
			pstmt.setLong(2, roomId);
			pstmt.setString(3, now.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void deleteAll(Long roomId) {
		String query = "DELETE FROM piece WHERE room_id = ?";

		try (Connection connection = connectionManager.getConnection(); PreparedStatement pstmt = connection.prepareStatement(
			query)) {
			pstmt.setLong(1, roomId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
