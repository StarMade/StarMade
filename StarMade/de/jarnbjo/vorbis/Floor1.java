package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class Floor1
  extends Floor
  implements Cloneable
{
  private int[] partitionClassList;
  private int maximumClass;
  private int multiplier;
  private int rangeBits;
  private int[] classDimensions;
  private int[] classSubclasses;
  private int[] classMasterbooks;
  private int[][] subclassBooks;
  private int[] xList;
  private int[] yList;
  private int[] lowNeighbours;
  private int[] highNeighbours;
  private static final int[] RANGES = { 256, 128, 86, 64 };
  
  private Floor1() {}
  
  protected Floor1(BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    this.maximumClass = -1;
    int partitions = source.getInt(5);
    this.partitionClassList = new int[partitions];
    for (int local_i = 0; local_i < this.partitionClassList.length; local_i++)
    {
      this.partitionClassList[local_i] = source.getInt(4);
      if (this.partitionClassList[local_i] > this.maximumClass) {
        this.maximumClass = this.partitionClassList[local_i];
      }
    }
    this.classDimensions = new int[this.maximumClass + 1];
    this.classSubclasses = new int[this.maximumClass + 1];
    this.classMasterbooks = new int[this.maximumClass + 1];
    this.subclassBooks = new int[this.maximumClass + 1][];
    int local_i = 2;
    for (int local_i1 = 0; local_i1 <= this.maximumClass; local_i1++)
    {
      this.classDimensions[local_i1] = (source.getInt(3) + 1);
      local_i += this.classDimensions[local_i1];
      this.classSubclasses[local_i1] = source.getInt(2);
      if ((this.classDimensions[local_i1] > header.getCodeBooks().length) || (this.classSubclasses[local_i1] > header.getCodeBooks().length)) {
        throw new VorbisFormatException("There is a class dimension or class subclasses entry higher than the number of codebooks in the setup header.");
      }
      if (this.classSubclasses[local_i1] != 0) {
        this.classMasterbooks[local_i1] = source.getInt(8);
      }
      this.subclassBooks[local_i1] = new int[1 << this.classSubclasses[local_i1]];
      for (int local_j = 0; local_j < this.subclassBooks[local_i1].length; local_j++) {
        this.subclassBooks[local_i1][local_j] = (source.getInt(8) - 1);
      }
    }
    this.multiplier = (source.getInt(2) + 1);
    this.rangeBits = source.getInt(4);
    int local_i1 = 0;
    ArrayList local_j = new ArrayList();
    local_j.add(new Integer(0));
    local_j.add(new Integer(1 << this.rangeBits));
    for (int local_i2 = 0; local_i2 < partitions; local_i2++) {
      for (int local_j1 = 0; local_j1 < this.classDimensions[this.partitionClassList[local_i2]]; local_j1++) {
        local_j.add(new Integer(source.getInt(this.rangeBits)));
      }
    }
    this.xList = new int[local_j.size()];
    this.lowNeighbours = new int[this.xList.length];
    this.highNeighbours = new int[this.xList.length];
    Iterator local_i2 = local_j.iterator();
    for (int local_j1 = 0; local_j1 < this.xList.length; local_j1++) {
      this.xList[local_j1] = ((Integer)local_i2.next()).intValue();
    }
    for (int local_j1 = 0; local_j1 < this.xList.length; local_j1++)
    {
      this.lowNeighbours[local_j1] = Util.lowNeighbour(this.xList, local_j1);
      this.highNeighbours[local_j1] = Util.highNeighbour(this.xList, local_j1);
    }
  }
  
  protected int getType()
  {
    return 1;
  }
  
  protected Floor decodeFloor(VorbisStream vorbis, BitInputStream source)
    throws VorbisFormatException, IOException
  {
    if (!source.getBit()) {
      return null;
    }
    Floor1 clone = (Floor1)clone();
    clone.yList = new int[this.xList.length];
    int range = RANGES[(this.multiplier - 1)];
    clone.yList[0] = source.getInt(Util.ilog(range - 1));
    clone.yList[1] = source.getInt(Util.ilog(range - 1));
    int offset = 2;
    for (int local_i = 0; local_i < this.partitionClassList.length; local_i++)
    {
      int cls = this.partitionClassList[local_i];
      int cdim = this.classDimensions[cls];
      int cbits = this.classSubclasses[cls];
      int csub = (1 << cbits) - 1;
      int cval = 0;
      if (cbits > 0) {
        cval = source.getInt(vorbis.getSetupHeader().getCodeBooks()[this.classMasterbooks[cls]].getHuffmanRoot());
      }
      for (int local_j = 0; local_j < cdim; local_j++)
      {
        int book = this.subclassBooks[cls][(cval & csub)];
        cval >>>= cbits;
        if (book >= 0) {
          clone.yList[(local_j + offset)] = source.getInt(vorbis.getSetupHeader().getCodeBooks()[book].getHuffmanRoot());
        } else {
          clone.yList[(local_j + offset)] = 0;
        }
      }
      offset += cdim;
    }
    return clone;
  }
  
  protected void computeFloor(float[] vector)
  {
    int local_n = vector.length;
    int values = this.xList.length;
    boolean[] step2Flags = new boolean[values];
    int range = RANGES[(this.multiplier - 1)];
    for (int local_i = 2; local_i < values; local_i++)
    {
      int lowNeighbourOffset = this.lowNeighbours[local_i];
      int highNeighbourOffset = this.highNeighbours[local_i];
      int predicted = Util.renderPoint(this.xList[lowNeighbourOffset], this.xList[highNeighbourOffset], this.yList[lowNeighbourOffset], this.yList[highNeighbourOffset], this.xList[local_i]);
      int val = this.yList[local_i];
      int highRoom = range - predicted;
      int lowRoom = predicted;
      int room = highRoom < lowRoom ? highRoom * 2 : lowRoom * 2;
      if (val != 0)
      {
        step2Flags[lowNeighbourOffset] = true;
        step2Flags[highNeighbourOffset] = true;
        step2Flags[local_i] = true;
        if (val >= room) {
          this.yList[local_i] = (highRoom > lowRoom ? val - lowRoom + predicted : -val + highRoom + predicted - 1);
        } else {
          this.yList[local_i] = ((val & 0x1) == 1 ? predicted - (val + 1 >> 1) : predicted + (val >> 1));
        }
      }
      else
      {
        step2Flags[local_i] = false;
        this.yList[local_i] = predicted;
      }
    }
    int[] local_i = new int[values];
    System.arraycopy(this.xList, 0, local_i, 0, values);
    sort(local_i, this.yList, step2Flags);
    int lowNeighbourOffset = 0;
    int highNeighbourOffset = 0;
    int predicted = 0;
    int val = this.yList[0] * this.multiplier;
    float[] highRoom = new float[vector.length];
    float[] lowRoom = new float[vector.length];
    Arrays.fill(highRoom, 1.0F);
    System.arraycopy(vector, 0, lowRoom, 0, vector.length);
    for (int room = 1; room < values; room++) {
      if (step2Flags[room] != 0)
      {
        highNeighbourOffset = this.yList[room] * this.multiplier;
        lowNeighbourOffset = local_i[room];
        Util.renderLine(predicted, val, lowNeighbourOffset, highNeighbourOffset, vector);
        Util.renderLine(predicted, val, lowNeighbourOffset, highNeighbourOffset, highRoom);
        predicted = lowNeighbourOffset;
        val = highNeighbourOffset;
      }
    }
    float room = DB_STATIC_TABLE[highNeighbourOffset];
    while (lowNeighbourOffset < local_n / 2) {
      vector[(lowNeighbourOffset++)] = room;
    }
  }
  
  public Object clone()
  {
    Floor1 clone = new Floor1();
    clone.classDimensions = this.classDimensions;
    clone.classMasterbooks = this.classMasterbooks;
    clone.classSubclasses = this.classSubclasses;
    clone.maximumClass = this.maximumClass;
    clone.multiplier = this.multiplier;
    clone.partitionClassList = this.partitionClassList;
    clone.rangeBits = this.rangeBits;
    clone.subclassBooks = this.subclassBooks;
    clone.xList = this.xList;
    clone.yList = this.yList;
    clone.lowNeighbours = this.lowNeighbours;
    clone.highNeighbours = this.highNeighbours;
    return clone;
  }
  
  private static final void sort(int[] local_x, int[] local_y, boolean[] local_b)
  {
    int off = 0;
    int len = local_x.length;
    int lim = len + off;
    for (int local_i = off; local_i < lim; local_i++) {
      for (int local_j = local_i; (local_j > off) && (local_x[(local_j - 1)] > local_x[local_j]); local_j--)
      {
        int itmp = local_x[local_j];
        local_x[local_j] = local_x[(local_j - 1)];
        local_x[(local_j - 1)] = itmp;
        itmp = local_y[local_j];
        local_y[local_j] = local_y[(local_j - 1)];
        local_y[(local_j - 1)] = itmp;
        boolean btmp = local_b[local_j];
        local_b[local_j] = local_b[(local_j - 1)];
        local_b[(local_j - 1)] = btmp;
      }
    }
  }
  
  private static final void swap(int[] local_x, int local_a, int local_b)
  {
    int local_t = local_x[local_a];
    local_x[local_a] = local_x[local_b];
    local_x[local_b] = local_t;
  }
  
  private static final void swap(boolean[] local_x, int local_a, int local_b)
  {
    boolean local_t = local_x[local_a];
    local_x[local_a] = local_x[local_b];
    local_x[local_b] = local_t;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Floor1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */