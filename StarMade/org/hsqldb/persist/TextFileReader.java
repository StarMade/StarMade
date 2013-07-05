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
    if (paramInt == -1)
      return null;
    try
    {
      this.dataFile.seek(paramInt);
      while (j == 0)
      {
        int n = this.dataFile.read();
        m = 0;
        if (n == -1)
        {
          if (this.buffer.size() == 0)
            return null;
          j = 1;
          if ((k != 0) || (this.isReadOnly))
            break;
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
          if (this.textFileSettings.isQuoted)
            i = i == 0 ? 1 : 0;
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
        if (m != 0)
          this.buffer.setPosition(this.buffer.size() - 1);
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
          if (this.buffer.size() == 0)
            return 0;
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
      if ((j == 0) && (i == 0))
        this.buffer.write(m);
    }
    if (k != 0)
      this.buffer.setPosition(this.buffer.size() - 1);
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

  private int findNextUsedLinePos(int paramInt)
  {
    try
    {
      int i = paramInt;
      int j = paramInt;
      int k = 0;
      this.dataFile.seek(paramInt);
      while (true)
      {
        int m = this.dataFile.read();
        j++;
        switch (m)
        {
        case 13:
          k = 1;
          break;
        case 10:
          k = 0;
          ((RowInputText)this.rowIn).skippedLine();
          i = j;
          break;
        case 32:
          if (k != 0)
          {
            k = 0;
            ((RowInputText)this.rowIn).skippedLine();
          }
          break;
        case -1:
          return -1;
        default:
          if (k != 0)
          {
            k = 0;
            ((RowInputText)this.rowIn).skippedLine();
          }
          return i;
        }
      }
    }
    catch (IOException localIOException)
    {
      throw Error.error(484, localIOException);
    }
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
 * JD-Core Version:    0.6.2
 */