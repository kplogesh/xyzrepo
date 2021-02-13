package CEFConfigurationProperties;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSName;
import java.util.regex.*;
import java.util.*;
// --- <<IS-END-IMPORTS>> ---

public final class internal

{
	// ---( internal utility methods )---

	final static internal _instance = new internal();

	static internal _newInstance() { return new internal(); }

	static internal _cast(Object o) { return (internal)o; }

	// ---( server methods )---




	public static final void ApplySearchFilter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(ApplySearchFilter)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required regexp
		// [i] field:0:optional context {"value","name","both"}
		// [i] record:1:required configurations
		// [i] - field:0:required configuration
		// [i] - field:0:required comment
		// [i] - record:1:required properties
		// [i] -- field:0:required name
		// [i] -- field:0:required value
		// [o] record:1:optional configurations
		// [o] - field:0:required configuration
		// [o] - field:0:required comment
		// [o] - record:1:required properties
		// [o] -- field:0:required name
		// [o] -- field:0:required value
		
		IDataCursor xPipeline_CUR  = pipeline.getCursor();
		
		   try{
		      String xRegExp = IDataUtil.getString(xPipeline_CUR, "regexp");
		
		      if (null == xRegExp || xRegExp.trim().length() == 0)
		         IDataUtil.remove(xPipeline_CUR, "configurations");
		      else
		         {
		         IData[] xConfigurations = IDataUtil.getIDataArray(xPipeline_CUR, "configurations");
		
		         if (null != xConfigurations && xConfigurations.length > 0)
		            {
		            xRegExp = xRegExp.trim();
		
		            if (!(".*".equals(xRegExp) || "^.*".equals(xRegExp) || "^.*$".equals(xRegExp)))  // shortcut evaluation: do not apply filter if result will be ALL properties
		               {
		               Pattern  xSearchExp  = Pattern.compile ( xRegExp
		                                                      , Pattern.MULTILINE
		                                                      );
		               Vector   xResult     = new Vector();
		               String   xContext    = IDataUtil.getString(xPipeline_CUR, "context");
		               String[] xFieldNames = null;
		
		               if ("value".equals(xContext))
		                  xFieldNames = new String[] { "value" };
		               else if ("name".equals(xContext))
		                  xFieldNames = new String[] { "name" };
		               else if ("both".equals(xContext))
		                  xFieldNames = new String[] { "name", "value" };
		               else
		                  xFieldNames = new String[] { "value" };
		
		               // configurations
		               for(int xCfgIdx = 0; xCfgIdx < xConfigurations.length; xCfgIdx++)
		                  {
		                  IDataCursor xConfiguration_CUR = xConfigurations[xCfgIdx].getCursor();
		                     boolean  xIsConfigurationSelected   = false;
		                     IData[]  xProperties                = IDataUtil.getIDataArray(xConfiguration_CUR, "properties");
		
		                     if (null != xProperties && xProperties.length > 0)
		                        {
		                        Vector   xMatchingProperties  = new Vector();
		
		                        for(int xPropIdx = 0; xPropIdx < xProperties.length; xPropIdx++)
		                           {
		                           boolean     xIsPropertySelected  = false;
		                           IDataCursor xProperty_CUR        = xProperties[xPropIdx].getCursor();
		
		                           for(int xIdx = 0; xIdx < xFieldNames.length && !xIsPropertySelected; xIdx++)
		                              {
		                              String   xFieldValue          = IDataUtil.getString(xProperty_CUR, xFieldNames[xIdx]);
		
		                              if (null == xFieldValue || "".equals(xFieldValue.trim()))
		                                 {  // allow empty matched to be evaluated (expression cannot be multiline, otherwise result is always false)
		                                    Matcher xValueMatcher   = Pattern.compile(xRegExp).matcher("");  // as empty value
		
		                                    xIsPropertySelected     = xValueMatcher.matches();
		                                 }
		                              else
		                                 {  // evaluate normal
		                                    Matcher xValueMatcher   = xSearchExp.matcher(xFieldValue);
		
		                                    xIsPropertySelected     = xValueMatcher.matches();
		                                 }
		
		                              if (xIsPropertySelected)
		                                 xMatchingProperties.add(xProperties[xPropIdx]);
		                              }
		                           xProperty_CUR.destroy();
		                           }
		
		                        if (xMatchingProperties.size() > 0)    // replace with matching set
		                           {
		                           IDataUtil.put(xConfiguration_CUR, "properties", (IData[])xMatchingProperties.toArray(new IData[] {}));
		                           xIsConfigurationSelected = true;
		                           }
		                        }
		
		                     if (xIsConfigurationSelected)
		                        xResult.add(xConfigurations[xCfgIdx]);
		
		                  xConfiguration_CUR.destroy();
		                  }
		
		               // output
		               if (xResult.size() > 0)
		                  IDataUtil.put(xPipeline_CUR, "configurations", (IData[])xResult.toArray(new IData[] {}));
		               else
		                  IDataUtil.remove(xPipeline_CUR, "configurations");
		               }
		            }
		         }
		      }
		   catch(Throwable e)
		      {
		      if (e instanceof ServiceException)
		         throw (ServiceException)e;
		      else
		         throw new ServiceException(e);
		      }
		   finally
		      {
		      xPipeline_CUR.destroy();
		      }
		// --- <<IS-END>> ---

                
	}



	public static final void ResetCachedServices (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(ResetCachedServices)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		String   xPackageName   = Service.getPackageName();
		String[] xServiceNames  = new String[] { xPackageName + ":GetProperties"
		                                       , xPackageName + ":GetConfigurations"
		                                       , xPackageName + ":GetPropertiesElement"
		                                       };
		
		try{
		   NSName      xResetCacheNS     = NSName.create("wm.server.cache:resetCache");
		   IData       xServiceInput     = IDataFactory.create();
		   IDataCursor xServiceInput_CUR = xServiceInput.getCursor();
		
		   for(int xIdx = 0; xIdx < xServiceNames.length; xIdx++)
		      {
		      IDataUtil.put(xServiceInput_CUR, "serviceName", xServiceNames[xIdx]);
		      Service.doInvoke(xResetCacheNS, xServiceInput);
		      }
		
		   xServiceInput_CUR.destroy();
		   }
		catch(Exception e)
		   {
		   throw new ServiceException(e);
		   }
			
		// --- <<IS-END>> ---

                
	}
}

