package org.schema.game.common.controller.elements.factory;

import class_755;
import class_796;
import java.util.ArrayList;
import java.util.Timer;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.TimedUpdateInterface;
import org.schema.game.common.controller.elements.UsableControllableElementManager;
import org.schema.game.common.data.element.BlockFactory;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.FactoryResource;

public class FactoryElementManager
  extends UsableControllableElementManager
  implements TimedUpdateInterface
{
  private static final long TIME_STEP = 10000L;
  private ArrayList managers = new ArrayList();
  private final short enhancerId;
  private final short facId;
  private long lastExecution;
  private long nextExecution;
  
  public FactoryElementManager(SegmentController paramSegmentController, short paramShort1, short paramShort2)
  {
    super(paramShort1, paramShort2, paramSegmentController);
    this.facId = paramShort1;
    this.enhancerId = paramShort2;
  }
  
  public ArrayList getCollectionManagers()
  {
    return this.managers;
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new FactoryCollectionManager(getEnhancerId(), paramclass_796, getSegmentController());
  }
  
  public void handle(class_755 paramclass_755) {}
  
  public short getEnhancerId()
  {
    return this.enhancerId;
  }
  
  public short getFacId()
  {
    return this.facId;
  }
  
  public void update(Timer paramTimer) {}
  
  public long getLastExecution()
  {
    return this.lastExecution;
  }
  
  public long getNextExecution()
  {
    return this.nextExecution;
  }
  
  public long getTimeStep()
  {
    return 10000L;
  }
  
  public int getProductCount()
  {
    return ElementKeyMap.getInfo(getFacId()).getFactory().input.length;
  }
  
  public FactoryResource[] getInputType(int paramInt)
  {
    return ElementKeyMap.getInfo(getFacId()).getFactory().input[paramInt];
  }
  
  public FactoryResource[] getOutputType(int paramInt)
  {
    return ElementKeyMap.getInfo(getFacId()).getFactory().output[paramInt];
  }
  
  public boolean isInputFactory()
  {
    return ElementKeyMap.getInfo(getFacId()).getFactory().input == null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.factory.FactoryElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */