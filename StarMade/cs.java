/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.io.PrintStream;
/*    */ import org.schema.game.common.data.element.Element;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ 
/*    */ public final class cs
/*    */ {
/*    */   private final ct a;
/*    */   public q a;
/*    */   public q b;
/* 20 */   private q c = new q();
/* 21 */   private q d = new q();
/*    */ 
/*    */   public cs(ct paramct)
/*    */   {
/* 14 */     this.jdField_a_of_type_Ct = paramct;
/*    */   }
/*    */ 
/*    */   public final void a(int paramInt)
/*    */   {
/* 25 */     if (this.jdField_a_of_type_Q != null)
/*    */     {
/* 27 */       if ((
/* 27 */         paramInt = (mv)this.jdField_a_of_type_Ct.getLocalAndRemoteObjectContainer().getLocalObjects().get(paramInt)) == null)
/*    */       {
/* 28 */         this.jdField_a_of_type_Ct.a().b = true;
/* 29 */         return;
/*    */       }
/* 31 */       paramInt = paramInt.a();
/* 32 */       System.err.println("UPDATE WAYPOINT: " + this.jdField_a_of_type_Q);
/*    */ 
/* 34 */       if (this.b == null) {
/* 35 */         this.b = new q();
/*    */       }
/*    */ 
/* 38 */       if (paramInt.equals(this.jdField_a_of_type_Q)) {
/* 39 */         a(null);
/* 40 */         return;
/*    */       }
/* 42 */       this.d.b(0, 0, 0);
/* 43 */       for (int i = 0; i < Element.DIRECTIONSi.length; i++)
/*    */       {
/* 45 */         this.c.b(paramInt, Element.DIRECTIONSi[i]);
/*    */ 
/* 47 */         if (this.c.equals(this.jdField_a_of_type_Q)) {
/* 48 */           this.b.b(paramInt, Element.DIRECTIONSi[i]);
/* 49 */           break;
/*    */         }
/*    */ 
/* 53 */         System.err.println("CHECKING WAYPOINT: " + paramInt + ": " + this.c);
/* 54 */         this.c.c(this.jdField_a_of_type_Q);
/* 55 */         if ((this.d.a() == 0.0F) || (this.c.a() < this.d.a())) {
/* 56 */           this.b.b(paramInt, Element.DIRECTIONSi[i]);
/* 57 */           this.d.b(this.c);
/*    */         } else {
/* 59 */           System.err.println("NOT TAKING: " + this.c.a() + " / " + this.d.a());
/*    */         }
/*    */       }
/* 62 */       System.err.println("NEAREST WAYPOINT " + this.b);
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void a(q paramq)
/*    */   {
/* 82 */     System.err.println("SETTING WAYPOINT: " + paramq);
/* 83 */     this.jdField_a_of_type_Q = paramq;
/* 84 */     this.b = null;
/* 85 */     a(this.jdField_a_of_type_Ct.a());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cs
 * JD-Core Version:    0.6.2
 */