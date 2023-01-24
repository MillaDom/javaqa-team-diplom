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
}
