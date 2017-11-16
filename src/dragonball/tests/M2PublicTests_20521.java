package dragonball.tests;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.EventObject;

import static org.junit.Assert.*;

import org.junit.*;

import dragonball.model.attack.*;
import dragonball.model.battle.*;
import dragonball.model.cell.*;
import dragonball.model.character.fighter.*;
import dragonball.model.dragon.*;
import dragonball.model.game.*;
import dragonball.model.player.*;
import dragonball.model.world.*;

public class M2PublicTests_20521 {
	boolean hasCalled = false;
	BattleEventType type;
	Battle b;
	Dragon dragon;
	boolean firstChange = true;
	
	@Test(timeout = 1000)
	public void testEnumGameState() throws Exception{
		assertTrue("GameState should be an enum.", GameState.class.isEnum());
		assertNotNull("GameState can be WORLD",GameState.valueOf("WORLD"));
		assertNotNull("GameState can be BATTLE",GameState.valueOf("BATTLE"));
		assertNotNull("GameState can be DRAGON",GameState.valueOf("DRAGON"));
		int values = GameState.values().length;
		assertTrue("GameState enum can only have three values",values == 3);
	}


	@Test(timeout = 1000)
	public void testDragonWishType() throws Exception{
		assertTrue("DragonWishType should be an enum.", DragonWishType.class.isEnum());
		assertNotNull("DragonWishType can be SENZU_BEANS", DragonWishType.valueOf("SENZU_BEANS"));
		assertNotNull("DragonWishType can be ABILITY_POINTS", DragonWishType.valueOf("ABILITY_POINTS"));
		assertNotNull("DragonWishType can be SUPER_ATTACK", DragonWishType.valueOf("SUPER_ATTACK"));
		assertNotNull("DragonWishType can be ULTIMATE_ATTACK", DragonWishType.valueOf("ULTIMATE_ATTACK"));
		int values = DragonWishType.values().length;
		assertTrue("DragonWishType enum can only have four values",values == 4);
	}


	@Test(timeout = 1000)
	public void testBattleEventType()throws Exception{
		assertTrue("BattleEventType should be an enum.", BattleEventType.class.isEnum());
		assertNotNull("BattleEventType can be STARTED",BattleEventType.valueOf("STARTED"));
		assertNotNull("BattleEventType can be ENDED",BattleEventType.valueOf("ENDED"));
		assertNotNull("BattleEventType can be NEW_TURN",BattleEventType.valueOf("NEW_TURN"));
		assertNotNull("BattleEventType can be ATTACK",BattleEventType.valueOf("ATTACK"));
		assertNotNull("BattleEventType can be BLOCK",BattleEventType.valueOf("BLOCK"));
		assertNotNull("BattleEventType can be USE",BattleEventType.valueOf("USE"));
		int values = BattleEventType.values().length;
		assertTrue("BattleEventType enum can only have six values",values == 6);
	}

	@Test(timeout = 1000)
	public void testPlayerListenerInterface() throws Exception {
		assertEquals("PlayerListener should be an Interface", 1537,
				PlayerListener.class.getModifiers());
	}


	@Test(timeout = 1000)
	public void testWorldListenerInterface()throws Exception {
		assertEquals("WorldListener should be an Interface", 1537,
				WorldListener.class.getModifiers());
	}


	@Test(timeout = 1000)
	public void testCellListenerInterface() throws Exception{
		assertEquals("CellListener should be an Interface", 1537,
				CellListener.class.getModifiers());
	}


	@Test(timeout = 1000)
	public void testBattleListenerInterface()throws Exception {
		assertEquals("BattleListener should be an Interface", 1537,
				BattleListener.class.getModifiers());
	}


	@Test(timeout = 1000)
	public void testGameListenerInterface()throws Exception {
		assertEquals("GameListener should be an Interface", 1537,
				GameListener.class.getModifiers());
	}


	@Test(timeout = 1000)
	@SuppressWarnings("rawtypes")
	public void testGameClassInterfaces() throws Exception{
		Class[] interfaces = Game.class.getInterfaces();
		boolean playerListenerExists = false;
		boolean wordlListenerExists = false;
		boolean battleListenerExists = false;
		for (Class i : interfaces) {
			if (i.toString().equals(PlayerListener.class.toString()))
				playerListenerExists = true;
			if (i.toString().equals(WorldListener.class.toString()))
				wordlListenerExists = true;
			if (i.toString().equals(BattleListener.class.toString()))
				battleListenerExists = true;
		}
		assertTrue(
				"Game class should implement PlayerListener interface.", playerListenerExists);
		assertTrue(
				"Game class should implement WorldListener interface.", wordlListenerExists);
		assertTrue(
				"Game class should implement BattleListener interface.", battleListenerExists);
	}


	@Test(timeout = 1000)
	@SuppressWarnings("rawtypes")
	public void testWorldClassInterfaces() throws Exception{
		Class[] interfaces = World.class.getInterfaces();
		boolean cellListenerExists = false;
		for (Class i : interfaces) {
			if (i.toString().equals(CellListener.class.toString()))
				cellListenerExists = true;
		}
		assertTrue(
				"World class should implement CellListener interface.", cellListenerExists);
	}


	@Test(timeout = 1000)
	public void testPlayerListenerInterfaceMethods() throws Exception{
		Method m;
		try{
			Method[] methods = PlayerListener.class.getDeclaredMethods();
			assertTrue("PlayerListener interface should have \"onDragonCalled\" method", containsMethodName(methods, "onDragonCalled"));
			assertTrue("PlayerListener interface should have \"onWishChosen\" method", containsMethodName(methods, "onWishChosen"));
			m = PlayerListener.class.getDeclaredMethod("onDragonCalled");
			assertTrue("incorrect return type for \"onDragonCalled\" method in PlayerListener interface.", 
					m.getReturnType().equals(Void.TYPE));
		}catch(Exception e){
			fail("Missing \"onDragonCalled\" method in PlayerListener interface which takes no input parameters");
		}

		try{
			m = PlayerListener.class.getDeclaredMethod("onWishChosen",DragonWish.class);
			assertTrue(
					"incorrect return type for \"onWishChosen\" method in PlayerListener interface.",
					m.getReturnType().equals(Void.TYPE));

		}catch(Exception e){
			fail("Missing \"onWishChosen\" method in PlayerListener interface which takes no input parameters");
		}
	}	


	@Test(timeout = 1000)
	public void testWorldListenerInterfaceMethods() throws Exception{

		Method[] methods = WorldListener.class.getDeclaredMethods();
		assertTrue("WorldListener interface should have \"onFoeEncountered\" method", containsMethodName(methods, "onFoeEncountered"));
		assertTrue("WorldListener interface should have \"onCollectibleFound\" method", containsMethodName(methods, "onCollectibleFound"));
		Method m;
		try{
			m = WorldListener.class.getDeclaredMethod("onFoeEncountered", NonPlayableFighter.class);
			assertTrue("incorrect return type for \"onFoeEncountered\" method in WorldListener interface.",
					m.getReturnType().equals(Void.TYPE));


		}catch(Exception e){
			fail("Missing \"onFoeEncountered\" method in WorldListener interface which takes an input of type NonPlayableFighter");
		}
		try{
			m = WorldListener.class.getDeclaredMethod("onCollectibleFound",Collectible.class);
			assertTrue(
					"incorrect return type for \"onCollectibleFound\" method in WorldListener interface.",
					m.getReturnType().equals(Void.TYPE));

		}catch(Exception e){
			fail("Missing \"onCollectibleFound\" method in WorldListener interface which takes an input of type Collectible");
		}
	}	


	@Test(timeout = 1000)
	public void testCellListenerInterfaceMethods() throws Exception{

		Method[] methods = CellListener.class.getDeclaredMethods();
		assertTrue("CellListener interface should have \"onFoeEncountered\" method", containsMethodName(methods, "onFoeEncountered"));
		assertTrue("CellListener interface should have \"onCollectibleFound\" method", containsMethodName(methods, "onCollectibleFound"));
		Method m;
		try{
			m = CellListener.class.getDeclaredMethod("onFoeEncountered", NonPlayableFighter.class);
			assertTrue("incorrect return type for \"onFoeEncountered\" method in CellListener interface.",
					m.getReturnType().equals(Void.TYPE));

		}
		catch(Exception e){
			fail("Missing \"onFoeEncountered\" method in CellListener interface which takes an input of type NonPlayableFighter");
		}

		try{
			m = CellListener.class.getDeclaredMethod("onCollectibleFound",Collectible.class);
			assertTrue(
					"incorrect return type for \"onCollectibleFound\" method in CellListener interface.",
					m.getReturnType().equals(Void.TYPE));

		}
		catch(Exception e){
			fail("Missing \"onCollectibleFound\" method in CellListener interface which takes an input of type Collectible");
		}

	}	


	@Test(timeout = 1000)
	public void testBattleListenerInterfaceMethods() throws Exception,
	SecurityException {

		Method[] methods = BattleListener.class.getDeclaredMethods();
		assertTrue("BattleListener interface should have \"onBattleEvent\" method", containsMethodName(methods, "onBattleEvent"));
		Method m;
		try{
			m = BattleListener.class.getDeclaredMethod("onBattleEvent",BattleEvent.class);
			assertTrue("incorrect return type for \"onBattleEvent\" method in BattleListener interface.",
					m.getReturnType().equals(Void.TYPE));
		}catch(Exception e){
			fail("Missing \"onBattleEvent\" method in BattleListener interface which takes an input of type BattleEvent");
		}
	}	


	@Test(timeout = 1000)
	public void testGameListenerInterfaceMethods() throws Exception {

		Method[] methods = GameListener.class.getDeclaredMethods();
		assertTrue("GameListener interface should have \"onDragonCalled\" method", containsMethodName(methods, "onDragonCalled"));
		assertTrue("GameListener interface should have \"onCollectibleFound\" method", containsMethodName(methods, "onCollectibleFound"));
		assertTrue("GameListener interface should have \"onBattleEvent\" method", containsMethodName(methods, "onBattleEvent"));
		Method m;
		try{
			m = GameListener.class.getDeclaredMethod("onDragonCalled", Dragon.class);
			assertTrue("incorrect return type for \"onDragonCalled\" method in GameListener interface.",
					m.getReturnType().equals(Void.TYPE));
		}
		catch(Exception e){
			fail("Missing \"onDragonCalled\" method in GameListener interface which takes an input parameter of type Dragon.");
		}

		try{
			m = GameListener.class.getDeclaredMethod("onCollectibleFound", Collectible.class);
			assertTrue("incorrect return type for \"onCollectibleFound\" method in GameListener interface.",
					m.getReturnType().equals(Void.TYPE));
		}
		catch(Exception e){
			fail("Missing \"onCollectibleFound\" method in GameListener interface which takes an input parameter of type Collectible.");
		}
		try{
			m = GameListener.class.getDeclaredMethod("onBattleEvent", BattleEvent.class);
			assertTrue("incorrect return type for \"onBattleEvent\" method in GameListener interface.",
					m.getReturnType().equals(Void.TYPE));
		}
		catch(Exception e){
			fail("Missing \"onBattleEvent\" method in GameListener interface which takes an input parameter of type BattleEvent.");
		}
	}	

	@Test(timeout = 1000)
	public void testGameConstructorSetsCorrectListeners()throws Exception{
		Game g = new Game();
		World w = g.getWorld();
		Player p = g.getPlayer();

		assertNotNull("Player should be intialized in the Game class constructor", p);
		Field[] fields = Player.class.getDeclaredFields();

		int index = -1;
		for (int k = 0; k < fields.length; k++) {
			if (fields[k].getType().equals(PlayerListener.class)) {
				index = k;
				break;
			}
		}
		System.out.println(index);
		if (index != -1) {
			Field f = Player.class.getDeclaredFields()[index];
			f.setAccessible(true);
			assertNotNull("The game constructor should set itself as a listener for the Player", f.get(p));
		}

		fields = World.class.getDeclaredFields();
		index = -1;
		for (int k = 0; k < fields.length; k++) {
			if (fields[k].getType().equals(WorldListener.class)) {
				index = k;
				break;
			}
		}
		if (index != -1) {
			Field f = World.class.getDeclaredFields()[index];
			f.setAccessible(true);
			assertNotNull("The game constructor should set itself as a listener for the World", f.get(w));

		}

	}

	@Test(timeout = 1000)
	public void testGameClassInstanceVariables() throws Exception {
		Field f = null;
		boolean thrown = false;
		try {
			f = Game.class.getDeclaredField("state");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"state\" instance variable in class Game",thrown);
		assertEquals("\"state\" instance variable in class Game should be of type GameState",f.getType(), GameState.class);
	}


	@Test(timeout = 1000)
	public void testGameClassVariablesAccessibility()
			throws Exception, NoSuchFieldException, SecurityException {
		Field f = Game.class.getDeclaredField("state");
		assertEquals(
				"\"state\" instance variable in class Game should not be accessed outside that class",
				2, f.getModifiers());
	}


	@Test(timeout = 1000)
	public void testGameClassREADVariables() throws Exception {
		Method[] methods = Game.class.getDeclaredMethods();
		assertTrue(
				"The \"state\" instance variable in class Game is a READ variable.",
				containsMethodName(methods, "getState"));
		try{
			Method m = Game.class.getDeclaredMethod("getState");
			assertTrue("incorrect return type for \"getState\" method in Game class.",
					m.getReturnType().equals(GameState.class));
		}
		catch(Exception e){
			fail("Missing \"getState\" method in Game class which takes no input parameters.");
		}
	}


	@Test(timeout = 1000)
	public void testBattleClassInstanceVariables() throws Exception {
		Field f = null;
		boolean thrown = false;
		try {
			f = Battle.class.getDeclaredField("meBlocking");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"meBlocking\" instance variable in class Battle",thrown);
		assertEquals("\"meBlocking\" instance variable in class Battle should be of type boolean",
				f.getType(), boolean.class);

		thrown = false;
		try {
			f = Battle.class.getDeclaredField("foeBlocking");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"foeBlocking\" instance variable in class Battle",thrown);
		assertEquals("\"foeBlocking\" instance variable in class Battle should be of type boolean",
				f.getType(), boolean.class);
	}


	@Test(timeout = 1000)
	public void testBattleClassVariablesAccessibility()
			throws Exception {
		Field f = Battle.class.getDeclaredField("meBlocking");
		assertEquals(
				"\"meBlocking\" instance variable in class Battle should not be accessed outside that class",
				2, f.getModifiers());

		f = Battle.class.getDeclaredField("foeBlocking");
		assertEquals(
				"\"foeBlocking\" instance variable in class Battle should not be accessed outside that class",
				2, f.getModifiers());
	}


	@Test(timeout = 1000)
	public void testPlayerClassMethods() throws Exception{

		Method[] methods = Player.class.getDeclaredMethods();
		assertTrue("Player class should have \"getMaxFighterLevel\" method", containsMethodName(methods, "getMaxFighterLevel"));
		assertTrue("Player class should have \"callDragon\" method", containsMethodName(methods, "callDragon"));
		assertTrue("Player class should have \"chooseWish\" method", containsMethodName(methods, "chooseWish"));
		assertTrue("Player class should have \"createFighter\" method", containsMethodName(methods, "createFighter"));
		assertTrue("Player class should have \"upgradeFighter\" method", containsMethodName(methods, "upgradeFighter"));
		assertTrue("Player class should have \"assignAttack\" method", containsMethodName(methods, "assignAttack"));
		assertTrue("Player class should have \"assignAttack\" method", containsMethodName(methods, "assignAttack"));
		Method m;
		try{
			m = Player.class.getDeclaredMethod("getMaxFighterLevel");
			assertTrue("incorrect return type for \"getMaxFighterLevel\" method in Player class.",
					m.getReturnType().equals(int.class));
		}catch(Exception e){
			fail("Missing method \"getMaxFighterLevel\" in class Player which takes no input parameters");
		}

		try{
			m = Player.class.getDeclaredMethod("callDragon");
			assertTrue("incorrect return type for \"callDragon\" method in Player class.",
					m.getReturnType().equals(Void.TYPE));
		}catch(Exception e){
			fail("Missing method \"callDragon\" in class Player which takes no input parameters");
		}

		try{
			m = Player.class.getDeclaredMethod("chooseWish", DragonWish.class);
			assertTrue("incorrect return type for \"chooseWish\" method in Player class.",
					m.getReturnType().equals(Void.TYPE));
		}catch(Exception e){
			fail("Missing method \"chooseWish\" in class Player which takes an input parameter of type DragonWish");
		}
		try{

			m = Player.class.getDeclaredMethod("createFighter", char.class, String.class);
			assertTrue("incorrect return type for \"createFighter\" method in Player class.",
					m.getReturnType().equals(Void.TYPE));
		}catch(Exception e){
			fail("Missing method \"createFighter\" in class Player which takes the input parameters: char, String");
		}
		try{
			m = Player.class.getDeclaredMethod("upgradeFighter", PlayableFighter.class, char.class);
			assertTrue("incorrect return type for \"upgradeFighter\" method in Player class.",
					m.getReturnType().equals(Void.TYPE));

		}catch(Exception e){
			fail("Missing method \"upgradeFighter\" in class Player which takes the input parameters: PlayableFighter, char");
		}

		try{
			m = Player.class.getDeclaredMethod("assignAttack", PlayableFighter.class, SuperAttack.class, SuperAttack.class);
			assertTrue("incorrect return type for \"assignAttack(SuperAttack s)\" method in Player class.",
					m.getReturnType().equals(Void.TYPE));
		}catch(Exception e){
			fail("Missing method \"assignAttack(SuperAttack s)\" in class Player which takes the input parameters: PlayableFighter, SuperAttack, SuperAttack");
		}
		
		try{
			m = Player.class.getDeclaredMethod("assignAttack", PlayableFighter.class, UltimateAttack.class, UltimateAttack.class);
			assertTrue("incorrect return type for \"assignAttack(UltimateAttack s)\" method in Player class.",
					m.getReturnType().equals(Void.TYPE));
		}catch(Exception e){
			fail("Missing method \"assignAttack(UltimateAttack s)\" in class Player which takes the input parameters: PlayableFighter, UltimateAttack, UltimateAttack");
		}
	}


	@Test(timeout = 1000)
	public void testBattleEventClassInstanceVariables() throws Exception{
		Field f = null;
		boolean thrown = false;
		try {
			f = BattleEvent.class.getDeclaredField("type");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"type\" instance variable in class BattleEvent",thrown);
		assertEquals("\"type\" instance variable in class BattleEvent should be of type BattleEventType enum",
				f.getType(), BattleEventType.class);

		thrown = false;
		try {
			f = BattleEvent.class.getDeclaredField("currentOpponent");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"currentOpponent\" instance variable in class BattleEvent",thrown);
		assertEquals("\"currentOpponent\" instance variable in class BattleEvent should be of type BattleOpponent",
				f.getType(), BattleOpponent.class);

		thrown = false;
		try {
			f = BattleEvent.class.getDeclaredField("winner");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"winner\" instance variable in class BattleEvent",thrown);
		assertEquals("\"winner\" instance variable in class BattleEvent should be of type BattleOpponent",
				f.getType(), BattleOpponent.class);

		thrown = false;
		try {
			f = BattleEvent.class.getDeclaredField("attack");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"attack\" instance variable in class BattleEvent",thrown);
		assertEquals("\"attack\" instance variable in class BattleEvent should be of type Attack",
				f.getType(), Attack.class);


		thrown = false;
		try {
			f = BattleEvent.class.getDeclaredField("collectible");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"collectible\" instance variable in class BattleEvent",thrown);
		assertEquals("\"collectible\" instance variable in class BattleEvent should be of type Collectible",
				f.getType(), Collectible.class);
	}


	@Test(timeout = 1000)
	public void testBattleEventClassVariablesAccessibility() throws Exception{
		Field f = BattleEvent.class.getDeclaredField("type");
		assertEquals(
				"\"type\" instance variable in class BattleEvent should not be accessed outside that class",
				2, f.getModifiers());

		f = BattleEvent.class.getDeclaredField("currentOpponent");
		assertEquals(
				"\"currentOpponent\" instance variable in class BattleEvent should not be accessed outside that class",
				2, f.getModifiers());

		f = BattleEvent.class.getDeclaredField("winner");
		assertEquals(
				"\"winner\" instance variable in class BattleEvent should not be accessed outside that class",
				2, f.getModifiers());

		f = BattleEvent.class.getDeclaredField("attack");
		assertEquals(
				"\"attack\" instance variable in class BattleEvent should not be accessed outside that class",
				2, f.getModifiers());

		f = BattleEvent.class.getDeclaredField("collectible");
		assertEquals(
				"\"collectible\" instance variable in class BattleEvent should not be accessed outside that class",
				2, f.getModifiers());
	}


	@Test(timeout = 1000)
	public void testBattleEventClassREADVariables() throws Exception{
		Method[] methods = BattleEvent.class.getDeclaredMethods();
		assertTrue(
				"The \"type\" instance variable in class BattleEvent is a READ variable.",
				containsMethodName(methods, "getType"));

		assertTrue(
				"The \"currentOpponent\" instance variable in class BattleEvent is a READ variable.",
				containsMethodName(methods, "getCurrentOpponent"));

		assertTrue(
				"The \"winner\" instance variable in class BattleEvent is a READ variable.",
				containsMethodName(methods, "getWinner"));

		assertTrue(
				"The \"attack\" instance variable in class BattleEvent is a READ variable.",
				containsMethodName(methods, "getAttack"));

		assertTrue(
				"The \"collectible\" instance variable in class BattleEvent is a READ variable.",
				containsMethodName(methods, "getCollectible"));
		Method m;
		try{
			m = BattleEvent.class.getDeclaredMethod("getType");
			assertTrue("incorrect return type for \"getType\" method in BattleEvent class.",
					m.getReturnType().equals(BattleEventType.class));
		}
		catch(Exception e){
			fail("Missing \"getType\" method in BattleEvent class which takes no input parameters.");
		}

		try{
			m = BattleEvent.class.getDeclaredMethod("getCurrentOpponent");
			assertTrue("incorrect return type for \"getCurrentOpponent\" method in BattleEvent class.",
					m.getReturnType().equals(BattleOpponent.class));
		}
		catch(Exception e){
			fail("Missing \"getCurrentOpponent\" method in BattleEvent class which takes no input parameters.");
		}


		try{
			m = BattleEvent.class.getDeclaredMethod("getWinner");

			assertTrue("incorrect return type for \"getWinner\" method in BattleEvent class.",
					m.getReturnType().equals(BattleOpponent.class));
		}
		catch(Exception e){
			fail("Missing \"getWinner\" method in BattleEvent class which takes no input parameters.");
		}

		try{
			m = BattleEvent.class.getDeclaredMethod("getAttack");
			assertTrue("incorrect return type for \"getAttack\" method in BattleEvent class.",
					m.getReturnType().equals(Attack.class));
		}
		catch(Exception e){
			fail("Missing \"getAttack\" method in BattleEvent class which takes no input parameters.");
		}

		try{
			m = BattleEvent.class.getDeclaredMethod("getCollectible");
			assertTrue("incorrect return type for \"getCollectible\" method in BattleEvent class.",
					m.getReturnType().equals(Collectible.class));
		}
		catch(Exception e){
			fail("Missing \"getCollectible\" method in BattleEvent class which takes no input parameters.");
		}
	}


	@Test(timeout = 1000)
	public void testBattleEventClassWRITEVariables() throws Exception{
		Method[] methods = BattleEvent.class.getDeclaredMethods();
		assertFalse(
				"The \"type\" instance variable in class BattleEvent is a READ ONLY variable.",
				containsMethodName(methods, "setType"));

		assertFalse(
				"The \"currentOpponent\" instance variable in class BattleEvent is a READ ONLY variable.",
				containsMethodName(methods, "setCurrentOpponent"));

		assertFalse(
				"The \"winner\" instance variable in class BattleEvent is a READ ONLY variable.",
				containsMethodName(methods, "setWinner"));

		assertFalse(
				"The \"attack\" instance variable in class BattleEvent is a READ ONLY variable.",
				containsMethodName(methods, "setAttack"));

		assertFalse(
				"The \"collectible\" instance variable in class BattleEvent is a READ ONLY variable.",
				containsMethodName(methods, "setCollectible"));
	}


	@Test(timeout = 1000)
	public void testDragonWishClassInstanceVariables() throws Exception{
		Field f = null;
		boolean thrown = false;
		try {
			f = DragonWish.class.getDeclaredField("type");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"type\" instance variable in class DragonWish",thrown);
		assertEquals("\"type\" instance variable in class DragonWish should be of type DragonWishType enum",
				f.getType(), DragonWishType.class);

		thrown = false;
		try {
			f = DragonWish.class.getDeclaredField("senzuBeans");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"senzuBeans\" instance variable in class DragonWish",thrown);
		assertEquals("\"senzuBeans\" instance variable in class DragonWish should be of type int",
				f.getType(), int.class);

		thrown = false;
		try {
			f = DragonWish.class.getDeclaredField("abilityPoints");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"abilityPoints\" instance variable in class DragonWish",thrown);
		assertEquals("\"abilityPoints\" instance variable in class DragonWish should be of type int",
				f.getType(), int.class);


		thrown = false;
		try {
			f = DragonWish.class.getDeclaredField("superAttack");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"superAttack\" instance variable in class DragonWish",thrown);
		assertEquals("\"superAttack\" instance variable in class DragonWish should be of type SuperAttack",
				f.getType(), SuperAttack.class);

		thrown = false;
		try {
			f = DragonWish.class.getDeclaredField("ultimateAttack");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse("there should be \"ultimateAttack\" instance variable in class DragonWish",thrown);
		assertEquals("\"ultimateAttack\" instance variable in class DragonWish should be of type UltimateAttack",
				f.getType(), UltimateAttack.class);

	}


	@Test(timeout = 1000)
	public void testDragonWishClassVariablesAccessibility() throws Exception{
		Field f = DragonWish.class.getDeclaredField("type");
		assertEquals(
				"\"type\" instance variable in class DragonWish should not be accessed outside that class",
				2, f.getModifiers());

		f = DragonWish.class.getDeclaredField("senzuBeans");
		assertEquals(
				"\"senzuBeans\" instance variable in class DragonWish should not be accessed outside that class",
				2, f.getModifiers());

		f = DragonWish.class.getDeclaredField("abilityPoints");
		assertEquals(
				"\"abilityPoints\" instance variable in class DragonWish should not be accessed outside that class",
				2, f.getModifiers());

		f = DragonWish.class.getDeclaredField("superAttack");
		assertEquals(
				"\"superAttack\" instance variable in class DragonWish should not be accessed outside that class",
				2, f.getModifiers());

		f = DragonWish.class.getDeclaredField("ultimateAttack");
		assertEquals(
				"\"ultimateAttack\" instance variable in class DragonWish should not be accessed outside that class",
				2, f.getModifiers());
	}


	@Test(timeout = 1000)
	public void testDragonWishClassREADVariables() throws Exception{
		Method[] methods = DragonWish.class.getDeclaredMethods();

		assertTrue("The \"type\" instance variable in class DragonWish is a READ variable.",
				containsMethodName(methods, "getType"));

		assertTrue("The \"senzuBeans\" instance variable in class DragonWish is a READ variable.",
				containsMethodName(methods, "getSenzuBeans"));

		assertTrue("The \"abilityPoints\" instance variable in class DragonWish is a READ variable.",
				containsMethodName(methods, "getAbilityPoints"));

		assertTrue("The \"superAttack\" instance variable in class DragonWish is a READ variable.",
				containsMethodName(methods, "getSuperAttack"));

		assertTrue("The \"ultimateAttack\" instance variable in class DragonWish is a READ variable.",
				containsMethodName(methods, "getUltimateAttack"));

		Method m;
		try{
			m = DragonWish.class.getDeclaredMethod("getType");
			assertTrue("incorrect return type for \"getType\" method in DragonWish class.",
					m.getReturnType().equals(DragonWishType.class));

		}
		catch(Exception e){
			fail("Missing method \"getType\" in class DragonWish which takes no input parameters");
		}

		try{
			m = DragonWish.class.getDeclaredMethod("getSenzuBeans");
			assertTrue("incorrect return type for \"getSenzuBeans\" method in DragonWish class.",
					m.getReturnType().equals(int.class));

		}catch(Exception e){
			fail("Missing method \"getSenzuBeans\" in class DragonWish which takes no input parameters");
		}

		try{
			m = DragonWish.class.getDeclaredMethod("getAbilityPoints");
			assertTrue("incorrect return type for \"getAbilityPoints\" method in DragonWish class.",
					m.getReturnType().equals(int.class));

		}catch(Exception e){
			fail("Missing method \"getAbilityPoints\" in class DragonWish which takes no input parameters");
		}

		try{
			m = DragonWish.class.getDeclaredMethod("getSuperAttack");
			assertTrue("incorrect return type for \"getSuperAttack\" method in DragonWish class.",
					m.getReturnType().equals(SuperAttack.class));

		}catch(Exception e){
			fail("Missing method \"getSuperAttack\" in class DragonWish which takes no input parameters");
		}

		try{
			m = DragonWish.class.getDeclaredMethod("getUltimateAttack");
			assertTrue("incorrect return type for \"getUltimateAttack\" method in DragonWish class.",
					m.getReturnType().equals(UltimateAttack.class));

		}catch(Exception e){
			fail("Missing method \"getUltimateAttack\" in class DragonWish which takes no input parameters");
		}

	}


	@Test(timeout = 1000)
	public void testDragonWishClassWRITEVariables() throws Exception{
		Method[] methods = DragonWish.class.getDeclaredMethods();

		assertFalse("The \"type\" instance variable in class DragonWish is a READ ONLY variable.",
				containsMethodName(methods, "setType"));

		assertFalse("The \"senzuBeans\" instance variable in class DragonWish is a READ ONLY variable.",
				containsMethodName(methods, "setSenzuBeans"));

		assertFalse("The \"abilityPoints\" instance variable in class DragonWish is a READ ONLY variable.",
				containsMethodName(methods, "setAbilityPoints"));

		assertFalse("The \"superAttack\" instance variable in class DragonWish is a READ ONLY variable.",
				containsMethodName(methods, "setSuperAttack"));

		assertFalse("The \"ultimateAttack\" instance variable in class DragonWish is a READ ONLY variable.",
				containsMethodName(methods, "setUltimateAttack"));
	}


	@Test(timeout = 1000)
	public void testGameClassConstructorInitialization() throws IOException{
		Game game = new Game();
		assertEquals(" The contructor of class Game should initialize the variable state to WORLD", GameState.WORLD, game.getState());	
	}


	@Test(timeout = 1000)
	public void testWorldClassConstructorInitialization() throws IOException{
		Game game = new Game();
		assertEquals("After creating a new game, the variable playerColumn should be initialized to be in column 9", 9, game.getWorld().getPlayerColumn());
		assertEquals("After creating a new game, the variable playerRow should be initialized to be in row 9", 9, game.getWorld().getPlayerRow());
	}


	@Test(timeout = 1000)
	public void testAttackClassMethodsAbstraction() throws Exception{
		try{
			Method m = Attack.class.getDeclaredMethod("getAppliedDamage", BattleOpponent.class);
			assertTrue("incorrect modifier for \"getAppliedDamage\" method in  Attack class",
					Modifier.isAbstract(m.getModifiers()));
		}
		catch(Exception e){
			fail("Method getAppliedDamage with one parameter of type BattleOpponent is missing in class Attack");		
		}
	}


	@Test(timeout = 1000)
	public void testDragonWishClassExtendsEventObject() throws Exception{
		assertEquals("DragonWish class should extend EventObject", EventObject.class, DragonWish.class.getSuperclass());
	}


	@Test(timeout = 1000)
	public void testBattleEventClassExtendsEventObject() throws Exception{
		assertEquals("BattleEvent class should extend EventObject", EventObject.class, BattleEvent.class.getSuperclass());
	}

	// ************************ Listeners tests *************************************//

	@Test(timeout = 1000)
	public void testPlayerClassCallDragonMethodListeners() throws Exception {
		hasCalled = false;
		Game s = new Game() {
			public void onDragonCalled() {
				hasCalled = true;
			}
		};
		Player p = null ;
		try{
			p = s.getPlayer();
		}catch (NullPointerException e) {
			fail("The game constructor should intialize th");
		}
		p.setDragonBalls(7);
		try {
			p.callDragon();
		} catch (NullPointerException e) {
			fail("The method callDragon in Player class should handle when listener is null");
		}
		invokeSetter(p, PlayerListener.class, s);
		p.callDragon();
		assertTrue(
				"Player class should notify the Game class when a Dragon is called",
				hasCalled);
	}

	@Test(timeout = 1000)
	public void testPlayerClassChooseWishMethodListeners() throws Exception {
		hasCalled = false;
		Game s = new Game() {
			public void onWishChosen(DragonWish wish) {
				hasCalled = true;
			}
		};
		Player p = s.getPlayer();
		SuperAttack k = new SuperAttack("Kamehameha", 400);
		UltimateAttack f = new UltimateAttack("Final Flash", 700);
		ArrayList<SuperAttack> fightersSuperAttacks = new ArrayList<SuperAttack>();
		fightersSuperAttacks.add(k);
		ArrayList<UltimateAttack> fighterUltimateAttacks = new ArrayList<UltimateAttack>();
		fighterUltimateAttacks.add(f);

		Namekian n = new Namekian("namekian", 2, 100, 200, 300, 400, 500, 5, 6,
				600, fightersSuperAttacks, fighterUltimateAttacks);
		ArrayList<PlayableFighter> fighters = new ArrayList<>();
		fighters.add(n);
		p.setFighters(fighters);
		p.setActiveFighter(n);
		ArrayList<SuperAttack> supers = new ArrayList<SuperAttack>();
		SuperAttack s1 = new SuperAttack("kamehameha", 250);
		supers.add(s1);
		ArrayList<UltimateAttack> ultimates = new ArrayList<UltimateAttack>();
		UltimateAttack u = new UltimateAttack("final flash", 450);
		ultimates.add(u);

		Dragon d = new Dragon("dodo", supers, ultimates, 3, 5);

		p.chooseWish(new DragonWish(d, DragonWishType.ABILITY_POINTS, 3));
		invokeSetter(p, PlayerListener.class, s);
		assertTrue(
				"Player class should notify the Game class when a wish is chosen",
				hasCalled);
	}
	@Test(timeout = 1000)
	public void testPlayerClassHasListener() throws Exception{
		assertTrue(
				"Player class should have an instance variable of type PlayerListener",
				classContainsField(Player.class, PlayerListener.class));
	}


	@Test(timeout = 1000)
	public void testListenersAreWriteVariables()throws Exception {
		Method[] methods = Player.class.getDeclaredMethods();
		int index = classContainsFieldAtIndex(Player.class,
				PlayerListener.class);
		if (index != -1) {
			Field f = Player.class.getDeclaredFields()[index];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			System.out.println(methodName);
			assertTrue(
					"The \"listener\" instance variable in class Player is a Write variable.",
					containsMethodName(methods, methodName));
		}

		methods = Cell.class.getDeclaredMethods();
		index = classContainsFieldAtIndex(Cell.class, CellListener.class);
		if (index != -1) {
			Field f = Cell.class.getDeclaredFields()[index];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			assertTrue(
					"The \"listener\" instance variable in class Cell is a Write variable.",
					containsMethodName(methods, methodName));
		}

		methods = Battle.class.getDeclaredMethods();
		index = classContainsFieldAtIndex(Battle.class, BattleListener.class);
		if (index != -1) {
			Field f = Battle.class.getDeclaredFields()[index];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			assertTrue(
					"The \"listener\" instance variable in class Battle is a Write variable.",
					containsMethodName(methods, methodName));
		}

		methods = World.class.getDeclaredMethods();
		index = classContainsFieldAtIndex(World.class, WorldListener.class);
		if (index != -1) {
			Field f = World.class.getDeclaredFields()[index];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			assertTrue(
					"The \"listener\" instance variable in class World is a Write variable.",
					containsMethodName(methods, methodName));
		}
		methods = Game.class.getDeclaredMethods();
		index = classContainsFieldAtIndex(Game.class, GameListener.class);
		if (index != -1) {
			Field f = Game.class.getDeclaredFields()[index];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			assertTrue(
					"The \"listener\" instance variable in class Game is a Write variable.",
					containsMethodName(methods, methodName));
		}
	}

	@Test(timeout = 1000)
	public void testGameClassOnBattleEventMethodListeners() throws Exception {
		hasCalled = false;
		GameController s = new GameController() {
			public void onBattleEvent(BattleEvent e) {
				hasCalled = true;
				type = e.getType();
			}
		};
		Battle b = new Battle(new Saiyan("saiyan"), new NonPlayableFighter(
				"foe", 1, 1, 1, 1, 1, 1, true, null, null));
		BattleEvent be = new BattleEvent(b, BattleEventType.ATTACK);
		Game g = new Game();
		g.onBattleEvent(be);
		invokeSetter(g, GameListener.class, s);
		g.onBattleEvent(be);
		assertTrue("Game class should notify the GameListener on BattleEvent",
				hasCalled);
		assertEquals(
				"The Battle Event type notified from GameListener from the Game class is incorrect",
				BattleEventType.ATTACK, type);
	}

	@Test(timeout = 1000)
	public void testGameClassOnDragonCalledMethodListeners() throws Exception {
		hasCalled = false;
		GameController s = new GameController() {
			public void onDragonCalled(Dragon dragon) {
				hasCalled = true;
			}
		};
		Game g = new Game();
		g.onDragonCalled();

		invokeSetter(g, GameListener.class, s);
		g.onDragonCalled();
		assertTrue(
				"Game class should notify the GameListener when a dragon is chosen",
				hasCalled);
	}

	@Test(timeout = 1000)
	public void testGameClassOnCollectibleFoundMethodListeners()
			throws Exception {
		hasCalled = false;
		GameController s = new GameController() {
			public void onCollectibleFound(Collectible collectible) {
				hasCalled = true;
			}
		};
		Game g = new Game();
		invokeSetter(g, GameListener.class, s);
		g.onCollectibleFound(Collectible.SENZU_BEAN);
		assertTrue(
				"Game class should notify the GameListener when a collectible is found",
				hasCalled);
	}

	@Test(timeout = 1000)
	public void testGameClassHasListener() throws Exception{
		assertTrue(
				"Game class should have an instance variable of type GameListener",
				classContainsField(Game.class, GameListener.class));
	}


	@Test(timeout = 1000)
	public void testWorldClassHasListener() throws Exception{
		assertTrue(
				"World class should have an instance variable of type WorldListener",
				classContainsField(World.class, WorldListener.class));
	}

	
	@Test(timeout = 1000)
	public void testCellClassHasListener() throws Exception{
		assertTrue(
				"Cell class should have an instance variable of type CellListener",
				classContainsField(Cell.class, CellListener.class));
	}


	@Test(timeout = 1000)
	public void testBattleClassHasListener() throws Exception{
		assertTrue(
				"Battle class should have an instance variable of type BattleListener",
				classContainsField(Battle.class, BattleListener.class));
	}
	// **************************** Functionality methods ********************************//
	@Test(timeout = 1000)
	public void testSuperAssignAttackMethod()throws Exception {
		ArrayList<SuperAttack> superAttacks1 = new ArrayList<SuperAttack>();
		SuperAttack s1 = new SuperAttack("super1", 50);
		SuperAttack s2 = new SuperAttack("super2", 50);
		SuperAttack s3 = new SuperAttack("super2", 50);

		superAttacks1.add(s1);
		superAttacks1.add(s2);
		superAttacks1.add(s3);
		Earthling fighter1 = new Earthling("fighter1"); 
		fighter1.setSuperAttacks(superAttacks1);

		ArrayList<SuperAttack> superAttacks2 = new ArrayList<SuperAttack>();
		SuperAttack s4 = new SuperAttack("super4", 50);
		SuperAttack s5 = new SuperAttack("super5", 50);
		SuperAttack s6 = new SuperAttack("super6", 50);
		SuperAttack s7 = new SuperAttack("super7", 50);

		superAttacks2.add(s4);
		superAttacks2.add(s5);
		superAttacks2.add(s6);
		superAttacks2.add(s7);
		Earthling fighter2 = new Earthling("fighter2");
		fighter2.setSuperAttacks(superAttacks2);

		ArrayList<PlayableFighter> fighters= new ArrayList<PlayableFighter>();
		fighters.add(fighter1);
		fighters.add(fighter2);

		Player player = new Player("player");
		player.setFighters(fighters);

		SuperAttack superAttack = new SuperAttack("super", 50);
		System.out.println(fighter1.getSuperAttacks().size()+ " "+ superAttack+" "+s1);

		player.assignAttack(fighter1, superAttack, s1);
		assertTrue("assignAttack method in class Player should add the new super Attack to the list of superAttacks of the input fighter", player.getFighters().get(0).getSuperAttacks().contains(superAttack));
		assertFalse("assignAttack method in class Player should remove the old super attack from the list of superAttacks of the input fighter", player.getFighters().get(0).getSuperAttacks().contains(s1));

		player.assignAttack(fighter1, superAttack, null);
		assertTrue("assignAttack method in class Player should add the new super Attack to the list of superAttacks of the input fighter", player.getFighters().get(0).getSuperAttacks().contains(superAttack));

		player.assignAttack(fighter2, superAttack, null);
		assertFalse("assignAttack method in class Player should not add the new super if the list of ultimate attacks of the input fighter is full", player.getFighters().get(1).getSuperAttacks().contains(superAttack));

	}

	@Test(timeout = 1000)
	public void testCreateFighterMethod()throws Exception {
		Player player = new Player("player");

		player.createFighter('E', "earthling");
		assertEquals("createFighter method in class Player should add the corresponding new fighter in the list of fighters",
				1, player.getFighters().size());
		assertEquals("createFighter method should create a new fighter of type Earthling when the char input is E", 
				Earthling.class, player.getFighters().get(player.getFighters().size() - 1).getClass());

		assertEquals("After creating a fighter, it should be set as the active fighter if it is the first fighter for the player", 
				player.getFighters().get(0), player.getActiveFighter());

		Fighter tmp = player.getFighters().get(0);
		player.createFighter('S', "saiyan");
		assertEquals("createFighter method in class Player should add the corresponding new fighter in the list of fighters",
				2, player.getFighters().size());
		assertEquals("createFighter method should create a new fighter of type Saiyan when the char input is S", 
				Saiyan.class, player.getFighters().get(player.getFighters().size() - 1).getClass());


		assertEquals("A newly created fighter should be set as the active fighter only if it is the first fighter for the player", 
				tmp, player.getActiveFighter());


		player.createFighter('N', "namekian");
		assertEquals("createFighter method in class Player should add the corresponding new fighter in the list of fighters",
				3, player.getFighters().size());
		assertEquals("createFighter method should create a new fighter of type Namekian when the char input is N", 
				Namekian.class, player.getFighters().get(player.getFighters().size() - 1).getClass());

		player.createFighter('F', "frieza");
		assertEquals("createFighter method in class Player should add the corresponding new fighter in the list of fighters",
				4, player.getFighters().size());
		assertEquals("createFighter method should create a new fighter of type Frieza when the char input is F", 
				Frieza.class, player.getFighters().get(player.getFighters().size() - 1).getClass());

		player.createFighter('M', "majin");
		assertEquals("createFighter method in class Player should add the corresponding new fighter in the list of fighters",
				5, player.getFighters().size());
		assertEquals("createFighter method should create a new fighter of type Majin when the char input is M", 
				Majin.class, player.getFighters().get(player.getFighters().size() - 1).getClass());

		assertEquals("A newly created fighter should be set as the active fighter only if it is the first fighter for the player", 
				player.getFighters().get(0), player.getActiveFighter());
	}


	@Test(timeout = 1000)
	public void testSetKiMethodBoundaries() throws Exception{
		PlayableFighter fighter = new Earthling("Goku", 1, 1, 1, 1, 1, 1, 1, 5,
				1, null, null);
		fighter.setKi(4);
		assertEquals(
				"The ki should be correctly set according to the input of method setKi",
				4, fighter.getKi());

		fighter.setKi(10);
		assertEquals("The ki cannot be set to a number that exceeds maxKi",
				fighter.getMaxKi(), fighter.getKi());

		fighter.setKi(-1);
		assertEquals("The ki cannot be set to a number that is less than 0", 0,
				fighter.getKi());
	}

	@Test(timeout = 1000)
	public void testSetXpMethod() throws Exception{
		PlayableFighter fighter = new Earthling("Goku", 1, 1, 100, 1, 1, 1, 1,
				5, 1, null, null);
		fighter.setXp(10);
		assertEquals(
				"The xp should be correctly set according to the corresponding input in method setXp",
				10, fighter.getXp());

		fighter.setXp(100);
		assertEquals(
				"Any fighter should level up once the xp reaches targetXp", 2,
				fighter.getLevel());
		assertEquals(
				"The ability points of a fighter should increase by 2 on leveling up",
				3, fighter.getAbilityPoints());
		assertEquals(
				"The target xp of a fighter should increase by 20 on leveling up",
				120, fighter.getTargetXp());
		assertEquals(
				"The xp should be reset to zero after the fighter levels up",
				0, fighter.getXp());


		fighter = new Earthling("Goku", 1, 1, 100, 1, 1, 1, 1, 5, 1, null, null);
		fighter.setXp(360);
		assertEquals(
				"Any fighter should keep level up as long as his xp is more than the new targetXp",
				4, fighter.getLevel());
		assertEquals(
				"The ability points of a fighter should increase by 2 on each level up",
				7, fighter.getAbilityPoints());
		assertEquals(
				"The target xp of a fighter should increase by 20 on each level up",
				160, fighter.getTargetXp());
		assertEquals(
				"The xp should be reset to zero after the fighter levels up",
				0, fighter.getXp());
	}
	@Test(timeout = 1000)
	public void testChooseWishMethod()throws Exception {
		PlayableFighter activeFighter = new Earthling("Goku", 1, 1, 1, 1, 1, 1,
				1, 1, 1, null, null);
		Player player = new Player("player", new ArrayList<PlayableFighter>(), new ArrayList<SuperAttack>(), new ArrayList<UltimateAttack>(), 1, 1,
				activeFighter, 1);
		Dragon dragon = new Dragon("Shenron", null, null, 1, 1);
		SuperAttack s = new SuperAttack("Kamehameha", 200);
		UltimateAttack u = new UltimateAttack("Break Cannon", 400);
		DragonWish wish;


		wish = new DragonWish(dragon, DragonWishType.ABILITY_POINTS, 3);
		player.chooseWish(wish);
		assertEquals("chooseWish method in class Player should increase the ability points "
				+ "according to the corresponding number of ability points in the dragonWish",
				4, player.getActiveFighter().getAbilityPoints());


		wish = new DragonWish(dragon, DragonWishType.SENZU_BEANS, 4);
		player.chooseWish(wish);
		assertEquals("chooseWish method in class Player should increase the senzubeans "
				+ "according to the corresponding number of senzubeans in the dragonWish",
				5, player.getSenzuBeans());


		wish = new DragonWish(dragon, DragonWishType.SUPER_ATTACK, s);
		player.chooseWish(wish);
		Boolean flag = false;
		for (int i = 0; i < player.getSuperAttacks().size(); i++) {
			if (player.getSuperAttacks().get(i).equals(s)) {
				flag = true;
			}
		}
		assertTrue("chooseWish method should add the correct super attack "
				+ "to the list of super attacks in class Player according to the dragon wish", flag);


		wish = new DragonWish(dragon, DragonWishType.ULTIMATE_ATTACK, u);
		player.chooseWish(wish);
		flag = false;
		for (int i = 0; i < player.getUltimateAttacks().size(); i++) {
			if (player.getUltimateAttacks().get(i).equals(u)) {
				flag = true;
			}
		}
		assertTrue("chooseWish method should add the correct ultimate attack "
				+ "to the list of ultimate attacks in class Player according to the dragon wish", flag);

	}


	@Test(timeout = 1000)
	public void testOnAttackerTurn_OnDefenderTurnMethods_Saiyan_Majin()throws Exception {


		Saiyan saiyan = new Saiyan("Saiyan"); 
		saiyan.setStamina(0);

		Majin majin = new Majin("Majin");
		majin.setStamina(0);
		majin.setHealthPoints(1500);

		majin.onAttackerTurn();
		assertEquals("OnAttackerTurn should not add one stamina for a majin fighter", 0, majin.getStamina());	
		assertEquals("OnAttackerTurn should not affect any attributes other than the stamina for a majin fighter", 0, majin.getKi());
		assertEquals( "OnAttackerTurn should not affect any attributes other than the stamina for a majin fighter", 1500, majin.getHealthPoints());

		majin.onDefenderTurn();
		assertEquals("OnDefenderTurn should add one stamina for a majin fighter", 1, majin.getStamina());
		assertEquals("OnDefenderTurn should not affect any attributes other than the stamina for a majin fighter", 0, majin.getKi());
		assertEquals( "OnDefenderTurn should not affect any attributes other than the stamina for a majin fighter", 1500, majin.getHealthPoints());



		saiyan.onAttackerTurn();
		assertEquals("OnAttackerTurn should add one stamina for a saiyan fighter", 1, saiyan.getStamina());	
		saiyan.onDefenderTurn();
		assertEquals("OnDefenderTurn should add one stamina for a saiyan fighter", 2, saiyan.getStamina());

		saiyan.setTransformed(true);
		saiyan.setKi(2);
		saiyan.onAttackerTurn();
		assertEquals("OnAttackerTurn should decrease one ki bar from a saiyan fighter as long as he is transformed", 1, saiyan.getKi());	
		saiyan.onDefenderTurn();
		assertEquals("OnDefenderTurn should decrease one ki bar from a saiyan fighter as long as he is transformed", 0, saiyan.getKi());
		assertFalse("A transformed saiyan should no longer be transformed once his Ki bars reach zero", saiyan.isTransformed());
		assertEquals("A transformed saiyan should have Stamina equal to zero once his Ki bars reach zero", 0, saiyan.getStamina());
	}

	@Test(timeout = 1000)
	public void testMoveDown() throws Exception {
		Game g;

		g = new Game();
		World w = g.getWorld();
		clearWorld(w.getMap());
		Field f;
		f = w.getClass().getDeclaredField("playerRow");
		f.setAccessible(true);
		f.set(w, 8);

		f = w.getClass().getDeclaredField("playerColumn");
		f.setAccessible(true);
		f.set(w, 8);

		int oldPlayerRow = g.getWorld().getPlayerRow();
		g.getWorld().moveDown();
		int newPlayerRow = g.getWorld().getPlayerRow();
		assertEquals(
				"moveDown method in class World should move the player one cell down if possible",
				oldPlayerRow + 1, newPlayerRow);

		g.getWorld().moveDown();
		int playerRow = g.getWorld().getPlayerRow();
		assertEquals(
				"moveDown method in class World should handle out of boundaries",
				playerRow, 9);

	}
	@Test(timeout = 1000)
	public void testMoveLeft() throws Exception {
		Game g = new Game();
		World w = g.getWorld();
		clearWorld(w.getMap());
		Field f;
		f = w.getClass().getDeclaredField("playerRow");
		f.setAccessible(true);
		f.set(w, 9);

		f = w.getClass().getDeclaredField("playerColumn");
		f.setAccessible(true);
		f.set(w, 1);

		int oldPlayerColumn = g.getWorld().getPlayerColumn();
		g.getWorld().moveLeft();
		int newPlayerColumn = g.getWorld().getPlayerColumn();
		assertEquals(
				"moveLeft method in class World should move the player one cell to the left if possible",
				oldPlayerColumn - 1, newPlayerColumn);

		g.getWorld().moveLeft();
		int playerColumn = g.getWorld().getPlayerColumn();
		assertEquals(
				"moveLeft method in class World should handle out of boundaries",
				playerColumn, 0);
	}
	@Test(timeout = 1000)
	public void testToStringCollectible() throws Exception {
		Collectible c = Collectible.SENZU_BEAN;
		String x = c.toString();
		assertEquals(
				"The toString method in class Collectible is not returning the correct String",
				"Senzu Bean", x);

		c = Collectible.DRAGON_BALL;
		x = c.toString();
		assertEquals(
				"The toString method in class Collectible is not returning the correct String",
				"Dragon Ball", x);
	}

	@Test(timeout = 1000)
	public void testOnDragonCalledGame() throws Exception {

		GameController s = new GameController() {
			public void onDragonCalled(Dragon d) {
				hasCalled = true;
				dragon = d;
			}
		};
		Game g = new Game();
		invokeSetter(g, GameListener.class, s);
		ArrayList<Dragon> dragons = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			g.onDragonCalled();
			dragons.add(dragon);
		}


		boolean random = false;
		for (int i = 0; i < dragons.size() - 1; i++)
			if (!dragons.get(i).getName()
					.equals(dragons.get(i + 1).getName()))
				random = true;
		assertTrue("On collecting 7 dragon balls, the dragon should be chosen randomly", random);


	}

	@Test(timeout = 1000)
	public void testOnWinScenarioFighterDefeatsBoss() throws Exception {
		Game g = new Game();
		World w = g.getWorld();
		int oldExploredMaps = g.getPlayer().getExploredMaps();
		World oldWorld = g.getWorld();
		ArrayList<SuperAttack> foeSuperAttacks = new ArrayList<SuperAttack>();
		foeSuperAttacks.add(new SuperAttack("Kamehameha", 250));
		foeSuperAttacks.add(new MaximumCharge());

		ArrayList<UltimateAttack> foeUltimateAttacks = new ArrayList<UltimateAttack>();
		foeUltimateAttacks.add(new UltimateAttack("Super Kamehameha", 1000));
		foeUltimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));

		NonPlayableFighter foe = ((FoeCell) oldWorld.getMap()[0][0]).getFoe();

		Player p = g.getPlayer();
		SuperAttack k = new SuperAttack("Kamehameha", 400);
		UltimateAttack f = new UltimateAttack("Final Flash", 5000);
		ArrayList<SuperAttack> fightersSuperAttacks = new ArrayList<SuperAttack>();
		fightersSuperAttacks.add(k);
		ArrayList<UltimateAttack> fighterUltimateAttacks = new ArrayList<UltimateAttack>();
		fighterUltimateAttacks.add(f);

		Namekian n = new Namekian("namekian", 2, 100, 500, 300, 400, 500, 5, 6,
				600, fightersSuperAttacks, fighterUltimateAttacks);
		ArrayList<PlayableFighter> fighters = new ArrayList<PlayableFighter>();
		fighters.add(n);
		p.setFighters(fighters);
		p.setActiveFighter(n);

		int oldAbilityPoints = n.getAbilityPoints();
		int oldLevel = n.getLevel();
		int oldXP = n.getXp();
		//System.err.println(oldXP);
		GameController controller = new GameController() {
			public void onBattleEvent(BattleEvent battleEvent) {
				b = (Battle)battleEvent.getSource();
			}
		};

		Field ff = w.getClass().getDeclaredField("playerColumn");
		ff.setAccessible(true);
		ff.set(w, 0);

		ff = w.getClass().getDeclaredField("playerRow");
		ff.setAccessible(true);
		ff.set(w, 1);

		invokeSetter(g, GameListener.class, controller);

		w.moveUp();

		if(b==null)
			fail("When meeting a Foe, a new battle should be created, and STARTED event type should be sent to the Battle Listener");

		p.getActiveFighter().setKi(6);
		
		b.start();
		b.attack(n.getUltimateAttacks().get(0));
		int foeHealth = foe.getHealthPoints();
		System.out.println("foe Health " + foeHealth);
		System.err.println("foe Level " + foe.getLevel());
		//System.err.println(oldXP);
		// after winning we should test:
		// 1. the xp points are set to foe's level *5
		assertEquals("The Xp points of the player is not changed correctly after defeating a boss",
				(foe.getLevel() * 5) + oldXP, p.getActiveFighter().getXp());
		

		// unlock foe's super and ultimate attacks i.e. skills
		// test that there are no duplicates
		assertFalse(
				"On winning, the winner shouldn't gain a super attack that he already has",
				containDuplicatesSuperAttacks(p.getSuperAttacks()));

		assertFalse(
				"On winning, the winner shouldn't gain a super attack that he already has",
				containDuplicatesUltimateAttacks(p.getUltimateAttacks()));

		ArrayList<SuperAttack> newPlayerSuperAttacks = new ArrayList<>();
		for (SuperAttack foSuperAttack : p.getSuperAttacks())
			newPlayerSuperAttacks.add(foSuperAttack);

		for (SuperAttack foSuperAttack : foeSuperAttacks)
			if (!newPlayerSuperAttacks.contains(foSuperAttack))
				newPlayerSuperAttacks.add(foSuperAttack);

		assertTrue(
				"On winning, the fighter should gain the foe's super attacks",
				superAttacksListsAreEqual(p.getSuperAttacks(),
						newPlayerSuperAttacks));

		ArrayList<UltimateAttack> newPlayerUltimateAttacks = new ArrayList<>();
		for (UltimateAttack ulAttack : p.getUltimateAttacks())
			newPlayerUltimateAttacks.add(ulAttack);

		for (UltimateAttack ulSuperAttack : foeUltimateAttacks)
			if (!newPlayerUltimateAttacks.contains(ulSuperAttack))
				newPlayerUltimateAttacks.add(ulSuperAttack);
		assertTrue(
				"On winning, the fighter should gain the foe's ultimate attacks",
				ultimateAttacksListsAreEqual(p.getUltimateAttacks(),
						newPlayerUltimateAttacks));
		// gain 2 ability points, in case the gained xp causes the fighter to
		// level up
		if (n.getLevel() - oldLevel == 1)
			assertEquals(
					"The fighter should gain 2 ability points, in case the gained xp causes the fighter to level up",
					oldAbilityPoints + 1, n.getAbilityPoints());

		// unlock new map, in case the opponent was a boss and exploredMaps
		// should be incremented by one; test that the map changes
		assertEquals(
				"On winning a boss, the player's explored maps should be incremented by one",
				oldExploredMaps + 1, g.getPlayer().getExploredMaps());

		System.out.println(TwoMapsDifferent(oldWorld.getMap(), g.getWorld()
				.getMap()));
		assertTrue("On winning a boss, a new world should be generated",
				TwoMapsDifferent(oldWorld.getMap(), g.getWorld().getMap()));

		assertEquals(
				"Game State should be changed to WORLD when a battle ends",
				GameState.WORLD, g.getState());
	}


	@Test(timeout = 1000)
	public void testOnCollectibleFoundUponCollectingSevenDragonBalls()
			throws Exception {
		Game g = new Game();
		World w = g.getWorld();
		hasCalled = false;
		Player pp = new Player("player") {
			public void callDragon() {
				hasCalled = true;
			}
		};
		Field f = g.getClass().getDeclaredField("player");
		f.setAccessible(true);
		f.set(g, pp);

		try {
			pp.setDragonBalls(6);
		} catch (NullPointerException e) {
			fail("Player should be intialized in the Game class");
		}

		w.getMap()[8][9] = new CollectibleCell(Collectible.DRAGON_BALL);
		Field[] fields = Cell.class.getDeclaredFields();
		int i = -1;
		for (int j = 0; j < fields.length; j++) {
			if (fields[j].getType().equals(CellListener.class)) {
				i = j;
				break;
			}
		}

		if (i != -1) {
			f = Cell.class.getDeclaredFields()[i];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			System.out.println(methodName);
			Method setter = Cell.class
					.getMethod(methodName, CellListener.class);
			try {
				setter.invoke(w.getMap()[8][9], w);
			} catch (Exception e) {
				fail("The class: " + w.getMap()[8][9].getClass()
						+ " should have a setter method for the listener");
			}
		}

		g.getPlayer().setDragonBalls(6);
		w.moveUp();
		assertTrue(
				"Game class should call callDragon() method in Player class upon collecting 7 dragon balls",
				hasCalled);

	}
	
	@Test(timeout = 1000)
	public void testOnCollectibleFoundTurnsTheCellToAnEmptyCell() throws Exception{
		Game g = new Game();
		World w = g.getWorld();
		Field f;

		w.getMap()[8][9] = new CollectibleCell(Collectible.DRAGON_BALL);
		Field[] fields = Cell.class.getDeclaredFields();
		int i = -1;
		for (int j = 0; j < fields.length; j++) {
			if (fields[j].getType().equals(CellListener.class)) {
				i = j;
				break;
			}
		}

		if (i != -1) {
			f = Cell.class.getDeclaredFields()[i];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			System.out.println(methodName);
			Method setter = Cell.class
					.getMethod(methodName, CellListener.class);
			try {
				setter.invoke(w.getMap()[8][9], w);
			} catch (Exception e) {
				fail("The class: " + w.getMap()[8][9].getClass()
						+ " should have a setter method for the listener");
			}
		}
		g.getPlayer().setDragonBalls(4);
		w.moveUp();
		assertEquals("On collecting a dragon ball, the old collectible cell should be turned to an empty cell",EmptyCell.class, w.getMap()[8][9].getClass());

		w.moveDown();
		w.getMap()[8][9] = new CollectibleCell(Collectible.SENZU_BEAN);

		if (i != -1) {
			f = Cell.class.getDeclaredFields()[i];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			System.out.println(methodName);
			Method setter = Cell.class
					.getMethod(methodName, CellListener.class);
			try {
				setter.invoke(w.getMap()[8][9], w);
			} catch (Exception e) {
				fail("The class: " + w.getMap()[8][9].getClass()
						+ " should have a setter method for the listener");
			}
		}
		w.moveUp();
		assertEquals("On collecting a senzu bean, the old collectible cell should be turned to an empty cell",EmptyCell.class, w.getMap()[8][9].getClass());
	}

	@Test(timeout = 1000)
	public void testGameStateChangesToBattleOnMeetingAFoe() throws Exception {
		Game g = new Game();
		World w = g.getWorld();
		ArrayList<SuperAttack> foeSuperAttacks = new ArrayList<SuperAttack>();
		foeSuperAttacks.add(new SuperAttack("Kamehameha", 250));
		foeSuperAttacks.add(new MaximumCharge());

		ArrayList<UltimateAttack> foeUltimateAttacks = new ArrayList<UltimateAttack>();
		foeUltimateAttacks.add(new UltimateAttack("Super Kamehameha", 450));
		foeUltimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));

		NonPlayableFighter foe = new NonPlayableFighter("Goku", 10, 3000, 350,
				400, 5, 6, false, foeSuperAttacks, foeUltimateAttacks);
		w.getMap()[8][9] = new FoeCell(foe);
		Field[] fields = Cell.class.getDeclaredFields();
		int i = -1;
		for (int j = 0; j < fields.length; j++) {
			if (fields[j].getType().equals(CellListener.class)) {
				i = j;
				break;
			}
		}

		if (i != -1) {
			Field f = Cell.class.getDeclaredFields()[i];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			System.out.println(methodName);
			Method setter = Cell.class
					.getMethod(methodName, CellListener.class);
			try {
				setter.invoke(w.getMap()[8][9], w);
			} catch (Exception e) {
				fail("The class: " + w.getMap()[8][9].getClass()
						+ " should have a setter method for the listener");
			}
		}

		Player p = g.getPlayer();
		SuperAttack k = new SuperAttack("Kamehameha", 400);
		UltimateAttack f = new UltimateAttack("Final Flash", 700);
		ArrayList<SuperAttack> fightersSuperAttacks = new ArrayList<SuperAttack>();
		fightersSuperAttacks.add(k);
		ArrayList<UltimateAttack> fighterUltimateAttacks = new ArrayList<UltimateAttack>();
		fighterUltimateAttacks.add(f);

		Namekian n = new Namekian("namekian", 2, 100, 200, 300, 400, 500, 5, 6,
				600, fightersSuperAttacks, fighterUltimateAttacks);
		ArrayList<PlayableFighter> fighters = new ArrayList<PlayableFighter>();
		fighters.add(n);
		p.setFighters(fighters);
		p.setActiveFighter(n);
		w.moveUp();
		assertEquals("Game state should be changed to BATTLE on meeting a foe",
				GameState.BATTLE, g.getState());

	}


	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Test(timeout = 1000)
	public void testBattleEventFirstConstructor()throws Exception {
		Class aClass = BattleEvent.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					Battle.class, BattleEventType.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 2 parametesr in BattleEvent class.",
				thrown);
	}

	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Test(timeout = 1000)
	public void testBattleEventSecondConstructor() throws Exception{
		Class aClass = BattleEvent.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass
					.getConstructor(new Class[] { Battle.class,
							BattleEventType.class, BattleOpponent.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 3 parametesr in BattleEvent class.",
				thrown);
	}

	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Test(timeout = 1000)
	public void testBattleEventThirdConstructor() throws Exception{
		Class aClass = BattleEvent.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					Battle.class, BattleEventType.class, Attack.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 3 parametesr in BattleEvent class.",
				thrown);
	}

	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Test(timeout = 1000)
	public void testBattleEventFourthConstructor() throws Exception{
		Class aClass = BattleEvent.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					Battle.class, BattleEventType.class, Collectible.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 3 parametesr in Battle Event class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testBattleEventFirstConstructorInitialization() throws Exception{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		Battle b = new Battle(e, strong);
		BattleEvent be = new BattleEvent(b, BattleEventType.ATTACK);
		assertEquals("", Battle.class, ((Battle)(be.getSource())).getClass());
		assertEquals(
				"The constructor of BattleEvent class should initialize the currentOpponent instance variable correctly.",
				b.getAttacker(), be.getCurrentOpponent());
		assertEquals(
				"The constructor of BattleEvent class should initialize the type instance variable correctly.",
				BattleEventType.ATTACK, be.getType());
	}

	@Test(timeout = 1000)
	public void testBattleEventSecondConstructorInitialization() throws Exception{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		Battle b = new Battle(e, strong);
		BattleEvent be = new BattleEvent(b, BattleEventType.ATTACK, e);
		assertEquals("", Battle.class, ((Battle)(be.getSource())).getClass());

		assertEquals(
				"The constructor of BattleEvent class should initialize the currentOpponent instance variable correctly.",
				b.getAttacker(), be.getCurrentOpponent());
		assertEquals(
				"The constructor of BattleEvent class should initialize the type instance variable correctly.",
				BattleEventType.ATTACK, be.getType());
		assertEquals(
				"The constructor of BattleEvent class should initialize the winner instance variable correctly.",
				e, be.getWinner());
	}

	@Test(timeout = 1000)
	public void testBattleEventThirdConstructorInitialization() throws Exception{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		SuperAttack s = new SuperAttack("Death Beam", 250);
		e.getSuperAttacks().add(s);
		Battle b = new Battle(e, strong);
		BattleEvent be = new BattleEvent(b, BattleEventType.ATTACK, s);
		assertEquals("", Battle.class, ((Battle)(be.getSource())).getClass());

		assertEquals(
				"The constructor of BattleEvent class should initialize the currentOpponent instance variable correctly.",
				b.getAttacker(), be.getCurrentOpponent());
		assertEquals(
				"The constructor of BattleEvent class should initialize the type instance variable correctly.",
				BattleEventType.ATTACK, be.getType());
		assertEquals(
				"The constructor of BattleEvent class should initialize the attack instance variable correctly.",
				s, be.getAttack());
	}

	@Test(timeout = 1000)
	public void testBattleEventFourthConstructorInitialization()throws Exception {
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		Battle b = new Battle(e, strong);
		BattleEvent be = new BattleEvent(b, BattleEventType.USE,
				Collectible.SENZU_BEAN);
		assertEquals("", Battle.class, ((Battle)(be.getSource())).getClass());

		assertEquals(
				"The constructor of BattleEvent class should initialize the currentOpponent instance variable correctly.",
				b.getAttacker(), be.getCurrentOpponent());
		assertEquals(
				"The constructor of BattleEvent class should initialize the type instance variable correctly.",
				BattleEventType.USE, be.getType());
		assertEquals(
				"The constructor of BattleEvent class should initialize the collectible instance variable correctly.",
				Collectible.SENZU_BEAN, be.getCollectible());
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Test(timeout=1000)
	public void testDragonWishFirstConstructor()throws Exception
	{
		Class aClass = DragonWish.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					Dragon.class,DragonWishType.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse("Missing constructor with 2 parametesr in DragonWish class.",
				thrown);
	}
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Test(timeout = 1000)
	public void testDragonWishSecondConstructor() throws Exception{
		Class aClass = DragonWish.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					Dragon.class, DragonWishType.class, int.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 3 parametesr in DragonWish class.",
				thrown);
	}

	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Test(timeout = 1000)
	public void testDragonWishThirdConstructor()throws Exception {
		Class aClass = DragonWish.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					Dragon.class, DragonWishType.class, SuperAttack.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 3 parametesr in DragonWish class.",
				thrown);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Test(timeout = 1000)
	public void testDragonWishFourthConstructor()throws Exception {
		Class aClass = DragonWish.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					Dragon.class, DragonWishType.class, UltimateAttack.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 3 parametesr in DragonWish class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testDragonWishSecondConstructorInitialization() throws Exception{
		SuperAttack s = new SuperAttack("Kamehameha", 250);
		UltimateAttack u = new UltimateAttack("Spirit Bomb", 450);
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(s);
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(u);
		Dragon d = new Dragon("Shenron", superAttacks, ultimateAttacks, 5, 7);
		DragonWish dw = new DragonWish(d, DragonWishType.ABILITY_POINTS, 3);
		assertEquals(
				"The constructor of DragonWish class should initialize the type instance variable correctly.",
				DragonWishType.ABILITY_POINTS, dw.getType());
		assertEquals(
				"The constructor of DragonWish class should initialize the abilityPoints instance variable correctly.",
				3, dw.getAbilityPoints());
		assertEquals("The constructor of DragonWish class that initializes the abilityPoints should not change the senzu beans.",
				0, dw.getSenzuBeans());
	}
	
	@Test(timeout = 1000)
	public void testDragonWishSecondConstructorInitialization2()throws Exception {
		SuperAttack s = new SuperAttack("Kamehameha", 250);
		UltimateAttack u = new UltimateAttack("Spirit Bomb", 450);
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(s);
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(u);
		Dragon d = new Dragon("Shenron", superAttacks, ultimateAttacks, 5, 7);
		DragonWish dw = new DragonWish(d, DragonWishType.SENZU_BEANS, 3);
		assertEquals(
				"The constructor of DragonWish class should initialize the type instance variable correctly.",
				DragonWishType.SENZU_BEANS, dw.getType());
		assertEquals(
				"The constructor of BattleEvent class should initialize the senzuBeans instance variable correctly.",
				3, dw.getSenzuBeans());
		assertEquals("The constructor of DragonWish class that initializes senzu beans should not change the ability points.",
				0, dw.getAbilityPoints());
	}

	@Test(timeout = 1000)
	public void testDragonWishThirdConstructorInitialization()throws Exception {
		SuperAttack s = new SuperAttack("Kamehameha", 250);
		UltimateAttack u = new UltimateAttack("Spirit Bomb", 450);
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(s);
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(u);
		Dragon d = new Dragon("Shenron", superAttacks, ultimateAttacks, 5, 7);
		DragonWish dw = new DragonWish(d, DragonWishType.SUPER_ATTACK, s);
		assertEquals(
				"The constructor of DragonWish class should initialize the type instance variable correctly.",
				DragonWishType.SUPER_ATTACK, dw.getType());
	
		boolean ok=dw.getSuperAttack().getDamage()==s.getDamage() &&dw.getSuperAttack().getName().equals(s.getName());
		assertTrue(
				"The constructor of DragonWish class should initialize the superAttack instance variable correctly.",
				ok );
	}

	@Test(timeout = 1000)
	public void testDragonWishFourthConstructorInitialization() throws Exception{
		SuperAttack s = new SuperAttack("Kamehameha", 250);
		UltimateAttack u = new UltimateAttack("Spirit Bomb", 450);
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(s);
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(u);
		Dragon d = new Dragon("Shenron", superAttacks, ultimateAttacks, 5, 7);
		DragonWish dw = new DragonWish(d, DragonWishType.ULTIMATE_ATTACK, u);
		assertEquals(
				"The constructor of DragonWish class should initialize the type instance variable correctly.",
				DragonWishType.ULTIMATE_ATTACK, dw.getType());
	
		boolean ok=dw.getUltimateAttack().getDamage()==u.getDamage() &&dw.getUltimateAttack().getName().equals(u.getName());
		assertTrue(
				"The constructor of DragonWish class should initialize the superAttack instance variable correctly.",
				ok );
	}

	@Test(timeout = 1000)
	public void testBattleOpponentMethods()throws Exception {
		Method m;
		try {
			m = BattleOpponent.class.getDeclaredMethod("onAttackerTurn");
			assertEquals(
					"onAttackerTurn method in BattleOpponent interface does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"BattleOpponent\" interface should have an onAttackerTurn method that takes no parameters.");
		}
		try {
			m = BattleOpponent.class.getDeclaredMethod("onDefenderTurn");
			assertEquals(
					"onFedenderTurn method in BattleOpponent interface does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"BattleOpponent\" interface should have an onAttackerTurn method that takes no parameters.");
		}

	}

	@Test(timeout = 1000)
	public void testFighterAdditionalMethods() throws Exception{

		Method[] methods = Fighter.class.getDeclaredMethods();
		assertTrue("The \"Fighter\" class has a onAttackerTurn method .",
				containsMethodName(methods, "onAttackerTurn"));
		assertTrue("The \"Fighter\" class has a onDefenderTurn method .",
				containsMethodName(methods, "onDefenderTurn"));
	}


	@Test(timeout = 1000)
	public void testWorldClassAdditionalMethods()throws Exception {
		Method m;
		try {
			m = World.class.getDeclaredMethod("resetPlayerPosition");
			assertEquals(
					"resetPlayerPosition method in World class does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"World\" class should have a resetPlayerPosition method which takes no parameters.");
		}
		try {
			m = World.class.getDeclaredMethod("moveUp");
			assertEquals(
					"moveUp method in World class does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"World\" class should have a moveUp method which takes no parameters.");
		}
		try {
			m = World.class.getDeclaredMethod("moveDown");
			assertEquals(
					"moveDown method in World class does not return anything",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"World\" class should have a moveDown method which takes no parameters.");
		}
		try {
			m = World.class.getDeclaredMethod("moveDown");
			assertEquals(
					"moveLeft method in World class does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"World\" class should have a moveLeft method which takes no parameters.");
		}
		try {
			m = World.class.getDeclaredMethod("moveRight");
			assertEquals(
					"moveRight method in World class does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"World\" class should have a moveRight method which takes no parameters.");
		}
	}

	@Test(timeout = 1000)
	public void testCellAndSubclassesAdditionalMethods() throws Exception{
		Method m;
		try {
			m = Cell.class.getDeclaredMethod("onStep");
			assertEquals(
					"onStep method in Cell class does not return anything ",
					void.class, m.getReturnType());
			assertTrue(
					"onStep method in Cell class does not have any default implementation for its subclasses",
					Modifier.isAbstract(m.getModifiers()));

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"Cell\" class should have an onStep method which takes no parameters.");
		}
		try {
			m = EmptyCell.class.getDeclaredMethod("onStep");
			assertEquals(
					"onStep method in EmptyCell class does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"EmptyCell\" class should have a onStep method which takes no parameters.");
		}
		try {
			m = CollectibleCell.class.getDeclaredMethod("onStep");
			assertEquals(
					"onStep method in CollectibleCell class does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"CollectibleCell\" class should have a onStep method which takes no parameters.");
		}
		try {
			m = FoeCell.class.getDeclaredMethod("onStep");
			assertEquals(
					"onStep method in FoeCell class does not return anything ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"FoeCell\" class should have a onStep method which takes no parameters.");
		}
	}

	@Test(timeout = 1000)
	public void testCollectibleAdditionalMethods() throws Exception{
		Method m;
		try {
			m = Collectible.class.getDeclaredMethod("toString");
			assertEquals(
					"toString method in Collectible should return a string ",
					String.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"Collectible\" enum should have a toString method which takes no parameters.");
		}
	}

	@SuppressWarnings("unused")
	@Test(timeout = 1000)
	public void testBattleClassRenamedVariables()throws Exception
	{
		Field f;
		boolean found = false;
		try {
			f = Battle.class.getDeclaredField("currentOpponent");
			found = true;
		} catch (NoSuchFieldException e) {
			found = false;
		}
		assertFalse(
				"currentOpponent instance variable in Battle class should be renamed to attacker",
				found);
		try {
			f = Battle.class.getDeclaredField("attacker");
		} catch (NoSuchFieldException e) {
			fail("Battle class should now have an attacker instance variable");
		}
	}

	@Test(timeout = 1000)
	public void testBattleClassAdditionalVariablesAndMethods() throws Exception{
		boolean found = false;
		Method m;
		try {
			m = Battle.class.getDeclaredMethod("getAttacker");
			assertEquals(
					"getAttacker method in Battle class should return a value of type BattleOpponent ",
					BattleOpponent.class, m.getReturnType());

		}
		catch (NoSuchMethodException e){
			fail("Missing \"getAttacker\" method in Battle class which takes no input parameters");			
		}
		catch( SecurityException e) {
			fail("The \"attacker\" instance variable in Battle class is a READ variable.");
		}
		try {
			found = true;
			m = Battle.class.getDeclaredMethod("setAttacker");

		} catch (NoSuchMethodException | SecurityException e) {
			found = false;
		}
		assertFalse(
				"attacker instance variable in Battle class is READ ONLY variable",
				found);
		try {
			found = true;
			m = Battle.class.getDeclaredMethod("getCurrentOpponent");

		} catch (NoSuchMethodException | SecurityException e) {
			found = false;
		}
		assertFalse(
				"there should not be an instance variable called currentOpponent in Battle class now.",
				found);
		try {
			m = Battle.class.getDeclaredMethod("getAssignedAttacks");
			assertEquals(
					"getAssignedAttacks method in Battle class should return a value of type ArrayList<Attack> ",
					ArrayList.class, m.getReturnType());
		}catch (Exception e){
			fail("Missing \"getAssignedAttacks\" method in Battle class which takes no input parameters");			
		}


		try {
			m = Battle.class.getDeclaredMethod("block");
			assertEquals("block method in Battle class should return nothing ",
					void.class, m.getReturnType());

		}
		catch (Exception e){
			fail("Missing \"block\" method in Battle class which takes no input parameters");			
		}
		try {
			m = Battle.class.getDeclaredMethod("play");

			assertEquals("play method in Battle class should return nothing ",
					void.class, m.getReturnType());
		}catch (Exception e){
			fail("Missing \"play\" method in Battle class which takes no input parameters");			
		}
		try {
			m = Battle.class.getDeclaredMethod("start");
			assertEquals("start method in Battle class should return nothing ",
					void.class, m.getReturnType());
		}catch (Exception e){
			fail("Missing \"start\" method in Battle class which takes no input parameters");			
		}
		try {
			m = Battle.class.getDeclaredMethod("endTurn");
			assertEquals(
					"endTurn method in Battle class should return nothing ",
					void.class, m.getReturnType());
		}catch (Exception e){
			fail("Missing \"endTurn\" method in Battle class which takes no input parameters");			

		}
		try {
			m = Battle.class.getDeclaredMethod("switchTurn");
			assertEquals(
					"switchTurn method in Battle class should return nothing ",
					void.class, m.getReturnType());
		}catch (Exception e){
			fail("Missing \"switchTurn\" method in Battle class which takes no input parameters");			
		}
		try {
			m = Battle.class.getDeclaredMethod("getDefender");
			assertEquals(
					"getDefender method in Battle class should return a value of type BattleOpponet ",
					BattleOpponent.class, m.getReturnType());
		}catch (NoSuchMethodException e){
			fail("Missing \"getDefender\" method in Battle class which takes no input parameters");			
		} catch (SecurityException e) {
			fail("The \"defender\" variable  in Battle class is a READ variable .");
		}
		try {
			m = Battle.class.getDeclaredMethod("use", new Class[] {
					Player.class, Collectible.class });
			assertEquals("use method in Battle class should return nothing ",
					void.class, m.getReturnType());
		}catch (Exception e){
			fail("Missing \"use\" method in Battle class which takes no input parameters");			

		}
		try {
			m = Battle.class.getDeclaredMethod("attack",
					new Class[] { Attack.class });
			assertEquals(
					"attack method in Battle class should return nothing ",
					void.class, m.getReturnType());
		}catch (Exception e){
			fail("Missing \"attack\" method in Battle class which takes no input parameters");			

		} 
	}

	@Test(timeout = 1000)
	public void testAttackAndSubclassesAdditionalMethods()throws Exception {
		Method m;
		try {
			m = Attack.class.getDeclaredMethod("getAppliedDamage",
					new Class[] { BattleOpponent.class });
			assertEquals(
					"getAppliedDamage method in Attack class should return a value of type int ",
					int.class, m.getReturnType());
			assertTrue(
					"getAppliedDamage method in Attack class does not have a default implementation for its subclaases",
					Modifier.isAbstract(m.getModifiers()));

		}
		catch (NoSuchMethodException | SecurityException e) {
			fail("The \"Attack\" class should have a getAppliedDamage method which takes an input parameter of type BattleOpponent.");
		}
		try {
			m = Attack.class
					.getDeclaredMethod("onUse", new Class[] {
							BattleOpponent.class, BattleOpponent.class,
							boolean.class });
			assertEquals("onUse method in Attack class should return nothing ",
					void.class, m.getReturnType());
			assertFalse(
					"onUse method in Attack class has some implementation that is common among all its subclaases",
					Modifier.isAbstract(m.getModifiers()));

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"Attack\" class should have an onUse method which takes the input parameters BattleOpponent and Boolean.");
		}
		try {
			m = PhysicalAttack.class.getDeclaredMethod("getAppliedDamage",
					new Class[] { BattleOpponent.class });
			assertEquals(
					"getAppliedDamage method in PhysicalAttack class should return a value of type int ",
					int.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"PhysicalAttack\" class should override getAppliedDamage method which takes an input parameter of type BattleOpponent.");
		}
		try {
			m = PhysicalAttack.class
					.getDeclaredMethod("onUse", new Class[] {
							BattleOpponent.class, BattleOpponent.class,
							boolean.class });
			assertEquals(
					"onUse method in PhysicalAttack class should return nothing ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"PhysicalAttack\" class should override onUse method .");
		}
		try {
			m = SuperAttack.class.getDeclaredMethod("getAppliedDamage",
					new Class[] { BattleOpponent.class });
			assertEquals(
					"getAppliedDamage method in SuperlAttack class should return a value of type int ",
					int.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"SuperAttack\" class should override getAppliedDamage method .");
		}
		try {
			m = SuperAttack.class
					.getDeclaredMethod("onUse", new Class[] {
							BattleOpponent.class, BattleOpponent.class,
							boolean.class });
			assertEquals(
					"onUse method in SuperAttack class should return nothing ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"SuperAttack\" class should override onUse method .");
		}
		try {
			m = UltimateAttack.class.getDeclaredMethod("getAppliedDamage",
					new Class[] { BattleOpponent.class });
			assertEquals(
					"getAppliedDamage method in UltimateAttack class should return a value of type int ",
					int.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"UltimateAttack\" class should override getAppliedDamage method .");
		}
		try {
			m = UltimateAttack.class
					.getDeclaredMethod("onUse", new Class[] {
							BattleOpponent.class, BattleOpponent.class,
							boolean.class });
			assertEquals(
					"onUse method in UltimateAttack class should return nothing ",
					void.class, m.getReturnType());

		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"UltimateAttack\" class should override onUse method .");
		}

	}

	@SuppressWarnings("unused")
	@Test(timeout = 1000)
	public void testDragonAdditionalMethods() throws Exception{

		try {
			Method m = Dragon.class.getDeclaredMethod("getWishes");
		} catch (NoSuchMethodException | SecurityException e) {
			fail("The \"Dragon\" class should have a getWishes method which takes no input parameters.");
		}
	}
	
	@Test(timeout = 1000)
	public void testGetAssignedAttacks() throws Exception{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true, null, null);
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(new SuperAttack("Kamehameha", 250));
		superAttacks.add(new MaximumCharge());
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(new UltimateAttack("Super Kamehameha", 450));
		ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));
		Frieza e = new Frieza("frieza");
		e.setSuperAttacks(superAttacks);
		e.setUltimateAttacks(ultimateAttacks);
		Battle b = new Battle(e, strong);
		ArrayList<Attack> res = b.getAssignedAttacks();
		boolean s1 = false;
		boolean s2 = false;
		boolean u1 = false;
		boolean u2 = false;
		boolean p = false;
		int i = 0;
		while (i < res.size()) {
			Attack current = res.get(i);
			if (current instanceof PhysicalAttack) {
				p = true;
				res.remove(current);
			} else if (current instanceof SuperAttack
					&& current.getName().equals("Kamehameha")
					&& current.getDamage() == 250) {
				s1 = true;
				res.remove(current);
			} else if (current instanceof MaximumCharge) {
				s2 = true;
				res.remove(current);
			} else if (current instanceof UltimateAttack
					&& current.getName().equals("Super Kamehameha")
					&& current.getDamage() == 450) {
				u1 = true;
				res.remove(current);
			} else if (current instanceof UltimateAttack
					&& current.getName().equals("Spirit Bomb")
					&& current.getDamage() == 500) {
				u2 = true;
				res.remove(current);
			} else
				i++;
		}
		assertTrue(
				"the array list returned from GetCurrentOpponentAttacks method in Battle class should return an instance of a physical attack",
				p);
		assertTrue(
				"the array list returned from GetCurrentOpponentAttacks method in Battle class should contain all super attacks the current opponent has",
				s1);
		assertTrue(
				"the array list returned from GetCurrentOpponentAttacks method in Battle class should contain all super attacks the current opponent has",
				s2);
		assertTrue(
				"the array list returned from GetCurrentOpponentAttacks method in Battle class should contain all ultimate attacks the current opponent has",
				u1);
		assertTrue(
				"the array list returned from GetCurrentOpponentAttacks method in Battle class should contain all ultimate attacks the current opponent has",
				u2);
		assertTrue(
				"the array list returned from GetCurrentOpponentAttacks method in Battle class should only contain the required items",
				res.isEmpty());
	}
	
	@Test(timeout = 1000)
	public void testDirectAttackWithPhysialAttack() throws Exception{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		Battle b = new Battle(e, strong);
		b.start();
		b.attack(new PhysicalAttack());
		assertEquals(
				"When me attacks the foe that is not blocking with physical attack , it should reduce points from his health points according to the game rules",
				2875, strong.getHealthPoints());
		b.attack(new PhysicalAttack());
		assertEquals(
				"When foe attacks me  while not blocking with physical attack , it should reduce points from his health points according to the game rules",
				650, e.getHealthPoints());
	}

	@Test(timeout = 1000)
	public void testPhysicalAttackGenerateKi() throws Exception{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		Battle b = new Battle(e, strong);
		b.start();
		int before = e.getKi();
		b.attack(new PhysicalAttack());
		assertEquals(
				"When me attacks the foe with physical attack , me's Ki should increase by 1",
				before + 1, e.getKi());
		before = strong.getKi();
		b.attack(new PhysicalAttack());
		assertEquals(
				"When foe attacks me physical attack , foe's Ki should increase by 1",
				before + 1, strong.getKi());
	}
	@Test(timeout = 1000)
	public void testUltimateAttackConsumesKiCorrectly() throws Exception{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 100, 5, 6, true, null, null);
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));
		Namekian e = new Namekian("namekian");
		e.setUltimateAttacks(ultimateAttacks);
		ArrayList<UltimateAttack> ultimateAttacks2 = new ArrayList<UltimateAttack>();
		ultimateAttacks2.add(new UltimateAttack("Super Kamehameha", 450));
		strong.setUltimateAttacks(ultimateAttacks2);
		Battle b = new Battle(e, strong);
		b.start();
		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		int before = e.getKi();
		b.attack(e.getUltimateAttacks().get(0));
		assertEquals(
				"When me attacks foe with Ultimate attack , me's ki should be reduced by 3",
				before - 3, e.getKi());
		before = strong.getKi();
		b.attack(strong.getUltimateAttacks().get(0));
		assertEquals(
				"When foe attacks me with Ultimate attack , foe's Ki should be reduced by 3",
				before - 3, strong.getKi());
	}
	@Test(timeout = 1000)
	public void testBlockingSuperAttack()throws Exception {
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 3, true, null, null);
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(new SuperAttack("Kamehameha", 250));
		Frieza e = new Frieza("frieza");
		e.setSuperAttacks(superAttacks);
		strong.setSuperAttacks(superAttacks);
		Battle b = new Battle(e, strong);
		b.start();
		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());
		b.block();

		int prevHP = e.getHealthPoints();
		int prevStamina = e.getStamina();
		//System.err.println("ATTACK DMG " + strong.getSuperAttacks().get(0).getAppliedDamage(strong));
		b.attack(strong.getSuperAttacks().get(0));
		assertEquals(
				"if foe attacks me while me was blocking ,me's Stamina should be reduced according to the game rules",
				1, e.getStamina());
		
		assertEquals(
				"When foe attacks me while me is blocking with Super attack and me's stamina reached zero , the health points of me should be reduced according to the game rules",
				prevHP - (600-prevStamina*100), e.getHealthPoints());

		b.attack(new PhysicalAttack());
		b.block();

	
		prevHP = strong.getHealthPoints();
		prevStamina = strong.getStamina();
		System.err.println(prevHP);
		System.err.println("FOES STAMINA " + prevStamina +" "+strong.getStamina());
		System.err.println("ATTACK DMG " + e.getSuperAttacks().get(0).getAppliedDamage(e));
		System.err.println("BENTENGANBENTENGANBENTENGANBENTENGAN " + (prevHP - (325-prevStamina*100)) + " " + strong.getHealthPoints());
		b.attack(e.getSuperAttacks().get(0));
		assertEquals(
				"if me attacks foe while foe was blocking ,foe's Stamina should be reduced according to the game rules",
				1, strong.getStamina());
	
		assertEquals(
				"When foe attacks me while me is blocking with Super attack and me's stamina reached zero , the health points of me should be reduced according to the game rules",
				prevHP - (325-prevStamina*100), strong.getHealthPoints());

	}
	@Test(timeout = 1000)
	public void testUseNotEnoughSenzuBeans() throws Exception{
		Player p = new Player("7amada");
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		p.setActiveFighter(e);
		Battle b = new Battle(e, strong);
		b.start();
		b.block();
		b.attack(new PhysicalAttack());
		int hbefore = e.getHealthPoints();
		b.use(p, Collectible.SENZU_BEAN);

		assertEquals(
				"When the player doesn't have any senzu bean , he can't use it therefore , its effects on stamina wont be applied",
				1, e.getStamina());
		assertEquals(
				"When the player doesn't have any senzu bean , he can't use it therefore , its effects on health points wont be applied",
				hbefore, e.getHealthPoints());

	}

	@Test(timeout = 1000)
	public void testUseSenzuBeans()throws Exception {
		Player p = new Player("7amada");
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 100, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		p.getFighters().add(e);
		p.setActiveFighter(e);
		p.setSenzuBeans(3);
		Battle b = new Battle(e, strong);
		b.start();
		b.attack(new PhysicalAttack());
		b.attack(new PhysicalAttack());
		b.use(p, Collectible.SENZU_BEAN);
		int sbefore=e.getMaxStamina();
		int hbeafore=e.getMaxHealthPoints();
		
		assertEquals(
				"When the player uses a senzu bean , it restores the stamina of me to max",
				e.getMaxStamina(), e.getStamina());
		assertEquals("Using a senzu bean does not affect the max stamina",sbefore,e.getMaxStamina());
		assertEquals(
				"When the player uses a senzu bean ,it restores the health points of me to max ",
				e.getMaxHealthPoints(), e.getHealthPoints());
		assertEquals("Using a senzu bean does not affect the max stamina",hbeafore,e.getMaxHealthPoints());
		assertEquals(
				"When the player uses a senzu bean, the number of senzu beans he has should be reduced by one",
				2, p.getSenzuBeans());
	}

	
	@Test(timeout = 1000)
	public void testGetDefender() throws Exception{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 100, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		Battle b = new Battle(e, strong);
		b.start();
		
		assertEquals(
				"getDefender method in Battle class doesn't return the correct fighting party",
				strong, b.getDefender());
		b.attack(new PhysicalAttack());
		assertEquals(
				"getDefender method in Battle class doesn't return the correct fighting party",
				e, b.getDefender());
	}

	
	@SuppressWarnings("unused")
	@Test (timeout = 1000)
	public void testSaiyanDoesnotStartTransformed()throws Exception {
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 100, 5, 6, true, null, null);
		Saiyan s = new Saiyan("Vegeta");
		s.setTransformed(true);
		Battle b = new Battle(s, strong);
		assertFalse("Any Saiyan starts the battle untransformed",
				s.isTransformed());
	}

	
	@Test(timeout = 1000)
	public void testPlayMethod()throws Exception {
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				100, 100, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(new SuperAttack("Kamehameha", 250));
		superAttacks.add(new SuperAttack("Death Beam", 300));
		strong.setSuperAttacks(superAttacks);
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));
		ultimateAttacks.add(new UltimateAttack("Super Kamehameha", 450));
		strong.setUltimateAttacks(ultimateAttacks);
		int block = 0;
		int physical = 0;
		int superr1 = 0;
		int superr2=0;
		int ultimate1 = 0;
		int ultimate2=0;
		for (int i = 0; i < 100; i++) {
			e.setHealthPoints(5000);
			strong.setKi(3);
			Game g = new Game();
			Battle b = new Battle(e, strong);
			b.setListener(g);
			b.start();
			b.attack(new PhysicalAttack());
			int meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			b.attack(new PhysicalAttack());
			meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			else if (e.getHealthPoints() == meHealth - 350)
				superr1++;
			else if(e.getHealthPoints()==meHealth-400)
				superr2++;
			b.attack(new PhysicalAttack());
			meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			else if (e.getHealthPoints() == meHealth - 350)
				superr1++;
			else if(e.getHealthPoints()==meHealth-400)
				superr2++;
			b.attack(new PhysicalAttack());
			meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			else if (e.getHealthPoints() == meHealth - 350)
				superr1++;
			else if(e.getHealthPoints()==meHealth-400)
				superr2++;
			else if (e.getHealthPoints() == meHealth - 600)
				ultimate1++;
			else if (e.getHealthPoints()==meHealth-550)
				ultimate2++;
			b.attack(new PhysicalAttack());
			meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			else if (e.getHealthPoints() == meHealth - 350)
				superr1++;
			else if(e.getHealthPoints()==meHealth-400)
				superr2++;
			else if (e.getHealthPoints() == meHealth - 600)
				ultimate1++;
			else if (e.getHealthPoints()==meHealth-550)
				ultimate2++;
		}
		
		assertTrue("One of the possible actions that the non playable fighter can do in play method is blocking",block>0);
		assertTrue("One of the possible actions that the non playable fighter can do in play method is using a super attack",superr1>0 || superr2>0);
		assertTrue("One of the possible actions that the non playable fighter can do in play method is using an ultimate attack",ultimate1>0 || ultimate2>0);
		assertTrue("One of the possible actions that the non playable fighter can do in play method is using physical attack",physical>0);
		assertTrue("if the non playable fighter is using a super attack in the play method , the super attack should be chosen randomly",superr1>0 && superr2>0);
		assertTrue("if the non playable fighter is using an ultimate attack in the play method , the ultimate attack should be chosen randomly",ultimate1>0 && ultimate2>0);

	}

	
	@SuppressWarnings("unused")
	@Test(timeout=1000)
	public void testOnUseMethodOnPhysicalAttack()throws Exception
	{
		Player p = new Player("7amada");
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				100, 100, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		p.getFighters().add(e);
		PhysicalAttack a = new PhysicalAttack();
		Battle b = new Battle(e,strong);
		a.onUse(e, strong, false);
		assertEquals("the functionality of onUse method of a physical attack is incorrect in case of defender not blocking",2875,strong.getHealthPoints());
		a.onUse(e, strong, true);
		assertEquals("the functionality of onUse method of a physical attack is incorrect in case of defender is blocking",2875,strong.getHealthPoints());
		assertEquals(" When calling onUse method on a Physical Attack , it should generate one Ki for the one who used it ",2,e.getKi());
	}
	@Test(timeout=1000)
	public void testMaximumCharge()throws Exception
	{
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 100, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(new MaximumCharge());
		superAttacks.add(new SuperAttack("Kamehameha",250));
		strong.setSuperAttacks(superAttacks);
		e.getSuperAttacks().add(new MaximumCharge());
		e.getSuperAttacks().add(new SuperAttack("Death Beam",300));
		e.setMaxHealthPoints(5000);
		//System.out.println("KI " + strong.getKi());
		Battle b = new Battle(e, strong);
		b.start();
		//System.out.println("KI " + strong.getKi());
		b.attack(new PhysicalAttack());
		b.attack(strong.getSuperAttacks().get(0));
		//System.out.println("KI " + strong.getKi());
		assertEquals("Maximum Charge does not require Ki to get activated",3,strong.getKi());
		assertEquals("Maximum Charge fills three empty Ki bars",3,strong.getKi());
		b.attack(e.getSuperAttacks().get(0));
		assertEquals("Maximum Charge does not consume Ki to get activated",4,e.getKi());
		assertEquals("Maximum Charge fills three empty Ki bars",4,e.getKi());
		b.attack(strong.getSuperAttacks().get(0));
		
		assertEquals("Maximum Charge fills the empty Ki bars only . If the empty Ki bars are less than three , it will fill them ",5,strong.getKi());
		assertEquals("Maximum Charge does not increase the maximum Ki if the left empty Ki bars are less than three ",5,strong.getMaxKi());
		b.attack(e.getSuperAttacks().get(1));
		b.attack(new PhysicalAttack());
		b.attack(e.getSuperAttacks().get(0));
		assertEquals("Maximum Charge fills the empty Ki bars only . If the empty Ki bars are less than three , it will fill them ",4,e.getKi());
		assertEquals("Maximum Charge does not increase the maximum Ki if the left empty Ki bars are less than three ",4,e.getMaxKi());
	}
	@Test(timeout = 1000)
	public void testAttackMethodEndsTurn() throws Exception {
		hasCalled = false;
		Battle b = new Battle(new Saiyan("saiyan"), new NonPlayableFighter(
				"foe", 1, 1, 1, 1, 1, 1, true, null, null)) {
			public void endTurn() {
				hasCalled = true;
			}
		};
		b.attack(new PhysicalAttack());
		assertTrue("Attack method in Battle class should end the turn",
				hasCalled);
	}	

	@Test(timeout = 1000)
	public void testEndTurnMethodSwitchesTurn() throws Exception {
		hasCalled = false;
		Method mm =Battle.class.getDeclaredMethod("switchTurn");
		assertNotEquals("switchTurn method should be public",2, mm.getModifiers());
		Battle b = new Battle(new Saiyan("saiyan"), new NonPlayableFighter(
				"foe", 1, 1, 1, 1, 1, 1, true, null, null)) {
			public void switchTurn() {
				hasCalled = true;
			}
		};
		Method m = Battle.class.getDeclaredMethod("endTurn");
		m.setAccessible(true);
		m.invoke(b);
		assertTrue("EndTurn method in Battle class should switch the turn",
				hasCalled);
	}

	
	@Test(timeout = 1000)
	public void testCurrentAttackerIsMe()throws Exception{
		BattleOpponent me = new Saiyan("saiyan");
		BattleOpponent foe = new NonPlayableFighter("foe", 1, 1, 1, 1, 1, 1, true, null, null);
		Battle battle = new Battle(me, foe);
		assertEquals("The active fighter \"me\" in class Battle should be the first one to attack in any new battle", me, battle.getAttacker());
	}
	// **************************** Helper methods ********************************//
	@SuppressWarnings("rawtypes")
	private static void invokeSetter(Object mainObject, Class variableClass,
			Object variableObject) throws NoSuchMethodException,
			SecurityException {
		
		int index = classContainsFieldAtIndex(mainObject.getClass(),
				variableClass);
	
		if (index != -1) {
			Field f = mainObject.getClass().getDeclaredFields()[index];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			System.out.println(methodName);
			Method setter = mainObject.getClass().getMethod(methodName,
					variableClass);
			try {
				setter.invoke(mainObject, variableObject);
			} catch (Exception e) {
				fail("The class: " + mainObject.getClass()
						+ " should have a setter method for the listener");
			}
		}
	}

	// returns the index of the variable in a certain class if exists, and -1
	// otherwise
	@SuppressWarnings("rawtypes")
	private static int classContainsFieldAtIndex(Class c, Class contained) {
		Field[] fields = c.getDeclaredFields();
		int i = -1;
		for (int j = 0; j < fields.length; j++) {
			if (fields[j].getType().equals(contained)) {
				i = j;
				break;
			}
		}
		return i;
	}

	@SuppressWarnings("rawtypes")
	private static boolean classContainsField(Class c, Class contained) {
		Field[] fields = c.getDeclaredFields();
		boolean contains = false;
		for (Field f : fields) {
			if (f.getType().equals(contained)) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	public static void clearWorld(Cell[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new EmptyCell();
			}
		}
	}
	private static boolean containDuplicatesSuperAttacks(
			ArrayList<SuperAttack> s1) {
		boolean dup = false;
		for (int i = 0; i < s1.size(); i++)
			for (int j = i + 1; j < s1.size(); j++)
				if (s1.get(i).getName().equals(s1.get(j).getName())) {
					dup = true;
					break;
				}
		return dup;
	}

	private static boolean containDuplicatesUltimateAttacks(
			ArrayList<UltimateAttack> s1) {
		boolean dup = false;
		for (int i = 0; i < s1.size(); i++)
			for (int j = i + 1; j < s1.size(); j++)
				if (s1.get(i).getName().equals(s1.get(j).getName())) {
					dup = true;
					break;
				}
		return dup;
	}

	private static boolean superAttacksListsAreEqual(ArrayList<SuperAttack> s1,
			ArrayList<SuperAttack> s2) {
		boolean equals = true;
		for (int i = 0; i < s1.size(); i++)
			if (!(s1.get(i).getName().equals(s2.get(i).getName()))) {
				equals = false;
				break;
			}
		return equals;
	}

	private static boolean ultimateAttacksListsAreEqual(
			ArrayList<UltimateAttack> s1, ArrayList<UltimateAttack> s2) {
		boolean equals = true;
		for (int i = 0; i < s1.size(); i++)
			if (!s1.get(i).getName().equals(s2.get(i).getName())) {
				equals = false;
				break;
			}
		return equals;
	}

	public static boolean TwoMapsDifferent(Cell[][] map1, Cell[][] map2) {
		boolean different = false;
		for (int i = 0; i < map1.length; i++) {
			for (int j = 0; j < map1[i].length; j++) {

				if (!map1[i][j].getClass().equals(map2[i][j].getClass())) {
					different = true;
					break;
				} else {

					if (map1[i][j].getClass().equals(FoeCell.class)
							&& !((FoeCell) map1[i][j])
							.getFoe()
							.getName()
							.equals(((FoeCell) map2[i][j]).getFoe()
									.getName())) {

						different = true;
						break;
					}

					else if (map1[i][j].getClass()
							.equals(CollectibleCell.class)
							&& ((CollectibleCell) map1[i][j]).getCollectible() != ((CollectibleCell) map2[i][j])
							.getCollectible()) {

						different = true;
						break;
					}
				}
			}

		}

		return different;

	}

	public static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	


}
class GameController implements GameListener {

	@Override
	public void onDragonCalled(Dragon dragon) {

	}

	@Override
	public void onCollectibleFound(Collectible collectible) {

	}


	@Override
	public void onBattleEvent(BattleEvent e) {

	}

}
