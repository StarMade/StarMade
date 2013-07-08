/*   1:    */import java.util.Iterator;
/*   2:    */import java.util.Set;
/*   3:    */import org.schema.game.common.controller.SegmentController;
/*   4:    */import org.schema.game.common.data.element.ElementDocking;
/*   5:    */import org.schema.game.common.data.world.Segment;
/*   6:    */import org.schema.game.server.controller.GameServerController;
/*   7:    */import org.schema.schine.network.StateInterface;
/*   8:    */
/*  13:    */public final class kt
/*  14:    */  extends kp
/*  15:    */{
/*  16:    */  public kt(StateInterface paramStateInterface, kd paramkd)
/*  17:    */  {
/*  18: 18 */    super(paramStateInterface, paramkd);
/*  19:    */  }
/*  20:    */  
/*  23:    */  public final String getUniqueIdentifier()
/*  24:    */  {
/*  25: 25 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getUniqueIdentifier() + "_AI";
/*  26:    */  }
/*  27:    */  
/*  28:    */  public final boolean isVolatile() {
/*  29: 29 */    return false;
/*  30:    */  }
/*  31:    */  
/*  35:    */  public final void a(SegmentController paramSegmentController)
/*  36:    */  {
/*  37: 37 */    super.a(paramSegmentController);
/*  38: 38 */    Iterator localIterator = null; if (this.jdField_a_of_type_Wo.a()) {
/*  39: 39 */      if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().b()) && (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().from.a().a() == paramSegmentController)) {
/*  40: 40 */        return;
/*  41:    */      }
/*  42: 42 */      if (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().isEmpty()) {
/*  43: 43 */        for (localIterator = this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().iterator(); localIterator.hasNext();) {
/*  44: 44 */          if (((ElementDocking)localIterator.next()).from.a().a() == paramSegmentController) {
/*  45: 45 */            return;
/*  46:    */          }
/*  47:    */        }
/*  48:    */      }
/*  49: 49 */      ((sJ)this.jdField_a_of_type_Wo).a(paramSegmentController);
/*  50:    */    }
/*  51:    */  }
/*  52:    */  
/*  54:    */  protected final void b(ko paramko)
/*  55:    */  {
/*  56: 56 */    if (paramko.a() == kq.b) {
/*  57: 57 */      if (paramko.a().equals("Turret")) {
/*  58: 58 */        this.jdField_a_of_type_Wo = new tm((kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, a(kq.c).a());
/*  59:    */      } else {
/*  60: 60 */        this.jdField_a_of_type_Wo = new sR((kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, a(kq.c).a());
/*  61:    */      }
/*  62:    */    }
/*  63:    */    
/*  64: 64 */    super.b(paramko);
/*  65:    */  }
/*  66:    */  
/*  72:    */  protected final void a(ko paramko)
/*  73:    */  {
/*  74: 74 */    if (paramko.a() == kq.c) {
/*  75: 75 */      paramko.a();
/*  76:    */      
/*  80: 80 */      this.jdField_a_of_type_Wo = new sJ("shipAiEntity", (kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*  81:    */    }
/*  82:    */  }
/*  83:    */  
/*  89:    */  public final void a(lb paramlb)
/*  90:    */  {
/*  91: 91 */    if (this.jdField_a_of_type_Wo.a()) {
/*  92: 92 */      if ((paramlb != null) && (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getFactionId() == -1)) {
/*  93: 93 */        ((vg)paramlb.getState()).a().a(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*  94:    */      }
/*  95: 95 */      if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getFactionId() != 0)) {
/*  96: 96 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setFactionId(0);
/*  97:    */      }
/*  98:    */    }
/*  99:    */    
/* 100:100 */    super.a(paramlb);
/* 101:    */  }
/* 102:    */  
/* 104:    */  protected final void b()
/* 105:    */  {
/* 106:    */    ko localko;
/* 107:    */    
/* 108:108 */    if ((localko = a(kq.b)).a().equals("Turret")) {
/* 109:109 */      this.jdField_a_of_type_Wo = new tm((kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, a(kq.c).a());return; }
/* 110:110 */    if (localko.a().equals("Ship")) {
/* 111:111 */      this.jdField_a_of_type_Wo = new sR((kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, a(kq.c).a());
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 126:    */  public final boolean b()
/* 127:    */  {
/* 128:128 */    return a(kq.c).a();
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */