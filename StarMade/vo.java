/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Locale;
/*     */ import org.schema.game.network.objects.NetworkGameState;
/*     */ import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*     */ 
/*     */ public enum vo
/*     */ {
/*     */   private static boolean jdField_a_of_type_Boolean;
/*     */   private final String jdField_a_of_type_JavaLangString;
/*     */   private xA jdField_a_of_type_XA;
/*     */   private Object jdField_a_of_type_JavaLangObject;
/*     */ 
/*     */   private vo(String paramxA, Object arg4, xA arg5)
/*     */   {
/*  71 */     this.jdField_a_of_type_JavaLangString = paramxA;
/*     */     Object localObject2;
/*  72 */     this.jdField_a_of_type_XA = localObject2;
/*     */     Object localObject1;
/*  73 */     a(localObject1);
/*     */   }
/*     */ 
/*     */   public static void a(NetworkGameState paramNetworkGameState)
/*     */   {
/*  97 */     if (jdField_a_of_type_Boolean) {
/*  98 */       for (vo localvo : values()) {
/*  99 */         if ((!jdField_b_of_type_Boolean) && (paramNetworkGameState == null)) throw new AssertionError();
/*     */         RemoteStringArray localRemoteStringArray;
/* 100 */         (
/* 101 */           localRemoteStringArray = new RemoteStringArray(2, paramNetworkGameState))
/* 101 */           .set(0, localvo.name());
/* 102 */         localRemoteStringArray.set(1, localvo.jdField_a_of_type_JavaLangObject.toString());
/* 103 */         paramNetworkGameState.serverConfig.add(localRemoteStringArray);
/*     */       }
/* 105 */       jdField_a_of_type_Boolean = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void b(NetworkGameState paramNetworkGameState) {
/* 110 */     for (int i1 = 0; i1 < paramNetworkGameState.serverConfig.getReceiveBuffer().size(); i1++) {
/* 111 */       RemoteStringArray localRemoteStringArray = (RemoteStringArray)paramNetworkGameState.serverConfig.getReceiveBuffer().get(i1);
/*     */       try
/*     */       {
/* 114 */         valueOf((String)localRemoteStringArray.get(0).get()).a((String)localRemoteStringArray.get(1).get());
/*     */       }
/*     */       catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/* 117 */         localStateParameterNotFoundException.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a() {
/*     */     try {
/* 122 */       String[] arrayOfString1 = new String[values().length];
/* 123 */       String[] arrayOfString2 = new String[values().length];
/* 124 */       Object localObject1 = new File("./server.cfg");
/* 125 */       localObject1 = new BufferedReader(new FileReader((File)localObject1));
/* 126 */       Object localObject2 = null;
/* 127 */       int i1 = 0;
/* 128 */       while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
/* 129 */         if (!((String)localObject2).startsWith("//"))
/*     */         {
/* 133 */           localObject2 = ((String)localObject2).substring(0, ((String)localObject2).indexOf("//"))
/* 133 */             .split("=", 2);
/* 134 */           arrayOfString1[i1] = localObject2[0].trim();
/* 135 */           arrayOfString2[i1] = localObject2[1].trim();
/* 136 */           i1++;
/*     */         }
/*     */       }
/* 139 */       for (i1 = 0; i1 < arrayOfString1.length; i1++) {
/*     */         try
/*     */         {
/* 142 */           if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("ADMIN_AUTHENTICATION_NEEDED"))) {
/* 143 */             arrayOfString1[i1] = "USE_STARMADE_AUTHENTICATION";
/*     */           }
/* 145 */           if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("AI_DESTRUCTION_LOOT_COUNT_MULTIPLYER"))) {
/* 146 */             arrayOfString1[i1] = "AI_DESTRUCTION_LOOT_COUNT_MULTIPLIER";
/*     */           }
/* 148 */           if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("AI_DESTRUCTION_LOOT_STACK_MULTIPLYER"))) {
/* 149 */             arrayOfString1[i1] = "AI_DESTRUCTION_LOOT_STACK_MULTIPLIER";
/*     */           }
/* 151 */           if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("CHEST_LOOT_COUNT_MULTIPLYER"))) {
/* 152 */             arrayOfString1[i1] = "CHEST_LOOT_COUNT_MULTIPLIER";
/*     */           }
/* 154 */           if ((arrayOfString1[i1] != null) && (arrayOfString1[i1].equals("CHEST_LOOT_STACK_MULTIPLYER"))) {
/* 155 */             arrayOfString1[i1] = "CHEST_LOOT_STACK_MULTIPLIER";
/*     */           }
/* 157 */           valueOf(arrayOfString1[i1]).a(arrayOfString2[i1]);
/*     */         } catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/* 159 */           System.err.println("[SERVER][CONFIG] No value for " + arrayOfString1[i1] + ". Creating default entry.");
/*     */         } catch (NullPointerException localNullPointerException) {
/* 161 */           System.err.println("[SERVER][CONFIG] No value for " + arrayOfString1[i1] + ". Creating default entry.");
/*     */         }
/*     */       }
/* 164 */       ((BufferedReader)localObject1).close();
/*     */       return;
/*     */     } catch (Exception localException) {
/* 168 */       localException.printStackTrace();
/*     */ 
/* 167 */       System.err.println("Could not read settings file: using defaults");
/*     */     }
/*     */   }
/*     */ 
/* 171 */   public static void b() { (
/* 172 */       localObject = new File("./server.cfg"))
/* 172 */       .delete();
/* 173 */     ((File)localObject).createNewFile();
/* 174 */     Object localObject = new BufferedWriter(new FileWriter((File)localObject));
/* 175 */     for (vo localvo : values()) {
/* 176 */       ((BufferedWriter)localObject).write(localvo.name() + " = " + localvo.jdField_a_of_type_JavaLangObject + " //" + localvo.jdField_a_of_type_JavaLangString);
/* 177 */       ((BufferedWriter)localObject).newLine();
/*     */     }
/* 179 */     ((BufferedWriter)localObject).flush();
/* 180 */     ((BufferedWriter)localObject).close();
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 240 */     if ((this.jdField_a_of_type_JavaLangObject instanceof Boolean)) {
/* 241 */       a(Boolean.valueOf(paramBoolean));
/* 242 */       return;
/*     */     }
/* 244 */     if (!jdField_b_of_type_Boolean) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public final Object a()
/*     */   {
/* 253 */     return this.jdField_a_of_type_JavaLangObject;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 274 */     return ((this.jdField_a_of_type_JavaLangObject instanceof Boolean)) && (((Boolean)this.jdField_a_of_type_JavaLangObject).booleanValue());
/*     */   }
/*     */ 
/*     */   public final void a(Object paramObject)
/*     */   {
/* 281 */     if (paramObject != this.jdField_a_of_type_JavaLangObject) {
/* 282 */       jdField_a_of_type_Boolean = true;
/*     */     }
/*     */ 
/* 286 */     this.jdField_a_of_type_JavaLangObject = paramObject;
/*     */   }
/*     */ 
/*     */   private void a(String paramString)
/*     */   {
/* 294 */     a(this.jdField_a_of_type_XA.a(paramString));
/*     */   }
/*     */ 
/*     */   public final String toString()
/*     */   {
/* 304 */     return name().toLowerCase(Locale.ENGLISH) + " (" + this.jdField_a_of_type_XA.a() + ") " + this.jdField_a_of_type_JavaLangObject;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  23 */     jdField_b_of_type_Boolean = !vo.class.desiredAssertionStatus();
/*     */ 
/*  25 */     jdField_a_of_type_Vo = new vo("PROTECT_STARTING_SECTOR", 0, "Protects the starting sector", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  26 */     jdField_b_of_type_Vo = new vo("ENABLE_SIMULATION", 1, "Universe AI simulation", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  27 */     c = new vo("CONCURRENT_SIMULATION", 2, "How many simulation groups may be in the universe simultaniously (performance)", Integer.valueOf(256), new xz());
/*     */ 
/*  29 */     d = new vo("ENEMY_SPAWNING", 3, "Enables enemy spawing", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*     */ 
/*  31 */     e = new vo("SIMULATION_SPAWN_DELAY", 4, "How much seconds between simulation spawn ticks", Integer.valueOf(420), new xz());
/*  32 */     f = new vo("SECTOR_INACTIVE_TIMEOUT", 5, "Time in secs after which sectors go inactive (-1 = off)", Integer.valueOf(20), new xz());
/*  33 */     g = new vo("SECTOR_INACTIVE_CLEANUP_TIMEOUT", 6, "Time in secs after which inactive sectors are completely removed from memory (-1 = off)", Integer.valueOf(10), new xz());
/*  34 */     h = new vo("USE_STARMADE_AUTHENTICATION", 7, "allow star-made.org authentication", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  35 */     i = new vo("STARTING_CREDITS", 8, "How many credits a new player has", Integer.valueOf(25000), new xz());
/*  36 */     j = new vo("DEFAULT_BLUEPRINT_ENEMY_USE", 9, "Default option to use uploaded ships in waves", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  37 */     k = new vo("LOCK_FACTION_SHIPS", 10, "If true, ships of other factions cant be edited, activated, or entered", Boolean.valueOf(true), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  38 */     l = new vo("DEBUG_FSM_STATE", 11, "transfer debug FSM state. Turing this on may slow doen network", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  39 */     m = new vo("PHYSICS_SHAPE_CASTING_TUNNELING_PREVENTION", 12, "Makes a convex cast for hight speed object to prevent clipping. High Cost. (Bugged right now, so dont turn it on)", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  40 */     n = new vo("CATALOG_SLOTS_PER_PLAYER", 13, "How many slots per player for saved ships (-1 for unlimited)", Integer.valueOf(-1), new xz());
/*     */ 
/*  42 */     o = new vo("CATALOG_NAME_COLLISION_HANDLING", 14, "if off, saving with an existing entry is denied, if on the name is automatically changed by adding numbers on the end", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  43 */     p = new vo("SECTOR_AUTOSAVE_SEC", 15, "Time interval in secs the server will autosave (-1 for never)", Integer.valueOf(300), new xz());
/*  44 */     q = new vo("PHYSICS_SLOWDOWN_THRESHOLD", 16, "Milliseconds a collision test may take before anti-slowdown mode is activated", Integer.valueOf(40), new xz());
/*  45 */     r = new vo("THRUST_SPEED_LIMIT", 17, "How fast ships, etc. may go in km/h. Too high values may induce physics tunneling effects", Integer.valueOf(50), new xz());
/*  46 */     s = new vo("MAX_CLIENTS", 18, "Max number of clients allowed on this server", Integer.valueOf(32), new xz());
/*  47 */     t = new vo("SUPER_ADMIN_PASSWORD_USE", 19, "Enable super admin for this server", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  48 */     u = new vo("SUPER_ADMIN_PASSWORD", 20, "Super admin password for this server", "mypassword", new xC());
/*  49 */     v = new vo("SERVER_LISTEN_IP", 21, "Enter specific ip for the server to listen to. use \"all\" to listen on every ip", "all", new xC());
/*  50 */     w = new vo("SOCKET_BUFFER_SIZE", 22, "buffer size of incoming and outgoing data per socket", Integer.valueOf(262144), new xz());
/*  51 */     x = new vo("PHYSICS_LINEAR_DAMPING", 23, "how much object slow down naturally (must be between 0 and 1): 0 is no slowdown", Float.valueOf(0.09F), new xy());
/*  52 */     y = new vo("PHYSICS_ROTATIONAL_DAMPING", 24, "how much object slow down naturally (must be between 0 and 1): 0 is no slowdown", Float.valueOf(0.09F), new xy());
/*  53 */     z = new vo("AI_DESTRUCTION_LOOT_COUNT_MULTIPLIER", 25, "multiply amount of items in a loot stack. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new xy());
/*  54 */     A = new vo("AI_DESTRUCTION_LOOT_STACK_MULTIPLIER", 26, "multiply amount of items spawned after AI destruction. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new xy());
/*  55 */     B = new vo("CHEST_LOOT_COUNT_MULTIPLIER", 27, "multiply amount of items in a loot stack. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new xy());
/*  56 */     C = new vo("CHEST_LOOT_STACK_MULTIPLIER", 28, "multiply amount of items spawned in chests of generated chests. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new xy());
/*  57 */     D = new vo("USE_WHITELIST", 29, "only names/ips from whitelist.txt are allowed", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*  58 */     E = new vo("FILTER_CONNECTION_MESSAGES", 30, "don't display join/disconnect messages", Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
/*     */ 
/*  23 */     jdField_a_of_type_ArrayOfVo = new vo[] { jdField_a_of_type_Vo, jdField_b_of_type_Vo, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E };
/*     */ 
/*  61 */     jdField_a_of_type_Boolean = true;
/*     */ 
/* 191 */     new ArrayList();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vo
 * JD-Core Version:    0.6.2
 */