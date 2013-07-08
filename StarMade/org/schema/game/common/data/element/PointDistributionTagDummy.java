package org.schema.game.common.data.element;

import class_48;
import class_69;
import class_80;
import class_844;
import java.util.Arrays;

public class PointDistributionTagDummy
  implements class_80
{
  private PointDistributionTagDummy.PointEffectDummy[] effects;
  private class_48 controllerPos;
  private class_48 idPos;
  
  public PointDistributionTagDummy() {}
  
  public PointDistributionTagDummy(class_844 paramclass_844)
  {
    this.controllerPos = paramclass_844.jdField_field_1091_of_type_Class_48;
    this.idPos = paramclass_844.jdField_field_1092_of_type_Class_48;
    this.effects = new PointDistributionTagDummy.PointEffectDummy[1];
    this.effects[0] = new PointDistributionTagDummy.PointEffectDummy(this);
    this.effects[0].setId(Integer.valueOf(paramclass_844.jdField_field_1091_of_type_Int));
    PointDistributionTagDummy.PointEffectDummy.access$002(this.effects[0], Integer.valueOf(paramclass_844.jdField_field_1092_of_type_Int));
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    setControllerPos((class_48)paramclass_69[0].a4());
    setIdPos((class_48)paramclass_69[1].a4());
    getFromEffectTagStruct(paramclass_69[2]);
  }
  
  public class_48 getControllerPos()
  {
    return this.controllerPos;
  }
  
  public PointDistributionTagDummy.PointEffectDummy[] getEffects()
  {
    return this.effects;
  }
  
  public void getFromEffectTagStruct(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    setEffects(new PointDistributionTagDummy.PointEffectDummy[paramclass_69.length - 1]);
    for (int i = 0; i < getEffects().length; i++)
    {
      getEffects()[i] = new PointDistributionTagDummy.PointEffectDummy(this);
      getEffects()[i].fromTagStructure(paramclass_69[i]);
    }
  }
  
  public class_48 getIdPos()
  {
    return this.idPos;
  }
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public void setControllerPos(class_48 paramclass_48)
  {
    this.controllerPos = paramclass_48;
  }
  
  public void setEffects(PointDistributionTagDummy.PointEffectDummy[] paramArrayOfPointEffectDummy)
  {
    this.effects = paramArrayOfPointEffectDummy;
  }
  
  public void setIdPos(class_48 paramclass_48)
  {
    this.idPos = paramclass_48;
  }
  
  public String toString()
  {
    return "[" + getControllerPos() + "; " + getIdPos() + "; " + Arrays.toString(getEffects()) + "]";
  }
  
  public class_69 toTagStructure()
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionTagDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */