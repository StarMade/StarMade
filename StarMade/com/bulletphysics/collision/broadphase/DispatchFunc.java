/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/* 30:   */public enum DispatchFunc
/* 31:   */{
/* 32:32 */  DISPATCH_DISCRETE(1), 
/* 33:33 */  DISPATCH_CONTINUOUS(2);
/* 34:   */  
/* 35:   */  private int value;
/* 36:   */  
/* 37:   */  private DispatchFunc(int value) {
/* 38:38 */    this.value = value;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public int getValue() {
/* 42:42 */    return this.value;
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DispatchFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */