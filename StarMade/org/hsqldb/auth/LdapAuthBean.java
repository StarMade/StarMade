package org.hsqldb.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import org.hsqldb.lib.FrameworkLogger;

public class LdapAuthBean
  implements AuthFunctionBean
{
  private static FrameworkLogger logger = FrameworkLogger.getLog(LdapAuthBean.class);
  private Integer ldapPort;
  private String ldapHost;
  private String principalTemplate;
  private String saslRealm;
  private String parentDn;
  private Pattern roleSchemaValuePattern;
  private Pattern accessValuePattern;
  private String initialContextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
  private boolean tls;
  private String mechanism = "SIMPLE";
  private String rdnAttribute = "uid";
  private boolean initialized;
  private String rolesSchemaAttribute;
  private String accessAttribute;
  protected String[] attributeUnion;
  
  public void setStartTls(boolean paramBoolean)
  {
    this.tls = paramBoolean;
  }
  
  public void setLdapPort(int paramInt)
  {
    this.ldapPort = Integer.valueOf(paramInt);
  }
  
  public void init()
  {
    if (this.ldapHost == null) {
      throw new IllegalStateException("Required property 'ldapHost' not set");
    }
    if (this.parentDn == null) {
      throw new IllegalStateException("Required property 'parentDn' not set");
    }
    if (this.initialContextFactory == null) {
      throw new IllegalStateException("Required property 'initialContextFactory' not set");
    }
    if (this.mechanism == null) {
      throw new IllegalStateException("Required property 'mechanism' not set");
    }
    if (this.rdnAttribute == null) {
      throw new IllegalStateException("Required property 'rdnAttribute' not set");
    }
    if ((this.rolesSchemaAttribute == null) && (this.accessAttribute == null)) {
      throw new IllegalStateException("You must set property 'rolesSchemaAttribute' and/or property 'accessAttribute'");
    }
    if ((this.roleSchemaValuePattern != null) && (this.rolesSchemaAttribute == null)) {
      throw new IllegalStateException("If property 'roleSchemaValuePattern' is set, then you must also set property 'rolesSchemaAttribute' to indicate which attribute to evalueate");
    }
    if ((this.accessValuePattern != null) && (this.accessAttribute == null)) {
      throw new IllegalStateException("If property 'accessValuePattern' is set, then you must also set property 'accessAttribute' to indicate which attribute to evalueate");
    }
    if ((this.rolesSchemaAttribute != null) && (this.accessAttribute != null)) {
      this.attributeUnion = new String[] { this.rolesSchemaAttribute, this.accessAttribute };
    } else if (this.rolesSchemaAttribute != null) {
      this.attributeUnion = new String[] { this.rolesSchemaAttribute };
    } else {
      this.attributeUnion = new String[] { this.accessAttribute };
    }
    this.initialized = true;
  }
  
  public void setAccessValuePattern(Pattern paramPattern)
  {
    this.accessValuePattern = paramPattern;
  }
  
  public void setAccessValuePatternString(String paramString)
  {
    setAccessValuePattern(Pattern.compile(paramString));
  }
  
  public void setRoleSchemaValuePattern(Pattern paramPattern)
  {
    this.roleSchemaValuePattern = paramPattern;
  }
  
  public void setRoleSchemaValuePatternString(String paramString)
  {
    setRoleSchemaValuePattern(Pattern.compile(paramString));
  }
  
  public void setSecurityMechanism(String paramString)
  {
    this.mechanism = paramString;
  }
  
  public void setLdapHost(String paramString)
  {
    this.ldapHost = paramString;
  }
  
  public void setPrincipalTemplate(String paramString)
  {
    this.principalTemplate = paramString;
  }
  
  public void setInitialContextFactory(String paramString)
  {
    this.initialContextFactory = paramString;
  }
  
  public void setSaslRealm(String paramString)
  {
    this.saslRealm = paramString;
  }
  
  public void setParentDn(String paramString)
  {
    this.parentDn = paramString;
  }
  
  public void setRdnAttribute(String paramString)
  {
    this.rdnAttribute = paramString;
  }
  
  public void setRolesSchemaAttribute(String paramString)
  {
    this.rolesSchemaAttribute = paramString;
  }
  
  public void setAccessAttribute(String paramString)
  {
    this.accessAttribute = paramString;
  }
  
  public String[] authenticate(String paramString1, String paramString2)
    throws DenyException
  {
    if (!this.initialized) {
      throw new IllegalStateException("You must invoke the 'init' method to initialize the " + LdapAuthBean.class.getName() + " instance.");
    }
    Hashtable localHashtable = new Hashtable(5, 0.75F);
    localHashtable.put("java.naming.factory.initial", this.initialContextFactory);
    localHashtable.put("java.naming.provider.url", "ldap://" + this.ldapHost + (this.ldapPort == null ? "" : new StringBuilder().append(":").append(this.ldapPort).toString()));
    StartTlsResponse localStartTlsResponse = null;
    InitialLdapContext localInitialLdapContext = null;
    try
    {
      localInitialLdapContext = new InitialLdapContext(localHashtable, null);
      if (this.tls)
      {
        localStartTlsResponse = (StartTlsResponse)localInitialLdapContext.extendedOperation(new StartTlsRequest());
        localStartTlsResponse.negotiate();
      }
      localInitialLdapContext.addToEnvironment("java.naming.security.authentication", this.mechanism);
      localInitialLdapContext.addToEnvironment("java.naming.security.principal", this.principalTemplate == null ? paramString1 : this.principalTemplate.replace("${username}", paramString1));
      localInitialLdapContext.addToEnvironment("java.naming.security.credentials", paramString2);
      if (this.saslRealm != null) {
        localHashtable.put("java.naming.security.sasl.realm", this.saslRealm);
      }
      NamingEnumeration localNamingEnumeration = null;
      try
      {
        localNamingEnumeration = localInitialLdapContext.search(this.parentDn, new BasicAttributes(this.rdnAttribute, paramString1), this.attributeUnion);
      }
      catch (AuthenticationException localAuthenticationException)
      {
        throw new DenyException();
      }
      catch (Exception localException)
      {
        throw new RuntimeException(localException);
      }
      if (!localNamingEnumeration.hasMore()) {
        throw new DenyException();
      }
      SearchResult localSearchResult = (SearchResult)localNamingEnumeration.next();
      if (localNamingEnumeration.hasMore()) {
        throw new RuntimeException("> 1 result");
      }
      Attributes localAttributes = localSearchResult.getAttributes();
      if (this.accessAttribute != null)
      {
        localObject1 = localAttributes.get(this.accessAttribute);
        if (localObject1 == null) {
          throw new DenyException();
        }
        if (((Attribute)localObject1).size() != 1) {
          throw new RuntimeException("Access attribute '" + this.accessAttribute + "' has unexpected value count: " + ((Attribute)localObject1).size());
        }
        if (this.accessValuePattern != null)
        {
          Object localObject2 = ((Attribute)localObject1).get(0);
          if (localObject2 == null) {
            throw new RuntimeException("Access Attr. value is null");
          }
          if (!(localObject2 instanceof String)) {
            throw new RuntimeException("Access Attr. value not a String: " + localObject2.getClass().getName());
          }
          if (!this.accessValuePattern.matcher((String)localObject2).matches()) {
            throw new DenyException();
          }
        }
      }
      if (this.rolesSchemaAttribute == null)
      {
        localObject1 = null;
        return localObject1;
      }
      Object localObject1 = new ArrayList();
      Attribute localAttribute = localAttributes.get(this.rolesSchemaAttribute);
      if (localAttribute != null)
      {
        int i = localAttribute.size();
        for (int j = 0; j < i; j++)
        {
          Object localObject3 = localAttribute.get(j);
          if (localObject3 == null) {
            throw new RuntimeException("R/S Attr value #" + j + " is null");
          }
          if (!(localObject3 instanceof String)) {
            throw new RuntimeException("R/S Attr value #" + j + " not a String: " + localObject3.getClass().getName());
          }
          if (this.roleSchemaValuePattern == null)
          {
            ((List)localObject1).add((String)localObject3);
          }
          else
          {
            Matcher localMatcher = this.roleSchemaValuePattern.matcher((String)localObject3);
            if (localMatcher.matches()) {
              ((List)localObject1).add(localMatcher.groupCount() > 0 ? localMatcher.group(1) : (String)localObject3);
            }
          }
        }
      }
      if (((List)localObject1).size() < 1)
      {
        if (this.accessAttribute == null) {
          throw new DenyException();
        }
        arrayOfString = new String[0];
        return arrayOfString;
      }
      String[] arrayOfString = (String[])((List)localObject1).toArray(new String[0]);
      return arrayOfString;
    }
    catch (DenyException localDenyException)
    {
      throw localDenyException;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw new RuntimeException(localIOException1);
    }
    catch (NamingException localNamingException1)
    {
      throw new RuntimeException(localNamingException1);
    }
    finally
    {
      if (localStartTlsResponse != null) {
        try
        {
          localStartTlsResponse.close();
        }
        catch (IOException localIOException5)
        {
          logger.error("Failed to close TLS Response", localIOException5);
        }
      }
      if (localInitialLdapContext != null) {
        try
        {
          localInitialLdapContext.close();
        }
        catch (NamingException localNamingException5)
        {
          logger.error("Failed to close LDAP Context", localNamingException5);
        }
      }
    }
  }
  
  public static void main(String[] paramArrayOfString)
    throws IOException
  {
    if (paramArrayOfString.length != 3) {
      throw new IllegalArgumentException("SYNTAX:  java " + AuthBeanMultiplexer.class.getName() + " path/to/file.properties <USERNAME> <PASSWORD>");
    }
    File localFile = new File(paramArrayOfString[0]);
    if (!localFile.isFile()) {
      throw new IllegalArgumentException("Not a file: " + localFile.getAbsolutePath());
    }
    Properties localProperties = new Properties();
    localProperties.load(new FileInputStream(localFile));
    String str1 = localProperties.getProperty("trustStore");
    String str2 = localProperties.getProperty("startTls");
    String str3 = localProperties.getProperty("ldapPort");
    String str4 = localProperties.getProperty("roleSchemaValuePattern");
    String str5 = localProperties.getProperty("accessValuePattern");
    String str6 = localProperties.getProperty("securityMechanism");
    String str7 = localProperties.getProperty("ldapHost");
    String str8 = localProperties.getProperty("principalTemplate");
    String str9 = localProperties.getProperty("initialContextFactory");
    String str10 = localProperties.getProperty("saslRealm");
    String str11 = localProperties.getProperty("parentDn");
    String str12 = localProperties.getProperty("rdnAttribute");
    String str13 = localProperties.getProperty("rolesSchemaAttribute");
    String str14 = localProperties.getProperty("accessAttribute");
    if (str1 != null)
    {
      if (!new File(str1).isFile()) {
        throw new IllegalArgumentException("Specified trust store is not a file: " + str1);
      }
      System.setProperty("javax.net.ssl.trustStore", str1);
    }
    LdapAuthBean localLdapAuthBean = new LdapAuthBean();
    if (str2 != null) {
      localLdapAuthBean.setStartTls(Boolean.parseBoolean(str2));
    }
    if (str3 != null) {
      localLdapAuthBean.setLdapPort(Integer.parseInt(str3));
    }
    if (str4 != null) {
      localLdapAuthBean.setRoleSchemaValuePatternString(str4);
    }
    if (str5 != null) {
      localLdapAuthBean.setAccessValuePatternString(str5);
    }
    if (str6 != null) {
      localLdapAuthBean.setSecurityMechanism(str6);
    }
    if (str7 != null) {
      localLdapAuthBean.setLdapHost(str7);
    }
    if (str8 != null) {
      localLdapAuthBean.setPrincipalTemplate(str8);
    }
    if (str9 != null) {
      localLdapAuthBean.setInitialContextFactory(str9);
    }
    if (str10 != null) {
      localLdapAuthBean.setSaslRealm(str10);
    }
    if (str11 != null) {
      localLdapAuthBean.setParentDn(str11);
    }
    if (str12 != null) {
      localLdapAuthBean.setRdnAttribute(str12);
    }
    if (str13 != null) {
      localLdapAuthBean.setRolesSchemaAttribute(str13);
    }
    if (str14 != null) {
      localLdapAuthBean.setAccessAttribute(str14);
    }
    localLdapAuthBean.init();
    String[] arrayOfString = null;
    try
    {
      arrayOfString = localLdapAuthBean.authenticate(paramArrayOfString[1], paramArrayOfString[2]);
    }
    catch (DenyException localDenyException)
    {
      System.out.println("<DENY ACCESS>");
      return;
    }
    if (arrayOfString == null) {
      System.out.println("<ALLOW ACCESS w/ local Roles+Schema>");
    } else {
      System.out.println(Integer.toString(arrayOfString.length) + " Roles/Schema: " + Arrays.toString(arrayOfString));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.auth.LdapAuthBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */