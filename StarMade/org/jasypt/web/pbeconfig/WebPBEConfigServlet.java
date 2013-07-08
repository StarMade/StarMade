package org.jasypt.web.pbeconfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.pbe.config.WebPBEConfig;

public final class WebPBEConfigServlet
  extends HttpServlet
{
  private static final long serialVersionUID = -7201635392816652667L;
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    execute(req, resp);
  }
  
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    execute(req, resp);
  }
  
  private void execute(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    try
    {
      WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
      if (registry.isWebConfigurationDone())
      {
        writeResponse(WebPBEConfigHtmlUtils.createConfigurationDoneHtml(), resp);
      }
      else
      {
        String settingFlag = req.getParameter("jasyptPwSetting");
        if (CommonUtils.isEmpty(settingFlag))
        {
          writeResponse(WebPBEConfigHtmlUtils.createInputFormHtml(req, false), resp);
        }
        else
        {
          List configs = registry.getConfigs();
          Iterator configsIter = configs.iterator();
          int local_i = 0;
          int valid = 0;
          while (configsIter.hasNext())
          {
            WebPBEConfig config = (WebPBEConfig)configsIter.next();
            String validation = req.getParameter("jasyptVa" + local_i);
            String password = req.getParameter("jasyptPw" + local_i);
            String retypedPassword = req.getParameter("jasyptRPw" + local_i);
            if ((!CommonUtils.isEmpty(validation)) && (!CommonUtils.isEmpty(password)) && (password.equals(retypedPassword)) && (config.getValidationWord().equals(validation))) {
              valid++;
            }
            local_i++;
          }
          SimpleDateFormat config = new SimpleDateFormat();
          Calendar validation = Calendar.getInstance();
          if (valid < configs.size())
          {
            getServletContext().log("Failed attempt to set PBE Configuration from " + req.getRemoteAddr() + " [" + config.format(validation.getTime()) + "]");
            writeResponse(WebPBEConfigHtmlUtils.createInputFormHtml(req, true), resp);
          }
          else
          {
            configsIter = configs.iterator();
            for (local_i = 0; configsIter.hasNext(); local_i++)
            {
              WebPBEConfig password = (WebPBEConfig)configsIter.next();
              String retypedPassword = req.getParameter("jasyptPw" + local_i);
              password.setPassword(retypedPassword);
            }
            registry.setWebConfigurationDone(true);
            getServletContext().log("PBE Configuration succesfully set from " + req.getRemoteAddr() + " [" + config.format(validation.getTime()) + "]");
            writeResponse(WebPBEConfigHtmlUtils.createConfigurationDoneHtml(), resp);
          }
        }
      }
    }
    catch (IOException registry)
    {
      getServletContext().log("Exception raised during servlet execution", registry);
      throw registry;
    }
    catch (Throwable registry)
    {
      getServletContext().log("Exception raised during servlet execution", registry);
      throw new ServletException(registry);
    }
  }
  
  private void writeResponse(String html, HttpServletResponse response)
    throws IOException
  {
    PrintWriter printWriter = response.getWriter();
    printWriter.write(html);
    printWriter.flush();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigServlet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */