/*     */ package org.lwjgl.util;
/*     */ 
/*     */ import com.sun.media.sound.WaveFileReader;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.ShortBuffer;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ public class WaveData
/*     */ {
/*     */   public final ByteBuffer data;
/*     */   public final int format;
/*     */   public final int samplerate;
/*     */ 
/*     */   private WaveData(ByteBuffer data, int format, int samplerate)
/*     */   {
/*  77 */     this.data = data;
/*  78 */     this.format = format;
/*  79 */     this.samplerate = samplerate;
/*     */   }
/*     */ 
/*     */   public void dispose()
/*     */   {
/*  86 */     this.data.clear();
/*     */   }
/*     */ 
/*     */   public static WaveData create(URL path)
/*     */   {
/*     */     try
/*     */     {
/* 100 */       WaveFileReader wfr = new WaveFileReader();
/* 101 */       return create(wfr.getAudioInputStream(new BufferedInputStream(path.openStream())));
/*     */     } catch (Exception e) {
/* 103 */       LWJGLUtil.log("Unable to create from: " + path + ", " + e.getMessage());
/* 104 */     }return null;
/*     */   }
/*     */ 
/*     */   public static WaveData create(String path)
/*     */   {
/* 115 */     return create(Thread.currentThread().getContextClassLoader().getResource(path));
/*     */   }
/*     */ 
/*     */   public static WaveData create(InputStream is)
/*     */   {
/*     */     try
/*     */     {
/* 126 */       return create(AudioSystem.getAudioInputStream(is));
/*     */     }
/*     */     catch (Exception e) {
/* 129 */       LWJGLUtil.log("Unable to create from inputstream, " + e.getMessage());
/* 130 */     }return null;
/*     */   }
/*     */ 
/*     */   public static WaveData create(byte[] buffer)
/*     */   {
/*     */     try
/*     */     {
/* 142 */       return create(AudioSystem.getAudioInputStream(new BufferedInputStream(new ByteArrayInputStream(buffer))));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 146 */       LWJGLUtil.log("Unable to create from byte array, " + e.getMessage());
/* 147 */     }return null;
/*     */   }
/*     */ 
/*     */   public static WaveData create(ByteBuffer buffer)
/*     */   {
/*     */     try
/*     */     {
/* 161 */       byte[] bytes = null;
/*     */ 
/* 163 */       if (buffer.hasArray()) {
/* 164 */         bytes = buffer.array();
/*     */       } else {
/* 166 */         bytes = new byte[buffer.capacity()];
/* 167 */         buffer.get(bytes);
/*     */       }
/* 169 */       return create(bytes);
/*     */     } catch (Exception e) {
/* 171 */       LWJGLUtil.log("Unable to create from ByteBuffer, " + e.getMessage());
/* 172 */     }return null;
/*     */   }
/*     */ 
/*     */   public static WaveData create(AudioInputStream ais)
/*     */   {
/* 184 */     AudioFormat audioformat = ais.getFormat();
/*     */ 
/* 187 */     int channels = 0;
/* 188 */     if (audioformat.getChannels() == 1) {
/* 189 */       if (audioformat.getSampleSizeInBits() == 8)
/* 190 */         channels = 4352;
/* 191 */       else if (audioformat.getSampleSizeInBits() == 16) {
/* 192 */         channels = 4353;
/*     */       }
/* 194 */       else if (!$assertionsDisabled) throw new AssertionError("Illegal sample size");
/*     */     }
/* 196 */     else if (audioformat.getChannels() == 2) {
/* 197 */       if (audioformat.getSampleSizeInBits() == 8)
/* 198 */         channels = 4354;
/* 199 */       else if (audioformat.getSampleSizeInBits() == 16) {
/* 200 */         channels = 4355;
/*     */       }
/* 202 */       else if (!$assertionsDisabled) throw new AssertionError("Illegal sample size");
/*     */ 
/*     */     }
/* 205 */     else if (!$assertionsDisabled) throw new AssertionError("Only mono or stereo is supported");
/*     */ 
/* 209 */     ByteBuffer buffer = null;
/*     */     try {
/* 211 */       int available = ais.available();
/* 212 */       if (available <= 0) {
/* 213 */         available = ais.getFormat().getChannels() * (int)ais.getFrameLength() * ais.getFormat().getSampleSizeInBits() / 8;
/*     */       }
/* 215 */       byte[] buf = new byte[ais.available()];
/* 216 */       int read = 0; int total = 0;
/*     */ 
/* 218 */       while (((read = ais.read(buf, total, buf.length - total)) != -1) && (total < buf.length)) {
/* 219 */         total += read;
/*     */       }
/* 221 */       buffer = convertAudioBytes(buf, audioformat.getSampleSizeInBits() == 16);
/*     */     } catch (IOException ioe) {
/* 223 */       return null;
/*     */     }
/*     */ 
/* 228 */     WaveData wavedata = new WaveData(buffer, channels, (int)audioformat.getSampleRate());
/*     */     try
/*     */     {
/* 233 */       ais.close();
/*     */     }
/*     */     catch (IOException ioe) {
/*     */     }
/* 237 */     return wavedata;
/*     */   }
/*     */ 
/*     */   private static ByteBuffer convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data) {
/* 241 */     ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
/* 242 */     dest.order(ByteOrder.nativeOrder());
/* 243 */     ByteBuffer src = ByteBuffer.wrap(audio_bytes);
/* 244 */     src.order(ByteOrder.LITTLE_ENDIAN);
/* 245 */     if (two_bytes_data) {
/* 246 */       ShortBuffer dest_short = dest.asShortBuffer();
/* 247 */       ShortBuffer src_short = src.asShortBuffer();
/* 248 */       while (src_short.hasRemaining())
/* 249 */         dest_short.put(src_short.get());
/*     */     } else {
/* 251 */       while (src.hasRemaining())
/* 252 */         dest.put(src.get());
/*     */     }
/* 254 */     dest.rewind();
/* 255 */     return dest;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.WaveData
 * JD-Core Version:    0.6.2
 */