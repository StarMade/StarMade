import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import java.io.File;
import java.io.PrintStream;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public final class class_76
{
  private static SoundSystem jdField_field_544_of_type_PaulscodeSoundSoundSystem;
  private class_77 jdField_field_544_of_type_Class_77 = new class_77();
  private class_77 field_545 = new class_77();
  private int jdField_field_544_of_type_Int;
  private static boolean jdField_field_544_of_type_Boolean = false;
  private final ObjectArrayList jdField_field_544_of_type_ComBulletphysicsUtilObjectArrayList = new ObjectArrayList();
  private final Vector3f jdField_field_544_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_76()
  {
    new class_77();
    this.jdField_field_544_of_type_Int = 0;
  }
  
  public final void a()
  {
    this.field_545.field_546 = false;
    if ((!jdField_field_544_of_type_Boolean) && (class_949.field_1181.b1()))
    {
      try
      {
        float f1 = a1();
        float f2 = ((Integer)class_949.field_1183.a4()).intValue() / 10.0F;
        a2(0.0F);
        a2(0.0F);
        SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
        SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
        SoundSystemConfig.setCodec("wav", CodecWav.class);
        jdField_field_544_of_type_PaulscodeSoundSoundSystem = new SoundSystem();
        a2(f1);
        a2(f2);
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        System.err.println("error linking with the LibraryJavaSound plug-in");
      }
      jdField_field_544_of_type_Boolean = true;
    }
  }
  
  private static float a1()
  {
    return ((Integer)class_949.field_1183.a4()).intValue() / 10.0F;
  }
  
  private static void a2(float paramFloat)
  {
    class_949.field_1183.a8(Integer.valueOf((int)(paramFloat * 10.0F)));
  }
  
  public static void b()
  {
    if (jdField_field_544_of_type_Boolean)
    {
      System.err.println("[AUDIO] Cleaning up sound system");
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.cleanup();
    }
  }
  
  public final void a3(String paramString, File paramFile)
  {
    this.jdField_field_544_of_type_Class_77.a(paramString, paramFile);
  }
  
  public final void a4(class_1382 paramclass_1382)
  {
    if ((!jdField_field_544_of_type_Boolean) || (a1() == 0.0F)) {
      return;
    }
    if (paramclass_1382 == null) {
      return;
    }
    jdField_field_544_of_type_PaulscodeSoundSoundSystem.setListenerPosition(paramclass_1382.getWorldTransform().origin.field_615, paramclass_1382.getWorldTransform().origin.field_616, paramclass_1382.getWorldTransform().origin.field_617);
    Vector3f localVector3f1 = GlUtil.c(new Vector3f(), paramclass_1382.getWorldTransform());
    Vector3f localVector3f2 = GlUtil.f(new Vector3f(), paramclass_1382.getWorldTransform());
    jdField_field_544_of_type_PaulscodeSoundSoundSystem.setListenerOrientation(localVector3f1.field_615, localVector3f1.field_616, localVector3f1.field_617, localVector3f2.field_615, localVector3f2.field_616, localVector3f2.field_617);
    if (((paramclass_1382 instanceof class_1421)) && ((paramclass_1382 = ((class_1421)paramclass_1382).getPhysicsDataContainer().getObject()) != null) && ((paramclass_1382 instanceof RigidBody)))
    {
      ((RigidBody)paramclass_1382).getLinearVelocity(this.jdField_field_544_of_type_JavaxVecmathVector3f);
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setListenerVelocity(this.jdField_field_544_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_544_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_544_of_type_JavaxVecmathVector3f.field_617);
    }
  }
  
  public final void c()
  {
    if ((!jdField_field_544_of_type_Boolean) || (a1() == 0.0F)) {
      return;
    }
    Iterator localIterator = this.jdField_field_544_of_type_ComBulletphysicsUtilObjectArrayList.iterator();
    while (localIterator.hasNext())
    {
      class_75 localclass_75 = (class_75)localIterator.next();
      b1(localclass_75, localclass_75.b(), localclass_75.b1(), 1.0F);
    }
  }
  
  private void b1(class_75 paramclass_75, String paramString, float paramFloat1, float paramFloat2)
  {
    if ((!jdField_field_544_of_type_Boolean) || (a1() == 0.0F)) {
      return;
    }
    class_61 localclass_61;
    if (((localclass_61 = this.jdField_field_544_of_type_Class_77.a1(paramString)) != null) && (paramFloat1 > 0.0F))
    {
      this.jdField_field_544_of_type_Int = ((this.jdField_field_544_of_type_Int + 1) % 256);
      paramString = ((class_80)paramclass_75).getUniqueIdentifier();
      float f = 16.0F;
      if (paramFloat1 > 1.0F) {
        f = 16.0F * paramFloat1;
      }
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.newSource(paramFloat1 > 1.0F, paramString, localclass_61.jdField_field_527_of_type_JavaNetURL, localclass_61.jdField_field_527_of_type_JavaLangString, true, paramclass_75.getWorldTransformClient().origin.field_615, paramclass_75.getWorldTransformClient().origin.field_616, paramclass_75.getWorldTransformClient().origin.field_617, 2, f);
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setPitch(paramString, paramFloat2);
      if (paramFloat1 > 1.0F) {
        paramFloat1 = 1.0F;
      }
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setVolume(paramString, paramFloat1 * a1());
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.play(paramString);
      return;
    }
    System.err.println("[SOUND] WARNING: sound not found: " + paramString);
  }
  
  public final void a5(class_75 paramclass_75, String paramString, float paramFloat1, float paramFloat2)
  {
    if ((!jdField_field_544_of_type_Boolean) || (a1() == 0.0F)) {
      return;
    }
    jdField_field_544_of_type_PaulscodeSoundSoundSystem.stop(paramclass_75.getUniqueIdentifier());
    b1(paramclass_75, paramString, paramFloat1, paramFloat2);
    if (!this.jdField_field_544_of_type_ComBulletphysicsUtilObjectArrayList.contains(paramclass_75)) {
      this.jdField_field_544_of_type_ComBulletphysicsUtilObjectArrayList.add(paramclass_75);
    }
  }
  
  public final void d()
  {
    Iterator localIterator = this.jdField_field_544_of_type_ComBulletphysicsUtilObjectArrayList.iterator();
    while (localIterator.hasNext())
    {
      class_75 localclass_75 = (class_75)localIterator.next();
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setPosition(localclass_75.getUniqueIdentifier(), localclass_75.getWorldTransformClient().origin.field_615, localclass_75.getWorldTransformClient().origin.field_616, localclass_75.getWorldTransformClient().origin.field_617);
      CollisionObject localCollisionObject;
      if (((localCollisionObject = localclass_75.getPhysicsDataContainer().getObject()) != null) && ((localCollisionObject instanceof RigidBody)))
      {
        ((RigidBody)localCollisionObject).getLinearVelocity(this.jdField_field_544_of_type_JavaxVecmathVector3f);
        jdField_field_544_of_type_PaulscodeSoundSoundSystem.setVelocity(localclass_75.getUniqueIdentifier(), this.jdField_field_544_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_544_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_544_of_type_JavaxVecmathVector3f.field_617);
      }
    }
  }
  
  public final void a6(String paramString)
  {
    if ((paramString = this.jdField_field_544_of_type_Class_77.a1(paramString)) != null)
    {
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.backgroundMusic("bm", paramString.jdField_field_527_of_type_JavaNetURL, paramString.jdField_field_527_of_type_JavaLangString, true);
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setVolume("bm", 0.18F);
    }
  }
  
  public final void a7(String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if ((!jdField_field_544_of_type_Boolean) || (a1() == 0.0F)) {
      return;
    }
    class_61 localclass_61;
    if (((localclass_61 = this.jdField_field_544_of_type_Class_77.a1(paramString)) != null) && (paramFloat4 > 0.0F))
    {
      this.jdField_field_544_of_type_Int = ((this.jdField_field_544_of_type_Int + 1) % 256);
      paramString = "sound_" + this.jdField_field_544_of_type_Int;
      float f = 16.0F;
      if (paramFloat4 > 1.0F) {
        f = 16.0F * paramFloat4;
      }
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.newSource(paramFloat4 > 1.0F, paramString, localclass_61.jdField_field_527_of_type_JavaNetURL, localclass_61.jdField_field_527_of_type_JavaLangString, false, paramFloat1, paramFloat2, paramFloat3, 2, f);
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setPitch(paramString, 1.0F);
      if (paramFloat4 > 1.0F) {
        paramFloat4 = 1.0F;
      }
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setVolume(paramString, paramFloat4 * a1());
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.play(paramString);
      return;
    }
    System.err.println("[SOUND] WARNING: sound not found: " + paramString);
  }
  
  public final void b2(String paramString)
  {
    if ((!jdField_field_544_of_type_Boolean) || (a1() == 0.0F)) {
      return;
    }
    if ((paramString = this.jdField_field_544_of_type_Class_77.a1(paramString)) != null)
    {
      this.jdField_field_544_of_type_Int = ((this.jdField_field_544_of_type_Int + 1) % 256);
      String str = "sound_" + this.jdField_field_544_of_type_Int;
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.newSource(false, str, paramString.jdField_field_527_of_type_JavaNetURL, paramString.jdField_field_527_of_type_JavaLangString, false, 0.0F, 0.0F, 0.0F, 0, 0.0F);
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setPitch(str, 1.0F);
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.setVolume(str, 0.25F * a1());
      jdField_field_544_of_type_PaulscodeSoundSoundSystem.play(str);
    }
  }
  
  public final ObjectArrayList a8()
  {
    return this.jdField_field_544_of_type_ComBulletphysicsUtilObjectArrayList;
  }
  
  public static boolean a9()
  {
    return jdField_field_544_of_type_Boolean;
  }
  
  public final void a10(Sendable paramSendable)
  {
    if ((!jdField_field_544_of_type_Boolean) || (a1() == 0.0F)) {
      return;
    }
    jdField_field_544_of_type_PaulscodeSoundSoundSystem.stop(((class_80)paramSendable).getUniqueIdentifier());
    this.jdField_field_544_of_type_ComBulletphysicsUtilObjectArrayList.remove(paramSendable);
  }
  
  public static void e()
  {
    if ((!jdField_field_544_of_type_Boolean) || (a1() == 0.0F)) {
      return;
    }
    jdField_field_544_of_type_PaulscodeSoundSoundSystem.stop("bm");
  }
  
  public final void a11(class_75 paramclass_75)
  {
    a5(paramclass_75, paramclass_75.a(), 1.0F, paramclass_75.b1());
  }
  
  public final void b3(class_75 paramclass_75)
  {
    a5(paramclass_75, paramclass_75.b(), paramclass_75.b1(), 1.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_76
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */