/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import le;
/*   8:    */import org.schema.game.common.controller.SegmentController;
/*   9:    */import org.schema.game.common.data.world.Segment;
/*  10:    */import q;
/*  11:    */import xq;
/*  12:    */
/*  17:    */public class ManagerModuleCollection
/*  18:    */  extends ManagerModuleControllable
/*  19:    */{
/*  20:    */  private final UsableControllableElementManager elementManager;
/*  21: 21 */  private final q tmpAbsPos = new q();
/*  22: 22 */  private final ObjectArrayFIFOQueue toAddControllers = new ObjectArrayFIFOQueue();
/*  23:    */  
/*  26:    */  public ManagerModuleCollection(UsableControllableElementManager paramUsableControllableElementManager, short paramShort1, short paramShort2)
/*  27:    */  {
/*  28: 28 */    super(paramUsableControllableElementManager, paramShort2, paramShort1);
/*  29: 29 */    this.elementManager = paramUsableControllableElementManager;
/*  30:    */  }
/*  31:    */  
/*  34:    */  public void addControlledBlock(q paramq1, q paramq2, short paramShort)
/*  35:    */  {
/*  36: 36 */    this.elementManager.addControllerBlockIfNecessary(paramq1, paramq2, paramShort);
/*  37:    */  }
/*  38:    */  
/*  39:    */  private void doControllerBlockAdds() {
/*  40: 40 */    if (!this.toAddControllers.isEmpty())
/*  41: 41 */      synchronized (this.toAddControllers) {
/*  42: 42 */        while (!this.toAddControllers.isEmpty())
/*  43:    */        {
/*  44:    */          le localle;
/*  45: 45 */          (localle = (le)this.toAddControllers.dequeue()).a(this.tmpAbsPos);
/*  46: 46 */          for (Object localObject2 = this.elementManager.getCollectionManagers().iterator(); ((Iterator)localObject2).hasNext();) {
/*  47: 47 */            if (((ControlBlockElementCollectionManager)((Iterator)localObject2).next()).getControllerPos().equals(this.tmpAbsPos))
/*  48:    */            {
/*  49: 49 */              return;
/*  50:    */            }
/*  51:    */          }
/*  52:    */          
/*  57: 57 */          if (((localObject2 = this.elementManager.getNewCollectionManager(localle)) instanceof ControlBlockElementCollectionManager)) {
/*  58: 58 */            ((ControlBlockElementCollectionManager)localObject2).refreshControlled(localle.a().a().getControlElementMap());
/*  59:    */          }
/*  60:    */          
/*  62: 62 */          getCollectionManagers().add(localObject2);
/*  63: 63 */          this.elementManager.onControllerBlockAdd();
/*  64:    */          
/*  65: 65 */          ((ElementCollectionManager)localObject2).pieceRefresh();
/*  66:    */        }
/*  67:    */        
/*  68: 68 */        return;
/*  69:    */      }
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void addControllerBlock(byte paramByte1, byte arg2, byte paramByte3, Segment paramSegment) {
/*  73: 73 */    paramByte1 = new le(paramSegment, paramByte1, ???, paramByte3);
/*  74: 74 */    synchronized (this.toAddControllers) {
/*  75: 75 */      this.toAddControllers.enqueue(paramByte1); return;
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  86:    */  public void clear()
/*  87:    */  {
/*  88: 88 */    for (int i = 0; i < this.elementManager.getCollectionManagers().size(); i++)
/*  89:    */    {
/*  90: 90 */      ((ElementCollectionManager)this.elementManager.getCollectionManagers().get(i)).clear();
/*  91:    */    }
/*  92: 92 */    this.elementManager.getCollectionManagers().clear();
/*  93:    */  }
/*  94:    */  
/*  97:    */  public List getCollectionManagers()
/*  98:    */  {
/*  99: 99 */    return getElementManager().getCollectionManagers();
/* 100:    */  }
/* 101:    */  
/* 102:    */  public UsableControllableElementManager getElementManager() {
/* 103:103 */    return this.elementManager;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public void removeController(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/* 107:    */  {
/* 108:108 */    paramSegment.a(paramByte1, paramByte2, paramByte3, this.tmpAbsPos);
/* 109:    */    
/* 110:110 */    for (paramByte1 = this.elementManager.getCollectionManagers().iterator(); paramByte1.hasNext();) {
/* 111:111 */      if ((paramByte2 = (ControlBlockElementCollectionManager)paramByte1.next()).getControllerPos().equals(this.tmpAbsPos)) {
/* 112:112 */        paramByte2.stopUpdate();
/* 113:113 */        this.elementManager.getCollectionManagers().remove(paramByte2);
/* 114:114 */        return;
/* 115:    */      }
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 124:    */  public void removeControllerBlock(q paramq1, q paramq2, short paramShort)
/* 125:    */  {
/* 126:126 */    this.elementManager.removeControllerIfNecessary(paramq1, paramq2, paramShort);
/* 127:    */  }
/* 128:    */  
/* 131:    */  public void update(xq paramxq, long paramLong)
/* 132:    */  {
/* 133:133 */    doControllerBlockAdds();
/* 134:134 */    int i = this.elementManager.getCollectionManagers().size();
/* 135:135 */    for (int j = 0; j < i; j++) {
/* 136:    */      ElementCollectionManager localElementCollectionManager;
/* 137:137 */      (localElementCollectionManager = (ElementCollectionManager)this.elementManager.getCollectionManagers().get(j)).updateStructure(paramLong);
/* 138:138 */      if (localElementCollectionManager.needsUpdate()) {
/* 139:139 */        localElementCollectionManager.update(paramxq);
/* 140:    */      }
/* 141:    */    }
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */