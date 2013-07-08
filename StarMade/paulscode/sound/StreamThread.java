/*   1:    */package paulscode.sound;
/*   2:    */
/*   3:    */import java.util.LinkedList;
/*   4:    */import java.util.List;
/*   5:    */import java.util.ListIterator;
/*   6:    */
/*  56:    */public class StreamThread
/*  57:    */  extends SimpleThread
/*  58:    */{
/*  59:    */  private SoundSystemLogger logger;
/*  60:    */  private List<Source> streamingSources;
/*  61: 61 */  private final Object listLock = new Object();
/*  62:    */  
/*  68:    */  public StreamThread()
/*  69:    */  {
/*  70: 70 */    this.logger = SoundSystemConfig.getLogger();
/*  71:    */    
/*  72: 72 */    this.streamingSources = new LinkedList();
/*  73:    */  }
/*  74:    */  
/*  81:    */  protected void cleanup()
/*  82:    */  {
/*  83: 83 */    kill();
/*  84: 84 */    super.cleanup();
/*  85:    */  }
/*  86:    */  
/*  96:    */  public void run()
/*  97:    */  {
/*  98: 98 */    snooze(3600000L);
/*  99:    */    
/* 100:100 */    while (!dying())
/* 101:    */    {
/* 102:102 */      while ((!dying()) && (!this.streamingSources.isEmpty()))
/* 103:    */      {
/* 105:105 */        synchronized (this.listLock)
/* 106:    */        {
/* 107:107 */          ListIterator<Source> iter = this.streamingSources.listIterator();
/* 108:108 */          while ((!dying()) && (iter.hasNext()))
/* 109:    */          {
/* 110:110 */            Source src = (Source)iter.next();
/* 111:111 */            if (src == null)
/* 112:    */            {
/* 113:113 */              iter.remove();
/* 114:    */            }
/* 115:115 */            else if (src.stopped())
/* 116:    */            {
/* 117:117 */              if (!src.rawDataStream) {
/* 118:118 */                iter.remove();
/* 119:    */              }
/* 120:120 */            } else if (!src.active())
/* 121:    */            {
/* 122:122 */              if ((src.toLoop) || (src.rawDataStream))
/* 123:123 */                src.toPlay = true;
/* 124:124 */              iter.remove();
/* 125:    */            }
/* 126:126 */            else if (!src.paused())
/* 127:    */            {
/* 128:128 */              src.checkFadeOut();
/* 129:129 */              if ((!src.stream()) && (!src.rawDataStream) && (
/* 130:    */              
/* 131:131 */                (src.channel == null) || (!src.channel.processBuffer())))
/* 132:    */              {
/* 134:134 */                if (src.nextCodec == null)
/* 135:    */                {
/* 136:136 */                  src.readBuffersFromNextSoundInSequence();
/* 137:    */                }
/* 138:    */                
/* 145:145 */                if (src.toLoop)
/* 146:    */                {
/* 148:148 */                  if (!src.playing())
/* 149:    */                  {
/* 151:151 */                    SoundSystemConfig.notifyEOS(src.sourcename, src.getSoundSequenceQueueSize());
/* 152:    */                    
/* 157:157 */                    if (src.checkFadeOut())
/* 158:    */                    {
/* 162:162 */                      src.preLoad = true;
/* 165:    */                    }
/* 166:    */                    else
/* 167:    */                    {
/* 170:170 */                      src.incrementSoundSequence();
/* 171:171 */                      src.preLoad = true;
/* 172:    */                    }
/* 173:    */                    
/* 174:    */                  }
/* 175:    */                  
/* 177:    */                }
/* 178:178 */                else if (!src.playing())
/* 179:    */                {
/* 181:181 */                  SoundSystemConfig.notifyEOS(src.sourcename, src.getSoundSequenceQueueSize());
/* 182:    */                  
/* 187:187 */                  if (!src.checkFadeOut())
/* 188:    */                  {
/* 192:192 */                    if (src.incrementSoundSequence())
/* 193:    */                    {
/* 194:194 */                      src.preLoad = true;
/* 195:    */                    } else {
/* 196:196 */                      iter.remove();
/* 197:    */                    }
/* 198:    */                  }
/* 199:    */                }
/* 200:    */              }
/* 201:    */            }
/* 202:    */          }
/* 203:    */        }
/* 204:    */        
/* 205:205 */        if ((!dying()) && (!this.streamingSources.isEmpty()))
/* 206:206 */          snooze(20L);
/* 207:    */      }
/* 208:208 */      if ((!dying()) && (this.streamingSources.isEmpty())) {
/* 209:209 */        snooze(3600000L);
/* 210:    */      }
/* 211:    */    }
/* 212:212 */    cleanup();
/* 213:    */  }
/* 214:    */  
/* 222:    */  public void watch(Source source)
/* 223:    */  {
/* 224:224 */    if (source == null) {
/* 225:225 */      return;
/* 226:    */    }
/* 227:    */    
/* 228:228 */    if (this.streamingSources.contains(source)) {
/* 229:229 */      return;
/* 230:    */    }
/* 231:    */    
/* 235:235 */    synchronized (this.listLock)
/* 236:    */    {
/* 240:240 */      ListIterator<Source> iter = this.streamingSources.listIterator();
/* 241:241 */      while (iter.hasNext())
/* 242:    */      {
/* 243:243 */        Source src = (Source)iter.next();
/* 244:244 */        if (src == null)
/* 245:    */        {
/* 246:246 */          iter.remove();
/* 247:    */        }
/* 248:248 */        else if (source.channel == src.channel)
/* 249:    */        {
/* 250:250 */          src.stop();
/* 251:251 */          iter.remove();
/* 252:    */        }
/* 253:    */      }
/* 254:    */      
/* 256:256 */      this.streamingSources.add(source);
/* 257:    */    }
/* 258:    */  }
/* 259:    */  
/* 264:    */  private void message(String message)
/* 265:    */  {
/* 266:266 */    this.logger.message(message, 0);
/* 267:    */  }
/* 268:    */  
/* 273:    */  private void importantMessage(String message)
/* 274:    */  {
/* 275:275 */    this.logger.importantMessage(message, 0);
/* 276:    */  }
/* 277:    */  
/* 284:    */  private boolean errorCheck(boolean error, String message)
/* 285:    */  {
/* 286:286 */    return this.logger.errorCheck(error, "StreamThread", message, 0);
/* 287:    */  }
/* 288:    */  
/* 293:    */  private void errorMessage(String message)
/* 294:    */  {
/* 295:295 */    this.logger.errorMessage("StreamThread", message, 0);
/* 296:    */  }
/* 297:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.StreamThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */