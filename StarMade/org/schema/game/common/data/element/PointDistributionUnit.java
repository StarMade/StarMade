package org.schema.game.common.data.element;

import class_48;
import class_69;
import class_79;
import class_796;
import class_80;
import class_844;
import java.io.PrintStream;
import java.util.Arrays;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
import org.schema.game.common.controller.elements.DistributionInterface;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.data.element.pointeffect.PointEffect;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteIntArray;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;

public abstract class PointDistributionUnit
  extends ElementCollection
  implements class_80
{
  private PointEffect[] effects;
  private int maxPoints;
  private boolean flagRedistribute;
  
  public void applyDummy(PointDistributionTagDummy paramPointDistributionTagDummy)
  {
    for (int i = 0; i < paramPointDistributionTagDummy.getEffects().length; i++)
    {
      PointDistributionTagDummy.PointEffectDummy localPointEffectDummy = paramPointDistributionTagDummy.getEffects()[i];
      PointEffect localPointEffect;
      (localPointEffect = getEffectById(localPointEffectDummy.getId().intValue())).setDistribution(localPointEffectDummy.getDist().intValue());
      sendDistChange(localPointEffect);
    }
  }
  
  public void clear()
  {
    super.clear();
    resetPointsSpent();
    setMaxPoints(0);
  }
  
  public void distributePoints()
  {
    resetPointsSpent();
    int i = 0;
    for (int j = 0; j < this.effects.length; j++)
    {
      int k = FastMath.a9(this.effects[j].getDistribution() / 100.0F * getMaxPoints());
      this.effects[j].setPointsSpend(k);
      i += k;
    }
    j = 0;
    while (i < getMaxPoints())
    {
      this.effects[j].setPointsSpend(this.effects[j].getPointsSpend() + 1);
      j = (j + 1) % this.effects.length;
      i++;
    }
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    (paramclass_69 = (class_69[])paramclass_69.a4())[0].a4();
    paramclass_69[1].a4();
    getFromEffectTagStruct(paramclass_69[2]);
  }
  
  public int getAvailableDist()
  {
    int i = 0;
    for (PointEffect localPointEffect : this.effects) {
      i += localPointEffect.getDistribution();
    }
    return 100 - i;
  }
  
  public PointEffect getEffectById(int paramInt)
  {
    PointEffect[] arrayOfPointEffect;
    int i = (arrayOfPointEffect = getEffects()).length;
    for (int j = 0; j < i; j++)
    {
      PointEffect localPointEffect;
      if ((localPointEffect = arrayOfPointEffect[j]).getEffectId() == paramInt) {
        return localPointEffect;
      }
    }
    throw new IllegalArgumentException("Effect Id " + paramInt + " not found in " + Arrays.toString(getEffects()));
  }
  
  public PointEffect[] getEffects()
  {
    if (this.flagRedistribute)
    {
      distributePoints();
      this.flagRedistribute = false;
    }
    return this.effects;
  }
  
  public class_69 getEffectTagStruct()
  {
    class_69[] arrayOfclass_69 = new class_69[this.effects.length + 1];
    for (int i = 0; i < this.effects.length; i++) {
      arrayOfclass_69[i] = getEffects()[i].toTagStructure();
    }
    arrayOfclass_69[this.effects.length] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "EffectStruct", arrayOfclass_69);
  }
  
  public void getFromEffectTagStruct(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    for (int i = 0; i < this.effects.length; i++) {
      this.effects[i].fromTagStructure(paramclass_69[i]);
    }
    distributePoints();
  }
  
  public int getMaxPoints()
  {
    return this.maxPoints;
  }
  
  private int getPointValue(long paramLong)
  {
    return 4;
  }
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public void initialize(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
  {
    super.initialize(paramShort, paramElementCollectionManager, paramSegmentController);
    this.effects = initializeEffects();
    initializeDistribution();
  }
  
  protected void initializeDistribution()
  {
    float f = 1.0F / this.effects.length;
    for (int i = 0; i < this.effects.length; i++) {
      this.effects[i].setDistribution((int)(f * 100.0F));
    }
  }
  
  protected abstract PointEffect[] initializeEffects();
  
  public boolean isVolatile()
  {
    return false;
  }
  
  protected void onAdd(long paramLong)
  {
    setMaxPoints(getMaxPoints() + getPointValue(paramLong));
  }
  
  public boolean addElement(long paramLong)
  {
    boolean bool;
    if ((bool = super.addElement(paramLong))) {
      onAdd(paramLong);
    }
    return bool;
  }
  
  public void onChangeFinished()
  {
    super.onChangeFinished();
    distributePoints();
  }
  
  protected void onRemove(class_48 paramclass_48)
  {
    super.onRemove(paramclass_48);
    setMaxPoints(getMaxPoints() - getPointValue(getIndex(paramclass_48)));
    System.err.println("REMOVING four points " + this + " " + getMaxPoints());
  }
  
  public void receiveDistChange(class_844 paramclass_844)
  {
    if (getController().getState().getId() != paramclass_844.field_1093)
    {
      PointEffect localPointEffect;
      (localPointEffect = getEffectById(paramclass_844.field_1091)).setDistribution(paramclass_844.field_1092);
      distributePoints();
      getAvailableDist();
      if (getController().isOnServer()) {
        sendDistChange(localPointEffect);
      }
    }
  }
  
  public boolean remove(class_48 paramclass_48)
  {
    return super.remove(paramclass_48);
  }
  
  public void resetPointsSpent()
  {
    PointEffect[] arrayOfPointEffect;
    int i = (arrayOfPointEffect = this.effects).length;
    for (int j = 0; j < i; j++) {
      arrayOfPointEffect[j].reset();
    }
  }
  
  public void sendAllDistChange()
  {
    for (PointEffect localPointEffect : this.effects) {
      sendDistChange(localPointEffect);
    }
  }
  
  public void sendDistChange(PointEffect paramPointEffect)
  {
    if (getId() == null)
    {
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      throw new NullPointerException("PointDistribution] Could Not load ID POS");
    }
    RemoteIntArrayBuffer localRemoteIntArrayBuffer = ((DistributionInterface)getController().getNetworkObject()).getDistributionModification();
    RemoteIntArray localRemoteIntArray = new RemoteIntArray(9, getController().getNetworkObject());
    class_48 localclass_481 = getId().a2(new class_48());
    class_48 localclass_482 = ((ControlBlockElementCollectionManager)this.col).getControllerPos();
    localRemoteIntArray.set(0, localclass_482.field_475);
    localRemoteIntArray.set(1, localclass_482.field_476);
    localRemoteIntArray.set(2, localclass_482.field_477);
    localRemoteIntArray.set(3, localclass_481.field_475);
    localRemoteIntArray.set(4, localclass_481.field_476);
    localRemoteIntArray.set(5, localclass_481.field_477);
    localRemoteIntArray.set(6, paramPointEffect.getEffectId());
    localRemoteIntArray.set(7, paramPointEffect.getDistribution());
    localRemoteIntArray.set(8, getController().getState().getId());
    localRemoteIntArrayBuffer.add(localRemoteIntArray);
  }
  
  public void setMaxPoints(int paramInt)
  {
    int i = this.maxPoints;
    this.maxPoints = paramInt;
    if (i != paramInt) {
      this.flagRedistribute = true;
    }
  }
  
  public class_69 toTagStructure()
  {
    Object localObject1 = getId().a2(new class_48());
    Object localObject2 = ((ControlBlockElementCollectionManager)this.col).getControllerPos();
    localObject2 = new class_69(class_79.field_558, "controller", localObject2);
    localObject1 = new class_69(class_79.field_558, "idPos", localObject1);
    return new class_69(class_79.field_561, "PointDist", new class_69[] { localObject2, localObject1, getEffectTagStruct(), new class_69(class_79.field_548, null, null) });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */