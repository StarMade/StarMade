package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import de.jarnbjo.util.io.HuffmanNode;
import java.io.IOException;
import java.util.Arrays;

class CodeBook
{
  private HuffmanNode huffmanRoot;
  private int dimensions;
  private int entries;
  private int[] entryLengths;
  private float[][] valueVector;
  private static long totalTime = 0L;
  
  protected CodeBook(BitInputStream source)
    throws VorbisFormatException, IOException
  {
    if (source.getInt(24) != 5653314) {
      throw new VorbisFormatException("The code book sync pattern is not correct.");
    }
    this.dimensions = source.getInt(16);
    this.entries = source.getInt(24);
    this.entryLengths = new int[this.entries];
    boolean ordered = source.getBit();
    if (ordered)
    {
      int local_cl = source.getInt(5) + 1;
      int local_i = 0;
      while (local_i < this.entryLengths.length)
      {
        int num = source.getInt(Util.ilog(this.entryLengths.length - local_i));
        if (local_i + num > this.entryLengths.length) {
          throw new VorbisFormatException("The codebook entry length list is longer than the actual number of entry lengths.");
        }
        Arrays.fill(this.entryLengths, local_i, local_i + num, local_cl);
        local_cl++;
        local_i += num;
      }
    }
    else
    {
      boolean local_cl = source.getBit();
      if (local_cl) {
        for (int local_i = 0; local_i < this.entryLengths.length; local_i++) {
          if (source.getBit()) {
            this.entryLengths[local_i] = (source.getInt(5) + 1);
          } else {
            this.entryLengths[local_i] = -1;
          }
        }
      } else {
        for (int local_i = 0; local_i < this.entryLengths.length; local_i++) {
          this.entryLengths[local_i] = (source.getInt(5) + 1);
        }
      }
    }
    if (!createHuffmanTree(this.entryLengths)) {
      throw new VorbisFormatException("An exception was thrown when building the codebook Huffman tree.");
    }
    int local_cl = source.getInt(4);
    switch (local_cl)
    {
    case 0: 
      break;
    case 1: 
    case 2: 
      float local_i = Util.float32unpack(source.getInt(32));
      float num = Util.float32unpack(source.getInt(32));
      int codeBookValueBits = source.getInt(4) + 1;
      boolean codeBookSequenceP = source.getBit();
      int codeBookLookupValues = 0;
      if (local_cl == 1) {
        codeBookLookupValues = Util.lookup1Values(this.entries, this.dimensions);
      } else {
        codeBookLookupValues = this.entries * this.dimensions;
      }
      int[] codeBookMultiplicands = new int[codeBookLookupValues];
      for (int local_i1 = 0; local_i1 < codeBookMultiplicands.length; local_i1++) {
        codeBookMultiplicands[local_i1] = source.getInt(codeBookValueBits);
      }
      this.valueVector = new float[this.entries][this.dimensions];
      if (local_cl == 1) {
        for (int local_i1 = 0; local_i1 < this.entries; local_i1++)
        {
          float last = 0.0F;
          int indexDivisor = 1;
          for (int local_j = 0; local_j < this.dimensions; local_j++)
          {
            int multiplicandOffset = local_i1 / indexDivisor % codeBookLookupValues;
            this.valueVector[local_i1][local_j] = (codeBookMultiplicands[multiplicandOffset] * num + local_i + last);
            if (codeBookSequenceP) {
              last = this.valueVector[local_i1][local_j];
            }
            indexDivisor *= codeBookLookupValues;
          }
        }
      } else {
        throw new UnsupportedOperationException();
      }
      break;
    default: 
      throw new VorbisFormatException("Unsupported codebook lookup type: " + local_cl);
    }
  }
  
  private boolean createHuffmanTree(int[] entryLengths)
  {
    this.huffmanRoot = new HuffmanNode();
    for (int local_i = 0; local_i < entryLengths.length; local_i++)
    {
      int local_el = entryLengths[local_i];
      if ((local_el > 0) && (!this.huffmanRoot.setNewValue(local_el, local_i))) {
        return false;
      }
    }
    return true;
  }
  
  protected int getDimensions()
  {
    return this.dimensions;
  }
  
  protected int getEntries()
  {
    return this.entries;
  }
  
  protected HuffmanNode getHuffmanRoot()
  {
    return this.huffmanRoot;
  }
  
  protected int readInt(BitInputStream source)
    throws IOException
  {
    return source.getInt(this.huffmanRoot);
  }
  
  protected void readVvAdd(float[][] local_a, BitInputStream source, int offset, int length)
    throws VorbisFormatException, IOException
  {
    int chptr = 0;
    int local_ch = local_a.length;
    if (local_ch == 0) {
      return;
    }
    int lim = (offset + length) / local_ch;
    int local_i = offset / local_ch;
    while (local_i < lim)
    {
      float[] local_ve = this.valueVector[source.getInt(this.huffmanRoot)];
      for (int local_j = 0; local_j < this.dimensions; local_j++)
      {
        local_a[(chptr++)][local_i] += local_ve[local_j];
        if (chptr == local_ch)
        {
          chptr = 0;
          local_i++;
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.CodeBook
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */