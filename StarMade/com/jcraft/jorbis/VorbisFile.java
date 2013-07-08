package com.jcraft.jorbis;

import [[F;
import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class VorbisFile
{
  static final int CHUNKSIZE = 8500;
  static final int SEEK_SET = 0;
  static final int SEEK_CUR = 1;
  static final int SEEK_END = 2;
  static final int OV_FALSE = -1;
  static final int OV_EOF = -2;
  static final int OV_HOLE = -3;
  static final int OV_EREAD = -128;
  static final int OV_EFAULT = -129;
  static final int OV_EIMPL = -130;
  static final int OV_EINVAL = -131;
  static final int OV_ENOTVORBIS = -132;
  static final int OV_EBADHEADER = -133;
  static final int OV_EVERSION = -134;
  static final int OV_ENOTAUDIO = -135;
  static final int OV_EBADPACKET = -136;
  static final int OV_EBADLINK = -137;
  static final int OV_ENOSEEK = -138;
  InputStream datasource;
  boolean seekable = false;
  long offset;
  long end;
  SyncState field_2110 = new SyncState();
  int links;
  long[] offsets;
  long[] dataoffsets;
  int[] serialnos;
  long[] pcmlengths;
  Info[] field_2111;
  Comment[] field_2112;
  long pcm_offset;
  boolean decode_ready = false;
  int current_serialno;
  int current_link;
  float bittrack;
  float samptrack;
  StreamState field_2113 = new StreamState();
  DspState field_2114 = new DspState();
  Block field_2115 = new Block(this.field_2114);
  
  public VorbisFile(String paramString)
    throws JOrbisException
  {
    SeekableInputStream localSeekableInputStream = null;
    try
    {
      localSeekableInputStream = new SeekableInputStream(paramString);
      int i = open(localSeekableInputStream, null, 0);
      if (i == -1) {
        throw new JOrbisException("VorbisFile: open return -1");
      }
    }
    catch (Exception localException)
    {
      throw new JOrbisException("VorbisFile: " + localException.toString());
    }
    finally
    {
      if (localSeekableInputStream != null) {
        try
        {
          localSeekableInputStream.close();
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
      }
    }
  }
  
  public VorbisFile(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt)
    throws JOrbisException
  {
    int i = open(paramInputStream, paramArrayOfByte, paramInt);
    if (i == -1) {}
  }
  
  private int get_data()
  {
    int i = this.field_2110.buffer(8500);
    byte[] arrayOfByte = this.field_2110.data;
    int j = 0;
    try
    {
      j = this.datasource.read(arrayOfByte, i, 8500);
    }
    catch (Exception localException)
    {
      return -128;
    }
    this.field_2110.wrote(j);
    if (j == -1) {
      j = 0;
    }
    return j;
  }
  
  private void seek_helper(long paramLong)
  {
    fseek(this.datasource, paramLong, 0);
    this.offset = paramLong;
    this.field_2110.reset();
  }
  
  private int get_next_page(Page paramPage, long paramLong)
  {
    if (paramLong > 0L) {
      paramLong += this.offset;
    }
    int i;
    do
    {
      for (;;)
      {
        if ((paramLong > 0L) && (this.offset >= paramLong)) {
          return -1;
        }
        i = this.field_2110.pageseek(paramPage);
        if (i >= 0) {
          break;
        }
        this.offset -= i;
      }
      if (i != 0) {
        break;
      }
      if (paramLong == 0L) {
        return -1;
      }
      j = get_data();
      if (j == 0) {
        return -2;
      }
    } while (j >= 0);
    return -128;
    int j = (int)this.offset;
    this.offset += i;
    return j;
  }
  
  private int get_prev_page(Page paramPage)
    throws JOrbisException
  {
    long l = this.offset;
    int j = -1;
    while (j == -1)
    {
      l -= 8500L;
      if (l < 0L) {
        l = 0L;
      }
      seek_helper(l);
      while (this.offset < l + 8500L)
      {
        i = get_next_page(paramPage, l + 8500L - this.offset);
        if (i == -128) {
          return -128;
        }
        if (i < 0)
        {
          if (j != -1) {
            break;
          }
          throw new JOrbisException();
        }
        j = i;
      }
    }
    seek_helper(j);
    int i = get_next_page(paramPage, 8500L);
    if (i < 0) {
      return -129;
    }
    return j;
  }
  
  int bisect_forward_serialno(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2)
  {
    long l1 = paramLong3;
    long l2 = paramLong3;
    Page localPage = new Page();
    while (paramLong2 < l1)
    {
      long l3;
      if (l1 - paramLong2 < 8500L) {
        l3 = paramLong2;
      } else {
        l3 = (paramLong2 + l1) / 2L;
      }
      seek_helper(l3);
      i = get_next_page(localPage, -1L);
      if (i == -128) {
        return -128;
      }
      if ((i < 0) || (localPage.serialno() != paramInt1))
      {
        l1 = l3;
        if (i >= 0) {
          l2 = i;
        }
      }
      else
      {
        paramLong2 = i + localPage.header_len + localPage.body_len;
      }
    }
    seek_helper(l2);
    int i = get_next_page(localPage, -1L);
    if (i == -128) {
      return -128;
    }
    if ((paramLong2 >= paramLong3) || (i == -1))
    {
      this.links = (paramInt2 + 1);
      this.offsets = new long[paramInt2 + 2];
      this.offsets[(paramInt2 + 1)] = paramLong2;
    }
    else
    {
      i = bisect_forward_serialno(l2, this.offset, paramLong3, localPage.serialno(), paramInt2 + 1);
      if (i == -128) {
        return -128;
      }
    }
    this.offsets[paramInt2] = paramLong1;
    return 0;
  }
  
  int fetch_headers(Info paramInfo, Comment paramComment, int[] paramArrayOfInt, Page paramPage)
  {
    Page localPage = new Page();
    Packet localPacket = new Packet();
    if (paramPage == null)
    {
      int i = get_next_page(localPage, 8500L);
      if (i == -128) {
        return -128;
      }
      if (i < 0) {
        return -132;
      }
      paramPage = localPage;
    }
    if (paramArrayOfInt != null) {
      paramArrayOfInt[0] = paramPage.serialno();
    }
    this.field_2113.init(paramPage.serialno());
    paramInfo.init();
    paramComment.init();
    int j = 0;
    while (j < 3)
    {
      this.field_2113.pagein(paramPage);
      while (j < 3)
      {
        int k = this.field_2113.packetout(localPacket);
        if (k == 0) {
          break;
        }
        if (k == -1)
        {
          paramInfo.clear();
          paramComment.clear();
          this.field_2113.clear();
          return -1;
        }
        if (paramInfo.synthesis_headerin(paramComment, localPacket) != 0)
        {
          paramInfo.clear();
          paramComment.clear();
          this.field_2113.clear();
          return -1;
        }
        j++;
      }
      if ((j < 3) && (get_next_page(paramPage, 1L) < 0))
      {
        paramInfo.clear();
        paramComment.clear();
        this.field_2113.clear();
        return -1;
      }
    }
    return 0;
  }
  
  void prefetch_all_headers(Info paramInfo, Comment paramComment, int paramInt)
    throws JOrbisException
  {
    Page localPage = new Page();
    this.field_2111 = new Info[this.links];
    this.field_2112 = new Comment[this.links];
    this.dataoffsets = new long[this.links];
    this.pcmlengths = new long[this.links];
    this.serialnos = new int[this.links];
    for (int j = 0; j < this.links; j++)
    {
      if ((paramInfo != null) && (paramComment != null) && (j == 0))
      {
        this.field_2111[j] = paramInfo;
        this.field_2112[j] = paramComment;
        this.dataoffsets[j] = paramInt;
      }
      else
      {
        seek_helper(this.offsets[j]);
        this.field_2111[j] = new Info();
        this.field_2112[j] = new Comment();
        if (fetch_headers(this.field_2111[j], this.field_2112[j], null, null) == -1)
        {
          this.dataoffsets[j] = -1L;
        }
        else
        {
          this.dataoffsets[j] = this.offset;
          this.field_2113.clear();
        }
      }
      long l = this.offsets[(j + 1)];
      seek_helper(l);
      do
      {
        int i = get_prev_page(localPage);
        if (i == -1)
        {
          this.field_2111[j].clear();
          this.field_2112[j].clear();
          break;
        }
      } while (localPage.granulepos() == -1L);
      this.serialnos[j] = localPage.serialno();
      this.pcmlengths[j] = localPage.granulepos();
    }
  }
  
  int make_decode_ready()
  {
    if (this.decode_ready) {
      System.exit(1);
    }
    this.field_2114.synthesis_init(this.field_2111[0]);
    this.field_2115.init(this.field_2114);
    this.decode_ready = true;
    return 0;
  }
  
  int open_seekable()
    throws JOrbisException
  {
    Info localInfo = new Info();
    Comment localComment = new Comment();
    Page localPage = new Page();
    int[] arrayOfInt = new int[1];
    int j = fetch_headers(localInfo, localComment, arrayOfInt, null);
    int i = arrayOfInt[0];
    int k = (int)this.offset;
    this.field_2113.clear();
    if (j == -1) {
      return -1;
    }
    this.seekable = true;
    fseek(this.datasource, 0L, 2);
    this.offset = ftell(this.datasource);
    long l = this.offset;
    l = get_prev_page(localPage);
    if (localPage.serialno() != i)
    {
      if (bisect_forward_serialno(0L, 0L, l + 1L, i, 0) < 0)
      {
        clear();
        return -128;
      }
    }
    else if (bisect_forward_serialno(0L, l, l + 1L, i, 0) < 0)
    {
      clear();
      return -128;
    }
    prefetch_all_headers(localInfo, localComment, k);
    return raw_seek(0);
  }
  
  int open_nonseekable()
  {
    this.links = 1;
    this.field_2111 = new Info[this.links];
    this.field_2111[0] = new Info();
    this.field_2112 = new Comment[this.links];
    this.field_2112[0] = new Comment();
    int[] arrayOfInt = new int[1];
    if (fetch_headers(this.field_2111[0], this.field_2112[0], arrayOfInt, null) == -1) {
      return -1;
    }
    this.current_serialno = arrayOfInt[0];
    make_decode_ready();
    return 0;
  }
  
  void decode_clear()
  {
    this.field_2113.clear();
    this.field_2114.clear();
    this.field_2115.clear();
    this.decode_ready = false;
    this.bittrack = 0.0F;
    this.samptrack = 0.0F;
  }
  
  int process_packet(int paramInt)
  {
    Page localPage = new Page();
    for (;;)
    {
      if (this.decode_ready)
      {
        Packet localPacket = new Packet();
        int j = this.field_2113.packetout(localPacket);
        if (j > 0)
        {
          long l = localPacket.granulepos;
          if (this.field_2115.synthesis(localPacket) == 0)
          {
            int m = this.field_2114.synthesis_pcmout(null, null);
            this.field_2114.synthesis_blockin(this.field_2115);
            this.samptrack += this.field_2114.synthesis_pcmout(null, null) - m;
            this.bittrack += localPacket.bytes * 8;
            if ((l != -1L) && (localPacket.e_o_s == 0))
            {
              m = this.seekable ? this.current_link : 0;
              int n = this.field_2114.synthesis_pcmout(null, null);
              l -= n;
              for (int i1 = 0; i1 < m; i1++) {
                l += this.pcmlengths[i1];
              }
              this.pcm_offset = l;
            }
            return 1;
          }
        }
      }
      if (paramInt == 0) {
        return 0;
      }
      if (get_next_page(localPage, -1L) < 0) {
        return 0;
      }
      this.bittrack += localPage.header_len * 8;
      if ((this.decode_ready) && (this.current_serialno != localPage.serialno())) {
        decode_clear();
      }
      if (!this.decode_ready)
      {
        int i;
        if (this.seekable)
        {
          this.current_serialno = localPage.serialno();
          for (i = 0; i < this.links; i++) {
            if (this.serialnos[i] == this.current_serialno) {
              break;
            }
          }
          if (i == this.links) {
            return -1;
          }
          this.current_link = i;
          this.field_2113.init(this.current_serialno);
          this.field_2113.reset();
        }
        else
        {
          int[] arrayOfInt = new int[1];
          int k = fetch_headers(this.field_2111[0], this.field_2112[0], arrayOfInt, localPage);
          this.current_serialno = arrayOfInt[0];
          if (k != 0) {
            return k;
          }
          this.current_link += 1;
          i = 0;
        }
        make_decode_ready();
      }
      this.field_2113.pagein(localPage);
    }
  }
  
  int clear()
  {
    this.field_2115.clear();
    this.field_2114.clear();
    this.field_2113.clear();
    if ((this.field_2111 != null) && (this.links != 0))
    {
      for (int i = 0; i < this.links; i++)
      {
        this.field_2111[i].clear();
        this.field_2112[i].clear();
      }
      this.field_2111 = null;
      this.field_2112 = null;
    }
    if (this.dataoffsets != null) {
      this.dataoffsets = null;
    }
    if (this.pcmlengths != null) {
      this.pcmlengths = null;
    }
    if (this.serialnos != null) {
      this.serialnos = null;
    }
    if (this.offsets != null) {
      this.offsets = null;
    }
    this.field_2110.clear();
    return 0;
  }
  
  static int fseek(InputStream paramInputStream, long paramLong, int paramInt)
  {
    if ((paramInputStream instanceof SeekableInputStream))
    {
      SeekableInputStream localSeekableInputStream = (SeekableInputStream)paramInputStream;
      try
      {
        if (paramInt == 0) {
          localSeekableInputStream.seek(paramLong);
        } else if (paramInt == 2) {
          localSeekableInputStream.seek(localSeekableInputStream.getLength() - paramLong);
        }
      }
      catch (Exception localException2) {}
      return 0;
    }
    try
    {
      if (paramInt == 0) {
        paramInputStream.reset();
      }
      paramInputStream.skip(paramLong);
    }
    catch (Exception localException1)
    {
      return -1;
    }
    return 0;
  }
  
  static long ftell(InputStream paramInputStream)
  {
    try
    {
      if ((paramInputStream instanceof SeekableInputStream))
      {
        SeekableInputStream localSeekableInputStream = (SeekableInputStream)paramInputStream;
        return localSeekableInputStream.tell();
      }
    }
    catch (Exception localException) {}
    return 0L;
  }
  
  int open(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt)
    throws JOrbisException
  {
    return open_callbacks(paramInputStream, paramArrayOfByte, paramInt);
  }
  
  int open_callbacks(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt)
    throws JOrbisException
  {
    this.datasource = paramInputStream;
    this.field_2110.init();
    if (paramArrayOfByte != null)
    {
      int j = this.field_2110.buffer(paramInt);
      System.arraycopy(paramArrayOfByte, 0, this.field_2110.data, j, paramInt);
      this.field_2110.wrote(paramInt);
    }
    int i;
    if ((paramInputStream instanceof SeekableInputStream)) {
      i = open_seekable();
    } else {
      i = open_nonseekable();
    }
    if (i != 0)
    {
      this.datasource = null;
      clear();
    }
    return i;
  }
  
  public int streams()
  {
    return this.links;
  }
  
  public boolean seekable()
  {
    return this.seekable;
  }
  
  public int bitrate(int paramInt)
  {
    if (paramInt >= this.links) {
      return -1;
    }
    if ((!this.seekable) && (paramInt != 0)) {
      return bitrate(0);
    }
    if (paramInt < 0)
    {
      long l = 0L;
      for (int i = 0; i < this.links; i++) {
        l += (this.offsets[(i + 1)] - this.dataoffsets[i]) * 8L;
      }
      return (int)Math.rint((float)l / time_total(-1));
    }
    if (this.seekable) {
      return (int)Math.rint((float)((this.offsets[(paramInt + 1)] - this.dataoffsets[paramInt]) * 8L) / time_total(paramInt));
    }
    if (this.field_2111[paramInt].bitrate_nominal > 0) {
      return this.field_2111[paramInt].bitrate_nominal;
    }
    if (this.field_2111[paramInt].bitrate_upper > 0)
    {
      if (this.field_2111[paramInt].bitrate_lower > 0) {
        return (this.field_2111[paramInt].bitrate_upper + this.field_2111[paramInt].bitrate_lower) / 2;
      }
      return this.field_2111[paramInt].bitrate_upper;
    }
    return -1;
  }
  
  public int bitrate_instant()
  {
    int i = this.seekable ? this.current_link : 0;
    if (this.samptrack == 0.0F) {
      return -1;
    }
    int j = (int)(this.bittrack / this.samptrack * this.field_2111[i].rate + 0.5D);
    this.bittrack = 0.0F;
    this.samptrack = 0.0F;
    return j;
  }
  
  public int serialnumber(int paramInt)
  {
    if (paramInt >= this.links) {
      return -1;
    }
    if ((!this.seekable) && (paramInt >= 0)) {
      return serialnumber(-1);
    }
    if (paramInt < 0) {
      return this.current_serialno;
    }
    return this.serialnos[paramInt];
  }
  
  public long raw_total(int paramInt)
  {
    if ((!this.seekable) || (paramInt >= this.links)) {
      return -1L;
    }
    if (paramInt < 0)
    {
      long l = 0L;
      for (int i = 0; i < this.links; i++) {
        l += raw_total(i);
      }
      return l;
    }
    return this.offsets[(paramInt + 1)] - this.offsets[paramInt];
  }
  
  public long pcm_total(int paramInt)
  {
    if ((!this.seekable) || (paramInt >= this.links)) {
      return -1L;
    }
    if (paramInt < 0)
    {
      long l = 0L;
      for (int i = 0; i < this.links; i++) {
        l += pcm_total(i);
      }
      return l;
    }
    return this.pcmlengths[paramInt];
  }
  
  public float time_total(int paramInt)
  {
    if ((!this.seekable) || (paramInt >= this.links)) {
      return -1.0F;
    }
    if (paramInt < 0)
    {
      float f = 0.0F;
      for (int i = 0; i < this.links; i++) {
        f += time_total(i);
      }
      return f;
    }
    return (float)this.pcmlengths[paramInt] / this.field_2111[paramInt].rate;
  }
  
  public int raw_seek(int paramInt)
  {
    if (!this.seekable) {
      return -1;
    }
    if ((paramInt < 0) || (paramInt > this.offsets[this.links]))
    {
      this.pcm_offset = -1L;
      decode_clear();
      return -1;
    }
    this.pcm_offset = -1L;
    decode_clear();
    seek_helper(paramInt);
    switch (process_packet(1))
    {
    case 0: 
      this.pcm_offset = pcm_total(-1);
      return 0;
    case -1: 
      this.pcm_offset = -1L;
      decode_clear();
      return -1;
    }
    for (;;)
    {
      switch (process_packet(0))
      {
      case 0: 
        return 0;
      case -1: 
        this.pcm_offset = -1L;
        decode_clear();
        return -1;
      }
    }
  }
  
  public int pcm_seek(long paramLong)
  {
    int i = -1;
    long l1 = pcm_total(-1);
    if (!this.seekable) {
      return -1;
    }
    if ((paramLong < 0L) || (paramLong > l1))
    {
      this.pcm_offset = -1L;
      decode_clear();
      return -1;
    }
    for (i = this.links - 1; i >= 0; i--)
    {
      l1 -= this.pcmlengths[i];
      if (paramLong >= l1) {
        break;
      }
    }
    long l2 = paramLong - l1;
    long l3 = this.offsets[(i + 1)];
    long l4 = this.offsets[i];
    int m = (int)l4;
    Page localPage = new Page();
    while (l4 < l3)
    {
      long l5;
      if (l3 - l4 < 8500L) {
        l5 = l4;
      } else {
        l5 = (l3 + l4) / 2L;
      }
      seek_helper(l5);
      int n = get_next_page(localPage, l3 - l5);
      if (n == -1)
      {
        l3 = l5;
      }
      else
      {
        long l6 = localPage.granulepos();
        if (l6 < l2)
        {
          m = n;
          l4 = this.offset;
        }
        else
        {
          l3 = l5;
        }
      }
    }
    if (raw_seek(m) != 0)
    {
      this.pcm_offset = -1L;
      decode_clear();
      return -1;
    }
    if (this.pcm_offset >= paramLong)
    {
      this.pcm_offset = -1L;
      decode_clear();
      return -1;
    }
    if (paramLong > pcm_total(-1))
    {
      this.pcm_offset = -1L;
      decode_clear();
      return -1;
    }
    while (this.pcm_offset < paramLong)
    {
      int j = (int)(paramLong - this.pcm_offset);
      float[][][] arrayOfFloat = new float[1][][];
      int[] arrayOfInt = new int[getInfo(-1).channels];
      int k = this.field_2114.synthesis_pcmout(arrayOfFloat, arrayOfInt);
      Object localObject = arrayOfFloat[0];
      if (k > j) {
        k = j;
      }
      this.field_2114.synthesis_read(k);
      this.pcm_offset += k;
      if ((k < j) && (process_packet(1) == 0)) {
        this.pcm_offset = pcm_total(-1);
      }
    }
    return 0;
  }
  
  int time_seek(float paramFloat)
  {
    int i = -1;
    long l1 = pcm_total(-1);
    float f = time_total(-1);
    if (!this.seekable) {
      return -1;
    }
    if ((paramFloat < 0.0F) || (paramFloat > f))
    {
      this.pcm_offset = -1L;
      decode_clear();
      return -1;
    }
    for (i = this.links - 1; i >= 0; i--)
    {
      l1 -= this.pcmlengths[i];
      f -= time_total(i);
      if (paramFloat >= f) {
        break;
      }
    }
    long l2 = ((float)l1 + (paramFloat - f) * this.field_2111[i].rate);
    return pcm_seek(l2);
  }
  
  public long raw_tell()
  {
    return this.offset;
  }
  
  public long pcm_tell()
  {
    return this.pcm_offset;
  }
  
  public float time_tell()
  {
    int i = -1;
    long l = 0L;
    float f = 0.0F;
    if (this.seekable)
    {
      l = pcm_total(-1);
      f = time_total(-1);
      for (i = this.links - 1; i >= 0; i--)
      {
        l -= this.pcmlengths[i];
        f -= time_total(i);
        if (this.pcm_offset >= l) {
          break;
        }
      }
    }
    return f + (float)(this.pcm_offset - l) / this.field_2111[i].rate;
  }
  
  public Info getInfo(int paramInt)
  {
    if (this.seekable)
    {
      if (paramInt < 0)
      {
        if (this.decode_ready) {
          return this.field_2111[this.current_link];
        }
        return null;
      }
      if (paramInt >= this.links) {
        return null;
      }
      return this.field_2111[paramInt];
    }
    if (this.decode_ready) {
      return this.field_2111[0];
    }
    return null;
  }
  
  public Comment getComment(int paramInt)
  {
    if (this.seekable)
    {
      if (paramInt < 0)
      {
        if (this.decode_ready) {
          return this.field_2112[this.current_link];
        }
        return null;
      }
      if (paramInt >= this.links) {
        return null;
      }
      return this.field_2112[paramInt];
    }
    if (this.decode_ready) {
      return this.field_2112[0];
    }
    return null;
  }
  
  int host_is_big_endian()
  {
    return 1;
  }
  
  int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    int i = host_is_big_endian();
    int j = 0;
    for (;;)
    {
      if (this.decode_ready)
      {
        float[][][] arrayOfFloat = new float[1][][];
        int[] arrayOfInt = new int[getInfo(-1).channels];
        int k = this.field_2114.synthesis_pcmout(arrayOfFloat, arrayOfInt);
        [[F local[[F = arrayOfFloat[0];
        if (k != 0)
        {
          int m = getInfo(-1).channels;
          int n = paramInt3 * m;
          if (k > paramInt1 / n) {
            k = paramInt1 / n;
          }
          int i2;
          int i3;
          int i4;
          int i1;
          if (paramInt3 == 1)
          {
            i2 = paramInt4 != 0 ? 0 : 128;
            for (i3 = 0; i3 < k; i3++) {
              for (i4 = 0; i4 < m; i4++)
              {
                i1 = (int)(local[[F[i4][(arrayOfInt[i4] + i3)] * 128.0D + 0.5D);
                if (i1 > 127) {
                  i1 = 127;
                } else if (i1 < -128) {
                  i1 = -128;
                }
                paramArrayOfByte[(j++)] = ((byte)(i1 + i2));
              }
            }
          }
          else
          {
            i2 = paramInt4 != 0 ? 0 : 32768;
            if (i == paramInt2)
            {
              int i6;
              int i7;
              if (paramInt4 != 0) {
                for (i3 = 0; i3 < m; i3++)
                {
                  i4 = arrayOfInt[i3];
                  i6 = i3;
                  for (i7 = 0; i7 < k; i7++)
                  {
                    i1 = (int)(local[[F[i3][(i4 + i7)] * 32768.0D + 0.5D);
                    if (i1 > 32767) {
                      i1 = 32767;
                    } else if (i1 < -32768) {
                      i1 = -32768;
                    }
                    paramArrayOfByte[i6] = ((byte)(i1 >>> 8));
                    paramArrayOfByte[(i6 + 1)] = ((byte)i1);
                    i6 += m * 2;
                  }
                }
              } else {
                for (i3 = 0; i3 < m; i3++)
                {
                  Object localObject = local[[F[i3];
                  i6 = i3;
                  for (i7 = 0; i7 < k; i7++)
                  {
                    i1 = (int)(localObject[i7] * 32768.0D + 0.5D);
                    if (i1 > 32767) {
                      i1 = 32767;
                    } else if (i1 < -32768) {
                      i1 = -32768;
                    }
                    paramArrayOfByte[i6] = ((byte)(i1 + i2 >>> 8));
                    paramArrayOfByte[(i6 + 1)] = ((byte)(i1 + i2));
                    i6 += m * 2;
                  }
                }
              }
            }
            else
            {
              int i5;
              if (paramInt2 != 0) {
                for (i3 = 0; i3 < k; i3++) {
                  for (i5 = 0; i5 < m; i5++)
                  {
                    i1 = (int)(local[[F[i5][i3] * 32768.0D + 0.5D);
                    if (i1 > 32767) {
                      i1 = 32767;
                    } else if (i1 < -32768) {
                      i1 = -32768;
                    }
                    i1 += i2;
                    paramArrayOfByte[(j++)] = ((byte)(i1 >>> 8));
                    paramArrayOfByte[(j++)] = ((byte)i1);
                  }
                }
              } else {
                for (i3 = 0; i3 < k; i3++) {
                  for (i5 = 0; i5 < m; i5++)
                  {
                    i1 = (int)(local[[F[i5][i3] * 32768.0D + 0.5D);
                    if (i1 > 32767) {
                      i1 = 32767;
                    } else if (i1 < -32768) {
                      i1 = -32768;
                    }
                    i1 += i2;
                    paramArrayOfByte[(j++)] = ((byte)i1);
                    paramArrayOfByte[(j++)] = ((byte)(i1 >>> 8));
                  }
                }
              }
            }
          }
          this.field_2114.synthesis_read(k);
          this.pcm_offset += k;
          if (paramArrayOfInt != null) {
            paramArrayOfInt[0] = this.current_link;
          }
          return k * n;
        }
      }
      switch (process_packet(1))
      {
      case 0: 
        return 0;
      case -1: 
        return -1;
      }
    }
  }
  
  public Info[] getInfo()
  {
    return this.field_2111;
  }
  
  public Comment[] getComment()
  {
    return this.field_2112;
  }
  
  public void close()
    throws IOException
  {
    this.datasource.close();
  }
  
  class SeekableInputStream
    extends InputStream
  {
    RandomAccessFile raf = null;
    final String mode = "r";
    
    private SeekableInputStream() {}
    
    SeekableInputStream(String paramString)
      throws IOException
    {
      this.raf = new RandomAccessFile(paramString, "r");
    }
    
    public int read()
      throws IOException
    {
      return this.raf.read();
    }
    
    public int read(byte[] paramArrayOfByte)
      throws IOException
    {
      return this.raf.read(paramArrayOfByte);
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      return this.raf.read(paramArrayOfByte, paramInt1, paramInt2);
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      return this.raf.skipBytes((int)paramLong);
    }
    
    public long getLength()
      throws IOException
    {
      return this.raf.length();
    }
    
    public long tell()
      throws IOException
    {
      return this.raf.getFilePointer();
    }
    
    public int available()
      throws IOException
    {
      return this.raf.length() == this.raf.getFilePointer() ? 0 : 1;
    }
    
    public void close()
      throws IOException
    {
      this.raf.close();
    }
    
    public synchronized void mark(int paramInt) {}
    
    public synchronized void reset()
      throws IOException
    {}
    
    public boolean markSupported()
    {
      return false;
    }
    
    public void seek(long paramLong)
      throws IOException
    {
      this.raf.seek(paramLong);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.VorbisFile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */