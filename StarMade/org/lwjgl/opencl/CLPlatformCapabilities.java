package org.lwjgl.opencl;

import java.util.Set;
import java.util.StringTokenizer;

public class CLPlatformCapabilities
{
  public final int majorVersion;
  public final int minorVersion;
  public final boolean OpenCL11;
  public final boolean OpenCL12;
  public final boolean CL_KHR_d3d10_sharing;
  public final boolean CL_KHR_gl_event;
  public final boolean CL_KHR_gl_sharing;
  public final boolean CL_KHR_icd;
  
  public CLPlatformCapabilities(CLPlatform platform)
  {
    String extensionList = platform.getInfoString(2308);
    String version = platform.getInfoString(2305);
    if (!version.startsWith("OpenCL ")) {
      throw new RuntimeException("Invalid OpenCL version string: " + version);
    }
    try
    {
      StringTokenizer tokenizer = new StringTokenizer(version.substring(7), ". ");
      this.majorVersion = Integer.parseInt(tokenizer.nextToken());
      this.minorVersion = Integer.parseInt(tokenizer.nextToken());
      this.OpenCL11 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (1 <= this.minorVersion)));
      this.OpenCL12 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (2 <= this.minorVersion)));
    }
    catch (RuntimeException tokenizer)
    {
      throw new RuntimeException("The major and/or minor OpenCL version \"" + version + "\" is malformed: " + tokenizer.getMessage());
    }
    Set<String> tokenizer = APIUtil.getExtensions(extensionList);
    this.CL_KHR_d3d10_sharing = tokenizer.contains("cl_khr_d3d10_sharing");
    this.CL_KHR_gl_event = ((tokenizer.contains("cl_khr_gl_event")) && (CLCapabilities.CL_KHR_gl_event));
    this.CL_KHR_gl_sharing = ((tokenizer.contains("cl_khr_gl_sharing")) && (CLCapabilities.CL_KHR_gl_sharing));
    this.CL_KHR_icd = ((tokenizer.contains("cl_khr_icd")) && (CLCapabilities.CL_KHR_icd));
  }
  
  public int getMajorVersion()
  {
    return this.majorVersion;
  }
  
  public int getMinorVersion()
  {
    return this.minorVersion;
  }
  
  public String toString()
  {
    StringBuilder buf = new StringBuilder();
    buf.append("OpenCL ").append(this.majorVersion).append('.').append(this.minorVersion);
    buf.append(" - Extensions: ");
    if (this.CL_KHR_d3d10_sharing) {
      buf.append("cl_khr_d3d10_sharing ");
    }
    if (this.CL_KHR_gl_event) {
      buf.append("cl_khr_gl_event ");
    }
    if (this.CL_KHR_gl_sharing) {
      buf.append("cl_khr_gl_sharing ");
    }
    if (this.CL_KHR_icd) {
      buf.append("cl_khr_icd ");
    }
    return buf.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLPlatformCapabilities
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */