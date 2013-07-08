import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.element.ElementClassNotFoundException;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.player.PlayerControlledTransformableNotFound;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.server.controller.EntityAlreadyExistsException;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.game.server.controller.GameServerController;
import org.schema.game.server.controller.NoIPException;
import org.schema.game.server.data.PlayerNotFountException;
import org.schema.game.server.data.admin.AdminCommands;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.server.ServerMessage;

public final class class_1007
{
  public Object[] field_1284;
  public AdminCommands field_1284;
  public RegisteredClientOnServer field_1284;
  
  public class_1007(RegisteredClientOnServer paramRegisteredClientOnServer, AdminCommands paramAdminCommands, Object[] paramArrayOfObject)
  {
    this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer = paramRegisteredClientOnServer;
    this.jdField_field_1284_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
    this.jdField_field_1284_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
  }
  
  public final void a(class_1041 paramclass_1041)
  {
    class_48 localclass_481 = new class_48(((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).intValue());
    try
    {
      localObject = new ObjectArrayFIFOQueue(4096);
      for (int i = localclass_481.field_477 << 4; i < (localclass_481.field_477 << 4) + 16; i++) {
        for (int j = localclass_481.field_476 << 4; j < (localclass_481.field_476 << 4) + 16; j++) {
          for (int k = localclass_481.field_475 << 4; k < (localclass_481.field_475 << 4) + 16; k++)
          {
            class_48 localclass_482 = new class_48(k, j, i);
            ((ObjectArrayFIFOQueue)localObject).enqueue(localclass_482);
          }
        }
      }
      paramclass_1041.field_144 = ((ObjectArrayFIFOQueue)localObject);
      return;
    }
    catch (Exception localException)
    {
      Object localObject;
      (localObject = localException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] critical: " + localObject.getClass().getSimpleName() + ": " + ((Exception)localObject).getMessage());
    }
  }
  
  public final void b(class_1041 paramclass_1041)
  {
    class_48 localclass_481 = new class_48(((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).intValue());
    class_48 localclass_482 = new class_48(((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[3]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[4]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[5]).intValue());
    try
    {
      localObject = new ObjectArrayFIFOQueue(4096);
      for (int i = localclass_481.field_477; i <= localclass_482.field_477; i++) {
        for (int j = localclass_481.field_476; j <= localclass_482.field_476; j++) {
          for (int k = localclass_481.field_475; k <= localclass_482.field_475; k++)
          {
            class_48 localclass_483 = new class_48(k, j, i);
            ((ObjectArrayFIFOQueue)localObject).enqueue(localclass_483);
          }
        }
      }
      paramclass_1041.field_144 = ((ObjectArrayFIFOQueue)localObject);
      return;
    }
    catch (Exception localException)
    {
      Object localObject;
      (localObject = localException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] critical: " + localObject.getClass().getSimpleName() + ": " + ((Exception)localObject).getMessage());
    }
  }
  
  public final void c(class_1041 paramclass_1041)
  {
    Object localObject = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    int i = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue();
    try
    {
      localObject = paramclass_1041.a60((String)localObject);
      class_773 localclass_773;
      if ((localclass_773 = paramclass_1041.a45().a156(((class_748)localObject).h1())) != null)
      {
        if ((class_758)localclass_773.a162().get(((class_748)localObject).getName()) != null)
        {
          if ((i > 0) && (i < 6))
          {
            localclass_773.a175("ADMIN", ((class_748)localObject).getName(), (byte)(i - 1), paramclass_1041.a12());
            return;
          }
          this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] role id must be between 1 and 5 ");
          return;
        }
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not part of the faction " + localclass_773.a());
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not in a faction; fid: " + ((class_748)localObject).h1());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
    }
  }
  
  public final void d(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    int i = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue();
    try
    {
      paramclass_1041 = paramclass_1041.a60(str);
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] set factionID of " + str + " to " + i);
      paramclass_1041.a141().a36(i);
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
    }
  }
  
  public final void e(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    int i = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue();
    try
    {
      paramclass_1041 = paramclass_1041.a60(str);
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] joining factionID of " + str + " to " + i);
      paramclass_1041.a141().c13(i);
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
    }
  }
  
  public final void f(class_1041 paramclass_1041)
  {
    try
    {
      Object localObject = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      if (((paramclass_1041 = (Sendable)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)((class_748)localObject).a127().selectedEntityId.get())) != null) && ((paramclass_1041 instanceof SegmentController))) {
        paramclass_1041 = (class_797)paramclass_1041;
      } else {
        paramclass_1041 = ((class_748)localObject).b17();
      }
      if ((paramclass_1041 instanceof SegmentController))
      {
        localObject = ((SegmentController)paramclass_1041).getSpawner();
        paramclass_1041 = ((SegmentController)paramclass_1041).getLastModifier();
        localObject = localObject != null ? "unknown" : ((String)localObject).length() > 0 ? localObject : "unknown";
        paramclass_1041 = paramclass_1041 != null ? "unknown" : paramclass_1041.length() > 0 ? paramclass_1041 : "unknown";
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] spawned by " + (String)localObject + "; last modified by " + paramclass_1041);
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      (paramclass_1041 = localPlayerNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      (paramclass_1041 = localPlayerControlledTransformableNotFound).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
    }
  }
  
  public final void g(class_1041 paramclass_1041)
  {
    Object localObject = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      localObject = paramclass_1041.a60((String)localObject);
      paramclass_1041.a59().a12(((class_748)localObject).getName());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
    }
  }
  
  public final void h(class_1041 paramclass_1041)
  {
    try
    {
      Object localObject = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
      Integer localInteger = (Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1];
      localObject = (paramclass_1041 = paramclass_1041.a60((String)localObject)).getInventory(new class_48());
      for (short s : ElementKeyMap.typeList()) {
        try
        {
          System.err.println("ADDING ITEM " + s + " " + localInteger);
          int k = ((class_639)localObject).b8(s, localInteger.intValue());
          paramclass_1041.sendInventoryModification(k, null);
        }
        catch (NoSlotFreeException localNoSlotFreeException)
        {
          this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no slot free for " + ElementKeyMap.getInfo(s).getName());
        }
      }
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
    }
  }
  
  /* Error */
  public final void i(class_1041 paramclass_1041)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	class_1007:jdField_field_1284_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
    //   4: iconst_0
    //   5: aaload
    //   6: checkcast 109	java/lang/String
    //   9: astore_2
    //   10: aload_0
    //   11: getfield 20	class_1007:jdField_field_1284_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
    //   14: iconst_1
    //   15: aaload
    //   16: checkcast 28	java/lang/Integer
    //   19: astore_3
    //   20: aload_0
    //   21: getfield 20	class_1007:jdField_field_1284_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
    //   24: iconst_2
    //   25: aaload
    //   26: checkcast 109	java/lang/String
    //   29: astore 4
    //   31: aload_1
    //   32: aload_2
    //   33: invokevirtual 113	class_1041:a60	(Ljava/lang/String;)Lclass_748;
    //   36: dup
    //   37: astore_1
    //   38: new 26	class_48
    //   41: dup
    //   42: invokespecial 280	class_48:<init>	()V
    //   45: invokevirtual 284	class_748:getInventory	(Lclass_48;)Lclass_639;
    //   48: astore_2
    //   49: invokestatic 290	org/schema/game/common/data/element/ElementKeyMap:typeList	()[S
    //   52: dup
    //   53: astore 5
    //   55: arraylength
    //   56: istore 6
    //   58: iconst_0
    //   59: istore 7
    //   61: iload 7
    //   63: iload 6
    //   65: if_icmpge +256 -> 321
    //   68: aload 5
    //   70: iload 7
    //   72: saload
    //   73: istore 8
    //   75: aload 4
    //   77: iload 8
    //   79: istore 10
    //   81: astore 9
    //   83: iload 10
    //   85: invokestatic 328	org/schema/game/common/data/element/ElementKeyMap:getInfo	(S)Lorg/schema/game/common/data/element/ElementInformation;
    //   88: invokevirtual 337	org/schema/game/common/data/element/ElementInformation:getType	()Ljava/lang/Class;
    //   91: astore 10
    //   93: aload 9
    //   95: ldc_w 339
    //   98: invokevirtual 343	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   101: ifeq +14 -> 115
    //   104: ldc_w 345
    //   107: aload 10
    //   109: invokevirtual 349	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   112: goto +101 -> 213
    //   115: aload 9
    //   117: ldc_w 351
    //   120: invokevirtual 343	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   123: ifeq +14 -> 137
    //   126: ldc_w 353
    //   129: aload 10
    //   131: invokevirtual 349	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   134: goto +79 -> 213
    //   137: aload 9
    //   139: ldc_w 355
    //   142: invokevirtual 343	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   145: ifeq +14 -> 159
    //   148: ldc_w 357
    //   151: aload 10
    //   153: invokevirtual 349	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   156: goto +57 -> 213
    //   159: aload 9
    //   161: ldc_w 359
    //   164: invokevirtual 343	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   167: ifeq +14 -> 181
    //   170: ldc_w 361
    //   173: aload 10
    //   175: invokevirtual 349	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   178: goto +35 -> 213
    //   181: aload 9
    //   183: ldc_w 363
    //   186: invokevirtual 343	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   189: ifeq +14 -> 203
    //   192: ldc_w 365
    //   195: aload 10
    //   197: invokevirtual 349	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   200: goto +13 -> 213
    //   203: new 334	org/schema/game/server/data/admin/UnknownCategoryException
    //   206: dup
    //   207: aload 9
    //   209: invokespecial 366	org/schema/game/server/data/admin/UnknownCategoryException:<init>	(Ljava/lang/String;)V
    //   212: athrow
    //   213: ifeq +67 -> 280
    //   216: getstatic 300	java/lang/System:err	Ljava/io/PrintStream;
    //   219: new 71	java/lang/StringBuilder
    //   222: dup
    //   223: ldc_w 302
    //   226: invokespecial 76	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   229: iload 8
    //   231: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   234: ldc_w 304
    //   237: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: aload_3
    //   241: invokevirtual 307	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   244: ldc_w 304
    //   247: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: aload 4
    //   252: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   258: invokevirtual 312	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   261: aload_2
    //   262: iload 8
    //   264: aload_3
    //   265: invokevirtual 32	java/lang/Integer:intValue	()I
    //   268: invokevirtual 318	class_639:b8	(SI)I
    //   271: istore 9
    //   273: aload_1
    //   274: iload 9
    //   276: aconst_null
    //   277: invokevirtual 322	class_748:sendInventoryModification	(ILclass_48;)V
    //   280: goto +35 -> 315
    //   283: pop
    //   284: aload_0
    //   285: getfield 16	class_1007:jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
    //   288: new 71	java/lang/StringBuilder
    //   291: dup
    //   292: ldc_w 324
    //   295: invokespecial 76	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   298: iload 8
    //   300: invokestatic 328	org/schema/game/common/data/element/ElementKeyMap:getInfo	(S)Lorg/schema/game/common/data/element/ElementInformation;
    //   303: invokevirtual 331	org/schema/game/common/data/element/ElementInformation:getName	()Ljava/lang/String;
    //   306: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   312: invokevirtual 103	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
    //   315: iinc 7 1
    //   318: goto -257 -> 61
    //   321: return
    //   322: pop
    //   323: aload_0
    //   324: getfield 16	class_1007:jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
    //   327: ldc 172
    //   329: invokevirtual 103	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
    //   332: return
    //   333: astore_2
    //   334: aload_0
    //   335: getfield 16	class_1007:jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
    //   338: new 71	java/lang/StringBuilder
    //   341: dup
    //   342: ldc_w 368
    //   345: invokespecial 76	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   348: aload_2
    //   349: invokevirtual 369	org/schema/game/server/data/admin/UnknownCategoryException:a	()Ljava/lang/String;
    //   352: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: ldc_w 371
    //   358: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   364: invokevirtual 103	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
    //   367: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	368	0	this	class_1007
    //   0	368	1	paramclass_1041	class_1041
    //   9	40	2	localObject	Object
    //   19	1	3	localInteger	Integer
    //   29	47	4	str1	String
    //   53	16	5	arrayOfShort	short[]
    //   56	10	6	i	int
    //   59	12	7	j	int
    //   73	5	8	s1	short
    //   81	127	9	str2	String
    //   79	5	10	s2	short
    //   91	105	10	localClass	Class
    // Exception table:
    //   from	to	target	type
    //   75	280	283	org/schema/game/common/data/player/inventory/NoSlotFreeException
    //   0	321	322	org/schema/game/server/data/PlayerNotFountException
    //   0	321	333	org/schema/game/server/data/admin/UnknownCategoryException
  }
  
  public final void j(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      paramclass_1041.a59().b2(str);
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully whitelisted: " + str);
      return;
    }
    catch (NoIPException localNoIPException)
    {
      localNoIPException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not an IP: " + str);
    }
  }
  
  public final void k(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      paramclass_1041.a59().c2(str);
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully banned: " + str);
      return;
    }
    catch (NoIPException localNoIPException)
    {
      localNoIPException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not an IP: " + str);
    }
  }
  
  public final void l(class_1041 paramclass_1041)
  {
    try
    {
      Object localObject1 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      Object localObject2 = new class_48(((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).intValue());
      class_670 localclass_670;
      if ((localclass_670 = paramclass_1041.a62().getSector((class_48)localObject2)) != null)
      {
        localObject1 = ((class_748)localObject1).a124().a1().iterator();
        while (((Iterator)localObject1).hasNext()) {
          if (((localObject2 = (class_755)((Iterator)localObject1).next()).field_1015 instanceof class_797)) {
            paramclass_1041.a59().a45((class_797)((class_755)localObject2).field_1015, localclass_670.field_136, 1);
          }
        }
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localObject2 + ": " + paramclass_1041.a62().getSectorSet());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
    }
  }
  
  public final void m(class_1041 paramclass_1041)
  {
    try
    {
      class_748 localclass_748 = paramclass_1041.a60(((String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).trim());
      class_48 localclass_48 = new class_48(((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[3]).intValue());
      class_670 localclass_670;
      if ((localclass_670 = paramclass_1041.a62().getSector(localclass_48)) != null)
      {
        int i = 0;
        Iterator localIterator = localclass_748.a124().a1().iterator();
        while (localIterator.hasNext())
        {
          class_755 localclass_755;
          if (((localclass_755 = (class_755)localIterator.next()).field_1015 instanceof class_797))
          {
            paramclass_1041.a59().a45((class_797)localclass_755.field_1015, localclass_670.field_136, 1);
            i = 1;
          }
        }
        if (i != 0)
        {
          this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] changed sector for " + localclass_748.getName() + " to " + localclass_48);
          return;
        }
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not changed sector for " + localclass_748.getName() + " to " + localclass_48 + ": Player is not bound to any entity");
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localclass_48 + ": " + paramclass_1041.a62().getSectorSet());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client " + (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
    }
  }
  
  public final void n(class_1041 paramclass_1041)
  {
    try
    {
      paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      class_48 localclass_48 = new class_48(((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).intValue());
      class_670 localclass_670;
      if ((localclass_670 = paramclass_1041.a62().getSector(localclass_48)) != null)
      {
        localclass_670.h();
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] sector repair queued: " + localclass_48);
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localclass_48 + ": " + paramclass_1041.a62().getSectorSet());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
    }
  }
  
  public final void o(class_1041 paramclass_1041)
  {
    try
    {
      paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      Object localObject = new class_48(((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).intValue());
      paramclass_1041 = paramclass_1041.a62().getSector((class_48)localObject);
      if ((!(localObject = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[3]).equals("+")) && (!((String)localObject).equals("-")))
      {
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] operator must be either + or -");
        return;
      }
      String str;
      if ((!(str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[4]).equals("peace")) && (!str.equals("protected"))) {
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode must be either 'peace' or 'protected' (without quotes)");
      }
      if (str.equals("protected"))
      {
        paramclass_1041.b13(((String)localObject).equals("+"));
        return;
      }
      if (str.equals("peace")) {
        paramclass_1041.c6(((String)localObject).equals("+"));
      }
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
    }
  }
  
  public final void p(class_1041 paramclass_1041)
  {
    Object localObject = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      if ((paramclass_1041 = paramclass_1041.a66().a5("%" + DatabaseIndex.a((String)localObject) + "%")).isEmpty())
      {
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] No matches found for '" + (String)localObject + "'");
        return;
      }
      paramclass_1041 = paramclass_1041.iterator();
      while (paramclass_1041.hasNext())
      {
        localObject = (class_757)paramclass_1041.next();
        class_748 localclass_748;
        (localclass_748 = (class_748)this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getPlayerObject()).a129(new ServerMessage("FOUND: " + ((class_757)localObject).jdField_field_1017_of_type_JavaLangString + " -> " + ((class_757)localObject).jdField_field_1017_of_type_Class_48, 0, localclass_748.getId()));
      }
      return;
    }
    catch (SQLException localSQLException)
    {
      localSQLException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION");
    }
  }
  
  public final void q(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    Object localObject = ((String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).trim().toLowerCase(Locale.ENGLISH);
    boolean bool = ((Boolean)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).booleanValue();
    class_917 localclass_917 = null;
    if (((String)localObject).equals("all")) {
      localclass_917 = class_917.field_1144;
    }
    if (((String)localObject).equals("unused")) {
      localclass_917 = class_917.field_1142;
    }
    if (((String)localObject).equals("used")) {
      localclass_917 = class_917.field_1143;
    }
    if (localclass_917 != null) {
      try
      {
        localObject = paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (((Iterator)localObject).hasNext())
        {
          Sendable localSendable;
          if ((((localSendable = (Sendable)((Iterator)localObject).next()) instanceof SegmentController)) && ((!bool) || ((localSendable instanceof class_747))) && (((SegmentController)localSendable).getUniqueIdentifier().split("_")[2].startsWith(str)))
          {
            localSendable.markedForPermanentDelete(true);
            localSendable.setMarkedForDeleteVolatile(true);
            this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] ACTIVE DESPAWNED " + localSendable + " ");
          }
        }
        int i = paramclass_1041.a66().a3(DatabaseIndex.a(str) + "%", localclass_917, null, bool);
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] DESPAWNED " + i + " ENTITIES (MODE: " + localclass_917.name() + ")");
        return;
      }
      catch (SQLException localSQLException)
      {
        localSQLException.printStackTrace();
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION");
        return;
      }
    }
    this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode '" + localclass_917 + "' unknown. mist be 'used', 'unused', or 'all'");
  }
  
  public final void r(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    Object localObject = ((String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).trim().toLowerCase(Locale.ENGLISH);
    boolean bool = ((Boolean)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).booleanValue();
    int i = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[3]).intValue();
    int k = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[4]).intValue();
    int m = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[5]).intValue();
    class_917 localclass_917 = null;
    if (((String)localObject).equals("all")) {
      localclass_917 = class_917.field_1144;
    }
    if (((String)localObject).equals("unused")) {
      localclass_917 = class_917.field_1142;
    }
    if (((String)localObject).equals("used")) {
      localclass_917 = class_917.field_1143;
    }
    localObject = new class_48(i, k, m);
    if (localclass_917 != null) {
      try
      {
        Iterator localIterator = paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Sendable localSendable;
          if ((((localSendable = (Sendable)localIterator.next()) instanceof SegmentController)) && ((!bool) || ((localSendable instanceof class_747)))) {
            if (((localclass_917 != class_917.field_1142) || (((SegmentController)localSendable).getLastModifier().length() != 0)) && ((localclass_917 != class_917.field_1143) || (((SegmentController)localSendable).getLastModifier().length() <= 0))) {
              if ((((SegmentController)localSendable).getUniqueIdentifier().split("_")[2].startsWith(str)) && (((SegmentController)localSendable).getSectorId() == paramclass_1041.a62().getSector((class_48)localObject).a3()))
              {
                localSendable.markedForPermanentDelete(true);
                localSendable.setMarkedForDeleteVolatile(true);
                this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] ACTIVE DESPAWNED " + localSendable);
              }
            }
          }
        }
        int j = paramclass_1041.a66().a3(DatabaseIndex.a(str) + "%", localclass_917, (class_48)localObject, bool);
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] DESPAWNED " + j + " ENTITIES IN SECTOR: " + localObject + " (MODE: " + localclass_917.name() + ")");
        return;
      }
      catch (SQLException localSQLException)
      {
        localSQLException.printStackTrace();
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION");
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] COULD NOT LOAD SECTOR: " + localObject);
        return;
      }
    }
    this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode '" + localclass_917 + "' unknown. mist be 'used', 'unused', or 'all'");
  }
  
  public static void s(class_1041 paramclass_1041)
  {
    synchronized (paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      paramclass_1041 = paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (paramclass_1041.hasNext())
      {
        Sendable localSendable;
        if (((localSendable = (Sendable)paramclass_1041.next()) instanceof SegmentController)) {
          ((SegmentController)localSendable).getSegmentBuffer().a23();
        }
      }
      return;
    }
  }
  
  public final void t(class_1041 paramclass_1041)
  {
    Object localObject1 = new StringBuilder();
    for (int i = 0; i < this.jdField_field_1284_of_type_ArrayOfJavaLangObject.length - 1; i++)
    {
      ((StringBuilder)localObject1).append((String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[i]);
      if (i < this.jdField_field_1284_of_type_ArrayOfJavaLangObject.length - 2) {
        ((StringBuilder)localObject1).append(" ");
      }
    }
    String str = ((StringBuilder)localObject1).toString();
    localObject1 = (Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[(this.jdField_field_1284_of_type_ArrayOfJavaLangObject.length - 1)];
    try
    {
      Object localObject2 = new ArrayList();
      int j = (localObject3 = ElementKeyMap.typeList()).length;
      Object localObject4;
      for (int k = 0; k < j; k++) {
        if (ElementKeyMap.getInfo((localObject4 = Short.valueOf(localObject3[k])).shortValue()).getName().toLowerCase(Locale.ENGLISH).startsWith(str.trim().toLowerCase(Locale.ENGLISH))) {
          ((ArrayList)localObject2).add(localObject4);
        }
      }
      if (((ArrayList)localObject2).size() == 1)
      {
        localObject3 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
        j = ((Short)((ArrayList)localObject2).get(0)).shortValue();
        class_670 localclass_670 = paramclass_1041.a62().getSector(((class_748)localObject3).c2());
        localObject4 = ((class_748)localObject3).b17().getWorldTransform();
        paramclass_1041 = new Vector3f(((Transform)localObject4).origin);
        (localObject2 = new Vector3f(((class_748)localObject3).a8())).scale(2.0F);
        paramclass_1041.add((Tuple3f)localObject2);
        localclass_670.a78().a36(paramclass_1041, j, ((Integer)localObject1).intValue());
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] sucessfully spawned item at " + paramclass_1041);
        return;
      }
      if (((ArrayList)localObject2).isEmpty())
      {
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no element starts with the string: \"" + str + "\"");
        return;
      }
      Object localObject3 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        short s = ((Short)((Iterator)localObject3).next()).shortValue();
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] ambigous string: \"" + str + "\": (" + ElementKeyMap.getInfo(s).getName() + " [" + s + "])" + (((Iterator)localObject3).hasNext() ? ", " : ""));
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] use either the classified name or the one in the parenthesis");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
      return;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Too many arguments");
      return;
    }
    catch (ElementClassNotFoundException localElementClassNotFoundException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + str);
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
    }
  }
  
  public final void u(class_1041 paramclass_1041)
  {
    String str1 = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    Object localObject1 = new StringBuilder();
    for (int i = 1; i < this.jdField_field_1284_of_type_ArrayOfJavaLangObject.length - 1; i++)
    {
      ((StringBuilder)localObject1).append((String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[i]);
      if (i < this.jdField_field_1284_of_type_ArrayOfJavaLangObject.length - 2) {
        ((StringBuilder)localObject1).append(" ");
      }
    }
    String str2 = ((StringBuilder)localObject1).toString();
    localObject1 = (Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[(this.jdField_field_1284_of_type_ArrayOfJavaLangObject.length - 1)];
    try
    {
      ArrayList localArrayList = new ArrayList();
      int j = (localObject2 = ElementKeyMap.typeList()).length;
      for (int k = 0; k < j; k++)
      {
        Short localShort;
        if (ElementKeyMap.getInfo((localShort = Short.valueOf(localObject2[k])).shortValue()).getName().toLowerCase(Locale.ENGLISH).startsWith(str2.trim().toLowerCase(Locale.ENGLISH))) {
          localArrayList.add(localShort);
        }
      }
      if (localArrayList.size() == 1)
      {
        j = (localObject2 = paramclass_1041.a60(str1)).getInventory(null).b8(((Short)localArrayList.get(0)).shortValue(), ((Integer)localObject1).intValue());
        ((class_748)localObject2).sendInventoryModification(j, null);
        return;
      }
      if (localArrayList.isEmpty())
      {
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no element starts with the string: \"" + str2 + "\"");
        return;
      }
      Object localObject2 = localArrayList.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        j = ((Short)((Iterator)localObject2).next()).shortValue();
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] ambigous string: \"" + str2 + "\": (" + ElementKeyMap.getInfo(j).getName() + " [" + j + "])" + (((Iterator)localObject2).hasNext() ? ", " : ""));
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] use either the classified name or the one in the parenthesis");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
      return;
    }
    catch (NoSlotFreeException localNoSlotFreeException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localNoSlotFreeException.getMessage());
      return;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Too many arguments");
      return;
    }
    catch (ElementClassNotFoundException localElementClassNotFoundException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + str2);
    }
  }
  
  public final void v(class_1041 paramclass_1041)
  {
    try
    {
      String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
      Integer localInteger = (Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1];
      paramclass_1041.a60(str).a38(localInteger.intValue());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
    }
  }
  
  public final void w(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    short s = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).shortValue();
    Integer localInteger = (Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2];
    try
    {
      if (ElementKeyMap.isValidType(s))
      {
        int i = (paramclass_1041 = paramclass_1041.a60(str)).getInventory(null).b8(s, localInteger.intValue());
        paramclass_1041.sendInventoryModification(i, null);
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] invalid type " + s);
      return;
    }
    catch (PlayerNotFountException paramclass_1041)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (NoSlotFreeException paramclass_1041)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + s);
      return;
    }
    catch (ElementClassNotFoundException localElementClassNotFoundException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + s);
    }
  }
  
  public final void x(class_1041 paramclass_1041)
  {
    int i = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).intValue();
    int j = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue();
    try
    {
      class_748 localclass_748;
      (localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b17();
      paramclass_1041.a59().a43(5, i, j, class_1216.field_1429, localclass_748.c2());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      (paramclass_1041 = localPlayerNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      (paramclass_1041 = localPlayerControlledTransformableNotFound).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (EntityNotFountException localEntityNotFountException)
    {
      (paramclass_1041 = localEntityNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (ErrorDialogException localErrorDialogException)
    {
      (paramclass_1041 = localErrorDialogException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
    {
      (paramclass_1041 = localEntityAlreadyExistsException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
    }
  }
  
  public final void y(class_1041 paramclass_1041)
  {
    try
    {
      class_748 localclass_748;
      class_797 localclass_797 = (localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b17();
      Vector3f localVector3f1 = new Vector3f(localclass_797.getWorldTransform().origin);
      Vector3f localVector3f2 = new Vector3f(localVector3f1);
      Vector3f localVector3f3;
      (localVector3f3 = new Vector3f(localclass_748.a8())).scale(5000.0F);
      localVector3f2.add(localVector3f3);
      paramclass_1041 = null;
      if ((paramclass_1041 = ((PhysicsExt)paramclass_1041.a62().getSector(localclass_797.getSectorId()).a64()).testRayCollisionPoint(localVector3f1, localVector3f2, false, null, null, false, null, false)).hasHit())
      {
        (paramclass_1041 = new Vector3f(paramclass_1041.hitPointWorld)).sub(localclass_748.a8());
        a1(localclass_797, paramclass_1041.field_615, paramclass_1041.field_616, paramclass_1041.field_617);
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] Object successfully jumped to " + paramclass_1041);
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no object in line of sight");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
    }
  }
  
  public final void z(class_1041 paramclass_1041)
  {
    Object localObject = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      localObject = paramclass_1041.a60((String)localObject);
      paramclass_1041.a59().sendLogout(((class_748)localObject).a3(), "You have been kicked by an admin");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
    }
  }
  
  public final void A(class_1041 paramclass_1041)
  {
    Object localObject1 = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      localObject1 = paramclass_1041.a60((String)localObject1);
      synchronized (paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        paramclass_1041 = paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (paramclass_1041.hasNext())
        {
          Object localObject2;
          if (((localObject2 = (Sendable)paramclass_1041.next()) instanceof class_750))
          {
            localObject2 = (class_750)localObject2;
            if (((class_748)localObject1).a3() == ((class_750)localObject2).a3())
            {
              System.err.println("Killing character " + localObject2);
              ((class_750)localObject2).setMarkedForDeleteVolatile(true);
            }
          }
        }
        return;
      }
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
    }
  }
  
  public final void B(class_1041 paramclass_1041)
  {
    String str = null;
    synchronized (paramclass_1041.a54())
    {
      str = paramclass_1041.a54().toString();
    }
    this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Admins: " + str);
  }
  
  public final void C(class_1041 paramclass_1041)
  {
    String str = null;
    synchronized (paramclass_1041.b8())
    {
      str = paramclass_1041.b8().toString();
    }
    this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Banned: " + str);
  }
  
  public final void D(class_1041 paramclass_1041)
  {
    String str = null;
    synchronized (paramclass_1041.c6())
    {
      str = paramclass_1041.c6().toString();
    }
    this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Banned: " + str);
  }
  
  public final void E(class_1041 paramclass_1041)
  {
    String str = null;
    synchronized (paramclass_1041.e4())
    {
      str = paramclass_1041.e4().toString();
    }
    this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Whitelisted: " + str);
  }
  
  public final void F(class_1041 paramclass_1041)
  {
    String str = null;
    synchronized (paramclass_1041.f3())
    {
      str = paramclass_1041.f3().toString();
    }
    this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Whitelisted: " + str);
  }
  
  public final void G(class_1041 paramclass_1041)
  {
    String str1 = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    String str2;
    if (!class_1070.a4(str2 = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]))
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Invalid Ship name (Only Characters And numbers and -_ allowed)");
      return;
    }
    Transform localTransform;
    (localTransform = new Transform()).setIdentity();
    try
    {
      Object localObject2 = (localObject1 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b17();
      localTransform.set(((class_797)localObject2).getWorldTransform());
      localObject2 = ByteBuffer.allocate(10240);
      paramclass_1041 = class_1216.field_1429.a4(paramclass_1041, str1, str2, localTransform, -1, 0, ((class_748)localObject1).c2(), "<admin>", (ByteBuffer)localObject2);
      System.err.println("[ADMIN] LOADING " + paramclass_1041.getClass());
      paramclass_1041.a(((class_748)localObject1).c2(), false);
      return;
    }
    catch (EntityNotFountException localEntityNotFountException)
    {
      (localObject1 = localEntityNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject1).getMessage());
      return;
    }
    catch (ErrorDialogException localErrorDialogException)
    {
      (localObject1 = localErrorDialogException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject1).getMessage());
      return;
    }
    catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
    {
      (localObject1 = localEntityAlreadyExistsException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Entity already exists: " + ((EntityAlreadyExistsException)localObject1).getMessage());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      (localObject1 = localPlayerNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject1).getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      (localObject1 = localPlayerControlledTransformableNotFound).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject1).getMessage());
      return;
    }
    catch (StateParameterNotFoundException localStateParameterNotFoundException)
    {
      Object localObject1;
      (localObject1 = localStateParameterNotFoundException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((StateParameterNotFoundException)localObject1).getMessage());
    }
  }
  
  public final void H(class_1041 paramclass_1041)
  {
    Object localObject = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      localObject = paramclass_1041.a60((String)localObject);
      paramclass_1041.a59().e2(((class_748)localObject).getName());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
    }
  }
  
  public final void I(class_1041 paramclass_1041)
  {
    String str;
    if (!class_1070.a4(str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]))
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Invalid Ship name (Only Characters And numbers and -_ allowed)");
      return;
    }
    try
    {
      class_748 localclass_748;
      class_797 localclass_797;
      Object localObject;
      if (((localclass_797 = (localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b17()) == null) || (!(localclass_797 instanceof SegmentController)))
      {
        System.err.println("[ADMIN COMMAND]checking selected");
        if (((localObject = (Sendable)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)localclass_748.a127().selectedEntityId.get())) != null) && ((localObject instanceof class_797))) {
          localclass_797 = (class_797)localObject;
        }
      }
      if ((localclass_797 instanceof SegmentController))
      {
        (localObject = (SegmentController)localclass_797).writeAllBufferedSegmentsToDatabase();
        class_1216.field_1429.a12((SegmentController)localObject, str, false);
        paramclass_1041.a53().a103((SegmentController)localObject, str, localclass_748);
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully saved ship in catalog as \"" + str + "\"\n");
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not inside any ship");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
    }
  }
  
  public final void J(class_1041 paramclass_1041)
  {
    try
    {
      class_748 localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      paramclass_1041.a59().b11(localclass_748);
      return;
    }
    catch (PlayerNotFountException paramclass_1041)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
      paramclass_1041.printStackTrace();
    }
  }
  
  public final void K(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    int i = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue();
    int j = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).intValue();
    System.err.println("Spawning " + j + " mobs of type: " + str);
    Transform localTransform;
    (localTransform = new Transform()).setIdentity();
    try
    {
      class_748 localclass_748;
      localObject = (localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b17();
      localTransform.set(((class_797)localObject).getWorldTransform());
      paramclass_1041.a63(j, str, localclass_748.c2(), localTransform, i, class_1216.field_1429);
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      (localObject = localPlayerNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject).getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      (localObject = localPlayerControlledTransformableNotFound).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject).getMessage());
      return;
    }
    catch (EntityNotFountException localEntityNotFountException)
    {
      (localObject = localEntityNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject).getMessage());
      return;
    }
    catch (ErrorDialogException localErrorDialogException)
    {
      (localObject = localErrorDialogException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject).getMessage());
      return;
    }
    catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
    {
      Object localObject;
      (localObject = localEntityAlreadyExistsException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityAlreadyExistsException)localObject).getMessage());
    }
  }
  
  public final void L(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    int i = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).intValue();
    int j = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).intValue();
    try
    {
      class_748 localclass_748;
      localObject1 = (localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b17();
      Vector3f localVector3f1 = new Vector3f(((class_797)localObject1).getWorldTransform().origin);
      Vector3f localVector3f2 = new Vector3f(localVector3f1);
      Object localObject2;
      (localObject2 = new Vector3f(localclass_748.a8())).scale(5000.0F);
      localVector3f2.add((Tuple3f)localObject2);
      (localObject2 = new Transform()).setIdentity();
      if ((localObject1 = ((PhysicsExt)paramclass_1041.a62().getSector(((class_797)localObject1).getSectorId()).a64()).testRayCollisionPoint(localVector3f1, localVector3f2, false, null, null, false, null, false)).hasHit())
      {
        ((Transform)localObject2).origin.set(((CollisionWorld.ClosestRayResultCallback)localObject1).hitPointWorld);
        System.err.println("Spawning " + j + " mobs of type: " + str + " at " + ((CollisionWorld.ClosestRayResultCallback)localObject1).hitPointWorld);
        paramclass_1041.a63(j, str, localclass_748.c2(), (Transform)localObject2, i, class_1216.field_1429);
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no object in line of sight");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      (localObject1 = localPlayerNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject1).getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      (localObject1 = localPlayerControlledTransformableNotFound).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject1).getMessage());
      return;
    }
    catch (EntityNotFountException localEntityNotFountException)
    {
      (localObject1 = localEntityNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject1).getMessage());
      return;
    }
    catch (ErrorDialogException localErrorDialogException)
    {
      (localObject1 = localErrorDialogException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject1).getMessage());
      return;
    }
    catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
    {
      Object localObject1;
      (localObject1 = localEntityAlreadyExistsException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityAlreadyExistsException)localObject1).getMessage());
    }
  }
  
  public final void M(class_1041 paramclass_1041)
  {
    try
    {
      int i = ((Integer)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).intValue();
      class_748 localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      if (((paramclass_1041 = (Sendable)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)localclass_748.a127().selectedEntityId.get())) != null) && ((paramclass_1041 instanceof class_747))) {
        paramclass_1041 = (class_797)paramclass_1041;
      } else {
        paramclass_1041 = localclass_748.b17();
      }
      if ((paramclass_1041 instanceof class_747))
      {
        ((class_747)paramclass_1041).setFactionId(i);
        ((class_747)paramclass_1041).a87().a183(class_776.field_1033).a173("Ship", true);
        ((class_747)paramclass_1041).a87().a183(class_776.field_1034).a173(Boolean.valueOf(true), true);
        ((class_747)paramclass_1041).a87().a13();
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] activated " + paramclass_1041 + " with faction " + i);
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      (localObject = localPlayerNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject).getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      Object localObject;
      (localObject = localPlayerControlledTransformableNotFound).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject).getMessage());
    }
  }
  
  public final void N(class_1041 paramclass_1041)
  {
    try
    {
      class_748 localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      if (((paramclass_1041 = (Sendable)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)localclass_748.a127().selectedEntityId.get())) != null) && ((paramclass_1041 instanceof class_747))) {
        paramclass_1041 = (class_797)paramclass_1041;
      } else {
        paramclass_1041 = localclass_748.b17();
      }
      if ((paramclass_1041 instanceof class_747))
      {
        ((class_747)paramclass_1041).a87().a183(class_776.field_1034).a173(Boolean.valueOf(false), true);
        ((class_747)paramclass_1041).a87().a13();
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      (paramclass_1041 = localPlayerNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      (paramclass_1041 = localPlayerControlledTransformableNotFound).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
    }
  }
  
  public final void O(class_1041 paramclass_1041)
  {
    try
    {
      class_748 localclass_748 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      if (((paramclass_1041 = (Sendable)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get(((Integer)localclass_748.a127().selectedEntityId.get()).intValue())) != null) && ((paramclass_1041 instanceof class_743)))
      {
        ((class_743)paramclass_1041).a72(true);
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] No Shop Selected: " + localclass_748.a127().selectedEntityId.get() + "->(" + paramclass_1041 + ")");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      (paramclass_1041 = localPlayerNotFountException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (NoSlotFreeException localNoSlotFreeException)
    {
      (paramclass_1041 = localNoSlotFreeException).printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] No more slots free " + paramclass_1041.getMessage());
    }
  }
  
  public final void P(class_1041 paramclass_1041)
  {
    try
    {
      paramclass_1041 = null;
      a1(paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()).b17(), 0.0F, 0.0F, 0.0F);
      return;
    }
    catch (PlayerNotFountException paramclass_1041)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (PlayerControlledTransformableNotFound paramclass_1041)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
    }
  }
  
  public final void Q(class_1041 paramclass_1041)
  {
    try
    {
      paramclass_1041 = null;
      a1(paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()).b17(), ((Float)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0]).floatValue(), ((Float)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).floatValue(), ((Float)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).floatValue());
      return;
    }
    catch (PlayerControlledTransformableNotFound paramclass_1041)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
      return;
    }
    catch (PlayerNotFountException paramclass_1041)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramclass_1041.getMessage());
    }
  }
  
  public final void R(class_1041 paramclass_1041)
  {
    String str = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    float f1 = ((Float)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[1]).floatValue();
    float f2 = ((Float)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[2]).floatValue();
    float f3 = ((Float)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[3]).floatValue();
    try
    {
      a1(paramclass_1041.a60(str).b17(), f1, f2, f3);
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] teleported " + str + " to ");
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
      return;
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not spawed");
    }
  }
  
  public final void S(class_1041 paramclass_1041)
  {
    Object localObject1 = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      class_748 localclass_748 = paramclass_1041.a60((String)localObject1);
      localObject1 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
      Object localObject2 = localclass_748.b17();
      new Vector3f(((class_797)localObject2).getWorldTransform().origin).field_615 += 1.0F;
      class_48 localclass_48 = new class_48(((class_748)localObject1).a44());
      class_670 localclass_670;
      if ((localclass_670 = paramclass_1041.a62().getSector(localclass_48)) != null)
      {
        if (localclass_748.c2() != localclass_670.a3())
        {
          localObject1 = ((class_748)localObject1).a124().a1().iterator();
          while (((Iterator)localObject1).hasNext()) {
            if (((localObject2 = (class_755)((Iterator)localObject1).next()).field_1015 instanceof class_797)) {
              paramclass_1041.a59().a45((class_797)((class_755)localObject2).field_1015, localclass_748.a44(), 1);
            }
          }
          return;
        }
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] not changing sector for object " + ((class_797)localObject2).getSectorId() + "/" + localclass_670.a3());
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localclass_48 + ": " + paramclass_1041.a62().getSectorSet());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
    }
  }
  
  public final void T(class_1041 paramclass_1041)
  {
    Object localObject1 = (String)this.jdField_field_1284_of_type_ArrayOfJavaLangObject[0];
    try
    {
      Object localObject2 = paramclass_1041.a60((String)localObject1);
      class_797 localclass_797 = (localObject1 = paramclass_1041.a61(this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b17();
      new Vector3f(localclass_797.getWorldTransform().origin).field_615 += 1.0F;
      localObject1 = new class_48(((class_748)localObject1).a44());
      class_670 localclass_670;
      if ((localclass_670 = paramclass_1041.a62().getSector((class_48)localObject1)) != null)
      {
        if (((class_748)localObject2).c2() != localclass_670.a3())
        {
          localObject1 = ((class_748)localObject2).a124().a1().iterator();
          while (((Iterator)localObject1).hasNext()) {
            if (((localObject2 = (class_755)((Iterator)localObject1).next()).field_1015 instanceof class_797)) {
              paramclass_1041.a59().a45((class_797)((class_755)localObject2).field_1015, localclass_670.field_136, 1);
            }
          }
          return;
        }
        this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] not changing sector for object " + localclass_797.getSectorId() + "/" + localclass_670.a3());
        return;
      }
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localObject1 + ": " + paramclass_1041.a62().getSectorSet());
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.jdField_field_1284_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
    }
  }
  
  private static void a1(class_797 paramclass_797, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramclass_797.warpTransformable(paramFloat1, paramFloat2, paramFloat3, true);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1007
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */