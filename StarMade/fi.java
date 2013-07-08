/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.Observable;
/*   5:    */import java.util.Observer;
/*   6:    */import java.util.concurrent.ConcurrentHashMap;
/*   7:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   8:    */import org.schema.schine.network.NetworkStateContainer;
/*   9:    */import org.schema.schine.network.client.ClientState;
/*  10:    */import org.schema.schine.network.objects.Sendable;
/*  11:    */
/* 153:    */public final class fi
/* 154:    */  extends yz
/* 155:    */  implements Observer
/* 156:    */{
/* 157:    */  private yE jdField_a_of_type_YE;
/* 158:    */  private yG jdField_a_of_type_YG;
/* 159:    */  private yA jdField_a_of_type_YA;
/* 160:160 */  private boolean jdField_a_of_type_Boolean = true;
/* 161:    */  
/* 163:    */  private boolean b;
/* 164:    */  
/* 165:165 */  private ConcurrentHashMap jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap = new ConcurrentHashMap();
/* 166:    */  
/* 167:167 */  public fi(ClientState paramClientState) { super(paramClientState);
/* 168:    */    
/* 169:169 */    fi localfi = this;this.jdField_a_of_type_YE = new yE(xe.a().a("panel-std-gui-"), localfi.a());localfi.jdField_a_of_type_YG = new yG(512.0F, 366.0F, localfi.a());localfi.jdField_a_of_type_YA = new yA(localfi.a());localfi.jdField_a_of_type_YG.c(localfi.jdField_a_of_type_YA);localfi.a(localfi.jdField_a_of_type_YE);localfi.jdField_a_of_type_YE.a(localfi.jdField_a_of_type_YG);localfi.jdField_a_of_type_YG.a(280.0F, 64.0F, 0.0F);
/* 170:    */    
/* 171:171 */    ((ct)paramClientState).addObserver(this);
/* 172:    */  }
/* 173:    */  
/* 177:    */  public final void a() {}
/* 178:    */  
/* 182:    */  public final void b()
/* 183:    */  {
/* 184:184 */    if (this.jdField_a_of_type_Boolean) {
/* 185:185 */      c();
/* 186:    */    }
/* 187:    */    
/* 188:188 */    GlUtil.d();
/* 189:189 */    r();
/* 190:190 */    this.jdField_a_of_type_YE.b();
/* 191:191 */    GlUtil.c();
/* 192:    */  }
/* 193:    */  
/* 196:    */  public final float a()
/* 197:    */  {
/* 198:198 */    return this.jdField_a_of_type_YE.a();
/* 199:    */  }
/* 200:    */  
/* 207:    */  public final float b()
/* 208:    */  {
/* 209:209 */    return this.jdField_a_of_type_YE.b();
/* 210:    */  }
/* 211:    */  
/* 239:    */  public final void c()
/* 240:    */  {
/* 241:241 */    this.jdField_a_of_type_YE.c();
/* 242:242 */    this.jdField_a_of_type_YG.c();
/* 243:243 */    this.jdField_a_of_type_Boolean = false;
/* 244:244 */    (
/* 245:245 */      localObject = new fk(a())).a("NAME", "KILLS", "DEATHS", "PING", "TEAM");
/* 246:246 */    Object localObject = new yD((yz)localObject, (yz)localObject, a());
/* 247:247 */    this.jdField_a_of_type_YA.a((yD)localObject);
/* 248:    */  }
/* 249:    */  
/* 274:    */  public final void update(Observable paramObservable, Object paramObject)
/* 275:    */  {
/* 276:276 */    this.b = true;
/* 277:    */  }
/* 278:    */  
/* 281:    */  public final void a(xq paramxq)
/* 282:    */  {
/* 283:283 */    super.a(paramxq);
/* 284:284 */    if (this.b) {
/* 285:285 */      fi localfi = this; synchronized (a().getLocalAndRemoteObjectContainer().getLocalObjects()) { Object localObject1; for (int j = 0; j < localfi.jdField_a_of_type_YA.size(); j++) if (((localObject1 = (lE)localfi.jdField_a_of_type_YA.a(j).a) != null) && (!localfi.a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((lE)localObject1).getId()))) { localfi.jdField_a_of_type_YA.b(j);localfi.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject1);j--; } for (Iterator localIterator = localfi.a().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); localfi.jdField_a_of_type_YA.a(new fj(localfi, (lE)localObject1, localfi.a()))) if ((!((localObject1 = (Sendable)localIterator.next()) instanceof lE)) || (localfi.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.containsKey(localObject1))) {} } localfi.jdField_a_of_type_YG.e();
/* 286:286 */      this.b = false;
/* 287:    */    }
/* 288:    */    
/* 289:289 */    for (int i = 0; i < this.jdField_a_of_type_YA.size(); i++) {
/* 290:290 */      this.jdField_a_of_type_YA.a(i).a(paramxq);
/* 291:    */    }
/* 292:    */  }
/* 293:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */