/*     */ package com.eaio.uuid;
/*     */ 
/*     */ import com.eaio.util.lang.Hex;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.SocketException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ public final class UUIDGen
/*     */ {
/*  75 */   private static long lastTime = -9223372036854775808L;
/*     */ 
/*  80 */   private static String macAddress = null;
/*     */ 
/*  85 */   private static long clockSeqAndNode = -9223372036854775808L;
/*     */ 
/*     */   public static long getClockSeqAndNode()
/*     */   {
/* 228 */     return clockSeqAndNode;
/*     */   }
/*     */ 
/*     */   public static long newTime()
/*     */   {
/* 239 */     return createTime(System.currentTimeMillis());
/*     */   }
/*     */ 
/*     */   public static synchronized long createTime(long currentTimeMillis)
/*     */   {
/* 256 */     long timeMillis = currentTimeMillis * 10000L + 122192928000000000L;
/*     */ 
/* 258 */     if (timeMillis > lastTime) {
/* 259 */       lastTime = timeMillis;
/*     */     }
/*     */     else {
/* 262 */       timeMillis = ++lastTime;
/*     */     }
/*     */ 
/* 267 */     long time = timeMillis << 32;
/*     */ 
/* 271 */     time |= (timeMillis & 0x0) >> 16;
/*     */ 
/* 275 */     time |= 0x1000 | timeMillis >> 48 & 0xFFF;
/*     */ 
/* 277 */     return time;
/*     */   }
/*     */ 
/*     */   public static String getMACAddress()
/*     */   {
/* 287 */     return macAddress;
/*     */   }
/*     */ 
/*     */   static String getFirstLineOfCommand(String[] commands)
/*     */     throws IOException
/*     */   {
/* 299 */     Process p = null;
/* 300 */     BufferedReader reader = null;
/*     */     try
/*     */     {
/* 303 */       p = Runtime.getRuntime().exec(commands);
/* 304 */       reader = new BufferedReader(new InputStreamReader(p.getInputStream()), 128);
/*     */ 
/* 307 */       return reader.readLine();
/*     */     }
/*     */     finally {
/* 310 */       if (p != null) {
/* 311 */         if (reader != null)
/*     */           try {
/* 313 */             reader.close();
/*     */           }
/*     */           catch (IOException ex)
/*     */           {
/*     */           }
/*     */         try
/*     */         {
/* 320 */           p.getErrorStream().close();
/*     */         }
/*     */         catch (IOException ex)
/*     */         {
/*     */         }
/*     */         try {
/* 326 */           p.getOutputStream().close();
/*     */         }
/*     */         catch (IOException ex)
/*     */         {
/*     */         }
/* 331 */         p.destroy();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  90 */       Class.forName("java.net.InterfaceAddress");
/*  91 */       macAddress = Class.forName("com.eaio.uuid.UUIDGen$HardwareAddressLookup").newInstance().toString();
/*     */     }
/*     */     catch (ExceptionInInitializerError err)
/*     */     {
/*     */     }
/*     */     catch (ClassNotFoundException ex)
/*     */     {
/*     */     }
/*     */     catch (LinkageError err)
/*     */     {
/*     */     }
/*     */     catch (IllegalAccessException ex)
/*     */     {
/*     */     }
/*     */     catch (InstantiationException ex)
/*     */     {
/*     */     }
/*     */     catch (SecurityException ex)
/*     */     {
/*     */     }
/*     */ 
/* 113 */     if (macAddress == null)
/*     */     {
/* 115 */       Process p = null;
/* 116 */       BufferedReader in = null;
/*     */       try
/*     */       {
/* 119 */         String osname = System.getProperty("os.name", ""); String osver = System.getProperty("os.version", "");
/*     */ 
/* 121 */         if (osname.startsWith("Windows")) {
/* 122 */           p = Runtime.getRuntime().exec(new String[] { "ipconfig", "/all" }, null);
/*     */         }
/* 127 */         else if ((osname.startsWith("Solaris")) || (osname.startsWith("SunOS")))
/*     */         {
/* 129 */           if (osver.startsWith("5.11")) {
/* 130 */             p = Runtime.getRuntime().exec(new String[] { "dladm", "show-phys", "-m" }, null);
/*     */           }
/*     */           else
/*     */           {
/* 134 */             String hostName = getFirstLineOfCommand(new String[] { "uname", "-n" });
/* 135 */             if (hostName != null) {
/* 136 */               p = Runtime.getRuntime().exec(new String[] { "/usr/sbin/arp", hostName }, null);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/* 142 */         else if (new File("/usr/sbin/lanscan").exists()) {
/* 143 */           p = Runtime.getRuntime().exec(new String[] { "/usr/sbin/lanscan" }, null);
/*     */         }
/* 146 */         else if (new File("/sbin/ifconfig").exists()) {
/* 147 */           p = Runtime.getRuntime().exec(new String[] { "/sbin/ifconfig", "-a" }, null);
/*     */         }
/*     */ 
/* 151 */         if (p != null) {
/* 152 */           in = new BufferedReader(new InputStreamReader(p.getInputStream()), 128);
/*     */ 
/* 154 */           String l = null;
/* 155 */           while ((l = in.readLine()) != null) {
/* 156 */             macAddress = MACAddressParser.parse(l);
/* 157 */             if ((macAddress != null) && (Hex.parseShort(macAddress) != 255))
/*     */             {
/* 159 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (SecurityException ex)
/*     */       {
/*     */       }
/*     */       catch (IOException ex)
/*     */       {
/*     */       }
/*     */       finally
/*     */       {
/* 172 */         if (p != null) {
/* 173 */           if (in != null)
/*     */             try {
/* 175 */               in.close();
/*     */             }
/*     */             catch (IOException ex)
/*     */             {
/*     */             }
/*     */           try
/*     */           {
/* 182 */             p.getErrorStream().close();
/*     */           }
/*     */           catch (IOException ex)
/*     */           {
/*     */           }
/*     */           try {
/* 188 */             p.getOutputStream().close();
/*     */           }
/*     */           catch (IOException ex)
/*     */           {
/*     */           }
/* 193 */           p.destroy();
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 199 */     if (macAddress != null)
/* 200 */       clockSeqAndNode |= Hex.parseLong(macAddress);
/*     */     else {
/*     */       try
/*     */       {
/* 204 */         byte[] local = InetAddress.getLocalHost().getAddress();
/* 205 */         clockSeqAndNode |= local[0] << 24 & 0xFF000000;
/* 206 */         clockSeqAndNode |= local[1] << 16 & 0xFF0000;
/* 207 */         clockSeqAndNode |= local[2] << 8 & 0xFF00;
/* 208 */         clockSeqAndNode |= local[3] & 0xFF;
/*     */       }
/*     */       catch (UnknownHostException ex) {
/* 211 */         clockSeqAndNode |= ()(Math.random() * 2147483647.0D);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 217 */     clockSeqAndNode |= ()(Math.random() * 16383.0D) << 48;
/*     */   }
/*     */ 
/*     */   static class HardwareAddressLookup
/*     */   {
/*     */     public String toString()
/*     */     {
/* 347 */       String out = null;
/*     */       try {
/* 349 */         Enumeration ifs = NetworkInterface.getNetworkInterfaces();
/* 350 */         if (ifs != null) {
/* 351 */           while (ifs.hasMoreElements()) {
/* 352 */             NetworkInterface iface = (NetworkInterface)ifs.nextElement();
/* 353 */             byte[] hardware = iface.getHardwareAddress();
/* 354 */             if ((hardware != null) && (hardware.length == 6) && (hardware[1] != -1))
/*     */             {
/* 356 */               out = Hex.append(new StringBuilder(36), hardware).toString();
/* 357 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (SocketException ex)
/*     */       {
/*     */       }
/* 365 */       return out;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.UUIDGen
 * JD-Core Version:    0.6.2
 */