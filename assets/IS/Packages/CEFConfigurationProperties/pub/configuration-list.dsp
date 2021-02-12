%ifvar message -notempty%
	%rename message erase%
%endif%
%ifvar operation -notempty%
	%switch operation%
	%case 'create'%
		%invoke CEFConfigurationProperties:AddConfiguration%
		%end invoke%
	%case 'delete'%
		%invoke CEFConfigurationProperties:RemoveConfiguration%
		%end invoke%
   %case 'reload'%
      %invoke CEFConfigurationProperties:LoadConfigurations%
      %end invoke%
	%end%
%endif%
<html>
   <head>
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="content-type" content="text/html; charset=utf-8">
      <meta http-equiv="expires" content="-1">
      <title>Multichoice CEFConfigurationProperties package: Configurations List</title>
      <link rel="stylesheet" type="text/css" href="webMethods.css"/>
      <script src="display.js"></script>
      <script language="JavaScript">
         function confirmConfigDelete (configuration) {
            var s1 = "OK to Delete the '"+configuration+"' Configuration?\n\n";
            var s2 = "The configuration will be totaly erased \n";
            return confirm(s1+s2);
         }
      </script>
      <style>
         li a em {
            font-weight: bold;
            font-style: normal;
         }
      </style>
   </head>
   <body>
      <table width="100%">
         <tr>
            <td height="10">&nbsp;</td>
            <td>
            	<table width="100%">
                  <tr>
                     <td class="menusection-Security" colspan="5" nowrap>Packages &gt; CEFConfigurationProperties &gt; List</td>
                  </tr>
                  %ifvar message -notempty%
                     <tr><td colspan="5">&nbsp;</td></tr>
                     <tr><td class="message" colspan="5">%value message encode(none)%</td></tr>
                     %rename message erase%
                  %endif%
                  %invoke CEFConfigurationProperties.ui:listConfigurations%
                  %onerror%
                     <tr><td colspan="5">&nbsp;</td></tr>
                     <tr><td class="message" colspan="5">%value errorMessage encode(none)%</td></tr>
                  %end invoke%
                  <tr>
                     <td colspan="5">
                        <ul>
                           <li><a href="configuration-new.dsp">Create Configuration</a></li>
                        </ul>
                     </td>
                  </tr>
                  <tr>
                     <td class="heading" colspan="5">Configurations List</td>
                  </tr>
                  <tr>
                     <td class="oddcol-l" nowrap>Configuration Name</td>
                     <td class="oddcol" nowrap>Comment</td>
                     <td class="oddcol" nowrap>Change</td>
                     <td class="oddcol" nowrap>View</td>
                     <td class="oddcol" nowrap>Delete</td>
                  </tr>
                  %ifvar output -notempty%
                     %loop output%
                        <tr>
                           <script>writeTD('rowdata-l');</script><a href="configuration-view.dsp?configuration=%value configuration encode(url)%">%value configuration%</a></td>
                           <script>writeTD('rowdata');</script>%value comment%</td>
                           <script>writeTD('rowdata');</script>
                           <a href="configuration-edit.dsp?configuration=%value configuration%">
                              <img src="images/config_edit.gif" alt="Edit" border="0">
                           </a></td>
                           <script>writeTD('rowdata');</script>
                           <a href="configuration-view.dsp?configuration=%value configuration%">
                              <img src="images/file.gif" alt="View" border="0">
                           </a></td>
                           <script>writeTD('rowdata');swapRows();</script>
                           <a class="imagelink" href="configuration-list.dsp?operation=delete&configuration=%value configuration encode(url)%"
                           onclick="return confirmConfigDelete('%value configuration%');"><img src="images/delete.gif" border="0"></a></td>
                        </tr>
                     %end loop%
                  %else%
                     <tr><td class="message" colspan="5">No Configurations found</td></tr>
                  %endif%
               </table>
               <nobr>
            </td>
         </tr>
      </table>
   </body>
</html>
