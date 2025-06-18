package pl.battleship.events;

import pl.battleship.Game;

import java.util.Random;

public class DoubleShotEvent implements GameEvent {
    private final String name = "Podwójny strzał";
    private final String description = "Gracz lub AI może strzelić dwa razy w tej turze.";

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public void trigger(Game game) {
        Random rand = new Random();
        boolean playerGetsDouble = rand.nextBoolean();

        if (playerGetsDouble) {
            game.setPlayerDoubleShot(true);
            System.out.println("[EVENT] " + getName() + ": Gracz odda dwa strzały!");
        } else {
            game.setAiDoubleShot(true);
            System.out.println("[EVENT] " + getName() + ": AI odda dwa strzały!");
        }
    }
}