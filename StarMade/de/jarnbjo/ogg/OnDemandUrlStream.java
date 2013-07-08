/*   1:    */package de.jarnbjo.ogg;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.net.URL;
/*   6:    */import java.net.URLConnection;
/*   7:    */import java.util.Collection;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.LinkedList;
/*  10:    */
/*  39:    */public class OnDemandUrlStream
/*  40:    */  implements PhysicalOggStream
/*  41:    */{
/*  42: 42 */  private boolean closed = false;
/*  43:    */  private URLConnection source;
/*  44:    */  private InputStream sourceStream;
/*  45: 45 */  private Object drainLock = new Object();
/*  46: 46 */  private LinkedList pageCache = new LinkedList();
/*  47: 47 */  private long numberOfSamples = -1L;
/*  48: 48 */  private int contentLength = 0;
/*  49: 49 */  private int position = 0;
/*  50:    */  
/*  51: 51 */  private HashMap logicalStreams = new HashMap();
/*  52:    */  private OggPage firstPage;
/*  53:    */  private static final int PAGECACHE_SIZE = 20;
/*  54:    */  
/*  55:    */  public OnDemandUrlStream(URL source) throws OggFormatException, IOException
/*  56:    */  {
/*  57: 57 */    this.source = source.openConnection();
/*  58: 58 */    this.sourceStream = this.source.getInputStream();
/*  59:    */    
/*  60: 60 */    this.contentLength = this.source.getContentLength();
/*  61:    */    
/*  62: 62 */    this.firstPage = OggPage.create(this.sourceStream);
/*  63: 63 */    this.position += this.firstPage.getTotalLength();
/*  64: 64 */    LogicalOggStreamImpl los = new LogicalOggStreamImpl(this, this.firstPage.getStreamSerialNumber());
/*  65: 65 */    this.logicalStreams.put(new Integer(this.firstPage.getStreamSerialNumber()), los);
/*  66: 66 */    los.checkFormat(this.firstPage);
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Collection getLogicalStreams() {
/*  70: 70 */    return this.logicalStreams.values();
/*  71:    */  }
/*  72:    */  
/*  73:    */  public boolean isOpen() {
/*  74: 74 */    return !this.closed;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void close() throws IOException {
/*  78: 78 */    this.closed = true;
/*  79: 79 */    this.sourceStream.close();
/*  80:    */  }
/*  81:    */  
/*  82:    */  public int getContentLength() {
/*  83: 83 */    return this.contentLength;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public int getPosition() {
/*  87: 87 */    return this.position;
/*  88:    */  }
/*  89:    */  
/*  90: 90 */  int pageNumber = 2;
/*  91:    */  
/*  92:    */  public OggPage getOggPage(int index) throws IOException {
/*  93: 93 */    if (this.firstPage != null) {
/*  94: 94 */      OggPage tmp = this.firstPage;
/*  95: 95 */      this.firstPage = null;
/*  96: 96 */      return tmp;
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    OggPage page = OggPage.create(this.sourceStream);
/* 100:100 */    this.position += page.getTotalLength();
/* 101:101 */    return page;
/* 102:    */  }
/* 103:    */  
/* 104:    */  private LogicalOggStream getLogicalStream(int serialNumber)
/* 105:    */  {
/* 106:106 */    return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/* 107:    */  }
/* 108:    */  
/* 109:    */  public void setTime(long granulePosition) throws IOException {
/* 110:110 */    throw new UnsupportedOperationException("Method not supported by this class");
/* 111:    */  }
/* 112:    */  
/* 116:    */  public boolean isSeekable()
/* 117:    */  {
/* 118:118 */    return false;
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.OnDemandUrlStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */