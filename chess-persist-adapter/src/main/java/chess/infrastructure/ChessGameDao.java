package chess.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChessGameDao extends JpaRepository<ChessGameEntity, Long> {
}
