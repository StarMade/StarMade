package org.schema.game.network;

import java.io.File;
import java.io.FilenameFilter;

final class StarMadePlayerStats$1
  implements FilenameFilter
{
  public final boolean accept(File paramFile, String paramString)
  {
    return paramString.startsWith("ENTITY_PLAYERSTATE");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.StarMadePlayerStats.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */