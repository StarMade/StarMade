/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.io.PrintStream;
/*   3:    */import javax.vecmath.Matrix3f;
/*   4:    */import org.lwjgl.input.Keyboard;
/*   5:    */import org.schema.game.common.controller.SegmentController;
/*   6:    */import org.schema.game.common.data.element.ElementDocking;
/*   7:    */import org.schema.game.common.data.world.Segment;
/*   8:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   9:    */
/*  72:    */final class du
/*  73:    */  extends Camera
/*  74:    */{
/*  75:    */  private dA jdField_a_of_type_DA;
/*  76:    */  private dB jdField_a_of_type_DB;
/*  77:    */  
/*  78:    */  public du(dt paramdt, xa paramxa)
/*  79:    */  {
/*  80: 80 */    super(paramxa);
/*  81: 81 */    this.jdField_a_of_type_DB = new dB(this, paramdt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*  82: 82 */    this.a = this.jdField_a_of_type_DB;
/*  83: 83 */    this.c = 0.2F;
/*  84: 84 */    this.b = 0.2F;
/*  85:    */    
/*  86: 86 */    this.jdField_a_of_type_DA = new dA(this, paramdt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*  87:    */  }
/*  88:    */  
/*  93:    */  public final void a()
/*  94:    */  {
/*  95: 95 */    super.a();
/*  96: 96 */    getWorldTransform().basis.set(this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
/*  97:    */  }
/*  98:    */  
/* 102:    */  public final void a(xq paramxq)
/* 103:    */  {
/* 104:104 */    if (this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a() != null)
/* 105:    */    {
/* 107:107 */      if ((this.jdField_a_of_type_DA.jdField_a_of_type_Int != org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.b()]) || (this.jdField_a_of_type_DA.jdField_a_of_type_Yh != this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.a().a()))
/* 108:    */      {
/* 111:111 */        System.err.println("NEW LOOKING ALGO " + this.jdField_a_of_type_DA.jdField_a_of_type_Int + " / " + org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.b()] + "; " + this.jdField_a_of_type_DA.jdField_a_of_type_Yh + " / " + this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.a().a());
/* 112:    */        
/* 113:113 */        this.jdField_a_of_type_DA = new dA(this, this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/* 114:    */      }
/* 115:    */      
/* 116:116 */      this.jdField_a_of_type_DA.jdField_a_of_type_Boolean = this.jdField_a_of_type_Dt.b;
/* 117:117 */      this.jdField_a_of_type_DA.jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 118:118 */      this.jdField_a_of_type_DA.a(this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.a().a());
/* 119:119 */      this.jdField_a_of_type_DA.a(org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.b()]);
/* 120:    */      
/* 121:121 */      this.a = this.jdField_a_of_type_DA;
/* 122:    */    }
/* 123:    */    else {
/* 124:124 */      this.a = this.jdField_a_of_type_DB;
/* 125:125 */      ((dB)this.a).jdField_a_of_type_Boolean = this.jdField_a_of_type_Dt.b;
/* 126:126 */      ((dB)this.a).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 127:127 */      ((dB)this.a).jdField_a_of_type_Int = this.jdField_a_of_type_Dt.jdField_a_of_type_Int;
/* 128:    */    }
/* 129:    */    
/* 131:131 */    if (wV.a())
/* 132:    */    {
/* 133:133 */      e();
/* 134:    */      
/* 135:135 */      if (cv.m.a()) {
/* 136:136 */        this.a.a(0.0F, 0.0F, -0.03F, 0.0F, b(), a());
/* 137:    */      }
/* 138:    */      
/* 145:145 */      if (cv.n.a()) {
/* 146:146 */        this.a.a(0.0F, 0.0F, 0.03F, 0.0F, b(), a());
/* 147:    */      }
/* 148:    */      
/* 156:156 */      if (Keyboard.isKeyDown(29))
/* 157:    */      {
/* 158:158 */        this.a.a(0.0F, this.jdField_a_of_type_WV.c / (xm.a() / 2), this.jdField_a_of_type_WV.b / (xm.b() / 2), 0.0F, b(), a());
/* 162:    */      }
/* 163:    */      else
/* 164:    */      {
/* 167:167 */        this.a.a(this.jdField_a_of_type_WV.b / (xm.b() / 2), ((xu.Y.b()) || (xu.Z.b()) ? -this.jdField_a_of_type_WV.c : this.jdField_a_of_type_WV.c) / (xm.a() / 2), 0.0F, a(), b(), 0.0F);
/* 168:    */      }
/* 169:    */      
/* 178:178 */      if (this.jdField_a_of_type_Dt.jdField_a_of_type_Boolean)
/* 179:    */      {
/* 180:180 */        this.jdField_a_of_type_Dt.jdField_a_of_type_Du.getWorldTransform().basis.setIdentity();
/* 181:181 */        this.a.a();
/* 182:182 */        this.jdField_a_of_type_Dt.jdField_a_of_type_Boolean = false;
/* 183:    */      }
/* 184:    */      
/* 186:186 */      b(paramxq);
/* 187:    */    }
/* 188:    */  }
/* 189:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     du
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */