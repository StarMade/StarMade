package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;
import java.util.HashMap;

abstract class Residue
{
  protected int begin;
  protected int end;
  protected int partitionSize;
  protected int classifications;
  protected int classBook;
  protected int[] cascade;
  protected int[][] books;
  protected HashMap looks = new HashMap();
  
  protected Residue() {}
  
  protected Residue(BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    this.begin = source.getInt(24);
    this.end = source.getInt(24);
    this.partitionSize = (source.getInt(24) + 1);
    this.classifications = (source.getInt(6) + 1);
    this.classBook = source.getInt(8);
    this.cascade = new int[this.classifications];
    int acc = 0;
    for (int local_i = 0; local_i < this.classifications; local_i++)
    {
      int highBits = 0;
      int lowBits = 0;
      lowBits = source.getInt(3);
      if (source.getBit()) {
        highBits = source.getInt(5);
      }
      this.cascade[local_i] = (highBits << 3 | lowBits);
      acc += Util.icount(this.cascade[local_i]);
    }
    this.books = new int[this.classifications][8];
    for (int local_i = 0; local_i < this.classifications; local_i++) {
      for (int highBits = 0; highBits < 8; highBits++) {
        if ((this.cascade[local_i] & 1 << highBits) != 0)
        {
          this.books[local_i][highBits] = source.getInt(8);
          if (this.books[local_i][highBits] > header.getCodeBooks().length) {
            throw new VorbisFormatException("Reference to invalid codebook entry in residue header.");
          }
        }
      }
    }
  }
  
  protected static Residue createInstance(BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    int type = source.getInt(16);
    switch (type)
    {
    case 0: 
      return new Residue0(source, header);
    case 1: 
      return new Residue2(source, header);
    case 2: 
      return new Residue2(source, header);
    }
    throw new VorbisFormatException("Residue type " + type + " is not supported.");
  }
  
  protected abstract int getType();
  
  protected abstract void decodeResidue(VorbisStream paramVorbisStream, BitInputStream paramBitInputStream, Mode paramMode, int paramInt, boolean[] paramArrayOfBoolean, float[][] paramArrayOfFloat)
    throws VorbisFormatException, IOException;
  
  protected int getBegin()
  {
    return this.begin;
  }
  
  protected int getEnd()
  {
    return this.end;
  }
  
  protected int getPartitionSize()
  {
    return this.partitionSize;
  }
  
  protected int getClassifications()
  {
    return this.classifications;
  }
  
  protected int getClassBook()
  {
    return this.classBook;
  }
  
  protected int[] getCascade()
  {
    return this.cascade;
  }
  
  protected int[][] getBooks()
  {
    return this.books;
  }
  
  protected final void fill(Residue clone)
  {
    clone.begin = this.begin;
    clone.books = this.books;
    clone.cascade = this.cascade;
    clone.classBook = this.classBook;
    clone.classifications = this.classifications;
    clone.end = this.end;
    clone.partitionSize = this.partitionSize;
  }
  
  protected Look getLook(VorbisStream source, Mode key)
  {
    Look look = (Look)this.looks.get(key);
    if (look == null)
    {
      look = new Look(source, key);
      this.looks.put(key, look);
    }
    return look;
  }
  
  class Look
  {
    int map;
    int parts;
    int stages;
    CodeBook[] fullbooks;
    CodeBook phrasebook;
    int[][] partbooks;
    int partvals;
    int[][] decodemap;
    int postbits;
    int phrasebits;
    int frames;
    
    protected Look(VorbisStream source, Mode mode)
    {
      int dim = 0;
      int acc = 0;
      int maxstage = 0;
      this.map = mode.getMapping();
      this.parts = Residue.this.getClassifications();
      this.fullbooks = source.getSetupHeader().getCodeBooks();
      this.phrasebook = this.fullbooks[Residue.this.getClassBook()];
      dim = this.phrasebook.getDimensions();
      this.partbooks = new int[this.parts][];
      for (int local_j = 0; local_j < this.parts; local_j++)
      {
        int stages = Util.ilog(Residue.this.getCascade()[local_j]);
        if (stages != 0)
        {
          if (stages > maxstage) {
            maxstage = stages;
          }
          this.partbooks[local_j] = new int[stages];
          for (int local_k = 0; local_k < stages; local_k++) {
            if ((Residue.this.getCascade()[local_j] & 1 << local_k) != 0) {
              this.partbooks[local_j][local_k] = Residue.this.getBooks()[local_j][local_k];
            }
          }
        }
      }
      this.partvals = ((int)Math.rint(Math.pow(this.parts, dim)));
      this.stages = maxstage;
      this.decodemap = new int[this.partvals][];
      for (int local_j = 0; local_j < this.partvals; local_j++)
      {
        int stages = local_j;
        int local_k = this.partvals / this.parts;
        this.decodemap[local_j] = new int[dim];
        for (int local_k1 = 0; local_k1 < dim; local_k1++)
        {
          int deco = stages / local_k;
          stages -= deco * local_k;
          local_k /= this.parts;
          this.decodemap[local_j][local_k1] = deco;
        }
      }
    }
    
    protected int[][] getDecodeMap()
    {
      return this.decodemap;
    }
    
    protected int getFrames()
    {
      return this.frames;
    }
    
    protected int getMap()
    {
      return this.map;
    }
    
    protected int[][] getPartBooks()
    {
      return this.partbooks;
    }
    
    protected int getParts()
    {
      return this.parts;
    }
    
    protected int getPartVals()
    {
      return this.partvals;
    }
    
    protected int getPhraseBits()
    {
      return this.phrasebits;
    }
    
    protected CodeBook getPhraseBook()
    {
      return this.phrasebook;
    }
    
    protected int getPostBits()
    {
      return this.postbits;
    }
    
    protected int getStages()
    {
      return this.stages;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Residue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */