/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import java.io.PrintStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Set;
/*      */ import javax.vecmath.Tuple3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.controller.database.DatabaseIndex;
/*      */ import org.schema.game.common.data.element.ElementClassNotFoundException;
/*      */ import org.schema.game.common.data.element.ElementInformation;
/*      */ import org.schema.game.common.data.element.ElementKeyMap;
/*      */ import org.schema.game.common.data.physics.PhysicsExt;
/*      */ import org.schema.game.common.data.player.PlayerControlledTransformableNotFound;
/*      */ import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.game.network.objects.NetworkPlayer;
/*      */ import org.schema.game.server.controller.EntityAlreadyExistsException;
/*      */ import org.schema.game.server.controller.EntityNotFountException;
/*      */ import org.schema.game.server.controller.GameServerController;
/*      */ import org.schema.game.server.controller.NoIPException;
/*      */ import org.schema.game.server.data.PlayerNotFountException;
/*      */ import org.schema.game.server.data.admin.AdminCommands;
/*      */ import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*      */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*      */ import org.schema.schine.network.NetworkStateContainer;
/*      */ import org.schema.schine.network.RegisteredClientOnServer;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*      */ import org.schema.schine.network.server.ServerMessage;
/*      */ 
/*      */ public final class vv
/*      */ {
/*      */   public Object[] a;
/*      */   public AdminCommands a;
/*      */   public RegisteredClientOnServer a;
/*      */ 
/*      */   public vv(RegisteredClientOnServer paramRegisteredClientOnServer, AdminCommands paramAdminCommands, Object[] paramArrayOfObject)
/*      */   {
/*   79 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer = paramRegisteredClientOnServer;
/*   80 */     this.jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
/*   81 */     this.jdField_a_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
/*      */   }
/*      */ 
/*      */   public final void a(vg paramvg)
/*      */   {
/*  543 */     q localq1 = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/*      */     try
/*      */     {
/*  546 */       localObject = new ObjectArrayFIFOQueue(4096);
/*  547 */       for (int i = localq1.c << 4; i < (localq1.c << 4) + 16; i++) {
/*  548 */         for (int j = localq1.b << 4; j < (localq1.b << 4) + 16; j++) {
/*  549 */           for (int k = localq1.a << 4; k < (localq1.a << 4) + 16; k++) {
/*  550 */             q localq2 = new q(k, j, i);
/*      */ 
/*  552 */             ((ObjectArrayFIFOQueue)localObject).enqueue(localq2);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  557 */       paramvg.a = ((ObjectArrayFIFOQueue)localObject);
/*      */       return;
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */       Object localObject;
/*  558 */       (
/*  559 */         localObject = 
/*  561 */         localException).printStackTrace();
/*  560 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] critical: " + localObject.getClass().getSimpleName() + ": " + ((Exception)localObject).getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void b(vg paramvg) {
/*  565 */     q localq1 = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/*  566 */     q localq2 = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[4]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[5]).intValue());
/*      */     try
/*      */     {
/*  569 */       localObject = new ObjectArrayFIFOQueue(4096);
/*  570 */       for (int i = localq1.c; i <= localq2.c; i++) {
/*  571 */         for (int j = localq1.b; j <= localq2.b; j++) {
/*  572 */           for (int k = localq1.a; k <= localq2.a; k++) {
/*  573 */             q localq3 = new q(k, j, i);
/*      */ 
/*  575 */             ((ObjectArrayFIFOQueue)localObject).enqueue(localq3);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  580 */       paramvg.a = ((ObjectArrayFIFOQueue)localObject);
/*      */       return;
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */       Object localObject;
/*  581 */       (
/*  582 */         localObject = 
/*  584 */         localException).printStackTrace();
/*  583 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] critical: " + localObject.getClass().getSimpleName() + ": " + ((Exception)localObject).getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void c(vg paramvg)
/*      */   {
/*  632 */     Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  633 */     int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/*      */     try
/*      */     {
/*  636 */       localObject = paramvg.a((String)localObject);
/*      */       lP locallP;
/*  639 */       if ((
/*  639 */         locallP = paramvg.a().a(((lE)localObject).h())) != null)
/*      */       {
/*  642 */         if ((lX)locallP.a().get(((lE)localObject).getName()) != null)
/*      */         {
/*  643 */           if ((i > 0) && (i < 6)) {
/*  644 */             locallP.a("ADMIN", ((lE)localObject).getName(), (byte)(i - 1), paramvg.a()); return;
/*      */           }
/*  646 */           this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] role id must be between 1 and 5 "); return;
/*      */         }
/*      */ 
/*  649 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not part of the faction " + locallP.a());
/*      */ 
/*  651 */         return;
/*  652 */       }this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not in a faction; fid: " + ((lE)localObject).h());
/*      */       return;
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) {
/*  656 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void d(vg paramvg)
/*      */   {
/*  686 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  687 */     int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/*      */     try { paramvg = paramvg.a(str);
/*  691 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] set factionID of " + str + " to " + i);
/*  692 */       paramvg.a().a(i);
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); }
/*      */   }
/*      */ 
/*      */   public final void e(vg paramvg) {
/*  698 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  699 */     int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/*      */     try { paramvg = paramvg.a(str);
/*  703 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] joining factionID of " + str + " to " + i);
/*  704 */       paramvg.a().c(i);
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); }
/*      */ 
/*      */   }
/*      */ 
/*      */   public final void f(vg paramvg)
/*      */   {
/*      */     try
/*      */     {
/*  719 */       Object localObject = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/*  725 */       if (((
/*  725 */         paramvg = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)((lE)localObject).a().selectedEntityId.get())) != null) && 
/*  725 */         ((paramvg instanceof SegmentController)))
/*  726 */         paramvg = (mF)paramvg;
/*      */       else {
/*  728 */         paramvg = ((lE)localObject).b();
/*      */       }
/*      */ 
/*  731 */       if ((paramvg instanceof SegmentController)) {
/*  732 */         localObject = ((SegmentController)paramvg).getSpawner();
/*  733 */         paramvg = ((SegmentController)paramvg).getLastModifier();
/*  734 */         localObject = localObject != null ? "unknown" : ((String)localObject).length() > 0 ? localObject : "unknown";
/*  735 */         paramvg = paramvg != null ? "unknown" : paramvg.length() > 0 ? paramvg : "unknown";
/*  736 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] spawned by " + (String)localObject + "; last modified by " + paramvg);
/*  737 */         return;
/*  738 */       }this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
/*  739 */       return; } catch (PlayerNotFountException localPlayerNotFountException) { (paramvg
/*  748 */          = localPlayerNotFountException)
/*  743 */         .printStackTrace();
/*  744 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) { (
/*  746 */         paramvg = 
/*  748 */         localPlayerControlledTransformableNotFound).printStackTrace();
/*  747 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void g(vg paramvg)
/*      */   {
/*  834 */     Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try {
/*  837 */       localObject = paramvg.a((String)localObject);
/*  838 */       paramvg.a().a(((lE)localObject).getName());
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); }
/*      */   }
/*      */ 
/*      */   public final void h(vg paramvg)
/*      */   {
/*      */     try
/*      */     {
/*  847 */       Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  848 */       Integer localInteger = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1];
/*      */ 
/*  851 */       localObject = (
/*  851 */         paramvg = paramvg.a((String)localObject))
/*  851 */         .getInventory(new q());
/*  852 */       for (short s : ElementKeyMap.typeList())
/*      */         try {
/*  854 */           System.err.println("ADDING ITEM " + s + " " + localInteger);
/*  855 */           int k = ((mf)localObject).b(s, localInteger.intValue());
/*  856 */           paramvg.sendInventoryModification(k, null);
/*      */         } catch (NoSlotFreeException localNoSlotFreeException) {
/*  858 */           this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no slot free for " + ElementKeyMap.getInfo(s).getName());
/*      */         }
/*      */       return;
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) {
/*  862 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); }  } 
/*      */   // ERROR //
/*      */   public final void i(vg paramvg) { // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 211	vv:jdField_a_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
/*      */     //   4: iconst_0
/*      */     //   5: aaload
/*      */     //   6: checkcast 120	java/lang/String
/*      */     //   9: astore_2
/*      */     //   10: aload_0
/*      */     //   11: getfield 211	vv:jdField_a_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
/*      */     //   14: iconst_1
/*      */     //   15: aaload
/*      */     //   16: checkcast 117	java/lang/Integer
/*      */     //   19: astore_3
/*      */     //   20: aload_0
/*      */     //   21: getfield 211	vv:jdField_a_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
/*      */     //   24: iconst_2
/*      */     //   25: aaload
/*      */     //   26: checkcast 120	java/lang/String
/*      */     //   29: astore 4
/*      */     //   31: aload_1
/*      */     //   32: aload_2
/*      */     //   33: invokevirtual 370	vg:a	(Ljava/lang/String;)LlE;
/*      */     //   36: dup
/*      */     //   37: astore_1
/*      */     //   38: new 181	q
/*      */     //   41: dup
/*      */     //   42: invokespecial 354	q:<init>	()V
/*      */     //   45: invokevirtual 284	lE:getInventory	(Lq;)Lmf;
/*      */     //   48: astore_2
/*      */     //   49: invokestatic 318	org/schema/game/common/data/element/ElementKeyMap:typeList	()[S
/*      */     //   52: dup
/*      */     //   53: astore 5
/*      */     //   55: arraylength
/*      */     //   56: istore 6
/*      */     //   58: iconst_0
/*      */     //   59: istore 7
/*      */     //   61: iload 7
/*      */     //   63: iload 6
/*      */     //   65: if_icmpge +242 -> 307
/*      */     //   68: aload 5
/*      */     //   70: iload 7
/*      */     //   72: saload
/*      */     //   73: istore 8
/*      */     //   75: aload 4
/*      */     //   77: iload 8
/*      */     //   79: istore 10
/*      */     //   81: astore 9
/*      */     //   83: iload 10
/*      */     //   85: invokestatic 316	org/schema/game/common/data/element/ElementKeyMap:getInfo	(S)Lorg/schema/game/common/data/element/ElementInformation;
/*      */     //   88: invokevirtual 315	org/schema/game/common/data/element/ElementInformation:getType	()Ljava/lang/Class;
/*      */     //   91: astore 10
/*      */     //   93: aload 9
/*      */     //   95: ldc 99
/*      */     //   97: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   100: ifeq +13 -> 113
/*      */     //   103: ldc 162
/*      */     //   105: aload 10
/*      */     //   107: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*      */     //   110: goto +93 -> 203
/*      */     //   113: aload 9
/*      */     //   115: ldc 97
/*      */     //   117: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   120: ifeq +13 -> 133
/*      */     //   123: ldc 160
/*      */     //   125: aload 10
/*      */     //   127: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*      */     //   130: goto +73 -> 203
/*      */     //   133: aload 9
/*      */     //   135: ldc 98
/*      */     //   137: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   140: ifeq +13 -> 153
/*      */     //   143: ldc 161
/*      */     //   145: aload 10
/*      */     //   147: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*      */     //   150: goto +53 -> 203
/*      */     //   153: aload 9
/*      */     //   155: ldc 94
/*      */     //   157: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   160: ifeq +13 -> 173
/*      */     //   163: ldc 159
/*      */     //   165: aload 10
/*      */     //   167: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*      */     //   170: goto +33 -> 203
/*      */     //   173: aload 9
/*      */     //   175: ldc 93
/*      */     //   177: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   180: ifeq +13 -> 193
/*      */     //   183: ldc 158
/*      */     //   185: aload 10
/*      */     //   187: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*      */     //   190: goto +13 -> 203
/*      */     //   193: new 173	org/schema/game/server/data/admin/UnknownCategoryException
/*      */     //   196: dup
/*      */     //   197: aload 9
/*      */     //   199: invokespecial 342	org/schema/game/server/data/admin/UnknownCategoryException:<init>	(Ljava/lang/String;)V
/*      */     //   202: athrow
/*      */     //   203: ifeq +64 -> 267
/*      */     //   206: getstatic 189	java/lang/System:err	Ljava/io/PrintStream;
/*      */     //   209: new 121	java/lang/StringBuilder
/*      */     //   212: dup
/*      */     //   213: ldc 30
/*      */     //   215: invokespecial 244	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   218: iload 8
/*      */     //   220: invokevirtual 245	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*      */     //   223: ldc 3
/*      */     //   225: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   228: aload_3
/*      */     //   229: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*      */     //   232: ldc 3
/*      */     //   234: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   237: aload 4
/*      */     //   239: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   242: invokevirtual 248	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   245: invokevirtual 223	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   248: aload_2
/*      */     //   249: iload 8
/*      */     //   251: aload_3
/*      */     //   252: invokevirtual 231	java/lang/Integer:intValue	()I
/*      */     //   255: invokevirtual 297	mf:b	(SI)I
/*      */     //   258: istore 9
/*      */     //   260: aload_1
/*      */     //   261: iload 9
/*      */     //   263: aconst_null
/*      */     //   264: invokevirtual 287	lE:sendInventoryModification	(ILq;)V
/*      */     //   267: goto +34 -> 301
/*      */     //   270: pop
/*      */     //   271: aload_0
/*      */     //   272: getfield 210	vv:jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
/*      */     //   275: new 121	java/lang/StringBuilder
/*      */     //   278: dup
/*      */     //   279: ldc 59
/*      */     //   281: invokespecial 244	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   284: iload 8
/*      */     //   286: invokestatic 316	org/schema/game/common/data/element/ElementKeyMap:getInfo	(S)Lorg/schema/game/common/data/element/ElementInformation;
/*      */     //   289: invokevirtual 314	org/schema/game/common/data/element/ElementInformation:getName	()Ljava/lang/String;
/*      */     //   292: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   295: invokevirtual 248	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   298: invokevirtual 351	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
/*      */     //   301: iinc 7 1
/*      */     //   304: goto -243 -> 61
/*      */     //   307: return
/*      */     //   308: pop
/*      */     //   309: aload_0
/*      */     //   310: getfield 210	vv:jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
/*      */     //   313: ldc 67
/*      */     //   315: invokevirtual 351	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
/*      */     //   318: return
/*      */     //   319: astore_2
/*      */     //   320: aload_0
/*      */     //   321: getfield 210	vv:jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
/*      */     //   324: new 121	java/lang/StringBuilder
/*      */     //   327: dup
/*      */     //   328: ldc 72
/*      */     //   330: invokespecial 244	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   333: aload_2
/*      */     //   334: invokevirtual 343	org/schema/game/server/data/admin/UnknownCategoryException:a	()Ljava/lang/String;
/*      */     //   337: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   340: ldc 27
/*      */     //   342: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   345: invokevirtual 248	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   348: invokevirtual 351	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
/*      */     //   351: return
/*      */     //
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   75	267	270	org/schema/game/common/data/player/inventory/NoSlotFreeException
/*      */     //   0	307	308	org/schema/game/server/data/PlayerNotFountException
/*      */     //   0	307	319	org/schema/game/server/data/admin/UnknownCategoryException } 
/*  894 */   public final void j(vg paramvg) { String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try {
/*  897 */       paramvg.a().b(str);
/*  898 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully whitelisted: " + str);
/*      */       return;
/*      */     } catch (NoIPException localNoIPException) {
/*  902 */       localNoIPException.printStackTrace();
/*      */ 
/*  901 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not an IP: " + str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void k(vg paramvg)
/*      */   {
/*  913 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try {
/*  916 */       paramvg.a().c(str);
/*  917 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully banned: " + str);
/*      */       return;
/*      */     } catch (NoIPException localNoIPException) {
/*  921 */       localNoIPException.printStackTrace();
/*      */ 
/*  920 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not an IP: " + str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void l(vg paramvg)
/*      */   {
/*      */     try
/*      */     {
/*  951 */       Object localObject1 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/*  954 */       Object localObject2 = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/*      */       mx localmx;
/*  956 */       if ((
/*  956 */         localmx = paramvg.a().getSector((q)localObject2)) != null)
/*      */       {
/*  957 */         for (localObject1 = ((lE)localObject1).a().a().iterator(); ((Iterator)localObject1).hasNext(); )
/*  958 */           if (((
/*  958 */             localObject2 = (lA)((Iterator)localObject1).next()).a instanceof mF))
/*      */           {
/*  959 */             paramvg.a().a((mF)((lA)localObject2).a, localmx.jdField_a_of_type_Q, 1); }  return;
/*      */       }
/*      */ 
/*  963 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localObject2 + ": " + paramvg.a().getSectorSet());
/*      */       return;
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*      */       return;
/*      */     }
/*      */     catch (Exception localException) {
/*  970 */       localException.printStackTrace();
/*      */ 
/*  969 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void m(vg paramvg)
/*      */   {
/*      */     try {
/*  976 */       lE locallE = paramvg.a(((String)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).trim());
/*      */ 
/*  979 */       q localq = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).intValue());
/*      */       mx localmx;
/*  981 */       if ((
/*  981 */         localmx = paramvg.a().getSector(localq)) != null)
/*      */       {
/*  982 */         int i = 0;
/*  983 */         for (Iterator localIterator = locallE.a().a().iterator(); localIterator.hasNext(); )
/*      */         {
/*      */           lA locallA;
/*  984 */           if (((
/*  984 */             locallA = (lA)localIterator.next()).a instanceof mF))
/*      */           {
/*  985 */             paramvg.a().a((mF)locallA.a, localmx.jdField_a_of_type_Q, 1);
/*  986 */             i = 1;
/*      */           }
/*      */         }
/*  989 */         if (i != 0) {
/*  990 */           this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] changed sector for " + locallE.getName() + " to " + localq); return;
/*      */         }
/*  992 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not changed sector for " + locallE.getName() + " to " + localq + ": Player is not bound to any entity");
/*      */ 
/*  994 */         return; } this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localq + ": " + paramvg.a().getSectorSet());
/*      */       return;
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client " + (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0]);
/*      */       return;
/*      */     }
/*      */     catch (Exception localException) {
/* 1002 */       localException.printStackTrace();
/*      */ 
/* 1001 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void n(vg paramvg)
/*      */   {
/*      */     try
/*      */     {
/* 1011 */       paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/* 1014 */       q localq = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/*      */       mx localmx;
/* 1016 */       if ((
/* 1016 */         localmx = paramvg.a().getSector(localq)) != null)
/*      */       {
/* 1017 */         localmx.h();
/* 1018 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] sector repair queued: " + localq); return;
/* 1020 */       }this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localq + ": " + paramvg.a().getSectorSet());
/*      */       return;
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*      */       return;
/*      */     }
/*      */     catch (Exception localException) {
/* 1027 */       localException.printStackTrace();
/*      */ 
/* 1026 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void o(vg paramvg)
/*      */   {
/*      */     try {
/* 1033 */       paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/* 1036 */       Object localObject = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/* 1037 */       paramvg = paramvg.a().getSector((q)localObject);
/*      */ 
/* 1041 */       if ((!(
/* 1041 */         localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[3])
/* 1041 */         .equals("+")) && (!((String)localObject).equals("-"))) {
/* 1042 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] operator must be either + or -");
/*      */         return;
/*      */       }
/*      */       String str;
/* 1046 */       if ((!(
/* 1046 */         str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[4])
/* 1046 */         .equals("peace")) && (!str.equals("protected"))) {
/* 1047 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode must be either 'peace' or 'protected' (without quotes)");
/*      */       }
/* 1049 */       if (str.equals("protected")) {
/* 1050 */         paramvg.b(((String)localObject).equals("+")); return;
/* 1051 */       }if (str.equals("peace"))
/* 1052 */         paramvg.c(((String)localObject).equals("+"));
/*      */       return;
/*      */     }
/* 1056 */     catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*      */       return;
/*      */     }
/*      */     catch (Exception localException) {
/* 1060 */       localException.printStackTrace();
/*      */ 
/* 1059 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void p(vg paramvg)
/*      */   {
/* 1088 */     Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try
/*      */     {
/* 1093 */       if ((
/* 1093 */         paramvg = paramvg.a().a("%" + DatabaseIndex.a((String)localObject) + "%"))
/* 1093 */         .isEmpty()) {
/* 1094 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] No matches found for '" + (String)localObject + "'"); return;
/*      */       }
/* 1096 */       for (paramvg = paramvg.iterator(); paramvg.hasNext(); ) { localObject = (kw)paramvg.next();
/*      */         lE locallE;
/* 1097 */         (
/* 1098 */           locallE = (lE)this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getPlayerObject())
/* 1098 */           .a(new ServerMessage("FOUND: " + ((kw)localObject).jdField_a_of_type_JavaLangString + " -> " + ((kw)localObject).jdField_a_of_type_Q, 0, locallE.getId()));
/*      */       }
/*      */       return;
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1104 */       localSQLException.printStackTrace();
/*      */ 
/* 1103 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION");
/*      */     }
/*      */   }
/*      */ 
/* 1107 */   public final void q(vg paramvg) { String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1108 */     Object localObject = ((String)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).trim().toLowerCase(Locale.ENGLISH);
/* 1109 */     boolean bool = ((Boolean)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).booleanValue();
/*      */ 
/* 1111 */     kB localkB = null;
/*      */ 
/* 1113 */     if (((String)localObject).equals("all")) {
/* 1114 */       localkB = kB.c;
/*      */     }
/* 1116 */     if (((String)localObject).equals("unused")) {
/* 1117 */       localkB = kB.a;
/*      */     }
/* 1119 */     if (((String)localObject).equals("used")) {
/* 1120 */       localkB = kB.b;
/*      */     }
/* 1122 */     if (localkB != null)
/*      */       try {
/* 1124 */         for (localObject = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); ((Iterator)localObject).hasNext(); )
/*      */         {
/*      */           Sendable localSendable;
/* 1125 */           if ((((
/* 1125 */             localSendable = (Sendable)((Iterator)localObject).next()) instanceof SegmentController)) && 
/* 1125 */             ((!bool) || ((localSendable instanceof kd))) && 
/* 1126 */             (((SegmentController)localSendable).getUniqueIdentifier().split("_")[2].startsWith(str))) {
/* 1127 */             localSendable.markedForPermanentDelete(true);
/* 1128 */             localSendable.setMarkedForDeleteVolatile(true);
/* 1129 */             this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] ACTIVE DESPAWNED " + localSendable + " ");
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1134 */         int i = paramvg.a().a(DatabaseIndex.a(str) + "%", localkB, null, bool);
/* 1135 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] DESPAWNED " + i + " ENTITIES (MODE: " + localkB.name() + ")");
/*      */         return;
/*      */       } catch (SQLException localSQLException) { localSQLException.printStackTrace();
/*      */ 
/* 1138 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION");
/* 1139 */         return;
/*      */       }
/* 1141 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode '" + localkB + "' unknown. mist be 'used', 'unused', or 'all'"); }
/*      */ 
/*      */   public final void r(vg paramvg)
/*      */   {
/* 1145 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1146 */     Object localObject = ((String)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).trim().toLowerCase(Locale.ENGLISH);
/* 1147 */     boolean bool = ((Boolean)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).booleanValue();
/*      */ 
/* 1149 */     int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).intValue();
/* 1150 */     int k = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[4]).intValue();
/* 1151 */     int m = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[5]).intValue();
/*      */ 
/* 1153 */     kB localkB = null;
/*      */ 
/* 1155 */     if (((String)localObject).equals("all")) {
/* 1156 */       localkB = kB.c;
/*      */     }
/* 1158 */     if (((String)localObject).equals("unused")) {
/* 1159 */       localkB = kB.a;
/*      */     }
/* 1161 */     if (((String)localObject).equals("used")) {
/* 1162 */       localkB = kB.b;
/*      */     }
/* 1164 */     localObject = new q(i, k, m);
/* 1165 */     if (localkB != null)
/*      */       try {
/* 1167 */         for (Iterator localIterator = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*      */         {
/*      */           Sendable localSendable;
/* 1168 */           if ((((
/* 1168 */             localSendable = (Sendable)localIterator.next()) instanceof SegmentController)) && 
/* 1168 */             ((!bool) || ((localSendable instanceof kd))))
/* 1169 */             if (((localkB != kB.a) || (((SegmentController)localSendable).getLastModifier().length() != 0)) && (
/* 1170 */               (localkB != kB.b) || (((SegmentController)localSendable).getLastModifier().length() <= 0)))
/*      */             {
/* 1173 */               if ((((SegmentController)localSendable).getUniqueIdentifier().split("_")[2].startsWith(str)) && (((SegmentController)localSendable).getSectorId() == paramvg.a().getSector((q)localObject).a()))
/*      */               {
/* 1177 */                 localSendable.markedForPermanentDelete(true);
/* 1178 */                 localSendable.setMarkedForDeleteVolatile(true);
/* 1179 */                 this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] ACTIVE DESPAWNED " + localSendable);
/*      */               }
/*      */             } 
/* 1183 */         }int j = paramvg.a().a(DatabaseIndex.a(str) + "%", localkB, (q)localObject, bool);
/* 1184 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] DESPAWNED " + j + " ENTITIES IN SECTOR: " + localObject + " (MODE: " + localkB.name() + ")");
/*      */         return; } catch (SQLException localSQLException) { localSQLException.printStackTrace();
/*      */ 
/* 1187 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION");
/*      */         return; } catch (Exception localException) { localException.printStackTrace();
/*      */ 
/* 1190 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] COULD NOT LOAD SECTOR: " + localObject);
/* 1191 */         return;
/*      */       }
/* 1193 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode '" + localkB + "' unknown. mist be 'used', 'unused', or 'all'");
/*      */   }
/*      */ 
/*      */   public static void s(vg paramvg)
/*      */   {
/* 1238 */     synchronized (paramvg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 1239 */       for (paramvg = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); paramvg.hasNext(); )
/*      */       {
/*      */         Sendable localSendable;
/* 1240 */         if (((
/* 1240 */           localSendable = (Sendable)paramvg.next()) instanceof SegmentController))
/*      */         {
/* 1241 */           ((SegmentController)localSendable).getSegmentBuffer().a();
/*      */         }
/*      */       }
/* 1244 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void t(vg paramvg)
/*      */   {
/* 1250 */     Object localObject1 = new StringBuilder();
/* 1251 */     for (int i = 0; i < this.jdField_a_of_type_ArrayOfJavaLangObject.length - 1; i++) {
/* 1252 */       ((StringBuilder)localObject1).append((String)this.jdField_a_of_type_ArrayOfJavaLangObject[i]);
/* 1253 */       if (i < this.jdField_a_of_type_ArrayOfJavaLangObject.length - 2) {
/* 1254 */         ((StringBuilder)localObject1).append(" ");
/*      */       }
/*      */     }
/* 1257 */     String str = ((StringBuilder)localObject1).toString();
/*      */ 
/* 1259 */     localObject1 = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(this.jdField_a_of_type_ArrayOfJavaLangObject.length - 1)];
/*      */     try
/*      */     {
/* 1263 */       Object localObject2 = new ArrayList();
/* 1264 */       int j = (localObject3 = ElementKeyMap.typeList()).length;
/*      */       Object localObject4;
/* 1264 */       for (int k = 0; k < j; k++) {
/* 1265 */         if (ElementKeyMap.getInfo((
/* 1265 */           localObject4 = Short.valueOf(localObject3[k]))
/* 1265 */           .shortValue()).getName().toLowerCase(Locale.ENGLISH).startsWith(str.trim().toLowerCase(Locale.ENGLISH))) {
/* 1266 */           ((ArrayList)localObject2).add(localObject4);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1271 */       if (((ArrayList)localObject2).size() == 1)
/*      */       {
/* 1273 */         localObject3 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/* 1275 */         j = ((Short)((ArrayList)localObject2).get(0)).shortValue();
/*      */ 
/* 1279 */         mx localmx = paramvg.a().getSector(((lE)localObject3).c());
/* 1280 */         localObject4 = ((lE)localObject3).b().getWorldTransform();
/* 1281 */         paramvg = new Vector3f(((Transform)localObject4).origin);
/* 1282 */         (
/* 1283 */           localObject2 = new Vector3f(((lE)localObject3).a()))
/* 1283 */           .scale(2.0F);
/* 1284 */         paramvg.add((Tuple3f)localObject2);
/*      */ 
/* 1290 */         localmx.a().a(paramvg, j, ((Integer)localObject1).intValue());
/*      */ 
/* 1292 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] sucessfully spawned item at " + paramvg);
/* 1293 */         return; } if (((ArrayList)localObject2).isEmpty()) {
/* 1294 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no element starts with the string: \"" + str + "\""); return;
/*      */       }
/* 1296 */       Object localObject3 = ((ArrayList)localObject2).iterator();
/*      */ 
/* 1298 */       while (((Iterator)localObject3).hasNext()) {
/* 1299 */         short s = ((Short)((Iterator)localObject3).next()).shortValue();
/* 1300 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] ambigous string: \"" + str + "\": (" + ElementKeyMap.getInfo(s).getName() + " [" + s + "])" + (((Iterator)localObject3).hasNext() ? ", " : ""));
/*      */       }
/* 1302 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] use either the classified name or the one in the parenthesis");
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/*      */       return; } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Too many arguments");
/*      */       return; } catch (ElementClassNotFoundException localElementClassNotFoundException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + str);
/*      */       return;
/*      */     } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1312 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void u(vg paramvg)
/*      */   {
/* 1318 */     String str1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */ 
/* 1320 */     Object localObject1 = new StringBuilder();
/* 1321 */     for (int i = 1; i < this.jdField_a_of_type_ArrayOfJavaLangObject.length - 1; i++) {
/* 1322 */       ((StringBuilder)localObject1).append((String)this.jdField_a_of_type_ArrayOfJavaLangObject[i]);
/* 1323 */       if (i < this.jdField_a_of_type_ArrayOfJavaLangObject.length - 2) {
/* 1324 */         ((StringBuilder)localObject1).append(" ");
/*      */       }
/*      */     }
/* 1327 */     String str2 = ((StringBuilder)localObject1).toString();
/*      */ 
/* 1329 */     localObject1 = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(this.jdField_a_of_type_ArrayOfJavaLangObject.length - 1)];
/*      */     try
/*      */     {
/* 1333 */       ArrayList localArrayList = new ArrayList();
/* 1334 */       int j = (localObject2 = ElementKeyMap.typeList()).length; for (int k = 0; k < j; k++)
/*      */       {
/*      */         Short localShort;
/* 1335 */         if (ElementKeyMap.getInfo((
/* 1335 */           localShort = Short.valueOf(localObject2[k]))
/* 1335 */           .shortValue()).getName().toLowerCase(Locale.ENGLISH).startsWith(str2.trim().toLowerCase(Locale.ENGLISH))) {
/* 1336 */           localArrayList.add(localShort);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1341 */       if (localArrayList.size() == 1)
/*      */       {
/* 1345 */         j = (
/* 1345 */           localObject2 = paramvg.a(str1))
/* 1345 */           .getInventory(null).b(((Short)localArrayList.get(0)).shortValue(), ((Integer)localObject1).intValue());
/* 1346 */         ((lE)localObject2).sendInventoryModification(j, null);
/* 1347 */         return; } if (localArrayList.isEmpty()) {
/* 1348 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no element starts with the string: \"" + str2 + "\""); return;
/*      */       }
/* 1350 */       Object localObject2 = localArrayList.iterator();
/*      */ 
/* 1352 */       while (((Iterator)localObject2).hasNext()) {
/* 1353 */         j = ((Short)((Iterator)localObject2).next()).shortValue();
/* 1354 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] ambigous string: \"" + str2 + "\": (" + ElementKeyMap.getInfo(j).getName() + " [" + j + "])" + (((Iterator)localObject2).hasNext() ? ", " : ""));
/*      */       }
/* 1356 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] use either the classified name or the one in the parenthesis");
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/*      */       return; } catch (NoSlotFreeException localNoSlotFreeException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localNoSlotFreeException.getMessage());
/*      */       return; } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Too many arguments");
/*      */       return;
/*      */     } catch (ElementClassNotFoundException localElementClassNotFoundException) {
/* 1366 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + str2);
/*      */     }
/*      */   }
/*      */   public final void v(vg paramvg) {
/*      */     try {
/* 1374 */       String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1375 */       Integer localInteger = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1];
/* 1376 */       paramvg.a(str)
/* 1377 */         .a(localInteger.intValue());
/*      */       return;
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); }
/*      */ 
/*      */   }
/*      */ 
/*      */   public final void w(vg paramvg)
/*      */   {
/* 1385 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1386 */     short s = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).shortValue();
/* 1387 */     Integer localInteger = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2];
/*      */     try
/*      */     {
/* 1390 */       if (ElementKeyMap.isValidType(s))
/*      */       {
/* 1394 */         int i = (
/* 1394 */           paramvg = paramvg.a(str))
/* 1394 */           .getInventory(null).b(s, localInteger.intValue());
/* 1395 */         paramvg.sendInventoryModification(i, null);
/* 1396 */         return; } this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] invalid type " + s);
/*      */       return; } catch (PlayerNotFountException paramvg) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (NoSlotFreeException paramvg) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + s);
/*      */       return;
/*      */     } catch (ElementClassNotFoundException localElementClassNotFoundException) {
/* 1406 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + s);
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void x(vg paramvg)
/*      */   {
/* 1412 */     int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue();
/* 1413 */     int j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/*      */     try { lE locallE;
/* 1417 */       (
/* 1418 */         locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()))
/* 1418 */         .b();
/*      */ 
/* 1420 */       paramvg.a().a(5, i, j, tH.a, locallE.c());
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { (
/* 1424 */         paramvg = 
/* 1438 */         localPlayerNotFountException).printStackTrace();
/* 1425 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) { (
/* 1427 */         paramvg = 
/* 1438 */         localPlayerControlledTransformableNotFound).printStackTrace();
/* 1428 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (EntityNotFountException localEntityNotFountException) { (
/* 1430 */         paramvg = 
/* 1438 */         localEntityNotFountException).printStackTrace();
/* 1431 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (ErrorDialogException localErrorDialogException) { (
/* 1433 */         paramvg = 
/* 1438 */         localErrorDialogException).printStackTrace();
/* 1434 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) { (
/* 1436 */         paramvg = 
/* 1438 */         localEntityAlreadyExistsException).printStackTrace();
/* 1437 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void y(vg paramvg)
/*      */   {
/*      */     try
/*      */     {
/*      */       lE locallE;
/* 1445 */       mF localmF = (
/* 1445 */         locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()))
/* 1445 */         .b();
/* 1446 */       Vector3f localVector3f1 = new Vector3f(localmF.getWorldTransform().origin);
/* 1447 */       Vector3f localVector3f2 = new Vector3f(localVector3f1);
/*      */       Vector3f localVector3f3;
/* 1448 */       (
/* 1449 */         localVector3f3 = new Vector3f(locallE.a()))
/* 1449 */         .scale(5000.0F);
/* 1450 */       localVector3f2.add(localVector3f3);
/*      */ 
/* 1452 */       paramvg = null;
/*      */ 
/* 1454 */       if ((
/* 1454 */         paramvg = ((PhysicsExt)paramvg.a().getSector(localmF.getSectorId())
/* 1452 */         .a()).testRayCollisionPoint(localVector3f1, localVector3f2, false, null, null, false, null, false))
/* 1454 */         .hasHit()) {
/* 1455 */         (
/* 1456 */           paramvg = new Vector3f(paramvg.hitPointWorld))
/* 1456 */           .sub(locallE.a());
/* 1457 */         a(localmF, paramvg.x, paramvg.y, paramvg.z);
/* 1458 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] Object successfully jumped to " + paramvg);
/* 1459 */         return;
/* 1460 */       }this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no object in line of sight");
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/*      */       return;
/*      */     } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1466 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void z(vg paramvg) {
/* 1471 */     Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try {
/* 1474 */       localObject = paramvg.a((String)localObject);
/* 1475 */       paramvg.a().sendLogout(((lE)localObject).a(), "You have been kicked by an admin");
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); }
/*      */   }
/*      */ 
/*      */   public final void A(vg paramvg)
/*      */   {
/* 1482 */     Object localObject1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try
/*      */     {
/* 1485 */       localObject1 = paramvg.a((String)localObject1);
/* 1486 */       synchronized (paramvg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 1487 */         for (paramvg = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); paramvg.hasNext(); )
/*      */         {
/*      */           Object localObject2;
/* 1488 */           if (((
/* 1488 */             localObject2 = (Sendable)paramvg.next()) instanceof lD))
/*      */           {
/* 1489 */             localObject2 = (lD)localObject2;
/* 1490 */             if (((lE)localObject1).a() == ((lD)localObject2).a()) {
/* 1491 */               System.err.println("Killing character " + localObject2);
/* 1492 */               ((lD)localObject2).setMarkedForDeleteVolatile(true);
/*      */             }
/*      */           }
/*      */         }
/* 1496 */         return;
/*      */       }
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); }
/*      */ 
/*      */   }
/*      */ 
/*      */   public final void B(vg paramvg)
/*      */   {
/* 1504 */     String str = null;
/* 1505 */     synchronized (paramvg.a()) {
/* 1506 */       str = paramvg.a().toString();
/*      */     }
/* 1508 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Admins: " + str);
/*      */   }
/*      */ 
/*      */   public final void C(vg paramvg)
/*      */   {
/* 1513 */     String str = null;
/* 1514 */     synchronized (paramvg.b()) {
/* 1515 */       str = paramvg.b().toString();
/*      */     }
/* 1517 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Banned: " + str);
/*      */   }
/*      */ 
/*      */   public final void D(vg paramvg)
/*      */   {
/* 1522 */     String str = null;
/* 1523 */     synchronized (paramvg.c()) {
/* 1524 */       str = paramvg.c().toString();
/*      */     }
/* 1526 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Banned: " + str);
/*      */   }
/*      */ 
/*      */   public final void E(vg paramvg) {
/* 1530 */     String str = null;
/* 1531 */     synchronized (paramvg.e()) {
/* 1532 */       str = paramvg.e().toString();
/*      */     }
/* 1534 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Whitelisted: " + str);
/*      */   }
/*      */ 
/*      */   public final void F(vg paramvg)
/*      */   {
/* 1539 */     String str = null;
/* 1540 */     synchronized (paramvg.f()) {
/* 1541 */       str = paramvg.f().toString();
/*      */     }
/* 1543 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Whitelisted: " + str);
/*      */   }
/*      */ 
/*      */   public final void G(vg paramvg)
/*      */   {
/* 1555 */     String str1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     String str2;
/* 1558 */     if (!ve.a(str2 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[1]))
/*      */     {
/* 1559 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Invalid Ship name (Only Characters And numbers and -_ allowed)");
/*      */       return;
/*      */     }
/*      */     Transform localTransform;
/* 1563 */     (
/* 1564 */       localTransform = new Transform())
/* 1564 */       .setIdentity();
/*      */     try { Object localObject2 = (
/* 1567 */         localObject1 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()))
/* 1567 */         .b();
/*      */ 
/* 1569 */       localTransform.set(((mF)localObject2).getWorldTransform());
/* 1570 */       localObject2 = ByteBuffer.allocate(10240);
/* 1571 */       paramvg = tH.a.a(paramvg, str1, str2, localTransform, -1, 0, ((lE)localObject1).c(), "<admin>", (ByteBuffer)localObject2);
/*      */ 
/* 1573 */       System.err.println("[ADMIN] LOADING " + paramvg.getClass());
/* 1574 */       paramvg.a(((lE)localObject1).c());
/*      */       return; } catch (EntityNotFountException localEntityNotFountException) { (
/* 1576 */         localObject1 = 
/* 1593 */         localEntityNotFountException).printStackTrace();
/* 1577 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject1).getMessage());
/*      */       return; } catch (ErrorDialogException localErrorDialogException) { (
/* 1579 */         localObject1 = 
/* 1593 */         localErrorDialogException).printStackTrace();
/* 1580 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject1).getMessage());
/*      */       return; } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) { (
/* 1582 */         localObject1 = 
/* 1593 */         localEntityAlreadyExistsException).printStackTrace();
/* 1583 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Entity already exists: " + ((EntityAlreadyExistsException)localObject1).getMessage());
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { (
/* 1585 */         localObject1 = 
/* 1593 */         localPlayerNotFountException).printStackTrace();
/* 1586 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject1).getMessage());
/*      */       return; } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) { (
/* 1588 */         localObject1 = 
/* 1593 */         localPlayerControlledTransformableNotFound).printStackTrace();
/* 1589 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject1).getMessage());
/*      */       return;
/*      */     }
/*      */     catch (StateParameterNotFoundException localStateParameterNotFoundException)
/*      */     {
/*      */       Object localObject1;
/* 1590 */       (
/* 1591 */         localObject1 = 
/* 1593 */         localStateParameterNotFoundException).printStackTrace();
/* 1592 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((StateParameterNotFoundException)localObject1).getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void H(vg paramvg)
/*      */   {
/* 1602 */     Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try {
/* 1605 */       localObject = paramvg.a((String)localObject);
/* 1606 */       paramvg.a().d(((lE)localObject).getName());
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); }
/*      */ 
/*      */   }
/*      */ 
/*      */   public final void I(vg paramvg)
/*      */   {
/*      */     String str;
/* 1619 */     if (!ve.a(str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0]))
/*      */     {
/* 1620 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Invalid Ship name (Only Characters And numbers and -_ allowed)");
/* 1621 */       return;
/*      */     }
/*      */     try
/*      */     {
/*      */       lE locallE;
/*      */       mF localmF;
/*      */       Object localObject;
/* 1629 */       if (((
/* 1629 */         localmF = (
/* 1627 */         locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()))
/* 1627 */         .b()) == null) || 
/* 1629 */         (!(localmF instanceof SegmentController))) {
/* 1630 */         System.err.println("[ADMIN COMMAND]checking selected");
/*      */ 
/* 1632 */         if (((
/* 1632 */           localObject = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)locallE.a().selectedEntityId.get())) != null) && 
/* 1632 */           ((localObject instanceof mF))) {
/* 1633 */           localmF = (mF)localObject;
/*      */         }
/*      */       }
/*      */ 
/* 1637 */       if ((localmF instanceof SegmentController)) {
/* 1638 */         (
/* 1639 */           localObject = (SegmentController)localmF)
/* 1639 */           .writeAllBufferedSegmentsToDatabase();
/* 1640 */         tH.a.a((SegmentController)localObject, str, false);
/*      */ 
/* 1642 */         paramvg.a().a((SegmentController)localObject, str, locallE);
/* 1643 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully saved ship in catalog as \"" + str + "\"\n");
/* 1644 */         return; } this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not inside any ship");
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/*      */       return;
/*      */     } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1650 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void J(vg paramvg) {
/*      */     try {
/* 1658 */       lE locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 1659 */       paramvg.a().b(locallE);
/*      */       return;
/*      */     } catch (PlayerNotFountException paramvg) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/* 1662 */       paramvg.printStackTrace(); }
/*      */   }
/*      */ 
/*      */   public final void K(vg paramvg) {
/* 1666 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1667 */     int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/* 1668 */     int j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue();
/* 1669 */     System.err.println("Spawning " + j + " mobs of type: " + str);
/*      */     Transform localTransform;
/* 1670 */     (
/* 1671 */       localTransform = new Transform())
/* 1671 */       .setIdentity();
/*      */     try { lE locallE;
/* 1678 */       localObject = (
/* 1678 */         locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()))
/* 1678 */         .b();
/*      */ 
/* 1680 */       localTransform.set(((mF)localObject).getWorldTransform());
/*      */ 
/* 1682 */       paramvg.a(j, str, locallE.c(), localTransform, i, tH.a);
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { (
/* 1685 */         localObject = 
/* 1699 */         localPlayerNotFountException).printStackTrace();
/* 1686 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject).getMessage());
/*      */       return; } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) { (
/* 1688 */         localObject = 
/* 1699 */         localPlayerControlledTransformableNotFound).printStackTrace();
/* 1689 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject).getMessage());
/*      */       return; } catch (EntityNotFountException localEntityNotFountException) { (
/* 1691 */         localObject = 
/* 1699 */         localEntityNotFountException).printStackTrace();
/* 1692 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject).getMessage());
/*      */       return; } catch (ErrorDialogException localErrorDialogException) { (
/* 1694 */         localObject = 
/* 1699 */         localErrorDialogException).printStackTrace();
/* 1695 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject).getMessage());
/*      */       return;
/*      */     }
/*      */     catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
/*      */     {
/*      */       Object localObject;
/* 1696 */       (
/* 1697 */         localObject = 
/* 1699 */         localEntityAlreadyExistsException).printStackTrace();
/* 1698 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityAlreadyExistsException)localObject).getMessage());
/*      */     }
/*      */   }
/*      */ 
/* 1702 */   public final void L(vg paramvg) { String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1703 */     int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/* 1704 */     int j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue();
/*      */     try
/*      */     {
/*      */       lE locallE;
/* 1709 */       localObject1 = (
/* 1709 */         locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()))
/* 1709 */         .b();
/* 1710 */       Vector3f localVector3f1 = new Vector3f(((mF)localObject1).getWorldTransform().origin);
/* 1711 */       Vector3f localVector3f2 = new Vector3f(localVector3f1);
/*      */       Object localObject2;
/* 1712 */       (
/* 1713 */         localObject2 = new Vector3f(locallE.a()))
/* 1713 */         .scale(5000.0F);
/* 1714 */       localVector3f2.add((Tuple3f)localObject2);
/*      */ 
/* 1716 */       (
/* 1717 */         localObject2 = new Transform())
/* 1717 */         .setIdentity();
/*      */ 
/* 1721 */       if ((
/* 1721 */         localObject1 = ((PhysicsExt)paramvg.a().getSector(((mF)localObject1).getSectorId())
/* 1719 */         .a()).testRayCollisionPoint(localVector3f1, localVector3f2, false, null, null, false, null, false))
/* 1721 */         .hasHit())
/*      */       {
/* 1723 */         ((Transform)localObject2).origin.set(((CollisionWorld.ClosestRayResultCallback)localObject1).hitPointWorld);
/*      */ 
/* 1725 */         System.err.println("Spawning " + j + " mobs of type: " + str + " at " + ((CollisionWorld.ClosestRayResultCallback)localObject1).hitPointWorld);
/*      */ 
/* 1729 */         paramvg.a(j, str, locallE.c(), (Transform)localObject2, i, tH.a); return; } this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no object in line of sight");
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { (
/* 1734 */         localObject1 = 
/* 1748 */         localPlayerNotFountException).printStackTrace();
/* 1735 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject1).getMessage());
/*      */       return; } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) { (
/* 1737 */         localObject1 = 
/* 1748 */         localPlayerControlledTransformableNotFound).printStackTrace();
/* 1738 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject1).getMessage());
/*      */       return; } catch (EntityNotFountException localEntityNotFountException) { (
/* 1740 */         localObject1 = 
/* 1748 */         localEntityNotFountException).printStackTrace();
/* 1741 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject1).getMessage());
/*      */       return; } catch (ErrorDialogException localErrorDialogException) { (
/* 1743 */         localObject1 = 
/* 1748 */         localErrorDialogException).printStackTrace();
/* 1744 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject1).getMessage());
/*      */       return;
/*      */     }
/*      */     catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
/*      */     {
/*      */       Object localObject1;
/* 1745 */       (
/* 1746 */         localObject1 = 
/* 1748 */         localEntityAlreadyExistsException).printStackTrace();
/* 1747 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityAlreadyExistsException)localObject1).getMessage());
/*      */     } }
/*      */ 
/*      */   public final void M(vg paramvg)
/*      */   {
/*      */     try {
/* 1753 */       int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue();
/* 1754 */       lE locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/* 1760 */       if (((
/* 1760 */         paramvg = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)locallE.a().selectedEntityId.get())) != null) && 
/* 1760 */         ((paramvg instanceof kd)))
/* 1761 */         paramvg = (mF)paramvg;
/*      */       else {
/* 1763 */         paramvg = locallE.b();
/*      */       }
/*      */ 
/* 1766 */       if ((paramvg instanceof kd)) {
/* 1767 */         ((kd)paramvg).setFactionId(i);
/* 1768 */         ((kd)paramvg).a().a(kq.b).a("Ship", true);
/* 1769 */         ((kd)paramvg).a().a(kq.c).a(Boolean.valueOf(true), true);
/* 1770 */         ((kd)paramvg).a().a();
/* 1771 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] activated " + paramvg + " with faction " + i); return;
/*      */       }
/* 1773 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
/* 1774 */       return;
/*      */     }
/*      */     catch (PlayerNotFountException localPlayerNotFountException)
/*      */     {
/* 1783 */       (localObject
/* 1783 */          = localPlayerNotFountException)
/* 1778 */         .printStackTrace();
/* 1779 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject).getMessage());
/*      */       return;
/*      */     }
/*      */     catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
/*      */     {
/*      */       Object localObject;
/* 1780 */       (
/* 1781 */         localObject = 
/* 1783 */         localPlayerControlledTransformableNotFound).printStackTrace();
/* 1782 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject).getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void N(vg paramvg)
/*      */   {
/*      */     try
/*      */     {
/* 1790 */       lE locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/* 1796 */       if (((
/* 1796 */         paramvg = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)locallE.a().selectedEntityId.get())) != null) && 
/* 1796 */         ((paramvg instanceof kd)))
/* 1797 */         paramvg = (mF)paramvg;
/*      */       else {
/* 1799 */         paramvg = locallE.b();
/*      */       }
/* 1801 */       if ((paramvg instanceof kd)) {
/* 1802 */         ((kd)paramvg).a().a(kq.c).a(Boolean.valueOf(false), true);
/* 1803 */         ((kd)paramvg).a().a(); return;
/*      */       }
/* 1805 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
/* 1806 */       return; } catch (PlayerNotFountException localPlayerNotFountException) { (paramvg
/* 1815 */          = localPlayerNotFountException)
/* 1810 */         .printStackTrace();
/* 1811 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) { (
/* 1813 */         paramvg = 
/* 1815 */         localPlayerControlledTransformableNotFound).printStackTrace();
/* 1814 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void O(vg paramvg)
/*      */   {
/*      */     try
/*      */     {
/* 1823 */       lE locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/* 1828 */       if (((
/* 1828 */         paramvg = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(((Integer)locallE.a().selectedEntityId.get()).intValue())) != null) && 
/* 1828 */         ((paramvg instanceof kf))) {
/* 1829 */         ((kf)paramvg)
/* 1830 */           .a();
/* 1831 */         return; } this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] No Shop Selected: " + locallE.a().selectedEntityId.get() + "->(" + paramvg + ")");
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { (
/* 1838 */         paramvg = 
/* 1843 */         localPlayerNotFountException).printStackTrace();
/* 1839 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return; } catch (NoSlotFreeException localNoSlotFreeException) {
/* 1840 */       (
/* 1841 */         paramvg = 
/* 1843 */         localNoSlotFreeException).printStackTrace();
/* 1842 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] No more slots free " + paramvg.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void P(vg paramvg)
/*      */   {
/*      */     try
/*      */     {
/* 1870 */       paramvg = null; a(paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())
/* 1870 */         .b(), 0.0F, 0.0F, 0.0F);
/*      */       return;
/*      */     }
/*      */     catch (PlayerNotFountException paramvg)
/*      */     {
/* 1872 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return;
/*      */     }
/*      */     catch (PlayerControlledTransformableNotFound paramvg)
/*      */     {
/* 1874 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void Q(vg paramvg) {
/*      */     try {
/* 1883 */       paramvg = null; a(paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())
/* 1883 */         .b(), ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).floatValue(), ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).floatValue(), ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).floatValue());
/*      */       return;
/*      */     }
/* 1889 */     catch (PlayerControlledTransformableNotFound paramvg) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */       return;
/*      */     }
/*      */     catch (PlayerNotFountException paramvg) {
/* 1891 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void R(vg paramvg)
/*      */   {
/* 1897 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1898 */     float f1 = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).floatValue();
/* 1899 */     float f2 = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).floatValue();
/* 1900 */     float f3 = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).floatValue();
/*      */     try { a(paramvg.a(str)
/* 1904 */         .b(), 
/* 1905 */         f1, f2, f3);
/* 1906 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] teleported " + str + " to ");
/*      */       return; } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*      */       return; } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1910 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not spawed");
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void S(vg paramvg)
/*      */   {
/* 1917 */     Object localObject1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try
/*      */     {
/* 1920 */       lE locallE = paramvg.a((String)localObject1);
/* 1921 */       localObject1 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*      */ 
/* 1923 */       Object localObject2 = locallE.b();
/* 1924 */       new Vector3f(((mF)localObject2).getWorldTransform().origin).x
/* 1925 */          += 1.0F;
/* 1926 */       q localq = new q(((lE)localObject1).a());
/*      */       mx localmx;
/* 1928 */       if ((
/* 1928 */         localmx = paramvg.a().getSector(localq)) != null)
/*      */       {
/* 1929 */         if (locallE.c() != localmx.a()) {
/* 1930 */           for (localObject1 = ((lE)localObject1).a().a().iterator(); ((Iterator)localObject1).hasNext(); )
/* 1931 */             if (((
/* 1931 */               localObject2 = (lA)((Iterator)localObject1).next()).a instanceof mF))
/*      */             {
/* 1932 */               paramvg.a().a((mF)((lA)localObject2).a, locallE.a(), 1); }  return;
/*      */         }
/*      */ 
/* 1936 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] not changing sector for object " + ((mF)localObject2).getSectorId() + "/" + localmx.a()); return;
/*      */       }
/*      */ 
/* 1939 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localq + ": " + paramvg.a().getSectorSet());
/*      */       return;
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*      */       return;
/*      */     }
/*      */     catch (Exception localException) {
/* 1947 */       localException.printStackTrace();
/*      */ 
/* 1946 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void T(vg paramvg)
/*      */   {
/* 1952 */     Object localObject1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*      */     try
/*      */     {
/* 1955 */       Object localObject2 = paramvg.a((String)localObject1);
/*      */ 
/* 1958 */       mF localmF = (
/* 1958 */         localObject1 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()))
/* 1958 */         .b();
/* 1959 */       new Vector3f(localmF.getWorldTransform().origin).x
/* 1960 */          += 1.0F;
/* 1961 */       localObject1 = new q(((lE)localObject1).a());
/*      */       mx localmx;
/* 1963 */       if ((
/* 1963 */         localmx = paramvg.a().getSector((q)localObject1)) != null)
/*      */       {
/* 1964 */         if (((lE)localObject2).c() != localmx.a()) {
/* 1965 */           for (localObject1 = ((lE)localObject2).a().a().iterator(); ((Iterator)localObject1).hasNext(); )
/* 1966 */             if (((
/* 1966 */               localObject2 = (lA)((Iterator)localObject1).next()).a instanceof mF))
/*      */             {
/* 1967 */               paramvg.a().a((mF)((lA)localObject2).a, localmx.jdField_a_of_type_Q, 1); }  return;
/*      */         }
/*      */ 
/* 1971 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] not changing sector for object " + localmF.getSectorId() + "/" + localmx.a()); return;
/*      */       }
/*      */ 
/* 1974 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localObject1 + ": " + paramvg.a().getSectorSet());
/*      */       return;
/*      */     } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*      */       return;
/*      */     }
/*      */     catch (Exception localException) {
/* 1982 */       localException.printStackTrace();
/*      */ 
/* 1981 */       this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void a(mF parammF, float paramFloat1, float paramFloat2, float paramFloat3)
/*      */   {
/* 2010 */     parammF.warpTransformable(paramFloat1, paramFloat2, paramFloat3, true);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vv
 * JD-Core Version:    0.6.2
 */