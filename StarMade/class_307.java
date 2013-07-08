import com.bulletphysics.dynamics.DynamicsWorld;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.schema.game.client.view.cubes.CubeOptOptMesh;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GLException;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.graphicsengine.forms.Mesh;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.server.ServerProcessor;

public final class class_307
  extends class_971
{
  private class_371 jdField_field_98_of_type_Class_371;
  private class_1366 jdField_field_98_of_type_Class_1366;
  private class_1366 jdField_field_106_of_type_Class_1366;
  private class_925 jdField_field_106_of_type_Class_925;
  private class_1371 jdField_field_98_of_type_Class_1371;
  private class_925 field_108;
  private class_925 field_109;
  private class_1351 jdField_field_98_of_type_Class_1351;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private final class_1387 jdField_field_98_of_type_Class_1387 = new class_1387();
  private long jdField_field_98_of_type_Long;
  private String jdField_field_98_of_type_JavaLangString = "";
  
  public class_307(class_371 paramclass_371)
  {
    this.jdField_field_98_of_type_Class_371 = paramclass_371;
  }
  
  public final void a()
  {
    class_1376.a();
    this.jdField_field_98_of_type_Class_371.a27().a2();
    if (this.jdField_field_98_of_type_Class_925 != null) {
      this.jdField_field_98_of_type_Class_925.a();
    }
  }
  
  private void e()
  {
    int i = class_933.b();
    int j = class_933.a();
    if (this.jdField_field_98_of_type_Class_925 != null) {
      this.jdField_field_98_of_type_Class_925.a();
    }
    if (this.jdField_field_106_of_type_Class_925 != null) {
      this.jdField_field_106_of_type_Class_925.a();
    }
    if (this.field_109 != null) {
      this.field_109.a();
    }
    if (this.field_108 != null) {
      this.field_108.a();
    }
    this.jdField_field_98_of_type_Class_925 = new class_925(i, j);
    this.jdField_field_106_of_type_Class_925 = new class_925(i, j);
    this.jdField_field_106_of_type_Class_925.field_1150 = false;
    this.field_109 = new class_925(i, j);
    this.field_108 = new class_925(i / 2, j / 2);
    if (this.jdField_field_98_of_type_Class_1371 == null) {
      this.jdField_field_98_of_type_Class_1371 = new class_1371();
    }
  }
  
  public final void b()
  {
    this.jdField_field_98_of_type_Class_371.a27().j();
    int i = (class_949.field_1206.b1()) || (class_949.field_1240.b1()) ? 1 : 0;
    class_307 localclass_307;
    if ((!class_969.a2().a6()) || (!this.jdField_field_98_of_type_Class_371.isReady()))
    {
      localclass_307 = this;
      if (this.jdField_field_98_of_type_Class_930 == null) {
        localclass_307.jdField_field_98_of_type_Class_930 = new class_930(300, 300, null);
      }
      GlUtil.a12(5889);
      GlUtil.d1();
      GlUtil.b2();
      GLU.gluOrtho2D(0.0F, class_933.b(), class_933.a(), 0.0F);
      GlUtil.a12(5888);
      GlUtil.b2();
      class_1380 localclass_1380;
      if ((localclass_1380 = class_969.a2().a5("schine")) != null)
      {
        localclass_1380.a161(class_933.b() - localclass_1380.c5(), class_933.a() - localclass_1380.a57(), 0.0F);
        localclass_1380.b();
      }
      else
      {
        throw new ErrorDialogException("Failed to load shine Logo");
      }
      GL11.glDisable(2896);
      GL11.glBindTexture(3553, 0);
      GL11.glDisable(3042);
      GL11.glDisable(2929);
      GL11.glDisable(3553);
      GL11.glEnable(2903);
      GlUtil.a38(0.9F, 0.9F, 0.9F, 0.8F);
      GlUtil.a38(0.2F, 0.9F, 0.2F, 0.9F);
      class_1363.i();
      if (localclass_307.jdField_field_98_of_type_Class_930.b15() == null) {
        localclass_307.jdField_field_98_of_type_Class_930.b16(new ArrayList());
      }
      localclass_307.jdField_field_98_of_type_Class_930.b15().clear();
      localclass_307.jdField_field_98_of_type_Class_930.b15().add(class_969.a2().a3());
      localclass_307.jdField_field_98_of_type_Class_930.b15().add("");
      localclass_307.jdField_field_98_of_type_Class_930.b15().add("");
      localclass_307.jdField_field_98_of_type_Class_930.b15().addAll(class_969.a2().a2());
      localclass_307.jdField_field_98_of_type_Class_930.b();
      class_1363.h1();
      GL11.glDisable(2903);
      GL11.glBindTexture(3553, 0);
      GL11.glEnable(2929);
      GlUtil.a12(5889);
      GlUtil.c2();
      GlUtil.a12(5888);
      this.jdField_field_98_of_type_Boolean = true;
      return;
    }
    if (this.jdField_field_98_of_type_Boolean)
    {
      c();
      this.jdField_field_98_of_type_Boolean = false;
    }
    if (class_949.field_1201.b1())
    {
      System.err.println("RECOMPILING SHADERS");
      class_1376.c();
      try
      {
        class_949.field_1201.d();
      }
      catch (StateParameterNotFoundException localStateParameterNotFoundException)
      {
        localStateParameterNotFoundException;
      }
    }
    GL11.glDepthFunc(515);
    class_984.g3();
    GlUtil.a12(5889);
    GlUtil.b2();
    float f = class_933.b() / class_933.a();
    GlUtil.a15(class_969.field_1260, ((Float)class_949.field_1180.a4()).floatValue(), f, class_971.field_106, class_971.jdField_field_98_of_type_Float, true);
    GlUtil.a38(0.2F, 1.0F, 0.3F, 1.0F);
    if (class_949.field_1185.b1())
    {
      class_971.jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
      class_971.jdField_field_98_of_type_JavaNioFloatBuffer.put(class_971.jdField_field_98_of_type_ArrayOfFloat);
      class_971.jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
      GL11.glEnable(2912);
      GL11.glFogi(2917, 2049);
      GL11.glFog(2918, class_971.jdField_field_98_of_type_JavaNioFloatBuffer);
      GL11.glFogf(2914, 0.1F);
      GL11.glHint(3156, 4354);
    }
    GlUtil.a12(5888);
    GlUtil.b2();
    class_969.a1().c();
    this.jdField_field_98_of_type_Class_1387.a4();
    this.jdField_field_98_of_type_Class_371.a23();
    this.jdField_field_98_of_type_Class_1387.a2(class_971.jdField_field_98_of_type_Class_998.a83(), this.jdField_field_98_of_type_JavaxVecmathVector3f, false);
    if (localclass_307 != 0)
    {
      localclass_307 = this;
      if ((this.jdField_field_98_of_type_Class_925 == null) || (class_933.b1()))
      {
        localclass_307.e();
        localclass_307.g();
      }
      localclass_307.jdField_field_106_of_type_Class_925.d();
      GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
      GL11.glClear(16640);
      localclass_307.jdField_field_98_of_type_Class_371.a27().a96().e();
      localclass_307.jdField_field_106_of_type_Class_925.b1();
      localclass_307.field_109.d();
      GL11.glClear(16640);
      class_40.a("SCENE-DRAW");
      localclass_307.f();
      class_40.b("SCENE-DRAW");
      localclass_307.field_109.b1();
      class_1376.field_1565.field_1578 = localclass_307.jdField_field_98_of_type_Class_1371;
      localclass_307.field_108.d();
      GL11.glClear(16640);
      localclass_307.jdField_field_98_of_type_Class_1371.jdField_field_107_of_type_Int = localclass_307.field_109.a3();
      localclass_307.jdField_field_98_of_type_Class_1371.jdField_field_107_of_type_JavaxVecmathVector4f.set(1.0F, 1.0F, 1.0F, 1.0F);
      class_1376.field_1565.b();
      localclass_307.jdField_field_98_of_type_Class_371.a27().g();
      class_1376.field_1565.d();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      localclass_307.jdField_field_98_of_type_Class_1371.jdField_field_107_of_type_JavaxVecmathVector4f.set(0.0F, 0.0F, 0.0F, 1.0F);
      class_1376.field_1565.b();
      localclass_307.field_109.c1();
      class_1376.field_1565.d();
      GL11.glDisable(3042);
      localclass_307.field_108.b1();
      if (class_949.field_1240.b1())
      {
        if ((localclass_307 = localclass_307).jdField_field_98_of_type_Class_1366 == null) {
          try
          {
            localclass_307.jdField_field_98_of_type_Class_1366 = new class_1366(localclass_307.jdField_field_98_of_type_Class_925);
            localclass_307.jdField_field_106_of_type_Class_1366 = new class_1366(localclass_307.field_108);
            localclass_307.jdField_field_98_of_type_Class_1366.a2(localclass_307.field_108.a3());
            localclass_307.jdField_field_106_of_type_Class_1366.a2(localclass_307.field_108.a3());
            localclass_307.jdField_field_98_of_type_Class_1351 = new class_1351();
            localclass_307.jdField_field_98_of_type_Class_1351.a6(localclass_307.field_108.a3(), localclass_307.jdField_field_98_of_type_Class_925);
            class_1376.field_1572.field_1578 = localclass_307.jdField_field_98_of_type_Class_1351;
          }
          catch (GLException localGLException)
          {
            localGLException;
          }
        }
        localclass_307.jdField_field_98_of_type_Class_925.d();
        GL11.glClear(16640);
        localclass_307.jdField_field_98_of_type_Class_371.a27().a96().d();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        localclass_307.jdField_field_106_of_type_Class_925.c1();
        GL11.glDisable(3042);
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 771);
        localclass_307.field_109.c1();
        GL11.glDisable(3042);
        localclass_307.jdField_field_98_of_type_Class_1351.field_107.set(localclass_307.jdField_field_98_of_type_JavaxVecmathVector3f);
        localclass_307.jdField_field_98_of_type_Class_925.b1();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        localclass_307.jdField_field_98_of_type_Class_1366.b();
        GL11.glDisable(3042);
        localclass_307.jdField_field_98_of_type_Class_1351.a7();
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 1);
        localclass_307.jdField_field_98_of_type_Class_925.a2(class_1376.field_1572);
        GL11.glDisable(3042);
        GL11.glBlendFunc(770, 771);
        localclass_307.jdField_field_98_of_type_Class_371.a27().e();
      }
      else
      {
        localclass_307.jdField_field_98_of_type_Class_925.d();
        GL11.glClear(16640);
        localclass_307.jdField_field_98_of_type_Class_371.a27().a96().d();
        localclass_307.jdField_field_98_of_type_Class_371.a27().a96().e();
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 771);
        localclass_307.field_109.c1();
        localclass_307.jdField_field_98_of_type_Class_371.a27().e();
        GL11.glDisable(3042);
        localclass_307.jdField_field_98_of_type_Class_925.b1();
        localclass_307.jdField_field_98_of_type_Class_925.c1();
      }
    }
    else
    {
      localclass_307 = this;
      GL11.glClear(16640);
      localclass_307.jdField_field_98_of_type_Class_371.a27().a96().b();
      class_40.a("SCENE-DRAW");
      localclass_307.f();
      class_40.b("SCENE-DRAW");
      localclass_307.jdField_field_98_of_type_Class_371.a27().e();
    }
    class_40.a("INFO-DRAW");
    GlUtil.d1();
    d();
    GlUtil.c2();
    class_40.b("INFO-DRAW");
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    this.jdField_field_98_of_type_Class_371.a27().h1();
  }
  
  private void f()
  {
    GlUtil.d1();
    class_971.jdField_field_98_of_type_Class_998.b();
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    class_40.a("WORLD-DRAW");
    this.jdField_field_98_of_type_Class_371.a27().b();
    class_40.b("WORLD-DRAW");
    if (((this.jdField_field_98_of_type_Class_371.a23().jdField_field_98_of_type_Class_925 == null) || (!this.jdField_field_98_of_type_Class_371.a23().jdField_field_98_of_type_Class_925.field_1148)) && (class_949.field_1221.b1()))
    {
      GL11.glDisable(2896);
      this.jdField_field_98_of_type_Class_371.a19().getDynamicsWorld().debugDrawWorld();
      this.jdField_field_98_of_type_Class_371.a19().drawDebugObjects();
      GL11.glEnable(2929);
      class_1428.b();
      class_1428.c();
      class_1428.f();
      class_1428.d();
      class_1428.e();
    }
    jdField_field_98_of_type_Class_27.clear();
    GlUtil.c2();
  }
  
  public static class_998 a25()
  {
    return class_971.jdField_field_98_of_type_Class_998;
  }
  
  public final class_1387 a26()
  {
    return this.jdField_field_98_of_type_Class_1387;
  }
  
  private void g()
  {
    try
    {
      this.jdField_field_98_of_type_Class_925.e();
      this.jdField_field_106_of_type_Class_925.e();
      this.field_109.e();
      this.field_108.e();
      return;
    }
    catch (GLException localGLException)
    {
      localGLException;
    }
  }
  
  public final void c()
  {
    
    if ((class_949.field_1206.b1()) || (class_949.field_1240.b1()))
    {
      e();
      g();
    }
    new class_1394(new class_980(new ArrayList()));
    this.jdField_field_98_of_type_Class_371.a19().getDynamicsWorld().setDebugDrawer(new class_919());
    class_969.a2().a4("Box");
    Mesh.k();
    this.jdField_field_98_of_type_Class_371.a27().c();
  }
  
  public final void a1(class_941 paramclass_941)
  {
    System.currentTimeMillis();
    jdField_field_98_of_type_JavaUtilArrayList.add(0, "[FPS] " + paramclass_941.a1());
    jdField_field_98_of_type_JavaUtilArrayList.add(1, "[PING] " + this.jdField_field_98_of_type_Class_371.getPing());
    if (System.currentTimeMillis() - this.jdField_field_98_of_type_Long > 900L)
    {
      this.jdField_field_98_of_type_JavaLangString = ("[MEMORY] free: " + class_371.jdField_field_145_of_type_Long / 1024L + "kb, taken: " + class_371.jdField_field_146_of_type_Long / 1024L + "kb, total " + class_371.field_144 / 1024L + "kb");
      this.jdField_field_98_of_type_Long = System.currentTimeMillis();
    }
    jdField_field_98_of_type_JavaUtilArrayList.add(2, this.jdField_field_98_of_type_JavaLangString);
    jdField_field_98_of_type_JavaUtilArrayList.add(3, "[CL SEG A/F; D; [VRAM-save]] " + class_371.jdField_field_145_of_type_Int + " / " + class_371.jdField_field_146_of_type_Int + " (CMI: " + CubeOptOptMesh.field_89 + "); " + class_371.field_152 + "; [" + class_371.field_153 / 1024 / 1024 + " / " + class_371.field_154 / 1024 / 1024 + " mb] " + class_400.field_794);
    jdField_field_98_of_type_JavaUtilArrayList.add(4, "[SE SEG A/F; PGK; SEC] " + class_1041.jdField_field_145_of_type_Int + " / " + class_1041.jdField_field_146_of_type_Int + "; " + ServerProcessor.totalPackagesQueued + "; " + class_1041.field_153 + "/" + class_1041.field_152);
    class_371.field_152 = 0;
    class_400.field_794 = 0;
    CubeOptOptMesh.field_90 = 0;
    CubeOptOptMesh.field_92 = 0;
    Object localObject = class_40.field_462.entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      jdField_field_98_of_type_JavaUtilArrayList.add((String)localEntry.getKey() + ": " + (float)((Long)localEntry.getValue()).longValue());
    }
    if ((!this.jdField_field_98_of_type_Class_371.a27().a101().a24(paramclass_941)) && (class_969.a1() != null))
    {
      jdField_field_98_of_type_JavaUtilArrayList.add("cam-pos:   " + class_969.a1().getClass().getSimpleName() + ": " + class_969.a1().a83());
      localObject = paramclass_941;
      class_969.a1().a12((class_941)localObject);
    }
    this.jdField_field_98_of_type_Class_371.a27().a12(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_307
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */