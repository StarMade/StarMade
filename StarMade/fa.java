/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import javax.vecmath.Vector4f;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   7:    */import org.schema.schine.network.client.ClientState;
/*   8:    */
/*  21:    */public final class fa
/*  22:    */  extends v
/*  23:    */{
/*  24:    */  private yE jdField_a_of_type_YE;
/*  25:    */  private eW jdField_a_of_type_EW;
/*  26:    */  private eW jdField_b_of_type_EW;
/*  27:    */  private yG jdField_a_of_type_YG;
/*  28:    */  private static yq jdField_a_of_type_Yq;
/*  29:    */  private boolean jdField_a_of_type_Boolean;
/*  30:    */  private w jdField_a_of_type_W;
/*  31:    */  private String jdField_b_of_type_JavaLangString;
/*  32: 32 */  private float jdField_a_of_type_Float = 0.0F;
/*  33:    */  private yP jdField_a_of_type_YP;
/*  34:    */  private yP jdField_b_of_type_YP;
/*  35:    */  private yP jdField_c_of_type_YP;
/*  36:    */  private Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*  37:    */  private String jdField_c_of_type_JavaLangString;
/*  38:    */  
/*  39:    */  public fa(ClientState paramClientState, w paramw, String paramString1, String paramString2)
/*  40:    */  {
/*  41: 41 */    super(paramClientState);
/*  42: 42 */    this.jdField_a_of_type_W = paramw;
/*  43: 43 */    this.jdField_c_of_type_JavaLangString = paramString2;
/*  44: 44 */    this.jdField_a_of_type_YE = new yE(xe.a().a("info-panel-gui-"), paramClientState);
/*  45:    */    
/*  46: 46 */    this.jdField_a_of_type_YG = new yG(363.0F, 110.0F, paramClientState);
/*  47: 47 */    this.jdField_b_of_type_JavaLangString = paramString1;
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
/*  76: 76 */    this.jdField_a_of_type_YE.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  77: 77 */    this.jdField_a_of_type_YP.a(new Color(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w));
/*  78:    */    
/*  79: 79 */    this.jdField_a_of_type_YE.b();
/*  80:    */    
/*  81: 81 */    this.jdField_a_of_type_EW.a().c(null);
/*  82: 82 */    this.jdField_a_of_type_YE.a().c(null);
/*  83: 83 */    this.jdField_a_of_type_YP.a(new Color(1, 1, 1, 1));
/*  84:    */    
/*  86: 86 */    GlUtil.c();
/*  87:    */  }
/*  88:    */  
/*  91:    */  public final float a()
/*  92:    */  {
/*  93: 93 */    return this.jdField_a_of_type_YE.a();
/*  94:    */  }
/*  95:    */  
/*  96:    */  public final float b()
/*  97:    */  {
/*  98: 98 */    return this.jdField_a_of_type_YE.b();
/*  99:    */  }
/* 100:    */  
/* 108:    */  public final void c()
/* 109:    */  {
/* 110:110 */    this.jdField_a_of_type_YP = new yP(220, 100, d.n(), a());
/* 111:111 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 112:112 */    this.jdField_a_of_type_YP.b.add(this.jdField_b_of_type_JavaLangString);
/* 113:    */    
/* 115:115 */    this.jdField_b_of_type_YP = new yP(200, 30, d.d(), a());
/* 116:116 */    this.jdField_b_of_type_YP.b = new ArrayList();
/* 117:117 */    this.jdField_b_of_type_YP.b.add(this.jdField_c_of_type_JavaLangString);
/* 118:118 */    this.jdField_c_of_type_YP = new yP(200, 30, d.k(), a());
/* 119:119 */    this.jdField_c_of_type_YP.b = new ArrayList();
/* 120:120 */    this.jdField_c_of_type_YP.b.add("(click to drag)");
/* 121:    */    
/* 122:122 */    this.jdField_a_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.c, "OK", this.jdField_a_of_type_W);
/* 123:    */    
/* 124:124 */    this.jdField_b_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.d, "CANCEL", this.jdField_a_of_type_W);
/* 125:    */    
/* 127:127 */    if (jdField_a_of_type_Yq == null) {
/* 128:128 */      jdField_a_of_type_Yq = new yq(a(), this.jdField_a_of_type_YE);
/* 129:129 */      this.jdField_a_of_type_YE.h(48);
/* 130:    */    } else {
/* 131:131 */      this.jdField_a_of_type_YE.a().set(jdField_a_of_type_Yq.a.a());
/* 132:132 */      jdField_a_of_type_Yq = new yq(a(), this.jdField_a_of_type_YE);
/* 133:    */    }
/* 134:    */    
/* 135:135 */    this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YP);
/* 136:    */    
/* 137:137 */    this.jdField_a_of_type_YE.a(this.jdField_b_of_type_YP);
/* 138:138 */    this.jdField_a_of_type_YE.a(this.jdField_c_of_type_YP);
/* 139:139 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YG);
/* 140:140 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_EW);
/* 141:141 */    this.jdField_a_of_type_YE.a(jdField_a_of_type_Yq);
/* 142:    */    
/* 146:146 */    this.jdField_b_of_type_YP.a(14.0F, 10.0F, 0.0F);
/* 147:147 */    this.jdField_c_of_type_YP.a(280.0F, 15.0F, 0.0F);
/* 148:    */    
/* 149:149 */    this.jdField_a_of_type_YG.a(23.0F, 42.0F, 0.0F);
/* 150:    */    
/* 151:151 */    this.jdField_a_of_type_EW.b(0.5F, 0.5F, 0.5F);
/* 152:    */    
/* 153:153 */    this.jdField_b_of_type_EW.b(0.5F, 0.5F, 0.5F);
/* 154:    */    
/* 157:157 */    this.jdField_a_of_type_EW.a(330.0F, 158.0F, 0.0F);
/* 158:158 */    this.jdField_b_of_type_EW.a(220.0F, 158.0F, 0.0F);
/* 159:    */    
/* 163:163 */    this.jdField_a_of_type_Boolean = true;
/* 164:    */  }
/* 165:    */  
/* 166:    */  public final void a(float paramFloat)
/* 167:    */  {
/* 168:168 */    this.jdField_a_of_type_Float = paramFloat;
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fa
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */