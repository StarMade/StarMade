/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */
/*  30:    */class SetupHeader
/*  31:    */{
/*  32:    */  private static final long HEADER = 126896460427126L;
/*  33:    */  private CodeBook[] codeBooks;
/*  34:    */  private Floor[] floors;
/*  35:    */  private Residue[] residues;
/*  36:    */  private Mapping[] mappings;
/*  37:    */  private Mode[] modes;
/*  38:    */  
/*  39:    */  public SetupHeader(VorbisStream vorbis, BitInputStream source)
/*  40:    */    throws VorbisFormatException, IOException
/*  41:    */  {
/*  42: 42 */    if (source.getLong(48) != 126896460427126L) {
/*  43: 43 */      throw new VorbisFormatException("The setup header has an illegal leading.");
/*  44:    */    }
/*  45:    */    
/*  48: 48 */    int codeBookCount = source.getInt(8) + 1;
/*  49: 49 */    this.codeBooks = new CodeBook[codeBookCount];
/*  50:    */    
/*  51: 51 */    for (int i = 0; i < this.codeBooks.length; i++) {
/*  52: 52 */      this.codeBooks[i] = new CodeBook(source);
/*  53:    */    }
/*  54:    */    
/*  58: 58 */    int timeCount = source.getInt(6) + 1;
/*  59: 59 */    for (int i = 0; i < timeCount; i++) {
/*  60: 60 */      if (source.getInt(16) != 0) {
/*  61: 61 */        throw new VorbisFormatException("Time domain transformation != 0");
/*  62:    */      }
/*  63:    */    }
/*  64:    */    
/*  67: 67 */    int floorCount = source.getInt(6) + 1;
/*  68: 68 */    this.floors = new Floor[floorCount];
/*  69:    */    
/*  70: 70 */    for (int i = 0; i < floorCount; i++) {
/*  71: 71 */      this.floors[i] = Floor.createInstance(source, this);
/*  72:    */    }
/*  73:    */    
/*  76: 76 */    int residueCount = source.getInt(6) + 1;
/*  77: 77 */    this.residues = new Residue[residueCount];
/*  78:    */    
/*  79: 79 */    for (int i = 0; i < residueCount; i++) {
/*  80: 80 */      this.residues[i] = Residue.createInstance(source, this);
/*  81:    */    }
/*  82:    */    
/*  85: 85 */    int mappingCount = source.getInt(6) + 1;
/*  86: 86 */    this.mappings = new Mapping[mappingCount];
/*  87:    */    
/*  88: 88 */    for (int i = 0; i < mappingCount; i++) {
/*  89: 89 */      this.mappings[i] = Mapping.createInstance(vorbis, source, this);
/*  90:    */    }
/*  91:    */    
/*  94: 94 */    int modeCount = source.getInt(6) + 1;
/*  95: 95 */    this.modes = new Mode[modeCount];
/*  96:    */    
/*  97: 97 */    for (int i = 0; i < modeCount; i++) {
/*  98: 98 */      this.modes[i] = new Mode(source, this);
/*  99:    */    }
/* 100:    */    
/* 101:101 */    if (!source.getBit()) {
/* 102:102 */      throw new VorbisFormatException("The setup header framing bit is incorrect.");
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 106:    */  public CodeBook[] getCodeBooks() {
/* 107:107 */    return this.codeBooks;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public Floor[] getFloors() {
/* 111:111 */    return this.floors;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public Residue[] getResidues() {
/* 115:115 */    return this.residues;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public Mapping[] getMappings() {
/* 119:119 */    return this.mappings;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public Mode[] getModes() {
/* 123:123 */    return this.modes;
/* 124:    */  }
/* 125:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.SetupHeader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */