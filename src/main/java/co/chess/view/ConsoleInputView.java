package co.chess.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String receiveStartCommand() {
        System.out.println("""
            체스 게임을 시작합니다.
            게임 시작은 start, 종료는 end 명령을 입력하세요.
            """);
        return SCANNER.nextLine();
    }
}
