/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector4f;
/*   4:    */import org.lwjgl.opengl.GL11;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   7:    */import org.schema.schine.network.client.ClientState;
/*   8:    */
/*  22:    */public final class fm
/*  23:    */  extends yz
/*  24:    */{
/*  25:    */  private yE jdField_a_of_type_YE;
/*  26:    */  private float jdField_a_of_type_Float;
/*  27:    */  private float jdField_b_of_type_Float;
/*  28:    */  private yP jdField_a_of_type_YP;
/*  29: 29 */  private boolean jdField_a_of_type_Boolean = true;
/*  30:    */  
/*  33: 33 */  private zI jdField_a_of_type_ZI = new zI(30.0F);
/*  34:    */  
/*  35:    */  private String jdField_b_of_type_JavaLangString;
/*  36:    */  
/*  37: 37 */  private Color jdField_a_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
/*  38:    */  
/*  39: 39 */  private boolean jdField_b_of_type_Boolean = true;
/*  40:    */  
/*  41:    */  public fm(ClientState paramClientState, String paramString, Color paramColor)
/*  42:    */  {
/*  43: 43 */    super(paramClientState);
/*  44: 44 */    if (this.jdField_a_of_type_YE == null) {
/*  45: 45 */      this.jdField_a_of_type_YE = new yE(xe.a().a("std-message-gui-"), paramClientState);
/*  46: 46 */      this.jdField_a_of_type_YP = new yP(300, 300, paramClientState);
/*  47: 47 */      paramClientState = null;this.jdField_a_of_type_YP.b = new ArrayList();
/*  48:    */    }
/*  49:    */    
/*  50: 50 */    Object localObject = null;this.jdField_b_of_type_JavaLangString = paramString;
/*  51:    */    
/*  52: 52 */    this.jdField_a_of_type_OrgNewdawnSlickColor.r = paramColor.r;
/*  53: 53 */    this.jdField_a_of_type_OrgNewdawnSlickColor.g = paramColor.g;
/*  54: 54 */    this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = paramColor.jdField_b_of_type_Float;
/*  55: 55 */    this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = paramColor.jdField_a_of_type_Float;
/*  56:    */    
/*  57: 57 */    this.jdField_a_of_type_YP.b.clear();
/*  58:    */    
/*  59: 59 */    paramString = (paramClientState = paramString.split("\n")).length; for (paramColor = 0; paramColor < paramString; paramColor++) { localObject = paramClientState[paramColor];
/*  60: 60 */      this.jdField_a_of_type_YP.b.add(localObject);
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  65:    */  public final void a() {}
/*  66:    */  
/*  67:    */  public final void e()
/*  68:    */  {
/*  69: 69 */    this.jdField_b_of_type_Boolean = false;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public final void b() {
/*  73: 73 */    if (this.jdField_a_of_type_Boolean) {
/*  74: 74 */      c();
/*  75:    */    }
/*  76: 76 */    if ((!this.jdField_b_of_type_Boolean) || (this.jdField_b_of_type_Float < 0.0F)) {
/*  77: 77 */      return;
/*  78:    */    }
/*  79:    */    
/*  81: 81 */    this.jdField_a_of_type_YE.a().a().set(this.jdField_a_of_type_OrgNewdawnSlickColor.r, this.jdField_a_of_type_OrgNewdawnSlickColor.g, this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float, 1.0F);
/*  82: 82 */    if (this.jdField_a_of_type_Float < 0.3F)
/*  83:    */    {
/*  84: 84 */      if (this.jdField_a_of_type_ZI.a() < 0.5F) {
/*  85: 85 */        float f = Math.max(1.0F, 1.0F - this.jdField_a_of_type_Float * 5.0F);
/*  86: 86 */        this.jdField_a_of_type_YE.a().a().set(f / 2.0F + this.jdField_a_of_type_OrgNewdawnSlickColor.r, f / 2.0F + this.jdField_a_of_type_OrgNewdawnSlickColor.g, f / 2.0F + this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float, 1.0F);
/*  87:    */      }
/*  88:    */    }
/*  89: 89 */    GL11.glEnable(3042);
/*  90: 90 */    GL11.glBlendFunc(770, 771);
/*  91:    */    
/*  92: 92 */    GlUtil.d();
/*  93: 93 */    r();
/*  94: 94 */    this.jdField_a_of_type_YE.b();
/*  95: 95 */    GlUtil.c();
/*  96:    */    
/*  97: 97 */    GL11.glDisable(3042);
/*  98:    */    
/*  99: 99 */    this.jdField_a_of_type_YE.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/* 100:100 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = 1.0F;
/* 101:101 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.r = 1.0F;
/* 102:102 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.g = 1.0F;
/* 103:103 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = 1.0F;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public final float a()
/* 107:    */  {
/* 108:108 */    return this.jdField_a_of_type_YE.a();
/* 109:    */  }
/* 110:    */  
/* 113:    */  public final String a()
/* 114:    */  {
/* 115:115 */    return this.jdField_b_of_type_JavaLangString;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public final float b()
/* 119:    */  {
/* 120:120 */    return this.jdField_a_of_type_YE.b();
/* 121:    */  }
/* 122:    */  
/* 135:    */  public final void c()
/* 136:    */  {
/* 137:137 */    this.jdField_a_of_type_YP.a(30.0F, 30.0F, 0.0F);
/* 138:138 */    this.jdField_a_of_type_YP.a(Color.white);
/* 139:139 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = d.b();
/* 140:140 */    this.jdField_a_of_type_YP.c();
/* 141:    */    
/* 143:143 */    this.jdField_a_of_type_YE.a().c(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/* 144:144 */    this.jdField_a_of_type_YE.c();
/* 145:    */    
/* 146:146 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/* 147:    */    
/* 151:151 */    this.jdField_a_of_type_Boolean = false;
/* 152:    */  }
/* 153:    */  
/* 158:    */  public final void f()
/* 159:    */  {
/* 160:160 */    this.jdField_b_of_type_Boolean = true;
/* 161:    */  }
/* 162:    */  
/* 171:    */  public final void g()
/* 172:    */  {
/* 173:173 */    this.jdField_b_of_type_Float = 0.0F;
/* 174:    */    
/* 175:175 */    this.jdField_a_of_type_Float = 0.0F;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public final void a(xq paramxq) {
/* 179:179 */    if (this.jdField_b_of_type_Float < 0.0F) {
/* 180:180 */      this.jdField_b_of_type_Float += paramxq.a();
/* 181:181 */      return;
/* 182:    */    }
/* 183:183 */    this.jdField_a_of_type_Float += paramxq.a();
/* 184:184 */    this.jdField_a_of_type_ZI.a(paramxq);
/* 185:    */  }
/* 186:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */