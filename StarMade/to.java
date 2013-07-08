/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.Random;
/*   7:    */import java.util.Set;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */import org.schema.game.common.controller.SegmentController;
/*  10:    */import org.schema.game.common.data.element.ElementDocking;
/*  11:    */import org.schema.game.common.data.world.Segment;
/*  12:    */import org.schema.game.common.data.world.Universe;
/*  13:    */import org.schema.game.network.objects.NetworkPlayer;
/*  14:    */import org.schema.schine.network.NetworkStateContainer;
/*  15:    */import org.schema.schine.network.StateInterface;
/*  16:    */import org.schema.schine.network.objects.Sendable;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  18:    */
/*  28:    */public final class to
/*  29:    */  extends sM
/*  30:    */{
/*  31: 31 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  32:    */  
/*  33: 33 */  private long jdField_a_of_type_Long = 0L;
/*  34:    */  
/*  36: 36 */  private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  37:    */  
/*  38:    */  private static final long serialVersionUID = 1L;
/*  39:    */  
/*  41:    */  public to(wk paramwk)
/*  42:    */  {
/*  43: 43 */    super(paramwk);
/*  44:    */  }
/*  45:    */  
/*  60:    */  private static boolean a(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
/*  61:    */  {
/*  62: 62 */    return (paramSegmentController1.getDockingController().a() != null) && (paramSegmentController1.getDockingController().a().to.a().a() == paramSegmentController2);
/*  63:    */  }
/*  64:    */  
/*  65:    */  public final boolean c() {
/*  66: 66 */    ((sL)a().a).a(null);
/*  67:    */    
/*  69: 69 */    return false;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public final boolean b() {
/*  73: 73 */    return false;
/*  74:    */  }
/*  75:    */  
/*  98:    */  public final boolean d()
/*  99:    */  {
/* 100:    */    to localto1;
/* 101:    */    
/* 123:    */    Object localObject1;
/* 124:    */    
/* 146:    */    Object localObject2;
/* 147:    */    
/* 169:    */    Object localObject3;
/* 170:    */    
/* 192:192 */    if (a().a(kq.a).a().equals("Selected Target")) {
/* 193:193 */      localto1 = this; if (((a().a() instanceof SegmentController)) && (((SegmentController)localto1.a().a()).getDockingController().a() != null) && (((localObject1 = ((SegmentController)localto1.a().a()).getDockingController().a().to.a().a()) instanceof cw))) { cw localcw = (cw)localObject1;localObject2 = null; for (Iterator localIterator1 = localcw.a().iterator(); localIterator1.hasNext();) for (localObject4 = (localObject3 = (lE)localIterator1.next()).a().a().iterator(); ((Iterator)localObject4).hasNext(); localObject2 = localObject3) { lA locallA; if (((locallA = (lA)((Iterator)localObject4).next()).jdField_a_of_type_LE != localObject3) || (!locallA.jdField_a_of_type_JavaLangObject.equals(kd.a))) {} } Object localObject4; if (localObject2 != null) { int i = ((Integer)((lE)localObject2).a().selectedEntityId.get()).intValue(); if (((localObject3 = (Sendable)localto1.a().a().getLocalAndRemoteObjectContainer().getLocalObjects().get(i)) != null) && ((localObject3 instanceof mF))) { localObject4 = (mF)localObject3;int j = 0; if (((localObject4 instanceof SegmentController)) && (a((SegmentController)localObject4, (SegmentController)localObject1))) j = 1; if (j == 0) { ((sL)localto1.a().a).a((mF)localObject4);localto1.a(new tB());
/* 194:    */            }
/* 195:195 */          } } } } else { localto1 = this; if (System.currentTimeMillis() - localto1.jdField_a_of_type_Long > 3000L) { localto1.jdField_a_of_type_Long = System.currentTimeMillis();localto1.jdField_a_of_type_JavaUtilArrayList.clear(); Iterator localIterator2; synchronized (localto1.a().a().getLocalAndRemoteObjectContainer().getLocalObjects()) { localObject2 = (mF)localto1.a().a(); for (localIterator2 = localto1.a().a().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator(); localIterator2.hasNext();) if (((localObject3 = (Sendable)localIterator2.next()) instanceof mF)) if ((((vf)localto1.a().a()).a().a((mF)localObject2, (mF)localObject3)) && ((!(localObject3 instanceof SegmentController)) || ((!a((SegmentController)localto1.a(), (SegmentController)localObject3)) && (!a((SegmentController)localObject3, (SegmentController)localto1.a()))))) { localObject1 = (mF)localObject3;to localto2 = localto1; if ((((localObject1 instanceof kd)) && (((kd)localObject1).c()) && (localto2.jdField_a_of_type_JavaxVecmathVector3f.length() > localto2.a().b() / 2.0F) ? 0 : ((localObject1 instanceof kd)) && (((kd)localObject1).a()) ? 0 : (localObject1 instanceof jy) ? 0 : 1) != 0) if ((!(localObject3 instanceof cw)) || ((localObject3 instanceof lD)) || (!((cw)localObject3).a().isEmpty()) || (((wp)localObject3).a().a())) { ((mF)localObject3).calcWorldTransformRelative(((SegmentController)localto1.a()).getSectorId(), ((vg)((SegmentController)localto1.a()).getState()).a().getSector(((SegmentController)localto1.a()).getSectorId()).a);localto1.jdField_a_of_type_JavaxVecmathVector3f.sub(((mF)localObject3).getClientTransform().origin, ((SegmentController)localto1.a()).getWorldTransform().origin); if (localto1.jdField_a_of_type_JavaxVecmathVector3f.length() < localto1.a().b()) { localto1.jdField_a_of_type_JavaxVecmathVector3f.length();localto1.jdField_a_of_type_JavaUtilArrayList.add((mF)localObject3); } } } } } if (localto1.jdField_a_of_type_JavaUtilArrayList.size() > 0) ((sL)localto1.a().a).a((mF)localto1.jdField_a_of_type_JavaUtilArrayList.get(Universe.getRandom().nextInt(localto1.jdField_a_of_type_JavaUtilArrayList.size()))); if (((sL)localto1.a().a).a() != null) localto1.a(new tB());
/* 196:    */    }
/* 197:197 */    ((sL)a().a).a();
/* 198:198 */    return false;
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     to
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */