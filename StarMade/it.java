/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.lwjgl.input.Mouse;
/*   5:    */import org.schema.game.common.data.element.ElementInformation;
/*   6:    */import org.schema.game.common.data.element.ElementKeyMap;
/*   7:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   8:    */import org.schema.schine.network.client.ClientState;
/*   9:    */
/*  21:    */public final class it
/*  22:    */  extends yz
/*  23:    */{
/*  24:    */  private int jdField_a_of_type_Int;
/*  25:    */  private int jdField_b_of_type_Int;
/*  26:    */  private yE jdField_a_of_type_YE;
/*  27:    */  private yE jdField_b_of_type_YE;
/*  28:    */  private yE c;
/*  29:    */  private yE d;
/*  30:    */  private bz jdField_a_of_type_Bz;
/*  31:    */  private yP jdField_a_of_type_YP;
/*  32: 32 */  private boolean jdField_a_of_type_Boolean = true;
/*  33:    */  private yP jdField_b_of_type_YP;
/*  34:    */  
/*  35: 35 */  public it(ClientState paramClientState, bz parambz) { super(paramClientState);
/*  36: 36 */    this.jdField_a_of_type_Int = 400;
/*  37: 37 */    this.jdField_b_of_type_Int = 217;
/*  38: 38 */    this.jdField_a_of_type_Bz = parambz;
/*  39:    */  }
/*  40:    */  
/*  43:    */  public final void a() {}
/*  44:    */  
/*  47:    */  public final void b()
/*  48:    */  {
/*  49: 49 */    if (this.jdField_a_of_type_Boolean) {
/*  50: 50 */      c();
/*  51:    */    }
/*  52:    */    
/*  53: 53 */    GlUtil.d();
/*  54: 54 */    r();
/*  55:    */    
/*  56: 56 */    this.jdField_a_of_type_YE.m();
/*  57: 57 */    this.jdField_b_of_type_YE.m();
/*  58: 58 */    this.c.m();
/*  59: 59 */    this.d.m();
/*  60:    */    
/*  64: 64 */    this.jdField_a_of_type_YE.a_(cU.a.a((this.jdField_a_of_type_YE.a_()) && (Mouse.isButtonDown(0))));
/*  65: 65 */    this.jdField_a_of_type_YE.b();
/*  66:    */    
/*  67: 67 */    this.jdField_b_of_type_YE.a_(cU.b.a((this.jdField_b_of_type_YE.a_()) && (Mouse.isButtonDown(0))));
/*  68: 68 */    this.jdField_b_of_type_YE.b();
/*  69:    */    
/*  70: 70 */    this.c.a_(cU.k.a((this.c.a_()) && (Mouse.isButtonDown(0))));
/*  71: 71 */    this.c.b();
/*  72:    */    
/*  73: 73 */    this.d.a_(cU.l.a((this.d.a_()) && (Mouse.isButtonDown(0))));
/*  74: 74 */    this.d.b();
/*  75:    */    
/*  81: 81 */    if (this.jdField_a_of_type_Bz.a >= 0) {
/*  82: 82 */      ElementInformation localElementInformation = ElementKeyMap.getInfo(this.jdField_a_of_type_Bz.a);
/*  83: 83 */      this.jdField_b_of_type_YP.b.set(0, localElementInformation.getFullName());
/*  84:    */      
/*  85: 85 */      kf localkf = ((ct)a()).a();
/*  86: 86 */      long l = 0L;
/*  87: 87 */      if (localkf != null)
/*  88:    */      {
/*  89: 89 */        int i = localkf.a().a(localElementInformation.getId());
/*  90: 90 */        int j = localkf.a().a(i);
/*  91: 91 */        if ((i >= 0) && (j > 0))
/*  92:    */        {
/*  93: 93 */          l = localkf.a(localElementInformation, 1);
/*  94:    */        }
/*  95:    */      }
/*  96:    */      
/*  98: 98 */      if (l <= 0L) {
/*  99: 99 */        this.jdField_a_of_type_YP.b.set(0, "not for sale");
/* 100:    */      } else {
/* 101:101 */        this.jdField_a_of_type_YP.b.set(0, "Cost: " + l);
/* 102:    */      }
/* 103:    */    }
/* 104:    */    
/* 105:105 */    this.jdField_a_of_type_YP.b();
/* 106:106 */    this.jdField_b_of_type_YP.b();
/* 107:107 */    GlUtil.c();
/* 108:    */  }
/* 109:    */  
/* 112:    */  public final float a()
/* 113:    */  {
/* 114:114 */    return this.jdField_b_of_type_Int;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public final float b()
/* 118:    */  {
/* 119:119 */    return this.jdField_a_of_type_Int;
/* 120:    */  }
/* 121:    */  
/* 128:    */  public final void c()
/* 129:    */  {
/* 130:130 */    this.jdField_a_of_type_YE = new yE(xe.a().a("buttons-8x8-gui-"), a());
/* 131:131 */    this.jdField_b_of_type_YE = new yE(xe.a().a("buttons-8x8-gui-"), a());
/* 132:132 */    this.d = new yE(xe.a().a("buttons-8x8-gui-"), a());
/* 133:133 */    this.c = new yE(xe.a().a("buttons-8x8-gui-"), a());
/* 134:    */    
/* 135:135 */    this.jdField_a_of_type_YE.a_(cU.a.a(false));
/* 136:136 */    this.jdField_b_of_type_YE.a_(cU.b.a(false));
/* 137:137 */    this.c.a_(cU.k.a(false));
/* 138:138 */    this.d.a_(cU.l.a(false));
/* 139:    */    
/* 140:140 */    this.jdField_a_of_type_YE.a(16.0F, 47.0F, 0.0F);
/* 141:    */    
/* 142:142 */    this.jdField_b_of_type_YE.a(172.0F, 47.0F, 0.0F);
/* 143:143 */    this.c.a(16.0F, 115.0F, 0.0F);
/* 144:144 */    this.d.a(172.0F, 115.0F, 0.0F);
/* 145:145 */    this.c.a.set(0.8F, 0.8F, 0.8F);
/* 146:146 */    this.d.a.set(0.8F, 0.8F, 0.8F);
/* 147:    */    
/* 149:149 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_Bz);
/* 150:150 */    this.jdField_b_of_type_YE.a(this.jdField_a_of_type_Bz);
/* 151:151 */    this.c.a(this.jdField_a_of_type_Bz);
/* 152:152 */    this.d.a(this.jdField_a_of_type_Bz);
/* 153:    */    
/* 154:154 */    this.jdField_a_of_type_YE.a = "buy";
/* 155:155 */    this.jdField_b_of_type_YE.a = "sell";
/* 156:156 */    this.c.a = "buy_more";
/* 157:157 */    this.d.a = "sell_more";
/* 158:    */    
/* 159:159 */    this.jdField_a_of_type_YE.c();
/* 160:160 */    this.jdField_b_of_type_YE.c();
/* 161:161 */    this.c.c();
/* 162:162 */    this.d.c();
/* 163:    */    
/* 167:167 */    this.jdField_a_of_type_YP = new yP(200, 200, d.i(), a());
/* 168:168 */    this.jdField_a_of_type_YP.b = new ArrayList(1);
/* 169:169 */    this.jdField_a_of_type_YP.b.add("Cost: 0");
/* 170:170 */    this.jdField_a_of_type_YP.a(16.0F, 27.0F, 0.0F);
/* 171:    */    
/* 174:174 */    this.jdField_b_of_type_YP = new yP(200, 200, d.h(), a());
/* 175:175 */    this.jdField_b_of_type_YP.b = new ArrayList(1);
/* 176:    */    
/* 177:177 */    this.jdField_b_of_type_YP.b.add("");
/* 178:    */    
/* 179:179 */    this.jdField_b_of_type_YP.a(16.0F, 3.0F, 0.0F);
/* 180:    */    
/* 181:181 */    this.jdField_a_of_type_Boolean = false;
/* 182:    */  }
/* 183:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */