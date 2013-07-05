/*     */ import java.io.IOException;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ 
/*     */ final class kb
/*     */   implements jM
/*     */ {
/*     */   kb(ka paramka)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final boolean handle(Segment paramSegment)
/*     */   {
/*     */     try
/*     */     {
/* 497 */       this.a.getSegmentProvider().a.a((mw)paramSegment);
/*     */     }
/*     */     catch (IOException localIOException) {
/* 500 */       localIOException.printStackTrace();
/*     */     }
/*     */ 
/* 501 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kb
 * JD-Core Version:    0.6.2
 */