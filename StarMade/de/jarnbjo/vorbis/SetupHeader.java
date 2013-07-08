package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;

class SetupHeader
{
  private static final long HEADER = 126896460427126L;
  private CodeBook[] codeBooks;
  private Floor[] floors;
  private Residue[] residues;
  private Mapping[] mappings;
  private Mode[] modes;
  
  public SetupHeader(VorbisStream vorbis, BitInputStream source)
    throws VorbisFormatException, IOException
  {
    if (source.getLong(48) != 126896460427126L) {
      throw new VorbisFormatException("The setup header has an illegal leading.");
    }
    int codeBookCount = source.getInt(8) + 1;
    this.codeBooks = new CodeBook[codeBookCount];
    for (int local_i = 0; local_i < this.codeBooks.length; local_i++) {
      this.codeBooks[local_i] = new CodeBook(source);
    }
    int local_i = source.getInt(6) + 1;
    for (int local_i1 = 0; local_i1 < local_i; local_i1++) {
      if (source.getInt(16) != 0) {
        throw new VorbisFormatException("Time domain transformation != 0");
      }
    }
    int local_i1 = source.getInt(6) + 1;
    this.floors = new Floor[local_i1];
    for (int local_i2 = 0; local_i2 < local_i1; local_i2++) {
      this.floors[local_i2] = Floor.createInstance(source, this);
    }
    int local_i2 = source.getInt(6) + 1;
    this.residues = new Residue[local_i2];
    for (int local_i3 = 0; local_i3 < local_i2; local_i3++) {
      this.residues[local_i3] = Residue.createInstance(source, this);
    }
    int local_i3 = source.getInt(6) + 1;
    this.mappings = new Mapping[local_i3];
    for (int local_i4 = 0; local_i4 < local_i3; local_i4++) {
      this.mappings[local_i4] = Mapping.createInstance(vorbis, source, this);
    }
    int local_i4 = source.getInt(6) + 1;
    this.modes = new Mode[local_i4];
    for (int local_i5 = 0; local_i5 < local_i4; local_i5++) {
      this.modes[local_i5] = new Mode(source, this);
    }
    if (!source.getBit()) {
      throw new VorbisFormatException("The setup header framing bit is incorrect.");
    }
  }
  
  public CodeBook[] getCodeBooks()
  {
    return this.codeBooks;
  }
  
  public Floor[] getFloors()
  {
    return this.floors;
  }
  
  public Residue[] getResidues()
  {
    return this.residues;
  }
  
  public Mapping[] getMappings()
  {
    return this.mappings;
  }
  
  public Mode[] getModes()
  {
    return this.modes;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.SetupHeader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */