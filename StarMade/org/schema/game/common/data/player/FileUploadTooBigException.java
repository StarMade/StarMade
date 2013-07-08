package org.schema.game.common.data.player;

import java.io.File;

public class FileUploadTooBigException
  extends Exception
{
  private static final long serialVersionUID = -5842851931941789653L;
  
  public FileUploadTooBigException(File paramFile)
  {
    super("cant upload " + paramFile.getAbsolutePath() + ": file is too big in size");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.player.FileUploadTooBigException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */