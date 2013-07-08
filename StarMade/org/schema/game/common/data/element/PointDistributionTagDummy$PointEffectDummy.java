/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */import Ah;
/*  4:   */import Ak;
/*  5:   */
/* 12:   */public class PointDistributionTagDummy$PointEffectDummy
/* 13:   */  implements Ak
/* 14:   */{
/* 15:   */  private Integer id;
/* 16:   */  private Integer dist;
/* 17:   */  
/* 18:   */  public PointDistributionTagDummy$PointEffectDummy(PointDistributionTagDummy paramPointDistributionTagDummy) {}
/* 19:   */  
/* 20:   */  public void fromTagStructure(Ah paramAh)
/* 21:   */  {
/* 22:22 */    paramAh = (Ah[])paramAh.a();
/* 23:23 */    setId((Integer)paramAh[0].a());
/* 24:24 */    setDist((Integer)paramAh[1].a());
/* 25:   */  }
/* 26:   */  
/* 29:   */  public Integer getDist()
/* 30:   */  {
/* 31:31 */    return this.dist;
/* 32:   */  }
/* 33:   */  
/* 35:   */  public Integer getId()
/* 36:   */  {
/* 37:37 */    return this.id;
/* 38:   */  }
/* 39:   */  
/* 40:40 */  public String getUniqueIdentifier() { return null; }
/* 41:   */  
/* 42:   */  public boolean isVolatile() {
/* 43:43 */    return false;
/* 44:   */  }
/* 45:   */  
/* 47:   */  public void setDist(Integer paramInteger)
/* 48:   */  {
/* 49:49 */    this.dist = paramInteger;
/* 50:   */  }
/* 51:   */  
/* 54:   */  public void setId(Integer paramInteger)
/* 55:   */  {
/* 56:56 */    this.id = paramInteger;
/* 57:   */  }
/* 58:   */  
/* 62:   */  public String toString()
/* 63:   */  {
/* 64:64 */    return "PointEffectDummy [id=" + getId() + ", dist=" + getDist() + "]";
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Ah toTagStructure() {
/* 68:68 */    return null;
/* 69:   */  }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionTagDummy.PointEffectDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */