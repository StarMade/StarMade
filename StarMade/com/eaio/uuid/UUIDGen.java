package com.eaio.uuid;

import com.eaio.util.lang.Hex;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public final class UUIDGen
{
  private static long lastTime = -9223372036854775808L;
  private static String macAddress = null;
  private static long clockSeqAndNode = -9223372036854775808L;
  
  public static long getClockSeqAndNode()
  {
    return clockSeqAndNode;
  }
  
  public static long newTime()
  {
    return createTime(System.currentTimeMillis());
  }
  
  public static synchronized long createTime(long currentTimeMillis)
  {
    long timeMillis = currentTimeMillis * 10000L + 122192928000000000L;
    if (timeMillis > lastTime) {
      lastTime = timeMillis;
    } else {
      timeMillis = ++lastTime;
    }
    long time = timeMillis << 32;
    time |= (timeMillis & 0x0) >> 16;
    time |= 0x1000 | timeMillis >> 48 & 0xFFF;
    return time;
  }
  
  public static String getMACAddress()
  {
    return macAddress;
  }
  
  static String getFirstLineOfCommand(String... commands)
    throws IOException
  {
    Process local_p = null;
    BufferedReader reader = null;
    try
    {
      local_p = Runtime.getRuntime().exec(commands);
      reader = new BufferedReader(new InputStreamReader(local_p.getInputStream()), 128);
      String str = reader.readLine();
      return str;
    }
    finally
    {
      if (local_p != null)
      {
        if (reader != null) {
          try
          {
            reader.close();
          }
          catch (IOException local_ex1) {}
        }
        try
        {
          local_p.getErrorStream().close();
        }
        catch (IOException local_ex1) {}
        try
        {
          local_p.getOutputStream().close();
        }
        catch (IOException local_ex1) {}
        local_p.destroy();
      }
    }
  }
  
  static
  {
    try
    {
      Class.forName("java.net.InterfaceAddress");
      macAddress = Class.forName("com.eaio.uuid.UUIDGen$HardwareAddressLookup").newInstance().toString();
    }
    catch (ExceptionInInitializerError err) {}catch (ClassNotFoundException err) {}catch (LinkageError err) {}catch (IllegalAccessException err) {}catch (InstantiationException err) {}catch (SecurityException err) {}
    if (macAddress == null)
    {
      Process err = null;
      BufferedReader local_in = null;
      try
      {
        String osname = System.getProperty("os.name", "");
        String osver = System.getProperty("os.version", "");
        if (osname.startsWith("Windows")) {
          err = Runtime.getRuntime().exec(new String[] { "ipconfig", "/all" }, null);
        } else if ((osname.startsWith("Solaris")) || (osname.startsWith("SunOS")))
        {
          if (osver.startsWith("5.11"))
          {
            err = Runtime.getRuntime().exec(new String[] { "dladm", "show-phys", "-m" }, null);
          }
          else
          {
            String hostName = getFirstLineOfCommand(new String[] { "uname", "-n" });
            if (hostName != null) {
              err = Runtime.getRuntime().exec(new String[] { "/usr/sbin/arp", hostName }, null);
            }
          }
        }
        else if (new File("/usr/sbin/lanscan").exists()) {
          err = Runtime.getRuntime().exec(new String[] { "/usr/sbin/lanscan" }, null);
        } else if (new File("/sbin/ifconfig").exists()) {
          err = Runtime.getRuntime().exec(new String[] { "/sbin/ifconfig", "-a" }, null);
        }
        if (err != null)
        {
          local_in = new BufferedReader(new InputStreamReader(err.getInputStream()), 128);
          String hostName = null;
          while ((hostName = local_in.readLine()) != null)
          {
            macAddress = MACAddressParser.parse(hostName);
            if ((macAddress != null) && (Hex.parseShort(macAddress) != 255)) {
              break;
            }
          }
        }
      }
      catch (SecurityException osname) {}catch (IOException osname) {}finally
      {
        if (err != null)
        {
          if (local_in != null) {
            try
            {
              local_in.close();
            }
            catch (IOException local_ex) {}
          }
          try
          {
            err.getErrorStream().close();
          }
          catch (IOException local_ex) {}
          try
          {
            err.getOutputStream().close();
          }
          catch (IOException local_ex) {}
          err.destroy();
        }
      }
    }
    if (macAddress != null) {
      clockSeqAndNode |= Hex.parseLong(macAddress);
    } else {
      try
      {
        byte[] err = InetAddress.getLocalHost().getAddress();
        clockSeqAndNode |= err[0] << 24 & 0xFF000000;
        clockSeqAndNode |= err[1] << 16 & 0xFF0000;
        clockSeqAndNode |= err[2] << 8 & 0xFF00;
        clockSeqAndNode |= err[3] & 0xFF;
      }
      catch (UnknownHostException err)
      {
        clockSeqAndNode |= (Math.random() * 2147483647.0D);
      }
    }
    clockSeqAndNode |= (Math.random() * 16383.0D) << 48;
  }
  
  static class HardwareAddressLookup
  {
    public String toString()
    {
      String out = null;
      try
      {
        Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
        if (ifs != null) {
          while (ifs.hasMoreElements())
          {
            NetworkInterface iface = (NetworkInterface)ifs.nextElement();
            byte[] hardware = iface.getHardwareAddress();
            if ((hardware != null) && (hardware.length == 6) && (hardware[1] != -1))
            {
              out = Hex.append(new StringBuilder(36), hardware).toString();
              break;
            }
          }
        }
      }
      catch (SocketException ifs) {}
      return out;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.eaio.uuid.UUIDGen
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */