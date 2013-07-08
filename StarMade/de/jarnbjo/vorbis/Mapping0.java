/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.PrintStream;
/*   6:    */
/*  24:    */class Mapping0
/*  25:    */  extends Mapping
/*  26:    */{
/*  27:    */  private int[] magnitudes;
/*  28:    */  private int[] angles;
/*  29:    */  private int[] mux;
/*  30:    */  private int[] submapFloors;
/*  31:    */  private int[] submapResidues;
/*  32:    */  
/*  33:    */  protected Mapping0(VorbisStream vorbis, BitInputStream source, SetupHeader header)
/*  34:    */    throws VorbisFormatException, IOException
/*  35:    */  {
/*  36: 36 */    int submaps = 1;
/*  37:    */    
/*  38: 38 */    if (source.getBit()) {
/*  39: 39 */      submaps = source.getInt(4) + 1;
/*  40:    */    }
/*  41:    */    
/*  44: 44 */    int channels = vorbis.getIdentificationHeader().getChannels();
/*  45: 45 */    int ilogChannels = Util.ilog(channels - 1);
/*  46:    */    
/*  49: 49 */    if (source.getBit()) {
/*  50: 50 */      int couplingSteps = source.getInt(8) + 1;
/*  51: 51 */      this.magnitudes = new int[couplingSteps];
/*  52: 52 */      this.angles = new int[couplingSteps];
/*  53:    */      
/*  54: 54 */      for (int i = 0; i < couplingSteps; i++) {
/*  55: 55 */        this.magnitudes[i] = source.getInt(ilogChannels);
/*  56: 56 */        this.angles[i] = source.getInt(ilogChannels);
/*  57: 57 */        if ((this.magnitudes[i] == this.angles[i]) || (this.magnitudes[i] >= channels) || (this.angles[i] >= channels)) {
/*  58: 58 */          System.err.println(this.magnitudes[i]);
/*  59: 59 */          System.err.println(this.angles[i]);
/*  60: 60 */          throw new VorbisFormatException("The channel magnitude and/or angle mismatch.");
/*  61:    */        }
/*  62:    */      }
/*  63:    */    }
/*  64:    */    else {
/*  65: 65 */      this.magnitudes = new int[0];
/*  66: 66 */      this.angles = new int[0];
/*  67:    */    }
/*  68:    */    
/*  69: 69 */    if (source.getInt(2) != 0) {
/*  70: 70 */      throw new VorbisFormatException("A reserved mapping field has an invalid value.");
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    this.mux = new int[channels];
/*  74: 74 */    if (submaps > 1) {
/*  75: 75 */      for (int i = 0; i < channels; i++) {
/*  76: 76 */        this.mux[i] = source.getInt(4);
/*  77: 77 */        if (this.mux[i] > submaps) {
/*  78: 78 */          throw new VorbisFormatException("A mapping mux value is higher than the number of submaps");
/*  79:    */        }
/*  80:    */        
/*  81:    */      }
/*  82:    */    } else {
/*  83: 83 */      for (int i = 0; i < channels; i++) {
/*  84: 84 */        this.mux[i] = 0;
/*  85:    */      }
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    this.submapFloors = new int[submaps];
/*  89: 89 */    this.submapResidues = new int[submaps];
/*  90:    */    
/*  91: 91 */    int floorCount = header.getFloors().length;
/*  92: 92 */    int residueCount = header.getResidues().length;
/*  93:    */    
/*  94: 94 */    for (int i = 0; i < submaps; i++) {
/*  95: 95 */      source.getInt(8);
/*  96: 96 */      this.submapFloors[i] = source.getInt(8);
/*  97: 97 */      this.submapResidues[i] = source.getInt(8);
/*  98:    */      
/*  99: 99 */      if (this.submapFloors[i] > floorCount) {
/* 100:100 */        throw new VorbisFormatException("A mapping floor value is higher than the number of floors.");
/* 101:    */      }
/* 102:    */      
/* 103:103 */      if (this.submapResidues[i] > residueCount) {
/* 104:104 */        throw new VorbisFormatException("A mapping residue value is higher than the number of residues.");
/* 105:    */      }
/* 106:    */    }
/* 107:    */  }
/* 108:    */  
/* 109:    */  protected int getType() {
/* 110:110 */    return 0;
/* 111:    */  }
/* 112:    */  
/* 113:    */  protected int[] getAngles() {
/* 114:114 */    return this.angles;
/* 115:    */  }
/* 116:    */  
/* 117:    */  protected int[] getMagnitudes() {
/* 118:118 */    return this.magnitudes;
/* 119:    */  }
/* 120:    */  
/* 121:    */  protected int[] getMux() {
/* 122:122 */    return this.mux;
/* 123:    */  }
/* 124:    */  
/* 125:    */  protected int[] getSubmapFloors() {
/* 126:126 */    return this.submapFloors;
/* 127:    */  }
/* 128:    */  
/* 129:    */  protected int[] getSubmapResidues() {
/* 130:130 */    return this.submapResidues;
/* 131:    */  }
/* 132:    */  
/* 133:    */  protected int getCouplingSteps() {
/* 134:134 */    return this.angles.length;
/* 135:    */  }
/* 136:    */  
/* 137:    */  protected int getSubmaps() {
/* 138:138 */    return this.submapFloors.length;
/* 139:    */  }
/* 140:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Mapping0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */