package org.schema.game.network.objects.remote;

import class_1041;
import class_1204;
import class_20;
import class_371;
import class_48;
import class_672;
import class_802;
import class_880;
import class_886;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.SegmentOutOfBoundsException;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteSegmentRemoteObj
  extends RemoteField
{
  private static ByteArrayOutputStream tmpArrayBuffer = new ByteArrayOutputStream(102400);
  private static DataOutputStream tmpBuffer = new DataOutputStream(tmpArrayBuffer);
  private static ThreadLocal threadLocal = new RemoteSegmentRemoteObj.1();
  
  public RemoteSegmentRemoteObj(NetworkObject paramNetworkObject)
  {
    super(new class_802(), paramNetworkObject);
  }
  
  public RemoteSegmentRemoteObj(class_802 paramclass_802, NetworkObject paramNetworkObject)
  {
    super(paramclass_802, paramNetworkObject);
  }
  
  public RemoteSegmentRemoteObj(boolean paramBoolean)
  {
    super(new class_802(), paramBoolean);
  }
  
  public RemoteSegmentRemoteObj(class_802 paramclass_802, boolean paramBoolean)
  {
    super(paramclass_802, paramBoolean);
  }
  
  public int byteLength()
  {
    return 0;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int arg2)
  {
    ??? = paramDataInputStream.readInt();
    int i = paramDataInputStream.readInt();
    int j = paramDataInputStream.readInt();
    int k = paramDataInputStream.readInt();
    int m = paramDataInputStream.readInt();
    Object localObject1 = (byte[])threadLocal.get();
    paramDataInputStream.readFully((byte[])localObject1, 0, m);
    paramDataInputStream = new ByteArrayInputStream((byte[])localObject1);
    paramDataInputStream = new DataInputStream(paramDataInputStream);
    if (this.onServer) {
      localObject1 = (Sendable)class_1041.field_144.getLocalAndRemoteObjectContainer().getLocalObjects().get(???);
    } else {
      localObject1 = (Sendable)class_371.field_144.getLocalAndRemoteObjectContainer().getLocalObjects().get(???);
    }
    if ((localObject1 == null) || (!(localObject1 instanceof SegmentController)))
    {
      System.err.println("[ERROR][RemoteSegmantRemoteObj] could not find segmentController: " + ??? + ": " + localObject1 + " -> dumping stream");
      class_20.field_14.a43(paramDataInputStream, m, true);
      return;
    }
    localObject1 = (class_20)(??? = (SegmentController)localObject1).getSegmentProvider();
    ((class_802)get()).jdField_field_1059_of_type_Class_48 = new class_48(i, j, k);
    int n = 0;
    synchronized (((class_20)localObject1).a5())
    {
      for (int i1 = 0; i1 < ((class_20)localObject1).a5().size(); i1++) {
        if (((Segment)((class_20)localObject1).a5().get(i1)).field_34.a3(i, j, k))
        {
          class_672 localclass_672;
          if ((localclass_672 = (class_672)((class_20)localObject1).a5().remove(i1)).a16() == null) {
            ???.getSegmentProvider().a25().assignData(localclass_672);
          }
          localclass_672.a43(paramDataInputStream, m, true);
          synchronized (((class_20)localObject1).a4())
          {
            assert (localclass_672 != null);
            ((class_20)localObject1).a4().add(localclass_672);
          }
          n = 1;
          break;
        }
      }
    }
    if (n == 0) {
      class_20.field_14.a43(paramDataInputStream, m, true);
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    SegmentController localSegmentController = ((class_802)get()).jdField_field_1059_of_type_OrgSchemaGameCommonControllerSegmentController;
    int i = 0;
    assert (tmpArrayBuffer.size() == 0);
    int j = 0;
    Object localObject1;
    Object localObject2;
    try
    {
      if ((localObject1 = localSegmentController.getSegmentBuffer().a5(((class_802)get()).jdField_field_1059_of_type_Class_48)) != null)
      {
        localObject2 = (class_672)localObject1;
        System.currentTimeMillis();
        class_672.d();
        assert (((class_672)localObject2).field_34.equals(((class_802)get()).jdField_field_1059_of_type_Class_48)) : (" serializing " + ((class_802)get()).jdField_field_1059_of_type_Class_48 + "; toSerialize " + ((class_672)localObject2).field_34);
        i = ((class_672)localObject2).a45(tmpBuffer);
        j = 1;
      }
    }
    catch (Exception localException)
    {
      localObject1 = null;
      localException.printStackTrace();
    }
    if (j == 0) {
      try
      {
        localObject2 = new class_48(((class_802)get()).jdField_field_1059_of_type_Class_48);
        localObject1 = (class_672)((class_1204)localSegmentController.getSegmentProvider()).a12(((class_802)get()).jdField_field_1059_of_type_Class_48.field_475, ((class_802)get()).jdField_field_1059_of_type_Class_48.field_476, ((class_802)get()).jdField_field_1059_of_type_Class_48.field_477);
        assert (((class_672)localObject1).field_34.equals(((class_802)get()).jdField_field_1059_of_type_Class_48)) : (" REQUESTED " + ((class_802)get()).jdField_field_1059_of_type_Class_48 + "; BUT GOT " + ((class_672)localObject1).field_34 + "; ORIGINAL: " + localObject2);
        System.currentTimeMillis();
        class_672.d();
        i = ((class_672)localObject1).a45(tmpBuffer);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
      catch (SegmentOutOfBoundsException localSegmentOutOfBoundsException)
      {
        localSegmentOutOfBoundsException;
      }
    }
    assert (i == tmpArrayBuffer.size()) : ("LEN " + i + "; written: " + tmpBuffer.size());
    assert (i > 0);
    paramDataOutputStream.writeInt(localSegmentController.getId());
    paramDataOutputStream.writeInt(((class_802)get()).jdField_field_1059_of_type_Class_48.field_475);
    paramDataOutputStream.writeInt(((class_802)get()).jdField_field_1059_of_type_Class_48.field_476);
    paramDataOutputStream.writeInt(((class_802)get()).jdField_field_1059_of_type_Class_48.field_477);
    paramDataOutputStream.writeInt(i);
    tmpArrayBuffer.writeTo(paramDataOutputStream);
    tmpArrayBuffer.reset();
    return i + 20;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentRemoteObj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */