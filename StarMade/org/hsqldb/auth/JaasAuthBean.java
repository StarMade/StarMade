package org.hsqldb.auth;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import org.hsqldb.lib.FrameworkLogger;

public class JaasAuthBean
  implements AuthFunctionBean
{
  private static FrameworkLogger logger = FrameworkLogger.getLog(JaasAuthBean.class);
  private boolean initialized;
  private String applicationKey;
  private Pattern roleSchemaValuePattern;
  private boolean roleSchemaViaCredential;

  public void setRoleSchemaViaCredential(boolean paramBoolean)
  {
    this.roleSchemaViaCredential = paramBoolean;
  }

  public void init()
  {
    if (this.applicationKey == null)
      throw new IllegalStateException("Required property 'applicationKey' not set");
    if ((this.roleSchemaViaCredential) && (this.roleSchemaValuePattern == null))
      throw new IllegalStateException("Properties 'roleSchemaViaCredential' and 'roleSchemaValuePattern' are mutually exclusive.  If you want JaasAuthBean to manage roles or schemas, you must set property 'roleSchemaValuePattern'.");
    this.initialized = true;
  }

  public void setApplicationKey(String paramString)
  {
    this.applicationKey = paramString;
  }

  public void setRoleSchemaValuePattern(Pattern paramPattern)
  {
    this.roleSchemaValuePattern = paramPattern;
  }

  public void setRoleSchemaValuePatternString(String paramString)
  {
    setRoleSchemaValuePattern(Pattern.compile(paramString));
  }

  public String[] authenticate(String paramString1, String paramString2)
    throws DenyException
  {
    if (!this.initialized)
      throw new IllegalStateException(new StringBuilder().append("You must invoke the 'init' method to initialize the ").append(JaasAuthBean.class.getName()).append(" instance.").toString());
    try
    {
      LoginContext localLoginContext = new LoginContext(this.applicationKey, new UPCallbackHandler(paramString1, paramString2));
      try
      {
        localLoginContext.login();
      }
      catch (LoginException localLoginException2)
      {
        logger.finer(new StringBuilder().append("JSSE backend denying access:  ").append(localLoginException2).toString());
        throw new DenyException();
      }
      try
      {
        if (this.roleSchemaValuePattern == null)
        {
          String[] arrayOfString = null;
          return arrayOfString;
        }
        int i = 0;
        Matcher localMatcher = null;
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        Subject localSubject = localLoginContext.getSubject();
        Object localObject2;
        if (this.roleSchemaViaCredential)
        {
          localObject1 = new ArrayList(localSubject.getPublicCredentials()).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = ((Iterator)localObject1).next();
            localArrayList1.add(localObject2.toString());
          }
        }
        else
        {
          localObject1 = new ArrayList(localSubject.getPrincipals()).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (Principal)((Iterator)localObject1).next();
            localArrayList1.add(((Principal)localObject2).getName());
          }
        }
        logger.finer(new StringBuilder().append(Integer.toString(localArrayList1.size())).append(" candidate ").append(this.roleSchemaViaCredential ? "Credentials" : "Principals").toString());
        Object localObject1 = localArrayList1.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          localMatcher = this.roleSchemaValuePattern.matcher((CharSequence)localObject2);
          if (localMatcher.matches())
          {
            logger.finer(new StringBuilder().append("    +").append(++i).append(": ").append(localMatcher.groupCount() > 0 ? localMatcher.group(1) : (String)localObject2).toString());
            localArrayList2.add(localMatcher.groupCount() > 0 ? localMatcher.group(1) : localObject2);
          }
          else
          {
            logger.finer(new StringBuilder().append("    -").append(++i).append(": ").append((String)localObject2).toString());
          }
        }
        localObject1 = (String[])localArrayList2.toArray(new String[0]);
        return localObject1;
      }
      finally
      {
        localLoginContext.logout();
      }
    }
    catch (LoginException localLoginException1)
    {
      logger.severe("System JaasAuthBean failure", localLoginException1);
      throw new RuntimeException(localLoginException1);
    }
    catch (RuntimeException localRuntimeException)
    {
      logger.severe("System JaasAuthBean failure", localRuntimeException);
      throw localRuntimeException;
    }
  }

  public static class UPCallbackHandler
    implements CallbackHandler
  {
    private String u;
    private char[] p;

    public UPCallbackHandler(String paramString1, String paramString2)
    {
      this.u = paramString1;
      this.p = paramString2.toCharArray();
    }

    public void handle(Callback[] paramArrayOfCallback)
      throws UnsupportedCallbackException
    {
      int i = 0;
      int j = 0;
      for (Callback localCallback : paramArrayOfCallback)
        if ((localCallback instanceof NameCallback))
        {
          ((NameCallback)localCallback).setName(this.u);
          i = 1;
        }
        else if ((localCallback instanceof PasswordCallback))
        {
          ((PasswordCallback)localCallback).setPassword(this.p);
          j = 1;
        }
        else
        {
          throw new UnsupportedCallbackException(localCallback, "Unsupported Callback type: " + localCallback.getClass().getName());
        }
      if (i == 0)
        throw new IllegalStateException("Supplied Callbacks does not include a NameCallback");
      if (j == 0)
        throw new IllegalStateException("Supplied Callbacks does not include a PasswordCallback");
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.auth.JaasAuthBean
 * JD-Core Version:    0.6.2
 */