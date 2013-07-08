/*  1:   */import java.util.ArrayList;
/*  2:   */import java.util.List;
/*  3:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  4:   */import org.schema.schine.network.client.ClientState;
/*  5:   */
/* 22:   */public final class in
/* 23:   */  extends yz
/* 24:   */{
/* 25:   */  public yP a;
/* 26:   */  public yP b;
/* 27:   */  public yP c;
/* 28:   */  private yE a;
/* 29:   */  
/* 30:   */  public in(ClientState paramClientState)
/* 31:   */  {
/* 32:32 */    super(paramClientState);
/* 33:   */    
/* 98:98 */    new q();this.jdField_a_of_type_YE = new yE(xe.a().a("top-bar-gui-"), paramClientState);
/* 99:   */  }
/* 100:   */  
/* 103:   */  public final void a() {}
/* 104:   */  
/* 106:   */  protected final void d()
/* 107:   */  {
/* 108:43 */    this.jdField_a_of_type_YE.h(36);
/* 109:   */  }
/* 110:   */  
/* 111:   */  public final void b()
/* 112:   */  {
/* 113:48 */    if (k()) {
/* 114:49 */      d();
/* 115:   */    }
/* 116:51 */    GlUtil.d();
/* 117:   */    
/* 118:53 */    r();
/* 119:54 */    this.jdField_a_of_type_YE.b();
/* 120:   */    
/* 121:56 */    GlUtil.c();
/* 122:   */  }
/* 123:   */  
/* 124:   */  public final float a() {
/* 125:60 */    return 64.0F;
/* 126:   */  }
/* 127:   */  
/* 128:   */  public final float b() {
/* 129:64 */    return 768.0F;
/* 130:   */  }
/* 131:   */  
/* 135:   */  public final void c()
/* 136:   */  {
/* 137:72 */    this.jdField_a_of_type_YP = new yP(300, 40, d.j(), a());
/* 138:73 */    this.jdField_a_of_type_YP.b = new ArrayList(1);
/* 139:74 */    this.jdField_a_of_type_YP.b.add("0");
/* 140:75 */    this.jdField_a_of_type_YP.a().x = 240.0F;
/* 141:76 */    this.jdField_a_of_type_YP.a().y = 34.0F;
/* 142:   */    
/* 143:78 */    this.b = new yP(300, 40, d.k(), a());
/* 144:79 */    this.b.b = new ArrayList(1);
/* 145:80 */    this.b.b.add("");
/* 146:81 */    this.b.b.add("");
/* 147:82 */    this.b.a().x = 398.0F;
/* 148:83 */    this.b.a().y = 30.0F;
/* 149:   */    
/* 150:85 */    this.c = new yP(300, 40, d.j(), a());
/* 151:86 */    this.c.b = new ArrayList(1);
/* 152:87 */    this.c.b.add("0");
/* 153:88 */    this.c.a().x = 609.0F;
/* 154:89 */    this.c.a().y = 34.0F;
/* 155:   */    
/* 158:93 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/* 159:94 */    this.jdField_a_of_type_YE.a(this.c);
/* 160:95 */    this.jdField_a_of_type_YE.a(this.b);
/* 161:   */  }
/* 162:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     in
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */