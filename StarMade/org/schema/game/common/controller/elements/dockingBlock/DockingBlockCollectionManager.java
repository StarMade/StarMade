package org.schema.game.common.controller.elements.dockingBlock;

import class_25;
import class_328;
import class_342;
import class_371;
import class_443;
import class_445;
import class_447;
import class_453;
import class_467;
import class_48;
import class_52;
import class_69;
import class_79;
import class_796;
import class_846;
import class_866;
import class_886;
import class_988;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.CollectionNotLoadedException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.BlockMetaDataDummy;
import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
import org.schema.game.common.data.element.ControlElementMap;

public abstract class DockingBlockCollectionManager
  extends ControlBlockElementCollectionManager
{
  public static final String TAG_ID = "A";
  public static final int defaultDockingHalfSize = 3;
  protected int enhancers;
  public byte orientation = 0;
  private boolean collision;
  private class_48 minArea = new class_48(0, 0, 0);
  private class_48 maxArea = new class_48(0, 0, 0);
  private static String dockingOnlineMsg = "Docking Module online!";
  private static String dockingOfflineMsg = "Docking Module offline!\nArea is blocked!";
  private final class_48 min = new class_48();
  private final class_48 max = new class_48();
  
  public DockingBlockCollectionManager(class_796 paramclass_796, SegmentController paramSegmentController, short paramShort)
  {
    super(paramclass_796, paramShort, paramSegmentController);
  }
  
  public void getDockingArea(class_48 paramclass_481, class_48 paramclass_482)
  {
    paramclass_482.a6(this.maxArea, this.minArea);
    paramclass_482.c2();
    paramclass_482.a(3, 3, 3);
    paramclass_481.b1(paramclass_482);
    paramclass_481.b3();
    paramclass_481.a5(2);
    paramclass_482.a5(2);
  }
  
  public void getDockingAreaAbsolute(class_48 paramclass_481, class_48 paramclass_482)
  {
    getDockingArea(paramclass_481, paramclass_482);
    Vector3f localVector3f = new Vector3f(Math.abs(paramclass_481.field_475 - paramclass_482.field_475) / 2.0F, Math.abs(paramclass_481.field_476 - paramclass_482.field_476) / 2.0F, Math.abs(paramclass_481.field_477 - paramclass_482.field_477) / 2.0F);
    class_48 localclass_48 = new class_48(getControllerPos());
    switch (org.schema.game.common.data.element.Element.orientationBackMapping[getControllerElement().b1()])
    {
    case 0: 
      localclass_48.b((int)(localclass_48.field_475 + localVector3f.field_615 / 2.0F + 1.0F), localclass_48.field_476, localclass_48.field_477);
      break;
    case 1: 
      localclass_48.b((int)(localclass_48.field_475 - localVector3f.field_615 / 2.0F - 1.0F), localclass_48.field_476, localclass_48.field_477);
      break;
    case 2: 
      localclass_48.b(localclass_48.field_475, (int)(localclass_48.field_476 + localVector3f.field_616 / 2.0F + 1.0F), localclass_48.field_477);
      break;
    case 3: 
      localclass_48.b(localclass_48.field_475, (int)(localclass_48.field_476 - localVector3f.field_616 / 2.0F - 1.0F), localclass_48.field_477);
      break;
    case 4: 
      localclass_48.b(localclass_48.field_475, localclass_48.field_476, (int)(localclass_48.field_477 + localVector3f.field_617 / 2.0F + 1.0F));
      break;
    case 5: 
      localclass_48.b(localclass_48.field_475, localclass_48.field_476, (int)(localclass_48.field_477 - localVector3f.field_617 / 2.0F - 1.0F));
    }
    paramclass_481.a2();
    paramclass_482.a2();
    paramclass_481.a1(localclass_48);
    paramclass_482.a1(localclass_48);
  }
  
  public boolean isObjectDockable(SegmentController paramSegmentController)
  {
    if (this.enhancers != getSegmentController().getControlElementMap().getControlledElements(getEnhancerClazz(), getControllerPos()).field_1094.size()) {
      throw new CollectionNotLoadedException();
    }
    if (!paramSegmentController.getBoundingBox().a6())
    {
      System.err.println("Exception Catched (OK): SegmentController tested to dock " + paramSegmentController + " to " + getSegmentController() + ": BB is not yet loaded");
      throw new CollectionNotLoadedException();
    }
    getDockingMoved(this.min, this.max);
    class_48 localclass_48 = new class_48(paramSegmentController.getSegmentBuffer().a6().field_1274.field_615, paramSegmentController.getSegmentBuffer().a6().field_1274.field_616, paramSegmentController.getSegmentBuffer().a6().field_1274.field_617);
    paramSegmentController = new class_48(paramSegmentController.getSegmentBuffer().a6().field_1273.field_615, paramSegmentController.getSegmentBuffer().a6().field_1273.field_616, paramSegmentController.getSegmentBuffer().a6().field_1273.field_617);
    paramSegmentController.field_476 -= this.min.field_476;
    localclass_48.field_476 -= this.max.field_476;
    if ((paramSegmentController.field_475 < this.min.field_475) || (paramSegmentController.field_476 < this.min.field_476) || (paramSegmentController.field_477 < this.min.field_477) || (localclass_48.field_475 > this.max.field_475) || (localclass_48.field_476 > this.max.field_476) || (localclass_48.field_477 > this.max.field_477))
    {
      System.err.println("[DOCKINGBLOCK] !NOT! DOCKABLE: Docking[" + this.min + "; " + this.max + "] / Segment[" + paramSegmentController + "; " + localclass_48 + "]");
      return false;
    }
    return true;
  }
  
  public abstract void getDockingMoved(class_48 paramclass_481, class_48 paramclass_482);
  
  public int getMargin()
  {
    return 0;
  }
  
  protected Class getType()
  {
    return DockingBlockUnit.class;
  }
  
  public boolean hasAreaCollision()
  {
    class_48 localclass_481 = new class_48();
    class_48 localclass_482 = new class_48();
    getDockingAreaAbsolute(localclass_481, localclass_482);
    Vector3f localVector3f1 = new Vector3f();
    Vector3f localVector3f2 = new Vector3f();
    localVector3f1.set(localclass_481.field_475 - 8, localclass_481.field_476 - 8, localclass_481.field_477 - 8);
    localVector3f2.set(localclass_482.field_475 - 8, localclass_482.field_476 - 8, localclass_482.field_477 - 8);
    return getSegmentController().getCollisionChecker().a3(localclass_481, localclass_482);
  }
  
  public boolean hasCollision()
  {
    return this.collision;
  }
  
  public boolean isValidPositionToBuild(class_48 paramclass_48)
  {
    class_48 localclass_481 = new class_48();
    class_48 localclass_482 = new class_48();
    getDockingAreaAbsolute(localclass_481, localclass_482);
    System.err.println("[DOCKING] CHECKING TO BUILD POSITION " + paramclass_48 + " ----> " + localclass_481 + "; " + localclass_482);
    return (paramclass_48.field_475 > localclass_482.field_475) || (paramclass_48.field_476 > localclass_482.field_476) || (paramclass_48.field_477 > localclass_482.field_477) || (paramclass_48.field_475 < localclass_481.field_475) || (paramclass_48.field_476 < localclass_481.field_476) || (paramclass_48.field_477 < localclass_481.field_477);
  }
  
  protected void onChangedCollection()
  {
    int i = -1;
    this.enhancers = 0;
    if (getCollection().isEmpty())
    {
      this.minArea.b(0, 0, 0);
      this.maxArea.b(0, 0, 0);
      return;
    }
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      DockingBlockUnit localDockingBlockUnit;
      int j;
      if ((j = Math.abs((localDockingBlockUnit = (DockingBlockUnit)localIterator.next()).getMax().field_475 - localDockingBlockUnit.getMin().field_475) * Math.abs(localDockingBlockUnit.getMax().field_476 - localDockingBlockUnit.getMin().field_476) * Math.abs(localDockingBlockUnit.getMax().field_477 - localDockingBlockUnit.getMin().field_477)) > i)
      {
        this.minArea.b1(localDockingBlockUnit.getMin());
        this.maxArea.b1(localDockingBlockUnit.getMax());
        this.maxArea.a(1, 1, 1);
        i = j;
      }
      this.enhancers += localDockingBlockUnit.size();
    }
  }
  
  public void refreshActive()
  {
    boolean bool = this.collision;
    this.collision = hasAreaCollision();
    class_371 localclass_371;
    class_445 localclass_445;
    if ((!getSegmentController().isOnServer()) && (bool != this.collision) && (((localclass_445 = (localclass_371 = (class_371)getSegmentController().getState()).a14().a18().a79()).a60().a53().c()) || (localclass_445.a60().a51().a45().a36().c())))
    {
      if (this.collision)
      {
        localclass_371.a4().a2(dockingOnlineMsg);
        localclass_371.a4().b1(dockingOfflineMsg);
        return;
      }
      localclass_371.a4().a2(dockingOfflineMsg);
      localclass_371.a4().d1(dockingOnlineMsg);
    }
  }
  
  protected boolean hasMetaData()
  {
    return true;
  }
  
  protected void applyMetaData(BlockMetaDataDummy paramBlockMetaDataDummy)
  {
    this.orientation = ((DockingMetaDataDummy)paramBlockMetaDataDummy).orientation;
  }
  
  protected class_69 toTagStructurePriv()
  {
    return new class_69(class_79.field_549, null, Byte.valueOf(this.orientation));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */