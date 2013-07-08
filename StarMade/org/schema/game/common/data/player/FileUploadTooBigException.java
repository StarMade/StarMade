/*  1:   */package org.schema.game.common.data.player;
/*  2:   */
/*  3:   */import java.io.File;
/*  4:   */
/*  6:   */public class FileUploadTooBigException
/*  7:   */  extends Exception
/*  8:   */{
/*  9:   */  private static final long serialVersionUID = -5842851931941789653L;
/* 10:   */  
/* 11:   */  public FileUploadTooBigException(File paramFile)
/* 12:   */  {
/* 13:13 */    super("cant upload " + paramFile.getAbsolutePath() + ": file is too big in size");
/* 14:   */  }
/* 15:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.player.FileUploadTooBigException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */