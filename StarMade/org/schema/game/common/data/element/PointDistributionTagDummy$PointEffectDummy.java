package org.schema.game.common.data.element;

import class_69;
import class_80;

public class PointDistributionTagDummy$PointEffectDummy
  implements class_80
{
  private Integer field_200;
  private Integer dist;
  
  public PointDistributionTagDummy$PointEffectDummy(PointDistributionTagDummy paramPointDistributionTagDummy) {}
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    setId((Integer)paramclass_69[0].a4());
    setDist((Integer)paramclass_69[1].a4());
  }
  
  public Integer getDist()
  {
    return this.dist;
  }
  
  public Integer getId()
  {
    return this.field_200;
  }
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public void setDist(Integer paramInteger)
  {
    this.dist = paramInteger;
  }
  
  public void setId(Integer paramInteger)
  {
    this.field_200 = paramInteger;
  }
  
  public String toString()
  {
    return "PointEffectDummy [id=" + getId() + ", dist=" + getDist() + "]";
  }
  
  public class_69 toTagStructure()
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionTagDummy.PointEffectDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */