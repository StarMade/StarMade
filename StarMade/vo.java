/*   1:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   2:    */import java.io.BufferedReader;
/*   3:    */import java.io.BufferedWriter;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileReader;
/*   6:    */import java.io.FileWriter;
/*   7:    */import java.io.PrintStream;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.Locale;
/*  10:    */import org.schema.game.network.objects.NetworkGameState;
/*  11:    */import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*  12:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  13:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  14:    */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  15:    */
/*  70:    */public enum vo
/*  71:    */{
/*  72:    */  private static boolean jdField_a_of_type_Boolean;
/*  73:    */  private final String jdField_a_of_type_JavaLangString;
/*  74:    */  private xA jdField_a_of_type_XA;
/*  75:    */  private Object jdField_a_of_type_JavaLangObject;
/*  76:    */  
/*  77:    */  private vo(String paramString, Object paramObject, xA paramxA)
/*  78:    */  {
/*  79: 79 */    this.jdField_a_of_type_JavaLangString = paramString;
/*  80: 80 */    this.jdField_a_of_type_XA = paramxA;
/*  81: 81 */    a(paramObject);
/*  82:    */  }
/*  83:    */  
/* 103:    */  public static void a(NetworkGameState paramNetworkGameState)
/* 104:    */  {
/* 105:105 */    if (jdField_a_of_type_Boolean) {
/* 106:106 */      for (vo localvo : values()) {
/* 107:107 */        if ((!jdField_b_of_type_Boolean) && (paramNetworkGameState == null)) throw new AssertionError();
/* 108:    */        RemoteStringArray localRemoteStringArray;
/* 109:109 */        (localRemoteStringArray = new RemoteStringArray(2, paramNetworkGameState)).set(0, localvo.name());
/* 110:110 */        localRemoteStringArray.set(1, localvo.jdField_a_of_type_JavaLangObject.toString());
/* 111:111 */        paramNetworkGameState.serverConfig.add(localRemoteStringArray);
/* 112:    */      }
/* 113:113 */      jdField_a_of_type_Boolean = false;
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  public static void b(NetworkGameState paramNetworkGameState) {
/* 118:118 */    for (int i1 = 0; i1 < paramNetworkGameState.serverConfig.getReceiveBuffer().size(); i1++) {
/* 119:119 */      RemoteStringArray localRemoteStringArray = (RemoteStringArray)paramNetworkGameState.serverConfig.getReceiveBuffer().get(i1);
/* 120:    */      try
/* 121:    */      {
/* 122:122 */        valueOf((String)localRemoteStringArray.get(0).get()).a((String)localRemoteStringArray.get(1).get());
/* 123:123 */      } catch (StateParameterNotFoundException localStateParameterNotFoundException) { 
/* 124:    */        
/* 125:125 */          localStateParameterNotFoundException;
/* 126:    */      }
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 130:    */  public static void a() {
/* 131:    */    try {
/* 132:130 */      String[] arrayOfString1 = new String[values().length];
/* 133:131 */      String[] arrayOfString2 = new String[values().length];
/* 134:132 */      Object localObject1 = new File("./server.cfg");
/* 135:133 */      localObject1 = new BufferedReader(new FileReader((File)localObject1));
/* 136:134 */      Object localObject2 = null;
/* 137:135 */      int i1 = 0;
/* 138:136 */      while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
/* 139:137 */        if (!((String)localObject2).startsWith("//"))
/* 140:    */        {
/* 143:141 */          localObject2 = ((String)localObject2).substring(0, ((String)localObject2).indexOf("//")).split("=", 2);
/* 144:142 */          arrayOfString1[i1] = localObject2[0].trim();
/* 145:143 */          arrayOfString2[i1] = localObject2[1].trim();
/* 146:144 */          i1++;
/* 147:    */        }
/* 148:    */      }
/* 149:147 */      for (i1 = 0; i1 < arrayOfString1.length; i1++) {
/* 150:    */        try
/* 151:    */        {
/* 152:150 */          if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("ADMIN_AUTHENTICATION_NEEDED"))) {
/* 153:151 */            arrayOfString1[i1] = "USE_STARMADE_AUTHENTICATION";
/* 154:    */          }
/* 155:153 */          if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("AI_DESTRUCTION_LOOT_COUNT_MULTIPLYER"))) {
/* 156:154 */            arrayOfString1[i1] = "AI_DESTRUCTION_LOOT_COUNT_MULTIPLIER";
/* 157:    */          }
/* 158:156 */          if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("AI_DESTRUCTION_LOOT_STACK_MULTIPLYER"))) {
/* 159:157 */            arrayOfString1[i1] = "AI_DESTRUCTION_LOOT_STACK_MULTIPLIER";
/* 160:    */          }
/* 161:159 */          if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("CHEST_LOOT_COUNT_MULTIPLYER"))) {
/* 162:160 */            arrayOfString1[i1] = "CHEST_LOOT_COUNT_MULTIPLIER";
/* 163:    */          }
/* 164:162 */          if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("CHEST_LOOT_STACK_MULTIPLYER"))) {
/* 165:163 */            arrayOfString1[i1] = "CHEST_LOOT_STACK_MULTIPLIER";
/* 166:    */          }
/* 167:165 */          valueOf(arrayOfString1[i1]).a(arrayOfString2[i1]);
/* 168:    */        } catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/* 169:167 */          System.err.println("[SERVER][CONFIG] No value for " + arrayOfString1[i1] + ". Creating default entry.");
/* 170:    */        } catch (NullPointerException localNullPointerException) {
/* 171:169 */          System.err.println("[SERVER][CONFIG] No value for " + arrayOfString1[i1] + ". Creating default entry.");
/* 172:    */        }
/* 173:    */      }
/* 174:172 */      ((BufferedReader)localObject1).close(); return;
/* 175:173 */    } catch (Exception localException) { 
/* 176:    */      
/* 178:176 */        localException.printStackTrace();System.err.println("Could not read settings file: using defaults");
/* 179:    */    }
/* 180:    */  }
/* 181:    */  
/* 182:180 */  public static void b() { (localObject = new File("./server.cfg")).delete();
/* 183:181 */    ((File)localObject).createNewFile();
/* 184:182 */    Object localObject = new BufferedWriter(new FileWriter((File)localObject));
/* 185:183 */    for (vo localvo : values()) {
/* 186:184 */      ((BufferedWriter)localObject).write(localvo.name() + " = " + localvo.jdField_a_of_type_JavaLangObject + " //" + localvo.jdField_a_of_type_JavaLangString);
/* 187:185 */      ((BufferedWriter)localObject).newLine();
/* 188:    */    }
/* 189:187 */    ((BufferedWriter)localObject).flush();
/* 190:188 */    ((BufferedWriter)localObject).close();
/* 191:    */  }
/* 192:    */  
/* 193:    */  static
/* 194:    */  {
/* 195: 23 */    jdField_b_of_type_Boolean = !vo.class.desiredAssertionStatus();
/* 196:    */    
/* 197: 25 */    jdField_a_of_type_Vo = new vo("PROTECT_STARTING_SECTOR", 0, "Protects the starting sector", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 198: 26 */    jdField_b_of_type_Vo = new vo("ENABLE_SIMULATION", 1, "Universe AI simulation", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 199: 27 */    c = new vo("CONCURRENT_SIMULATION", 2, "How many simulation groups may be in the universe simultaniously (performance)", Integer.valueOf(256), new xz());
/* 200:    */    
/* 201: 29 */    d = new vo("ENEMY_SPAWNING", 3, "Enables enemy spawing", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 202:    */    
/* 203: 31 */    e = new vo("SIMULATION_SPAWN_DELAY", 4, "How much seconds between simulation spawn ticks", Integer.valueOf(420), new xz());
/* 204: 32 */    f = new vo("SIMULATION_TRADING_FILLS_SHOPS", 5, "Trading guild will deliver stock to shops", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 205: 33 */    g = new vo("SECTOR_INACTIVE_TIMEOUT", 6, "Time in secs after which sectors go inactive (-1 = off)", Integer.valueOf(20), new xz());
/* 206: 34 */    h = new vo("SECTOR_INACTIVE_CLEANUP_TIMEOUT", 7, "Time in secs after which inactive sectors are completely removed from memory (-1 = off)", Integer.valueOf(10), new xz());
/* 207: 35 */    i = new vo("USE_STARMADE_AUTHENTICATION", 8, "allow star-made.org authentication", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 208: 36 */    j = new vo("STARTING_CREDITS", 9, "How many credits a new player has", Integer.valueOf(25000), new xz());
/* 209: 37 */    k = new vo("DEFAULT_BLUEPRINT_ENEMY_USE", 10, "Default option to use uploaded ships in waves", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 210: 38 */    l = new vo("LOCK_FACTION_SHIPS", 11, "If true, ships of other factions cant be edited, activated, or entered", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 211: 39 */    m = new vo("DEBUG_FSM_STATE", 12, "transfer debug FSM state. Turing this on may slow doen network", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 212: 40 */    n = new vo("PHYSICS_SHAPE_CASTING_TUNNELING_PREVENTION", 13, "Makes a convex cast for hight speed object to prevent clipping. High Cost. (Bugged right now, so dont turn it on)", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 213: 41 */    o = new vo("CATALOG_SLOTS_PER_PLAYER", 14, "How many slots per player for saved ships (-1 for unlimited)", Integer.valueOf(-1), new xz());
/* 214:    */    
/* 215: 43 */    p = new vo("CATALOG_NAME_COLLISION_HANDLING", 15, "if off, saving with an existing entry is denied, if on the name is automatically changed by adding numbers on the end", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 216: 44 */    q = new vo("SECTOR_AUTOSAVE_SEC", 16, "Time interval in secs the server will autosave (-1 for never)", Integer.valueOf(300), new xz());
/* 217: 45 */    r = new vo("PHYSICS_SLOWDOWN_THRESHOLD", 17, "Milliseconds a collision test may take before anti-slowdown mode is activated", Integer.valueOf(40), new xz());
/* 218: 46 */    s = new vo("THRUST_SPEED_LIMIT", 18, "How fast ships, etc. may go in km/h. Too high values may induce physics tunneling effects", Integer.valueOf(50), new xz());
/* 219: 47 */    t = new vo("MAX_CLIENTS", 19, "Max number of clients allowed on this server", Integer.valueOf(32), new xz());
/* 220: 48 */    u = new vo("SUPER_ADMIN_PASSWORD_USE", 20, "Enable super admin for this server", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 221: 49 */    v = new vo("SUPER_ADMIN_PASSWORD", 21, "Super admin password for this server", "mypassword", new xC());
/* 222: 50 */    w = new vo("SERVER_LISTEN_IP", 22, "Enter specific ip for the server to listen to. use \"all\" to listen on every ip", "all", new xC());
/* 223: 51 */    x = new vo("SOCKET_BUFFER_SIZE", 23, "buffer size of incoming and outgoing data per socket", Integer.valueOf(65536), new xz());
/* 224: 52 */    y = new vo("PHYSICS_LINEAR_DAMPING", 24, "how much object slow down naturally (must be between 0 and 1): 0 is no slowdown", Float.valueOf(0.09F), new xy());
/* 225: 53 */    z = new vo("PHYSICS_ROTATIONAL_DAMPING", 25, "how much object slow down naturally (must be between 0 and 1): 0 is no slowdown", Float.valueOf(0.09F), new xy());
/* 226: 54 */    A = new vo("AI_DESTRUCTION_LOOT_COUNT_MULTIPLIER", 26, "multiply amount of items in a loot stack. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new xy());
/* 227: 55 */    B = new vo("AI_DESTRUCTION_LOOT_STACK_MULTIPLIER", 27, "multiply amount of items spawned after AI destruction. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new xy());
/* 228: 56 */    C = new vo("CHEST_LOOT_COUNT_MULTIPLIER", 28, "multiply amount of items in a loot stack. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new xy());
/* 229: 57 */    D = new vo("CHEST_LOOT_STACK_MULTIPLIER", 29, "multiply amount of items spawned in chests of generated chests. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new xy());
/* 230: 58 */    E = new vo("USE_WHITELIST", 30, "only names/ips from whitelist.txt are allowed", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 231: 59 */    F = new vo("FILTER_CONNECTION_MESSAGES", 31, "don't display join/disconnect messages", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 232: 60 */    G = new vo("USE_UDP", 32, "Use 'User Datagram Protocol' (UDP) instead of 'Transmission Control Protocol' (TCP) for connections", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 233: 61 */    H = new vo("AUTO_KICK_MODIFIED_BLUEPRINT_USE", 33, "Kick players that spawn modified blueprints", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 234: 62 */    I = new vo("AUTO_BAN_ID_MODIFIED_BLUEPRINT_USE", 34, "Ban player by name that spawn modified blueprints", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 235: 63 */    J = new vo("AUTO_BAN_IP_MODIFIED_BLUEPRINT_USE", 35, "Ban player by IP that spawn modified blueprints", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 236: 64 */    K = new vo("REMOVE_MODIFIED_BLUEPRINTS", 36, "Auto-removes a modified blueprint", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 237: 65 */    L = new vo("TCP_NODELAY", 37, "Naggles algorithm (WARNING: only change when you know what you're doing)", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/* 238: 66 */    M = new vo("PING_FLUSH", 38, "flushes ping/pong immediately (WARNING: only change when you know what you're doing)", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));jdField_a_of_type_ArrayOfVo = new vo[] { jdField_a_of_type_Vo, jdField_b_of_type_Vo, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M };
/* 239:    */    
/* 241: 69 */    jdField_a_of_type_Boolean = true;
/* 242:    */    
/* 371:199 */    new ArrayList();
/* 372:    */  }
/* 373:    */  
/* 418:    */  public final void a(boolean paramBoolean)
/* 419:    */  {
/* 420:248 */    if ((this.jdField_a_of_type_JavaLangObject instanceof Boolean)) {
/* 421:249 */      a(Boolean.valueOf(paramBoolean));
/* 422:250 */      return;
/* 423:    */    }
/* 424:252 */    if (!jdField_b_of_type_Boolean) { throw new AssertionError();
/* 425:    */    }
/* 426:    */  }
/* 427:    */  
/* 431:    */  public final Object a()
/* 432:    */  {
/* 433:261 */    return this.jdField_a_of_type_JavaLangObject;
/* 434:    */  }
/* 435:    */  
/* 452:    */  public final boolean a()
/* 453:    */  {
/* 454:282 */    return ((this.jdField_a_of_type_JavaLangObject instanceof Boolean)) && (((Boolean)this.jdField_a_of_type_JavaLangObject).booleanValue());
/* 455:    */  }
/* 456:    */  
/* 459:    */  public final void a(Object paramObject)
/* 460:    */  {
/* 461:289 */    if (paramObject != this.jdField_a_of_type_JavaLangObject) {
/* 462:290 */      jdField_a_of_type_Boolean = true;
/* 463:    */    }
/* 464:    */    
/* 466:294 */    this.jdField_a_of_type_JavaLangObject = paramObject;
/* 467:    */  }
/* 468:    */  
/* 472:    */  private void a(String paramString)
/* 473:    */  {
/* 474:302 */    a(this.jdField_a_of_type_XA.a(paramString));
/* 475:    */  }
/* 476:    */  
/* 482:    */  public final String toString()
/* 483:    */  {
/* 484:312 */    return name().toLowerCase(Locale.ENGLISH) + " (" + this.jdField_a_of_type_XA.a() + ") " + this.jdField_a_of_type_JavaLangObject;
/* 485:    */  }
/* 486:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */