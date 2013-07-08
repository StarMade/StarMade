/*  1:   */import java.util.ArrayList;
/*  2:   */import java.util.List;
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */import org.schema.game.common.controller.SegmentController;
/*  5:   */import org.schema.game.common.controller.elements.ManagerModuleSingle;
/*  6:   */import org.schema.game.common.controller.elements.ManagerThrustInterface;
/*  7:   */import org.schema.game.common.controller.elements.PowerAddOn;
/*  8:   */import org.schema.game.common.controller.elements.PowerManagerInterface;
/*  9:   */import org.schema.game.common.controller.elements.ShieldContainerInterface;
/* 10:   */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/* 11:   */import org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager;
/* 12:   */import org.schema.schine.network.client.ClientState;
/* 13:   */
/* 40:   */public final class io
/* 41:   */  extends yr
/* 42:   */{
/* 43:   */  private yP jdField_a_of_type_YP;
/* 44:   */  private long jdField_a_of_type_Long;
/* 45:   */  
/* 46:   */  public final void c()
/* 47:   */  {
/* 48:48 */    this.jdField_a_of_type_YP = new yP(300, 40, d.k(), a());
/* 49:49 */    this.jdField_a_of_type_YP.b = new ArrayList(10);
/* 50:50 */    this.jdField_a_of_type_YP.a().x = 0.0F;
/* 51:51 */    this.jdField_a_of_type_YP.a().y = 0.0F;
/* 52:   */    
/* 53:53 */    a(this.jdField_a_of_type_YP);
/* 54:   */  }
/* 55:   */  
/* 61:   */  public final void b()
/* 62:   */  {
/* 63:63 */    if (System.currentTimeMillis() - this.jdField_a_of_type_Long > 50L) {
/* 64:64 */      io localio = this;this.jdField_a_of_type_YP.b.clear();(localObject1 = (ct)localio.a()).a().b();Object localObject1 = ((ct)localObject1).a(); try { if ((localObject1 != null) && ((localObject1 instanceof ld))) { Object localObject2 = (SegmentController)localObject1;localObject1 = ((ld)localObject1).a();String str1 = "Mass: " + i.a(((SegmentController)localObject2).getMass()) + " (Blocks: " + ((SegmentController)localObject2).getTotalElements() + ")";(localObject3 = new Vector3f()).sub(((SegmentController)localObject2).getBoundingBox().b, ((SegmentController)localObject2).getBoundingBox().a);localObject2 = "Length: " + (int)((Vector3f)localObject3).z + "m, Height: " + (int)((Vector3f)localObject3).y + "m; Width: " + (int)((Vector3f)localObject3).x + "m";Object localObject3 = "Thrust: none"; if ((localObject1 instanceof ManagerThrustInterface)) localObject3 = "Thrust: " + i.a(((ThrusterCollectionManager)((ManagerThrustInterface)localObject1).getThrust().getCollectionManager()).getTotalThrust()); String str2 = "Shields: none"; if ((localObject1 instanceof ShieldContainerInterface)) str2 = "Shields: " + i.a(((ShieldContainerInterface)localObject1).getShieldManager().getShields()) + "/" + ((ShieldContainerInterface)localObject1).getShieldManager().getShieldCapabilityHP() + " (" + ((ShieldContainerInterface)localObject1).getShieldManager().getShieldRechargeRate() + " s/sec)"; String str3 = "Power: none"; if ((localObject1 instanceof PowerManagerInterface)) { localObject1 = (PowerManagerInterface)localObject1;str3 = "Power: " + i.a(((PowerManagerInterface)localObject1).getPowerAddOn().getPower()) + "/" + i.a(((PowerManagerInterface)localObject1).getPowerAddOn().getMaxPower()) + " (" + i.a(((PowerManagerInterface)localObject1).getPowerAddOn().getRecharge()) + " e/sec)"; } localio.jdField_a_of_type_YP.b.add(str1);localio.jdField_a_of_type_YP.b.add(localObject2);localio.jdField_a_of_type_YP.b.add(str3);localio.jdField_a_of_type_YP.b.add(localObject3);localio.jdField_a_of_type_YP.b.add(str2); } } catch (Exception localException) {}
/* 65:65 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 66:   */    }
/* 67:67 */    super.b();
/* 68:   */  }
/* 69:   */  
/* 70:   */  public io(ClientState paramClientState)
/* 71:   */  {
/* 72:38 */    super(paramClientState, 500.0F, 150.0F);
/* 73:   */    
/* 105:71 */    new q();
/* 106:   */  }
/* 107:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     io
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */