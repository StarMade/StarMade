/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */public class BlockFactory
/*  4:   */{
/*  5:   */  public short enhancer;
/*  6:   */  public FactoryResource[][] input;
/*  7:   */  public FactoryResource[][] output;
/*  8:   */  
/*  9:   */  public String toString()
/* 10:   */  {
/* 11:11 */    if (this.input != null) return "Block Factory Products: " + this.input.length; return "INPUT";
/* 12:   */  }
/* 13:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.BlockFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */