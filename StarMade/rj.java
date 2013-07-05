/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import javax.swing.AbstractListModel;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ 
/*    */ public final class rj extends AbstractListModel
/*    */ {
/*    */   private static final long serialVersionUID = -2709488666387980490L;
/* 19 */   private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*    */   private ct jdField_a_of_type_Ct;
/*    */ 
/*    */   public rj(ct paramct)
/*    */   {
/* 23 */     this.jdField_a_of_type_Ct = paramct;
/*    */ 
/* 25 */     a();
/*    */   }
/*    */   public final void a() {
/* 28 */     this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 29 */     for (Iterator localIterator = this.jdField_a_of_type_Ct.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*    */     {
/*    */       Sendable localSendable;
/* 30 */       if (((
/* 30 */         localSendable = (Sendable)localIterator.next()) instanceof lE))
/*    */       {
/* 31 */         this.jdField_a_of_type_JavaUtilArrayList.add((lE)localSendable);
/*    */       }
/*    */     }
/*    */ 
/* 35 */     Collections.sort(this.jdField_a_of_type_JavaUtilArrayList, new rk());
/*    */ 
/* 44 */     fireContentsChanged(this, 0, this.jdField_a_of_type_JavaUtilArrayList.size());
/*    */   }
/*    */ 
/*    */   public final Object getElementAt(int paramInt)
/*    */   {
/*    */     try
/*    */     {
/* 51 */       return this.jdField_a_of_type_JavaUtilArrayList.get(paramInt); } catch (Exception localException) { localException
/* 52 */         .printStackTrace();
/*    */     }
/*    */ 
/* 55 */     return "Exception";
/*    */   }
/*    */ 
/*    */   public final int getSize()
/*    */   {
/* 60 */     return this.jdField_a_of_type_JavaUtilArrayList.size();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rj
 * JD-Core Version:    0.6.2
 */