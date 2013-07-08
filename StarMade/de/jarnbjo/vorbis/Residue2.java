package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;

class Residue2
  extends Residue
{
  private double[][] decodedVectors;
  
  private Residue2() {}
  
  protected Residue2(BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    super(source, header);
  }
  
  protected int getType()
  {
    return 2;
  }
  
  protected void decodeResidue(VorbisStream vorbis, BitInputStream source, Mode mode, int local_ch, boolean[] doNotDecodeFlags, float[][] vectors)
    throws VorbisFormatException, IOException
  {
    Residue.Look look = getLook(vorbis, mode);
    CodeBook codeBook = vorbis.getSetupHeader().getCodeBooks()[getClassBook()];
    int classvalsPerCodeword = codeBook.getDimensions();
    int nToRead = getEnd() - getBegin();
    int partitionsToRead = nToRead / getPartitionSize();
    int samplesPerPartition = getPartitionSize();
    int partitionsPerWord = look.getPhraseBook().getDimensions();
    int partWords = (partitionsToRead + partitionsPerWord - 1) / partitionsPerWord;
    int realCh = 0;
    for (int local_i = 0; local_i < doNotDecodeFlags.length; local_i++) {
      if (doNotDecodeFlags[local_i] == 0) {
        realCh++;
      }
    }
    float[][] local_i = new float[realCh][];
    realCh = 0;
    for (int local_i1 = 0; local_i1 < doNotDecodeFlags.length; local_i1++) {
      if (doNotDecodeFlags[local_i1] == 0) {
        local_i[(realCh++)] = vectors[local_i1];
      }
    }
    int[][] local_i1 = new int[partWords][];
    for (int local_s = 0; local_s < look.getStages(); local_s++)
    {
      int local_i2 = 0;
      for (int local_l = 0; local_i2 < partitionsToRead; local_l++)
      {
        if (local_s == 0)
        {
          int temp = source.getInt(look.getPhraseBook().getHuffmanRoot());
          if (temp == -1) {
            throw new VorbisFormatException("");
          }
          local_i1[local_l] = look.getDecodeMap()[temp];
          if (local_i1[local_l] == null) {
            throw new VorbisFormatException("");
          }
        }
        int temp = 0;
        while ((temp < partitionsPerWord) && (local_i2 < partitionsToRead))
        {
          int offset = this.begin + local_i2 * samplesPerPartition;
          if ((this.cascade[local_i1[local_l][temp]] & 1 << local_s) != 0)
          {
            CodeBook stagebook = vorbis.getSetupHeader().getCodeBooks()[look.getPartBooks()[local_i1[local_l][temp]][local_s]];
            if (stagebook != null) {
              stagebook.readVvAdd(local_i, source, offset, samplesPerPartition);
            }
          }
          temp++;
          local_i2++;
        }
      }
    }
  }
  
  public Object clone()
  {
    Residue2 clone = new Residue2();
    fill(clone);
    return clone;
  }
  
  protected double[][] getDecodedVectors()
  {
    return this.decodedVectors;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Residue2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */