/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.lwjgl.opengl.GL11;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   7:    */import org.schema.schine.network.client.ClientState;
/*   8:    */
/*  17:    */public final class fq
/*  18:    */  extends yz
/*  19:    */{
/*  20: 20 */  private float jdField_b_of_type_Float = 5.0F;
/*  21:    */  
/*  22:    */  private float c;
/*  23:    */  
/*  24:    */  private float d;
/*  25:    */  private yP jdField_a_of_type_YP;
/*  26: 26 */  private boolean jdField_a_of_type_Boolean = true;
/*  27:    */  
/*  31: 31 */  private float e = 0.0F;
/*  32: 32 */  private Color jdField_a_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
/*  33:    */  private String jdField_b_of_type_JavaLangString;
/*  34:    */  public float a;
/*  35:    */  
/*  36:    */  public fq(String paramString1, ClientState paramClientState, String paramString2, Color paramColor)
/*  37:    */  {
/*  38: 38 */    super(paramClientState);this.jdField_a_of_type_Float = 0.0F;
/*  39: 39 */    this.jdField_b_of_type_JavaLangString = paramString1;
/*  40: 40 */    this.jdField_a_of_type_YP = new yP(800, 40, paramClientState);
/*  41: 41 */    this.jdField_a_of_type_YP.b = new ArrayList();
/*  42:    */    
/*  43: 43 */    this.jdField_a_of_type_OrgNewdawnSlickColor.r = paramColor.r;
/*  44: 44 */    this.jdField_a_of_type_OrgNewdawnSlickColor.g = paramColor.g;
/*  45: 45 */    this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = paramColor.jdField_b_of_type_Float;
/*  46: 46 */    this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = paramColor.jdField_a_of_type_Float;
/*  47:    */    
/*  48: 48 */    this.jdField_a_of_type_YP.b.add(paramString2);
/*  49:    */  }
/*  50:    */  
/*  52:    */  public final void a() {}
/*  53:    */  
/*  55:    */  public final void b()
/*  56:    */  {
/*  57: 57 */    if (this.jdField_a_of_type_Boolean) {
/*  58: 58 */      c();
/*  59:    */    }
/*  60: 60 */    if (this.d < 0.0F) {
/*  61: 61 */      return;
/*  62:    */    }
/*  63: 63 */    a().x = 100.0F;
/*  64: 64 */    a().y = (this.e + 100.0F);
/*  65: 65 */    if (!j()) {
/*  66:    */      return;
/*  67:    */    }
/*  68:    */    
/*  69:    */    float f;
/*  70: 70 */    if ((f = this.jdField_b_of_type_Float - this.c) < 1.0F) {
/*  71: 71 */      this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = f;
/*  72:    */    }
/*  73: 73 */    GL11.glEnable(3042);
/*  74: 74 */    GL11.glBlendFunc(770, 771);
/*  75:    */    
/*  76: 76 */    GlUtil.d();
/*  77: 77 */    r();
/*  78: 78 */    this.jdField_a_of_type_YP.b();
/*  79: 79 */    GlUtil.c();
/*  80:    */    
/*  81: 81 */    GL11.glDisable(3042);
/*  82:    */    
/*  83: 83 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = 1.0F;
/*  84: 84 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.r = 1.0F;
/*  85: 85 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.g = 1.0F;
/*  86: 86 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = 1.0F;
/*  87:    */  }
/*  88:    */  
/*  91:    */  public final boolean equals(Object paramObject)
/*  92:    */  {
/*  93: 93 */    return this.jdField_b_of_type_JavaLangString.equals(((fq)paramObject).jdField_b_of_type_JavaLangString);
/*  94:    */  }
/*  95:    */  
/*  96:    */  public final float a()
/*  97:    */  {
/*  98: 98 */    return this.jdField_a_of_type_YP.a();
/*  99:    */  }
/* 100:    */  
/* 102:    */  public final String a()
/* 103:    */  {
/* 104:104 */    return this.jdField_b_of_type_JavaLangString;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public final float b()
/* 108:    */  {
/* 109:109 */    return this.jdField_a_of_type_YP.b();
/* 110:    */  }
/* 111:    */  
/* 112:    */  public final boolean a() {
/* 113:113 */    return this.c < this.jdField_b_of_type_Float;
/* 114:    */  }
/* 115:    */  
/* 123:    */  public final void c()
/* 124:    */  {
/* 125:125 */    this.jdField_a_of_type_YP.a(Color.white);
/* 126:126 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = d.e();
/* 127:127 */    this.jdField_a_of_type_YP.c();
/* 128:    */    
/* 133:133 */    this.jdField_a_of_type_Boolean = false;
/* 134:    */    
/* 136:136 */    this.e = (-1.0F * (this.jdField_a_of_type_YP.a() * this.a.y + 5.0F));
/* 137:    */  }
/* 138:    */  
/* 146:    */  public final void e()
/* 147:    */  {
/* 148:148 */    this.c = 0.0F;
/* 149:    */  }
/* 150:    */  
/* 160:    */  public final void a(String paramString)
/* 161:    */  {
/* 162:162 */    this.jdField_a_of_type_YP.b.set(0, paramString);
/* 163:    */  }
/* 164:    */  
/* 167:    */  public final void f()
/* 168:    */  {
/* 169:169 */    this.d = 0.0F;
/* 170:    */    
/* 171:171 */    this.c = 0.0F;
/* 172:    */  }
/* 173:    */  
/* 178:    */  public final void a(xq paramxq)
/* 179:    */  {
/* 180:180 */    if (this.d < 0.0F) {
/* 181:181 */      this.d += paramxq.a();
/* 182:182 */      return;
/* 183:    */    }
/* 184:184 */    this.c += paramxq.a();
/* 185:    */    
/* 186:186 */    float f1 = this.jdField_a_of_type_Float * (this.jdField_a_of_type_YP.a() * this.a.y + 5.0F);
/* 187:    */    
/* 188:188 */    float f2 = Math.min(1.0F, Math.max(0.01F, Math.abs(this.e - f1)) / (this.jdField_a_of_type_YP.a() * this.a.y));
/* 189:    */    
/* 190:190 */    if (this.e > f1) {
/* 191:191 */      this.e -= paramxq.a() * 1000.0F * f2;
/* 192:192 */      if (this.e <= f1) {
/* 193:193 */        this.e = f1;
/* 194:    */      }
/* 195:195 */    } else if (this.e < f1) {
/* 196:196 */      this.e += paramxq.a() * 1000.0F * f2;
/* 197:197 */      if (this.e >= f1) {
/* 198:198 */        this.e = f1;
/* 199:    */      }
/* 200:    */    }
/* 201:    */  }
/* 202:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */