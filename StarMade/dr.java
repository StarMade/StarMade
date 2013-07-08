/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */import org.schema.game.common.controller.SegmentController;
/*   4:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   5:    */
/*  67:    */public final class dr
/*  68:    */  extends Camera
/*  69:    */  implements dw, wx
/*  70:    */{
/*  71:    */  private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*  72:    */  private wW jdField_a_of_type_WW;
/*  73:    */  private Camera jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/*  74:    */  
/*  75:    */  public dr(Camera paramCamera, al paramal)
/*  76:    */  {
/*  77: 77 */    super(new dx(paramal));
/*  78: 78 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramal.a();
/*  79:    */    
/*  81: 81 */    this.a = new wX(this);
/*  82:    */    
/*  84: 84 */    this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera;
/*  85:    */  }
/*  86:    */  
/*  91:    */  public final Vector3f b()
/*  92:    */  {
/*  93: 93 */    return ((dx)a()).b();
/*  94:    */  }
/*  95:    */  
/* 100:    */  public final void handleKeyEvent()
/* 101:    */  {
/* 102:102 */    ((dx)a()).handleKeyEvent();
/* 103:    */  }
/* 104:    */  
/* 126:    */  public final void a(q paramq)
/* 127:    */  {
/* 128:128 */    ((dx)a()).a(new q(paramq));
/* 129:    */  }
/* 130:    */  
/* 139:    */  public final void a(xq paramxq)
/* 140:    */  {
/* 141:141 */    if (!cv.U.a()) {
/* 142:142 */      ((wX)this.a).a.set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 143:    */      
/* 144:144 */      super.a(paramxq);
/* 145:    */    }
/* 146:    */    
/* 148:148 */    if ((this.jdField_a_of_type_WW != null) && (this.jdField_a_of_type_WW.a()))
/* 149:    */    {
/* 150:150 */      this.jdField_a_of_type_WW.a(paramxq);
/* 151:151 */      getWorldTransform().set(this.jdField_a_of_type_WW.getWorldTransform());
/* 152:    */    }
/* 153:    */    
/* 154:154 */    if (this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null) {
/* 155:155 */      a(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
/* 156:156 */      this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
/* 157:    */    }
/* 158:    */  }
/* 159:    */  
/* 169:    */  public final void a(Camera paramCamera)
/* 170:    */  {
/* 171:171 */    this.jdField_a_of_type_WW = new wW(paramCamera, this);
/* 172:    */  }
/* 173:    */  
/* 174:    */  public final SegmentController a() {
/* 175:175 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/* 176:    */  }
/* 177:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */