<html>
   <head>
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="content-type" content="text/html; charset=utf-8">
      <meta http-equiv="expires" content="-1">
      <title>Multichoice CEFConfigurationProperties package: Search</title>
      <link rel="stylesheet" type="text/css" href="webMethods.css"/>
      <script src="display.js"></script>
      <style>
         td.heading a
            {
            color: #fff;
            text-decoration: none;
            }
         td.propertyValue, td.formLabel
            {
            width: 350px;
            max-width: 800px;
            min-width: 350px;
            }
         td.oddrowdata-l span.text
            {
            font-weight: normal;
            }
         span.text tt
            {
            font-family: "courier new", "courier";
            color: #111;
            font-size: 110%;
            }
         #regexp
            {
            width: 98%;
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
                     <td class="menusection-Security" nowrap>Packages &gt; CEFConfigurationProperties &gt; Search</td>
                  </tr>

                  %ifvar action%
                     %invoke CEFConfigurationProperties:search%
                     %endinvoke%
                     %ifvar message -notempty%
                        <tr><td>&nbsp;</td></tr>
                        <tr><td class="message">%value message encode(none)%</td></tr>
                        %rename message erase%
                        %rename action erase%
                     %endif%
                  %endif%

                  <tr><td>&nbsp;</td></tr>
                  <tr>
                     <td>
                        <form name="frmSearch" method="post" action="search.dsp">
                           <table width="100%">
                              <tr>
                                 <td class="heading" colspan="2">Search Matching Property</td>
                              </tr>
                              <tr>
                                 <td class="oddrow formLabel">Regular Expression</td>
                                 <td class="oddrowdata-l"><input type="text" name="regexp" id="regexp" value="%value regexp%" /></td>
                              </tr>
                              <tr>
                                 <td class="oddrow" valign="top">Search Context</td>
                                 <td class="oddrow-l">
                                    <input type="radio" name="context" value="value" %ifvar context equals('value')%checked%else%%ifvar context%%else%checked%endif%%endif% />&nbsp;Property Value&nbsp;&nbsp;&nbsp;<span class="text">(<em>e.g.</em>; <tt>^http[s]{0,1}://.*</tt>)</span></br>
                                    <input type="radio" name="context" value="name"  %ifvar context equals('name')%checked%endif% />&nbsp;Property Name&nbsp;&nbsp;&nbsp;<span class="text">(<em>e.g.</em>; <tt>^.*\.(port|host)$</tt>)</span></br>
                                    <input type="radio" name="context" value="both"  %ifvar context equals('both')%checked%endif% />&nbsp;Both Name &amp; Value
                                 </td>
                              </tr>
                              <tr>
                                 <td class="action" colspan="2">
                                    <input type="submit" value="Search" />
                                    <input type="hidden" name="action" value="search" />
                                 </td>
                              </tr>
                           </table>
                        </form>
                     </td>
                  </tr>
                  %ifvar action%
                     %ifvar configurations%
                        %loop configurations%
                           <tr><td>&nbsp;</td></tr>
                           <tr>
                              <td>
                                 <table width="100%">
                                    <tr>
                                       <td class="heading" colspan="2"><a href="configuration-edit.dsp?configuration=%value configuration%">%value configuration%</a></td>
                                    </tr>
                                    %loop properties%
                                       <tr>
                                          <td class="oddrow propertyValue">%value name%</td>
                                          <td class="oddrowdata-l">%value value%</td>
                                       </tr>
                                    %endloop%
                                 </table>
                              </td>
                           </tr>
                        %endloop%
                     %else%
                        <tr><td class="message">No matched found.</td></tr>
                     %endif%
                  %endif%
               </table>
            </td>
         </tr>
      </table>
   </body>
</html>
