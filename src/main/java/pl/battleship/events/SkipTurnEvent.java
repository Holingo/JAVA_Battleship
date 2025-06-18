package pl.battleship.events;

import pl.battleship.Game;

import java.util.Random;

public class SkipTurnEvent implements GameEvent {
    private final String name = "Burza magnetyczna";
    private final String description = "Jedna ze stron traci swoją turę!";

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public void trigger(Game game) {
        Random rand = new Random();
        boolean skipPlayer = rand.nextBoolean();

        if (skipPlayer) {
            game.setSkipPlayerTurn(true);
            System.out.println("[EVENT] " + getName() + ": Gracz traci turę!");
        } else {
            game.setSkipAiTurn(true);
            System.out.println("[EVENT] " + getName() + ": AI traci turę!");

        }
    }
}
