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
/*  12:    */import javax.sound.sampled.AudioFormat.Encoding;
/*  13:    */import javax.sound.sampled.AudioInputStream;
/*  14:    */import javax.sound.sampled.AudioSystem;
/*  15:    */import org.lwjgl.LWJGLUtil;
/*  16:    */
/*  67:    */public class AiffData
/*  68:    */{
/*  69:    */  public final ByteBuffer data;
/*  70:    */  public final int format;
/*  71:    */  public final int samplerate;
/*  72:    */  
/*  73:    */  private AiffData(ByteBuffer data, int format, int samplerate)
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
/*  91:    */  public static AiffData create(URL path)
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
/* 110:    */  public static AiffData create(String path)
/* 111:    */  {
/* 112:112 */    return create(AiffData.class.getClassLoader().getResource(path));
/* 113:    */  }
/* 114:    */  
/* 119:    */  public static AiffData create(InputStream is)
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
/* 136:    */  public static AiffData create(byte[] buffer)
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
/* 155:    */  public static AiffData create(ByteBuffer buffer)
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
/* 180:    */  public static AiffData create(AudioInputStream ais)
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
/* 223:223 */    ByteBuffer buffer = convertAudioBytes(audioformat, buf, audioformat.getSampleSizeInBits() == 16);
/* 224:    */    
/* 226:226 */    AiffData Aiffdata = new AiffData(buffer, channels, (int)audioformat.getSampleRate());
/* 227:    */    
/* 229:    */    try
/* 230:    */    {
/* 231:231 */      ais.close();
/* 232:    */    }
/* 233:    */    catch (IOException ioe) {}
/* 234:    */    
/* 235:235 */    return Aiffdata;
/* 236:    */  }
/* 237:    */  
/* 245:    */  private static ByteBuffer convertAudioBytes(AudioFormat format, byte[] audio_bytes, boolean two_bytes_data)
/* 246:    */  {
/* 247:247 */    ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
/* 248:248 */    dest.order(ByteOrder.nativeOrder());
/* 249:249 */    ByteBuffer src = ByteBuffer.wrap(audio_bytes);
/* 250:250 */    src.order(ByteOrder.BIG_ENDIAN);
/* 251:251 */    if (two_bytes_data) {
/* 252:252 */      ShortBuffer dest_short = dest.asShortBuffer();
/* 253:253 */      ShortBuffer src_short = src.asShortBuffer();
/* 254:254 */      while (src_short.hasRemaining())
/* 255:255 */        dest_short.put(src_short.get());
/* 256:    */    } else {
/* 257:257 */      while (src.hasRemaining()) {
/* 258:258 */        byte b = src.get();
/* 259:259 */        if (format.getEncoding() == AudioFormat.Encoding.PCM_SIGNED) {
/* 260:260 */          b = (byte)(b + 127);
/* 261:    */        }
/* 262:262 */        dest.put(b);
/* 263:    */      }
/* 264:    */    }
/* 265:265 */    dest.rewind();
/* 266:266 */    return dest;
/* 267:    */  }
/* 268:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.AiffData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */