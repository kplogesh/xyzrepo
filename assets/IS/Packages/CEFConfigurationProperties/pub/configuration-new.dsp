<html>
   <head>
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="content-type" content="text/html; charset=utf-8">
      <meta http-equiv="expires" content="-1">
      <title>Multicoice CEFConfigurationProperties Package: Configurations New</title>
      <link rel="stylesheet" type="text/css" href="webMethods.css"/>
      <script src="validate.js"></script>
      <script src="display.js"></script>
      <style>
         input#configuration, input#comment { width: 95%; }
      </style>
   </head>
   <body>
      %invoke CEFConfigurationProperties:GetProperties%
      %onerror%
         <script>writeMessage("%value errorMessage encode(none)%");</script>
      %end invoke%
      <table width="100%">
         <tr>
            <td height="10">&nbsp;</td>
            <td>
               <table width="100%">
                  <form name="addConfiguration" method="post" action="configuration-list.dsp">
                     <input type="hidden" name="operation" value="create" />
                     <tr>
                        <td class="menusection-Security" colspan="4" nowrap>Packages &gt; CEFConfigurationProperties &gt; New</td>
                     </tr>
                     <tr>
                        <td colspan="2">
                           <ul>
                             <li><a href="configuration-list.dsp">configurations list</a></li>
                           </ul>
                        </td>
                     </tr>
                     <tr>
                        <td class="heading" colspan="2">New Configuration Details</td>
                     </tr>
                     <tr>
                        <script>writeTD('row');</script>Name</td>
                        <script>writeTD('rowdata-l');</script><input type="text" name="configuration" id="configuration" value=""></td>
                        <script>swapRows();</script></td>
                     </tr>
                     <tr>
                        <script>writeTD('row');</script>Comment</td>
                        <script>writeTD('rowdata-l');</script><input type="text" name="comment" id="comment" value=""></td>
                        <script>swapRows();</script></td>
                     </tr>
                  </table>
                  <nobr>
                  <table width="100%">
                     <tr>
                        <td class="action" colspan="2">
                           <input type="submit" name="submit" value="Save Changes" width="100" onclick="return validateFormCreateConfiguration(this.form)" />
                        </td>
                     </tr>
                  </table>
               </form>
            </td>
         </tr>
      </table>
   </body>
</html>
