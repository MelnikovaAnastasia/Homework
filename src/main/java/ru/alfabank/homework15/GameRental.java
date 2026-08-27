package ru.alfabank.homework15;

import java.util.Map;
import java.util.TreeMap;

public class GameRental {

    private final Map<String, BoardGame> gameCatalog = new TreeMap<>();

    public void addGame(BoardGame boardGame) {
        if (boardGame == null) {
            throw new IllegalArgumentException("Игра должна существовать для добавления!");
        }
        String name = boardGame.getName();
        if (gameCatalog.containsKey(name)) {
            throw new IllegalArgumentException("Игра с таким названием уже существует!");
        }
        gameCatalog.put(name, boardGame);

    }

    public BoardGame findGame(String name) {
        return gameCatalog.get(name);
    }

    public boolean rentGame(String name, int customerAge) {
        BoardGame boardGame = findGame(name);
        if (boardGame == null) {
            throw new IllegalArgumentException("Указанная игра не найдена, попробуйте изменить запрос.");
        }
        if (!boardGame.canBeRentedBy(customerAge)) return false;
        if (boardGame.isRented()) return false;
        boardGame.setRented(true);
        return true;
    }

    public boolean returnGame(String name) {
        BoardGame boardGame = findGame(name);
        if (boardGame == null) return false;
        if (!boardGame.isRented()) return false;
        boardGame.setRented(false);
        return true;
    }

    public int calculateCost(String name, int days) {
        BoardGame boardGame = findGame(name);
        if (boardGame == null) {
            throw new IllegalArgumentException("Указанная игра не найдена, попробуйте изменить запрос.");
        }
        if (days <= 0 ) {
            throw new IllegalArgumentException("Количество дней аренды должно быть больше 0.");
        }
        return boardGame.getRentalPrice() * days;
    }

    public void reset() {
        for (BoardGame boardGame : gameCatalog.values()) {
            boardGame.setRented(false);
        }
    }
}
