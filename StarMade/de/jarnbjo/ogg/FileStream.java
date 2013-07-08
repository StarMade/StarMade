/*   1:    */package de.jarnbjo.ogg;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.RandomAccessFile;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.HashMap;
/*   8:    */import java.util.Iterator;
/*   9:    */
/*  33:    */public class FileStream
/*  34:    */  implements PhysicalOggStream
/*  35:    */{
/*  36: 36 */  private boolean closed = false;
/*  37:    */  private RandomAccessFile source;
/*  38:    */  private long[] pageOffsets;
/*  39: 39 */  private long numberOfSamples = -1L;
/*  40:    */  
/*  41: 41 */  private HashMap logicalStreams = new HashMap();
/*  42:    */  
/*  51:    */  public FileStream(RandomAccessFile source)
/*  52:    */    throws OggFormatException, IOException
/*  53:    */  {
/*  54: 54 */    this.source = source;
/*  55:    */    
/*  56: 56 */    ArrayList po = new ArrayList();
/*  57: 57 */    int pageNumber = 0;
/*  58:    */    try {
/*  59:    */      for (;;) {
/*  60: 60 */        po.add(new Long(this.source.getFilePointer()));
/*  61:    */        
/*  63: 63 */        OggPage op = getNextPage(pageNumber > 0);
/*  64: 64 */        if (op == null) {
/*  65:    */          break;
/*  66:    */        }
/*  67:    */        
/*  68: 68 */        LogicalOggStreamImpl los = (LogicalOggStreamImpl)getLogicalStream(op.getStreamSerialNumber());
/*  69: 69 */        if (los == null) {
/*  70: 70 */          los = new LogicalOggStreamImpl(this, op.getStreamSerialNumber());
/*  71: 71 */          this.logicalStreams.put(new Integer(op.getStreamSerialNumber()), los);
/*  72:    */        }
/*  73:    */        
/*  74: 74 */        if (pageNumber == 0) {
/*  75: 75 */          los.checkFormat(op);
/*  76:    */        }
/*  77:    */        
/*  78: 78 */        los.addPageNumberMapping(pageNumber);
/*  79: 79 */        los.addGranulePosition(op.getAbsoluteGranulePosition());
/*  80:    */        
/*  81: 81 */        if (pageNumber > 0) {
/*  82: 82 */          this.source.seek(this.source.getFilePointer() + op.getTotalLength());
/*  83:    */        }
/*  84:    */        
/*  85: 85 */        pageNumber++;
/*  86:    */      }
/*  87:    */      
/*  89:    */    }
/*  90:    */    catch (EndOfOggStreamException e) {}catch (IOException e)
/*  91:    */    {
/*  92: 92 */      throw e;
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    this.source.seek(0L);
/*  96: 96 */    this.pageOffsets = new long[po.size()];
/*  97: 97 */    int i = 0;
/*  98: 98 */    Iterator iter = po.iterator();
/*  99: 99 */    while (iter.hasNext()) {
/* 100:100 */      this.pageOffsets[(i++)] = ((Long)iter.next()).longValue();
/* 101:    */    }
/* 102:    */  }
/* 103:    */  
/* 104:    */  public Collection getLogicalStreams() {
/* 105:105 */    return this.logicalStreams.values();
/* 106:    */  }
/* 107:    */  
/* 108:    */  public boolean isOpen() {
/* 109:109 */    return !this.closed;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void close() throws IOException {
/* 113:113 */    this.closed = true;
/* 114:114 */    this.source.close();
/* 115:    */  }
/* 116:    */  
/* 117:    */  private OggPage getNextPage() throws EndOfOggStreamException, IOException, OggFormatException {
/* 118:118 */    return getNextPage(false);
/* 119:    */  }
/* 120:    */  
/* 121:    */  private OggPage getNextPage(boolean skipData) throws EndOfOggStreamException, IOException, OggFormatException {
/* 122:122 */    return OggPage.create(this.source, skipData);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public OggPage getOggPage(int index) throws IOException {
/* 126:126 */    this.source.seek(this.pageOffsets[index]);
/* 127:127 */    return OggPage.create(this.source);
/* 128:    */  }
/* 129:    */  
/* 130:    */  private LogicalOggStream getLogicalStream(int serialNumber) {
/* 131:131 */    return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/* 132:    */  }
/* 133:    */  
/* 134:    */  public void setTime(long granulePosition) throws IOException {
/* 135:135 */    for (Iterator iter = this.logicalStreams.values().iterator(); iter.hasNext();) {
/* 136:136 */      LogicalOggStream los = (LogicalOggStream)iter.next();
/* 137:137 */      los.setTime(granulePosition);
/* 138:    */    }
/* 139:    */  }
/* 140:    */  
/* 144:    */  public boolean isSeekable()
/* 145:    */  {
/* 146:146 */    return true;
/* 147:    */  }
/* 148:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.FileStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */