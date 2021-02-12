<html>
   <head>
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="content-type" content="text/html; charset=utf-8">
      <meta http-equiv="expires" content="-1">
      <title>Multichoice CEFConfigurationProperties Package: Configurations View</title>
      <link rel="stylesheet" type="text/css" href="webMethods.css" />
      <script src="display.js"></script>
   </head>
   <body>
      %invoke CEFConfigurationProperties:GetPropertiesElement%
      <table width="100%">
         <tr>
            <td class="heading" colspan="2">Properties Element</td>
         </tr>
         <tr>
            <script>writeTD('row','width="30%"');</script>Configuration Name</td>
            <script>writeTD('rowdata-l');</script>%value configuration%</td>
            <script>swapRows();</script></td>
         </tr>
         <tr>
            <script>writeTD('row','width="30%"');</script>Property Name</td>
            <script>writeTD('rowdata-l');</script>%value name%</td>
            <script>swapRows();</script></td>
         </tr>
         <tr>
            <script>writeTD('row','width="30%"');</script>Property value</td>
            <script>writeTD('rowdata-l');</script><pre style="font-size:1.2em;margin:2px 0;">%value value encode(xml)%</pre></td>
            <script>swapRows();</script></td>
         </tr>
      </table>
   </body>
</html>