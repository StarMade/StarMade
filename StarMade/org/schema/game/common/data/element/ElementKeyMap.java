/*      */ package org.schema.game.common.data.element;
/*      */ 
/*      */ import cv;
/*      */ import d;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.StringWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import javax.vecmath.Vector3f;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.transform.Result;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerFactory;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import org.schema.game.common.data.element.deathstar.DeathStarElement;
/*      */ import org.schema.game.common.data.element.ship.ShipElement;
/*      */ import org.schema.game.common.data.element.spacestation.SpaceStationElement;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ 
/*      */ public class ElementKeyMap
/*      */ {
/*   43 */   private static final Map informationKeyMap = new HashMap();
/*      */ 
/*   45 */   public static int highestType = 0;
/*      */   private static ElementInformation[] infoArray;
/*      */   public static final short WEAPON_CONTROLLER_ID = 6;
/*      */   public static final short WEAPON_ID = 16;
/*      */   public static final short CORE_ID = 1;
/*      */   public static final short DEATHSTAR_CORE_ID = 65;
/*      */   public static final short HULL_ID = 5;
/*      */   public static final short GLASS_ID = 63;
/*      */   public static final short THRUSTER_ID = 8;
/*      */   public static final short TURRET_DOCK_ID = 7;
/*      */   public static final short TURRET_DOCK_ENHANCE_ID = 88;
/*      */   public static final short POWER_ID = 2;
/*      */   public static final short POWER_CAP_ID = 331;
/*      */   public static final short SHIELD_ID = 3;
/*      */   public static final short EXPLOSIVE_ID = 14;
/*      */   public static final short RADAR_JAMMING_ID = 15;
/*      */   public static final short CLOAKING_ID = 22;
/*      */   public static final short SALVAGE_ID = 24;
/*      */   public static final short MISSILE_DUMB_CONTROLLER_ID = 38;
/*      */   public static final short MISSILE_DUMB_ID = 32;
/*      */   public static final short MISSILE_HEAT_CONTROLLER_ID = 46;
/*      */   public static final short MISSILE_HEAT_ID = 40;
/*      */   public static final short MISSILE_FAFO_CONTROLLER_ID = 54;
/*      */   public static final short MISSILE_FAFO_ID = 48;
/*      */   public static final short SALVAGE_CONTROLLER_ID = 4;
/*      */   public static final short GRAVITY_ID = 56;
/*      */   public static final short REPAIR_ID = 30;
/*      */   public static final short REPAIR_CONTROLLER_ID = 39;
/*      */   public static final short COCKPIT_ID = 47;
/*      */   public static final short LIGHT_ID = 55;
/*      */   public static final short LIGHT_BEACON_ID = 62;
/*      */   public static final short TERRAIN_ICE_ID = 64;
/*      */   public static final short HULL_COLOR_PURPLE_ID = 69;
/*      */   public static final short HULL_COLOR_BROWN_ID = 70;
/*      */   public static final short HULL_COLOR_BLACK_ID = 75;
/*      */   public static final short HULL_COLOR_RED_ID = 76;
/*      */   public static final short HULL_COLOR_BLUE_ID = 77;
/*      */   public static final short HULL_COLOR_GREEN_ID = 78;
/*      */   public static final short HULL_COLOR_YELLOW_ID = 79;
/*      */   public static final short LANDING_ELEMENT = 112;
/*      */   public static final short LIFT_ELEMENT = 113;
/*      */   public static final short RECYCLER_ELEMENT = 114;
/*      */   public static final short STASH_ELEMENT = 120;
/*      */   public static final short AI_ELEMENT = 121;
/*      */   public static final short DOOR_ELEMENT = 122;
/*      */   public static final short BUILD_BLOCK_ID = 123;
/*      */   public static final short TERRAIN_LAVA_ID = 80;
/*      */   public static final short TERRAIN_GOLD_ID = 128;
/*      */   public static final short TERRAIN_IRIDIUM_ID = 129;
/*      */   public static final short TERRAIN_MERCURY_ID = 130;
/*      */   public static final short TERRAIN_PALLADIUM_ID = 131;
/*      */   public static final short TERRAIN_PLATINUM_ID = 132;
/*      */   public static final short TERRAIN_LITHIUM_ID = 133;
/*      */   public static final short TERRAIN_MAGNESIUM_ID = 134;
/*      */   public static final short TERRAIN_TITANIUM_ID = 135;
/*      */   public static final short TERRAIN_URANIUM_ID = 136;
/*      */   public static final short TERRAIN_POLONIUM_ID = 137;
/*      */   public static final short TERRAIN_EXTRANIUM_ID = 72;
/*      */   public static final short TERRAIN_INSANIUNM_ID = 210;
/*      */   public static final short TERRAIN_METATE_ID = 209;
/*      */   public static final short TERRAIN_NEGAGATE_ID = 208;
/*      */   public static final short TERRAIN_QUANTACIDE_ID = 207;
/*      */   public static final short TERRAIN_NEGACIDE_ID = 206;
/*      */   public static final short TERRAIN_MARS_TOP = 138;
/*      */   public static final short TERRAIN_MARS_ROCK = 139;
/*      */   public static final short TERRAIN_MARS_DIRT = 140;
/*      */   public static final short TERRAIN_ROCK_ID = 73;
/*      */   public static final short TERRAIN_SAND_ID = 74;
/*      */   public static final short TERRAIN_EARTH_TOP_DIRT = 82;
/*      */   public static final short TERRAIN_EARTH_TOP_ROCK = 83;
/*      */   public static final short TERRAIN_TREE_TRUNK_ID = 84;
/*      */   public static final short TERRAIN_TREE_LEAF_ID = 85;
/*      */   public static final short TERRAIN_WATER = 86;
/*      */   public static final short TERRAIN_DIRT_ID = 87;
/*      */   public static final short TERRAIN_VINES_ID = 85;
/*      */   public static final short TERRAIN_CACTUS_ID = 89;
/*      */   public static final short TERRAIN_PURPLE_ALIEN_TOP = 90;
/*      */   public static final short TERRAIN_PURPLE_ALIEN_ROCK = 91;
/*      */   public static final short TERRAIN_PURPLE_ALIEN_VINE = 92;
/*      */   public static final short WATER = 86;
/*      */   public static final short PLAYER_SPAWN_MODULE = 94;
/*      */   public static final short TERRAIN_GRASS_SPRITE = 93;
/*      */   public static final short TERRAIN_GRASSFLOWERS_SPRITE = 98;
/*      */   public static final short TERRAIN_TALLGRASSFLOWERS_SPRITE = 102;
/*      */   public static final short TERRAIN_TALLFLOWERS_SPRITE = 106;
/*      */   public static final short TERRAIN_BROWNWEED_SPRITE = 95;
/*      */   public static final short TERRAIN_MINICACTUS_SPRITE = 103;
/*      */   public static final short TERRAIN_LONGWEED_SPRITE = 99;
/*      */   public static final short TERRAIN_ROCK_SPRITE = 107;
/*      */   public static final short TERRAIN_MARSTENTACLES_SPRITE = 96;
/*      */   public static final short TERRAIN_REDSHROOM_SPRITE = 104;
/*      */   public static final short TERRAIN_TALLSHROOM_SPRITE = 100;
/*      */   public static final short TERRAIN_ALIENFLOWERS_SPRITE = 108;
/*      */   public static final short TERRAIN_ALIENVINE_SPRITE = 97;
/*      */   public static final short TERRAIN_PURSPIRE_SPRITE = 101;
/*      */   public static final short TERRAIN_PURPTACLES_SPRITE = 105;
/*      */   public static final short TERRAIN_YHOLE_SPRITE = 109;
/*      */   public static final short FACTORY_INPUT_ID = 211;
/*      */   public static final short FACTORY_INPUT_ENH_ID = 212;
/*      */   public static final short FACTORY_POWER_CELL_ID = 213;
/*      */   public static final short FACTORY_POWER_CELL_ENH_ID = 214;
/*      */   public static final short FACTORY_POWER_COIL_ID = 215;
/*      */   public static final short FACTORY_POWER_COIL_ENH_ID = 216;
/*      */   public static final short FACTORY_POWER_BLOCK_ID = 217;
/*      */   public static final short FACTORY_POWER_BLOCK_ENH_ID = 218;
/*      */   public static final short TERRAIN_ICEPLANET_SURFACE = 274;
/*      */   public static final short TERRAIN_ICEPLANET_ROCK = 275;
/*      */   public static final short TERRAIN_ICEPLANET_WOOD = 276;
/*      */   public static final short TERRAIN_ICEPLANET_LEAVES = 277;
/*      */   public static final short TERRAIN_ICEPLANET_SPIKE_SPRITE = 278;
/*      */   public static final short TERRAIN_ICEPLANET_ICECRAG_SPRITE = 279;
/*      */   public static final short TERRAIN_ICEPLANET_ICECORAL_SPRITE = 280;
/*      */   public static final short TERRAIN_ICEPLANET_ICEGRASS_SPRITE = 281;
/*      */   public static final short LIGHT_RED = 282;
/*      */   public static final short LIGHT_BLUE = 283;
/*      */   public static final short LIGHT_GREEN = 284;
/*      */   public static final short LIGHT_YELLOW = 285;
/*      */   public static final short TERRAIN_ICEPLANET_CRYSTAL = 286;
/*      */   public static final short TERRAIN_REDWOOD = 287;
/*      */   public static final short TERRAIN_REDWOOD_LEAVES = 288;
/*      */   public static final short FIXED_DOCK_ID = 289;
/*      */   public static final short FIXED_DOCK_ID_ENHANCER = 290;
/*      */   public static final short FACTION_BLOCK = 291;
/*      */   public static final short FACTION_HUB_BLOCK = 292;
/*      */   public static final short POWER_CELL = 219;
/*      */   public static final short POWER_COIL = 220;
/*      */   public static final short POWER_DRAIN_BEAM_COMPUTER = 332;
/*      */   public static final short POWER_DRAIN_BEAM_MODULE = 333;
/*      */   public static final short POWER_SUPPLY_BEAM_COMPUTER = 334;
/*      */   public static final short POWER_SUPPLY_BEAM_MODULE = 335;
/*      */   public static boolean initialized;
/*  202 */   private static final HashSet factoryKeySet = new HashSet(256);
/*  203 */   public static final HashSet keySet = new HashSet(256);
/*      */   private static short[] keyArray;
/*  205 */   private static final HashSet leveldKeySet = new HashSet(256);
/*  206 */   private static final HashMap levelMap = new HashMap();
/*      */   private static ElementCategory categoryHirarchy;
/*      */   public static Properties properties;
/*      */ 
/*      */   public static void reinitializeData(File paramFile, String paramString)
/*      */   {
/*  214 */     initialized = false;
/*  215 */     categoryHirarchy = null;
/*  216 */     factoryKeySet.clear();
/*  217 */     keySet.clear();
/*  218 */     leveldKeySet.clear();
/*  219 */     levelMap.clear();
/*  220 */     highestType = 0;
/*  221 */     informationKeyMap.clear();
/*  222 */     infoArray = null;
/*  223 */     keyArray = null;
/*  224 */     initializeData(paramFile, paramString);
/*      */   }
/*      */ 
/*      */   public static void initializeData() {
/*  228 */     initializeData(null, null);
/*      */   }
/*      */ 
/*      */   public static void addInformationToExisting(ElementInformation paramElementInformation)
/*      */   {
/*  233 */     categoryHirarchy.insertRecusrive(paramElementInformation);
/*  234 */     add(paramElementInformation.getId(), paramElementInformation);
/*  235 */     infoArray = new ElementInformation[highestType + 1];
/*      */ 
/*  237 */     for (Map.Entry localEntry : informationKeyMap.entrySet())
/*      */     {
/*  239 */       infoArray[((Short)localEntry.getKey()).shortValue()] = ((ElementInformation)localEntry.getValue());
/*      */     }
/*  241 */     if (factoryKeySet.contains(Short.valueOf(paramElementInformation.getId()))) {
/*  242 */       paramElementInformation.getFactory().enhancer = 212;
/*  243 */       paramElementInformation.getControlling().addAll(factoryKeySet);
/*  244 */       paramElementInformation.getControlling().remove(Short.valueOf(paramElementInformation.getId()));
/*  245 */       paramElementInformation.getControlledBy().addAll(factoryKeySet);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void removeFromExisting(ElementInformation paramElementInformation)
/*      */   {
/*  251 */     keySet.remove(Short.valueOf(paramElementInformation.getId()));
/*  252 */     informationKeyMap.remove(Short.valueOf(paramElementInformation.getId()));
/*  253 */     highestType = 0;
/*  254 */     for (Object localObject1 = keySet.iterator(); ((Iterator)localObject1).hasNext(); ) { int i = ((Short)((Iterator)localObject1).next()).shortValue();
/*  255 */       highestType = Math.max(highestType, i);
/*      */     }
/*      */ 
/*  258 */     factoryKeySet.remove(Short.valueOf(paramElementInformation.getId()));
/*      */ 
/*  260 */     getLeveldkeyset().remove(Short.valueOf(paramElementInformation.getId()));
/*      */     Object localObject2;
/*      */     Iterator localIterator;
/*      */     Object localObject3;
/*  262 */     if (paramElementInformation.isLeveled()) {
/*  263 */       localObject1 = (HashMap)levelMap.get(Short.valueOf(paramElementInformation.getLevel().getIdBase()));
/*  264 */       localObject2 = new HashSet();
/*  265 */       if (localObject1 != null) {
/*  266 */         for (localIterator = ((HashMap)localObject1).entrySet().iterator(); localIterator.hasNext(); ) {
/*  267 */           if (((Short)(
/*  267 */             localObject3 = (Map.Entry)localIterator.next())
/*  267 */             .getValue()).shortValue() == paramElementInformation.getId()) {
/*  268 */             ((Set)localObject2).add(((Map.Entry)localObject3).getKey());
/*      */           }
/*      */         }
/*  271 */         for (localIterator = ((Set)localObject2).iterator(); localIterator.hasNext(); ) { localObject3 = (Integer)localIterator.next();
/*  272 */           ((HashMap)localObject1).remove(localObject3);
/*      */         }
/*      */       }
/*      */     }
/*  276 */     levelMap.remove(Short.valueOf(paramElementInformation.getId()));
/*      */ 
/*  278 */     infoArray = new ElementInformation[highestType + 1];
/*      */ 
/*  280 */     for (localObject1 = informationKeyMap.entrySet().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/*  281 */       infoArray[((Short)localObject2.getKey()).shortValue()] = ((ElementInformation)((Map.Entry)localObject2).getValue());
/*      */     }
/*  283 */     categoryHirarchy.removeRecursive(paramElementInformation);
/*      */   }
/*      */ 
/*      */   private static void initElements(ArrayList paramArrayList, ElementCategory paramElementCategory) {
/*  287 */     for (paramArrayList = paramArrayList.iterator(); paramArrayList.hasNext(); )
/*  288 */       add((
/*  288 */         localObject = (ElementInformation)paramArrayList.next())
/*  288 */         .getId(), (ElementInformation)localObject);
/*  294 */     Object localObject;
/*  290 */     categoryHirarchy = paramElementCategory;
/*      */ 
/*  292 */     infoArray = new ElementInformation[highestType + 1];
/*      */ 
/*  294 */     for (paramArrayList = informationKeyMap.entrySet().iterator(); paramArrayList.hasNext(); ) { localObject = (Map.Entry)paramArrayList.next();
/*  295 */       infoArray[((Short)localObject.getKey()).shortValue()] = ((ElementInformation)((Map.Entry)localObject).getValue());
/*      */     }
/*      */ 
/*  299 */     for (paramArrayList = factoryKeySet.iterator(); paramArrayList.hasNext(); )
/*      */     {
/*      */       short s;
/*  300 */       getInfo(s = ((Short)paramArrayList.next()).shortValue())
/*  300 */         .getFactory().enhancer = (getInfo(s).getControlling().size() > 0 ? ((Short)getInfo(s).getControlling().iterator().next()).shortValue() : 212);
/*  301 */       getInfo(s).getControlling().addAll(factoryKeySet);
/*  302 */       getInfo(s).getControlling().remove(Short.valueOf(s));
/*  303 */       getInfo(s).getControlledBy().addAll(factoryKeySet);
/*      */     }
/*  305 */     initialized = true;
/*      */   }
/*      */   public static void initializeData(File paramFile, String paramString) {
/*      */     while (true) { if (initialized) {
/*  309 */         return;
/*      */       }
/*      */ 
/*      */       try
/*      */       {
/*  316 */         initElements((
/*  316 */           paramFile = load(paramFile, paramString))
/*  316 */           .getInfoElements(), paramFile.getRootCategory()); } catch (Exception localException) {
/*  317 */         d.a(
/*  321 */           localException);
/*      */ 
/*  319 */         paramString = null; } paramFile = null; }
/*  320 */     keyArray = new short[keySet.size()];
/*      */ 
/*  326 */     paramFile = 0;
/*  327 */     for (paramString = keySet.iterator(); paramString.hasNext(); ) { int i = ((Short)paramString.next()).shortValue();
/*  328 */       keyArray[paramFile] = i;
/*  329 */       paramFile++; }
/*      */   }
/*      */ 
/*      */   private static void add(short paramShort, ElementInformation paramElementInformation)
/*      */   {
/*  334 */     if (keySet.contains(Short.valueOf(paramShort))) {
/*  335 */       throw new ParserConfigurationException("Duplicate Block ID " + paramShort + " (" + paramElementInformation.getName() + " and " + ((ElementInformation)informationKeyMap.get(Short.valueOf(paramShort))).getName() + ")");
/*      */     }
/*  337 */     keySet.add(Short.valueOf(paramShort));
/*  338 */     informationKeyMap.put(Short.valueOf(paramShort), paramElementInformation);
/*  339 */     highestType = Math.max(highestType, paramShort);
/*      */ 
/*  341 */     if (paramElementInformation.getFactory() != null) {
/*  342 */       factoryKeySet.add(Short.valueOf(paramShort));
/*      */     }
/*      */ 
/*  345 */     if (paramElementInformation.isLeveled()) {
/*  346 */       getLeveldkeyset().add(Short.valueOf(paramShort));
/*      */       HashMap localHashMap;
/*  348 */       if ((
/*  348 */         localHashMap = (HashMap)levelMap.get(Short.valueOf(paramElementInformation.getLevel().getIdBase()))) == null)
/*      */       {
/*  349 */         localHashMap = new HashMap();
/*  350 */         levelMap.put(Short.valueOf(paramElementInformation.getLevel().getIdBase()), localHashMap);
/*      */       }
/*  352 */       localHashMap.put(Integer.valueOf(paramElementInformation.getLevel().getLevel()), Short.valueOf(paramShort));
/*      */     }
/*      */   }
/*      */ 
/*      */   public static short getLevel(short paramShort, int paramInt) {
/*  357 */     if (((HashMap)levelMap.get(Short.valueOf(paramShort))).get(Integer.valueOf(paramInt)) == null) {
/*  358 */       return -1;
/*      */     }
/*  360 */     return ((Short)((HashMap)levelMap.get(Short.valueOf(paramShort))).get(Integer.valueOf(paramInt))).shortValue();
/*      */   }
/*      */   public static void addShipHull(short paramShort1, int paramInt, String paramString, short paramShort2) {
/*  363 */     (
/*  364 */       paramString = new ElementInformation(paramShort1, "Hull " + paramString, GeneralElement.class, paramShort2))
/*  364 */       .setPrice(500L);
/*  365 */     paramString.setAmour(50);
/*  366 */     paramString.setBuildIconNum(paramInt);
/*  367 */     paramString.setShoppable(true);
/*  368 */     if (paramShort1 == 63) {
/*  369 */       paramString.setBlended(true);
/*      */     }
/*  371 */     paramString.setDescription(formatDescString("The Hull Element\n\nA cheap amoured Element to give the ship structure.\nIt should be used to protect more important elements.\n\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
/*      */ 
/*  383 */     add(paramShort1, paramString);
/*      */   }
/*      */ 
/*      */   public static String formatDescString(String paramString)
/*      */   {
/*  388 */     paramString = new StringBuffer(paramString);
/*      */ 
/*  390 */     int i = 0;
/*  391 */     for (int j = 0; j < paramString.length() - 1; 
/*  392 */       j++) {
/*  393 */       if (paramString.charAt(j) == '\n') {
/*  394 */         i = 0;
/*  395 */         j++;
/*      */       }
/*      */ 
/*  399 */       if (i > 50) {
/*  400 */         while ((j > 0) && (paramString.charAt(j) != ' ')) {
/*  401 */           j--;
/*      */         }
/*  403 */         paramString.deleteCharAt(j);
/*  404 */         paramString.insert(j, "\n");
/*  405 */         j++;
/*      */ 
/*  407 */         i = 0;
/*      */       }
/*      */ 
/*  411 */       i++;
/*      */     }
/*      */ 
/*  415 */     return paramString.toString();
/*      */   }
/*      */ 
/*      */   public static ElementCategory getCategoryHirarchy()
/*      */   {
/*  421 */     return categoryHirarchy;
/*      */   }
/*      */ 
/*      */   public static ElementInformation getInfo(short paramShort) {
/*  425 */     assert (infoArray[paramShort] != null) : ("type " + paramShort + " unknown, please check the properties and the xml");
/*      */     ElementInformation localElementInformation;
/*  427 */     if ((
/*  427 */       localElementInformation = infoArray[paramShort]) == null)
/*      */     {
/*  428 */       throw new NullPointerException("Exception: REQUESTED TYPE " + paramShort + " IS NULL");
/*      */     }
/*  430 */     return localElementInformation;
/*      */   }
/*      */   public static boolean exists(short paramShort) {
/*  433 */     return (paramShort > 0) && (paramShort < infoArray.length) && (infoArray[paramShort] != null);
/*      */   }
/*      */ 
/*      */   public static void initializeDeathStarData()
/*      */   {
/*      */     ElementInformation localElementInformation;
/*  437 */     (
/*  438 */       localElementInformation = new ElementInformation((short)65, "Deathstar Core", DeathStarElement.class, (short)65))
/*  438 */       .setAmour(30);
/*  439 */     localElementInformation.setMaxHitPoints((short)1000);
/*  440 */     add((short)65, localElementInformation);
/*      */   }
/*      */ 
/*      */   public static void initializeShipData()
/*      */   {
/*      */     ElementInformation localElementInformation;
/*  444 */     (
/*  445 */       localElementInformation = new ElementInformation((short)6, "Weapon Controller", ShipElement.class, (short)6))
/*  445 */       .setPrice(300L);
/*  446 */     localElementInformation.setAmour(5);
/*  447 */     localElementInformation.getControlling().add(Short.valueOf((short)16));
/*  448 */     localElementInformation.setBuildIconNum(10);
/*  449 */     localElementInformation.setShoppable(true);
/*  450 */     localElementInformation.setEnterable(true);
/*  451 */     localElementInformation.setDescription(formatDescString("The Weapon Controller Element\n\nThis element is used to control weapon\n elements of a Ship.\n\nWeapons have to be connected to the\nweapon controller in order for them to work.\nWeapon controllers can be entered by\nindividuals to crew the ship\n\n\nCOST:          300\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      WeaponElement\nCONTROLLED BY: Core"));
/*      */ 
/*  466 */     add((short)6, localElementInformation);
/*      */ 
/*  468 */     (
/*  469 */       localElementInformation = new ElementInformation((short)1, "Core", ShipElement.class, (short)1))
/*  469 */       .setPrice(10000L);
/*  470 */     localElementInformation.setBuildIconNum(1);
/*  471 */     localElementInformation.setAmour(20);
/*  472 */     localElementInformation.setMaxHitPoints((short)500);
/*  473 */     localElementInformation.setShoppable(true);
/*  474 */     localElementInformation.setEnterable(true);
/*  475 */     localElementInformation.getControlling().add(Short.valueOf((short)16));
/*  476 */     localElementInformation.getControlling().add(Short.valueOf((short)8));
/*  477 */     localElementInformation.getControlling().add(Short.valueOf((short)22));
/*  478 */     localElementInformation.getControlling().add(Short.valueOf((short)15));
/*  479 */     localElementInformation.setDescription(formatDescString("The Core Element\n\nThis element is the most important element of a ship\nIt can only be placed, when spawning a new ship,\nand cannot be destroyed or removed!\n\nIf a core element is damaged enough, the individual\ninside the element will be catapulted outside of the ship\nCore controllers can be entered by\nindividuals to crew the ship\n\n\nCOST:          10000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      WeaponController, HarvestController, Thrusters\nCONTROLLED BY: -"));
/*      */ 
/*  495 */     add((short)1, localElementInformation);
/*      */ 
/*  497 */     addShipHull((short)5, 5, "grey", (short)5);
/*  498 */     addShipHull((short)75, 33, "black", (short)75);
/*  499 */     addShipHull((short)76, 34, "red", (short)76);
/*  500 */     addShipHull((short)77, 35, "blue", (short)77);
/*  501 */     addShipHull((short)79, 36, "yellow", (short)79);
/*  502 */     addShipHull((short)78, 37, "green", (short)78);
/*  503 */     addShipHull((short)70, 39, "brown", (short)70);
/*  504 */     addShipHull((short)69, 38, "purple", (short)69);
/*      */ 
/*  507 */     addShipHull((short)63, 40, "glass", (short)63);
/*      */ 
/*  509 */     (
/*  510 */       localElementInformation = new ElementInformation((short)47, "Cockpit", ShipElement.class, (short)47))
/*  510 */       .setPrice(500L);
/*  511 */     localElementInformation.setAmour(10);
/*  512 */     localElementInformation.setBuildIconNum(30);
/*  513 */     localElementInformation.setShoppable(true);
/*  514 */     localElementInformation.setDescription(formatDescString("The Cockpit Element\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
/*      */ 
/*  525 */     add((short)47, localElementInformation);
/*      */ 
/*  528 */     (
/*  529 */       localElementInformation = new ElementInformation((short)55, "Light", ShipElement.class, (short)55))
/*  529 */       .setPrice(500L);
/*  530 */     localElementInformation.setBuildIconNum(31);
/*  531 */     localElementInformation.setLightSource(true);
/*  532 */     localElementInformation.setShoppable(true);
/*  533 */     localElementInformation.getLightSourceColor().set(1.0F, 1.0F, 0.23F);
/*  534 */     localElementInformation.setDescription(formatDescString("The Light Element\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
/*      */ 
/*  545 */     add((short)55, localElementInformation);
/*      */ 
/*  547 */     (
/*  548 */       localElementInformation = new ElementInformation((short)62, "LightBeacon", ShipElement.class, (short)62))
/*  548 */       .setPrice(1000L);
/*  549 */     localElementInformation.setBuildIconNum(32);
/*  550 */     localElementInformation.setShoppable(true);
/*  551 */     localElementInformation.setLightSource(true);
/*  552 */     localElementInformation.setBlended(true);
/*  553 */     localElementInformation.setDescription(formatDescString("The LightBeacon Element\n\nCOST:          1000\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
/*      */ 
/*  564 */     add((short)62, localElementInformation);
/*      */ 
/*  566 */     (
/*  567 */       localElementInformation = new ElementInformation((short)8, "Thruster", ShipElement.class, (short)8))
/*  567 */       .setPrice(300L);
/*  568 */     localElementInformation.setBuildIconNum(13);
/*  569 */     localElementInformation.setIndividualSides(6);
/*  570 */     localElementInformation.setShoppable(true);
/*  571 */     localElementInformation.setOrientatable(true);
/*  572 */     localElementInformation.setDescription(formatDescString("The Thruster Element\n\nThe basic thruster elements to get ships moving.\nThe more elements are present in a ship, \nthe faster the ship will go relative to its mass.\n\n\nCOST:          300\nRANGE:         -\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Core"));
/*      */ 
/*  585 */     add((short)8, localElementInformation);
/*      */ 
/*  587 */     (
/*  588 */       localElementInformation = new ElementInformation((short)3, "Shield Generator", ShipElement.class, (short)3))
/*  588 */       .setPrice(3000L);
/*  589 */     localElementInformation.setBuildIconNum(8);
/*  590 */     localElementInformation.setShoppable(true);
/*  591 */     localElementInformation.setDescription(formatDescString("The Shield Generator Element\n\nThis element provides a forcefield against Energy Weapons.\nOnce, its not been hit for 60 seconds, it will automatically recharge. \nThe basic radius of the forcefield is fairly small,\nbut this is a groupable Element: the more elements are\ntouching each other, the bigger and stronger the forcefield will become.\n\n\nCOST:          3000\nRANGE:         -\nHP:         \t0\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: -"));
/*      */ 
/*  606 */     add((short)3, localElementInformation);
/*      */ 
/*  609 */     (
/*  610 */       localElementInformation = new ElementInformation((short)16, "Weapon", ShipElement.class, (short)16))
/*  610 */       .setPrice(500L);
/*  611 */     localElementInformation.setIndividualSides(6);
/*  612 */     localElementInformation.setOrientatable(true);
/*  613 */     localElementInformation.setBuildIconNum(9);
/*  614 */     localElementInformation.setShoppable(true);
/*  615 */     localElementInformation.setDescription(formatDescString("The Weapon Element\n\nThis is a basic weapon Element.\nIt does 1 unit of damage. \nThis is a groupable Element: \nyou can add elements to build a larger more efficient weapon \nMore WIDTH means MORE DAMAGE.\nMore HEIGHT means LESS RELOAD TIME.\nMore DEPTH means MORE SPEED.\n\n\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: WeaponController"));
/*      */ 
/*  633 */     add((short)16, localElementInformation);
/*      */ 
/*  635 */     (
/*  636 */       localElementInformation = new ElementInformation((short)2, "Battery", ShipElement.class, (short)2))
/*  636 */       .setPrice(300L);
/*  637 */     localElementInformation.setBuildIconNum(11);
/*  638 */     localElementInformation.setShoppable(true);
/*  639 */     localElementInformation.setDescription(formatDescString("The Power Generator Element\n\nThis Element provides power for all Elements,\nthat are in range. \n\n\n\nCOST:          300\nRANGE:         5\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -\n"));
/*      */ 
/*  652 */     add((short)2, localElementInformation);
/*      */ 
/*  655 */     (
/*  656 */       localElementInformation = new ElementInformation((short)14, "Explosive Module", ShipElement.class, (short)14))
/*  656 */       .setPrice(500L);
/*  657 */     localElementInformation.setBuildIconNum(23);
/*  658 */     localElementInformation.setShoppable(true);
/*  659 */     localElementInformation.setDescription(formatDescString("The Explosive Element\n\nThis is a very dangerous Element\nOnce you connect it to the core,\nit will detonate in a huge balst\non impact."));
/*      */ 
/*  667 */     add((short)14, localElementInformation);
/*      */ 
/*  669 */     (
/*  670 */       localElementInformation = new ElementInformation((short)22, "Cloaking Module", ShipElement.class, (short)22))
/*  670 */       .setPrice(1000L);
/*  671 */     localElementInformation.setBuildIconNum(22);
/*  672 */     localElementInformation.setShoppable(true);
/*  673 */     localElementInformation.setDescription(formatDescString("The Cloaking Module\n\nThis Module enables you be invisibe \nfrom other players for a short\ntime.\n\nGrouping these modules will increase cloking time"));
/*      */ 
/*  680 */     add((short)22, localElementInformation);
/*      */ 
/*  682 */     (
/*  683 */       localElementInformation = new ElementInformation((short)15, "Radar Jamming Module", ShipElement.class, (short)15))
/*  683 */       .setPrice(300L);
/*  684 */     localElementInformation.setBuildIconNum(24);
/*  685 */     localElementInformation.setShoppable(true);
/*  686 */     localElementInformation.setDescription(formatDescString("The Radar Jamming Element\n\nThis Module enables you to vanish\nfrom other players radar for a short\ntime.\n\nGrouping these modules will increase jamming time"));
/*      */ 
/*  693 */     add((short)15, localElementInformation);
/*      */ 
/*  696 */     (
/*  697 */       localElementInformation = new ElementInformation((short)24, "Salvage Beam", ShipElement.class, (short)24))
/*  697 */       .setPrice(700L);
/*  698 */     localElementInformation.setBuildIconNum(12);
/*  699 */     localElementInformation.setIndividualSides(6);
/*  700 */     localElementInformation.setOrientatable(true);
/*  701 */     localElementInformation.setShoppable(true);
/*  702 */     localElementInformation.setDescription(formatDescString("The Salvager Beam Element\n\nThis Element can be used like a weapon.\nWith this module, you can collect elements\nfrom neutral structures. Grouping this elements\nwill decrease the salvage time per element\n\n\n\nCOST:          700\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Salvager Controller\n"));
/*      */ 
/*  716 */     add((short)24, localElementInformation);
/*      */ 
/*  718 */     (
/*  719 */       localElementInformation = new ElementInformation((short)4, "Salvage Controller", ShipElement.class, (short)4))
/*  719 */       .setPrice(1000L);
/*  720 */     localElementInformation.setBuildIconNum(14);
/*  721 */     localElementInformation.setShoppable(true);
/*  722 */     localElementInformation.setEnterable(true);
/*  723 */     localElementInformation.getControlling().add(Short.valueOf((short)24));
/*  724 */     localElementInformation.setDescription(formatDescString("The Salvager Beam Controller Element\n\nThis element is use like the weapon controller,\nwith the difference that it controls salvage elements\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Salvager\nCONTROLLED BY: -\n"));
/*      */ 
/*  736 */     add((short)4, localElementInformation);
/*      */ 
/*  739 */     (
/*  740 */       localElementInformation = new ElementInformation((short)30, "Repair Beam", ShipElement.class, (short)30))
/*  740 */       .setPrice(700L);
/*  741 */     localElementInformation.setBuildIconNum(26);
/*  742 */     localElementInformation.setShoppable(true);
/*  743 */     localElementInformation.setDescription(formatDescString("The Repair Beam Element\n\nThis Element can be used like a weapon.\nWith this module, you can repair elements\nfrom other structures. Grouping this elements\nwill decrease the repair time per element\n\n\n\nCOST:          700\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Repair Controller\n"));
/*      */ 
/*  757 */     add((short)30, localElementInformation);
/*      */ 
/*  759 */     (
/*  760 */       localElementInformation = new ElementInformation((short)39, "Repair Controller", ShipElement.class, (short)39))
/*  760 */       .setPrice(1000L);
/*  761 */     localElementInformation.setBuildIconNum(27);
/*  762 */     localElementInformation.setShoppable(true);
/*  763 */     localElementInformation.setEnterable(true);
/*  764 */     localElementInformation.getControlling().add(Short.valueOf((short)30));
/*  765 */     localElementInformation.setDescription(formatDescString("The repair Beam Controller Module\n\nThis element is use like the weapon controller,\nwith the difference that it controls repair elements\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      repair\nCONTROLLED BY: -\n"));
/*      */ 
/*  777 */     add((short)39, localElementInformation);
/*      */ 
/*  779 */     (
/*  780 */       localElementInformation = new ElementInformation((short)7, "Docking Station", ShipElement.class, (short)7))
/*  780 */       .setPrice(500L);
/*  781 */     localElementInformation.setBuildIconNum(15);
/*  782 */     localElementInformation.setShoppable(true);
/*  783 */     localElementInformation.setDescription(formatDescString("The Dock Element\n\nYou can dock other ships here,\n\n\n\nCOST:          500\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -\n"));
/*      */ 
/*  794 */     add((short)7, localElementInformation);
/*      */ 
/*  798 */     (
/*  799 */       localElementInformation = new ElementInformation((short)38, "Dumb Missile Controller", ShipElement.class, (short)38))
/*  799 */       .setPrice(1000L);
/*  800 */     localElementInformation.setBuildIconNum(16);
/*  801 */     localElementInformation.setShoppable(true);
/*  802 */     localElementInformation.getControlling().add(Short.valueOf((short)32));
/*  803 */     localElementInformation.setEnterable(true);
/*  804 */     localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
/*      */ 
/*  815 */     add((short)38, localElementInformation);
/*      */ 
/*  817 */     (
/*  818 */       localElementInformation = new ElementInformation((short)32, "Dumb Missile Module", ShipElement.class, (short)32))
/*  818 */       .setPrice(800L);
/*  819 */     localElementInformation.setBuildIconNum(17);
/*  820 */     localElementInformation.setShoppable(true);
/*  821 */     localElementInformation.setIndividualSides(6);
/*  822 */     localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          800\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
/*      */ 
/*  831 */     add((short)32, localElementInformation);
/*      */ 
/*  836 */     (
/*  837 */       localElementInformation = new ElementInformation((short)46, "Heat Seeking Missile Controller", ShipElement.class, (short)46))
/*  837 */       .setPrice(1200L);
/*  838 */     localElementInformation.setBuildIconNum(18);
/*  839 */     localElementInformation.setShoppable(true);
/*  840 */     localElementInformation.setEnterable(true);
/*  841 */     localElementInformation.getControlling().add(Short.valueOf((short)40));
/*  842 */     localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1200\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
/*      */ 
/*  853 */     add((short)46, localElementInformation);
/*      */ 
/*  855 */     (
/*  856 */       localElementInformation = new ElementInformation((short)40, "Heat Seeking Missile", ShipElement.class, (short)40))
/*  856 */       .setPrice(1000L);
/*  857 */     localElementInformation.setBuildIconNum(19);
/*  858 */     localElementInformation.setShoppable(true);
/*  859 */     localElementInformation.setIndividualSides(6);
/*  860 */     localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
/*      */ 
/*  869 */     add((short)40, localElementInformation);
/*      */ 
/*  875 */     (
/*  876 */       localElementInformation = new ElementInformation((short)54, "Fire and forget Missile Controller", ShipElement.class, (short)54))
/*  876 */       .setPrice(1500L);
/*  877 */     localElementInformation.setBuildIconNum(20);
/*  878 */     localElementInformation.setShoppable(true);
/*  879 */     localElementInformation.setEnterable(true);
/*  880 */     localElementInformation.getControlling().add(Short.valueOf((short)48));
/*  881 */     localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1500\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
/*      */ 
/*  892 */     add((short)54, localElementInformation);
/*      */ 
/*  894 */     (
/*  895 */       localElementInformation = new ElementInformation((short)48, "Fire and forget Missile", ShipElement.class, (short)48))
/*  895 */       .setPrice(1300L);
/*  896 */     localElementInformation.setBuildIconNum(21);
/*  897 */     localElementInformation.setShoppable(true);
/*  898 */     localElementInformation.setIndividualSides(6);
/*  899 */     localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          1300\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
/*      */ 
/*  908 */     add((short)48, localElementInformation);
/*      */   }
/*      */ 
/*      */   public static void initializeSpaceStationData()
/*      */   {
/*      */     ElementInformation localElementInformation;
/*  914 */     (
/*  915 */       localElementInformation = new ElementInformation((short)112, "Landing Module", SpaceStationElement.class, (short)112))
/*  915 */       .setBuildIconNum(41);
/*  916 */     localElementInformation.setShoppable(true);
/*  917 */     localElementInformation.setPrice(10L);
/*  918 */     localElementInformation.setDescription(formatDescString("The Landing Module\n\naffixes any ship to this module.make sure there is enough space!\n \n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*      */ 
/*  924 */     add((short)112, localElementInformation);
/*      */ 
/*  927 */     (
/*  928 */       localElementInformation = new ElementInformation((short)113, "Lift Module", SpaceStationElement.class, (short)113))
/*  928 */       .setBuildIconNum(42);
/*  929 */     localElementInformation.setShoppable(true);
/*  930 */     localElementInformation.setPrice(10L);
/*  931 */     localElementInformation.setDescription(formatDescString("The Lift Module\n\nit will lift up any objects\n from bottom to the top of any group\nof lift objects.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*      */ 
/*  937 */     add((short)113, localElementInformation);
/*      */ 
/*  940 */     (
/*  941 */       localElementInformation = new ElementInformation((short)122, "Door Module", SpaceStationElement.class, (short)122))
/*  941 */       .setBuildIconNum(43);
/*  942 */     localElementInformation.setShoppable(true);
/*  943 */     localElementInformation.setPrice(10L);
/*  944 */     localElementInformation.setDescription(formatDescString("The door module\n\nThis module will only work\n if placed on top of an opening\nuse a line of modules to \nmake the door wider.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*      */ 
/*  951 */     add((short)122, localElementInformation);
/*      */ 
/*  954 */     (
/*  955 */       localElementInformation = new ElementInformation((short)120, "Chest Module", SpaceStationElement.class, (short)120))
/*  955 */       .setBuildIconNum(44);
/*  956 */     localElementInformation.setShoppable(true);
/*  957 */     localElementInformation.setPrice(10L);
/*  958 */     localElementInformation.setDescription(formatDescString("The Recycler Module\n\nPlace modules inside\n to keep them save.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*      */ 
/*  963 */     add((short)120, localElementInformation);
/*      */ 
/*  966 */     (
/*  967 */       localElementInformation = new ElementInformation((short)114, "Recycler Module", SpaceStationElement.class, (short)114))
/*  967 */       .setBuildIconNum(45);
/*  968 */     localElementInformation.setShoppable(true);
/*  969 */     localElementInformation.setPrice(10L);
/*  970 */     localElementInformation.setDescription(formatDescString("The Recycler Module\n\nplace modules inside\n to convert them to a better module.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*      */ 
/*  975 */     add((short)114, localElementInformation);
/*      */ 
/*  978 */     (
/*  979 */       localElementInformation = new ElementInformation((short)121, "AI Module", ShipElement.class, (short)121))
/*  979 */       .setBuildIconNum(46);
/*  980 */     localElementInformation.setShoppable(true);
/*  981 */     localElementInformation.setPrice(10L);
/*  982 */     localElementInformation.setDescription(formatDescString("The AI Module\n\nlets you control any ship\n if it has been placed on there.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*      */ 
/*  987 */     add((short)121, localElementInformation);
/*      */ 
/*  990 */     (
/*  991 */       localElementInformation = new ElementInformation((short)123, "Build Block", SpaceStationElement.class, (short)123))
/*  991 */       .setBuildIconNum(47);
/*  992 */     localElementInformation.setShoppable(true);
/*  993 */     localElementInformation.setPrice(10L);
/*  994 */     localElementInformation.setEnterable(true);
/*      */ 
/*  996 */     localElementInformation.setDescription(formatDescString("The Build Module\n\nthis module is used to edit \n space stations from the inside.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*      */ 
/* 1001 */     add((short)123, localElementInformation);
/*      */   }
/*      */ 
/*      */   public static void initializeTerrainData()
/*      */   {
/*      */     ElementInformation localElementInformation;
/* 1005 */     (
/* 1006 */       localElementInformation = new ElementInformation((short)64, "Ice Element", GeneralElement.class, (short)64))
/* 1006 */       .setBuildIconNum(66);
/* 1007 */     add((short)64, localElementInformation);
/*      */ 
/* 1009 */     (
/* 1010 */       localElementInformation = new ElementInformation((short)80, "Lava Element", GeneralElement.class, (short)80))
/* 1010 */       .setLightSource(true);
/* 1011 */     localElementInformation.setAnimated(true);
/* 1012 */     localElementInformation.getLightSourceColor().set(1.0F, 0.2F, 0.2F);
/* 1013 */     localElementInformation.setBuildIconNum(68);
/* 1014 */     add((short)80, localElementInformation);
/*      */ 
/* 1016 */     (
/* 1017 */       localElementInformation = new ElementInformation((short)72, "Mineral Element", GeneralElement.class, (short)72))
/* 1017 */       .setBuildIconNum(69);
/* 1018 */     add((short)72, localElementInformation);
/*      */ 
/* 1020 */     (
/* 1021 */       localElementInformation = new ElementInformation((short)73, "Rock Element", GeneralElement.class, (short)73))
/* 1021 */       .setBuildIconNum(65);
/* 1022 */     add((short)73, localElementInformation);
/*      */ 
/* 1024 */     (
/* 1025 */       localElementInformation = new ElementInformation((short)74, "Sand Element", GeneralElement.class, (short)74))
/* 1025 */       .setBuildIconNum(71);
/* 1026 */     add((short)74, localElementInformation);
/*      */ 
/* 1032 */     (
/* 1033 */       localElementInformation = new ElementInformation((short)128, "Gold", GeneralElement.class, (short)128))
/* 1033 */       .setBuildIconNum(72);
/* 1034 */     add((short)128, localElementInformation);
/*      */ 
/* 1037 */     (
/* 1038 */       localElementInformation = new ElementInformation((short)129, "Iridium", GeneralElement.class, (short)129))
/* 1038 */       .setBuildIconNum(73);
/* 1039 */     add((short)129, localElementInformation);
/*      */ 
/* 1042 */     (
/* 1043 */       localElementInformation = new ElementInformation((short)130, "Mercury", GeneralElement.class, (short)130))
/* 1043 */       .setBuildIconNum(74);
/* 1044 */     add((short)130, localElementInformation);
/*      */ 
/* 1047 */     (
/* 1048 */       localElementInformation = new ElementInformation((short)131, "Palladium", GeneralElement.class, (short)131))
/* 1048 */       .setBuildIconNum(75);
/* 1049 */     add((short)131, localElementInformation);
/*      */ 
/* 1052 */     (
/* 1053 */       localElementInformation = new ElementInformation((short)132, "Platinum", GeneralElement.class, (short)132))
/* 1053 */       .setBuildIconNum(76);
/* 1054 */     add((short)132, localElementInformation);
/*      */ 
/* 1057 */     (
/* 1058 */       localElementInformation = new ElementInformation((short)133, "Lithium", GeneralElement.class, (short)133))
/* 1058 */       .setBuildIconNum(77);
/* 1059 */     add((short)133, localElementInformation);
/*      */ 
/* 1062 */     (
/* 1063 */       localElementInformation = new ElementInformation((short)134, "Magnesium", GeneralElement.class, (short)134))
/* 1063 */       .setBuildIconNum(78);
/* 1064 */     add((short)134, localElementInformation);
/*      */ 
/* 1067 */     (
/* 1068 */       localElementInformation = new ElementInformation((short)135, "Titanium", GeneralElement.class, (short)135))
/* 1068 */       .setBuildIconNum(79);
/* 1069 */     add((short)135, localElementInformation);
/*      */ 
/* 1072 */     (
/* 1073 */       localElementInformation = new ElementInformation((short)136, "Uranium", GeneralElement.class, (short)136))
/* 1073 */       .setBuildIconNum(80);
/* 1074 */     add((short)136, localElementInformation);
/*      */ 
/* 1077 */     (
/* 1078 */       localElementInformation = new ElementInformation((short)137, "Polonium", GeneralElement.class, (short)137))
/* 1078 */       .setBuildIconNum(81);
/* 1079 */     add((short)137, localElementInformation);
/*      */ 
/* 1082 */     (
/* 1083 */       localElementInformation = new ElementInformation((short)138, "Red Top", GeneralElement.class, (short)138))
/* 1083 */       .setBuildIconNum(82);
/* 1084 */     add((short)138, localElementInformation);
/*      */ 
/* 1087 */     (
/* 1088 */       localElementInformation = new ElementInformation((short)139, "Red Rock", GeneralElement.class, (short)139))
/* 1088 */       .setBuildIconNum(83);
/* 1089 */     add((short)139, localElementInformation);
/*      */ 
/* 1092 */     (
/* 1093 */       localElementInformation = new ElementInformation((short)140, "Red Dirt", GeneralElement.class, (short)140))
/* 1093 */       .setBuildIconNum(84);
/* 1094 */     add((short)140, localElementInformation);
/*      */   }
/*      */ 
/*      */   public static boolean isValidType(short paramShort)
/*      */   {
/* 1103 */     return (paramShort >= 0) && (paramShort < infoArray.length) && (infoArray[paramShort] != null);
/*      */   }
/*      */ 
/*      */   public static String list() {
/* 1107 */     return keySet.toString();
/*      */   }
/*      */ 
/*      */   private static ElementParser load(File paramFile, String paramString)
/*      */   {
/*      */     ElementParser localElementParser;
/* 1112 */     if (paramFile == null) {
/* 1113 */       (
/* 1114 */         localElementParser = new ElementParser())
/* 1114 */         .loadAndParseDefault();
/*      */ 
/* 1116 */       return localElementParser;
/*      */     }
/* 1118 */     (
/* 1119 */       localElementParser = new ElementParser())
/* 1119 */       .loadAndParseCustomXML(paramFile, paramString);
/*      */ 
/* 1121 */     return localElementParser;
/*      */   }
/*      */ 
/*      */   public static short[] typeList()
/*      */   {
/* 1126 */     return keyArray;
/*      */   }
/*      */ 
/*      */   public Class getTypeFromString(String paramString) {
/* 1130 */     if (paramString.equals("terrain"))
/* 1131 */       return GeneralElement.class;
/* 1132 */     if (paramString.equals("ship"))
/* 1133 */       return ShipElement.class;
/* 1134 */     if (paramString.equals("spacestation"))
/* 1135 */       return SpaceStationElement.class;
/* 1136 */     if (paramString.equals("deathstar")) {
/* 1137 */       return SpaceStationElement.class;
/*      */     }
/*      */ 
/* 1140 */     throw new ElementTypeNotFoundException(paramString);
/*      */   }
/*      */ 
/*      */   public static HashSet getLeveldkeyset()
/*      */   {
/* 1147 */     return leveldKeySet;
/*      */   }
/*      */ 
/*      */   public static HashSet getFactorykeyset()
/*      */   {
/* 1153 */     return factoryKeySet;
/*      */   }
/*      */ 
/*      */   public static File writeDocument(String paramString)
/*      */   {
/* 1158 */     return writeDocument(new File(paramString));
/*      */   }
/*      */ 
/*      */   public static File writeDocument(File paramFile)
/*      */   {
/*      */     try
/*      */     {
/* 1168 */       localObject1 = DocumentBuilderFactory.newInstance()
/* 1167 */         .newDocumentBuilder()
/* 1168 */         .newDocument();
/*      */ 
/* 1172 */       Object localObject2 = categoryHirarchy;
/*      */ 
/* 1174 */       Element localElement = ((Document)localObject1).createElement(ElementParser.getStringFromType(((ElementCategory)localObject2).getCategory()));
/*      */ 
/* 1176 */       Object localObject3 = ((Document)localObject1).createComment("autocreated by the starmade block editor");
/* 1177 */       localElement.appendChild((Node)localObject3);
/*      */ 
/* 1179 */       for (localObject2 = ((ElementCategory)localObject2).getChildren().iterator(); ((Iterator)localObject2).hasNext(); ) {
/* 1180 */         writeCatToXML((ElementCategory)((Iterator)localObject2).next(), 
/* 1180 */           localElement, (Document)localObject1);
/*      */       }
/*      */ 
/* 1184 */       ((Document)localObject1).appendChild(localElement);
/* 1185 */       ((Document)localObject1).setXmlVersion("1.0");
/*      */ 
/* 1195 */       (
/* 1196 */         localObject3 = TransformerFactory.newInstance()
/* 1195 */         .newTransformer())
/* 1196 */         .setOutputProperty("omit-xml-declaration", "yes");
/* 1197 */       ((Transformer)localObject3).setOutputProperty("indent", "yes");
/*      */ 
/* 1200 */       new StringWriter();
/* 1201 */       localObject2 = new StreamResult(paramFile);
/* 1202 */       localObject1 = new DOMSource((Node)localObject1);
/* 1203 */       ((Transformer)localObject3).transform((Source)localObject1, (Result)localObject2);
/*      */ 
/* 1209 */       return paramFile;
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */       Object localObject1;
/* 1210 */       (
/* 1211 */         localObject1 = localException)
/* 1211 */         .printStackTrace();
/* 1212 */       d.a((Exception)localObject1);
/*      */     }
/* 1214 */     return null;
/*      */   }
/*      */ 
/*      */   private static void writeCatToXML(ElementCategory paramElementCategory, Element paramElement, Document paramDocument) {
/* 1218 */     Element localElement = paramDocument.createElement(ElementParser.getStringFromType(paramElementCategory.getCategory()));
/*      */ 
/* 1220 */     for (Iterator localIterator = paramElementCategory.getChildren().iterator(); localIterator.hasNext(); ) {
/* 1221 */       writeCatToXML((ElementCategory)localIterator.next(), 
/* 1221 */         localElement, paramDocument);
/*      */     }
/* 1223 */     for (localIterator = paramElementCategory.getInfoElements().iterator(); localIterator.hasNext(); ) ((ElementInformation)localIterator.next())
/* 1225 */         .appendXML(paramDocument, localElement);
/*      */ 
/* 1227 */     paramElement.appendChild(localElement);
/*      */   }
/*      */   public static void clear() {
/* 1230 */     informationKeyMap.clear();
/* 1231 */     infoArray = null;
/* 1232 */     highestType = 0;
/* 1233 */     factoryKeySet.clear();
/* 1234 */     keySet.clear();
/* 1235 */     leveldKeySet.clear();
/* 1236 */     levelMap.clear();
/* 1237 */     categoryHirarchy.clear();
/*      */   }
/*      */ 
/*      */   public static void reparseProperties()
/*      */   {
/* 1243 */     properties = new Properties();
/* 1244 */     FileInputStream localFileInputStream = new FileInputStream("./data/config/BlockTypes.properties");
/* 1245 */     properties.load(localFileInputStream);
/*      */   }
/*      */ 
/*      */   public static void reparseProperties(String paramString)
/*      */   {
/* 1251 */     properties = new Properties();
/* 1252 */     paramString = new FileInputStream(paramString);
/* 1253 */     properties.load(paramString);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementKeyMap
 * JD-Core Version:    0.6.2
 */