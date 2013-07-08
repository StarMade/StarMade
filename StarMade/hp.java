/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import org.schema.schine.network.client.ClientState;
/*   4:    */
/*  18:    */public final class hp
/*  19:    */  extends yz
/*  20:    */{
/*  21:    */  private hH jdField_a_of_type_HH;
/*  22:    */  private yr jdField_a_of_type_Yr;
/*  23:    */  private yr b;
/*  24:    */  private hj jdField_a_of_type_Hj;
/*  25:    */  private yP jdField_a_of_type_YP;
/*  26:    */  lP jdField_a_of_type_LP;
/*  27:    */  
/*  28:    */  public hp(ClientState paramClientState)
/*  29:    */  {
/*  30: 30 */    super(paramClientState);
/*  31:    */  }
/*  32:    */  
/*  35:    */  public final void a() {}
/*  36:    */  
/*  38:    */  public final void b()
/*  39:    */  {
/*  40: 40 */    k();
/*  41:    */  }
/*  42:    */  
/*  43:    */  private aO a() {
/*  44: 44 */    return ((ct)a()).a().a.a.a;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public final void c()
/*  48:    */  {
/*  49: 49 */    this.jdField_a_of_type_Yr = new yr(a(), 400.0F, 40.0F);
/*  50: 50 */    this.b = new yr(a(), 400.0F, 40.0F);
/*  51:    */    
/*  52: 52 */    this.jdField_a_of_type_HH = new hq(a(), a());
/*  53:    */    
/*  79: 79 */    this.jdField_a_of_type_Hj = new hj(a(), ((ct)a()).a().a.a.a);
/*  80:    */    
/*  81: 81 */    this.jdField_a_of_type_Hj.c();
/*  82: 82 */    this.b.a().y = 40.0F;
/*  83: 83 */    this.jdField_a_of_type_Hj.a().y = 80.0F;
/*  84: 84 */    this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_Hj);
/*  85: 85 */    this.jdField_a_of_type_Yr.c();
/*  86:    */    
/*  87: 87 */    this.jdField_a_of_type_YP = new yP(100, 40, d.d(), a());
/*  88:    */    
/*  89: 89 */    this.jdField_a_of_type_YP.b = new ArrayList();
/*  90: 90 */    this.jdField_a_of_type_YP.b.add(new hr(this));
/*  91:    */    
/*  97:    */    yP localyP;
/*  98:    */    
/* 104:104 */    (localyP = new yP(100, 30, d.h(), a())).a(new hs(this));
/* 105:    */    
/* 133:    */    hu localhu;
/* 134:    */    
/* 161:161 */    (localhu = new hu(this, a(), "Abandon Home", new ht(this), a())).a().x = 400.0F;
/* 162:    */    
/* 163:163 */    this.b.a(localyP);
/* 164:164 */    this.b.a(localhu);
/* 165:    */    
/* 166:166 */    this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YP);
/* 167:167 */    this.jdField_a_of_type_HH.a().y = 140.0F;
/* 168:168 */    this.jdField_a_of_type_HH.c();
/* 169:169 */    a(this.jdField_a_of_type_HH);
/* 170:170 */    a(this.jdField_a_of_type_Yr);
/* 171:171 */    a(this.b);
/* 172:    */  }
/* 173:    */  
/* 174:    */  public final float a()
/* 175:    */  {
/* 176:176 */    if (this.jdField_a_of_type_HH != null) return this.jdField_a_of_type_HH.a(); return 0.0F;
/* 177:    */  }
/* 178:    */  
/* 179:    */  public final float b()
/* 180:    */  {
/* 181:181 */    if (this.jdField_a_of_type_HH != null) return this.jdField_a_of_type_HH.b(); return 0.0F;
/* 182:    */  }
/* 183:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */