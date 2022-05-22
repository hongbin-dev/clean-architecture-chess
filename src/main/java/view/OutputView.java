package view;

import java.util.List;
import java.util.Map;

import domain.GameManager;
import domain.board.Location;
import domain.piece.Piece;
import domain.result.GameStatistic;

public class OutputView {

	private static final int MAXIMUM_BOARD_SIZE = 8;
	private static final char FIRST_COLUMN_VALUE = 'a';
	private static final String EMPTY_SHAPE = ".";

	public static void printBoard(GameManager gameManager) {
		Map<Location, Piece> board = gameManager.getBoard();

		for (int row = 0; row < MAXIMUM_BOARD_SIZE; row++) {
			for (int col = 0; col < MAXIMUM_BOARD_SIZE; col++) {
				Location reverseLocation = reverseLocation(row, col);
				System.out.print(findPieceOrDefault(board, reverseLocation));
			}
			System.out.println();
		}
	}

	private static Location reverseLocation(int row, int col) {
		int reversedRow = MAXIMUM_BOARD_SIZE - row;
		char reversedCol = (char)(FIRST_COLUMN_VALUE + col);
		String location = String.format("%c%d", reversedCol, reversedRow);
		return Location.of(location);
	}

	private static String findPieceOrDefault(Map<Location, Piece> board, Location target) {
		String value = EMPTY_SHAPE;
		if (board.containsKey(target)) {
			Piece piece = board.get(target);
			value = piece.toString();
		}
		return value;
	}

	public static void printInformation() {
		System.out.println("> 체스 게임을 시작합니다.\n"
			+ "> 게임 시작 : start\n"
			+ "> 게임 종료 : end\n"
			+ "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printStatus(List<GameStatistic> teamScore) {
		System.out.println("결과출력");
		for (GameStatistic gameStatistic : teamScore) {
			System.out.println(
				String.format("%s : %1.1f - %s",
					gameStatistic.getTeam(),
					gameStatistic.getScore(),
					gameStatistic.getGameResult()
				)
			);
		}
	}


	public static void printMessage(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void printWinner(GameManager gameManager) {
		System.out.println(String.format("승자는 %s입니다!", gameManager.findWinner()));
	}
}