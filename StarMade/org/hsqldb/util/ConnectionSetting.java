package org.hsqldb.util;

import java.io.Serializable;

public class ConnectionSetting
  implements Serializable
{
  private String name;
  private String driver;
  private String url;
  private String user;
  private String field_1774;
  
  String getName()
  {
    return this.name;
  }
  
  String getDriver()
  {
    return this.driver;
  }
  
  String getUrl()
  {
    return this.url;
  }
  
  String getUser()
  {
    return this.user;
  }
  
  String getPassword()
  {
    return this.field_1774;
  }
  
  private ConnectionSetting() {}
  
  ConnectionSetting(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.name = paramString1;
    this.driver = paramString2;
    this.url = paramString3;
    this.user = paramString4;
    this.field_1774 = paramString5;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ConnectionSetting)) {
      return false;
    }
    ConnectionSetting localConnectionSetting = (ConnectionSetting)paramObject;
    if (getName() == localConnectionSetting.getName()) {
      return true;
    }
    if (getName() == null) {
      return false;
    }
    return getName().trim().equals(localConnectionSetting.getName().trim());
  }
  
  public int hashCode()
  {
    return getName() == null ? 0 : getName().trim().hashCode();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.util.ConnectionSetting
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */