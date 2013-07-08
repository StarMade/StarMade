/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import org.schema.game.common.data.world.Segment;
/*   3:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   4:    */import org.schema.schine.network.client.ClientState;
/*   5:    */
/*  21:    */public final class fr
/*  22:    */  extends yz
/*  23:    */{
/*  24:    */  private yE jdField_a_of_type_YE;
/*  25:    */  private yG jdField_a_of_type_YG;
/*  26: 26 */  private boolean jdField_a_of_type_Boolean = true;
/*  27:    */  private yA jdField_a_of_type_YA;
/*  28:    */  
/*  29:    */  public fr(ClientState paramClientState) {
/*  30: 30 */    super(paramClientState);
/*  31: 31 */    this.jdField_a_of_type_YE = new yE(xe.a().a("ai-panel-gui-"), a());
/*  32:    */  }
/*  33:    */  
/*  35:    */  public final void a(yz paramyz)
/*  36:    */  {
/*  37: 37 */    this.jdField_a_of_type_YE.a(paramyz);
/*  38:    */  }
/*  39:    */  
/*  40:    */  public final void a() {
/*  41: 41 */    this.jdField_a_of_type_YE.a();
/*  42:    */  }
/*  43:    */  
/*  44:    */  public final void b(yz paramyz) {
/*  45: 45 */    this.jdField_a_of_type_YE.b(paramyz);
/*  46:    */  }
/*  47:    */  
/*  48:    */  public final void b() {
/*  49: 49 */    if (this.jdField_a_of_type_Boolean) {
/*  50: 50 */      c();
/*  51:    */    }
/*  52:    */    
/*  55: 55 */    GlUtil.d();
/*  56: 56 */    r();
/*  57: 57 */    this.jdField_a_of_type_YE.b();
/*  58: 58 */    GlUtil.c();
/*  59:    */  }
/*  60:    */  
/*  61:    */  private V a() {
/*  62: 62 */    return ((ct)a()).a().a.a.a;
/*  63:    */  }
/*  64:    */  
/*  66:    */  public final float a()
/*  67:    */  {
/*  68: 68 */    return this.jdField_a_of_type_YE.a();
/*  69:    */  }
/*  70:    */  
/*  71:    */  public final float b() {
/*  72: 72 */    return this.jdField_a_of_type_YE.b();
/*  73:    */  }
/*  74:    */  
/*  84:    */  public final void c()
/*  85:    */  {
/*  86: 86 */    this.jdField_a_of_type_YG = new yG(533.0F, 316.0F, a());
/*  87: 87 */    this.jdField_a_of_type_YG.a().set(251.0F, 107.0F, 0.0F);
/*  88:    */    
/*  89: 89 */    this.jdField_a_of_type_YA = new yA(a());
/*  90:    */    
/*  91: 91 */    this.jdField_a_of_type_YA.a(a());
/*  92:    */    
/*  93: 93 */    this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YA);
/*  94:    */    
/*  96: 96 */    this.jdField_a_of_type_YE.c();
/*  97:    */    
/*  99: 99 */    super.a(this.jdField_a_of_type_YE);
/* 100:    */    
/* 101:101 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YG);
/* 102:    */    
/* 103:103 */    d();
/* 104:    */    
/* 105:105 */    this.jdField_a_of_type_Boolean = false;
/* 106:    */  }
/* 107:    */  
/* 108:    */  private void a(kp paramkp) {
/* 109:109 */    this.jdField_a_of_type_YA.clear();
/* 110:    */    
/* 111:111 */    if (paramkp != null)
/* 112:    */    {
/* 113:113 */      int i = (paramkp = paramkp.a()).length; for (int j = 0; j < i; j++) { ko localko;
/* 114:114 */        Object localObject; if (((localko = paramkp[j]).a() instanceof Boolean)) {
/* 115:115 */          localObject = new fs(a(), localko);
/* 116:116 */          this.jdField_a_of_type_YA.a(new yM(a(), localko.a(), (yL)localObject, true));
/* 117:    */        } else {
/* 118:118 */          localObject = new ft(a(), localko);
/* 119:119 */          this.jdField_a_of_type_YA.a(new yM(a(), localko.a(), (yL)localObject, true));
/* 120:    */        }
/* 121:    */      }
/* 122:    */    }
/* 123:    */  }
/* 124:    */  
/* 125:    */  public final void a(xq paramxq)
/* 126:    */  {
/* 127:127 */    super.a(paramxq);
/* 128:    */    
/* 129:129 */    if (a().d) {
/* 130:130 */      if (a().a != null) {
/* 131:131 */        if (!(a().a.a().a() instanceof wp)) {
/* 132:132 */          ((ct)a()).a().b("this structure has no AI");
/* 133:133 */          return;
/* 134:    */        }
/* 135:135 */        a((kp)((wp)a().a.a().a()).a());
/* 136:    */      } else {
/* 137:137 */        a(null);
/* 138:    */      }
/* 139:139 */      a().d = false;
/* 140:    */    }
/* 141:    */  }
/* 142:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */