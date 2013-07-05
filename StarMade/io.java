/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ManagerModuleSingle;
/*    */ import org.schema.game.common.controller.elements.ManagerThrustInterface;
/*    */ import org.schema.game.common.controller.elements.PowerAddOn;
/*    */ import org.schema.game.common.controller.elements.PowerManagerInterface;
/*    */ import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*    */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*    */ import org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class io extends yr
/*    */ {
/*    */   private yP jdField_a_of_type_YP;
/*    */   private long jdField_a_of_type_Long;
/*    */ 
/*    */   public io(ClientState paramClientState)
/*    */   {
/* 38 */     super(paramClientState, 500.0F, 150.0F);
/*    */ 
/* 71 */     new q();
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 48 */     this.jdField_a_of_type_YP = new yP(300, 40, d.k(), a());
/* 49 */     this.jdField_a_of_type_YP.b = new ArrayList(10);
/* 50 */     this.jdField_a_of_type_YP.a().x = 0.0F;
/* 51 */     this.jdField_a_of_type_YP.a().y = 0.0F;
/*    */ 
/* 53 */     a(this.jdField_a_of_type_YP);
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 63 */     if (System.currentTimeMillis() - this.jdField_a_of_type_Long > 50L) {
/* 64 */       io localio = this; this.jdField_a_of_type_YP.b.clear(); (localObject1 = (ct)localio.a()).a().b(); Object localObject1 = ((ct)localObject1).a();
/*    */       try { if ((localObject1 != null) && ((localObject1 instanceof ld))) { Object localObject2 = (SegmentController)localObject1; localObject1 = ((ld)localObject1).a(); String str1 = "Mass: " + i.a(((SegmentController)localObject2).getMass()) + " (Blocks: " + ((SegmentController)localObject2).getTotalElements() + ")"; (localObject3 = new Vector3f()).sub(((SegmentController)localObject2).getBoundingBox().b, ((SegmentController)localObject2).getBoundingBox().a); localObject2 = "Length: " + (int)((Vector3f)localObject3).z + "m, Height: " + (int)((Vector3f)localObject3).y + "m; Width: " + (int)((Vector3f)localObject3).x + "m"; Object localObject3 = "Thrust: none"; if ((localObject1 instanceof ManagerThrustInterface)) localObject3 = "Thrust: " + i.a(((ThrusterCollectionManager)((ManagerThrustInterface)localObject1).getThrust().getCollectionManager()).getTotalThrust()); String str2 = "Shields: none"; if ((localObject1 instanceof ShieldContainerInterface)) str2 = "Shields: " + i.a(((ShieldContainerInterface)localObject1).getShieldManager().getShields()) + "/" + ((ShieldContainerInterface)localObject1).getShieldManager().getShieldCapabilityHP() + " (" + ((ShieldContainerInterface)localObject1).getShieldManager().getShieldRechargeRate() + " s/sec)"; String str3 = "Power: none"; if ((localObject1 instanceof PowerManagerInterface)) { localObject1 = (PowerManagerInterface)localObject1; str3 = "Power: " + i.a(((PowerManagerInterface)localObject1).getPowerAddOn().getPower()) + "/" + i.a(((PowerManagerInterface)localObject1).getPowerAddOn().getMaxPower()) + " (" + i.a(((PowerManagerInterface)localObject1).getPowerAddOn().getRecharge()) + " e/sec)"; } localio.jdField_a_of_type_YP.b.add(str1); localio.jdField_a_of_type_YP.b.add(localObject2); localio.jdField_a_of_type_YP.b.add(str3); localio.jdField_a_of_type_YP.b.add(localObject3); localio.jdField_a_of_type_YP.b.add(str2); }  } catch (Exception localException) {  }
/*    */ 
/* 65 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*    */     }
/* 67 */     super.b();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     io
 * JD-Core Version:    0.6.2
 */