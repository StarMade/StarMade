package org.schema.game.client.view.space;

import class_1376;
import class_1377;
import class_1395;
import class_73;
import class_814;
import class_818;
import class_949;
import class_965;
import class_969;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Random;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.ResourceException;
import org.schema.schine.graphicsengine.forms.Mesh;

public class StarSky
  implements class_965
{
  private IntBuffer jdField_field_98_of_type_JavaNioIntBuffer;
  private IntBuffer jdField_field_106_of_type_JavaNioIntBuffer;
  private FloatBuffer jdField_field_98_of_type_JavaNioFloatBuffer;
  private FloatBuffer jdField_field_106_of_type_JavaNioFloatBuffer;
  private int jdField_field_98_of_type_Int = 2000;
  private boolean jdField_field_98_of_type_Boolean = true;
  private class_818 jdField_field_98_of_type_Class_818;
  private class_814 jdField_field_98_of_type_Class_814;
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new StarSky();
    try
    {
      paramArrayOfString.d();
      return;
    }
    catch (ResourceException localResourceException)
    {
      localResourceException;
    }
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    StarSky localStarSky = this;
    if (this.jdField_field_98_of_type_Boolean) {
      localStarSky.c();
    }
    Vector3f localVector3f = class_969.a1().a83();
    GL11.glDisable(2929);
    GlUtil.d1();
    Mesh localMesh = (Mesh)class_969.a2().a4("Box").a152().get(0);
    GlUtil.c4(localVector3f.field_615, localVector3f.field_616, localVector3f.field_617);
    GlUtil.b5(10.0F, 10.0F, 10.0F);
    GL11.glDisable(2884);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, 0);
    GL11.glBindTexture(34067, localStarSky.jdField_field_98_of_type_Class_814.a1().field_1592);
    class_1376.field_1560.field_1578 = localStarSky.jdField_field_98_of_type_Class_814;
    class_1376.field_1560.b();
    localMesh.e();
    class_1376.field_1560.d();
    GL11.glBindTexture(34067, 0);
    GL11.glEnable(2884);
    GlUtil.c2();
    localStarSky = this;
    if (class_949.field_1230.b1())
    {
      if (localStarSky.jdField_field_98_of_type_Boolean) {
        localStarSky.c();
      }
      GlUtil.d1();
      localVector3f = class_969.a1().a83();
      GL11.glDisable(2929);
      GL11.glTranslatef(localVector3f.field_615, localVector3f.field_616, localVector3f.field_617);
      class_1376.field_1547.field_1578 = localStarSky.jdField_field_98_of_type_Class_818;
      class_1376.field_1547.b();
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glEnableClientState(32884);
      GL15.glBindBuffer(34962, localStarSky.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
      GL11.glVertexPointer(3, 5126, 0, 0L);
      GL11.glEnableClientState(32886);
      GL15.glBindBuffer(34962, localStarSky.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
      GL11.glColorPointer(3, 5126, 0, 0L);
      GL11.glDisable(2884);
      GL11.glDrawArrays(0, 0, localStarSky.jdField_field_98_of_type_Int);
      GL11.glEnable(2884);
      GL15.glBindBuffer(34962, 0);
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32886);
      GL11.glEnable(2929);
      GlUtil.c2();
      GL11.glBindTexture(3552, 0);
      GL11.glDisable(3552);
      GL11.glDisable(3553);
      GL11.glDisable(3042);
      class_1376.field_1547.d();
    }
  }
  
  private void d()
  {
    this.jdField_field_98_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(this.jdField_field_98_of_type_Int * 3);
    this.jdField_field_106_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(this.jdField_field_98_of_type_Int * 3);
    Vector3f[] arrayOfVector3f1;
    Vector3f[] arrayOfVector3f2 = arrayOfVector3f1 = new Vector3f[this.jdField_field_98_of_type_Int];
    float f1 = 3.141593F * (3.0F - FastMath.l(5.0F));
    float f2 = 2.0F / arrayOfVector3f2.length;
    float f3 = 0.0F;
    for (int j = 0; j < arrayOfVector3f2.length; j++)
    {
      int k = 750 + FastMath.field_1862.nextInt(1000);
      k = Math.random() > 0.5D ? -k : k;
      arrayOfVector3f2[j] = new Vector3f();
      float f4 = j * f2 - 1.0F + f2 / 2.0F;
      float f5 = FastMath.l(1.0F - f4 * f4);
      float f6 = f3;
      f3 = (float)(f3 + (0.5D + Math.random()) * f1);
      arrayOfVector3f2[j].set(FastMath.d(f6) * f5, f4, FastMath.h(f6) * f5);
      arrayOfVector3f2[j].normalize();
      arrayOfVector3f2[j].scale(k);
    }
    for (int i = 0; i < this.jdField_field_98_of_type_Int; i++)
    {
      Vector3f localVector3f = arrayOfVector3f1[i];
      this.jdField_field_98_of_type_JavaNioFloatBuffer.put(localVector3f.field_615);
      this.jdField_field_98_of_type_JavaNioFloatBuffer.put(localVector3f.field_616);
      this.jdField_field_98_of_type_JavaNioFloatBuffer.put(localVector3f.field_617);
      if (Math.random() >= 0.8D)
      {
        this.jdField_field_106_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
        this.jdField_field_106_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
        this.jdField_field_106_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
      }
      else
      {
        this.jdField_field_106_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
        this.jdField_field_106_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
        this.jdField_field_106_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
      }
    }
  }
  
  public final void c()
  {
    try
    {
      d();
    }
    catch (ResourceException localResourceException)
    {
      localResourceException;
    }
    this.jdField_field_98_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
    GL15.glGenBuffers(this.jdField_field_98_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, this.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
    this.jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
    GL15.glBufferData(34962, this.jdField_field_98_of_type_JavaNioFloatBuffer, 35044);
    GL15.glBindBuffer(34962, 0);
    this.jdField_field_106_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
    GL15.glGenBuffers(this.jdField_field_106_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, this.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
    this.jdField_field_106_of_type_JavaNioFloatBuffer.rewind();
    GL15.glBufferData(34962, this.jdField_field_106_of_type_JavaNioFloatBuffer, 35044);
    GL15.glBindBuffer(34962, 0);
    this.jdField_field_98_of_type_Class_818 = new class_818();
    this.jdField_field_98_of_type_Class_814 = new class_814();
    this.jdField_field_98_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.client.view.space.StarSky
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */