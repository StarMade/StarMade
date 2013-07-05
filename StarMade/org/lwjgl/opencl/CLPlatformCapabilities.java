/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.util.Set;
/*    */ import java.util.StringTokenizer;
/*    */ 
/*    */ public class CLPlatformCapabilities
/*    */ {
/*    */   public final int majorVersion;
/*    */   public final int minorVersion;
/*    */   public final boolean OpenCL11;
/*    */   public final boolean OpenCL12;
/*    */   public final boolean CL_KHR_d3d10_sharing;
/*    */   public final boolean CL_KHR_gl_event;
/*    */   public final boolean CL_KHR_gl_sharing;
/*    */   public final boolean CL_KHR_icd;
/*    */ 
/*    */   public CLPlatformCapabilities(CLPlatform platform)
/*    */   {
/* 21 */     String extensionList = platform.getInfoString(2308);
/* 22 */     String version = platform.getInfoString(2305);
/* 23 */     if (!version.startsWith("OpenCL "))
/* 24 */       throw new RuntimeException("Invalid OpenCL version string: " + version);
/*    */     try
/*    */     {
/* 27 */       StringTokenizer tokenizer = new StringTokenizer(version.substring(7), ". ");
/*    */ 
/* 29 */       this.majorVersion = Integer.parseInt(tokenizer.nextToken());
/* 30 */       this.minorVersion = Integer.parseInt(tokenizer.nextToken());
/*    */ 
/* 32 */       this.OpenCL11 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (1 <= this.minorVersion)));
/* 33 */       this.OpenCL12 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (2 <= this.minorVersion)));
/*    */     } catch (RuntimeException e) {
/* 35 */       throw new RuntimeException("The major and/or minor OpenCL version \"" + version + "\" is malformed: " + e.getMessage());
/*    */     }
/*    */ 
/* 38 */     Set extensions = APIUtil.getExtensions(extensionList);
/* 39 */     this.CL_KHR_d3d10_sharing = extensions.contains("cl_khr_d3d10_sharing");
/* 40 */     this.CL_KHR_gl_event = ((extensions.contains("cl_khr_gl_event")) && (CLCapabilities.CL_KHR_gl_event));
/* 41 */     this.CL_KHR_gl_sharing = ((extensions.contains("cl_khr_gl_sharing")) && (CLCapabilities.CL_KHR_gl_sharing));
/* 42 */     this.CL_KHR_icd = ((extensions.contains("cl_khr_icd")) && (CLCapabilities.CL_KHR_icd));
/*    */   }
/*    */ 
/*    */   public int getMajorVersion() {
/* 46 */     return this.majorVersion;
/*    */   }
/*    */ 
/*    */   public int getMinorVersion() {
/* 50 */     return this.minorVersion;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 54 */     StringBuilder buf = new StringBuilder();
/*    */ 
/* 56 */     buf.append("OpenCL ").append(this.majorVersion).append('.').append(this.minorVersion);
/*    */ 
/* 58 */     buf.append(" - Extensions: ");
/* 59 */     if (this.CL_KHR_d3d10_sharing) buf.append("cl_khr_d3d10_sharing ");
/* 60 */     if (this.CL_KHR_gl_event) buf.append("cl_khr_gl_event ");
/* 61 */     if (this.CL_KHR_gl_sharing) buf.append("cl_khr_gl_sharing ");
/* 62 */     if (this.CL_KHR_icd) buf.append("cl_khr_icd ");
/*    */ 
/* 64 */     return buf.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLPlatformCapabilities
 * JD-Core Version:    0.6.2
 */