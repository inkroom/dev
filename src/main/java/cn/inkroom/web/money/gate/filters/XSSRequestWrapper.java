package cn.inkroom.web.money.gate.filters;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;


public class XSSRequestWrapper extends HttpServletRequestWrapper {
	protected final Log log = LogFactory.getLog(getClass());
    protected XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    private static String EDITOR_FILTER = "content";

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (!parameter.equals(EDITOR_FILTER)) {
            if (values == null) {
                return null;
            }

            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                //log.debug("---------------------"+values[i]+"-------------------------");
                encodedValues[i] = stripXSS(values[i]);
            }
            return encodedValues;
        }else {
            if (values == null) {
                return null;
            }

            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                //log.debug("---------------------"+values[i]+"-------------------------");
                encodedValues[i] = stripLittleXSS(values[i]);
            }
            return values;
        }


    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (!parameter.equals(EDITOR_FILTER)) {
            //log.debug("---------------------getParameter-------------------------");
            //log.debug("---------------------parameter-------------------------"+parameter);

            ////log.debug("---------------------value-------------------------"+value);
            return stripXSS(value);
        }else {
            ////log.debug("---------------------value-------------------------"+value);
            return stripLittleXSS(value);
        }


    }
    
	@Override
    public Map<String, String[]> getParameterMap() {
    	//log.debug("---------------------getParameterMap-------------------------");
    	// Map<String,String[]> paramMap = super.getParameterMap();
    	 HashMap<String,String[]>  paramMap = new HashMap<String,String[]> ();
    	 Enumeration<String> enums = super.getRequest().getParameterNames();
    	 while (enums.hasMoreElements()){
             String aParam = enums.nextElement();
             String[]  values=super.getRequest().getParameterValues(aParam);    
             paramMap.put(aParam,values);
         }

    	 //paramMap =  (HashMap<String, String[]>) paramMap.clone();
        for (Entry<String, String[]> entry : paramMap.entrySet()) {
            String[] values = entry.getValue();
            String key = entry.getKey();
            if (!key.equals(EDITOR_FILTER)) {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null) {
                        values[i] = stripXSS(values[i]);
                        //log.debug("---------------------"+values[i]+"-------------------------");
                    }
                }
            } else {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null) {
                        values[i] = stripLittleXSS(values[i]);
                        //log.debug("---------------------"+values[i]+"-------------------------");
                    }
                }
            }
            entry.setValue(values);
        }
         return paramMap;

    }

    @Override
    public String getHeader(String name) {
    	//log.debug("---------------------getHeader-------------------------");
    	//log.debug("---------------------name-------------------------"+name);
    	//log.debug("---------------------super.getHeader(name)-------------------------"+super.getHeader(name));
        if (!name.equals(EDITOR_FILTER)) {
            String value = super.getHeader(name);
            return stripXSS(value);
        }else {
            String value = super.getHeader(name);
            return stripLittleXSS(value);
        }
    }

    public static String stripXSS(String value) {
    	 if (value != null) {
             // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
             // avoid encoded attacks.
             // value = ESAPI.encoder().canonicalize(value);

             // Avoid null characters
             value = value.replaceAll("", "");

             // Avoid anything between script tags
             Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
             value = scriptPattern.matcher(value).replaceAll("");
             
             // Avoid anything in a src='...' type of e­xpression
             scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
             value = scriptPattern.matcher(value).replaceAll("");

             scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
             value = scriptPattern.matcher(value).replaceAll("");

             // Remove any lonesome </script> tag
             scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
             value = scriptPattern.matcher(value).replaceAll("");

             // Remove any lonesome <script ...> tag
             scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
             value = scriptPattern.matcher(value).replaceAll("");

             // Avoid eval(...) e­xpressions
             scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
             value = scriptPattern.matcher(value).replaceAll("");

             // Avoid e­xpression(...) e­xpressions
             scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
             value = scriptPattern.matcher(value).replaceAll("");

             // Avoid javascript:... e­xpressions
             scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
             value = scriptPattern.matcher(value).replaceAll("");

             // Avoid vbscript:... e­xpressions
             scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
             value = scriptPattern.matcher(value).replaceAll("");

             // Avoid onload= e­xpressions
             scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
             value = scriptPattern.matcher(value).replaceAll("");
             //Avoid < > ' '
             scriptPattern = Pattern.compile("<.*?>");
             value = scriptPattern.matcher(value).replaceAll("");
             
             scriptPattern = Pattern.compile("\\(.*?\\)");
             value = scriptPattern.matcher(value).replaceAll("");
             
             scriptPattern = Pattern.compile("\\'.*?\\'");
             value = scriptPattern.matcher(value).replaceAll("");
             
         }
    	 return value;
    }



    public static String stripLittleXSS(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("\\(.*?\\)");
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("\\'.*?\\'");
            value = scriptPattern.matcher(value).replaceAll("");

        }
        return value;
    }
}