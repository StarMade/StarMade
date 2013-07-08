/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */
/*  29:    */class Residue2
/*  30:    */  extends Residue
/*  31:    */{
/*  32:    */  private double[][] decodedVectors;
/*  33:    */  
/*  34:    */  private Residue2() {}
/*  35:    */  
/*  36:    */  protected Residue2(BitInputStream source, SetupHeader header)
/*  37:    */    throws VorbisFormatException, IOException
/*  38:    */  {
/*  39: 39 */    super(source, header);
/*  40:    */  }
/*  41:    */  
/*  42:    */  protected int getType() {
/*  43: 43 */    return 2;
/*  44:    */  }
/*  45:    */  
/*  46:    */  protected void decodeResidue(VorbisStream vorbis, BitInputStream source, Mode mode, int ch, boolean[] doNotDecodeFlags, float[][] vectors) throws VorbisFormatException, IOException
/*  47:    */  {
/*  48: 48 */    Residue.Look look = getLook(vorbis, mode);
/*  49:    */    
/*  50: 50 */    CodeBook codeBook = vorbis.getSetupHeader().getCodeBooks()[getClassBook()];
/*  51:    */    
/*  52: 52 */    int classvalsPerCodeword = codeBook.getDimensions();
/*  53: 53 */    int nToRead = getEnd() - getBegin();
/*  54: 54 */    int partitionsToRead = nToRead / getPartitionSize();
/*  55:    */    
/*  56: 56 */    int samplesPerPartition = getPartitionSize();
/*  57: 57 */    int partitionsPerWord = look.getPhraseBook().getDimensions();
/*  58:    */    
/*  59: 59 */    int partWords = (partitionsToRead + partitionsPerWord - 1) / partitionsPerWord;
/*  60:    */    
/*  61: 61 */    int realCh = 0;
/*  62: 62 */    for (int i = 0; i < doNotDecodeFlags.length; i++) {
/*  63: 63 */      if (doNotDecodeFlags[i] == 0) {
/*  64: 64 */        realCh++;
/*  65:    */      }
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    float[][] realVectors = new float[realCh][];
/*  69:    */    
/*  70: 70 */    realCh = 0;
/*  71: 71 */    for (int i = 0; i < doNotDecodeFlags.length; i++) {
/*  72: 72 */      if (doNotDecodeFlags[i] == 0) {
/*  73: 73 */        realVectors[(realCh++)] = vectors[i];
/*  74:    */      }
/*  75:    */    }
/*  76:    */    
/*  77: 77 */    int[][] partword = new int[partWords][];
/*  78: 78 */    for (int s = 0; s < look.getStages(); s++) {
/*  79: 79 */      int i = 0; for (int l = 0; i < partitionsToRead; l++) {
/*  80: 80 */        if (s == 0)
/*  81:    */        {
/*  82: 82 */          int temp = source.getInt(look.getPhraseBook().getHuffmanRoot());
/*  83: 83 */          if (temp == -1) {
/*  84: 84 */            throw new VorbisFormatException("");
/*  85:    */          }
/*  86: 86 */          partword[l] = look.getDecodeMap()[temp];
/*  87: 87 */          if (partword[l] == null) {
/*  88: 88 */            throw new VorbisFormatException("");
/*  89:    */          }
/*  90:    */        }
/*  91:    */        
/*  92: 92 */        for (int k = 0; (k < partitionsPerWord) && (i < partitionsToRead); i++) {
/*  93: 93 */          int offset = this.begin + i * samplesPerPartition;
/*  94: 94 */          if ((this.cascade[partword[l][k]] & 1 << s) != 0) {
/*  95: 95 */            CodeBook stagebook = vorbis.getSetupHeader().getCodeBooks()[look.getPartBooks()[partword[l][k]][s]];
/*  96: 96 */            if (stagebook != null) {
/*  97: 97 */              stagebook.readVvAdd(realVectors, source, offset, samplesPerPartition);
/*  98:    */            }
/*  99:    */          }
/* 100: 92 */          k++;
/* 101:    */        }
/* 102:    */      }
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 113:    */  public Object clone()
/* 114:    */  {
/* 115:107 */    Residue2 clone = new Residue2();
/* 116:108 */    fill(clone);
/* 117:109 */    return clone;
/* 118:    */  }
/* 119:    */  
/* 120:    */  protected double[][] getDecodedVectors() {
/* 121:113 */    return this.decodedVectors;
/* 122:    */  }
/* 123:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Residue2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */