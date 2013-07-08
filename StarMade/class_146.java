import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerActivityInterface;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.ManagerModule;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.ManagerReloadInterface;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;

public final class class_146
  extends class_1363
{
  private class_832[] jdField_field_89_of_type_ArrayOfClass_832;
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 field_90;
  private int jdField_field_89_of_type_Int;
  private class_130 jdField_field_89_of_type_Class_130;
  private class_48 jdField_field_89_of_type_Class_48;
  private class_796 jdField_field_89_of_type_Class_796;
  
  public class_146(class_1380 paramclass_1380, class_371 paramclass_371)
  {
    super(paramclass_371);
    new class_48();
    new HashMap();
    this.jdField_field_89_of_type_Int = -1;
    this.jdField_field_89_of_type_Class_48 = new class_48();
    this.jdField_field_89_of_type_Class_796 = new class_796();
    this.jdField_field_89_of_type_ArrayOfClass_832 = new class_832[10];
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfClass_832.length; i++)
    {
      this.jdField_field_89_of_type_ArrayOfClass_832[i] = new class_832(class_969.a2().a5("build-icons-00-16x16-gui-"), paramclass_371);
      this.jdField_field_89_of_type_ArrayOfClass_832[i].a83().field_616 = 21.0F;
      this.jdField_field_89_of_type_ArrayOfClass_832[i].a83().field_615 = (159.0F + i * 70.0F);
    }
    this.field_90 = new class_972(class_969.a2().a5("tools-16x16-gui-"), paramclass_371);
    this.jdField_field_89_of_type_Class_972 = new class_972(paramclass_1380, paramclass_371);
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 64.0F, 64.0F, new Vector4f(1.0F, 1.0F, 1.0F, 0.18F));
  }
  
  private void a38(SegmentController paramSegmentController, class_48 paramclass_48, int paramInt)
  {
    class_796 localclass_796;
    if (((localclass_796 = paramSegmentController.getSegmentBuffer().a10(paramclass_48, false, this.jdField_field_89_of_type_Class_796)) != null) && (localclass_796.a9() != 0))
    {
      int i = ElementKeyMap.getInfo(localclass_796.a9()).getBuildIconNum();
      localclass_796.a9();
      class_832.e();
      this.jdField_field_89_of_type_ArrayOfClass_832[paramInt].b21(false);
      this.jdField_field_89_of_type_ArrayOfClass_832[paramInt].a83().field_616 = 21.5F;
      this.jdField_field_89_of_type_ArrayOfClass_832[paramInt].a83().field_615 = (159.0F + paramInt * 69.800003F);
      this.jdField_field_89_of_type_ArrayOfClass_832[paramInt].a_2(i);
      this.jdField_field_89_of_type_ArrayOfClass_832[paramInt].b();
      ManagerModuleCollection localManagerModuleCollection;
      if (((localManagerModuleCollection = (ManagerModuleCollection)(paramSegmentController = ((class_798)paramSegmentController).a()).getModulesControllerMap().get(Short.valueOf(localclass_796.a9()))) != null) && (!localManagerModuleCollection.getCollectionManagers().isEmpty()))
      {
        if ((localManagerModuleCollection.getElementManager() instanceof ManagerReloadInterface))
        {
          this.field_90.a83().field_616 = 21.5F;
          this.field_90.a83().field_615 = (159.0F + paramInt * 69.800003F);
          ((ManagerReloadInterface)localManagerModuleCollection.getElementManager()).drawReloads(this.field_90, paramclass_48);
        }
        if (((localManagerModuleCollection.getElementManager() instanceof ManagerActivityInterface)) && (((ManagerActivityInterface)localManagerModuleCollection.getElementManager()).isActive()))
        {
          this.field_90.a_2(10);
          this.field_90.a83().field_616 = 21.5F;
          this.field_90.a83().field_615 = (159.0F + paramInt * 69.800003F);
          this.field_90.b();
        }
      }
      if (((paramSegmentController = (ManagerModule)paramSegmentController.getModulesMap().get(Short.valueOf(localclass_796.a9()))) != null) && ((paramSegmentController.getElementManager() instanceof ManagerActivityInterface)))
      {
        if ((paramSegmentController.getElementManager() instanceof ManagerReloadInterface))
        {
          this.field_90.a83().field_616 = 21.5F;
          this.field_90.a83().field_615 = (159.0F + paramInt * 69.800003F);
          ((ManagerReloadInterface)paramSegmentController.getElementManager()).drawReloads(this.field_90, paramclass_48);
        }
        if (((ManagerActivityInterface)paramSegmentController.getElementManager()).isActive())
        {
          this.field_90.a_2(10);
          this.field_90.a83().field_616 = 21.5F;
          this.field_90.a83().field_615 = (159.0F + paramInt * 69.800003F);
          this.field_90.b();
        }
      }
    }
  }
  
  public final void a2() {}
  
  public final void b()
  {
    class_48 localclass_48 = null;
    class_332 localclass_332 = ((class_371)a24()).a14().field_4.field_4.field_4.a51().a45().field_4;
    Object localObject3 = (class_371)a24();
    if ((localclass_332 == null) || (localclass_332.a42() == null) || (!((class_371)localObject3).a20().a131(localclass_332.a42())))
    {
      System.err.println("[WEAPON-SIDE-BAR] NO CONTROLLER DRAW");
      return;
    }
    GlUtil.d1();
    localObject3 = localclass_332.a42();
    r();
    this.jdField_field_89_of_type_Class_972.b();
    if (localclass_332.a41(this.jdField_field_89_of_type_Class_48).equals(class_747.field_136)) {
      try
      {
        float f1 = 0.0F;
        localObject3 = localObject3;
        Object localObject1 = this;
        Object localObject4 = ((class_371)a24()).a20();
        try
        {
          class_359 localclass_359 = ((class_748)localObject4).a128((SegmentController)localObject3);
          for (int i = 0; i < 10; i++)
          {
            if ((localclass_48 = localclass_359.a17(i)) != null)
            {
              int j = i;
              Object localObject6 = localclass_48;
              Object localObject5 = localObject1;
              if ((((class_48)localObject6).field_475 == 8) && (((class_48)localObject6).field_476 == 8) && (((class_48)localObject6).field_477 == 8))
              {
                if (localObject5.jdField_field_89_of_type_Int < 0)
                {
                  localObject6 = ElementKeyMap.getInfo((short)1);
                  localObject5.jdField_field_89_of_type_Int = ((ElementInformation)localObject6).getBuildIconNum();
                }
                class_832.e();
                localObject5.jdField_field_89_of_type_ArrayOfClass_832[j].b21(false);
                localObject5.jdField_field_89_of_type_ArrayOfClass_832[j].a83().field_616 = 21.5F;
                localObject5.jdField_field_89_of_type_ArrayOfClass_832[j].a83().field_615 = (159.0F + j * 69.800003F);
                localObject5.jdField_field_89_of_type_Int %= 256;
                localObject5.jdField_field_89_of_type_ArrayOfClass_832[j].a_2(localObject5.jdField_field_89_of_type_Int);
                localObject5.jdField_field_89_of_type_ArrayOfClass_832[j].b();
              }
              ((class_146)localObject1).a38((SegmentController)localObject3, localclass_48, i);
            }
            GlUtil.d1();
            if (i == ((class_748)localObject4).d1())
            {
              ((class_146)localObject1).jdField_field_89_of_type_Class_1402.a83().set(localObject1.jdField_field_89_of_type_ArrayOfClass_832[i].a83());
              ((class_146)localObject1).jdField_field_89_of_type_Class_1402.field_89.set(localObject1.jdField_field_89_of_type_ArrayOfClass_832[i].field_89);
              ((class_146)localObject1).jdField_field_89_of_type_Class_1402.b();
            }
            GlUtil.c2();
          }
        }
        catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException)
        {
          System.err.println("NO CONFIG");
        }
        try
        {
          localObject4 = ((class_332)localObject1).a41(this.jdField_field_89_of_type_Class_48);
          float f2 = 0.0F;
          localObject3 = localObject3;
          localObject2 = null;
          a38((SegmentController)localObject3, (class_48)localObject4, 0);
        }
        catch (IOException localIOException2)
        {
          (localObject2 = localIOException2).printStackTrace();
          throw new ErrorDialogException(((IOException)localObject2).getMessage());
        }
        catch (InterruptedException localInterruptedException2)
        {
          Object localObject2;
          (localObject2 = localInterruptedException2).printStackTrace();
          throw new ErrorDialogException(((InterruptedException)localObject2).getMessage());
        }
      }
      catch (IOException localIOException1)
      {
        (localObject1 = localIOException1).printStackTrace();
        throw new ErrorDialogException(((IOException)localObject1).getMessage());
      }
      catch (InterruptedException localInterruptedException1)
      {
        (localObject1 = localInterruptedException1).printStackTrace();
        throw new ErrorDialogException(((InterruptedException)localObject1).getMessage());
      }
    }
    this.jdField_field_89_of_type_ArrayOfClass_832[0].a147().b13(0);
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfClass_832.length; i++) {
      this.jdField_field_89_of_type_ArrayOfClass_832[i].c();
    }
    this.jdField_field_89_of_type_Class_1402.c();
    this.field_90.b28(2.0F, 2.0F, 2.0F);
    this.field_90.c();
    this.jdField_field_89_of_type_Class_130 = new class_130((class_371)a24());
    this.jdField_field_89_of_type_Class_130.c();
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_130);
  }
  
  public final void a12(class_941 paramclass_941)
  {
    super.a12(paramclass_941);
    this.jdField_field_89_of_type_Class_130.a12(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_146
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */