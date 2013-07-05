/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import javax.swing.AbstractListModel;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ 
/*    */ public final class qs extends AbstractListModel
/*    */ {
/* 12 */   private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*    */   private ct jdField_a_of_type_Ct;
/*    */ 
/*    */   public qs(ct paramct)
/*    */   {
/* 16 */     this.jdField_a_of_type_Ct = paramct;
/*    */ 
/* 18 */     a();
/*    */   }
/*    */   public final void a() {
/* 21 */     this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 22 */     for (Sendable localSendable : this.jdField_a_of_type_Ct.getLocalAndRemoteObjectContainer().getLocalObjects().values()) {
/* 23 */       this.jdField_a_of_type_JavaUtilArrayList.add(localSendable);
/*    */     }
/*    */ 
/* 26 */     Collections.sort(this.jdField_a_of_type_JavaUtilArrayList, new qt());
/*    */ 
/* 35 */     fireContentsChanged(this, 0, this.jdField_a_of_type_JavaUtilArrayList.size());
/*    */   }
/*    */ 
/*    */   public final Object getElementAt(int paramInt)
/*    */   {
/*    */     try
/*    */     {
/* 42 */       return this.jdField_a_of_type_JavaUtilArrayList.get(paramInt); } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) { localIndexOutOfBoundsException
/* 43 */         .printStackTrace();
/*    */     }
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */   public final int getSize()
/*    */   {
/* 51 */     return this.jdField_a_of_type_JavaUtilArrayList.size();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qs
 * JD-Core Version:    0.6.2
 */