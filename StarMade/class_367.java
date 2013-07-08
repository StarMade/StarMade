import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import org.lwjgl.input.Keyboard;
import org.schema.common.ParseException;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteShort;

public enum class_367
{
  private final int jdField_field_711_of_type_Int;
  private final String jdField_field_711_of_type_JavaLangString;
  private int jdField_field_712_of_type_Int;
  public static final class_367[] field_711;
  private final class_369 jdField_field_711_of_type_Class_369;
  private final short jdField_field_711_of_type_Short;
  
  public final boolean a(Short paramShort)
  {
    if ((!jdField_field_711_of_type_Boolean) && (this.jdField_field_711_of_type_Short <= 0)) {
      throw new AssertionError();
    }
    return (paramShort.shortValue() & this.jdField_field_711_of_type_Short) == this.jdField_field_711_of_type_Short;
  }
  
  public final void a1(RemoteShort paramRemoteShort)
  {
    if (!Keyboard.isCreated()) {
      return;
    }
    if (Keyboard.isKeyDown(this.jdField_field_712_of_type_Int))
    {
      paramRemoteShort.set(Short.valueOf((short)(((Short)paramRemoteShort.get()).shortValue() | this.jdField_field_711_of_type_Short)));
      return;
    }
    paramRemoteShort.set(Short.valueOf((short)(((Short)paramRemoteShort.get()).shortValue() & (this.jdField_field_711_of_type_Short ^ 0xFFFFFFFF))));
  }
  
  public static void a2()
  {
    try
    {
      localObject1 = new String[values().length + 1];
      String[] arrayOfString = new String[values().length + 1];
      Object localObject2 = new File("./keyboard.cfg");
      localObject2 = new BufferedReader(new FileReader((File)localObject2));
      Object localObject3 = null;
      for (int j = 0; (localObject3 = ((BufferedReader)localObject2).readLine()) != null; j++)
      {
        localObject3 = ((String)localObject3).split(" = ", 2);
        localObject1[j] = localObject3[0];
        arrayOfString[j] = localObject3[1].trim();
        if ((j == 0) && (!localObject1[0].equals("#version")))
        {
          System.err.println("UNKNOWN VERSION!! RESETTING KEYS");
          return;
        }
        if ((j == 0) && (localObject1[0].equals("#version")) && (Integer.parseInt(arrayOfString[j]) != 0)) {
          System.err.println("OLD VERSION!! RESETTING KEYS");
        }
        System.err.println("KEY: " + localObject1[j] + " = " + arrayOfString[j]);
      }
      for (j = 1; j < localObject1.length; j++) {
        try
        {
          int i;
          if ((i = Keyboard.getKeyIndex(arrayOfString[j])) == 0) {
            throw new ParseException("Key not known: " + arrayOfString[j]);
          }
          int k = i;
          localObject4 = null;
          valueOf(localObject1[j]).jdField_field_712_of_type_Int = k;
        }
        catch (ParseException localParseException)
        {
          Object localObject4 = null;
          localParseException.printStackTrace();
        }
      }
      ((BufferedReader)localObject2).close();
      return;
    }
    catch (Exception localException)
    {
      Object localObject1;
      (localObject1 = localException).printStackTrace();
      System.err.println("Could not read settings file: using defaults (" + ((Exception)localObject1).getMessage() + ")");
    }
  }
  
  public static void b()
  {
    Object localObject;
    (localObject = new File("./keyboard.cfg")).delete();
    ((File)localObject).createNewFile();
    (localObject = new BufferedWriter(new FileWriter((File)localObject))).write("#version = 0");
    ((BufferedWriter)localObject).newLine();
    for (class_367 localclass_367 : values())
    {
      ((BufferedWriter)localObject).write(localclass_367.name() + " = " + Keyboard.getKeyName(localclass_367.jdField_field_712_of_type_Int));
      ((BufferedWriter)localObject).newLine();
    }
    ((BufferedWriter)localObject).flush();
    ((BufferedWriter)localObject).close();
  }
  
  private class_367(int paramInt, String paramString, class_369 paramclass_369, short paramShort)
  {
    this.jdField_field_711_of_type_Int = paramInt;
    this.jdField_field_711_of_type_JavaLangString = paramString;
    this.jdField_field_712_of_type_Int = this.jdField_field_711_of_type_Int;
    this.jdField_field_711_of_type_Class_369 = paramclass_369;
    this.jdField_field_711_of_type_Short = paramShort;
  }
  
  public final class_369 a3()
  {
    return this.jdField_field_711_of_type_Class_369;
  }
  
  public final String a4()
  {
    return this.jdField_field_711_of_type_JavaLangString;
  }
  
  public final String b1()
  {
    return Keyboard.getKeyName(this.jdField_field_712_of_type_Int);
  }
  
  public final int a5()
  {
    return this.jdField_field_712_of_type_Int;
  }
  
  public final boolean a6()
  {
    return Keyboard.isKeyDown(this.jdField_field_712_of_type_Int);
  }
  
  public final void a7(int paramInt)
  {
    this.jdField_field_712_of_type_Int = paramInt;
  }
  
  public final void a8(RemoteBuffer paramRemoteBuffer, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramRemoteBuffer.add(new RemoteShort((short)(paramBoolean1 ? this.jdField_field_711_of_type_Short : -this.jdField_field_711_of_type_Short), paramBoolean2));
  }
  
  public final boolean a9(int paramInt)
  {
    return this.jdField_field_711_of_type_Short == paramInt;
  }
  
  static
  {
    jdField_field_711_of_type_Class_367 = new class_367("STRAFE_LEFT", 0, 30, "Strafe Left", class_369.field_764, (short)1);
    jdField_field_712_of_type_Class_367 = new class_367("STRAFE_RIGHT", 1, 32, "Strafe right", class_369.field_764, (short)2);
    field_713 = new class_367("FORWARD", 2, 17, "Forward", class_369.field_764, (short)4);
    field_714 = new class_367("BACKWARDS", 3, 31, "Backwards", class_369.field_764, (short)8);
    field_715 = new class_367("UP", 4, 18, "Strafe Up", class_369.field_764, (short)16);
    field_716 = new class_367("DOWN", 5, 16, "Strafe Down", class_369.field_764, (short)32);
    field_717 = new class_367("STRAFE_LEFT_SHIP", 6, 30, "Strafe Left", class_369.field_767, (short)1);
    field_718 = new class_367("STRAFE_RIGHT_SHIP", 7, 32, "Strafe right", class_369.field_767, (short)2);
    field_719 = new class_367("FORWARD_SHIP", 8, 17, "Forward", class_369.field_767, (short)4);
    field_720 = new class_367("BACKWARDS_SHIP", 9, 31, "Backwards", class_369.field_767, (short)8);
    field_721 = new class_367("UP_SHIP", 10, 18, "Strafe Up", class_369.field_767, (short)16);
    field_722 = new class_367("DOWN_SHIP", 11, 16, "Strafe Down", class_369.field_767, (short)32);
    field_723 = new class_367("ROTATE_LEFT_SHIP", 12, 44, "Rotate Left", class_369.field_767, (short)512);
    field_724 = new class_367("ROTATE_RIGHT_SHIP", 13, 45, "Rotate Right", class_369.field_767, (short)1024);
    field_725 = new class_367("DROP_ITEM", 14, 14, "Drop Item", class_369.field_764, (short)-1);
    field_726 = new class_367("BREAK", 15, 42, "Break", class_369.field_767, (short)64);
    field_727 = new class_367("ROLL", 16, 29, "Roll Ship", class_369.field_767, (short)-1);
    field_728 = new class_367("CHANGE_SHIP_MODE", 17, 57, "Change Ship Mode", class_369.field_766, (short)128);
    field_729 = new class_367("JUMP", 18, 57, "Jump", class_369.field_765, (short)256);
    field_730 = new class_367("JUMP_TO_MODULE", 19, 45, "Jump to module", class_369.field_768, (short)-1);
    field_731 = new class_367("FREE_SHIP_CAM", 20, 54, "Free Ship Camera", class_369.field_767, (short)-1);
    field_732 = new class_367("ENTER_SHIP", 21, 19, "Exit Ship", class_369.field_766, (short)-1);
    field_733 = new class_367("ACTIVATE", 22, 19, "Activate Module", class_369.field_765, (short)-1);
    field_734 = new class_367("SPAWN_SHIP", 23, 45, "Spawn Ship", class_369.field_765, (short)-1);
    field_735 = new class_367("SPAWN_SPACE_STATION", 24, 50, "Spawn Space Station", class_369.field_765, (short)-1);
    field_736 = new class_367("SELECT_MODULE", 25, 46, "Select Module", class_369.field_768, (short)-1);
    field_737 = new class_367("CONNECT_MODULE", 26, 47, "Connect Module", class_369.field_768, (short)-1);
    field_738 = new class_367("HELP", 27, 87, "Help Screen", class_369.field_764, (short)-1);
    field_739 = new class_367("SWITCH_COCKPIT_SHIP_NEXT", 28, 200, "Docking Ship", class_369.field_767, (short)-1);
    field_740 = new class_367("SWITCH_COCKPIT_NEXT", 29, 205, "Next Cockpit", class_369.field_767, (short)-1);
    field_741 = new class_367("SWITCH_COCKPIT_PREVIOUS", 30, 203, "Previous Cockpit", class_369.field_767, (short)-1);
    field_742 = new class_367("TEAM_CHANGE", 31, 36, "Change Team", class_369.field_764, (short)-1);
    field_743 = new class_367("CHAT", 32, 28, "Chat", class_369.field_764, (short)-1);
    field_744 = new class_367("SHOP_PANEL", 33, 48, "Shop Menu", class_369.field_764, (short)-1);
    field_745 = new class_367("INVENTORY_PANEL", 34, 23, "Inventory Menu", class_369.field_764, (short)-1);
    field_746 = new class_367("WEAPON_PANEL", 35, 20, "Weapon Menu", class_369.field_764, (short)-1);
    field_747 = new class_367("NAVIGATION_PANEL", 36, 49, "Navigation Menu", class_369.field_764, (short)-1);
    field_748 = new class_367("AI_CONFIG_PANEL", 37, 38, "AI Config Menu", class_369.field_764, (short)-1);
    field_749 = new class_367("SELECT_NEXT_ENTITY", 38, 22, "Select Next Entity", class_369.field_764, (short)-1);
    field_750 = new class_367("SELECT_PREV_ENTITY", 39, 21, "Select Previous Entity", class_369.field_764, (short)-1);
    field_751 = new class_367("SELECT_NEAR_ENTITY", 40, 34, "Select Nearest Entity", class_369.field_764, (short)-1);
    field_752 = new class_367("SELECT_LOOK_ENTITY", 41, 33, "Select Targeted Entity", class_369.field_764, (short)-1);
    field_753 = new class_367("RELEASE_MOUSE", 42, 60, "Release Mouse", class_369.field_764, (short)-1);
    field_754 = new class_367("PLAYER_STATISTICS", 43, 15, "Player Statistics", class_369.field_764, (short)-1);
    field_755 = new class_367("NEXT_CONTROLLER", 44, 205, "Select next controller", class_369.field_768, (short)-1);
    field_756 = new class_367("PREVIOUS_CONTROLLER", 45, 203, "Select prev controller", class_369.field_768, (short)-1);
    field_757 = new class_367("SELECT_CORE", 46, 200, "Select core", class_369.field_768, (short)-1);
    field_758 = new class_367("BUILD_MODE_FIX_CAM", 47, class_1270.a() == class_1270.field_1453 ? 12 : 29, "Advanced Build Mode", class_369.field_768, (short)-1);
    field_759 = new class_367("ALIGN_SHIP", 48, 46, "Align Ship Cam", class_369.field_767, (short)-1);
    field_760 = new class_367("SCREENSHOT_WITH_GUI", 49, 63, "Screenshot (with GUI)", class_369.field_764, (short)-1);
    field_761 = new class_367("SCREENSHOT_WITHOUT_GUI", 50, 64, "Screenshot (without GUI)", class_369.field_764, (short)-1);
    field_762 = new class_367("FACTION_MENU", 51, 35, "Open Faction Menu", class_369.field_764, (short)-1);
    field_763 = new class_367("MAP_PANEL", 52, 25, "Map", class_369.field_764, (short)-1);
    jdField_field_712_of_type_ArrayOfClass_367 = new class_367[] { jdField_field_711_of_type_Class_367, jdField_field_712_of_type_Class_367, field_713, field_714, field_715, field_716, field_717, field_718, field_719, field_720, field_721, field_722, field_723, field_724, field_725, field_726, field_727, field_728, field_729, field_730, field_731, field_732, field_733, field_734, field_735, field_736, field_737, field_738, field_739, field_740, field_741, field_742, field_743, field_744, field_745, field_746, field_747, field_748, field_749, field_750, field_751, field_752, field_753, field_754, field_755, field_756, field_757, field_758, field_759, field_760, field_761, field_762, field_763 };
    ObjectArrayList localObjectArrayList = new ObjectArrayList();
    class_367[] arrayOfclass_367;
    int j = (arrayOfclass_367 = values()).length;
    for (int k = 0; k < j; k++)
    {
      class_367 localclass_367;
      if ((localclass_367 = arrayOfclass_367[k]).jdField_field_711_of_type_Short > 0) {
        localObjectArrayList.add(localclass_367);
      }
    }
    jdField_field_711_of_type_ArrayOfClass_367 = new class_367[localObjectArrayList.size()];
    for (int i = 0; i < jdField_field_711_of_type_ArrayOfClass_367.length; i++) {
      jdField_field_711_of_type_ArrayOfClass_367[i] = ((class_367)localObjectArrayList.get(i));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_367
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */