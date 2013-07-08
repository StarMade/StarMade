import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;

public class class_637
  implements class_80, class_953
{
  private short jdField_field_136_of_type_Short;
  private int jdField_field_136_of_type_Int;
  private Vector3f jdField_field_136_of_type_JavaxVecmathVector3f;
  private long jdField_field_136_of_type_Long = -1L;
  private int jdField_field_139_of_type_Int;
  private static int field_182 = 180000;
  private int field_183 = -1;
  private int field_184 = 0;
  private static int field_185 = 10;
  private static int field_186 = field_182 / field_185;
  private static class_48 jdField_field_136_of_type_Class_48 = new class_48();
  private static Vector3f jdField_field_139_of_type_JavaxVecmathVector3f = new Vector3f();
  private static class_48 jdField_field_139_of_type_Class_48 = new class_48();
  
  public class_637() {}
  
  public class_637(int paramInt1, short paramShort, int paramInt2, Vector3f paramVector3f)
  {
    this();
    a32(paramInt1, paramShort, paramInt2, paramVector3f);
  }
  
  public final void a32(int paramInt1, short paramShort, int paramInt2, Vector3f paramVector3f)
  {
    this.jdField_field_139_of_type_Int = paramInt1;
    this.jdField_field_136_of_type_Short = paramShort;
    this.jdField_field_136_of_type_Int = paramInt2;
    this.jdField_field_136_of_type_JavaxVecmathVector3f = paramVector3f;
  }
  
  public final boolean a33(long paramLong)
  {
    long l2 = paramLong;
    paramLong = this;
    long l1 = l2 - paramLong.jdField_field_136_of_type_Long;
    this.field_184 = ((int)(l1 / field_186));
    return l1 < field_182;
  }
  
  public final boolean a7()
  {
    boolean bool = this.field_183 != this.field_184;
    this.field_183 = this.field_184;
    return bool;
  }
  
  public final short a34()
  {
    return this.jdField_field_136_of_type_Short;
  }
  
  public final void a35(short paramShort)
  {
    this.jdField_field_136_of_type_Short = paramShort;
  }
  
  public final int a3()
  {
    return this.jdField_field_136_of_type_Int;
  }
  
  public final void a36(int paramInt)
  {
    this.jdField_field_136_of_type_Int = paramInt;
  }
  
  public final Vector3f a8()
  {
    return this.jdField_field_136_of_type_JavaxVecmathVector3f;
  }
  
  public final void a37(Vector3f paramVector3f)
  {
    this.jdField_field_136_of_type_JavaxVecmathVector3f = paramVector3f;
  }
  
  public final long a28()
  {
    return this.jdField_field_136_of_type_Long;
  }
  
  public final void a38(long paramLong)
  {
    this.jdField_field_136_of_type_Long = paramLong;
  }
  
  public final int b5()
  {
    return this.jdField_field_139_of_type_Int;
  }
  
  public final int a9(class_1380 paramclass_1380)
  {
    if (this.jdField_field_136_of_type_Short == 0)
    {
      System.err.println("[CLIENT] WARNING: TRIED TO DRAW FREE ITEM, BUT type == TYPE_NONE");
      return -1;
    }
    if (this.jdField_field_136_of_type_Short == -2)
    {
      if (paramclass_1380.b14().startsWith("build-icons-extra")) {
        return 0;
      }
      return -1;
    }
    int i = ElementKeyMap.getInfo(this.jdField_field_136_of_type_Short).getBuildIconNum() / 256;
    if (paramclass_1380.b14().startsWith("build-icons-" + class_41.b(i))) {
      return ElementKeyMap.getInfo(this.jdField_field_136_of_type_Short).getBuildIconNum() % 256;
    }
    return -1;
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_557, null, this.jdField_field_136_of_type_JavaxVecmathVector3f);
    class_69 localclass_692 = new class_69(class_79.field_550, null, Short.valueOf(this.jdField_field_136_of_type_Short));
    class_69 localclass_693 = new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_136_of_type_Int));
    return new class_69(class_79.field_561, null, new class_69[] { localclass_691, localclass_692, localclass_693, new class_69(class_79.field_548, null, null) });
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_JavaxVecmathVector3f = ((Vector3f)paramclass_69[0].a4());
    this.jdField_field_136_of_type_Short = ((Short)paramclass_69[1].a4()).shortValue();
    this.jdField_field_136_of_type_Int = ((Integer)paramclass_69[2].a4()).intValue();
  }
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public final float a10(long paramLong)
  {
    long l = paramLong - this.jdField_field_136_of_type_Long;
    return 0.001F + 0.009F * (1.0F - (float)l / field_182);
  }
  
  public final void b6(int paramInt)
  {
    this.jdField_field_139_of_type_Int = paramInt;
  }
  
  public final boolean a39(class_670 paramclass_670)
  {
    synchronized (paramclass_670.a78().getState().getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      Iterator localIterator = paramclass_670.a78().getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Object localObject1;
        if (((localObject1 = (Sendable)localIterator.next()) instanceof SegmentController)) {
          if ((localObject1 = (SegmentController)localObject1).getSectorId() == paramclass_670.a3())
          {
            jdField_field_139_of_type_JavaxVecmathVector3f.set(this.jdField_field_136_of_type_JavaxVecmathVector3f);
            ((SegmentController)localObject1).getWorldTransformInverse().transform(jdField_field_139_of_type_JavaxVecmathVector3f);
            if (((SegmentController)localObject1).getSegmentBuffer().a6().a7(jdField_field_139_of_type_JavaxVecmathVector3f))
            {
              int i = Math.round(jdField_field_139_of_type_JavaxVecmathVector3f.field_615) + 8;
              int j = Math.round(jdField_field_139_of_type_JavaxVecmathVector3f.field_616) + 8;
              int k = Math.round(jdField_field_139_of_type_JavaxVecmathVector3f.field_617) + 8;
              jdField_field_136_of_type_Class_48.b(i, j, k);
              class_796 localclass_796 = ((SegmentController)localObject1).getSegmentBuffer().a9(jdField_field_136_of_type_Class_48, false);
              System.err.println(localObject1 + " POINT INSIDE " + this + ":    " + this.jdField_field_136_of_type_JavaxVecmathVector3f + " -trans> " + jdField_field_136_of_type_Class_48 + ": " + localclass_796);
              if ((localclass_796 != null) && (localclass_796.a9() != 0))
              {
                jdField_field_139_of_type_Class_48.b1(jdField_field_136_of_type_Class_48);
                for (paramclass_670 = 1; paramclass_670 < 8; paramclass_670++)
                {
                  jdField_field_136_of_type_Class_48.b1(jdField_field_139_of_type_Class_48);
                  jdField_field_136_of_type_Class_48.field_476 -= paramclass_670;
                  if (((localclass_796 = ((SegmentController)localObject1).getSegmentBuffer().a9(jdField_field_136_of_type_Class_48, false)) == null) || (localclass_796.a9() == 0)) {
                    break;
                  }
                  jdField_field_136_of_type_Class_48.b1(jdField_field_139_of_type_Class_48);
                  jdField_field_136_of_type_Class_48.field_475 += paramclass_670;
                  if (((localclass_796 = ((SegmentController)localObject1).getSegmentBuffer().a9(jdField_field_136_of_type_Class_48, false)) == null) || (localclass_796.a9() == 0)) {
                    break;
                  }
                  jdField_field_136_of_type_Class_48.b1(jdField_field_139_of_type_Class_48);
                  jdField_field_136_of_type_Class_48.field_475 -= paramclass_670;
                  if (((localclass_796 = ((SegmentController)localObject1).getSegmentBuffer().a9(jdField_field_136_of_type_Class_48, false)) == null) || (localclass_796.a9() == 0)) {
                    break;
                  }
                  jdField_field_136_of_type_Class_48.b1(jdField_field_139_of_type_Class_48);
                  jdField_field_136_of_type_Class_48.field_477 += paramclass_670;
                  if (((localclass_796 = ((SegmentController)localObject1).getSegmentBuffer().a9(jdField_field_136_of_type_Class_48, false)) == null) || (localclass_796.a9() == 0)) {
                    break;
                  }
                  jdField_field_136_of_type_Class_48.b1(jdField_field_139_of_type_Class_48);
                  jdField_field_136_of_type_Class_48.field_477 -= paramclass_670;
                  if (((localclass_796 = ((SegmentController)localObject1).getSegmentBuffer().a9(jdField_field_136_of_type_Class_48, false)) == null) || (localclass_796.a9() == 0)) {
                    break;
                  }
                  jdField_field_136_of_type_Class_48.b1(jdField_field_139_of_type_Class_48);
                  jdField_field_136_of_type_Class_48.field_476 += paramclass_670;
                  if (((localclass_796 = ((SegmentController)localObject1).getSegmentBuffer().a9(jdField_field_136_of_type_Class_48, false)) == null) || (localclass_796.a9() == 0)) {
                    break;
                  }
                }
                jdField_field_136_of_type_Class_48.c1(jdField_field_139_of_type_Class_48);
                this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 += jdField_field_136_of_type_Class_48.field_475;
                this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 += jdField_field_136_of_type_Class_48.field_476;
                this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 += jdField_field_136_of_type_Class_48.field_477;
                this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 = (FastMath.a9(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615) + 0.5F);
                this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 = (FastMath.a9(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616) + 0.5F);
                this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 = (FastMath.a9(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617) + 0.5F);
                System.err.println("[ITEM][COLLISION] warping item out of collision " + this.jdField_field_136_of_type_JavaxVecmathVector3f);
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  public String toString()
  {
    return "(ITEM[" + this.jdField_field_139_of_type_Int + "]: type " + this.jdField_field_136_of_type_Short + "; #" + this.jdField_field_136_of_type_Int + "; " + this.jdField_field_136_of_type_JavaxVecmathVector3f + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_637
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */