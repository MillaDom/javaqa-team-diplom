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
    public void shouldAddSeveralGame() { // должен хранить игры в каталоге

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн1", "Стратегия");
        Game game3 = store.publishGame("Нетология Баттл Онлайн2", "Quest");
        Game game4 = store.publishGame("Нетология Баттл Онлайн3", "Shooter");
        Game game5 = store.publishGame("Нетология Баттл Онлайн4", "Аркады");

        assertTrue(store.containsGame(game3));
    }

    @Test
    public void shouldNotContainNewGameNoGames() {

        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(store.containsGame(game));
    }

    // тесты на метод publishGame

    //    @Test
//    public void shouldNotDuplicateGame(){ // не должен создавать дублирующие объекты
//        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
//        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
//        assertFalse(store.containsGame(game2));
//    }
    //из-за добавленного exception должен быть другой тест на assertThrows
    @Test
    public void shouldNotAddOldGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertThrows(RuntimeException.class, () -> {
            store.publishGame("Нетология Баттл Онлайн", "Аркады");
        });
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

    //    @Test
//    public void couldAddNegativeOrNullTime() {// добавляет отрицательные и нулевые значения
//        store.addPlayTime("Kolya", 2);
//        store.addPlayTime("Petya", -5);
//        store.addPlayTime("Olga", 0);
//        assertEquals("Kolya", store.getMostPlayer());
//    }

    //из-за добавленного exception должен быть другой тест на assertThrows
    @Test
    public void couldNotAddNegativeTime() {// должен выкидывать исключение для отрицательных значений времени
        assertThrows(RuntimeException.class, () -> {
            store.addPlayTime("Olga", -5);
        });
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

    @Test
    public void shouldSumPlayedTimeIfOne() { //должен суммировать время игроков

        store.addPlayTime("Petya", 3);
        store.addPlayTime("Petya", 5);

        int expected = 8;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }
}
