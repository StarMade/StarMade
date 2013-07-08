/*   1:    */package de.jarnbjo.ogg;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.LinkedList;
/*   8:    */
/*  34:    */public class BasicStream
/*  35:    */  implements PhysicalOggStream
/*  36:    */{
/*  37: 37 */  private boolean closed = false;
/*  38:    */  private InputStream sourceStream;
/*  39: 39 */  private Object drainLock = new Object();
/*  40: 40 */  private LinkedList pageCache = new LinkedList();
/*  41: 41 */  private long numberOfSamples = -1L;
/*  42: 42 */  private int position = 0;
/*  43:    */  
/*  44: 44 */  private HashMap logicalStreams = new HashMap();
/*  45:    */  private OggPage firstPage;
/*  46:    */  
/*  47:    */  public BasicStream(InputStream sourceStream) throws OggFormatException, IOException {
/*  48: 48 */    this.firstPage = OggPage.create(sourceStream);
/*  49: 49 */    this.position += this.firstPage.getTotalLength();
/*  50: 50 */    LogicalOggStreamImpl los = new LogicalOggStreamImpl(this, this.firstPage.getStreamSerialNumber());
/*  51: 51 */    this.logicalStreams.put(new Integer(this.firstPage.getStreamSerialNumber()), los);
/*  52: 52 */    los.checkFormat(this.firstPage);
/*  53:    */  }
/*  54:    */  
/*  55:    */  public Collection getLogicalStreams() {
/*  56: 56 */    return this.logicalStreams.values();
/*  57:    */  }
/*  58:    */  
/*  59:    */  public boolean isOpen() {
/*  60: 60 */    return !this.closed;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void close() throws IOException {
/*  64: 64 */    this.closed = true;
/*  65: 65 */    this.sourceStream.close();
/*  66:    */  }
/*  67:    */  
/*  68:    */  public int getContentLength() {
/*  69: 69 */    return -1;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public int getPosition() {
/*  73: 73 */    return this.position;
/*  74:    */  }
/*  75:    */  
/*  76: 76 */  int pageNumber = 2;
/*  77:    */  
/*  78:    */  public OggPage getOggPage(int index) throws IOException {
/*  79: 79 */    if (this.firstPage != null) {
/*  80: 80 */      OggPage tmp = this.firstPage;
/*  81: 81 */      this.firstPage = null;
/*  82: 82 */      return tmp;
/*  83:    */    }
/*  84:    */    
/*  85: 85 */    OggPage page = OggPage.create(this.sourceStream);
/*  86: 86 */    this.position += page.getTotalLength();
/*  87: 87 */    return page;
/*  88:    */  }
/*  89:    */  
/*  90:    */  private LogicalOggStream getLogicalStream(int serialNumber)
/*  91:    */  {
/*  92: 92 */    return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void setTime(long granulePosition) throws IOException {
/*  96: 96 */    throw new UnsupportedOperationException("Method not supported by this class");
/*  97:    */  }
/*  98:    */  
/* 102:    */  public boolean isSeekable()
/* 103:    */  {
/* 104:104 */    return false;
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.BasicStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */