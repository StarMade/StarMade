/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import javax.vecmath.Vector4f;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   7:    */import org.schema.schine.network.client.ClientState;
/*   8:    */
/*  23:    */public final class iz
/*  24:    */  extends v
/*  25:    */{
/*  26:    */  private yE jdField_a_of_type_YE;
/*  27:    */  private eW jdField_a_of_type_EW;
/*  28:    */  private eW jdField_b_of_type_EW;
/*  29:    */  private eW jdField_c_of_type_EW;
/*  30:    */  private static yq jdField_a_of_type_Yq;
/*  31:    */  private boolean jdField_a_of_type_Boolean;
/*  32:    */  private bI jdField_a_of_type_BI;
/*  33:    */  private eW jdField_d_of_type_EW;
/*  34:    */  private String jdField_b_of_type_JavaLangString;
/*  35: 35 */  private float jdField_a_of_type_Float = 0.0F;
/*  36:    */  private yP jdField_a_of_type_YP;
/*  37:    */  private yP jdField_b_of_type_YP;
/*  38:    */  private yP jdField_c_of_type_YP;
/*  39:    */  private Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*  40:    */  private yP jdField_d_of_type_YP;
/*  41:    */  private ix jdField_a_of_type_Ix;
/*  42:    */  
/*  43:    */  public iz(ClientState paramClientState, bI parambI, String paramString) {
/*  44: 44 */    super(paramClientState);
/*  45: 45 */    this.jdField_a_of_type_BI = parambI;
/*  46: 46 */    this.jdField_a_of_type_YE = new yE(xe.a().a("info-panel-gui-"), paramClientState);
/*  47: 47 */    this.jdField_b_of_type_JavaLangString = paramString;
/*  48: 48 */    this.jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F - this.jdField_a_of_type_Float);
/*  49:    */  }
/*  50:    */  
/*  53:    */  public final void a() {}
/*  54:    */  
/*  57:    */  public final void b()
/*  58:    */  {
/*  59: 59 */    if (!this.jdField_a_of_type_Boolean) {
/*  60: 60 */      c();
/*  61:    */    }
/*  62:    */    
/*  69: 69 */    GlUtil.d();
/*  70: 70 */    r();
/*  71:    */    
/*  74: 74 */    this.jdField_a_of_type_JavaxVecmathVector4f.set(1.0F, 1.0F, 1.0F, 1.0F - this.jdField_a_of_type_Float);
/*  75: 75 */    this.jdField_a_of_type_EW.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  76: 76 */    this.jdField_b_of_type_EW.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  77: 77 */    this.jdField_d_of_type_EW.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  78: 78 */    this.jdField_c_of_type_EW.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  79: 79 */    this.jdField_a_of_type_YE.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  80: 80 */    this.jdField_a_of_type_YP.a(new Color(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w));
/*  81:    */    
/*  82: 82 */    this.jdField_a_of_type_YE.b();
/*  83:    */    
/*  84: 84 */    this.jdField_a_of_type_EW.a().c(null);
/*  85: 85 */    this.jdField_b_of_type_EW.a().c(null);
/*  86: 86 */    this.jdField_d_of_type_EW.a().c(null);
/*  87: 87 */    this.jdField_c_of_type_EW.a().c(null);
/*  88: 88 */    this.jdField_a_of_type_YE.a().c(null);
/*  89: 89 */    this.jdField_a_of_type_YP.a(new Color(1, 1, 1, 1));
/*  90:    */    
/*  92: 92 */    GlUtil.c();
/*  93:    */  }
/*  94:    */  
/*  97:    */  public final float a()
/*  98:    */  {
/*  99: 99 */    return this.jdField_a_of_type_YE.a();
/* 100:    */  }
/* 101:    */  
/* 102:    */  public final float b()
/* 103:    */  {
/* 104:104 */    return this.jdField_a_of_type_YE.b();
/* 105:    */  }
/* 106:    */  
/* 114:    */  public final void c()
/* 115:    */  {
/* 116:116 */    this.jdField_d_of_type_YP = new yP(10, 10, d.k(), a());
/* 117:117 */    this.jdField_a_of_type_Ix = new ix(a());
/* 118:    */    
/* 119:119 */    this.jdField_a_of_type_YP = new yP(200, 200, d.n(), a());
/* 120:120 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 121:121 */    this.jdField_a_of_type_YP.b.add(this.jdField_b_of_type_JavaLangString);
/* 122:    */    
/* 124:124 */    this.jdField_b_of_type_YP = new yP(200, 30, d.d(), a());
/* 125:125 */    this.jdField_b_of_type_YP.b = new ArrayList();
/* 126:126 */    this.jdField_b_of_type_YP.b.add("Tutorial");
/* 127:127 */    this.jdField_c_of_type_YP = new yP(200, 30, d.k(), a());
/* 128:128 */    this.jdField_c_of_type_YP.b = new ArrayList();
/* 129:129 */    this.jdField_c_of_type_YP.b.add("(click to drag)");
/* 130:    */    
/* 131:131 */    this.jdField_a_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.o, "NEXT", this.jdField_a_of_type_BI);
/* 132:    */    
/* 133:133 */    this.jdField_b_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.p, "BACK", this.jdField_a_of_type_BI);
/* 134:    */    
/* 135:135 */    this.jdField_d_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.q, "SKIP", this.jdField_a_of_type_BI);
/* 136:    */    
/* 137:137 */    this.jdField_c_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.r, "END", this.jdField_a_of_type_BI);
/* 138:    */    
/* 140:140 */    if (jdField_a_of_type_Yq == null) {
/* 141:141 */      jdField_a_of_type_Yq = new yq(a(), this.jdField_a_of_type_YE);
/* 142:142 */      this.jdField_a_of_type_YE.h(48);
/* 143:143 */      this.jdField_a_of_type_YE.a().y -= 100.0F;
/* 144:    */    } else {
/* 145:145 */      this.jdField_a_of_type_YE.a().set(jdField_a_of_type_Yq.a.a());
/* 146:146 */      jdField_a_of_type_Yq = new yq(a(), this.jdField_a_of_type_YE);
/* 147:    */    }
/* 148:    */    
/* 151:151 */    this.jdField_a_of_type_YE.a(this.jdField_b_of_type_YP);
/* 152:152 */    this.jdField_a_of_type_YE.a(this.jdField_c_of_type_YP);
/* 153:153 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/* 154:154 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_EW);
/* 155:    */    
/* 156:156 */    this.jdField_a_of_type_YE.a(this.jdField_d_of_type_EW);
/* 157:    */    
/* 158:158 */    this.jdField_a_of_type_YE.a(jdField_a_of_type_Yq);
/* 159:    */    
/* 161:161 */    if ((this.jdField_a_of_type_BI.a() instanceof ce)) {
/* 162:162 */      this.jdField_d_of_type_YP.a("Show tutorial next time");
/* 163:163 */      this.jdField_a_of_type_YE.a(this.jdField_d_of_type_YP);
/* 164:164 */      this.jdField_a_of_type_YE.a(this.jdField_a_of_type_Ix);
/* 165:165 */      this.jdField_d_of_type_YP.a(133.0F, 165.0F, 0.0F);
/* 166:166 */      this.jdField_a_of_type_Ix.a(98.0F, 156.0F, 0.0F);
/* 167:    */    } else {
/* 168:168 */      this.jdField_a_of_type_YE.a(this.jdField_b_of_type_EW);
/* 169:169 */      this.jdField_a_of_type_YE.a(this.jdField_c_of_type_EW);
/* 170:    */    }
/* 171:    */    
/* 172:172 */    this.jdField_b_of_type_YP.a(14.0F, 10.0F, 0.0F);
/* 173:173 */    this.jdField_c_of_type_YP.a(280.0F, 15.0F, 0.0F);
/* 174:    */    
/* 175:175 */    this.jdField_a_of_type_YP.a(30.0F, 50.0F, 0.0F);
/* 176:    */    
/* 177:177 */    this.jdField_a_of_type_EW.b(0.5F, 0.5F, 0.5F);
/* 178:    */    
/* 179:179 */    this.jdField_b_of_type_EW.b(0.5F, 0.5F, 0.5F);
/* 180:180 */    this.jdField_d_of_type_EW.b(0.5F, 0.5F, 0.5F);
/* 181:181 */    this.jdField_c_of_type_EW.b(0.5F, 0.5F, 0.5F);
/* 182:    */    
/* 185:185 */    this.jdField_a_of_type_EW.a(330.0F, 158.0F, 0.0F);
/* 186:186 */    this.jdField_b_of_type_EW.a(250.0F, 158.0F, 0.0F);
/* 187:187 */    this.jdField_d_of_type_EW.a(110.0F, 158.0F, 0.0F);
/* 188:188 */    this.jdField_c_of_type_EW.a(30.0F, 158.0F, 0.0F);
/* 189:    */    
/* 193:193 */    this.jdField_d_of_type_EW.b(true);
/* 194:194 */    if (((this.jdField_a_of_type_BI.a() instanceof cf)) || (!(this.jdField_a_of_type_BI.a() instanceof cc))) {
/* 195:195 */      this.jdField_d_of_type_EW.b(false);
/* 196:    */    }
/* 197:197 */    this.jdField_a_of_type_Boolean = true;
/* 198:    */  }
/* 199:    */  
/* 200:    */  public final void a(float paramFloat)
/* 201:    */  {
/* 202:202 */    this.jdField_a_of_type_Float = paramFloat;
/* 203:    */  }
/* 204:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */