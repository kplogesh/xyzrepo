<html>
   <head>
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="content-type" content="text/html; charset=utf-8">
      <meta http-equiv="expires" content="-1">
      <title>Multichoice CEFConfigurationProperties Package: Configurations View</title>
      <link rel="stylesheet" type="text/css" href="webMethods.css"/>
      <script src="display.js"></script>
   </head>
   <body>
      %invoke CEFConfigurationProperties:GetProperties%
      %onerror%
         <script>writeMessage("%value errorMessage%");</script>
      %end invoke%
      <table width="100%">
         <tr>
            <td height="10">&nbsp;</td>
            <td>
               <table width="100%">
                  <tr>
                     <td class="menusection-Security" colspan="4" nowrap>Packages &gt; CEFConfigurationProperties &gt; View &gt; %value configuration encode(none)% Configuration</td>
                  </tr>
                  <tr>
                     <td colspan="2">
                       <ul>
                        <li><a href="configuration-list.dsp">Return to configurations list</a></li>
                        %ifvar /view equals('keyvalue')%
                           <li><a href="configuration-view.dsp?configuration=%value configuration encode(url)%">Normal Properties View</a></li>
                        %else%
                           <li><a href="configuration-view.dsp?view=keyvalue&configuration=%value configuration encode(url)%">Key=value View</a></li>
                        %endif%
                        <li><a href="configuration-edit.dsp?configuration=%value configuration encode(url)%">Edit properties of this configuration</a></li>
                       </ul>
                  </td>
               </tr>
               <tr>
                  <td class="heading" colspan="2">Configuration Details</td>
               </tr>
               <tr>
                  <script>writeTD('row','width="30%"');</script>Name</td>
                  <script>writeTD('rowdata-l');</script>%value configuration%</td>
                  <script>swapRows();</script></td>
               </tr>
               <tr>
                  <script>writeTD('row');</script>Comment</td>
                  <script>writeTD('rowdata-l');</script>%value comment%</td>
                  <script>swapRows();</script></td>
               </tr>
               <tr>
                  <td class="heading" colspan="2">Configuration Properties</td>
               </tr>
               %ifvar propertiesTable -notempty%
                  %ifvar /view equals('keyvalue')%
                     <tr><td class="evenrow-l" colspan="2"><pre class="fixedwidth">%loop propertiesTable%%value name%=%value value encode(xml)%&#010;%endloop%</pre></td></tr>
                  %else%
                     %loop propertiesTable%
                        <tr>
                           <script>writeTD('row');</script>%value name%</td>
                           <script>writeTD('rowdata-l','valign="center"');</script>%value value encode(xml)%</td>
                           <script>swapRows();</script></td>
                        </tr>
                     %end loop%
                  %endif%
                  <tr>
                     <td class="heading" colspan="2">View in original style     (XML cases)</td>
                  </tr>
                  <tr nowrap class="evenrow-l">
                     <form target="_propertiesElement" action="configuration-viewPropertiesElement.dsp" method="post">
                        <input type="hidden" name="configuration" value="%value /configuration%" />
                        <td class="evenrow-l" colspan="2">Choose Property&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           <select name="name">
                              %loop propertiesTable%
                                 <option value="%value name%">%value name%</option>
                              %endloop%
                           </select>&nbsp;&nbsp;<input type="submit" name="submit" value="View Field" width="100" />
                        </td>
                     </form>
                  </tr>
                  %else%
                     <tr><td class="message" colspan="2">No properties found</td></tr>
                  %endif%
               </table>
               <nobr>
            </td>
         </tr>
      </table>
   </body>
</html>
