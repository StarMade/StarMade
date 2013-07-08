/*   1:    */package org.schema.game.common.controller.elements.factory;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Timer;
/*   5:    */import lA;
/*   6:    */import le;
/*   7:    */import org.schema.game.common.controller.SegmentController;
/*   8:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*   9:    */import org.schema.game.common.controller.elements.TimedUpdateInterface;
/*  10:    */import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*  11:    */import org.schema.game.common.data.element.BlockFactory;
/*  12:    */import org.schema.game.common.data.element.ElementInformation;
/*  13:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  14:    */import org.schema.game.common.data.element.FactoryResource;
/*  15:    */
/*  16:    */public class FactoryElementManager extends UsableControllableElementManager implements TimedUpdateInterface
/*  17:    */{
/*  18:    */  private static final long TIME_STEP = 10000L;
/*  19:    */  private ArrayList managers;
/*  20:    */  private final short enhancerId;
/*  21:    */  private final short facId;
/*  22:    */  private long lastExecution;
/*  23:    */  private long nextExecution;
/*  24:    */  
/*  25:    */  public FactoryElementManager(SegmentController paramSegmentController, short paramShort1, short paramShort2)
/*  26:    */  {
/*  27: 27 */    super(paramShort1, paramShort2, paramSegmentController);
/*  28: 28 */    this.managers = new ArrayList();
/*  29: 29 */    this.facId = paramShort1;
/*  30: 30 */    this.enhancerId = paramShort2;
/*  31:    */  }
/*  32:    */  
/*  35:    */  public ArrayList getCollectionManagers()
/*  36:    */  {
/*  37: 37 */    return this.managers;
/*  38:    */  }
/*  39:    */  
/*  41:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  42:    */  {
/*  43: 43 */    return new FactoryCollectionManager(getEnhancerId(), paramle, getSegmentController());
/*  44:    */  }
/*  45:    */  
/*  49:    */  public void handle(lA paramlA) {}
/*  50:    */  
/*  54:    */  public short getEnhancerId()
/*  55:    */  {
/*  56: 56 */    return this.enhancerId;
/*  57:    */  }
/*  58:    */  
/*  61:    */  public short getFacId()
/*  62:    */  {
/*  63: 63 */    return this.facId;
/*  64:    */  }
/*  65:    */  
/*  71:    */  public void update(Timer paramTimer) {}
/*  72:    */  
/*  77:    */  public long getLastExecution()
/*  78:    */  {
/*  79: 79 */    return this.lastExecution;
/*  80:    */  }
/*  81:    */  
/*  82: 82 */  public long getNextExecution() { return this.nextExecution; }
/*  83:    */  
/*  84:    */  public long getTimeStep() {
/*  85: 85 */    return 10000L;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public int getProductCount() {
/*  89: 89 */    return ElementKeyMap.getInfo(getFacId()).getFactory().input.length;
/*  90:    */  }
/*  91:    */  
/*  92: 92 */  public FactoryResource[] getInputType(int paramInt) { return ElementKeyMap.getInfo(getFacId()).getFactory().input[paramInt]; }
/*  93:    */  
/*  94:    */  public FactoryResource[] getOutputType(int paramInt) {
/*  95: 95 */    return ElementKeyMap.getInfo(getFacId()).getFactory().output[paramInt];
/*  96:    */  }
/*  97:    */  
/*  99:    */  public boolean isInputFactory()
/* 100:    */  {
/* 101:101 */    return ElementKeyMap.getInfo(getFacId()).getFactory().input == null;
/* 102:    */  }
/* 103:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.factory.FactoryElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */