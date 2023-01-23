package ru.netology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStore {
    private List<Game> games = new ArrayList<>();

    /**
     * Информация о том, какой игрок сколько играл в игры этого каталога
     * Ключ - имя игрока
     * Значение - суммарное количество часов в игры этого каталога
     */
    private Map<String, Integer> playedTime = new HashMap<>();

    /**
     * Создание объекта игры с заданными заголовком и жанром
     * Каждый объект игры помнит объект каталога, которому она принадлежит
     */
    public Game publishGame(String title, String genre) {
        if (containsGame(new Game(title, genre, this)) != true) {
            Game game = new Game(title, genre, this);
            games.add(game);
            return game;
        } else {
            throw new RuntimeException("Игра " + new Game(title, genre, this) + " уже добавлена");
        }
    }

    /**
     * Проверяет наличие игры в каталоге и возврашает true
     * если игра есть и false иначе
     */
    public boolean containsGame(Game game) { // чинить счетчик (i = 0; i > games.size(); i++) { if (games.get(i).equals(game))
        if (games.contains(game)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Регистрирует количество времени, которое проиграл игрок
     * за игрой этого каталога. Игрок задаётся по имени. Время должно
     * суммироваться с прошлым значением для этого игрока
     */
    public void addPlayTime(String playerName, int hours) {
        if (hours < 0) {
            throw new RuntimeException("Значение " + hours + " не может быть отрицательным");
        } else {
            if (playedTime.containsKey(playerName)) {
                int playTime = playedTime.get(playerName);
                int newPlayTime = playTime + hours;
                playedTime.put(playerName, newPlayTime);
            } else {
                playedTime.put(playerName, hours);
            }
        }
    }

    /**
     * Ищет имя игрока, который играл в игры этого каталога больше всего
     * времени. Если игроков нет, то возвращется null
     */
    public String getMostPlayer() {
        int mostTime = 1;
        String bestPlayer = null;
        for (String playerName : playedTime.keySet()) {
            int playerTime = playedTime.get(playerName);
            if (playerTime > mostTime) {
                mostTime = playerTime;
                bestPlayer = playerName;
            }
        }
        return bestPlayer;
    }

    /**
     * Суммирует общее количество времени всех игроков, проведённого
     * за играми этого каталога
     */
    public int getSumPlayedTime() {
        int sumTime = 0;
        for (int i : playedTime.values()) {
            sumTime += i;
        }
        return sumTime;
    }
}
