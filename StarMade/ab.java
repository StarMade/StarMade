/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public final class ab extends U
/*    */ {
/*    */   public ab(ct paramct)
/*    */   {
/* 14 */     super(paramct);
/*    */   }
/*    */ 
/*    */   public final void handleKeyEvent()
/*    */   {
/* 21 */     super.handleKeyEvent();
/*    */   }
/*    */ 
/*    */   public final void a(xp paramxp)
/*    */   {
/* 27 */     super.a(paramxp);
/*    */   }
/*    */ 
/*    */   public final void b(boolean paramBoolean)
/*    */   {
/* 37 */     int i = 0;
/* 38 */     if (paramBoolean)
/*    */     {
/*    */       Iterator localIterator;
/* 39 */       synchronized (a().b()) {
/* 40 */         for (localIterator = a().b().iterator(); localIterator.hasNext(); ) {
/* 41 */           if (((H)localIterator.next() instanceof G))
/*    */           {
/* 42 */             i = 1;
/*    */           }
/*    */         }
/*    */       }
/* 46 */       if (i == 0) {
/* 47 */         ??? = new G(a());
/* 48 */         a().b().add(???);
/*    */       }
/*    */     } else {
/* 51 */       H.a = System.currentTimeMillis();
/* 52 */       synchronized (a().b()) {
/* 53 */         for (int j = 0; j < a().b().size(); j++)
/*    */         {
/* 55 */           if (((H)a().b().get(j) instanceof G))
/*    */           {
/* 57 */             ((H)a().b().get(j)).d();
/* 58 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 64 */     super.b(paramBoolean);
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq)
/*    */   {
/* 71 */     wV.a = false;
/* 72 */     super.a(paramxq);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ab
 * JD-Core Version:    0.6.2
 */