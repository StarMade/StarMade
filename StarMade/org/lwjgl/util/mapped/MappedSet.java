/*  1:   */package org.lwjgl.util.mapped;
/*  2:   */
/* 44:   */public class MappedSet
/* 45:   */{
/* 46:   */  public static MappedSet2 create(MappedObject a, MappedObject b)
/* 47:   */  {
/* 48:48 */    return new MappedSet2(a, b);
/* 49:   */  }
/* 50:   */  
/* 55:   */  public static MappedSet3 create(MappedObject a, MappedObject b, MappedObject c)
/* 56:   */  {
/* 57:57 */    return new MappedSet3(a, b, c);
/* 58:   */  }
/* 59:   */  
/* 64:   */  public static MappedSet4 create(MappedObject a, MappedObject b, MappedObject c, MappedObject d)
/* 65:   */  {
/* 66:66 */    return new MappedSet4(a, b, c, d);
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */