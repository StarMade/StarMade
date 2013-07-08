/*   1:    */import java.util.Iterator;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.lwjgl.opengl.GL11;
/*   5:    */import org.lwjgl.opengl.GL13;
/*   6:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   7:    */import org.schema.schine.graphicsengine.forms.Mesh;
/*   8:    */
/*  45:    */public final class dq
/*  46:    */  implements xg, zr
/*  47:    */{
/*  48:    */  public float a;
/*  49:    */  private int jdField_a_of_type_Int;
/*  50:    */  private int jdField_b_of_type_Int;
/*  51:    */  private float jdField_b_of_type_Float;
/*  52:    */  private float c;
/*  53:    */  private Mesh jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
/*  54:    */  
/*  55:    */  public final void a() {}
/*  56:    */  
/*  57:    */  public final void b()
/*  58:    */  {
/*  59: 59 */    GlUtil.d();
/*  60:    */    
/*  64: 64 */    GlUtil.a(xd.a.a());
/*  65: 65 */    GlUtil.b(10.0F, 10.0F, 10.0F);
/*  66: 66 */    GL11.glEnable(2884);
/*  67: 67 */    GL11.glEnable(3042);
/*  68: 68 */    GL11.glBlendFunc(770, 771);
/*  69:    */    
/*  70: 70 */    zk.l.a = this;
/*  71: 71 */    zk.l.b();
/*  72: 72 */    this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();
/*  73: 73 */    zk.l.d();
/*  74: 74 */    GL11.glDisable(3042);
/*  75: 75 */    GlUtil.c();
/*  76:    */  }
/*  77:    */  
/*  86:    */  public final void c()
/*  87:    */  {
/*  88: 88 */    this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)xe.a().a("BlackHole").a().iterator().next());
/*  89:    */    
/*  90: 90 */    this.jdField_b_of_type_Float = 5.0E-007F;
/*  91: 91 */    this.c = 0.7F;
/*  92:    */    
/*  93: 93 */    this.jdField_a_of_type_Int = xe.a().a("detail").a().a().c;
/*  94: 94 */    this.jdField_b_of_type_Int = xe.a().a("detail_normal").a().a().c;
/*  95:    */  }
/*  96:    */  
/* 112:    */  public final void d()
/* 113:    */  {
/* 114:114 */    GL13.glActiveTexture(33984);
/* 115:115 */    GL11.glDisable(3553);
/* 116:116 */    GL11.glBindTexture(3553, 0);
/* 117:    */    
/* 118:118 */    GL13.glActiveTexture(33985);
/* 119:119 */    GL11.glDisable(3553);
/* 120:120 */    GL11.glBindTexture(3553, 0);
/* 121:    */    
/* 122:122 */    GL13.glActiveTexture(33984);
/* 123:    */  }
/* 124:    */  
/* 141:    */  public final void a(zj paramzj)
/* 142:    */  {
/* 143:143 */    GlUtil.a(paramzj, "cSharp", this.jdField_b_of_type_Float);
/* 144:144 */    GlUtil.a(paramzj, "cCover", this.c);
/* 145:145 */    GlUtil.a(paramzj, "cMove", this.jdField_a_of_type_Float);
/* 146:146 */    GlUtil.a(paramzj, "lightPos", xd.a.a().x, xd.a.a().y, xd.a.a().z, 1.0F);
/* 147:    */    
/* 151:151 */    GL13.glActiveTexture(33984);
/* 152:152 */    GL11.glEnable(3553);
/* 153:153 */    GL11.glBindTexture(3553, this.jdField_a_of_type_Int);
/* 154:    */    
/* 156:156 */    GL13.glActiveTexture(33985);
/* 157:157 */    GL11.glEnable(3553);
/* 158:158 */    GL11.glBindTexture(3553, this.jdField_b_of_type_Int);
/* 159:    */    
/* 160:160 */    GL13.glActiveTexture(33986);
/* 161:    */    
/* 162:162 */    GlUtil.a(paramzj, "tex", 0);
/* 163:163 */    GlUtil.a(paramzj, "nmap", 1);
/* 164:    */  }
/* 165:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */