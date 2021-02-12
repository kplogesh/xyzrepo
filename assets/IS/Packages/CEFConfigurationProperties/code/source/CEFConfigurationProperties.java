

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.dif.properties.PropertiesManager;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.wm.lang.ns.*;
// --- <<IS-END-IMPORTS>> ---

public final class CEFConfigurationProperties

{
	// ---( internal utility methods )---

	final static CEFConfigurationProperties _instance = new CEFConfigurationProperties();

	static CEFConfigurationProperties _newInstance() { return new CEFConfigurationProperties(); }

	static CEFConfigurationProperties _cast(Object o) { return (CEFConfigurationProperties)o; }

	// ---( server methods )---




	public static final void AddConfiguration (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(AddConfiguration)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required configuration
		// [i] field:0:required comment
		// [o] field:0:required message
		IDataCursor pipelineCursor= pipeline.getCursor();
		String configuration= IDataUtil.getString(pipelineCursor, "configuration");
		String comment= IDataUtil.getString(pipelineCursor, "comment");
		
		
		IDataUtil.put(pipelineCursor, "message", mSuccessMsg + "(" + configuration + ")");
		try { 
			PropertiesManager.addProperties(configuration, new Properties(), comment);
		}
		catch (Exception excp) {
			IDataUtil.put(pipelineCursor, "message", mErrorMsg + "(" + excp.getMessage() + ")");
		}
		pipelineCursor.destroy();
		resetCachedServices(pipeline);
			
		// --- <<IS-END>> ---

                
	}



	public static final void AddProperties (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(AddProperties)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required configuration
		// [i] field:0:required propertiesString
		// [o] field:0:required message
		IDataCursor pipelineCursor= pipeline.getCursor();
		String configuration= IDataUtil.getString(pipelineCursor, "configuration");
		String propertiesString= IDataUtil.getString(pipelineCursor, "propertiesString");
		
		IDataUtil.put(pipelineCursor, "message", mSuccessMsg + "(" + configuration + ")");
		try {
			PropertiesManager.pasteIntoProperties(configuration, propertiesString);
		}
		catch (Exception excp) {
			IDataUtil.put(pipelineCursor, "message", mErrorMsg + "(" + excp.getMessage() + ")");
		}
		pipelineCursor.destroy();	
		resetCachedServices(pipeline);
		
		// --- <<IS-END>> ---

                
	}



	public static final void AddPropertiesElement (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(AddPropertiesElement)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required configuration
		// [i] field:0:required name
		// [i] field:0:required value
		// [o] field:0:required message
		IDataCursor pipelineCursor= pipeline.getCursor();
		String configuration= IDataUtil.getString(pipelineCursor, "configuration");
		String name= IDataUtil.getString(pipelineCursor, "name");
		String value= IDataUtil.getString(pipelineCursor, "value");
		
		IDataUtil.put(pipelineCursor, "message", mSuccessMsg + "(" + name+ ")");
		try {
			PropertiesManager.addPropertiesElement(configuration, name, value);
		}
		catch (Exception excp) {
			IDataUtil.put(pipelineCursor, "message", mErrorMsg + "(" + excp.getMessage() + ")");
		}
		pipelineCursor.destroy();
		resetCachedServices(pipeline); 
			
		// --- <<IS-END>> ---

                
	}



	public static final void DuplicateConfiguration (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(DuplicateConfiguration)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required newConfigurationName
		// [i] field:0:required newConfigurationComment
		// [i] field:0:required configuration
		// [o] field:0:required message
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	newConfigurationName = IDataUtil.getString( pipelineCursor, "newConfigurationName" );
		String	newConfigurationComment = IDataUtil.getString( pipelineCursor, "newConfigurationComment" );
		String	configuration = IDataUtil.getString( pipelineCursor, "configuration" );
		
		
		IDataUtil.put(pipelineCursor, "message", mSuccessMsg + "(" + newConfigurationName+ ")");
		try {
			PropertiesManager.duplicateProperties(configuration,newConfigurationName,newConfigurationComment); 
		}
		catch (Exception excp) {
			IDataUtil.put(pipelineCursor, "message", mErrorMsg + "(" + excp.getMessage() + ")");
		}
		pipelineCursor.destroy();
		resetCachedServices(pipeline);
			
		// --- <<IS-END>> ---

                
	}



	public static final void GetConfigurations (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(GetConfigurations)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional complete
		// [i] record:0:optional filter
		// [i] - field:1:optional configurations
		// [i] - field:0:optional configurationsRE
		// [o] record:1:required output
		// [o] - field:0:required configuration
		// [o] - field:0:required comment
		// [o] - record:1:optional properties
		// [o] -- field:0:required name
		// [o] -- field:0:required value
		IDataCursor xPipeline_CUR = pipeline.getCursor();
		
		boolean  xIsComplete          = IDataUtil.getBoolean(xPipeline_CUR, "complete");
		String[] xConfigurationNames  = PropertiesManager.getKeys();
		
		if (xConfigurationNames == null || xConfigurationNames.length == 0)
		   return;
		 
		Arrays.sort(xConfigurationNames);
		
		String[] xNarrowConfigurationsList = null;
		Pattern  xNarrowConfigurationsRE   = null;
		
		if (xPipeline_CUR.first("filter"))
		   {
		   IData       xNarrow     = (IData)xPipeline_CUR.getValue();
		   IDataCursor xNarrow_CUR = xNarrow.getCursor();
		
		      if (xNarrow_CUR.first("configurations"))
		         {
		         xNarrowConfigurationsList = (String[])xNarrow_CUR.getValue();
		         Arrays.sort(xNarrowConfigurationsList);
		         }
		      else if (xNarrow_CUR.first("configurationsRE"))
		         xNarrowConfigurationsRE = Pattern.compile((String)xNarrow_CUR.getValue());
		
		   xNarrow_CUR.destroy();
		   }
		
		IData xOutputDocs[] = null;
		{
		   Vector xAuxiliar = new Vector(xConfigurationNames.length);
		
		   for(int xConfigIdx = 0; xConfigIdx < xConfigurationNames.length; xConfigIdx++)
		      {
		      boolean xIsSelectable = true;
		
		      if (null != xNarrowConfigurationsList)
		         xIsSelectable = (Arrays.binarySearch(xNarrowConfigurationsList, xConfigurationNames[xConfigIdx]) > -1);
		      else if (null != xNarrowConfigurationsRE)
		         xIsSelectable = xNarrowConfigurationsRE.matcher(xConfigurationNames[xConfigIdx]).matches();
		
		      if (xIsSelectable)
		         {
		         IData xTmp = IDataFactory.create();
		
		         IDataCursor xTmp_CUR = xTmp.getCursor();
		         IDataUtil.put(xTmp_CUR, "configuration", xConfigurationNames[xConfigIdx]);
		         IDataUtil.put(xTmp_CUR, "comment"      , PropertiesManager.getPropertiesComment(xConfigurationNames[xConfigIdx]));
		
		         if (xIsComplete)
		            {
		            Properties xProperties  = PropertiesManager.getProperties(xConfigurationNames[xConfigIdx]);
		            String[]    xKeys       = (String[])xProperties.keySet().toArray(new String[0]);
		
		            Arrays.sort(xKeys);
		            IData xPropertyDocs[] = new IData[xKeys.length];
		            for(int xIdx = 0; xIdx < xKeys.length; xIdx++)
		               {
		               xPropertyDocs[xIdx] = IDataFactory.create();
		               IDataCursor xPropertyDocsCursor = xPropertyDocs[xIdx].getCursor();
		               IDataUtil.put(xPropertyDocsCursor, "name", xKeys[xIdx]);
		               IDataUtil.put(xPropertyDocsCursor, "value", xProperties.getProperty(xKeys[xIdx]));
		               xPropertyDocsCursor.destroy();
		               }
		            IDataUtil.put(xTmp_CUR, "properties", xPropertyDocs);
		            }
		         xTmp_CUR.destroy();
		
		         xAuxiliar.add(xTmp);
		         }
		      }
		
		   if (xAuxiliar.size() > 0)
		      xOutputDocs = (IData[])xAuxiliar.toArray(new IData[] {});
		}
		
		if (null != xOutputDocs)
		   IDataUtil.put(xPipeline_CUR, "output", xOutputDocs);
		
		xPipeline_CUR.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void GetProperties (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(GetProperties)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required configuration
		// [i] field:0:required list
		// [o] record:1:required propertiesTable
		// [o] - field:0:required name
		// [o] - field:0:required value
		// [o] field:0:required comment
		// [o] record:0:required propertiesList
		IDataCursor pipelineCursor= pipeline.getCursor();
		String configuration= IDataUtil.getString(pipelineCursor, "configuration");
		boolean lList= IDataUtil.getBoolean(pipelineCursor, "list");
		pipelineCursor.destroy();
		
		Properties lconfigProperties= PropertiesManager.getProperties(configuration);
		
		if (lconfigProperties == null || lconfigProperties.size() == 0)
			return;
		
		
		IData propertiesTable[]= new IData[lconfigProperties.size()];
		IData propertiesList= null;
		IDataCursor propertiesListCursor= null;
		
		if (lList) {
			propertiesList= IDataFactory.create();
			propertiesListCursor= propertiesList.getCursor();
		}
		String[] lPropertiesKeys= (String[])lconfigProperties.keySet().toArray(new String[0]);
		Arrays.sort(lPropertiesKeys);
		
		for (int ij= 0; ij < lPropertiesKeys.length; ij++) {
		
			propertiesTable[ij]= IDataFactory.create();
			IDataCursor propertiesCursor= propertiesTable[ij].getCursor();
			IDataUtil.put(propertiesCursor, "name", lPropertiesKeys[ij]);
			IDataUtil.put(propertiesCursor, "value", lconfigProperties.getProperty(lPropertiesKeys[ij]));
			propertiesCursor.destroy();
			if (lList) {
				IDataUtil.put(propertiesListCursor, lPropertiesKeys[ij], lconfigProperties.getProperty(lPropertiesKeys[ij]));
			}
		}
		
		if (lList) {
			propertiesListCursor.destroy();
		}
		
		IDataCursor pipelineCursorOut= pipeline.getCursor();
		IDataUtil.put(pipelineCursorOut, "comment", PropertiesManager.getPropertiesComment(configuration));
		IDataUtil.put(pipelineCursorOut, "propertiesTable", propertiesTable);
		IDataUtil.put(pipelineCursorOut, "propertiesList", propertiesList);
		pipelineCursorOut.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void GetPropertiesElement (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(GetPropertiesElement)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required configuration
		// [i] field:0:required name
		// [o] field:0:required value
		IDataCursor pipelineCursor = pipeline.getCursor();
		String configuration = IDataUtil.getString(pipelineCursor, "configuration");
		String name = IDataUtil.getString(pipelineCursor, "name");
		
		if (configuration == null || configuration.length() == 0)
		   return;
		 
		if (name == null || name.length() == 0)
		   return;
		
		Properties lLocalProperties= PropertiesManager.getProperties(configuration);
		
		if (lLocalProperties == null || lLocalProperties.size() == 0)
		   return;
		
		IDataUtil.put(pipelineCursor, "value", lLocalProperties.getProperty(name));
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void LoadConfigurations (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(LoadConfigurations)>> ---
		// @subtype unknown
		// @sigtype java 3.5
   try{
      PropertiesManager.loadProperties(getPropertiesFolderName() + File.separator);
      }
   catch(Throwable e)
      {
      if (e instanceof ServiceException)
         throw (ServiceException)e;
      else
         throw new ServiceException(e);
      }
   resetCachedServices(pipeline);
	
		// --- <<IS-END>> ---

                
	}



	public static final void RemoveConfiguration (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(RemoveConfiguration)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required configuration
		// [o] field:0:required message
		IDataCursor pipelineCursor= pipeline.getCursor();
		String configuration= IDataUtil.getString(pipelineCursor, "configuration");
		
		IDataUtil.put(pipelineCursor, "message", mSuccessMsg + "(" + configuration+ ")");
		
		try {
			PropertiesManager.removeProperties(configuration);
		}
		catch (Exception excp) {
			IDataUtil.put(pipelineCursor, "message", mErrorMsg + "(" + excp.getMessage() + ")");
		}
		pipelineCursor.destroy();
		resetCachedServices(pipeline);	
		
		// --- <<IS-END>> ---

                
	}



	public static final void RemovePropertiesElement (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(RemovePropertiesElement)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required configuration
		// [i] field:0:required name
		// [o] field:0:required message
		IDataCursor pipelineCursor= pipeline.getCursor();
		String configuration= IDataUtil.getString(pipelineCursor, "configuration");
		String name= IDataUtil.getString(pipelineCursor, "name");
		
		IDataUtil.put(pipelineCursor, "message", mSuccessMsg + "(" + name+ ")");
		try {
			PropertiesManager.removePropertiesElement(configuration, name);
		}
		catch (Exception excp) {
			IDataUtil.put(pipelineCursor, "message", mErrorMsg + "(" + excp.getMessage() + ")");
		}
		pipelineCursor.destroy();
		resetCachedServices(pipeline);	
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	// (old) private static final String mConfigDir    = "config";
	// (old) private static final String mPackagesDir  = "packages";
	
	private static final String mSuccessMsg   = "Operation performed with success";
	private static final String mErrorMsg     = "Error performing operation ";
	
	/**
	 * Get the name of the folder where the properties are located.
	 * Returns  A relative path, **NOT** terminated by a folder separator, directly usable to access the property files.
	 */
	private static String getPropertiesFolderName()
	   throws ServiceException
	{
	   // (old) return mPackagesDir + File.separator + Service.getPackageName() + File.separator + mConfigDir;
	   try{
	      String      xResult = null;
	      IData       xOutput = Service.doInvoke ( NSName.create("CEFConfigurationProperties.internal:propertiesDir")
	                                             , null
	                                             );
	
	      IDataCursor xOutput_CUR = xOutput.getCursor();
	      try{
	         if (xOutput_CUR.first("dir"))
	            xResult = (String)xOutput_CUR.getValue();
	         else if (xOutput_CUR.first("message"))
	            throw new ServiceException((String)xOutput_CUR.getValue());
	         else
	            throw new ServiceException("Could not resolve the name of the folder for the property files.");
	         }
	      finally
	         {
	         xOutput_CUR.destroy();
	         }
	
	      return xResult;
	      }
	   catch(Exception e)
	      {
	      if (e instanceof ServiceException)
	         throw (ServiceException)e;
	      else
	         throw new ServiceException(e);
	      }
	}
	
	/**
	 * Reset the cache for all package service that are cached.
	 * @param   pipeline
	 *             Any <u>not-null</u> pipeline.
	 */
	private static void resetCachedServices(IData pipeline)
	   throws ServiceException
	{
	   // This code was changed from an internal Java call to a service invoke.
	   // This change was made to make the source code compatible with the
	   //    webMethods v8.2 Java Service editor in Designer:
	   //    - The Designer generates a class per service when editing.
	   //      This class doen not reflect the final generated code, but causes
	   //      Java source errors while being edited.
	   try{
	      Service.doInvoke  ( NSName.create( "CEFConfigurationProperties.internal:ResetCachedServices")
	                        , pipeline
	                        );
	      }
	   catch(Exception e)
	      {
	      if (e instanceof ServiceException)
	         throw (ServiceException)e;
	      else
	         throw new ServiceException(e);
	      }
	}
		
		
	// --- <<IS-END-SHARED>> ---
}

