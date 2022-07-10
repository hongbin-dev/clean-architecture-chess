package chess.exception;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(Long gameId) {
		super("찾을 수 없는 엔티티 입니다. id=" + gameId);
	}
}
