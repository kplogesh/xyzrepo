%ifvar message -notempty%
	%rename message erase%
%endif%
%ifvar operation -notempty%
	%switch operation%
	%case 'addProperty'%
		%invoke CEFConfigurationProperties:AddPropertiesElement%
		%end invoke%
	%case 'deleteProperty'%
		%invoke CEFConfigurationProperties:RemovePropertiesElement%
		%end invoke%
	%end%
%endif%
<html>
   <head>
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv='content-type' content='text/html; charset=utf-8'>
      <meta http-equiv="expires" content="-1">
      <title>Multichoice CEFConfigurationProperties package: Configurations Edit</title>
      <link rel="stylesheet" type="text/css" href="webMethods.css"></link>
      <script src="display.js"></script>
      <script src="validate.js"></script>
      <script language="JavaScript">
         function getInnerText(element) {
            var xResult = "";

            if (typeof element == "string")
               return element;
            else if (typeof element == "undefined")
               return element;
            else if (element.innerText)
               xResult = element.innerText;   //Not needed, but it is faster
            else if (element.innerHTML)
               xResult = element.innerHTML;
            else
               {
               var xChildNodes      = element.childNodes;
               var xChildNodesCount = xChildNodes.length;

               xResult = "";
               for(var xIdx = 0; xIdx < xChildNodesCount; xIdx++)
                  {
                  switch (xChildNodes[xIdx].nodeType)
                     {
                     case 1:     // ELEMENT_NODE
                        xResult += getInnerText(xChildNodes[xIdx]);
                        break;

                     case 3:     // TEXT_NODE
                        xResult += xChildNodes[xIdx].nodeValue;
                        break;
                     }
                  }
               }

            return xResult;
         }
         function confirmPropertyDelete (configuration, property) {
           var s1 = "OK to Delete the '"+property+"' Property in the '"+configuration+"' Configuration ?\n\n";
           return confirm(s1);
         }
         function populateForm(propertyname, valueId) {
            window.document.addProperty.name.value  = propertyname;
            window.document.addProperty.value.value = document.getElementById(valueId).value;
         }
      </script>
      <style>
         input#edit-property-name, textarea#edit-property-value { width: 95%; }
         textarea#edit-property-value {
            font-family: "courier new", "courier";
            font-size: 110%;
            }
      </style>
   </head>
   <body>
      %invoke CEFConfigurationProperties:HTMLEncodeGetProperties%
      %onerror%
         <script>writeMessage("%value errorMessage%");</script>
      %end invoke%

      <table width="100%">
         <tr>
            <td height="10">&nbsp;</td>
            <td>
               <table width="100%">
                  <tr>
                     <td class="menusection-Security" colspan="4" nowrap>Packages &gt; CEFConfigurationProperties &gt; Edit &gt; %value configuration% Configuration</td>
                  </tr>
                  %ifvar message -notempty%
                     <tr><td colspan="5">&nbsp;</td></tr>
                     <tr><td class="message" colspan="4">%value message encode(none)%</td></tr>
                  %endif%
                  <tr>
                     <td colspan="3">
                        <ul>
                           <li><a href="configuration-list.dsp">Return to configurations list</a></li>
                        </ul>
                     </td>
                  </tr>
                  <tr>
                     <td class="heading" colspan="4">Configuration Details %value configuration%%value name%%value operation%</td>
                  </tr>
                  <tr>
                     <script>writeTD('row','width="30%"');</script>Name</td>
                     <script>writeTD('rowdata-l','colspan="3"');</script>%value configuration%</td>
                  	<script>swapRows();</script>
                  </tr>
                  <tr>
                     <script>writeTD('row');</script>Comment</td>
                     <script>writeTD('rowdata-l', 'colspan="3"');</script>%value comment encode(none)%</td>
                  	<script>swapRows();</script>
                  </tr>
                  <tr>
                     <td class="heading" colspan="4">Configuration Properties</td>
                  </tr>
                  <tr>
                     <td class="oddcol" nowrap>Name</td>
                     <td class="oddcol" nowrap>Value</td>
                     <td class="oddcol" width="20" nowrap>Change</td>
                     <td class="oddcol" width="20" nowrap>Delete</td>
                  </tr>
            		%ifvar propertiesTable -notempty%
                     %loop propertiesTable%
                        <tr>
                           <script>writeTD('row');</script>%value name encode(none)%</td>
                           <script>writeTD('rowdata-l','id="row%value $index%"');</script>%value encodedValue encode(none)%</td>
                           <script>writeTD('rowdata');</script><a class="imagelink" href="javascript:populateForm('%value name encode(none)%', 'raw%value $index%');"><img src="images/copy.gif" border="0"></a></td>
                           <script>writeTD('rowdata');swapRows();</script><a class="imagelink" href="configuration-edit.dsp?operation=deleteProperty&configuration=%value /configuration encode(url)%&name=%value name encode(url)%"
                           onclick="return confirmPropertyDelete('%value /configuration encode(none)%', '%value name encode(none)%');"><img src="images/delete.gif" border="0"></a>
                           <textarea id="raw%value $index%" style="display:none;">%value value encode(xml)%</textarea></td>
                        </tr>
                     %end loop%
                  %else%
                     <tr><td class="message" colspan="8">No properties found</td></tr>
                  %endif%
               </table>
               <br>
               <form name="addProperty" method="post" action="configuration-edit.dsp">
                  <table valign="middle" width="100%">
                     <tr>
                        <td class="heading" colspan="2">Add/Change Property</td>
                     </tr>
                     <input type="hidden" name="operation" value="addProperty" />
                     <input type="hidden" name="configuration" value="%value configuration%" />
                     <tr>
                        <script>writeTD('row');</script>Name</td>
                        <script>writeTD('rowdata-l');</script><input type="text" name="name" id="edit-property-name" value=""/></td>
                        <script>swapRows();</script>
                     </tr>
                     <tr>
                        <script>writeTD('row');</script>Value</td>
                        <script>writeTD('rowdata-l');</script><textarea name="value" id="edit-property-value"></textarea></td>
                     </tr>
                  </table>
                  <table width="100%">
                     <tr>
                        <td class="action" colspan="2">
                           <input type="submit" name="SUBMIT" value="Save Changes" onclick="return validateFormAddProperty(this.form)" />
                        </td>
                     </tr>
                  </table>
               </form>
            </td>
         </tr>
      </table>
   </body>
</html>
