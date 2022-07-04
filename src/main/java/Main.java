import controller.ChessController;
import dao.ConnectionManager;
import dao.JdbcGameStateDao;
import dao.JdbcPieceDao;

public class Main {

	public static void main(String[] args) {
		ConnectionManager connectionManager = new ConnectionManager() {
		};
		new ChessController(new JdbcPieceDao(connectionManager), new JdbcGameStateDao(connectionManager))
			.run();
	}
}
