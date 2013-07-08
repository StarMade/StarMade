package org.schema.game.common.data.world;

import class_35;
import class_48;
import class_796;
import class_798;
import class_866;
import class_880;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.schine.network.IdGen;
import org.schema.schine.network.Identifiable;

public abstract class Segment
  implements Identifiable
{
  public float[] field_34;
  private int jdField_field_34_of_type_Int = 0;
  private int field_35;
  public SegmentData field_34;
  public final SegmentController field_34;
  public final class_48 field_34;
  public final class_48 field_35;
  public Object field_36;
  private class_35 jdField_field_34_of_type_Class_35;
  public float[] field_35;
  public float[] field_36;
  public short field_34;
  public boolean field_36;
  
  public static class_48 a9(int paramInt1, int paramInt2, int paramInt3, class_48 paramclass_48)
  {
    paramclass_48.b(ByteUtil.b(paramInt1), ByteUtil.b(paramInt2), ByteUtil.b(paramInt3));
    return paramclass_48;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    for (paramArrayOfString = -64; paramArrayOfString < 64; paramArrayOfString++)
    {
      class_35 localclass_35 = new class_35();
      int i = paramArrayOfString;
      localclass_35.b((byte)ByteUtil.d(0), (byte)ByteUtil.d(0), (byte)ByteUtil.d(i));
      System.err.println("0, 0, " + paramArrayOfString + " -> " + localclass_35);
    }
  }
  
  public Segment(SegmentController paramSegmentController)
  {
    this.jdField_field_34_of_type_ArrayOfFloat = new float[16];
    new class_48();
    this.jdField_field_36_of_type_JavaLangObject = new Object();
    this.jdField_field_34_of_type_Class_35 = new class_35();
    this.jdField_field_35_of_type_ArrayOfFloat = new float[3];
    this.jdField_field_36_of_type_ArrayOfFloat = new float[3];
    this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_35_of_type_Int = IdGen.getIndependentId();
    this.jdField_field_34_of_type_Class_48 = new class_48();
    this.jdField_field_35_of_type_Class_48 = new class_48();
  }
  
  public final boolean a10(short paramShort, class_35 paramclass_35, int paramInt, boolean paramBoolean)
  {
    boolean bool = false;
    if ((g()) && (this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData == null)) {
      this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a25().assignData(this);
    }
    SegmentData.getInfoIndex(paramclass_35);
    if (!this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.contains(paramclass_35))
    {
      this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer();
      paramBoolean = !paramBoolean ? 0 : ElementInformation.defaultActive(paramShort);
      this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.setInfoElement(paramclass_35, paramShort, (byte)paramInt, paramBoolean, true);
      bool = true;
    }
    return bool;
  }
  
  public abstract void a11(boolean paramBoolean);
  
  public boolean equals(Object paramObject)
  {
    return this.jdField_field_35_of_type_Int == ((Segment)paramObject).jdField_field_35_of_type_Int;
  }
  
  public final boolean a12(class_48 paramclass_48, byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return paramclass_48.a3(this.jdField_field_34_of_type_Class_48.field_475 + paramByte1, this.jdField_field_34_of_type_Class_48.field_476 + paramByte2, this.jdField_field_34_of_type_Class_48.field_477 + paramByte3);
  }
  
  public final class_48 a13(byte paramByte1, byte paramByte2, byte paramByte3, class_48 paramclass_48)
  {
    paramclass_48.field_475 = (this.jdField_field_34_of_type_Class_48.field_475 + paramByte1);
    paramclass_48.field_476 = (this.jdField_field_34_of_type_Class_48.field_476 + paramByte2);
    paramclass_48.field_477 = (this.jdField_field_34_of_type_Class_48.field_477 + paramByte3);
    return paramclass_48;
  }
  
  public final class_48 a14(class_35 paramclass_35, class_48 paramclass_48)
  {
    return a13(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455, paramclass_48);
  }
  
  public int getId()
  {
    return this.jdField_field_35_of_type_Int;
  }
  
  public final SegmentController a15()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController;
  }
  
  public final SegmentData a16()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData;
  }
  
  public final int b1()
  {
    return this.jdField_field_34_of_type_Int;
  }
  
  public final Vector3f a17(Vector3f paramVector3f)
  {
    paramVector3f.set(this.jdField_field_34_of_type_Class_48.field_475, this.jdField_field_34_of_type_Class_48.field_476, this.jdField_field_34_of_type_Class_48.field_477);
    this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis.transform(paramVector3f);
    paramVector3f.add(this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().origin);
    return paramVector3f;
  }
  
  public void a18(class_796 paramclass_796)
  {
    if (this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData == null)
    {
      System.err.println("[SEGMENT][" + this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "] RECEIVED SEG PIECE: SEGMENT DATA WAS NULL!!!! " + g() + " " + this.jdField_field_34_of_type_Int);
      this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a25().assignData(this);
    }
    int i;
    if (((i = this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.applySegmentData(paramclass_796.a6(this.jdField_field_34_of_type_Class_35), paramclass_796.a4())) == 0) && (this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (paramclass_796.a9() == 291))
    {
      System.err.println("[SREVER] FACTION BLOCK ADDED TO " + this + "; resetting faction !!!!!!!!!!!!!!");
      this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.setFactionId(0);
    }
    if ((i == 4) && (!this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (!this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()))
    {
      System.err.println("PIECE ACTIVE CHANGED");
      if ((this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController instanceof class_798)) {
        synchronized (this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getNeedsActiveUpdateClient())
        {
          this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getNeedsActiveUpdateClient().add(paramclass_796);
        }
      }
    }
    if (g())
    {
      if (this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData != null)
      {
        System.err.println("[SEGMENT][" + this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "] SEGMENT DATA SET TO NULL BECAUSE ITS NOT EMPTY!!!!");
        this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a20(this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData);
      }
    }
    else if ((paramclass_796.a9() == 122) && (paramclass_796.b2()))
    {
      int j = paramclass_796.a3();
      for (paramclass_796 = 0; paramclass_796 < 6; paramclass_796++) {
        if ((org.schema.game.common.data.element.Element.SIDE_FLAG[paramclass_796] & j) == org.schema.game.common.data.element.Element.SIDE_FLAG[paramclass_796]) {
          a21(org.schema.game.common.data.element.Element.DIRECTIONSb[paramclass_796]);
        }
      }
      paramclass_796 = new class_35();
      int k = 0;
      for (int m = 0; m < 6; m++) {
        if ((org.schema.game.common.data.element.Element.SIDE_FLAG[m] & j) == org.schema.game.common.data.element.Element.SIDE_FLAG[m])
        {
          k++;
          paramclass_796.a1(org.schema.game.common.data.element.Element.DIRECTIONSb[m]);
        }
      }
      if (k > 1) {
        a21(paramclass_796);
      }
    }
    a11(true);
  }
  
  public final boolean a19(class_48 paramclass_481, class_48 paramclass_482)
  {
    if ((paramclass_481.field_475 < this.jdField_field_34_of_type_Class_48.field_475) && (paramclass_481.field_476 < this.jdField_field_34_of_type_Class_48.field_476) && (paramclass_481.field_477 < this.jdField_field_34_of_type_Class_48.field_477) && (paramclass_482.field_475 > this.jdField_field_34_of_type_Class_48.field_475 + 16) && (paramclass_482.field_476 > this.jdField_field_34_of_type_Class_48.field_476 + 16) && (paramclass_482.field_477 > this.jdField_field_34_of_type_Class_48.field_477 + 16)) {
      return !g();
    }
    return this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getCollisionChecker().a4(this, paramclass_481, paramclass_482);
  }
  
  public int hashCode()
  {
    return this.jdField_field_34_of_type_Class_48.hashCode() + this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getId();
  }
  
  public final boolean g()
  {
    return ((this.jdField_field_34_of_type_Int == 0) && (!this.jdField_field_36_of_type_Boolean)) || (this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData == null);
  }
  
  public final boolean a20(class_35 paramclass_35, boolean paramBoolean)
  {
    boolean bool1 = paramBoolean;
    paramBoolean = paramclass_35;
    paramclass_35 = this;
    boolean bool2 = false;
    if (!paramclass_35.g())
    {
      bool2 = paramclass_35.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.contains(paramBoolean);
      paramclass_35.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.setInfoElement(paramBoolean, (short)0, bool1);
    }
    if ((paramclass_35.g()) && (paramclass_35.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData != null)) {
      paramclass_35.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a20(paramclass_35.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData);
    }
    return bool2;
  }
  
  private void a21(class_35 paramclass_35)
  {
    if ((paramclass_35 = this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentFromCache(this.jdField_field_34_of_type_Class_48.field_475 + paramclass_35.field_453, this.jdField_field_34_of_type_Class_48.field_476 + paramclass_35.field_454, this.jdField_field_34_of_type_Class_48.field_477 + paramclass_35.field_455)) != null) {
      paramclass_35.a11(true);
    }
  }
  
  public void setId(int paramInt)
  {
    this.jdField_field_35_of_type_Int = paramInt;
  }
  
  public static void d() {}
  
  public final void a22(SegmentData paramSegmentData)
  {
    synchronized (this.jdField_field_36_of_type_JavaLangObject)
    {
      this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData = paramSegmentData;
      return;
    }
  }
  
  public final void b2(int paramInt)
  {
    this.jdField_field_34_of_type_Int = paramInt;
  }
  
  public String toString()
  {
    return "DATA:" + this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getId() + ":" + this.jdField_field_34_of_type_Int;
  }
  
  public final void a23(float paramFloat1, Vector3f paramVector3f, float paramFloat2)
  {
    if (!g())
    {
      (paramVector3f = new class_48(paramVector3f)).c1(this.jdField_field_34_of_type_Class_48);
      this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.damage(paramFloat1, paramVector3f, paramFloat2);
    }
  }
  
  public final void a24(class_48 paramclass_48)
  {
    a25(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public final void a25(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jdField_field_34_of_type_Class_48.b(paramInt1, paramInt2, paramInt3);
    this.jdField_field_35_of_type_Class_48.b(ByteUtil.b(paramInt1), ByteUtil.b(paramInt2), ByteUtil.b(paramInt3));
  }
  
  static
  {
    Segment.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.world.Segment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */