/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.ogg.LogicalOggStream;
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import de.jarnbjo.util.io.ByteArrayBitInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class VorbisStream
/*     */ {
/*     */   private LogicalOggStream oggStream;
/*     */   private IdentificationHeader identificationHeader;
/*     */   private CommentHeader commentHeader;
/*     */   private SetupHeader setupHeader;
/*     */   private AudioPacket lastAudioPacket;
/*     */   private AudioPacket nextAudioPacket;
/*  49 */   private LinkedList audioPackets = new LinkedList();
/*     */   private byte[] currentPcm;
/*     */   private int currentPcmIndex;
/*     */   private int currentPcmLimit;
/*     */   private static final int IDENTIFICATION_HEADER = 1;
/*     */   private static final int COMMENT_HEADER = 3;
/*     */   private static final int SETUP_HEADER = 5;
/*  58 */   private int bitIndex = 0;
/*  59 */   private byte lastByte = 0;
/*  60 */   private boolean initialized = false;
/*     */ 
/*  62 */   private Object streamLock = new Object();
/*  63 */   private int pageCounter = 0;
/*     */ 
/*  65 */   private int currentBitRate = 0;
/*     */   private long currentGranulePosition;
/*     */   public static final int BIG_ENDIAN = 0;
/*     */   public static final int LITTLE_ENDIAN = 1;
/*     */ 
/*     */   public VorbisStream()
/*     */   {
/*     */   }
/*     */ 
/*     */   public VorbisStream(LogicalOggStream oggStream)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/*  76 */     this.oggStream = oggStream;
/*     */ 
/*  78 */     for (int i = 0; i < 3; i++) {
/*  79 */       BitInputStream source = new ByteArrayBitInputStream(oggStream.getNextOggPacket());
/*  80 */       int headerType = source.getInt(8);
/*  81 */       switch (headerType) {
/*     */       case 1:
/*  83 */         this.identificationHeader = new IdentificationHeader(source);
/*  84 */         break;
/*     */       case 3:
/*  86 */         this.commentHeader = new CommentHeader(source);
/*  87 */         break;
/*     */       case 5:
/*  89 */         this.setupHeader = new SetupHeader(this, source);
/*     */       case 2:
/*     */       case 4:
/*     */       }
/*     */     }
/*  94 */     if (this.identificationHeader == null) {
/*  95 */       throw new VorbisFormatException("The file has no identification header.");
/*     */     }
/*     */ 
/*  98 */     if (this.commentHeader == null) {
/*  99 */       throw new VorbisFormatException("The file has no commentHeader.");
/*     */     }
/*     */ 
/* 102 */     if (this.setupHeader == null) {
/* 103 */       throw new VorbisFormatException("The file has no setup header.");
/*     */     }
/*     */ 
/* 107 */     this.currentPcm = new byte[this.identificationHeader.getChannels() * this.identificationHeader.getBlockSize1() * 2];
/*     */   }
/*     */ 
/*     */   public IdentificationHeader getIdentificationHeader()
/*     */   {
/* 112 */     return this.identificationHeader;
/*     */   }
/*     */ 
/*     */   public CommentHeader getCommentHeader() {
/* 116 */     return this.commentHeader;
/*     */   }
/*     */ 
/*     */   protected SetupHeader getSetupHeader() {
/* 120 */     return this.setupHeader;
/*     */   }
/*     */ 
/*     */   public boolean isOpen() {
/* 124 */     return this.oggStream.isOpen();
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 128 */     this.oggStream.close();
/*     */   }
/*     */ 
/*     */   public int readPcm(byte[] buffer, int offset, int length) throws IOException
/*     */   {
/* 133 */     synchronized (this.streamLock) {
/* 134 */       int channels = this.identificationHeader.getChannels();
/*     */ 
/* 136 */       if (this.lastAudioPacket == null) {
/* 137 */         this.lastAudioPacket = getNextAudioPacket();
/*     */       }
/* 139 */       if ((this.currentPcm == null) || (this.currentPcmIndex >= this.currentPcmLimit)) {
/* 140 */         AudioPacket ap = getNextAudioPacket();
/*     */         try {
/* 142 */           ap.getPcm(this.lastAudioPacket, this.currentPcm);
/* 143 */           this.currentPcmLimit = (ap.getNumberOfSamples() * this.identificationHeader.getChannels() * 2);
/*     */         }
/*     */         catch (ArrayIndexOutOfBoundsException e) {
/* 146 */           return 0;
/*     */         }
/* 148 */         this.currentPcmIndex = 0;
/* 149 */         this.lastAudioPacket = ap;
/*     */       }
/* 151 */       int written = 0;
/* 152 */       int i = 0;
/* 153 */       int arrIx = 0;
/* 154 */       for (i = this.currentPcmIndex; (i < this.currentPcmLimit) && (arrIx < length); i++) {
/* 155 */         buffer[(offset + arrIx++)] = this.currentPcm[i];
/* 156 */         written++;
/*     */       }
/* 158 */       this.currentPcmIndex = i;
/* 159 */       return written;
/*     */     }
/*     */   }
/*     */ 
/*     */   private AudioPacket getNextAudioPacket() throws VorbisFormatException, IOException
/*     */   {
/* 165 */     this.pageCounter += 1;
/* 166 */     byte[] data = this.oggStream.getNextOggPacket();
/* 167 */     AudioPacket res = null;
/* 168 */     while (res == null) {
/*     */       try {
/* 170 */         res = new AudioPacket(this, new ByteArrayBitInputStream(data));
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException e)
/*     */       {
/*     */       }
/*     */     }
/* 176 */     this.currentGranulePosition += res.getNumberOfSamples();
/* 177 */     this.currentBitRate = (data.length * 8 * this.identificationHeader.getSampleRate() / res.getNumberOfSamples());
/* 178 */     return res;
/*     */   }
/*     */ 
/*     */   public long getCurrentGranulePosition() {
/* 182 */     return this.currentGranulePosition;
/*     */   }
/*     */ 
/*     */   public int getCurrentBitRate() {
/* 186 */     return this.currentBitRate;
/*     */   }
/*     */ 
/*     */   public byte[] processPacket(byte[] packet) throws VorbisFormatException, IOException {
/* 190 */     if (packet.length == 0) {
/* 191 */       throw new VorbisFormatException("Cannot decode a vorbis packet with length = 0");
/*     */     }
/* 193 */     if ((packet[0] & 0x1) == 1)
/*     */     {
/* 195 */       BitInputStream source = new ByteArrayBitInputStream(packet);
/* 196 */       switch (source.getInt(8)) {
/*     */       case 1:
/* 198 */         this.identificationHeader = new IdentificationHeader(source);
/* 199 */         break;
/*     */       case 3:
/* 201 */         this.commentHeader = new CommentHeader(source);
/* 202 */         break;
/*     */       case 5:
/* 204 */         this.setupHeader = new SetupHeader(this, source);
/*     */       case 2:
/*     */       case 4:
/* 207 */       }return null;
/*     */     }
/*     */ 
/* 211 */     if ((this.identificationHeader == null) || (this.commentHeader == null) || (this.setupHeader == null))
/*     */     {
/* 215 */       throw new VorbisFormatException("Cannot decode audio packet before all three header packets have been decoded.");
/*     */     }
/*     */ 
/* 218 */     AudioPacket ap = new AudioPacket(this, new ByteArrayBitInputStream(packet));
/* 219 */     this.currentGranulePosition += ap.getNumberOfSamples();
/*     */ 
/* 221 */     if (this.lastAudioPacket == null) {
/* 222 */       this.lastAudioPacket = ap;
/* 223 */       return null;
/*     */     }
/*     */ 
/* 226 */     byte[] res = new byte[this.identificationHeader.getChannels() * ap.getNumberOfSamples() * 2];
/*     */     try
/*     */     {
/* 229 */       ap.getPcm(this.lastAudioPacket, res);
/*     */     }
/*     */     catch (IndexOutOfBoundsException e) {
/* 232 */       Arrays.fill(res, (byte)0);
/*     */     }
/*     */ 
/* 235 */     this.lastAudioPacket = ap;
/*     */ 
/* 237 */     return res;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.VorbisStream
 * JD-Core Version:    0.6.2
 */