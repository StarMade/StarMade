package org.hsqldb.scriptio;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.hsqldb.Database;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileAccess.FileSync;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.persist.Crypto;
import org.hsqldb.persist.Logger;
import org.hsqldb.rowio.RowOutputTextLog;

public class ScriptWriterEncode
  extends ScriptWriterText
{
  Crypto crypto;
  HsqlByteArrayOutputStream byteOut;
  OutputStream cryptOut;
  
  public ScriptWriterEncode(Database paramDatabase, OutputStream paramOutputStream, FileAccess.FileSync paramFileSync, boolean paramBoolean, Crypto paramCrypto)
  {
    super(paramDatabase, paramOutputStream, paramFileSync, paramBoolean);
    try
    {
      this.cryptOut = paramCrypto.getOutputStream(this.fileStreamOut);
      this.fileStreamOut = new GZIPOutputStream(this.cryptOut);
      this.isCrypt = true;
    }
    catch (IOException localIOException)
    {
      throw Error.error(localIOException, 452, 26, new Object[] { localIOException.toString(), this.outFile });
    }
  }
  
  public ScriptWriterEncode(Database paramDatabase, String paramString, boolean paramBoolean, Crypto paramCrypto)
  {
    super(paramDatabase, paramString, paramBoolean, true, false);
    try
    {
      this.cryptOut = paramCrypto.getOutputStream(this.fileStreamOut);
      this.fileStreamOut = new GZIPOutputStream(this.cryptOut);
      this.isCrypt = true;
    }
    catch (IOException localIOException)
    {
      throw Error.error(localIOException, 452, 26, new Object[] { localIOException.toString(), this.outFile });
    }
  }
  
  public ScriptWriterEncode(Database paramDatabase, String paramString, Crypto paramCrypto)
  {
    super(paramDatabase, paramString, false, false, false);
    this.crypto = paramCrypto;
    this.byteOut = new HsqlByteArrayOutputStream();
    this.isCrypt = true;
  }
  
  protected void openFile()
  {
    try
    {
      FileAccess localFileAccess = this.isDump ? FileUtil.getFileUtil() : this.database.logger.getFileAccess();
      OutputStream localOutputStream = localFileAccess.openOutputStreamElement(this.outFile);
      this.outDescriptor = localFileAccess.getFileSync(localOutputStream);
      this.fileStreamOut = localOutputStream;
      this.fileStreamOut = new BufferedOutputStream(localOutputStream, 16384);
    }
    catch (IOException localIOException)
    {
      throw Error.error(localIOException, 452, 26, new Object[] { localIOException.toString(), this.outFile });
    }
  }
  
  protected void finishStream()
    throws IOException
  {
    if ((this.fileStreamOut instanceof GZIPOutputStream)) {
      ((GZIPOutputStream)this.fileStreamOut).finish();
    }
  }
  
  void writeRowOutToFile()
    throws IOException
  {
    synchronized (this.fileStreamOut)
    {
      if (this.byteOut == null)
      {
        this.fileStreamOut.write(this.rowOut.getBuffer(), 0, this.rowOut.size());
        this.byteCount += this.rowOut.size();
        this.lineCount += 1L;
        return;
      }
      int i = this.crypto.getEncodedSize(this.rowOut.size());
      this.byteOut.ensureRoom(i + 4);
      i = this.crypto.encode(this.rowOut.getBuffer(), 0, this.rowOut.size(), this.byteOut.getBuffer(), 4);
      this.byteOut.setPosition(0);
      this.byteOut.writeInt(i);
      this.fileStreamOut.write(this.byteOut.getBuffer(), 0, i + 4);
      this.byteCount += this.rowOut.size();
      this.lineCount += 1L;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.scriptio.ScriptWriterEncode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */