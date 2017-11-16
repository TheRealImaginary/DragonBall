package dragonball.model.world;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
//import dragonball.model.character.NonPlayableCharacter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.game.Game;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Cell;
import dragonball.model.cell.CellListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.CollectibleCell;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.FoeCell;

public class World implements CellListener, Serializable {

    private Cell[][] map;
    private int playerColumn;
    private int playerRow;
    private WorldListener listener;

    public World() {
	map = new Cell[10][10];
    }

    public void generateMap(ArrayList<NonPlayableFighter> weakFoes, ArrayList<NonPlayableFighter> strongFoes) {

	playerColumn = map[0].length - 1;
	playerRow = map.length - 1;

	Random foe = new Random();
	Random position = new Random();

	int strongFoesSize = strongFoes.size();
	int weakFoesSize = weakFoes.size();

	map[0][0] = new FoeCell(strongFoes.get(foe.nextInt(strongFoesSize)));
	map[0][0].setListener(this);

	for (int i = 0; i < 15;) {
	    int row = position.nextInt(10);
	    int column = position.nextInt(10);
	    if (map[row][column] == null && (row != 9 || column != 9)) {
		int index = foe.nextInt(weakFoesSize);
		map[row][column] = new FoeCell(weakFoes.get(index));
		map[row][column].setListener(this);
		i++;
	    }
	}

	// Generate a number in a specific range (Upper - Lower) + Lower
	int max_Senzu_Beans = new Random().nextInt(2) + 3;
	for (int i = 0; i < max_Senzu_Beans;) {
	    int row = position.nextInt(10);
	    int column = position.nextInt(10);
	    if (map[row][column] == null && (row != 9 || column != 9)) {
		map[row][column] = new CollectibleCell(Collectible.SENZU_BEAN);
		map[row][column].setListener(this);
		i++;
	    }
	}

	while (true) {
	    int row = position.nextInt(10);
	    int column = position.nextInt(10);
	    if (map[row][column] == null && (row != 9 || column != 9)) {
		map[row][column] = new CollectibleCell(Collectible.DRAGON_BALL);
		map[row][column].setListener(this);
		break;
	    }
	}

	for (int i = 0; i < map.length; ++i) {
	    for (int j = 0; j < map[0].length; ++j) {
		if (map[i][j] == null) {
		    map[i][j] = new EmptyCell();
		    map[i][j].setListener(this);
		}
	    }
	}
    }

    public void clear() {
	for (int i = 0; i < 10; i++)
	    Arrays.fill(map[i], null);
    }

    public int getPlayerColumn() {
	return playerColumn;
    }

    public int getPlayerRow() {
	return playerRow;
    }

    public Cell[][] getMap() {
	return map;
    }

    public void resetPlayerPosition() {
	playerColumn = playerRow = 9;
    }

    public void moveUp() throws ClassNotFoundException, IOException {
	if (playerRow == 0) {
	    throw new MapIndexOutOfBoundsException(playerRow, playerColumn);
	}
	playerRow--;
	map[playerRow][playerColumn].onStep();
    }

    public void moveDown() throws ClassNotFoundException, IOException {
	if (playerRow == 9) {
	    throw new MapIndexOutOfBoundsException(playerRow, playerColumn);
	}
	playerRow++;
	map[playerRow][playerColumn].onStep();
    }

    public void moveRight() throws ClassNotFoundException, IOException {
	if (playerColumn == 9) {
	    throw new MapIndexOutOfBoundsException(playerRow, playerColumn);
	}
	playerColumn++;
	map[playerRow][playerColumn].onStep();
    }

    public void moveLeft() throws ClassNotFoundException, IOException {
	if (playerColumn == 0) {
	    throw new MapIndexOutOfBoundsException(playerRow, playerColumn);
	}
	playerColumn--;
	map[playerRow][playerColumn].onStep();
    }

    public void setListener(WorldListener listener) {
	this.listener = listener;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < map.length; i++)
	    sb.append(Arrays.toString(map[i]) + "\n");
	return sb.toString();
    }

    @Override
    public void onFoeEncountered(NonPlayableFighter foe) throws ClassNotFoundException, IOException {
	if (listener != null)
	    listener.onFoeEncountered(foe);
    }

    @Override
    public void onCollectibleFound(Collectible collectible) {
	if (listener != null)
	    listener.onCollectibleFound(collectible);
    }

    public static void main(String[] args) throws IOException {
	/*
	 * Game game = new Game(); World w = new World();
	 * w.generateMap(game.getWeakFoes(), game.getStrongFoes()); for(int i =
	 * 0;i < 10;++i) System.out.println(Arrays.toString(w.map[i]));
	 */
	// Game game = new Game();
	// World w = game.getWorld();
	// System.out.println(w);
	// game.onBattleEvent(new BattleEvent((new Battle(new Frieza(), new
	// Majin())), BattleEventType.ENDED, new Frieza()));
	// System.out.println(w);
    }

}
