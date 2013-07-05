/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.FactoryAddOnInterface;
/*    */ import org.schema.game.common.controller.elements.ManagerContainerFactoryAddOn;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ 
/*    */ public final class ep
/*    */   implements xg
/*    */ {
/* 23 */   private final HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*    */   private final ct jdField_a_of_type_Ct;
/*    */ 
/*    */   public ep(ct paramct)
/*    */   {
/* 27 */     this.jdField_a_of_type_Ct = paramct;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void d()
/*    */   {
/* 36 */     for (Object localObject1 = this.jdField_a_of_type_Ct.a().values().iterator(); ((Iterator)localObject1).hasNext(); )
/* 37 */       if (((((
/* 37 */         localObject2 = (mF)((Iterator)localObject1).next()) instanceof jA)) || 
/* 37 */         ((localObject2 instanceof ki))) && 
/* 38 */         (!this.jdField_a_of_type_JavaUtilHashMap.containsKey(localObject2))) {
/* 39 */         localObject3 = new eo((SegmentController)localObject2);
/* 40 */         this.jdField_a_of_type_JavaUtilHashMap.put((SegmentController)localObject2, localObject3);
/*    */       }
/*    */     Object localObject3;
/* 44 */     localObject1 = new ArrayList();
/* 45 */     for (Object localObject2 = this.jdField_a_of_type_JavaUtilHashMap.keySet().iterator(); ((Iterator)localObject2).hasNext(); ) {
/* 46 */       if ((
/* 46 */         localObject3 = (SegmentController)((Iterator)localObject2).next())
/* 46 */         .getSectorId() != this.jdField_a_of_type_Ct.a()) {
/* 47 */         ((ArrayList)localObject1).add(localObject3);
/*    */       }
/*    */     }
/* 50 */     for (localObject2 = ((ArrayList)localObject1).iterator(); ((Iterator)localObject2).hasNext(); ) { localObject3 = (SegmentController)((Iterator)localObject2).next();
/*    */ 
/* 53 */       if ((
/* 53 */         localObject1 = (eo)this.jdField_a_of_type_JavaUtilHashMap.remove(localObject3)) != null)
/*    */       {
/* 54 */         ((eo)localObject1).a();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 61 */     d.J.c();
/*    */ 
/* 64 */     for (Iterator localIterator = this.jdField_a_of_type_JavaUtilHashMap.values().iterator(); localIterator.hasNext(); )
/*    */     {
/*    */       eo localeo;
/* 66 */       float f = ((FactoryAddOnInterface)((ld)(
/* 65 */         localeo = (eo)localIterator.next())
/* 65 */         .a()).a()).getFactory()
/* 66 */         .getAccumulated() / 5.0F;
/* 67 */       GlUtil.a(d.J, "time", f);
/* 68 */       localeo.b();
/*    */     }
/*    */ 
/* 71 */     zj.e();
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void a(ka paramka)
/*    */   {
/*    */     eo localeo;
/* 88 */     if ((
/* 88 */       localeo = (eo)this.jdField_a_of_type_JavaUtilHashMap.get(paramka)) != null)
/*    */     {
/* 89 */       localeo.d(); return;
/*    */     }
/* 91 */     System.err.println("[CLIENT][ConnectionDrawer] WARNING segController to update not found!!!!!!!!!!! searching " + paramka);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ep
 * JD-Core Version:    0.6.2
 */