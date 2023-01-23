package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

        GameStore store = new GameStore();

    //тесты на метод containsGame

    @Test
    public void shouldAddGame() { // должен хранить игры в каталоге, даже одну
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldNotContainNewGameNoGames() {

        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(store.containsGame(game));
    }

    // тесты на метод publishGame
    @Test
    public void shouldNotDuplicateGame(){ // не должен создавать дублирующие объекты
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game2));
    }

    // тесты на метод addPlayTime
    @Test
    public void shouldSumTimeForExistingPlayer() { // должен добавлять время уже существующему игроку
        store.addPlayTime("Kolya", 5);
        store.addPlayTime("Petya", 6);
        store.addPlayTime("Kolya", 2);
        store.addPlayTime("Anya", 4);
        assertEquals("Kolya", store.getMostPlayer());
    }

    @Test
    public void couldAddNegativeOrNullTime() {// должен выкидывать исключение для отрицательных и нулевых значений времени
        store.addPlayTime("Kolya", 2);
        store.addPlayTime("Petya", -5);
        store.addPlayTime("Olga", 0);
        assertEquals("Kolya", store.getMostPlayer());
    }

    // тесты на метод getMostPlayer
    @Test
    public void shouldGetMostPlayerNoPlayers() {//null если игроков нет
        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldGetMostPlayer() {//должен возвращать игрока с самым большим временем игры
        store.addPlayTime("Kolya", 2);
        store.addPlayTime("Petya", 5);
        store.addPlayTime("Olga", 1);
        assertEquals("Petya", store.getMostPlayer());
    }

    @Test
    public void shouldGetMostOnePlayerOneHour() {

        store.addPlayTime("Olga", 1);
        assertEquals("Olga", store.getMostPlayer());
    }

    // тесты на метод getSumPlayedTime
    @Test
    public void shouldSumPlayedTimeIfSeveral() { //должен суммировать время игроков

        store.addPlayTime("Petya", 3);
        store.addPlayTime("Olga", 5);

        int expected = 8;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }
}
