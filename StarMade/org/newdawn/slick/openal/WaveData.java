/*   1:    */package org.newdawn.slick.openal;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.ByteArrayInputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.InputStream;
/*   7:    */import java.net.URL;
/*   8:    */import java.nio.ByteBuffer;
/*   9:    */import java.nio.ByteOrder;
/*  10:    */import java.nio.ShortBuffer;
/*  11:    */import javax.sound.sampled.AudioFormat;
/*  12:    */import javax.sound.sampled.AudioInputStream;
/*  13:    */import javax.sound.sampled.AudioSystem;
/*  14:    */import org.lwjgl.LWJGLUtil;
/*  15:    */
/*  67:    */public class WaveData
/*  68:    */{
/*  69:    */  public final ByteBuffer data;
/*  70:    */  public final int format;
/*  71:    */  public final int samplerate;
/*  72:    */  
/*  73:    */  private WaveData(ByteBuffer data, int format, int samplerate)
/*  74:    */  {
/*  75: 75 */    this.data = data;
/*  76: 76 */    this.format = format;
/*  77: 77 */    this.samplerate = samplerate;
/*  78:    */  }
/*  79:    */  
/*  82:    */  public void dispose()
/*  83:    */  {
/*  84: 84 */    this.data.clear();
/*  85:    */  }
/*  86:    */  
/*  91:    */  public static WaveData create(URL path)
/*  92:    */  {
/*  93:    */    try
/*  94:    */    {
/*  95: 95 */      return create(AudioSystem.getAudioInputStream(new BufferedInputStream(path.openStream())));
/*  96:    */    }
/*  97:    */    catch (Exception e)
/*  98:    */    {
/*  99: 99 */      LWJGLUtil.log("Unable to create from: " + path);
/* 100:100 */      e.printStackTrace(); }
/* 101:101 */    return null;
/* 102:    */  }
/* 103:    */  
/* 110:    */  public static WaveData create(String path)
/* 111:    */  {
/* 112:112 */    return create(WaveData.class.getClassLoader().getResource(path));
/* 113:    */  }
/* 114:    */  
/* 119:    */  public static WaveData create(InputStream is)
/* 120:    */  {
/* 121:    */    try
/* 122:    */    {
/* 123:123 */      return create(AudioSystem.getAudioInputStream(is));
/* 124:    */    }
/* 125:    */    catch (Exception e) {
/* 126:126 */      LWJGLUtil.log("Unable to create from inputstream");
/* 127:127 */      e.printStackTrace(); }
/* 128:128 */    return null;
/* 129:    */  }
/* 130:    */  
/* 136:    */  public static WaveData create(byte[] buffer)
/* 137:    */  {
/* 138:    */    try
/* 139:    */    {
/* 140:140 */      return create(AudioSystem.getAudioInputStream(new BufferedInputStream(new ByteArrayInputStream(buffer))));
/* 141:    */    }
/* 142:    */    catch (Exception e)
/* 143:    */    {
/* 144:144 */      e.printStackTrace(); }
/* 145:145 */    return null;
/* 146:    */  }
/* 147:    */  
/* 155:    */  public static WaveData create(ByteBuffer buffer)
/* 156:    */  {
/* 157:    */    try
/* 158:    */    {
/* 159:159 */      byte[] bytes = null;
/* 160:    */      
/* 161:161 */      if (buffer.hasArray()) {
/* 162:162 */        bytes = buffer.array();
/* 163:    */      } else {
/* 164:164 */        bytes = new byte[buffer.capacity()];
/* 165:165 */        buffer.get(bytes);
/* 166:    */      }
/* 167:167 */      return create(bytes);
/* 168:    */    } catch (Exception e) {
/* 169:169 */      e.printStackTrace(); }
/* 170:170 */    return null;
/* 171:    */  }
/* 172:    */  
/* 180:    */  public static WaveData create(AudioInputStream ais)
/* 181:    */  {
/* 182:182 */    AudioFormat audioformat = ais.getFormat();
/* 183:    */    
/* 185:185 */    int channels = 0;
/* 186:186 */    if (audioformat.getChannels() == 1) {
/* 187:187 */      if (audioformat.getSampleSizeInBits() == 8) {
/* 188:188 */        channels = 4352;
/* 189:189 */      } else if (audioformat.getSampleSizeInBits() == 16) {
/* 190:190 */        channels = 4353;
/* 191:    */      } else {
/* 192:192 */        throw new RuntimeException("Illegal sample size");
/* 193:    */      }
/* 194:194 */    } else if (audioformat.getChannels() == 2) {
/* 195:195 */      if (audioformat.getSampleSizeInBits() == 8) {
/* 196:196 */        channels = 4354;
/* 197:197 */      } else if (audioformat.getSampleSizeInBits() == 16) {
/* 198:198 */        channels = 4355;
/* 199:    */      } else {
/* 200:200 */        throw new RuntimeException("Illegal sample size");
/* 201:    */      }
/* 202:    */    } else {
/* 203:203 */      throw new RuntimeException("Only mono or stereo is supported");
/* 204:    */    }
/* 205:    */    
/* 207:207 */    byte[] buf = new byte[audioformat.getChannels() * (int)ais.getFrameLength() * audioformat.getSampleSizeInBits() / 8];
/* 208:    */    
/* 212:212 */    int read = 0;int total = 0;
/* 213:    */    try
/* 214:    */    {
/* 215:215 */      while (((read = ais.read(buf, total, buf.length - total)) != -1) && (total < buf.length)) {
/* 216:216 */        total += read;
/* 217:    */      }
/* 218:    */    } catch (IOException ioe) {
/* 219:219 */      return null;
/* 220:    */    }
/* 221:    */    
/* 223:223 */    ByteBuffer buffer = convertAudioBytes(buf, audioformat.getSampleSizeInBits() == 16);
/* 224:    */    
/* 229:229 */    WaveData wavedata = new WaveData(buffer, channels, (int)audioformat.getSampleRate());
/* 230:    */    
/* 232:    */    try
/* 233:    */    {
/* 234:234 */      ais.close();
/* 235:    */    }
/* 236:    */    catch (IOException ioe) {}
/* 237:    */    
/* 238:238 */    return wavedata;
/* 239:    */  }
/* 240:    */  
/* 247:    */  private static ByteBuffer convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data)
/* 248:    */  {
/* 249:249 */    ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
/* 250:250 */    dest.order(ByteOrder.nativeOrder());
/* 251:251 */    ByteBuffer src = ByteBuffer.wrap(audio_bytes);
/* 252:252 */    src.order(ByteOrder.LITTLE_ENDIAN);
/* 253:253 */    if (two_bytes_data) {
/* 254:254 */      ShortBuffer dest_short = dest.asShortBuffer();
/* 255:255 */      ShortBuffer src_short = src.asShortBuffer();
/* 256:256 */      while (src_short.hasRemaining())
/* 257:257 */        dest_short.put(src_short.get());
/* 258:    */    } else {
/* 259:259 */      while (src.hasRemaining())
/* 260:260 */        dest.put(src.get());
/* 261:    */    }
/* 262:262 */    dest.rewind();
/* 263:263 */    return dest;
/* 264:    */  }
/* 265:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.WaveData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */