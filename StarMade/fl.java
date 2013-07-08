/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import javax.vecmath.Vector4f;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */import org.newdawn.slick.Color;
/*   7:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   8:    */import org.schema.schine.network.client.ClientState;
/*   9:    */
/*  22:    */public final class fl
/*  23:    */  extends yz
/*  24:    */{
/*  25:    */  private yE jdField_a_of_type_YE;
/*  26: 26 */  private float jdField_b_of_type_Float = 5.0F;
/*  27:    */  
/*  28:    */  private float c;
/*  29:    */  
/*  30:    */  private float d;
/*  31:    */  private yP jdField_a_of_type_YP;
/*  32: 32 */  private boolean jdField_a_of_type_Boolean = true;
/*  33:    */  
/*  37: 37 */  private float e = 0.0F;
/*  38:    */  
/*  39:    */  private String jdField_b_of_type_JavaLangString;
/*  40:    */  
/*  41: 41 */  private Color jdField_a_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
/*  42:    */  public float a;
/*  43:    */  
/*  44:    */  public fl(ClientState paramClientState, String paramString, Color paramColor)
/*  45:    */  {
/*  46: 46 */    super(paramClientState);this.jdField_a_of_type_Float = 0.0F;
/*  47:    */    
/*  48: 48 */    this.jdField_a_of_type_YP = new yP(300, 300, paramClientState);
/*  49: 49 */    this.jdField_a_of_type_YP.b = new ArrayList();
/*  50: 50 */    this.jdField_b_of_type_JavaLangString = paramString;
/*  51:    */    
/*  52: 52 */    this.jdField_a_of_type_OrgNewdawnSlickColor.r = paramColor.r;
/*  53: 53 */    this.jdField_a_of_type_OrgNewdawnSlickColor.g = paramColor.g;
/*  54: 54 */    this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = paramColor.jdField_b_of_type_Float;
/*  55: 55 */    this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = paramColor.jdField_a_of_type_Float;
/*  56:    */    
/*  58: 58 */    paramString = (paramClientState = paramString.split("\n")).length; for (paramColor = 0; paramColor < paramString; paramColor++) { Object localObject = paramClientState[paramColor];
/*  59: 59 */      this.jdField_a_of_type_YP.b.add(localObject);
/*  60:    */    }
/*  61:    */  }
/*  62:    */  
/*  64:    */  public final void a() {}
/*  65:    */  
/*  67:    */  public final void b()
/*  68:    */  {
/*  69: 69 */    if (this.jdField_a_of_type_Boolean) {
/*  70: 70 */      c();
/*  71:    */    }
/*  72: 72 */    if (this.d < 0.0F) {
/*  73: 73 */      return;
/*  74:    */    }
/*  75: 75 */    a().x = (xm.b() - this.a.x * this.jdField_a_of_type_YE.b() - 96.0F);
/*  76: 76 */    a().y = this.e;
/*  77: 77 */    if (!j()) {
/*  78: 78 */      return;
/*  79:    */    }
/*  80:    */    
/*  81: 81 */    float f = this.jdField_b_of_type_Float - this.c;
/*  82: 82 */    this.jdField_a_of_type_YE.a().a().set(this.jdField_a_of_type_OrgNewdawnSlickColor.r, this.jdField_a_of_type_OrgNewdawnSlickColor.g, this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float, 1.0F);
/*  83: 83 */    if (f < 1.0F) {
/*  84: 84 */      this.jdField_a_of_type_YE.a().a().set(this.jdField_a_of_type_OrgNewdawnSlickColor.r, this.jdField_a_of_type_OrgNewdawnSlickColor.g, this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float, f);
/*  85: 85 */      this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = f;
/*  86:    */    }
/*  87: 87 */    GL11.glEnable(3042);
/*  88: 88 */    GL11.glBlendFunc(770, 771);
/*  89:    */    
/*  90: 90 */    GlUtil.d();
/*  91: 91 */    r();
/*  92: 92 */    this.jdField_a_of_type_YE.b();
/*  93: 93 */    GlUtil.c();
/*  94:    */    
/*  95: 95 */    GL11.glDisable(3042);
/*  96:    */    
/*  97: 97 */    this.jdField_a_of_type_YE.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/*  98: 98 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = 1.0F;
/*  99: 99 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.r = 1.0F;
/* 100:100 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.g = 1.0F;
/* 101:101 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = 1.0F;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public final float a() {
/* 105:105 */    return this.jdField_a_of_type_YE.a();
/* 106:    */  }
/* 107:    */  
/* 109:    */  public final String a()
/* 110:    */  {
/* 111:111 */    return this.jdField_b_of_type_JavaLangString;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public final float b()
/* 115:    */  {
/* 116:116 */    return this.jdField_a_of_type_YE.b();
/* 117:    */  }
/* 118:    */  
/* 119:    */  public final boolean a() {
/* 120:120 */    return this.c < this.jdField_b_of_type_Float;
/* 121:    */  }
/* 122:    */  
/* 129:    */  public final void c()
/* 130:    */  {
/* 131:131 */    this.jdField_a_of_type_YE = new yE(xe.a().a("std-message-gui-"), a());
/* 132:132 */    this.jdField_a_of_type_YP.a(30.0F, 30.0F, 0.0F);
/* 133:133 */    this.jdField_a_of_type_YP.a(Color.white);
/* 134:134 */    this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = d.b();
/* 135:135 */    this.jdField_a_of_type_YP.c();
/* 136:    */    
/* 137:137 */    this.jdField_a_of_type_YE.a().c(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/* 138:138 */    this.jdField_a_of_type_YE.c();
/* 139:    */    
/* 140:140 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/* 141:    */    
/* 142:142 */    this.e = (-1.0F * (this.jdField_a_of_type_YE.a() * this.a.y + 5.0F));
/* 143:    */    
/* 145:145 */    this.jdField_a_of_type_Boolean = false;
/* 146:    */  }
/* 147:    */  
/* 153:    */  public final void e()
/* 154:    */  {
/* 155:155 */    String[] arrayOfString = this.jdField_b_of_type_JavaLangString.split("\n");
/* 156:156 */    this.jdField_a_of_type_YP.b.clear();
/* 157:157 */    for (String str : arrayOfString) {
/* 158:158 */      this.jdField_a_of_type_YP.b.add(str);
/* 159:    */    }
/* 160:160 */    this.c = 0.0F;
/* 161:    */  }
/* 162:    */  
/* 165:    */  public final void a(String paramString)
/* 166:    */  {
/* 167:167 */    this.jdField_b_of_type_JavaLangString = paramString;
/* 168:    */  }
/* 169:    */  
/* 172:    */  public final void f()
/* 173:    */  {
/* 174:174 */    this.d = 0.0F;
/* 175:    */    
/* 176:176 */    this.c = 0.0F;
/* 177:    */  }
/* 178:    */  
/* 179:179 */  public final void g() { if (this.c < this.jdField_b_of_type_Float - 1.0F) {
/* 180:180 */      this.c = (this.jdField_b_of_type_Float - 1.0F);
/* 181:    */    }
/* 182:    */  }
/* 183:    */  
/* 184:    */  public final void a(xq paramxq) {
/* 185:185 */    if (this.jdField_a_of_type_Boolean) {
/* 186:186 */      return;
/* 187:    */    }
/* 188:188 */    if (this.d < 0.0F) {
/* 189:189 */      this.d += paramxq.a();
/* 190:190 */      return;
/* 191:    */    }
/* 192:192 */    this.c += paramxq.a();
/* 193:    */    
/* 194:194 */    float f1 = this.jdField_a_of_type_Float * (this.jdField_a_of_type_YE.a() * this.a.y + 5.0F);
/* 195:    */    
/* 196:196 */    float f2 = Math.min(1.0F, Math.max(0.01F, Math.abs(this.e - f1)) / (this.jdField_a_of_type_YE.a() * this.a.y));
/* 197:    */    
/* 198:198 */    if (this.e > f1) {
/* 199:199 */      this.e -= paramxq.a() * 1000.0F * f2;
/* 200:200 */      if (this.e <= f1) {
/* 201:201 */        this.e = f1;
/* 202:    */      }
/* 203:203 */    } else if (this.e < f1) {
/* 204:204 */      this.e += paramxq.a() * 1000.0F * f2;
/* 205:205 */      if (this.e >= f1) {
/* 206:206 */        this.e = f1;
/* 207:    */      }
/* 208:    */    }
/* 209:    */  }
/* 210:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */