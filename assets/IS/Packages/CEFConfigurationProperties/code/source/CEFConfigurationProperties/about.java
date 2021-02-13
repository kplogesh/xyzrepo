package CEFConfigurationProperties;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class about

{
	// ---( internal utility methods )---

	final static about _instance = new about();

	static about _newInstance() { return new about(); }

	static about _cast(Object o) { return (about)o; }

	// ---( server methods )---




	public static final void GetInfo (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(GetInfo)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required packageName
		// [o] field:0:required packageVersion
		
		IDataCursor xPipeline_CUR        = pipeline.getCursor();
		
		try{
		   String                        xCurrentPackageName  = Service.getServiceEntry().getPackage().getName();
		   com.wm.app.b2b.server.Package xPackage             = com.wm.app.b2b.server.PackageManager.getPackage(xCurrentPackageName);
		   String                        xPackageVersion      = null;
		
		   if (xPackage != null)
		      xPackageVersion   = (String)xPackage.getProperty("version");
		   else
		      xPackageVersion   = "#undef#";
		
		   xPipeline_CUR.insertAfter("packageName"    , xCurrentPackageName);
		   xPipeline_CUR.insertAfter("packageVersion" , xPackageVersion    );
		   }
		catch(Exception e)
		   {
		   throw new ServiceException(e);
		   }
		finally
		   {
		   xPipeline_CUR.destroy();
		   }
		
		// --- <<IS-END>> ---

                
	}
}

