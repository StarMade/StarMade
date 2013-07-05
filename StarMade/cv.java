/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.PrintStream;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.common.ParseException;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteShort;
/*     */ 
/*     */ public enum cv
/*     */ {
/*     */   private final int jdField_a_of_type_Int;
/*     */   private final String jdField_a_of_type_JavaLangString;
/*     */   private int jdField_b_of_type_Int;
/*     */   public static final cv[] a;
/*     */   private final cu jdField_a_of_type_Cu;
/*     */   private final short jdField_a_of_type_Short;
/*     */ 
/*     */   public final boolean a(Short paramShort)
/*     */   {
/*  95 */     if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Short <= 0)) throw new AssertionError();
/*     */ 
/*  97 */     return (paramShort.shortValue() & this.jdField_a_of_type_Short) == this.jdField_a_of_type_Short;
/*     */   }
/*     */   public final void a(RemoteShort paramRemoteShort) {
/* 100 */     if (!Keyboard.isCreated()) {
/* 101 */       return;
/*     */     }
/* 103 */     if (Keyboard.isKeyDown(this.jdField_b_of_type_Int)) {
/* 104 */       paramRemoteShort.set(Short.valueOf((short)(((Short)paramRemoteShort.get()).shortValue() | this.jdField_a_of_type_Short))); return;
/*     */     }
/* 106 */     paramRemoteShort.set(Short.valueOf((short)(((Short)paramRemoteShort.get()).shortValue() & (this.jdField_a_of_type_Short ^ 0xFFFFFFFF))));
/*     */   }
/*     */ 
/*     */   public static void a() {
/*     */     try {
/* 111 */       localObject1 = new String[values().length + 1];
/* 112 */       String[] arrayOfString = new String[values().length + 1];
/* 113 */       Object localObject2 = new File("./keyboard.cfg");
/* 114 */       localObject2 = new BufferedReader(new FileReader((File)localObject2));
/* 115 */       Object localObject3 = null;
/* 116 */       int i2 = 0;
/* 117 */       while ((localObject3 = ((BufferedReader)localObject2).readLine()) != null)
/*     */       {
/* 119 */         localObject3 = ((String)localObject3).split(" = ", 2);
/* 120 */         localObject1[i2] = localObject3[0];
/* 121 */         arrayOfString[i2] = localObject3[1].trim();
/* 122 */         if ((i2 == 0) && (!localObject1[0].equals("#version"))) {
/* 123 */           System.err.println("UNKNOWN VERSION!! RESETTING KEYS");
/* 124 */           return;
/* 125 */         }if ((i2 == 0) && (localObject1[0].equals("#version")) && (Integer.parseInt(arrayOfString[i2]) != 0)) {
/* 126 */           System.err.println("OLD VERSION!! RESETTING KEYS");
/*     */         }
/* 128 */         System.err.println("KEY: " + localObject1[i2] + " = " + arrayOfString[i2]);
/* 129 */         i2++;
/*     */       }
/*     */ 
/* 132 */       for (i2 = 1; i2 < localObject1.length; i2++) {
/*     */         try
/*     */         {
/*     */           int i1;
/* 135 */           if ((
/* 135 */             i1 = Keyboard.getKeyIndex(arrayOfString[i2])) == 0)
/*     */           {
/* 136 */             throw new ParseException("Key not known: " + arrayOfString[i2]);
/*     */           }
/* 138 */           int i3 = i1; localObject4 = null; valueOf(localObject1[i2]).jdField_b_of_type_Int = i3; } catch (ParseException localParseException) {
/* 139 */           Object localObject4 = null;
/*     */ 
/* 141 */           localParseException.printStackTrace();
/*     */         }
/*     */       }
/*     */ 
/* 143 */       ((BufferedReader)localObject2).close();
/*     */       return;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */       Object localObject1;
/* 144 */       (
/* 145 */         localObject1 = 
/* 147 */         localException).printStackTrace();
/* 146 */       System.err.println("Could not read settings file: using defaults (" + ((Exception)localObject1).getMessage() + ")");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void b()
/*     */   {
/*     */     Object localObject;
/* 150 */     (
/* 151 */       localObject = new File("./keyboard.cfg"))
/* 151 */       .delete();
/* 152 */     ((File)localObject).createNewFile();
/* 153 */     (
/* 154 */       localObject = new BufferedWriter(new FileWriter((File)localObject)))
/* 154 */       .write("#version = 0");
/* 155 */     ((BufferedWriter)localObject).newLine();
/* 156 */     for (cv localcv : values()) {
/* 157 */       ((BufferedWriter)localObject).write(localcv.name() + " = " + Keyboard.getKeyName(localcv.jdField_b_of_type_Int));
/* 158 */       ((BufferedWriter)localObject).newLine();
/*     */     }
/*     */ 
/* 161 */     ((BufferedWriter)localObject).flush();
/* 162 */     ((BufferedWriter)localObject).close();
/*     */   }
/*     */ 
/*     */   private cv(int paramcu, String paramShort, cu arg5, short arg6)
/*     */   {
/* 188 */     this.jdField_a_of_type_Int = paramcu;
/* 189 */     this.jdField_a_of_type_JavaLangString = paramShort;
/* 190 */     this.jdField_b_of_type_Int = this.jdField_a_of_type_Int;
/*     */     Object localObject;
/* 191 */     this.jdField_a_of_type_Cu = localObject;
/*     */     short s1;
/* 192 */     this.jdField_a_of_type_Short = s1;
/*     */   }
/*     */ 
/*     */   public final cu a()
/*     */   {
/* 207 */     return this.jdField_a_of_type_Cu;
/*     */   }
/*     */ 
/*     */   public final String a() {
/* 211 */     return this.jdField_a_of_type_JavaLangString;
/*     */   }
/*     */   public final String b() {
/* 214 */     return Keyboard.getKeyName(this.jdField_b_of_type_Int);
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 220 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 226 */     return Keyboard.isKeyDown(this.jdField_b_of_type_Int);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt)
/*     */   {
/* 232 */     this.jdField_b_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final void a(RemoteBuffer paramRemoteBuffer, boolean paramBoolean1, boolean paramBoolean2) {
/* 236 */     paramRemoteBuffer.add(new RemoteShort((short)(paramBoolean1 ? this.jdField_a_of_type_Short : -this.jdField_a_of_type_Short), paramBoolean2));
/*     */   }
/*     */ 
/*     */   public final boolean a(int paramInt) {
/* 240 */     return this.jdField_a_of_type_Short == paramInt;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  22 */     jdField_a_of_type_Cv = new cv("STRAFE_LEFT", 0, 30, "Strafe Left", cu.jdField_a_of_type_Cu, (short)1);
/*  23 */     jdField_b_of_type_Cv = new cv("STRAFE_RIGHT", 1, 32, "Strafe right", cu.jdField_a_of_type_Cu, (short)2);
/*  24 */     c = new cv("FORWARD", 2, 17, "Forward", cu.jdField_a_of_type_Cu, (short)4);
/*  25 */     d = new cv("BACKWARDS", 3, 31, "Backwards", cu.jdField_a_of_type_Cu, (short)8);
/*  26 */     e = new cv("UP", 4, 18, "Strafe Up", cu.jdField_a_of_type_Cu, (short)16);
/*  27 */     f = new cv("DOWN", 5, 16, "Strafe Down", cu.jdField_a_of_type_Cu, (short)32);
/*     */ 
/*  29 */     g = new cv("STRAFE_LEFT_SHIP", 6, 30, "Strafe Left", cu.d, (short)1);
/*  30 */     h = new cv("STRAFE_RIGHT_SHIP", 7, 32, "Strafe right", cu.d, (short)2);
/*  31 */     i = new cv("FORWARD_SHIP", 8, 17, "Forward", cu.d, (short)4);
/*  32 */     j = new cv("BACKWARDS_SHIP", 9, 31, "Backwards", cu.d, (short)8);
/*  33 */     k = new cv("UP_SHIP", 10, 18, "Strafe Up", cu.d, (short)16);
/*  34 */     l = new cv("DOWN_SHIP", 11, 16, "Strafe Down", cu.d, (short)32);
/*  35 */     m = new cv("ROTATE_LEFT_SHIP", 12, 44, "Rotate Left", cu.d, (short)512);
/*  36 */     n = new cv("ROTATE_RIGHT_SHIP", 13, 45, "Rotate Right", cu.d, (short)1024);
/*     */ 
/*  38 */     o = new cv("DROP_ITEM", 14, 14, "Drop Item", cu.jdField_a_of_type_Cu, (short)-1);
/*     */ 
/*  40 */     p = new cv("BREAK", 15, 42, "Break", cu.d, (short)64);
/*     */ 
/*  42 */     q = new cv("ROLL", 16, 29, "Roll Ship", cu.d, (short)-1);
/*     */ 
/*  44 */     r = new cv("CHANGE_SHIP_MODE", 17, 57, "Change Ship Mode", cu.c, (short)128);
/*     */ 
/*  46 */     s = new cv("JUMP", 18, 57, "Jump", cu.b, (short)256);
/*     */ 
/*  48 */     t = new cv("JUMP_TO_MODULE", 19, 45, "Jump to module", cu.e, (short)-1);
/*  49 */     u = new cv("FREE_SHIP_CAM", 20, 54, "Free Ship Camera", cu.d, (short)-1);
/*  50 */     v = new cv("ENTER_SHIP", 21, 19, "Exit Ship", cu.c, (short)-1);
/*  51 */     w = new cv("ACTIVATE", 22, 19, "Activate Module", cu.b, (short)-1);
/*  52 */     x = new cv("SPAWN_SHIP", 23, 45, "Spawn Ship", cu.b, (short)-1);
/*  53 */     y = new cv("SPAWN_SPACE_STATION", 24, 50, "Spawn Space Station", cu.b, (short)-1);
/*  54 */     z = new cv("SELECT_MODULE", 25, 46, "Select Module", cu.e, (short)-1);
/*  55 */     A = new cv("CONNECT_MODULE", 26, 47, "Connect Module", cu.e, (short)-1);
/*  56 */     B = new cv("HELP", 27, 87, "Help Screen", cu.jdField_a_of_type_Cu, (short)-1);
/*  57 */     C = new cv("SWITCH_COCKPIT_SHIP_NEXT", 28, 200, "Docking Ship", cu.d, (short)-1);
/*  58 */     D = new cv("SWITCH_COCKPIT_NEXT", 29, 205, "Next Cockpit", cu.d, (short)-1);
/*  59 */     E = new cv("SWITCH_COCKPIT_PREVIOUS", 30, 203, "Previous Cockpit", cu.d, (short)-1);
/*  60 */     aa = new cv("TEAM_CHANGE", 31, 36, "Change Team", cu.jdField_a_of_type_Cu, (short)-1);
/*  61 */     F = new cv("CHAT", 32, 28, "Chat", cu.jdField_a_of_type_Cu, (short)-1);
/*  62 */     G = new cv("SHOP_PANEL", 33, 48, "Shop Menu", cu.jdField_a_of_type_Cu, (short)-1);
/*  63 */     H = new cv("INVENTORY_PANEL", 34, 23, "Inventory Menu", cu.jdField_a_of_type_Cu, (short)-1);
/*  64 */     I = new cv("WEAPON_PANEL", 35, 20, "Weapon Menu", cu.jdField_a_of_type_Cu, (short)-1);
/*  65 */     J = new cv("NAVIGATION_PANEL", 36, 49, "Navigation Menu", cu.jdField_a_of_type_Cu, (short)-1);
/*  66 */     K = new cv("AI_CONFIG_PANEL", 37, 38, "AI Config Menu", cu.jdField_a_of_type_Cu, (short)-1);
/*  67 */     L = new cv("SELECT_NEXT_ENTITY", 38, 22, "Select Next Entity", cu.jdField_a_of_type_Cu, (short)-1);
/*  68 */     M = new cv("SELECT_PREV_ENTITY", 39, 21, "Select Previous Entity", cu.jdField_a_of_type_Cu, (short)-1);
/*  69 */     N = new cv("SELECT_NEAR_ENTITY", 40, 34, "Select Nearest Entity", cu.jdField_a_of_type_Cu, (short)-1);
/*  70 */     O = new cv("SELECT_LOOK_ENTITY", 41, 33, "Select Targeted Entity", cu.jdField_a_of_type_Cu, (short)-1);
/*  71 */     P = new cv("RELEASE_MOUSE", 42, 60, "Release Mouse", cu.jdField_a_of_type_Cu, (short)-1);
/*  72 */     Q = new cv("PLAYER_STATISTICS", 43, 15, "Player Statistics", cu.jdField_a_of_type_Cu, (short)-1);
/*  73 */     R = new cv("NEXT_CONTROLLER", 44, 205, "Select next controller", cu.e, (short)-1);
/*  74 */     S = new cv("PREVIOUS_CONTROLLER", 45, 203, "Select prev controller", cu.e, (short)-1);
/*  75 */     T = new cv("SELECT_CORE", 46, 200, "Select core", cu.e, (short)-1);
/*  76 */     U = new cv("BUILD_MODE_FIX_CAM", 47, sE.a() == sE.d ? 12 : 29, "Advanced Build Mode", cu.e, (short)-1);
/*  77 */     V = new cv("ALIGN_SHIP", 48, 46, "Align Ship Cam", cu.d, (short)-1);
/*  78 */     W = new cv("SCREENSHOT_WITH_GUI", 49, 63, "Screenshot (with GUI)", cu.jdField_a_of_type_Cu, (short)-1);
/*  79 */     X = new cv("SCREENSHOT_WITHOUT_GUI", 50, 64, "Screenshot (without GUI)", cu.jdField_a_of_type_Cu, (short)-1);
/*  80 */     Y = new cv("FACTION_MENU", 51, 35, "Open Faction Menu", cu.jdField_a_of_type_Cu, (short)-1);
/*  81 */     Z = new cv("MAP_PANEL", 52, 25, "Map", cu.jdField_a_of_type_Cu, (short)-1);
/*     */ 
/*  18 */     jdField_b_of_type_ArrayOfCv = new cv[] { jdField_a_of_type_Cv, jdField_b_of_type_Cv, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, aa, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z };
/*     */ 
/*  83 */     ObjectArrayList localObjectArrayList = new ObjectArrayList();
/*     */     cv[] arrayOfcv;
/* 173 */     int i2 = (arrayOfcv = values()).length; for (int i3 = 0; i3 < i2; i3++)
/*     */     {
/*     */       cv localcv;
/* 174 */       if ((
/* 174 */         localcv = arrayOfcv[i3]).jdField_a_of_type_Short > 0)
/*     */       {
/* 175 */         localObjectArrayList.add(localcv);
/*     */       }
/*     */     }
/* 178 */     jdField_a_of_type_ArrayOfCv = new cv[localObjectArrayList.size()];
/* 179 */     for (int i1 = 0; i1 < jdField_a_of_type_ArrayOfCv.length; i1++)
/* 180 */       jdField_a_of_type_ArrayOfCv[i1] = ((cv)localObjectArrayList.get(i1));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cv
 * JD-Core Version:    0.6.2
 */