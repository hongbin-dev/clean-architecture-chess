package co.chess.controller;

import java.util.Arrays;

import co.chess.exception.BusinessException;

public enum Command {
    START, END;

    public static Command of(String rawCommand) {
        return Arrays.stream(values())
            .filter(command -> command.isSame(rawCommand))
            .findAny()
            .orElseThrow(BusinessException::new);
    }

    private boolean isSame(String rawCommand) {
        return name().equalsIgnoreCase(rawCommand);
    }

    public boolean isRunnable() {
        return this == START;
    }
}
