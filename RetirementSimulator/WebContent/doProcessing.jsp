<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ page import="com.fenic.MonteCarlo" %>
<%@ page import="com.fenic.Heuristic" %>

<%
	Double money=Double.valueOf(request.getParameter("money"));
	Integer years=Integer.valueOf(request.getParameter("years"));
	Double income=Double.valueOf(request.getParameter("income"));
	Double spending=Double.valueOf(request.getParameter("spending"));
	Double aftertax=Double.valueOf(request.getParameter("aftertax"));
	Double rate = Double.valueOf(request.getParameter("rate"));
	Double afterRate = Double.valueOf(request.getParameter("afterrate"));
	Double spendInflation = Double.valueOf(request.getParameter("spendinflation"));
	Double wageInflation = Double.valueOf(request.getParameter("wageinflation"));
    MonteCarlo mc = new MonteCarlo();
    
    if (rate>1.0f)
    	rate=rate/100.0f;
    
    if (aftertax>1.0f)
    	aftertax=aftertax/100.0f;
    
    mc.setStartingValue(money);
    mc.setYears(years);
    mc.setIncome(income);
    mc.setDesiredSpend(spending);
    mc.setAfterTaxPercent(aftertax);
    mc.setAfterrate(afterRate);
    mc.setRate(rate);
    mc.setSpendinflate(1.0f + spendInflation);
    mc.setWageinflate(1.0f + wageInflation);
    mc.simulate1();
%>
<html>
<body>
	<h2> Average ending result : <b> <%=String.format("$%,9.0f", mc.getResultsAverage())%> </b> </h2>
	<h2> Wipe out Percentage  : <b> <%=String.format("%3.2f", mc.getWipePercentage())%> % </b> </h2>
	
	<% for (int i=0; i<20; i++) { %>
		<h3> For bucket <%=i*250000%> to <%=(i+1)*250000%><sp> <sp> <%=String.format("%3.2f",((float) mc.getHeuristic().getBucketValue(i) / (float) mc.getNumberOfRuns()) * 100.0f)%>%</h3>
	<% } %>
	
</body>
</html>