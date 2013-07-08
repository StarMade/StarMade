/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
/*   4:    */import it.unimi.dsi.fastutil.longs.LongIterator;
/*   5:    */import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.List;
/*   8:    */import org.schema.game.common.data.element.ElementCollection;
/*   9:    */import q;
/*  10:    */
/*  15:    */public class ElementCollectionCalculationThread
/*  16:    */{
/*  17:    */  private ElementCollection lastElementCollection;
/*  18:    */  private LongOpenHashSet closedCollection;
/*  19:    */  private LongArrayFIFOQueue openCollection;
/*  20:    */  private final ElementCollectionManager man;
/*  21: 21 */  private final q absPosTmp = new q();
/*  22: 22 */  private final q absPos = new q();
/*  23:    */  private CollectionCalculationCallback callback;
/*  24: 24 */  private final ArrayList collections = new ArrayList();
/*  25:    */  
/*  33: 33 */  public static long[] vals = { -1L, 1L, -65534L, 65534L, -4294705156L, 4294705156L };
/*  34:    */  
/*  35: 35 */  public static void main(String[] paramArrayOfString) { for (paramArrayOfString = -100; paramArrayOfString < 100; paramArrayOfString++) {
/*  36: 36 */      for (int i = -100; i < 100; i++) {
/*  37: 37 */        for (int j = -100; j < 100; j++)
/*  38:    */        {
/*  39:    */          q localq1;
/*  40: 40 */          long l1 = ElementCollection.getIndex(localq1 = new q(j, i, paramArrayOfString));
/*  41:    */          
/*  42: 42 */          for (int k = 0; k < 6; k++) {
/*  43:    */            q localq2;
/*  44: 44 */            (localq2 = new q(localq1)).a(org.schema.game.common.data.element.Element.DIRECTIONSi[k]);
/*  45: 45 */            long l2 = ElementCollection.getIndex(localq2);
/*  46:    */            
/*  47: 47 */            assert (l1 - vals[k] == l2) : (l1 + "; " + l2 + "; " + (l1 - l2) + "; " + vals[k] + "; " + (l1 + vals[k]));
/*  48:    */          }
/*  49:    */        }
/*  50:    */      }
/*  51:    */    }
/*  52:    */  }
/*  53:    */  
/*  57:    */  public ElementCollectionCalculationThread(ElementCollectionManager paramElementCollectionManager)
/*  58:    */  {
/*  59: 59 */    this.man = paramElementCollectionManager;
/*  60:    */  }
/*  61:    */  
/*  67:    */  public boolean equals(Object paramObject)
/*  68:    */  {
/*  69: 69 */    return this.man == ((ElementCollectionCalculationThread)paramObject).man;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public int hashCode() {
/*  73: 73 */    return this.man.hashCode();
/*  74:    */  }
/*  75:    */  
/*  77:    */  public void initialize(LongOpenHashSet paramLongOpenHashSet, LongArrayFIFOQueue paramLongArrayFIFOQueue, CollectionCalculationCallback paramCollectionCalculationCallback)
/*  78:    */  {
/*  79: 79 */    this.closedCollection = paramLongOpenHashSet;
/*  80: 80 */    this.openCollection = paramLongArrayFIFOQueue;
/*  81: 81 */    this.callback = paramCollectionCalculationCallback;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public void onFinish() {
/*  85: 85 */    this.lastElementCollection = null;
/*  86: 86 */    this.closedCollection = null;
/*  87: 87 */    this.openCollection = null;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public void process() {
/*  91: 91 */    while (!this.closedCollection.isEmpty()) {
/*  92: 92 */      if (this.openCollection.isEmpty())
/*  93:    */      {
/*  96: 96 */        assert (!this.closedCollection.isEmpty());
/*  97:    */        
/*  98:    */        LongIterator localLongIterator;
/*  99: 99 */        long l2 = (localLongIterator = this.closedCollection.iterator()).nextLong();
/* 100:100 */        localLongIterator.remove();
/* 101:101 */        this.openCollection.enqueue(l2);
/* 102:    */        
/* 103:    */        ElementCollection localElementCollection;
/* 104:104 */        (localElementCollection = getCollectionInstance()).addElement(l2);
/* 105:    */        
/* 106:106 */        getCollections().add(localElementCollection);
/* 107:    */        
/* 108:108 */        this.lastElementCollection = localElementCollection;
/* 109:    */      } else {
/* 110:110 */        while (!this.openCollection.isEmpty()) {
/* 111:111 */          long l1 = this.openCollection.dequeue().longValue();
/* 112:    */          
/* 114:114 */          for (int i = 0; i < 6; i++)
/* 115:    */          {
/* 117:117 */            long l3 = l1 - vals[i];
/* 118:118 */            if (this.closedCollection.remove(l3)) {
/* 119:119 */              this.lastElementCollection.addElement(l3);
/* 120:120 */              this.openCollection.enqueue(l3);
/* 121:    */            }
/* 122:    */          }
/* 123:    */        }
/* 124:    */      }
/* 125:    */    }
/* 126:    */    
/* 128:128 */    onCalculationFinished();
/* 129:    */  }
/* 130:    */  
/* 131:    */  public void apply() {
/* 132:132 */    this.man.getCollection().clear();
/* 133:133 */    this.man.getCollection().addAll(getCollections());
/* 134:    */  }
/* 135:    */  
/* 136:    */  private void onCalculationFinished() {
/* 137:137 */    this.callback.callback(this);
/* 138:    */  }
/* 139:    */  
/* 141:    */  public ElementCollection getCollectionInstance()
/* 142:    */  {
/* 143:143 */    return getMan().newElementCollection(getMan().getEnhancerClazz(), getMan(), getMan().getSegmentController());
/* 144:    */  }
/* 145:    */  
/* 147:    */  public ElementCollectionManager getMan()
/* 148:    */  {
/* 149:149 */    return this.man;
/* 150:    */  }
/* 151:    */  
/* 152:    */  public void flagUpdateFinished()
/* 153:    */  {
/* 154:154 */    getMan().flagUpdateFinished(this);
/* 155:    */  }
/* 156:    */  
/* 160:    */  public ArrayList getCollections()
/* 161:    */  {
/* 162:162 */    return this.collections;
/* 163:    */  }
/* 164:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionCalculationThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */