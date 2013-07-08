/*    1:     */package org.schema.game.common.data.element;
/*    2:     */
/*    3:     */import cv;
/*    4:     */import d;
/*    5:     */import java.io.File;
/*    6:     */import java.io.FileInputStream;
/*    7:     */import java.io.StringWriter;
/*    8:     */import java.util.ArrayList;
/*    9:     */import java.util.HashMap;
/*   10:     */import java.util.HashSet;
/*   11:     */import java.util.Iterator;
/*   12:     */import java.util.Map;
/*   13:     */import java.util.Map.Entry;
/*   14:     */import java.util.Properties;
/*   15:     */import java.util.Set;
/*   16:     */import javax.vecmath.Vector3f;
/*   17:     */import javax.xml.parsers.DocumentBuilder;
/*   18:     */import javax.xml.parsers.DocumentBuilderFactory;
/*   19:     */import javax.xml.parsers.ParserConfigurationException;
/*   20:     */import javax.xml.transform.Result;
/*   21:     */import javax.xml.transform.Source;
/*   22:     */import javax.xml.transform.Transformer;
/*   23:     */import javax.xml.transform.TransformerFactory;
/*   24:     */import javax.xml.transform.dom.DOMSource;
/*   25:     */import javax.xml.transform.stream.StreamResult;
/*   26:     */import org.schema.game.common.data.element.deathstar.DeathStarElement;
/*   27:     */import org.schema.game.common.data.element.ship.ShipElement;
/*   28:     */import org.schema.game.common.data.element.spacestation.SpaceStationElement;
/*   29:     */import org.w3c.dom.Document;
/*   30:     */import org.w3c.dom.Element;
/*   31:     */import org.w3c.dom.Node;
/*   32:     */
/*   41:     */public class ElementKeyMap
/*   42:     */{
/*   43:  43 */  private static final Map informationKeyMap = new HashMap();
/*   44:     */  
/*   45:  45 */  public static int highestType = 0;
/*   46:     */  
/*   47:     */  private static ElementInformation[] infoArray;
/*   48:     */  
/*   49:     */  public static final short WEAPON_CONTROLLER_ID = 6;
/*   50:     */  
/*   51:     */  public static final short WEAPON_ID = 16;
/*   52:     */  
/*   53:     */  public static final short CORE_ID = 1;
/*   54:     */  
/*   55:     */  public static final short DEATHSTAR_CORE_ID = 65;
/*   56:     */  
/*   57:     */  public static final short HULL_ID = 5;
/*   58:     */  
/*   59:     */  public static final short GLASS_ID = 63;
/*   60:     */  
/*   61:     */  public static final short THRUSTER_ID = 8;
/*   62:     */  
/*   63:     */  public static final short TURRET_DOCK_ID = 7;
/*   64:     */  
/*   65:     */  public static final short TURRET_DOCK_ENHANCE_ID = 88;
/*   66:     */  
/*   67:     */  public static final short POWER_ID = 2;
/*   68:     */  
/*   69:     */  public static final short POWER_CAP_ID = 331;
/*   70:     */  
/*   71:     */  public static final short SHIELD_ID = 3;
/*   72:     */  
/*   73:     */  public static final short EXPLOSIVE_ID = 14;
/*   74:     */  
/*   75:     */  public static final short RADAR_JAMMING_ID = 15;
/*   76:     */  
/*   77:     */  public static final short CLOAKING_ID = 22;
/*   78:     */  
/*   79:     */  public static final short SALVAGE_ID = 24;
/*   80:     */  
/*   81:     */  public static final short MISSILE_DUMB_CONTROLLER_ID = 38;
/*   82:     */  
/*   83:     */  public static final short MISSILE_DUMB_ID = 32;
/*   84:     */  
/*   85:     */  public static final short MISSILE_HEAT_CONTROLLER_ID = 46;
/*   86:     */  
/*   87:     */  public static final short MISSILE_HEAT_ID = 40;
/*   88:     */  
/*   89:     */  public static final short MISSILE_FAFO_CONTROLLER_ID = 54;
/*   90:     */  
/*   91:     */  public static final short MISSILE_FAFO_ID = 48;
/*   92:     */  
/*   93:     */  public static final short SALVAGE_CONTROLLER_ID = 4;
/*   94:     */  
/*   95:     */  public static final short GRAVITY_ID = 56;
/*   96:     */  
/*   97:     */  public static final short REPAIR_ID = 30;
/*   98:     */  public static final short REPAIR_CONTROLLER_ID = 39;
/*   99:     */  public static final short COCKPIT_ID = 47;
/*  100:     */  public static final short LIGHT_ID = 55;
/*  101:     */  public static final short LIGHT_BEACON_ID = 62;
/*  102:     */  public static final short TERRAIN_ICE_ID = 64;
/*  103:     */  public static final short HULL_COLOR_PURPLE_ID = 69;
/*  104:     */  public static final short HULL_COLOR_BROWN_ID = 70;
/*  105:     */  public static final short HULL_COLOR_BLACK_ID = 75;
/*  106:     */  public static final short HULL_COLOR_RED_ID = 76;
/*  107:     */  public static final short HULL_COLOR_BLUE_ID = 77;
/*  108:     */  public static final short HULL_COLOR_GREEN_ID = 78;
/*  109:     */  public static final short HULL_COLOR_YELLOW_ID = 79;
/*  110:     */  public static final short LANDING_ELEMENT = 112;
/*  111:     */  public static final short LIFT_ELEMENT = 113;
/*  112:     */  public static final short RECYCLER_ELEMENT = 114;
/*  113:     */  public static final short STASH_ELEMENT = 120;
/*  114:     */  public static final short AI_ELEMENT = 121;
/*  115:     */  public static final short DOOR_ELEMENT = 122;
/*  116:     */  public static final short BUILD_BLOCK_ID = 123;
/*  117:     */  public static final short TERRAIN_LAVA_ID = 80;
/*  118:     */  public static final short TERRAIN_GOLD_ID = 128;
/*  119:     */  public static final short TERRAIN_IRIDIUM_ID = 129;
/*  120:     */  public static final short TERRAIN_MERCURY_ID = 130;
/*  121:     */  public static final short TERRAIN_PALLADIUM_ID = 131;
/*  122:     */  public static final short TERRAIN_PLATINUM_ID = 132;
/*  123:     */  public static final short TERRAIN_LITHIUM_ID = 133;
/*  124:     */  public static final short TERRAIN_MAGNESIUM_ID = 134;
/*  125:     */  public static final short TERRAIN_TITANIUM_ID = 135;
/*  126:     */  public static final short TERRAIN_URANIUM_ID = 136;
/*  127:     */  public static final short TERRAIN_POLONIUM_ID = 137;
/*  128:     */  public static final short TERRAIN_EXTRANIUM_ID = 72;
/*  129:     */  public static final short TERRAIN_INSANIUNM_ID = 210;
/*  130:     */  public static final short TERRAIN_METATE_ID = 209;
/*  131:     */  public static final short TERRAIN_NEGAGATE_ID = 208;
/*  132:     */  public static final short TERRAIN_QUANTACIDE_ID = 207;
/*  133:     */  public static final short TERRAIN_NEGACIDE_ID = 206;
/*  134:     */  public static final short TERRAIN_MARS_TOP = 138;
/*  135:     */  public static final short TERRAIN_MARS_ROCK = 139;
/*  136:     */  public static final short TERRAIN_MARS_DIRT = 140;
/*  137:     */  public static final short TERRAIN_ROCK_ID = 73;
/*  138:     */  public static final short TERRAIN_SAND_ID = 74;
/*  139:     */  public static final short TERRAIN_EARTH_TOP_DIRT = 82;
/*  140:     */  public static final short TERRAIN_EARTH_TOP_ROCK = 83;
/*  141:     */  public static final short TERRAIN_TREE_TRUNK_ID = 84;
/*  142:     */  public static final short TERRAIN_TREE_LEAF_ID = 85;
/*  143:     */  public static final short TERRAIN_WATER = 86;
/*  144:     */  public static final short TERRAIN_DIRT_ID = 87;
/*  145:     */  public static final short TERRAIN_VINES_ID = 85;
/*  146:     */  public static final short TERRAIN_CACTUS_ID = 89;
/*  147:     */  public static final short TERRAIN_PURPLE_ALIEN_TOP = 90;
/*  148:     */  public static final short TERRAIN_PURPLE_ALIEN_ROCK = 91;
/*  149:     */  public static final short TERRAIN_PURPLE_ALIEN_VINE = 92;
/*  150:     */  public static final short WATER = 86;
/*  151:     */  public static final short PLAYER_SPAWN_MODULE = 94;
/*  152:     */  public static final short TERRAIN_GRASS_SPRITE = 93;
/*  153:     */  public static final short TERRAIN_GRASSFLOWERS_SPRITE = 98;
/*  154:     */  public static final short TERRAIN_TALLGRASSFLOWERS_SPRITE = 102;
/*  155:     */  public static final short TERRAIN_TALLFLOWERS_SPRITE = 106;
/*  156:     */  public static final short TERRAIN_BROWNWEED_SPRITE = 95;
/*  157:     */  public static final short TERRAIN_MINICACTUS_SPRITE = 103;
/*  158:     */  public static final short TERRAIN_LONGWEED_SPRITE = 99;
/*  159:     */  public static final short TERRAIN_ROCK_SPRITE = 107;
/*  160:     */  public static final short TERRAIN_MARSTENTACLES_SPRITE = 96;
/*  161:     */  public static final short TERRAIN_REDSHROOM_SPRITE = 104;
/*  162:     */  public static final short TERRAIN_TALLSHROOM_SPRITE = 100;
/*  163:     */  public static final short TERRAIN_ALIENFLOWERS_SPRITE = 108;
/*  164:     */  public static final short TERRAIN_ALIENVINE_SPRITE = 97;
/*  165:     */  public static final short TERRAIN_PURSPIRE_SPRITE = 101;
/*  166:     */  public static final short TERRAIN_PURPTACLES_SPRITE = 105;
/*  167:     */  public static final short TERRAIN_YHOLE_SPRITE = 109;
/*  168:     */  public static final short FACTORY_INPUT_ID = 211;
/*  169:     */  public static final short FACTORY_INPUT_ENH_ID = 212;
/*  170:     */  public static final short FACTORY_POWER_CELL_ID = 213;
/*  171:     */  public static final short FACTORY_POWER_CELL_ENH_ID = 214;
/*  172:     */  public static final short FACTORY_POWER_COIL_ID = 215;
/*  173:     */  public static final short FACTORY_POWER_COIL_ENH_ID = 216;
/*  174:     */  public static final short FACTORY_POWER_BLOCK_ID = 217;
/*  175:     */  public static final short FACTORY_POWER_BLOCK_ENH_ID = 218;
/*  176:     */  public static final short TERRAIN_ICEPLANET_SURFACE = 274;
/*  177:     */  public static final short TERRAIN_ICEPLANET_ROCK = 275;
/*  178:     */  public static final short TERRAIN_ICEPLANET_WOOD = 276;
/*  179:     */  public static final short TERRAIN_ICEPLANET_LEAVES = 277;
/*  180:     */  public static final short TERRAIN_ICEPLANET_SPIKE_SPRITE = 278;
/*  181:     */  public static final short TERRAIN_ICEPLANET_ICECRAG_SPRITE = 279;
/*  182:     */  public static final short TERRAIN_ICEPLANET_ICECORAL_SPRITE = 280;
/*  183:     */  public static final short TERRAIN_ICEPLANET_ICEGRASS_SPRITE = 281;
/*  184:     */  public static final short LIGHT_RED = 282;
/*  185:     */  public static final short LIGHT_BLUE = 283;
/*  186:     */  public static final short LIGHT_GREEN = 284;
/*  187:     */  public static final short LIGHT_YELLOW = 285;
/*  188:     */  public static final short TERRAIN_ICEPLANET_CRYSTAL = 286;
/*  189:     */  public static final short TERRAIN_REDWOOD = 287;
/*  190:     */  public static final short TERRAIN_REDWOOD_LEAVES = 288;
/*  191:     */  public static final short FIXED_DOCK_ID = 289;
/*  192:     */  public static final short FIXED_DOCK_ID_ENHANCER = 290;
/*  193:     */  public static final short FACTION_BLOCK = 291;
/*  194:     */  public static final short FACTION_HUB_BLOCK = 292;
/*  195:     */  public static final short POWER_CELL = 219;
/*  196:     */  public static final short POWER_COIL = 220;
/*  197:     */  public static final short POWER_DRAIN_BEAM_COMPUTER = 332;
/*  198:     */  public static final short POWER_DRAIN_BEAM_MODULE = 333;
/*  199:     */  public static final short POWER_SUPPLY_BEAM_COMPUTER = 334;
/*  200:     */  public static final short POWER_SUPPLY_BEAM_MODULE = 335;
/*  201:     */  public static boolean initialized;
/*  202: 202 */  private static final HashSet factoryKeySet = new HashSet(256);
/*  203: 203 */  public static final HashSet keySet = new HashSet(256);
/*  204:     */  private static short[] keyArray;
/*  205: 205 */  private static final HashSet leveldKeySet = new HashSet(256);
/*  206: 206 */  private static final HashMap levelMap = new HashMap();
/*  207:     */  
/*  208:     */  private static ElementCategory categoryHirarchy;
/*  209:     */  
/*  210:     */  public static Properties properties;
/*  211:     */  
/*  212:     */  public static void reinitializeData(File paramFile, String paramString)
/*  213:     */  {
/*  214: 214 */    initialized = false;
/*  215: 215 */    categoryHirarchy = null;
/*  216: 216 */    factoryKeySet.clear();
/*  217: 217 */    keySet.clear();
/*  218: 218 */    leveldKeySet.clear();
/*  219: 219 */    levelMap.clear();
/*  220: 220 */    highestType = 0;
/*  221: 221 */    informationKeyMap.clear();
/*  222: 222 */    infoArray = null;
/*  223: 223 */    keyArray = null;
/*  224: 224 */    initializeData(paramFile, paramString);
/*  225:     */  }
/*  226:     */  
/*  227:     */  public static void initializeData() {
/*  228: 228 */    initializeData(null, null);
/*  229:     */  }
/*  230:     */  
/*  231:     */  public static void addInformationToExisting(ElementInformation paramElementInformation)
/*  232:     */  {
/*  233: 233 */    categoryHirarchy.insertRecusrive(paramElementInformation);
/*  234: 234 */    add(paramElementInformation.getId(), paramElementInformation);
/*  235: 235 */    infoArray = new ElementInformation[highestType + 1];
/*  236:     */    
/*  237: 237 */    for (Map.Entry localEntry : informationKeyMap.entrySet())
/*  238:     */    {
/*  239: 239 */      infoArray[((Short)localEntry.getKey()).shortValue()] = ((ElementInformation)localEntry.getValue());
/*  240:     */    }
/*  241: 241 */    if (factoryKeySet.contains(Short.valueOf(paramElementInformation.getId()))) {
/*  242: 242 */      paramElementInformation.getFactory().enhancer = 212;
/*  243: 243 */      paramElementInformation.getControlling().addAll(factoryKeySet);
/*  244: 244 */      paramElementInformation.getControlling().remove(Short.valueOf(paramElementInformation.getId()));
/*  245: 245 */      paramElementInformation.getControlledBy().addAll(factoryKeySet);
/*  246:     */    }
/*  247:     */  }
/*  248:     */  
/*  249:     */  public static void removeFromExisting(ElementInformation paramElementInformation)
/*  250:     */  {
/*  251: 251 */    keySet.remove(Short.valueOf(paramElementInformation.getId()));
/*  252: 252 */    informationKeyMap.remove(Short.valueOf(paramElementInformation.getId()));
/*  253: 253 */    highestType = 0;
/*  254: 254 */    for (Object localObject1 = keySet.iterator(); ((Iterator)localObject1).hasNext();) { int i = ((Short)((Iterator)localObject1).next()).shortValue();
/*  255: 255 */      highestType = Math.max(highestType, i);
/*  256:     */    }
/*  257:     */    
/*  258: 258 */    factoryKeySet.remove(Short.valueOf(paramElementInformation.getId()));
/*  259:     */    
/*  260: 260 */    getLeveldkeyset().remove(Short.valueOf(paramElementInformation.getId()));
/*  261:     */    Object localObject2;
/*  262: 262 */    Iterator localIterator; Object localObject3; if (paramElementInformation.isLeveled()) {
/*  263: 263 */      localObject1 = (HashMap)levelMap.get(Short.valueOf(paramElementInformation.getLevel().getIdBase()));
/*  264: 264 */      localObject2 = new HashSet();
/*  265: 265 */      if (localObject1 != null) {
/*  266: 266 */        for (localIterator = ((HashMap)localObject1).entrySet().iterator(); localIterator.hasNext();) {
/*  267: 267 */          if (((Short)(localObject3 = (Map.Entry)localIterator.next()).getValue()).shortValue() == paramElementInformation.getId()) {
/*  268: 268 */            ((Set)localObject2).add(((Map.Entry)localObject3).getKey());
/*  269:     */          }
/*  270:     */        }
/*  271: 271 */        for (localIterator = ((Set)localObject2).iterator(); localIterator.hasNext();) { localObject3 = (Integer)localIterator.next();
/*  272: 272 */          ((HashMap)localObject1).remove(localObject3);
/*  273:     */        }
/*  274:     */      }
/*  275:     */    }
/*  276: 276 */    levelMap.remove(Short.valueOf(paramElementInformation.getId()));
/*  277:     */    
/*  278: 278 */    infoArray = new ElementInformation[highestType + 1];
/*  279:     */    
/*  280: 280 */    for (localObject1 = informationKeyMap.entrySet().iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/*  281: 281 */      infoArray[((Short)localObject2.getKey()).shortValue()] = ((ElementInformation)((Map.Entry)localObject2).getValue());
/*  282:     */    }
/*  283: 283 */    categoryHirarchy.removeRecursive(paramElementInformation);
/*  284:     */  }
/*  285:     */  
/*  286:     */  private static void initElements(ArrayList paramArrayList, ElementCategory paramElementCategory) {
/*  287: 287 */    for (paramArrayList = paramArrayList.iterator(); paramArrayList.hasNext();)
/*  288: 288 */      add((localObject = (ElementInformation)paramArrayList.next()).getId(), (ElementInformation)localObject);
/*  289:     */    Object localObject;
/*  290: 290 */    categoryHirarchy = paramElementCategory;
/*  291:     */    
/*  292: 292 */    infoArray = new ElementInformation[highestType + 1];
/*  293:     */    
/*  294: 294 */    for (paramArrayList = informationKeyMap.entrySet().iterator(); paramArrayList.hasNext();) { localObject = (Map.Entry)paramArrayList.next();
/*  295: 295 */      infoArray[((Short)localObject.getKey()).shortValue()] = ((ElementInformation)((Map.Entry)localObject).getValue());
/*  296:     */    }
/*  297:     */    
/*  299: 299 */    for (paramArrayList = factoryKeySet.iterator(); paramArrayList.hasNext();) { short s;
/*  300: 300 */      getInfo(s = ((Short)paramArrayList.next()).shortValue()).getFactory().enhancer = (getInfo(s).getControlling().size() > 0 ? ((Short)getInfo(s).getControlling().iterator().next()).shortValue() : 212);
/*  301: 301 */      getInfo(s).getControlling().addAll(factoryKeySet);
/*  302: 302 */      getInfo(s).getControlling().remove(Short.valueOf(s));
/*  303: 303 */      getInfo(s).getControlledBy().addAll(factoryKeySet);
/*  304:     */    }
/*  305: 305 */    initialized = true;
/*  306:     */  }
/*  307:     */  
/*  308: 308 */  public static void initializeData(File paramFile, String paramString) { for (;;) { if (initialized) {
/*  309: 309 */        return;
/*  310:     */      }
/*  311:     */      
/*  314:     */      try
/*  315:     */      {
/*  316: 316 */        initElements((paramFile = load(paramFile, paramString)).getInfoElements(), paramFile.getRootCategory());
/*  317: 317 */      } catch (Exception localException) { d.a(
/*  318:     */        
/*  321: 321 */          localException);paramString = null;
/*  322:     */      }
/*  323: 319 */      paramFile = null; }
/*  324: 320 */    keyArray = new short[keySet.size()];
/*  325:     */    
/*  330: 326 */    paramFile = 0;
/*  331: 327 */    for (paramString = keySet.iterator(); paramString.hasNext();) { int i = ((Short)paramString.next()).shortValue();
/*  332: 328 */      keyArray[paramFile] = i;
/*  333: 329 */      paramFile++;
/*  334:     */    }
/*  335:     */  }
/*  336:     */  
/*  337:     */  private static void add(short paramShort, ElementInformation paramElementInformation) {
/*  338: 334 */    if (keySet.contains(Short.valueOf(paramShort))) {
/*  339: 335 */      throw new ParserConfigurationException("Duplicate Block ID " + paramShort + " (" + paramElementInformation.getName() + " and " + ((ElementInformation)informationKeyMap.get(Short.valueOf(paramShort))).getName() + ")");
/*  340:     */    }
/*  341: 337 */    keySet.add(Short.valueOf(paramShort));
/*  342: 338 */    informationKeyMap.put(Short.valueOf(paramShort), paramElementInformation);
/*  343: 339 */    highestType = Math.max(highestType, paramShort);
/*  344:     */    
/*  345: 341 */    if (paramElementInformation.getFactory() != null) {
/*  346: 342 */      factoryKeySet.add(Short.valueOf(paramShort));
/*  347:     */    }
/*  348:     */    
/*  349: 345 */    if (paramElementInformation.isLeveled()) {
/*  350: 346 */      getLeveldkeyset().add(Short.valueOf(paramShort));
/*  351:     */      HashMap localHashMap;
/*  352: 348 */      if ((localHashMap = (HashMap)levelMap.get(Short.valueOf(paramElementInformation.getLevel().getIdBase()))) == null) {
/*  353: 349 */        localHashMap = new HashMap();
/*  354: 350 */        levelMap.put(Short.valueOf(paramElementInformation.getLevel().getIdBase()), localHashMap);
/*  355:     */      }
/*  356: 352 */      localHashMap.put(Integer.valueOf(paramElementInformation.getLevel().getLevel()), Short.valueOf(paramShort));
/*  357:     */    }
/*  358:     */  }
/*  359:     */  
/*  360:     */  public static short getLevel(short paramShort, int paramInt) {
/*  361: 357 */    if (((HashMap)levelMap.get(Short.valueOf(paramShort))).get(Integer.valueOf(paramInt)) == null) {
/*  362: 358 */      return -1;
/*  363:     */    }
/*  364: 360 */    return ((Short)((HashMap)levelMap.get(Short.valueOf(paramShort))).get(Integer.valueOf(paramInt))).shortValue();
/*  365:     */  }
/*  366:     */  
/*  367:     */  public static void addShipHull(short paramShort1, int paramInt, String paramString, short paramShort2) {
/*  368: 364 */    (paramString = new ElementInformation(paramShort1, "Hull " + paramString, GeneralElement.class, paramShort2)).setPrice(500L);
/*  369: 365 */    paramString.setAmour(50);
/*  370: 366 */    paramString.setBuildIconNum(paramInt);
/*  371: 367 */    paramString.setShoppable(true);
/*  372: 368 */    if (paramShort1 == 63) {
/*  373: 369 */      paramString.setBlended(true);
/*  374:     */    }
/*  375: 371 */    paramString.setDescription(formatDescString("The Hull Element\n\nA cheap amoured Element to give the ship structure.\nIt should be used to protect more important elements.\n\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
/*  376:     */    
/*  387: 383 */    add(paramShort1, paramString);
/*  388:     */  }
/*  389:     */  
/*  390:     */  public static String formatDescString(String paramString)
/*  391:     */  {
/*  392: 388 */    paramString = new StringBuffer(paramString);
/*  393:     */    
/*  394: 390 */    int i = 0;
/*  395: 391 */    for (int j = 0; j < paramString.length() - 1; 
/*  396: 392 */        j++) {
/*  397: 393 */      if (paramString.charAt(j) == '\n') {
/*  398: 394 */        i = 0;
/*  399: 395 */        j++;
/*  400:     */      }
/*  401:     */      
/*  403: 399 */      if (i > 50) {
/*  404: 400 */        while ((j > 0) && (paramString.charAt(j) != ' ')) {
/*  405: 401 */          j--;
/*  406:     */        }
/*  407: 403 */        paramString.deleteCharAt(j);
/*  408: 404 */        paramString.insert(j, "\n");
/*  409: 405 */        j++;
/*  410:     */        
/*  411: 407 */        i = 0;
/*  412:     */      }
/*  413:     */      
/*  415: 411 */      i++;
/*  416:     */    }
/*  417:     */    
/*  419: 415 */    return paramString.toString();
/*  420:     */  }
/*  421:     */  
/*  423:     */  public static ElementCategory getCategoryHirarchy()
/*  424:     */  {
/*  425: 421 */    return categoryHirarchy;
/*  426:     */  }
/*  427:     */  
/*  428:     */  public static ElementInformation getInfo(short paramShort) {
/*  429: 425 */    assert (infoArray[paramShort] != null) : ("type " + paramShort + " unknown, please check the properties and the xml");
/*  430:     */    ElementInformation localElementInformation;
/*  431: 427 */    if ((localElementInformation = infoArray[paramShort]) == null) {
/*  432: 428 */      throw new NullPointerException("Exception: REQUESTED TYPE " + paramShort + " IS NULL");
/*  433:     */    }
/*  434: 430 */    return localElementInformation;
/*  435:     */  }
/*  436:     */  
/*  437: 433 */  public static boolean exists(short paramShort) { return (paramShort > 0) && (paramShort < infoArray.length) && (infoArray[paramShort] != null); }
/*  438:     */  
/*  439:     */  public static void initializeDeathStarData()
/*  440:     */  {
/*  441:     */    ElementInformation localElementInformation;
/*  442: 438 */    (localElementInformation = new ElementInformation((short)65, "Deathstar Core", DeathStarElement.class, (short)65)).setAmour(30);
/*  443: 439 */    localElementInformation.setMaxHitPoints((short)1000);
/*  444: 440 */    add((short)65, localElementInformation);
/*  445:     */  }
/*  446:     */  
/*  447:     */  public static void initializeShipData() {
/*  448:     */    ElementInformation localElementInformation;
/*  449: 445 */    (localElementInformation = new ElementInformation((short)6, "Weapon Controller", ShipElement.class, (short)6)).setPrice(300L);
/*  450: 446 */    localElementInformation.setAmour(5);
/*  451: 447 */    localElementInformation.getControlling().add(Short.valueOf((short)16));
/*  452: 448 */    localElementInformation.setBuildIconNum(10);
/*  453: 449 */    localElementInformation.setShoppable(true);
/*  454: 450 */    localElementInformation.setEnterable(true);
/*  455: 451 */    localElementInformation.setDescription(formatDescString("The Weapon Controller Element\n\nThis element is used to control weapon\n elements of a Ship.\n\nWeapons have to be connected to the\nweapon controller in order for them to work.\nWeapon controllers can be entered by\nindividuals to crew the ship\n\n\nCOST:          300\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      WeaponElement\nCONTROLLED BY: Core"));
/*  456:     */    
/*  470: 466 */    add((short)6, localElementInformation);
/*  471:     */    
/*  472: 468 */    (
/*  473: 469 */      localElementInformation = new ElementInformation((short)1, "Core", ShipElement.class, (short)1)).setPrice(10000L);
/*  474: 470 */    localElementInformation.setBuildIconNum(1);
/*  475: 471 */    localElementInformation.setAmour(20);
/*  476: 472 */    localElementInformation.setMaxHitPoints((short)500);
/*  477: 473 */    localElementInformation.setShoppable(true);
/*  478: 474 */    localElementInformation.setEnterable(true);
/*  479: 475 */    localElementInformation.getControlling().add(Short.valueOf((short)16));
/*  480: 476 */    localElementInformation.getControlling().add(Short.valueOf((short)8));
/*  481: 477 */    localElementInformation.getControlling().add(Short.valueOf((short)22));
/*  482: 478 */    localElementInformation.getControlling().add(Short.valueOf((short)15));
/*  483: 479 */    localElementInformation.setDescription(formatDescString("The Core Element\n\nThis element is the most important element of a ship\nIt can only be placed, when spawning a new ship,\nand cannot be destroyed or removed!\n\nIf a core element is damaged enough, the individual\ninside the element will be catapulted outside of the ship\nCore controllers can be entered by\nindividuals to crew the ship\n\n\nCOST:          10000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      WeaponController, HarvestController, Thrusters\nCONTROLLED BY: -"));
/*  484:     */    
/*  499: 495 */    add((short)1, localElementInformation);
/*  500:     */    
/*  501: 497 */    addShipHull((short)5, 5, "grey", (short)5);
/*  502: 498 */    addShipHull((short)75, 33, "black", (short)75);
/*  503: 499 */    addShipHull((short)76, 34, "red", (short)76);
/*  504: 500 */    addShipHull((short)77, 35, "blue", (short)77);
/*  505: 501 */    addShipHull((short)79, 36, "yellow", (short)79);
/*  506: 502 */    addShipHull((short)78, 37, "green", (short)78);
/*  507: 503 */    addShipHull((short)70, 39, "brown", (short)70);
/*  508: 504 */    addShipHull((short)69, 38, "purple", (short)69);
/*  509:     */    
/*  511: 507 */    addShipHull((short)63, 40, "glass", (short)63);
/*  512:     */    
/*  513: 509 */    (
/*  514: 510 */      localElementInformation = new ElementInformation((short)47, "Cockpit", ShipElement.class, (short)47)).setPrice(500L);
/*  515: 511 */    localElementInformation.setAmour(10);
/*  516: 512 */    localElementInformation.setBuildIconNum(30);
/*  517: 513 */    localElementInformation.setShoppable(true);
/*  518: 514 */    localElementInformation.setDescription(formatDescString("The Cockpit Element\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
/*  519:     */    
/*  529: 525 */    add((short)47, localElementInformation);
/*  530:     */    
/*  532: 528 */    (
/*  533: 529 */      localElementInformation = new ElementInformation((short)55, "Light", ShipElement.class, (short)55)).setPrice(500L);
/*  534: 530 */    localElementInformation.setBuildIconNum(31);
/*  535: 531 */    localElementInformation.setLightSource(true);
/*  536: 532 */    localElementInformation.setShoppable(true);
/*  537: 533 */    localElementInformation.getLightSourceColor().set(1.0F, 1.0F, 0.23F);
/*  538: 534 */    localElementInformation.setDescription(formatDescString("The Light Element\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
/*  539:     */    
/*  549: 545 */    add((short)55, localElementInformation);
/*  550:     */    
/*  551: 547 */    (
/*  552: 548 */      localElementInformation = new ElementInformation((short)62, "LightBeacon", ShipElement.class, (short)62)).setPrice(1000L);
/*  553: 549 */    localElementInformation.setBuildIconNum(32);
/*  554: 550 */    localElementInformation.setShoppable(true);
/*  555: 551 */    localElementInformation.setLightSource(true);
/*  556: 552 */    localElementInformation.setBlended(true);
/*  557: 553 */    localElementInformation.setDescription(formatDescString("The LightBeacon Element\n\nCOST:          1000\nRANGE:         -\nHP:         \t100\nAMOUR:         50\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -"));
/*  558:     */    
/*  568: 564 */    add((short)62, localElementInformation);
/*  569:     */    
/*  570: 566 */    (
/*  571: 567 */      localElementInformation = new ElementInformation((short)8, "Thruster", ShipElement.class, (short)8)).setPrice(300L);
/*  572: 568 */    localElementInformation.setBuildIconNum(13);
/*  573: 569 */    localElementInformation.setIndividualSides(6);
/*  574: 570 */    localElementInformation.setShoppable(true);
/*  575: 571 */    localElementInformation.setOrientatable(true);
/*  576: 572 */    localElementInformation.setDescription(formatDescString("The Thruster Element\n\nThe basic thruster elements to get ships moving.\nThe more elements are present in a ship, \nthe faster the ship will go relative to its mass.\n\n\nCOST:          300\nRANGE:         -\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Core"));
/*  577:     */    
/*  589: 585 */    add((short)8, localElementInformation);
/*  590:     */    
/*  591: 587 */    (
/*  592: 588 */      localElementInformation = new ElementInformation((short)3, "Shield Generator", ShipElement.class, (short)3)).setPrice(3000L);
/*  593: 589 */    localElementInformation.setBuildIconNum(8);
/*  594: 590 */    localElementInformation.setShoppable(true);
/*  595: 591 */    localElementInformation.setDescription(formatDescString("The Shield Generator Element\n\nThis element provides a forcefield against Energy Weapons.\nOnce, its not been hit for 60 seconds, it will automatically recharge. \nThe basic radius of the forcefield is fairly small,\nbut this is a groupable Element: the more elements are\ntouching each other, the bigger and stronger the forcefield will become.\n\n\nCOST:          3000\nRANGE:         -\nHP:         \t0\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: -"));
/*  596:     */    
/*  610: 606 */    add((short)3, localElementInformation);
/*  611:     */    
/*  613: 609 */    (
/*  614: 610 */      localElementInformation = new ElementInformation((short)16, "Weapon", ShipElement.class, (short)16)).setPrice(500L);
/*  615: 611 */    localElementInformation.setIndividualSides(6);
/*  616: 612 */    localElementInformation.setOrientatable(true);
/*  617: 613 */    localElementInformation.setBuildIconNum(9);
/*  618: 614 */    localElementInformation.setShoppable(true);
/*  619: 615 */    localElementInformation.setDescription(formatDescString("The Weapon Element\n\nThis is a basic weapon Element.\nIt does 1 unit of damage. \nThis is a groupable Element: \nyou can add elements to build a larger more efficient weapon \nMore WIDTH means MORE DAMAGE.\nMore HEIGHT means LESS RELOAD TIME.\nMore DEPTH means MORE SPEED.\n\n\n\nCOST:          500\nRANGE:         -\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: WeaponController"));
/*  620:     */    
/*  637: 633 */    add((short)16, localElementInformation);
/*  638:     */    
/*  639: 635 */    (
/*  640: 636 */      localElementInformation = new ElementInformation((short)2, "Battery", ShipElement.class, (short)2)).setPrice(300L);
/*  641: 637 */    localElementInformation.setBuildIconNum(11);
/*  642: 638 */    localElementInformation.setShoppable(true);
/*  643: 639 */    localElementInformation.setDescription(formatDescString("The Power Generator Element\n\nThis Element provides power for all Elements,\nthat are in range. \n\n\n\nCOST:          300\nRANGE:         5\nHP:         \t100\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -\n"));
/*  644:     */    
/*  656: 652 */    add((short)2, localElementInformation);
/*  657:     */    
/*  659: 655 */    (
/*  660: 656 */      localElementInformation = new ElementInformation((short)14, "Explosive Module", ShipElement.class, (short)14)).setPrice(500L);
/*  661: 657 */    localElementInformation.setBuildIconNum(23);
/*  662: 658 */    localElementInformation.setShoppable(true);
/*  663: 659 */    localElementInformation.setDescription(formatDescString("The Explosive Element\n\nThis is a very dangerous Element\nOnce you connect it to the core,\nit will detonate in a huge balst\non impact."));
/*  664:     */    
/*  671: 667 */    add((short)14, localElementInformation);
/*  672:     */    
/*  673: 669 */    (
/*  674: 670 */      localElementInformation = new ElementInformation((short)22, "Cloaking Module", ShipElement.class, (short)22)).setPrice(1000L);
/*  675: 671 */    localElementInformation.setBuildIconNum(22);
/*  676: 672 */    localElementInformation.setShoppable(true);
/*  677: 673 */    localElementInformation.setDescription(formatDescString("The Cloaking Module\n\nThis Module enables you be invisibe \nfrom other players for a short\ntime.\n\nGrouping these modules will increase cloking time"));
/*  678:     */    
/*  684: 680 */    add((short)22, localElementInformation);
/*  685:     */    
/*  686: 682 */    (
/*  687: 683 */      localElementInformation = new ElementInformation((short)15, "Radar Jamming Module", ShipElement.class, (short)15)).setPrice(300L);
/*  688: 684 */    localElementInformation.setBuildIconNum(24);
/*  689: 685 */    localElementInformation.setShoppable(true);
/*  690: 686 */    localElementInformation.setDescription(formatDescString("The Radar Jamming Element\n\nThis Module enables you to vanish\nfrom other players radar for a short\ntime.\n\nGrouping these modules will increase jamming time"));
/*  691:     */    
/*  697: 693 */    add((short)15, localElementInformation);
/*  698:     */    
/*  700: 696 */    (
/*  701: 697 */      localElementInformation = new ElementInformation((short)24, "Salvage Beam", ShipElement.class, (short)24)).setPrice(700L);
/*  702: 698 */    localElementInformation.setBuildIconNum(12);
/*  703: 699 */    localElementInformation.setIndividualSides(6);
/*  704: 700 */    localElementInformation.setOrientatable(true);
/*  705: 701 */    localElementInformation.setShoppable(true);
/*  706: 702 */    localElementInformation.setDescription(formatDescString("The Salvager Beam Element\n\nThis Element can be used like a weapon.\nWith this module, you can collect elements\nfrom neutral structures. Grouping this elements\nwill decrease the salvage time per element\n\n\n\nCOST:          700\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Salvager Controller\n"));
/*  707:     */    
/*  720: 716 */    add((short)24, localElementInformation);
/*  721:     */    
/*  722: 718 */    (
/*  723: 719 */      localElementInformation = new ElementInformation((short)4, "Salvage Controller", ShipElement.class, (short)4)).setPrice(1000L);
/*  724: 720 */    localElementInformation.setBuildIconNum(14);
/*  725: 721 */    localElementInformation.setShoppable(true);
/*  726: 722 */    localElementInformation.setEnterable(true);
/*  727: 723 */    localElementInformation.getControlling().add(Short.valueOf((short)24));
/*  728: 724 */    localElementInformation.setDescription(formatDescString("The Salvager Beam Controller Element\n\nThis element is use like the weapon controller,\nwith the difference that it controls salvage elements\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Salvager\nCONTROLLED BY: -\n"));
/*  729:     */    
/*  740: 736 */    add((short)4, localElementInformation);
/*  741:     */    
/*  743: 739 */    (
/*  744: 740 */      localElementInformation = new ElementInformation((short)30, "Repair Beam", ShipElement.class, (short)30)).setPrice(700L);
/*  745: 741 */    localElementInformation.setBuildIconNum(26);
/*  746: 742 */    localElementInformation.setShoppable(true);
/*  747: 743 */    localElementInformation.setDescription(formatDescString("The Repair Beam Element\n\nThis Element can be used like a weapon.\nWith this module, you can repair elements\nfrom other structures. Grouping this elements\nwill decrease the repair time per element\n\n\n\nCOST:          700\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   YES\nCONTROLS:      -\nCONTROLLED BY: Repair Controller\n"));
/*  748:     */    
/*  761: 757 */    add((short)30, localElementInformation);
/*  762:     */    
/*  763: 759 */    (
/*  764: 760 */      localElementInformation = new ElementInformation((short)39, "Repair Controller", ShipElement.class, (short)39)).setPrice(1000L);
/*  765: 761 */    localElementInformation.setBuildIconNum(27);
/*  766: 762 */    localElementInformation.setShoppable(true);
/*  767: 763 */    localElementInformation.setEnterable(true);
/*  768: 764 */    localElementInformation.getControlling().add(Short.valueOf((short)30));
/*  769: 765 */    localElementInformation.setDescription(formatDescString("The repair Beam Controller Module\n\nThis element is use like the weapon controller,\nwith the difference that it controls repair elements\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      repair\nCONTROLLED BY: -\n"));
/*  770:     */    
/*  781: 777 */    add((short)39, localElementInformation);
/*  782:     */    
/*  783: 779 */    (
/*  784: 780 */      localElementInformation = new ElementInformation((short)7, "Docking Station", ShipElement.class, (short)7)).setPrice(500L);
/*  785: 781 */    localElementInformation.setBuildIconNum(15);
/*  786: 782 */    localElementInformation.setShoppable(true);
/*  787: 783 */    localElementInformation.setDescription(formatDescString("The Dock Element\n\nYou can dock other ships here,\n\n\n\nCOST:          500\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: -\n"));
/*  788:     */    
/*  798: 794 */    add((short)7, localElementInformation);
/*  799:     */    
/*  802: 798 */    (
/*  803: 799 */      localElementInformation = new ElementInformation((short)38, "Dumb Missile Controller", ShipElement.class, (short)38)).setPrice(1000L);
/*  804: 800 */    localElementInformation.setBuildIconNum(16);
/*  805: 801 */    localElementInformation.setShoppable(true);
/*  806: 802 */    localElementInformation.getControlling().add(Short.valueOf((short)32));
/*  807: 803 */    localElementInformation.setEnterable(true);
/*  808: 804 */    localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
/*  809:     */    
/*  819: 815 */    add((short)38, localElementInformation);
/*  820:     */    
/*  821: 817 */    (
/*  822: 818 */      localElementInformation = new ElementInformation((short)32, "Dumb Missile Module", ShipElement.class, (short)32)).setPrice(800L);
/*  823: 819 */    localElementInformation.setBuildIconNum(17);
/*  824: 820 */    localElementInformation.setShoppable(true);
/*  825: 821 */    localElementInformation.setIndividualSides(6);
/*  826: 822 */    localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          800\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
/*  827:     */    
/*  835: 831 */    add((short)32, localElementInformation);
/*  836:     */    
/*  840: 836 */    (
/*  841: 837 */      localElementInformation = new ElementInformation((short)46, "Heat Seeking Missile Controller", ShipElement.class, (short)46)).setPrice(1200L);
/*  842: 838 */    localElementInformation.setBuildIconNum(18);
/*  843: 839 */    localElementInformation.setShoppable(true);
/*  844: 840 */    localElementInformation.setEnterable(true);
/*  845: 841 */    localElementInformation.getControlling().add(Short.valueOf((short)40));
/*  846: 842 */    localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1200\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
/*  847:     */    
/*  857: 853 */    add((short)46, localElementInformation);
/*  858:     */    
/*  859: 855 */    (
/*  860: 856 */      localElementInformation = new ElementInformation((short)40, "Heat Seeking Missile", ShipElement.class, (short)40)).setPrice(1000L);
/*  861: 857 */    localElementInformation.setBuildIconNum(19);
/*  862: 858 */    localElementInformation.setShoppable(true);
/*  863: 859 */    localElementInformation.setIndividualSides(6);
/*  864: 860 */    localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          1000\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
/*  865:     */    
/*  873: 869 */    add((short)40, localElementInformation);
/*  874:     */    
/*  879: 875 */    (
/*  880: 876 */      localElementInformation = new ElementInformation((short)54, "Fire and forget Missile Controller", ShipElement.class, (short)54)).setPrice(1500L);
/*  881: 877 */    localElementInformation.setBuildIconNum(20);
/*  882: 878 */    localElementInformation.setShoppable(true);
/*  883: 879 */    localElementInformation.setEnterable(true);
/*  884: 880 */    localElementInformation.getControlling().add(Short.valueOf((short)48));
/*  885: 881 */    localElementInformation.setDescription(formatDescString("The Missile Controller\n\nNeeded for missiles,\n\n\n\nCOST:          1500\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      Missiles\nCONTROLLED BY: Core\n"));
/*  886:     */    
/*  896: 892 */    add((short)54, localElementInformation);
/*  897:     */    
/*  898: 894 */    (
/*  899: 895 */      localElementInformation = new ElementInformation((short)48, "Fire and forget Missile", ShipElement.class, (short)48)).setPrice(1300L);
/*  900: 896 */    localElementInformation.setBuildIconNum(21);
/*  901: 897 */    localElementInformation.setShoppable(true);
/*  902: 898 */    localElementInformation.setIndividualSides(6);
/*  903: 899 */    localElementInformation.setDescription(formatDescString("The Missile Module\n\nCOST:          1300\nRANGE:         -\nAMOUR:         0\nNEEDS POWER:   NO\nCONTROLS:      -\nCONTROLLED BY: Missile Control Module"));
/*  904:     */    
/*  912: 908 */    add((short)48, localElementInformation);
/*  913:     */  }
/*  914:     */  
/*  916:     */  public static void initializeSpaceStationData()
/*  917:     */  {
/*  918:     */    ElementInformation localElementInformation;
/*  919: 915 */    (localElementInformation = new ElementInformation((short)112, "Landing Module", SpaceStationElement.class, (short)112)).setBuildIconNum(41);
/*  920: 916 */    localElementInformation.setShoppable(true);
/*  921: 917 */    localElementInformation.setPrice(10L);
/*  922: 918 */    localElementInformation.setDescription(formatDescString("The Landing Module\n\naffixes any ship to this module.make sure there is enough space!\n \n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*  923:     */    
/*  928: 924 */    add((short)112, localElementInformation);
/*  929:     */    
/*  931: 927 */    (
/*  932: 928 */      localElementInformation = new ElementInformation((short)113, "Lift Module", SpaceStationElement.class, (short)113)).setBuildIconNum(42);
/*  933: 929 */    localElementInformation.setShoppable(true);
/*  934: 930 */    localElementInformation.setPrice(10L);
/*  935: 931 */    localElementInformation.setDescription(formatDescString("The Lift Module\n\nit will lift up any objects\n from bottom to the top of any group\nof lift objects.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*  936:     */    
/*  941: 937 */    add((short)113, localElementInformation);
/*  942:     */    
/*  944: 940 */    (
/*  945: 941 */      localElementInformation = new ElementInformation((short)122, "Door Module", SpaceStationElement.class, (short)122)).setBuildIconNum(43);
/*  946: 942 */    localElementInformation.setShoppable(true);
/*  947: 943 */    localElementInformation.setPrice(10L);
/*  948: 944 */    localElementInformation.setDescription(formatDescString("The door module\n\nThis module will only work\n if placed on top of an opening\nuse a line of modules to \nmake the door wider.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*  949:     */    
/*  955: 951 */    add((short)122, localElementInformation);
/*  956:     */    
/*  958: 954 */    (
/*  959: 955 */      localElementInformation = new ElementInformation((short)120, "Chest Module", SpaceStationElement.class, (short)120)).setBuildIconNum(44);
/*  960: 956 */    localElementInformation.setShoppable(true);
/*  961: 957 */    localElementInformation.setPrice(10L);
/*  962: 958 */    localElementInformation.setDescription(formatDescString("The Recycler Module\n\nPlace modules inside\n to keep them save.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*  963:     */    
/*  967: 963 */    add((short)120, localElementInformation);
/*  968:     */    
/*  970: 966 */    (
/*  971: 967 */      localElementInformation = new ElementInformation((short)114, "Recycler Module", SpaceStationElement.class, (short)114)).setBuildIconNum(45);
/*  972: 968 */    localElementInformation.setShoppable(true);
/*  973: 969 */    localElementInformation.setPrice(10L);
/*  974: 970 */    localElementInformation.setDescription(formatDescString("The Recycler Module\n\nplace modules inside\n to convert them to a better module.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*  975:     */    
/*  979: 975 */    add((short)114, localElementInformation);
/*  980:     */    
/*  982: 978 */    (
/*  983: 979 */      localElementInformation = new ElementInformation((short)121, "AI Module", ShipElement.class, (short)121)).setBuildIconNum(46);
/*  984: 980 */    localElementInformation.setShoppable(true);
/*  985: 981 */    localElementInformation.setPrice(10L);
/*  986: 982 */    localElementInformation.setDescription(formatDescString("The AI Module\n\nlets you control any ship\n if it has been placed on there.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/*  987:     */    
/*  991: 987 */    add((short)121, localElementInformation);
/*  992:     */    
/*  994: 990 */    (
/*  995: 991 */      localElementInformation = new ElementInformation((short)123, "Build Block", SpaceStationElement.class, (short)123)).setBuildIconNum(47);
/*  996: 992 */    localElementInformation.setShoppable(true);
/*  997: 993 */    localElementInformation.setPrice(10L);
/*  998: 994 */    localElementInformation.setEnterable(true);
/*  999:     */    
/* 1000: 996 */    localElementInformation.setDescription(formatDescString("The Build Module\n\nthis module is used to edit \n space stations from the inside.\n\nPress " + cv.w.b() + " to activate\n\n\n"));
/* 1001:     */    
/* 1005:1001 */    add((short)123, localElementInformation);
/* 1006:     */  }
/* 1007:     */  
/* 1008:     */  public static void initializeTerrainData() {
/* 1009:     */    ElementInformation localElementInformation;
/* 1010:1006 */    (localElementInformation = new ElementInformation((short)64, "Ice Element", GeneralElement.class, (short)64)).setBuildIconNum(66);
/* 1011:1007 */    add((short)64, localElementInformation);
/* 1012:     */    
/* 1013:1009 */    (
/* 1014:1010 */      localElementInformation = new ElementInformation((short)80, "Lava Element", GeneralElement.class, (short)80)).setLightSource(true);
/* 1015:1011 */    localElementInformation.setAnimated(true);
/* 1016:1012 */    localElementInformation.getLightSourceColor().set(1.0F, 0.2F, 0.2F);
/* 1017:1013 */    localElementInformation.setBuildIconNum(68);
/* 1018:1014 */    add((short)80, localElementInformation);
/* 1019:     */    
/* 1020:1016 */    (
/* 1021:1017 */      localElementInformation = new ElementInformation((short)72, "Mineral Element", GeneralElement.class, (short)72)).setBuildIconNum(69);
/* 1022:1018 */    add((short)72, localElementInformation);
/* 1023:     */    
/* 1024:1020 */    (
/* 1025:1021 */      localElementInformation = new ElementInformation((short)73, "Rock Element", GeneralElement.class, (short)73)).setBuildIconNum(65);
/* 1026:1022 */    add((short)73, localElementInformation);
/* 1027:     */    
/* 1028:1024 */    (
/* 1029:1025 */      localElementInformation = new ElementInformation((short)74, "Sand Element", GeneralElement.class, (short)74)).setBuildIconNum(71);
/* 1030:1026 */    add((short)74, localElementInformation);
/* 1031:     */    
/* 1036:1032 */    (
/* 1037:1033 */      localElementInformation = new ElementInformation((short)128, "Gold", GeneralElement.class, (short)128)).setBuildIconNum(72);
/* 1038:1034 */    add((short)128, localElementInformation);
/* 1039:     */    
/* 1041:1037 */    (
/* 1042:1038 */      localElementInformation = new ElementInformation((short)129, "Iridium", GeneralElement.class, (short)129)).setBuildIconNum(73);
/* 1043:1039 */    add((short)129, localElementInformation);
/* 1044:     */    
/* 1046:1042 */    (
/* 1047:1043 */      localElementInformation = new ElementInformation((short)130, "Mercury", GeneralElement.class, (short)130)).setBuildIconNum(74);
/* 1048:1044 */    add((short)130, localElementInformation);
/* 1049:     */    
/* 1051:1047 */    (
/* 1052:1048 */      localElementInformation = new ElementInformation((short)131, "Palladium", GeneralElement.class, (short)131)).setBuildIconNum(75);
/* 1053:1049 */    add((short)131, localElementInformation);
/* 1054:     */    
/* 1056:1052 */    (
/* 1057:1053 */      localElementInformation = new ElementInformation((short)132, "Platinum", GeneralElement.class, (short)132)).setBuildIconNum(76);
/* 1058:1054 */    add((short)132, localElementInformation);
/* 1059:     */    
/* 1061:1057 */    (
/* 1062:1058 */      localElementInformation = new ElementInformation((short)133, "Lithium", GeneralElement.class, (short)133)).setBuildIconNum(77);
/* 1063:1059 */    add((short)133, localElementInformation);
/* 1064:     */    
/* 1066:1062 */    (
/* 1067:1063 */      localElementInformation = new ElementInformation((short)134, "Magnesium", GeneralElement.class, (short)134)).setBuildIconNum(78);
/* 1068:1064 */    add((short)134, localElementInformation);
/* 1069:     */    
/* 1071:1067 */    (
/* 1072:1068 */      localElementInformation = new ElementInformation((short)135, "Titanium", GeneralElement.class, (short)135)).setBuildIconNum(79);
/* 1073:1069 */    add((short)135, localElementInformation);
/* 1074:     */    
/* 1076:1072 */    (
/* 1077:1073 */      localElementInformation = new ElementInformation((short)136, "Uranium", GeneralElement.class, (short)136)).setBuildIconNum(80);
/* 1078:1074 */    add((short)136, localElementInformation);
/* 1079:     */    
/* 1081:1077 */    (
/* 1082:1078 */      localElementInformation = new ElementInformation((short)137, "Polonium", GeneralElement.class, (short)137)).setBuildIconNum(81);
/* 1083:1079 */    add((short)137, localElementInformation);
/* 1084:     */    
/* 1086:1082 */    (
/* 1087:1083 */      localElementInformation = new ElementInformation((short)138, "Red Top", GeneralElement.class, (short)138)).setBuildIconNum(82);
/* 1088:1084 */    add((short)138, localElementInformation);
/* 1089:     */    
/* 1091:1087 */    (
/* 1092:1088 */      localElementInformation = new ElementInformation((short)139, "Red Rock", GeneralElement.class, (short)139)).setBuildIconNum(83);
/* 1093:1089 */    add((short)139, localElementInformation);
/* 1094:     */    
/* 1096:1092 */    (
/* 1097:1093 */      localElementInformation = new ElementInformation((short)140, "Red Dirt", GeneralElement.class, (short)140)).setBuildIconNum(84);
/* 1098:1094 */    add((short)140, localElementInformation);
/* 1099:     */  }
/* 1100:     */  
/* 1105:     */  public static boolean isValidType(short paramShort)
/* 1106:     */  {
/* 1107:1103 */    return (paramShort >= 0) && (paramShort < infoArray.length) && (infoArray[paramShort] != null);
/* 1108:     */  }
/* 1109:     */  
/* 1110:     */  public static String list() {
/* 1111:1107 */    return keySet.toString();
/* 1112:     */  }
/* 1113:     */  
/* 1114:     */  private static ElementParser load(File paramFile, String paramString) {
/* 1115:     */    ElementParser localElementParser;
/* 1116:1112 */    if (paramFile == null)
/* 1117:     */    {
/* 1118:1114 */      (localElementParser = new ElementParser()).loadAndParseDefault();
/* 1119:     */      
/* 1120:1116 */      return localElementParser;
/* 1121:     */    }
/* 1122:     */    
/* 1123:1119 */    (localElementParser = new ElementParser()).loadAndParseCustomXML(paramFile, paramString);
/* 1124:     */    
/* 1125:1121 */    return localElementParser;
/* 1126:     */  }
/* 1127:     */  
/* 1128:     */  public static short[] typeList()
/* 1129:     */  {
/* 1130:1126 */    return keyArray;
/* 1131:     */  }
/* 1132:     */  
/* 1133:     */  public Class getTypeFromString(String paramString) {
/* 1134:1130 */    if (paramString.equals("terrain"))
/* 1135:1131 */      return GeneralElement.class;
/* 1136:1132 */    if (paramString.equals("ship"))
/* 1137:1133 */      return ShipElement.class;
/* 1138:1134 */    if (paramString.equals("spacestation"))
/* 1139:1135 */      return SpaceStationElement.class;
/* 1140:1136 */    if (paramString.equals("deathstar")) {
/* 1141:1137 */      return SpaceStationElement.class;
/* 1142:     */    }
/* 1143:     */    
/* 1144:1140 */    throw new ElementTypeNotFoundException(paramString);
/* 1145:     */  }
/* 1146:     */  
/* 1149:     */  public static HashSet getLeveldkeyset()
/* 1150:     */  {
/* 1151:1147 */    return leveldKeySet;
/* 1152:     */  }
/* 1153:     */  
/* 1155:     */  public static HashSet getFactorykeyset()
/* 1156:     */  {
/* 1157:1153 */    return factoryKeySet;
/* 1158:     */  }
/* 1159:     */  
/* 1160:     */  public static File writeDocument(String paramString)
/* 1161:     */  {
/* 1162:1158 */    return writeDocument(new File(paramString));
/* 1163:     */  }
/* 1164:     */  
/* 1168:     */  public static File writeDocument(File paramFile)
/* 1169:     */  {
/* 1170:     */    try
/* 1171:     */    {
/* 1172:1168 */      localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
/* 1173:     */      
/* 1176:1172 */      Object localObject2 = categoryHirarchy;
/* 1177:     */      
/* 1178:1174 */      Element localElement = ((Document)localObject1).createElement(ElementParser.getStringFromType(((ElementCategory)localObject2).getCategory()));
/* 1179:     */      
/* 1180:1176 */      Object localObject3 = ((Document)localObject1).createComment("autocreated by the starmade block editor");
/* 1181:1177 */      localElement.appendChild((Node)localObject3);
/* 1182:     */      
/* 1183:1179 */      for (localObject2 = ((ElementCategory)localObject2).getChildren().iterator(); ((Iterator)localObject2).hasNext();) {
/* 1184:1180 */        writeCatToXML((ElementCategory)((Iterator)localObject2).next(), localElement, (Document)localObject1);
/* 1185:     */      }
/* 1186:     */      
/* 1188:1184 */      ((Document)localObject1).appendChild(localElement);
/* 1189:1185 */      ((Document)localObject1).setXmlVersion("1.0");
/* 1190:     */      
/* 1199:1195 */      (
/* 1200:1196 */        localObject3 = TransformerFactory.newInstance().newTransformer()).setOutputProperty("omit-xml-declaration", "yes");
/* 1201:1197 */      ((Transformer)localObject3).setOutputProperty("indent", "yes");
/* 1202:     */      
/* 1204:1200 */      new StringWriter();
/* 1205:1201 */      localObject2 = new StreamResult(paramFile);
/* 1206:1202 */      localObject1 = new DOMSource((Node)localObject1);
/* 1207:1203 */      ((Transformer)localObject3).transform((Source)localObject1, (Result)localObject2);
/* 1208:     */      
/* 1213:1209 */      return paramFile;
/* 1214:     */    } catch (Exception localException) { Object localObject1;
/* 1215:1211 */      (localObject1 = localException).printStackTrace();
/* 1216:1212 */      d.a((Exception)localObject1);
/* 1217:     */    }
/* 1218:1214 */    return null;
/* 1219:     */  }
/* 1220:     */  
/* 1221:     */  private static void writeCatToXML(ElementCategory paramElementCategory, Element paramElement, Document paramDocument) {
/* 1222:1218 */    Element localElement = paramDocument.createElement(ElementParser.getStringFromType(paramElementCategory.getCategory()));
/* 1223:     */    
/* 1224:1220 */    for (Iterator localIterator = paramElementCategory.getChildren().iterator(); localIterator.hasNext();) {
/* 1225:1221 */      writeCatToXML((ElementCategory)localIterator.next(), localElement, paramDocument);
/* 1226:     */    }
/* 1227:1223 */    for (localIterator = paramElementCategory.getInfoElements().iterator(); localIterator.hasNext();)
/* 1228:     */    {
/* 1229:1225 */      ((ElementInformation)localIterator.next()).appendXML(paramDocument, localElement);
/* 1230:     */    }
/* 1231:1227 */    paramElement.appendChild(localElement);
/* 1232:     */  }
/* 1233:     */  
/* 1234:1230 */  public static void clear() { informationKeyMap.clear();
/* 1235:1231 */    infoArray = null;
/* 1236:1232 */    highestType = 0;
/* 1237:1233 */    factoryKeySet.clear();
/* 1238:1234 */    keySet.clear();
/* 1239:1235 */    leveldKeySet.clear();
/* 1240:1236 */    levelMap.clear();
/* 1241:1237 */    categoryHirarchy.clear();
/* 1242:     */  }
/* 1243:     */  
/* 1245:     */  public static void reparseProperties()
/* 1246:     */  {
/* 1247:1243 */    properties = new Properties();
/* 1248:1244 */    FileInputStream localFileInputStream = new FileInputStream("./data/config/BlockTypes.properties");
/* 1249:1245 */    properties.load(localFileInputStream);
/* 1250:     */  }
/* 1251:     */  
/* 1253:     */  public static void reparseProperties(String paramString)
/* 1254:     */  {
/* 1255:1251 */    properties = new Properties();
/* 1256:1252 */    paramString = new FileInputStream(paramString);
/* 1257:1253 */    properties.load(paramString);
/* 1258:     */  }
/* 1259:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementKeyMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */