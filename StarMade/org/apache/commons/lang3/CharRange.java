/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */
/*  58:    */final class CharRange
/*  59:    */  implements Iterable<Character>, Serializable
/*  60:    */{
/*  61:    */  private static final long serialVersionUID = 8270183163158333422L;
/*  62:    */  private final char start;
/*  63:    */  private final char end;
/*  64:    */  private final boolean negated;
/*  65:    */  private transient String iToString;
/*  66:    */  
/*  67:    */  private CharRange(char start, char end, boolean negated)
/*  68:    */  {
/*  69: 69 */    if (start > end) {
/*  70: 70 */      char temp = start;
/*  71: 71 */      start = end;
/*  72: 72 */      end = temp;
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    this.start = start;
/*  76: 76 */    this.end = end;
/*  77: 77 */    this.negated = negated;
/*  78:    */  }
/*  79:    */  
/*  87:    */  public static CharRange is(char ch)
/*  88:    */  {
/*  89: 89 */    return new CharRange(ch, ch, false);
/*  90:    */  }
/*  91:    */  
/*  99:    */  public static CharRange isNot(char ch)
/* 100:    */  {
/* 101:101 */    return new CharRange(ch, ch, true);
/* 102:    */  }
/* 103:    */  
/* 112:    */  public static CharRange isIn(char start, char end)
/* 113:    */  {
/* 114:114 */    return new CharRange(start, end, false);
/* 115:    */  }
/* 116:    */  
/* 125:    */  public static CharRange isNotIn(char start, char end)
/* 126:    */  {
/* 127:127 */    return new CharRange(start, end, true);
/* 128:    */  }
/* 129:    */  
/* 136:    */  public char getStart()
/* 137:    */  {
/* 138:138 */    return this.start;
/* 139:    */  }
/* 140:    */  
/* 145:    */  public char getEnd()
/* 146:    */  {
/* 147:147 */    return this.end;
/* 148:    */  }
/* 149:    */  
/* 157:    */  public boolean isNegated()
/* 158:    */  {
/* 159:159 */    return this.negated;
/* 160:    */  }
/* 161:    */  
/* 169:    */  public boolean contains(char ch)
/* 170:    */  {
/* 171:171 */    return ((ch >= this.start) && (ch <= this.end)) != this.negated;
/* 172:    */  }
/* 173:    */  
/* 181:    */  public boolean contains(CharRange range)
/* 182:    */  {
/* 183:183 */    if (range == null) {
/* 184:184 */      throw new IllegalArgumentException("The Range must not be null");
/* 185:    */    }
/* 186:186 */    if (this.negated) {
/* 187:187 */      if (range.negated) {
/* 188:188 */        return (this.start >= range.start) && (this.end <= range.end);
/* 189:    */      }
/* 190:190 */      return (range.end < this.start) || (range.start > this.end);
/* 191:    */    }
/* 192:192 */    if (range.negated) {
/* 193:193 */      return (this.start == 0) && (this.end == 65535);
/* 194:    */    }
/* 195:195 */    return (this.start <= range.start) && (this.end >= range.end);
/* 196:    */  }
/* 197:    */  
/* 207:    */  public boolean equals(Object obj)
/* 208:    */  {
/* 209:209 */    if (obj == this) {
/* 210:210 */      return true;
/* 211:    */    }
/* 212:212 */    if (!(obj instanceof CharRange)) {
/* 213:213 */      return false;
/* 214:    */    }
/* 215:215 */    CharRange other = (CharRange)obj;
/* 216:216 */    return (this.start == other.start) && (this.end == other.end) && (this.negated == other.negated);
/* 217:    */  }
/* 218:    */  
/* 224:    */  public int hashCode()
/* 225:    */  {
/* 226:226 */    return 'S' + this.start + '\007' * this.end + (this.negated ? 1 : 0);
/* 227:    */  }
/* 228:    */  
/* 234:    */  public String toString()
/* 235:    */  {
/* 236:236 */    if (this.iToString == null) {
/* 237:237 */      StringBuilder buf = new StringBuilder(4);
/* 238:238 */      if (isNegated()) {
/* 239:239 */        buf.append('^');
/* 240:    */      }
/* 241:241 */      buf.append(this.start);
/* 242:242 */      if (this.start != this.end) {
/* 243:243 */        buf.append('-');
/* 244:244 */        buf.append(this.end);
/* 245:    */      }
/* 246:246 */      this.iToString = buf.toString();
/* 247:    */    }
/* 248:248 */    return this.iToString;
/* 249:    */  }
/* 250:    */  
/* 259:    */  public Iterator<Character> iterator()
/* 260:    */  {
/* 261:261 */    return new CharacterIterator(this, null);
/* 262:    */  }
/* 263:    */  
/* 266:    */  private static class CharacterIterator
/* 267:    */    implements Iterator<Character>
/* 268:    */  {
/* 269:    */    private char current;
/* 270:    */    
/* 272:    */    private final CharRange range;
/* 273:    */    
/* 275:    */    private boolean hasNext;
/* 276:    */    
/* 279:    */    private CharacterIterator(CharRange r)
/* 280:    */    {
/* 281:281 */      this.range = r;
/* 282:282 */      this.hasNext = true;
/* 283:    */      
/* 284:284 */      if (this.range.negated) {
/* 285:285 */        if (this.range.start == 0) {
/* 286:286 */          if (this.range.end == 65535)
/* 287:    */          {
/* 288:288 */            this.hasNext = false;
/* 289:    */          } else {
/* 290:290 */            this.current = ((char)(this.range.end + '\001'));
/* 291:    */          }
/* 292:    */        } else {
/* 293:293 */          this.current = '\000';
/* 294:    */        }
/* 295:    */      } else {
/* 296:296 */        this.current = this.range.start;
/* 297:    */      }
/* 298:    */    }
/* 299:    */    
/* 302:    */    private void prepareNext()
/* 303:    */    {
/* 304:304 */      if (this.range.negated) {
/* 305:305 */        if (this.current == 65535) {
/* 306:306 */          this.hasNext = false;
/* 307:307 */        } else if (this.current + '\001' == this.range.start) {
/* 308:308 */          if (this.range.end == 65535) {
/* 309:309 */            this.hasNext = false;
/* 310:    */          } else {
/* 311:311 */            this.current = ((char)(this.range.end + '\001'));
/* 312:    */          }
/* 313:    */        } else {
/* 314:314 */          this.current = ((char)(this.current + '\001'));
/* 315:    */        }
/* 316:316 */      } else if (this.current < this.range.end) {
/* 317:317 */        this.current = ((char)(this.current + '\001'));
/* 318:    */      } else {
/* 319:319 */        this.hasNext = false;
/* 320:    */      }
/* 321:    */    }
/* 322:    */    
/* 327:    */    public boolean hasNext()
/* 328:    */    {
/* 329:329 */      return this.hasNext;
/* 330:    */    }
/* 331:    */    
/* 336:    */    public Character next()
/* 337:    */    {
/* 338:338 */      if (!this.hasNext) {
/* 339:339 */        throw new NoSuchElementException();
/* 340:    */      }
/* 341:341 */      char cur = this.current;
/* 342:342 */      prepareNext();
/* 343:343 */      return Character.valueOf(cur);
/* 344:    */    }
/* 345:    */    
/* 351:    */    public void remove()
/* 352:    */    {
/* 353:353 */      throw new UnsupportedOperationException();
/* 354:    */    }
/* 355:    */  }
/* 356:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */