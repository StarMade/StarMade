/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import javax.vecmath.Vector4f;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */import org.schema.game.common.controller.SegmentController;
/*   7:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   8:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   9:    */import org.schema.schine.network.client.ClientState;
/*  10:    */
/*  18:    */public final class im
/*  19:    */  extends yz
/*  20:    */{
/*  21:    */  private ct jdField_a_of_type_Ct;
/*  22:    */  private float jdField_a_of_type_Float;
/*  23:    */  
/*  24:    */  public im(ClientState paramClientState, float paramFloat)
/*  25:    */  {
/*  26: 26 */    super(paramClientState);
/*  27: 27 */    this.jdField_a_of_type_Ct = ((ct)paramClientState);
/*  28: 28 */    this.jdField_a_of_type_Float = paramFloat; }
/*  29:    */  
/*  30: 30 */  private Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f();
/*  31:    */  
/*  36: 36 */  private Transform b = new Transform();
/*  37: 37 */  private Vector3f c = new Vector3f();
/*  38:    */  
/*  39:    */  public final void a() {}
/*  40:    */  
/*  41: 41 */  public final void b() { mF localmF1; if ((localmF1 = this.jdField_a_of_type_Ct.a()) != null) {
/*  42: 42 */      if ((localmF1 instanceof SegmentController)) {
/*  43: 43 */        this.b.set(((SegmentController)localmF1).getWorldTransformInverse());
/*  44:    */      }
/*  45: 45 */      else if ((localmF1 instanceof lD)) {
/*  46: 46 */        this.b.set(xe.a().getWorldTransform());
/*  47: 47 */        this.b.inverse();
/*  48:    */      } else {
/*  49: 49 */        this.b.set(localmF1.getWorldTransform());
/*  50: 50 */        this.b.inverse();
/*  51:    */      }
/*  52:    */      
/*  59: 59 */      localmF1 = ((ct)a()).a().a.a.a.a();
/*  60:    */      
/*  63: 63 */      GL11.glPointSize(2.0F);
/*  64:    */      
/*  65: 65 */      GL11.glBegin(0);
/*  66:    */      
/*  69: 69 */      for (mF localmF2 : this.jdField_a_of_type_Ct.a().values())
/*  70:    */      {
/*  73: 73 */        this.c.set(localmF2.getWorldTransformClient().origin);
/*  74: 74 */        this.b.transform(this.c);
/*  75: 75 */        this.c.scale(0.07F);
/*  76: 76 */        if (this.c.length() < this.jdField_a_of_type_Float / 2.0F) {
/*  77: 77 */          ij.a(localmF2, this.jdField_a_of_type_JavaxVecmathVector4f, localmF1 == localmF2, this.jdField_a_of_type_Ct);
/*  78: 78 */          this.c.x = (this.jdField_a_of_type_Float / 2.0F - this.c.x);
/*  79: 79 */          this.c.z = (this.jdField_a_of_type_Float / 2.0F - this.c.z);
/*  80: 80 */          GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w);
/*  81: 81 */          GL11.glVertex2f(this.c.x, this.c.z);
/*  82:    */        }
/*  83:    */      }
/*  84: 84 */      GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  85: 85 */      GL11.glEnd();
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  92:    */  public final void c() {}
/*  93:    */  
/*  96:    */  public final float a()
/*  97:    */  {
/*  98: 98 */    return 0.0F;
/*  99:    */  }
/* 100:    */  
/* 102:    */  public final float b()
/* 103:    */  {
/* 104:104 */    return 0.0F;
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     im
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */