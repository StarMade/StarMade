/*      */ package com.jcraft.jorbis;
/*      */ 
/*      */ import com.jcraft.jogg.Packet;
/*      */ import com.jcraft.jogg.Page;
/*      */ import com.jcraft.jogg.StreamState;
/*      */ import com.jcraft.jogg.SyncState;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.RandomAccessFile;
/*      */ 
/*      */ public class VorbisFile
/*      */ {
/*      */   static final int CHUNKSIZE = 8500;
/*      */   static final int SEEK_SET = 0;
/*      */   static final int SEEK_CUR = 1;
/*      */   static final int SEEK_END = 2;
/*      */   static final int OV_FALSE = -1;
/*      */   static final int OV_EOF = -2;
/*      */   static final int OV_HOLE = -3;
/*      */   static final int OV_EREAD = -128;
/*      */   static final int OV_EFAULT = -129;
/*      */   static final int OV_EIMPL = -130;
/*      */   static final int OV_EINVAL = -131;
/*      */   static final int OV_ENOTVORBIS = -132;
/*      */   static final int OV_EBADHEADER = -133;
/*      */   static final int OV_EVERSION = -134;
/*      */   static final int OV_ENOTAUDIO = -135;
/*      */   static final int OV_EBADPACKET = -136;
/*      */   static final int OV_EBADLINK = -137;
/*      */   static final int OV_ENOSEEK = -138;
/*      */   InputStream datasource;
/*   58 */   boolean seekable = false;
/*      */   long offset;
/*      */   long end;
/*   62 */   SyncState oy = new SyncState();
/*      */   int links;
/*      */   long[] offsets;
/*      */   long[] dataoffsets;
/*      */   int[] serialnos;
/*      */   long[] pcmlengths;
/*      */   Info[] vi;
/*      */   Comment[] vc;
/*      */   long pcm_offset;
/*   74 */   boolean decode_ready = false;
/*      */   int current_serialno;
/*      */   int current_link;
/*      */   float bittrack;
/*      */   float samptrack;
/*   82 */   StreamState os = new StreamState();
/*      */ 
/*   84 */   DspState vd = new DspState();
/*      */ 
/*   86 */   Block vb = new Block(this.vd);
/*      */ 
/*      */   public VorbisFile(String file)
/*      */     throws JOrbisException
/*      */   {
/*   92 */     InputStream is = null;
/*      */     try {
/*   94 */       is = new SeekableInputStream(file);
/*   95 */       int ret = open(is, null, 0);
/*   96 */       if (ret == -1)
/*   97 */         throw new JOrbisException("VorbisFile: open return -1");
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  101 */       throw new JOrbisException("VorbisFile: " + e.toString());
/*      */     }
/*      */     finally {
/*  104 */       if (is != null)
/*      */         try {
/*  106 */           is.close();
/*      */         }
/*      */         catch (IOException e) {
/*  109 */           e.printStackTrace();
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public VorbisFile(InputStream is, byte[] initial, int ibytes)
/*      */     throws JOrbisException
/*      */   {
/*  118 */     int ret = open(is, initial, ibytes);
/*  119 */     if (ret == -1);
/*      */   }
/*      */ 
/*      */   private int get_data()
/*      */   {
/*  124 */     int index = this.oy.buffer(8500);
/*  125 */     byte[] buffer = this.oy.data;
/*  126 */     int bytes = 0;
/*      */     try {
/*  128 */       bytes = this.datasource.read(buffer, index, 8500);
/*      */     }
/*      */     catch (Exception e) {
/*  131 */       return -128;
/*      */     }
/*  133 */     this.oy.wrote(bytes);
/*  134 */     if (bytes == -1) {
/*  135 */       bytes = 0;
/*      */     }
/*  137 */     return bytes;
/*      */   }
/*      */ 
/*      */   private void seek_helper(long offst) {
/*  141 */     fseek(this.datasource, offst, 0);
/*  142 */     this.offset = offst;
/*  143 */     this.oy.reset();
/*      */   }
/*      */ 
/*      */   private int get_next_page(Page page, long boundary) {
/*  147 */     if (boundary > 0L)
/*  148 */       boundary += this.offset;
/*      */     while (true)
/*      */     {
/*  151 */       if ((boundary > 0L) && (this.offset >= boundary))
/*  152 */         return -1;
/*  153 */       int more = this.oy.pageseek(page);
/*  154 */       if (more < 0) {
/*  155 */         this.offset -= more;
/*      */       }
/*  158 */       else if (more == 0) {
/*  159 */         if (boundary == 0L)
/*  160 */           return -1;
/*  161 */         int ret = get_data();
/*  162 */         if (ret == 0)
/*  163 */           return -2;
/*  164 */         if (ret < 0)
/*  165 */           return -128;
/*      */       }
/*      */       else {
/*  168 */         int ret = (int)this.offset;
/*  169 */         this.offset += more;
/*  170 */         return ret;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private int get_prev_page(Page page) throws JOrbisException
/*      */   {
/*  177 */     long begin = this.offset;
/*      */ 
/*  179 */     int offst = -1;
/*  180 */     label97: while (offst == -1) {
/*  181 */       begin -= 8500L;
/*  182 */       if (begin < 0L)
/*  183 */         begin = 0L;
/*  184 */       seek_helper(begin);
/*      */       while (true) { if (this.offset >= begin + 8500L) break label97;
/*  186 */         int ret = get_next_page(page, begin + 8500L - this.offset);
/*  187 */         if (ret == -128) {
/*  188 */           return -128;
/*      */         }
/*  190 */         if (ret < 0) {
/*  191 */           if (offst != -1) break;
/*  192 */           throw new JOrbisException();
/*      */         }
/*      */ 
/*  196 */         offst = ret;
/*      */       }
/*      */     }
/*      */ 
/*  200 */     seek_helper(offst);
/*  201 */     int ret = get_next_page(page, 8500L);
/*  202 */     if (ret < 0) {
/*  203 */       return -129;
/*      */     }
/*  205 */     return offst;
/*      */   }
/*      */ 
/*      */   int bisect_forward_serialno(long begin, long searched, long end, int currentno, int m)
/*      */   {
/*  210 */     long endsearched = end;
/*  211 */     long next = end;
/*  212 */     Page page = new Page();
/*      */ 
/*  215 */     while (searched < endsearched)
/*      */     {
/*      */       long bisect;
/*      */       long bisect;
/*  217 */       if (endsearched - searched < 8500L) {
/*  218 */         bisect = searched;
/*      */       }
/*      */       else {
/*  221 */         bisect = (searched + endsearched) / 2L;
/*      */       }
/*      */ 
/*  224 */       seek_helper(bisect);
/*  225 */       int ret = get_next_page(page, -1L);
/*  226 */       if (ret == -128)
/*  227 */         return -128;
/*  228 */       if ((ret < 0) || (page.serialno() != currentno)) {
/*  229 */         endsearched = bisect;
/*  230 */         if (ret >= 0)
/*  231 */           next = ret;
/*      */       }
/*      */       else {
/*  234 */         searched = ret + page.header_len + page.body_len;
/*      */       }
/*      */     }
/*  237 */     seek_helper(next);
/*  238 */     int ret = get_next_page(page, -1L);
/*  239 */     if (ret == -128) {
/*  240 */       return -128;
/*      */     }
/*  242 */     if ((searched >= end) || (ret == -1)) {
/*  243 */       this.links = (m + 1);
/*  244 */       this.offsets = new long[m + 2];
/*  245 */       this.offsets[(m + 1)] = searched;
/*      */     }
/*      */     else {
/*  248 */       ret = bisect_forward_serialno(next, this.offset, end, page.serialno(), m + 1);
/*  249 */       if (ret == -128)
/*  250 */         return -128;
/*      */     }
/*  252 */     this.offsets[m] = begin;
/*  253 */     return 0;
/*      */   }
/*      */ 
/*      */   int fetch_headers(Info vi, Comment vc, int[] serialno, Page og_ptr)
/*      */   {
/*  259 */     Page og = new Page();
/*  260 */     Packet op = new Packet();
/*      */ 
/*  263 */     if (og_ptr == null) {
/*  264 */       int ret = get_next_page(og, 8500L);
/*  265 */       if (ret == -128)
/*  266 */         return -128;
/*  267 */       if (ret < 0)
/*  268 */         return -132;
/*  269 */       og_ptr = og;
/*      */     }
/*      */ 
/*  272 */     if (serialno != null) {
/*  273 */       serialno[0] = og_ptr.serialno();
/*      */     }
/*  275 */     this.os.init(og_ptr.serialno());
/*      */ 
/*  280 */     vi.init();
/*  281 */     vc.init();
/*      */ 
/*  283 */     int i = 0;
/*  284 */     while (i < 3) {
/*  285 */       this.os.pagein(og_ptr);
/*  286 */       while (i < 3) {
/*  287 */         int result = this.os.packetout(op);
/*  288 */         if (result == 0)
/*      */           break;
/*  290 */         if (result == -1) {
/*  291 */           vi.clear();
/*  292 */           vc.clear();
/*  293 */           this.os.clear();
/*  294 */           return -1;
/*      */         }
/*  296 */         if (vi.synthesis_headerin(vc, op) != 0) {
/*  297 */           vi.clear();
/*  298 */           vc.clear();
/*  299 */           this.os.clear();
/*  300 */           return -1;
/*      */         }
/*  302 */         i++;
/*      */       }
/*  304 */       if ((i < 3) && 
/*  305 */         (get_next_page(og_ptr, 1L) < 0)) {
/*  306 */         vi.clear();
/*  307 */         vc.clear();
/*  308 */         this.os.clear();
/*  309 */         return -1;
/*      */       }
/*      */     }
/*  312 */     return 0;
/*      */   }
/*      */ 
/*      */   void prefetch_all_headers(Info first_i, Comment first_c, int dataoffset)
/*      */     throws JOrbisException
/*      */   {
/*  321 */     Page og = new Page();
/*      */ 
/*  324 */     this.vi = new Info[this.links];
/*  325 */     this.vc = new Comment[this.links];
/*  326 */     this.dataoffsets = new long[this.links];
/*  327 */     this.pcmlengths = new long[this.links];
/*  328 */     this.serialnos = new int[this.links];
/*      */ 
/*  330 */     for (int i = 0; i < this.links; i++) {
/*  331 */       if ((first_i != null) && (first_c != null) && (i == 0))
/*      */       {
/*  334 */         this.vi[i] = first_i;
/*  335 */         this.vc[i] = first_c;
/*  336 */         this.dataoffsets[i] = dataoffset;
/*      */       }
/*      */       else
/*      */       {
/*  340 */         seek_helper(this.offsets[i]);
/*  341 */         this.vi[i] = new Info();
/*  342 */         this.vc[i] = new Comment();
/*  343 */         if (fetch_headers(this.vi[i], this.vc[i], null, null) == -1) {
/*  344 */           this.dataoffsets[i] = -1L;
/*      */         }
/*      */         else {
/*  347 */           this.dataoffsets[i] = this.offset;
/*  348 */           this.os.clear();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  355 */       long end = this.offsets[(i + 1)];
/*  356 */       seek_helper(end);
/*      */       do
/*      */       {
/*  359 */         int ret = get_prev_page(og);
/*  360 */         if (ret == -1)
/*      */         {
/*  362 */           this.vi[i].clear();
/*  363 */           this.vc[i].clear();
/*  364 */           break;
/*      */         }
/*      */       }
/*  366 */       while (og.granulepos() == -1L);
/*  367 */       this.serialnos[i] = og.serialno();
/*  368 */       this.pcmlengths[i] = og.granulepos();
/*      */     }
/*      */   }
/*      */ 
/*      */   private int make_decode_ready()
/*      */   {
/*  377 */     if (this.decode_ready)
/*  378 */       System.exit(1);
/*  379 */     this.vd.synthesis_init(this.vi[0]);
/*  380 */     this.vb.init(this.vd);
/*  381 */     this.decode_ready = true;
/*  382 */     return 0;
/*      */   }
/*      */ 
/*      */   int open_seekable() throws JOrbisException {
/*  386 */     Info initial_i = new Info();
/*  387 */     Comment initial_c = new Comment();
/*      */ 
/*  392 */     Page og = new Page();
/*      */ 
/*  394 */     int[] foo = new int[1];
/*  395 */     int ret = fetch_headers(initial_i, initial_c, foo, null);
/*  396 */     int serialno = foo[0];
/*  397 */     int dataoffset = (int)this.offset;
/*  398 */     this.os.clear();
/*  399 */     if (ret == -1)
/*  400 */       return -1;
/*  401 */     if (ret < 0) {
/*  402 */       return ret;
/*      */     }
/*  404 */     this.seekable = true;
/*  405 */     fseek(this.datasource, 0L, 2);
/*  406 */     this.offset = ftell(this.datasource);
/*  407 */     long end = this.offset;
/*      */ 
/*  410 */     end = get_prev_page(og);
/*      */ 
/*  412 */     if (og.serialno() != serialno)
/*      */     {
/*  415 */       if (bisect_forward_serialno(0L, 0L, end + 1L, serialno, 0) < 0) {
/*  416 */         clear();
/*  417 */         return -128;
/*      */       }
/*      */ 
/*      */     }
/*  422 */     else if (bisect_forward_serialno(0L, end, end + 1L, serialno, 0) < 0) {
/*  423 */       clear();
/*  424 */       return -128;
/*      */     }
/*      */ 
/*  427 */     prefetch_all_headers(initial_i, initial_c, dataoffset);
/*  428 */     return 0;
/*      */   }
/*      */ 
/*      */   int open_nonseekable()
/*      */   {
/*  433 */     this.links = 1;
/*  434 */     this.vi = new Info[this.links];
/*  435 */     this.vi[0] = new Info();
/*  436 */     this.vc = new Comment[this.links];
/*  437 */     this.vc[0] = new Comment();
/*      */ 
/*  440 */     int[] foo = new int[1];
/*  441 */     if (fetch_headers(this.vi[0], this.vc[0], foo, null) == -1)
/*  442 */       return -1;
/*  443 */     this.current_serialno = foo[0];
/*  444 */     make_decode_ready();
/*  445 */     return 0;
/*      */   }
/*      */ 
/*      */   void decode_clear()
/*      */   {
/*  450 */     this.os.clear();
/*  451 */     this.vd.clear();
/*  452 */     this.vb.clear();
/*  453 */     this.decode_ready = false;
/*  454 */     this.bittrack = 0.0F;
/*  455 */     this.samptrack = 0.0F;
/*      */   }
/*      */ 
/*      */   int process_packet(int readp)
/*      */   {
/*  469 */     Page og = new Page();
/*      */     while (true)
/*      */     {
/*  476 */       if (this.decode_ready) {
/*  477 */         Packet op = new Packet();
/*  478 */         int result = this.os.packetout(op);
/*      */ 
/*  483 */         if (result > 0)
/*      */         {
/*  485 */           long granulepos = op.granulepos;
/*  486 */           if (this.vb.synthesis(op) == 0)
/*      */           {
/*  495 */             int oldsamples = this.vd.synthesis_pcmout((float[][][])null, null);
/*  496 */             this.vd.synthesis_blockin(this.vb);
/*  497 */             this.samptrack += this.vd.synthesis_pcmout((float[][][])null, null) - oldsamples;
/*  498 */             this.bittrack += op.bytes * 8;
/*      */ 
/*  502 */             if ((granulepos != -1L) && (op.e_o_s == 0)) {
/*  503 */               int link = this.seekable ? this.current_link : 0;
/*      */ 
/*  517 */               int samples = this.vd.synthesis_pcmout((float[][][])null, null);
/*  518 */               granulepos -= samples;
/*  519 */               for (int i = 0; i < link; i++) {
/*  520 */                 granulepos += this.pcmlengths[i];
/*      */               }
/*  522 */               this.pcm_offset = granulepos;
/*      */             }
/*  524 */             return 1;
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  529 */       if (readp == 0)
/*  530 */         return 0;
/*  531 */       if (get_next_page(og, -1L) < 0) {
/*  532 */         return 0;
/*      */       }
/*      */ 
/*  536 */       this.bittrack += og.header_len * 8;
/*      */ 
/*  539 */       if ((this.decode_ready) && 
/*  540 */         (this.current_serialno != og.serialno())) {
/*  541 */         decode_clear();
/*      */       }
/*      */ 
/*  556 */       if (!this.decode_ready)
/*      */       {
/*      */         int i;
/*  558 */         if (this.seekable) {
/*  559 */           this.current_serialno = og.serialno();
/*      */ 
/*  564 */           for (int i = 0; (i < this.links) && 
/*  565 */             (this.serialnos[i] != this.current_serialno); i++);
/*  568 */           if (i == this.links) {
/*  569 */             return -1;
/*      */           }
/*  571 */           this.current_link = i;
/*      */ 
/*  573 */           this.os.init(this.current_serialno);
/*  574 */           this.os.reset();
/*      */         }
/*      */         else
/*      */         {
/*  580 */           int[] foo = new int[1];
/*  581 */           int ret = fetch_headers(this.vi[0], this.vc[0], foo, og);
/*  582 */           this.current_serialno = foo[0];
/*  583 */           if (ret != 0)
/*  584 */             return ret;
/*  585 */           this.current_link += 1;
/*  586 */           i = 0;
/*      */         }
/*  588 */         make_decode_ready();
/*      */       }
/*  590 */       this.os.pagein(og);
/*      */     }
/*      */   }
/*      */ 
/*      */   int clear()
/*      */   {
/*  597 */     this.vb.clear();
/*  598 */     this.vd.clear();
/*  599 */     this.os.clear();
/*      */ 
/*  601 */     if ((this.vi != null) && (this.links != 0)) {
/*  602 */       for (int i = 0; i < this.links; i++) {
/*  603 */         this.vi[i].clear();
/*  604 */         this.vc[i].clear();
/*      */       }
/*  606 */       this.vi = null;
/*  607 */       this.vc = null;
/*      */     }
/*  609 */     if (this.dataoffsets != null)
/*  610 */       this.dataoffsets = null;
/*  611 */     if (this.pcmlengths != null)
/*  612 */       this.pcmlengths = null;
/*  613 */     if (this.serialnos != null)
/*  614 */       this.serialnos = null;
/*  615 */     if (this.offsets != null)
/*  616 */       this.offsets = null;
/*  617 */     this.oy.clear();
/*      */ 
/*  619 */     return 0;
/*      */   }
/*      */ 
/*      */   static int fseek(InputStream fis, long off, int whence) {
/*  623 */     if ((fis instanceof SeekableInputStream)) {
/*  624 */       SeekableInputStream sis = (SeekableInputStream)fis;
/*      */       try {
/*  626 */         if (whence == 0) {
/*  627 */           sis.seek(off);
/*      */         }
/*  629 */         else if (whence == 2) {
/*  630 */           sis.seek(sis.getLength() - off);
/*      */         }
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/*      */       }
/*      */ 
/*  637 */       return 0;
/*      */     }
/*      */     try {
/*  640 */       if (whence == 0) {
/*  641 */         fis.reset();
/*      */       }
/*  643 */       fis.skip(off);
/*      */     }
/*      */     catch (Exception e) {
/*  646 */       return -1;
/*      */     }
/*  648 */     return 0;
/*      */   }
/*      */ 
/*      */   static long ftell(InputStream fis) {
/*      */     try {
/*  653 */       if ((fis instanceof SeekableInputStream)) {
/*  654 */         SeekableInputStream sis = (SeekableInputStream)fis;
/*  655 */         return sis.tell();
/*      */       }
/*      */     }
/*      */     catch (Exception e) {
/*      */     }
/*  660 */     return 0L;
/*      */   }
/*      */ 
/*      */   int open(InputStream is, byte[] initial, int ibytes)
/*      */     throws JOrbisException
/*      */   {
/*  671 */     return open_callbacks(is, initial, ibytes);
/*      */   }
/*      */ 
/*      */   int open_callbacks(InputStream is, byte[] initial, int ibytes)
/*      */     throws JOrbisException
/*      */   {
/*  677 */     this.datasource = is;
/*      */ 
/*  679 */     this.oy.init();
/*      */ 
/*  685 */     if (initial != null) {
/*  686 */       int index = this.oy.buffer(ibytes);
/*  687 */       System.arraycopy(initial, 0, this.oy.data, index, ibytes);
/*  688 */       this.oy.wrote(ibytes);
/*      */     }
/*      */     int ret;
/*      */     int ret;
/*  691 */     if ((is instanceof SeekableInputStream)) {
/*  692 */       ret = open_seekable();
/*      */     }
/*      */     else {
/*  695 */       ret = open_nonseekable();
/*      */     }
/*  697 */     if (ret != 0) {
/*  698 */       this.datasource = null;
/*  699 */       clear();
/*      */     }
/*  701 */     return ret;
/*      */   }
/*      */ 
/*      */   public int streams()
/*      */   {
/*  706 */     return this.links;
/*      */   }
/*      */ 
/*      */   public boolean seekable()
/*      */   {
/*  711 */     return this.seekable;
/*      */   }
/*      */ 
/*      */   public int bitrate(int i)
/*      */   {
/*  724 */     if (i >= this.links)
/*  725 */       return -1;
/*  726 */     if ((!this.seekable) && (i != 0))
/*  727 */       return bitrate(0);
/*  728 */     if (i < 0) {
/*  729 */       long bits = 0L;
/*  730 */       for (int j = 0; j < this.links; j++) {
/*  731 */         bits += (this.offsets[(j + 1)] - this.dataoffsets[j]) * 8L;
/*      */       }
/*  733 */       return (int)Math.rint((float)bits / time_total(-1));
/*      */     }
/*      */ 
/*  736 */     if (this.seekable)
/*      */     {
/*  738 */       return (int)Math.rint((float)((this.offsets[(i + 1)] - this.dataoffsets[i]) * 8L) / time_total(i));
/*      */     }
/*      */ 
/*  742 */     if (this.vi[i].bitrate_nominal > 0) {
/*  743 */       return this.vi[i].bitrate_nominal;
/*      */     }
/*      */ 
/*  746 */     if (this.vi[i].bitrate_upper > 0) {
/*  747 */       if (this.vi[i].bitrate_lower > 0) {
/*  748 */         return (this.vi[i].bitrate_upper + this.vi[i].bitrate_lower) / 2;
/*      */       }
/*      */ 
/*  751 */       return this.vi[i].bitrate_upper;
/*      */     }
/*      */ 
/*  754 */     return -1;
/*      */   }
/*      */ 
/*      */   public int bitrate_instant()
/*      */   {
/*  763 */     int _link = this.seekable ? this.current_link : 0;
/*  764 */     if (this.samptrack == 0.0F)
/*  765 */       return -1;
/*  766 */     int ret = (int)(this.bittrack / this.samptrack * this.vi[_link].rate + 0.5D);
/*  767 */     this.bittrack = 0.0F;
/*  768 */     this.samptrack = 0.0F;
/*  769 */     return ret;
/*      */   }
/*      */ 
/*      */   public int serialnumber(int i) {
/*  773 */     if (i >= this.links)
/*  774 */       return -1;
/*  775 */     if ((!this.seekable) && (i >= 0))
/*  776 */       return serialnumber(-1);
/*  777 */     if (i < 0) {
/*  778 */       return this.current_serialno;
/*      */     }
/*      */ 
/*  781 */     return this.serialnos[i];
/*      */   }
/*      */ 
/*      */   public long raw_total(int i)
/*      */   {
/*  790 */     if ((!this.seekable) || (i >= this.links))
/*  791 */       return -1L;
/*  792 */     if (i < 0) {
/*  793 */       long acc = 0L;
/*  794 */       for (int j = 0; j < this.links; j++) {
/*  795 */         acc += raw_total(j);
/*      */       }
/*  797 */       return acc;
/*      */     }
/*      */ 
/*  800 */     return this.offsets[(i + 1)] - this.offsets[i];
/*      */   }
/*      */ 
/*      */   public long pcm_total(int i)
/*      */   {
/*  808 */     if ((!this.seekable) || (i >= this.links))
/*  809 */       return -1L;
/*  810 */     if (i < 0) {
/*  811 */       long acc = 0L;
/*  812 */       for (int j = 0; j < this.links; j++) {
/*  813 */         acc += pcm_total(j);
/*      */       }
/*  815 */       return acc;
/*      */     }
/*      */ 
/*  818 */     return this.pcmlengths[i];
/*      */   }
/*      */ 
/*      */   public float time_total(int i)
/*      */   {
/*  826 */     if ((!this.seekable) || (i >= this.links))
/*  827 */       return -1.0F;
/*  828 */     if (i < 0) {
/*  829 */       float acc = 0.0F;
/*  830 */       for (int j = 0; j < this.links; j++) {
/*  831 */         acc += time_total(j);
/*      */       }
/*  833 */       return acc;
/*      */     }
/*      */ 
/*  836 */     return (float)this.pcmlengths[i] / this.vi[i].rate;
/*      */   }
/*      */ 
/*      */   public int raw_seek(int pos)
/*      */   {
/*  849 */     if (!this.seekable)
/*  850 */       return -1;
/*  851 */     if ((pos < 0) || (pos > this.offsets[this.links]))
/*      */     {
/*  853 */       this.pcm_offset = -1L;
/*  854 */       decode_clear();
/*  855 */       return -1;
/*      */     }
/*      */ 
/*  859 */     this.pcm_offset = -1L;
/*  860 */     decode_clear();
/*      */ 
/*  863 */     seek_helper(pos);
/*      */ 
/*  871 */     switch (process_packet(1))
/*      */     {
/*      */     case 0:
/*  875 */       this.pcm_offset = pcm_total(-1);
/*  876 */       return 0;
/*      */     case -1:
/*  880 */       this.pcm_offset = -1L;
/*  881 */       decode_clear();
/*  882 */       return -1;
/*      */     }
/*      */ 
/*      */     while (true)
/*      */     {
/*  888 */       switch (process_packet(0))
/*      */       {
/*      */       case 0:
/*  893 */         return 0;
/*      */       case -1:
/*  897 */         this.pcm_offset = -1L;
/*  898 */         decode_clear();
/*  899 */         return -1;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public int pcm_seek(long pos)
/*      */   {
/*  917 */     int link = -1;
/*  918 */     long total = pcm_total(-1);
/*      */ 
/*  920 */     if (!this.seekable)
/*  921 */       return -1;
/*  922 */     if ((pos < 0L) || (pos > total))
/*      */     {
/*  924 */       this.pcm_offset = -1L;
/*  925 */       decode_clear();
/*  926 */       return -1;
/*      */     }
/*      */ 
/*  930 */     for (link = this.links - 1; link >= 0; link--) {
/*  931 */       total -= this.pcmlengths[link];
/*  932 */       if (pos >= total)
/*      */       {
/*      */         break;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  942 */     long target = pos - total;
/*  943 */     long end = this.offsets[(link + 1)];
/*  944 */     long begin = this.offsets[link];
/*  945 */     int best = (int)begin;
/*      */ 
/*  947 */     Page og = new Page();
/*  948 */     while (begin < end)
/*      */     {
/*      */       long bisect;
/*      */       long bisect;
/*  952 */       if (end - begin < 8500L) {
/*  953 */         bisect = begin;
/*      */       }
/*      */       else {
/*  956 */         bisect = (end + begin) / 2L;
/*      */       }
/*      */ 
/*  959 */       seek_helper(bisect);
/*  960 */       int ret = get_next_page(og, end - bisect);
/*      */ 
/*  962 */       if (ret == -1) {
/*  963 */         end = bisect;
/*      */       }
/*      */       else {
/*  966 */         long granulepos = og.granulepos();
/*  967 */         if (granulepos < target) {
/*  968 */           best = ret;
/*  969 */           begin = this.offset;
/*      */         }
/*      */         else {
/*  972 */           end = bisect;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  977 */     if (raw_seek(best) != 0)
/*      */     {
/*  979 */       this.pcm_offset = -1L;
/*  980 */       decode_clear();
/*  981 */       return -1;
/*      */     }
/*      */ 
/*  986 */     if (this.pcm_offset >= pos)
/*      */     {
/*  988 */       this.pcm_offset = -1L;
/*  989 */       decode_clear();
/*  990 */       return -1;
/*      */     }
/*  992 */     if (pos > pcm_total(-1))
/*      */     {
/*  994 */       this.pcm_offset = -1L;
/*  995 */       decode_clear();
/*  996 */       return -1;
/*      */     }
/*      */ 
/* 1001 */     while (this.pcm_offset < pos) {
/* 1002 */       int target = (int)(pos - this.pcm_offset);
/* 1003 */       float[][][] _pcm = new float[1][][];
/* 1004 */       int[] _index = new int[getInfo(-1).channels];
/* 1005 */       int samples = this.vd.synthesis_pcmout(_pcm, _index);
/*      */ 
/* 1007 */       if (samples > target)
/* 1008 */         samples = target;
/* 1009 */       this.vd.synthesis_read(samples);
/* 1010 */       this.pcm_offset += samples;
/*      */ 
/* 1012 */       if ((samples < target) && 
/* 1013 */         (process_packet(1) == 0)) {
/* 1014 */         this.pcm_offset = pcm_total(-1);
/*      */       }
/*      */     }
/* 1017 */     return 0;
/*      */   }
/*      */ 
/*      */   int time_seek(float seconds)
/*      */   {
/* 1031 */     int link = -1;
/* 1032 */     long pcm_total = pcm_total(-1);
/* 1033 */     float time_total = time_total(-1);
/*      */ 
/* 1035 */     if (!this.seekable)
/* 1036 */       return -1;
/* 1037 */     if ((seconds < 0.0F) || (seconds > time_total))
/*      */     {
/* 1039 */       this.pcm_offset = -1L;
/* 1040 */       decode_clear();
/* 1041 */       return -1;
/*      */     }
/*      */ 
/* 1045 */     for (link = this.links - 1; link >= 0; link--) {
/* 1046 */       pcm_total -= this.pcmlengths[link];
/* 1047 */       time_total -= time_total(link);
/* 1048 */       if (seconds >= time_total)
/*      */       {
/*      */         break;
/*      */       }
/*      */     }
/*      */ 
/* 1054 */     long target = ()((float)pcm_total + (seconds - time_total) * this.vi[link].rate);
/* 1055 */     return pcm_seek(target);
/*      */   }
/*      */ 
/*      */   public long raw_tell()
/*      */   {
/* 1068 */     return this.offset;
/*      */   }
/*      */ 
/*      */   public long pcm_tell()
/*      */   {
/* 1073 */     return this.pcm_offset;
/*      */   }
/*      */ 
/*      */   public float time_tell()
/*      */   {
/* 1080 */     int link = -1;
/* 1081 */     long pcm_total = 0L;
/* 1082 */     float time_total = 0.0F;
/*      */ 
/* 1084 */     if (this.seekable) {
/* 1085 */       pcm_total = pcm_total(-1);
/* 1086 */       time_total = time_total(-1);
/*      */ 
/* 1089 */       for (link = this.links - 1; link >= 0; link--) {
/* 1090 */         pcm_total -= this.pcmlengths[link];
/* 1091 */         time_total -= time_total(link);
/* 1092 */         if (this.pcm_offset >= pcm_total) {
/*      */           break;
/*      */         }
/*      */       }
/*      */     }
/* 1097 */     return time_total + (float)(this.pcm_offset - pcm_total) / this.vi[link].rate;
/*      */   }
/*      */ 
/*      */   public Info getInfo(int link)
/*      */   {
/* 1109 */     if (this.seekable) {
/* 1110 */       if (link < 0) {
/* 1111 */         if (this.decode_ready) {
/* 1112 */           return this.vi[this.current_link];
/*      */         }
/*      */ 
/* 1115 */         return null;
/*      */       }
/*      */ 
/* 1119 */       if (link >= this.links) {
/* 1120 */         return null;
/*      */       }
/*      */ 
/* 1123 */       return this.vi[link];
/*      */     }
/*      */ 
/* 1128 */     if (this.decode_ready) {
/* 1129 */       return this.vi[0];
/*      */     }
/*      */ 
/* 1132 */     return null;
/*      */   }
/*      */ 
/*      */   public Comment getComment(int link)
/*      */   {
/* 1138 */     if (this.seekable) {
/* 1139 */       if (link < 0) {
/* 1140 */         if (this.decode_ready) {
/* 1141 */           return this.vc[this.current_link];
/*      */         }
/*      */ 
/* 1144 */         return null;
/*      */       }
/*      */ 
/* 1148 */       if (link >= this.links) {
/* 1149 */         return null;
/*      */       }
/*      */ 
/* 1152 */       return this.vc[link];
/*      */     }
/*      */ 
/* 1157 */     if (this.decode_ready) {
/* 1158 */       return this.vc[0];
/*      */     }
/*      */ 
/* 1161 */     return null;
/*      */   }
/*      */ 
/*      */   int host_is_big_endian()
/*      */   {
/* 1167 */     return 1;
/*      */   }
/*      */ 
/*      */   int read(byte[] buffer, int length, int bigendianp, int word, int sgned, int[] bitstream)
/*      */   {
/* 1208 */     int host_endian = host_is_big_endian();
/* 1209 */     int index = 0;
/*      */     while (true)
/*      */     {
/* 1212 */       if (this.decode_ready)
/*      */       {
/* 1214 */         float[][][] _pcm = new float[1][][];
/* 1215 */         int[] _index = new int[getInfo(-1).channels];
/* 1216 */         int samples = this.vd.synthesis_pcmout(_pcm, _index);
/* 1217 */         float[][] pcm = _pcm[0];
/* 1218 */         if (samples != 0)
/*      */         {
/* 1220 */           int channels = getInfo(-1).channels;
/* 1221 */           int bytespersample = word * channels;
/* 1222 */           if (samples > length / bytespersample) {
/* 1223 */             samples = length / bytespersample;
/*      */           }
/*      */ 
/* 1228 */           if (word == 1) {
/* 1229 */             int off = sgned != 0 ? 0 : 128;
/* 1230 */             for (int j = 0; j < samples; j++)
/* 1231 */               for (int i = 0; i < channels; i++) {
/* 1232 */                 int val = (int)(pcm[i][(_index[i] + j)] * 128.0D + 0.5D);
/* 1233 */                 if (val > 127)
/* 1234 */                   val = 127;
/* 1235 */                 else if (val < -128)
/* 1236 */                   val = -128;
/* 1237 */                 buffer[(index++)] = ((byte)(val + off));
/*      */               }
/*      */           }
/*      */           else
/*      */           {
/* 1242 */             int off = sgned != 0 ? 0 : 32768;
/*      */ 
/* 1244 */             if (host_endian == bigendianp) {
/* 1245 */               if (sgned != 0) {
/* 1246 */                 for (int i = 0; i < channels; i++) {
/* 1247 */                   int src = _index[i];
/* 1248 */                   int dest = i;
/* 1249 */                   for (int j = 0; j < samples; j++) {
/* 1250 */                     int val = (int)(pcm[i][(src + j)] * 32768.0D + 0.5D);
/* 1251 */                     if (val > 32767)
/* 1252 */                       val = 32767;
/* 1253 */                     else if (val < -32768)
/* 1254 */                       val = -32768;
/* 1255 */                     buffer[dest] = ((byte)(val >>> 8));
/* 1256 */                     buffer[(dest + 1)] = ((byte)val);
/* 1257 */                     dest += channels * 2;
/*      */                   }
/*      */                 }
/*      */               }
/*      */               else {
/* 1262 */                 for (int i = 0; i < channels; i++) {
/* 1263 */                   float[] src = pcm[i];
/* 1264 */                   int dest = i;
/* 1265 */                   for (int j = 0; j < samples; j++) {
/* 1266 */                     int val = (int)(src[j] * 32768.0D + 0.5D);
/* 1267 */                     if (val > 32767)
/* 1268 */                       val = 32767;
/* 1269 */                     else if (val < -32768)
/* 1270 */                       val = -32768;
/* 1271 */                     buffer[dest] = ((byte)(val + off >>> 8));
/* 1272 */                     buffer[(dest + 1)] = ((byte)(val + off));
/* 1273 */                     dest += channels * 2;
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/* 1278 */             else if (bigendianp != 0) {
/* 1279 */               for (int j = 0; j < samples; j++) {
/* 1280 */                 for (int i = 0; i < channels; i++) {
/* 1281 */                   int val = (int)(pcm[i][j] * 32768.0D + 0.5D);
/* 1282 */                   if (val > 32767)
/* 1283 */                     val = 32767;
/* 1284 */                   else if (val < -32768)
/* 1285 */                     val = -32768;
/* 1286 */                   val += off;
/* 1287 */                   buffer[(index++)] = ((byte)(val >>> 8));
/* 1288 */                   buffer[(index++)] = ((byte)val);
/*      */                 }
/*      */               }
/*      */             }
/*      */             else
/*      */             {
/* 1294 */               for (int j = 0; j < samples; j++) {
/* 1295 */                 for (int i = 0; i < channels; i++) {
/* 1296 */                   int val = (int)(pcm[i][j] * 32768.0D + 0.5D);
/* 1297 */                   if (val > 32767)
/* 1298 */                     val = 32767;
/* 1299 */                   else if (val < -32768)
/* 1300 */                     val = -32768;
/* 1301 */                   val += off;
/* 1302 */                   buffer[(index++)] = ((byte)val);
/* 1303 */                   buffer[(index++)] = ((byte)(val >>> 8));
/*      */                 }
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/* 1310 */           this.vd.synthesis_read(samples);
/* 1311 */           this.pcm_offset += samples;
/* 1312 */           if (bitstream != null)
/* 1313 */             bitstream[0] = this.current_link;
/* 1314 */           return samples * bytespersample;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1319 */       switch (process_packet(1)) {
/*      */       case 0:
/* 1321 */         return 0;
/*      */       case -1:
/* 1323 */         return -1;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public Info[] getInfo()
/*      */   {
/* 1331 */     return this.vi;
/*      */   }
/*      */ 
/*      */   public Comment[] getComment() {
/* 1335 */     return this.vc;
/*      */   }
/*      */ 
/*      */   public void close() throws IOException {
/* 1339 */     this.datasource.close();
/*      */   }
/*      */ 
/*      */   class SeekableInputStream extends InputStream {
/* 1343 */     RandomAccessFile raf = null;
/* 1344 */     final String mode = "r";
/*      */ 
/*      */     SeekableInputStream(String file) throws IOException {
/* 1347 */       this.raf = new RandomAccessFile(file, "r");
/*      */     }
/*      */ 
/*      */     public int read() throws IOException {
/* 1351 */       return this.raf.read();
/*      */     }
/*      */ 
/*      */     public int read(byte[] buf) throws IOException {
/* 1355 */       return this.raf.read(buf);
/*      */     }
/*      */ 
/*      */     public int read(byte[] buf, int s, int len) throws IOException {
/* 1359 */       return this.raf.read(buf, s, len);
/*      */     }
/*      */ 
/*      */     public long skip(long n) throws IOException {
/* 1363 */       return this.raf.skipBytes((int)n);
/*      */     }
/*      */ 
/*      */     public long getLength() throws IOException {
/* 1367 */       return this.raf.length();
/*      */     }
/*      */ 
/*      */     public long tell() throws IOException {
/* 1371 */       return this.raf.getFilePointer();
/*      */     }
/*      */ 
/*      */     public int available() throws IOException {
/* 1375 */       return this.raf.length() == this.raf.getFilePointer() ? 0 : 1;
/*      */     }
/*      */ 
/*      */     public void close() throws IOException {
/* 1379 */       this.raf.close();
/*      */     }
/*      */ 
/*      */     public synchronized void mark(int m) {
/*      */     }
/*      */ 
/*      */     public synchronized void reset() throws IOException {
/*      */     }
/*      */ 
/*      */     public boolean markSupported() {
/* 1389 */       return false;
/*      */     }
/*      */ 
/*      */     public void seek(long pos) throws IOException {
/* 1393 */       this.raf.seek(pos);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.VorbisFile
 * JD-Core Version:    0.6.2
 */