/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import java.util.Comparator;
/*   4:    */
/* 150:    */final class UnionFindExt$1
/* 151:    */  implements Comparator
/* 152:    */{
/* 153:    */  public final int compare(UnionFindExt.Element paramElement1, UnionFindExt.Element paramElement2)
/* 154:    */  {
/* 155:155 */    if (paramElement1.id < paramElement2.id) return -1; return 1;
/* 156:    */  }
/* 157:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.UnionFindExt.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */