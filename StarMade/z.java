/*      */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ final class z
/*      */   implements Runnable
/*      */ {
/*      */   z(x paramx, mF[] paramArrayOfmF, IntOpenHashSet paramIntOpenHashSet)
/*      */   {
/*      */   }
/*      */ 
/*      */   public final void run()
/*      */   {
/* 1266 */     synchronized (x.a(this.jdField_a_of_type_X))
/*      */     {
/* 1268 */       for (mF localmF : this.jdField_a_of_type_ArrayOfMF) {
/* 1269 */         if (!this.jdField_a_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet.contains(localmF.getSectorId()))
/*      */         {
/* 1271 */           ((ka)localmF).writeAllBufferedSegmentsToDatabase();
/*      */ 
/* 1273 */           synchronized (x.a(this.jdField_a_of_type_X)) {
/* 1274 */             x.a(this.jdField_a_of_type_X).add((ka)localmF);
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1280 */       return;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     z
 * JD-Core Version:    0.6.2
 */