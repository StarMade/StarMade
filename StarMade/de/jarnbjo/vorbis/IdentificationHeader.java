/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */
/*  34:    */public class IdentificationHeader
/*  35:    */{
/*  36:    */  private int version;
/*  37:    */  private int channels;
/*  38:    */  private int sampleRate;
/*  39:    */  private int bitrateMaximum;
/*  40:    */  private int bitrateNominal;
/*  41:    */  private int bitrateMinimum;
/*  42:    */  private int blockSize0;
/*  43:    */  private int blockSize1;
/*  44:    */  private boolean framingFlag;
/*  45: 45 */  private MdctFloat[] mdct = new MdctFloat[2];
/*  46:    */  
/*  48:    */  private static final long HEADER = 126896460427126L;
/*  49:    */  
/*  52:    */  public IdentificationHeader(BitInputStream source)
/*  53:    */    throws VorbisFormatException, IOException
/*  54:    */  {
/*  55: 55 */    long leading = source.getLong(48);
/*  56: 56 */    if (leading != 126896460427126L) {
/*  57: 57 */      throw new VorbisFormatException("The identification header has an illegal leading.");
/*  58:    */    }
/*  59: 59 */    this.version = source.getInt(32);
/*  60: 60 */    this.channels = source.getInt(8);
/*  61: 61 */    this.sampleRate = source.getInt(32);
/*  62: 62 */    this.bitrateMaximum = source.getInt(32);
/*  63: 63 */    this.bitrateNominal = source.getInt(32);
/*  64: 64 */    this.bitrateMinimum = source.getInt(32);
/*  65: 65 */    int bs = source.getInt(8);
/*  66: 66 */    this.blockSize0 = (1 << (bs & 0xF));
/*  67: 67 */    this.blockSize1 = (1 << (bs >> 4));
/*  68:    */    
/*  69: 69 */    this.mdct[0] = new MdctFloat(this.blockSize0);
/*  70: 70 */    this.mdct[1] = new MdctFloat(this.blockSize1);
/*  71:    */    
/*  74: 74 */    this.framingFlag = (source.getInt(8) != 0);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public int getSampleRate() {
/*  78: 78 */    return this.sampleRate;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public int getMaximumBitrate() {
/*  82: 82 */    return this.bitrateMaximum;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public int getNominalBitrate() {
/*  86: 86 */    return this.bitrateNominal;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public int getMinimumBitrate() {
/*  90: 90 */    return this.bitrateMinimum;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public int getChannels() {
/*  94: 94 */    return this.channels;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public int getBlockSize0() {
/*  98: 98 */    return this.blockSize0;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public int getBlockSize1() {
/* 102:102 */    return this.blockSize1;
/* 103:    */  }
/* 104:    */  
/* 105:    */  protected MdctFloat getMdct0() {
/* 106:106 */    return this.mdct[0];
/* 107:    */  }
/* 108:    */  
/* 109:    */  protected MdctFloat getMdct1() {
/* 110:110 */    return this.mdct[1];
/* 111:    */  }
/* 112:    */  
/* 113:    */  public int getVersion() {
/* 114:114 */    return this.version;
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.IdentificationHeader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */