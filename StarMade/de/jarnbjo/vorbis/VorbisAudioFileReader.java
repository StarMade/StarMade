package de.jarnbjo.vorbis;

import de.jarnbjo.ogg.BasicStream;
import de.jarnbjo.ogg.EndOfOggStreamException;
import de.jarnbjo.ogg.FileStream;
import de.jarnbjo.ogg.LogicalOggStream;
import de.jarnbjo.ogg.OggFormatException;
import de.jarnbjo.ogg.PhysicalOggStream;
import de.jarnbjo.ogg.UncachedUrlStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;

public class VorbisAudioFileReader
  extends AudioFileReader
{
  public AudioFileFormat getAudioFileFormat(File file)
    throws IOException, UnsupportedAudioFileException
  {
    try
    {
      return getAudioFileFormat(new FileStream(new RandomAccessFile(file, "r")));
    }
    catch (OggFormatException local_e)
    {
      throw new UnsupportedAudioFileException(local_e.getMessage());
    }
  }
  
  public AudioFileFormat getAudioFileFormat(InputStream stream)
    throws IOException, UnsupportedAudioFileException
  {
    try
    {
      return getAudioFileFormat(new BasicStream(stream));
    }
    catch (OggFormatException local_e)
    {
      throw new UnsupportedAudioFileException(local_e.getMessage());
    }
  }
  
  public AudioFileFormat getAudioFileFormat(URL url)
    throws IOException, UnsupportedAudioFileException
  {
    try
    {
      return getAudioFileFormat(new UncachedUrlStream(url));
    }
    catch (OggFormatException local_e)
    {
      throw new UnsupportedAudioFileException(local_e.getMessage());
    }
  }
  
  private AudioFileFormat getAudioFileFormat(PhysicalOggStream oggStream)
    throws IOException, UnsupportedAudioFileException
  {
    try
    {
      Collection streams = oggStream.getLogicalStreams();
      if (streams.size() != 1) {
        throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
      }
      LogicalOggStream los = (LogicalOggStream)streams.iterator().next();
      if (los.getFormat() != "audio/x-vorbis") {
        throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
      }
      VorbisStream local_vs = new VorbisStream(los);
      AudioFormat audioFormat = new AudioFormat(local_vs.getIdentificationHeader().getSampleRate(), 16, local_vs.getIdentificationHeader().getChannels(), true, true);
      return new AudioFileFormat(VorbisFormatType.getInstance(), audioFormat, -1);
    }
    catch (OggFormatException streams)
    {
      throw new UnsupportedAudioFileException(streams.getMessage());
    }
    catch (VorbisFormatException streams)
    {
      throw new UnsupportedAudioFileException(streams.getMessage());
    }
  }
  
  public AudioInputStream getAudioInputStream(File file)
    throws IOException, UnsupportedAudioFileException
  {
    try
    {
      return getAudioInputStream(new FileStream(new RandomAccessFile(file, "r")));
    }
    catch (OggFormatException local_e)
    {
      throw new UnsupportedAudioFileException(local_e.getMessage());
    }
  }
  
  public AudioInputStream getAudioInputStream(InputStream stream)
    throws IOException, UnsupportedAudioFileException
  {
    try
    {
      return getAudioInputStream(new BasicStream(stream));
    }
    catch (OggFormatException local_e)
    {
      throw new UnsupportedAudioFileException(local_e.getMessage());
    }
  }
  
  public AudioInputStream getAudioInputStream(URL url)
    throws IOException, UnsupportedAudioFileException
  {
    try
    {
      return getAudioInputStream(new UncachedUrlStream(url));
    }
    catch (OggFormatException local_e)
    {
      throw new UnsupportedAudioFileException(local_e.getMessage());
    }
  }
  
  private AudioInputStream getAudioInputStream(PhysicalOggStream oggStream)
    throws IOException, UnsupportedAudioFileException
  {
    try
    {
      Collection streams = oggStream.getLogicalStreams();
      if (streams.size() != 1) {
        throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
      }
      LogicalOggStream los = (LogicalOggStream)streams.iterator().next();
      if (los.getFormat() != "audio/x-vorbis") {
        throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
      }
      VorbisStream local_vs = new VorbisStream(los);
      AudioFormat audioFormat = new AudioFormat(local_vs.getIdentificationHeader().getSampleRate(), 16, local_vs.getIdentificationHeader().getChannels(), true, true);
      return new AudioInputStream(new VorbisInputStream(local_vs), audioFormat, -1L);
    }
    catch (OggFormatException streams)
    {
      throw new UnsupportedAudioFileException(streams.getMessage());
    }
    catch (VorbisFormatException streams)
    {
      throw new UnsupportedAudioFileException(streams.getMessage());
    }
  }
  
  public static class VorbisInputStream
    extends InputStream
  {
    private VorbisStream source;
    private byte[] buffer = new byte[8192];
    
    public VorbisInputStream(VorbisStream source)
    {
      this.source = source;
    }
    
    public int read()
      throws IOException
    {
      return 0;
    }
    
    public int read(byte[] buffer)
      throws IOException
    {
      return read(buffer, 0, buffer.length);
    }
    
    public int read(byte[] buffer, int offset, int length)
      throws IOException
    {
      try
      {
        return this.source.readPcm(buffer, offset, length);
      }
      catch (EndOfOggStreamException local_e) {}
      return -1;
    }
  }
  
  public static class VorbisFormatType
    extends AudioFileFormat.Type
  {
    private static final VorbisFormatType instance = new VorbisFormatType();
    
    private VorbisFormatType()
    {
      super("ogg");
    }
    
    public static AudioFileFormat.Type getInstance()
    {
      return instance;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.VorbisAudioFileReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */