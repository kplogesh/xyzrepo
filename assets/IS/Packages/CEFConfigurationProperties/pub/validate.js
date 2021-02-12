function validateFormCreateConfiguration(theForm)
{
    var returnValue = 0;
    var errors = "Errors:\n";
	
    if (theForm.elements['configuration'] != null && theForm.elements['configuration'].value.length == 0) {
        returnValue = -1;
        errors += "Configuration name required.\n";
    }
    
    if (!/^\w*$/.test(theForm.elements['configuration'].value)) {
        returnValue = -1;
        errors += "Configuration Name contains invalid characters.\n";
    }
     
    if (returnValue) {
        alert(errors);
        return false;
    }
    else {
        return true;
    }
    
}


function validateFormAddProperty(theForm)
{

	var returnValue = 0;
    var errors = "Errors:\n";
	
    if (theForm.elements['name'] != null && theForm.elements['name'].value.length == 0) {
        returnValue = -1;
        errors += "Property name required.\n";
    }
    
     
    if (returnValue) {
        alert(errors);
        return false;
    }
    else {
        return true;
    }


}