/*   1:    */package de.jarnbjo.ogg;
/*   2:    */
/*   3:    */import java.io.ByteArrayOutputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.util.ArrayList;
/*   6:    */
/*  34:    */public class LogicalOggStreamImpl
/*  35:    */  implements LogicalOggStream
/*  36:    */{
/*  37:    */  private PhysicalOggStream source;
/*  38:    */  private int serialNumber;
/*  39: 39 */  private ArrayList pageNumberMapping = new ArrayList();
/*  40: 40 */  private ArrayList granulePositions = new ArrayList();
/*  41:    */  
/*  42: 42 */  private int pageIndex = 0;
/*  43:    */  
/*  44:    */  private OggPage currentPage;
/*  45:    */  private int currentSegmentIndex;
/*  46: 46 */  private boolean open = true;
/*  47:    */  
/*  48: 48 */  private String format = "application/octet-stream";
/*  49:    */  
/*  50:    */  public LogicalOggStreamImpl(PhysicalOggStream source, int serialNumber) {
/*  51: 51 */    this.source = source;
/*  52: 52 */    this.serialNumber = serialNumber;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public void addPageNumberMapping(int physicalPageNumber) {
/*  56: 56 */    this.pageNumberMapping.add(new Integer(physicalPageNumber));
/*  57:    */  }
/*  58:    */  
/*  59:    */  public void addGranulePosition(long granulePosition) {
/*  60: 60 */    this.granulePositions.add(new Long(granulePosition));
/*  61:    */  }
/*  62:    */  
/*  63:    */  public synchronized void reset() throws OggFormatException, IOException {
/*  64: 64 */    this.currentPage = null;
/*  65: 65 */    this.currentSegmentIndex = 0;
/*  66: 66 */    this.pageIndex = 0;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public synchronized OggPage getNextOggPage() throws EndOfOggStreamException, OggFormatException, IOException {
/*  70: 70 */    if (this.source.isSeekable()) {
/*  71: 71 */      this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
/*  72:    */    }
/*  73:    */    else {
/*  74: 74 */      this.currentPage = this.source.getOggPage(-1);
/*  75:    */    }
/*  76: 76 */    return this.currentPage;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public synchronized byte[] getNextOggPacket() throws EndOfOggStreamException, OggFormatException, IOException {
/*  80: 80 */    ByteArrayOutputStream res = new ByteArrayOutputStream();
/*  81: 81 */    int segmentLength = 0;
/*  82:    */    
/*  83: 83 */    if (this.currentPage == null) {
/*  84: 84 */      this.currentPage = getNextOggPage();
/*  85:    */    }
/*  86:    */    do
/*  87:    */    {
/*  88: 88 */      if (this.currentSegmentIndex >= this.currentPage.getSegmentOffsets().length) {
/*  89: 89 */        this.currentSegmentIndex = 0;
/*  90:    */        
/*  91: 91 */        if (!this.currentPage.isEos()) {
/*  92: 92 */          if ((this.source.isSeekable()) && (this.pageNumberMapping.size() <= this.pageIndex)) {
/*  93: 93 */            while (this.pageNumberMapping.size() <= this.pageIndex + 10) {
/*  94:    */              try {
/*  95: 95 */                Thread.sleep(1000L);
/*  96:    */              }
/*  97:    */              catch (InterruptedException ex) {}
/*  98:    */            }
/*  99:    */          }
/* 100:    */          
/* 101:101 */          this.currentPage = getNextOggPage();
/* 102:    */          
/* 103:103 */          if ((res.size() == 0) && (this.currentPage.isContinued())) {
/* 104:104 */            boolean done = false;
/* 105:105 */            while (!done) {
/* 106:106 */              if (this.currentPage.getSegmentLengths()[(this.currentSegmentIndex++)] != 255) {
/* 107:107 */                done = true;
/* 108:    */              }
/* 109:109 */              if (this.currentSegmentIndex > this.currentPage.getSegmentTable().length) {
/* 110:110 */                this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
/* 111:    */              }
/* 112:    */            }
/* 113:    */          }
/* 114:    */        }
/* 115:    */        else {
/* 116:116 */          throw new EndOfOggStreamException();
/* 117:    */        }
/* 118:    */      }
/* 119:119 */      segmentLength = this.currentPage.getSegmentLengths()[this.currentSegmentIndex];
/* 120:120 */      res.write(this.currentPage.getData(), this.currentPage.getSegmentOffsets()[this.currentSegmentIndex], segmentLength);
/* 121:121 */      this.currentSegmentIndex += 1;
/* 122:122 */    } while (segmentLength == 255);
/* 123:    */    
/* 124:124 */    return res.toByteArray();
/* 125:    */  }
/* 126:    */  
/* 127:    */  public boolean isOpen() {
/* 128:128 */    return this.open;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public void close() throws IOException {
/* 132:132 */    this.open = false;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public long getMaximumGranulePosition() {
/* 136:136 */    Long mgp = (Long)this.granulePositions.get(this.granulePositions.size() - 1);
/* 137:137 */    return mgp.longValue();
/* 138:    */  }
/* 139:    */  
/* 140:    */  public synchronized long getTime() {
/* 141:141 */    return this.currentPage != null ? this.currentPage.getAbsoluteGranulePosition() : -1L;
/* 142:    */  }
/* 143:    */  
/* 144:    */  public synchronized void setTime(long granulePosition) throws IOException
/* 145:    */  {
/* 146:146 */    int page = 0;
/* 147:147 */    for (page = 0; page < this.granulePositions.size(); page++) {
/* 148:148 */      Long gp = (Long)this.granulePositions.get(page);
/* 149:149 */      if (gp.longValue() > granulePosition) {
/* 150:    */        break;
/* 151:    */      }
/* 152:    */    }
/* 153:    */    
/* 154:154 */    this.pageIndex = page;
/* 155:155 */    this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
/* 156:156 */    this.currentSegmentIndex = 0;
/* 157:157 */    int segmentLength = 0;
/* 158:    */    do {
/* 159:159 */      if (this.currentSegmentIndex >= this.currentPage.getSegmentOffsets().length) {
/* 160:160 */        this.currentSegmentIndex = 0;
/* 161:161 */        if (this.pageIndex >= this.pageNumberMapping.size()) {
/* 162:162 */          throw new EndOfOggStreamException();
/* 163:    */        }
/* 164:164 */        this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
/* 165:    */      }
/* 166:166 */      segmentLength = this.currentPage.getSegmentLengths()[this.currentSegmentIndex];
/* 167:167 */      this.currentSegmentIndex += 1;
/* 168:168 */    } while (segmentLength == 255);
/* 169:    */  }
/* 170:    */  
/* 171:    */  public void checkFormat(OggPage page) {
/* 172:172 */    byte[] data = page.getData();
/* 173:    */    
/* 174:174 */    if ((data.length >= 7) && (data[1] == 118) && (data[2] == 111) && (data[3] == 114) && (data[4] == 98) && (data[5] == 105) && (data[6] == 115))
/* 175:    */    {
/* 182:182 */      this.format = "audio/x-vorbis";
/* 183:    */    }
/* 184:184 */    else if ((data.length >= 7) && (data[1] == 116) && (data[2] == 104) && (data[3] == 101) && (data[4] == 111) && (data[5] == 114) && (data[6] == 97))
/* 185:    */    {
/* 192:192 */      this.format = "video/x-theora";
/* 193:    */    }
/* 194:194 */    else if ((data.length == 4) && (data[0] == 102) && (data[1] == 76) && (data[2] == 97) && (data[3] == 67))
/* 195:    */    {
/* 200:200 */      this.format = "audio/x-flac";
/* 201:    */    }
/* 202:    */  }
/* 203:    */  
/* 204:    */  public String getFormat() {
/* 205:205 */    return this.format;
/* 206:    */  }
/* 207:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.LogicalOggStreamImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */