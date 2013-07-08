package org.hsqldb.persist;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowInputText;

public class TextFileReader
{
  private RandomAccessInterface dataFile;
  private RowInputInterface rowIn;
  private TextFileSettings textFileSettings;
  private String header;
  private boolean isReadOnly;
  private HsqlByteArrayOutputStream buffer;
  
  TextFileReader(RandomAccessInterface paramRandomAccessInterface, TextFileSettings paramTextFileSettings, RowInputInterface paramRowInputInterface, boolean paramBoolean)
  {
    this.dataFile = paramRandomAccessInterface;
    this.textFileSettings = paramTextFileSettings;
    this.rowIn = paramRowInputInterface;
    this.isReadOnly = paramBoolean;
    this.buffer = new HsqlByteArrayOutputStream(128);
  }
  
  public RowInputInterface readObject(int paramInt)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    this.buffer.reset();
    paramInt = findNextUsedLinePos(paramInt);
    if (paramInt == -1) {
      return null;
    }
    try
    {
      this.dataFile.seek(paramInt);
      while (j == 0)
      {
        int n = this.dataFile.read();
        m = 0;
        if (n == -1)
        {
          if (this.buffer.size() == 0) {
            return null;
          }
          j = 1;
          if ((k != 0) || (this.isReadOnly)) {
            break;
          }
          this.dataFile.write(TextFileSettings.BYTES_LINE_SEP, 0, TextFileSettings.BYTES_LINE_SEP.length);
          this.buffer.write(TextFileSettings.BYTES_LINE_SEP);
          break;
        }
        switch (n)
        {
        case 34: 
          m = 1;
          j = k;
          k = 0;
          if (this.textFileSettings.isQuoted) {
            i = i == 0 ? 1 : 0;
          }
          break;
        case 13: 
          k = i == 0 ? 1 : 0;
          break;
        case 10: 
          j = i == 0 ? 1 : 0;
          break;
        default: 
          m = 1;
          j = k;
          k = 0;
        }
        this.buffer.write(n);
      }
      if (j != 0)
      {
        if (m != 0) {
          this.buffer.setPosition(this.buffer.size() - 1);
        }
        String str;
        try
        {
          str = this.buffer.toString(this.textFileSettings.stringEncoding);
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          str = this.buffer.toString();
        }
        ((RowInputText)this.rowIn).setSource(str, paramInt, this.buffer.size());
        return this.rowIn;
      }
      return null;
    }
    catch (IOException localIOException)
    {
      throw Error.error(484, localIOException);
    }
  }
  
  public int readHeaderLine()
  {
    int i = 0;
    int j = 0;
    int k = 0;
    this.buffer.reset();
    try
    {
      this.dataFile.seek(0L);
    }
    catch (IOException localIOException1)
    {
      throw Error.error(484, localIOException1);
    }
    while (i == 0)
    {
      k = 0;
      int m;
      try
      {
        m = this.dataFile.read();
        if (m == -1)
        {
          if (this.buffer.size() == 0) {
            return 0;
          }
          i = 1;
          if (!this.isReadOnly)
          {
            this.dataFile.write(TextFileSettings.BYTES_LINE_SEP, 0, TextFileSettings.BYTES_LINE_SEP.length);
            this.buffer.write(TextFileSettings.BYTES_LINE_SEP);
          }
          break;
        }
      }
      catch (IOException localIOException2)
      {
        throw Error.error(483);
      }
      switch (m)
      {
      case 13: 
        j = 1;
        break;
      case 10: 
        i = 1;
        break;
      default: 
        k = 1;
        i = j;
        j = 0;
      }
      if ((j == 0) && (i == 0)) {
        this.buffer.write(m);
      }
    }
    if (k != 0) {
      this.buffer.setPosition(this.buffer.size() - 1);
    }
    try
    {
      this.header = this.buffer.toString(this.textFileSettings.stringEncoding);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      this.header = this.buffer.toString();
    }
    return this.buffer.size();
  }
  
  /* Error */
  private int findNextUsedLinePos(int paramInt)
  {
    // Byte code:
    //   0: iload_1
    //   1: istore_2
    //   2: iload_1
    //   3: istore_3
    //   4: iconst_0
    //   5: istore 4
    //   7: aload_0
    //   8: getfield 2	org/hsqldb/persist/TextFileReader:dataFile	Lorg/hsqldb/persist/RandomAccessInterface;
    //   11: iload_1
    //   12: i2l
    //   13: invokeinterface 11 3 0
    //   18: aload_0
    //   19: getfield 2	org/hsqldb/persist/TextFileReader:dataFile	Lorg/hsqldb/persist/RandomAccessInterface;
    //   22: invokeinterface 12 1 0
    //   27: istore 5
    //   29: iinc 3 1
    //   32: iload 5
    //   34: lookupswitch	default:+89->123, -1:+87->121, 10:+48->82, 13:+42->76, 32:+66->100
    //   77: istore 4
    //   79: goto +64 -> 143
    //   82: iconst_0
    //   83: istore 4
    //   85: aload_0
    //   86: getfield 4	org/hsqldb/persist/TextFileReader:rowIn	Lorg/hsqldb/rowio/RowInputInterface;
    //   89: checkcast 24	org/hsqldb/rowio/RowInputText
    //   92: invokevirtual 30	org/hsqldb/rowio/RowInputText:skippedLine	()V
    //   95: iload_3
    //   96: istore_2
    //   97: goto +46 -> 143
    //   100: iload 4
    //   102: ifeq +41 -> 143
    //   105: iconst_0
    //   106: istore 4
    //   108: aload_0
    //   109: getfield 4	org/hsqldb/persist/TextFileReader:rowIn	Lorg/hsqldb/rowio/RowInputInterface;
    //   112: checkcast 24	org/hsqldb/rowio/RowInputText
    //   115: invokevirtual 30	org/hsqldb/rowio/RowInputText:skippedLine	()V
    //   118: goto +25 -> 143
    //   121: iconst_m1
    //   122: ireturn
    //   123: iload 4
    //   125: ifeq +16 -> 141
    //   128: iconst_0
    //   129: istore 4
    //   131: aload_0
    //   132: getfield 4	org/hsqldb/persist/TextFileReader:rowIn	Lorg/hsqldb/rowio/RowInputInterface;
    //   135: checkcast 24	org/hsqldb/rowio/RowInputText
    //   138: invokevirtual 30	org/hsqldb/rowio/RowInputText:skippedLine	()V
    //   141: iload_2
    //   142: ireturn
    //   143: goto -125 -> 18
    //   146: astore_2
    //   147: sipush 484
    //   150: aload_2
    //   151: invokestatic 27	org/hsqldb/error/Error:error	(ILjava/lang/Throwable;)Lorg/hsqldb/HsqlException;
    //   154: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	155	0	this	TextFileReader
    //   0	155	1	paramInt	int
    //   1	141	2	i	int
    //   146	5	2	localIOException	IOException
    //   3	93	3	j	int
    //   5	125	4	k	int
    //   27	6	5	m	int
    // Exception table:
    //   from	to	target	type
    //   0	122	146	java/io/IOException
    //   123	142	146	java/io/IOException
    //   143	146	146	java/io/IOException
  }
  
  public String getHeaderLine()
  {
    return this.header;
  }
  
  public int getLineNumber()
  {
    return ((RowInputText)this.rowIn).getLineNumber();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.TextFileReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */