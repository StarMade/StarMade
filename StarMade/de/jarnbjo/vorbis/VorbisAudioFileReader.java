/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.ogg.BasicStream;
/*   4:    */import de.jarnbjo.ogg.EndOfOggStreamException;
/*   5:    */import de.jarnbjo.ogg.FileStream;
/*   6:    */import de.jarnbjo.ogg.LogicalOggStream;
/*   7:    */import de.jarnbjo.ogg.OggFormatException;
/*   8:    */import de.jarnbjo.ogg.PhysicalOggStream;
/*   9:    */import de.jarnbjo.ogg.UncachedUrlStream;
/*  10:    */import java.io.File;
/*  11:    */import java.io.IOException;
/*  12:    */import java.io.InputStream;
/*  13:    */import java.io.RandomAccessFile;
/*  14:    */import java.net.URL;
/*  15:    */import java.util.Collection;
/*  16:    */import java.util.Iterator;
/*  17:    */import javax.sound.sampled.AudioFileFormat;
/*  18:    */import javax.sound.sampled.AudioFileFormat.Type;
/*  19:    */import javax.sound.sampled.AudioFormat;
/*  20:    */import javax.sound.sampled.AudioInputStream;
/*  21:    */import javax.sound.sampled.UnsupportedAudioFileException;
/*  22:    */import javax.sound.sampled.spi.AudioFileReader;
/*  23:    */
/*  32:    */public class VorbisAudioFileReader
/*  33:    */  extends AudioFileReader
/*  34:    */{
/*  35:    */  public AudioFileFormat getAudioFileFormat(File file)
/*  36:    */    throws IOException, UnsupportedAudioFileException
/*  37:    */  {
/*  38:    */    try
/*  39:    */    {
/*  40: 40 */      return getAudioFileFormat(new FileStream(new RandomAccessFile(file, "r")));
/*  41:    */    }
/*  42:    */    catch (OggFormatException e) {
/*  43: 43 */      throw new UnsupportedAudioFileException(e.getMessage());
/*  44:    */    }
/*  45:    */  }
/*  46:    */  
/*  47:    */  public AudioFileFormat getAudioFileFormat(InputStream stream) throws IOException, UnsupportedAudioFileException {
/*  48:    */    try {
/*  49: 49 */      return getAudioFileFormat(new BasicStream(stream));
/*  50:    */    }
/*  51:    */    catch (OggFormatException e) {
/*  52: 52 */      throw new UnsupportedAudioFileException(e.getMessage());
/*  53:    */    }
/*  54:    */  }
/*  55:    */  
/*  56:    */  public AudioFileFormat getAudioFileFormat(URL url) throws IOException, UnsupportedAudioFileException {
/*  57:    */    try {
/*  58: 58 */      return getAudioFileFormat(new UncachedUrlStream(url));
/*  59:    */    }
/*  60:    */    catch (OggFormatException e) {
/*  61: 61 */      throw new UnsupportedAudioFileException(e.getMessage());
/*  62:    */    }
/*  63:    */  }
/*  64:    */  
/*  65:    */  private AudioFileFormat getAudioFileFormat(PhysicalOggStream oggStream) throws IOException, UnsupportedAudioFileException {
/*  66:    */    try {
/*  67: 67 */      Collection streams = oggStream.getLogicalStreams();
/*  68: 68 */      if (streams.size() != 1) {
/*  69: 69 */        throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
/*  70:    */      }
/*  71:    */      
/*  72: 72 */      LogicalOggStream los = (LogicalOggStream)streams.iterator().next();
/*  73: 73 */      if (los.getFormat() != "audio/x-vorbis") {
/*  74: 74 */        throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
/*  75:    */      }
/*  76:    */      
/*  77: 77 */      VorbisStream vs = new VorbisStream(los);
/*  78:    */      
/*  79: 79 */      AudioFormat audioFormat = new AudioFormat(vs.getIdentificationHeader().getSampleRate(), 16, vs.getIdentificationHeader().getChannels(), true, true);
/*  80:    */      
/*  85: 85 */      return new AudioFileFormat(VorbisFormatType.getInstance(), audioFormat, -1);
/*  86:    */    }
/*  87:    */    catch (OggFormatException e) {
/*  88: 88 */      throw new UnsupportedAudioFileException(e.getMessage());
/*  89:    */    }
/*  90:    */    catch (VorbisFormatException e) {
/*  91: 91 */      throw new UnsupportedAudioFileException(e.getMessage());
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  95:    */  public AudioInputStream getAudioInputStream(File file) throws IOException, UnsupportedAudioFileException
/*  96:    */  {
/*  97:    */    try
/*  98:    */    {
/*  99: 99 */      return getAudioInputStream(new FileStream(new RandomAccessFile(file, "r")));
/* 100:    */    }
/* 101:    */    catch (OggFormatException e) {
/* 102:102 */      throw new UnsupportedAudioFileException(e.getMessage());
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 106:    */  public AudioInputStream getAudioInputStream(InputStream stream) throws IOException, UnsupportedAudioFileException {
/* 107:    */    try {
/* 108:108 */      return getAudioInputStream(new BasicStream(stream));
/* 109:    */    }
/* 110:    */    catch (OggFormatException e) {
/* 111:111 */      throw new UnsupportedAudioFileException(e.getMessage());
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 115:    */  public AudioInputStream getAudioInputStream(URL url) throws IOException, UnsupportedAudioFileException {
/* 116:    */    try {
/* 117:117 */      return getAudioInputStream(new UncachedUrlStream(url));
/* 118:    */    }
/* 119:    */    catch (OggFormatException e) {
/* 120:120 */      throw new UnsupportedAudioFileException(e.getMessage());
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 124:    */  private AudioInputStream getAudioInputStream(PhysicalOggStream oggStream) throws IOException, UnsupportedAudioFileException {
/* 125:    */    try {
/* 126:126 */      Collection streams = oggStream.getLogicalStreams();
/* 127:127 */      if (streams.size() != 1) {
/* 128:128 */        throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
/* 129:    */      }
/* 130:    */      
/* 131:131 */      LogicalOggStream los = (LogicalOggStream)streams.iterator().next();
/* 132:132 */      if (los.getFormat() != "audio/x-vorbis") {
/* 133:133 */        throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
/* 134:    */      }
/* 135:    */      
/* 136:136 */      VorbisStream vs = new VorbisStream(los);
/* 137:    */      
/* 138:138 */      AudioFormat audioFormat = new AudioFormat(vs.getIdentificationHeader().getSampleRate(), 16, vs.getIdentificationHeader().getChannels(), true, true);
/* 139:    */      
/* 144:144 */      return new AudioInputStream(new VorbisInputStream(vs), audioFormat, -1L);
/* 145:    */    }
/* 146:    */    catch (OggFormatException e) {
/* 147:147 */      throw new UnsupportedAudioFileException(e.getMessage());
/* 148:    */    }
/* 149:    */    catch (VorbisFormatException e) {
/* 150:150 */      throw new UnsupportedAudioFileException(e.getMessage());
/* 151:    */    }
/* 152:    */  }
/* 153:    */  
/* 154:    */  public static class VorbisFormatType
/* 155:    */    extends AudioFileFormat.Type
/* 156:    */  {
/* 157:157 */    private static final VorbisFormatType instance = new VorbisFormatType();
/* 158:    */    
/* 159:    */    private VorbisFormatType() {
/* 160:160 */      super("ogg");
/* 161:    */    }
/* 162:    */    
/* 163:    */    public static AudioFileFormat.Type getInstance() {
/* 164:164 */      return instance;
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 168:    */  public static class VorbisInputStream extends InputStream
/* 169:    */  {
/* 170:    */    private VorbisStream source;
/* 171:171 */    private byte[] buffer = new byte[8192];
/* 172:    */    
/* 173:    */    public VorbisInputStream(VorbisStream source) {
/* 174:174 */      this.source = source;
/* 175:    */    }
/* 176:    */    
/* 177:    */    public int read() throws IOException {
/* 178:178 */      return 0;
/* 179:    */    }
/* 180:    */    
/* 181:    */    public int read(byte[] buffer) throws IOException {
/* 182:182 */      return read(buffer, 0, buffer.length);
/* 183:    */    }
/* 184:    */    
/* 185:    */    public int read(byte[] buffer, int offset, int length) throws IOException {
/* 186:    */      try {
/* 187:187 */        return this.source.readPcm(buffer, offset, length);
/* 188:    */      }
/* 189:    */      catch (EndOfOggStreamException e) {}
/* 190:190 */      return -1;
/* 191:    */    }
/* 192:    */  }
/* 193:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.VorbisAudioFileReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */