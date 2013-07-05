/*    */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import org.schema.game.common.data.element.BlockLevel;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*    */ 
/*    */ public final class mk extends md
/*    */ {
/*    */   public mk(mh parammh, q paramq)
/*    */   {
/* 15 */     super(parammh, paramq);
/*    */   }
/*    */ 
/*    */   public final int a()
/*    */   {
/* 20 */     return 0;
/*    */   }
/*    */ 
/*    */   public final int b() {
/* 24 */     return 45;
/*    */   }
/*    */ 
/*    */   public final int c()
/*    */   {
/* 30 */     return 1;
/*    */   }
/*    */ 
/*    */   public final int d()
/*    */   {
/* 35 */     return 45;
/*    */   }
/*    */   public final void a() {
/* 41 */     Iterator localIterator = ElementKeyMap.getLeveldkeyset().iterator();
/*    */     short s;
/*    */     do { if (!localIterator.hasNext()) break; s = ((Short)localIterator.next()).shortValue();
/*    */     }
/* 43 */     while ((b(s) >= 
/* 43 */       10) && 
/* 44 */       (!a(s)));
/*    */ 
/* 49 */     this.a = false;
/*    */   }
/*    */ 
/*    */   private boolean a(short paramShort)
/*    */   {
/*    */     ElementInformation localElementInformation;
/* 54 */     short s = ElementKeyMap.getLevel((
/* 54 */       localElementInformation = ElementKeyMap.getInfo(paramShort))
/* 54 */       .getLevel().getIdBase(), localElementInformation.getLevel().getLevel() + 1);
/* 55 */     IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet();
/* 56 */     boolean bool = false;
/* 57 */     if (s > 0)
/*    */     {
/*    */       try {
/* 60 */         int j = b(paramShort);
/* 61 */         a(paramShort, localIntOpenHashSet);
/* 62 */         int k = j;
/* 63 */         if (j >= 10) {
/* 64 */           int i = j / 10 * 10;
/* 65 */           k = j - i;
/*    */ 
/* 67 */           i /= 10;
/* 68 */           localIntOpenHashSet.add(b(s, i));
/* 69 */           bool = true;
/*    */         }
/*    */ 
/* 72 */         localIntOpenHashSet.add(b(paramShort, k));
/*    */       }
/*    */       catch (NoSlotFreeException localNoSlotFreeException) {
/*    */       }
/* 76 */       a(localIntOpenHashSet);
/*    */     }
/*    */ 
/* 82 */     return bool;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mk
 * JD-Core Version:    0.6.2
 */