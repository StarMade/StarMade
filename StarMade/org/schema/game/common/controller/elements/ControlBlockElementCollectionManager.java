/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import Aj;
/*   5:    */import ct;
/*   6:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   7:    */import jD;
/*   8:    */import ja;
/*   9:    */import java.util.ArrayList;
/*  10:    */import le;
/*  11:    */import org.schema.game.common.controller.SegmentController;
/*  12:    */import org.schema.game.common.data.element.ControlElementMap;
/*  13:    */import org.schema.game.common.data.element.ElementCollection;
/*  14:    */import q;
/*  15:    */
/*  20:    */public abstract class ControlBlockElementCollectionManager
/*  21:    */  extends ElementCollectionManager
/*  22:    */{
/*  23:    */  private final le controllerElement;
/*  24: 24 */  private q tmp = new q();
/*  25:    */  
/*  26:    */  public ControlBlockElementCollectionManager(le paramle, short paramShort, SegmentController paramSegmentController)
/*  27:    */  {
/*  28: 28 */    super(paramShort, paramSegmentController);
/*  29: 29 */    this.controllerElement = paramle;
/*  30: 30 */    pieceRefresh();
/*  31:    */  }
/*  32:    */  
/*  37:    */  public boolean equals(Object paramObject)
/*  38:    */  {
/*  39: 39 */    return ((ControlBlockElementCollectionManager)paramObject).controllerElement.equals(this.controllerElement);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public boolean equalsControllerPos(q paramq) {
/*  43: 43 */    return (paramq != null) && (getControllerElement().a(paramq));
/*  44:    */  }
/*  45:    */  
/*  47:    */  public le getControllerElement()
/*  48:    */  {
/*  49: 49 */    return this.controllerElement;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public q getControllerPos()
/*  53:    */  {
/*  54: 54 */    assert (this.controllerElement.a(this.tmp)) : (this.tmp + ": " + this.controllerElement.a(new q()));
/*  55: 55 */    return this.tmp;
/*  56:    */  }
/*  57:    */  
/*  61:    */  public int hashCode()
/*  62:    */  {
/*  63: 63 */    return getControllerElement().hashCode();
/*  64:    */  }
/*  65:    */  
/*  66:    */  protected void pieceRefresh()
/*  67:    */  {
/*  68: 68 */    this.controllerElement.a();
/*  69: 69 */    getControllerElement().a(this.tmp);
/*  70:    */  }
/*  71:    */  
/*  73:    */  public void updateStructure(long paramLong)
/*  74:    */  {
/*  75: 75 */    if ((hasMetaData()) && (!getContainer().getInitialBlockMetaData().isEmpty()) && (
/*  76: 76 */      (getSegmentController().isOnServer()) || (((ct)getSegmentController().getState()).a().containsKey(getSegmentController().getId()))))
/*  77:    */    {
/*  79: 79 */      for (int i = 0; i < getContainer().getInitialBlockMetaData().size(); i++) {
/*  80: 80 */        BlockMetaDataDummy localBlockMetaDataDummy = (BlockMetaDataDummy)getContainer().getInitialBlockMetaData().get(i);
/*  81: 81 */        if (getControllerPos().equals(localBlockMetaDataDummy.getControllerPos())) {
/*  82: 82 */          applyMetaData(localBlockMetaDataDummy);
/*  83: 83 */          getContainer().getInitialPointDists().remove(i);
/*  84: 84 */          i--;
/*  85:    */        }
/*  86:    */      }
/*  87:    */    }
/*  88:    */    
/*  90: 90 */    super.updateStructure(paramLong);
/*  91:    */  }
/*  92:    */  
/*  93: 93 */  protected boolean hasMetaData() { return false; }
/*  94:    */  
/* 101:    */  protected void applyMetaData(BlockMetaDataDummy paramBlockMetaDataDummy) {}
/* 102:    */  
/* 108:    */  public void refreshControlled(ControlElementMap paramControlElementMap)
/* 109:    */  {
/* 110:110 */    for (ja localja : paramControlElementMap.getControlledElements(getEnhancerClazz(), getControllerPos()).a)
/* 111:111 */      doAdd(ElementCollection.getIndex(localja.a, localja.b, localja.c));
/* 112:    */  }
/* 113:    */  
/* 114:    */  public Ah toTagStructure() {
/* 115:115 */    return new Ah(Aj.n, null, new Ah[] { new Ah(Aj.k, null, getControllerPos()), toTagStructurePriv(), new Ah(Aj.a, null, null) });
/* 116:    */  }
/* 117:    */  
/* 118:    */  protected Ah toTagStructurePriv()
/* 119:    */  {
/* 120:120 */    return new Ah(Aj.b, null, Byte.valueOf((byte)0));
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ControlBlockElementCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */