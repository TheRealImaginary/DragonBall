package dragonball.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Test;

import dragonball.model.game.Game;
import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.cell.Cell;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.CollectibleCell;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.FoeCell;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.NonPlayableCharacter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.PlayableCharacter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.player.Player;
import dragonball.model.world.World;
import dragonball.model.character.Character;
import dragonball.model.dragon.Dragon;

public class M1PublicTests_20305 {
	public void testPlayerClassInstanceVariables() throws SecurityException {
		Field f;
		boolean thrown = false;
		try {
			f = Player.class.getDeclaredField("name");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"name\" instance variable in class Player",
				thrown);
		try {
			f = Player.class.getDeclaredField("fighters");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"fighters\" instance variable in class Player",
				thrown);

		try {
			f = Player.class.getDeclaredField("senzuBeans");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"senzuBeans\" instance variable in class Player",
				thrown);
		try {
			f = Player.class.getDeclaredField("dragonBalls");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"dragonBalls\" instance variable in class Player",
				thrown);
		try {
			f = Player.class.getDeclaredField("superAttacks");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"superAttacks\" instance variable in class Player",
				thrown);
		try {
			f = Player.class.getDeclaredField("ultimateAttacks");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"ultimateAttacks\" instance variable in class Player",
				thrown);
		try {
			f = Player.class.getDeclaredField("activeFighter");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"activeFighter\" instance variable in class Player",
				thrown);
		try {
			f = Player.class.getDeclaredField("exploredMaps");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"exploredMaps\" instance variable in class Player",
				thrown);

	}

	@Test(timeout = 1000)
	public void testPlayerClassVariablesAccessibility()
			throws NoSuchFieldException, SecurityException {
		Field f = Player.class.getDeclaredField("fighters");
		assertEquals(
				"\"fighters\" instance variable in class Player should not be accessed outside that class",
				2, f.getModifiers());
		f = Player.class.getDeclaredField("name");
		assertEquals(
				"\"name\" instance variable in class Player should not be accessed outside that class",
				2, f.getModifiers());
		f = Player.class.getDeclaredField("senzuBeans");
		assertEquals(
				"\"senzuBeans\" instance variable in class Player should not be accessed outside that class",
				2, f.getModifiers());
		f = Player.class.getDeclaredField("dragonBalls");
		assertEquals(
				"\"dragonBalls\" instance variable in class Player should not be accessed outside that class",
				2, f.getModifiers());
		f = Player.class.getDeclaredField("superAttacks");
		assertEquals(
				"\"superAttacks\" instance variable in class Player should not be accessed outside that class",
				2, f.getModifiers());
		f = Player.class.getDeclaredField("ultimateAttacks");
		assertEquals(
				"\"ultimateAttacks\" instance variable in class Player should not be accessed outside that class",
				2, f.getModifiers());
		f = Player.class.getDeclaredField("activeFighter");
		assertEquals(
				"\"activeFighter\" instance variable in class Player should not be accessed outside that class",
				2, f.getModifiers());
		f = Player.class.getDeclaredField("exploredMaps");
		assertEquals(
				"\"exploredMaps\" instance variable in class Player should not be accessed outside that class",
				2, f.getModifiers());
	}

	@Test(timeout = 1000)
	public void testPlayerClassREADVariables() {
		Method[] methods = Player.class.getDeclaredMethods();
		assertTrue(
				"The \"fighters\" instance variable in class Player is a READ variable.",
				containsMethodName(methods, "getFighters"));
		assertTrue(
				"The \"name\" instance variable in class Player is a READ variable.",
				containsMethodName(methods, "getName"));

		assertTrue(
				"The \"senzuBeans\" instance variable in class Player is a READ variable.",
				containsMethodName(methods, "getSenzuBeans"));
		assertTrue(
				"The \"dragonBalls\" instance variable in class Player is a READ variable.",
				containsMethodName(methods, "getDragonBalls"));
		assertTrue(
				"The \"superAttacks\" instance variable in class Player is a READ variable.",
				containsMethodName(methods, "getSuperAttacks"));
		assertTrue(
				"The \"ultimateAttacks\" instance variable in class Player is a READ variable.",
				containsMethodName(methods, "getUltimateAttacks"));
		assertTrue(
				"The \"activeFighter\" instance variable in class Player is a READ variable.",
				containsMethodName(methods, "getActiveFighter"));
		assertTrue(
				"The \"exploredMaps\" instance variable in class Player is a READ variable.",
				containsMethodName(methods, "getExploredMaps"));
	}

	@Test(timeout = 1000)
	public void testPlayerClassWRITEVariables() {
		Method[] methods = Player.class.getDeclaredMethods();
		assertTrue(
				"The \"fighters\" instance variable in class Player is a WRITE variable.",
				containsMethodName(methods, "setFighters"));
		assertTrue(
				"The \"name\" instance variable in class Player is a WRITE variable.",
				containsMethodName(methods, "setName"));

		assertTrue(
				"The \"senzuBeans\" instance variable in class Player is a WRITE variable.",
				containsMethodName(methods, "setSenzuBeans"));
		assertTrue(
				"The \"dragonBalls\" instance variable in class Player is a WRITE variable.",
				containsMethodName(methods, "setDragonBalls"));
		assertTrue(
				"The \"superAttacks\" instance variable in class Player is a WRITE variable.",
				containsMethodName(methods, "setSuperAttacks"));
		assertTrue(
				"The \"ultimateAttacks\" instance variable in class Player is a WRITE variable.",
				containsMethodName(methods, "setUltimateAttacks"));
		assertTrue(
				"The \"activeFighter\" instance variable in class Player is a WRITE variable.",
				containsMethodName(methods, "setActiveFighter"));
		assertTrue(
				"The \"exploredMaps\" instance variable in class Player is a WRITE variable.",
				containsMethodName(methods, "setExploredMaps"));


		boolean found = true;
		Method m = null;
		try {
			m = Player.class.getDeclaredMethod("setFighters", ArrayList.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Player class should have \"setFighters\" method that takes one ArrayList parameter",
				found);
		assertTrue(
				"incorrect return type for \"setFighters\" method in Player class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = Player.class.getDeclaredMethod("setName", String.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Player class should have \"setName\" method that takes one String parameter",
				found);
		assertTrue(
				"incorrect return type for \"setName\" method in Player class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = Player.class.getDeclaredMethod("setSenzuBeans", int.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Player class should have \"setSenzuBeans\" method that takes one int parameter",
				found);
		assertTrue(
				"incorrect return type for \"setSenzuBeans\" method in Player class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = Player.class.getDeclaredMethod("setDragonBalls", int.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Player class should have \"setDragonBalls\" method that takes one int parameter",
				found);
		assertTrue(
				"incorrect return type for \"setDragonBalls\" method in Player class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = Player.class.getDeclaredMethod("setSuperAttacks",
					ArrayList.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Player class should have \"setSuperAttacks\" method that takes one ArrayList parameter",
				found);
		assertTrue(
				"incorrect return type for \"setSuperAttacks\" method in Player class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = Player.class.getDeclaredMethod("setUltimateAttacks",
					ArrayList.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Player class should have \"setUltimateAttacks\" method that takes one ArrayList parameter",
				found);
		assertTrue(
				"incorrect return type for \"setUltimateAttacks\" method in Player class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = Player.class.getDeclaredMethod("setActiveFighter",
					PlayableFighter.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Player class should have \"setActiveFighter\" method that takes a PlayableFighter parameter",
				found);
		assertTrue(
				"incorrect return type for \"setActiveFighter\" method in Player class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = Player.class.getDeclaredMethod("setExploredMaps", int.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Player class should have \"setExploredMaps\" method that takes one int parameter",
				found);
		assertTrue(
				"incorrect return type for \"setExploredMaps\" method in Player class.",
				m.getReturnType().equals(Void.TYPE));

	}

	@Test(timeout = 1000)
	public void testPlayerClassFirstConstructor() throws SecurityException {

		Class aClass = Player.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass
					.getConstructor(new Class[] { String.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with String parameter in Player class.",
				thrown);
	}
	@Test(timeout = 1000)
	public void testCharacterCannotBeInstaniated() {
		assertTrue(
				"You should not be able to create new instances from Character class.",
				Modifier.isAbstract(Character.class.getModifiers()));
	}

	@Test(timeout = 1000)
	public void testCharacterClassInstanceVariables()

			throws NoSuchFieldException, SecurityException {
		Field f = Character.class.getDeclaredField("name");
		assertNotEquals(
				"there should be \"name\" instance variable in class Character",
				null, f);

	}

	@Test(timeout = 1000)
	public void testCharacterClassVariablesAccessibility()
			throws NoSuchFieldException, SecurityException {
		Field f = Character.class.getDeclaredField("name");
		assertEquals(
				"\"name\" instance variable in class Character should not be accessed outside that class",
				2, f.getModifiers());
	}

	@Test(timeout = 1000)
	public void testCharacterClassREADVariables() {
		Method[] methods = Character.class.getDeclaredMethods();
		assertTrue(
				"The \"name\" instance variable in class Character is a READ variable.",
				containsMethodName(methods, "getName"));

	}

	@Test(timeout = 1000)
	public void testCharacterClassWRITEVariables() {
		Method[] methods = Character.class.getDeclaredMethods();
		assertFalse(
				"The \"name\" instance variable in class Player is a READ ONLY variable.",
				containsMethodName(methods, "setName"));

	}

	@Test(timeout = 1000)
	public void testCharacterClassConstructor() throws SecurityException {

		Class aClass = Character.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass
					.getConstructor(new Class[] { String.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with String parameter in Character class.",
				thrown);
	}
	@Test(timeout = 1000)
	public void testFighterClassREADVariables() {
		Method[] methods = Fighter.class.getDeclaredMethods();

		assertTrue(
				"The \"level\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getLevel"));
		assertTrue(
				"The \"healthPoints\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getHealthPoints"));
		assertTrue(
				"The \"blastDamage\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getBlastDamage"));
		assertTrue(
				"The \"physicalDamage\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getPhysicalDamage"));
		assertTrue(
				"The \"ki\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getKi"));
		assertTrue(
				"The \"stamina\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getStamina"));
		assertTrue(
				"The \"maxHealthPoints\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getMaxHealthPoints"));
		assertTrue(
				"The \"maxKi\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getMaxKi"));
		assertTrue(
				"The \"maxStamina\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getMaxStamina"));
		assertTrue(
				"The \"superAttacks\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getSuperAttacks"));
		assertTrue(
				"The \"ultimateAttacks\" instance variable in class Fighter is a READ variable.",
				containsMethodName(methods, "getUltimateAttacks"));

	}
	@Test(timeout = 1000)
	public void testFighterClassConstructor() throws SecurityException {
		Class aClass = Fighter.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					String.class, int.class, int.class, int.class, int.class,
					int.class, int.class, ArrayList.class, ArrayList.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing a constructor with 13 parameters in Fighter class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testBattleopponentInterface() {
		assertEquals("BattleOpponent should be an Interface", 1537,
				BattleOpponent.class.getModifiers());
	}

	@Test(timeout = 1000)
	public void testPlayableFighterClassVariablesAccessibility()
			throws NoSuchFieldException, SecurityException {
		Field f = PlayableFighter.class.getDeclaredField("xp");
		assertEquals(
				"\"xp\" instance variable in class Playable Fighter should not be accessed outside that class",
				2, f.getModifiers());
		f = PlayableFighter.class.getDeclaredField("targetXp");
		assertEquals(
				"\"targetXp\" instance variable in class Playable Fighter should not be accessed outside that class",
				2, f.getModifiers());
		f = PlayableFighter.class.getDeclaredField("abilityPoints");
		assertEquals(
				"\"abilityPoints\" instance variable in class Playable Fighter should not be accessed outside that class",
				2, f.getModifiers());
	}

	@Test(timeout = 1000)
	public void testPlayableFighterClassREADVariables() {
		Method[] methods = PlayableFighter.class.getDeclaredMethods();

		assertTrue(
				"The \"xp\" instance variable in class Playable Fighter is a READ variable.",
				containsMethodName(methods, "getXp"));
		assertTrue(
				"The \"targetXp\" instance variable in class Playable Fighter is a READ variable.",
				containsMethodName(methods, "getTargetXp"));
		assertTrue(
				"The \"abilityPoints\" instance variable in class Playable Fighter is a READ variable.",
				containsMethodName(methods, "getAbilityPoints"));
	}

	@Test(timeout = 1000)
	public void testPlayableFighterClassWRITEVariables() {
		Method[] methods = PlayableFighter.class.getDeclaredMethods();
		assertTrue(
				"The \"xp\" instance variable in class Playable Fighter is a WRITE variable.",
				containsMethodName(methods, "setXp"));
		assertTrue(
				"The \"targetXp\" instance variable in class Playable Fighter is a WRITE variable.",
				containsMethodName(methods, "setTargetXp"));
		assertTrue(
				"The \"abilityPoints\" instance variable in class Playable Fighter is a WRITE variable.",
				containsMethodName(methods, "setAbilityPoints"));


		Method m = null;
		boolean found = true;
		try {
			m = PlayableFighter.class.getDeclaredMethod("setXp", int.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"PlayableFighter class should have \"setXp\" method that takes one int parameter",
				found);
		assertTrue(
				"incorrect return type for \"setXp\" method in PlayableFighter class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = PlayableFighter.class.getDeclaredMethod("setTargetXp",
					int.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"PlayableFighter class should have \"setTargetXp\" method that takes one int parameter",
				found);
		assertTrue(
				"incorrect return type for \"setTargetXp\" method in PlayableFighter class.",
				m.getReturnType().equals(Void.TYPE));

		try {
			m = PlayableFighter.class.getDeclaredMethod("setAbilityPoints",
					int.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"PlayableFighter class should have \"setAbilityPoints\" method that takes one int parameter",
				found);
		assertTrue(
				"incorrect return type for \"setAbilityPoints\" method in PlayableFighter class.",
				m.getReturnType().equals(Void.TYPE));

	}
	@Test(timeout = 1000)
	public void testPlayableFighterClassFirstConstructor()
			throws SecurityException {
		Class aClass = PlayableFighter.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					String.class, int.class, int.class, int.class, int.class,
					int.class, int.class, int.class, int.class, int.class,ArrayList.class, ArrayList.class});
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing a constructor with 12 parameters in PlayableFighter class.",
				thrown);
	}
	@Test(timeout = 1000)
	public void testPlayableFighterClassInterface() {
		Class[] interfaces = PlayableFighter.class.getInterfaces();
		boolean inherits = false;
		for (Class i : interfaces) {
			if (i.toString().equals(PlayableCharacter.class.toString()))
				inherits = true;
		}
		assertTrue(
				"PlayableFighter class should implement PlayableCharacter interface.",
				inherits);
	}
	@Test(timeout = 1000)
	public void testPlayableCharacterInterface() {
		assertEquals("PlayableCharacter should be an Interface", 1537,
				PlayableCharacter.class.getModifiers());
	}
	@Test(timeout = 1000)
	public void testEarthlingClassInheritance() {
		assertEquals("Earthling class should inherit from PlayableFighter .",
				PlayableFighter.class, Earthling.class.getSuperclass());
	}

	@Test(timeout = 1000)
	public void testSaiyanClassInheritance() {
		assertEquals("Saiyan class should inherit from PlayableFighter .",
				PlayableFighter.class, Saiyan.class.getSuperclass());
	}

	@Test(timeout = 1000)
	public void testNamekianClassInheritance() {
		assertEquals("Namekian class should inherit from PlayableFighter .",
				PlayableFighter.class, Namekian.class.getSuperclass());
	}
	@Test(timeout = 1000)
	public void testEarthlingClassFirstConstructor() {
		Class aClass = Earthling.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass
					.getConstructor(new Class[] { String.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing a constructor with String parameter in Earthling class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testEarthlingClassSecondConstructor() {
		Class aClass = Earthling.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					String.class, int.class, int.class, int.class, int.class,
					int.class, int.class, int.class, int.class, int.class,
					ArrayList.class, ArrayList.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing a constructor with 12 parameters in Earthling class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testFriezaClassFirstConstructor() {
		Class aClass = Frieza.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass
					.getConstructor(new Class[] { String.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing a constructor with String parameter in Frieza class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testFriezaClassSecondConstructor() {
		Class aClass = Frieza.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					String.class, int.class, int.class, int.class, int.class,
					int.class, int.class, int.class, int.class, int.class,
					ArrayList.class, ArrayList.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing a constructor with 12 parameters in Frieza class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testMajinClassFirstConstructor() {
		Class aClass = Majin.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass
					.getConstructor(new Class[] { String.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing a constructor with String parameter in Majin class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testMajinClassSecondConstructor() {
		Class aClass = Majin.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					String.class, int.class, int.class, int.class, int.class,
					int.class, int.class, int.class, int.class, int.class,
					ArrayList.class, ArrayList.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse("Missing a constructor with 12 parameters in Majin class.",
				thrown);
	}
	@Test(timeout = 1000)
	public void testFriezaClassFirstConstructorInitialization() {
		Frieza e = new Frieza("frieza");
		assertEquals(
				"The first constructor of the Frieza class should initialize the instance variable \"name\" correctly",
				"frieza", e.getName());
		assertEquals("All frieza should start with \"1100\" healthpoints",
				1100, e.getMaxHealthPoints());
		assertEquals("All frieza should start with \"75\" blastDamage", 75,
				e.getBlastDamage());
		assertEquals("All frieza should start with \"75\" physicalDamage", 75,
				e.getPhysicalDamage());
		assertEquals("All frieza can get up to \"4\" Ki bars", 4, e.getMaxKi());
		assertEquals("All frieza should start with \"4\" stamina bars", 4,
				e.getMaxStamina());

		assertEquals(
				"The first constructor of the Frieza class should initialize the instance variable \"ki\" correctly",
				0, e.getKi());
		assertEquals("All frieza should start with  \"level 1\"", 1,
				e.getLevel());
		assertEquals("All frieza should start with \"0 xp\" ", 0, e.getXp());
		assertEquals("Allfrieza should start with \"10 targetXp\" ", 10,
				e.getTargetXp());
		assertEquals("All frieza should start with  \"0 abilityPoints\" ", 0,
				e.getAbilityPoints());
	}
	@Test(timeout = 1000)
	public void testMajinClassSecondConstructorInitialization() {
		SuperAttack k = new SuperAttack("Kamehameha", 400);
		UltimateAttack f = new UltimateAttack("Final Flash", 700);
		ArrayList<SuperAttack> supers = new ArrayList<SuperAttack>();
		supers.add(k);
		ArrayList<UltimateAttack> ultimates = new ArrayList<UltimateAttack>();
		ultimates.add(f);
		Majin e = new Majin("majin", 2, 100, 200, 300, 400, 500, 5, 6, 600,
				supers, ultimates);
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"name\" correctly",
				"majin", e.getName());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"level\" correctly",
				2, e.getLevel());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"xp\" correctly",
				100, e.getXp());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"targetXp\" correctly",
				200, e.getTargetXp());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"maxHealthPoints\" correctly",
				300, e.getMaxHealthPoints());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"blastDamage\" correctly",
				400, e.getBlastDamage());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"physicalDamage\" correctly",
				500, e.getPhysicalDamage());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"abilityPoints\" correctly",
				5, e.getAbilityPoints());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"ki\" correctly",
				0, e.getKi());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"maxKi\" correctly",
				6, e.getMaxKi());
		assertEquals(
				"The second constructor of the Majin class should initialize the instance variable \"maxStamina\" correctly",
				600, e.getMaxStamina());
		assertTrue(
				"The second constructor of the Majin class should initialize the instance variable \"superAttacks\" correctly",
				e.getSuperAttacks() != null
				&& supers.get(0).getName()
				.equals(e.getSuperAttacks().get(0).getName()));
		assertTrue(
				"The second constructor of the Majin class should initialize the instance variable \"ultimateAttacks\" correctly",
				e.getUltimateAttacks() != null
				&& ultimates.get(0).getName()
				.equals(e.getUltimateAttacks().get(0).getName()));
	}

	@Test(timeout = 1000)
	public void testMajinClassInstanceVariable() throws NoSuchFieldException,
	SecurityException {
		boolean found = false;
		try {
			Field f = Majin.class.getDeclaredField("transformed");
			found = true;
		} catch (NoSuchFieldException e) {
		}
		assertFalse("Only Saiyans have \"transformed\" instance variable",
				found);

	}
	@Test(timeout = 1000)
	public void testEarthlingClassInstanceVariable()
			throws NoSuchFieldException, SecurityException {
		boolean found = false;
		try {
			Field f = Earthling.class.getDeclaredField("transformed");
			found = true;
		} catch (NoSuchFieldException e) {
		}
		assertFalse("Only Saiyans have \"transformed\" instance variable",
				found);

	}

	@Test(timeout = 1000)
	public void testEarthlingClassFirstConstructorInitialization() {
		Earthling e = new Earthling("earthling");

		assertEquals(
				"The constructor of the Earthling class should initialize the instance variable \"name\" correctly",
				"earthling", e.getName());
		assertEquals("All earthlings should start with \"1250\" healthpoints",
				1250, e.getMaxHealthPoints());
		assertEquals("All earthlings should start with \"50\" blastDamage", 50,
				e.getBlastDamage());
		assertEquals("All earthlings should start with \"50\" physicalDamage",
				50, e.getPhysicalDamage());
		assertEquals("All earthlings can get up tp \"4\" Ki bars", 4,
				e.getMaxKi());
		assertEquals("All earthlings can get up to \"4\" stamina bars", 4,
				e.getMaxStamina());
		assertEquals("All earthlings should start with level \"1\" ", 1,
				e.getLevel());
		assertEquals("All earthlings should start with \"0\" xp", 0, e.getXp());
		assertEquals("All earthlings should start with \"10\" targetXp:", 10,
				e.getTargetXp());
		assertEquals("All earthlings should start with \"0\" abilityPoints:",
				0, e.getAbilityPoints());
		assertEquals(
				"The first constructor of the Earthling class should initialize the instance variable \"ki\" correctly",
				0, e.getKi());
	}
	@Test(timeout = 1000)
	public void testNamekianFirstConstructorInitialization() {
		Namekian e = new Namekian("namekian");
		assertEquals(
				"The constructor of the Namekian class should initialize the instance variable \"name\" correctly",
				"namekian", e.getName());

		assertEquals("All namekians should start with \"1350\" healthpoints",
				1350, e.getMaxHealthPoints());

		assertEquals("All namekians can get up to \"3\" Ki bars", 3,
				e.getMaxKi());
		assertEquals("All namekians can get up to \"5\" stamina bars", 5,
				e.getMaxStamina());
		assertEquals("All namekians should start with level \"1\" ", 1,
				e.getLevel());
		assertEquals("All namekians should start with \"0\" xp", 0, e.getXp());
		assertEquals("All namekians should start with \"10\" targetXp:", 10,
				e.getTargetXp());
		assertEquals("All namekians should start with \"0\" abilityPoints:", 0,
				e.getAbilityPoints());
		assertEquals("All namekians should start with 0 \"blast damage\"", 0,
				e.getBlastDamage());
		assertEquals("All namekians should start with 50 \"physicalDamage\" ",
				50, e.getPhysicalDamage());
		assertEquals(
				"The first constructor of the Namekian class should initialize the instance variable \"ki\" correctly",
				0, e.getKi());
	}
	@Test(timeout = 1000)
	public void testSaiyanFirstConstructorInitialization() {
		Saiyan e = new Saiyan("saiyan");
		assertEquals(
				"The constructor of the Saiyan class should initialize the instance variable \"name\" correctly",
				"saiyan", e.getName());

		assertEquals("All saiyans can get up to \"5\" Ki bars", 5, e.getMaxKi());
		assertEquals("All saiyans can get up to \"3\" stamina bars", 3,
				e.getMaxStamina());
		assertEquals("All saiyans should start with level \"1\" ", 1,
				e.getLevel());
		assertEquals("All saiyans should start with \"0\" xp", 0, e.getXp());
		assertEquals("All saiyans should start with \"10\" targetXp:", 10,
				e.getTargetXp());
		assertEquals("All saiyans should start with \"0\" abilityPoints:", 0,
				e.getAbilityPoints());
		assertEquals("All saiyans should start with 150 \"blast damage\"", 150,
				e.getBlastDamage());
		assertEquals("All saiyans should start with 100 \"physicalDamage\" ",
				100, e.getPhysicalDamage());
		assertEquals(
				"The first constructor of the Saiyan class should initialize the instance variable \"ki\" correctly",
				0, e.getKi());

	}

	@Test(timeout = 1000)
	public void testSaiyanClassSecondConstructorInitialization() {
		SuperAttack k = new SuperAttack("Kamehameha", 400);
		UltimateAttack f = new UltimateAttack("Final Flash", 700);
		ArrayList<SuperAttack> supers = new ArrayList<SuperAttack>();
		supers.add(k);
		ArrayList<UltimateAttack> ultimates = new ArrayList<UltimateAttack>();
		ultimates.add(f);
		Saiyan e = new Saiyan("saiyan", 2, 100, 200, 300, 400, 500, 5, 6, 600,
				supers, ultimates);
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"name\" correctly",
				"saiyan", e.getName());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"level\" correctly",
				2, e.getLevel());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"xp\" correctly",
				100, e.getXp());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"targetXp\" correctly",
				200, e.getTargetXp());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"maxHealthPoints\" correctly",
				300, e.getMaxHealthPoints());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"blastDamage\" correctly",
				400, e.getBlastDamage());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"physicalDamage\" correctly",
				500, e.getPhysicalDamage());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"abilityPoints\" correctly",
				5, e.getAbilityPoints());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"ki\" correctly",
				0, e.getKi());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"maxKi\" correctly",
				6, e.getMaxKi());
		assertEquals(
				"The second constructor of the Saiyan class should initialize the instance variable \"maxStamina\" correctly",
				600, e.getMaxStamina());
		assertTrue(
				"The second constructor of the Saiyan class should initialize the instance variable \"superAttacks\" correctly",
				e.getSuperAttacks() != null
				&& supers.get(0).getName()
				.equals(e.getSuperAttacks().get(0).getName()));
		assertTrue(
				"The second constructor of the Saiyan class should initialize the instance variable \"ultimateAttacks\" correctly",
				e.getUltimateAttacks() != null
				&& ultimates.get(0).getName()
				.equals(e.getUltimateAttacks().get(0).getName()));
	}
	@Test(timeout = 1000)
	public void testNonPlayableFighterClassVariablesAccessibility()
			throws NoSuchFieldException, SecurityException {
		Field f = NonPlayableFighter.class.getDeclaredField("strong");
		assertEquals(
				"\"strong\" instance variable in class NonPlayableFighter should not be accessed outside that class",
				2, f.getModifiers());
	}

	@Test(timeout = 1000)
	public void testNonPlayableFighterClassREADVariables() {
		Method[] methods = NonPlayableFighter.class.getDeclaredMethods();
		assertTrue(
				"The \"strong\" instance variable in class NonPlayableFighter is a READ variable.",
				containsMethodName(methods, "isStrong"));

	}

	@Test(timeout = 1000)
	public void testNonPlayableFighterClassWRITEVariables() {
		Method[] methods = Fighter.class.getDeclaredMethods();
		assertFalse(
				"The \"strong\" instance variable in class NonPlayableFighter is a READ ONLY variable.",
				containsMethodName(methods, "setStrong"));
	}
	@Test(timeout = 1000)
	public void testNonPlayableFighterClassConstructor()
			throws SecurityException {
		Class aClass = NonPlayableFighter.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					String.class, int.class, int.class, int.class, int.class,
					int.class, int.class, boolean.class,ArrayList.class,ArrayList.class});
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing a constructor with 10 parameters in NonPlayableFighter class.",
				thrown);
	}

	@Test(timeout = 1000)
	public void testNonPlayableFighterClassInheritance() {
		assertEquals(
				"NonPlayableFighter class should inherit from Fighter class.",
				Fighter.class, NonPlayableFighter.class.getSuperclass());
	}

	@Test(timeout = 1000)
	public void testNonPlayableFighterClassInterface() {

		Class[] interfaces = NonPlayableFighter.class.getInterfaces();
		boolean inherits = false;
		for (Class i : interfaces) {
			if (i.toString().equals(NonPlayableCharacter.class.toString()))
				inherits = true;
		}
		assertTrue(
				"NonPlayableFighter class should implement NonPlayableCharacter interface.",
				inherits);
	}
	@Test(timeout = 1000)
	public void testNonPlayableCharacterInterface() {
		assertEquals("NonPlayableCharacter should be an Interface", 1537,
				NonPlayableCharacter.class.getModifiers());
	}
	@Test(timeout = 1000)
	public void testWorldClassInstanceVariable() throws NoSuchFieldException,
	SecurityException {

		Field f = World.class.getDeclaredField("map");
		assertNotEquals(" World class should  have \"map\" instance variable",
				null, f);

		f = World.class.getDeclaredField("playerColumn");
		assertNotEquals(" World class should  have \"playerColumn\" instance variable",
				null, f);

		f = World.class.getDeclaredField("playerRow");
		assertNotEquals(" World class should  have \"playerRow\" instance variable",
				null, f);
	}
	@Test(timeout = 1000)
	public void testWorldCellClassWRITEVariables() {
		Method[] methods = World.class.getDeclaredMethods();
		assertFalse(
				"The \"map\" instance variable in class World is not a WRITE variable.",
				containsMethodName(methods, "setMap"));

		assertFalse(
				"The \"playerColumn\" instance variable in class World is not a WRITE variable.",
				containsMethodName(methods, "setPlayerColumn"));

		assertFalse(
				"The \"playerRow\" instance variable in class World is not a WRITE variable.",
				containsMethodName(methods, "setPlayerRow"));

	}

	@Test(timeout = 1000)
	public void testWorldClassREADVariables() {
		Method[] methods = World.class.getDeclaredMethods();
		assertTrue(
				"The \"map\" instance variable in class World should be a READ variable.",
				containsMethodName(methods, "getMap"));
		assertTrue(
				"The \"playerColumn\" instance variable in class World should be a READ variable.",
				containsMethodName(methods, "getPlayerColumn"));

		assertTrue(
				"The \"playerRow\" instance variable in class World should be a READ variable.",
				containsMethodName(methods, "getPlayerRow"));

	}

	@Test(timeout = 1000)
	public void testWorldClassMethods() throws NoSuchMethodException,
	SecurityException {

		Method[] methods = World.class.getDeclaredMethods();
		assertTrue("World class should have \"toString\" method",
				containsMethodName(methods, "toString"));
		assertTrue("World class should have \"generateMap\" method",
				containsMethodName(methods, "generateMap"));

		Method m = World.class.getDeclaredMethod("toString");
		assertTrue(
				"incorrect return type for \"toString\" method in World class.",
				m.getReturnType().equals(String.class));

		m = World.class.getDeclaredMethod("generateMap",ArrayList.class,ArrayList.class);
		assertTrue(
				"incorrect return type for \"generateMap\" method in World class.",
				m.getReturnType().equals(Void.TYPE));
		Class[] parameters = m.getParameterTypes();
		assertEquals(
				"incorrect inputs for \"generateMap\" method in World class.",
				parameters, (new Class[] { ArrayList.class, ArrayList.class }));
	}

	@Test(timeout = 1000)
	public void testWorldConstructorInitialization()
			throws IllegalArgumentException, IllegalAccessException {
		World w = new World();
		Field f;
		Cell[][] map = null;
		boolean mapFound = true;
		try {
			f = World.class.getDeclaredField("map");
			f.setAccessible(true);
			map = (Cell[][]) f.get(w);

		} catch (NoSuchFieldException e) {
			mapFound = false;
		}

		assertTrue("The instance variable \"map\" in class World is missing",
				mapFound);
		assertEquals(
				"the constructor of the World class should initialize the instance variable \"map\" as a 10x10 grid",
				10, map.length);
		boolean wrong = false;
		for (int i = 0; i < map.length; i++) {
			if (map[i].length != 10)
				wrong = true;
		}
		assertFalse(
				"the constructor of the World class should initialize the instance variable \"map\" as a 10x10 grid",
				wrong);
	}

	@Test(timeout = 1000)
	public void testWorldClassConstructor() throws SecurityException {

		Class aClass = World.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {});
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse("Missing parameter-less constructor in World class.",
				thrown);
	}
	@Test(timeout = 1000)
	public void testCellCannotBeInstaniated() {
		assertTrue(
				"You should not be able to create new instances from Cell class.",
				Modifier.isAbstract(Cell.class.getModifiers()));
	}
	@Test(timeout = 1000)
	public void testCellClassToStringMethod() throws NoSuchMethodException,
	SecurityException {
		Method[] methods = Cell.class.getDeclaredMethods();
		assertTrue("Cell class should have \"toString\" method",
				containsMethodName(methods, "toString"));
		Method m = Cell.class.getDeclaredMethod("toString");
		assertTrue(
				"incorrect return type for \"toString\" method in Cell class.",
				m.getReturnType().equals(String.class));

		assertTrue("incorrect modifier for \"toString\" method in  Cell class",
				Modifier.isAbstract(m.getModifiers()));
	}
	@Test(timeout = 1000)
	public void testFoeCellClassInheritance() {
		assertEquals("Wrong inheritance in FoeCell class .", Cell.class,
				FoeCell.class.getSuperclass());
	}

	@Test(timeout = 1000)
	public void testFoeCellClassToStringMethod() throws NoSuchMethodException,
	SecurityException {
		Method[] methods = FoeCell.class.getDeclaredMethods();
		assertTrue("Foe Cell class should have \"toString\" method",
				containsMethodName(methods, "toString"));
		Method m = FoeCell.class.getDeclaredMethod("toString");
		assertTrue(
				"incorrect return type for \"toString\" method in Foe Cell class.",
				m.getReturnType().equals(String.class));

		FoeCell cell = new FoeCell(new NonPlayableFighter("foe", 1, 1, 1, 1, 1,
				1, false, null, null));
		String cellOutput = cell.toString();
		assertEquals(
				"Foe Cell with a weak foe fighter should be represented as w written in square brackets",
				"[w]", cellOutput);

		cell = new FoeCell(new NonPlayableFighter("foe", 1, 1, 1, 1, 1, 1,
				true, null, null));
		cellOutput = cell.toString();
		assertEquals(
				"Foe Cell with a strong foe (Boss) fighter should be represented as b written in square brackets",
				"[b]", cellOutput);
	}

	public void testFoeCellClassInstanceVariables() throws SecurityException {
		Field f;
		boolean thrown = false;
		try {
			f = FoeCell.class.getDeclaredField("foe");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"foe\" instance variable in class FoeCell",
				thrown);
	}
	@Test(timeout = 1000)
	public void testCollectibleCellClassInheritance() {
		assertEquals("Wrong inheritance in CollectibleCell class .",
				Cell.class, CollectibleCell.class.getSuperclass());
	}

	@Test(timeout = 1000)
	public void testCollectibleCellClassToStringMethod()
			throws NoSuchMethodException, SecurityException {
		Method[] methods = CollectibleCell.class.getDeclaredMethods();
		assertTrue("Collectible Cell class should have \"toString\" method",
				containsMethodName(methods, "toString"));
		Method m = CollectibleCell.class.getDeclaredMethod("toString");
		assertTrue(
				"incorrect return type for \"toString\" method in Collectible Cell class.",
				m.getReturnType().equals(String.class));
		CollectibleCell cell = new CollectibleCell(Collectible.DRAGON_BALL);
		String cellOutput = cell.toString();
		assertEquals(
				"Collectible Cell with a dragon ball should be represented as d written in square brackets",
				"[d]", cellOutput);

		cell = new CollectibleCell(Collectible.SENZU_BEAN);
		cellOutput = cell.toString();
		assertEquals(
				"Collectible Cell with a senzu bean should be represented as s written in square brackets",
				"[s]", cellOutput);

	}

	public void testCollectibleCellClassInstanceVariables()
			throws SecurityException {
		Field f;
		boolean thrown = false;
		try {
			f = CollectibleCell.class.getDeclaredField("collectible");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"collectible\" instance variable in class CollectibleCell",
				thrown);
	}
	@Test(timeout = 1000)
	public void testEnumCollectible() throws Exception {
		assertTrue("Collectible should be an enum.", Collectible.class.isEnum());
		assertNotNull("Collectible can be SENZU_BEAN",
				Collectible.valueOf("SENZU_BEAN"));
		assertNotNull("Collectible can be DRAGON_BALL",
				Collectible.valueOf("DRAGON_BALL"));
		int values = Collectible.values().length;
		assertTrue("Collectible enum can only have two values",values == 2);
	}
	@Test(timeout = 1000)
	public void testBattleClassVariablesAccessibility()
			throws NoSuchFieldException, SecurityException {
		Field f = Battle.class.getDeclaredField("me");
		assertEquals(
				"\"me\" instance variable in class Battle should not be accessed outside that class",
				2, f.getModifiers());

		f = Battle.class.getDeclaredField("foe");
		assertEquals(
				"\"foe\" instance variable in class Battle should not be accessed outside that class",
				2, f.getModifiers());

		f = Battle.class.getDeclaredField("currentOpponent");
		assertEquals(
				"\"currentOpponent\" instance variable in class Battle should not be accessed outside that class",
				2, f.getModifiers());
	}

	@Test(timeout = 1000)
	public void testBattleClassREADVariables() {
		Method[] methods = Battle.class.getDeclaredMethods();
		assertTrue(
				"The \"me\" instance variable in class Battle is a READ variable.",
				containsMethodName(methods, "getMe"));
		assertTrue(
				"The \"foe\" instance variable in class Battle is a READ variable.",
				containsMethodName(methods, "getFoe"));
		assertTrue(
				"The \"currentOpponent\" instance variable in class Battle is a READ variable.",
				containsMethodName(methods, "getCurrentOpponent"));
	}

	@Test(timeout = 1000)
	public void testBattleClassWRITEVariables() {
		Method[] methods = Battle.class.getDeclaredMethods();
		assertFalse(
				"The \"me\" instance variable in class Battle is a not WRITE variable.",
				containsMethodName(methods, "setMe"));
		assertFalse(
				"The \"foe\" instance variable in class Battle is a not WRITE variable.",
				containsMethodName(methods, "setFoe"));
		assertFalse(
				"The \"currentOpponent\" instance variable in class Battle is a not WRITE variable.",
				containsMethodName(methods, "setCurrentOpponent"));
	}
	@Test(timeout = 1000)
	public void testBattleClassConstructor() throws SecurityException {

		Class aClass = Battle.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					BattleOpponent.class, BattleOpponent.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse("Missing constructor in Battle class.", thrown);
	}
	@Test(timeout = 1000)
	public void testAttackCannotBeInstaniated() {
		assertTrue(
				"You should not be able to create new instances from Attack class.",
				Modifier.isAbstract(Attack.class.getModifiers()));
	}

	@Test(timeout = 1000)
	public void testAttackClassInstanceVariables() throws SecurityException {
		Field f;
		boolean thrown = false;
		try {
			f = Attack.class.getDeclaredField("damage");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"damage\" instance variable in class Attack",
				thrown);
		thrown = false;
		try {
			f = Attack.class.getDeclaredField("name");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"name\" instance variable in class Attack",
				thrown);
	}

	@Test(timeout = 1000)
	public void testAttackClassVariablesAccessibility()
			throws NoSuchFieldException, SecurityException {
		Field f = Attack.class.getDeclaredField("damage");
		assertEquals(
				"\"damage\" instance variable in class Attack should not be accessed outside that class",
				2, f.getModifiers());
		f = Attack.class.getDeclaredField("name");
		assertEquals(
				"\"name\" instance variable in class Attack should not be accessed outside that class",
				2, f.getModifiers());
	}

	@Test(timeout = 1000)
	public void testAttackClassREADVariables() {
		Method[] methods = Attack.class.getDeclaredMethods();
		assertTrue(
				"The \"damage\" instance variable in class Attack is a READ variable.",
				containsMethodName(methods, "getDamage"));
		assertTrue(
				"The \"name\" instance variable in class Attack is a READ variable.",
				containsMethodName(methods, "getName"));

	}
	@Test(timeout = 1000)
	public void testAttackSubclassesConstructorInitialization() {
		PhysicalAttack p = new PhysicalAttack();


		assertTrue(
				"the constructor of the PhysicalAttack class should initialize the instance variable \"name\" correctly",
				p.getName().replaceAll("\\s+", "")
				.equalsIgnoreCase("physicalattack"));


		assertEquals("Physical attacks by default deal 50 damage", 50,
				p.getDamage());

		SuperAttack s = new SuperAttack("kamehameha", 250);
		assertEquals(
				"the constructor of the SuperAttack class should initialize the instance variable \"name\" correctly",
				"kamehameha", s.getName());
		assertEquals(
				"the constructor of the SuperAttack class should initialize the instance variable \"damage\" correctly",
				250, s.getDamage());

		MaximumCharge m = new MaximumCharge();

		assertTrue(
				"the constructor of the MaximumCharge class should initialize the instance variable \"name\" correctly",
				m.getName().replaceAll("\\s+", "")
				.equalsIgnoreCase("maximumcharge"));

		assertEquals("Maximum Charge does not inflict damage", 0, m.getDamage());
	}
	@Test(timeout = 1000)
	public void testSuperAttackClassInstanceVariables()
			throws NoSuchFieldException, SecurityException {
		boolean found = false;
		try {
			Field f = SuperAttack.class.getDeclaredField("damage");
			found = true;
		} catch (NoSuchFieldException e) {
		}
		assertFalse(
				"the \"damage\" instance variable should be inherited from class Attack",
				found);

		try {
			Field f = SuperAttack.class.getDeclaredField("name");
			found = true;
		} catch (NoSuchFieldException e) {
		}
		assertFalse(
				"the \"name\" instance variable should be inherited from class Attack",
				found);

	}

	@Test(timeout = 1000)
	public void testUltimateAttackClassInheritance() {
		assertEquals("UltimateAttack class should inherit from Attack.",
				Attack.class, UltimateAttack.class.getSuperclass());
	}
	@Test(timeout = 1000)
	public void testSuperSaiyanClassInstanceVariables()
			throws NoSuchFieldException, SecurityException {
		boolean found = false;
		try {
			Field f = SuperSaiyan.class.getDeclaredField("damage");
			found = true;
		} catch (NoSuchFieldException e) {
		}
		assertFalse(
				"the \"damage\" instance variable should be inherited from class Attack",
				found);

		try {
			Field f = SuperSaiyan.class.getDeclaredField("name");
			found = true;
		} catch (NoSuchFieldException e) {
		}
		assertFalse(
				"the \"name\" instance variable should be inherited from class Attack",
				found);

	}
	@Test(timeout = 1000)
	public void testDragonClassWRITEVariables() {
		Method[] methods = Dragon.class.getDeclaredMethods();
		assertFalse(
				"The \"name\" instance variable in class Dragon is a READ ONLY variable.",
				containsMethodName(methods, "setName"));
		assertFalse(
				"The \"superAttacks\" instance variable in class Dragon is a READ ONLY variable.",
				containsMethodName(methods, "setSuperAttacks"));
		assertFalse(
				"The \"ultimateAttacks\" instance variable in class Dragon is a READ ONLY variable.",
				containsMethodName(methods, "setUltimateAttacks"));
		assertFalse(
				"The \"senzuBeans\" instance variable in class Dragon is a READ ONLY variable.",
				containsMethodName(methods, "setSenzuBeans"));
		assertFalse(
				"The \"abilityPoints\" instance variable in class Dragon is a READ ONLY variable.",
				containsMethodName(methods, "setAbilityPoints"));

	}

	@Test(timeout = 1000)
	public void testDragonConstructorInitialization() {
		ArrayList<SuperAttack> supers = new ArrayList<SuperAttack>();
		SuperAttack s = new SuperAttack("kamehameha", 250);
		supers.add(s);
		ArrayList<UltimateAttack> ultimates = new ArrayList<UltimateAttack>();
		UltimateAttack u = new UltimateAttack("final flash", 450);
		ultimates.add(u);

		Dragon d = new Dragon("dodo", supers, ultimates, 3, 5);
		assertEquals(
				"the constructor of the Dragon class should initialize the instance variable \"name\" correctly",
				"dodo", d.getName());
		assertEquals(
				"the constructor of the Dragon class should initialize the instance variable \"superAttacks\" correctly",
				supers, d.getSuperAttacks());
		assertEquals(
				"the constructor of the Dragon class should initialize the instance variable \"ultimateAttacks\" correctly",
				ultimates, d.getUltimateAttacks());
		assertEquals(
				"the constructor of the Dragon class should initialize the instance variable \"senzuBeans\" correctly",
				3, d.getSenzuBeans());
		assertEquals(
				"the constructor of the Dragon class should initialize the instance variable \"abilityPoints\" correctly",
				5, d.getAbilityPoints());
	}
	@Test(timeout = 1000)
	public void testGameClassMethods() throws NoSuchMethodException,
	SecurityException {
		Method[] methods = Game.class.getDeclaredMethods();
		assertTrue("Game class should have \"loadCSV\" method",
				containsMethodName(methods, "loadCSV"));
		assertTrue("Game class should have \"loadFoes\" method",
				containsMethodName(methods, "loadFoes"));
		assertTrue("Game class should have \"loadAttacks\" method",
				containsMethodName(methods, "loadAttacks"));
		assertTrue("Game class should have \"loadDragons\" method",
				containsMethodName(methods, "loadDragons"));

		boolean found = true;
		Method m = null;
		try {
			m = Game.class.getDeclaredMethod("loadCSV", String.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Game class should have \"loadCSV\" method that takes one String parameter",
				found);
		assertEquals(
				"\"loadCSV\" method in class Game should not be accessed outside that class",
				2, m.getModifiers());

		found = true;
		try {
			m = Game.class.getDeclaredMethod("loadAttacks", String.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Game class should have \"loadAttacks\" method that takes one String parameter",
				found);
		assertTrue(
				"incorrect return type for \"loadAttacks\" method in Game class.",
				m.getReturnType().equals(Void.TYPE));
		assertEquals(
				"\"loadAttacks\" method in class Game should not be accessed outside that class",
				2, m.getModifiers());

		found = true;
		try {
			m = Game.class.getDeclaredMethod("loadFoes", String.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Game class should have \"loadFoes\" method that takes one String parameter",
				found);
		assertTrue(
				"incorrect return type for \"loadFoes\" method in Game class.",
				m.getReturnType().equals(Void.TYPE));
		assertEquals(
				"\"loadFoes\" method in class Game should not be accessed outside that class",
				2, m.getModifiers());

		found = true;
		try {
			m = Game.class.getDeclaredMethod("loadDragons", String.class);
		} catch (Exception e) {
			found = false;
		}
		assertTrue(
				"Game class should have \"loadDragons\" method that takes one String parameter",
				found);
		assertTrue(
				"incorrect return type for \"loadDragons\" method in Game class.",
				m.getReturnType().equals(Void.TYPE));
		assertEquals(
				"\"loadDragons\" method in class Game should not be accessed outside that class",
				2, m.getModifiers());

	}
	@Test(timeout = 1000)
	public void testMapAllCellIntialized() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		World w = new World();
		Game game = new Game();
		w.generateMap(game.getWeakFoes(), game.getStrongFoes());
		Field f = World.class.getDeclaredField("map");
		f.setAccessible(true);
		Cell[][] map = (Cell[][]) f.get(w);

		boolean full = true;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == null) {
					full = false;
					break;
				}
			}
		}
		assertTrue("One or more cells of the map are not intialized", full);
	}
	@Test(timeout = 1000)
	public void testMapSenzoBeansCount() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		World w = new World();
		Game game = new Game();
		w.generateMap(game.getWeakFoes(), game.getStrongFoes());
		Field f = World.class.getDeclaredField("map");
		f.setAccessible(true);
		Cell[][] map = (Cell[][]) f.get(w);

		int senzoBeans = 0;
		boolean full = true;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].getClass().equals(CollectibleCell.class))
					try {
						if (((CollectibleCell) map[i][j]).getCollectible()
								.equals(Collectible.SENZU_BEAN))
							senzoBeans++;
					} catch (NullPointerException e) {
						full = false;
						break;
					}

			}
		}

		assertTrue(
				"The constructor of the World class should intilaize all the map cells",
				full);
		assertTrue("The map should contain 3 to 5 senzoBeans", senzoBeans >= 3
				&& senzoBeans <= 5);

	}
	@Test(timeout = 1000)
	public void testMapSenzoBeansCountRandomlyGenerated()
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, IOException {
		World w = new World();
		Game game = new Game();

		int[] counts = new int[100];

		for (int m = 0; m < 100; m++) {
			w.generateMap(game.getWeakFoes(), game.getStrongFoes());
			Field f = World.class.getDeclaredField("map");
			f.setAccessible(true);
			Cell[][] map = (Cell[][]) f.get(w);
			int senzoBeans = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j].getClass().equals(CollectibleCell.class))
						try {
							if (((CollectibleCell) map[i][j]).getCollectible()
									.equals(Collectible.SENZU_BEAN))
								senzoBeans++;
						} catch (NullPointerException e) {
						}

				}
			}
			counts[m] = senzoBeans;
			w = new World();
		}

		int three = 0;
		int four = 0;
		int five = 0;

		for (int e : counts) {
			if (e == 3)
				three++;
			else if (e == 4)
				four++;
			else if (e == 5)
				five++;
		}
		boolean random = true;
		if (three == 100 || four == 100 || five == 100)
			random = false;
		assertTrue(
				"The number of senzu beans should be randomly generated in the map",
				random);
	}
	@Test(timeout = 1000)
	public void testMapSenzoBeansLocationsRandomlyGenerated()
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, IOException {
		World w = new World();
		Game game = new Game();
		ArrayList<ArrayList<Location>> locations = new ArrayList<ArrayList<Location>>();
		for (int m = 0; m < 50; m++) {
			w.generateMap(game.getWeakFoes(), game.getStrongFoes());
			Field f = World.class.getDeclaredField("map");
			f.setAccessible(true);
			Cell[][] map = (Cell[][]) f.get(w);
			ArrayList<Location> mapLocations = new ArrayList<Location>();
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j].getClass().equals(CollectibleCell.class))
						try {
							if (((CollectibleCell) map[i][j]).getCollectible()
									.equals(Collectible.SENZU_BEAN))
								mapLocations.add(new Location(i, j));
						} catch (NullPointerException e) {
						}

				}
			}
			locations.add(mapLocations);
			w = new World();
		}

		boolean random = true;
		for (int i = 0; i < locations.size(); i++) {
			ArrayList<Location> l = locations.get(i);
			for (int j = i + 1; j < locations.size(); j++) {
				boolean equal = false;
				ArrayList<Location> m = locations.get(j);
				if (l.size() == m.size()) {
					equal = true;
					for (int x = 0; x < l.size(); x++) {
						if (!l.get(x).equals(m.get(x)))
							equal = false;
					}
				}
				if (equal) {
					random = false;
					break;
				}
			}
		}

		assertTrue(
				"The locations of the senzu beans should be randomly generated in the map",
				random);

	}
	@Test(timeout = 1000)
	public void testMapStrongFoesCount() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		World w = new World();
		Game game = new Game();
		w.generateMap(game.getWeakFoes(), game.getStrongFoes());
		Field f = World.class.getDeclaredField("map");
		f.setAccessible(true);
		Cell[][] map = (Cell[][]) f.get(w);

		int strongFoes = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].getClass().equals(FoeCell.class))
					try {
						if (((FoeCell) map[i][j]).getFoe().isStrong() == true)
							strongFoes++;
					} catch (NullPointerException e) {
					}
			}
		}
		assertTrue("The map should contain only 1 strong foe", strongFoes == 1);
	}

	@Test(timeout = 1000)
	public void testMapStrongFoesLocation() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		World w = new World();
		Game game = new Game();
		w.generateMap(game.getWeakFoes(), game.getStrongFoes());
		Field f = World.class.getDeclaredField("map");
		f.setAccessible(true);
		Cell[][] map = (Cell[][]) f.get(w);
		assertTrue(
				"The strong foe (Boss) should be located at cell of row zero and column zero",
				map[0][0].getClass().equals(FoeCell.class)
				&& ((FoeCell) map[0][0]).getFoe().isStrong() == true);
	}

	@Test(timeout = 1000)
	public void testMapPlayerLocation() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		World w = new World();
		Game game = new Game();
		w.generateMap(game.getWeakFoes(), game.getStrongFoes());
		Field f = World.class.getDeclaredField("map");
		f.setAccessible(true);
		Cell[][] map = (Cell[][]) f.get(w);

		assertTrue(
				"The cell of row 9 and column 9 should be an empty cell, as this is the player's location",
				map[9][9].getClass().equals(EmptyCell.class));

	}
	public static String toString(Cell[][] map) {
		String s = "";
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				s += map[i][j].toString() + "\t";
			}
			s += '\n';
		}
		return s;
	}

	@Test(timeout = 1000)
	public void testLoadFoesMethod() throws IllegalAccessException,
	IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException, IOException {
		PrintWriter foesWriter = new PrintWriter("FoesTest.csv");
		foesWriter.println("Goku,10,3000,350,400,5,6,TRUE");
		foesWriter.println("Kamehameha");
		foesWriter.println("Super Kamehameha,Spirit Bomb");
		foesWriter.println("Gohan (Kid),6,1500,200,150,7,5,TRUE");
		foesWriter.println("Masenko,Maximum Charge");
		foesWriter.println("Explosive Assault");
		foesWriter.println("Krillin,5,1400,150,200,4,5,FALSE");
		foesWriter.println("Ki Blast Thrust,Kamehameha");
		foesWriter.println("");
		foesWriter.println("Yamcha,5,1250,150,100,3,4,FALSE");
		foesWriter.println("");
		foesWriter.println("");
		foesWriter.println("Navel,2,1250,200,50,3,4,FALSE");
		foesWriter.println("Beam Rifle");
		foesWriter.println("");

		foesWriter.close();

		Game g = new Game();
		g.getWeakFoes().clear();
		g.getStrongFoes().clear();
		Method method = Game.class.getDeclaredMethod("loadFoes",
				new Class[] { String.class });
		method.setAccessible(true);
		method.invoke(g, "FoesTest.csv");
		ArrayList<NonPlayableFighter> testStrongFoes = g.getStrongFoes();

		ArrayList<NonPlayableFighter> testWeakFoes = g.getWeakFoes();

		assertEquals(
				"The loaded strong foes ArrayList doesn't contain the same number of weak foes given in the CSV file",
				2, testStrongFoes.size());

		assertEquals(
				"The loaded weak foes ArrayList doesn't contain the same number of weak foes given in the CSV file",
				3, testWeakFoes.size());

		assertEquals(
				"Strong foe name is not loaded correctly from the CSV file of Foes",
				"Goku", testStrongFoes.get(0).getName());
		assertEquals(
				"Strong foe level is not loaded correctly from the CSV file of Foes",
				10, testStrongFoes.get(0).getLevel());
		assertEquals(
				"Strong foe maxHealthPoints is not loaded correctly from the CSV file of Foes",
				3000, testStrongFoes.get(0).getMaxHealthPoints());
		assertEquals(
				"Strong foe blast damage is not loaded correctly from the CSV file of Foes",
				350, testStrongFoes.get(0).getBlastDamage());
		assertEquals(
				"Strong foe physical damage is not loaded correctly from the CSV file of Foes",
				400, testStrongFoes.get(0).getPhysicalDamage());
		assertEquals(
				"Strong foe maxKi is not loaded correctly from the CSV file of Foes",
				5, testStrongFoes.get(0).getMaxKi());
		assertEquals(
				"Strong foe maxStamina is not loaded correctly from the CSV file of Foes",
				6, testStrongFoes.get(0).getMaxStamina());
		assertTrue(
				"\"Strong\" boolean for a Strong foe should be true while loading from the CSV file of Foes",
				testStrongFoes.get(0).isStrong());

		assertEquals(
				"Super Attacks of Strong foe doesn't contain the same number of super attacks given in the CSV file",
				1, testStrongFoes.get(0).getSuperAttacks().size());
		assertEquals(
				"Super Attack of Strong foe is not loaded correctly from the CSV file of Foes",
				"Kamehameha", testStrongFoes.get(0).getSuperAttacks().get(0)
				.getName());


		assertEquals(
				"Ultimate Attacks of Strong foe doesn't contain the same number of ultimate attacks given in the CSV file",
				2, testStrongFoes.get(0).getUltimateAttacks().size());
		assertEquals(
				"Ultimate Attack of Strong foe is not loaded correctly from the CSV file of Foes",
				"Super Kamehameha", testStrongFoes.get(0).getUltimateAttacks()
				.get(0).getName());

		assertEquals(
				"Ultimate Attack of Strong foe is not loaded correctly from the CSV file of Foes",
				"Spirit Bomb", testStrongFoes.get(0).getUltimateAttacks()
				.get(1).getName());

		assertEquals(
				"Strong foe name is not loaded correctly from the CSV file of Foes",
				"Gohan (Kid)", testStrongFoes.get(1).getName());
		assertEquals(
				"Strong foe level is not loaded correctly from the CSV file of Foes",
				6, testStrongFoes.get(1).getLevel());
		assertEquals(
				"Strong foe maxHealthPoints is not loaded correctly from the CSV file of Foes",
				1500, testStrongFoes.get(1).getMaxHealthPoints());
		assertEquals(
				"Strong foe blast damage is not loaded correctly from the CSV file of Foes",
				200, testStrongFoes.get(1).getBlastDamage());
		assertEquals(
				"Strong foe physical damage is not loaded correctly from the CSV file of Foes",
				150, testStrongFoes.get(1).getPhysicalDamage());
		assertEquals(
				"Strong foe maxKi is not loaded correctly from the CSV file of Foes",
				7, testStrongFoes.get(1).getMaxKi());
		assertEquals(
				"Strong foe maxStamina is not loaded correctly from the CSV file of Foes",
				5, testStrongFoes.get(1).getMaxStamina());
		assertTrue(
				"\"Strong\" boolean for a Strong foe should be true while loading from the CSV file of Foes",
				testStrongFoes.get(1).isStrong());


		assertEquals(
				"Super Attacks of Strong foe doesn't contain the same number of super attacks given in the CSV file",
				2, testStrongFoes.get(1).getSuperAttacks().size());
		assertEquals(
				"Super Attack of Strong foe is not loaded correctly from the CSV file of Foes",
				"Masenko", testStrongFoes.get(1).getSuperAttacks().get(0)
				.getName());
		assertEquals(
				"Super Attack of Strong foe is not loaded correctly from the CSV file of Foes",
				"Maximum Charge", testStrongFoes.get(1).getSuperAttacks()
				.get(1).getName());


		assertEquals(
				"Ultimate Attacks of Strong foe doesn't contain the same number of ultimate attacks given in the CSV file",
				1, testStrongFoes.get(1).getUltimateAttacks().size());
		assertEquals(
				"Ultimate Attack of Strong foe is not loaded correctly from the CSV file of Foes",
				"Explosive Assault", testStrongFoes.get(1).getUltimateAttacks()
				.get(0).getName());


		assertEquals(
				"Weak foe name is not loaded correctly from the CSV file of Foes",
				"Krillin", testWeakFoes.get(0).getName());
		assertEquals(
				"Weak foe level is not loaded correctly from the CSV file of Foes",
				5, testWeakFoes.get(0).getLevel());
		assertEquals(
				"Weak foe maxHealthPoints is not loaded correctly from the CSV file of Foes",
				1400, testWeakFoes.get(0).getMaxHealthPoints());
		assertEquals(
				"Weak foe blast damage is not loaded correctly from the CSV file of Foes",
				150, testWeakFoes.get(0).getBlastDamage());
		assertEquals(
				"Weak foe physical damage is not loaded correctly from the CSV file of Foes",
				200, testWeakFoes.get(0).getPhysicalDamage());
		assertEquals(
				"Weak foe maxKi is not loaded correctly from the CSV file of Foes",
				4, testWeakFoes.get(0).getMaxKi());
		assertEquals(
				"Weak foe maxStamina is not loaded correctly from the CSV file of Foes",
				5, testWeakFoes.get(0).getMaxStamina());
		assertFalse(
				"\"Strong\" boolean for a weak foe should be false while loading from the CSV file of Foes",
				testWeakFoes.get(0).isStrong());


		assertEquals(
				"Super Attacks of Weak foe doesn't contain the same number of super attacks given in the CSV file",
				2, testWeakFoes.get(0).getSuperAttacks().size());
		assertEquals(
				"Super Attack of Weak foe is not loaded correctly from the CSV file of Foes",
				"Ki Blast Thrust", testWeakFoes.get(0).getSuperAttacks().get(0)
				.getName());
		assertEquals(
				"Super Attack of Weak foe is not loaded correctly from the CSV file of Foes",
				"Kamehameha", testWeakFoes.get(0).getSuperAttacks().get(1)
				.getName());


		assertEquals(
				"Ultimate Attacks of Weak foe doesn't contain the same number of ultimate attacks given in the CSV file",
				0, testWeakFoes.get(0).getUltimateAttacks().size());


		assertEquals(
				"Weak foe name is not loaded correctly from the CSV file of Foes",
				"Yamcha", testWeakFoes.get(1).getName());
		assertEquals(
				"Weak foe level is not loaded correctly from the CSV file of Foes",
				5, testWeakFoes.get(1).getLevel());
		assertEquals(
				"Weak foe maxHealthPoints is not loaded correctly from the CSV file of Foes",
				1250, testWeakFoes.get(1).getMaxHealthPoints());
		assertEquals(
				"Weak foe blast damage is not loaded correctly from the CSV file of Foes",
				150, testWeakFoes.get(1).getBlastDamage());
		assertEquals(
				"Weak foe physical damage is not loaded correctly from the CSV file of Foes",
				100, testWeakFoes.get(1).getPhysicalDamage());
		assertEquals(
				"Weak foe maxKi is not loaded correctly from the CSV file of Foes",
				3, testWeakFoes.get(1).getMaxKi());
		assertEquals(
				"Weak foe maxStamina is not loaded correctly from the CSV file of Foes",
				4, testWeakFoes.get(1).getMaxStamina());
		assertFalse(
				"\"Strong\" boolean for a weak foe should be false while loading from the CSV file of Foes",
				testWeakFoes.get(1).isStrong());


		assertEquals(
				"Super Attacks of Weak foe doesn't contain the same number of super attacks given in the CSV file",
				0, testWeakFoes.get(1).getSuperAttacks().size());


		assertEquals(
				"Ultimate Attacks of Weak foe doesn't contain the same number of ultimate attacks given in the CSV file",
				0, testWeakFoes.get(1).getUltimateAttacks().size());


		assertEquals(
				"Weak foe name is not loaded correctly from the CSV file of Foes",
				"Navel", testWeakFoes.get(2).getName());
		assertEquals(
				"Weak foe level is not loaded correctly from the CSV file of Foes",
				2, testWeakFoes.get(2).getLevel());
		assertEquals(
				"Weak foe maxHealthPoints is not loaded correctly from the CSV file of Foes",
				1250, testWeakFoes.get(2).getMaxHealthPoints());
		assertEquals(
				"Weak foe blast damage is not loaded correctly from the CSV file of Foes",
				200, testWeakFoes.get(2).getBlastDamage());
		assertEquals(
				"Weak foe physical damage is not loaded correctly from the CSV file of Foes",
				50, testWeakFoes.get(2).getPhysicalDamage());
		assertEquals(
				"Weak foe maxKi is not loaded correctly from the CSV file of Foes",
				3, testWeakFoes.get(2).getMaxKi());
		assertEquals(
				"Weak foe maxStamina is not loaded correctly from the CSV file of Foes",
				4, testWeakFoes.get(2).getMaxStamina());
		assertFalse(
				"\"Strong\" boolean for a weak foe should be false while loading from the CSV file of Foes",
				testWeakFoes.get(2).isStrong());


		assertEquals(
				"Super Attacks of Weak foe doesn't contain the same number of super attacks given in the CSV file",
				1, testWeakFoes.get(2).getSuperAttacks().size());
		assertEquals(
				"Super Attack of Weak foe is not loaded correctly from the CSV file of Foes",
				"Beam Rifle", testWeakFoes.get(2).getSuperAttacks().get(0)
				.getName());


		assertEquals(
				"Ultimate Attacks of Weak foe doesn't contain the same number of ultimate attacks given in the CSV file",
				0, testWeakFoes.get(2).getUltimateAttacks().size());
		
		try {
			File file = new File("FoesTest.csv");
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(timeout = 1000)
	public void testLoadedFoes() throws IOException {
		ArrayList<NonPlayableFighter> strongFoes = new ArrayList<NonPlayableFighter>();
		ArrayList<NonPlayableFighter> weakFoes = new ArrayList<NonPlayableFighter>();


		NonPlayableFighter strong1 = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 6, true,null,null);
		ArrayList<SuperAttack> superAttacks1 = new ArrayList<SuperAttack>();
		superAttacks1.add(new SuperAttack("Kamehameha", 250));
		superAttacks1.add(new MaximumCharge());
		strong1.setSuperAttacks(superAttacks1);
		ArrayList<UltimateAttack> ultimateAttacks1 = new ArrayList<UltimateAttack>();
		ultimateAttacks1.add(new UltimateAttack("Super Kamehameha", 450));
		ultimateAttacks1.add(new UltimateAttack("Spirit Bomb", 500));
		strong1.setUltimateAttacks(ultimateAttacks1);

		NonPlayableFighter strong2 = new NonPlayableFighter("Gohan (Kid)", 6,
				1500, 200, 150, 5, 5, true,null,null);
		ArrayList<SuperAttack> superAttacks2 = new ArrayList<SuperAttack>();
		superAttacks2.add(new SuperAttack("Masenko", 200));
		superAttacks2.add(new MaximumCharge());
		strong2.setSuperAttacks(superAttacks2);
		ArrayList<UltimateAttack> ultimateAttacks2 = new ArrayList<UltimateAttack>();
		ultimateAttacks2.add(new UltimateAttack("Explosive Assault", 450));
		strong2.setUltimateAttacks(ultimateAttacks2);

		NonPlayableFighter strong3 = new NonPlayableFighter("Krillin", 5, 1400,
				150, 200, 4, 5, true,null,null);
		ArrayList<SuperAttack> superAttacks3 = new ArrayList<SuperAttack>();
		superAttacks3.add(new SuperAttack("Kamehameha", 250));
		superAttacks3.add(new SuperAttack("Destructo Disc", 200));
		strong3.setSuperAttacks(superAttacks3);
		ArrayList<UltimateAttack> ultimateAttacks3 = new ArrayList<UltimateAttack>();
		ultimateAttacks3.add(new UltimateAttack("Scatter-Kamehameha", 400));
		strong3.setUltimateAttacks(ultimateAttacks3);

		NonPlayableFighter strong4 = new NonPlayableFighter("Yamcha", 3, 1250,
				150, 100, 3, 4, true,null,null);
		ArrayList<SuperAttack> superAttacks4 = new ArrayList<SuperAttack>();
		superAttacks4.add(new SuperAttack("Ki Blast Thrust", 150));
		superAttacks4.add(new SuperAttack("Kamehameha", 250));
		strong4.setSuperAttacks(superAttacks4);
		ArrayList<UltimateAttack> ultimateAttacks4 = new ArrayList<UltimateAttack>();
		ultimateAttacks4.add(new UltimateAttack("Spirit Ball", 400));
		strong4.setUltimateAttacks(ultimateAttacks4);

		NonPlayableFighter strong5 = new NonPlayableFighter("Tien", 5, 1450,
				200, 100, 6, 3, true,null,null);
		ArrayList<SuperAttack> superAttacks5 = new ArrayList<SuperAttack>();
		superAttacks5.add(new SuperAttack("Dodon Ray", 200));
		superAttacks5.add(new SuperAttack("Tri-Beam", 250));
		strong5.setSuperAttacks(superAttacks5);
		ArrayList<UltimateAttack> ultimateAttacks5 = new ArrayList<UltimateAttack>();
		ultimateAttacks5.add(new UltimateAttack("Neo Tri-Beam", 400));
		strong5.setUltimateAttacks(ultimateAttacks5);

		NonPlayableFighter strong6 = new NonPlayableFighter("Piccolo", 7, 1750,
				200, 200, 4, 6, true,null,null);
		ArrayList<SuperAttack> superAttacks6 = new ArrayList<SuperAttack>();
		superAttacks6.add(new MaximumCharge());
		superAttacks6.add(new SuperAttack("Evil Explosion", 200));
		superAttacks6.add(new SuperAttack("Masenko", 200));
		strong6.setSuperAttacks(superAttacks6);
		ArrayList<UltimateAttack> ultimateAttacks6 = new ArrayList<UltimateAttack>();
		ultimateAttacks6.add(new UltimateAttack("Special Beam Cannon", 400));
		strong6.setUltimateAttacks(ultimateAttacks6);

		NonPlayableFighter strong7 = new NonPlayableFighter("Raditz", 3, 1200,
				200, 50, 4, 3, true,null,null);
		ArrayList<SuperAttack> superAttacks7 = new ArrayList<SuperAttack>();
		superAttacks7.add(new SuperAttack("Double Sunday", 150));
		superAttacks7.add(new SuperAttack("Shining Friday", 150));
		strong7.setSuperAttacks(superAttacks7);
		ArrayList<UltimateAttack> ultimateAttacks7 = new ArrayList<UltimateAttack>();
		ultimateAttacks7.add(new UltimateAttack("Weekend", 450));
		ultimateAttacks7.add(new UltimateAttack("Vacation Delete", 400));
		strong7.setUltimateAttacks(ultimateAttacks7);

		NonPlayableFighter strong8 = new NonPlayableFighter("Nappa", 8, 2000,
				200, 200, 6, 4, true,null,null);
		ArrayList<SuperAttack> superAttacks8 = new ArrayList<SuperAttack>();
		superAttacks8.add(new SuperAttack("Bomber DX", 200));
		superAttacks8.add(new MaximumCharge());
		strong8.setSuperAttacks(superAttacks8);
		ArrayList<UltimateAttack> ultimateAttacks8 = new ArrayList<UltimateAttack>();
		ultimateAttacks8.add(new UltimateAttack("Break Cannon", 475));
		ultimateAttacks8.add(new UltimateAttack("Giant Storm", 475));
		strong8.setUltimateAttacks(ultimateAttacks8);

		NonPlayableFighter strong9 = new NonPlayableFighter("Vegeta", 10, 3000,
				400, 350, 6, 5, true,null,null);
		ArrayList<SuperAttack> superAttacks9 = new ArrayList<SuperAttack>();
		superAttacks9.add(new MaximumCharge());
		superAttacks9.add(new SuperAttack("Galick Gun", 200));
		superAttacks9.add(new SuperAttack("Shine Shot", 200));
		strong9.setSuperAttacks(superAttacks9);
		ArrayList<UltimateAttack> ultimateAttacks9 = new ArrayList<UltimateAttack>();
		ultimateAttacks9.add(new UltimateAttack("Final Flash", 450));
		strong9.setUltimateAttacks(ultimateAttacks9);


		NonPlayableFighter weak1 = new NonPlayableFighter("Saibaman", 2, 1050,
				50, 50, 3, 3, false,null,null);
		ArrayList<SuperAttack> superAttacks_1 = new ArrayList<SuperAttack>();
		superAttacks_1.add(new SuperAttack("Saibabeam", 100));
		weak1.setSuperAttacks(superAttacks_1);
		ArrayList<UltimateAttack> ultimateAttacks_1 = new ArrayList<UltimateAttack>();
		weak1.setUltimateAttacks(ultimateAttacks_1);

		NonPlayableFighter weak2 = new NonPlayableFighter("Tennenmen", 1, 1000,
				50, 50, 3, 3, false,null,null);
		ArrayList<SuperAttack> superAttacks_2 = new ArrayList<SuperAttack>();
		superAttacks_2.add(new SuperAttack("Saibabeam", 100));
		weak2.setSuperAttacks(superAttacks_2);
		ArrayList<UltimateAttack> ultimateAttacks_2 = new ArrayList<UltimateAttack>();
		weak2.setUltimateAttacks(ultimateAttacks_2);

		NonPlayableFighter weak3 = new NonPlayableFighter("Jinkoumen", 2, 1100,
				50, 50, 3, 3, false,null,null);
		ArrayList<SuperAttack> superAttacks_3 = new ArrayList<SuperAttack>();
		superAttacks_3.add(new SuperAttack("Saibabeam", 100));
		weak3.setSuperAttacks(superAttacks_3);
		ArrayList<UltimateAttack> ultimateAttacks_3 = new ArrayList<UltimateAttack>();
		weak3.setUltimateAttacks(ultimateAttacks_3);

		NonPlayableFighter weak4 = new NonPlayableFighter("Raspberry", 3, 1200,
				150, 50, 3, 4, false,null,null);
		ArrayList<SuperAttack> superAttacks_4 = new ArrayList<SuperAttack>();
		superAttacks_4.add(new SuperAttack("Beam Rifle", 100));
		weak4.setSuperAttacks(superAttacks_4);
		ArrayList<UltimateAttack> ultimateAttacks_4 = new ArrayList<UltimateAttack>();
		weak4.setUltimateAttacks(ultimateAttacks_4);

		NonPlayableFighter weak5 = new NonPlayableFighter("Navel", 2, 1250,
				200, 50, 3, 4, false,null,null);
		ArrayList<SuperAttack> superAttacks_5 = new ArrayList<SuperAttack>();
		superAttacks_5.add(new SuperAttack("Beam Rifle", 100));
		weak5.setSuperAttacks(superAttacks_5);
		ArrayList<UltimateAttack> ultimateAttacks_5 = new ArrayList<UltimateAttack>();
		weak5.setUltimateAttacks(ultimateAttacks_5);

		NonPlayableFighter weak6 = new NonPlayableFighter("Monre", 3, 1300,
				150, 50, 3, 4, false,null,null);
		ArrayList<SuperAttack> superAttacks_6 = new ArrayList<SuperAttack>();
		superAttacks_6.add(new SuperAttack("Beam Rifle", 100));
		weak6.setSuperAttacks(superAttacks_6);
		ArrayList<UltimateAttack> ultimateAttacks_6 = new ArrayList<UltimateAttack>();
		weak6.setUltimateAttacks(ultimateAttacks_6);

		NonPlayableFighter weak7 = new NonPlayableFighter("Ramon", 3, 1200,
				150, 100, 3, 3, false,null,null);
		ArrayList<SuperAttack> superAttacks_7 = new ArrayList<SuperAttack>();
		superAttacks_7.add(new SuperAttack("Energy Shot", 150));
		weak7.setSuperAttacks(superAttacks_7);
		ArrayList<UltimateAttack> ultimateAttacks_7 = new ArrayList<UltimateAttack>();
		weak7.setUltimateAttacks(ultimateAttacks_7);

		NonPlayableFighter weak8 = new NonPlayableFighter("Orlen", 2, 1300,
				150, 50, 3, 4, false,null,null);
		ArrayList<SuperAttack> superAttacks_8 = new ArrayList<SuperAttack>();
		superAttacks_8.add(new SuperAttack("Energy Shot", 150));
		weak8.setSuperAttacks(superAttacks_8);
		ArrayList<UltimateAttack> ultimateAttacks_8 = new ArrayList<UltimateAttack>();
		weak8.setUltimateAttacks(ultimateAttacks_8);

		strongFoes.add(strong1);
		strongFoes.add(strong2);
		strongFoes.add(strong3);
		strongFoes.add(strong4);
		strongFoes.add(strong5);
		strongFoes.add(strong6);
		strongFoes.add(strong7);
		strongFoes.add(strong8);
		strongFoes.add(strong9);

		weakFoes.add(weak1);
		weakFoes.add(weak2);
		weakFoes.add(weak3);
		weakFoes.add(weak4);
		weakFoes.add(weak5);
		weakFoes.add(weak6);
		weakFoes.add(weak7);
		weakFoes.add(weak8);

		Game game = new Game();
		ArrayList<NonPlayableFighter> strongFoesToTest = game.getStrongFoes();
		ArrayList<NonPlayableFighter> weakFoesToTest = game.getWeakFoes();

		assertEquals(
				"The loaded strongFoes ArrayList doesn't contain the same number of strong foes given in the CSV file",
				strongFoes.size(), strongFoesToTest.size());

		assertEquals(
				"The loaded weakFoes ArrayList doesn't contain the same number of weak foes given in the CSV file",
				weakFoes.size(), weakFoesToTest.size());


		for (int i = 0; i < strongFoes.size(); i++) {
			assertEquals(
					"A strong Foe should have the same name as its corresponding input in the CSV file",
					strongFoes.get(i).getName(), strongFoesToTest.get(i)
					.getName());
			assertEquals(strongFoes.get(i).getName() + " strong foe should have the same level as its corresponding input in the CSV file",
					strongFoes.get(i).getLevel(), strongFoesToTest.get(i)
					.getLevel());
			assertEquals(strongFoes.get(i).getName() + " strong foe should have the same health points as its corresponding input in the CSV file",
					strongFoes.get(i).getHealthPoints(), strongFoesToTest
					.get(i).getHealthPoints());

			assertEquals(strongFoes.get(i).getName() + " strong foe should have the same blast damage as its corresponding input in the CSV file",
					strongFoes.get(i).getBlastDamage(), strongFoesToTest.get(i)
					.getBlastDamage());
			assertEquals(strongFoes.get(i).getName() + " strong foe should have the same physical damage as its corresponding input in the CSV file",
					strongFoes.get(i).getPhysicalDamage(), strongFoesToTest
					.get(i).getPhysicalDamage());
			assertEquals(strongFoes.get(i).getName() + " strong foe should have the same ki as its corresponding input in the CSV file",
					strongFoes.get(i).getMaxKi(), strongFoesToTest.get(i)
					.getMaxKi());
			assertEquals(strongFoes.get(i).getName() + " strong foe should have the same stamina as its corresponding input in the CSV file",
					strongFoes.get(i).getMaxStamina(), strongFoesToTest.get(i)
					.getMaxStamina());
			assertEquals(strongFoes.get(i).getName() + " strong foe should have the value strong equal to true after loaded from the CSV file",
					strongFoes.get(i).isStrong(), strongFoesToTest.get(i)
					.isStrong());


			assertEquals(
					"The loaded super Attacks of " + strongFoes.get(i).getName() + " strong foe doesn't contain its same number of super attacks given in the CSV file",
					strongFoes.get(i).getSuperAttacks().size(),
					strongFoesToTest.get(i).getSuperAttacks().size());

			for (int j = 0; j < strongFoes.get(i).getSuperAttacks().size(); j++) {
				assertEquals(
						"The super attack " + strongFoes.get(i).getSuperAttacks().get(j).getName() + " of " + strongFoes.get(i).getName() + " strong foe should have the same type as its corresponding input in the CSV file",
						strongFoes.get(i).getSuperAttacks().get(j).getClass(),
						strongFoesToTest.get(i).getSuperAttacks().get(j)
						.getClass());

				assertEquals(
						"The super attack " + strongFoes.get(i).getSuperAttacks().get(j).getName() + " of " + strongFoes.get(i).getName() + " strong foe should have the same name as its corresponding input in the CSV file",
						strongFoes.get(i).getSuperAttacks().get(j).getName(),
						strongFoesToTest.get(i).getSuperAttacks().get(j)
						.getName());

				assertEquals(
						"The super attack " + strongFoes.get(i).getSuperAttacks().get(j).getName() + " of " + strongFoes.get(i).getName() + " strong foe should have the same damage as its corresponding input in the CSV file",
						strongFoes.get(i).getSuperAttacks().get(j).getDamage(),
						strongFoesToTest.get(i).getSuperAttacks().get(j)
						.getDamage());
			}

			assertEquals(
					"The loaded ultimate Attacks of " + strongFoes.get(i).getName() + " strong foe doesn't contain its same number of ultimate attacks given in the CSV file",
					strongFoes.get(i).getUltimateAttacks().size(),
					strongFoesToTest.get(i).getUltimateAttacks().size());

			for (int j = 0; j < strongFoes.get(i).getUltimateAttacks().size(); j++) {
				assertEquals(
						"The ultimate attack " + strongFoes.get(i).getUltimateAttacks().get(j).getName() + " of " + strongFoes.get(i).getName() + " strong foe should have the same type as its corresponding input in the CSV file",
						strongFoes.get(i).getUltimateAttacks().get(j)
						.getClass(), strongFoesToTest.get(i)
						.getUltimateAttacks().get(j).getClass());

				assertEquals(
						"The ultimate attack " + strongFoes.get(i).getUltimateAttacks().get(j).getName() + " of " + strongFoes.get(i).getName() + " strong foe should have the same name as its corresponding input in the CSV file",
						strongFoes.get(i).getUltimateAttacks().get(j).getName(),
						strongFoesToTest.get(i).getUltimateAttacks().get(j)
						.getName());

				assertEquals(
						"The ultimate attack " + strongFoes.get(i).getUltimateAttacks().get(j).getName() + " of " + strongFoes.get(i).getName() + " strong foe should have the same damage as its corresponding input in the CSV file",
						strongFoes.get(i).getUltimateAttacks().get(j)
						.getDamage(), strongFoesToTest.get(i)
						.getUltimateAttacks().get(j).getDamage());
			}

		}


		for (int i = 0; i < weakFoes.size(); i++) {
			assertEquals("A weak foe should have the same name as its corresponding input in the CSV file",
					weakFoes.get(i).getName(), weakFoesToTest.get(i).getName());
			assertEquals(weakFoes.get(i).getName() + " weak foe should have the same level as its corresponding input in the CSV file",
					weakFoes.get(i).getLevel(), weakFoesToTest.get(i)
					.getLevel());
			assertEquals(weakFoes.get(i).getName() + " weak foe should have the same health points as its corresponding input in the CSV file",
					weakFoes.get(i).getHealthPoints(), weakFoesToTest.get(i)
					.getHealthPoints());

			assertEquals(weakFoes.get(i).getName() + " weak foe should have the same blast damage as its corresponding input in the CSV file",
					weakFoes.get(i).getBlastDamage(), weakFoesToTest.get(i)
					.getBlastDamage());
			assertEquals(weakFoes.get(i).getName() + " weak foe should have the same physical damage as its corresponding input in the CSV file",
					weakFoes.get(i).getPhysicalDamage(), weakFoesToTest.get(i)
					.getPhysicalDamage());
			assertEquals(weakFoes.get(i).getName() + " weak foe should have the same ki as its corresponding input in the CSV file",
					weakFoes.get(i).getMaxKi(), weakFoesToTest.get(i)
					.getMaxKi());
			assertEquals(weakFoes.get(i).getName() + " weak foe should have the same stamina as its corresponding input in the CSV file",
					weakFoes.get(i).getMaxStamina(), weakFoesToTest.get(i)
					.getMaxStamina());
			assertEquals(weakFoes.get(i).getName() + " weak foe should have the value strong equal to false after loaded from the CSV file",
					weakFoes.get(i).isStrong(), weakFoesToTest.get(i)
					.isStrong());


			assertEquals(
					"The loaded super Attacks of " + weakFoes.get(i).getName() + " weak foe doesn't contain its same number of super attacks given in the CSV file",
					weakFoes.get(i).getSuperAttacks().size(), weakFoesToTest
					.get(i).getSuperAttacks().size());

			for (int j = 0; j < weakFoes.get(i).getSuperAttacks().size(); j++) {
				assertEquals(
						"The super attack " + weakFoes.get(i).getSuperAttacks().get(j).getName() + " of " + weakFoes.get(i).getName() + " weak foe should have the same type as its corresponding input in the CSV file",
						weakFoes.get(i).getSuperAttacks().get(j).getClass(),
						weakFoesToTest.get(i).getSuperAttacks().get(j)
						.getClass());

				assertEquals(
						"The super attack " + weakFoes.get(i).getSuperAttacks().get(j).getName() + " of " + weakFoes.get(i).getName() + " weak foe should have the same name as its corresponding input in the CSV file",
						weakFoes.get(i).getSuperAttacks().get(j).getName(),
						weakFoesToTest.get(i).getSuperAttacks().get(j)
						.getName());

				assertEquals(
						"The super attack " + weakFoes.get(i).getSuperAttacks().get(j).getName() + " of " + weakFoes.get(i).getName() + " weak foe should have the same damage as its corresponding input in the CSV file",
						weakFoes.get(i).getSuperAttacks().get(j).getDamage(),
						weakFoesToTest.get(i).getSuperAttacks().get(j)
						.getDamage());
			}

			assertEquals(
					"The loaded ultimate Attacks of " + weakFoes.get(i).getName() + " weak foe doesn't contain its same number of ultimate attacks given in the CSV file",
					weakFoes.get(i).getUltimateAttacks().size(), weakFoesToTest
					.get(i).getUltimateAttacks().size());

			for (int j = 0; j < weakFoes.get(i).getUltimateAttacks().size(); j++) {
				assertEquals(
						"The ultimate attack " + weakFoes.get(i).getUltimateAttacks().get(j).getName() + " of " + weakFoes.get(i).getName() + " weak foe should have the same type as its corresponding input in the CSV file",
						weakFoes.get(i).getUltimateAttacks().get(j).getClass(),
						weakFoesToTest.get(i).getUltimateAttacks().get(j)
						.getClass());

				assertEquals(
						"The ultimate attack " + weakFoes.get(i).getUltimateAttacks().get(j).getName() + " of " + weakFoes.get(i).getName() + " weak foe should have the same name as its corresponding input in the CSV file",
						weakFoes.get(i).getUltimateAttacks().get(j).getName(),
						weakFoesToTest.get(i).getUltimateAttacks().get(j)
						.getName());

				assertEquals(
						"The ultimate attack " + weakFoes.get(i).getUltimateAttacks().get(j).getName() + " of " + weakFoes.get(i).getName() + " weak foe should have the same damage as its corresponding input in the CSV file",
						weakFoes.get(i).getUltimateAttacks().get(j).getDamage(),
						weakFoesToTest.get(i).getUltimateAttacks().get(j)
						.getDamage());
			}
		}
	}
	@Test(timeout = 1000)
	public void testLoadedDragons() throws IOException {
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();

		ArrayList<SuperAttack> superAttacks1 = new ArrayList<SuperAttack>();
		superAttacks1.add(new SuperAttack("Big Bang Kamehameha", 350));
		superAttacks1.add(new SuperAttack("Emperor's Death Beam", 350));
		superAttacks1.add(new SuperAttack("Demon Wave", 300));
		superAttacks1.add(new SuperAttack("Guilty Flash", 300));
		ArrayList<UltimateAttack> ultimateAttacks1 = new ArrayList<UltimateAttack>();
		ultimateAttacks1.add(new UltimateAttack("Super Big Bang Kamehameha",525));
		ultimateAttacks1.add(new UltimateAttack("Final Shine Attack", 500));
		ultimateAttacks1.add(new UltimateAttack("Final Galick Gun", 500));
		ultimateAttacks1.add(new UltimateAttack("Explosive Demon Wave", 450));
		Dragon dragon1 = new Dragon("Shenron", superAttacks1, ultimateAttacks1, 10, 5);
		dragons.add(dragon1);


		ArrayList<SuperAttack> superAttacks2 = new ArrayList<SuperAttack>();
		superAttacks2.add(new SuperAttack("Neo Dodon Ray", 300));
		superAttacks2.add(new SuperAttack("Nova Star", 350));
		superAttacks2.add(new SuperAttack("Ice Ray", 350));
		superAttacks2.add(new SuperAttack("Kamehameha Cannon", 350));

		ArrayList<UltimateAttack> ultimateAttacks2 = new ArrayList<UltimateAttack>();
		ultimateAttacks2.add(new UltimateAttack("Super Dodon Wave", 425));
		ultimateAttacks2.add(new UltimateAttack("Thunder Shock Surprise", 450));
		ultimateAttacks2.add(new UltimateAttack("Evil Containment Wave", 400));
		ultimateAttacks2.add(new UltimateAttack("Super Kamehameha Cannon", 500));
		Dragon dragon2 = new Dragon("Porunga", superAttacks2, ultimateAttacks2, 5, 7);
		dragons.add(dragon2);

		Game game = new Game();
		ArrayList<Dragon> testDragons = game.getDragons();

		assertEquals(
				"The dragons ArrayList doesn't contain the same number of dragons given in the CSV file",
				dragons.size(), testDragons.size());

		for (int i = 0; i < dragons.size(); i++) {
			assertEquals(
					"A dragon should have the same name as its corresponding input in the CSV file",
					dragons.get(i).getName(), testDragons.get(i).getName());
			assertEquals(
					"The dragon " + dragons.get(i).getName() + " should have the same number of senzuBeans as its corresponding input in the CSV file",
					dragons.get(i).getSenzuBeans(), testDragons.get(i)
					.getSenzuBeans());
			assertEquals(
					"The dragon " + dragons.get(i).getName() + " should have the same number of abilityPoints as its corresponding input in the CSV file",
					dragons.get(i).getAbilityPoints(), testDragons.get(i)
					.getAbilityPoints());


			assertEquals(
					"The loaded super attacks of " + dragons.get(i).getName() + " dragon don't contain the same number of super attacks given in the CSV file",
					dragons.get(i).getSuperAttacks().size(), testDragons.get(i)
					.getSuperAttacks().size());

			for (int j = 0; j < dragons.get(i).getSuperAttacks().size(); j++) {
				assertEquals(
						"The super attack " + dragons.get(i).getSuperAttacks().get(j).getName() + " of " + dragons.get(i).getName() + " dragon should have the same type as its corresponding input in the CSV file",
						dragons.get(i).getSuperAttacks().get(j).getClass(),
						testDragons.get(i).getSuperAttacks().get(j).getClass());

				assertEquals(
						"The super attack of " + dragons.get(i).getName() + " dragon should have the same name as its corresponding input in the CSV file",
						dragons.get(i).getSuperAttacks().get(j).getName(),
						testDragons.get(i).getSuperAttacks().get(j).getName());

				assertEquals(
						"The super attack " + dragons.get(i).getSuperAttacks().get(j).getName() + " of " + dragons.get(i).getName() + " dragon should have the same damage as its corresponding input in the CSV file",
						dragons.get(i).getSuperAttacks().get(j).getDamage(),
						testDragons.get(i).getSuperAttacks().get(j).getDamage());
			}

			assertEquals(
					"The loaded ultimate attacks of " + dragons.get(i).getName() + " dragon don't contain the same number of ultimate attacks given in the CSV file",
					dragons.get(i).getUltimateAttacks().size(), testDragons
					.get(i).getUltimateAttacks().size());

			for (int j = 0; j < dragons.get(i).getUltimateAttacks().size(); j++) {
				assertEquals(
						"The ultimate attack " + dragons.get(i).getUltimateAttacks().get(j).getName() + " of " + dragons.get(i).getName() + " dragon should have the same type as its corresponding input in the CSV file",
						dragons.get(i).getUltimateAttacks().get(j).getClass(),
						testDragons.get(i).getUltimateAttacks().get(j)
						.getClass());

				assertEquals(
						"The ultimate attack of " + dragons.get(i).getName() + " dragon should have the same name as its corresponding input in the CSV file",
						dragons.get(i).getUltimateAttacks().get(j).getName(),
						testDragons.get(i).getUltimateAttacks().get(j)
						.getName());
				assertEquals(
						"The ultimate attack " + dragons.get(i).getUltimateAttacks().get(j).getName() + " of " + dragons.get(i).getName() + " dragon should have the same damage as its corresponding input in the CSV file",
						dragons.get(i).getUltimateAttacks().get(j).getDamage(),
						testDragons.get(i).getUltimateAttacks().get(j)
						.getDamage());
			}

		}
	}
	public static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}


	public static boolean TwoMapsEqual(Cell[][] map1, Cell[][] map2) {
		int different = 0;
		for (int i = 0; i < map1.length; i++) {
			for (int j = 0; j < map1[i].length; j++) {


				if (!map1[i][j].getClass().equals(map2[i][j].getClass()))
					different++;
				else {

					if (map1[i][j].getClass().equals(FoeCell.class)
							&& !((FoeCell) map1[i][j])
							.getFoe()
							.getName()
							.equals(((FoeCell) map2[i][j]).getFoe()
									.getName()))
						different++;


					else if (map1[i][j].getClass()
							.equals(CollectibleCell.class)
							&& ((CollectibleCell) map1[i][j]).getCollectible() != ((CollectibleCell) map2[i][j])
							.getCollectible())
						different++;
				}
			}

		}
		if (different >= 100)
			return false;

		return true;

	}

	public static boolean uniqueLocations(ArrayList<Location> locations) {
		for (int i = 0; i < locations.size(); i++) {
			Location m = locations.get(i);
			int identical = 0;
			for (int j = i + 1; j < locations.size(); j++) {
				if (m.equals(locations.get(j)))
					identical++;
			}
			if (identical >= locations.size() / 2)
				return false;
		}
		return true;
	}
}

class Location {
	int x, y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Location a) {
		if (this.x == a.x && this.y == a.y)
			return true;
		return false;
	}

}
