%ifvar message -notempty%
	%rename message erase%
%endif%
%ifvar operation -notempty%
	%switch operation%

	%case 'duplicate'%
		%invoke CEFConfigurationProperties:DuplicateConfiguration%
		%end invoke%
	%end%
%endif%
<html>
   <head>
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="content-type" content="text/html; charset=utf-8">
      <meta http-equiv="expires" content="-1">
      <title>Multichoice CEFConfigurationProperties package: Configurations Duplicate</title>
      <link rel="stylesheet" type="text/css" href="webMethods.css"/>
      <script src="display.js"></script>
   </head>
   <body>
      %invoke CEFConfigurationProperties:GetConfigurations%
      %onerror%
         <script>writeMessage("%value errorMessage%");</script>
      %end invoke%
      <table width="100%">
         <tr>
            <td height="10">&nbsp;</td>
            <td>
               <table width="100%">
                  <tr>
                     <td class="menusection-Security" colspan="5" nowrap>Packages &gt; CEFConfigurationProperties &gt; Duplicate</td>
                  </tr>
                  %ifvar message -notempty%
                     <tr><td colspan="5">&nbsp;</td></tr>
                     <tr><td class="message" colspan="5">%value message encode(none)%</td></tr>
                  %endif%
                  <tr>
                     <td colspan="5">
                        <ul>
                           <li><a href="configuration-new.dsp">Create Configuration</a></li>
                        </ul>
                     </td>
                  </tr>
                  <tr>
                     <td class="heading" colspan="2">Configurations List</td>
                  </tr>
                  <tr>
                     <td class="oddcol-l" nowrap width="30%">Configuration Name</td>
                     <td class="oddcol" nowrap>Comment</td>
                  </tr>
                  %ifvar output -notempty%
                     %loop output%
                        <tr>
                           <script>writeTD('rowdata-l');</script><a href="configuration-view.dsp?configuration=%value configuration% encode(url)">%value configuration%</a></td>
                           <script>writeTD('rowdata');</script>%value comment%</td>
                        </tr>
                     %end loop%
                  %else %
                     <tr><td class="message" colspan="5">No Configurations found</td></tr>
                  %endif%
               </table>
               <br>
               <form name="duplicateProperties" method="post" action="configuration-duplicate.dsp">
                  <input type="hidden" name="operation" value="duplicate">
                  <table valign="middle" width="100%">
                     <tr>
                        <td class="heading" colspan="4">Duplicate Configuration</td>
                     </tr>
                     <tr>
                        <script>writeTD('row');</script>Choose Configuration</td>
                        <script>writeTD('rowdata-l','colspan="3"');</script>
                        <select name="configuration">
                           %loop output%
                              <option value="%value configuration%">%value configuration%</option>
                           %endloop%
                        </select></td>
                     </tr>
                     <tr>
                        <script>writeTD('row');</script>New Configuration Name</td>
                        <script>writeTD('rowdata-l','colspan="3"');</script>
                        <input type="text" name="newConfigurationName" size="50" />
                     </tr>
                     <tr>
                        <script>writeTD('row');</script>New Configuration Comment</td>
                        <script>writeTD('rowdata-l','colspan="3"');</script>
                        <input type="text" name="newConfigurationComment" size="80" />
                     </tr>
                  </table>
                  <table width="100%">
                     <tr>
                       <td class="action" colspan="2">
                         <input type="submit" name="submit" value="Save Changes" width="100" />
                       </td>
                   </tr>
                  </table>
               </form>
               <nobr>
            </td>
         </tr>
      </table>
   </body>
</html>

