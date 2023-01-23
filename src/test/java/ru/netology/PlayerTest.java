package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    // другие ваши тесты

    @Test
    public void playShouldThrowException() { // метод play должен выкидывать Exception т.к. ира не установлена
        Player player = new Player("Kolya");
        GameStore store = new GameStore();

        Assertions.assertThrows(RuntimeException.class, () -> player.play(new Game("1", "2", store), 10));
    }

//    @Test
//    public void shouldInstallGame() { //метод installGame получает в параметре новую игру
//        Player player = new Player("Petya");
//        GameStore store = new GameStore();
//        Game game = new Game("Игра 1", "Симулятор", store);
//        player.installGame(game);
//
//        Map<Game, Integer> expected = new HashMap<>();
//        expected.put(game, 0);
//
//        Map<Game, Integer> actual = player.getPlayedTime(); // БАГ РЕПОРТ - нет возможности просмотреть коллекцию
//
//        Assertions.assertEquals(expected, actual);
//    }

//    @Test
//    public void shouldNotInstallGame() { //метод installGame получает в параметре уже установленную игру
//        Player player = new Player("Petya");
//        GameStore store = new GameStore();
//        Game game = new Game("Игра 1", "Симулятор", store);
//        player.installGame(game);
//        player.play(game, 3);
//        player.installGame(game);
//
//        Map<Game, Integer> expected = new HashMap<>();
//        expected.put(game, 3);
//
//        Map<Game, Integer> actual = player.getPlayedTime();
//
//        Assertions.assertEquals(expected, actual);
//    }

    @Test
    public void shouldCountNewGameHours() { //метод play записывает информацию о количестве сыгранных в новую игру часов
        Player player = new Player("Petya");
        GameStore store = new GameStore();
        Game game = new Game("Игра 1", "Симулятор", store);
        player.installGame(game);

        int expected = 3;
        int actual = player.play(game, 3); // БАГ РЕПОРТ - метод play не записывает количество сыгранных часов

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddGameHours() { //метод play добавляет сыгранные часы к ранее сыгранной игре
        Player player = new Player("Petya");
        GameStore store = new GameStore();
        Game game = new Game("Игра 1", "Симулятор", store);
        player.installGame(game);
        player.play(game, 3);

        int expected = 6;
        int actual = player.play(game, 3); // проверить исправится ли после исправления метода play по Issue#

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCountGenreHours() { //* метод sumGenre показывает время, проведённое в играх заданного в параметре жанра
        Player player = new Player("Petya");
        GameStore store = new GameStore();
        Game game = new Game("Игра 1", "Симулятор", store);
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre("Симулятор");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnZeroGenreHours() { //метод sumGenre возвращает 0, т.к. в игры заданного жанра не играли
        Player player = new Player("Petya");
        GameStore store = new GameStore();
        Game game = new Game("Игра 1", "Симулятор", store);
        player.installGame(game);
        player.play(game, 3);

        int expected = 0;
        int actual = player.sumGenre("Стратегия");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMostPLayed() { //метод mostPlayedByGenre возвращает игру
        Player player = new Player("Petya");
        GameStore store = new GameStore();
        Game game = new Game("Игра 1", "Симулятор", store);
        player.installGame(game);
        player.play(game, 3);

        Game expected = game;
        Game actual = player.mostPlayerByGenre("Симулятор");

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnNull() {  //метод mostPlayedByGenre возвращает null
        Player player = new Player("Petya");
        GameStore store = new GameStore();
        Game game = new Game("Игра 1", "Симулятор", store);
        player.installGame(game);
        player.play(game, 3);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Стратегия");

        Assertions.assertEquals(expected,actual);
    }
}
