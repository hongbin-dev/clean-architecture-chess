package dao;

import java.util.Map;

import domain.board.Location;
import domain.piece.Piece;

public interface PieceDao {
	void saveBy(Long roomId, Map<Location, Piece> mapper);

	void deletePiece(Long roomId, Location location);

	Map<Location, Piece> findAll(Long roomId);

	void updateLocation(Long roomId, Location now, Location destination);

	void deleteAll(Long roomId);
}
