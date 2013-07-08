/*  1:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  2:   */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.Collection;
/*  6:   */import java.util.HashMap;
/*  7:   */import java.util.Iterator;
/*  8:   */import java.util.Set;
/*  9:   */import org.schema.game.common.controller.SegmentController;
/* 10:   */import org.schema.game.common.controller.elements.FactoryAddOnInterface;
/* 11:   */import org.schema.game.common.controller.elements.ManagerContainerFactoryAddOn;
/* 12:   */import org.schema.schine.graphicsengine.core.GlUtil;
/* 13:   */
/* 20:   */public final class ep
/* 21:   */  implements xg
/* 22:   */{
/* 23:23 */  private final HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/* 24:   */  private final ct jdField_a_of_type_Ct;
/* 25:   */  
/* 26:   */  public ep(ct paramct) {
/* 27:27 */    this.jdField_a_of_type_Ct = paramct;
/* 28:   */  }
/* 29:   */  
/* 31:   */  public final void a() {}
/* 32:   */  
/* 34:   */  public final void d()
/* 35:   */  {
/* 36:36 */    for (Object localObject1 = this.jdField_a_of_type_Ct.a().values().iterator(); ((Iterator)localObject1).hasNext();) {
/* 37:37 */      if (((((localObject2 = (mF)((Iterator)localObject1).next()) instanceof jA)) || ((localObject2 instanceof ki))) && 
/* 38:38 */        (!this.jdField_a_of_type_JavaUtilHashMap.containsKey(localObject2))) {
/* 39:39 */        localObject3 = new eo((SegmentController)localObject2);
/* 40:40 */        this.jdField_a_of_type_JavaUtilHashMap.put((SegmentController)localObject2, localObject3);
/* 41:   */      }
/* 42:   */    }
/* 43:   */    Object localObject3;
/* 44:44 */    localObject1 = new ArrayList();
/* 45:45 */    for (Object localObject2 = this.jdField_a_of_type_JavaUtilHashMap.keySet().iterator(); ((Iterator)localObject2).hasNext();) {
/* 46:46 */      if ((localObject3 = (SegmentController)((Iterator)localObject2).next()).getSectorId() != this.jdField_a_of_type_Ct.a()) {
/* 47:47 */        ((ArrayList)localObject1).add(localObject3);
/* 48:   */      }
/* 49:   */    }
/* 50:50 */    for (localObject2 = ((ArrayList)localObject1).iterator(); ((Iterator)localObject2).hasNext();) { localObject3 = (SegmentController)((Iterator)localObject2).next();
/* 51:   */      
/* 53:53 */      if ((localObject1 = (eo)this.jdField_a_of_type_JavaUtilHashMap.remove(localObject3)) != null) {
/* 54:54 */        ((eo)localObject1).a();
/* 55:   */      }
/* 56:   */    }
/* 57:   */  }
/* 58:   */  
/* 59:   */  public final void b()
/* 60:   */  {
/* 61:61 */    zk.C.c();
/* 62:   */    
/* 64:64 */    for (Iterator localIterator = this.jdField_a_of_type_JavaUtilHashMap.values().iterator(); localIterator.hasNext();) {
/* 65:   */      eo localeo;
/* 66:66 */      float f = ((FactoryAddOnInterface)((ld)(localeo = (eo)localIterator.next()).a()).a()).getFactory().getAccumulated() / 5.0F;
/* 67:67 */      GlUtil.a(zk.C, "time", f);
/* 68:68 */      localeo.b();
/* 69:   */    }
/* 70:   */    
/* 71:71 */    zj.e();
/* 72:   */  }
/* 73:   */  
/* 77:   */  public final void c() {}
/* 78:   */  
/* 82:   */  public final void a(ka paramka)
/* 83:   */  {
/* 84:   */    eo localeo;
/* 85:   */    
/* 88:88 */    if ((localeo = (eo)this.jdField_a_of_type_JavaUtilHashMap.get(paramka)) != null) {
/* 89:89 */      localeo.d();return;
/* 90:   */    }
/* 91:91 */    System.err.println("[CLIENT][ConnectionDrawer] WARNING segController to update not found!!!!!!!!!!! searching " + paramka);
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */