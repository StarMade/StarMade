/*   1:    */package com.eaio.uuid;
/*   2:    */
/*   3:    */import com.eaio.util.lang.Hex;
/*   4:    */import java.io.BufferedReader;
/*   5:    */import java.io.File;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.io.InputStreamReader;
/*   9:    */import java.io.OutputStream;
/*  10:    */import java.net.InetAddress;
/*  11:    */import java.net.NetworkInterface;
/*  12:    */import java.net.SocketException;
/*  13:    */import java.net.UnknownHostException;
/*  14:    */import java.util.Enumeration;
/*  15:    */
/*  73:    */public final class UUIDGen
/*  74:    */{
/*  75: 75 */  private static long lastTime = -9223372036854775808L;
/*  76:    */  
/*  80: 80 */  private static String macAddress = null;
/*  81:    */  
/*  85: 85 */  private static long clockSeqAndNode = -9223372036854775808L;
/*  86:    */  
/*  87:    */  static
/*  88:    */  {
/*  89:    */    try {
/*  90: 90 */      Class.forName("java.net.InterfaceAddress");
/*  91: 91 */      macAddress = Class.forName("com.eaio.uuid.UUIDGen$HardwareAddressLookup").newInstance().toString();
/*  92:    */    }
/*  93:    */    catch (ExceptionInInitializerError err) {}catch (ClassNotFoundException ex) {}catch (LinkageError err) {}catch (IllegalAccessException ex) {}catch (InstantiationException ex) {}catch (SecurityException ex) {}
/*  94:    */    
/* 113:113 */    if (macAddress == null)
/* 114:    */    {
/* 115:115 */      Process p = null;
/* 116:116 */      BufferedReader in = null;
/* 117:    */      try
/* 118:    */      {
/* 119:119 */        String osname = System.getProperty("os.name", "");String osver = System.getProperty("os.version", "");
/* 120:    */        
/* 121:121 */        if (osname.startsWith("Windows")) {
/* 122:122 */          p = Runtime.getRuntime().exec(new String[] { "ipconfig", "/all" }, null);
/* 126:    */        }
/* 127:127 */        else if ((osname.startsWith("Solaris")) || (osname.startsWith("SunOS")))
/* 128:    */        {
/* 129:129 */          if (osver.startsWith("5.11")) {
/* 130:130 */            p = Runtime.getRuntime().exec(new String[] { "dladm", "show-phys", "-m" }, null);
/* 131:    */          }
/* 132:    */          else
/* 133:    */          {
/* 134:134 */            String hostName = getFirstLineOfCommand(new String[] { "uname", "-n" });
/* 135:135 */            if (hostName != null) {
/* 136:136 */              p = Runtime.getRuntime().exec(new String[] { "/usr/sbin/arp", hostName }, null);
/* 137:    */            }
/* 138:    */            
/* 139:    */          }
/* 140:    */          
/* 141:    */        }
/* 142:142 */        else if (new File("/usr/sbin/lanscan").exists()) {
/* 143:143 */          p = Runtime.getRuntime().exec(new String[] { "/usr/sbin/lanscan" }, null);
/* 145:    */        }
/* 146:146 */        else if (new File("/sbin/ifconfig").exists()) {
/* 147:147 */          p = Runtime.getRuntime().exec(new String[] { "/sbin/ifconfig", "-a" }, null);
/* 148:    */        }
/* 149:    */        
/* 151:151 */        if (p != null) {
/* 152:152 */          in = new BufferedReader(new InputStreamReader(p.getInputStream()), 128);
/* 153:    */          
/* 154:154 */          String l = null;
/* 155:155 */          while ((l = in.readLine()) != null) {
/* 156:156 */            macAddress = MACAddressParser.parse(l);
/* 157:157 */            if ((macAddress != null) && (Hex.parseShort(macAddress) != 255))
/* 158:    */            {
/* 159:159 */              break;
/* 161:    */            }
/* 162:    */            
/* 163:    */          }
/* 164:    */          
/* 165:    */        }
/* 166:    */        
/* 168:    */      }
/* 169:    */      catch (SecurityException ex) {}catch (IOException ex) {}finally
/* 170:    */      {
/* 172:172 */        if (p != null) {
/* 173:173 */          if (in != null) {
/* 174:    */            try {
/* 175:175 */              in.close();
/* 176:    */            }
/* 177:    */            catch (IOException ex) {}
/* 178:    */          }
/* 179:    */          
/* 180:    */          try
/* 181:    */          {
/* 182:182 */            p.getErrorStream().close();
/* 183:    */          }
/* 184:    */          catch (IOException ex) {}
/* 185:    */          
/* 186:    */          try
/* 187:    */          {
/* 188:188 */            p.getOutputStream().close();
/* 189:    */          }
/* 190:    */          catch (IOException ex) {}
/* 191:    */          
/* 193:193 */          p.destroy();
/* 194:    */        }
/* 195:    */      }
/* 196:    */    }
/* 197:    */    
/* 199:199 */    if (macAddress != null) {
/* 200:200 */      clockSeqAndNode |= Hex.parseLong(macAddress);
/* 201:    */    } else {
/* 202:    */      try
/* 203:    */      {
/* 204:204 */        byte[] local = InetAddress.getLocalHost().getAddress();
/* 205:205 */        clockSeqAndNode |= local[0] << 24 & 0xFF000000;
/* 206:206 */        clockSeqAndNode |= local[1] << 16 & 0xFF0000;
/* 207:207 */        clockSeqAndNode |= local[2] << 8 & 0xFF00;
/* 208:208 */        clockSeqAndNode |= local[3] & 0xFF;
/* 209:    */      }
/* 210:    */      catch (UnknownHostException ex) {
/* 211:211 */        clockSeqAndNode |= (Math.random() * 2147483647.0D);
/* 212:    */      }
/* 213:    */    }
/* 214:    */    
/* 217:217 */    clockSeqAndNode |= (Math.random() * 16383.0D) << 48;
/* 218:    */  }
/* 219:    */  
/* 226:    */  public static long getClockSeqAndNode()
/* 227:    */  {
/* 228:228 */    return clockSeqAndNode;
/* 229:    */  }
/* 230:    */  
/* 237:    */  public static long newTime()
/* 238:    */  {
/* 239:239 */    return createTime(System.currentTimeMillis());
/* 240:    */  }
/* 241:    */  
/* 254:    */  public static synchronized long createTime(long currentTimeMillis)
/* 255:    */  {
/* 256:256 */    long timeMillis = currentTimeMillis * 10000L + 122192928000000000L;
/* 257:    */    
/* 258:258 */    if (timeMillis > lastTime) {
/* 259:259 */      lastTime = timeMillis;
/* 260:    */    }
/* 261:    */    else {
/* 262:262 */      timeMillis = ++lastTime;
/* 263:    */    }
/* 264:    */    
/* 267:267 */    long time = timeMillis << 32;
/* 268:    */    
/* 271:271 */    time |= (timeMillis & 0x0) >> 16;
/* 272:    */    
/* 275:275 */    time |= 0x1000 | timeMillis >> 48 & 0xFFF;
/* 276:    */    
/* 277:277 */    return time;
/* 278:    */  }
/* 279:    */  
/* 285:    */  public static String getMACAddress()
/* 286:    */  {
/* 287:287 */    return macAddress;
/* 288:    */  }
/* 289:    */  
/* 296:    */  static String getFirstLineOfCommand(String... commands)
/* 297:    */    throws IOException
/* 298:    */  {
/* 299:299 */    Process p = null;
/* 300:300 */    BufferedReader reader = null;
/* 301:    */    try
/* 302:    */    {
/* 303:303 */      p = Runtime.getRuntime().exec(commands);
/* 304:304 */      reader = new BufferedReader(new InputStreamReader(p.getInputStream()), 128);
/* 305:    */      
/* 307:307 */      return reader.readLine();
/* 308:    */    }
/* 309:    */    finally {
/* 310:310 */      if (p != null) {
/* 311:311 */        if (reader != null) {
/* 312:    */          try {
/* 313:313 */            reader.close();
/* 314:    */          }
/* 315:    */          catch (IOException ex) {}
/* 316:    */        }
/* 317:    */        
/* 318:    */        try
/* 319:    */        {
/* 320:320 */          p.getErrorStream().close();
/* 321:    */        }
/* 322:    */        catch (IOException ex) {}
/* 323:    */        
/* 324:    */        try
/* 325:    */        {
/* 326:326 */          p.getOutputStream().close();
/* 327:    */        }
/* 328:    */        catch (IOException ex) {}
/* 329:    */        
/* 331:331 */        p.destroy();
/* 332:    */      }
/* 333:    */    }
/* 334:    */  }
/* 335:    */  
/* 343:    */  static class HardwareAddressLookup
/* 344:    */  {
/* 345:    */    public String toString()
/* 346:    */    {
/* 347:347 */      String out = null;
/* 348:    */      try {
/* 349:349 */        Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
/* 350:350 */        if (ifs != null) {
/* 351:351 */          while (ifs.hasMoreElements()) {
/* 352:352 */            NetworkInterface iface = (NetworkInterface)ifs.nextElement();
/* 353:353 */            byte[] hardware = iface.getHardwareAddress();
/* 354:354 */            if ((hardware != null) && (hardware.length == 6) && (hardware[1] != -1))
/* 355:    */            {
/* 356:356 */              out = Hex.append(new StringBuilder(36), hardware).toString();
/* 357:357 */              break;
/* 358:    */            }
/* 359:    */          }
/* 360:    */        }
/* 361:    */      }
/* 362:    */      catch (SocketException ex) {}
/* 363:    */      
/* 365:365 */      return out;
/* 366:    */    }
/* 367:    */  }
/* 368:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.UUIDGen
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */