/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import com.sun.media.sound.WaveFileReader;
/*   4:    */import java.io.BufferedInputStream;
/*   5:    */import java.io.ByteArrayInputStream;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.net.URL;
/*   9:    */import java.nio.ByteBuffer;
/*  10:    */import java.nio.ByteOrder;
/*  11:    */import java.nio.ShortBuffer;
/*  12:    */import javax.sound.sampled.AudioFormat;
/*  13:    */import javax.sound.sampled.AudioInputStream;
/*  14:    */import javax.sound.sampled.AudioSystem;
/*  15:    */import org.lwjgl.LWJGLUtil;
/*  16:    */
/*  69:    */public class WaveData
/*  70:    */{
/*  71:    */  public final ByteBuffer data;
/*  72:    */  public final int format;
/*  73:    */  public final int samplerate;
/*  74:    */  
/*  75:    */  private WaveData(ByteBuffer data, int format, int samplerate)
/*  76:    */  {
/*  77: 77 */    this.data = data;
/*  78: 78 */    this.format = format;
/*  79: 79 */    this.samplerate = samplerate;
/*  80:    */  }
/*  81:    */  
/*  84:    */  public void dispose()
/*  85:    */  {
/*  86: 86 */    this.data.clear();
/*  87:    */  }
/*  88:    */  
/*  96:    */  public static WaveData create(URL path)
/*  97:    */  {
/*  98:    */    try
/*  99:    */    {
/* 100:100 */      WaveFileReader wfr = new WaveFileReader();
/* 101:101 */      return create(wfr.getAudioInputStream(new BufferedInputStream(path.openStream())));
/* 102:    */    } catch (Exception e) {
/* 103:103 */      LWJGLUtil.log("Unable to create from: " + path + ", " + e.getMessage()); }
/* 104:104 */    return null;
/* 105:    */  }
/* 106:    */  
/* 113:    */  public static WaveData create(String path)
/* 114:    */  {
/* 115:115 */    return create(Thread.currentThread().getContextClassLoader().getResource(path));
/* 116:    */  }
/* 117:    */  
/* 122:    */  public static WaveData create(InputStream is)
/* 123:    */  {
/* 124:    */    try
/* 125:    */    {
/* 126:126 */      return create(AudioSystem.getAudioInputStream(is));
/* 127:    */    }
/* 128:    */    catch (Exception e) {
/* 129:129 */      LWJGLUtil.log("Unable to create from inputstream, " + e.getMessage()); }
/* 130:130 */    return null;
/* 131:    */  }
/* 132:    */  
/* 138:    */  public static WaveData create(byte[] buffer)
/* 139:    */  {
/* 140:    */    try
/* 141:    */    {
/* 142:142 */      return create(AudioSystem.getAudioInputStream(new BufferedInputStream(new ByteArrayInputStream(buffer))));
/* 143:    */    }
/* 144:    */    catch (Exception e)
/* 145:    */    {
/* 146:146 */      LWJGLUtil.log("Unable to create from byte array, " + e.getMessage()); }
/* 147:147 */    return null;
/* 148:    */  }
/* 149:    */  
/* 157:    */  public static WaveData create(ByteBuffer buffer)
/* 158:    */  {
/* 159:    */    try
/* 160:    */    {
/* 161:161 */      byte[] bytes = null;
/* 162:    */      
/* 163:163 */      if (buffer.hasArray()) {
/* 164:164 */        bytes = buffer.array();
/* 165:    */      } else {
/* 166:166 */        bytes = new byte[buffer.capacity()];
/* 167:167 */        buffer.get(bytes);
/* 168:    */      }
/* 169:169 */      return create(bytes);
/* 170:    */    } catch (Exception e) {
/* 171:171 */      LWJGLUtil.log("Unable to create from ByteBuffer, " + e.getMessage()); }
/* 172:172 */    return null;
/* 173:    */  }
/* 174:    */  
/* 182:    */  public static WaveData create(AudioInputStream ais)
/* 183:    */  {
/* 184:184 */    AudioFormat audioformat = ais.getFormat();
/* 185:    */    
/* 187:187 */    int channels = 0;
/* 188:188 */    if (audioformat.getChannels() == 1) {
/* 189:189 */      if (audioformat.getSampleSizeInBits() == 8) {
/* 190:190 */        channels = 4352;
/* 191:191 */      } else if (audioformat.getSampleSizeInBits() == 16) {
/* 192:192 */        channels = 4353;
/* 193:    */      }
/* 194:194 */      else if (!$assertionsDisabled) throw new AssertionError("Illegal sample size");
/* 195:    */    }
/* 196:196 */    else if (audioformat.getChannels() == 2) {
/* 197:197 */      if (audioformat.getSampleSizeInBits() == 8) {
/* 198:198 */        channels = 4354;
/* 199:199 */      } else if (audioformat.getSampleSizeInBits() == 16) {
/* 200:200 */        channels = 4355;
/* 201:    */      }
/* 202:202 */      else if (!$assertionsDisabled) { throw new AssertionError("Illegal sample size");
/* 203:    */      }
/* 204:    */    }
/* 205:205 */    else if (!$assertionsDisabled) { throw new AssertionError("Only mono or stereo is supported");
/* 206:    */    }
/* 207:    */    
/* 209:209 */    ByteBuffer buffer = null;
/* 210:    */    try {
/* 211:211 */      int available = ais.available();
/* 212:212 */      if (available <= 0) {
/* 213:213 */        available = ais.getFormat().getChannels() * (int)ais.getFrameLength() * ais.getFormat().getSampleSizeInBits() / 8;
/* 214:    */      }
/* 215:215 */      byte[] buf = new byte[ais.available()];
/* 216:216 */      int read = 0;int total = 0;
/* 217:    */      
/* 218:218 */      while (((read = ais.read(buf, total, buf.length - total)) != -1) && (total < buf.length)) {
/* 219:219 */        total += read;
/* 220:    */      }
/* 221:221 */      buffer = convertAudioBytes(buf, audioformat.getSampleSizeInBits() == 16);
/* 222:    */    } catch (IOException ioe) {
/* 223:223 */      return null;
/* 224:    */    }
/* 225:    */    
/* 228:228 */    WaveData wavedata = new WaveData(buffer, channels, (int)audioformat.getSampleRate());
/* 229:    */    
/* 231:    */    try
/* 232:    */    {
/* 233:233 */      ais.close();
/* 234:    */    }
/* 235:    */    catch (IOException ioe) {}
/* 236:    */    
/* 237:237 */    return wavedata;
/* 238:    */  }
/* 239:    */  
/* 240:    */  private static ByteBuffer convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data) {
/* 241:241 */    ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
/* 242:242 */    dest.order(ByteOrder.nativeOrder());
/* 243:243 */    ByteBuffer src = ByteBuffer.wrap(audio_bytes);
/* 244:244 */    src.order(ByteOrder.LITTLE_ENDIAN);
/* 245:245 */    if (two_bytes_data) {
/* 246:246 */      ShortBuffer dest_short = dest.asShortBuffer();
/* 247:247 */      ShortBuffer src_short = src.asShortBuffer();
/* 248:248 */      while (src_short.hasRemaining())
/* 249:249 */        dest_short.put(src_short.get());
/* 250:    */    } else {
/* 251:251 */      while (src.hasRemaining())
/* 252:252 */        dest.put(src.get());
/* 253:    */    }
/* 254:254 */    dest.rewind();
/* 255:255 */    return dest;
/* 256:    */  }
/* 257:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.WaveData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */