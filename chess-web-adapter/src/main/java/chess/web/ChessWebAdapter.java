package chess.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chess.port.in.ChessInitialize;
import chess.port.in.ChessLoad;
import chess.port.in.ChessMove;
import chess.port.in.ChessStatus;
import chess.port.in.dto.LoadResponse;
import chess.port.in.dto.MoveRequest;
import chess.port.in.dto.StatusResponse;

@RestController
@RequestMapping("/api/chessGame")
public class ChessWebAdapter {

	private final ChessInitialize chessInitialize;
	private final ChessLoad chessLoad;
	private final ChessMove chessMove;
	private final ChessStatus chessStatus;

	public ChessWebAdapter(ChessInitialize chessInitialize, ChessLoad chessLoad, ChessMove chessMove,
		ChessStatus chessStatus) {
		this.chessInitialize = chessInitialize;
		this.chessLoad = chessLoad;
		this.chessMove = chessMove;
		this.chessStatus = chessStatus;
	}

	@PostMapping
	public ResponseEntity<Void> init() {
		Long id = chessInitialize.init();
		return ResponseEntity.created(URI.create("/api/chessGame/" + id))
			.build();
	}

	@GetMapping("/{gameId}")
	public ResponseEntity<LoadResponse> load(@PathVariable Long gameId) {
		LoadResponse response = chessLoad.load(gameId);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/{gameId}")
	public ResponseEntity<Void> move(@PathVariable Long gameId, MoveRequest moveRequest) {
		chessMove.move(gameId, moveRequest);
		return ResponseEntity.ok()
			.build();
	}

	@GetMapping("/{gameId}/status")
	public ResponseEntity<StatusResponse> status(@PathVariable Long gameId) {
		StatusResponse response = chessStatus.status(gameId);
		return ResponseEntity.ok(response);
	}
}
