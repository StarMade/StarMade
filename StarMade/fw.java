/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.lwjgl.input.Keyboard;
/*   5:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   6:    */import org.schema.schine.network.client.ClientState;
/*   7:    */
/*  29:    */public final class fw
/*  30:    */  extends yz
/*  31:    */{
/*  32:    */  private yr jdField_a_of_type_Yr;
/*  33:    */  private yr jdField_b_of_type_Yr;
/*  34:    */  private yG jdField_a_of_type_YG;
/*  35:    */  private yP jdField_a_of_type_YP;
/*  36:    */  private yP jdField_b_of_type_YP;
/*  37:    */  private io jdField_a_of_type_Io;
/*  38: 38 */  private boolean jdField_a_of_type_Boolean = true;
/*  39:    */  private yA jdField_a_of_type_YA;
/*  40:    */  
/*  41:    */  public fw(ClientState paramClientState) {
/*  42: 42 */    super(paramClientState);
/*  43: 43 */    this.jdField_a_of_type_Yr = new yr(a(), 300.0F, 300.0F);
/*  44: 44 */    this.jdField_a_of_type_Io = new io(paramClientState);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public final void a(yz paramyz)
/*  48:    */  {
/*  49: 49 */    this.jdField_a_of_type_Yr.a(paramyz);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public final void a()
/*  53:    */  {
/*  54: 54 */    this.jdField_a_of_type_Yr.a();
/*  55:    */  }
/*  56:    */  
/*  57:    */  public final void b(yz paramyz) {
/*  58: 58 */    this.jdField_a_of_type_Yr.b(paramyz);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public final void b() {
/*  62: 62 */    if (this.jdField_a_of_type_Boolean) {
/*  63: 63 */      c();
/*  64:    */    }
/*  65:    */    
/*  67:    */    String str;
/*  68:    */    
/*  69: 69 */    if (!(str = "press " + cv.U.b() + "\nto enter advanced\nbuild mode\n\n").equals(this.jdField_a_of_type_YP.b.get(0))) {
/*  70: 70 */      this.jdField_a_of_type_YP.b.set(0, str);
/*  71:    */    }
/*  72: 72 */    GlUtil.d();
/*  73:    */    
/*  74: 74 */    this.jdField_a_of_type_Io.h(17);
/*  75: 75 */    this.jdField_a_of_type_Io.b();
/*  76:    */    
/*  77: 77 */    r();
/*  78: 78 */    this.jdField_a_of_type_Yr.b();
/*  79: 79 */    GlUtil.c();
/*  80:    */  }
/*  81:    */  
/*  82:    */  public final ai a() {
/*  83: 83 */    return ((ct)a()).a().a.a.a.a();
/*  84:    */  }
/*  85:    */  
/*  86:    */  public final az a() {
/*  87:    */    ar localar;
/*  88: 88 */    if ((localar = ((ct)a()).a().a.a.a).a().a().a.c) {
/*  89: 89 */      return localar.a().a().a.a();
/*  90:    */    }
/*  91: 91 */    return localar.a().a().a();
/*  92:    */  }
/*  93:    */  
/*  94:    */  public final float a()
/*  95:    */  {
/*  96: 96 */    return this.jdField_a_of_type_Yr.a();
/*  97:    */  }
/*  98:    */  
/* 100:    */  public final float b()
/* 101:    */  {
/* 102:102 */    return this.jdField_a_of_type_Yr.b();
/* 103:    */  }
/* 104:    */  
/* 105:    */  public final boolean a_() {
/* 106:106 */    return this.jdField_a_of_type_Yr.a_();
/* 107:    */  }
/* 108:    */  
/* 115:    */  public final void c()
/* 116:    */  {
/* 117:117 */    this.jdField_a_of_type_Io.c();
/* 118:118 */    this.jdField_a_of_type_YP = new yP(100, 30, d.c(), a());
/* 119:119 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 120:    */    
/* 121:121 */    this.jdField_a_of_type_YP.b.add("press " + cv.U.b() + "\nto enter advanced\nbuild mode\n\n");
/* 122:    */    
/* 123:123 */    this.jdField_a_of_type_YP.b.add("press " + cv.t.b() + "\nto reset the camera\n\n");
/* 124:    */    
/* 125:125 */    this.jdField_a_of_type_YP.b.add("hold Shift\nto move faster\n\n");
/* 126:    */    
/* 127:127 */    this.jdField_a_of_type_YP.b.add("press " + cv.r.b() + "\nfor flight mode\n\n");
/* 128:    */    
/* 129:129 */    this.jdField_a_of_type_YP.b.add("Shift + MouseWheel to zoom\n\n");
/* 130:    */    
/* 131:131 */    this.jdField_b_of_type_Yr = new yr(a());
/* 132:    */    
/* 133:133 */    this.jdField_b_of_type_YP = new yP(100, 30, d.d(), a());
/* 134:134 */    this.jdField_b_of_type_YP.b = new ArrayList();
/* 135:135 */    this.jdField_b_of_type_YP.b.add("");
/* 136:    */    
/* 137:137 */    this.jdField_b_of_type_YP.a().y = 100.0F;
/* 138:    */    
/* 139:139 */    this.jdField_a_of_type_YG = new yG(533.0F, 316.0F, a());
/* 140:140 */    this.jdField_a_of_type_YG.a().set(0.0F, 0.0F, 0.0F);
/* 141:    */    
/* 142:142 */    this.jdField_a_of_type_YA = new yA(a());
/* 143:    */    
/* 144:144 */    this.jdField_a_of_type_YA.a(a());
/* 145:145 */    this.jdField_b_of_type_Yr.a(this.jdField_a_of_type_YA);
/* 146:146 */    this.jdField_b_of_type_Yr.a(this.jdField_b_of_type_YP);
/* 147:    */    
/* 149:149 */    this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YP);
/* 150:    */    
/* 153:153 */    this.jdField_a_of_type_YA.a(this.jdField_b_of_type_YP);
/* 154:    */    
/* 155:155 */    this.jdField_a_of_type_Yr.c();
/* 156:    */    
/* 157:157 */    fw localfw = this;this.jdField_a_of_type_YA.clear();localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 100, "Remove Mode", new fx(localfw, localfw.a()), false));localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "X", new fz(localfw.a(), localfw.a().a), false));localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "Y", new fz(localfw.a(), localfw.a().b), false));localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "Z", new fz(localfw.a(), localfw.a().c), false));Object localObject = new yM(localfw.a(), 90, "Orientation", new fF(localfw.a()), false);localfw.jdField_a_of_type_YA.a((yD)localObject);(localObject = new yP(100, 40, d.d(), localfw.a())).a("Symmetry Build Planes");localfw.jdField_a_of_type_YA.a(new yD((yz)localObject, (yz)localObject, localfw.a()));localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "XY-Plane", new fC(localfw.a(), 1), false));localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "XZ-Plane", new fC(localfw.a(), 2), false));localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "YZ-Plane", new fC(localfw.a(), 4), false));localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 100, "Odd-sym Mode", new fy(localfw, localfw.a()), false));
/* 158:    */    
/* 159:159 */    super.a(this.jdField_a_of_type_Yr);
/* 160:    */    
/* 161:161 */    this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YG);
/* 162:    */    
/* 163:163 */    d();
/* 164:    */    
/* 166:166 */    this.jdField_a_of_type_Yr.a().set(xm.b() - 270, 64.0F, 0.0F);
/* 167:167 */    this.jdField_a_of_type_Yr.g = true;
/* 168:    */    
/* 169:169 */    this.jdField_a_of_type_Boolean = false;
/* 170:    */  }
/* 171:    */  
/* 233:    */  public final void a(xq paramxq)
/* 234:    */  {
/* 235:235 */    super.a(paramxq);
/* 236:236 */    if (Keyboard.isKeyDown(cv.U.a())) {
/* 237:237 */      this.jdField_a_of_type_YG.c(this.jdField_b_of_type_Yr);return;
/* 238:    */    }
/* 239:239 */    this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YP);
/* 240:    */  }
/* 241:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */