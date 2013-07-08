import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerModuleSingle;
import org.schema.game.common.controller.elements.ManagerThrustInterface;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager;
import org.schema.schine.network.client.ClientState;

public final class class_893
  extends class_1414
{
  private class_930 jdField_field_89_of_type_Class_930;
  private long jdField_field_89_of_type_Long;
  
  public class_893(ClientState paramClientState)
  {
    super(paramClientState, 500.0F, 150.0F);
    new class_48();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930 = new class_930(300, 40, class_29.k(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList(10);
    this.jdField_field_89_of_type_Class_930.a83().field_615 = 0.0F;
    this.jdField_field_89_of_type_Class_930.a83().field_616 = 0.0F;
    a9(this.jdField_field_89_of_type_Class_930);
  }
  
  public final void b()
  {
    if (System.currentTimeMillis() - this.jdField_field_89_of_type_Long > 50L)
    {
      class_893 localclass_893 = this;
      this.jdField_field_89_of_type_Class_930.field_90.clear();
      (localObject1 = (class_371)localclass_893.a24()).a20().b5();
      Object localObject1 = ((class_371)localObject1).a6();
      try
      {
        if ((localObject1 != null) && ((localObject1 instanceof class_798)))
        {
          Object localObject2 = (SegmentController)localObject1;
          localObject1 = ((class_798)localObject1).a();
          String str1 = "Mass: " + class_41.a2(((SegmentController)localObject2).getMass()) + " (Blocks: " + ((SegmentController)localObject2).getTotalElements() + ")";
          (localObject3 = new Vector3f()).sub(((SegmentController)localObject2).getBoundingBox().field_1274, ((SegmentController)localObject2).getBoundingBox().field_1273);
          localObject2 = "Length: " + (int)((Vector3f)localObject3).field_617 + "m, Height: " + (int)((Vector3f)localObject3).field_616 + "m; Width: " + (int)((Vector3f)localObject3).field_615 + "m";
          Object localObject3 = "Thrust: none";
          if ((localObject1 instanceof ManagerThrustInterface)) {
            localObject3 = "Thrust: " + class_41.a2(((ThrusterCollectionManager)((ManagerThrustInterface)localObject1).getThrust().getCollectionManager()).getTotalThrust());
          }
          String str2 = "Shields: none";
          if ((localObject1 instanceof ShieldContainerInterface)) {
            str2 = "Shields: " + class_41.a3(((ShieldContainerInterface)localObject1).getShieldManager().getShields()) + "/" + ((ShieldContainerInterface)localObject1).getShieldManager().getShieldCapabilityHP() + " (" + ((ShieldContainerInterface)localObject1).getShieldManager().getShieldRechargeRate() + " s/sec)";
          }
          String str3 = "Power: none";
          if ((localObject1 instanceof PowerManagerInterface))
          {
            localObject1 = (PowerManagerInterface)localObject1;
            str3 = "Power: " + class_41.a3(((PowerManagerInterface)localObject1).getPowerAddOn().getPower()) + "/" + class_41.a3(((PowerManagerInterface)localObject1).getPowerAddOn().getMaxPower()) + " (" + class_41.a3(((PowerManagerInterface)localObject1).getPowerAddOn().getRecharge()) + " e/sec)";
          }
          localclass_893.jdField_field_89_of_type_Class_930.field_90.add(str1);
          localclass_893.jdField_field_89_of_type_Class_930.field_90.add(localObject2);
          localclass_893.jdField_field_89_of_type_Class_930.field_90.add(str3);
          localclass_893.jdField_field_89_of_type_Class_930.field_90.add(localObject3);
          localclass_893.jdField_field_89_of_type_Class_930.field_90.add(str2);
        }
      }
      catch (Exception localException) {}
      this.jdField_field_89_of_type_Long = System.currentTimeMillis();
    }
    super.b();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_893
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */