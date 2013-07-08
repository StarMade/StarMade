/*   1:    */package org.jasypt.web.pbeconfig;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintWriter;
/*   5:    */import java.text.SimpleDateFormat;
/*   6:    */import java.util.Calendar;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import javax.servlet.ServletContext;
/*  10:    */import javax.servlet.ServletException;
/*  11:    */import javax.servlet.http.HttpServlet;
/*  12:    */import javax.servlet.http.HttpServletRequest;
/*  13:    */import javax.servlet.http.HttpServletResponse;
/*  14:    */import org.jasypt.commons.CommonUtils;
/*  15:    */import org.jasypt.encryption.pbe.config.WebPBEConfig;
/*  16:    */
/*  85:    */public final class WebPBEConfigServlet
/*  86:    */  extends HttpServlet
/*  87:    */{
/*  88:    */  private static final long serialVersionUID = -7201635392816652667L;
/*  89:    */  
/*  90:    */  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
/*  91:    */    throws ServletException, IOException
/*  92:    */  {
/*  93: 93 */    execute(req, resp);
/*  94:    */  }
/*  95:    */  
/*  96:    */  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*  97:    */  {
/*  98: 98 */    execute(req, resp);
/*  99:    */  }
/* 100:    */  
/* 101:    */  private void execute(HttpServletRequest req, HttpServletResponse resp)
/* 102:    */    throws ServletException, IOException
/* 103:    */  {
/* 104:    */    try
/* 105:    */    {
/* 106:106 */      WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
/* 107:    */      
/* 108:108 */      if (registry.isWebConfigurationDone())
/* 109:    */      {
/* 111:111 */        writeResponse(WebPBEConfigHtmlUtils.createConfigurationDoneHtml(), resp);
/* 113:    */      }
/* 114:    */      else
/* 115:    */      {
/* 117:117 */        String settingFlag = req.getParameter("jasyptPwSetting");
/* 118:    */        
/* 119:119 */        if (CommonUtils.isEmpty(settingFlag))
/* 120:    */        {
/* 122:122 */          writeResponse(WebPBEConfigHtmlUtils.createInputFormHtml(req, false), resp);
/* 127:    */        }
/* 128:    */        else
/* 129:    */        {
/* 133:133 */          List configs = registry.getConfigs();
/* 134:134 */          Iterator configsIter = configs.iterator();
/* 135:135 */          int i = 0;
/* 136:136 */          int valid = 0;
/* 137:137 */          while (configsIter.hasNext())
/* 138:    */          {
/* 139:139 */            WebPBEConfig config = (WebPBEConfig)configsIter.next();
/* 140:    */            
/* 141:141 */            String validation = req.getParameter("jasyptVa" + i);
/* 142:    */            
/* 143:143 */            String password = req.getParameter("jasyptPw" + i);
/* 144:    */            
/* 145:145 */            String retypedPassword = req.getParameter("jasyptRPw" + i);
/* 146:    */            
/* 148:148 */            if ((!CommonUtils.isEmpty(validation)) && (!CommonUtils.isEmpty(password)) && (password.equals(retypedPassword)) && (config.getValidationWord().equals(validation)))
/* 149:    */            {
/* 157:157 */              valid++;
/* 158:    */            }
/* 159:    */            
/* 160:160 */            i++;
/* 161:    */          }
/* 162:    */          
/* 164:164 */          SimpleDateFormat dateFormat = new SimpleDateFormat();
/* 165:165 */          Calendar now = Calendar.getInstance();
/* 166:    */          
/* 167:167 */          if (valid < configs.size())
/* 168:    */          {
/* 173:173 */            getServletContext().log("Failed attempt to set PBE Configuration from " + req.getRemoteAddr() + " [" + dateFormat.format(now.getTime()) + "]");
/* 174:    */            
/* 178:178 */            writeResponse(WebPBEConfigHtmlUtils.createInputFormHtml(req, true), resp);
/* 182:    */          }
/* 183:    */          else
/* 184:    */          {
/* 188:188 */            configsIter = configs.iterator();
/* 189:189 */            i = 0;
/* 190:190 */            while (configsIter.hasNext()) {
/* 191:191 */              WebPBEConfig config = (WebPBEConfig)configsIter.next();
/* 192:192 */              String password = req.getParameter("jasyptPw" + i);
/* 193:    */              
/* 194:194 */              config.setPassword(password);
/* 195:195 */              i++;
/* 196:    */            }
/* 197:    */            
/* 198:198 */            registry.setWebConfigurationDone(true);
/* 199:    */            
/* 200:200 */            getServletContext().log("PBE Configuration succesfully set from " + req.getRemoteAddr() + " [" + dateFormat.format(now.getTime()) + "]");
/* 201:    */            
/* 205:205 */            writeResponse(WebPBEConfigHtmlUtils.createConfigurationDoneHtml(), resp);
/* 206:    */          }
/* 207:    */          
/* 208:    */        }
/* 209:    */        
/* 210:    */      }
/* 211:    */      
/* 213:    */    }
/* 214:    */    catch (IOException e)
/* 215:    */    {
/* 216:216 */      getServletContext().log("Exception raised during servlet execution", e);
/* 217:    */      
/* 218:218 */      throw e;
/* 219:    */    } catch (Throwable t) {
/* 220:220 */      getServletContext().log("Exception raised during servlet execution", t);
/* 221:    */      
/* 222:222 */      throw new ServletException(t);
/* 223:    */    }
/* 224:    */  }
/* 225:    */  
/* 228:    */  private void writeResponse(String html, HttpServletResponse response)
/* 229:    */    throws IOException
/* 230:    */  {
/* 231:231 */    PrintWriter printWriter = response.getWriter();
/* 232:232 */    printWriter.write(html);
/* 233:233 */    printWriter.flush();
/* 234:    */  }
/* 235:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigServlet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */