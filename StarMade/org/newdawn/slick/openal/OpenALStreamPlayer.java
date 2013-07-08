/*   1:    */package org.newdawn.slick.openal;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.net.URL;
/*   5:    */import java.nio.ByteBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import org.lwjgl.BufferUtils;
/*   8:    */import org.lwjgl.openal.AL10;
/*   9:    */import org.lwjgl.openal.OpenALException;
/*  10:    */import org.newdawn.slick.util.Log;
/*  11:    */import org.newdawn.slick.util.ResourceLoader;
/*  12:    */
/*  26:    */public class OpenALStreamPlayer
/*  27:    */{
/*  28:    */  public static final int BUFFER_COUNT = 3;
/*  29:    */  private static final int sectionSize = 81920;
/*  30: 30 */  private byte[] buffer = new byte[81920];
/*  31:    */  
/*  32:    */  private IntBuffer bufferNames;
/*  33:    */  
/*  34: 34 */  private ByteBuffer bufferData = BufferUtils.createByteBuffer(81920);
/*  35:    */  
/*  36: 36 */  private IntBuffer unqueued = BufferUtils.createIntBuffer(1);
/*  37:    */  
/*  38:    */  private int source;
/*  39:    */  
/*  40:    */  private int remainingBufferCount;
/*  41:    */  
/*  42:    */  private boolean loop;
/*  43:    */  
/*  44: 44 */  private boolean done = true;
/*  45:    */  
/*  47:    */  private AudioInputStream audio;
/*  48:    */  
/*  50:    */  private String ref;
/*  51:    */  
/*  53:    */  private URL url;
/*  54:    */  
/*  56:    */  private float pitch;
/*  57:    */  
/*  58:    */  private float positionOffset;
/*  59:    */  
/*  61:    */  public OpenALStreamPlayer(int source, String ref)
/*  62:    */  {
/*  63: 63 */    this.source = source;
/*  64: 64 */    this.ref = ref;
/*  65:    */    
/*  66: 66 */    this.bufferNames = BufferUtils.createIntBuffer(3);
/*  67: 67 */    AL10.alGenBuffers(this.bufferNames);
/*  68:    */  }
/*  69:    */  
/*  75:    */  public OpenALStreamPlayer(int source, URL url)
/*  76:    */  {
/*  77: 77 */    this.source = source;
/*  78: 78 */    this.url = url;
/*  79:    */    
/*  80: 80 */    this.bufferNames = BufferUtils.createIntBuffer(3);
/*  81: 81 */    AL10.alGenBuffers(this.bufferNames);
/*  82:    */  }
/*  83:    */  
/*  87:    */  private void initStreams()
/*  88:    */    throws IOException
/*  89:    */  {
/*  90: 90 */    if (this.audio != null) {
/*  91: 91 */      this.audio.close();
/*  92:    */    }
/*  93:    */    
/*  94:    */    OggInputStream audio;
/*  95:    */    OggInputStream audio;
/*  96: 96 */    if (this.url != null) {
/*  97: 97 */      audio = new OggInputStream(this.url.openStream());
/*  98:    */    } else {
/*  99: 99 */      audio = new OggInputStream(ResourceLoader.getResourceAsStream(this.ref));
/* 100:    */    }
/* 101:    */    
/* 102:102 */    this.audio = audio;
/* 103:103 */    this.positionOffset = 0.0F;
/* 104:    */  }
/* 105:    */  
/* 110:    */  public String getSource()
/* 111:    */  {
/* 112:112 */    return this.url == null ? this.ref : this.url.toString();
/* 113:    */  }
/* 114:    */  
/* 117:    */  private void removeBuffers()
/* 118:    */  {
/* 119:119 */    IntBuffer buffer = BufferUtils.createIntBuffer(1);
/* 120:120 */    int queued = AL10.alGetSourcei(this.source, 4117);
/* 121:    */    
/* 122:122 */    while (queued > 0)
/* 123:    */    {
/* 124:124 */      AL10.alSourceUnqueueBuffers(this.source, buffer);
/* 125:125 */      queued--;
/* 126:    */    }
/* 127:    */  }
/* 128:    */  
/* 133:    */  public void play(boolean loop)
/* 134:    */    throws IOException
/* 135:    */  {
/* 136:136 */    this.loop = loop;
/* 137:137 */    initStreams();
/* 138:    */    
/* 139:139 */    this.done = false;
/* 140:    */    
/* 141:141 */    AL10.alSourceStop(this.source);
/* 142:142 */    removeBuffers();
/* 143:    */    
/* 144:144 */    startPlayback();
/* 145:    */  }
/* 146:    */  
/* 151:    */  public void setup(float pitch)
/* 152:    */  {
/* 153:153 */    this.pitch = pitch;
/* 154:    */  }
/* 155:    */  
/* 161:    */  public boolean done()
/* 162:    */  {
/* 163:163 */    return this.done;
/* 164:    */  }
/* 165:    */  
/* 171:    */  public void update()
/* 172:    */  {
/* 173:173 */    if (this.done) {
/* 174:174 */      return;
/* 175:    */    }
/* 176:    */    
/* 177:177 */    float sampleRate = this.audio.getRate();
/* 178:    */    float sampleSize;
/* 179:179 */    float sampleSize; if (this.audio.getChannels() > 1) {
/* 180:180 */      sampleSize = 4.0F;
/* 181:    */    } else {
/* 182:182 */      sampleSize = 2.0F;
/* 183:    */    }
/* 184:    */    
/* 185:185 */    int processed = AL10.alGetSourcei(this.source, 4118);
/* 186:186 */    while (processed > 0) {
/* 187:187 */      this.unqueued.clear();
/* 188:188 */      AL10.alSourceUnqueueBuffers(this.source, this.unqueued);
/* 189:    */      
/* 190:190 */      int bufferIndex = this.unqueued.get(0);
/* 191:    */      
/* 192:192 */      float bufferLength = AL10.alGetBufferi(bufferIndex, 8196) / sampleSize / sampleRate;
/* 193:193 */      this.positionOffset += bufferLength;
/* 194:    */      
/* 195:195 */      if (stream(bufferIndex)) {
/* 196:196 */        AL10.alSourceQueueBuffers(this.source, this.unqueued);
/* 197:    */      } else {
/* 198:198 */        this.remainingBufferCount -= 1;
/* 199:199 */        if (this.remainingBufferCount == 0) {
/* 200:200 */          this.done = true;
/* 201:    */        }
/* 202:    */      }
/* 203:203 */      processed--;
/* 204:    */    }
/* 205:    */    
/* 206:206 */    int state = AL10.alGetSourcei(this.source, 4112);
/* 207:    */    
/* 208:208 */    if (state != 4114) {
/* 209:209 */      AL10.alSourcePlay(this.source);
/* 210:    */    }
/* 211:    */  }
/* 212:    */  
/* 217:    */  public boolean stream(int bufferId)
/* 218:    */  {
/* 219:    */    try
/* 220:    */    {
/* 221:221 */      int count = this.audio.read(this.buffer);
/* 222:    */      
/* 223:223 */      if (count != -1) {
/* 224:224 */        this.bufferData.clear();
/* 225:225 */        this.bufferData.put(this.buffer, 0, count);
/* 226:226 */        this.bufferData.flip();
/* 227:    */        
/* 228:228 */        int format = this.audio.getChannels() > 1 ? 4355 : 4353;
/* 229:    */        try {
/* 230:230 */          AL10.alBufferData(bufferId, format, this.bufferData, this.audio.getRate());
/* 231:    */        } catch (OpenALException e) {
/* 232:232 */          Log.error("Failed to loop buffer: " + bufferId + " " + format + " " + count + " " + this.audio.getRate(), e);
/* 233:233 */          return false;
/* 234:    */        }
/* 235:    */      }
/* 236:236 */      else if (this.loop) {
/* 237:237 */        initStreams();
/* 238:238 */        stream(bufferId);
/* 239:    */      } else {
/* 240:240 */        this.done = true;
/* 241:241 */        return false;
/* 242:    */      }
/* 243:    */      
/* 245:245 */      return true;
/* 246:    */    } catch (IOException e) {
/* 247:247 */      Log.error(e); }
/* 248:248 */    return false;
/* 249:    */  }
/* 250:    */  
/* 256:    */  public boolean setPosition(float position)
/* 257:    */  {
/* 258:    */    try
/* 259:    */    {
/* 260:260 */      if (getPosition() > position) {
/* 261:261 */        initStreams();
/* 262:    */      }
/* 263:    */      
/* 264:264 */      float sampleRate = this.audio.getRate();
/* 265:    */      float sampleSize;
/* 266:266 */      float sampleSize; if (this.audio.getChannels() > 1) {
/* 267:267 */        sampleSize = 4.0F;
/* 268:    */      } else {
/* 269:269 */        sampleSize = 2.0F;
/* 270:    */      }
/* 271:    */      
/* 272:272 */      while (this.positionOffset < position) {
/* 273:273 */        int count = this.audio.read(this.buffer);
/* 274:274 */        if (count != -1) {
/* 275:275 */          float bufferLength = count / sampleSize / sampleRate;
/* 276:276 */          this.positionOffset += bufferLength;
/* 277:    */        } else {
/* 278:278 */          if (this.loop) {
/* 279:279 */            initStreams();
/* 280:    */          } else {
/* 281:281 */            this.done = true;
/* 282:    */          }
/* 283:283 */          return false;
/* 284:    */        }
/* 285:    */      }
/* 286:    */      
/* 287:287 */      startPlayback();
/* 288:    */      
/* 289:289 */      return true;
/* 290:    */    } catch (IOException e) {
/* 291:291 */      Log.error(e); }
/* 292:292 */    return false;
/* 293:    */  }
/* 294:    */  
/* 298:    */  private void startPlayback()
/* 299:    */  {
/* 300:300 */    AL10.alSourcei(this.source, 4103, 0);
/* 301:301 */    AL10.alSourcef(this.source, 4099, this.pitch);
/* 302:    */    
/* 303:303 */    this.remainingBufferCount = 3;
/* 304:    */    
/* 305:305 */    for (int i = 0; i < 3; i++) {
/* 306:306 */      stream(this.bufferNames.get(i));
/* 307:    */    }
/* 308:    */    
/* 309:309 */    AL10.alSourceQueueBuffers(this.source, this.bufferNames);
/* 310:310 */    AL10.alSourcePlay(this.source);
/* 311:    */  }
/* 312:    */  
/* 317:    */  public float getPosition()
/* 318:    */  {
/* 319:319 */    return this.positionOffset + AL10.alGetSourcef(this.source, 4132);
/* 320:    */  }
/* 321:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.OpenALStreamPlayer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */