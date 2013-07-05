/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ import Ad;
/*    */ import Ag;
/*    */ 
/*    */ public class PointDistributionTagDummy$PointEffectDummy
/*    */   implements Ag
/*    */ {
/*    */   private Integer id;
/*    */   private Integer dist;
/*    */ 
/*    */   public PointDistributionTagDummy$PointEffectDummy(PointDistributionTagDummy paramPointDistributionTagDummy)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void fromTagStructure(Ad paramAd)
/*    */   {
/* 22 */     paramAd = (Ad[])paramAd.a();
/* 23 */     setId((Integer)paramAd[0].a());
/* 24 */     setDist((Integer)paramAd[1].a());
/*    */   }
/*    */ 
/*    */   public Integer getDist()
/*    */   {
/* 31 */     return this.dist;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 37 */     return this.id;
/*    */   }
/*    */   public String getUniqueIdentifier() {
/* 40 */     return null;
/*    */   }
/*    */   public boolean isVolatile() {
/* 43 */     return false;
/*    */   }
/*    */ 
/*    */   public void setDist(Integer paramInteger)
/*    */   {
/* 49 */     this.dist = paramInteger;
/*    */   }
/*    */ 
/*    */   public void setId(Integer paramInteger)
/*    */   {
/* 56 */     this.id = paramInteger;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 64 */     return "PointEffectDummy [id=" + getId() + ", dist=" + getDist() + "]";
/*    */   }
/*    */ 
/*    */   public Ad toTagStructure() {
/* 68 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionTagDummy.PointEffectDummy
 * JD-Core Version:    0.6.2
 */