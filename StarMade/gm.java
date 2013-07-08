/*   1:    */import java.util.Collection;
/*   2:    */import java.util.Collections;
/*   3:    */import java.util.HashSet;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.Observable;
/*   6:    */import java.util.Observer;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */import javax.vecmath.Vector4f;
/*   9:    */import org.schema.schine.network.client.ClientState;
/*  10:    */
/*  28:    */public abstract class gm
/*  29:    */  extends yz
/*  30:    */  implements Observer
/*  31:    */{
/*  32:    */  private yG jdField_a_of_type_YG;
/*  33:    */  private yr jdField_a_of_type_Yr;
/*  34:    */  private yA jdField_a_of_type_YA;
/*  35: 35 */  boolean jdField_a_of_type_Boolean = true;
/*  36:    */  
/*  38:    */  private boolean jdField_b_of_type_Boolean;
/*  39:    */  
/*  41:    */  private yN jdField_a_of_type_YN;
/*  42:    */  
/*  43:    */  private yN jdField_b_of_type_YN;
/*  44:    */  private yN c;
/*  45:    */  private gs jdField_a_of_type_Gs;
/*  46:    */  private yN d;
/*  47:    */  
/*  48: 48 */  public gm(ClientState paramClientState, boolean paramBoolean) { this(paramClientState, 340, paramBoolean); }
/*  49:    */  
/*  50:    */  public gm(ClientState paramClientState, int paramInt, boolean paramBoolean) {
/*  51: 51 */    super(paramClientState);
/*  52: 52 */    this.jdField_b_of_type_Boolean = paramBoolean;
/*  53: 53 */    this.jdField_a_of_type_YG = new yG(542.0F, paramInt - 30, paramClientState);
/*  54: 54 */    this.jdField_a_of_type_Yr = new yr(paramClientState, 542.0F, 30.0F);
/*  55: 55 */    this.jdField_a_of_type_YA = new yA(a());
/*  56:    */  }
/*  57:    */  
/*  71:    */  public final void a() {}
/*  72:    */  
/*  86:    */  public abstract Collection a();
/*  87:    */  
/* 100:    */  public final void b()
/* 101:    */  {
/* 102:102 */    if (this.jdField_a_of_type_Boolean) {
/* 103:103 */      yA localyA = this.jdField_a_of_type_YA;gm localgm = this;Object localObject1 = a();HashSet localHashSet = new HashSet(); Object localObject3; for (Iterator localIterator = localyA.iterator(); localIterator.hasNext(); localHashSet.add(((fV)localObject3).a().a)) if ((!((localObject2 = (yD)localIterator.next()) instanceof fV)) || (!((localObject3 = (fV)localObject2).a() instanceof yC)) || (!((yC)((fV)localObject3).a()).c())) {} localyA.clear();int i = 0; for (Object localObject2 = ((Collection)localObject1).iterator(); ((Iterator)localObject2).hasNext(); i++) { localObject3 = (lL)((Iterator)localObject2).next();localObject1 = new fM(localgm.a(), localgm, (lL)localObject3, localgm.jdField_b_of_type_Boolean, i);localyA.a(new fV((yz)localObject1, (yz)localObject1, (lL)localObject3, localgm.a()));((yC)localObject1).a(localHashSet.contains(((lL)localObject3).a)); } if (localgm.jdField_a_of_type_Gs == null) localgm.a(gs.jdField_a_of_type_Gs); else localgm.b(localgm.jdField_a_of_type_Gs); localgm.b(localgm.jdField_a_of_type_Yr); if (localyA.size() > 0) localgm.a(localgm.jdField_a_of_type_Yr);
/* 104:104 */      this.jdField_a_of_type_Boolean = false;
/* 105:    */    }
/* 106:106 */    k();
/* 107:    */  }
/* 108:    */  
/* 151:    */  public final void a(gs paramgs)
/* 152:    */  {
/* 153:153 */    paramgs.jdField_a_of_type_JavaUtilComparator = Collections.reverseOrder(paramgs.jdField_a_of_type_JavaUtilComparator);
/* 154:154 */    b(paramgs);
/* 155:    */  }
/* 156:    */  
/* 157:157 */  private void b(gs paramgs) { Collections.sort(this.jdField_a_of_type_YA, paramgs.jdField_a_of_type_JavaUtilComparator);
/* 158:158 */    this.jdField_a_of_type_Gs = paramgs;
/* 159:159 */    this.jdField_a_of_type_YN.a.set(0.7F, 0.7F, 0.7F, 0.7F);
/* 160:160 */    this.jdField_b_of_type_YN.a.set(0.7F, 0.7F, 0.7F, 0.7F);
/* 161:161 */    this.c.a.set(0.7F, 0.7F, 0.7F, 0.7F);
/* 162:162 */    this.d.a.set(0.7F, 0.7F, 0.7F, 0.7F);
/* 163:163 */    switch (gr.a[paramgs.ordinal()]) {
/* 164:164 */    case 1:  this.jdField_a_of_type_YN.a.set(1.0F, 1.0F, 1.0F, 1.0F);break;
/* 165:165 */    case 2:  this.jdField_b_of_type_YN.a.set(1.0F, 1.0F, 1.0F, 1.0F);break;
/* 166:166 */    case 3:  this.c.a.set(1.0F, 1.0F, 1.0F, 1.0F);break;
/* 167:167 */    case 4:  this.d.a.set(1.0F, 1.0F, 1.0F, 1.0F);
/* 168:    */    }
/* 169:    */    
/* 170:170 */    for (paramgs = 0; paramgs < this.jdField_a_of_type_YA.size(); paramgs++) {
/* 171:171 */      ((fM)this.jdField_a_of_type_YA.a(paramgs).a()).a(paramgs);
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 175:    */  public final void c()
/* 176:    */  {
/* 177:177 */    this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YA);
/* 178:    */    
/* 179:179 */    this.jdField_a_of_type_YG.a().y = this.jdField_a_of_type_Yr.a();
/* 180:    */    
/* 184:184 */    this.jdField_a_of_type_YN = new yN(a(), 217, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Name", new gn(this));
/* 185:    */    
/* 199:199 */    this.jdField_b_of_type_YN = new yN(a(), 140, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Owner", new go(this));
/* 200:    */    
/* 214:214 */    this.c = new yN(a(), 90, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Price", new gp(this));
/* 215:    */    
/* 229:229 */    this.d = new yN(a(), 50, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Rating", new gq(this));
/* 230:    */    
/* 247:247 */    this.jdField_b_of_type_YN.a().x = (this.jdField_a_of_type_YN.a().x + 217.0F);
/* 248:    */    
/* 249:249 */    this.c.a().x = (this.jdField_b_of_type_YN.a().x + 140.0F);
/* 250:    */    
/* 251:251 */    this.d.a().x = (this.c.a().x + 90.0F);
/* 252:    */    
/* 253:253 */    this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YN);
/* 254:254 */    this.jdField_a_of_type_Yr.a(this.jdField_b_of_type_YN);
/* 255:255 */    this.jdField_a_of_type_Yr.a(this.c);
/* 256:256 */    this.jdField_a_of_type_Yr.a(this.d);
/* 257:    */    
/* 259:259 */    a(this.jdField_a_of_type_Yr);
/* 260:    */    
/* 261:261 */    a(this.jdField_a_of_type_YG);
/* 262:    */  }
/* 263:    */  
/* 266:    */  public final float a()
/* 267:    */  {
/* 268:268 */    return this.jdField_a_of_type_Yr.a() + this.jdField_a_of_type_YG.a();
/* 269:    */  }
/* 270:    */  
/* 271:    */  public final float b()
/* 272:    */  {
/* 273:273 */    return this.jdField_a_of_type_Yr.b();
/* 274:    */  }
/* 275:    */  
/* 284:    */  public void update(Observable paramObservable, Object paramObject)
/* 285:    */  {
/* 286:286 */    if ((paramObservable instanceof yC)) {
/* 287:287 */      this.jdField_a_of_type_YA.f();
/* 288:    */    }
/* 289:    */  }
/* 290:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */