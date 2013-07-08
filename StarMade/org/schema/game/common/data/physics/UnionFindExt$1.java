package org.schema.game.common.data.physics;

import java.util.Comparator;

final class UnionFindExt$1
  implements Comparator
{
  public final int compare(UnionFindExt.Element paramElement1, UnionFindExt.Element paramElement2)
  {
    if (paramElement1.field_2174 < paramElement2.field_2174) {
      return -1;
    }
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.UnionFindExt.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */