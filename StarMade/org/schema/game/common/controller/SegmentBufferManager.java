package org.schema.game.common.controller;

import class_1041;
import class_371;
import class_48;
import class_756;
import class_796;
import class_880;
import class_884;
import class_886;
import class_888;
import class_890;
import class_988;
import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import javax.vecmath.Vector3f;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.StateInterface;

public class SegmentBufferManager
  implements class_886
{
  private SegmentController jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController;
  private class_988 jdField_field_208_of_type_Class_988;
  private Long2ObjectRBTreeMap jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap;
  private int jdField_field_208_of_type_Int;
  private int jdField_field_209_of_type_Int;
  private int field_210;
  private long jdField_field_208_of_type_Long;
  private final class_48 jdField_field_208_of_type_Class_48 = new class_48();
  private final class_48 jdField_field_209_of_type_Class_48 = new class_48();
  
  private static long a25(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((!jdField_field_208_of_type_Boolean) && (paramInt1 >= 1048576)) {
      throw new AssertionError();
    }
    if ((!jdField_field_208_of_type_Boolean) && (paramInt2 >= 1048576)) {
      throw new AssertionError();
    }
    if ((!jdField_field_208_of_type_Boolean) && (paramInt3 >= 1048576)) {
      throw new AssertionError();
    }
    long l1 = paramInt3 << 42 & 0x0;
    long l2 = paramInt2 << 21 & 0xFFE00000;
    return paramInt1 + l2 + l1;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.err.println("-8796093022208 MBytes needed if array 12");
    paramArrayOfString = new HashSet();
    for (int i = -1000; i < 100; i++) {
      for (int j = -100; j < 100; j++) {
        for (int k = -100; k < 100; k++)
        {
          long l = a25(k, j, i);
          if ((!jdField_field_208_of_type_Boolean) && (paramArrayOfString.contains(Long.valueOf(l)))) {
            throw new AssertionError();
          }
          paramArrayOfString.add(Long.valueOf(l));
        }
      }
    }
  }
  
  public SegmentBufferManager(SegmentController paramSegmentController)
  {
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_208_of_type_Class_988 = new class_988();
    this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap = new Long2ObjectRBTreeMap();
  }
  
  public final void a(Segment paramSegment)
  {
    synchronized (this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap)
    {
      a28(paramSegment.field_34, true).a(paramSegment);
      if ((!jdField_field_208_of_type_Boolean) && (a5(paramSegment.field_34) == null)) {
        throw new AssertionError();
      }
      return;
    }
  }
  
  public final int a1()
  {
    Long2ObjectRBTreeMap localLong2ObjectRBTreeMap1 = new Long2ObjectRBTreeMap();
    synchronized (this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap)
    {
      localLong2ObjectRBTreeMap1.putAll(this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap);
      this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.clear();
      this.jdField_field_208_of_type_Int = 0;
      this.jdField_field_209_of_type_Int = 0;
    }
    if ((!this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().isTerminating()) && (!this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().isTerminated())) {
      this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().execute(new class_890(this, localLong2ObjectRBTreeMap2));
    }
    return 0;
  }
  
  public final boolean a2(int paramInt1, int paramInt2, int paramInt3)
  {
    class_886 localclass_886;
    if ((localclass_886 = a27(paramInt1, paramInt2, paramInt3, false)) != null) {
      return localclass_886.a2(paramInt1, paramInt2, paramInt3);
    }
    return false;
  }
  
  public final boolean a3(class_48 paramclass_48)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramclass_48, false)) != null) {
      return localclass_886.a3(paramclass_48);
    }
    return false;
  }
  
  public final void c1()
  {
    this.jdField_field_208_of_type_Int = Math.max(0, this.jdField_field_208_of_type_Int - 1);
  }
  
  public final void d1()
  {
    this.jdField_field_209_of_type_Int = Math.max(0, this.jdField_field_209_of_type_Int - 1);
  }
  
  public final boolean b1(class_48 paramclass_48)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramclass_48, false)) == null) {
      return false;
    }
    return localclass_886.b1(paramclass_48);
  }
  
  public final Segment a4(int paramInt1, int paramInt2, int paramInt3)
  {
    class_886 localclass_886;
    if ((localclass_886 = a27(paramInt1, paramInt2, paramInt3, false)) != null) {
      return localclass_886.a4(paramInt1, paramInt2, paramInt3);
    }
    return null;
  }
  
  public final Segment a5(class_48 paramclass_48)
  {
    return a4(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public final class_988 a6()
  {
    return this.jdField_field_208_of_type_Class_988;
  }
  
  public final Long2ObjectRBTreeMap a26()
  {
    return this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap;
  }
  
  private class_886 a27(int paramInt1, int paramInt2, int paramInt3, boolean arg4)
  {
    int i = ByteUtil.c(ByteUtil.b(paramInt1));
    int j = ByteUtil.c(ByteUtil.b(paramInt2));
    int k = ByteUtil.c(ByteUtil.b(paramInt3));
    long l = a25(i, j, k);
    Object localObject = (class_886)this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(l);
    if ((??? != 0) && (localObject == null)) {
      synchronized (this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap)
      {
        if (!this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.containsKey(l))
        {
          paramInt1 = ByteUtil.c(ByteUtil.b(paramInt1)) << 3;
          paramInt2 = ByteUtil.c(ByteUtil.b(paramInt2)) << 3;
          paramInt3 = ByteUtil.c(ByteUtil.b(paramInt3)) << 3;
          localObject = new class_48(paramInt1, paramInt2, paramInt3);
          (paramInt1 = new class_48(paramInt1, paramInt2, paramInt3)).a(8, 8, 8);
          localObject = new class_884(this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController, (class_48)localObject, paramInt1, this);
          this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.put(l, localObject);
          if ((!jdField_field_208_of_type_Boolean) && (this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(l) == null)) {
            throw new AssertionError();
          }
        }
      }
    }
    return localObject;
  }
  
  private class_886 a28(class_48 paramclass_48, boolean paramBoolean)
  {
    return a27(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477, paramBoolean);
  }
  
  public final long a8()
  {
    return this.jdField_field_208_of_type_Long;
  }
  
  public final long a29(class_48 paramclass_481, class_48 paramclass_482)
  {
    this.jdField_field_208_of_type_Class_48.field_475 = ByteUtil.c(ByteUtil.b(paramclass_481.field_475));
    this.jdField_field_208_of_type_Class_48.field_476 = ByteUtil.c(ByteUtil.b(paramclass_481.field_476));
    this.jdField_field_208_of_type_Class_48.field_477 = ByteUtil.c(ByteUtil.b(paramclass_481.field_477));
    this.jdField_field_209_of_type_Class_48.field_475 = ByteUtil.c(ByteUtil.b(paramclass_482.field_475));
    this.jdField_field_209_of_type_Class_48.field_476 = ByteUtil.c(ByteUtil.b(paramclass_482.field_476));
    this.jdField_field_209_of_type_Class_48.field_477 = ByteUtil.c(ByteUtil.b(paramclass_482.field_477));
    this.jdField_field_209_of_type_Class_48.field_475 += 1;
    this.jdField_field_209_of_type_Class_48.field_476 += 1;
    this.jdField_field_209_of_type_Class_48.field_477 += 1;
    if (this.jdField_field_208_of_type_Class_48.field_475 == this.jdField_field_209_of_type_Class_48.field_475) {
      this.jdField_field_209_of_type_Class_48.field_475 += 1;
    }
    if (this.jdField_field_208_of_type_Class_48.field_476 == this.jdField_field_209_of_type_Class_48.field_476) {
      this.jdField_field_209_of_type_Class_48.field_476 += 1;
    }
    if (this.jdField_field_208_of_type_Class_48.field_477 == this.jdField_field_209_of_type_Class_48.field_477) {
      this.jdField_field_209_of_type_Class_48.field_477 += 1;
    }
    long l = 0L;
    for (paramclass_481 = this.jdField_field_208_of_type_Class_48.field_475; paramclass_481 < this.jdField_field_209_of_type_Class_48.field_475; paramclass_481 += 8) {
      for (paramclass_482 = this.jdField_field_208_of_type_Class_48.field_476; paramclass_482 < this.jdField_field_209_of_type_Class_48.field_476; paramclass_482 += 8) {
        for (int i = this.jdField_field_208_of_type_Class_48.field_477; i < this.jdField_field_209_of_type_Class_48.field_477; i += 8)
        {
          class_886 localclass_886;
          if ((localclass_886 = (class_886)this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(a25(paramclass_481, paramclass_482, i))) != null) {
            l = Math.max(l, localclass_886.a8());
          }
        }
      }
    }
    return l;
  }
  
  public final class_796 a9(class_48 paramclass_48, boolean paramBoolean)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramclass_48, paramBoolean)) == null) {
      return null;
    }
    return localclass_886.a9(paramclass_48, paramBoolean);
  }
  
  public final class_796 a10(class_48 paramclass_48, boolean paramBoolean, class_796 paramclass_796)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramclass_48, paramBoolean)) == null) {
      return null;
    }
    return localclass_886.a10(paramclass_48, paramBoolean, paramclass_796);
  }
  
  public final SegmentController a12()
  {
    return this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController;
  }
  
  public final int c()
  {
    return this.jdField_field_209_of_type_Int;
  }
  
  public final boolean a13(class_48 paramclass_48, class_888 paramclass_888)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramclass_48, false)) != null) {
      return localclass_886.a13(paramclass_48, paramclass_888);
    }
    return true;
  }
  
  public final boolean a14(int paramInt1, int paramInt2, int paramInt3, class_888 paramclass_888)
  {
    class_886 localclass_886;
    if ((localclass_886 = a27(paramInt1, paramInt2, paramInt3, false)) != null) {
      return localclass_886.a14(paramInt1, paramInt2, paramInt3, paramclass_888);
    }
    return true;
  }
  
  public final void e()
  {
    this.jdField_field_208_of_type_Int += 1;
  }
  
  public final void a15(int paramInt, Segment paramSegment)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramSegment.field_34, false)) != null) {
      localclass_886.a15(paramInt, paramSegment);
    }
  }
  
  public final class_756 a30(class_48 paramclass_48)
  {
    if (((paramclass_48 = a9(paramclass_48, false)) != null) && (paramclass_48.a9() != 0))
    {
      class_756 localclass_756;
      SegmentBufferManager localSegmentBufferManager = this;
      class_48 localclass_481 = paramclass_48;
      short s = localclass_481.a9();
      class_48 localclass_482 = null;
      (paramclass_48 = localclass_756 = new class_756()).jdField_field_1016_of_type_Short = s;
      if ((paramclass_48.jdField_field_1016_of_type_Short == 0) || (!ElementKeyMap.getInfo(paramclass_48.jdField_field_1016_of_type_Short).getControlledBy().isEmpty()))
      {
        paramclass_48.jdField_field_1016_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(localclass_481);
        localclass_481 = new class_48();
        localclass_482 = new class_48();
        while (!paramclass_48.jdField_field_1016_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.isEmpty())
        {
          class_796 localclass_7961;
          class_48 localclass_483 = (localclass_7961 = (class_796)paramclass_48.jdField_field_1016_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.dequeue()).a2(new class_48());
          paramclass_48.jdField_field_1016_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localclass_483);
          if (localclass_7961.a9() == paramclass_48.jdField_field_1016_of_type_Short)
          {
            paramclass_48.jdField_field_1016_of_type_JavaUtilArrayList.add(localclass_483);
            for (int i = 0; i < 6; i++)
            {
              localclass_7961.a2(localclass_481);
              localclass_481.a1(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
              class_796 localclass_7962;
              if (((localclass_7962 = localSegmentBufferManager.a10(localclass_481, false, new class_796())) == null) || (localclass_7962.a9() == 0) || (localclass_7962.a9() != paramclass_48.jdField_field_1016_of_type_Short))
              {
                paramclass_48.jdField_field_1016_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localclass_7962.a2(new class_48()));
              }
              else if (!paramclass_48.jdField_field_1016_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(localclass_7962.a2(localclass_482)))
              {
                paramclass_48.jdField_field_1016_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localclass_7962.a2(new class_48()));
                paramclass_48.jdField_field_1016_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(localclass_7962);
              }
            }
          }
        }
      }
      return localclass_756;
    }
    return null;
  }
  
  public final void f()
  {
    this.jdField_field_209_of_type_Int += 1;
  }
  
  public final boolean b2()
  {
    return this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.isEmpty();
  }
  
  public final boolean a17(class_888 paramclass_888, class_48 paramclass_481, class_48 paramclass_482)
  {
    class_48 localclass_48 = new class_48();
    for (int i = paramclass_481.field_475; i < paramclass_482.field_475; i += 16) {
      for (int j = paramclass_481.field_476; j < paramclass_482.field_476; j += 16) {
        for (int k = paramclass_481.field_477; k < paramclass_482.field_477; k += 16)
        {
          localclass_48.b(i, j, k);
          boolean bool;
          if (!(bool = a13(localclass_48, paramclass_888))) {
            return bool;
          }
        }
      }
    }
    return true;
  }
  
  public final boolean a18(class_888 paramclass_888, boolean paramBoolean)
  {
    boolean bool;
    if (paramBoolean) {
      synchronized (this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap)
      {
        Iterator localIterator = this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator();
        while (localIterator.hasNext()) {
          if (!(bool = ((class_886)localIterator.next()).a18(paramclass_888, paramBoolean))) {
            return bool;
          }
        }
      }
    }
    ??? = this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator();
    while (((Iterator)???).hasNext()) {
      if (!(bool = ((class_886)((Iterator)???).next()).a18(paramclass_888, paramBoolean))) {
        return bool;
      }
    }
    return true;
  }
  
  public final boolean b3(class_888 paramclass_888, boolean paramBoolean)
  {
    boolean bool;
    if (paramBoolean) {
      synchronized (this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap)
      {
        Iterator localIterator = this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator();
        while (localIterator.hasNext()) {
          if (!(bool = ((class_886)localIterator.next()).b3(paramclass_888, paramBoolean))) {
            return bool;
          }
        }
      }
    }
    ??? = this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator();
    while (((Iterator)???).hasNext()) {
      if (!(bool = ((class_886)((Iterator)???).next()).b3(paramclass_888, paramBoolean))) {
        return bool;
      }
    }
    return true;
  }
  
  public final boolean b4(class_888 paramclass_888, class_48 paramclass_481, class_48 paramclass_482)
  {
    for (int i = paramclass_481.field_475; i < paramclass_482.field_475; i += 16) {
      for (int j = paramclass_481.field_476; j < paramclass_482.field_476; j += 16) {
        for (int k = paramclass_481.field_477; k < paramclass_482.field_477; k += 16)
        {
          boolean bool;
          if (!(bool = a14(i, j, k, paramclass_888))) {
            return bool;
          }
        }
      }
    }
    return true;
  }
  
  public final void a21(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramSegment.field_34, false)) != null) {
      localclass_886.a21(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
    }
  }
  
  public final void b5(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramSegment.field_34, false)) != null) {
      localclass_886.b5(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
    }
  }
  
  public final void a23()
  {
    Iterator localIterator = null;
    synchronized (this.jdField_field_208_of_type_Class_988)
    {
      this.jdField_field_208_of_type_Class_988.a8();
      localIterator = this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator();
      while (localIterator.hasNext()) {
        ((class_886)localIterator.next()).a23();
      }
      return;
    }
  }
  
  public final void a24(SegmentData paramSegmentData)
  {
    class_886 localclass_886;
    if (((localclass_886 = a28(paramSegmentData.getSegment().field_34, false)) != null) && ((localclass_886.a6().field_1273.field_615 == this.jdField_field_208_of_type_Class_988.field_1273.field_615) || (localclass_886.a6().field_1273.field_616 == this.jdField_field_208_of_type_Class_988.field_1273.field_616) || (localclass_886.a6().field_1273.field_617 == this.jdField_field_208_of_type_Class_988.field_1273.field_617) || (localclass_886.a6().field_1274.field_615 == this.jdField_field_208_of_type_Class_988.field_1274.field_615) || (localclass_886.a6().field_1274.field_616 == this.jdField_field_208_of_type_Class_988.field_1274.field_616) || (localclass_886.a6().field_1274.field_617 == this.jdField_field_208_of_type_Class_988.field_1274.field_617))) {
      localclass_886.a24(paramSegmentData);
    }
  }
  
  public final int d()
  {
    return this.jdField_field_208_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.size();
  }
  
  public final void a31(class_988 paramclass_988)
  {
    this.jdField_field_208_of_type_Class_988.a3(paramclass_988.field_1273, paramclass_988.field_1274);
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.aabbRecalcFlag();
  }
  
  public final void b6(Segment paramSegment)
  {
    class_886 localclass_886;
    if ((localclass_886 = a28(paramSegment.field_34, false)) != null) {
      localclass_886.b6(paramSegment);
    }
  }
  
  public final void g()
  {
    this.jdField_field_208_of_type_Long = System.currentTimeMillis();
  }
  
  public final void b7()
  {
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a24();
    this.field_210 = this.jdField_field_209_of_type_Int;
    if (this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
    {
      class_1041.field_144 += this.field_210;
      return;
    }
    class_371.field_144 += this.field_210;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.SegmentBufferManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */