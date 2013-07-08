import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import org.schema.game.network.objects.NetworkGameState;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public enum class_1057
{
  private static boolean jdField_field_1318_of_type_Boolean;
  private final String jdField_field_1318_of_type_JavaLangString;
  private class_1014 jdField_field_1318_of_type_Class_1014;
  private Object jdField_field_1318_of_type_JavaLangObject;
  
  private class_1057(String paramString, Object paramObject, class_1014 paramclass_1014)
  {
    this.jdField_field_1318_of_type_JavaLangString = paramString;
    this.jdField_field_1318_of_type_Class_1014 = paramclass_1014;
    a5(paramObject);
  }
  
  public static void a(NetworkGameState paramNetworkGameState)
  {
    if (jdField_field_1318_of_type_Boolean)
    {
      for (class_1057 localclass_1057 : values())
      {
        if ((!jdField_field_1319_of_type_Boolean) && (paramNetworkGameState == null)) {
          throw new AssertionError();
        }
        RemoteStringArray localRemoteStringArray;
        (localRemoteStringArray = new RemoteStringArray(2, paramNetworkGameState)).set(0, localclass_1057.name());
        localRemoteStringArray.set(1, localclass_1057.jdField_field_1318_of_type_JavaLangObject.toString());
        paramNetworkGameState.serverConfig.add(localRemoteStringArray);
      }
      jdField_field_1318_of_type_Boolean = false;
    }
  }
  
  public static void b(NetworkGameState paramNetworkGameState)
  {
    for (int i = 0; i < paramNetworkGameState.serverConfig.getReceiveBuffer().size(); i++)
    {
      RemoteStringArray localRemoteStringArray = (RemoteStringArray)paramNetworkGameState.serverConfig.getReceiveBuffer().get(i);
      try
      {
        valueOf((String)localRemoteStringArray.get(0).get()).a6((String)localRemoteStringArray.get(1).get());
      }
      catch (StateParameterNotFoundException localStateParameterNotFoundException)
      {
        localStateParameterNotFoundException;
      }
    }
  }
  
  public static void a1()
  {
    try
    {
      String[] arrayOfString1 = new String[values().length];
      String[] arrayOfString2 = new String[values().length];
      Object localObject1 = new File("./server.cfg");
      localObject1 = new BufferedReader(new FileReader((File)localObject1));
      Object localObject2 = null;
      int i = 0;
      while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
        if (!((String)localObject2).startsWith("//"))
        {
          localObject2 = ((String)localObject2).substring(0, ((String)localObject2).indexOf("//")).split("=", 2);
          arrayOfString1[i] = localObject2[0].trim();
          arrayOfString2[i] = localObject2[1].trim();
          i++;
        }
      }
      for (i = 0; i < arrayOfString1.length; i++) {
        try
        {
          if ((arrayOfString1[i] != null) && (arrayOfString1[i].equals("ADMIN_AUTHENTICATION_NEEDED"))) {
            arrayOfString1[i] = "USE_STARMADE_AUTHENTICATION";
          }
          if ((arrayOfString1[i] != null) && (arrayOfString1[i].equals("AI_DESTRUCTION_LOOT_COUNT_MULTIPLYER"))) {
            arrayOfString1[i] = "AI_DESTRUCTION_LOOT_COUNT_MULTIPLIER";
          }
          if ((arrayOfString1[i] != null) && (arrayOfString1[i].equals("AI_DESTRUCTION_LOOT_STACK_MULTIPLYER"))) {
            arrayOfString1[i] = "AI_DESTRUCTION_LOOT_STACK_MULTIPLIER";
          }
          if ((arrayOfString1[i] != null) && (arrayOfString1[i].equals("CHEST_LOOT_COUNT_MULTIPLYER"))) {
            arrayOfString1[i] = "CHEST_LOOT_COUNT_MULTIPLIER";
          }
          if ((arrayOfString1[i] != null) && (arrayOfString1[i].equals("CHEST_LOOT_STACK_MULTIPLYER"))) {
            arrayOfString1[i] = "CHEST_LOOT_STACK_MULTIPLIER";
          }
          valueOf(arrayOfString1[i]).a6(arrayOfString2[i]);
        }
        catch (StateParameterNotFoundException localStateParameterNotFoundException)
        {
          System.err.println("[SERVER][CONFIG] No value for " + arrayOfString1[i] + ". Creating default entry.");
        }
        catch (NullPointerException localNullPointerException)
        {
          System.err.println("[SERVER][CONFIG] No value for " + arrayOfString1[i] + ". Creating default entry.");
        }
      }
      ((BufferedReader)localObject1).close();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      System.err.println("Could not read settings file: using defaults");
    }
  }
  
  public static void b1()
  {
    (localObject = new File("./server.cfg")).delete();
    ((File)localObject).createNewFile();
    Object localObject = new BufferedWriter(new FileWriter((File)localObject));
    for (class_1057 localclass_1057 : values())
    {
      ((BufferedWriter)localObject).write(localclass_1057.name() + " = " + localclass_1057.jdField_field_1318_of_type_JavaLangObject + " //" + localclass_1057.jdField_field_1318_of_type_JavaLangString);
      ((BufferedWriter)localObject).newLine();
    }
    ((BufferedWriter)localObject).flush();
    ((BufferedWriter)localObject).close();
  }
  
  public final void a2(boolean paramBoolean)
  {
    if ((this.jdField_field_1318_of_type_JavaLangObject instanceof Boolean))
    {
      a5(Boolean.valueOf(paramBoolean));
      return;
    }
    if (!jdField_field_1319_of_type_Boolean) {
      throw new AssertionError();
    }
  }
  
  public final Object a3()
  {
    return this.jdField_field_1318_of_type_JavaLangObject;
  }
  
  public final boolean a4()
  {
    return ((this.jdField_field_1318_of_type_JavaLangObject instanceof Boolean)) && (((Boolean)this.jdField_field_1318_of_type_JavaLangObject).booleanValue());
  }
  
  public final void a5(Object paramObject)
  {
    if (paramObject != this.jdField_field_1318_of_type_JavaLangObject) {
      jdField_field_1318_of_type_Boolean = true;
    }
    this.jdField_field_1318_of_type_JavaLangObject = paramObject;
  }
  
  private void a6(String paramString)
  {
    a5(this.jdField_field_1318_of_type_Class_1014.a(paramString));
  }
  
  public final String toString()
  {
    return name().toLowerCase(Locale.ENGLISH) + " (" + this.jdField_field_1318_of_type_Class_1014.a1() + ") " + this.jdField_field_1318_of_type_JavaLangObject;
  }
  
  static
  {
    jdField_field_1319_of_type_Boolean = !vo.class.desiredAssertionStatus();
    jdField_field_1318_of_type_Class_1057 = new class_1057("PROTECT_STARTING_SECTOR", 0, "Protects the starting sector", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    jdField_field_1319_of_type_Class_1057 = new class_1057("ENABLE_SIMULATION", 1, "Universe AI simulation", Boolean.valueOf(true), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1320 = new class_1057("CONCURRENT_SIMULATION", 2, "How many simulation groups may be in the universe simultaniously (performance)", Integer.valueOf(256), new class_1397());
    field_1321 = new class_1057("ENEMY_SPAWNING", 3, "Enables enemy spawing", Boolean.valueOf(true), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1322 = new class_1057("SIMULATION_SPAWN_DELAY", 4, "How much seconds between simulation spawn ticks", Integer.valueOf(420), new class_1397());
    field_1323 = new class_1057("SIMULATION_TRADING_FILLS_SHOPS", 5, "Trading guild will deliver stock to shops", Boolean.valueOf(true), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1324 = new class_1057("SECTOR_INACTIVE_TIMEOUT", 6, "Time in secs after which sectors go inactive (-1 = off)", Integer.valueOf(20), new class_1397());
    field_1325 = new class_1057("SECTOR_INACTIVE_CLEANUP_TIMEOUT", 7, "Time in secs after which inactive sectors are completely removed from memory (-1 = off)", Integer.valueOf(10), new class_1397());
    field_1326 = new class_1057("USE_STARMADE_AUTHENTICATION", 8, "allow star-made.org authentication", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1327 = new class_1057("STARTING_CREDITS", 9, "How many credits a new player has", Integer.valueOf(25000), new class_1397());
    field_1328 = new class_1057("DEFAULT_BLUEPRINT_ENEMY_USE", 10, "Default option to use uploaded ships in waves", Boolean.valueOf(true), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1329 = new class_1057("LOCK_FACTION_SHIPS", 11, "If true, ships of other factions cant be edited, activated, or entered", Boolean.valueOf(true), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1330 = new class_1057("DEBUG_FSM_STATE", 12, "transfer debug FSM state. Turing this on may slow doen network", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1331 = new class_1057("PHYSICS_SHAPE_CASTING_TUNNELING_PREVENTION", 13, "Makes a convex cast for hight speed object to prevent clipping. High Cost. (Bugged right now, so dont turn it on)", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1332 = new class_1057("CATALOG_SLOTS_PER_PLAYER", 14, "How many slots per player for saved ships (-1 for unlimited)", Integer.valueOf(-1), new class_1397());
    field_1333 = new class_1057("CATALOG_NAME_COLLISION_HANDLING", 15, "if off, saving with an existing entry is denied, if on the name is automatically changed by adding numbers on the end", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1334 = new class_1057("SECTOR_AUTOSAVE_SEC", 16, "Time interval in secs the server will autosave (-1 for never)", Integer.valueOf(300), new class_1397());
    field_1335 = new class_1057("PHYSICS_SLOWDOWN_THRESHOLD", 17, "Milliseconds a collision test may take before anti-slowdown mode is activated", Integer.valueOf(40), new class_1397());
    field_1336 = new class_1057("THRUST_SPEED_LIMIT", 18, "How fast ships, etc. may go in km/h. Too high values may induce physics tunneling effects", Integer.valueOf(50), new class_1397());
    field_1337 = new class_1057("MAX_CLIENTS", 19, "Max number of clients allowed on this server", Integer.valueOf(32), new class_1397());
    field_1338 = new class_1057("SUPER_ADMIN_PASSWORD_USE", 20, "Enable super admin for this server", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1339 = new class_1057("SUPER_ADMIN_PASSWORD", 21, "Super admin password for this server", "mypassword", new class_1016());
    field_1340 = new class_1057("SERVER_LISTEN_IP", 22, "Enter specific ip for the server to listen to. use \"all\" to listen on every ip", "all", new class_1016());
    field_1341 = new class_1057("SOCKET_BUFFER_SIZE", 23, "buffer size of incoming and outgoing data per socket", Integer.valueOf(65536), new class_1397());
    field_1342 = new class_1057("PHYSICS_LINEAR_DAMPING", 24, "how much object slow down naturally (must be between 0 and 1): 0 is no slowdown", Float.valueOf(0.09F), new class_1400());
    field_1343 = new class_1057("PHYSICS_ROTATIONAL_DAMPING", 25, "how much object slow down naturally (must be between 0 and 1): 0 is no slowdown", Float.valueOf(0.09F), new class_1400());
    field_1344 = new class_1057("AI_DESTRUCTION_LOOT_COUNT_MULTIPLIER", 26, "multiply amount of items in a loot stack. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new class_1400());
    field_1345 = new class_1057("AI_DESTRUCTION_LOOT_STACK_MULTIPLIER", 27, "multiply amount of items spawned after AI destruction. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new class_1400());
    field_1346 = new class_1057("CHEST_LOOT_COUNT_MULTIPLIER", 28, "multiply amount of items in a loot stack. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new class_1400());
    field_1347 = new class_1057("CHEST_LOOT_STACK_MULTIPLIER", 29, "multiply amount of items spawned in chests of generated chests. use values smaller 1 for less and 0 for none", Float.valueOf(0.9F), new class_1400());
    field_1348 = new class_1057("USE_WHITELIST", 30, "only names/ips from whitelist.txt are allowed", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1349 = new class_1057("FILTER_CONNECTION_MESSAGES", 31, "don't display join/disconnect messages", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1350 = new class_1057("USE_UDP", 32, "Use 'User Datagram Protocol' (UDP) instead of 'Transmission Control Protocol' (TCP) for connections", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1351 = new class_1057("AUTO_KICK_MODIFIED_BLUEPRINT_USE", 33, "Kick players that spawn modified blueprints", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1352 = new class_1057("AUTO_BAN_ID_MODIFIED_BLUEPRINT_USE", 34, "Ban player by name that spawn modified blueprints", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1353 = new class_1057("AUTO_BAN_IP_MODIFIED_BLUEPRINT_USE", 35, "Ban player by IP that spawn modified blueprints", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1354 = new class_1057("REMOVE_MODIFIED_BLUEPRINTS", 36, "Auto-removes a modified blueprint", Boolean.valueOf(true), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1355 = new class_1057("TCP_NODELAY", 37, "Naggles algorithm (WARNING: only change when you know what you're doing)", Boolean.valueOf(true), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    field_1356 = new class_1057("PING_FLUSH", 38, "flushes ping/pong immediately (WARNING: only change when you know what you're doing)", Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }));
    jdField_field_1318_of_type_ArrayOfClass_1057 = new class_1057[] { jdField_field_1318_of_type_Class_1057, jdField_field_1319_of_type_Class_1057, field_1320, field_1321, field_1322, field_1323, field_1324, field_1325, field_1326, field_1327, field_1328, field_1329, field_1330, field_1331, field_1332, field_1333, field_1334, field_1335, field_1336, field_1337, field_1338, field_1339, field_1340, field_1341, field_1342, field_1343, field_1344, field_1345, field_1346, field_1347, field_1348, field_1349, field_1350, field_1351, field_1352, field_1353, field_1354, field_1355, field_1356 };
    jdField_field_1318_of_type_Boolean = true;
    new ArrayList();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1057
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */