import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public class class_657
  extends class_672
{
  private boolean jdField_field_187_of_type_Boolean;
  private boolean jdField_field_188_of_type_Boolean = false;
  private boolean jdField_field_189_of_type_Boolean = true;
  public long field_34;
  private CubeMeshBufferContainer jdField_field_34_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer;
  private class_400 jdField_field_34_of_type_Class_400;
  private class_400 jdField_field_35_of_type_Class_400;
  public Object field_34;
  public long field_35;
  public boolean field_34;
  private boolean jdField_field_190_of_type_Boolean;
  public class_663 field_34;
  public int field_34;
  public boolean field_35;
  private int jdField_field_35_of_type_Int;
  public long field_36;
  private static Vector3f jdField_field_34_of_type_JavaxVecmathVector3f = new Vector3f();
  private static Vector3f jdField_field_35_of_type_JavaxVecmathVector3f = new Vector3f();
  private static Matrix3f jdField_field_34_of_type_JavaxVecmathMatrix3f = new Matrix3f();
  private static Vector3f field_36 = new Vector3f();
  private static Vector3f jdField_field_187_of_type_JavaxVecmathVector3f = new Vector3f();
  private static Vector3f jdField_field_188_of_type_JavaxVecmathVector3f = new Vector3f();
  private Transform jdField_field_34_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Vector3f jdField_field_189_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_190_of_type_JavaxVecmathVector3f = new Vector3f();
  public float field_34;
  
  public class_657(SegmentController paramSegmentController)
  {
    super(paramSegmentController);
    this.jdField_field_34_of_type_JavaLangObject = new Object();
    this.jdField_field_34_of_type_Class_663 = class_663.field_933;
    this.jdField_field_34_of_type_Int = 0;
  }
  
  public final void a2()
  {
    synchronized (this.jdField_field_34_of_type_JavaLangObject)
    {
      class_400 localclass_400 = this.jdField_field_35_of_type_Class_400;
      if ((!field_191) && (this.jdField_field_35_of_type_Class_400 == this.jdField_field_34_of_type_Class_400)) {
        throw new AssertionError();
      }
      this.jdField_field_35_of_type_Class_400 = this.jdField_field_34_of_type_Class_400;
      SegmentDrawer.field_98.a4(localclass_400);
      this.jdField_field_34_of_type_Class_400 = null;
      return;
    }
  }
  
  public final void a11(boolean paramBoolean)
  {
    super.a11(paramBoolean);
    e(true);
    ((class_371)this.field_34.getState()).a27().a90().field_109 = true;
  }
  
  public final void b3()
  {
    synchronized (this.jdField_field_34_of_type_JavaLangObject)
    {
      class_657 localclass_6571 = this;
      synchronized (this.jdField_field_34_of_type_JavaLangObject)
      {
        SegmentDrawer.field_98.a4(localclass_6571.jdField_field_35_of_type_Class_400);
        localclass_6571.jdField_field_35_of_type_Class_400 = null;
      }
      class_657 localclass_6572 = this;
      synchronized (this.jdField_field_34_of_type_JavaLangObject)
      {
        SegmentDrawer.field_98.a4(localclass_6572.jdField_field_34_of_type_Class_400);
        localclass_6572.jdField_field_34_of_type_Class_400 = null;
      }
      return;
    }
  }
  
  public final void a30(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    Transform localTransform1;
    if (!(localTransform1 = this.field_34.getWorldTransformClient()).equals(this.jdField_field_34_of_type_ComBulletphysicsLinearmathTransform))
    {
      paramVector3f1.set(this.jdField_field_34_of_type_Class_48.field_475, this.jdField_field_34_of_type_Class_48.field_476, this.jdField_field_34_of_type_Class_48.field_477);
      paramVector3f2.set(paramVector3f1);
      paramVector3f2.field_615 += 8.0F;
      paramVector3f2.field_616 += 8.0F;
      paramVector3f2.field_617 += 8.0F;
      paramVector3f1.field_615 -= 8.0F;
      paramVector3f1.field_616 -= 8.0F;
      paramVector3f1.field_617 -= 8.0F;
      Vector3f localVector3f4 = this.jdField_field_190_of_type_JavaxVecmathVector3f;
      Vector3f localVector3f3 = this.jdField_field_189_of_type_JavaxVecmathVector3f;
      Transform localTransform2 = localTransform1;
      Vector3f localVector3f2 = paramVector3f2;
      Vector3f localVector3f1 = paramVector3f1;
      if ((!field_191) && (localVector3f1.field_615 > localVector3f2.field_615)) {
        throw new AssertionError();
      }
      if ((!field_191) && (localVector3f1.field_616 > localVector3f2.field_616)) {
        throw new AssertionError();
      }
      if ((!field_191) && (localVector3f1.field_617 > localVector3f2.field_617)) {
        throw new AssertionError();
      }
      jdField_field_34_of_type_JavaxVecmathVector3f.sub(localVector3f2, localVector3f1);
      jdField_field_34_of_type_JavaxVecmathVector3f.scale(0.5F);
      jdField_field_34_of_type_JavaxVecmathVector3f.field_615 += 0.0F;
      jdField_field_34_of_type_JavaxVecmathVector3f.field_616 += 0.0F;
      jdField_field_34_of_type_JavaxVecmathVector3f.field_617 += 0.0F;
      jdField_field_35_of_type_JavaxVecmathVector3f.add(localVector3f2, localVector3f1);
      jdField_field_35_of_type_JavaxVecmathVector3f.scale(0.5F);
      jdField_field_34_of_type_JavaxVecmathMatrix3f.set(localTransform2.basis);
      MatrixUtil.absolute(jdField_field_34_of_type_JavaxVecmathMatrix3f);
      field_36.set(jdField_field_35_of_type_JavaxVecmathVector3f);
      localTransform2.transform(field_36);
      jdField_field_34_of_type_JavaxVecmathMatrix3f.getRow(0, jdField_field_188_of_type_JavaxVecmathVector3f);
      jdField_field_187_of_type_JavaxVecmathVector3f.field_615 = jdField_field_188_of_type_JavaxVecmathVector3f.dot(jdField_field_34_of_type_JavaxVecmathVector3f);
      jdField_field_34_of_type_JavaxVecmathMatrix3f.getRow(1, jdField_field_188_of_type_JavaxVecmathVector3f);
      jdField_field_187_of_type_JavaxVecmathVector3f.field_616 = jdField_field_188_of_type_JavaxVecmathVector3f.dot(jdField_field_34_of_type_JavaxVecmathVector3f);
      jdField_field_34_of_type_JavaxVecmathMatrix3f.getRow(2, jdField_field_188_of_type_JavaxVecmathVector3f);
      jdField_field_187_of_type_JavaxVecmathVector3f.field_617 = jdField_field_188_of_type_JavaxVecmathVector3f.dot(jdField_field_34_of_type_JavaxVecmathVector3f);
      localVector3f3.sub(field_36, jdField_field_187_of_type_JavaxVecmathVector3f);
      localVector3f4.add(field_36, jdField_field_187_of_type_JavaxVecmathVector3f);
      this.jdField_field_34_of_type_ComBulletphysicsLinearmathTransform.set(localTransform1);
    }
    paramVector3f1.set(this.jdField_field_189_of_type_JavaxVecmathVector3f);
    paramVector3f2.set(this.jdField_field_190_of_type_JavaxVecmathVector3f);
  }
  
  public final CubeMeshBufferContainer a31()
  {
    if ((!field_191) && (this.jdField_field_34_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer != null)) {
      throw new AssertionError();
    }
    CubeMeshBufferContainer localCubeMeshBufferContainer = class_396.a();
    this.jdField_field_34_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer = localCubeMeshBufferContainer;
    return localCubeMeshBufferContainer;
  }
  
  public final CubeMeshBufferContainer b4()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer;
  }
  
  public final class_400 a32()
  {
    return this.jdField_field_35_of_type_Class_400;
  }
  
  public final class_400 b5()
  {
    return this.jdField_field_34_of_type_Class_400;
  }
  
  public final int a4()
  {
    return this.jdField_field_35_of_type_Int;
  }
  
  public final boolean a1()
  {
    return this.jdField_field_189_of_type_Boolean;
  }
  
  public final boolean b6()
  {
    return this.jdField_field_190_of_type_Boolean;
  }
  
  public final boolean c()
  {
    return this.jdField_field_188_of_type_Boolean;
  }
  
  public final boolean d1()
  {
    return this.jdField_field_187_of_type_Boolean;
  }
  
  public final void c1()
  {
    if (this.jdField_field_34_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer != null)
    {
      class_396.a2(this.jdField_field_34_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer);
      this.jdField_field_34_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer = null;
    }
  }
  
  public final void b7(boolean paramBoolean)
  {
    if (this.jdField_field_190_of_type_Boolean != paramBoolean) {
      this.field_34.getSegmentBuffer().a15(paramBoolean ? 1 : -1, this);
    }
    this.jdField_field_190_of_type_Boolean = paramBoolean;
  }
  
  public final void c2(boolean paramBoolean)
  {
    this.jdField_field_189_of_type_Boolean = paramBoolean;
  }
  
  public final void d2(boolean paramBoolean)
  {
    this.jdField_field_188_of_type_Boolean = paramBoolean;
  }
  
  public final void e(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.jdField_field_189_of_type_Boolean = true;
    }
    this.jdField_field_187_of_type_Boolean = paramBoolean;
  }
  
  public final void a33(class_400 paramclass_400)
  {
    this.jdField_field_34_of_type_Class_400 = paramclass_400;
  }
  
  public final void a5(int paramInt)
  {
    this.jdField_field_35_of_type_Int = paramInt;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_657
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */