import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import java.awt.Color;
import java.awt.Font;
import java.io.PrintStream;
import java.util.List;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.controller.elements.weapon.WeaponCollectionManager;
import org.schema.game.common.controller.elements.weapon.WeaponUnit;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_881
  extends class_1363
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 field_90;
  private class_883 jdField_field_89_of_type_Class_883;
  private class_972 field_92;
  private class_972 field_93;
  private class_887 jdField_field_89_of_type_Class_887;
  private class_1433 jdField_field_89_of_type_Class_1433 = new class_1433(10.0F);
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_881(class_371 paramclass_371)
  {
    super(paramclass_371);
    this.jdField_field_89_of_type_Class_887 = new class_887(paramclass_371);
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("hud-sides-left-gui-"), paramclass_371);
    this.field_90 = new class_972(class_969.a2().a5("hud-sides-right-gui-"), paramclass_371);
    this.field_92 = new class_972(class_969.a2().a5("crosshair-c-gui-"), paramclass_371);
    this.field_93 = new class_972(class_969.a2().a5("crosshair-simple-c-gui-"), paramclass_371);
    this.jdField_field_89_of_type_Class_883 = new class_883(paramclass_371);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (k1()) {
      d();
    }
    GlUtil.d1();
    Object localObject3;
    if ((b3()) && ((class_969.a1() instanceof class_191)))
    {
      Object localObject1 = this;
      Object localObject2 = null;
      localObject1 = a28().a51().field_5 ? ((class_881)localObject1).a28().a51().a40().a7().a15() : ((class_881)localObject1).a28().a53().a40().a7().a15();
      localObject2 = (class_191)class_969.a1();
      Object localObject4 = new Vector3f(((class_191)localObject2).b9());
      localObject2 = new Vector3f(((class_191)localObject2).a83());
      Object localObject5 = a126();
      float f4 = 0.0F;
      if (localObject5 != null) {
        for (int i = 0; i < ((WeaponCollectionManager)localObject5).getCollection().size(); i++) {
          f4 = Math.max(f4, ((WeaponUnit)((WeaponCollectionManager)localObject5).getCollection().get(i)).getDistance());
        }
      } else {
        f4 = 64.0F;
      }
      ((Vector3f)localObject4).scale(f4);
      ((Vector3f)localObject2).add((Tuple3f)localObject4);
      Object localObject6 = ((class_371)a24()).a23().a26().a2((Vector3f)localObject2, new Vector3f(), false);
      this.field_93.a83().set((Tuple3f)localObject6);
      this.field_93.a83().field_615 += 3.0F;
      this.field_93.a83().field_616 -= 3.0F;
      localObject4 = localObject1;
      float f1 = f4;
      localObject1 = this;
      localObject5 = (class_191)class_969.a1();
      Vector3f localVector3f = new Vector3f(class_969.a1().a83());
      localObject5 = new Vector3f(((class_191)localObject5).b9());
      localObject6 = (PhysicsExt)((class_1407)((class_881)localObject1).a24()).a();
      ((Vector3f)localObject5).scale(f1);
      ((Vector3f)localObject5).add(localVector3f);
      localObject3 = null;
      if (((PhysicsExt)localObject6).testRayCollisionPoint(localVector3f, (Vector3f)localObject5, false, (SegmentController)localObject4, null, false, null, false).hasHit()) {
        ((class_881)localObject1).field_93.a147().a63().set(0.0F, 1.0F, 0.0F, 1.0F);
      } else {
        ((class_881)localObject1).field_93.a147().a63().set(1.0F, 1.0F, 1.0F, 1.0F);
      }
      ((class_881)localObject1).field_93.b();
      ((class_881)localObject1).field_93.a147().a63().set(1.0F, 1.0F, 1.0F, 1.0F);
      localObject1 = this;
      this.jdField_field_89_of_type_Class_972.h3(17);
      ((class_881)localObject1).jdField_field_89_of_type_Class_972.b28(0.8F, 0.8F, 0.8F);
      ((class_881)localObject1).jdField_field_89_of_type_Class_972.b();
      ((class_881)localObject1).field_90.h3(18);
      ((class_881)localObject1).field_90.b28(0.8F, 0.8F, 0.8F);
      ((class_881)localObject1).field_90.b();
      ((class_881)localObject1).jdField_field_89_of_type_Class_887.h3(6);
      ((class_881)localObject1).jdField_field_89_of_type_Class_887.a83().field_616 += 64.0F;
      ((class_881)localObject1).jdField_field_89_of_type_Class_887.b();
      (localObject3 = localObject1).field_92.h3(48);
      localObject3.field_92.a83().field_615 += localObject3.field_92.b1() / 2.0F;
      localObject3.field_92.a83().field_616 += localObject3.field_92.a3() / 2.0F;
      if (localObject3.a125().a1())
      {
        if (((class_371)localObject3.a24()).a20().a120() != null)
        {
          localObject3.field_92.a147().a63().set(localObject3.jdField_field_89_of_type_Class_1433.a1(), 1.0F, localObject3.jdField_field_89_of_type_Class_1433.a1(), 1.0F);
          localObject3.field_92.a147().b28(1.0F, 1.0F, 1.0F);
          localObject3.jdField_field_89_of_type_Boolean = true;
        }
        else
        {
          if (localObject3.a125().a43() != null)
          {
            localObject3.a125();
            float f2 = Math.min(1.0F, localObject3.a125().b4() / class_332.a39());
            float f3 = 1.0F - f2;
            localObject3.field_92.a147().b28(f3 + 1.0F, f3 + 1.0F, f3 + 1.0F);
            localObject3.field_92.a147().a63().set(f3, f2, 0.0F, 1.0F);
            localObject3.field_92.b27(f2 * 360.0F);
          }
          else
          {
            localObject3.field_92.a147().a63().set(1.0F, 1.0F, 1.0F, 1.0F);
            localObject3.field_92.a147().b28(2.0F, 2.0F, 2.0F);
            localObject3.field_92.b27(0.0F);
          }
          localObject3.jdField_field_89_of_type_Class_1433.a2();
          localObject3.jdField_field_89_of_type_Boolean = false;
        }
      }
      else
      {
        localObject3.field_92.a147().a63().set(1.0F, 1.0F, 1.0F, 1.0F);
        localObject3.field_92.a147().b28(1.0F, 1.0F, 1.0F);
        localObject3.field_92.b27(0.0F);
        localObject3.jdField_field_89_of_type_Boolean = false;
      }
      localObject3.field_92.b();
    }
    else
    {
      if ((b3()) && (!(class_969.a1() instanceof class_191))) {
        System.err.println("WARNING: HudBasic has wrong camera: " + class_969.a1().getClass());
      }
      this.field_93.h3(48);
      this.field_93.a83().field_615 += this.field_93.b1() / 2.0F;
      this.field_93.a83().field_616 += this.field_93.a3() / 2.0F;
      this.field_93.b();
      localObject3 = null;
      if (((class_371)a24()).a14().field_4.field_4.field_4.a52().field_6)
      {
        this.jdField_field_89_of_type_Class_887.h3(6);
        this.jdField_field_89_of_type_Class_887.a83().field_616 += 64.0F;
        this.jdField_field_89_of_type_Class_887.b();
      }
    }
    this.jdField_field_89_of_type_Class_883.b();
    GlUtil.c2();
  }
  
  private class_332 a125()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4.a51().a45().field_4;
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  private class_443 a28()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
  }
  
  private WeaponCollectionManager a126()
  {
    Object localObject;
    class_747 localclass_747;
    if (((localclass_747 = (localObject = (class_371)a24()).a25()) != null) && (((class_371)localObject).a20().a131(localclass_747))) {
      try
      {
        localObject = ((class_371)localObject).a20().a128(localclass_747).a17(((class_371)localObject).a20().d1());
        for (int i = 0; i < localclass_747.a112().getWeapon().getCollectionManagers().size(); i++) {
          if (((WeaponCollectionManager)localclass_747.a112().getWeapon().getCollectionManagers().get(i)).equalsControllerPos((class_48)localObject)) {
            return (WeaponCollectionManager)localclass_747.a112().getWeapon().getCollectionManagers().get(i);
          }
        }
      }
      catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException)
      {
        localShipConfigurationNotFoundException;
      }
    }
    return null;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  private boolean b3()
  {
    return (a28().a51().a45().field_4.field_6) || (a28().a53().a67().field_6);
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    this.field_90.c();
    this.field_92.c();
    this.field_92.a147().c6(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
    this.field_93.c();
    this.field_93.a147().c6(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
    this.jdField_field_89_of_type_Class_883.c();
    this.jdField_field_89_of_type_Class_887.c();
    Object localObject = new Font("Arial", 0, 20);
    (localObject = new UnicodeFont((Font)localObject)).getEffects().add(new OutlineEffect(4, Color.black));
    ((UnicodeFont)localObject).getEffects().add(new ColorEffect(Color.green.darker()));
    ((UnicodeFont)localObject).addAsciiGlyphs();
    try
    {
      ((UnicodeFont)localObject).loadGlyphs();
      return;
    }
    catch (SlickException localSlickException)
    {
      localSlickException;
    }
  }
  
  public final void e()
  {
    this.jdField_field_89_of_type_Class_883.f();
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Class_883.a12(paramclass_941);
    if (this.jdField_field_89_of_type_Boolean) {
      this.jdField_field_89_of_type_Class_1433.a(paramclass_941);
    }
  }
  
  public final class_883 a127()
  {
    return this.jdField_field_89_of_type_Class_883;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_881
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */