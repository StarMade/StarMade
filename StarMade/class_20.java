import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import javax.vecmath.Vector3f;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.network.objects.NetworkSegmentProvider;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteVector3i;

public class class_20
  extends class_880
{
  private class_48 jdField_field_14_of_type_Class_48 = new class_48();
  private class_48 jdField_field_15_of_type_Class_48 = new class_48();
  private class_48 jdField_field_16_of_type_Class_48 = new class_48();
  private long jdField_field_15_of_type_Long;
  private static Random jdField_field_14_of_type_JavaUtilRandom;
  private final ObjectOpenHashSet jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet = new ObjectOpenHashSet();
  private final ObjectOpenHashSet jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet = new ObjectOpenHashSet();
  private final ObjectOpenHashSet jdField_field_16_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet = new ObjectOpenHashSet();
  private final ObjectOpenHashSet jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet = new ObjectOpenHashSet();
  private final ArrayList jdField_field_15_of_type_JavaUtilArrayList = new ArrayList();
  private class_749 jdField_field_14_of_type_Class_749;
  public static class_672 field_14;
  public static int field_14;
  private Vector3f jdField_field_14_of_type_JavaxVecmathVector3f = new Vector3f();
  private class_988 jdField_field_14_of_type_Class_988 = new class_988();
  private ObjectOpenHashSet jdField_field_18_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet = new ObjectOpenHashSet();
  private int jdField_field_15_of_type_Int;
  private long jdField_field_16_of_type_Long;
  private static final class_17 jdField_field_14_of_type_Class_17;
  private class_48 jdField_field_17_of_type_Class_48 = new class_48();
  private class_48 jdField_field_18_of_type_Class_48 = new class_48();
  private int jdField_field_16_of_type_Int;
  private Vector3f jdField_field_15_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_16_of_type_JavaxVecmathVector3f = new Vector3f();
  private ArrayList jdField_field_16_of_type_JavaUtilArrayList = new ArrayList();
  private class_657[] jdField_field_14_of_type_ArrayOfClass_657 = new class_657[32];
  private class_657[] jdField_field_15_of_type_ArrayOfClass_657 = new class_657[32];
  private int jdField_field_17_of_type_Int = 0;
  private int jdField_field_18_of_type_Int = 0;
  private boolean jdField_field_14_of_type_Boolean;
  private Vector3f jdField_field_17_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Comparator jdField_field_14_of_type_JavaUtilComparator = new class_23(this);
  private boolean jdField_field_15_of_type_Boolean;
  private boolean jdField_field_16_of_type_Boolean;
  private long jdField_field_17_of_type_Long = -1L;
  private boolean jdField_field_17_of_type_Boolean;
  
  public class_20(SegmentController paramSegmentController)
  {
    super(paramSegmentController);
    if (jdField_field_14_of_type_Class_672 == null)
    {
      System.err.println("NEW DUMMY SEGMENT");
      (class_20.jdField_field_14_of_type_Class_672 = new class_672(paramSegmentController)).a22(new SegmentData(false));
    }
  }
  
  private void g()
  {
    class_20 localclass_20 = this;
    long l1 = System.currentTimeMillis();
    if (!localclass_20.jdField_field_16_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty()) {
      synchronized (localclass_20.jdField_field_16_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
      {
        ObjectIterator localObjectIterator = localclass_20.jdField_field_16_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator();
        while (localObjectIterator.hasNext())
        {
          class_802 localclass_802 = (class_802)localObjectIterator.next();
          localObjectIterator.remove();
          int i;
          if (localclass_802.jdField_field_1059_of_type_Boolean) {
            synchronized (localclass_20.jdField_field_15_of_type_JavaUtilArrayList)
            {
              for (i = 0; i < localclass_20.jdField_field_15_of_type_JavaUtilArrayList.size(); i++)
              {
                Segment localSegment;
                if (((localSegment = (Segment)localclass_20.jdField_field_15_of_type_JavaUtilArrayList.get(i)) == null) || (localSegment.jdField_field_34_of_type_Class_48 == null))
                {
                  localclass_20.jdField_field_15_of_type_JavaUtilArrayList.remove(i);
                  i--;
                }
                else if (localSegment.jdField_field_34_of_type_Class_48.equals(localclass_802.jdField_field_1059_of_type_Class_48))
                {
                  if (!localSegment.g())
                  {
                    System.err.println("[CLIENT][PROVIDER] Segment was not empty but received signature says so! -> setting empty on client (reqrequest though missile hit?)");
                    localclass_20.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a20(localSegment.a16());
                    localSegment.b2(0);
                  }
                  ((class_672)localSegment).a46(localclass_802.jdField_field_1059_of_type_Long);
                  localclass_20.f((class_672)localSegment);
                }
              }
            }
          }
          if (localclass_20.a7(localclass_802))
          {
            ??? = null;
            i = 0;
            int j;
            synchronized (localclass_20.jdField_field_15_of_type_JavaUtilArrayList)
            {
              for (int k = 0; k < localclass_20.jdField_field_15_of_type_JavaUtilArrayList.size(); ???++) {
                if (((??? = (Segment)localclass_20.jdField_field_15_of_type_JavaUtilArrayList.get(k)) == null) || (((Segment)???).jdField_field_34_of_type_Class_48 == null))
                {
                  localclass_20.jdField_field_15_of_type_JavaUtilArrayList.remove(k);
                  k--;
                }
                else if (((Segment)???).jdField_field_34_of_type_Class_48.equals(localclass_802.jdField_field_1059_of_type_Class_48))
                {
                  class_672 localclass_672 = (class_672)???;
                  if ((??? = localclass_20).jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a3(localclass_672.jdField_field_34_of_type_Class_48))
                  {
                    synchronized (((class_20)???).jdField_field_15_of_type_JavaUtilArrayList)
                    {
                      ((class_20)???).jdField_field_15_of_type_JavaUtilArrayList.remove(localclass_672);
                    }
                    synchronized (((class_20)???).jdField_field_14_of_type_JavaUtilCollection)
                    {
                      ((class_20)???).jdField_field_14_of_type_JavaUtilHashSet.remove(localclass_672.jdField_field_34_of_type_Class_48);
                    }
                  }
                  jdField_field_14_of_type_Class_17.a(new class_22((class_20)???, localclass_672));
                  j = 1;
                  break;
                }
              }
            }
            if (j == 0) {
              System.err.println("[WARNING] the request could not be found: " + localclass_802.jdField_field_1059_of_type_Class_48 + " " + localObject3.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController);
            }
          }
          else
          {
            if (((??? = localObject3.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a5(localclass_802.jdField_field_1059_of_type_Class_48)) != null) && (!jdField_field_18_of_type_Boolean) && (!localclass_802.jdField_field_1059_of_type_Class_48.equals(((Segment)???).jdField_field_34_of_type_Class_48))) {
              throw new AssertionError();
            }
            synchronized (localObject3.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
            {
              localObject3.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(new class_48(localclass_802.jdField_field_1059_of_type_Class_48));
            }
          }
        }
      }
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 20L) {
      System.err.println("[CLIENT][SEGMENTPROVIDER] WARNING: checkReceivedSignatures of " + localObject5.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController + " took: " + l2);
    }
    j();
  }
  
  public final void a()
  {
    jdField_field_14_of_type_Int -= 1;
  }
  
  private void h()
  {
    long l1 = System.currentTimeMillis();
    if (e())
    {
      if (!this.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty()) {
        synchronized (this.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
        {
          ObjectIterator localObjectIterator = this.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator();
          while (localObjectIterator.hasNext())
          {
            class_48 localclass_48 = (class_48)localObjectIterator.next();
            localObjectIterator.remove();
            this.jdField_field_14_of_type_Class_749.a47().segmentRequestBuffer.add(new RemoteVector3i(new class_48(localclass_48), false));
          }
        }
      }
    }
    else {
      System.err.println("WAITING FOR PIPE!! " + this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController);
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - localObject1) > 5L) {
      System.err.println("[CLIENT][SEGMENTPROVIDER] WARNING: doNTSegmentRequests of " + this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController + " took: " + l2);
    }
  }
  
  private void i()
  {
    long l1 = System.currentTimeMillis();
    int i = this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.size();
    if (e())
    {
      if (!this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty()) {
        synchronized (this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
        {
          ObjectIterator localObjectIterator = this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator();
          while (localObjectIterator.hasNext())
          {
            class_48 localclass_48 = (class_48)localObjectIterator.next();
            localObjectIterator.remove();
            if ((!jdField_field_18_of_type_Boolean) && (localclass_48 == null)) {
              throw new AssertionError();
            }
            if ((!jdField_field_18_of_type_Boolean) && (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController == null)) {
              throw new AssertionError();
            }
            if ((!jdField_field_18_of_type_Boolean) && ((class_52)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getController() == null)) {
              throw new AssertionError();
            }
            if ((!jdField_field_18_of_type_Boolean) && (!a21(localclass_48))) {
              throw new AssertionError();
            }
            this.jdField_field_14_of_type_Class_749.a47().signatureRequestBuffer.add(new RemoteVector3i(new class_48(localclass_48), false));
          }
        }
      }
    }
    else {
      System.err.println("WAITING FOR PIPE!! " + this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController);
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - localObject1) > 5L) {
      System.err.println("[CLIENT][SEGMENTPROVIDER] WARNING: doNTSegmentSinaturesRequests of " + this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController + " took: " + l2 + "; requests: " + i);
    }
  }
  
  protected final class_672 a1(class_48 paramclass_48, boolean paramBoolean)
  {
    if ((!jdField_field_18_of_type_Boolean) && (paramBoolean)) {
      throw new AssertionError();
    }
    return super.a1(paramclass_48, paramBoolean);
  }
  
  protected final void a2(class_672 paramclass_672)
  {
    ((class_371)paramclass_672.a15().getState()).a26().a(paramclass_672);
  }
  
  private float a3(class_48 paramclass_48)
  {
    Transform localTransform = this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformClient();
    this.jdField_field_15_of_type_JavaxVecmathVector3f.set(paramclass_48.field_475 + localTransform.origin.field_615, paramclass_48.field_476 + localTransform.origin.field_616, paramclass_48.field_477 + localTransform.origin.field_617);
    this.jdField_field_15_of_type_JavaxVecmathVector3f.sub(class_969.a1().a83());
    return this.jdField_field_15_of_type_JavaxVecmathVector3f.lengthSquared();
  }
  
  public final ObjectOpenHashSet a4()
  {
    return this.jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet;
  }
  
  public final ObjectOpenHashSet b()
  {
    return this.jdField_field_16_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet;
  }
  
  public final ArrayList a5()
  {
    return this.jdField_field_15_of_type_JavaUtilArrayList;
  }
  
  private void f(class_672 paramclass_672)
  {
    if ((!jdField_field_18_of_type_Boolean) && (!paramclass_672.g()) && (paramclass_672.a16() == null)) {
      throw new AssertionError(" SEGMENT NULL: " + paramclass_672.b1() + "; " + paramclass_672.jdField_field_34_of_type_Class_48 + " " + paramclass_672.a15());
    }
    if ((!jdField_field_18_of_type_Boolean) && (paramclass_672 == null)) {
      throw new AssertionError();
    }
    class_672 localclass_672 = paramclass_672;
    paramclass_672 = this;
    if (!this.jdField_field_14_of_type_JavaUtilArrayList.contains(localclass_672)) {
      paramclass_672.jdField_field_14_of_type_JavaUtilArrayList.add(localclass_672);
    }
  }
  
  private void j()
  {
    long l1 = System.currentTimeMillis();
    Segment localSegment = null;
    if (!this.jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty()) {
      synchronized (this.jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
      {
        ObjectIterator localObjectIterator = this.jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator();
        while (localObjectIterator.hasNext())
        {
          localSegment = (Segment)localObjectIterator.next();
          localObjectIterator.remove();
          if ((!jdField_field_18_of_type_Boolean) && (localSegment == null)) {
            throw new AssertionError();
          }
          f((class_672)localSegment);
        }
      }
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - localObject1) > 5L) {
      System.err.println("[CLIENT][SEGMENTPROVIDER] WARNING: handleReceivedSegments of " + this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController + " took: " + l2);
    }
  }
  
  private class_663 a6(class_657 paramclass_657)
  {
    if (a3(paramclass_657.jdField_field_34_of_type_Class_48) >= class_969.field_1259.a() * class_969.field_1259.a() * class_969.field_1259.a()) {
      return class_663.field_936;
    }
    if (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a5(paramclass_657.jdField_field_34_of_type_Class_48) != paramclass_657) {
      return class_663.field_937;
    }
    for (int i = 0; i < 6; i++)
    {
      if ((paramclass_657.jdField_field_34_of_type_Int & org.schema.game.common.data.element.Element.SIDE_FLAG[i]) != org.schema.game.common.data.element.Element.SIDE_FLAG[i])
      {
        this.jdField_field_17_of_type_Class_48.b1(paramclass_657.jdField_field_34_of_type_Class_48);
        this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getNeighborSegmentPos(this.jdField_field_17_of_type_Class_48, i, this.jdField_field_18_of_type_Class_48);
        if (!a21(this.jdField_field_18_of_type_Class_48))
        {
          paramclass_657.jdField_field_34_of_type_Int += org.schema.game.common.data.element.Element.SIDE_FLAG[i];
        }
        else if (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a3(this.jdField_field_18_of_type_Class_48))
        {
          paramclass_657.jdField_field_34_of_type_Int += org.schema.game.common.data.element.Element.SIDE_FLAG[i];
          this.jdField_field_16_of_type_Int += 1;
        }
        else if ((!this.jdField_field_14_of_type_JavaUtilCollection.contains(this.jdField_field_18_of_type_Class_48)) && (!this.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(this.jdField_field_18_of_type_Class_48)) && (!this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(this.jdField_field_18_of_type_Class_48)) && (a3(this.jdField_field_18_of_type_Class_48) < class_969.field_1259.a() * class_969.field_1259.a()))
        {
          class_48 localclass_48 = new class_48(this.jdField_field_18_of_type_Class_48);
          this.jdField_field_18_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localclass_48);
          paramclass_657.jdField_field_34_of_type_Int += org.schema.game.common.data.element.Element.SIDE_FLAG[i];
          this.jdField_field_16_of_type_Int += 1;
        }
      }
      this.jdField_field_15_of_type_Int += 1;
    }
    if ((!jdField_field_18_of_type_Boolean) && (paramclass_657.jdField_field_34_of_type_Int >= 64)) {
      throw new AssertionError(paramclass_657.jdField_field_34_of_type_Int + "; " + paramclass_657);
    }
    if (paramclass_657.jdField_field_34_of_type_Int == 63) {
      return class_663.field_935;
    }
    return class_663.field_934;
  }
  
  public final void b1()
  {
    synchronized (this.jdField_field_15_of_type_JavaUtilArrayList)
    {
      for (int i = 0; i < this.jdField_field_15_of_type_JavaUtilArrayList.size(); i++) {
        try
        {
          Object localObject2;
          if ((localObject2 = (Segment)this.jdField_field_15_of_type_JavaUtilArrayList.get(i)) == null)
          {
            System.err.println("[CLIENT-SEGMENT-PROVIDER] ERROR-REQUESTED SEG: NULL: " + this.jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet);
            this.jdField_field_15_of_type_JavaUtilArrayList.remove(i);
            i--;
          }
          else
          {
            localObject2 = (class_672)localObject2;
            if (System.currentTimeMillis() - ((class_672)localObject2).b8() > 50000L)
            {
              System.err.println("[CLIENT-SEGMENT-PROVIDER] REQUEST TIMEOUT FOR " + localObject2 + " REREQUESTING SIGNATURE (" + this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController + ")");
              if (((class_371)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a7().containsKey(((class_672)localObject2).a15().getId()))
              {
                ((class_672)localObject2).b9(System.currentTimeMillis());
                synchronized (this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
                {
                  this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(new class_48(((class_672)localObject2).jdField_field_34_of_type_Class_48));
                  this.jdField_field_15_of_type_JavaUtilArrayList.remove(i);
                  i--;
                }
              }
              System.err.println("REQUEST IN ANOTHER SECTOR. DISCARDING " + localObject3);
              this.jdField_field_15_of_type_JavaUtilArrayList.remove(i);
              i--;
            }
          }
        }
        catch (Exception localException)
        {
          System.err.println("[CLIENTSEGEMENTPROVIDER] CATCHED EXCEPTION " + localException.getClass() + ": " + localException.getMessage() + " (" + this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController + ")");
        }
      }
      return;
    }
  }
  
  public final void c()
  {
    jdField_field_14_of_type_Int += 1;
  }
  
  private boolean a7(class_802 paramclass_802)
  {
    try
    {
      long l;
      if (((l = this.jdField_field_14_of_type_Class_720.a5(paramclass_802.jdField_field_1059_of_type_Class_48.field_475, paramclass_802.jdField_field_1059_of_type_Class_48.field_476, paramclass_802.jdField_field_1059_of_type_Class_48.field_477)) < 0L) || (l < paramclass_802.jdField_field_1059_of_type_Long)) {
        return false;
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      return false;
    }
    return true;
  }
  
  public final void a8(Segment paramSegment)
  {
    synchronized (this.jdField_field_15_of_type_JavaUtilArrayList)
    {
      this.jdField_field_15_of_type_JavaUtilArrayList.remove(paramSegment);
      return;
    }
  }
  
  final int a9(class_672 paramclass_672)
  {
    return this.jdField_field_14_of_type_Class_720.a8(paramclass_672.jdField_field_34_of_type_Class_48.field_475, paramclass_672.jdField_field_34_of_type_Class_48.field_476, paramclass_672.jdField_field_34_of_type_Class_48.field_477, paramclass_672);
  }
  
  protected final boolean a10()
  {
    return (this.jdField_field_15_of_type_JavaUtilArrayList.size() < 200) && (this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.size() < 200);
  }
  
  protected final class_48 a11()
  {
    ArrayList localArrayList = (ArrayList)this.jdField_field_14_of_type_JavaUtilCollection;
    this.jdField_field_17_of_type_JavaxVecmathVector3f.set(class_969.a1().a83());
    int i = -1;
    for (int j = 0; j < localArrayList.size(); j++)
    {
      class_48 localclass_48 = (class_48)localArrayList.get(j);
      if ((i < 0) || (this.jdField_field_14_of_type_JavaUtilComparator.compare(localArrayList.get(i), localclass_48) > 0)) {
        i = j;
      }
    }
    return (class_48)localArrayList.remove(i);
  }
  
  public final Segment a12(int paramInt1, int paramInt2, int paramInt3)
  {
    if (!jdField_field_18_of_type_Boolean) {
      throw new AssertionError();
    }
    throw new CannotImmediateRequestOnClientException(new class_48(paramInt1, paramInt2, paramInt3));
  }
  
  public final boolean b2()
  {
    if (!((class_371)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a7().containsKey(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getId())) {
      return false;
    }
    if (System.currentTimeMillis() - this.jdField_field_16_of_type_Long > 1000L)
    {
      d();
      this.jdField_field_16_of_type_Long = System.currentTimeMillis();
      return true;
    }
    NetworkObject localNetworkObject;
    if (((localNetworkObject = (NetworkObject)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getRemoteObjects().get(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getId())) == null) || (localNetworkObject.newObject) || (class_969.a1() == null) || (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getLocalCamPos().field_617 == (1.0F / 1.0F))) {
      return false;
    }
    long l1 = System.currentTimeMillis();
    this.jdField_field_14_of_type_JavaxVecmathVector3f.set(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getLocalCamPos());
    this.jdField_field_14_of_type_Class_988.field_1273.set(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_475 << 4, this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_476 << 4, this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_477 << 4);
    this.jdField_field_14_of_type_Class_988.field_1274.set(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_475 << 4, this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_476 << 4, this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_477 << 4);
    int i = ByteUtil.b((int)this.jdField_field_14_of_type_JavaxVecmathVector3f.field_615);
    int m = ByteUtil.b((int)this.jdField_field_14_of_type_JavaxVecmathVector3f.field_616);
    int n = ByteUtil.b((int)this.jdField_field_14_of_type_JavaxVecmathVector3f.field_617);
    this.jdField_field_14_of_type_Class_48.b(i, m, n);
    this.jdField_field_15_of_type_Int = 0;
    this.jdField_field_16_of_type_Int = 0;
    if (this.jdField_field_14_of_type_Class_988.a7(this.jdField_field_14_of_type_JavaxVecmathVector3f))
    {
      this.jdField_field_15_of_type_Class_48.b(i << 4, m << 4, n << 4);
      if ((!this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a3(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_14_of_type_JavaUtilCollection.contains(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(this.jdField_field_15_of_type_Class_48)))
      {
        if (a21(this.jdField_field_15_of_type_Class_48)) {
          this.jdField_field_18_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(new class_48(this.jdField_field_15_of_type_Class_48));
        }
        this.jdField_field_16_of_type_Int += 1;
      }
      this.jdField_field_15_of_type_Int += 1;
    }
    else
    {
      Vector3f localVector3f = this.jdField_field_14_of_type_Class_988.a4(this.jdField_field_14_of_type_JavaxVecmathVector3f, new Vector3f());
      class_988 localclass_9881;
      (localclass_9881 = new class_988()).field_1273.field_615 = (localVector3f.field_615 - 16.0F);
      localclass_9881.field_1273.field_616 = (localVector3f.field_616 - 16.0F);
      localclass_9881.field_1273.field_617 = (localVector3f.field_617 - 16.0F);
      localclass_9881.field_1274.field_615 = (localVector3f.field_615 + 16.0F);
      localclass_9881.field_1274.field_616 = (localVector3f.field_616 + 16.0F);
      localclass_9881.field_1274.field_617 = (localVector3f.field_617 + 16.0F);
      class_988 localclass_9882;
      if ((localclass_9882 = localclass_9881.a5(this.jdField_field_14_of_type_Class_988, new class_988())) == null) {
        return false;
      }
      localclass_9882.field_1273.field_615 = (ByteUtil.a((int)localclass_9882.field_1273.field_615) << 4);
      localclass_9882.field_1273.field_616 = (ByteUtil.a((int)localclass_9882.field_1273.field_616) << 4);
      localclass_9882.field_1273.field_617 = (ByteUtil.a((int)localclass_9882.field_1273.field_617) << 4);
      localclass_9882.field_1274.field_615 = (ByteUtil.a((int)localclass_9882.field_1274.field_615) << 4);
      localclass_9882.field_1274.field_616 = (ByteUtil.a((int)localclass_9882.field_1274.field_616) << 4);
      localclass_9882.field_1274.field_617 = (ByteUtil.a((int)localclass_9882.field_1274.field_617) << 4);
      try
      {
        for (int i2 = (int)localclass_9882.field_1273.field_615; i2 <= localclass_9882.field_1274.field_615; i2 += 16) {
          for (i = (int)localclass_9882.field_1273.field_616; i <= localclass_9882.field_1274.field_616; i += 16) {
            for (m = (int)localclass_9882.field_1273.field_617; m <= localclass_9882.field_1274.field_617; m += 16)
            {
              this.jdField_field_15_of_type_Class_48.b(i2, i, m);
              if ((!this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a3(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_14_of_type_JavaUtilCollection.contains(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_15_of_type_JavaUtilArrayList.contains(this.jdField_field_15_of_type_Class_48)))
              {
                if (a21(this.jdField_field_15_of_type_Class_48)) {
                  this.jdField_field_18_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(new class_48(this.jdField_field_15_of_type_Class_48));
                }
                this.jdField_field_16_of_type_Int += 1;
              }
              this.jdField_field_15_of_type_Int += 1;
            }
          }
        }
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        localArrayIndexOutOfBoundsException;
      }
    }
    long l2 = System.currentTimeMillis();
    int i1;
    class_657 localclass_657;
    if (this.jdField_field_14_of_type_Boolean) {
      for (i1 = 0; i1 < this.jdField_field_18_of_type_Int; i1++) {
        (localclass_657 = this.jdField_field_15_of_type_ArrayOfClass_657[i1]).jdField_field_34_of_type_Class_663 = a6(localclass_657);
      }
    } else {
      for (i1 = 0; i1 < this.jdField_field_17_of_type_Int; i1++) {
        (localclass_657 = this.jdField_field_14_of_type_ArrayOfClass_657[i1]).jdField_field_34_of_type_Class_663 = a6(localclass_657);
      }
    }
    long l3 = System.currentTimeMillis();
    i = this.jdField_field_18_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.size();
    a22(this.jdField_field_18_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet);
    if ((!this.jdField_field_14_of_type_Class_48.equals(this.jdField_field_16_of_type_Class_48)) || (System.currentTimeMillis() - this.jdField_field_15_of_type_Long > 700L))
    {
      this.jdField_field_16_of_type_Class_48.b1(this.jdField_field_14_of_type_Class_48);
      this.jdField_field_15_of_type_Long = System.currentTimeMillis();
    }
    m = (int)(System.currentTimeMillis() - l1);
    int j = (int)(System.currentTimeMillis() - l3);
    int k = (int)(System.currentTimeMillis() - l2) - j;
    if (m > 20) {
      System.err.println("[CLIENTPROVIDER] WARNING: request time total > 10: " + m + "; RETtime " + k + "; enqTime " + j + " ;ITERATIONS: " + this.jdField_field_16_of_type_Int + " / " + this.jdField_field_15_of_type_Int + "; Ret REQ size: " + this.jdField_field_16_of_type_JavaUtilArrayList.size());
    }
    return i > 0;
  }
  
  private boolean d()
  {
    boolean bool = false;
    NetworkObject localNetworkObject;
    if (((localNetworkObject = (NetworkObject)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getRemoteObjects().get(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getId())) == null) || (localNetworkObject.newObject) || (class_969.a1() == null) || (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getLocalCamPos().field_617 == (1.0F / 1.0F))) {
      return false;
    }
    long l = System.currentTimeMillis();
    synchronized (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getLocalCamPos())
    {
      this.jdField_field_14_of_type_JavaxVecmathVector3f.set(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getLocalCamPos());
    }
    this.jdField_field_14_of_type_Class_988.field_1273.set((this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_475 << 4) - 8, (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_476 << 4) - 8, (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_477 << 4) - 8);
    this.jdField_field_14_of_type_Class_988.field_1274.set((this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_475 << 4) + 8, (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_476 << 4) + 8, (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_477 << 4) + 8);
    Object localObject2 = null;
    if (!this.jdField_field_14_of_type_Class_988.a7(this.jdField_field_14_of_type_JavaxVecmathVector3f))
    {
      (localObject2 = this.jdField_field_14_of_type_Class_988.a4(this.jdField_field_14_of_type_JavaxVecmathVector3f, new Vector3f())).sub(this.jdField_field_14_of_type_JavaxVecmathVector3f);
      if (((Vector3f)localObject2).length() > class_969.field_1259.a() * class_969.field_1259.a()) {
        return false;
      }
    }
    int i = ByteUtil.b((int)this.jdField_field_14_of_type_JavaxVecmathVector3f.field_615);
    int m = ByteUtil.b((int)this.jdField_field_14_of_type_JavaxVecmathVector3f.field_616);
    int i1 = ByteUtil.b((int)this.jdField_field_14_of_type_JavaxVecmathVector3f.field_617);
    int i2 = 0;
    int i3 = 0;
    this.jdField_field_14_of_type_Class_48.b(i, m, i1);
    if ((!this.jdField_field_14_of_type_Class_48.equals(this.jdField_field_16_of_type_Class_48)) || (System.currentTimeMillis() - this.jdField_field_15_of_type_Long > 700L))
    {
      this.jdField_field_16_of_type_Class_48.b1(this.jdField_field_14_of_type_Class_48);
      class_988 localclass_988;
      (localclass_988 = new class_988()).field_1273.field_615 = (-80.0F + this.jdField_field_14_of_type_JavaxVecmathVector3f.field_615);
      localclass_988.field_1273.field_616 = (-80.0F + this.jdField_field_14_of_type_JavaxVecmathVector3f.field_616);
      localclass_988.field_1273.field_617 = (-80.0F + this.jdField_field_14_of_type_JavaxVecmathVector3f.field_617);
      localclass_988.field_1274.field_615 = (80.0F + this.jdField_field_14_of_type_JavaxVecmathVector3f.field_615);
      localclass_988.field_1274.field_616 = (80.0F + this.jdField_field_14_of_type_JavaxVecmathVector3f.field_616);
      localclass_988.field_1274.field_617 = (80.0F + this.jdField_field_14_of_type_JavaxVecmathVector3f.field_617);
      if ((localclass_988 = localclass_988.a5(this.jdField_field_14_of_type_Class_988, new class_988())) == null) {
        if (localObject2 != null)
        {
          ((Vector3f)localObject2).add(this.jdField_field_14_of_type_JavaxVecmathVector3f);
          this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getClientTransformInverse().transform((Vector3f)localObject2);
          localclass_988 = new class_988();
          localObject2 = this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos();
          class_48 localclass_48 = this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos();
          if (Math.abs(((class_48)localObject2).field_475 - localclass_48.field_475) > 0) {
            jdField_field_14_of_type_JavaUtilRandom.nextInt(Math.abs(((class_48)localObject2).field_475 - localclass_48.field_475));
          }
          if (Math.abs(((class_48)localObject2).field_475 - localclass_48.field_475) > 0) {
            jdField_field_14_of_type_JavaUtilRandom.nextInt(Math.abs(((class_48)localObject2).field_476 - localclass_48.field_476));
          }
          if (Math.abs(((class_48)localObject2).field_475 - localclass_48.field_475) > 0) {
            jdField_field_14_of_type_JavaUtilRandom.nextInt(Math.abs(((class_48)localObject2).field_477 - localclass_48.field_477));
          }
          localclass_988.field_1273.set(0.0F, 0.0F, 0.0F);
          localclass_988.field_1274.set(0.0F, 0.0F, 0.0F);
        }
        else
        {
          return false;
        }
      }
      localclass_988.field_1273.field_615 = (ByteUtil.a((int)localclass_988.field_1273.field_615) << 4);
      localclass_988.field_1273.field_616 = (ByteUtil.a((int)localclass_988.field_1273.field_616) << 4);
      localclass_988.field_1273.field_617 = (ByteUtil.a((int)localclass_988.field_1273.field_617) << 4);
      localclass_988.field_1274.field_615 = (ByteUtil.a((int)localclass_988.field_1274.field_615) << 4);
      localclass_988.field_1274.field_616 = (ByteUtil.a((int)localclass_988.field_1274.field_616) << 4);
      localclass_988.field_1274.field_617 = (ByteUtil.a((int)localclass_988.field_1274.field_617) << 4);
      try
      {
        for (int k = (int)localclass_988.field_1273.field_615; k <= localclass_988.field_1274.field_615; k += 16)
        {
          for (int n = (int)localclass_988.field_1273.field_616; n <= localclass_988.field_1274.field_616; n += 16) {
            for (i1 = (int)localclass_988.field_1273.field_617; i1 <= localclass_988.field_1274.field_617; i1 += 16)
            {
              this.jdField_field_15_of_type_Class_48.b(k, n, i1);
              if ((!this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a3(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_14_of_type_JavaUtilCollection.contains(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(this.jdField_field_15_of_type_Class_48)) && (!this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(this.jdField_field_15_of_type_Class_48)))
              {
                bool = true;
                if (a21(this.jdField_field_15_of_type_Class_48)) {
                  this.jdField_field_18_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(new class_48(this.jdField_field_15_of_type_Class_48));
                }
                i3++;
              }
            }
          }
          i2++;
        }
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        localArrayIndexOutOfBoundsException;
      }
      a22(this.jdField_field_18_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet);
      this.jdField_field_15_of_type_Long = System.currentTimeMillis();
    }
    int j;
    if ((j = (int)(System.currentTimeMillis() - l)) > 20) {
      System.err.println("[CLIENTPROVIDER] WARNING: WHOLE request time > 10: " + j + "; ITERATIONS: " + i3 + " / " + i2);
    }
    return bool;
  }
  
  public final void b3(class_672 paramclass_672)
  {
    if (!jdField_field_18_of_type_Boolean) {
      throw new AssertionError("no blocked request in client please!");
    }
    throw new CannotImmediateRequestOnClientException(paramclass_672.jdField_field_34_of_type_Class_48);
  }
  
  public final void c1(class_672 paramclass_672)
  {
    if ((!jdField_field_18_of_type_Boolean) && (paramclass_672 == null)) {
      throw new AssertionError();
    }
    boolean bool1 = false;
    synchronized (this.jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
    {
      bool1 = this.jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(paramclass_672);
    }
    boolean bool2 = false;
    synchronized (this.jdField_field_15_of_type_JavaUtilArrayList)
    {
      bool2 = this.jdField_field_15_of_type_JavaUtilArrayList.contains(paramclass_672);
    }
    if ((!bool1) && (!bool2))
    {
      paramclass_672.b9(System.currentTimeMillis());
      synchronized (this.jdField_field_15_of_type_JavaUtilArrayList)
      {
        this.jdField_field_15_of_type_JavaUtilArrayList.add(paramclass_672);
      }
      synchronized (this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
      {
        if (!this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(paramclass_672.jdField_field_34_of_type_Class_48)) {
          this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(new class_48(paramclass_672.jdField_field_34_of_type_Class_48));
        }
        return;
      }
    }
  }
  
  public final void a13(class_657 paramclass_657)
  {
    this.jdField_field_16_of_type_JavaUtilArrayList.add(paramclass_657);
  }
  
  public final void a14(class_749 paramclass_749)
  {
    this.jdField_field_14_of_type_Class_749 = paramclass_749;
  }
  
  protected final Collection a15()
  {
    return new class_915();
  }
  
  private boolean e()
  {
    if (!this.jdField_field_16_of_type_Boolean) {
      this.jdField_field_16_of_type_Boolean = ((this.jdField_field_14_of_type_Class_749 != null) && (this.jdField_field_14_of_type_Class_749.a1()));
    }
    return this.jdField_field_16_of_type_Boolean;
  }
  
  public final void d1()
  {
    super.d1();
    if (e())
    {
      class_20 localclass_20 = this;
      long l1 = System.currentTimeMillis();
      int i;
      if ((i = localclass_20.jdField_field_16_of_type_JavaUtilArrayList.size()) > 0)
      {
        int j;
        if (localclass_20.jdField_field_14_of_type_Boolean)
        {
          localclass_20.jdField_field_17_of_type_Int = 0;
          for (j = 0; j < localclass_20.jdField_field_16_of_type_JavaUtilArrayList.size(); j++) {
            if (((class_657)localclass_20.jdField_field_16_of_type_JavaUtilArrayList.get(j)).jdField_field_34_of_type_Class_663.field_933 == 0)
            {
              localclass_20.jdField_field_16_of_type_JavaUtilArrayList.remove(j);
              j--;
            }
            else if (localclass_20.jdField_field_17_of_type_Int < localclass_20.jdField_field_14_of_type_ArrayOfClass_657.length)
            {
              localclass_20.jdField_field_14_of_type_ArrayOfClass_657[localclass_20.jdField_field_17_of_type_Int] = ((class_657)localclass_20.jdField_field_16_of_type_JavaUtilArrayList.get(j));
              localclass_20.jdField_field_17_of_type_Int += 1;
            }
          }
          localclass_20.jdField_field_14_of_type_Boolean = false;
        }
        else
        {
          localclass_20.jdField_field_18_of_type_Int = 0;
          for (j = 0; j < localclass_20.jdField_field_16_of_type_JavaUtilArrayList.size(); j++) {
            if (((class_657)localclass_20.jdField_field_16_of_type_JavaUtilArrayList.get(j)).jdField_field_34_of_type_Class_663.field_933 == 0)
            {
              localclass_20.jdField_field_16_of_type_JavaUtilArrayList.remove(j);
              j--;
            }
            else if (localclass_20.jdField_field_18_of_type_Int < localclass_20.jdField_field_15_of_type_ArrayOfClass_657.length)
            {
              localclass_20.jdField_field_15_of_type_ArrayOfClass_657[localclass_20.jdField_field_18_of_type_Int] = ((class_657)localclass_20.jdField_field_16_of_type_JavaUtilArrayList.get(j));
              localclass_20.jdField_field_18_of_type_Int += 1;
            }
          }
          localclass_20.jdField_field_14_of_type_Boolean = true;
        }
      }
      long l2;
      if ((l2 = System.currentTimeMillis() - l1) > 20L) {
        System.err.println("[CLIENT][SEGMENTPROVIDER] WARNING: cleanReturnedRequests of " + localclass_20.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController + " of size " + i + ", took " + l2 + " ms");
      }
      i();
      h();
      g();
      localclass_20 = this;
      if ((!this.jdField_field_15_of_type_Boolean) && (localclass_20.e()))
      {
        localclass_20.jdField_field_14_of_type_Class_749.c1();
        localclass_20.jdField_field_15_of_type_Boolean = true;
      }
      localclass_20 = this;
      if ((!this.jdField_field_17_of_type_Boolean) && (localclass_20.e()))
      {
        localclass_20.jdField_field_14_of_type_Class_749.d();
        localclass_20.jdField_field_17_of_type_Boolean = true;
      }
      if (class_949.field_1234.a4().equals("ALL_INFO"))
      {
        class_371.field_147 += this.jdField_field_14_of_type_JavaUtilCollection.size();
        class_371.field_148 += this.jdField_field_15_of_type_JavaUtilArrayList.size();
        class_371.field_149 += this.jdField_field_16_of_type_JavaUtilArrayList.size();
      }
      localclass_20 = this;
      if ((this.jdField_field_14_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty()) && (localclass_20.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty()) && (localclass_20.jdField_field_16_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty()) && (localclass_20.jdField_field_17_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty()))
      {
        if (localclass_20.jdField_field_17_of_type_Long < 0L)
        {
          localclass_20.jdField_field_17_of_type_Long = System.currentTimeMillis();
          return;
        }
        if (System.currentTimeMillis() - localclass_20.jdField_field_17_of_type_Long > 8000L) {
          localclass_20.jdField_field_17_of_type_Long = -1L;
        }
      }
      else if (localclass_20.jdField_field_17_of_type_Long > 0L)
      {
        localclass_20.jdField_field_17_of_type_Long = -1L;
      }
    }
  }
  
  public final class_749 a16()
  {
    return this.jdField_field_14_of_type_Class_749;
  }
  
  public final ObjectOpenHashSet c2()
  {
    return this.jdField_field_15_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet;
  }
  
  static
  {
    jdField_field_14_of_type_JavaUtilRandom = new Random();
    (class_20.jdField_field_14_of_type_Class_17 = new class_17()).start();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_20
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */