/*   1:    */import java.awt.Color;
/*   2:    */import java.awt.Font;
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.List;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */import org.newdawn.slick.SlickException;
/*   7:    */import org.newdawn.slick.UnicodeFont;
/*   8:    */import org.newdawn.slick.font.effects.ColorEffect;
/*   9:    */import org.newdawn.slick.font.effects.OutlineEffect;
/*  10:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  11:    */import org.schema.schine.network.client.ClientState;
/*  12:    */
/*  23:    */public final class fo
/*  24:    */  extends yz
/*  25:    */{
/*  26:    */  private yP jdField_a_of_type_YP;
/*  27:    */  private yP jdField_b_of_type_YP;
/*  28:    */  private yE jdField_a_of_type_YE;
/*  29:    */  private String jdField_b_of_type_JavaLangString;
/*  30: 30 */  private boolean jdField_a_of_type_Boolean = true;
/*  31:    */  
/*  32:    */  private static UnicodeFont jdField_a_of_type_OrgNewdawnSlickUnicodeFont;
/*  33:    */  
/*  35:    */  public fo(ClientState paramClientState, String paramString)
/*  36:    */  {
/*  37: 37 */    super(paramClientState);
/*  38: 38 */    this.jdField_b_of_type_JavaLangString = paramString;
/*  39:    */  }
/*  40:    */  
/*  42:    */  public final void a()
/*  43:    */  {
/*  44: 44 */    this.jdField_a_of_type_YE.a();
/*  45: 45 */    this.jdField_a_of_type_YP.a();
/*  46:    */  }
/*  47:    */  
/*  48:    */  public final void b() {
/*  49: 49 */    if (this.jdField_a_of_type_Boolean) {
/*  50: 50 */      c();
/*  51:    */    }
/*  52: 52 */    this.jdField_a_of_type_YP.b.set(0, this.jdField_b_of_type_JavaLangString);
/*  53: 53 */    GlUtil.d();
/*  54: 54 */    GL11.glEnable(3042);
/*  55: 55 */    GL11.glBlendFunc(770, 771);
/*  56: 56 */    r();
/*  57: 57 */    if (0L < System.currentTimeMillis()) {
/*  58: 58 */      this.jdField_b_of_type_YP.b.clear();
/*  59:    */    }
/*  60: 60 */    this.jdField_a_of_type_YE.b();
/*  61:    */    
/*  63: 63 */    GL11.glDisable(3042);
/*  64: 64 */    GlUtil.c();
/*  65:    */  }
/*  66:    */  
/*  67:    */  public final float a()
/*  68:    */  {
/*  69: 69 */    return 256.0F;
/*  70:    */  }
/*  71:    */  
/*  79:    */  public final float b()
/*  80:    */  {
/*  81: 81 */    return 256.0F;
/*  82:    */  }
/*  83:    */  
/*  91:    */  public final void c()
/*  92:    */  {
/*  93: 93 */    if (jdField_a_of_type_OrgNewdawnSlickUnicodeFont == null) {
/*  94: 94 */      localObject = new Font("Arial", 1, 28);
/*  95: 95 */      (
/*  96: 96 */        fo.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont((Font)localObject)).getEffects().add(new OutlineEffect(4, Color.black));
/*  97: 97 */      jdField_a_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
/*  98: 98 */      jdField_a_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
/*  99:    */      try {
/* 100:100 */        jdField_a_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
/* 101:101 */      } catch (SlickException localSlickException) { 
/* 102:    */        
/* 103:103 */          localSlickException;
/* 104:    */      }
/* 105:    */    }
/* 106:    */    
/* 107:105 */    this.jdField_a_of_type_YP = new yP(256, 64, jdField_a_of_type_OrgNewdawnSlickUnicodeFont, a());
/* 108:    */    
/* 110:108 */    this.jdField_b_of_type_YP = new yP(256, 64, a());
/* 111:109 */    this.jdField_a_of_type_YE = new yE(xe.a().a("panel-std-gui-"), a());
/* 112:    */    
/* 115:113 */    (
/* 116:114 */      localObject = new ArrayList()).add(this.jdField_b_of_type_JavaLangString);
/* 117:115 */    this.jdField_a_of_type_YP.b = ((List)localObject);
/* 118:    */    
/* 119:117 */    Object localObject = new ArrayList();
/* 120:118 */    this.jdField_b_of_type_YP.b = ((List)localObject);
/* 121:119 */    this.jdField_a_of_type_YE.h(48);
/* 122:    */    
/* 124:122 */    this.jdField_a_of_type_YE.c();
/* 125:123 */    this.jdField_a_of_type_YP.c();
/* 126:    */    
/* 127:125 */    a(this.jdField_a_of_type_YE);
/* 128:126 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/* 129:127 */    this.jdField_a_of_type_YE.a(this.jdField_b_of_type_YP);
/* 130:    */    
/* 132:130 */    this.jdField_a_of_type_YP.a(280.0F, 80.0F, 0.0F);
/* 133:131 */    this.jdField_b_of_type_YP.a(300.0F, 30.0F, 0.0F);
/* 134:    */    
/* 135:133 */    this.jdField_a_of_type_Boolean = false;
/* 136:    */  }
/* 137:    */  
/* 146:    */  public final void a(String paramString)
/* 147:    */  {
/* 148:146 */    this.jdField_b_of_type_JavaLangString = paramString;
/* 149:    */  }
/* 150:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */