/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.ogg.BasicStream;
/*     */ import de.jarnbjo.ogg.EndOfOggStreamException;
/*     */ import de.jarnbjo.ogg.FileStream;
/*     */ import de.jarnbjo.ogg.LogicalOggStream;
/*     */ import de.jarnbjo.ogg.OggFormatException;
/*     */ import de.jarnbjo.ogg.PhysicalOggStream;
/*     */ import de.jarnbjo.ogg.UncachedUrlStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.sound.sampled.AudioFileFormat;
/*     */ import javax.sound.sampled.AudioFileFormat.Type;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import javax.sound.sampled.UnsupportedAudioFileException;
/*     */ import javax.sound.sampled.spi.AudioFileReader;
/*     */ 
/*     */ public class VorbisAudioFileReader extends AudioFileReader
/*     */ {
/*     */   public AudioFileFormat getAudioFileFormat(File file)
/*     */     throws IOException, UnsupportedAudioFileException
/*     */   {
/*     */     try
/*     */     {
/*  40 */       return getAudioFileFormat(new FileStream(new RandomAccessFile(file, "r")));
/*     */     }
/*     */     catch (OggFormatException e) {
/*  43 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public AudioFileFormat getAudioFileFormat(InputStream stream) throws IOException, UnsupportedAudioFileException {
/*     */     try {
/*  49 */       return getAudioFileFormat(new BasicStream(stream));
/*     */     }
/*     */     catch (OggFormatException e) {
/*  52 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public AudioFileFormat getAudioFileFormat(URL url) throws IOException, UnsupportedAudioFileException {
/*     */     try {
/*  58 */       return getAudioFileFormat(new UncachedUrlStream(url));
/*     */     }
/*     */     catch (OggFormatException e) {
/*  61 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private AudioFileFormat getAudioFileFormat(PhysicalOggStream oggStream) throws IOException, UnsupportedAudioFileException {
/*     */     try {
/*  67 */       Collection streams = oggStream.getLogicalStreams();
/*  68 */       if (streams.size() != 1) {
/*  69 */         throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
/*     */       }
/*     */ 
/*  72 */       LogicalOggStream los = (LogicalOggStream)streams.iterator().next();
/*  73 */       if (los.getFormat() != "audio/x-vorbis") {
/*  74 */         throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
/*     */       }
/*     */ 
/*  77 */       VorbisStream vs = new VorbisStream(los);
/*     */ 
/*  79 */       AudioFormat audioFormat = new AudioFormat(vs.getIdentificationHeader().getSampleRate(), 16, vs.getIdentificationHeader().getChannels(), true, true);
/*     */ 
/*  85 */       return new AudioFileFormat(VorbisFormatType.getInstance(), audioFormat, -1);
/*     */     }
/*     */     catch (OggFormatException e) {
/*  88 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */     catch (VorbisFormatException e) {
/*  91 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public AudioInputStream getAudioInputStream(File file) throws IOException, UnsupportedAudioFileException
/*     */   {
/*     */     try
/*     */     {
/*  99 */       return getAudioInputStream(new FileStream(new RandomAccessFile(file, "r")));
/*     */     }
/*     */     catch (OggFormatException e) {
/* 102 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public AudioInputStream getAudioInputStream(InputStream stream) throws IOException, UnsupportedAudioFileException {
/*     */     try {
/* 108 */       return getAudioInputStream(new BasicStream(stream));
/*     */     }
/*     */     catch (OggFormatException e) {
/* 111 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public AudioInputStream getAudioInputStream(URL url) throws IOException, UnsupportedAudioFileException {
/*     */     try {
/* 117 */       return getAudioInputStream(new UncachedUrlStream(url));
/*     */     }
/*     */     catch (OggFormatException e) {
/* 120 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private AudioInputStream getAudioInputStream(PhysicalOggStream oggStream) throws IOException, UnsupportedAudioFileException {
/*     */     try {
/* 126 */       Collection streams = oggStream.getLogicalStreams();
/* 127 */       if (streams.size() != 1) {
/* 128 */         throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
/*     */       }
/*     */ 
/* 131 */       LogicalOggStream los = (LogicalOggStream)streams.iterator().next();
/* 132 */       if (los.getFormat() != "audio/x-vorbis") {
/* 133 */         throw new UnsupportedAudioFileException("Only Ogg files with one logical Vorbis stream are supported.");
/*     */       }
/*     */ 
/* 136 */       VorbisStream vs = new VorbisStream(los);
/*     */ 
/* 138 */       AudioFormat audioFormat = new AudioFormat(vs.getIdentificationHeader().getSampleRate(), 16, vs.getIdentificationHeader().getChannels(), true, true);
/*     */ 
/* 144 */       return new AudioInputStream(new VorbisInputStream(vs), audioFormat, -1L);
/*     */     }
/*     */     catch (OggFormatException e) {
/* 147 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */     catch (VorbisFormatException e) {
/* 150 */       throw new UnsupportedAudioFileException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class VorbisInputStream extends InputStream
/*     */   {
/*     */     private VorbisStream source;
/* 171 */     private byte[] buffer = new byte[8192];
/*     */ 
/*     */     public VorbisInputStream(VorbisStream source) {
/* 174 */       this.source = source;
/*     */     }
/*     */ 
/*     */     public int read() throws IOException {
/* 178 */       return 0;
/*     */     }
/*     */ 
/*     */     public int read(byte[] buffer) throws IOException {
/* 182 */       return read(buffer, 0, buffer.length);
/*     */     }
/*     */ 
/*     */     public int read(byte[] buffer, int offset, int length) throws IOException {
/*     */       try {
/* 187 */         return this.source.readPcm(buffer, offset, length);
/*     */       } catch (EndOfOggStreamException e) {
/*     */       }
/* 190 */       return -1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class VorbisFormatType extends AudioFileFormat.Type
/*     */   {
/* 157 */     private static final VorbisFormatType instance = new VorbisFormatType();
/*     */ 
/*     */     private VorbisFormatType() {
/* 160 */       super("ogg");
/*     */     }
/*     */ 
/*     */     public static AudioFileFormat.Type getInstance() {
/* 164 */       return instance;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.VorbisAudioFileReader
 * JD-Core Version:    0.6.2
 */