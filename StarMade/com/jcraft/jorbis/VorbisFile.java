/*    1:     */package com.jcraft.jorbis;
/*    2:     */
/*    3:     */import com.jcraft.jogg.Packet;
/*    4:     */import com.jcraft.jogg.Page;
/*    5:     */import com.jcraft.jogg.StreamState;
/*    6:     */import com.jcraft.jogg.SyncState;
/*    7:     */import java.io.IOException;
/*    8:     */import java.io.InputStream;
/*    9:     */import java.io.RandomAccessFile;
/*   10:     */
/*   37:     */public class VorbisFile
/*   38:     */{
/*   39:     */  static final int CHUNKSIZE = 8500;
/*   40:     */  static final int SEEK_SET = 0;
/*   41:     */  static final int SEEK_CUR = 1;
/*   42:     */  static final int SEEK_END = 2;
/*   43:     */  static final int OV_FALSE = -1;
/*   44:     */  static final int OV_EOF = -2;
/*   45:     */  static final int OV_HOLE = -3;
/*   46:     */  static final int OV_EREAD = -128;
/*   47:     */  static final int OV_EFAULT = -129;
/*   48:     */  static final int OV_EIMPL = -130;
/*   49:     */  static final int OV_EINVAL = -131;
/*   50:     */  static final int OV_ENOTVORBIS = -132;
/*   51:     */  static final int OV_EBADHEADER = -133;
/*   52:     */  static final int OV_EVERSION = -134;
/*   53:     */  static final int OV_ENOTAUDIO = -135;
/*   54:     */  static final int OV_EBADPACKET = -136;
/*   55:     */  static final int OV_EBADLINK = -137;
/*   56:     */  static final int OV_ENOSEEK = -138;
/*   57:     */  InputStream datasource;
/*   58:  58 */  boolean seekable = false;
/*   59:     */  
/*   60:     */  long offset;
/*   61:     */  long end;
/*   62:  62 */  SyncState oy = new SyncState();
/*   63:     */  
/*   64:     */  int links;
/*   65:     */  
/*   66:     */  long[] offsets;
/*   67:     */  
/*   68:     */  long[] dataoffsets;
/*   69:     */  int[] serialnos;
/*   70:     */  long[] pcmlengths;
/*   71:     */  Info[] vi;
/*   72:     */  Comment[] vc;
/*   73:     */  long pcm_offset;
/*   74:  74 */  boolean decode_ready = false;
/*   75:     */  
/*   76:     */  int current_serialno;
/*   77:     */  
/*   78:     */  int current_link;
/*   79:     */  
/*   80:     */  float bittrack;
/*   81:     */  float samptrack;
/*   82:  82 */  StreamState os = new StreamState();
/*   83:     */  
/*   84:  84 */  DspState vd = new DspState();
/*   85:     */  
/*   86:  86 */  Block vb = new Block(this.vd);
/*   87:     */  
/*   89:     */  public VorbisFile(String file)
/*   90:     */    throws JOrbisException
/*   91:     */  {
/*   92:  92 */    InputStream is = null;
/*   93:     */    try {
/*   94:  94 */      is = new SeekableInputStream(file);
/*   95:  95 */      int ret = open(is, null, 0);
/*   96:  96 */      if (ret == -1) {
/*   97:  97 */        throw new JOrbisException("VorbisFile: open return -1");
/*   98:     */      }
/*   99:     */      return;
/*  100:     */    } catch (Exception e) {
/*  101: 101 */      throw new JOrbisException("VorbisFile: " + e.toString());
/*  102:     */    }
/*  103:     */    finally {
/*  104: 104 */      if (is != null) {
/*  105:     */        try {
/*  106: 106 */          is.close();
/*  107:     */        }
/*  108:     */        catch (IOException e) {
/*  109: 109 */          e.printStackTrace();
/*  110:     */        }
/*  111:     */      }
/*  112:     */    }
/*  113:     */  }
/*  114:     */  
/*  115:     */  public VorbisFile(InputStream is, byte[] initial, int ibytes)
/*  116:     */    throws JOrbisException
/*  117:     */  {
/*  118: 118 */    int ret = open(is, initial, ibytes);
/*  119: 119 */    if (ret == -1) {}
/*  120:     */  }
/*  121:     */  
/*  122:     */  private int get_data()
/*  123:     */  {
/*  124: 124 */    int index = this.oy.buffer(8500);
/*  125: 125 */    byte[] buffer = this.oy.data;
/*  126: 126 */    int bytes = 0;
/*  127:     */    try {
/*  128: 128 */      bytes = this.datasource.read(buffer, index, 8500);
/*  129:     */    }
/*  130:     */    catch (Exception e) {
/*  131: 131 */      return -128;
/*  132:     */    }
/*  133: 133 */    this.oy.wrote(bytes);
/*  134: 134 */    if (bytes == -1) {
/*  135: 135 */      bytes = 0;
/*  136:     */    }
/*  137: 137 */    return bytes;
/*  138:     */  }
/*  139:     */  
/*  140:     */  private void seek_helper(long offst) {
/*  141: 141 */    fseek(this.datasource, offst, 0);
/*  142: 142 */    this.offset = offst;
/*  143: 143 */    this.oy.reset();
/*  144:     */  }
/*  145:     */  
/*  146:     */  private int get_next_page(Page page, long boundary) {
/*  147: 147 */    if (boundary > 0L) {
/*  148: 148 */      boundary += this.offset;
/*  149:     */    }
/*  150:     */    for (;;) {
/*  151: 151 */      if ((boundary > 0L) && (this.offset >= boundary))
/*  152: 152 */        return -1;
/*  153: 153 */      int more = this.oy.pageseek(page);
/*  154: 154 */      if (more < 0) {
/*  155: 155 */        this.offset -= more;
/*  157:     */      }
/*  158: 158 */      else if (more == 0) {
/*  159: 159 */        if (boundary == 0L)
/*  160: 160 */          return -1;
/*  161: 161 */        int ret = get_data();
/*  162: 162 */        if (ret == 0)
/*  163: 163 */          return -2;
/*  164: 164 */        if (ret < 0) {
/*  165: 165 */          return -128;
/*  166:     */        }
/*  167:     */      } else {
/*  168: 168 */        int ret = (int)this.offset;
/*  169: 169 */        this.offset += more;
/*  170: 170 */        return ret;
/*  171:     */      }
/*  172:     */    }
/*  173:     */  }
/*  174:     */  
/*  175:     */  private int get_prev_page(Page page) throws JOrbisException
/*  176:     */  {
/*  177: 177 */    long begin = this.offset;
/*  178:     */    
/*  179: 179 */    int offst = -1;
/*  180: 180 */    label97: while (offst == -1) {
/*  181: 181 */      begin -= 8500L;
/*  182: 182 */      if (begin < 0L)
/*  183: 183 */        begin = 0L;
/*  184: 184 */      seek_helper(begin);
/*  185: 185 */      for (;;) { if (this.offset >= begin + 8500L) break label97;
/*  186: 186 */        int ret = get_next_page(page, begin + 8500L - this.offset);
/*  187: 187 */        if (ret == -128) {
/*  188: 188 */          return -128;
/*  189:     */        }
/*  190: 190 */        if (ret < 0) {
/*  191: 191 */          if (offst != -1) break;
/*  192: 192 */          throw new JOrbisException();
/*  193:     */        }
/*  194:     */        
/*  196: 196 */        offst = ret;
/*  197:     */      }
/*  198:     */    }
/*  199:     */    
/*  200: 200 */    seek_helper(offst);
/*  201: 201 */    int ret = get_next_page(page, 8500L);
/*  202: 202 */    if (ret < 0) {
/*  203: 203 */      return -129;
/*  204:     */    }
/*  205: 205 */    return offst;
/*  206:     */  }
/*  207:     */  
/*  208:     */  int bisect_forward_serialno(long begin, long searched, long end, int currentno, int m)
/*  209:     */  {
/*  210: 210 */    long endsearched = end;
/*  211: 211 */    long next = end;
/*  212: 212 */    Page page = new Page();
/*  213:     */    
/*  215: 215 */    while (searched < endsearched) { long bisect;
/*  216:     */      long bisect;
/*  217: 217 */      if (endsearched - searched < 8500L) {
/*  218: 218 */        bisect = searched;
/*  219:     */      }
/*  220:     */      else {
/*  221: 221 */        bisect = (searched + endsearched) / 2L;
/*  222:     */      }
/*  223:     */      
/*  224: 224 */      seek_helper(bisect);
/*  225: 225 */      int ret = get_next_page(page, -1L);
/*  226: 226 */      if (ret == -128)
/*  227: 227 */        return -128;
/*  228: 228 */      if ((ret < 0) || (page.serialno() != currentno)) {
/*  229: 229 */        endsearched = bisect;
/*  230: 230 */        if (ret >= 0) {
/*  231: 231 */          next = ret;
/*  232:     */        }
/*  233:     */      } else {
/*  234: 234 */        searched = ret + page.header_len + page.body_len;
/*  235:     */      }
/*  236:     */    }
/*  237: 237 */    seek_helper(next);
/*  238: 238 */    int ret = get_next_page(page, -1L);
/*  239: 239 */    if (ret == -128) {
/*  240: 240 */      return -128;
/*  241:     */    }
/*  242: 242 */    if ((searched >= end) || (ret == -1)) {
/*  243: 243 */      this.links = (m + 1);
/*  244: 244 */      this.offsets = new long[m + 2];
/*  245: 245 */      this.offsets[(m + 1)] = searched;
/*  246:     */    }
/*  247:     */    else {
/*  248: 248 */      ret = bisect_forward_serialno(next, this.offset, end, page.serialno(), m + 1);
/*  249: 249 */      if (ret == -128)
/*  250: 250 */        return -128;
/*  251:     */    }
/*  252: 252 */    this.offsets[m] = begin;
/*  253: 253 */    return 0;
/*  254:     */  }
/*  255:     */  
/*  257:     */  int fetch_headers(Info vi, Comment vc, int[] serialno, Page og_ptr)
/*  258:     */  {
/*  259: 259 */    Page og = new Page();
/*  260: 260 */    Packet op = new Packet();
/*  261:     */    
/*  263: 263 */    if (og_ptr == null) {
/*  264: 264 */      int ret = get_next_page(og, 8500L);
/*  265: 265 */      if (ret == -128)
/*  266: 266 */        return -128;
/*  267: 267 */      if (ret < 0)
/*  268: 268 */        return -132;
/*  269: 269 */      og_ptr = og;
/*  270:     */    }
/*  271:     */    
/*  272: 272 */    if (serialno != null) {
/*  273: 273 */      serialno[0] = og_ptr.serialno();
/*  274:     */    }
/*  275: 275 */    this.os.init(og_ptr.serialno());
/*  276:     */    
/*  280: 280 */    vi.init();
/*  281: 281 */    vc.init();
/*  282:     */    
/*  283: 283 */    int i = 0;
/*  284: 284 */    while (i < 3) {
/*  285: 285 */      this.os.pagein(og_ptr);
/*  286: 286 */      while (i < 3) {
/*  287: 287 */        int result = this.os.packetout(op);
/*  288: 288 */        if (result == 0)
/*  289:     */          break;
/*  290: 290 */        if (result == -1) {
/*  291: 291 */          vi.clear();
/*  292: 292 */          vc.clear();
/*  293: 293 */          this.os.clear();
/*  294: 294 */          return -1;
/*  295:     */        }
/*  296: 296 */        if (vi.synthesis_headerin(vc, op) != 0) {
/*  297: 297 */          vi.clear();
/*  298: 298 */          vc.clear();
/*  299: 299 */          this.os.clear();
/*  300: 300 */          return -1;
/*  301:     */        }
/*  302: 302 */        i++;
/*  303:     */      }
/*  304: 304 */      if ((i < 3) && 
/*  305: 305 */        (get_next_page(og_ptr, 1L) < 0)) {
/*  306: 306 */        vi.clear();
/*  307: 307 */        vc.clear();
/*  308: 308 */        this.os.clear();
/*  309: 309 */        return -1;
/*  310:     */      }
/*  311:     */    }
/*  312: 312 */    return 0;
/*  313:     */  }
/*  314:     */  
/*  318:     */  void prefetch_all_headers(Info first_i, Comment first_c, int dataoffset)
/*  319:     */    throws JOrbisException
/*  320:     */  {
/*  321: 321 */    Page og = new Page();
/*  322:     */    
/*  324: 324 */    this.vi = new Info[this.links];
/*  325: 325 */    this.vc = new Comment[this.links];
/*  326: 326 */    this.dataoffsets = new long[this.links];
/*  327: 327 */    this.pcmlengths = new long[this.links];
/*  328: 328 */    this.serialnos = new int[this.links];
/*  329:     */    
/*  330: 330 */    for (int i = 0; i < this.links; i++) {
/*  331: 331 */      if ((first_i != null) && (first_c != null) && (i == 0))
/*  332:     */      {
/*  334: 334 */        this.vi[i] = first_i;
/*  335: 335 */        this.vc[i] = first_c;
/*  336: 336 */        this.dataoffsets[i] = dataoffset;
/*  337:     */      }
/*  338:     */      else
/*  339:     */      {
/*  340: 340 */        seek_helper(this.offsets[i]);
/*  341: 341 */        this.vi[i] = new Info();
/*  342: 342 */        this.vc[i] = new Comment();
/*  343: 343 */        if (fetch_headers(this.vi[i], this.vc[i], null, null) == -1) {
/*  344: 344 */          this.dataoffsets[i] = -1L;
/*  345:     */        }
/*  346:     */        else {
/*  347: 347 */          this.dataoffsets[i] = this.offset;
/*  348: 348 */          this.os.clear();
/*  349:     */        }
/*  350:     */      }
/*  351:     */      
/*  355: 355 */      long end = this.offsets[(i + 1)];
/*  356: 356 */      seek_helper(end);
/*  357:     */      do
/*  358:     */      {
/*  359: 359 */        int ret = get_prev_page(og);
/*  360: 360 */        if (ret == -1)
/*  361:     */        {
/*  362: 362 */          this.vi[i].clear();
/*  363: 363 */          this.vc[i].clear();
/*  364: 364 */          break;
/*  365:     */        }
/*  366: 366 */      } while (og.granulepos() == -1L);
/*  367: 367 */      this.serialnos[i] = og.serialno();
/*  368: 368 */      this.pcmlengths[i] = og.granulepos();
/*  369:     */    }
/*  370:     */  }
/*  371:     */  
/*  375:     */  private int make_decode_ready()
/*  376:     */  {
/*  377: 377 */    if (this.decode_ready)
/*  378: 378 */      System.exit(1);
/*  379: 379 */    this.vd.synthesis_init(this.vi[0]);
/*  380: 380 */    this.vb.init(this.vd);
/*  381: 381 */    this.decode_ready = true;
/*  382: 382 */    return 0;
/*  383:     */  }
/*  384:     */  
/*  385:     */  int open_seekable() throws JOrbisException {
/*  386: 386 */    Info initial_i = new Info();
/*  387: 387 */    Comment initial_c = new Comment();
/*  388:     */    
/*  392: 392 */    Page og = new Page();
/*  393:     */    
/*  394: 394 */    int[] foo = new int[1];
/*  395: 395 */    int ret = fetch_headers(initial_i, initial_c, foo, null);
/*  396: 396 */    int serialno = foo[0];
/*  397: 397 */    int dataoffset = (int)this.offset;
/*  398: 398 */    this.os.clear();
/*  399: 399 */    if (ret == -1)
/*  400: 400 */      return -1;
/*  401: 401 */    if (ret < 0) {
/*  402: 402 */      return ret;
/*  403:     */    }
/*  404: 404 */    this.seekable = true;
/*  405: 405 */    fseek(this.datasource, 0L, 2);
/*  406: 406 */    this.offset = ftell(this.datasource);
/*  407: 407 */    long end = this.offset;
/*  408:     */    
/*  410: 410 */    end = get_prev_page(og);
/*  411:     */    
/*  412: 412 */    if (og.serialno() != serialno)
/*  413:     */    {
/*  415: 415 */      if (bisect_forward_serialno(0L, 0L, end + 1L, serialno, 0) < 0) {
/*  416: 416 */        clear();
/*  417: 417 */        return -128;
/*  418:     */      }
/*  419:     */      
/*  421:     */    }
/*  422: 422 */    else if (bisect_forward_serialno(0L, end, end + 1L, serialno, 0) < 0) {
/*  423: 423 */      clear();
/*  424: 424 */      return -128;
/*  425:     */    }
/*  426:     */    
/*  427: 427 */    prefetch_all_headers(initial_i, initial_c, dataoffset);
/*  428: 428 */    return 0;
/*  429:     */  }
/*  430:     */  
/*  431:     */  int open_nonseekable()
/*  432:     */  {
/*  433: 433 */    this.links = 1;
/*  434: 434 */    this.vi = new Info[this.links];
/*  435: 435 */    this.vi[0] = new Info();
/*  436: 436 */    this.vc = new Comment[this.links];
/*  437: 437 */    this.vc[0] = new Comment();
/*  438:     */    
/*  440: 440 */    int[] foo = new int[1];
/*  441: 441 */    if (fetch_headers(this.vi[0], this.vc[0], foo, null) == -1)
/*  442: 442 */      return -1;
/*  443: 443 */    this.current_serialno = foo[0];
/*  444: 444 */    make_decode_ready();
/*  445: 445 */    return 0;
/*  446:     */  }
/*  447:     */  
/*  448:     */  void decode_clear()
/*  449:     */  {
/*  450: 450 */    this.os.clear();
/*  451: 451 */    this.vd.clear();
/*  452: 452 */    this.vb.clear();
/*  453: 453 */    this.decode_ready = false;
/*  454: 454 */    this.bittrack = 0.0F;
/*  455: 455 */    this.samptrack = 0.0F;
/*  456:     */  }
/*  457:     */  
/*  467:     */  int process_packet(int readp)
/*  468:     */  {
/*  469: 469 */    Page og = new Page();
/*  470:     */    
/*  474:     */    for (;;)
/*  475:     */    {
/*  476: 476 */      if (this.decode_ready) {
/*  477: 477 */        Packet op = new Packet();
/*  478: 478 */        int result = this.os.packetout(op);
/*  479:     */        
/*  483: 483 */        if (result > 0)
/*  484:     */        {
/*  485: 485 */          long granulepos = op.granulepos;
/*  486: 486 */          if (this.vb.synthesis(op) == 0)
/*  487:     */          {
/*  495: 495 */            int oldsamples = this.vd.synthesis_pcmout((float[][][])null, null);
/*  496: 496 */            this.vd.synthesis_blockin(this.vb);
/*  497: 497 */            this.samptrack += this.vd.synthesis_pcmout((float[][][])null, null) - oldsamples;
/*  498: 498 */            this.bittrack += op.bytes * 8;
/*  499:     */            
/*  502: 502 */            if ((granulepos != -1L) && (op.e_o_s == 0)) {
/*  503: 503 */              int link = this.seekable ? this.current_link : 0;
/*  504:     */              
/*  517: 517 */              int samples = this.vd.synthesis_pcmout((float[][][])null, null);
/*  518: 518 */              granulepos -= samples;
/*  519: 519 */              for (int i = 0; i < link; i++) {
/*  520: 520 */                granulepos += this.pcmlengths[i];
/*  521:     */              }
/*  522: 522 */              this.pcm_offset = granulepos;
/*  523:     */            }
/*  524: 524 */            return 1;
/*  525:     */          }
/*  526:     */        }
/*  527:     */      }
/*  528:     */      
/*  529: 529 */      if (readp == 0)
/*  530: 530 */        return 0;
/*  531: 531 */      if (get_next_page(og, -1L) < 0) {
/*  532: 532 */        return 0;
/*  533:     */      }
/*  534:     */      
/*  536: 536 */      this.bittrack += og.header_len * 8;
/*  537:     */      
/*  539: 539 */      if ((this.decode_ready) && 
/*  540: 540 */        (this.current_serialno != og.serialno())) {
/*  541: 541 */        decode_clear();
/*  542:     */      }
/*  543:     */      
/*  556: 556 */      if (!this.decode_ready) {
/*  557:     */        int i;
/*  558: 558 */        if (this.seekable) {
/*  559: 559 */          this.current_serialno = og.serialno();
/*  560:     */          
/*  564: 564 */          for (int i = 0; i < this.links; i++) {
/*  565: 565 */            if (this.serialnos[i] == this.current_serialno)
/*  566:     */              break;
/*  567:     */          }
/*  568: 568 */          if (i == this.links) {
/*  569: 569 */            return -1;
/*  570:     */          }
/*  571: 571 */          this.current_link = i;
/*  572:     */          
/*  573: 573 */          this.os.init(this.current_serialno);
/*  574: 574 */          this.os.reset();
/*  576:     */        }
/*  577:     */        else
/*  578:     */        {
/*  580: 580 */          int[] foo = new int[1];
/*  581: 581 */          int ret = fetch_headers(this.vi[0], this.vc[0], foo, og);
/*  582: 582 */          this.current_serialno = foo[0];
/*  583: 583 */          if (ret != 0)
/*  584: 584 */            return ret;
/*  585: 585 */          this.current_link += 1;
/*  586: 586 */          i = 0;
/*  587:     */        }
/*  588: 588 */        make_decode_ready();
/*  589:     */      }
/*  590: 590 */      this.os.pagein(og);
/*  591:     */    }
/*  592:     */  }
/*  593:     */  
/*  595:     */  int clear()
/*  596:     */  {
/*  597: 597 */    this.vb.clear();
/*  598: 598 */    this.vd.clear();
/*  599: 599 */    this.os.clear();
/*  600:     */    
/*  601: 601 */    if ((this.vi != null) && (this.links != 0)) {
/*  602: 602 */      for (int i = 0; i < this.links; i++) {
/*  603: 603 */        this.vi[i].clear();
/*  604: 604 */        this.vc[i].clear();
/*  605:     */      }
/*  606: 606 */      this.vi = null;
/*  607: 607 */      this.vc = null;
/*  608:     */    }
/*  609: 609 */    if (this.dataoffsets != null)
/*  610: 610 */      this.dataoffsets = null;
/*  611: 611 */    if (this.pcmlengths != null)
/*  612: 612 */      this.pcmlengths = null;
/*  613: 613 */    if (this.serialnos != null)
/*  614: 614 */      this.serialnos = null;
/*  615: 615 */    if (this.offsets != null)
/*  616: 616 */      this.offsets = null;
/*  617: 617 */    this.oy.clear();
/*  618:     */    
/*  619: 619 */    return 0;
/*  620:     */  }
/*  621:     */  
/*  622:     */  static int fseek(InputStream fis, long off, int whence) {
/*  623: 623 */    if ((fis instanceof SeekableInputStream)) {
/*  624: 624 */      SeekableInputStream sis = (SeekableInputStream)fis;
/*  625:     */      try {
/*  626: 626 */        if (whence == 0) {
/*  627: 627 */          sis.seek(off);
/*  628:     */        }
/*  629: 629 */        else if (whence == 2) {
/*  630: 630 */          sis.seek(sis.getLength() - off);
/*  631:     */        }
/*  632:     */      }
/*  633:     */      catch (Exception e) {}
/*  634:     */      
/*  637: 637 */      return 0;
/*  638:     */    }
/*  639:     */    try {
/*  640: 640 */      if (whence == 0) {
/*  641: 641 */        fis.reset();
/*  642:     */      }
/*  643: 643 */      fis.skip(off);
/*  644:     */    }
/*  645:     */    catch (Exception e) {
/*  646: 646 */      return -1;
/*  647:     */    }
/*  648: 648 */    return 0;
/*  649:     */  }
/*  650:     */  
/*  651:     */  static long ftell(InputStream fis) {
/*  652:     */    try {
/*  653: 653 */      if ((fis instanceof SeekableInputStream)) {
/*  654: 654 */        SeekableInputStream sis = (SeekableInputStream)fis;
/*  655: 655 */        return sis.tell();
/*  656:     */      }
/*  657:     */    }
/*  658:     */    catch (Exception e) {}
/*  659:     */    
/*  660: 660 */    return 0L;
/*  661:     */  }
/*  662:     */  
/*  668:     */  int open(InputStream is, byte[] initial, int ibytes)
/*  669:     */    throws JOrbisException
/*  670:     */  {
/*  671: 671 */    return open_callbacks(is, initial, ibytes);
/*  672:     */  }
/*  673:     */  
/*  674:     */  int open_callbacks(InputStream is, byte[] initial, int ibytes)
/*  675:     */    throws JOrbisException
/*  676:     */  {
/*  677: 677 */    this.datasource = is;
/*  678:     */    
/*  679: 679 */    this.oy.init();
/*  680:     */    
/*  685: 685 */    if (initial != null) {
/*  686: 686 */      int index = this.oy.buffer(ibytes);
/*  687: 687 */      System.arraycopy(initial, 0, this.oy.data, index, ibytes);
/*  688: 688 */      this.oy.wrote(ibytes); }
/*  689:     */    int ret;
/*  690:     */    int ret;
/*  691: 691 */    if ((is instanceof SeekableInputStream)) {
/*  692: 692 */      ret = open_seekable();
/*  693:     */    }
/*  694:     */    else {
/*  695: 695 */      ret = open_nonseekable();
/*  696:     */    }
/*  697: 697 */    if (ret != 0) {
/*  698: 698 */      this.datasource = null;
/*  699: 699 */      clear();
/*  700:     */    }
/*  701: 701 */    return ret;
/*  702:     */  }
/*  703:     */  
/*  704:     */  public int streams()
/*  705:     */  {
/*  706: 706 */    return this.links;
/*  707:     */  }
/*  708:     */  
/*  709:     */  public boolean seekable()
/*  710:     */  {
/*  711: 711 */    return this.seekable;
/*  712:     */  }
/*  713:     */  
/*  722:     */  public int bitrate(int i)
/*  723:     */  {
/*  724: 724 */    if (i >= this.links)
/*  725: 725 */      return -1;
/*  726: 726 */    if ((!this.seekable) && (i != 0))
/*  727: 727 */      return bitrate(0);
/*  728: 728 */    if (i < 0) {
/*  729: 729 */      long bits = 0L;
/*  730: 730 */      for (int j = 0; j < this.links; j++) {
/*  731: 731 */        bits += (this.offsets[(j + 1)] - this.dataoffsets[j]) * 8L;
/*  732:     */      }
/*  733: 733 */      return (int)Math.rint((float)bits / time_total(-1));
/*  734:     */    }
/*  735:     */    
/*  736: 736 */    if (this.seekable)
/*  737:     */    {
/*  738: 738 */      return (int)Math.rint((float)((this.offsets[(i + 1)] - this.dataoffsets[i]) * 8L) / time_total(i));
/*  739:     */    }
/*  740:     */    
/*  742: 742 */    if (this.vi[i].bitrate_nominal > 0) {
/*  743: 743 */      return this.vi[i].bitrate_nominal;
/*  744:     */    }
/*  745:     */    
/*  746: 746 */    if (this.vi[i].bitrate_upper > 0) {
/*  747: 747 */      if (this.vi[i].bitrate_lower > 0) {
/*  748: 748 */        return (this.vi[i].bitrate_upper + this.vi[i].bitrate_lower) / 2;
/*  749:     */      }
/*  750:     */      
/*  751: 751 */      return this.vi[i].bitrate_upper;
/*  752:     */    }
/*  753:     */    
/*  754: 754 */    return -1;
/*  755:     */  }
/*  756:     */  
/*  761:     */  public int bitrate_instant()
/*  762:     */  {
/*  763: 763 */    int _link = this.seekable ? this.current_link : 0;
/*  764: 764 */    if (this.samptrack == 0.0F)
/*  765: 765 */      return -1;
/*  766: 766 */    int ret = (int)(this.bittrack / this.samptrack * this.vi[_link].rate + 0.5D);
/*  767: 767 */    this.bittrack = 0.0F;
/*  768: 768 */    this.samptrack = 0.0F;
/*  769: 769 */    return ret;
/*  770:     */  }
/*  771:     */  
/*  772:     */  public int serialnumber(int i) {
/*  773: 773 */    if (i >= this.links)
/*  774: 774 */      return -1;
/*  775: 775 */    if ((!this.seekable) && (i >= 0))
/*  776: 776 */      return serialnumber(-1);
/*  777: 777 */    if (i < 0) {
/*  778: 778 */      return this.current_serialno;
/*  779:     */    }
/*  780:     */    
/*  781: 781 */    return this.serialnos[i];
/*  782:     */  }
/*  783:     */  
/*  788:     */  public long raw_total(int i)
/*  789:     */  {
/*  790: 790 */    if ((!this.seekable) || (i >= this.links))
/*  791: 791 */      return -1L;
/*  792: 792 */    if (i < 0) {
/*  793: 793 */      long acc = 0L;
/*  794: 794 */      for (int j = 0; j < this.links; j++) {
/*  795: 795 */        acc += raw_total(j);
/*  796:     */      }
/*  797: 797 */      return acc;
/*  798:     */    }
/*  799:     */    
/*  800: 800 */    return this.offsets[(i + 1)] - this.offsets[i];
/*  801:     */  }
/*  802:     */  
/*  806:     */  public long pcm_total(int i)
/*  807:     */  {
/*  808: 808 */    if ((!this.seekable) || (i >= this.links))
/*  809: 809 */      return -1L;
/*  810: 810 */    if (i < 0) {
/*  811: 811 */      long acc = 0L;
/*  812: 812 */      for (int j = 0; j < this.links; j++) {
/*  813: 813 */        acc += pcm_total(j);
/*  814:     */      }
/*  815: 815 */      return acc;
/*  816:     */    }
/*  817:     */    
/*  818: 818 */    return this.pcmlengths[i];
/*  819:     */  }
/*  820:     */  
/*  824:     */  public float time_total(int i)
/*  825:     */  {
/*  826: 826 */    if ((!this.seekable) || (i >= this.links))
/*  827: 827 */      return -1.0F;
/*  828: 828 */    if (i < 0) {
/*  829: 829 */      float acc = 0.0F;
/*  830: 830 */      for (int j = 0; j < this.links; j++) {
/*  831: 831 */        acc += time_total(j);
/*  832:     */      }
/*  833: 833 */      return acc;
/*  834:     */    }
/*  835:     */    
/*  836: 836 */    return (float)this.pcmlengths[i] / this.vi[i].rate;
/*  837:     */  }
/*  838:     */  
/*  847:     */  public int raw_seek(int pos)
/*  848:     */  {
/*  849: 849 */    if (!this.seekable)
/*  850: 850 */      return -1;
/*  851: 851 */    if ((pos < 0) || (pos > this.offsets[this.links]))
/*  852:     */    {
/*  853: 853 */      this.pcm_offset = -1L;
/*  854: 854 */      decode_clear();
/*  855: 855 */      return -1;
/*  856:     */    }
/*  857:     */    
/*  859: 859 */    this.pcm_offset = -1L;
/*  860: 860 */    decode_clear();
/*  861:     */    
/*  863: 863 */    seek_helper(pos);
/*  864:     */    
/*  871: 871 */    switch (process_packet(1))
/*  872:     */    {
/*  874:     */    case 0: 
/*  875: 875 */      this.pcm_offset = pcm_total(-1);
/*  876: 876 */      return 0;
/*  877:     */    
/*  879:     */    case -1: 
/*  880: 880 */      this.pcm_offset = -1L;
/*  881: 881 */      decode_clear();
/*  882: 882 */      return -1;
/*  883:     */    }
/*  884:     */    
/*  885:     */    
/*  886:     */    for (;;)
/*  887:     */    {
/*  888: 888 */      switch (process_packet(0))
/*  889:     */      {
/*  892:     */      case 0: 
/*  893: 893 */        return 0;
/*  894:     */      
/*  896:     */      case -1: 
/*  897: 897 */        this.pcm_offset = -1L;
/*  898: 898 */        decode_clear();
/*  899: 899 */        return -1;
/*  900:     */      }
/*  901:     */      
/*  902:     */    }
/*  903:     */  }
/*  904:     */  
/*  915:     */  public int pcm_seek(long pos)
/*  916:     */  {
/*  917: 917 */    int link = -1;
/*  918: 918 */    long total = pcm_total(-1);
/*  919:     */    
/*  920: 920 */    if (!this.seekable)
/*  921: 921 */      return -1;
/*  922: 922 */    if ((pos < 0L) || (pos > total))
/*  923:     */    {
/*  924: 924 */      this.pcm_offset = -1L;
/*  925: 925 */      decode_clear();
/*  926: 926 */      return -1;
/*  927:     */    }
/*  928:     */    
/*  930: 930 */    for (link = this.links - 1; link >= 0; link--) {
/*  931: 931 */      total -= this.pcmlengths[link];
/*  932: 932 */      if (pos >= total) {
/*  933:     */        break;
/*  934:     */      }
/*  935:     */    }
/*  936:     */    
/*  942: 942 */    long target = pos - total;
/*  943: 943 */    long end = this.offsets[(link + 1)];
/*  944: 944 */    long begin = this.offsets[link];
/*  945: 945 */    int best = (int)begin;
/*  946:     */    
/*  947: 947 */    Page og = new Page();
/*  948: 948 */    while (begin < end)
/*  949:     */    {
/*  950:     */      long bisect;
/*  951:     */      long bisect;
/*  952: 952 */      if (end - begin < 8500L) {
/*  953: 953 */        bisect = begin;
/*  954:     */      }
/*  955:     */      else {
/*  956: 956 */        bisect = (end + begin) / 2L;
/*  957:     */      }
/*  958:     */      
/*  959: 959 */      seek_helper(bisect);
/*  960: 960 */      int ret = get_next_page(og, end - bisect);
/*  961:     */      
/*  962: 962 */      if (ret == -1) {
/*  963: 963 */        end = bisect;
/*  964:     */      }
/*  965:     */      else {
/*  966: 966 */        long granulepos = og.granulepos();
/*  967: 967 */        if (granulepos < target) {
/*  968: 968 */          best = ret;
/*  969: 969 */          begin = this.offset;
/*  970:     */        }
/*  971:     */        else {
/*  972: 972 */          end = bisect;
/*  973:     */        }
/*  974:     */      }
/*  975:     */    }
/*  976:     */    
/*  977: 977 */    if (raw_seek(best) != 0)
/*  978:     */    {
/*  979: 979 */      this.pcm_offset = -1L;
/*  980: 980 */      decode_clear();
/*  981: 981 */      return -1;
/*  982:     */    }
/*  983:     */    
/*  986: 986 */    if (this.pcm_offset >= pos)
/*  987:     */    {
/*  988: 988 */      this.pcm_offset = -1L;
/*  989: 989 */      decode_clear();
/*  990: 990 */      return -1;
/*  991:     */    }
/*  992: 992 */    if (pos > pcm_total(-1))
/*  993:     */    {
/*  994: 994 */      this.pcm_offset = -1L;
/*  995: 995 */      decode_clear();
/*  996: 996 */      return -1;
/*  997:     */    }
/*  998:     */    
/* 1001:1001 */    while (this.pcm_offset < pos) {
/* 1002:1002 */      int target = (int)(pos - this.pcm_offset);
/* 1003:1003 */      float[][][] _pcm = new float[1][][];
/* 1004:1004 */      int[] _index = new int[getInfo(-1).channels];
/* 1005:1005 */      int samples = this.vd.synthesis_pcmout(_pcm, _index);
/* 1006:     */      
/* 1007:1007 */      if (samples > target)
/* 1008:1008 */        samples = target;
/* 1009:1009 */      this.vd.synthesis_read(samples);
/* 1010:1010 */      this.pcm_offset += samples;
/* 1011:     */      
/* 1012:1012 */      if ((samples < target) && 
/* 1013:1013 */        (process_packet(1) == 0)) {
/* 1014:1014 */        this.pcm_offset = pcm_total(-1);
/* 1015:     */      }
/* 1016:     */    }
/* 1017:1017 */    return 0;
/* 1018:     */  }
/* 1019:     */  
/* 1029:     */  int time_seek(float seconds)
/* 1030:     */  {
/* 1031:1031 */    int link = -1;
/* 1032:1032 */    long pcm_total = pcm_total(-1);
/* 1033:1033 */    float time_total = time_total(-1);
/* 1034:     */    
/* 1035:1035 */    if (!this.seekable)
/* 1036:1036 */      return -1;
/* 1037:1037 */    if ((seconds < 0.0F) || (seconds > time_total))
/* 1038:     */    {
/* 1039:1039 */      this.pcm_offset = -1L;
/* 1040:1040 */      decode_clear();
/* 1041:1041 */      return -1;
/* 1042:     */    }
/* 1043:     */    
/* 1045:1045 */    for (link = this.links - 1; link >= 0; link--) {
/* 1046:1046 */      pcm_total -= this.pcmlengths[link];
/* 1047:1047 */      time_total -= time_total(link);
/* 1048:1048 */      if (seconds >= time_total) {
/* 1049:     */        break;
/* 1050:     */      }
/* 1051:     */    }
/* 1052:     */    
/* 1054:1054 */    long target = ((float)pcm_total + (seconds - time_total) * this.vi[link].rate);
/* 1055:1055 */    return pcm_seek(target);
/* 1056:     */  }
/* 1057:     */  
/* 1066:     */  public long raw_tell()
/* 1067:     */  {
/* 1068:1068 */    return this.offset;
/* 1069:     */  }
/* 1070:     */  
/* 1071:     */  public long pcm_tell()
/* 1072:     */  {
/* 1073:1073 */    return this.pcm_offset;
/* 1074:     */  }
/* 1075:     */  
/* 1078:     */  public float time_tell()
/* 1079:     */  {
/* 1080:1080 */    int link = -1;
/* 1081:1081 */    long pcm_total = 0L;
/* 1082:1082 */    float time_total = 0.0F;
/* 1083:     */    
/* 1084:1084 */    if (this.seekable) {
/* 1085:1085 */      pcm_total = pcm_total(-1);
/* 1086:1086 */      time_total = time_total(-1);
/* 1087:     */      
/* 1089:1089 */      for (link = this.links - 1; link >= 0; link--) {
/* 1090:1090 */        pcm_total -= this.pcmlengths[link];
/* 1091:1091 */        time_total -= time_total(link);
/* 1092:1092 */        if (this.pcm_offset >= pcm_total) {
/* 1093:     */          break;
/* 1094:     */        }
/* 1095:     */      }
/* 1096:     */    }
/* 1097:1097 */    return time_total + (float)(this.pcm_offset - pcm_total) / this.vi[link].rate;
/* 1098:     */  }
/* 1099:     */  
/* 1107:     */  public Info getInfo(int link)
/* 1108:     */  {
/* 1109:1109 */    if (this.seekable) {
/* 1110:1110 */      if (link < 0) {
/* 1111:1111 */        if (this.decode_ready) {
/* 1112:1112 */          return this.vi[this.current_link];
/* 1113:     */        }
/* 1114:     */        
/* 1115:1115 */        return null;
/* 1116:     */      }
/* 1117:     */      
/* 1119:1119 */      if (link >= this.links) {
/* 1120:1120 */        return null;
/* 1121:     */      }
/* 1122:     */      
/* 1123:1123 */      return this.vi[link];
/* 1124:     */    }
/* 1125:     */    
/* 1128:1128 */    if (this.decode_ready) {
/* 1129:1129 */      return this.vi[0];
/* 1130:     */    }
/* 1131:     */    
/* 1132:1132 */    return null;
/* 1133:     */  }
/* 1134:     */  
/* 1136:     */  public Comment getComment(int link)
/* 1137:     */  {
/* 1138:1138 */    if (this.seekable) {
/* 1139:1139 */      if (link < 0) {
/* 1140:1140 */        if (this.decode_ready) {
/* 1141:1141 */          return this.vc[this.current_link];
/* 1142:     */        }
/* 1143:     */        
/* 1144:1144 */        return null;
/* 1145:     */      }
/* 1146:     */      
/* 1148:1148 */      if (link >= this.links) {
/* 1149:1149 */        return null;
/* 1150:     */      }
/* 1151:     */      
/* 1152:1152 */      return this.vc[link];
/* 1153:     */    }
/* 1154:     */    
/* 1157:1157 */    if (this.decode_ready) {
/* 1158:1158 */      return this.vc[0];
/* 1159:     */    }
/* 1160:     */    
/* 1161:1161 */    return null;
/* 1162:     */  }
/* 1163:     */  
/* 1165:     */  int host_is_big_endian()
/* 1166:     */  {
/* 1167:1167 */    return 1;
/* 1168:     */  }
/* 1169:     */  
/* 1206:     */  int read(byte[] buffer, int length, int bigendianp, int word, int sgned, int[] bitstream)
/* 1207:     */  {
/* 1208:1208 */    int host_endian = host_is_big_endian();
/* 1209:1209 */    int index = 0;
/* 1210:     */    for (;;)
/* 1211:     */    {
/* 1212:1212 */      if (this.decode_ready)
/* 1213:     */      {
/* 1214:1214 */        float[][][] _pcm = new float[1][][];
/* 1215:1215 */        int[] _index = new int[getInfo(-1).channels];
/* 1216:1216 */        int samples = this.vd.synthesis_pcmout(_pcm, _index);
/* 1217:1217 */        float[][] pcm = _pcm[0];
/* 1218:1218 */        if (samples != 0)
/* 1219:     */        {
/* 1220:1220 */          int channels = getInfo(-1).channels;
/* 1221:1221 */          int bytespersample = word * channels;
/* 1222:1222 */          if (samples > length / bytespersample) {
/* 1223:1223 */            samples = length / bytespersample;
/* 1224:     */          }
/* 1225:     */          
/* 1228:1228 */          if (word == 1) {
/* 1229:1229 */            int off = sgned != 0 ? 0 : 128;
/* 1230:1230 */            for (int j = 0; j < samples; j++) {
/* 1231:1231 */              for (int i = 0; i < channels; i++) {
/* 1232:1232 */                int val = (int)(pcm[i][(_index[i] + j)] * 128.0D + 0.5D);
/* 1233:1233 */                if (val > 127) {
/* 1234:1234 */                  val = 127;
/* 1235:1235 */                } else if (val < -128)
/* 1236:1236 */                  val = -128;
/* 1237:1237 */                buffer[(index++)] = ((byte)(val + off));
/* 1238:     */              }
/* 1239:     */            }
/* 1240:     */          }
/* 1241:     */          else {
/* 1242:1242 */            int off = sgned != 0 ? 0 : 32768;
/* 1243:     */            
/* 1244:1244 */            if (host_endian == bigendianp) {
/* 1245:1245 */              if (sgned != 0) {
/* 1246:1246 */                for (int i = 0; i < channels; i++) {
/* 1247:1247 */                  int src = _index[i];
/* 1248:1248 */                  int dest = i;
/* 1249:1249 */                  for (int j = 0; j < samples; j++) {
/* 1250:1250 */                    int val = (int)(pcm[i][(src + j)] * 32768.0D + 0.5D);
/* 1251:1251 */                    if (val > 32767) {
/* 1252:1252 */                      val = 32767;
/* 1253:1253 */                    } else if (val < -32768)
/* 1254:1254 */                      val = -32768;
/* 1255:1255 */                    buffer[dest] = ((byte)(val >>> 8));
/* 1256:1256 */                    buffer[(dest + 1)] = ((byte)val);
/* 1257:1257 */                    dest += channels * 2;
/* 1258:     */                  }
/* 1259:     */                  
/* 1260:     */                }
/* 1261:     */              } else {
/* 1262:1262 */                for (int i = 0; i < channels; i++) {
/* 1263:1263 */                  float[] src = pcm[i];
/* 1264:1264 */                  int dest = i;
/* 1265:1265 */                  for (int j = 0; j < samples; j++) {
/* 1266:1266 */                    int val = (int)(src[j] * 32768.0D + 0.5D);
/* 1267:1267 */                    if (val > 32767) {
/* 1268:1268 */                      val = 32767;
/* 1269:1269 */                    } else if (val < -32768)
/* 1270:1270 */                      val = -32768;
/* 1271:1271 */                    buffer[dest] = ((byte)(val + off >>> 8));
/* 1272:1272 */                    buffer[(dest + 1)] = ((byte)(val + off));
/* 1273:1273 */                    dest += channels * 2;
/* 1274:     */                  }
/* 1275:     */                }
/* 1276:     */              }
/* 1277:     */            }
/* 1278:1278 */            else if (bigendianp != 0) {
/* 1279:1279 */              for (int j = 0; j < samples; j++) {
/* 1280:1280 */                for (int i = 0; i < channels; i++) {
/* 1281:1281 */                  int val = (int)(pcm[i][j] * 32768.0D + 0.5D);
/* 1282:1282 */                  if (val > 32767) {
/* 1283:1283 */                    val = 32767;
/* 1284:1284 */                  } else if (val < -32768)
/* 1285:1285 */                    val = -32768;
/* 1286:1286 */                  val += off;
/* 1287:1287 */                  buffer[(index++)] = ((byte)(val >>> 8));
/* 1288:1288 */                  buffer[(index++)] = ((byte)val);
/* 1289:     */                }
/* 1290:     */                
/* 1291:     */              }
/* 1292:     */              
/* 1293:     */            } else {
/* 1294:1294 */              for (int j = 0; j < samples; j++) {
/* 1295:1295 */                for (int i = 0; i < channels; i++) {
/* 1296:1296 */                  int val = (int)(pcm[i][j] * 32768.0D + 0.5D);
/* 1297:1297 */                  if (val > 32767) {
/* 1298:1298 */                    val = 32767;
/* 1299:1299 */                  } else if (val < -32768)
/* 1300:1300 */                    val = -32768;
/* 1301:1301 */                  val += off;
/* 1302:1302 */                  buffer[(index++)] = ((byte)val);
/* 1303:1303 */                  buffer[(index++)] = ((byte)(val >>> 8));
/* 1304:     */                }
/* 1305:     */              }
/* 1306:     */            }
/* 1307:     */          }
/* 1308:     */          
/* 1310:1310 */          this.vd.synthesis_read(samples);
/* 1311:1311 */          this.pcm_offset += samples;
/* 1312:1312 */          if (bitstream != null)
/* 1313:1313 */            bitstream[0] = this.current_link;
/* 1314:1314 */          return samples * bytespersample;
/* 1315:     */        }
/* 1316:     */      }
/* 1317:     */      
/* 1319:1319 */      switch (process_packet(1)) {
/* 1320:     */      case 0: 
/* 1321:1321 */        return 0;
/* 1322:     */      case -1: 
/* 1323:1323 */        return -1;
/* 1324:     */      }
/* 1325:     */      
/* 1326:     */    }
/* 1327:     */  }
/* 1328:     */  
/* 1329:     */  public Info[] getInfo()
/* 1330:     */  {
/* 1331:1331 */    return this.vi;
/* 1332:     */  }
/* 1333:     */  
/* 1334:     */  public Comment[] getComment() {
/* 1335:1335 */    return this.vc;
/* 1336:     */  }
/* 1337:     */  
/* 1338:     */  public void close() throws IOException {
/* 1339:1339 */    this.datasource.close();
/* 1340:     */  }
/* 1341:     */  
/* 1342:     */  class SeekableInputStream extends InputStream {
/* 1343:1343 */    RandomAccessFile raf = null;
/* 1344:1344 */    final String mode = "r";
/* 1345:     */    
/* 1346:     */    SeekableInputStream(String file) throws IOException {
/* 1347:1347 */      this.raf = new RandomAccessFile(file, "r");
/* 1348:     */    }
/* 1349:     */    
/* 1350:     */    public int read() throws IOException {
/* 1351:1351 */      return this.raf.read();
/* 1352:     */    }
/* 1353:     */    
/* 1354:     */    public int read(byte[] buf) throws IOException {
/* 1355:1355 */      return this.raf.read(buf);
/* 1356:     */    }
/* 1357:     */    
/* 1358:     */    public int read(byte[] buf, int s, int len) throws IOException {
/* 1359:1359 */      return this.raf.read(buf, s, len);
/* 1360:     */    }
/* 1361:     */    
/* 1362:     */    public long skip(long n) throws IOException {
/* 1363:1363 */      return this.raf.skipBytes((int)n);
/* 1364:     */    }
/* 1365:     */    
/* 1366:     */    public long getLength() throws IOException {
/* 1367:1367 */      return this.raf.length();
/* 1368:     */    }
/* 1369:     */    
/* 1370:     */    public long tell() throws IOException {
/* 1371:1371 */      return this.raf.getFilePointer();
/* 1372:     */    }
/* 1373:     */    
/* 1374:     */    public int available() throws IOException {
/* 1375:1375 */      return this.raf.length() == this.raf.getFilePointer() ? 0 : 1;
/* 1376:     */    }
/* 1377:     */    
/* 1378:     */    public void close() throws IOException {
/* 1379:1379 */      this.raf.close();
/* 1380:     */    }
/* 1381:     */    
/* 1382:     */    public synchronized void mark(int m) {}
/* 1383:     */    
/* 1384:     */    public synchronized void reset() throws IOException
/* 1385:     */    {}
/* 1386:     */    
/* 1387:     */    public boolean markSupported()
/* 1388:     */    {
/* 1389:1389 */      return false;
/* 1390:     */    }
/* 1391:     */    
/* 1392:     */    public void seek(long pos) throws IOException {
/* 1393:1393 */      this.raf.seek(pos);
/* 1394:     */    }
/* 1395:     */  }
/* 1396:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.VorbisFile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */