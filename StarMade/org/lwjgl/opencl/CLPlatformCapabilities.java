/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.util.Set;
/*  4:   */import java.util.StringTokenizer;
/*  5:   */
/*  8:   */public class CLPlatformCapabilities
/*  9:   */{
/* 10:   */  public final int majorVersion;
/* 11:   */  public final int minorVersion;
/* 12:   */  public final boolean OpenCL11;
/* 13:   */  public final boolean OpenCL12;
/* 14:   */  public final boolean CL_KHR_d3d10_sharing;
/* 15:   */  public final boolean CL_KHR_gl_event;
/* 16:   */  public final boolean CL_KHR_gl_sharing;
/* 17:   */  public final boolean CL_KHR_icd;
/* 18:   */  
/* 19:   */  public CLPlatformCapabilities(CLPlatform platform)
/* 20:   */  {
/* 21:21 */    String extensionList = platform.getInfoString(2308);
/* 22:22 */    String version = platform.getInfoString(2305);
/* 23:23 */    if (!version.startsWith("OpenCL ")) {
/* 24:24 */      throw new RuntimeException("Invalid OpenCL version string: " + version);
/* 25:   */    }
/* 26:   */    try {
/* 27:27 */      StringTokenizer tokenizer = new StringTokenizer(version.substring(7), ". ");
/* 28:   */      
/* 29:29 */      this.majorVersion = Integer.parseInt(tokenizer.nextToken());
/* 30:30 */      this.minorVersion = Integer.parseInt(tokenizer.nextToken());
/* 31:   */      
/* 32:32 */      this.OpenCL11 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (1 <= this.minorVersion)));
/* 33:33 */      this.OpenCL12 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (2 <= this.minorVersion)));
/* 34:   */    } catch (RuntimeException e) {
/* 35:35 */      throw new RuntimeException("The major and/or minor OpenCL version \"" + version + "\" is malformed: " + e.getMessage());
/* 36:   */    }
/* 37:   */    
/* 38:38 */    Set<String> extensions = APIUtil.getExtensions(extensionList);
/* 39:39 */    this.CL_KHR_d3d10_sharing = extensions.contains("cl_khr_d3d10_sharing");
/* 40:40 */    this.CL_KHR_gl_event = ((extensions.contains("cl_khr_gl_event")) && (CLCapabilities.CL_KHR_gl_event));
/* 41:41 */    this.CL_KHR_gl_sharing = ((extensions.contains("cl_khr_gl_sharing")) && (CLCapabilities.CL_KHR_gl_sharing));
/* 42:42 */    this.CL_KHR_icd = ((extensions.contains("cl_khr_icd")) && (CLCapabilities.CL_KHR_icd));
/* 43:   */  }
/* 44:   */  
/* 45:   */  public int getMajorVersion() {
/* 46:46 */    return this.majorVersion;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public int getMinorVersion() {
/* 50:50 */    return this.minorVersion;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public String toString() {
/* 54:54 */    StringBuilder buf = new StringBuilder();
/* 55:   */    
/* 56:56 */    buf.append("OpenCL ").append(this.majorVersion).append('.').append(this.minorVersion);
/* 57:   */    
/* 58:58 */    buf.append(" - Extensions: ");
/* 59:59 */    if (this.CL_KHR_d3d10_sharing) buf.append("cl_khr_d3d10_sharing ");
/* 60:60 */    if (this.CL_KHR_gl_event) buf.append("cl_khr_gl_event ");
/* 61:61 */    if (this.CL_KHR_gl_sharing) buf.append("cl_khr_gl_sharing ");
/* 62:62 */    if (this.CL_KHR_icd) { buf.append("cl_khr_icd ");
/* 63:   */    }
/* 64:64 */    return buf.toString();
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLPlatformCapabilities
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */