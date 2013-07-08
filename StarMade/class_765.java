import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;
import org.schema.game.common.data.player.faction.FactionFileOutdatedException;
import org.schema.game.common.data.player.faction.FactionNotFoundException;
import org.schema.game.network.objects.NetworkGameState;
import org.schema.game.network.objects.remote.RemoteFaction;
import org.schema.game.network.objects.remote.RemoteFactionBuffer;
import org.schema.game.network.objects.remote.RemoteFactionInvitation;
import org.schema.game.network.objects.remote.RemoteFactionInvitationBuffer;
import org.schema.game.network.objects.remote.RemoteFactionNewsPost;
import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
import org.schema.game.network.objects.remote.RemoteFactionRoles;
import org.schema.game.network.objects.remote.RemoteFactionRolesBuffer;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.game.server.controller.GameServerController;
import org.schema.game.server.data.PlayerNotFountException;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteStringArray;
import org.schema.schine.network.server.ServerMessage;

public class class_765
  extends Observable
  implements class_80
{
  private static int jdField_field_136_of_type_Int;
  private boolean jdField_field_136_of_type_Boolean;
  private final HashSet jdField_field_136_of_type_JavaUtilHashSet = new HashSet();
  private final HashMap jdField_field_136_of_type_JavaUtilHashMap = new HashMap();
  private final Long2ObjectOpenHashMap jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap = new Long2ObjectOpenHashMap();
  private final Long2ObjectOpenHashMap jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap = new Long2ObjectOpenHashMap();
  private final ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_139_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_182_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_183_of_type_JavaUtilArrayList = new ArrayList();
  private final HashMap jdField_field_139_of_type_JavaUtilHashMap = new HashMap();
  private final class_800 jdField_field_136_of_type_Class_800;
  private final ArrayList jdField_field_184_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList field_185 = new ArrayList();
  private final ArrayList field_186 = new ArrayList();
  private final ArrayList field_192 = new ArrayList();
  private final ArrayList field_193 = new ArrayList();
  private final ArrayList field_194 = new ArrayList();
  private final ArrayList field_196 = new ArrayList();
  private final ArrayList field_197 = new ArrayList();
  private final ArrayList field_198 = new ArrayList();
  private final ArrayList field_199 = new ArrayList();
  private boolean jdField_field_139_of_type_Boolean;
  private boolean jdField_field_182_of_type_Boolean;
  private boolean jdField_field_183_of_type_Boolean;
  private static Random jdField_field_136_of_type_JavaUtilRandom = new Random();
  
  public final void a13()
  {
    this.jdField_field_136_of_type_Boolean = true;
  }
  
  public static synchronized int a3()
  {
    return jdField_field_136_of_type_Int++;
  }
  
  public class_765(class_800 paramclass_800)
  {
    this.jdField_field_136_of_type_Class_800 = paramclass_800;
    if (paramclass_800.isOnServer())
    {
      try
      {
        paramclass_800 = ((class_1041)paramclass_800.getState()).a59().a46("FACTIONS", "fac");
        fromTagStructure(paramclass_800);
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      catch (EntityNotFountException paramclass_800)
      {
        System.err.println("[SERVER] NO FACTIONS FOUND ON DISK: " + paramclass_800.getMessage());
        e2();
      }
      catch (FactionFileOutdatedException paramclass_800)
      {
        System.err.println("[SERVER] NO FACTIONS FOUND ON DISK (outdated): " + paramclass_800.getMessage());
        e2();
      }
      if (!this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.containsKey(class_760.a150(-1, -2))) {
        this.jdField_field_136_of_type_JavaUtilArrayList.add(new class_760(-1, -2, (byte)1));
      }
    }
  }
  
  private void e2()
  {
    class_773 localclass_7731;
    (localclass_7731 = new class_773(-1, "Pirates", "Pirates", "Ravaging space pirates")).b13(true);
    class_773 localclass_7732 = new class_773(-2, "Trading guild", "Trading guild", "The intergalactic trading guild");
    this.jdField_field_139_of_type_JavaUtilHashMap.put(Integer.valueOf(localclass_7732.a3()), localclass_7732);
    this.jdField_field_139_of_type_JavaUtilHashMap.put(Integer.valueOf(localclass_7731.a3()), localclass_7731);
    this.jdField_field_136_of_type_JavaUtilArrayList.add(new class_760(-1, -2, (byte)1));
  }
  
  public final void a152(Integer paramInteger)
  {
    class_773 localclass_773;
    if ((localclass_773 = (class_773)this.jdField_field_139_of_type_JavaUtilHashMap.get(paramInteger)) != null)
    {
      b26(localclass_773);
      return;
    }
    throw new FactionNotFoundException(paramInteger);
  }
  
  public final void a153(class_773 paramclass_773)
  {
    synchronized (this.jdField_field_184_of_type_JavaUtilArrayList)
    {
      this.jdField_field_184_of_type_JavaUtilArrayList.add(paramclass_773);
      return;
    }
  }
  
  private void b26(class_773 paramclass_773)
  {
    synchronized (this.field_185)
    {
      this.field_185.add(paramclass_773);
      return;
    }
  }
  
  public final void a154(class_777 paramclass_777)
  {
    synchronized (this.field_194)
    {
      this.field_194.add(paramclass_777);
      return;
    }
  }
  
  public final void b27(class_777 paramclass_777)
  {
    this.jdField_field_136_of_type_Class_800.a52().factionInviteDel.add(new RemoteFactionInvitation(paramclass_777, this.jdField_field_136_of_type_Class_800.a52()));
  }
  
  public final void c16(class_777 paramclass_777)
  {
    this.jdField_field_136_of_type_Class_800.a52().factionInviteAdd.add(new RemoteFactionInvitation(paramclass_777, this.jdField_field_136_of_type_Class_800.a52()));
  }
  
  public final void b4()
  {
    Object localObject16;
    Object localObject23;
    Object localObject1;
    if (this.jdField_field_136_of_type_Boolean)
    {
      localObject16 = this;
      localObject23 = null;
      Iterator localIterator = ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).b10().values().iterator();
      while (localIterator.hasNext())
      {
        class_748 localclass_748 = (class_748)localIterator.next();
        localObject1 = ((class_765)localObject16).jdField_field_139_of_type_JavaUtilHashMap.values().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          class_773 localclass_7735;
          if (((localclass_7735 = (class_773)((Iterator)localObject1).next()).a3() != localclass_748.h1()) && (localclass_7735.a162().containsKey(localclass_748.getName())))
          {
            localclass_7735.a177(localclass_748.getName(), ((class_765)localObject16).jdField_field_136_of_type_Class_800);
            if (localclass_7735.a162().size() == 0) {
              ((class_765)localObject16).b26(localclass_7735);
            }
          }
        }
      }
      this.jdField_field_136_of_type_Boolean = false;
    }
    if (!this.field_192.isEmpty()) {
      synchronized (this.field_192)
      {
        while (!this.field_192.isEmpty())
        {
          localObject1 = (class_771)this.field_192.remove(0);
          localObject23 = null;
          System.err.println("[FACTIONMANAGER] trying to add news entry: " + localObject1 + " on " + this.jdField_field_136_of_type_Class_800.getState() + " -> fid(" + ((class_771)localObject1).b5() + ")");
          if (!this.jdField_field_136_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(((class_771)localObject1).b5()))) {
            this.jdField_field_136_of_type_JavaUtilHashMap.put(Integer.valueOf(((class_771)localObject1).b5()), new TreeSet());
          }
          ((TreeSet)this.jdField_field_136_of_type_JavaUtilHashMap.get(Integer.valueOf(((class_771)localObject1).b5()))).add(localObject1);
          if (this.jdField_field_136_of_type_Class_800.isOnServer())
          {
            this.jdField_field_136_of_type_Class_800.a52().factionNewsPosts.add(new RemoteFactionNewsPost((class_771)localObject1, this.jdField_field_136_of_type_Class_800.a52()));
          }
          else
          {
            localObject16 = ((class_371)this.jdField_field_136_of_type_Class_800.getState()).a20();
            if ((((class_771)localObject1).a28() > ((class_748)localObject16).a28()) && (((class_771)localObject1).b5() == ((class_748)localObject16).h1())) {
              ((class_371)this.jdField_field_136_of_type_Class_800.getState()).a4().c1("A news post has been posted\non your faction board");
            }
          }
          System.err.println("[FACTIONMANAGER] updated news on " + this.jdField_field_136_of_type_Class_800.getState() + " for factionID " + ((class_771)localObject1).b5());
          this.jdField_field_182_of_type_Boolean = true;
        }
      }
    }
    if (!this.field_193.isEmpty()) {
      synchronized (this.field_193)
      {
        while (!this.field_193.isEmpty())
        {
          localObject1 = (class_777)this.field_193.remove(0);
          synchronized (this.jdField_field_136_of_type_JavaUtilHashSet)
          {
            this.jdField_field_136_of_type_JavaUtilHashSet.remove(localObject1);
            this.jdField_field_136_of_type_JavaUtilHashSet.add(localObject1);
          }
          this.jdField_field_139_of_type_Boolean = true;
          if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
            this.jdField_field_136_of_type_Class_800.a52().factionInviteAdd.add(new RemoteFactionInvitation((class_777)localObject1, this.jdField_field_136_of_type_Class_800.a52()));
          }
        }
      }
    }
    Object localObject25;
    if (!this.field_194.isEmpty()) {
      synchronized (this.field_194)
      {
        while (!this.field_194.isEmpty())
        {
          localObject1 = (class_777)this.field_194.remove(0);
          synchronized (this.jdField_field_136_of_type_JavaUtilHashSet)
          {
            boolean bool = this.jdField_field_136_of_type_JavaUtilHashSet.remove(localObject1);
            localObject25 = null;
            System.err.println("[FactionManager] Removing faction invitation " + localObject1 + " on " + this.jdField_field_136_of_type_Class_800.getState() + "; success: " + bool);
            if (!bool) {
              System.err.println("[FactionManager] Failed to delete invitation: " + localObject1 + ": " + this.jdField_field_136_of_type_JavaUtilHashSet);
            }
          }
          this.jdField_field_139_of_type_Boolean = true;
          if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
            this.jdField_field_136_of_type_Class_800.a52().factionInviteDel.add(new RemoteFactionInvitation((class_777)localObject1, this.jdField_field_136_of_type_Class_800.a52()));
          }
        }
      }
    }
    if (this.jdField_field_139_of_type_Boolean)
    {
      System.err.println("Faction Invites Changed");
      setChanged();
      notifyObservers("INVITATION_UPDATE");
      this.jdField_field_139_of_type_Boolean = false;
    }
    if (!this.field_197.isEmpty()) {
      synchronized (this.field_197)
      {
        while (!this.field_197.isEmpty())
        {
          class_769 localclass_769 = (class_769)this.field_197.remove(0);
          if ((??? = (class_773)this.jdField_field_139_of_type_JavaUtilHashMap.get(Integer.valueOf(localclass_769.jdField_field_1028_of_type_Int))) != null) {
            if ("OTJ".equals(localclass_769.jdField_field_1028_of_type_JavaLangString))
            {
              ((class_773)???).c6(Boolean.parseBoolean(localclass_769.field_1029));
            }
            else if ("DES".equals(localclass_769.jdField_field_1028_of_type_JavaLangString))
            {
              ((class_773)???).c18(localclass_769.field_1029);
            }
            else if ("NAM".equals(localclass_769.jdField_field_1028_of_type_JavaLangString))
            {
              ((class_773)???).a106(localclass_769.field_1029);
            }
            else if ("ATN".equals(localclass_769.jdField_field_1028_of_type_JavaLangString))
            {
              ((class_773)???).b13(Boolean.parseBoolean(localclass_769.field_1029));
            }
            else if ("ADW".equals(localclass_769.jdField_field_1028_of_type_JavaLangString))
            {
              ((class_773)???).d11(Boolean.parseBoolean(localclass_769.field_1029));
            }
            else
            {
              System.err.println("[CLIENT] Exception: unknown faction mod command: " + localclass_769.jdField_field_1028_of_type_JavaLangString + " -> " + localclass_769.field_1029);
              if (!jdField_field_184_of_type_Boolean) {
                throw new AssertionError();
              }
            }
          }
          this.jdField_field_182_of_type_Boolean = true;
        }
      }
    }
    Object localObject19;
    Object localObject21;
    Object localObject28;
    if (!this.field_185.isEmpty()) {
      synchronized (this.field_185)
      {
        while (!this.field_185.isEmpty())
        {
          class_773 localclass_7731 = (class_773)this.field_185.remove(0);
          synchronized (this.jdField_field_139_of_type_JavaUtilHashMap)
          {
            if ((localObject19 = (class_773)this.jdField_field_139_of_type_JavaUtilHashMap.remove(Integer.valueOf(localclass_7731.a3()))) != null)
            {
              localObject21 = new ArrayList();
              localObject25 = this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.values().iterator();
              while (((Iterator)localObject25).hasNext()) {
                if ((localObject28 = (class_760)((Iterator)localObject25).next()).a18(((class_773)localObject19).a3())) {
                  ((ArrayList)localObject21).add(localObject28);
                }
              }
              this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.values().removeAll((Collection)localObject21);
            }
            else
            {
              localObject25 = null;
              System.err.println("[FACTION][WARNING] " + this.jdField_field_136_of_type_Class_800.getState() + " could not delete " + localclass_7731 + ". ID NOT FOUND");
            }
          }
          if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
            this.jdField_field_136_of_type_Class_800.a52().factionDel.add(new RemoteFaction(localclass_7732, this.jdField_field_136_of_type_Class_800.a52()));
          }
          this.jdField_field_182_of_type_Boolean = true;
        }
      }
    }
    if (!this.jdField_field_184_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_184_of_type_JavaUtilArrayList)
      {
        while (!this.jdField_field_184_of_type_JavaUtilArrayList.isEmpty())
        {
          class_773 localclass_7733 = (class_773)this.jdField_field_184_of_type_JavaUtilArrayList.remove(0);
          localObject19 = localclass_7733;
          ??? = this;
          if (((class_773)localObject19).a3() != -1) {
            ((class_765)???).jdField_field_136_of_type_JavaUtilArrayList.add(new class_760(-1, ((class_773)localObject19).a3(), (byte)1));
          }
          synchronized (this.jdField_field_139_of_type_JavaUtilHashMap)
          {
            this.jdField_field_139_of_type_JavaUtilHashMap.put(Integer.valueOf(localclass_7733.a3()), localclass_7733);
          }
          if (this.jdField_field_136_of_type_Class_800.isOnServer())
          {
            this.jdField_field_136_of_type_Class_800.a52().factionAdd.add(new RemoteFaction(localclass_7734, this.jdField_field_136_of_type_Class_800.a52()));
            for (int i = 0; i < this.field_199.size(); i++) {
              if ((localObject19 = (class_767)this.field_199.get(i)).jdField_field_1026_of_type_Int == localclass_7734.a3())
              {
                this.field_198.add(localObject19);
                this.field_199.remove(i);
                i--;
              }
            }
          }
          this.jdField_field_182_of_type_Boolean = true;
        }
      }
    }
    if (!this.field_198.isEmpty()) {
      synchronized (this.field_198)
      {
        while (!this.field_198.isEmpty())
        {
          class_767 localclass_767 = (class_767)this.field_198.remove(0);
          synchronized (this.jdField_field_139_of_type_JavaUtilHashMap)
          {
            localObject19 = (class_773)this.jdField_field_139_of_type_JavaUtilHashMap.get(Integer.valueOf(localclass_767.jdField_field_1026_of_type_Int));
            localObject25 = null;
            if ((this.jdField_field_136_of_type_Class_800.isOnServer()) && (localclass_767.jdField_field_1026_of_type_JavaLangString != null))
            {
              if ((localObject21 = (class_758)((class_773)localObject19).a162().get(localclass_767.jdField_field_1026_of_type_JavaLangString)) != null)
              {
                if (((localObject25 = (class_758)((class_773)localObject19).a162().get(localclass_767.jdField_field_1027_of_type_JavaLangString)) != null) && (((class_758)localObject21).field_136 <= ((class_758)localObject25).field_136))
                {
                  System.err.println("[SERVER][ERROR] Failed to set permission (role too low: initiator: " + ((class_758)localObject21).field_136 + "; target " + ((class_758)localObject25).field_136 + ")! initiator: " + localclass_767.jdField_field_1026_of_type_JavaLangString);
                  try
                  {
                    (localObject28 = ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a60(localclass_767.jdField_field_1026_of_type_JavaLangString)).a129(new ServerMessage("Cannot set Permission!\nToo low ranked!", 3, ((class_748)localObject28).getId()));
                  }
                  catch (PlayerNotFountException localPlayerNotFountException4)
                  {
                    localObject28 = null;
                    localPlayerNotFountException4.printStackTrace();
                  }
                }
                else if ((localclass_767.jdField_field_1027_of_type_Boolean) && ((localObject21 == null) || (!((class_758)localObject21).d7((class_773)localObject19))))
                {
                  System.err.println("[SERVER][ERROR] Failed to set permission! initiator: " + localclass_767.jdField_field_1026_of_type_JavaLangString);
                  try
                  {
                    (localObject28 = ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a60(localclass_767.jdField_field_1026_of_type_JavaLangString)).a129(new ServerMessage("Cannot set Permission!\nAccess denied!", 3, ((class_748)localObject28).getId()));
                  }
                  catch (PlayerNotFountException localPlayerNotFountException5)
                  {
                    localObject28 = null;
                    localPlayerNotFountException5.printStackTrace();
                  }
                }
                else if ((!localclass_767.jdField_field_1027_of_type_Boolean) && ((localObject21 == null) || (!((class_758)localObject21).b24((class_773)localObject19))))
                {
                  System.err.println("[SERVER][ERROR] Failed to kick! initiator: " + localclass_767.jdField_field_1026_of_type_JavaLangString);
                  try
                  {
                    (localObject28 = ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a60(localclass_767.jdField_field_1026_of_type_JavaLangString)).a129(new ServerMessage("Cannot kick!\nAccess denied!", 3, ((class_748)localObject28).getId()));
                  }
                  catch (PlayerNotFountException localPlayerNotFountException6)
                  {
                    localObject28 = null;
                    localPlayerNotFountException6.printStackTrace();
                  }
                }
                else {}
              }
              else
              {
                System.err.println("[SERVER][ERROR] Could not hande faction mod! unknown member: " + localclass_767.jdField_field_1026_of_type_JavaLangString);
                if (!jdField_field_184_of_type_Boolean) {
                  throw new AssertionError();
                }
              }
            }
            else
            {
              if (localclass_767.jdField_field_1027_of_type_Boolean)
              {
                if (localObject19 != null)
                {
                  ((class_773)localObject19).a175(localclass_767.jdField_field_1027_of_type_JavaLangString, localclass_767.jdField_field_1027_of_type_JavaLangString, localclass_767.jdField_field_1026_of_type_Byte, this.jdField_field_136_of_type_Class_800);
                }
                else
                {
                  System.err.println("[FactionManager][ERROR] adding member failed: " + localclass_767.jdField_field_1027_of_type_JavaLangString + " from " + localclass_767.jdField_field_1026_of_type_Int);
                  if (!localclass_767.jdField_field_1026_of_type_Boolean)
                  {
                    System.currentTimeMillis();
                    this.field_199.add(localclass_767);
                  }
                }
              }
              else if (localObject19 != null)
              {
                System.err.println("[FactionManager] removing member: " + localclass_767.jdField_field_1027_of_type_JavaLangString + " from " + localObject19);
                ((class_773)localObject19).a177(localclass_767.jdField_field_1027_of_type_JavaLangString, this.jdField_field_136_of_type_Class_800);
                if ((this.jdField_field_136_of_type_Class_800.isOnServer()) && (((class_773)localObject19).a162().isEmpty()))
                {
                  System.err.println("[SERVER][FactionManager] Faction Empty -> Removing " + localObject19);
                  b26((class_773)localObject19);
                }
              }
              else
              {
                System.err.println("[FactionManager][ERROR] removing member failed: " + localclass_767.jdField_field_1027_of_type_JavaLangString + " from " + localclass_767.jdField_field_1026_of_type_Int);
                if (!localclass_767.jdField_field_1026_of_type_Boolean)
                {
                  System.currentTimeMillis();
                  this.field_199.add(localclass_767);
                }
                localclass_767.jdField_field_1026_of_type_Boolean = true;
              }
              this.jdField_field_182_of_type_Boolean = true;
            }
          }
        }
      }
    }
    Object localObject22;
    if (!this.field_196.isEmpty()) {
      synchronized (this.field_196)
      {
        while (!this.field_196.isEmpty())
        {
          class_779 localclass_779 = (class_779)this.field_196.remove(0);
          if ((??? = a156(localclass_779.jdField_field_1035_of_type_Int)) != null) {
            if (((localObject19 = (class_758)((class_773)???).a162().get(localclass_779.jdField_field_1035_of_type_JavaLangString)) == null) || (!((class_758)localObject19).d7((class_773)???)))
            {
              try
              {
                (localObject21 = ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a60(localclass_779.field_1036)).a141().a36(0);
                ((class_748)localObject21).a129(new ServerMessage("Permission denied!", 3, ((class_748)localObject21).getId()));
              }
              catch (PlayerNotFountException localPlayerNotFountException1) {}
            }
            else
            {
              localObject22 = new class_767(((class_773)???).a3(), localclass_779.field_1036);
              try
              {
                (localObject25 = ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a60(localclass_779.field_1036)).a141().a36(0);
                ((class_748)localObject25).a129(new ServerMessage("You have been kicked from\n" + ((class_773)???).a(), 1, ((class_748)localObject25).getId()));
              }
              catch (PlayerNotFountException localPlayerNotFountException3)
              {
                System.err.println("[FACTION][KICK] player not found (could be offline)");
              }
              this.field_198.add(localObject22);
            }
          }
        }
      }
    }
    Object localObject26;
    if (!this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
      {
        while (!this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty())
        {
          class_760 localclass_760 = (class_760)this.jdField_field_136_of_type_JavaUtilArrayList.remove(0);
          this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.put(localclass_760.a28(), localclass_760);
          this.jdField_field_182_of_type_Boolean = true;
          localObject26 = null;
          if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
            this.jdField_field_136_of_type_Class_800.a52().factionRelationships.add(localclass_760.a151(this.jdField_field_136_of_type_Class_800.a52()));
          }
        }
      }
    }
    if (!this.jdField_field_139_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_139_of_type_JavaUtilArrayList)
      {
        while (!this.jdField_field_139_of_type_JavaUtilArrayList.isEmpty())
        {
          this.jdField_field_183_of_type_Boolean = true;
          class_629 localclass_629;
          if ((localclass_629 = (class_629)this.jdField_field_139_of_type_JavaUtilArrayList.remove(0)).jdField_field_136_of_type_Int == localclass_629.field_139)
          {
            try
            {
              throw new NullPointerException("tried to have relation with self " + localclass_629.jdField_field_136_of_type_Int + "; " + localclass_629.field_139);
            }
            catch (Exception localException)
            {
              localException;
            }
          }
          else
          {
            ??? = a156(localclass_629.jdField_field_136_of_type_Int);
            localObject19 = a156(localclass_629.field_139);
            if ((??? != null) && (localObject19 != null))
            {
              localObject26 = null;
              if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
                if (localclass_629.a7())
                {
                  (localObject22 = new class_771()).a170(((class_773)localObject19).a3(), ((class_773)???).a(), System.currentTimeMillis(), ((class_773)???).a() + " declared war!\n" + localclass_629.a(), 0);
                  this.field_192.add(localObject22);
                }
                else if (localclass_629.b2())
                {
                  (localObject22 = new class_771()).a170(((class_773)localObject19).a3(), ((class_773)???).a(), System.currentTimeMillis(), ((class_773)???).a() + " offered an Alliance!\n" + localclass_629.a(), 0);
                  this.field_192.add(localObject22);
                }
                else if (localclass_629.c3())
                {
                  (localObject22 = new class_771()).a170(((class_773)localObject19).a3(), ((class_773)???).a(), System.currentTimeMillis(), ((class_773)???).a() + " offered a peace treaty!\n" + localclass_629.a(), 0);
                  this.field_192.add(localObject22);
                }
                else if (!jdField_field_184_of_type_Boolean)
                {
                  throw new AssertionError();
                }
              }
              if (localclass_629.a7())
              {
                if (this.jdField_field_136_of_type_Class_800.isOnServer())
                {
                  (localObject22 = new class_760()).a29(((class_773)???).a3(), ((class_773)localObject19).a3());
                  ((class_760)localObject22).a13();
                  synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
                  {
                    this.jdField_field_136_of_type_JavaUtilArrayList.add(localObject22);
                  }
                }
              }
              else
              {
                this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.put(localObject10.a28(), localObject10);
                System.err.println("[FACTIONMANAGER] put relationship offer " + localObject10 + " on " + this.jdField_field_136_of_type_Class_800.getState());
                if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
                  this.jdField_field_136_of_type_Class_800.a52().factionRelationshipOffer.add(localObject10.a31(this.jdField_field_136_of_type_Class_800.a52()));
                }
              }
            }
          }
        }
      }
    }
    if (!this.jdField_field_182_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_182_of_type_JavaUtilArrayList)
      {
        while (!this.jdField_field_182_of_type_JavaUtilArrayList.isEmpty())
        {
          class_625 localclass_625 = (class_625)this.jdField_field_182_of_type_JavaUtilArrayList.remove(0);
          if ((??? = a156(localclass_625.jdField_field_136_of_type_Int)) != null)
          {
            ((class_773)???).a180().a27(localclass_625);
            ??? = null;
            if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
              a27(localclass_625);
            }
          }
          else
          {
            System.err.println("[FactionManager][ERROR] could not find factionto apply rule " + localclass_625);
          }
        }
      }
    }
    if (!this.jdField_field_183_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_183_of_type_JavaUtilArrayList)
      {
        while (!this.jdField_field_183_of_type_JavaUtilArrayList.isEmpty())
        {
          class_775 localclass_775 = (class_775)this.jdField_field_183_of_type_JavaUtilArrayList.remove(0);
          if ((??? = a156(localclass_775.jdField_field_1030_of_type_Int)) != null)
          {
            ??? = null;
            if ((this.jdField_field_136_of_type_Class_800.isOnServer()) && (((localObject19 = (class_758)((class_773)???).a162().get(localclass_775.jdField_field_1030_of_type_JavaLangString)) == null) || (!((class_758)localObject19).d7((class_773)???))))
            {
              try
              {
                (localObject22 = ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a60(localclass_775.jdField_field_1030_of_type_JavaLangString)).a129(new ServerMessage("Cannot set homebase:\nPermission Denied!\n(Only for Faction Admin)", 1, ((class_748)localObject22).getId()));
              }
              catch (PlayerNotFountException localPlayerNotFountException2)
              {
                System.err.println("[FACTION][HOMEBASE] player not found " + localclass_775.jdField_field_1030_of_type_JavaLangString);
              }
              System.err.println("[FACTION][HOMEBASE][ERROR] no right to set base " + localclass_775.jdField_field_1030_of_type_JavaLangString);
            }
            else
            {
              if ((!localclass_775.field_1031.startsWith("ENTITY_SPACESTATION")) && (!localclass_775.field_1031.startsWith("ENTITY_PLANET")))
              {
                System.err.println("[FACTION][HOMEBASE][ERROR] cannot make a home base " + localclass_775.field_1031);
                localclass_775.field_1031 = "";
              }
              ((class_773)???).d9(localclass_775.field_1031);
              ((class_773)???).a44().b1(localclass_775.jdField_field_1030_of_type_Class_48);
              if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
                a164(localclass_775.jdField_field_1030_of_type_JavaLangString, localclass_775.jdField_field_1030_of_type_Int, localclass_775.field_1031, localclass_775.jdField_field_1030_of_type_Class_48);
              } else if (((class_371)this.jdField_field_136_of_type_Class_800.getState()).a20().h1() == localclass_775.jdField_field_1030_of_type_Int) {
                if (localclass_775.field_1031.length() > 0) {
                  ((class_371)this.jdField_field_136_of_type_Class_800.getState()).a4().c1("Your faction has a new home:\n" + localclass_775.field_1031);
                } else {
                  ((class_371)this.jdField_field_136_of_type_Class_800.getState()).a4().c1("Your faction has abandoned\nits home!");
                }
              }
            }
          }
          else
          {
            System.err.println("[FACTION][HOMEBASE][ERROR] faction not found " + localclass_775.jdField_field_1030_of_type_Int);
          }
        }
      }
    }
    if (!this.field_186.isEmpty()) {
      synchronized (this.field_186)
      {
        while (!this.field_186.isEmpty())
        {
          this.jdField_field_183_of_type_Boolean = true;
          class_631 localclass_631 = (class_631)this.field_186.remove(0);
          ??? = null;
          if ((??? = (class_629)this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.remove(localclass_631.jdField_field_912_of_type_Long)) != null)
          {
            if ((this.jdField_field_136_of_type_Class_800.isOnServer()) && (localclass_631.jdField_field_912_of_type_Boolean))
            {
              (localObject19 = new class_760()).a29(((class_629)???).jdField_field_136_of_type_Int, ((class_629)???).field_139);
              if (((class_629)???).a7()) {
                ((class_760)localObject19).a13();
              } else if (((class_629)???).b2()) {
                ((class_760)localObject19).b4();
              } else if (((class_629)???).c3()) {
                ((class_760)localObject19).c1();
              } else if (!jdField_field_184_of_type_Boolean) {
                throw new AssertionError();
              }
              synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
              {
                this.jdField_field_136_of_type_JavaUtilArrayList.add(localObject19);
              }
            }
            if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
              a157(localObject14.jdField_field_912_of_type_JavaLangString, (class_629)???, localObject14.jdField_field_912_of_type_Boolean);
            }
          }
          else
          {
            System.err.println("[FACTION][OFFERACCEPT] relation ship offer not found " + localObject14.jdField_field_912_of_type_Long);
          }
        }
      }
    }
    if (this.jdField_field_183_of_type_Boolean)
    {
      System.err.println("[FACTIONMANAGER] Faction Offers Changed");
      setChanged();
      notifyObservers("RELATIONSHIP_OFFER");
      this.jdField_field_183_of_type_Boolean = false;
    }
    if (this.jdField_field_182_of_type_Boolean)
    {
      setChanged();
      notifyObservers();
      this.jdField_field_182_of_type_Boolean = false;
    }
  }
  
  public final void a155(int paramInt1, int paramInt2, byte paramByte)
  {
    synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
    {
      this.jdField_field_136_of_type_JavaUtilArrayList.add(new class_760(paramInt1, paramInt2, paramByte));
      return;
    }
  }
  
  public final boolean a18(int paramInt)
  {
    return this.jdField_field_139_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(paramInt));
  }
  
  public final class_773 a156(int paramInt)
  {
    return (class_773)this.jdField_field_139_of_type_JavaUtilHashMap.get(Integer.valueOf(paramInt));
  }
  
  public final void a157(String paramString, class_629 paramclass_629, boolean paramBoolean)
  {
    RemoteStringArray localRemoteStringArray;
    (localRemoteStringArray = new RemoteStringArray(3, this.jdField_field_136_of_type_Class_800.a52())).set(0, paramString);
    localRemoteStringArray.set(1, String.valueOf(paramclass_629.a28()));
    localRemoteStringArray.set(2, String.valueOf(paramBoolean));
    this.jdField_field_136_of_type_Class_800.a52().factionRelationshipAcceptBuffer.add(localRemoteStringArray);
  }
  
  public final void a30(String paramString1, int paramInt1, int paramInt2, byte paramByte, String paramString2)
  {
    class_629 localclass_629;
    (localclass_629 = new class_629()).a30(paramString1, paramInt1, paramInt2, paramByte, paramString2);
    this.jdField_field_136_of_type_Class_800.a52().factionRelationshipOffer.add(localclass_629.a31(this.jdField_field_136_of_type_Class_800.a52()));
  }
  
  public final boolean a158(int paramInt1, int paramInt2)
  {
    long l = class_760.a150(paramInt1, paramInt2);
    if ((paramInt1 == 0) || (paramInt2 == 0)) {
      return ((paramInt1 = a156(paramInt1 == 0 ? paramInt2 : paramInt1)) != null) && (paramInt1.b2());
    }
    if (paramInt1 == paramInt2) {
      return false;
    }
    return (this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.containsKey(l)) && (((class_760)this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.get(l)).a7());
  }
  
  public final boolean b28(int paramInt1, int paramInt2)
  {
    long l = class_760.a150(paramInt1, paramInt2);
    if ((paramInt1 == 0) || (paramInt2 == 0)) {
      return ((paramInt1 = a156(paramInt1 == 0 ? paramInt2 : paramInt1)) != null) && (paramInt1.a7());
    }
    if (paramInt1 == paramInt2) {
      return true;
    }
    return (this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.containsKey(l)) && (((class_760)this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.get(l)).b2());
  }
  
  public final boolean c17(int paramInt1, int paramInt2)
  {
    long l = class_760.a150(paramInt1, paramInt2);
    return (!this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.containsKey(l)) || (((class_760)this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.get(l)).c3());
  }
  
  public final class_762 a159(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) || (paramInt2 == 0))
    {
      class_773 localclass_773;
      if ((localclass_773 = a156(paramInt1 == 0 ? paramInt2 : paramInt1)) != null)
      {
        if (localclass_773.b2()) {
          return class_762.field_1023;
        }
        if (localclass_773.a7()) {
          return class_762.field_1024;
        }
        return class_762.field_1022;
      }
      return class_762.field_1022;
    }
    if (paramInt1 == paramInt2) {
      return class_762.field_1024;
    }
    long l = class_760.a150(paramInt1, paramInt2);
    if (!this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.containsKey(l)) {
      return class_762.field_1022;
    }
    return ((class_760)this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.get(l)).a149();
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if (paramclass_69.a2().equals("factions-v0"))
    {
      Object localObject1 = (class_69[])(paramclass_69 = (class_69[])paramclass_69.a4())[0].a4();
      int j = jdField_field_136_of_type_Int;
      for (int m = 0; m < localObject1.length - 1; m++)
      {
        class_773 localclass_7731;
        (localclass_7731 = new class_773()).fromTagStructure(localObject1[m]);
        if ((!jdField_field_184_of_type_Boolean) && (this.jdField_field_139_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(localclass_7731.a3())))) {
          throw new AssertionError();
        }
        if ((!localclass_7731.a162().isEmpty()) || (localclass_7731.a3() < 0))
        {
          this.jdField_field_139_of_type_JavaUtilHashMap.put(Integer.valueOf(localclass_7731.a3()), localclass_7731);
          j = Math.max(j, localclass_7731.a3());
        }
        else
        {
          System.err.println("[SERVER][FACTION] not adding empty faction: " + localclass_7731 + ": " + localclass_7731.a162());
        }
      }
      jdField_field_136_of_type_Int = j;
      class_69[] arrayOfclass_694 = (class_69[])paramclass_69[1].a4();
      for (int i2 = 0; i2 < arrayOfclass_694.length - 1; i2++)
      {
        (localObject1 = new class_777()).fromTagStructure(arrayOfclass_694[i2]);
        if ((class_773)this.jdField_field_139_of_type_JavaUtilHashMap.get(Integer.valueOf(((class_777)localObject1).a3())) != null) {
          this.jdField_field_136_of_type_JavaUtilHashSet.add(localObject1);
        }
      }
      class_69[] arrayOfclass_695 = (class_69[])paramclass_69[2].a4();
      Object localObject2;
      for (int i = 0; i < arrayOfclass_695.length - 1; i++) {
        if (arrayOfclass_695[i].a3() == class_79.field_561)
        {
          class_69[] arrayOfclass_692 = (class_69[])arrayOfclass_695[i].a4();
          for (int n = 0; n < arrayOfclass_692.length - 1; n++)
          {
            (localObject2 = new class_771()).fromTagStructure(arrayOfclass_692[n]);
            class_773 localclass_7732;
            if ((localclass_7732 = (class_773)this.jdField_field_139_of_type_JavaUtilHashMap.get(Integer.valueOf(((class_771)localObject2).b5()))) != null)
            {
              TreeSet localTreeSet;
              if ((localTreeSet = (TreeSet)this.jdField_field_136_of_type_JavaUtilHashMap.get(Integer.valueOf(localclass_7732.a3()))) == null)
              {
                localTreeSet = new TreeSet();
                this.jdField_field_136_of_type_JavaUtilHashMap.put(Integer.valueOf(localclass_7732.a3()), localTreeSet);
              }
              localTreeSet.add(localObject2);
            }
          }
        }
      }
      class_69[] arrayOfclass_691 = (class_69[])paramclass_69[3].a4();
      for (int k = 0; k < arrayOfclass_691.length - 1; k++)
      {
        class_760 localclass_760;
        (localclass_760 = new class_760()).fromTagStructure(arrayOfclass_691[k]);
        if (localclass_760.jdField_field_136_of_type_Int != localclass_760.field_139) {
          this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.put(localclass_760.a28(), localclass_760);
        } else {
          System.err.println("[WARNING] not adding self-relation");
        }
      }
      class_69[] arrayOfclass_693 = (class_69[])paramclass_69[4].a4();
      for (int i1 = 0; i1 < arrayOfclass_693.length - 1; i1++)
      {
        (localObject2 = new class_629()).fromTagStructure(arrayOfclass_693[i1]);
        System.err.println("[SERVER][FACION][TAG] loaded relation offer " + localObject2);
        this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.put(((class_629)localObject2).a28(), localObject2);
      }
      return;
    }
    throw new FactionFileOutdatedException();
  }
  
  public class_69 toTagStructure()
  {
    class_69[] arrayOfclass_691 = null;
    int i = 0;
    class_69[] arrayOfclass_693 = null;
    Object localObject3;
    Object localObject2;
    synchronized (this.jdField_field_139_of_type_JavaUtilHashMap)
    {
      localObject3 = null;
      arrayOfclass_691 = new class_69[this.jdField_field_139_of_type_JavaUtilHashMap.size() + 1];
      arrayOfclass_693 = new class_69[this.jdField_field_139_of_type_JavaUtilHashMap.size() + 1];
      Iterator localIterator = this.jdField_field_139_of_type_JavaUtilHashMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (class_773)localIterator.next();
        arrayOfclass_691[i] = ((class_773)localObject2).toTagStructure();
        if ((localObject3 = (TreeSet)this.jdField_field_136_of_type_JavaUtilHashMap.get(Integer.valueOf(((class_773)localObject2).a3()))) == null) {
          arrayOfclass_693[i] = new class_69(class_79.field_549, "0FN", Byte.valueOf(0));
        } else {
          arrayOfclass_693[i] = class_69.a6((Collection)localObject3, "FN");
        }
        i++;
      }
      arrayOfclass_691[this.jdField_field_139_of_type_JavaUtilHashMap.size()] = new class_69(class_79.field_548, null, null);
      arrayOfclass_693[this.jdField_field_139_of_type_JavaUtilHashMap.size()] = new class_69(class_79.field_548, null, null);
    }
    synchronized (this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap)
    {
      i = 0;
      ??? = new class_69[this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.size() + 1];
      localObject2 = this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.values().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (class_760)((Iterator)localObject2).next();
        ???[i] = ((class_760)localObject3).toTagStructure();
        i++;
      }
      ???[this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.size()] = new class_69(class_79.field_548, null, null);
    }
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap)
    {
      i = 0;
      ??? = new class_69[this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.size() + 1];
      localObject3 = this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.values().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject5 = (class_629)((Iterator)localObject3).next();
        ???[i] = ((class_629)localObject5).toTagStructure();
        i++;
      }
      ???[this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.size()] = new class_69(class_79.field_548, null, null);
    }
    synchronized (this.jdField_field_136_of_type_JavaUtilHashSet)
    {
      ??? = new class_69[this.jdField_field_136_of_type_JavaUtilHashSet.size() + 1];
      i = 0;
      localObject5 = this.jdField_field_136_of_type_JavaUtilHashSet.iterator();
      while (((Iterator)localObject5).hasNext())
      {
        localObject7 = (class_777)((Iterator)localObject5).next();
        ???[i] = ((class_777)localObject7).toTagStructure();
        i++;
      }
      ???[this.jdField_field_136_of_type_JavaUtilHashSet.size()] = new class_69(class_79.field_548, null, null);
    }
    ??? = new class_69(class_79.field_561, "NStruct", arrayOfclass_693);
    Object localObject5 = new class_69(class_79.field_561, null, arrayOfclass_692);
    Object localObject7 = new class_69(class_79.field_561, null, (class_69[])???);
    class_69 localclass_691 = new class_69(class_79.field_561, null, (class_69[])???);
    class_69 localclass_692 = new class_69(class_79.field_561, null, (class_69[])???);
    return new class_69(class_79.field_561, "factions-v0", new class_69[] { localObject5, localObject7, ???, localclass_691, localclass_692, new class_69(class_79.field_548, null, null) });
  }
  
  public String getUniqueIdentifier()
  {
    return "FACTIONS";
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public static String a()
  {
    return class_41.c(6 + jdField_field_136_of_type_JavaUtilRandom.nextInt(18));
  }
  
  public static void c1() {}
  
  public final void a100(NetworkGameState paramNetworkGameState)
  {
    Object localObject1;
    for (int i = 0; i < paramNetworkGameState.factionNewsPosts.getReceiveBuffer().size(); i++)
    {
      localObject1 = (RemoteFactionNewsPost)paramNetworkGameState.factionNewsPosts.getReceiveBuffer().get(i);
      synchronized (this.field_192)
      {
        this.field_192.add(((RemoteFactionNewsPost)localObject1).get());
      }
    }
    for (i = 0; i < paramNetworkGameState.factionInviteAdd.getReceiveBuffer().size(); i++)
    {
      localObject1 = (RemoteFactionInvitation)paramNetworkGameState.factionInviteAdd.getReceiveBuffer().get(i);
      synchronized (this.field_193)
      {
        this.field_193.add(((RemoteFactionInvitation)localObject1).get());
      }
    }
    for (i = 0; i < paramNetworkGameState.factionRelationshipAcceptBuffer.getReceiveBuffer().size(); i++)
    {
      localObject1 = (RemoteStringArray)paramNetworkGameState.factionRelationshipAcceptBuffer.getReceiveBuffer().get(i);
      synchronized (this.field_186)
      {
        String str1 = (String)((RemoteStringArray)localObject1).get(0).get();
        long l = Long.parseLong((String)((RemoteStringArray)localObject1).get(1).get());
        boolean bool = Boolean.parseBoolean((String)((RemoteStringArray)localObject1).get(2).get());
        synchronized (this.field_186)
        {
          this.field_186.add(new class_631(str1, l, bool));
        }
      }
    }
    for (i = 0; i < paramNetworkGameState.factionkickMemberRequests.getReceiveBuffer().size(); i++)
    {
      ??? = (RemoteStringArray)paramNetworkGameState.factionkickMemberRequests.getReceiveBuffer().get(i);
      synchronized (this.field_196)
      {
        System.err.println("[FactionManager] Received Faction Kick on " + this.jdField_field_136_of_type_Class_800.getState());
        this.field_196.add(new class_779((String)((RemoteStringArray)???).get(2).get(), Integer.parseInt((String)((RemoteStringArray)???).get(1).get()), (String)((RemoteStringArray)???).get(0).get()));
      }
    }
    for (i = 0; i < paramNetworkGameState.factionInviteDel.getReceiveBuffer().size(); i++)
    {
      ??? = (RemoteFactionInvitation)paramNetworkGameState.factionInviteDel.getReceiveBuffer().get(i);
      synchronized (this.field_194)
      {
        System.err.println("[FactionManager] Received Faction Invite DELETE on " + this.jdField_field_136_of_type_Class_800.getState());
        this.field_194.add(((RemoteFactionInvitation)???).get());
      }
    }
    Object localObject6;
    String str2;
    Object localObject8;
    Object localObject9;
    for (i = 0; i < paramNetworkGameState.factionMod.getReceiveBuffer().size(); i++)
    {
      (??? = (RemoteField[])((RemoteStringArray)paramNetworkGameState.factionMod.getReceiveBuffer().get(i)).get())[0].get();
      localObject6 = Integer.parseInt((String)???[1].get());
      str2 = (String)???[2].get();
      localObject8 = (String)???[3].get();
      (localObject9 = new class_769()).jdField_field_1028_of_type_Int = localObject6;
      ((class_769)localObject9).jdField_field_1028_of_type_JavaLangString = str2;
      ((class_769)localObject9).field_1029 = ((String)localObject8);
      synchronized (this.field_197)
      {
        this.field_197.add(localObject9);
      }
    }
    for (i = 0; i < paramNetworkGameState.factionAdd.getReceiveBuffer().size(); i++)
    {
      ??? = (RemoteFaction)paramNetworkGameState.factionAdd.getReceiveBuffer().get(i);
      a153((class_773)((RemoteFaction)???).get());
    }
    for (i = 0; i < paramNetworkGameState.factionDel.getReceiveBuffer().size(); i++)
    {
      ??? = (RemoteFaction)paramNetworkGameState.factionDel.getReceiveBuffer().get(i);
      b26((class_773)((RemoteFaction)???).get());
    }
    for (i = 0; i < paramNetworkGameState.factionMemberMod.getReceiveBuffer().size(); i++)
    {
      ??? = (String)(??? = (RemoteField[])((RemoteStringArray)paramNetworkGameState.factionMemberMod.getReceiveBuffer().get(i)).get())[0].get();
      localObject6 = Integer.parseInt((String)???[1].get());
      str2 = (String)???[2].get();
      localObject8 = (String)???[3].get();
      localObject9 = new class_767();
      if (str2.equals("r"))
      {
        ((class_767)localObject9).jdField_field_1027_of_type_Boolean = false;
        ((class_767)localObject9).jdField_field_1026_of_type_Int = localObject6;
        ((class_767)localObject9).jdField_field_1027_of_type_JavaLangString = ???;
        ((class_767)localObject9).jdField_field_1026_of_type_JavaLangString = ((String)localObject8);
      }
      else
      {
        byte b = Byte.parseByte(str2);
        ((class_767)localObject9).jdField_field_1027_of_type_Boolean = true;
        ((class_767)localObject9).jdField_field_1026_of_type_Int = localObject6;
        ((class_767)localObject9).jdField_field_1027_of_type_JavaLangString = ???;
        ((class_767)localObject9).jdField_field_1026_of_type_Byte = b;
        ((class_767)localObject9).jdField_field_1026_of_type_JavaLangString = ((String)localObject8);
      }
      synchronized (this.field_198)
      {
        this.field_198.add(localObject9);
      }
    }
    int n;
    for (i = 0; i < paramNetworkGameState.factionRelationships.getReceiveBuffer().size(); i++)
    {
      ??? = ((Integer)(??? = (RemoteField[])((org.schema.schine.network.objects.remote.RemoteIntegerArray)paramNetworkGameState.factionRelationships.getReceiveBuffer().get(i)).get())[0].get()).intValue();
      localObject6 = ((Integer)???[1].get()).intValue();
      n = ((Integer)???[2].get()).intValue();
      if (??? != localObject6)
      {
        (localObject8 = new class_760()).a29(???, localObject6);
        ((class_760)localObject8).a4((byte)n);
        synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
        {
          this.jdField_field_136_of_type_JavaUtilArrayList.add(localObject8);
        }
      }
      System.err.println("[FACTION] Exception: received relationship with self: " + ???);
    }
    for (i = 0; i < paramNetworkGameState.factionRelationshipOffer.getReceiveBuffer().size(); i++)
    {
      ??? = Integer.parseInt((String)(??? = (RemoteField[])((RemoteStringArray)paramNetworkGameState.factionRelationshipOffer.getReceiveBuffer().get(i)).get())[0].get());
      int k = Integer.parseInt((String)???[1].get());
      n = Integer.parseInt((String)???[2].get());
      localObject8 = (String)???[3].get();
      ??? = (String)???[4].get();
      (??? = new class_629()).a30((String)???, ???, k, (byte)n, (String)localObject8);
      synchronized (this.jdField_field_139_of_type_JavaUtilArrayList)
      {
        this.jdField_field_139_of_type_JavaUtilArrayList.add(???);
      }
    }
    for (i = 0; i < paramNetworkGameState.factionHomeBaseChangeBuffer.getReceiveBuffer().size(); i++)
    {
      ??? = (String)(??? = (RemoteStringArray)paramNetworkGameState.factionHomeBaseChangeBuffer.getReceiveBuffer().get(i)).get(0).get();
      int m = Integer.parseInt((String)((RemoteStringArray)???).get(1).get());
      String str3 = (String)((RemoteStringArray)???).get(2).get();
      int i1 = Integer.parseInt((String)((RemoteStringArray)???).get(3).get());
      int i2 = Integer.parseInt((String)((RemoteStringArray)???).get(4).get());
      int j = Integer.parseInt((String)((RemoteStringArray)???).get(5).get());
      class_775 localclass_775 = new class_775(???, m, str3, new class_48(i1, i2, j));
      synchronized (this.jdField_field_183_of_type_JavaUtilArrayList)
      {
        this.jdField_field_183_of_type_JavaUtilArrayList.add(localclass_775);
      }
    }
    for (i = 0; i < paramNetworkGameState.factionRolesBuffer.getReceiveBuffer().size(); i++)
    {
      ??? = (RemoteFactionRoles)paramNetworkGameState.factionRolesBuffer.getReceiveBuffer().get(i);
      synchronized (this.jdField_field_182_of_type_JavaUtilArrayList)
      {
        this.jdField_field_182_of_type_JavaUtilArrayList.add(((RemoteFactionRoles)???).get());
      }
    }
  }
  
  public static void d2() {}
  
  public final void b16(NetworkGameState paramNetworkGameState)
  {
    Iterator localIterator;
    Object localObject1;
    synchronized (this.jdField_field_139_of_type_JavaUtilHashMap)
    {
      localIterator = this.jdField_field_139_of_type_JavaUtilHashMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject1 = (class_773)localIterator.next();
        paramNetworkGameState.factionAdd.add(new RemoteFaction((class_773)localObject1, paramNetworkGameState));
        this.jdField_field_136_of_type_JavaUtilHashMap.get(Integer.valueOf(((class_773)localObject1).a3()));
      }
    }
    synchronized (this.jdField_field_136_of_type_JavaUtilHashSet)
    {
      localIterator = this.jdField_field_136_of_type_JavaUtilHashSet.iterator();
      while (localIterator.hasNext())
      {
        localObject1 = (class_777)localIterator.next();
        this.jdField_field_136_of_type_Class_800.a52().factionInviteAdd.add(new RemoteFactionInvitation((class_777)localObject1, this.jdField_field_136_of_type_Class_800.a52()));
      }
    }
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap)
    {
      localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject1 = (class_629)localIterator.next();
        this.jdField_field_136_of_type_Class_800.a52().factionRelationshipOffer.add(((class_629)localObject1).a31(this.jdField_field_136_of_type_Class_800.a52()));
      }
    }
    synchronized (this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap)
    {
      localIterator = this.jdField_field_139_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject1 = (class_760)localIterator.next();
        this.jdField_field_136_of_type_Class_800.a52().factionRelationships.add(((class_760)localObject1).a151(this.jdField_field_136_of_type_Class_800.a52()));
      }
      return;
    }
  }
  
  public String toString()
  {
    return this.jdField_field_139_of_type_JavaUtilHashMap.values().toString();
  }
  
  public final void a160(int paramInt, class_748 paramclass_748)
  {
    if ((!jdField_field_184_of_type_Boolean) && (!this.jdField_field_136_of_type_Class_800.isOnServer())) {
      throw new AssertionError();
    }
    synchronized (this.field_198)
    {
      this.field_198.add(new class_767(paramInt, paramclass_748.getName()));
      return;
    }
  }
  
  public final HashSet a143()
  {
    return this.jdField_field_136_of_type_JavaUtilHashSet;
  }
  
  public final class_800 a161()
  {
    return this.jdField_field_136_of_type_Class_800;
  }
  
  public final Collection a101()
  {
    return this.jdField_field_139_of_type_JavaUtilHashMap.values();
  }
  
  public final HashMap a162()
  {
    return this.jdField_field_136_of_type_JavaUtilHashMap;
  }
  
  public final Long2ObjectOpenHashMap a163()
  {
    return this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLong2ObjectOpenHashMap;
  }
  
  public final void a27(class_625 paramclass_625)
  {
    this.jdField_field_136_of_type_Class_800.a52().factionRolesBuffer.add(new RemoteFactionRoles(paramclass_625, this.jdField_field_136_of_type_Class_800.a52()));
  }
  
  public final void a164(String paramString1, int paramInt, String paramString2, class_48 paramclass_48)
  {
    RemoteStringArray localRemoteStringArray;
    (localRemoteStringArray = new RemoteStringArray(6, this.jdField_field_136_of_type_Class_800.a52())).set(0, paramString1);
    localRemoteStringArray.set(1, String.valueOf(paramInt));
    localRemoteStringArray.set(2, paramString2);
    localRemoteStringArray.set(3, String.valueOf(paramclass_48.field_475));
    localRemoteStringArray.set(4, String.valueOf(paramclass_48.field_476));
    localRemoteStringArray.set(5, String.valueOf(paramclass_48.field_477));
    this.jdField_field_136_of_type_Class_800.a52().factionHomeBaseChangeBuffer.add(localRemoteStringArray);
  }
  
  public final boolean a165(class_797 paramclass_7971, class_797 paramclass_7972)
  {
    return a166(paramclass_7971, paramclass_7972) == class_762.field_1023;
  }
  
  public final class_762 a166(class_797 paramclass_7971, class_797 paramclass_7972)
  {
    if (((paramclass_7971 instanceof class_365)) && (!((class_365)paramclass_7971).a26().isEmpty())) {
      paramclass_7971 = ((class_748)((class_365)paramclass_7971).a26().get(0)).h1();
    } else {
      paramclass_7971 = paramclass_7971.getFactionId();
    }
    if (((paramclass_7972 instanceof class_365)) && (!((class_365)paramclass_7972).a26().isEmpty())) {
      paramclass_7972 = ((class_748)((class_365)paramclass_7972).a26().get(0)).h1();
    } else {
      paramclass_7972 = paramclass_7972.getFactionId();
    }
    return a159(paramclass_7971, paramclass_7972);
  }
  
  public final boolean b29(class_797 paramclass_7971, class_797 paramclass_7972)
  {
    return a166(paramclass_7971, paramclass_7972) == class_762.field_1024;
  }
  
  public final void a167(class_629 paramclass_629)
  {
    synchronized (this.jdField_field_139_of_type_JavaUtilArrayList)
    {
      this.jdField_field_139_of_type_JavaUtilArrayList.add(paramclass_629);
      return;
    }
  }
  
  static
  {
    jdField_field_136_of_type_Int = 10000;
    { "pirates", "traiding guild" }[2] = "NEUTRAL";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_765
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */