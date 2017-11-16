package dragonball.model.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.EmptyCell;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.InvalidFormatException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.player.Player;
import dragonball.model.player.PlayerListener;
import dragonball.model.world.World;
import dragonball.model.world.WorldListener;

public class Game implements PlayerListener, WorldListener, BattleListener, Serializable {

    private static class Pair implements Serializable {

	String type;
	int damage;

	public Pair(String t, int d) {
	    type = t;
	    damage = d;
	}
    }

    // private static final String foes_Path = "Database-Foes_20311.csv";
    // private static final String attacks_Path = "Database-Attacks_20309.csv";
    // private static final String dragons_Path = "Database-Dragons_20310.csv";

    private static File lastSave;
    private static final String SaveFile = "SavedGame";

    private static final String FOES_PATH_AUX = "Database-Foes-aux.csv";
    private static final String ATTACKS_PATH_AUX = "Database-Attacks-aux.csv";
    private static final String DRAGONS_PATH_AUX = "Database-Dragons-aux.csv";

    // private static final String FOES_PATH = "Database-Foes_Mod.csv";
    private static final String[] FOES_PATHS = { "Database-Foes-Range1.csv", "Database-Foes-Range2.csv",
	    "Database-Foes-Range3.csv", "Database-Foes-Range4.csv" };
    private static final String ATTACKS_PATH = "Database-Attacks_Mod.csv";
    private static final String DRAGONS_PATH = "Database-Dragons_Mod.csv";

    private Player player;
    private World world;
    private ArrayList<NonPlayableFighter> weakFoes;
    private ArrayList<NonPlayableFighter> strongFoes;
    private ArrayList<Attack> attacks;
    private ArrayList<Dragon> dragons;
    private TreeMap<String, Pair> map;
    private GameState state;
    private GameListener listener;

    public Game() {

	weakFoes = new ArrayList<>();
	strongFoes = new ArrayList<>();
	attacks = new ArrayList<>();
	dragons = new ArrayList<>();
	map = new TreeMap<>();

	player = new Player();
	player.setDragonBalls(6);
	player.setListener(this);
	state = GameState.WORLD;

	try {
	    try {
		loadAttacks(ATTACKS_PATH);
	    }
	    catch (Exception e) {
		attacks = new ArrayList<>();
		loadAttacks(ATTACKS_PATH_AUX);
	    }

	    try {
		// loadFoes(FOES_PATH);
		if (player.getActiveFighter() == null)
		    loadFoes(FOES_PATHS[0]);
		else if (player.getActiveFighter().getLevel() == 1)
		    loadFoes(FOES_PATHS[0]);
		else if (player.getActiveFighter().getLevel() == 2)
		    loadFoes(FOES_PATHS[1]);
		else if (player.getActiveFighter().getLevel() == 3)
		    loadFoes(FOES_PATHS[2]);
		else if (player.getActiveFighter().getLevel() >= 4)
		    loadFoes(FOES_PATHS[3]);
	    }
	    catch (Exception e) {
		strongFoes = new ArrayList<>();
		weakFoes = new ArrayList<>();
		loadFoes(FOES_PATH_AUX);
	    }

	    try {
		loadDragons(DRAGONS_PATH);
	    }
	    catch (Exception e) {
		dragons = new ArrayList<>();
		loadDragons(DRAGONS_PATH_AUX);
	    }
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	world = new World();
	world.generateMap(weakFoes, strongFoes);
	world.setListener(this);
    }

    // Should read csv files and output them in a DS for other methods to use.
    private ArrayList<String[]> loadCSV(String filePath) throws IOException { // returns
									      // an
									      // ArrayList
									      // of
									      // String
									      // arrays
	BufferedReader br = new BufferedReader(new FileReader(filePath));
	StringTokenizer st;
	String[] s;
	ArrayList<String[]> result = new ArrayList<>();
	String line;
	while ((line = br.readLine()) != null && !line.isEmpty()) {
	    s = line.split(",");
	    result.add(s);
	}
	br.close();
	return result;

    }

    public void loadAttacks(String filePath) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader(filePath));
	String line;
	int lineN = 1;
	while ((line = br.readLine()) != null && !line.isEmpty()) {
	    StringTokenizer st = new StringTokenizer(line, ",");
	    if (st.countTokens() < 3)
		throw new MissingFieldException(String.format("%s %d %d", filePath, lineN, 3 - st.countTokens()),
			filePath, lineN, 3 - st.countTokens());
	    String typeAttack = st.nextToken();
	    String name = st.nextToken();
	    int damage = Integer.parseInt(st.nextToken());

	    if (typeAttack.equals("SA"))
		attacks.add(new SuperAttack(name, damage));

	    else if (typeAttack.equals("UA"))
		attacks.add(new UltimateAttack(name, damage));

	    else if (typeAttack.equals("MC"))
		attacks.add(new MaximumCharge(name, damage));

	    else if (typeAttack.equals("SS"))
		attacks.add(new SuperSaiyan(name, damage));
	    else
		throw new UnknownAttackTypeException(filePath, lineN, typeAttack);
	    map.put(name, new Pair(typeAttack, damage));
	    lineN++;
	}

    }

    public void loadFoes(String filePath) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader(filePath));
	String line;
	int lineN = 1;
	while ((line = br.readLine()) != null && !line.isEmpty()) {

	    String[] fLine = line.split(",");
	    StringTokenizer superLine = new StringTokenizer(br.readLine(), ",");
	    StringTokenizer ultimateLine = new StringTokenizer(br.readLine(), ",");

	    if (fLine.length != 8) {
		int c = 0;
		if (fLine.length != 8)
		    c++;
		if (superLine.countTokens() == 0)
		    c++;
		if (ultimateLine.countTokens() == 0)
		    c++;
		throw new MissingFieldException(filePath + " " + lineN + " " + c, filePath, lineN, c);
	    }

	    ArrayList<SuperAttack> superAttacks = new ArrayList<>();
	    ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<>();

	    while (superLine.hasMoreTokens()) {
		String name = superLine.nextToken();
		Pair p = map.get(name);
		if (p == null) // Continue as this attack isn't in data base
			       // (loaded aux)
		    continue;
		if (p.type.equals("SA"))
		    superAttacks.add(new SuperAttack(name, p.damage));
		else if (p.type.equals("MC"))
		    superAttacks.add(new MaximumCharge(name, p.damage));
	    }

	    while (ultimateLine.hasMoreTokens()) {
		String name = ultimateLine.nextToken();
		Pair p = map.get(name);
		if (p == null) // Continue as this attack isn't in data base
			       // (loaded aux)
		    continue;
		if (p.type.equals("UA"))
		    ultimateAttacks.add(new UltimateAttack(name, p.damage));
		else if (p.type.equals("SS"))
		    ultimateAttacks.add(new SuperSaiyan(name, p.damage));
	    }

	    if (fLine[fLine.length - 1].equals("TRUE")) {
		strongFoes.add(new NonPlayableFighter(fLine[0], Integer.parseInt(fLine[1]), Integer.parseInt(fLine[2]),
			Integer.parseInt(fLine[3]), Integer.parseInt(fLine[4]), Integer.parseInt(fLine[5]),
			Integer.parseInt(fLine[6]), true, superAttacks, ultimateAttacks));
	    }
	    else {
		weakFoes.add(new NonPlayableFighter(fLine[0], Integer.parseInt(fLine[1]), Integer.parseInt(fLine[2]),
			Integer.parseInt(fLine[3]), Integer.parseInt(fLine[4]), Integer.parseInt(fLine[5]),
			Integer.parseInt(fLine[6]), false, superAttacks, ultimateAttacks));
	    }
	    lineN += 3;
	}
    }

    public void loadDragons(String filePath) throws IOException {
	// try {
	BufferedReader br = new BufferedReader(new FileReader(filePath));
	String line;
	int lineN = 1;
	while ((line = br.readLine()) != null && !line.isEmpty()) {
	    StringTokenizer st = new StringTokenizer(line, ",");
	    if (st.countTokens() < 3)
		throw new MissingFieldException(String.format("%s %d %d", filePath, lineN, 3 - st.countTokens()),
			filePath, lineN, 3 - st.countTokens());
	    StringTokenizer superLine = new StringTokenizer(br.readLine(), ",");
	    StringTokenizer ultimateLine = new StringTokenizer(br.readLine(), ",");

	    ArrayList<SuperAttack> superAttacks = new ArrayList<>();
	    ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<>();

	    while (superLine.hasMoreTokens()) {
		String name = superLine.nextToken();
		Pair p = map.get(name);
		if (p == null) // Continue as this attack isn't in data base
			       // (loaded aux)
		    continue;
		if (p.type.equals("SA"))
		    superAttacks.add(new SuperAttack(name, p.damage));
		else if (p.type.equals("MS"))
		    superAttacks.add(new MaximumCharge(name, p.damage));
	    }

	    while (ultimateLine.hasMoreTokens()) {
		String name = ultimateLine.nextToken();
		Pair p = map.get(name);
		if (p == null) // Continue as this attack isn't in data base
			       // (loaded aux)
		    continue;
		if (p.type.equals("UA"))
		    ultimateAttacks.add(new UltimateAttack(name, p.damage));
		else if (p.type.equals("SS"))
		    ultimateAttacks.add(new SuperSaiyan(name, p.damage));
	    }

	    dragons.add(new Dragon(st.nextToken(), superAttacks, ultimateAttacks, Integer.parseInt(st.nextToken()),
		    Integer.parseInt(st.nextToken())));
	    lineN += 3;
	}
    }

    public Player getPlayer() {
	return player;
    }

    public World getWorld() {
	return world;
    }

    public ArrayList<NonPlayableFighter> getWeakFoes() {
	return weakFoes;
    }

    public ArrayList<NonPlayableFighter> getStrongFoes() {
	return strongFoes;
    }

    public ArrayList<Attack> getAttacks() {
	return attacks;
    }

    public ArrayList<Dragon> getDragons() {
	return dragons;
    }

    public GameState getState() {
	return state;
    }

    public void setListener(GameListener listener) {
	this.listener = listener;
    }

    @Override
    public void onBattleEvent(BattleEvent e) throws ClassNotFoundException, IOException {
	if (e.getType() == BattleEventType.STARTED)
	    state = GameState.BATTLE;
	else if (e.getType() == BattleEventType.ENDED) {
	    NonPlayableFighter npf = null;
	    if (((Battle) e.getSource()).getDefender() instanceof NonPlayableFighter)
		npf = (NonPlayableFighter) ((Battle) e.getSource()).getDefender();
	    else {
		System.err.println("LAST SAVE " + lastSave);
		if (lastSave != null && lastSave.exists()) {
		    load(lastSave.getPath());
		}
		else {
		    world = new World();
		    world.generateMap(weakFoes, strongFoes);
		    world.setListener(this);
		    state = GameState.WORLD;
		}
		if (listener != null)
		    listener.onBattleEvent(e);
		return;
	    }
	    if (npf != null) {
		player.getActiveFighter().setXp(npf.getLevel() * 5);
		for (SuperAttack s : npf.getSuperAttacks()) {
		    if (!player.getSuperAttacks().contains(s))
			player.getSuperAttacks().add(s);
		}
		for (UltimateAttack u : npf.getUltimateAttacks()) {
		    if (!player.getSuperAttacks().contains(u))
			player.getUltimateAttacks().add(u);
		}
		// player.getSuperAttacks().addAll(npf.getSuperAttacks());
		// player.getUltimateAttacks().addAll(npf.getUltimateAttacks());
		if (npf.isStrong()) {
		    player.setExploredMaps(player.getExploredMaps() + 1);
		    world = new World();
		    world.generateMap(weakFoes, strongFoes);
		    world.setListener(this);
		}
		else
		    world.getMap()[world.getPlayerRow()][world.getPlayerColumn()] = new EmptyCell();
	    }
	    state = GameState.WORLD;
	}

	if (listener != null)
	    listener.onBattleEvent(e);
    }

    @Override
    public void onFoeEncountered(NonPlayableFighter foe) throws ClassNotFoundException, IOException {
	Battle b = new Battle(player.getActiveFighter(), foe);
	b.setListener(this);
	b.start();
	// if(listener != null)
	// listener.onBattleEvent(BattleEventType.STARTED);
    }

    @Override
    public void onCollectibleFound(Collectible collectible) {
	if (collectible == Collectible.SENZU_BEAN)
	    player.setSenzuBeans(player.getSenzuBeans() + 1);

	else if (collectible == Collectible.DRAGON_BALL) {
	    player.setDragonBalls(player.getDragonBalls() + 1);
	    if (player.getDragonBalls() == 7)
		player.callDragon();
	}
	world.getMap()[world.getPlayerRow()][world.getPlayerColumn()] = new EmptyCell();

	if (listener != null)
	    listener.onCollectibleFound(collectible);
    }

    @Override
    public void onDragonCalled() {
	Random r = new Random();
	Dragon d;
	int x = r.nextInt(100);
	if (x > 50)
	    d = dragons.get(0);
	else
	    d = dragons.get(1);

	player.setDragonBalls(0);
	state = GameState.DRAGON;

	if (listener != null)
	    listener.onDragonCalled(d);
    }

    @Override
    public void onWishChosen(DragonWish wish) {
	state = GameState.WORLD;
    }

    public void save(String fileName) throws IOException {
	lastSave = new File(fileName);
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(lastSave));
	oos.writeObject(this);
	oos.close();
    }

    public void load(String fileName) throws IOException, ClassNotFoundException {
	ObjectInputStream ooi = new ObjectInputStream(new FileInputStream(fileName));
	Game loaded = (Game) ooi.readObject();
	this.weakFoes = loaded.weakFoes;
	this.strongFoes = loaded.strongFoes;
	this.attacks = loaded.attacks;
	this.dragons = loaded.dragons;
	this.listener = loaded.listener;
	this.map = loaded.map;
	this.player = loaded.player;
	this.state = loaded.state;
	this.world = loaded.world;
	this.world.setListener(this);
	this.player.setListener(this);
	// for(int i = 0;i < 10;i++){
	// for(int j = 0;j < 10;j++)
	// world.getMap()[i][j].setListener(world);
	// }
	ooi.close();
    }

    public void load() throws IOException, ClassNotFoundException {
	if (lastSave != null)
	    load(lastSave.getName());
    }

    private Game loadTest(String fileName) throws IOException, ClassNotFoundException {
	ObjectInputStream ooi = new ObjectInputStream(new FileInputStream(fileName));
	return (Game) ooi.readObject();
    }

    public boolean isSaved() {
	// return lastSave != null || lastSave.exists();
	lastSave = new File(SaveFile);
	if (lastSave != null && lastSave.exists())
	    return true;
	return false;
    }

    public static void main(String[] args) throws Exception {

	// String s = "GUC";
	// Game game = new Game();
	// game.loadAttacks(attacks_Path);
	// System.out.println(game.weakFoes.size());
	// System.out.println(game.strongFoes.size());
	// StringTokenizer st = new StringTokenizer("HELLO HH", ",");
	// System.out.println(st.nextToken());

	// for(int i = 0;i < 50;i++){

	// }
	// System.out.println();
	// String x = "2";
	// String s2 = "GUC";
	// String s1 = "GUC";

	// System.out.println(s == s1);

	/*
	 * Game g = new Game(); System.out.println(g.dragons);
	 * System.out.println(g.attacks); System.out.println(g.player);
	 * System.out.println(g.strongFoes); System.out.println(g.weakFoes);
	 * System.out.println(g.state); System.out.println(g.world);
	 * g.save("Testing123");
	 * 
	 * Game testing = g.loadTest("Testing123");
	 * System.out.println(testing.dragons);
	 * System.out.println(testing.attacks);
	 * System.out.println(testing.player);
	 * System.out.println(testing.strongFoes);
	 * System.out.println(testing.weakFoes);
	 * System.out.println(testing.state); System.out.println(testing.world);
	 */

	// System.out.println(lastSave.getPath());
	// System.out.println(g.map);
	Game g = new Game();
	// Game g = new Game();
	// try {
	// g.save("SavedGame");
	// } catch (IOException ioe) {
	// System.out.println("ERROR!");
	// }
	// System.out.println(lastSave.getName());
    }

}
