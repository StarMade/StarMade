/*    1:     */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*    2:     */import com.bulletphysics.linearmath.Transform;
/*    3:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    4:     */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*    5:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    6:     */import java.io.PrintStream;
/*    7:     */import java.nio.ByteBuffer;
/*    8:     */import java.sql.SQLException;
/*    9:     */import java.util.ArrayList;
/*   10:     */import java.util.HashMap;
/*   11:     */import java.util.HashSet;
/*   12:     */import java.util.Iterator;
/*   13:     */import java.util.List;
/*   14:     */import java.util.Locale;
/*   15:     */import java.util.Set;
/*   16:     */import javax.vecmath.Tuple3f;
/*   17:     */import javax.vecmath.Vector3f;
/*   18:     */import org.schema.game.common.controller.SegmentController;
/*   19:     */import org.schema.game.common.controller.database.DatabaseIndex;
/*   20:     */import org.schema.game.common.data.element.ElementClassNotFoundException;
/*   21:     */import org.schema.game.common.data.element.ElementInformation;
/*   22:     */import org.schema.game.common.data.element.ElementKeyMap;
/*   23:     */import org.schema.game.common.data.physics.PhysicsExt;
/*   24:     */import org.schema.game.common.data.player.PlayerControlledTransformableNotFound;
/*   25:     */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*   26:     */import org.schema.game.common.data.world.Universe;
/*   27:     */import org.schema.game.network.objects.NetworkPlayer;
/*   28:     */import org.schema.game.server.controller.EntityAlreadyExistsException;
/*   29:     */import org.schema.game.server.controller.EntityNotFountException;
/*   30:     */import org.schema.game.server.controller.GameServerController;
/*   31:     */import org.schema.game.server.controller.NoIPException;
/*   32:     */import org.schema.game.server.data.PlayerNotFountException;
/*   33:     */import org.schema.game.server.data.admin.AdminCommands;
/*   34:     */import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*   35:     */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*   36:     */import org.schema.schine.network.NetworkStateContainer;
/*   37:     */import org.schema.schine.network.RegisteredClientOnServer;
/*   38:     */import org.schema.schine.network.objects.Sendable;
/*   39:     */import org.schema.schine.network.objects.remote.RemoteInteger;
/*   40:     */import org.schema.schine.network.server.ServerMessage;
/*   41:     */
/*   71:     */public final class vv
/*   72:     */{
/*   73:     */  public Object[] a;
/*   74:     */  public AdminCommands a;
/*   75:     */  public RegisteredClientOnServer a;
/*   76:     */  
/*   77:     */  public vv(RegisteredClientOnServer paramRegisteredClientOnServer, AdminCommands paramAdminCommands, Object[] paramArrayOfObject)
/*   78:     */  {
/*   79:  79 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer = paramRegisteredClientOnServer;
/*   80:  80 */    this.jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
/*   81:  81 */    this.jdField_a_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
/*   82:     */  }
/*   83:     */  
/*  541:     */  public final void a(vg paramvg)
/*  542:     */  {
/*  543: 543 */    q localq1 = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/*  544:     */    try
/*  545:     */    {
/*  546: 546 */      localObject = new ObjectArrayFIFOQueue(4096);
/*  547: 547 */      for (int i = localq1.c << 4; i < (localq1.c << 4) + 16; i++) {
/*  548: 548 */        for (int j = localq1.b << 4; j < (localq1.b << 4) + 16; j++) {
/*  549: 549 */          for (int k = localq1.a << 4; k < (localq1.a << 4) + 16; k++) {
/*  550: 550 */            q localq2 = new q(k, j, i);
/*  551:     */            
/*  552: 552 */            ((ObjectArrayFIFOQueue)localObject).enqueue(localq2);
/*  553:     */          }
/*  554:     */        }
/*  555:     */      }
/*  556:     */      
/*  557: 557 */      paramvg.a = ((ObjectArrayFIFOQueue)localObject); return;
/*  558:     */    } catch (Exception localException) { Object localObject;
/*  559: 559 */      (localObject = 
/*  560:     */      
/*  561: 561 */        localException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] critical: " + localObject.getClass().getSimpleName() + ": " + ((Exception)localObject).getMessage());
/*  562:     */    }
/*  563:     */  }
/*  564:     */  
/*  565:     */  public final void b(vg paramvg) {
/*  566: 565 */    q localq1 = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/*  567: 566 */    q localq2 = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[4]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[5]).intValue());
/*  568:     */    try
/*  569:     */    {
/*  570: 569 */      localObject = new ObjectArrayFIFOQueue(4096);
/*  571: 570 */      for (int i = localq1.c; i <= localq2.c; i++) {
/*  572: 571 */        for (int j = localq1.b; j <= localq2.b; j++) {
/*  573: 572 */          for (int k = localq1.a; k <= localq2.a; k++) {
/*  574: 573 */            q localq3 = new q(k, j, i);
/*  575:     */            
/*  576: 575 */            ((ObjectArrayFIFOQueue)localObject).enqueue(localq3);
/*  577:     */          }
/*  578:     */        }
/*  579:     */      }
/*  580:     */      
/*  581: 580 */      paramvg.a = ((ObjectArrayFIFOQueue)localObject); return;
/*  582:     */    } catch (Exception localException) { Object localObject;
/*  583: 582 */      (localObject = 
/*  584:     */      
/*  585: 584 */        localException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] critical: " + localObject.getClass().getSimpleName() + ": " + ((Exception)localObject).getMessage());
/*  586:     */    }
/*  587:     */  }
/*  588:     */  
/*  632:     */  public final void c(vg paramvg)
/*  633:     */  {
/*  634: 632 */    Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  635: 633 */    int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/*  636:     */    try
/*  637:     */    {
/*  638: 636 */      localObject = paramvg.a((String)localObject);
/*  639:     */      
/*  640:     */      lP locallP;
/*  641: 639 */      if ((locallP = paramvg.a().a(((lE)localObject).h())) != null)
/*  642:     */      {
/*  644: 642 */        if ((lX)locallP.a().get(((lE)localObject).getName()) != null) {
/*  645: 643 */          if ((i > 0) && (i < 6)) {
/*  646: 644 */            locallP.a("ADMIN", ((lE)localObject).getName(), (byte)(i - 1), paramvg.a());return;
/*  647:     */          }
/*  648: 646 */          this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] role id must be between 1 and 5 ");return;
/*  649:     */        }
/*  650:     */        
/*  651: 649 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not part of the faction " + locallP.a());
/*  652:     */        
/*  653: 651 */        return; }
/*  654: 652 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not in a faction; fid: " + ((lE)localObject).h()); return;
/*  655:     */    }
/*  656:     */    catch (PlayerNotFountException localPlayerNotFountException)
/*  657:     */    {
/*  658: 656 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*  659:     */    }
/*  660:     */  }
/*  661:     */  
/*  686:     */  public final void d(vg paramvg)
/*  687:     */  {
/*  688: 686 */    String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  689: 687 */    int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/*  690:     */    try
/*  691:     */    {
/*  692: 690 */      paramvg = paramvg.a(str);
/*  693: 691 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] set factionID of " + str + " to " + i);
/*  694: 692 */      paramvg.a().a(i); return;
/*  695:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/*  696: 694 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*  697:     */    }
/*  698:     */  }
/*  699:     */  
/*  700: 698 */  public final void e(vg paramvg) { String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  701: 699 */    int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/*  702:     */    try
/*  703:     */    {
/*  704: 702 */      paramvg = paramvg.a(str);
/*  705: 703 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] joining factionID of " + str + " to " + i);
/*  706: 704 */      paramvg.a().c(i); return;
/*  707:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/*  708: 706 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*  709:     */    }
/*  710:     */  }
/*  711:     */  
/*  717:     */  public final void f(vg paramvg)
/*  718:     */  {
/*  719:     */    try
/*  720:     */    {
/*  721: 719 */      Object localObject = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/*  722:     */      
/*  727: 725 */      if (((paramvg = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)((lE)localObject).a().selectedEntityId.get())) != null) && ((paramvg instanceof SegmentController))) {
/*  728: 726 */        paramvg = (mF)paramvg;
/*  729:     */      } else {
/*  730: 728 */        paramvg = ((lE)localObject).b();
/*  731:     */      }
/*  732:     */      
/*  733: 731 */      if ((paramvg instanceof SegmentController)) {
/*  734: 732 */        localObject = ((SegmentController)paramvg).getSpawner();
/*  735: 733 */        paramvg = ((SegmentController)paramvg).getLastModifier();
/*  736: 734 */        localObject = localObject != null ? "unknown" : ((String)localObject).length() > 0 ? localObject : "unknown";
/*  737: 735 */        paramvg = paramvg != null ? "unknown" : paramvg.length() > 0 ? paramvg : "unknown";
/*  738: 736 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] spawned by " + (String)localObject + "; last modified by " + paramvg);
/*  739: 737 */        return; }
/*  740: 738 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
/*  741: 739 */      return;
/*  742:     */    }
/*  743:     */    catch (PlayerNotFountException localPlayerNotFountException)
/*  744:     */    {
/*  745: 743 */      
/*  746:     */      
/*  750: 748 */        (paramvg = localPlayerNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/*  751:     */    }
/*  752:     */    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
/*  753:     */    {
/*  754: 746 */      (paramvg = 
/*  755:     */      
/*  756: 748 */        localPlayerControlledTransformableNotFound).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/*  757:     */    }
/*  758:     */  }
/*  759:     */  
/*  841:     */  public final void g(vg paramvg)
/*  842:     */  {
/*  843: 834 */    Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  844:     */    try
/*  845:     */    {
/*  846: 837 */      localObject = paramvg.a((String)localObject);
/*  847: 838 */      paramvg.a().a(((lE)localObject).getName()); return;
/*  848:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/*  849: 840 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/*  850:     */    }
/*  851:     */  }
/*  852:     */  
/*  853:     */  public final void h(vg paramvg)
/*  854:     */  {
/*  855:     */    try {
/*  856: 847 */      Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/*  857: 848 */      Integer localInteger = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1];
/*  858:     */      
/*  860: 851 */      localObject = (paramvg = paramvg.a((String)localObject)).getInventory(new q());
/*  861: 852 */      for (short s : ElementKeyMap.typeList())
/*  862:     */        try {
/*  863: 854 */          System.err.println("ADDING ITEM " + s + " " + localInteger);
/*  864: 855 */          int k = ((mf)localObject).b(s, localInteger.intValue());
/*  865: 856 */          paramvg.sendInventoryModification(k, null);
/*  866:     */        } catch (NoSlotFreeException localNoSlotFreeException) {
/*  867: 858 */          this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no slot free for " + ElementKeyMap.getInfo(s).getName());
/*  868:     */        }
/*  869:     */      return;
/*  870:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/*  871: 862 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/*  872:     */    }
/*  873:     */  }
/*  874:     */  
/*  875:     */  /* Error */
/*  876:     */  public final void i(vg paramvg)
/*  877:     */  {
/*  878:     */    // Byte code:
/*  879:     */    //   0: aload_0
/*  880:     */    //   1: getfield 211	vv:jdField_a_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
/*  881:     */    //   4: iconst_0
/*  882:     */    //   5: aaload
/*  883:     */    //   6: checkcast 120	java/lang/String
/*  884:     */    //   9: astore_2
/*  885:     */    //   10: aload_0
/*  886:     */    //   11: getfield 211	vv:jdField_a_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
/*  887:     */    //   14: iconst_1
/*  888:     */    //   15: aaload
/*  889:     */    //   16: checkcast 117	java/lang/Integer
/*  890:     */    //   19: astore_3
/*  891:     */    //   20: aload_0
/*  892:     */    //   21: getfield 211	vv:jdField_a_of_type_ArrayOfJavaLangObject	[Ljava/lang/Object;
/*  893:     */    //   24: iconst_2
/*  894:     */    //   25: aaload
/*  895:     */    //   26: checkcast 120	java/lang/String
/*  896:     */    //   29: astore 4
/*  897:     */    //   31: aload_1
/*  898:     */    //   32: aload_2
/*  899:     */    //   33: invokevirtual 370	vg:a	(Ljava/lang/String;)LlE;
/*  900:     */    //   36: dup
/*  901:     */    //   37: astore_1
/*  902:     */    //   38: new 181	q
/*  903:     */    //   41: dup
/*  904:     */    //   42: invokespecial 354	q:<init>	()V
/*  905:     */    //   45: invokevirtual 284	lE:getInventory	(Lq;)Lmf;
/*  906:     */    //   48: astore_2
/*  907:     */    //   49: invokestatic 318	org/schema/game/common/data/element/ElementKeyMap:typeList	()[S
/*  908:     */    //   52: dup
/*  909:     */    //   53: astore 5
/*  910:     */    //   55: arraylength
/*  911:     */    //   56: istore 6
/*  912:     */    //   58: iconst_0
/*  913:     */    //   59: istore 7
/*  914:     */    //   61: iload 7
/*  915:     */    //   63: iload 6
/*  916:     */    //   65: if_icmpge +242 -> 307
/*  917:     */    //   68: aload 5
/*  918:     */    //   70: iload 7
/*  919:     */    //   72: saload
/*  920:     */    //   73: istore 8
/*  921:     */    //   75: aload 4
/*  922:     */    //   77: iload 8
/*  923:     */    //   79: istore 10
/*  924:     */    //   81: astore 9
/*  925:     */    //   83: iload 10
/*  926:     */    //   85: invokestatic 316	org/schema/game/common/data/element/ElementKeyMap:getInfo	(S)Lorg/schema/game/common/data/element/ElementInformation;
/*  927:     */    //   88: invokevirtual 315	org/schema/game/common/data/element/ElementInformation:getType	()Ljava/lang/Class;
/*  928:     */    //   91: astore 10
/*  929:     */    //   93: aload 9
/*  930:     */    //   95: ldc 99
/*  931:     */    //   97: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*  932:     */    //   100: ifeq +13 -> 113
/*  933:     */    //   103: ldc 162
/*  934:     */    //   105: aload 10
/*  935:     */    //   107: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*  936:     */    //   110: goto +93 -> 203
/*  937:     */    //   113: aload 9
/*  938:     */    //   115: ldc 97
/*  939:     */    //   117: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*  940:     */    //   120: ifeq +13 -> 133
/*  941:     */    //   123: ldc 160
/*  942:     */    //   125: aload 10
/*  943:     */    //   127: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*  944:     */    //   130: goto +73 -> 203
/*  945:     */    //   133: aload 9
/*  946:     */    //   135: ldc 98
/*  947:     */    //   137: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*  948:     */    //   140: ifeq +13 -> 153
/*  949:     */    //   143: ldc 161
/*  950:     */    //   145: aload 10
/*  951:     */    //   147: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*  952:     */    //   150: goto +53 -> 203
/*  953:     */    //   153: aload 9
/*  954:     */    //   155: ldc 94
/*  955:     */    //   157: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*  956:     */    //   160: ifeq +13 -> 173
/*  957:     */    //   163: ldc 159
/*  958:     */    //   165: aload 10
/*  959:     */    //   167: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*  960:     */    //   170: goto +33 -> 203
/*  961:     */    //   173: aload 9
/*  962:     */    //   175: ldc 93
/*  963:     */    //   177: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
/*  964:     */    //   180: ifeq +13 -> 193
/*  965:     */    //   183: ldc 158
/*  966:     */    //   185: aload 10
/*  967:     */    //   187: invokevirtual 227	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
/*  968:     */    //   190: goto +13 -> 203
/*  969:     */    //   193: new 173	org/schema/game/server/data/admin/UnknownCategoryException
/*  970:     */    //   196: dup
/*  971:     */    //   197: aload 9
/*  972:     */    //   199: invokespecial 342	org/schema/game/server/data/admin/UnknownCategoryException:<init>	(Ljava/lang/String;)V
/*  973:     */    //   202: athrow
/*  974:     */    //   203: ifeq +64 -> 267
/*  975:     */    //   206: getstatic 189	java/lang/System:err	Ljava/io/PrintStream;
/*  976:     */    //   209: new 121	java/lang/StringBuilder
/*  977:     */    //   212: dup
/*  978:     */    //   213: ldc 30
/*  979:     */    //   215: invokespecial 244	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*  980:     */    //   218: iload 8
/*  981:     */    //   220: invokevirtual 245	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*  982:     */    //   223: ldc 3
/*  983:     */    //   225: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*  984:     */    //   228: aload_3
/*  985:     */    //   229: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*  986:     */    //   232: ldc 3
/*  987:     */    //   234: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*  988:     */    //   237: aload 4
/*  989:     */    //   239: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*  990:     */    //   242: invokevirtual 248	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*  991:     */    //   245: invokevirtual 223	java/io/PrintStream:println	(Ljava/lang/String;)V
/*  992:     */    //   248: aload_2
/*  993:     */    //   249: iload 8
/*  994:     */    //   251: aload_3
/*  995:     */    //   252: invokevirtual 231	java/lang/Integer:intValue	()I
/*  996:     */    //   255: invokevirtual 297	mf:b	(SI)I
/*  997:     */    //   258: istore 9
/*  998:     */    //   260: aload_1
/*  999:     */    //   261: iload 9
/* 1000:     */    //   263: aconst_null
/* 1001:     */    //   264: invokevirtual 287	lE:sendInventoryModification	(ILq;)V
/* 1002:     */    //   267: goto +34 -> 301
/* 1003:     */    //   270: pop
/* 1004:     */    //   271: aload_0
/* 1005:     */    //   272: getfield 210	vv:jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
/* 1006:     */    //   275: new 121	java/lang/StringBuilder
/* 1007:     */    //   278: dup
/* 1008:     */    //   279: ldc 59
/* 1009:     */    //   281: invokespecial 244	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/* 1010:     */    //   284: iload 8
/* 1011:     */    //   286: invokestatic 316	org/schema/game/common/data/element/ElementKeyMap:getInfo	(S)Lorg/schema/game/common/data/element/ElementInformation;
/* 1012:     */    //   289: invokevirtual 314	org/schema/game/common/data/element/ElementInformation:getName	()Ljava/lang/String;
/* 1013:     */    //   292: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1014:     */    //   295: invokevirtual 248	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* 1015:     */    //   298: invokevirtual 351	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
/* 1016:     */    //   301: iinc 7 1
/* 1017:     */    //   304: goto -243 -> 61
/* 1018:     */    //   307: return
/* 1019:     */    //   308: pop
/* 1020:     */    //   309: aload_0
/* 1021:     */    //   310: getfield 210	vv:jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
/* 1022:     */    //   313: ldc 67
/* 1023:     */    //   315: invokevirtual 351	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
/* 1024:     */    //   318: return
/* 1025:     */    //   319: astore_2
/* 1026:     */    //   320: aload_0
/* 1027:     */    //   321: getfield 210	vv:jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer	Lorg/schema/schine/network/RegisteredClientOnServer;
/* 1028:     */    //   324: new 121	java/lang/StringBuilder
/* 1029:     */    //   327: dup
/* 1030:     */    //   328: ldc 72
/* 1031:     */    //   330: invokespecial 244	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/* 1032:     */    //   333: aload_2
/* 1033:     */    //   334: invokevirtual 343	org/schema/game/server/data/admin/UnknownCategoryException:a	()Ljava/lang/String;
/* 1034:     */    //   337: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1035:     */    //   340: ldc 27
/* 1036:     */    //   342: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1037:     */    //   345: invokevirtual 248	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* 1038:     */    //   348: invokevirtual 351	org/schema/schine/network/RegisteredClientOnServer:serverMessage	(Ljava/lang/String;)V
/* 1039:     */    //   351: return
/* 1040:     */    // Line number table:
/* 1041:     */    //   Java source line #868	-> byte code offset #0
/* 1042:     */    //   Java source line #869	-> byte code offset #10
/* 1043:     */    //   Java source line #870	-> byte code offset #20
/* 1044:     */    //   Java source line #871	-> byte code offset #31
/* 1045:     */    //   Java source line #873	-> byte code offset #37
/* 1046:     */    //   Java source line #874	-> byte code offset #49
/* 1047:     */    //   Java source line #877	-> byte code offset #75
/* 1048:     */    //   Java source line #878	-> byte code offset #206
/* 1049:     */    //   Java source line #879	-> byte code offset #248
/* 1050:     */    //   Java source line #880	-> byte code offset #260
/* 1051:     */    //   Java source line #884	-> byte code offset #267
/* 1052:     */    //   Java source line #882	-> byte code offset #270
/* 1053:     */    //   Java source line #883	-> byte code offset #271
/* 1054:     */    //   Java source line #874	-> byte code offset #301
/* 1055:     */    //   Java source line #890	-> byte code offset #307
/* 1056:     */    //   Java source line #886	-> byte code offset #308
/* 1057:     */    //   Java source line #887	-> byte code offset #309
/* 1058:     */    //   Java source line #890	-> byte code offset #318
/* 1059:     */    //   Java source line #888	-> byte code offset #319
/* 1060:     */    //   Java source line #889	-> byte code offset #320
/* 1061:     */    //   Java source line #891	-> byte code offset #351
/* 1062:     */    // Local variable table:
/* 1063:     */    //   start	length	slot	name	signature
/* 1064:     */    //   0	352	0	this	vv
/* 1065:     */    //   0	352	1	paramvg	vg
/* 1066:     */    //   9	40	2	localObject	Object
/* 1067:     */    //   19	1	3	localInteger	Integer
/* 1068:     */    //   29	47	4	str1	String
/* 1069:     */    //   53	16	5	arrayOfShort	short[]
/* 1070:     */    //   56	10	6	i	int
/* 1071:     */    //   59	12	7	j	int
/* 1072:     */    //   73	5	8	s1	short
/* 1073:     */    //   81	117	9	str2	String
/* 1074:     */    //   79	5	10	s2	short
/* 1075:     */    //   91	95	10	localClass	Class
/* 1076:     */    // Exception table:
/* 1077:     */    //   from	to	target	type
/* 1078:     */    //   75	267	270	org/schema/game/common/data/player/inventory/NoSlotFreeException
/* 1079:     */    //   0	307	308	org/schema/game/server/data/PlayerNotFountException
/* 1080:     */    //   0	307	319	org/schema/game/server/data/admin/UnknownCategoryException
/* 1081:     */  }
/* 1082:     */  
/* 1083:     */  public final void j(vg paramvg)
/* 1084:     */  {
/* 1085: 894 */    String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1086:     */    try
/* 1087:     */    {
/* 1088: 897 */      paramvg.a().b(str);
/* 1089: 898 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully whitelisted: " + str); return;
/* 1090: 899 */    } catch (NoIPException localNoIPException) { 
/* 1091:     */      
/* 1093: 902 */        localNoIPException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not an IP: " + str);
/* 1094:     */    }
/* 1095:     */  }
/* 1096:     */  
/* 1102:     */  public final void k(vg paramvg)
/* 1103:     */  {
/* 1104: 913 */    String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1105:     */    try
/* 1106:     */    {
/* 1107: 916 */      paramvg.a().c(str);
/* 1108: 917 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully banned: " + str); return;
/* 1109: 918 */    } catch (NoIPException localNoIPException) { 
/* 1110:     */      
/* 1112: 921 */        localNoIPException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not an IP: " + str);
/* 1113:     */    }
/* 1114:     */  }
/* 1115:     */  
/* 1138:     */  public final void l(vg paramvg)
/* 1139:     */  {
/* 1140:     */    try
/* 1141:     */    {
/* 1142: 951 */      Object localObject1 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 1143:     */      
/* 1145: 954 */      Object localObject2 = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/* 1146:     */      mx localmx;
/* 1147: 956 */      if ((localmx = paramvg.a().getSector((q)localObject2)) != null) {
/* 1148: 957 */        for (localObject1 = ((lE)localObject1).a().a().iterator(); ((Iterator)localObject1).hasNext();)
/* 1149: 958 */          if (((localObject2 = (lA)((Iterator)localObject1).next()).a instanceof mF))
/* 1150: 959 */            paramvg.a().a((mF)((lA)localObject2).a, localmx.jdField_a_of_type_Q, 1); return;
/* 1151:     */      }
/* 1152:     */      
/* 1154: 963 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localObject2 + ": " + paramvg.a().getSectorSet()); return;
/* 1155:     */    }
/* 1156:     */    catch (PlayerNotFountException localPlayerNotFountException) {
/* 1157: 966 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); return;
/* 1158: 967 */    } catch (Exception localException) { 
/* 1159:     */      
/* 1161: 970 */        localException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/* 1162:     */    }
/* 1163:     */  }
/* 1164:     */  
/* 1165:     */  public final void m(vg paramvg) {
/* 1166:     */    try {
/* 1167: 976 */      lE locallE = paramvg.a(((String)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).trim());
/* 1168:     */      
/* 1170: 979 */      q localq = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).intValue());
/* 1171:     */      mx localmx;
/* 1172: 981 */      if ((localmx = paramvg.a().getSector(localq)) != null) {
/* 1173: 982 */        int i = 0;
/* 1174: 983 */        for (Iterator localIterator = locallE.a().a().iterator(); localIterator.hasNext();) { lA locallA;
/* 1175: 984 */          if (((locallA = (lA)localIterator.next()).a instanceof mF)) {
/* 1176: 985 */            paramvg.a().a((mF)locallA.a, localmx.jdField_a_of_type_Q, 1);
/* 1177: 986 */            i = 1;
/* 1178:     */          }
/* 1179:     */        }
/* 1180: 989 */        if (i != 0) {
/* 1181: 990 */          this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] changed sector for " + locallE.getName() + " to " + localq);return;
/* 1182:     */        }
/* 1183: 992 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not changed sector for " + locallE.getName() + " to " + localq + ": Player is not bound to any entity");
/* 1184:     */        
/* 1185: 994 */        return; }
/* 1186: 995 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localq + ": " + paramvg.a().getSectorSet()); return;
/* 1187:     */    }
/* 1188:     */    catch (PlayerNotFountException localPlayerNotFountException) {
/* 1189: 998 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client " + (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0]); return;
/* 1190: 999 */    } catch (Exception localException) { 
/* 1191:     */      
/* 1193:1002 */        localException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/* 1194:     */    }
/* 1195:     */  }
/* 1196:     */  
/* 1198:     */  public final void n(vg paramvg)
/* 1199:     */  {
/* 1200:     */    try
/* 1201:     */    {
/* 1202:1011 */      paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 1203:     */      
/* 1205:1014 */      q localq = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/* 1206:     */      mx localmx;
/* 1207:1016 */      if ((localmx = paramvg.a().getSector(localq)) != null) {
/* 1208:1017 */        localmx.h();
/* 1209:1018 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] sector repair queued: " + localq);return;
/* 1210:     */      }
/* 1211:1020 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localq + ": " + paramvg.a().getSectorSet()); return;
/* 1212:     */    }
/* 1213:     */    catch (PlayerNotFountException localPlayerNotFountException) {
/* 1214:1023 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); return;
/* 1215:1024 */    } catch (Exception localException) { 
/* 1216:     */      
/* 1218:1027 */        localException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/* 1219:     */    }
/* 1220:     */  }
/* 1221:     */  
/* 1222:     */  public final void o(vg paramvg) {
/* 1223:     */    try {
/* 1224:1033 */      paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 1225:     */      
/* 1227:1036 */      Object localObject = new q(((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue(), ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue());
/* 1228:1037 */      paramvg = paramvg.a().getSector((q)localObject);
/* 1229:     */      
/* 1232:1041 */      if ((!(localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).equals("+")) && (!((String)localObject).equals("-"))) {
/* 1233:1042 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] operator must be either + or -"); return;
/* 1234:     */      }
/* 1235:     */      
/* 1236:     */      String str;
/* 1237:1046 */      if ((!(str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[4]).equals("peace")) && (!str.equals("protected"))) {
/* 1238:1047 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode must be either 'peace' or 'protected' (without quotes)");
/* 1239:     */      }
/* 1240:1049 */      if (str.equals("protected")) {
/* 1241:1050 */        paramvg.b(((String)localObject).equals("+"));return; }
/* 1242:1051 */      if (str.equals("peace")) {
/* 1243:1052 */        paramvg.c(((String)localObject).equals("+"));
/* 1244:     */      }
/* 1245:     */      return;
/* 1246:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/* 1247:1056 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); return;
/* 1248:1057 */    } catch (Exception localException) { 
/* 1249:     */      
/* 1251:1060 */        localException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/* 1252:     */    }
/* 1253:     */  }
/* 1254:     */  
/* 1277:     */  public final void p(vg paramvg)
/* 1278:     */  {
/* 1279:1088 */    Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1280:     */    
/* 1282:     */    try
/* 1283:     */    {
/* 1284:1093 */      if ((paramvg = paramvg.a().a("%" + DatabaseIndex.a((String)localObject) + "%")).isEmpty()) {
/* 1285:1094 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] No matches found for '" + (String)localObject + "'");return;
/* 1286:     */      }
/* 1287:1096 */      for (paramvg = paramvg.iterator(); paramvg.hasNext();) { localObject = (kw)paramvg.next();
/* 1288:     */        lE locallE;
/* 1289:1098 */        (locallE = (lE)this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getPlayerObject()).a(new ServerMessage("FOUND: " + ((kw)localObject).jdField_a_of_type_JavaLangString + " -> " + ((kw)localObject).jdField_a_of_type_Q, 0, locallE.getId()));
/* 1290:     */      }
/* 1291:     */      return;
/* 1292:1101 */    } catch (SQLException localSQLException) { 
/* 1293:     */      
/* 1295:1104 */        localSQLException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION");
/* 1296:     */    } }
/* 1297:     */  
/* 1298:1107 */  public final void q(vg paramvg) { String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1299:1108 */    Object localObject = ((String)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).trim().toLowerCase(Locale.ENGLISH);
/* 1300:1109 */    boolean bool = ((Boolean)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).booleanValue();
/* 1301:     */    
/* 1302:1111 */    kB localkB = null;
/* 1303:     */    
/* 1304:1113 */    if (((String)localObject).equals("all")) {
/* 1305:1114 */      localkB = kB.c;
/* 1306:     */    }
/* 1307:1116 */    if (((String)localObject).equals("unused")) {
/* 1308:1117 */      localkB = kB.a;
/* 1309:     */    }
/* 1310:1119 */    if (((String)localObject).equals("used")) {
/* 1311:1120 */      localkB = kB.b;
/* 1312:     */    }
/* 1313:1122 */    if (localkB != null)
/* 1314:     */      try {
/* 1315:1124 */        for (localObject = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); ((Iterator)localObject).hasNext();) { Sendable localSendable;
/* 1316:1125 */          if ((((localSendable = (Sendable)((Iterator)localObject).next()) instanceof SegmentController)) && ((!bool) || ((localSendable instanceof kd))) && 
/* 1317:1126 */            (((SegmentController)localSendable).getUniqueIdentifier().split("_")[2].startsWith(str))) {
/* 1318:1127 */            localSendable.markedForPermanentDelete(true);
/* 1319:1128 */            localSendable.setMarkedForDeleteVolatile(true);
/* 1320:1129 */            this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] ACTIVE DESPAWNED " + localSendable + " ");
/* 1321:     */          }
/* 1322:     */        }
/* 1323:     */        
/* 1325:1134 */        int i = paramvg.a().a(DatabaseIndex.a(str) + "%", localkB, null, bool);
/* 1326:1135 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] DESPAWNED " + i + " ENTITIES (MODE: " + localkB.name() + ")"); return;
/* 1327:1136 */      } catch (SQLException localSQLException) { 
/* 1328:     */        
/* 1330:1139 */          localSQLException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION");return;
/* 1331:     */      }
/* 1332:1141 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode '" + localkB + "' unknown. mist be 'used', 'unused', or 'all'");
/* 1333:     */  }
/* 1334:     */  
/* 1335:     */  public final void r(vg paramvg) {
/* 1336:1145 */    String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1337:1146 */    Object localObject = ((String)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).trim().toLowerCase(Locale.ENGLISH);
/* 1338:1147 */    boolean bool = ((Boolean)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).booleanValue();
/* 1339:     */    
/* 1340:1149 */    int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).intValue();
/* 1341:1150 */    int k = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[4]).intValue();
/* 1342:1151 */    int m = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[5]).intValue();
/* 1343:     */    
/* 1344:1153 */    kB localkB = null;
/* 1345:     */    
/* 1346:1155 */    if (((String)localObject).equals("all")) {
/* 1347:1156 */      localkB = kB.c;
/* 1348:     */    }
/* 1349:1158 */    if (((String)localObject).equals("unused")) {
/* 1350:1159 */      localkB = kB.a;
/* 1351:     */    }
/* 1352:1161 */    if (((String)localObject).equals("used")) {
/* 1353:1162 */      localkB = kB.b;
/* 1354:     */    }
/* 1355:1164 */    localObject = new q(i, k, m);
/* 1356:1165 */    if (localkB != null)
/* 1357:     */      try {
/* 1358:1167 */        for (Iterator localIterator = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/* 1359:1168 */          if ((((localSendable = (Sendable)localIterator.next()) instanceof SegmentController)) && ((!bool) || ((localSendable instanceof kd)))) {
/* 1360:1169 */            if (((localkB != kB.a) || (((SegmentController)localSendable).getLastModifier().length() != 0)) && (
/* 1361:1170 */              (localkB != kB.b) || (((SegmentController)localSendable).getLastModifier().length() <= 0)))
/* 1362:     */            {
/* 1364:1173 */              if ((((SegmentController)localSendable).getUniqueIdentifier().split("_")[2].startsWith(str)) && (((SegmentController)localSendable).getSectorId() == paramvg.a().getSector((q)localObject).a()))
/* 1365:     */              {
/* 1368:1177 */                localSendable.markedForPermanentDelete(true);
/* 1369:1178 */                localSendable.setMarkedForDeleteVolatile(true);
/* 1370:1179 */                this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] ACTIVE DESPAWNED " + localSendable);
/* 1371:     */              } }
/* 1372:     */          }
/* 1373:     */        }
/* 1374:1183 */        int j = paramvg.a().a(DatabaseIndex.a(str) + "%", localkB, (q)localObject, bool);
/* 1375:1184 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [SUCCESS] DESPAWNED " + j + " ENTITIES IN SECTOR: " + localObject + " (MODE: " + localkB.name() + ")"); return;
/* 1376:1185 */      } catch (SQLException localSQLException) { 
/* 1377:     */        
/* 1382:1191 */          localSQLException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] SQL EXCEPTION"); return;
/* 1383:     */      }
/* 1384:     */      catch (Exception localException)
/* 1385:     */      {
/* 1386:1188 */        
/* 1387:     */        
/* 1389:1191 */          localException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] COULD NOT LOAD SECTOR: " + localObject);return;
/* 1390:     */      }
/* 1391:1193 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] mode '" + localkB + "' unknown. mist be 'used', 'unused', or 'all'");
/* 1392:     */  }
/* 1393:     */  
/* 1434:     */  public static void s(vg paramvg)
/* 1435:     */  {
/* 1436:1238 */    synchronized (paramvg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 1437:1239 */      for (paramvg = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); paramvg.hasNext();) { Sendable localSendable;
/* 1438:1240 */        if (((localSendable = (Sendable)paramvg.next()) instanceof SegmentController)) {
/* 1439:1241 */          ((SegmentController)localSendable).getSegmentBuffer().a();
/* 1440:     */        }
/* 1441:     */      }
/* 1442:1244 */      return;
/* 1443:     */    }
/* 1444:     */  }
/* 1445:     */  
/* 1446:     */  public final void t(vg paramvg)
/* 1447:     */  {
/* 1448:1250 */    Object localObject1 = new StringBuilder();
/* 1449:1251 */    for (int i = 0; i < this.jdField_a_of_type_ArrayOfJavaLangObject.length - 1; i++) {
/* 1450:1252 */      ((StringBuilder)localObject1).append((String)this.jdField_a_of_type_ArrayOfJavaLangObject[i]);
/* 1451:1253 */      if (i < this.jdField_a_of_type_ArrayOfJavaLangObject.length - 2) {
/* 1452:1254 */        ((StringBuilder)localObject1).append(" ");
/* 1453:     */      }
/* 1454:     */    }
/* 1455:1257 */    String str = ((StringBuilder)localObject1).toString();
/* 1456:     */    
/* 1457:1259 */    localObject1 = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(this.jdField_a_of_type_ArrayOfJavaLangObject.length - 1)];
/* 1458:     */    
/* 1459:     */    try
/* 1460:     */    {
/* 1461:1263 */      Object localObject2 = new ArrayList();
/* 1462:1264 */      int j = (localObject3 = ElementKeyMap.typeList()).length; Object localObject4; for (int k = 0; k < j; k++) {
/* 1463:1265 */        if (ElementKeyMap.getInfo((localObject4 = Short.valueOf(localObject3[k])).shortValue()).getName().toLowerCase(Locale.ENGLISH).startsWith(str.trim().toLowerCase(Locale.ENGLISH))) {
/* 1464:1266 */          ((ArrayList)localObject2).add(localObject4);
/* 1465:     */        }
/* 1466:     */      }
/* 1467:     */      
/* 1469:1271 */      if (((ArrayList)localObject2).size() == 1)
/* 1470:     */      {
/* 1471:1273 */        localObject3 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 1472:     */        
/* 1473:1275 */        j = ((Short)((ArrayList)localObject2).get(0)).shortValue();
/* 1474:     */        
/* 1477:1279 */        mx localmx = paramvg.a().getSector(((lE)localObject3).c());
/* 1478:1280 */        localObject4 = ((lE)localObject3).b().getWorldTransform();
/* 1479:1281 */        paramvg = new Vector3f(((Transform)localObject4).origin);
/* 1480:1282 */        (
/* 1481:1283 */          localObject2 = new Vector3f(((lE)localObject3).a())).scale(2.0F);
/* 1482:1284 */        paramvg.add((Tuple3f)localObject2);
/* 1483:     */        
/* 1488:1290 */        localmx.a().a(paramvg, j, ((Integer)localObject1).intValue());
/* 1489:     */        
/* 1490:1292 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] sucessfully spawned item at " + paramvg);
/* 1491:1293 */        return; } if (((ArrayList)localObject2).isEmpty()) {
/* 1492:1294 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no element starts with the string: \"" + str + "\"");return;
/* 1493:     */      }
/* 1494:1296 */      Object localObject3 = ((ArrayList)localObject2).iterator();
/* 1495:     */      
/* 1496:1298 */      while (((Iterator)localObject3).hasNext()) {
/* 1497:1299 */        short s = ((Short)((Iterator)localObject3).next()).shortValue();
/* 1498:1300 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] ambigous string: \"" + str + "\": (" + ElementKeyMap.getInfo(s).getName() + " [" + s + "])" + (((Iterator)localObject3).hasNext() ? ", " : ""));
/* 1499:     */      }
/* 1500:1302 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] use either the classified name or the one in the parenthesis"); return;
/* 1501:     */    }
/* 1502:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 1503:     */    {
/* 1504:1306 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); return;
/* 1505:     */    } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
/* 1506:1308 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Too many arguments"); return;
/* 1507:     */    } catch (ElementClassNotFoundException localElementClassNotFoundException) {
/* 1508:1310 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + str); return;
/* 1509:     */    } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1510:1312 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
/* 1511:     */    }
/* 1512:     */  }
/* 1513:     */  
/* 1514:     */  public final void u(vg paramvg)
/* 1515:     */  {
/* 1516:1318 */    String str1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1517:     */    
/* 1518:1320 */    Object localObject1 = new StringBuilder();
/* 1519:1321 */    for (int i = 1; i < this.jdField_a_of_type_ArrayOfJavaLangObject.length - 1; i++) {
/* 1520:1322 */      ((StringBuilder)localObject1).append((String)this.jdField_a_of_type_ArrayOfJavaLangObject[i]);
/* 1521:1323 */      if (i < this.jdField_a_of_type_ArrayOfJavaLangObject.length - 2) {
/* 1522:1324 */        ((StringBuilder)localObject1).append(" ");
/* 1523:     */      }
/* 1524:     */    }
/* 1525:1327 */    String str2 = ((StringBuilder)localObject1).toString();
/* 1526:     */    
/* 1527:1329 */    localObject1 = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(this.jdField_a_of_type_ArrayOfJavaLangObject.length - 1)];
/* 1528:     */    
/* 1529:     */    try
/* 1530:     */    {
/* 1531:1333 */      ArrayList localArrayList = new ArrayList();
/* 1532:1334 */      int j = (localObject2 = ElementKeyMap.typeList()).length; for (int k = 0; k < j; k++) { Short localShort;
/* 1533:1335 */        if (ElementKeyMap.getInfo((localShort = Short.valueOf(localObject2[k])).shortValue()).getName().toLowerCase(Locale.ENGLISH).startsWith(str2.trim().toLowerCase(Locale.ENGLISH))) {
/* 1534:1336 */          localArrayList.add(localShort);
/* 1535:     */        }
/* 1536:     */      }
/* 1537:     */      
/* 1539:1341 */      if (localArrayList.size() == 1)
/* 1540:     */      {
/* 1543:1345 */        j = (localObject2 = paramvg.a(str1)).getInventory(null).b(((Short)localArrayList.get(0)).shortValue(), ((Integer)localObject1).intValue());
/* 1544:1346 */        ((lE)localObject2).sendInventoryModification(j, null);
/* 1545:1347 */        return; } if (localArrayList.isEmpty()) {
/* 1546:1348 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no element starts with the string: \"" + str2 + "\"");return;
/* 1547:     */      }
/* 1548:1350 */      Object localObject2 = localArrayList.iterator();
/* 1549:     */      
/* 1550:1352 */      while (((Iterator)localObject2).hasNext()) {
/* 1551:1353 */        j = ((Short)((Iterator)localObject2).next()).shortValue();
/* 1552:1354 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] ambigous string: \"" + str2 + "\": (" + ElementKeyMap.getInfo(j).getName() + " [" + j + "])" + (((Iterator)localObject2).hasNext() ? ", " : ""));
/* 1553:     */      }
/* 1554:1356 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] use either the classified name or the one in the parenthesis"); return;
/* 1555:     */    }
/* 1556:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 1557:     */    {
/* 1558:1360 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); return;
/* 1559:     */    } catch (NoSlotFreeException localNoSlotFreeException) {
/* 1560:1362 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localNoSlotFreeException.getMessage()); return;
/* 1561:     */    } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
/* 1562:1364 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Too many arguments"); return;
/* 1563:     */    } catch (ElementClassNotFoundException localElementClassNotFoundException) {
/* 1564:1366 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + str2);
/* 1565:     */    }
/* 1566:     */  }
/* 1567:     */  
/* 1568:     */  public final void v(vg paramvg)
/* 1569:     */  {
/* 1570:     */    try
/* 1571:     */    {
/* 1572:1374 */      String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1573:1375 */      Integer localInteger = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1];
/* 1574:1376 */      paramvg.a(str)
/* 1575:1377 */        .a(localInteger.intValue()); return;
/* 1576:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/* 1577:1379 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/* 1578:     */    }
/* 1579:     */  }
/* 1580:     */  
/* 1581:     */  public final void w(vg paramvg)
/* 1582:     */  {
/* 1583:1385 */    String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1584:1386 */    short s = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).shortValue();
/* 1585:1387 */    Integer localInteger = (Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2];
/* 1586:     */    try
/* 1587:     */    {
/* 1588:1390 */      if (ElementKeyMap.isValidType(s))
/* 1589:     */      {
/* 1592:1394 */        int i = (paramvg = paramvg.a(str)).getInventory(null).b(s, localInteger.intValue());
/* 1593:1395 */        paramvg.sendInventoryModification(i, null);
/* 1594:1396 */        return; }
/* 1595:1397 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] invalid type " + s); return;
/* 1596:     */    }
/* 1597:     */    catch (PlayerNotFountException paramvg) {
/* 1598:1400 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 1599:     */    } catch (NoSlotFreeException paramvg) {
/* 1600:1402 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 1601:     */    } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
/* 1602:1404 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + s); return;
/* 1603:     */    } catch (ElementClassNotFoundException localElementClassNotFoundException) {
/* 1604:1406 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Unknown Element" + s);
/* 1605:     */    }
/* 1606:     */  }
/* 1607:     */  
/* 1608:     */  public final void x(vg paramvg)
/* 1609:     */  {
/* 1610:1412 */    int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue();
/* 1611:1413 */    int j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/* 1612:     */    
/* 1613:     */    try
/* 1614:     */    {
/* 1615:     */      lE locallE;
/* 1616:1418 */      (locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b();
/* 1617:     */      
/* 1618:1420 */      paramvg.a().a(5, i, j, tH.a, locallE.c()); return;
/* 1619:     */    }
/* 1620:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 1621:     */    {
/* 1622:1424 */      (paramvg = 
/* 1623:     */      
/* 1636:1438 */        localPlayerNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 1637:     */    } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1638:1427 */      (paramvg = 
/* 1639:     */      
/* 1649:1438 */        localPlayerControlledTransformableNotFound).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 1650:     */    } catch (EntityNotFountException localEntityNotFountException) {
/* 1651:1430 */      (paramvg = 
/* 1652:     */      
/* 1659:1438 */        localEntityNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 1660:     */    } catch (ErrorDialogException localErrorDialogException) {
/* 1661:1433 */      (paramvg = 
/* 1662:     */      
/* 1666:1438 */        localErrorDialogException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 1667:     */    } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) {
/* 1668:1436 */      (paramvg = 
/* 1669:     */      
/* 1670:1438 */        localEntityAlreadyExistsException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/* 1671:     */    }
/* 1672:     */  }
/* 1673:     */  
/* 1674:     */  public final void y(vg paramvg)
/* 1675:     */  {
/* 1676:     */    try {
/* 1677:     */      lE locallE;
/* 1678:1445 */      mF localmF = (locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b();
/* 1679:1446 */      Vector3f localVector3f1 = new Vector3f(localmF.getWorldTransform().origin);
/* 1680:1447 */      Vector3f localVector3f2 = new Vector3f(localVector3f1);
/* 1681:     */      Vector3f localVector3f3;
/* 1682:1449 */      (localVector3f3 = new Vector3f(locallE.a())).scale(5000.0F);
/* 1683:1450 */      localVector3f2.add(localVector3f3);
/* 1684:     */      
/* 1685:1452 */      paramvg = null;
/* 1686:     */      
/* 1687:1454 */      if ((paramvg = ((PhysicsExt)paramvg.a().getSector(localmF.getSectorId()).a()).testRayCollisionPoint(localVector3f1, localVector3f2, false, null, null, false, null, false)).hasHit())
/* 1688:     */      {
/* 1689:1456 */        (paramvg = new Vector3f(paramvg.hitPointWorld)).sub(locallE.a());
/* 1690:1457 */        a(localmF, paramvg.x, paramvg.y, paramvg.z);
/* 1691:1458 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] Object successfully jumped to " + paramvg);
/* 1692:1459 */        return; }
/* 1693:1460 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no object in line of sight"); return;
/* 1694:     */    }
/* 1695:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 1696:     */    {
/* 1697:1464 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); return;
/* 1698:     */    } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1699:1466 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
/* 1700:     */    }
/* 1701:     */  }
/* 1702:     */  
/* 1703:     */  public final void z(vg paramvg) {
/* 1704:1471 */    Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1705:     */    try
/* 1706:     */    {
/* 1707:1474 */      localObject = paramvg.a((String)localObject);
/* 1708:1475 */      paramvg.a().sendLogout(((lE)localObject).a(), "You have been kicked by an admin"); return;
/* 1709:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/* 1710:1477 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/* 1711:     */    }
/* 1712:     */  }
/* 1713:     */  
/* 1714:     */  public final void A(vg paramvg) {
/* 1715:1482 */    Object localObject1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1716:     */    try
/* 1717:     */    {
/* 1718:1485 */      localObject1 = paramvg.a((String)localObject1);
/* 1719:1486 */      synchronized (paramvg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 1720:1487 */        for (paramvg = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); paramvg.hasNext();) { Object localObject2;
/* 1721:1488 */          if (((localObject2 = (Sendable)paramvg.next()) instanceof lD)) {
/* 1722:1489 */            localObject2 = (lD)localObject2;
/* 1723:1490 */            if (((lE)localObject1).a() == ((lD)localObject2).a()) {
/* 1724:1491 */              System.err.println("Killing character " + localObject2);
/* 1725:1492 */              ((lD)localObject2).setMarkedForDeleteVolatile(true);
/* 1726:     */            }
/* 1727:     */          }
/* 1728:     */        }
/* 1729:1496 */        return;
/* 1730:     */      }
/* 1731:1498 */    } catch (PlayerNotFountException localPlayerNotFountException) { this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/* 1732:     */    }
/* 1733:     */  }
/* 1734:     */  
/* 1735:     */  public final void B(vg paramvg)
/* 1736:     */  {
/* 1737:1504 */    String str = null;
/* 1738:1505 */    synchronized (paramvg.a()) {
/* 1739:1506 */      str = paramvg.a().toString();
/* 1740:     */    }
/* 1741:1508 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Admins: " + str);
/* 1742:     */  }
/* 1743:     */  
/* 1744:     */  public final void C(vg paramvg)
/* 1745:     */  {
/* 1746:1513 */    String str = null;
/* 1747:1514 */    synchronized (paramvg.b()) {
/* 1748:1515 */      str = paramvg.b().toString();
/* 1749:     */    }
/* 1750:1517 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Banned: " + str);
/* 1751:     */  }
/* 1752:     */  
/* 1753:     */  public final void D(vg paramvg)
/* 1754:     */  {
/* 1755:1522 */    String str = null;
/* 1756:1523 */    synchronized (paramvg.c()) {
/* 1757:1524 */      str = paramvg.c().toString();
/* 1758:     */    }
/* 1759:1526 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Banned: " + str);
/* 1760:     */  }
/* 1761:     */  
/* 1762:     */  public final void E(vg paramvg) {
/* 1763:1530 */    String str = null;
/* 1764:1531 */    synchronized (paramvg.e()) {
/* 1765:1532 */      str = paramvg.e().toString();
/* 1766:     */    }
/* 1767:1534 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Whitelisted: " + str);
/* 1768:     */  }
/* 1769:     */  
/* 1770:     */  public final void F(vg paramvg)
/* 1771:     */  {
/* 1772:1539 */    String str = null;
/* 1773:1540 */    synchronized (paramvg.f()) {
/* 1774:1541 */      str = paramvg.f().toString();
/* 1775:     */    }
/* 1776:1543 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("Whitelisted: " + str);
/* 1777:     */  }
/* 1778:     */  
/* 1786:     */  public final void G(vg paramvg)
/* 1787:     */  {
/* 1788:1555 */    String str1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1789:     */    
/* 1790:     */    String str2;
/* 1791:1558 */    if (!ve.a(str2 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[1])) {
/* 1792:1559 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Invalid Ship name (Only Characters And numbers and -_ allowed)"); return;
/* 1793:     */    }
/* 1794:     */    
/* 1795:     */    Transform localTransform;
/* 1796:     */    
/* 1797:1564 */    (localTransform = new Transform()).setIdentity();
/* 1798:     */    try
/* 1799:     */    {
/* 1800:1567 */      Object localObject2 = (localObject1 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b();
/* 1801:     */      
/* 1802:1569 */      localTransform.set(((mF)localObject2).getWorldTransform());
/* 1803:1570 */      localObject2 = ByteBuffer.allocate(10240);
/* 1804:1571 */      paramvg = tH.a.a(paramvg, str1, str2, localTransform, -1, 0, ((lE)localObject1).c(), "<admin>", (ByteBuffer)localObject2);
/* 1805:     */      
/* 1806:1573 */      System.err.println("[ADMIN] LOADING " + paramvg.getClass());
/* 1807:1574 */      paramvg.a(((lE)localObject1).c(), false); return;
/* 1808:     */    } catch (EntityNotFountException localEntityNotFountException) {
/* 1809:1576 */      (localObject1 = 
/* 1810:     */      
/* 1826:1593 */        localEntityNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject1).getMessage()); return;
/* 1827:     */    } catch (ErrorDialogException localErrorDialogException) {
/* 1828:1579 */      (localObject1 = 
/* 1829:     */      
/* 1842:1593 */        localErrorDialogException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject1).getMessage()); return;
/* 1843:     */    } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) {
/* 1844:1582 */      (localObject1 = 
/* 1845:     */      
/* 1855:1593 */        localEntityAlreadyExistsException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Entity already exists: " + ((EntityAlreadyExistsException)localObject1).getMessage()); return;
/* 1856:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/* 1857:1585 */      (localObject1 = 
/* 1858:     */      
/* 1865:1593 */        localPlayerNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject1).getMessage()); return;
/* 1866:     */    } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1867:1588 */      (localObject1 = 
/* 1868:     */      
/* 1872:1593 */        localPlayerControlledTransformableNotFound).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject1).getMessage()); return;
/* 1873:     */    } catch (StateParameterNotFoundException localStateParameterNotFoundException) { Object localObject1;
/* 1874:1591 */      (localObject1 = 
/* 1875:     */      
/* 1876:1593 */        localStateParameterNotFoundException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((StateParameterNotFoundException)localObject1).getMessage());
/* 1877:     */    }
/* 1878:     */  }
/* 1879:     */  
/* 1884:     */  public final void H(vg paramvg)
/* 1885:     */  {
/* 1886:1602 */    Object localObject = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1887:     */    try
/* 1888:     */    {
/* 1889:1605 */      localObject = paramvg.a((String)localObject);
/* 1890:1606 */      paramvg.a().e(((lE)localObject).getName()); return;
/* 1891:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/* 1892:1608 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage());
/* 1893:     */    }
/* 1894:     */  }
/* 1895:     */  
/* 1898:     */  public final void I(vg paramvg)
/* 1899:     */  {
/* 1900:     */    String str;
/* 1901:     */    
/* 1903:1619 */    if (!ve.a(str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0])) {
/* 1904:1620 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] Invalid Ship name (Only Characters And numbers and -_ allowed)");
/* 1905:1621 */      return;
/* 1906:     */    }
/* 1907:     */    
/* 1908:     */    try
/* 1909:     */    {
/* 1910:     */      lE locallE;
/* 1911:     */      mF localmF;
/* 1912:     */      Object localObject;
/* 1913:1629 */      if (((localmF = (locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b()) == null) || (!(localmF instanceof SegmentController))) {
/* 1914:1630 */        System.err.println("[ADMIN COMMAND]checking selected");
/* 1915:     */        
/* 1916:1632 */        if (((localObject = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)locallE.a().selectedEntityId.get())) != null) && ((localObject instanceof mF))) {
/* 1917:1633 */          localmF = (mF)localObject;
/* 1918:     */        }
/* 1919:     */      }
/* 1920:     */      
/* 1921:1637 */      if ((localmF instanceof SegmentController))
/* 1922:     */      {
/* 1923:1639 */        (localObject = (SegmentController)localmF).writeAllBufferedSegmentsToDatabase();
/* 1924:1640 */        tH.a.a((SegmentController)localObject, str, false);
/* 1925:     */        
/* 1926:1642 */        paramvg.a().a((SegmentController)localObject, str, locallE);
/* 1927:1643 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] successfully saved ship in catalog as \"" + str + "\"\n");
/* 1928:1644 */        return; }
/* 1929:1645 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] not inside any ship"); return;
/* 1930:     */    }
/* 1931:     */    catch (PlayerNotFountException localPlayerNotFountException) {
/* 1932:1648 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerNotFountException.getMessage()); return;
/* 1933:     */    } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1934:1650 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + localPlayerControlledTransformableNotFound.getMessage());
/* 1935:     */    }
/* 1936:     */  }
/* 1937:     */  
/* 1938:     */  public final void J(vg paramvg)
/* 1939:     */  {
/* 1940:     */    try
/* 1941:     */    {
/* 1942:1658 */      lE locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 1943:1659 */      paramvg.a().b(locallE); return;
/* 1944:     */    } catch (PlayerNotFountException paramvg) {
/* 1945:1661 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client");
/* 1946:1662 */      paramvg.printStackTrace();
/* 1947:     */    }
/* 1948:     */  }
/* 1949:     */  
/* 1950:1666 */  public final void K(vg paramvg) { String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 1951:1667 */    int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/* 1952:1668 */    int j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue();
/* 1953:1669 */    System.err.println("Spawning " + j + " mobs of type: " + str);
/* 1954:     */    Transform localTransform;
/* 1955:1671 */    (localTransform = new Transform()).setIdentity();
/* 1956:     */    
/* 1958:     */    try
/* 1959:     */    {
/* 1960:     */      lE locallE;
/* 1961:     */      
/* 1962:1678 */      localObject = (locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b();
/* 1963:     */      
/* 1964:1680 */      localTransform.set(((mF)localObject).getWorldTransform());
/* 1965:     */      
/* 1966:1682 */      paramvg.a(j, str, locallE.c(), localTransform, i, tH.a); return;
/* 1967:     */    }
/* 1968:     */    catch (PlayerNotFountException localPlayerNotFountException) {
/* 1969:1685 */      (localObject = 
/* 1970:     */      
/* 1983:1699 */        localPlayerNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject).getMessage()); return;
/* 1984:     */    } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 1985:1688 */      (localObject = 
/* 1986:     */      
/* 1996:1699 */        localPlayerControlledTransformableNotFound).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject).getMessage()); return;
/* 1997:     */    } catch (EntityNotFountException localEntityNotFountException) {
/* 1998:1691 */      (localObject = 
/* 1999:     */      
/* 2006:1699 */        localEntityNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject).getMessage()); return;
/* 2007:     */    } catch (ErrorDialogException localErrorDialogException) {
/* 2008:1694 */      (localObject = 
/* 2009:     */      
/* 2013:1699 */        localErrorDialogException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject).getMessage()); return;
/* 2014:     */    } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) { Object localObject;
/* 2015:1697 */      (localObject = 
/* 2016:     */      
/* 2017:1699 */        localEntityAlreadyExistsException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityAlreadyExistsException)localObject).getMessage());
/* 2018:     */    }
/* 2019:     */  }
/* 2020:     */  
/* 2021:1702 */  public final void L(vg paramvg) { String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 2022:1703 */    int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).intValue();
/* 2023:1704 */    int j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).intValue();
/* 2024:     */    
/* 2025:     */    try
/* 2026:     */    {
/* 2027:     */      lE locallE;
/* 2028:1709 */      localObject1 = (locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b();
/* 2029:1710 */      Vector3f localVector3f1 = new Vector3f(((mF)localObject1).getWorldTransform().origin);
/* 2030:1711 */      Vector3f localVector3f2 = new Vector3f(localVector3f1);
/* 2031:     */      Object localObject2;
/* 2032:1713 */      (localObject2 = new Vector3f(locallE.a())).scale(5000.0F);
/* 2033:1714 */      localVector3f2.add((Tuple3f)localObject2);
/* 2034:     */      
/* 2035:1716 */      (
/* 2036:1717 */        localObject2 = new Transform()).setIdentity();
/* 2037:     */      
/* 2040:1721 */      if ((localObject1 = ((PhysicsExt)paramvg.a().getSector(((mF)localObject1).getSectorId()).a()).testRayCollisionPoint(localVector3f1, localVector3f2, false, null, null, false, null, false)).hasHit())
/* 2041:     */      {
/* 2042:1723 */        ((Transform)localObject2).origin.set(((CollisionWorld.ClosestRayResultCallback)localObject1).hitPointWorld);
/* 2043:     */        
/* 2044:1725 */        System.err.println("Spawning " + j + " mobs of type: " + str + " at " + ((CollisionWorld.ClosestRayResultCallback)localObject1).hitPointWorld);
/* 2045:     */        
/* 2048:1729 */        paramvg.a(j, str, locallE.c(), (Transform)localObject2, i, tH.a);return;
/* 2049:     */      }
/* 2050:1731 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] no object in line of sight"); return;
/* 2051:     */    }
/* 2052:     */    catch (PlayerNotFountException localPlayerNotFountException) {
/* 2053:1734 */      (localObject1 = 
/* 2054:     */      
/* 2067:1748 */        localPlayerNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject1).getMessage()); return;
/* 2068:     */    } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 2069:1737 */      (localObject1 = 
/* 2070:     */      
/* 2080:1748 */        localPlayerControlledTransformableNotFound).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject1).getMessage()); return;
/* 2081:     */    } catch (EntityNotFountException localEntityNotFountException) {
/* 2082:1740 */      (localObject1 = 
/* 2083:     */      
/* 2090:1748 */        localEntityNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityNotFountException)localObject1).getMessage()); return;
/* 2091:     */    } catch (ErrorDialogException localErrorDialogException) {
/* 2092:1743 */      (localObject1 = 
/* 2093:     */      
/* 2097:1748 */        localErrorDialogException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((ErrorDialogException)localObject1).getMessage()); return;
/* 2098:     */    } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) { Object localObject1;
/* 2099:1746 */      (localObject1 = 
/* 2100:     */      
/* 2101:1748 */        localEntityAlreadyExistsException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((EntityAlreadyExistsException)localObject1).getMessage());
/* 2102:     */    }
/* 2103:     */  }
/* 2104:     */  
/* 2105:     */  public final void M(vg paramvg) {
/* 2106:     */    try {
/* 2107:1753 */      int i = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).intValue();
/* 2108:1754 */      lE locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 2109:     */      
/* 2114:1760 */      if (((paramvg = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)locallE.a().selectedEntityId.get())) != null) && ((paramvg instanceof kd))) {
/* 2115:1761 */        paramvg = (mF)paramvg;
/* 2116:     */      } else {
/* 2117:1763 */        paramvg = locallE.b();
/* 2118:     */      }
/* 2119:     */      
/* 2120:1766 */      if ((paramvg instanceof kd)) {
/* 2121:1767 */        ((kd)paramvg).setFactionId(i);
/* 2122:1768 */        ((kd)paramvg).a().a(kq.b).a("Ship", true);
/* 2123:1769 */        ((kd)paramvg).a().a(kq.c).a(Boolean.valueOf(true), true);
/* 2124:1770 */        ((kd)paramvg).a().a();
/* 2125:1771 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] activated " + paramvg + " with faction " + i);return;
/* 2126:     */      }
/* 2127:1773 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
/* 2128:1774 */      return;
/* 2129:     */    }
/* 2130:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 2131:     */    {
/* 2132:1778 */      
/* 2133:     */      
/* 2137:1783 */        (localObject = localPlayerNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerNotFountException)localObject).getMessage()); return;
/* 2138:     */    }
/* 2139:     */    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
/* 2140:     */    {
/* 2141:     */      Object localObject;
/* 2142:1781 */      (localObject = 
/* 2143:     */      
/* 2144:1783 */        localPlayerControlledTransformableNotFound).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + ((PlayerControlledTransformableNotFound)localObject).getMessage());
/* 2145:     */    }
/* 2146:     */  }
/* 2147:     */  
/* 2148:     */  public final void N(vg paramvg)
/* 2149:     */  {
/* 2150:     */    try
/* 2151:     */    {
/* 2152:1790 */      lE locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 2153:     */      
/* 2158:1796 */      if (((paramvg = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)locallE.a().selectedEntityId.get())) != null) && ((paramvg instanceof kd))) {
/* 2159:1797 */        paramvg = (mF)paramvg;
/* 2160:     */      } else {
/* 2161:1799 */        paramvg = locallE.b();
/* 2162:     */      }
/* 2163:1801 */      if ((paramvg instanceof kd)) {
/* 2164:1802 */        ((kd)paramvg).a().a(kq.c).a(Boolean.valueOf(false), true);
/* 2165:1803 */        ((kd)paramvg).a().a();return;
/* 2166:     */      }
/* 2167:1805 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] you are not inside or selected a ship");
/* 2168:1806 */      return;
/* 2169:     */    }
/* 2170:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 2171:     */    {
/* 2172:1810 */      
/* 2173:     */      
/* 2177:1815 */        (paramvg = localPlayerNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 2178:     */    }
/* 2179:     */    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
/* 2180:     */    {
/* 2181:1813 */      (paramvg = 
/* 2182:     */      
/* 2183:1815 */        localPlayerControlledTransformableNotFound).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/* 2184:     */    }
/* 2185:     */  }
/* 2186:     */  
/* 2188:     */  public final void O(vg paramvg)
/* 2189:     */  {
/* 2190:     */    try
/* 2191:     */    {
/* 2192:1823 */      lE locallE = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 2193:     */      
/* 2197:1828 */      if (((paramvg = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(((Integer)locallE.a().selectedEntityId.get()).intValue())) != null) && ((paramvg instanceof kf)))
/* 2198:     */      {
/* 2199:1830 */        ((kf)paramvg).a(true);
/* 2200:1831 */        return; }
/* 2201:1832 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] No Shop Selected: " + locallE.a().selectedEntityId.get() + "->(" + paramvg + ")"); return;
/* 2203:     */    }
/* 2204:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 2205:     */    {
/* 2207:1838 */      (paramvg = 
/* 2208:     */      
/* 2212:1843 */        localPlayerNotFountException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 2213:     */    } catch (NoSlotFreeException localNoSlotFreeException) {
/* 2214:1841 */      (paramvg = 
/* 2215:     */      
/* 2216:1843 */        localNoSlotFreeException).printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] No more slots free " + paramvg.getMessage());
/* 2217:     */    }
/* 2218:     */  }
/* 2219:     */  
/* 2240:     */  public final void P(vg paramvg)
/* 2241:     */  {
/* 2242:     */    try
/* 2243:     */    {
/* 2244:1870 */      paramvg = null;a(paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()).b(), 0.0F, 0.0F, 0.0F); return;
/* 2245:     */    } catch (PlayerNotFountException paramvg) {
/* 2246:1872 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 2247:     */    } catch (PlayerControlledTransformableNotFound paramvg) {
/* 2248:1874 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/* 2249:     */    }
/* 2250:     */  }
/* 2251:     */  
/* 2253:     */  public final void Q(vg paramvg)
/* 2254:     */  {
/* 2255:     */    try
/* 2256:     */    {
/* 2257:1883 */      paramvg = null;a(paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId()).b(), ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[0]).floatValue(), ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).floatValue(), ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).floatValue()); return;
/* 2259:     */    }
/* 2260:     */    catch (PlayerControlledTransformableNotFound paramvg)
/* 2261:     */    {
/* 2263:1889 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage()); return;
/* 2264:     */    } catch (PlayerNotFountException paramvg) {
/* 2265:1891 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] " + paramvg.getMessage());
/* 2266:     */    }
/* 2267:     */  }
/* 2268:     */  
/* 2269:     */  public final void R(vg paramvg)
/* 2270:     */  {
/* 2271:1897 */    String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 2272:1898 */    float f1 = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[1]).floatValue();
/* 2273:1899 */    float f2 = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[2]).floatValue();
/* 2274:1900 */    float f3 = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[3]).floatValue();
/* 2275:     */    
/* 2277:     */    try
/* 2278:     */    {
/* 2279:1905 */      a(paramvg.a(str).b(), f1, f2, f3);
/* 2280:1906 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] teleported " + str + " to "); return;
/* 2281:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/* 2282:1908 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); return;
/* 2283:     */    } catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {
/* 2284:1910 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player is not spawed");
/* 2285:     */    }
/* 2286:     */  }
/* 2287:     */  
/* 2289:     */  public final void S(vg paramvg)
/* 2290:     */  {
/* 2291:1917 */    Object localObject1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 2292:     */    try
/* 2293:     */    {
/* 2294:1920 */      lE locallE = paramvg.a((String)localObject1);
/* 2295:1921 */      localObject1 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId());
/* 2296:     */      
/* 2297:1923 */      Object localObject2 = locallE.b();
/* 2298:     */      
/* 2299:1925 */      new Vector3f(((mF)localObject2).getWorldTransform().origin).x += 1.0F;
/* 2300:1926 */      q localq = new q(((lE)localObject1).a());
/* 2301:     */      mx localmx;
/* 2302:1928 */      if ((localmx = paramvg.a().getSector(localq)) != null) {
/* 2303:1929 */        if (locallE.c() != localmx.a()) {
/* 2304:1930 */          for (localObject1 = ((lE)localObject1).a().a().iterator(); ((Iterator)localObject1).hasNext();)
/* 2305:1931 */            if (((localObject2 = (lA)((Iterator)localObject1).next()).a instanceof mF))
/* 2306:1932 */              paramvg.a().a((mF)((lA)localObject2).a, locallE.a(), 1); return;
/* 2307:     */        }
/* 2308:     */        
/* 2310:1936 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] not changing sector for object " + ((mF)localObject2).getSectorId() + "/" + localmx.a());return;
/* 2311:     */      }
/* 2312:     */      
/* 2313:1939 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localq + ": " + paramvg.a().getSectorSet()); return;
/* 2314:     */    }
/* 2315:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 2316:     */    {
/* 2317:1943 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); return;
/* 2318:1944 */    } catch (Exception localException) { 
/* 2319:     */      
/* 2321:1947 */        localException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/* 2322:     */    }
/* 2323:     */  }
/* 2324:     */  
/* 2325:     */  public final void T(vg paramvg) {
/* 2326:1952 */    Object localObject1 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[0];
/* 2327:     */    try
/* 2328:     */    {
/* 2329:1955 */      Object localObject2 = paramvg.a((String)localObject1);
/* 2330:     */      
/* 2332:1958 */      mF localmF = (localObject1 = paramvg.a(this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getId())).b();
/* 2333:     */      
/* 2334:1960 */      new Vector3f(localmF.getWorldTransform().origin).x += 1.0F;
/* 2335:1961 */      localObject1 = new q(((lE)localObject1).a());
/* 2336:     */      mx localmx;
/* 2337:1963 */      if ((localmx = paramvg.a().getSector((q)localObject1)) != null) {
/* 2338:1964 */        if (((lE)localObject2).c() != localmx.a()) {
/* 2339:1965 */          for (localObject1 = ((lE)localObject2).a().a().iterator(); ((Iterator)localObject1).hasNext();)
/* 2340:1966 */            if (((localObject2 = (lA)((Iterator)localObject1).next()).a instanceof mF))
/* 2341:1967 */              paramvg.a().a((mF)((lA)localObject2).a, localmx.jdField_a_of_type_Q, 1); return;
/* 2342:     */        }
/* 2343:     */        
/* 2345:1971 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] not changing sector for object " + localmF.getSectorId() + "/" + localmx.a());return;
/* 2346:     */      }
/* 2347:     */      
/* 2348:1974 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] sector not found: " + localObject1 + ": " + paramvg.a().getSectorSet()); return;
/* 2349:     */    }
/* 2350:     */    catch (PlayerNotFountException localPlayerNotFountException)
/* 2351:     */    {
/* 2352:1978 */      this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] player not found for your client"); return;
/* 2353:1979 */    } catch (Exception localException) { 
/* 2354:     */      
/* 2356:1982 */        localException.printStackTrace();this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("[ADMIN COMMAND] [ERROR] server could not load sector");
/* 2357:     */    }
/* 2358:     */  }
/* 2359:     */  
/* 2382:     */  private static void a(mF parammF, float paramFloat1, float paramFloat2, float paramFloat3)
/* 2383:     */  {
/* 2384:2010 */    parammF.warpTransformable(paramFloat1, paramFloat2, paramFloat3, true);
/* 2385:     */  }
/* 2386:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */