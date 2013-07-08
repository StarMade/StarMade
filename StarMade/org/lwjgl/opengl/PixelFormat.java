package org.lwjgl.opengl;

public final class PixelFormat
  implements PixelFormatLWJGL
{
  private int bpp;
  private int alpha;
  private int depth;
  private int stencil;
  private int samples;
  private int colorSamples;
  private int num_aux_buffers;
  private int accum_bpp;
  private int accum_alpha;
  private boolean stereo;
  private boolean floating_point;
  private boolean floating_point_packed;
  private boolean sRGB;
  
  public PixelFormat()
  {
    this(0, 8, 0);
  }
  
  public PixelFormat(int alpha, int depth, int stencil)
  {
    this(alpha, depth, stencil, 0);
  }
  
  public PixelFormat(int alpha, int depth, int stencil, int samples)
  {
    this(0, alpha, depth, stencil, samples);
  }
  
  public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples)
  {
    this(bpp, alpha, depth, stencil, samples, 0, 0, 0, false);
  }
  
  public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples, int num_aux_buffers, int accum_bpp, int accum_alpha, boolean stereo)
  {
    this(bpp, alpha, depth, stencil, samples, num_aux_buffers, accum_bpp, accum_alpha, stereo, false);
  }
  
  public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples, int num_aux_buffers, int accum_bpp, int accum_alpha, boolean stereo, boolean floating_point)
  {
    this.bpp = bpp;
    this.alpha = alpha;
    this.depth = depth;
    this.stencil = stencil;
    this.samples = samples;
    this.num_aux_buffers = num_aux_buffers;
    this.accum_bpp = accum_bpp;
    this.accum_alpha = accum_alpha;
    this.stereo = stereo;
    this.floating_point = floating_point;
    this.floating_point_packed = false;
    this.sRGB = false;
  }
  
  private PixelFormat(PixelFormat local_pf)
  {
    this.bpp = local_pf.bpp;
    this.alpha = local_pf.alpha;
    this.depth = local_pf.depth;
    this.stencil = local_pf.stencil;
    this.samples = local_pf.samples;
    this.colorSamples = local_pf.colorSamples;
    this.num_aux_buffers = local_pf.num_aux_buffers;
    this.accum_bpp = local_pf.accum_bpp;
    this.accum_alpha = local_pf.accum_alpha;
    this.stereo = local_pf.stereo;
    this.floating_point = local_pf.floating_point;
    this.floating_point_packed = local_pf.floating_point_packed;
    this.sRGB = local_pf.sRGB;
  }
  
  public int getBitsPerPixel()
  {
    return this.bpp;
  }
  
  public PixelFormat withBitsPerPixel(int bpp)
  {
    if (bpp < 0) {
      throw new IllegalArgumentException("Invalid number of bits per pixel specified: " + bpp);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.bpp = bpp;
    return local_pf;
  }
  
  public int getAlphaBits()
  {
    return this.alpha;
  }
  
  public PixelFormat withAlphaBits(int alpha)
  {
    if (alpha < 0) {
      throw new IllegalArgumentException("Invalid number of alpha bits specified: " + alpha);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.alpha = alpha;
    return local_pf;
  }
  
  public int getDepthBits()
  {
    return this.depth;
  }
  
  public PixelFormat withDepthBits(int depth)
  {
    if (depth < 0) {
      throw new IllegalArgumentException("Invalid number of depth bits specified: " + depth);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.depth = depth;
    return local_pf;
  }
  
  public int getStencilBits()
  {
    return this.stencil;
  }
  
  public PixelFormat withStencilBits(int stencil)
  {
    if (stencil < 0) {
      throw new IllegalArgumentException("Invalid number of stencil bits specified: " + stencil);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.stencil = stencil;
    return local_pf;
  }
  
  public int getSamples()
  {
    return this.samples;
  }
  
  public PixelFormat withSamples(int samples)
  {
    if (samples < 0) {
      throw new IllegalArgumentException("Invalid number of samples specified: " + samples);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.samples = samples;
    return local_pf;
  }
  
  public PixelFormat withCoverageSamples(int colorSamples)
  {
    return withCoverageSamples(colorSamples, this.samples);
  }
  
  public PixelFormat withCoverageSamples(int colorSamples, int coverageSamples)
  {
    if ((coverageSamples < 0) || (colorSamples < 0) || ((coverageSamples == 0) && (0 < colorSamples)) || (coverageSamples < colorSamples)) {
      throw new IllegalArgumentException("Invalid number of coverage samples specified: " + coverageSamples + " - " + colorSamples);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.samples = coverageSamples;
    local_pf.colorSamples = colorSamples;
    return local_pf;
  }
  
  public int getAuxBuffers()
  {
    return this.num_aux_buffers;
  }
  
  public PixelFormat withAuxBuffers(int num_aux_buffers)
  {
    if (num_aux_buffers < 0) {
      throw new IllegalArgumentException("Invalid number of auxiliary buffers specified: " + num_aux_buffers);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.num_aux_buffers = num_aux_buffers;
    return local_pf;
  }
  
  public int getAccumulationBitsPerPixel()
  {
    return this.accum_bpp;
  }
  
  public PixelFormat withAccumulationBitsPerPixel(int accum_bpp)
  {
    if (accum_bpp < 0) {
      throw new IllegalArgumentException("Invalid number of bits per pixel in the accumulation buffer specified: " + accum_bpp);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.accum_bpp = accum_bpp;
    return local_pf;
  }
  
  public int getAccumulationAlpha()
  {
    return this.accum_alpha;
  }
  
  public PixelFormat withAccumulationAlpha(int accum_alpha)
  {
    if (accum_alpha < 0) {
      throw new IllegalArgumentException("Invalid number of alpha bits in the accumulation buffer specified: " + accum_alpha);
    }
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.accum_alpha = accum_alpha;
    return local_pf;
  }
  
  public boolean isStereo()
  {
    return this.stereo;
  }
  
  public PixelFormat withStereo(boolean stereo)
  {
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.stereo = stereo;
    return local_pf;
  }
  
  public boolean isFloatingPoint()
  {
    return this.floating_point;
  }
  
  public PixelFormat withFloatingPoint(boolean floating_point)
  {
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.floating_point = floating_point;
    if (floating_point) {
      local_pf.floating_point_packed = false;
    }
    return local_pf;
  }
  
  public PixelFormat withFloatingPointPacked(boolean floating_point_packed)
  {
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.floating_point_packed = floating_point_packed;
    if (floating_point_packed) {
      local_pf.floating_point = false;
    }
    return local_pf;
  }
  
  public boolean isSRGB()
  {
    return this.sRGB;
  }
  
  public PixelFormat withSRGB(boolean sRGB)
  {
    PixelFormat local_pf = new PixelFormat(this);
    local_pf.sRGB = sRGB;
    return local_pf;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.PixelFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */