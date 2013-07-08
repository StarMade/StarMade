/*   1:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.util.HashSet;
/*   4:    */import org.schema.game.client.view.SegmentDrawer;
/*   5:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   6:    */import org.schema.game.common.data.world.SegmentData;
/*   7:    */
/* 172:    */public final class dd
/* 173:    */  extends Thread
/* 174:    */{
/* 175:    */  private dL jdField_a_of_type_DL;
/* 176:    */  private mr jdField_a_of_type_Mr;
/* 177:    */  private mr b;
/* 178:    */  private de jdField_a_of_type_De;
/* 179:    */  
/* 180:    */  public dd(SegmentDrawer paramSegmentDrawer, de paramde)
/* 181:    */  {
/* 182:182 */    super("LightUpdate" + SegmentDrawer.jdField_a_of_type_Int++);
/* 183:183 */    setPriority(4);
/* 184:184 */    this.jdField_a_of_type_DL = new dL();
/* 185:185 */    this.jdField_a_of_type_De = paramde;
/* 186:    */  }
/* 187:    */  
/* 188:    */  public final void a(mr parammr) {
/* 189:189 */    synchronized (this) {
/* 190:190 */      this.jdField_a_of_type_Mr = parammr;
/* 191:191 */      notify();
/* 192:192 */      return;
/* 193:    */    }
/* 194:    */  }
/* 195:    */  
/* 196:    */  private void a(mr parammr, CubeMeshBufferContainer paramCubeMeshBufferContainer, dG paramdG) {
/* 197:197 */    for (;;) { try { if ((!parammr.g()) && (parammr.a() != null))
/* 198:    */        {
/* 199:199 */          synchronized (localSegmentData1 = parammr.a()) {
/* 200:    */            SegmentData localSegmentData1;
/* 201:200 */            CubeMeshBufferContainer localCubeMeshBufferContainer = paramCubeMeshBufferContainer;SegmentData localSegmentData2 = localSegmentData1;dd localdd = this; if (localSegmentData2.getSize() > 0) { if ((!jdField_a_of_type_Boolean) && (localSegmentData2 == null)) throw new AssertionError(); localdd.jdField_a_of_type_DL.a(localCubeMeshBufferContainer);
/* 202:    */            }
/* 203:202 */            localCubeMeshBufferContainer = paramCubeMeshBufferContainer;localSegmentData2 = localSegmentData1;localdd = this; if ((!jdField_a_of_type_Boolean) && (localSegmentData2 == null)) throw new AssertionError(); localdd.jdField_a_of_type_DL.a(localSegmentData2, localCubeMeshBufferContainer);
/* 204:    */            
/* 205:204 */            SegmentDrawer.a(paramdG, localSegmentData1, paramCubeMeshBufferContainer);
/* 206:205 */            return;
/* 207:    */          }
/* 208:    */        }
/* 209:    */        
/* 212:211 */        return;
/* 213:    */      }
/* 214:    */      catch (Exception localException2)
/* 215:    */      {
/* 216:    */        Exception localException1;
/* 217:208 */        (localException1 = 
/* 218:    */        
/* 220:211 */          localException2).printStackTrace();System.err.println("[CLIENT] Exception: " + localException1.getClass().getSimpleName() + " in computing Lighting. retrying");
/* 221:    */      }
/* 222:    */    }
/* 223:    */  }
/* 224:    */  
/* 272:    */  public final void run()
/* 273:    */  {
/* 274:    */    try
/* 275:    */    {
/* 276:265 */      while (!xm.a()) {
/* 277:266 */        synchronized (this) {
/* 278:267 */          while ((this.jdField_a_of_type_Mr == null) && (this.b == null)) {
/* 279:268 */            wait();
/* 280:    */          }
/* 281:270 */          this.b = this.jdField_a_of_type_Mr;
/* 282:271 */          this.jdField_a_of_type_Mr = null;
/* 283:    */        }
/* 284:273 */        mr localmr = this.b;??? = this; if ((!jdField_a_of_type_Boolean) && (localmr.b() != null)) throw new AssertionError(); synchronized (localmr.a) { dG localdG = SegmentDrawer.jdField_a_of_type_DH.a(localmr);localmr.a(localdG);CubeMeshBufferContainer localCubeMeshBufferContainer = localmr.a();((dd)???).a(localmr, localCubeMeshBufferContainer, localdG); synchronized (((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList) { int i; if ((i = ((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.indexOf(localmr)) >= 0) { if ((!jdField_a_of_type_Boolean) && (localmr.getId() == ((mr)((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).getId())) throw new AssertionError(); synchronized (SegmentDrawer.a(((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) { SegmentDrawer.a(((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).add(((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)); } localObject1.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(i); } localObject1.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(localmr); } } localObject3.jdField_a_of_type_De.jdField_a_of_type_Int += 1;
/* 285:    */        
/* 286:275 */        Thread.sleep(3L);
/* 287:    */        
/* 288:277 */        this.jdField_a_of_type_De.a(this, this.b, true);
/* 289:278 */        this.b = null;
/* 290:    */      }
/* 291:    */      return;
/* 292:281 */    } catch (InterruptedException localInterruptedException) { 
/* 293:    */      
/* 294:283 */        localInterruptedException;
/* 295:    */    }
/* 296:    */  }
/* 297:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dd
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */