package pl.battleship.events;

import pl.battleship.Game;

public interface GameEvent {
    String getName();
    String getDescription();
    void trigger(Game game); // lub inne obiekty potrzebne do wpływu
}
