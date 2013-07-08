/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.AxisSweep3;
/*   4:    */import com.bulletphysics.collision.broadphase.AxisSweep3.HandleImpl;
/*   5:    */import com.bulletphysics.collision.broadphase.AxisSweep3Internal.EdgeArray;
/*   6:    */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*   7:    */import java.io.PrintStream;
/*   8:    */
/*   9:    */public class AxisSweep3Ext extends AxisSweep3
/*  10:    */{
/*  11:    */  public AxisSweep3Ext(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, int paramInt, OverlappingPairCache paramOverlappingPairCache)
/*  12:    */  {
/*  13: 13 */    super(paramVector3f1, paramVector3f2, paramInt, paramOverlappingPairCache);
/*  14:    */  }
/*  15:    */  
/*  17:    */  public AxisSweep3Ext(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, int paramInt)
/*  18:    */  {
/*  19: 19 */    super(paramVector3f1, paramVector3f2, paramInt);
/*  20:    */  }
/*  21:    */  
/*  22:    */  public AxisSweep3Ext(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
/*  23:    */  {
/*  24: 24 */    super(paramVector3f1, paramVector3f2);
/*  25:    */  }
/*  26:    */  
/*  27:    */  public void cleanUpReferences() {
/*  28: 28 */    for (int i = 0; i < this.pHandles.length; i++) {
/*  29: 29 */      this.pHandles[i].clientObject = null;
/*  30:    */    }
/*  31:    */  }
/*  32:    */  
/*  37:    */  private boolean assertNonNull()
/*  38:    */  {
/*  39: 39 */    for (int i = 0; i < this.maxHandles; i++) {
/*  40: 40 */      assert (this.pHandles[i] != null) : i;
/*  41:    */    }
/*  42: 42 */    return true;
/*  43:    */  }
/*  44:    */  
/*  61:    */  protected AxisSweep3Internal.EdgeArray createEdgeArray(int paramInt)
/*  62:    */  {
/*  63: 63 */    return new AxisSweep3Ext.EdgeArrayImplExt(paramInt);
/*  64:    */  }
/*  65:    */  
/*  66:    */  protected int allocHandle()
/*  67:    */  {
/*  68: 68 */    if (this.firstFreeHandle == 0)
/*  69:    */    {
/*  74: 74 */      com.bulletphysics.collision.broadphase.AxisSweep3Internal.Handle[] arrayOfHandle1 = this.pHandles;
/*  75: 75 */      int j = (this.pHandles.length - 1 << 1) + 1;
/*  76:    */      
/*  77: 77 */      System.err.println("[Physics][AXIS-SWEEP] Handle Array grows: " + arrayOfHandle1.length + " -> " + j);
/*  78:    */      
/*  79: 79 */      this.pHandles = new AxisSweep3.HandleImpl[j];
/*  80: 80 */      for (com.bulletphysics.collision.broadphase.AxisSweep3Internal.Handle[] arrayOfHandle2 = 0; arrayOfHandle2 < arrayOfHandle1.length; arrayOfHandle2++) {
/*  81: 81 */        this.pHandles[arrayOfHandle2] = arrayOfHandle1[arrayOfHandle2];
/*  82:    */      }
/*  83: 83 */      arrayOfHandle2 = arrayOfHandle1.length;
/*  84:    */      
/*  85: 85 */      this.maxHandles = j;
/*  86:    */      
/*  88: 88 */      for (arrayOfHandle1 = arrayOfHandle2; arrayOfHandle1 < this.maxHandles; arrayOfHandle1++) {
/*  89: 89 */        this.pHandles[arrayOfHandle1] = createHandle();
/*  90: 90 */        this.pHandles[arrayOfHandle1].setNextFree(arrayOfHandle1 + 1);
/*  91:    */      }
/*  92: 92 */      this.pHandles[(this.maxHandles - 1)].setNextFree(0);
/*  93:    */      
/*  94: 94 */      this.firstFreeHandle = arrayOfHandle2;
/*  95:    */      
/*  97: 97 */      for (i = 0; i < 3; i++) {
/*  98: 98 */        AxisSweep3Internal.EdgeArray localEdgeArray = this.pEdges[i];
/*  99: 99 */        this.pEdges[i] = createEdgeArray(this.maxHandles << 1);
/* 100:    */        
/* 101:101 */        ((AxisSweep3Ext.EdgeArrayImplExt)this.pEdges[i]).insert((AxisSweep3Ext.EdgeArrayImplExt)localEdgeArray);
/* 102:    */      }
/* 103:103 */      assert (assertNonNull());
/* 104:    */    }
/* 105:105 */    assert (this.firstFreeHandle != 0);
/* 106:106 */    int i = this.firstFreeHandle;
/* 107:107 */    this.firstFreeHandle = getHandle(i).getNextFree();
/* 108:108 */    this.numHandles += 1;
/* 109:    */    
/* 110:110 */    return i;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public void cleanUp() {
/* 114:114 */    for (int i = 0; i < this.pHandles.length; i++) {
/* 115:115 */      this.pHandles[i].clientObject = null;
/* 116:116 */      this.pHandles[i] = null;
/* 117:    */    }
/* 118:118 */    for (i = 0; i < this.pEdges.length; i++) {
/* 119:119 */      this.pEdges[i] = null;
/* 120:    */    }
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.AxisSweep3Ext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */