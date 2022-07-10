package chess.port.in.dto;

public class MoveRequest {
	private String source;
	private String target;

	public MoveRequest() {
	}

	public MoveRequest(String source, String target) {
		this.source = source;
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}
}
