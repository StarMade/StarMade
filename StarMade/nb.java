/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import javax.swing.AbstractListModel;
/*    */ 
/*    */ public final class nb extends AbstractListModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ArrayList a;
/*    */ 
/*    */   public nb(ArrayList paramArrayList)
/*    */   {
/* 12 */     this.a = paramArrayList;
/*    */   }
/*    */ 
/*    */   public final Object getElementAt(int paramInt)
/*    */   {
/* 19 */     if ((paramInt < 0) || (paramInt >= getSize())) {
/* 20 */       localObject1 = "index, " + paramInt + ", is out of bounds for getSize() = " + getSize();
/*    */ 
/* 22 */       throw new IllegalArgumentException((String)localObject1);
/*    */     }
/* 24 */     Object localObject1 = this.a.iterator();
/* 25 */     int i = 0;
/* 26 */     while (((Iterator)localObject1).hasNext()) {
/* 27 */       Object localObject2 = ((Iterator)localObject1).next();
/* 28 */       if (paramInt == i) {
/* 29 */         return localObject2;
/*    */       }
/* 31 */       i++;
/*    */     }
/*    */ 
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */   public final int getSize()
/*    */   {
/* 39 */     return this.a.size();
/*    */   }
/*    */ 
/*    */   private int a(Object paramObject) {
/* 43 */     int i = 0;
/* 44 */     for (Iterator localIterator = this.a.iterator(); localIterator.hasNext(); ) {
/* 45 */       if (localIterator.next()
/* 45 */         .equals(paramObject)) {
/* 46 */         return i;
/*    */       }
/* 48 */       i++;
/*    */     }
/* 50 */     return -1;
/*    */   }
/*    */ 
/*    */   public final boolean a(Object paramObject)
/*    */   {
/*    */     boolean bool;
/* 55 */     if ((
/* 55 */       bool = this.a.add(paramObject)))
/*    */     {
/* 56 */       paramObject = a(paramObject);
/* 57 */       fireIntervalAdded(this, paramObject, paramObject + 1);
/*    */     }
/* 59 */     return bool;
/*    */   }
/*    */ 
/*    */   public final boolean b(Object paramObject)
/*    */   {
/*    */     int i;
/* 64 */     if ((
/* 64 */       i = a(paramObject)) < 0)
/*    */     {
/* 65 */       return false;
/*    */     }
/* 67 */     paramObject = this.a.remove(paramObject);
/* 68 */     fireIntervalRemoved(this, i, i + 1);
/* 69 */     return paramObject;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nb
 * JD-Core Version:    0.6.2
 */