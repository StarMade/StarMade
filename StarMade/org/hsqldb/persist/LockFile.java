package org.hsqldb.persist;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.hsqldb.DatabaseManager;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HsqlTimer;
import org.hsqldb.lib.StringConverter;

public class LockFile
{
  public static final long HEARTBEAT_INTERVAL = 10000L;
  public static final long HEARTBEAT_INTERVAL_PADDED = 10100L;
  protected static final byte[] MAGIC = { 72, 83, 81, 76, 76, 79, 67, 75 };
  public static final int USED_REGION = 16;
  public static final int POLL_RETRIES_DEFAULT = 10;
  public static final String POLL_RETRIES_PROPERTY = "hsqldb.lockfile.poll.retries";
  public static final String POLL_INTERVAL_PROPERTY = "hsqldb.lockfile.poll.interval";
  public static final boolean USE_NIO_FILELOCK_DEFAULT = false;
  public static final String USE_NIO_FILELOCK_PROPERTY = "hsqldb.lockfile.nio.filelock";
  public static final boolean NIO_FILELOCK_AVAILABLE;
  public static final Class NIO_LOCKFILE_CLASS;
  protected static final HsqlTimer timer = DatabaseManager.getTimer();
  protected File file;
  private String cpath;
  protected volatile RandomAccessFile raf;
  protected volatile boolean locked;
  private volatile Object timerTask;
  
  private static final LockFile newNIOLockFile()
  {
    if ((NIO_FILELOCK_AVAILABLE) && (NIO_LOCKFILE_CLASS != null)) {
      try
      {
        return (LockFile)NIO_LOCKFILE_CLASS.newInstance();
      }
      catch (Exception localException) {}
    }
    return null;
  }
  
  public static final LockFile newLockFile(String paramString)
    throws LockFile.FileCanonicalizationException, LockFile.FileSecurityException
  {
    LockFile localLockFile = newNIOLockFile();
    if (localLockFile == null) {
      localLockFile = new LockFile();
    }
    localLockFile.setPath(paramString);
    return localLockFile;
  }
  
  public static final LockFile newLockFileLock(String paramString)
    throws HsqlException
  {
    LockFile localLockFile = null;
    try
    {
      localLockFile = newLockFile(paramString + ".lck");
    }
    catch (BaseException localBaseException1)
    {
      throw Error.error(451, localBaseException1.getMessage());
    }
    boolean bool = false;
    try
    {
      bool = localLockFile.tryLock();
    }
    catch (BaseException localBaseException2)
    {
      throw Error.error(451, localBaseException2.getMessage());
    }
    if (!bool) {
      throw Error.error(451, localLockFile.toString());
    }
    return localLockFile;
  }
  
  private final void checkHeartbeat(boolean paramBoolean)
    throws LockFile.FileSecurityException, LockFile.LockHeldExternallyException, LockFile.UnexpectedEndOfFileException, LockFile.UnexpectedFileIOException, LockFile.UnexpectedFileNotFoundException, LockFile.WrongLengthException, LockFile.WrongMagicException
  {
    long l3 = 0L;
    try
    {
      if (paramBoolean) {
        try
        {
          if (this.file.createNewFile()) {
            return;
          }
        }
        catch (IOException localIOException) {}
      }
      if (!this.file.exists()) {
        return;
      }
      l3 = this.file.length();
    }
    catch (SecurityException localSecurityException)
    {
      throw new FileSecurityException(this, "checkHeartbeat", localSecurityException);
    }
    if (l3 != 16L)
    {
      if (l3 == 0L)
      {
        this.file.delete();
        return;
      }
      throw new WrongLengthException(this, "checkHeartbeat", l3);
    }
    long l1 = System.currentTimeMillis();
    long l2 = readHeartbeat();
    if (Math.abs(l1 - l2) <= 10100L) {
      throw new LockHeldExternallyException(this, "checkHeartbeat", l1, l2);
    }
  }
  
  private final void closeRAF()
    throws LockFile.UnexpectedFileIOException
  {
    if (this.raf != null) {
      try
      {
        this.raf.close();
      }
      catch (IOException localIOException)
      {
        throw new UnexpectedFileIOException(this, "closeRAF", localIOException);
      }
      finally
      {
        this.raf = null;
      }
    }
  }
  
  protected boolean doOptionalLockActions()
  {
    return false;
  }
  
  protected boolean doOptionalReleaseActions()
  {
    return false;
  }
  
  private final void setPath(String paramString)
    throws LockFile.FileCanonicalizationException, LockFile.FileSecurityException
  {
    paramString = FileUtil.getFileUtil().canonicalOrAbsolutePath(paramString);
    this.file = new File(paramString);
    try
    {
      FileUtil.getFileUtil().makeParentDirectories(this.file);
    }
    catch (SecurityException localSecurityException1)
    {
      throw new FileSecurityException(this, "setPath", localSecurityException1);
    }
    try
    {
      this.file = FileUtil.getFileUtil().canonicalFile(paramString);
    }
    catch (SecurityException localSecurityException2)
    {
      throw new FileSecurityException(this, "setPath", localSecurityException2);
    }
    catch (IOException localIOException)
    {
      throw new FileCanonicalizationException(this, "setPath", localIOException);
    }
    this.cpath = this.file.getPath();
  }
  
  private final void openRAF()
    throws LockFile.UnexpectedFileNotFoundException, LockFile.FileSecurityException, LockFile.UnexpectedFileIOException
  {
    try
    {
      this.raf = new RandomAccessFile(this.file, "rw");
    }
    catch (SecurityException localSecurityException)
    {
      throw new FileSecurityException(this, "openRAF", localSecurityException);
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      throw new UnexpectedFileNotFoundException(this, "openRAF", localFileNotFoundException);
    }
    catch (IOException localIOException)
    {
      throw new UnexpectedFileIOException(this, "openRAF", localIOException);
    }
  }
  
  private final void checkMagic(DataInputStream paramDataInputStream)
    throws LockFile.FileSecurityException, LockFile.UnexpectedEndOfFileException, LockFile.UnexpectedFileIOException, LockFile.WrongMagicException
  {
    int i = 1;
    byte[] arrayOfByte = new byte[MAGIC.length];
    try
    {
      for (int j = 0; j < MAGIC.length; j++)
      {
        arrayOfByte[j] = paramDataInputStream.readByte();
        if (MAGIC[j] != arrayOfByte[j]) {
          i = 0;
        }
      }
    }
    catch (SecurityException localSecurityException)
    {
      throw new FileSecurityException(this, "checkMagic", localSecurityException);
    }
    catch (EOFException localEOFException)
    {
      throw new UnexpectedEndOfFileException(this, "checkMagic", localEOFException);
    }
    catch (IOException localIOException)
    {
      throw new UnexpectedFileIOException(this, "checkMagic", localIOException);
    }
    if (i == 0) {
      throw new WrongMagicException(this, "checkMagic", arrayOfByte);
    }
  }
  
  private final long readHeartbeat()
    throws LockFile.FileSecurityException, LockFile.UnexpectedFileNotFoundException, LockFile.UnexpectedEndOfFileException, LockFile.UnexpectedFileIOException, LockFile.WrongMagicException
  {
    FileInputStream localFileInputStream = null;
    DataInputStream localDataInputStream = null;
    try
    {
      if (!this.file.exists())
      {
        l = -9223372036854775808L;
        return l;
      }
      localFileInputStream = new FileInputStream(this.file);
      localDataInputStream = new DataInputStream(localFileInputStream);
      checkMagic(localDataInputStream);
      long l = localDataInputStream.readLong();
      return l;
    }
    catch (SecurityException localSecurityException)
    {
      throw new FileSecurityException(this, "readHeartbeat", localSecurityException);
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      throw new UnexpectedFileNotFoundException(this, "readHeartbeat", localFileNotFoundException);
    }
    catch (EOFException localEOFException)
    {
      throw new UnexpectedEndOfFileException(this, "readHeartbeat", localEOFException);
    }
    catch (IOException localIOException1)
    {
      throw new UnexpectedFileIOException(this, "readHeartbeat", localIOException1);
    }
    finally
    {
      if (localFileInputStream != null) {
        try
        {
          localFileInputStream.close();
        }
        catch (IOException localIOException4) {}
      }
    }
  }
  
  private final void startHeartbeat()
  {
    if ((this.timerTask == null) || (HsqlTimer.isCancelled(this.timerTask)))
    {
      HeartbeatRunner localHeartbeatRunner = new HeartbeatRunner(null);
      this.timerTask = timer.schedulePeriodicallyAfter(0L, 10000L, localHeartbeatRunner, true);
    }
  }
  
  private final void stopHeartbeat()
  {
    if ((this.timerTask != null) && (!HsqlTimer.isCancelled(this.timerTask)))
    {
      HsqlTimer.cancel(this.timerTask);
      this.timerTask = null;
    }
  }
  
  private final void writeMagic()
    throws LockFile.FileSecurityException, LockFile.UnexpectedEndOfFileException, LockFile.UnexpectedFileIOException
  {
    try
    {
      this.raf.seek(0L);
      this.raf.write(MAGIC);
    }
    catch (SecurityException localSecurityException)
    {
      throw new FileSecurityException(this, "writeMagic", localSecurityException);
    }
    catch (EOFException localEOFException)
    {
      throw new UnexpectedEndOfFileException(this, "writeMagic", localEOFException);
    }
    catch (IOException localIOException)
    {
      throw new UnexpectedFileIOException(this, "writeMagic", localIOException);
    }
  }
  
  private final void writeHeartbeat()
    throws LockFile.FileSecurityException, LockFile.UnexpectedEndOfFileException, LockFile.UnexpectedFileIOException
  {
    try
    {
      this.raf.seek(MAGIC.length);
      this.raf.writeLong(System.currentTimeMillis());
    }
    catch (SecurityException localSecurityException)
    {
      throw new FileSecurityException(this, "writeHeartbeat", localSecurityException);
    }
    catch (EOFException localEOFException)
    {
      throw new UnexpectedEndOfFileException(this, "writeHeartbeat", localEOFException);
    }
    catch (IOException localIOException)
    {
      throw new UnexpectedFileIOException(this, "writeHeartbeat", localIOException);
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof LockFile))
    {
      LockFile localLockFile = (LockFile)paramObject;
      return this.file == null ? false : localLockFile.file == null ? true : this.file.equals(localLockFile.file);
    }
    return false;
  }
  
  public final String getCanonicalPath()
  {
    return this.cpath;
  }
  
  public final int hashCode()
  {
    return this.file == null ? 0 : this.file.hashCode();
  }
  
  public final boolean isLocked()
  {
    return this.locked;
  }
  
  public static final boolean isLocked(String paramString)
  {
    boolean bool = true;
    try
    {
      LockFile localLockFile = newLockFile(paramString);
      localLockFile.checkHeartbeat(false);
      bool = false;
    }
    catch (Exception localException) {}
    return bool;
  }
  
  public boolean isValid()
  {
    return (isLocked()) && (this.file != null) && (this.file.exists()) && (this.raf != null);
  }
  
  public String toString()
  {
    return super.toString() + "[file =" + this.cpath + ", exists=" + this.file.exists() + ", locked=" + isLocked() + ", valid=" + isValid() + ", " + toStringImpl() + "]";
  }
  
  protected String toStringImpl()
  {
    return "";
  }
  
  public int getPollHeartbeatRetries()
  {
    int i = 10;
    try
    {
      i = Integer.getInteger("hsqldb.lockfile_poll_retries", i).intValue();
    }
    catch (Exception localException) {}
    if (i < 1) {
      i = 1;
    }
    return i;
  }
  
  public long getPollHeartbeatInterval()
  {
    int i = getPollHeartbeatRetries();
    long l = 10L + 10100L / i;
    try
    {
      l = Long.getLong("hsqldb.lockfile.poll.interval", l).longValue();
    }
    catch (Exception localException) {}
    if (l <= 0L) {
      l = 10L + 10100L / i;
    }
    return l;
  }
  
  private final void pollHeartbeat()
    throws LockFile.FileSecurityException, LockFile.LockHeldExternallyException, LockFile.UnexpectedFileNotFoundException, LockFile.UnexpectedEndOfFileException, LockFile.UnexpectedFileIOException, LockFile.WrongLengthException, LockFile.WrongMagicException
  {
    int i = 0;
    int j = getPollHeartbeatRetries();
    long l = getPollHeartbeatInterval();
    Object localObject = null;
    int k = j;
    while (k > 0) {
      try
      {
        checkHeartbeat(true);
        i = 1;
      }
      catch (BaseException localBaseException)
      {
        localObject = localBaseException;
        try
        {
          Thread.sleep(l);
        }
        catch (InterruptedException localInterruptedException)
        {
          break;
        }
        k--;
      }
    }
    if (i == 0)
    {
      if ((localObject instanceof FileSecurityException)) {
        throw ((FileSecurityException)localObject);
      }
      if ((localObject instanceof LockHeldExternallyException)) {
        throw ((LockHeldExternallyException)localObject);
      }
      if ((localObject instanceof UnexpectedFileNotFoundException)) {
        throw ((UnexpectedFileNotFoundException)localObject);
      }
      if ((localObject instanceof UnexpectedEndOfFileException)) {
        throw ((UnexpectedEndOfFileException)localObject);
      }
      if ((localObject instanceof UnexpectedFileIOException)) {
        throw ((UnexpectedFileIOException)localObject);
      }
      if ((localObject instanceof WrongLengthException)) {
        throw ((WrongLengthException)localObject);
      }
      if ((localObject instanceof WrongMagicException)) {
        throw ((WrongMagicException)localObject);
      }
    }
  }
  
  public final boolean tryLock()
    throws LockFile.FileSecurityException, LockFile.LockHeldExternallyException, LockFile.UnexpectedFileNotFoundException, LockFile.UnexpectedEndOfFileException, LockFile.UnexpectedFileIOException, LockFile.WrongLengthException, LockFile.WrongMagicException
  {
    if (this.locked) {
      return true;
    }
    try
    {
      pollHeartbeat();
      openRAF();
      doOptionalLockActions();
      writeMagic();
      writeHeartbeat();
      FileUtil.getFileUtil().deleteOnExit(this.file);
      this.locked = true;
      startHeartbeat();
      return this.locked;
    }
    finally
    {
      if (!this.locked)
      {
        doOptionalReleaseActions();
        try
        {
          closeRAF();
        }
        catch (Exception localException2) {}
      }
    }
  }
  
  public final boolean tryRelease()
    throws LockFile.FileSecurityException, LockFile.UnexpectedFileIOException
  {
    boolean bool = !this.locked;
    if (bool) {
      return true;
    }
    stopHeartbeat();
    doOptionalReleaseActions();
    Object localObject1 = null;
    FileSecurityException localFileSecurityException = null;
    try
    {
      try
      {
        closeRAF();
      }
      catch (UnexpectedFileIOException localUnexpectedFileIOException)
      {
        localObject1 = localUnexpectedFileIOException;
      }
      try
      {
        Thread.sleep(100L);
      }
      catch (Exception localException) {}
      try
      {
        bool = this.file.delete();
      }
      catch (SecurityException localSecurityException)
      {
        localFileSecurityException = new FileSecurityException(this, "tryRelease", localSecurityException);
      }
    }
    finally
    {
      this.locked = false;
    }
    if (localObject1 != null) {
      throw localObject1;
    }
    if (localFileSecurityException != null) {
      throw localFileSecurityException;
    }
    return bool;
  }
  
  protected final void finalize()
    throws Throwable
  {
    tryRelease();
  }
  
  static
  {
    synchronized (LockFile.class)
    {
      boolean bool1 = false;
      try
      {
        bool1 = "true".equalsIgnoreCase(System.getProperty("hsqldb.lockfile.nio.filelock", bool1 ? "true" : "false"));
      }
      catch (Exception localException1) {}
      boolean bool2 = false;
      Class localClass = null;
      if (bool1) {
        try
        {
          Class.forName("java.nio.channels.FileLock");
          localClass = Class.forName("org.hsqldb.persist.NIOLockFile");
          bool2 = true;
        }
        catch (Exception localException2) {}
      }
      NIO_FILELOCK_AVAILABLE = bool2;
      NIO_LOCKFILE_CLASS = localClass;
    }
  }
  
  public static final class WrongMagicException
    extends LockFile.BaseException
  {
    private final byte[] magic;
    
    public WrongMagicException(LockFile paramLockFile, String paramString, byte[] paramArrayOfByte)
    {
      super(paramString);
      this.magic = paramArrayOfByte;
    }
    
    public String getMessage()
    {
      String str = new StringBuilder().append(super.getMessage()).append(" magic: ").toString();
      str = new StringBuilder().append(str).append(this.magic == null ? "null" : new StringBuilder().append("'").append(StringConverter.byteArrayToHexString(this.magic)).append("'").toString()).toString();
      return str;
    }
    
    public byte[] getMagic()
    {
      return this.magic == null ? null : (byte[])this.magic.clone();
    }
  }
  
  public static final class WrongLengthException
    extends LockFile.BaseException
  {
    private final long length;
    
    public WrongLengthException(LockFile paramLockFile, String paramString, long paramLong)
    {
      super(paramString);
      this.length = paramLong;
    }
    
    public long getLength()
    {
      return this.length;
    }
    
    public String getMessage()
    {
      return super.getMessage() + " length: " + this.length;
    }
  }
  
  public static final class UnexpectedFileNotFoundException
    extends LockFile.BaseException
  {
    private final FileNotFoundException reason;
    
    public UnexpectedFileNotFoundException(LockFile paramLockFile, String paramString, FileNotFoundException paramFileNotFoundException)
    {
      super(paramString);
      this.reason = paramFileNotFoundException;
    }
    
    public FileNotFoundException getReason()
    {
      return this.reason;
    }
    
    public String getMessage()
    {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static final class UnexpectedFileIOException
    extends LockFile.BaseException
  {
    private final IOException reason;
    
    public UnexpectedFileIOException(LockFile paramLockFile, String paramString, IOException paramIOException)
    {
      super(paramString);
      this.reason = paramIOException;
    }
    
    public IOException getReason()
    {
      return this.reason;
    }
    
    public String getMessage()
    {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static final class UnexpectedEndOfFileException
    extends LockFile.BaseException
  {
    private final EOFException reason;
    
    public UnexpectedEndOfFileException(LockFile paramLockFile, String paramString, EOFException paramEOFException)
    {
      super(paramString);
      this.reason = paramEOFException;
    }
    
    public EOFException getReason()
    {
      return this.reason;
    }
    
    public String getMessage()
    {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static final class LockHeldExternallyException
    extends LockFile.BaseException
  {
    private final long read;
    private final long heartbeat;
    
    public LockHeldExternallyException(LockFile paramLockFile, String paramString, long paramLong1, long paramLong2)
    {
      super(paramString);
      this.read = paramLong1;
      this.heartbeat = paramLong2;
    }
    
    public long getHeartbeat()
    {
      return this.heartbeat;
    }
    
    public long getRead()
    {
      return this.read;
    }
    
    public String getMessage()
    {
      return super.getMessage() + " read: " + HsqlDateTime.getTimestampString(this.read) + " heartbeat - read: " + (this.heartbeat - this.read) + " ms.";
    }
  }
  
  public static final class FileSecurityException
    extends LockFile.BaseException
  {
    private final SecurityException reason;
    
    public FileSecurityException(LockFile paramLockFile, String paramString, SecurityException paramSecurityException)
    {
      super(paramString);
      this.reason = paramSecurityException;
    }
    
    public SecurityException getReason()
    {
      return this.reason;
    }
    
    public String getMessage()
    {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static final class FileCanonicalizationException
    extends LockFile.BaseException
  {
    private final IOException reason;
    
    public FileCanonicalizationException(LockFile paramLockFile, String paramString, IOException paramIOException)
    {
      super(paramString);
      this.reason = paramIOException;
    }
    
    public IOException getReason()
    {
      return this.reason;
    }
    
    public String getMessage()
    {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static abstract class BaseException
    extends Exception
  {
    private final LockFile lockFile;
    private final String inMethod;
    
    public BaseException(LockFile paramLockFile, String paramString)
    {
      if (paramLockFile == null) {
        throw new NullPointerException("lockFile");
      }
      if (paramString == null) {
        throw new NullPointerException("inMethod");
      }
      this.lockFile = paramLockFile;
      this.inMethod = paramString;
    }
    
    public String getMessage()
    {
      return "lockFile: " + this.lockFile + " method: " + this.inMethod;
    }
    
    public String getInMethod()
    {
      return this.inMethod;
    }
    
    public LockFile getLockFile()
    {
      return this.lockFile;
    }
  }
  
  private final class HeartbeatRunner
    implements Runnable
  {
    private HeartbeatRunner() {}
    
    public void run()
    {
      try
      {
        LockFile.this.writeHeartbeat();
      }
      catch (Throwable localThrowable)
      {
        Error.printSystemOut(localThrowable.toString());
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.LockFile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */