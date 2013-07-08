/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.ogg.LogicalOggStream;
/*   4:    */import de.jarnbjo.util.io.BitInputStream;
/*   5:    */import de.jarnbjo.util.io.ByteArrayBitInputStream;
/*   6:    */import java.io.IOException;
/*   7:    */import java.util.Arrays;
/*   8:    */import java.util.LinkedList;
/*   9:    */
/*  41:    */public class VorbisStream
/*  42:    */{
/*  43:    */  private LogicalOggStream oggStream;
/*  44:    */  private IdentificationHeader identificationHeader;
/*  45:    */  private CommentHeader commentHeader;
/*  46:    */  private SetupHeader setupHeader;
/*  47:    */  private AudioPacket lastAudioPacket;
/*  48:    */  private AudioPacket nextAudioPacket;
/*  49: 49 */  private LinkedList audioPackets = new LinkedList();
/*  50:    */  
/*  51:    */  private byte[] currentPcm;
/*  52:    */  
/*  53:    */  private int currentPcmIndex;
/*  54:    */  private int currentPcmLimit;
/*  55:    */  private static final int IDENTIFICATION_HEADER = 1;
/*  56:    */  private static final int COMMENT_HEADER = 3;
/*  57:    */  private static final int SETUP_HEADER = 5;
/*  58: 58 */  private int bitIndex = 0;
/*  59: 59 */  private byte lastByte = 0;
/*  60: 60 */  private boolean initialized = false;
/*  61:    */  
/*  62: 62 */  private Object streamLock = new Object();
/*  63: 63 */  private int pageCounter = 0;
/*  64:    */  
/*  65: 65 */  private int currentBitRate = 0;
/*  66:    */  
/*  67:    */  private long currentGranulePosition;
/*  68:    */  public static final int BIG_ENDIAN = 0;
/*  69:    */  public static final int LITTLE_ENDIAN = 1;
/*  70:    */  
/*  71:    */  public VorbisStream() {}
/*  72:    */  
/*  73:    */  public VorbisStream(LogicalOggStream oggStream)
/*  74:    */    throws VorbisFormatException, IOException
/*  75:    */  {
/*  76: 76 */    this.oggStream = oggStream;
/*  77:    */    
/*  78: 78 */    for (int i = 0; i < 3; i++) {
/*  79: 79 */      BitInputStream source = new ByteArrayBitInputStream(oggStream.getNextOggPacket());
/*  80: 80 */      int headerType = source.getInt(8);
/*  81: 81 */      switch (headerType) {
/*  82:    */      case 1: 
/*  83: 83 */        this.identificationHeader = new IdentificationHeader(source);
/*  84: 84 */        break;
/*  85:    */      case 3: 
/*  86: 86 */        this.commentHeader = new CommentHeader(source);
/*  87: 87 */        break;
/*  88:    */      case 5: 
/*  89: 89 */        this.setupHeader = new SetupHeader(this, source);
/*  90:    */      }
/*  91:    */      
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    if (this.identificationHeader == null) {
/*  95: 95 */      throw new VorbisFormatException("The file has no identification header.");
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    if (this.commentHeader == null) {
/*  99: 99 */      throw new VorbisFormatException("The file has no commentHeader.");
/* 100:    */    }
/* 101:    */    
/* 102:102 */    if (this.setupHeader == null) {
/* 103:103 */      throw new VorbisFormatException("The file has no setup header.");
/* 104:    */    }
/* 105:    */    
/* 107:107 */    this.currentPcm = new byte[this.identificationHeader.getChannels() * this.identificationHeader.getBlockSize1() * 2];
/* 108:    */  }
/* 109:    */  
/* 110:    */  public IdentificationHeader getIdentificationHeader()
/* 111:    */  {
/* 112:112 */    return this.identificationHeader;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public CommentHeader getCommentHeader() {
/* 116:116 */    return this.commentHeader;
/* 117:    */  }
/* 118:    */  
/* 119:    */  protected SetupHeader getSetupHeader() {
/* 120:120 */    return this.setupHeader;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public boolean isOpen() {
/* 124:124 */    return this.oggStream.isOpen();
/* 125:    */  }
/* 126:    */  
/* 127:    */  public void close() throws IOException {
/* 128:128 */    this.oggStream.close();
/* 129:    */  }
/* 130:    */  
/* 131:    */  public int readPcm(byte[] buffer, int offset, int length) throws IOException
/* 132:    */  {
/* 133:133 */    synchronized (this.streamLock) {
/* 134:134 */      int channels = this.identificationHeader.getChannels();
/* 135:    */      
/* 136:136 */      if (this.lastAudioPacket == null) {
/* 137:137 */        this.lastAudioPacket = getNextAudioPacket();
/* 138:    */      }
/* 139:139 */      if ((this.currentPcm == null) || (this.currentPcmIndex >= this.currentPcmLimit)) {
/* 140:140 */        AudioPacket ap = getNextAudioPacket();
/* 141:    */        try {
/* 142:142 */          ap.getPcm(this.lastAudioPacket, this.currentPcm);
/* 143:143 */          this.currentPcmLimit = (ap.getNumberOfSamples() * this.identificationHeader.getChannels() * 2);
/* 144:    */        }
/* 145:    */        catch (ArrayIndexOutOfBoundsException e) {
/* 146:146 */          return 0;
/* 147:    */        }
/* 148:148 */        this.currentPcmIndex = 0;
/* 149:149 */        this.lastAudioPacket = ap;
/* 150:    */      }
/* 151:151 */      int written = 0;
/* 152:152 */      int i = 0;
/* 153:153 */      int arrIx = 0;
/* 154:154 */      for (i = this.currentPcmIndex; (i < this.currentPcmLimit) && (arrIx < length); i++) {
/* 155:155 */        buffer[(offset + arrIx++)] = this.currentPcm[i];
/* 156:156 */        written++;
/* 157:    */      }
/* 158:158 */      this.currentPcmIndex = i;
/* 159:159 */      return written;
/* 160:    */    }
/* 161:    */  }
/* 162:    */  
/* 163:    */  private AudioPacket getNextAudioPacket() throws VorbisFormatException, IOException
/* 164:    */  {
/* 165:165 */    this.pageCounter += 1;
/* 166:166 */    byte[] data = this.oggStream.getNextOggPacket();
/* 167:167 */    AudioPacket res = null;
/* 168:168 */    while (res == null) {
/* 169:    */      try {
/* 170:170 */        res = new AudioPacket(this, new ByteArrayBitInputStream(data));
/* 171:    */      }
/* 172:    */      catch (ArrayIndexOutOfBoundsException e) {}
/* 173:    */    }
/* 174:    */    
/* 176:176 */    this.currentGranulePosition += res.getNumberOfSamples();
/* 177:177 */    this.currentBitRate = (data.length * 8 * this.identificationHeader.getSampleRate() / res.getNumberOfSamples());
/* 178:178 */    return res;
/* 179:    */  }
/* 180:    */  
/* 181:    */  public long getCurrentGranulePosition() {
/* 182:182 */    return this.currentGranulePosition;
/* 183:    */  }
/* 184:    */  
/* 185:    */  public int getCurrentBitRate() {
/* 186:186 */    return this.currentBitRate;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public byte[] processPacket(byte[] packet) throws VorbisFormatException, IOException {
/* 190:190 */    if (packet.length == 0) {
/* 191:191 */      throw new VorbisFormatException("Cannot decode a vorbis packet with length = 0");
/* 192:    */    }
/* 193:193 */    if ((packet[0] & 0x1) == 1)
/* 194:    */    {
/* 195:195 */      BitInputStream source = new ByteArrayBitInputStream(packet);
/* 196:196 */      switch (source.getInt(8)) {
/* 197:    */      case 1: 
/* 198:198 */        this.identificationHeader = new IdentificationHeader(source);
/* 199:199 */        break;
/* 200:    */      case 3: 
/* 201:201 */        this.commentHeader = new CommentHeader(source);
/* 202:202 */        break;
/* 203:    */      case 5: 
/* 204:204 */        this.setupHeader = new SetupHeader(this, source);
/* 205:    */      }
/* 206:    */      
/* 207:207 */      return null;
/* 208:    */    }
/* 209:    */    
/* 211:211 */    if ((this.identificationHeader == null) || (this.commentHeader == null) || (this.setupHeader == null))
/* 212:    */    {
/* 215:215 */      throw new VorbisFormatException("Cannot decode audio packet before all three header packets have been decoded.");
/* 216:    */    }
/* 217:    */    
/* 218:218 */    AudioPacket ap = new AudioPacket(this, new ByteArrayBitInputStream(packet));
/* 219:219 */    this.currentGranulePosition += ap.getNumberOfSamples();
/* 220:    */    
/* 221:221 */    if (this.lastAudioPacket == null) {
/* 222:222 */      this.lastAudioPacket = ap;
/* 223:223 */      return null;
/* 224:    */    }
/* 225:    */    
/* 226:226 */    byte[] res = new byte[this.identificationHeader.getChannels() * ap.getNumberOfSamples() * 2];
/* 227:    */    try
/* 228:    */    {
/* 229:229 */      ap.getPcm(this.lastAudioPacket, res);
/* 230:    */    }
/* 231:    */    catch (IndexOutOfBoundsException e) {
/* 232:232 */      Arrays.fill(res, (byte)0);
/* 233:    */    }
/* 234:    */    
/* 235:235 */    this.lastAudioPacket = ap;
/* 236:    */    
/* 237:237 */    return res;
/* 238:    */  }
/* 239:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.VorbisStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */