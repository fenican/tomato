<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styling.css" media="screen" />
</head>
<body>
<form name="frm" method="get" action="doProcessing.jsp">

<h1> Andy's Amazing Retirement Simulator</h1>

<br>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="22%">&nbsp;</td>
    <td width="78%">&nbsp;</td>
    </tr>
  <tr>
    <td>Total Money</td>
    <td><input type="text" name="money"></td>
  </tr>
  <tr>
    <td>After Tax Percent</td>
    <td><input type="text" name="aftertax" value=".25"></td>
  </tr>
  <tr>
    <td>Years</td>
    <td><input type="text" name="years" value="25"></td>
  </tr>
  <tr>
    <td>Annual Spending</td>
    <td><input type="text" name="spending"></td>
  </tr>
  <tr>
    <td>Income</td>
    <td><input type="text" name="income"></td>
  </tr>
   <tr>
    <td>Rate of Return (Pre-Tax Money)</td>
    <td><input type="text" name="rate" value=".09"></td>
  </tr>
   <tr>
    <td>Rate of Return (Post-Tax Money)</td>
    <td><input type="text" name="afterrate" value=".03"></td>
  </tr>
   <tr>
    <td>Rate of Spending Inflation</td>
    <td><input type="text" name="spendinflation" value=".015"></td>
  </tr>
   <tr>
    <td>Rate of Wage Inflation</td>
    <td><input type="text" name="wageinflation" value=".03"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><button type="submit" name="submit" value="Submit">Submit</button></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
</table>
</form>
</body>
</html>