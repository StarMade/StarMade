/*   1:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   2:    */import java.io.BufferedReader;
/*   3:    */import java.io.BufferedWriter;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileReader;
/*   6:    */import java.io.FileWriter;
/*   7:    */import java.io.PrintStream;
/*   8:    */import org.lwjgl.input.Keyboard;
/*   9:    */import org.schema.common.ParseException;
/*  10:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  11:    */import org.schema.schine.network.objects.remote.RemoteShort;
/*  12:    */
/*  84:    */public enum cv
/*  85:    */{
/*  86:    */  private final int jdField_a_of_type_Int;
/*  87:    */  private final String jdField_a_of_type_JavaLangString;
/*  88:    */  private int jdField_b_of_type_Int;
/*  89:    */  public static final cv[] a;
/*  90:    */  private final cu jdField_a_of_type_Cu;
/*  91:    */  private final short jdField_a_of_type_Short;
/*  92:    */  
/*  93:    */  public final boolean a(Short paramShort)
/*  94:    */  {
/*  95: 95 */    if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Short <= 0)) { throw new AssertionError();
/*  96:    */    }
/*  97: 97 */    return (paramShort.shortValue() & this.jdField_a_of_type_Short) == this.jdField_a_of_type_Short;
/*  98:    */  }
/*  99:    */  
/* 100:100 */  public final void a(RemoteShort paramRemoteShort) { if (!Keyboard.isCreated()) {
/* 101:101 */      return;
/* 102:    */    }
/* 103:103 */    if (Keyboard.isKeyDown(this.jdField_b_of_type_Int)) {
/* 104:104 */      paramRemoteShort.set(Short.valueOf((short)(((Short)paramRemoteShort.get()).shortValue() | this.jdField_a_of_type_Short)));return;
/* 105:    */    }
/* 106:106 */    paramRemoteShort.set(Short.valueOf((short)(((Short)paramRemoteShort.get()).shortValue() & (this.jdField_a_of_type_Short ^ 0xFFFFFFFF))));
/* 107:    */  }
/* 108:    */  
/* 109:    */  public static void a() {
/* 110:    */    try {
/* 111:111 */      localObject1 = new String[values().length + 1];
/* 112:112 */      String[] arrayOfString = new String[values().length + 1];
/* 113:113 */      Object localObject2 = new File("./keyboard.cfg");
/* 114:114 */      localObject2 = new BufferedReader(new FileReader((File)localObject2));
/* 115:115 */      Object localObject3 = null;
/* 116:116 */      int i2 = 0;
/* 117:117 */      while ((localObject3 = ((BufferedReader)localObject2).readLine()) != null)
/* 118:    */      {
/* 119:119 */        localObject3 = ((String)localObject3).split(" = ", 2);
/* 120:120 */        localObject1[i2] = localObject3[0];
/* 121:121 */        arrayOfString[i2] = localObject3[1].trim();
/* 122:122 */        if ((i2 == 0) && (!localObject1[0].equals("#version"))) {
/* 123:123 */          System.err.println("UNKNOWN VERSION!! RESETTING KEYS");
/* 124:124 */          return; }
/* 125:125 */        if ((i2 == 0) && (localObject1[0].equals("#version")) && (Integer.parseInt(arrayOfString[i2]) != 0)) {
/* 126:126 */          System.err.println("OLD VERSION!! RESETTING KEYS");
/* 127:    */        }
/* 128:128 */        System.err.println("KEY: " + localObject1[i2] + " = " + arrayOfString[i2]);
/* 129:129 */        i2++;
/* 130:    */      }
/* 131:    */      
/* 132:132 */      for (i2 = 1; i2 < localObject1.length; i2++) {
/* 133:    */        try {
/* 134:    */          int i1;
/* 135:135 */          if ((i1 = Keyboard.getKeyIndex(arrayOfString[i2])) == 0) {
/* 136:136 */            throw new ParseException("Key not known: " + arrayOfString[i2]);
/* 137:    */          }
/* 138:138 */          int i3 = i1;localObject4 = null;valueOf(localObject1[i2]).jdField_b_of_type_Int = i3;
/* 139:139 */        } catch (ParseException localParseException) { Object localObject4 = null;
/* 140:    */          
/* 141:141 */          localParseException.printStackTrace();
/* 142:    */        }
/* 143:    */      }
/* 144:    */      
/* 145:143 */      ((BufferedReader)localObject2).close(); return;
/* 146:    */    } catch (Exception localException) { Object localObject1;
/* 147:145 */      (localObject1 = 
/* 148:    */      
/* 149:147 */        localException).printStackTrace();System.err.println("Could not read settings file: using defaults (" + ((Exception)localObject1).getMessage() + ")");
/* 150:    */    }
/* 151:    */  }
/* 152:    */  
/* 153:    */  public static void b() { Object localObject;
/* 154:151 */    (localObject = new File("./keyboard.cfg")).delete();
/* 155:152 */    ((File)localObject).createNewFile();
/* 156:153 */    (
/* 157:154 */      localObject = new BufferedWriter(new FileWriter((File)localObject))).write("#version = 0");
/* 158:155 */    ((BufferedWriter)localObject).newLine();
/* 159:156 */    for (cv localcv : values()) {
/* 160:157 */      ((BufferedWriter)localObject).write(localcv.name() + " = " + Keyboard.getKeyName(localcv.jdField_b_of_type_Int));
/* 161:158 */      ((BufferedWriter)localObject).newLine();
/* 162:    */    }
/* 163:    */    
/* 164:161 */    ((BufferedWriter)localObject).flush();
/* 165:162 */    ((BufferedWriter)localObject).close();
/* 166:    */  }
/* 167:    */  
/* 168:    */  static
/* 169:    */  {
/* 170: 22 */    jdField_a_of_type_Cv = new cv("STRAFE_LEFT", 0, 30, "Strafe Left", cu.jdField_a_of_type_Cu, (short)1);
/* 171: 23 */    jdField_b_of_type_Cv = new cv("STRAFE_RIGHT", 1, 32, "Strafe right", cu.jdField_a_of_type_Cu, (short)2);
/* 172: 24 */    c = new cv("FORWARD", 2, 17, "Forward", cu.jdField_a_of_type_Cu, (short)4);
/* 173: 25 */    d = new cv("BACKWARDS", 3, 31, "Backwards", cu.jdField_a_of_type_Cu, (short)8);
/* 174: 26 */    e = new cv("UP", 4, 18, "Strafe Up", cu.jdField_a_of_type_Cu, (short)16);
/* 175: 27 */    f = new cv("DOWN", 5, 16, "Strafe Down", cu.jdField_a_of_type_Cu, (short)32);
/* 176:    */    
/* 177: 29 */    g = new cv("STRAFE_LEFT_SHIP", 6, 30, "Strafe Left", cu.d, (short)1);
/* 178: 30 */    h = new cv("STRAFE_RIGHT_SHIP", 7, 32, "Strafe right", cu.d, (short)2);
/* 179: 31 */    i = new cv("FORWARD_SHIP", 8, 17, "Forward", cu.d, (short)4);
/* 180: 32 */    j = new cv("BACKWARDS_SHIP", 9, 31, "Backwards", cu.d, (short)8);
/* 181: 33 */    k = new cv("UP_SHIP", 10, 18, "Strafe Up", cu.d, (short)16);
/* 182: 34 */    l = new cv("DOWN_SHIP", 11, 16, "Strafe Down", cu.d, (short)32);
/* 183: 35 */    m = new cv("ROTATE_LEFT_SHIP", 12, 44, "Rotate Left", cu.d, (short)512);
/* 184: 36 */    n = new cv("ROTATE_RIGHT_SHIP", 13, 45, "Rotate Right", cu.d, (short)1024);
/* 185:    */    
/* 186: 38 */    o = new cv("DROP_ITEM", 14, 14, "Drop Item", cu.jdField_a_of_type_Cu, (short)-1);
/* 187:    */    
/* 188: 40 */    p = new cv("BREAK", 15, 42, "Break", cu.d, (short)64);
/* 189:    */    
/* 190: 42 */    q = new cv("ROLL", 16, 29, "Roll Ship", cu.d, (short)-1);
/* 191:    */    
/* 192: 44 */    r = new cv("CHANGE_SHIP_MODE", 17, 57, "Change Ship Mode", cu.c, (short)128);
/* 193:    */    
/* 194: 46 */    s = new cv("JUMP", 18, 57, "Jump", cu.b, (short)256);
/* 195:    */    
/* 196: 48 */    t = new cv("JUMP_TO_MODULE", 19, 45, "Jump to module", cu.e, (short)-1);
/* 197: 49 */    u = new cv("FREE_SHIP_CAM", 20, 54, "Free Ship Camera", cu.d, (short)-1);
/* 198: 50 */    v = new cv("ENTER_SHIP", 21, 19, "Exit Ship", cu.c, (short)-1);
/* 199: 51 */    w = new cv("ACTIVATE", 22, 19, "Activate Module", cu.b, (short)-1);
/* 200: 52 */    x = new cv("SPAWN_SHIP", 23, 45, "Spawn Ship", cu.b, (short)-1);
/* 201: 53 */    y = new cv("SPAWN_SPACE_STATION", 24, 50, "Spawn Space Station", cu.b, (short)-1);
/* 202: 54 */    z = new cv("SELECT_MODULE", 25, 46, "Select Module", cu.e, (short)-1);
/* 203: 55 */    A = new cv("CONNECT_MODULE", 26, 47, "Connect Module", cu.e, (short)-1);
/* 204: 56 */    B = new cv("HELP", 27, 87, "Help Screen", cu.jdField_a_of_type_Cu, (short)-1);
/* 205: 57 */    C = new cv("SWITCH_COCKPIT_SHIP_NEXT", 28, 200, "Docking Ship", cu.d, (short)-1);
/* 206: 58 */    D = new cv("SWITCH_COCKPIT_NEXT", 29, 205, "Next Cockpit", cu.d, (short)-1);
/* 207: 59 */    E = new cv("SWITCH_COCKPIT_PREVIOUS", 30, 203, "Previous Cockpit", cu.d, (short)-1);
/* 208: 60 */    aa = new cv("TEAM_CHANGE", 31, 36, "Change Team", cu.jdField_a_of_type_Cu, (short)-1);
/* 209: 61 */    F = new cv("CHAT", 32, 28, "Chat", cu.jdField_a_of_type_Cu, (short)-1);
/* 210: 62 */    G = new cv("SHOP_PANEL", 33, 48, "Shop Menu", cu.jdField_a_of_type_Cu, (short)-1);
/* 211: 63 */    H = new cv("INVENTORY_PANEL", 34, 23, "Inventory Menu", cu.jdField_a_of_type_Cu, (short)-1);
/* 212: 64 */    I = new cv("WEAPON_PANEL", 35, 20, "Weapon Menu", cu.jdField_a_of_type_Cu, (short)-1);
/* 213: 65 */    J = new cv("NAVIGATION_PANEL", 36, 49, "Navigation Menu", cu.jdField_a_of_type_Cu, (short)-1);
/* 214: 66 */    K = new cv("AI_CONFIG_PANEL", 37, 38, "AI Config Menu", cu.jdField_a_of_type_Cu, (short)-1);
/* 215: 67 */    L = new cv("SELECT_NEXT_ENTITY", 38, 22, "Select Next Entity", cu.jdField_a_of_type_Cu, (short)-1);
/* 216: 68 */    M = new cv("SELECT_PREV_ENTITY", 39, 21, "Select Previous Entity", cu.jdField_a_of_type_Cu, (short)-1);
/* 217: 69 */    N = new cv("SELECT_NEAR_ENTITY", 40, 34, "Select Nearest Entity", cu.jdField_a_of_type_Cu, (short)-1);
/* 218: 70 */    O = new cv("SELECT_LOOK_ENTITY", 41, 33, "Select Targeted Entity", cu.jdField_a_of_type_Cu, (short)-1);
/* 219: 71 */    P = new cv("RELEASE_MOUSE", 42, 60, "Release Mouse", cu.jdField_a_of_type_Cu, (short)-1);
/* 220: 72 */    Q = new cv("PLAYER_STATISTICS", 43, 15, "Player Statistics", cu.jdField_a_of_type_Cu, (short)-1);
/* 221: 73 */    R = new cv("NEXT_CONTROLLER", 44, 205, "Select next controller", cu.e, (short)-1);
/* 222: 74 */    S = new cv("PREVIOUS_CONTROLLER", 45, 203, "Select prev controller", cu.e, (short)-1);
/* 223: 75 */    T = new cv("SELECT_CORE", 46, 200, "Select core", cu.e, (short)-1);
/* 224: 76 */    U = new cv("BUILD_MODE_FIX_CAM", 47, sE.a() == sE.d ? 12 : 29, "Advanced Build Mode", cu.e, (short)-1);
/* 225: 77 */    V = new cv("ALIGN_SHIP", 48, 46, "Align Ship Cam", cu.d, (short)-1);
/* 226: 78 */    W = new cv("SCREENSHOT_WITH_GUI", 49, 63, "Screenshot (with GUI)", cu.jdField_a_of_type_Cu, (short)-1);
/* 227: 79 */    X = new cv("SCREENSHOT_WITHOUT_GUI", 50, 64, "Screenshot (without GUI)", cu.jdField_a_of_type_Cu, (short)-1);
/* 228: 80 */    Y = new cv("FACTION_MENU", 51, 35, "Open Faction Menu", cu.jdField_a_of_type_Cu, (short)-1);
/* 229: 81 */    Z = new cv("MAP_PANEL", 52, 25, "Map", cu.jdField_a_of_type_Cu, (short)-1);jdField_b_of_type_ArrayOfCv = new cv[] { jdField_a_of_type_Cv, jdField_b_of_type_Cv, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, aa, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z };
/* 230:    */    
/* 231: 83 */    ObjectArrayList localObjectArrayList = new ObjectArrayList();
/* 232:    */    
/* 276:    */    cv[] arrayOfcv;
/* 277:    */    
/* 321:173 */    int i2 = (arrayOfcv = values()).length; for (int i3 = 0; i3 < i2; i3++) { cv localcv;
/* 322:174 */      if ((localcv = arrayOfcv[i3]).jdField_a_of_type_Short > 0) {
/* 323:175 */        localObjectArrayList.add(localcv);
/* 324:    */      }
/* 325:    */    }
/* 326:178 */    jdField_a_of_type_ArrayOfCv = new cv[localObjectArrayList.size()];
/* 327:179 */    for (int i1 = 0; i1 < jdField_a_of_type_ArrayOfCv.length; i1++) {
/* 328:180 */      jdField_a_of_type_ArrayOfCv[i1] = ((cv)localObjectArrayList.get(i1));
/* 329:    */    }
/* 330:    */  }
/* 331:    */  
/* 334:    */  private cv(int paramInt, String paramString, cu paramcu, short paramShort)
/* 335:    */  {
/* 336:188 */    this.jdField_a_of_type_Int = paramInt;
/* 337:189 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 338:190 */    this.jdField_b_of_type_Int = this.jdField_a_of_type_Int;
/* 339:191 */    this.jdField_a_of_type_Cu = paramcu;
/* 340:192 */    this.jdField_a_of_type_Short = paramShort;
/* 341:    */  }
/* 342:    */  
/* 353:    */  public final cu a()
/* 354:    */  {
/* 355:207 */    return this.jdField_a_of_type_Cu;
/* 356:    */  }
/* 357:    */  
/* 358:    */  public final String a() {
/* 359:211 */    return this.jdField_a_of_type_JavaLangString;
/* 360:    */  }
/* 361:    */  
/* 362:214 */  public final String b() { return Keyboard.getKeyName(this.jdField_b_of_type_Int); }
/* 363:    */  
/* 366:    */  public final int a()
/* 367:    */  {
/* 368:220 */    return this.jdField_b_of_type_Int;
/* 369:    */  }
/* 370:    */  
/* 372:    */  public final boolean a()
/* 373:    */  {
/* 374:226 */    return Keyboard.isKeyDown(this.jdField_b_of_type_Int);
/* 375:    */  }
/* 376:    */  
/* 378:    */  public final void a(int paramInt)
/* 379:    */  {
/* 380:232 */    this.jdField_b_of_type_Int = paramInt;
/* 381:    */  }
/* 382:    */  
/* 383:    */  public final void a(RemoteBuffer paramRemoteBuffer, boolean paramBoolean1, boolean paramBoolean2) {
/* 384:236 */    paramRemoteBuffer.add(new RemoteShort((short)(paramBoolean1 ? this.jdField_a_of_type_Short : -this.jdField_a_of_type_Short), paramBoolean2));
/* 385:    */  }
/* 386:    */  
/* 387:    */  public final boolean a(int paramInt) {
/* 388:240 */    return this.jdField_a_of_type_Short == paramInt;
/* 389:    */  }
/* 390:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */