/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.common.data.world.Universe;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ 
/*    */ public final class lm extends lp
/*    */ {
/* 22 */   private float b = 0.0F;
/*    */ 
/* 24 */   public lm(StateInterface paramStateInterface) { super(paramStateInterface); }
/*    */ 
/*    */ 
/*    */   public final void c(xq paramxq)
/*    */   {
/* 85 */     mx localmx = null; if (this.b) {
/* 86 */       if (this.b > 1.0F) {
/* 87 */         paramxq = this; Object localObject1 = null; float f = 0.0F; Vector3f localVector3f = new Vector3f(); for (Object localObject2 = paramxq.getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator(); ((Iterator)localObject2).hasNext(); )
/*    */         {
/* 87 */           Object localObject3;
/* 87 */           if (((localObject3 = (Sendable)((Iterator)localObject2).next()) != paramxq.jdField_a_of_type_Lb) && ((localObject3 instanceof mF))) { mF localmF = (mF)localObject3; Object localObject4 = paramxq; if (((localmF instanceof lD)) || ((localmF instanceof ki)) || ((localmF instanceof kd))) { localmx = ((vg)((lm)localObject4).getState()).a().getSector(((ln)localObject4).jdField_a_of_type_Int); localmF.calcWorldTransformRelative(localmx.a(), localmx.a); } if ((((localObject4 = ((vg)((lm)localObject4).getState()).a().getSector(localmF.getSectorId())) != null) && (localmx != null) && (Math.abs(((mx)localObject4).a.jdField_a_of_type_Int - localmx.a.jdField_a_of_type_Int) < 2) && (Math.abs(((mx)localObject4).a.b - localmx.a.b) < 2) && (Math.abs(((mx)localObject4).a.c - localmx.a.c) < 2) ? 1 : localmF.getSectorId() == ((ln)localObject4).jdField_a_of_type_Int ? 1 : 0) != 0) if (!(localObject3 = (mF)localObject3).isHidden()) { localVector3f.sub(((mF)localObject3).getClientTransform().origin, paramxq.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin); if ((localObject1 == null) || (localVector3f.length() < f)) { localObject1 = localObject3; f = localVector3f.length(); }  }   } 
/*    */         }
/* 87 */         if (localObject1 != paramxq.a()) { paramxq.jdField_a_of_type_MF = localObject1; (localObject2 = new lv(paramxq.jdField_a_of_type_Short)).jdField_a_of_type_Int = (paramxq.a() == null ? -1 : paramxq.a().getId()); System.err.println("[SERVER][MISSILE] adding target update " + paramxq.a() + " -> " + ((lv)localObject2).jdField_a_of_type_Int); paramxq.jdField_a_of_type_JavaUtilArrayList.add(localObject2); }
/* 88 */         this.b = 0.0F; return;
/*    */       }
/* 90 */       this.b += paramxq.a();
/*    */     }
/*    */   }
/*    */ 
/*    */   public final byte a()
/*    */   {
/* 98 */     return 2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lm
 * JD-Core Version:    0.6.2
 */