package org.schema.game.common.data.element;

import class_29;
import class_367;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.vecmath.Vector3f;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.schema.game.common.data.element.deathstar.DeathStarElement;
import org.schema.game.common.data.element.ship.ShipElement;
import org.schema.game.common.data.element.spacestation.SpaceStationElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ElementKeyMap
{
  private static final Map informationKeyMap = new HashMap();
  public static int highestType = 0;
  private static ElementInformation[] infoArray;
  public static final short WEAPON_CONTROLLER_ID = 6;
  public static final short WEAPON_ID = 16;
  public static final short CORE_ID = 1;
  public static final short DEATHSTAR_CORE_ID = 65;
  public static final short HULL_ID = 5;
  public static final short GLASS_ID = 63;
  public static final short THRUSTER_ID = 8;
  public static final short TURRET_DOCK_ID = 7;
  public static final short TURRET_DOCK_ENHANCE_ID = 88;
  public static final short POWER_ID = 2;
  public static final short POWER_CAP_ID = 331;
  public static final short SHIELD_ID = 3;
  public static final short EXPLOSIVE_ID = 14;
  public static final short RADAR_JAMMING_ID = 15;
  public static final short CLOAKING_ID = 22;
  public static final short SALVAGE_ID = 24;
  public static final short MISSILE_DUMB_CONTROLLER_ID = 38;
  public static final short MISSILE_DUMB_ID = 32;
  public static final short MISSILE_HEAT_CONTROLLER_ID = 46;
  public static final short MISSILE_HEAT_ID = 40;
  public static final short MISSILE_FAFO_CONTROLLER_ID = 54;
  public static final short MISSILE_FAFO_ID = 48;
  public static final short SALVAGE_CONTROLLER_ID = 4;
  public static final short GRAVITY_ID = 56;
  public static final short REPAIR_ID = 30;
  public static final short REPAIR_CONTROLLER_ID = 39;
  public static final short COCKPIT_ID = 47;
  public static final short LIGHT_ID = 55;
  public static final short LIGHT_BEACON_ID = 62;
  public static final short TERRAIN_ICE_ID = 64;
  public static final short HULL_COLOR_PURPLE_ID = 69;
  public static final short HULL_COLOR_BROWN_ID = 70;
  public static final short HULL_COLOR_BLACK_ID = 75;
  public static final short HULL_COLOR_RED_ID = 76;
  public static final short HULL_COLOR_BLUE_ID = 77;
  public static final short HULL_COLOR_GREEN_ID = 78;
  public static final short HULL_COLOR_YELLOW_ID = 79;
  public static final short LANDING_ELEMENT = 112;
  public static final short LIFT_ELEMENT = 113;
  public static final short RECYCLER_ELEMENT = 114;
  public static final short STASH_ELEMENT = 120;
  public static final short AI_ELEMENT = 121;
  public static final short DOOR_ELEMENT = 122;
  public static final short BUILD_BLOCK_ID = 123;
  public static final short TERRAIN_LAVA_ID = 80;
  public static final short TERRAIN_GOLD_ID = 128;
  public static final short TERRAIN_IRIDIUM_ID = 129;
  public static final short TERRAIN_MERCURY_ID = 130;
  public static final short TERRAIN_PALLADIUM_ID = 131;
  public static final short TERRAIN_PLATINUM_ID = 132;
  public static final short TERRAIN_LITHIUM_ID = 133;
  public static final short TERRAIN_MAGNESIUM_ID = 134;
  public static final short TERRAIN_TITANIUM_ID = 135;
  public static final short TERRAIN_URANIUM_ID = 136;
  public static final short TERRAIN_POLONIUM_ID = 137;
  public static final short TERRAIN_EXTRANIUM_ID = 72;
  public static final short TERRAIN_INSANIUNM_ID = 210;
  public static final short TERRAIN_METATE_ID = 209;
  public static final short TERRAIN_NEGAGATE_ID = 208;
  public static final short TERRAIN_QUANTACIDE_ID = 207;
  public static final short TERRAIN_NEGACIDE_ID = 206;
  public static final short TERRAIN_MARS_TOP = 138;
  public static final short TERRAIN_MARS_ROCK = 139;
  public static final short TERRAIN_MARS_DIRT = 140;
  public static final short TERRAIN_ROCK_ID = 73;
  public static final short TERRAIN_SAND_ID = 74;
  public static final short TERRAIN_EARTH_TOP_DIRT = 82;
  public static final short TERRAIN_EARTH_TOP_ROCK = 83;
  public static final short TERRAIN_TREE_TRUNK_ID = 84;
  public static final short TERRAIN_TREE_LEAF_ID = 85;
  public static final short TERRAIN_WATER = 86;
  public static final short TERRAIN_DIRT_ID = 87;
  public static final short TERRAIN_VINES_ID = 85;
  public static final short TERRAIN_CACTUS_ID = 89;
  public static final short TERRAIN_PURPLE_ALIEN_TOP = 90;
  public static final short TERRAIN_PURPLE_ALIEN_ROCK = 91;
  public static final short TERRAIN_PURPLE_ALIEN_VINE = 92;
  public static final short WATER = 86;
  public static final short PLAYER_SPAWN_MODULE = 94;
  public static final short TERRAIN_GRASS_SPRITE = 93;
  public static final short TERRAIN_GRASSFLOWERS_SPRITE = 98;
  public static final short TERRAIN_TALLGRASSFLOWERS_SPRITE = 102;
  public static final short TERRAIN_TALLFLOWERS_SPRITE = 106;
  public static final short TERRAIN_BROWNWEED_SPRITE = 95;
  public static final short TERRAIN_MINICACTUS_SPRITE = 103;
  public static final short TERRAIN_LONGWEED_SPRITE = 99;
  public static final short TERRAIN_ROCK_SPRITE = 107;
  public static final short TERRAIN_MARSTENTACLES_SPRITE = 96;
  public static final short TERRAIN_REDSHROOM_SPRITE = 104;
  public static final short TERRAIN_TALLSHROOM_SPRITE = 100;
  public static final short TERRAIN_ALIENFLOWERS_SPRITE = 108;
  public static final short TERRAIN_ALIENVINE_SPRITE = 97;
  public static final short TERRAIN_PURSPIRE_SPRITE = 101;
  public static final short TERRAIN_PURPTACLES_SPRITE = 105;
  public static final short TERRAIN_YHOLE_SPRITE = 109;
  public static final short FACTORY_INPUT_ID = 211;
  public static final short FACTORY_INPUT_ENH_ID = 212;
  public static final short FACTORY_POWER_CELL_ID = 213;
  public static final short FACTORY_POWER_CELL_ENH_ID = 214;
  public static final short FACTORY_POWER_COIL_ID = 215;
  public static final short FACTORY_POWER_COIL_ENH_ID = 216;
  public static final short FACTORY_POWER_BLOCK_ID = 217;
  public static final short FACTORY_POWER_BLOCK_ENH_ID = 218;
  public static final short TERRAIN_ICEPLANET_SURFACE = 274;
  public static final short TERRAIN_ICEPLANET_ROCK = 275;
  public static final short TERRAIN_ICEPLANET_WOOD = 276;
  public static final short TERRAIN_ICEPLANET_LEAVES = 277;
  public static final short TERRAIN_ICEPLANET_SPIKE_SPRITE = 278;
  public static final short TERRAIN_ICEPLANET_ICECRAG_SPRITE = 279;
  public static final short TERRAIN_ICEPLANET_ICECORAL_SPRITE = 280;
  public static final short TERRAIN_ICEPLANET_ICEGRASS_SPRITE = 281;
  public static final short LIGHT_RED = 282;
  public static final short LIGHT_BLUE = 283;
  public static final short LIGHT_GREEN = 284;
  public static final short LIGHT_YELLOW = 285;
  public static final short TERRAIN_ICEPLANET_CRYSTAL = 286;
  public static final short TERRAIN_REDWOOD = 287;
  public static final short TERRAIN_REDWOOD_LEAVES = 288;
  public static final short FIXED_DOCK_ID = 289;
  public static final short FIXED_DOCK_ID_ENHANCER = 290;
  public static final short FACTION_BLOCK = 291;
  public static final short FACTION_HUB_BLOCK = 292;
  public static final short POWER_CELL = 219;
  public static final short POWER_COIL = 220;
  public static final short POWER_DRAIN_BEAM_COMPUTER = 332;
  public static final short POWER_DRAIN_BEAM_MODULE = 333;
  public static final short POWER_SUPPLY_BEAM_COMPUTER = 334;
  public static final short POWER_SUPPLY_BEAM_MODULE = 335;
  public static boolean initialized;
  private static final HashSet factoryKeySet = new HashSet(256);
  public static final HashSet keySet = new HashSet(256);
  private static short[] keyArray;
  private static final HashSet leveldKeySet = new HashSet(256);
  private static final HashMap levelMap = new HashMap();
  private static ElementCategory categoryHirarchy;
  public static Properties properties;
  
  public static void reinitializeData(File paramFile, String paramString)
  {
    initialized = false;
    categoryHirarchy = null;
    factoryKeySet.clear();
    keySet.clear();
    leveldKeySet.clear();
    levelMap.clear();
    highestType = 0;
    informationKeyMap.clear();
    infoArray = null;
    keyArray = null;
    initializeData(paramFile, paramString);
  }
  
  public static void initializeData()
  {
    initializeData(null, null);
  }
  
  public static void addInformationToExisting(ElementInformation paramElementInformation)
  {
    categoryHirarchy.insertRecusrive(paramElementInformation);
    add(paramElementInformation.getId(), paramElementInformation);
    infoArray = new ElementInformation[highestType + 1];
    Iterator localIterator = informationKeyMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      infoArray[((Short)localEntry.getKey()).shortValue()] = ((ElementInformation)localEntry.getValue());
    }
    if (factoryKeySet.contains(Short.valueOf(paramElementInformation.getId())))
    {
      paramElementInformation.getFactory().enhancer = 212;
      paramElementInformation.getControlling().addAll(factoryKeySet);
      paramElementInformation.getControlling().remove(Short.valueOf(paramElementInformation.getId()));
      paramElementInformation.getControlledBy().addAll(factoryKeySet);
    }
  }
  
  public static void removeFromExisting(ElementInformation paramElementInformation)
  {
    keySet.remove(Short.valueOf(paramElementInformation.getId()));
    informationKeyMap.remove(Short.valueOf(paramElementInformation.getId()));
    highestType = 0;
    Object localObject1 = keySet.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      int i = ((Short)((Iterator)localObject1).next()).shortValue();
      highestType = Math.max(highestType, i);
    }
    factoryKeySet.remove(Short.valueOf(paramElementInformation.getId()));
    getLeveldkeyset().remove(Short.valueOf(paramElementInformation.getId()));
    Object localObject2;
    if (paramElementInformation.isLeveled())
    {
      localObject1 = (HashMap)levelMap.get(Short.valueOf(paramElementInformation.getLevel().getIdBase()));
      localObject2 = new HashSet();
      if (localObject1 != null)
      {
        Iterator localIterator = ((HashMap)localObject1).entrySet().iterator();
        Object localObject3;
        while (localIterator.hasNext()) {
          if (((Short)(localObject3 = (Map.Entry)localIterator.next()).getValue()).shortValue() == paramElementInformation.getId()) {
            ((Set)localObject2).add(((Map.Entry)localObject3).getKey());
          }
        }
        localIterator = ((Set)localObject2).iterator();
        while (localIterator.hasNext())
        {
          localObject3 = (Integer)localIterator.next();
          ((HashMap)localObject1).remove(localObject3);
        }
      }
    }
    levelMap.remove(Short.valueOf(paramElementInformation.getId()));
    infoArray = new ElementInformation[highestType + 1];
    localObject1 = informationKeyMap.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)localObject1).next();
      infoArray[((Short)localObject2.getKey()).shortValue()] = ((ElementInformation)((Map.Entry)localObject2).getValue());
    }
    categoryHirarchy.removeRecursive(paramElementInformation);
  }
  
  private static void initElements(ArrayList paramArrayList, ElementCategory paramElementCategory)
  {
    paramArrayList = paramArrayList.iterator();
    Object localObject;
    while (paramArrayList.hasNext()) {
      add((localObject = (ElementInformation)paramArrayList.next()).getId(), (ElementInformation)localObject);
    }
    categoryHirarchy = paramElementCategory;
    infoArray = new ElementInformation[highestType + 1];
    paramArrayList = informationKeyMap.entrySet().iterator();
    while (paramArrayList.hasNext())
    {
      localObject = (Map.Entry)paramArrayList.next();
      infoArray[((Short)localObject.getKey()).shortValue()] = ((ElementInformation)((Map.Entry)localObject).getValue());
    }
    paramArrayList = factoryKeySet.iterator();
    while (paramArrayList.hasNext())
    {
      short s;
      getInfo(s = ((Short)paramArrayList.next()).shortValue()).getFactory().enhancer = (getInfo(s).getControlling().size() > 0 ? ((Short)getInfo(s).getControlling().iterator().next()).shortValue() : 212);
      getInfo(s).getControlling().addAll(factoryKeySet);
      getInfo(s).getControlling().remove(Short.valueOf(s));
      getInfo(s).getControlledBy().addAll(factoryKeySet);
    }
    initialized = true;
  }
  
  public static void initializeData(File paramFile, String paramString)
  {
    for (;;)
    {
      if (initialized) {
        return;
      }
      try
      {
        initElements((paramFile = load(paramFile, paramString)).getInfoElements(), paramFile.getRootCategory());
      }
      catch (Exception localException)
      {
        class_29.a12(localException);
        paramString = null;
        paramFile = null;
      }
    }
    keyArray = new short[keySet.size()];
    paramFile = 0;
    paramString = keySet.iterator();
    while (paramString.hasNext())
    {
      int i = ((Short)paramString.next()).shortValue();
      keyArray[paramFile] = i;
      paramFile++;
    }
  }
  
  private static void add(short paramShort, ElementInformation paramElementInformation)
  {
    if (keySet.contains(Short.valueOf(paramShort))) {
      throw new ParserConfigurationException("Duplicate Block ID " + paramShort + " (" + paramElementInformation.getName() + " and " + ((ElementInformation)informationKeyMap.get(Short.valueOf(paramShort))).getName() + ")");
    }
    keySet.add(Short.valueOf(paramShort));
    informationKeyMap.put(Short.valueOf(paramShort), paramElementInformation);
    highestType = Math.max(highestType, paramShort);
    if (paramElementInformation.getFactory() != null) {
      factoryKeySet.add(Short.valueOf(paramShort));
    }
    if (paramElementInformation.isLeveled())
    {
      getLeveldkeyset().add(Short.valueOf(paramShort));
      HashMap localHashMap;
      if ((localHashMap = (HashMap)levelMap.get(Short.valueOf(paramElementInformation.getLevel().getIdBase()))) == null)
      {
        localHashMap = new HashMap();
        levelMap.put(Short.valueOf(paramElementInformation.getLevel().getIdBase()), localHashMap);
      }
      localHashMap.put(Integer.valueOf(paramElementInformation.getLevel().getLevel()), Short.valueOf(paramShort));
    }
  }
  
  public static short getLevel(short paramShort, int paramInt)
  {
    if (((HashMap)levelMap.get(Short.valueOf(paramShort))).get(Integer.valueOf(paramInt)) == null) {
      return -1;
    }
    return ((Short)((HashMap)levelMap.get(Short.valueOf(paramShort))).get(Integer.valueOf(paramInt))).shortValue();
  }
  
  public static void addShipHull(short paramShort1, int paramInt, String paramString, short paramShort2)
  {
    (paramString = new ElementInformation(paramShort1, "Hull " + paramString, GeneralElement.class, paramShort2)).setPrice(500L);
    paramString.setAmour(50);
    paramString.setBuildIconNum(paramInt);
    paramString.setShoppable(true);
    if (paramShort1 == 63) {
      paramString.setBlended(true);
    }
    paramString.setDescription(formatDescString("The Hull Element\n\nA cheap amoured Element to give the ship structure.\nIt should be used to protect more important elements.\n\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
    add(paramShort1, paramString);
  }
  
  public static String formatDescString(String paramString)
  {
    paramString = new StringBuffer(paramString);
    int i = 0;
    for (int j = 0; j < paramString.length() - 1; j++)
    {
      if (paramString.charAt(j) == '\n')
      {
        i = 0;
        j++;
      }
      if (i > 50)
      {
        while ((j > 0) && (paramString.charAt(j) != ' ')) {
          j--;
        }
        paramString.deleteCharAt(j);
        paramString.insert(j, "\n");
        j++;
        i = 0;
      }
      i++;
    }
    return paramString.toString();
  }
  
  public static ElementCategory getCategoryHirarchy()
  {
    return categoryHirarchy;
  }
  
  public static ElementInformation getInfo(short paramShort)
  {
    assert (infoArray[paramShort] != null) : ("type " + paramShort + " unknown, please check the properties and the xml");
    ElementInformation localElementInformation;
    if ((localElementInformation = infoArray[paramShort]) == null) {
      throw new NullPointerException("Exception: REQUESTED TYPE " + paramShort + " IS NULL");
    }
    return localElementInformation;
  }
  
  public static boolean exists(short paramShort)
  {
    return (paramShort > 0) && (paramShort < infoArray.length) && (infoArray[paramShort] != null);
  }
  
  public static void initializeDeathStarData()
  {
    ElementInformation localElementInformation;
    (localElementInformation = new ElementInformation((short)65, "Deathstar Core", DeathStarElement.class, (short)65)).setAmour(30);
    localElementInformation.setMaxHitPoints((short)1000);
    add((short)65, localElementInformation);
  }
  
  public static void initializeShipData()
  {
    ElementInformation localElementInformation;
    (localElementInformation = new ElementInformation((short)6, "Weapon Controller", ShipElement.class, (short)6)).setPrice(300L);
    localElementInformation.setAmour(5);
    localElementInformation.getControlling().add(Short.valueOf((short)16));
    localElementInformation.setBuildIconNum(10);
    localElementInformation.setShoppable(true);
    localElementInformation.setEnterable(true);
    localElementInformation.setDescription(formatDescString("The Weapon Controller Element\n\nThis element is used to control weapon\n elements of a Ship.\n\nWeapons have to be connected to the\nweapon controller in order for them to work.\nWeapon controllers can be entered by\nindividuals to crew the ship\n\n\nCOST:          300\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      WeaponElement\nCONTROLLED BY: Core"));
    add((short)6, localElementInformation);
    (localElementInformation = new ElementInformation((short)1, "Core", ShipElement.class, (short)1)).setPrice(10000L);
    localElementInformation.setBuildIconNum(1);
    localElementInformation.setAmour(20);
    localElementInformation.setMaxHitPoints((short)500);
    localElementInformation.setShoppable(true);
    localElementInformation.setEnterable(true);
    localElementInformation.getControlling().add(Short.valueOf((short)16));
    localElementInformation.getControlling().add(Short.valueOf((short)8));
    localElementInformation.getControlling().add(Short.valueOf((short)22));
    localElementInformation.getControlling().add(Short.valueOf((short)15));
    localElementInformation.setDescription(formatDescString("The Core Element\n\nThis element is the most important element of a ship\nIt can only be placed, when spawning a new ship,\nand cannot be destroyed or removed!\n\nIf a core element is damaged enough, the individual\ninside the element will be catapulted outside of the ship\nCore controllers can be entered by\nindividuals to crew the ship\n\n\nCOST:          10000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      WeaponController, HarvestController, Thrusters\nCONTROLLED BY: -"));
    add((short)1, localElementInformation);
    addShipHull((short)5, 5, "grey", (short)5);
    addShipHull((short)75, 33, "black", (short)75);
    addShipHull((short)76, 34, "red", (short)76);
    addShipHull((short)77, 35, "blue", (short)77);
    addShipHull((short)79, 36, "yellow", (short)79);
    addShipHull((short)78, 37, "green", (short)78);
    addShipHull((short)70, 39, "brown", (short)70);
    addShipHull((short)69, 38, "purple", (short)69);
    addShipHull((short)63, 40, "glass", (short)63);
    (localElementInformation = new ElementInformation((short)47, "Cockpit", ShipElement.class, (short)47)).setPrice(500L);
    localElementInformation.setAmour(10);
    localElementInformation.setBuildIconNum(30);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Cockpit Element\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
    add((short)47, localElementInformation);
    (localElementInformation = new ElementInformation((short)55, "Light", ShipElement.class, (short)55)).setPrice(500L);
    localElementInformation.setBuildIconNum(31);
    localElementInformation.setLightSource(true);
    localElementInformation.setShoppable(true);
    localElementInformation.getLightSourceColor().set(1.0F, 1.0F, 0.23F);
    localElementInformation.setDescription(formatDescString("The Light Element\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
    add((short)55, localElementInformation);
    (localElementInformation = new ElementInformation((short)62, "LightBeacon", ShipElement.class, (short)62)).setPrice(1000L);
    localElementInformation.setBuildIconNum(32);
    localElementInformation.setShoppable(true);
    localElementInformation.setLightSource(true);
    localElementInformation.setBlended(true);
    localElementInformation.setDescription(formatDescString("The LightBeacon Element\n\nCOST:          1000\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
    add((short)62, localElementInformation);
    (localElementInformation = new ElementInformation((short)8, "Thruster", ShipElement.class, (short)8)).setPrice(300L);
    localElementInformation.setBuildIconNum(13);
    localElementInformation.setIndividualSides(6);
    localElementInformation.setShoppable(true);
    localElementInformation.setOrientatable(true);
    localElementInformation.setDescription(formatDescString("The Thruster Element\n\nThe basic thruster elements to get ships moving.\nThe more elements are present in a ship, \nthe faster the ship will go relative to its mass.\n\n\nCOST:          300\nRANGE:         -\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Core"));
    add((short)8, localElementInformation);
    (localElementInformation = new ElementInformation((short)3, "Shield Generator", ShipElement.class, (short)3)).setPrice(3000L);
    localElementInformation.setBuildIconNum(8);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Shield Generator Element\n\nThis element provides a forcefield against Energy Weapons.\nOnce, its not been hit for 60 seconds, it will automatically recharge. \nThe basic radius of the forcefield is fairly small,\nbut this is a groupable Element: the more elements are\ntouching each other, the bigger and stronger the forcefield will become.\n\n\nCOST:          3000\nRANGE:         -\nHP:         \t0\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: -"));
    add((short)3, localElementInformation);
    (localElementInformation = new ElementInformation((short)16, "Weapon", ShipElement.class, (short)16)).setPrice(500L);
    localElementInformation.setIndividualSides(6);
    localElementInformation.setOrientatable(true);
    localElementInformation.setBuildIconNum(9);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Weapon Element\n\nThis is a basic weapon Element.\nIt does 1 unit of damage. \nThis is a groupable Element: \nyou can add elements to build a larger more efficient weapon \nMore WIDTH means MORE DAMAGE.\nMore HEIGHT means LESS RELOAD TIME.\nMore DEPTH means MORE SPEED.\n\n\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: WeaponController"));
    add((short)16, localElementInformation);
    (localElementInformation = new ElementInformation((short)2, "Battery", ShipElement.class, (short)2)).setPrice(300L);
    localElementInformation.setBuildIconNum(11);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Power Generator Element\n\nThis Element provides power for all Elements,\nthat are in range. \n\n\n\nCOST:          300\nRANGE:         5\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -\n"));
    add((short)2, localElementInformation);
    (localElementInformation = new ElementInformation((short)14, "Explosive Module", ShipElement.class, (short)14)).setPrice(500L);
    localElementInformation.setBuildIconNum(23);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Explosive Element\n\nThis is a very dangerous Element\nOnce you connect it to the core,\nit will detonate in a huge balst\non impact."));
    add((short)14, localElementInformation);
    (localElementInformation = new ElementInformation((short)22, "Cloaking Module", ShipElement.class, (short)22)).setPrice(1000L);
    localElementInformation.setBuildIconNum(22);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Cloaking Module\n\nThis Module enables you be invisibe \nfrom other players for a short\ntime.\n\nGrouping these modules will increase cloking time"));
    add((short)22, localElementInformation);
    (localElementInformation = new ElementInformation((short)15, "Radar Jamming Module", ShipElement.class, (short)15)).setPrice(300L);
    localElementInformation.setBuildIconNum(24);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Radar Jamming Element\n\nThis Module enables you to vanish\nfrom other players radar for a short\ntime.\n\nGrouping these modules will increase jamming time"));
    add((short)15, localElementInformation);
    (localElementInformation = new ElementInformation((short)24, "Salvage Beam", ShipElement.class, (short)24)).setPrice(700L);
    localElementInformation.setBuildIconNum(12);
    localElementInformation.setIndividualSides(6);
    localElementInformation.setOrientatable(true);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Salvager Beam Element\n\nThis Element can be used like a weapon.\nWith this module, you can collect elements\nfrom neutral structures. Grouping this elements\nwill decrease the salvage time per element\n\n\n\nCOST:          700\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Salvager Controller\n"));
    add((short)24, localElementInformation);
    (localElementInformation = new ElementInformation((short)4, "Salvage Controller", ShipElement.class, (short)4)).setPrice(1000L);
    localElementInformation.setBuildIconNum(14);
    localElementInformation.setShoppable(true);
    localElementInformation.setEnterable(true);
    localElementInformation.getControlling().add(Short.valueOf((short)24));
    localElementInformation.setDescription(formatDescString("The Salvager Beam Controller Element\n\nThis element is use like the weapon controller,\nwith the difference that it controls salvage elements\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Salvager\nCONTROLLED BY: -\n"));
    add((short)4, localElementInformation);
    (localElementInformation = new ElementInformation((short)30, "Repair Beam", ShipElement.class, (short)30)).setPrice(700L);
    localElementInformation.setBuildIconNum(26);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Repair Beam Element\n\nThis Element can be used like a weapon.\nWith this module, you can repair elements\nfrom other structures. Grouping this elements\nwill decrease the repair time per element\n\n\n\nCOST:          700\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Repair Controller\n"));
    add((short)30, localElementInformation);
    (localElementInformation = new ElementInformation((short)39, "Repair Controller", ShipElement.class, (short)39)).setPrice(1000L);
    localElementInformation.setBuildIconNum(27);
    localElementInformation.setShoppable(true);
    localElementInformation.setEnterable(true);
    localElementInformation.getControlling().add(Short.valueOf((short)30));
    localElementInformation.setDescription(formatDescString("The repair Beam Controller Module\n\nThis element is use like the weapon controller,\nwith the difference that it controls repair elements\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      repair\nCONTROLLED BY: -\n"));
    add((short)39, localElementInformation);
    (localElementInformation = new ElementInformation((short)7, "Docking Station", ShipElement.class, (short)7)).setPrice(500L);
    localElementInformation.setBuildIconNum(15);
    localElementInformation.setShoppable(true);
    localElementInformation.setDescription(formatDescString("The Dock Element\n\nYou can dock other ships here,\n\n\n\nCOST:          500\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -\n"));
    add((short)7, localElementInformation);
    (localElementInformation = new ElementInformation((short)38, "Dumb Missile Controller", ShipElement.class, (short)38)).setPrice(1000L);
    localElementInformation.setBuildIconNum(16);
    localElementInformation.setShoppable(true);
    localElementInformation.getControlling().add(Short.valueOf((short)32));
    localElementInformation.setEnterable(true);
    localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
    add((short)38, localElementInformation);
    (localElementInformation = new ElementInformation((short)32, "Dumb Missile Module", ShipElement.class, (short)32)).setPrice(800L);
    localElementInformation.setBuildIconNum(17);
    localElementInformation.setShoppable(true);
    localElementInformation.setIndividualSides(6);
    localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          800\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
    add((short)32, localElementInformation);
    (localElementInformation = new ElementInformation((short)46, "Heat Seeking Missile Controller", ShipElement.class, (short)46)).setPrice(1200L);
    localElementInformation.setBuildIconNum(18);
    localElementInformation.setShoppable(true);
    localElementInformation.setEnterable(true);
    localElementInformation.getControlling().add(Short.valueOf((short)40));
    localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1200\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
    add((short)46, localElementInformation);
    (localElementInformation = new ElementInformation((short)40, "Heat Seeking Missile", ShipElement.class, (short)40)).setPrice(1000L);
    localElementInformation.setBuildIconNum(19);
    localElementInformation.setShoppable(true);
    localElementInformation.setIndividualSides(6);
    localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
    add((short)40, localElementInformation);
    (localElementInformation = new ElementInformation((short)54, "Fire and forget Missile Controller", ShipElement.class, (short)54)).setPrice(1500L);
    localElementInformation.setBuildIconNum(20);
    localElementInformation.setShoppable(true);
    localElementInformation.setEnterable(true);
    localElementInformation.getControlling().add(Short.valueOf((short)48));
    localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1500\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
    add((short)54, localElementInformation);
    (localElementInformation = new ElementInformation((short)48, "Fire and forget Missile", ShipElement.class, (short)48)).setPrice(1300L);
    localElementInformation.setBuildIconNum(21);
    localElementInformation.setShoppable(true);
    localElementInformation.setIndividualSides(6);
    localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          1300\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
    add((short)48, localElementInformation);
  }
  
  public static void initializeSpaceStationData()
  {
    ElementInformation localElementInformation;
    (localElementInformation = new ElementInformation((short)112, "Landing Module", SpaceStationElement.class, (short)112)).setBuildIconNum(41);
    localElementInformation.setShoppable(true);
    localElementInformation.setPrice(10L);
    localElementInformation.setDescription(formatDescString("The Landing Module\n\naffixes any ship to this module.make sure there is enough space!\n \n\nPress " + class_367.field_733.b1() + " to activate\n\n\n"));
    add((short)112, localElementInformation);
    (localElementInformation = new ElementInformation((short)113, "Lift Module", SpaceStationElement.class, (short)113)).setBuildIconNum(42);
    localElementInformation.setShoppable(true);
    localElementInformation.setPrice(10L);
    localElementInformation.setDescription(formatDescString("The Lift Module\n\nit will lift up any objects\n from bottom to the top of any group\nof lift objects.\n\nPress " + class_367.field_733.b1() + " to activate\n\n\n"));
    add((short)113, localElementInformation);
    (localElementInformation = new ElementInformation((short)122, "Door Module", SpaceStationElement.class, (short)122)).setBuildIconNum(43);
    localElementInformation.setShoppable(true);
    localElementInformation.setPrice(10L);
    localElementInformation.setDescription(formatDescString("The door module\n\nThis module will only work\n if placed on top of an opening\nuse a line of modules to \nmake the door wider.\n\nPress " + class_367.field_733.b1() + " to activate\n\n\n"));
    add((short)122, localElementInformation);
    (localElementInformation = new ElementInformation((short)120, "Chest Module", SpaceStationElement.class, (short)120)).setBuildIconNum(44);
    localElementInformation.setShoppable(true);
    localElementInformation.setPrice(10L);
    localElementInformation.setDescription(formatDescString("The Recycler Module\n\nPlace modules inside\n to keep them save.\n\nPress " + class_367.field_733.b1() + " to activate\n\n\n"));
    add((short)120, localElementInformation);
    (localElementInformation = new ElementInformation((short)114, "Recycler Module", SpaceStationElement.class, (short)114)).setBuildIconNum(45);
    localElementInformation.setShoppable(true);
    localElementInformation.setPrice(10L);
    localElementInformation.setDescription(formatDescString("The Recycler Module\n\nplace modules inside\n to convert them to a better module.\n\nPress " + class_367.field_733.b1() + " to activate\n\n\n"));
    add((short)114, localElementInformation);
    (localElementInformation = new ElementInformation((short)121, "AI Module", ShipElement.class, (short)121)).setBuildIconNum(46);
    localElementInformation.setShoppable(true);
    localElementInformation.setPrice(10L);
    localElementInformation.setDescription(formatDescString("The AI Module\n\nlets you control any ship\n if it has been placed on there.\n\nPress " + class_367.field_733.b1() + " to activate\n\n\n"));
    add((short)121, localElementInformation);
    (localElementInformation = new ElementInformation((short)123, "Build Block", SpaceStationElement.class, (short)123)).setBuildIconNum(47);
    localElementInformation.setShoppable(true);
    localElementInformation.setPrice(10L);
    localElementInformation.setEnterable(true);
    localElementInformation.setDescription(formatDescString("The Build Module\n\nthis module is used to edit \n space stations from the inside.\n\nPress " + class_367.field_733.b1() + " to activate\n\n\n"));
    add((short)123, localElementInformation);
  }
  
  public static void initializeTerrainData()
  {
    ElementInformation localElementInformation;
    (localElementInformation = new ElementInformation((short)64, "Ice Element", GeneralElement.class, (short)64)).setBuildIconNum(66);
    add((short)64, localElementInformation);
    (localElementInformation = new ElementInformation((short)80, "Lava Element", GeneralElement.class, (short)80)).setLightSource(true);
    localElementInformation.setAnimated(true);
    localElementInformation.getLightSourceColor().set(1.0F, 0.2F, 0.2F);
    localElementInformation.setBuildIconNum(68);
    add((short)80, localElementInformation);
    (localElementInformation = new ElementInformation((short)72, "Mineral Element", GeneralElement.class, (short)72)).setBuildIconNum(69);
    add((short)72, localElementInformation);
    (localElementInformation = new ElementInformation((short)73, "Rock Element", GeneralElement.class, (short)73)).setBuildIconNum(65);
    add((short)73, localElementInformation);
    (localElementInformation = new ElementInformation((short)74, "Sand Element", GeneralElement.class, (short)74)).setBuildIconNum(71);
    add((short)74, localElementInformation);
    (localElementInformation = new ElementInformation((short)128, "Gold", GeneralElement.class, (short)128)).setBuildIconNum(72);
    add((short)128, localElementInformation);
    (localElementInformation = new ElementInformation((short)129, "Iridium", GeneralElement.class, (short)129)).setBuildIconNum(73);
    add((short)129, localElementInformation);
    (localElementInformation = new ElementInformation((short)130, "Mercury", GeneralElement.class, (short)130)).setBuildIconNum(74);
    add((short)130, localElementInformation);
    (localElementInformation = new ElementInformation((short)131, "Palladium", GeneralElement.class, (short)131)).setBuildIconNum(75);
    add((short)131, localElementInformation);
    (localElementInformation = new ElementInformation((short)132, "Platinum", GeneralElement.class, (short)132)).setBuildIconNum(76);
    add((short)132, localElementInformation);
    (localElementInformation = new ElementInformation((short)133, "Lithium", GeneralElement.class, (short)133)).setBuildIconNum(77);
    add((short)133, localElementInformation);
    (localElementInformation = new ElementInformation((short)134, "Magnesium", GeneralElement.class, (short)134)).setBuildIconNum(78);
    add((short)134, localElementInformation);
    (localElementInformation = new ElementInformation((short)135, "Titanium", GeneralElement.class, (short)135)).setBuildIconNum(79);
    add((short)135, localElementInformation);
    (localElementInformation = new ElementInformation((short)136, "Uranium", GeneralElement.class, (short)136)).setBuildIconNum(80);
    add((short)136, localElementInformation);
    (localElementInformation = new ElementInformation((short)137, "Polonium", GeneralElement.class, (short)137)).setBuildIconNum(81);
    add((short)137, localElementInformation);
    (localElementInformation = new ElementInformation((short)138, "Red Top", GeneralElement.class, (short)138)).setBuildIconNum(82);
    add((short)138, localElementInformation);
    (localElementInformation = new ElementInformation((short)139, "Red Rock", GeneralElement.class, (short)139)).setBuildIconNum(83);
    add((short)139, localElementInformation);
    (localElementInformation = new ElementInformation((short)140, "Red Dirt", GeneralElement.class, (short)140)).setBuildIconNum(84);
    add((short)140, localElementInformation);
  }
  
  public static boolean isValidType(short paramShort)
  {
    return (paramShort >= 0) && (paramShort < infoArray.length) && (infoArray[paramShort] != null);
  }
  
  public static String list()
  {
    return keySet.toString();
  }
  
  private static ElementParser load(File paramFile, String paramString)
  {
    ElementParser localElementParser;
    if (paramFile == null)
    {
      (localElementParser = new ElementParser()).loadAndParseDefault();
      return localElementParser;
    }
    (localElementParser = new ElementParser()).loadAndParseCustomXML(paramFile, paramString);
    return localElementParser;
  }
  
  public static short[] typeList()
  {
    return keyArray;
  }
  
  public Class getTypeFromString(String paramString)
  {
    if (paramString.equals("terrain")) {
      return GeneralElement.class;
    }
    if (paramString.equals("ship")) {
      return ShipElement.class;
    }
    if (paramString.equals("spacestation")) {
      return SpaceStationElement.class;
    }
    if (paramString.equals("deathstar")) {
      return SpaceStationElement.class;
    }
    throw new ElementTypeNotFoundException(paramString);
  }
  
  public static HashSet getLeveldkeyset()
  {
    return leveldKeySet;
  }
  
  public static HashSet getFactorykeyset()
  {
    return factoryKeySet;
  }
  
  public static File writeDocument(String paramString)
  {
    return writeDocument(new File(paramString));
  }
  
  public static File writeDocument(File paramFile)
  {
    try
    {
      localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      Object localObject2 = categoryHirarchy;
      Element localElement = ((Document)localObject1).createElement(ElementParser.getStringFromType(((ElementCategory)localObject2).getCategory()));
      Object localObject3 = ((Document)localObject1).createComment("autocreated by the starmade block editor");
      localElement.appendChild((Node)localObject3);
      localObject2 = ((ElementCategory)localObject2).getChildren().iterator();
      while (((Iterator)localObject2).hasNext()) {
        writeCatToXML((ElementCategory)((Iterator)localObject2).next(), localElement, (Document)localObject1);
      }
      ((Document)localObject1).appendChild(localElement);
      ((Document)localObject1).setXmlVersion("1.0");
      (localObject3 = TransformerFactory.newInstance().newTransformer()).setOutputProperty("omit-xml-declaration", "yes");
      ((Transformer)localObject3).setOutputProperty("indent", "yes");
      new StringWriter();
      localObject2 = new StreamResult(paramFile);
      localObject1 = new DOMSource((Node)localObject1);
      ((Transformer)localObject3).transform((Source)localObject1, (Result)localObject2);
      return paramFile;
    }
    catch (Exception localException)
    {
      Object localObject1;
      (localObject1 = localException).printStackTrace();
      class_29.a12((Exception)localObject1);
    }
    return null;
  }
  
  private static void writeCatToXML(ElementCategory paramElementCategory, Element paramElement, Document paramDocument)
  {
    Element localElement = paramDocument.createElement(ElementParser.getStringFromType(paramElementCategory.getCategory()));
    Iterator localIterator = paramElementCategory.getChildren().iterator();
    while (localIterator.hasNext()) {
      writeCatToXML((ElementCategory)localIterator.next(), localElement, paramDocument);
    }
    localIterator = paramElementCategory.getInfoElements().iterator();
    while (localIterator.hasNext()) {
      ((ElementInformation)localIterator.next()).appendXML(paramDocument, localElement);
    }
    paramElement.appendChild(localElement);
  }
  
  public static void clear()
  {
    informationKeyMap.clear();
    infoArray = null;
    highestType = 0;
    factoryKeySet.clear();
    keySet.clear();
    leveldKeySet.clear();
    levelMap.clear();
    categoryHirarchy.clear();
  }
  
  public static void reparseProperties()
  {
    properties = new Properties();
    FileInputStream localFileInputStream = new FileInputStream("./data/config/BlockTypes.properties");
    properties.load(localFileInputStream);
  }
  
  public static void reparseProperties(String paramString)
  {
    properties = new Properties();
    paramString = new FileInputStream(paramString);
    properties.load(paramString);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ElementKeyMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */