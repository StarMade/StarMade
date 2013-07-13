package jo.sm.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @author jgrant
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StringUtils
{
    /**
     * @param title
     * @return
     */
    public static boolean nonTrivial(String title)
    {
        return (title != null) && (title.length() > 0);
    }

    /**
     * @param title
     * @return
     */
    public static boolean trivial(String title)
    {
        return (title == null) || (title.length() == 0);
    }

    public static boolean fuzzyMatch(String str, String pat)
    {
        return str.toLowerCase().indexOf(pat.toLowerCase()) >= 0;
    }

    public static String removeWhitespace(String html)
	{
		StringBuffer ret = new StringBuffer();
		StringBuffer acc = new StringBuffer();
		int state = 0;
		char[] c = html.toCharArray();
		for (int i = 0; i < c.length; i++)
			switch (state)
			{
				case 0 :
					if (Character.isWhitespace(c[i]))
					{
						acc.append(c[i]);
						state = 1;
					}
					else if (c[i] == '>')
					{
						ret.append(c[i]);
						state = 2;
					}
					else
					{
						ret.append(c[i]);
					}
					break;
				case 1 : // skipping leading whitespace
					if (Character.isWhitespace(c[i]))
					{
						acc.append(c[i]);
					}
					else if (c[i] == '<')
					{
						acc.setLength(0);
						ret.append(c[i]);
						state = 0;
					}
					else
					{
						ret.append(acc.toString());
						acc.setLength(0);
						ret.append(c[i]);
						state = 0;
					}
					break;
				case 2 : // skipping trailing whitespace
					if (Character.isWhitespace(c[i]))
					{
						;
					}
					else
					{
						ret.append(c[i]);
						state = 0;
					}
					break;
			}
		return ret.toString();
	}

    public static String condenseWhitespace(String html)
    {
        StringBuffer ret = new StringBuffer();
        int state = 0;
        char[] c = html.toCharArray();
        for (int i = 0; i < c.length; i++)
            switch (state)
            {
                case 0 :
                    if (Character.isWhitespace(c[i]))
                    {
                        ret.append(' ');
                        state = 1;
                    }
                    else if (c[i] == '<')
                    {
                        ret.append(c[i]);
                        state = 2;
                    }
                    else
                    {
                        ret.append(c[i]);
                    }
                    break;
                case 1 : // skipping whitespace
                    if (Character.isWhitespace(c[i]))
                        ;
                    else if (c[i] == '<')
                    {
                        ret.append(c[i]);
                        state = 2;
                    }
                    else
                    {
                        ret.append(c[i]);
                        state = 0;
                    }
                    break;
                case 2 : // skipping tag
                    ret.append(c[i]);
                    if (c[i] == '>')
                    {
                        state = 0;
                    }
                    break;
            }
        return ret.toString();
    }
	public static String extractAttr(String tag, String attr)
	{
		int o = tag.toLowerCase().indexOf(attr.toLowerCase());
		if (o < 0)
			return null;
		tag = tag.substring(o + attr.length()).trim();
		if (!tag.startsWith("="))
			return null;
		tag = tag.substring(1).trim();
		String delim = tag.substring(0, 1);
		tag = tag.substring(1);
		o = tag.indexOf(delim);
		if (o < 0)
			return null;
		return tag.substring(0, o);
	}
	public static String extractNextTagContents(StringBuffer html, String tag)
	{
		if (StringUtils.extractNextTag(html, tag) == null)
			return null;
		String h = html.toString();
		int o = h.toLowerCase().indexOf("</" + tag.toLowerCase());
		if (o < 0)
		{
			return null;
		}
		html.setLength(0);
		html.append(h.substring(o + 1 + tag.length()));
		return h.substring(0, o);
	}
	public static String extractNextTag(StringBuffer html, String tag)
	{
		String h = html.toString();
		int o = h.toLowerCase().indexOf("<" + tag.toLowerCase());
		if (o < 0)
		{
			return null;
		}
		h = h.substring(o);
		o = h.indexOf(">");
		if (o < 0)
		{
			return null;
		}
		html.setLength(0);
		html.append(h.substring(o + 1));
		return h.substring(0, o + 1);
	}
	public static String[] extractArray(
		String html,
		String openTag,
		String endTag)
	{
		if (html == null)
			return null;
		Vector<String> v = new Vector<String>();
		String htmlLC = html.toLowerCase();
		openTag = openTag.toLowerCase();
		if (endTag != null)
            endTag = endTag.toLowerCase();
		// trim start
		int o = htmlLC.indexOf(openTag);
		if (o < 0)
			return new String[0];
		html = html.substring(o);
		htmlLC = htmlLC.substring(o);
		String thisElem, thisElemLC;
		for (;;)
		{
			o = htmlLC.indexOf(openTag, 1);
			if (o < 0)
			{
				thisElem = html;
				thisElemLC = htmlLC;
				html = null;
			}
			else
			{
				thisElem = html.substring(0, o);
				thisElemLC = htmlLC.substring(0, o);
				html = html.substring(o);
				htmlLC = htmlLC.substring(o);
			}
			if (endTag != null)
                o = thisElemLC.lastIndexOf(endTag);
            else
                o = thisElemLC.indexOf(openTag, 1);
			if (o >= 0)
				thisElem = thisElem.substring(0, o);
			v.addElement(thisElem);
			if (html == null)
				break;
		}
		String[] ret = new String[v.size()];
		v.copyInto(ret);
		return ret;
	}
	public static String extract(String html, String tag)
	{
		return StringUtils.extract(html, "<" + tag, "</" + tag);
	}
	public static String extract(String html, String openTag, String endTag)
	{
		return StringUtils.extract(new StringBuffer(html), openTag, endTag);
	}
	public static String extract(
		StringBuffer html,
		String openTag,
		String endTag)
	{
		int o = html.toString().toLowerCase().indexOf(openTag.toLowerCase());
		if (o < 0)
			return null;
		String ret = html.substring(o + openTag.length());
		if (openTag.startsWith("<") && !openTag.endsWith(">"))
		{
			int o2 = ret.indexOf('>');
			if (o2 >= 0)
				ret = ret.substring(o2 + 1);
		}
		if (endTag.startsWith("</")
			&& endTag.endsWith(">")
			&& openTag.startsWith("<" + endTag.substring(2, endTag.length() - 1)))
		{
			String openTagType =
				"<" + endTag.substring(2, endTag.length() - 1).toLowerCase();
			//OutputLogic.println("Nested extract!!! "+openTagType+"..."+endTag);
			int nest = 1;
			int index = 0;
			while (nest > 0)
			{
				//OutputLogic.println("nest="+nest+", index="+index);
				//OutputLogic.println(ret.substring(index, index+50));
				int openOff = ret.toLowerCase().indexOf(openTagType, index + 1);
				int endOff = ret.toLowerCase().indexOf(endTag, index + 1);
				if ((openOff < 0) && (endOff < 0))
				{
					index = ret.length();
					break;
				}
				if (endOff < 0)
				{
					nest++;
					index = openOff;
				}
				else if (openOff < 0)
				{
					nest--;
					index = endOff;
				}
				else if (openOff < endOff)
				{
					nest++;
					index = openOff;
				}
				else
				{
					nest--;
					index = endOff;
				}
			}
			o = index;
		}
		else
		{
			//OutputLogic.println("Unested extract!!! "+openTag+"..."+endTag);
			if (endTag.length() == 0)
				o = ret.length();
			else
				o = ret.toLowerCase().indexOf(endTag.toLowerCase());
		}
		if (o < 0)
			return null;
		html.setLength(0);
		if (o + endTag.length() < ret.length())
			html.append(ret.substring(o + endTag.length()));
		ret = ret.substring(0, o);
		return ret.trim();
	}
	public static String excise(String html, String tag)
	{
		return StringUtils.excise(html, "<" + tag, "</" + tag);
	}
	public static String excise(
		String html,
		String openTag,
		String endTag)
	{
	    int endTagOff;
		int openTagOff = html.toLowerCase().indexOf(openTag.toLowerCase());
		if (openTagOff < 0)
			return html.toString();
		String ret = html.substring(0, openTagOff);
		html = html.substring(openTagOff + openTag.length());
		for (;;)
		{
		    endTagOff = html.toLowerCase().indexOf(endTag.toLowerCase());
		    if (endTagOff < 0)
		        break;
		    openTagOff = html.toLowerCase().indexOf(openTag.toLowerCase());
		    if ((openTagOff < 0) || (openTagOff > endTagOff))
		        break;
		    html = html.substring(endTagOff + endTag.length());
		}
		html = html.substring(endTagOff + endTag.length());
		if (!endTag.endsWith(">"))
		{
		    int o = html.indexOf(">");
		    if (o >= 0)
		        html = html.substring(o+1);
		}
		ret += html;
		return ret;
	}
    public static String exciseText(String content, String text)
    {
        int o = 0;
        for (;;)
        {
            o = content.indexOf(text, o);
            if (o < 0)
                break;
            content = content.substring(0, o) + content.substring(o + text.length());
        }
        return content;
    }
    public static String exciseAllTags(String html)
    {
        int o = 0;
        for (;;)
        {
            o = html.indexOf('<', o);
            if (o < 0)
                break;
            int o2 = html.indexOf('>', o);
            if (o2 < 0)
                break;
            html = html.substring(0, o) + html.substring(o2 + 1);
        }
        return html;
    }
	public static String exciseAll(String html, String tag)
	{
	    for (;;)
	    {
	        String ori = html;
	        html = excise(html, tag);
	        if (html.equals(ori))
	            break;
	    }
	    return html;
	}
	public static String exciseAll(String html, String openTag, String endTag)
	{
	    for (;;)
	    {
	        String ori = html;
	        html = excise(html, openTag, endTag);
	        if (html.equals(ori))
	            break;
	    }
	    return html;
	}
	/**
	 * @param string
	 * @return
	 */
	public static int digitize(String string)
	{
	    return (int)digitizeLong(string);
	}
    public static long digitizeLong(String string)
    {
        if (string == null)
            return 0;
        StringBuffer ret = new StringBuffer("0");
        char[] c = string.toCharArray();
        for (int i = 0; i < c.length; i++)
            if ((c[i] >= '0') && (c[i] <= '9'))
                ret.append(c[i]);
        //DebugLogic.trace("Digitize:"+string+"->"+ret.toString());
        return Long.parseLong(ret.toString());
    }
	public static long extractLong(String html, String openTag, String endTag)
	{		
		String str = extract(html, openTag, endTag);
		if (str == null)
		{
			//DebugLogic.critical("Cant find {{"+openTag+"}} ... {{"+endTag+"}} within:");
			//DebugLogic.critical(html);
			return 0L;
		}		
		try
		{
			return Long.parseLong(str);
		}
		catch (NumberFormatException ex)
		{
			return 0L;
		}
	}
	public static int extractInt(String html, String openTag, String endTag)
	{		
		String str = extract(html, openTag, endTag);
		if (str == null)
		{
			//DebugLogic.critical("Cant find {{"+openTag+"}} ... {{"+endTag+"}} within:");
			//DebugLogic.critical(html);
			return 0;
		}		
		try
		{
			return Integer.parseInt(str);
		}
		catch (NumberFormatException ex)
		{
			return 0;
		}
	}
	public static double extractDouble(String html, String openTag, String endTag)
	{		
		String str = extract(html, openTag, endTag);
		if (str == null)
		{
			//DebugLogic.critical("Cant find {{"+openTag+"}} ... {{"+endTag+"}} within:");
			//DebugLogic.critical(html);
			return 0;
		}		
		try
		{
			return Double.parseDouble(str);
		}
		catch (NumberFormatException ex)
		{
			return 0;
		}
	}
	public static int extractDigit(String html, String openTag, String endTag)
	{		
		String str = extract(html, openTag, endTag);
		if (str == null)
		{
			//DebugLogic.critical("Cant find {{"+openTag+"}} ... {{"+endTag+"}} within:");
			//DebugLogic.critical(html);
			return 0;
		}		
		return digitize(str);
	}
	
	public static String findTableWith(String html, String grep)
	{
		String[] tables = extractArray(html, "<table", "</table>");
		if (tables != null)	
			for (int i = 0; i < tables.length; i++)
			{
				if (tables[i].indexOf(grep) >= 0)
					return tables[i];	
			}
		return "";
	}
	
	public static String findTagWith(String html, String grep)
	{
		int o =  html.indexOf(grep);
		if (o < 0)
			return null;
		int start;
		int end;
		for (start = o; start >= 0; start--)
			if (html.charAt(start) == '<')
				break;
		for (end = o+grep.length()-1; end < html.length(); end++)
			if (html.charAt(end) == '>')
				break;
		return html.substring(start, end);
	}
	
	public static String[] findTagsWith(String html, String grep)
	{
		List<String> found = new ArrayList<String>();
		for (;;)
		{
			String tag = findTagWith(html, grep);
			if (tag == null)
				break;
			found.add(tag);
			int o = html.indexOf(tag);
			html = html.substring(o + tag.length());
		}
		String[] ret = new String[found.size()];
		found.toArray(ret);
		return ret;
	}
	
	public static String alphatize(String html)
	{
		StringBuffer ret = new StringBuffer();
		char[] c = html.toCharArray();
		boolean inTag = false;
		for (int i = 0; i < c.length; i++)
		{
			if (inTag)
			{
				if (c[i] == '>')
					inTag = false;
			}
			else
			{
				if (c[i] == '<')
				{
					inTag = true;
					if (ret.charAt(ret.length() - 1) != ' ')
						ret.append(' ');
				}
				else
				{
					if (c[i] == ' ')
					{
						if (ret.charAt(ret.length() - 1) != ' ')
							ret.append(' ');
					}
					else
						ret.append(c[i]);
				}
			}
		}
		return ret.toString();
	}
	
	public static String prefix(String str, char prefix, int width)
	{
		StringBuffer ret = new StringBuffer(str);
		while (ret.length() < width)
			ret.insert(0, prefix);
		return ret.toString();
	}
	
	public static String zeroPrefix(String str, int width)
	{
		return prefix(str, '0', width);
	}
	
	public static String spacePrefix(String str, int width)
	{
		return prefix(str, ' ', width);
	}
	
	public static String zeroPrefix(long l, int width)
	{
		return prefix(String.valueOf(l), '0', width);
	}
	
	public static String spacePrefix(long l, int width)
	{
		return prefix(String.valueOf(l), ' ', width);
	}
	
	public static String substitute(String txt, String pattern, String sub)
	{
	    int o = 0;
		for (;;)
		{
			o = txt.indexOf(pattern, o);
			if (o < 0)
				break;
			txt = txt.substring(0, o) + sub + txt.substring(o+pattern.length());
			o += sub.length();
		}
		return txt;
	}
	
	public static String toPC(double pc)
	{
		String ret = String.valueOf(pc*100);
		int o = ret.indexOf('.');
		if (o < 0)
			ret += ".00";
		else if (o + 3 > ret.length())
		{
			do
			{
				ret += "0";
			} while (o + 3 > ret.length());
		}
		else
			ret = ret.substring(0, o + 3);
		ret += "%";
		return ret;
	}
	
	public static boolean match(String wild, String txt)
	{
	    if (wild.equals("*"))
	        return true;
	    else if (wild.startsWith("*"))
	        if (wild.endsWith("*"))
	            return txt.indexOf(wild.substring(1, wild.length() - 1)) >= 0;
	        else
	            return txt.endsWith(wild.substring(1));
	    else if (wild.endsWith("*"))
	        return txt.startsWith(wild.substring(0, wild.length() - 1));
	    else
	        return wild.equals(txt);
	}
    /**
     * @param list
     */
    public static List<String> tokenize(String list, String delim)
    {
        List<String> ret = new ArrayList<String>();
        if (list != null)
            for (StringTokenizer st = new StringTokenizer(list, delim); st.hasMoreTokens(); )
                ret.add(st.nextToken());
        return ret;
    }

    public static List<String> tokenize(String list)
    {
    	return tokenize(list, ",");
    }
    
    public static String listize(Collection<?> list, String delim)
    {
        StringBuffer ret = new StringBuffer();
        if (list != null)
	        for (Iterator<?> i = list.iterator(); i.hasNext(); )
	        {
	            if (ret.length() > 0)
	                ret.append(delim);
	            ret.append(i.next().toString());
	        }
        return ret.toString();
    }
    
    public static String listize(Collection<?> list)
    {
        return listize(list, ",");
    }
    
    public static boolean isTrivial(String str)
    {
        return (str == null) || (str.length() == 0);
    }

    public static String webalize(String str)
    {
        StringBuffer ret = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++)
            if ((c[i] < ' ') || (c[i] > '~') || (c[i] == '&') || (c[i] == '+') || (c[i] == '#'))
            {
                ret.append("%");
                String hex = Integer.toHexString(c[i]);
                if (hex.length() == 1)
                    ret.append("0");
                ret.append(hex);
            }
            else if (c[i] == ' ')
                ret.append("+");
            else
                ret.append(c[i]);
        return ret.toString();
    }
    
    private static String[] unicodeToEntity;
    private static Map<String,String> entityToUnicode;
    static 
    {
        unicodeToEntity = new String[256];
        unicodeToEntity['>'] = "gt";
        unicodeToEntity['<'] = "lt";
        unicodeToEntity['&'] = "amp";
        unicodeToEntity['"'] = "quot";
        unicodeToEntity['\u00A0'] = "nbsp";
        unicodeToEntity['\u00A1'] = "iexcl";
        unicodeToEntity['\u00A2'] = "cent";
        unicodeToEntity['\u00A3'] = "pound";
        unicodeToEntity['\u00A4'] = "curren";
        unicodeToEntity['\u00A5'] = "yen";
        unicodeToEntity['\u00A6'] = "brvbar";
        unicodeToEntity['\u00A7'] = "sect";
        unicodeToEntity['\u00A8'] = "uml";
        unicodeToEntity['\u00A9'] = "copy";
        unicodeToEntity['\u00AA'] = "ordf";
        unicodeToEntity['\u00AB'] = "laquo";
        unicodeToEntity['\u00AC'] = "not";
        unicodeToEntity['\u00AD'] = "shy";
        unicodeToEntity['\u00AE'] = "reg";
        unicodeToEntity['\u00AF'] = "macr";
        unicodeToEntity['\u00B0'] = "deg";
        unicodeToEntity['\u00B1'] = "plusmn";
        unicodeToEntity['\u00B2'] = "sup2";
        unicodeToEntity['\u00B3'] = "sup3";
        unicodeToEntity['\u00B4'] = "acute";
        unicodeToEntity['\u00B5'] = "micro";
        unicodeToEntity['\u00B6'] = "para";
        unicodeToEntity['\u00B7'] = "middot";
        unicodeToEntity['\u00B8'] = "cedil";
        unicodeToEntity['\u00B9'] = "sup1";
        unicodeToEntity['\u00BA'] = "ordm";
        unicodeToEntity['\u00BB'] = "raquo";
        unicodeToEntity['\u00BC'] = "frac14";
        unicodeToEntity['\u00BD'] = "frac12";
        unicodeToEntity['\u00BE'] = "frac34";
        unicodeToEntity['\u00BF'] = "iquest";
        unicodeToEntity['\u00C0'] = "Agrave";
        unicodeToEntity['\u00C1'] = "Aacute";
        unicodeToEntity['\u00C2'] = "Acirc";
        unicodeToEntity['\u00C3'] = "Atilde";
        unicodeToEntity['\u00C4'] = "Auml";
        unicodeToEntity['\u00C5'] = "Aring";
        unicodeToEntity['\u00C6'] = "AElig";
        unicodeToEntity['\u00C7'] = "Ccedil";
        unicodeToEntity['\u00C8'] = "Egrave";
        unicodeToEntity['\u00C9'] = "Eacute";
        unicodeToEntity['\u00CA'] = "Ecirc";
        unicodeToEntity['\u00CB'] = "Euml";
        unicodeToEntity['\u00CC'] = "Igrave";
        unicodeToEntity['\u00CD'] = "Iacute";
        unicodeToEntity['\u00CE'] = "Icirc";
        unicodeToEntity['\u00CF'] = "Iuml";
        unicodeToEntity['\u00D0'] = "ETH";
        unicodeToEntity['\u00D1'] = "Ntilde";
        unicodeToEntity['\u00D2'] = "Ograve";
        unicodeToEntity['\u00D3'] = "Oacute";
        unicodeToEntity['\u00D4'] = "Ocirc";
        unicodeToEntity['\u00D5'] = "Otilde";
        unicodeToEntity['\u00D6'] = "Ouml";
        unicodeToEntity['\u00D7'] = "times";
        unicodeToEntity['\u00D8'] = "Oslash";
        unicodeToEntity['\u00D9'] = "Ugrave";
        unicodeToEntity['\u00DA'] = "Uacute";
        unicodeToEntity['\u00DB'] = "Ucirc";
        unicodeToEntity['\u00DC'] = "Uuml";
        unicodeToEntity['\u00DD'] = "Yacute";
        unicodeToEntity['\u00DE'] = "THORN";
        unicodeToEntity['\u00DF'] = "szlig";
        unicodeToEntity['\u00E0'] = "agrave";
        unicodeToEntity['\u00E1'] = "aacute";
        unicodeToEntity['\u00E2'] = "acirc";
        unicodeToEntity['\u00E3'] = "atilde";
        unicodeToEntity['\u00E4'] = "auml";
        unicodeToEntity['\u00E5'] = "aring";
        unicodeToEntity['\u00E6'] = "aelig";
        unicodeToEntity['\u00E7'] = "ccedil";
        unicodeToEntity['\u00E8'] = "egrave";
        unicodeToEntity['\u00E9'] = "eacute";
        unicodeToEntity['\u00EA'] = "ecirc";
        unicodeToEntity['\u00EB'] = "euml";
        unicodeToEntity['\u00EC'] = "igrave";
        unicodeToEntity['\u00ED'] = "iacute";
        unicodeToEntity['\u00EE'] = "icirc";
        unicodeToEntity['\u00EF'] = "iuml";
        unicodeToEntity['\u00F0'] = "eth";
        unicodeToEntity['\u00F1'] = "ntilde";
        unicodeToEntity['\u00F2'] = "ograve";
        unicodeToEntity['\u00F3'] = "oacute";
        unicodeToEntity['\u00F4'] = "ocirc";
        unicodeToEntity['\u00F5'] = "otilde";
        unicodeToEntity['\u00F6'] = "ouml";
        unicodeToEntity['\u00F7'] = "divide";
        unicodeToEntity['\u00F8'] = "oslash";
        unicodeToEntity['\u00F9'] = "ugrave";
        unicodeToEntity['\u00FA'] = "uacute";
        unicodeToEntity['\u00FB'] = "ucirc";
        unicodeToEntity['\u00FC'] = "uuml";
        unicodeToEntity['\u00FD'] = "yacute";
        unicodeToEntity['\u00FE'] = "thorn";
        unicodeToEntity['\u00FF'] = "yuml";
        entityToUnicode = new HashMap<String,String>();
        for (int i = 0; i < unicodeToEntity.length; i++)
        {
            if (unicodeToEntity[i] == null)
                continue;
            entityToUnicode.put(unicodeToEntity[i], String.valueOf((char)i));
        }
    };
    
    public static String insertEntities(String str)
    {
        StringBuffer ret = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if ((c[i] > 0x00ff) || (c[i] < 0x0020))
            {
                ret.append("&#");
                ret.append((int)c[i]);
                ret.append(";");
            }
            else if (unicodeToEntity[c[i]] != null)
            {
                ret.append("&");
                ret.append(unicodeToEntity[c[i]]);
                ret.append(";");
            }
            else
                ret.append(c[i]);
        }
        return ret.toString();
    }
    
    public static String removeEntities(String str)
    {
        StringBuffer ret = new StringBuffer();
        for (;;)
        {
            int o1 = str.indexOf("&");
            if (o1 < 0)
            {
                ret.append(str);
                break;
            }
            ret.append(str.substring(0, o1));
            int o2 = str.indexOf(";", o1);
            String ent = str.substring(o1+1, o2);
            String uni = (String)entityToUnicode.get(ent);
            if (uni != null)
                ret.append(uni);
            str = str.substring(o2+1);
        }
        return ret.toString();
    }
    
    public static String normalizePath(String path)
    {
        StringBuffer ret = new StringBuffer();
        char[] c = path.toCharArray();
        for (int i = 0; i < c.length; i++)
            if (c[i] == '\\')
                ret.append('/');
            else if (Character.isUpperCase(c[i]))
                ret.append(Character.toLowerCase(c[i]));
            else
                ret.append(c[i]);
        return ret.toString();
    }
    
    public static String[] copyStringArray(String[] arr)
    {
        String[] ret = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
            ret[i] = arr[i];
        return ret;
    }
    
    public static boolean compareStringArray(String[] s1, String[] s2)
    {
        if (s1.length != s2.length)
            return false;
        for (int i = 0; i < s1.length; i++)
            if (!s1.equals(s2))
                return false;
        return true;
    }

    public static String fromStringArray(String[] arr, String delim)
    {
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < arr.length; i++)
        {
            if (i > 0)
                ret.append(delim);
            ret.append(arr[i]);
        }
        return ret.toString();
    }

    public static String[] toStringArray(String arr, String delim)
    {
        StringTokenizer st = new StringTokenizer(arr, delim);
        String[] ret = new String[st.countTokens()];
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = st.nextToken();
        }
        return ret;
    }

    public static String[] dup(String[] arr)
    {
        String[] ret = new String[arr.length];
        System.arraycopy(arr, 0, ret, 0, arr.length);
        return ret;
    }
    
    public static String trimAll(String str)
    {
        StringBuffer ret = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++)
            if (!Character.isWhitespace(c[i]))
                ret.append(c[i]);
        return ret.toString();
    }

    public static int indexOf(String str, String[] patt)
    {
        return indexOf(str, patt, 0);
    }
    public static int indexOf(String str, String[] patt, int start)
    {
        int best = -1;
        for (int i = 0; i < patt.length; i++)
        {
            int o = str.indexOf(patt[i], start);
            if ((o >= 0) && ((best == -1) || (o < best)))
                best = o;
        }
        return best;
    }

    public static String webify(String s)
    {
        if (s == null)
            return null;
        StringBuffer ret = new StringBuffer();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (((c[i] >= '_') && (c[i] <= 'z'))
                    || ((c[i] >= 'A') && (c[i] <= 'Z'))
                    || ((c[i] >= '0') && (c[i] <= '9'))
                    || ((c[i] >= '\u0100') && (c[i] <= '\uffff'))
                    || (c[i] == '.'))
                ret.append(String.valueOf(c[i]));
            else
            {
                ret.append("%");
                String h = Integer.toHexString((int)c[i]).toUpperCase();
                if (h.length() > 2)
                    h = h.substring(h.length() - 2);
                else if (h.length() == 1)
                    h = "0" + h;
                ret.append(h);
            }
        }
        return ret.toString();
    }

    public static String unwebify(String s)
    {
        if (s == null)
            return null;
        StringBuffer ret = new StringBuffer();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i] == '%')
            {
                char ch = (char)(Integer.parseInt(new String(c, i + 1, 2), 16));
                ret.append(String.valueOf(ch));
                i += 2;
            }
            else
            {
                ret.append(String.valueOf(c[i]));
            }
        }
        return ret.toString();
    }

	public static int indexNotOf(String str, String allowed) 
	{
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++)
			if (allowed.indexOf(ch[i]) < 0)
				return i;
		return -1;
	}
    
    public static int countOccurances(String text, String pattern)
    {
        int count = 0;
        for (int o = 0; o < text.length(); o++)
        {
            o = text.indexOf(pattern, o+1);
            if (o == -1)
                break;
            count++;
        }
        return count;
    }

    public static String initialCaptial(String name)
    {
        if (name == null)
            return null;
        char[] c = name.toCharArray();
        if (Character.isLowerCase(c[0]))
            c[0] = Character.toUpperCase(c[0]);
        return new String(c);
    }

    public static String trimNewlines(String txt)
    {
        StringBuffer t = new StringBuffer(txt);
        while (t.length() > 0)
            if ("\r\n".indexOf(t.charAt(0)) >= 0)
                t.replace(0, 1, "");
            else
                break;
        while (t.length() > 0)
            if ("\r\n".indexOf(t.charAt(t.length() - 1)) >= 0)
                t.setLength(t.length() - 1);
            else
                break;
        return t.toString();
    }

    public static String safe(String txt)
    {
        if (txt == null)
            return "";
        return txt;
    }
    public static String escapeUnicode(String txt)
    {
        StringBuffer sb = new StringBuffer();
        char[] c = txt.toCharArray();
        for (int i = 0; i < c.length; i++)
            if ((c[i] >= ' ') && (c[i] < 0x80))
                sb.append(c[i]);
            else
                sb.append("\\u"+zeroPrefix(Integer.toHexString(c[i]), 4));
        return sb.toString();
    }

    public static int compareTo(String s1, String s2)
    {
        if (s1 == null)
            if (s2 == null)
                return 0;
            else
                return -1;
        else
            if (s2 == null)
                return 1;
            else
                return s1.compareTo(s2);
    }
    
    public static boolean isAllDigits(String txt)
    {
        if (isTrivial(txt))
            return false;
        for (char c : txt.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }
}
