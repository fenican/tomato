package com.fenic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MonteCarlo {

	Random randomGenerator = new Random(System.nanoTime());
	Heuristic heuristic = null;

	public double getNormalRandom(double mean, double stdev) {
		double val = randomGenerator.nextGaussian() * stdev + mean;
		return val;
	}
	
	public double getMedian(ArrayList<Double> results)
	{
	    Collections.sort(results);
	 
	    if (results.size() % 2 == 1)
		return results.get((results.size()+1)/2-1);
	    else
	    {
		double lower = results.get(results.size()/2-1);
		double upper = results.get(results.size()/2);
	 
		return (lower + upper) / 2.0;
	    }	
	}
	
	public double getResultsAverage() {
		double average = 0.0f;
		for (Double d : results) {
			average += d;
		}
		average = average / (double) numberOfRuns;
		return average;
	}
	
	public double getResultsLow() {
		double low = 999999999.0f;
		for (double d : results) {
			if (d < low)
				 low = d;
		}
		return low;
	}
	
	public double getResultsHigh() {
		double high = 0.0f;
		for (double d : results) {
			if (d> high)
				high = d;
		}
		return high;
	}
	
	public int getWipes() {
		int wipes = 0;
		for (double d : results) {
			if (d < wipe) {
				wipes += 1;
			}
		}
		return wipes;
	}
	
	public int getWins() {
		int wins = 0;
		for (double d : results) {
			if (d >= win) {
				wins += 1;
			}
		}
		return wins;
	}
	

	int numberOfRuns = 10000;
	int years = 25;
	
	ArrayList<ArrayList<Double>> resultsByYear = new ArrayList<ArrayList<Double>>(years);
	
	ArrayList<Double> results = new ArrayList<Double>(numberOfRuns);
	
	double startingValue = 787000.0;
	double afterTaxPercent = 0.25f;
	
	double secondHomeStart = 193000.0f;
	double realEstateAppreciation = .02f;
	double mortgage = -176000.0f; 
	
	double wipe = 0.0;
	double win = 1000000.0;

	double spendinflate = 1.01f;
	double wageinflate = 1.03f;
	
	double inflationrate = .03;
	
	double desiredSpend = 55000.0;
	//double spendFloor = 25000.0;
	
	double income = 33000.0;
	double taxRate = .23;
	double penaltyRate = .1;
	
	double rate = .07;
	double afterrate = .07;
	double deviation = .1;
	
	double excesstax = 0.0;
	
	public void simulate1() {
		
		
		  for (int i=0; i<years; i++) {
			  resultsByYear.add(new ArrayList<Double>());
		  }
		
		  for (int i=0; i<numberOfRuns; i++) {
				double preTax = startingValue * (1-afterTaxPercent);
				double afterTax = startingValue * (afterTaxPercent);
				double annualSpend = desiredSpend;
				double baseIncome = income;
				//double secondHome = secondHomeStart;
				
				int j = 0;
				
				for (j=0; j<years; j++) {
					
					double rateOfReturn = getNormalRandom(rate,deviation);
					double rateOfReturnA = getNormalRandom(afterrate,deviation/2);		
					
					preTax = preTax * ( 1 + rateOfReturn );
				
					// use tax adjusted rate for after tax gains
					
					afterTax = afterTax * ( 1 + rateOfReturnA );
					
					// put income in after tax bucket
					
					afterTax += income * (1 - taxRate);
					
					if (afterTax - annualSpend > 0) {
						afterTax = afterTax - annualSpend;
					} else {
						preTax -= annualSpend;
						preTax -= annualSpend * penaltyRate; // penalty;
						preTax -= annualSpend * taxRate; // taxes;
					}
					
					annualSpend *= spendinflate;
					//if (annualSpend < spendFloor) {
					//	annualSpend = spendFloor;
					//}
					baseIncome  *= wageinflate;
					//double rateOfReturnC = getNormalRandom(realEstateAppreciation,deviation/2);
					//secondHome *= ( 1 + rateOfReturnC );
					
					resultsByYear.get(j).add(preTax + afterTax);
				}

			
				results.add(preTax + afterTax );
				if (heuristic!=null) {
					heuristic.addValue((int) (preTax + afterTax));
				}
				 
		  }
		  
		  System.out.printf("average ending value: $%,9.0f\n",getResultsAverage());
		  System.out.printf("high value: $%,9.0f\n",getResultsHigh());
		  System.out.printf("low value: $%,9.0f\n",getResultsLow());
		  System.out.printf("median value: $%,9.0f\n",getMedian(results));
		  System.out.printf("number of wipes percentage: %3.2f\n",(double) getWipes()/numberOfRuns * 100.0f);
		  System.out.printf("number of wins percentage: %3.2f\n",(double) getWins()/numberOfRuns * 100.0f);
		  
		  for (int i=0; i<years; i++) {
			  System.out.printf("median value for year %d : $%,9.0f\n",i+1,getMedian(resultsByYear.get(i)));
		  }
	}
		
	public static void main(String args[]) {
		MonteCarlo mc = new MonteCarlo();
		//mc.simulate1();
	}
	
	public double getWipePercentage() {
		return (double) getWipes()/numberOfRuns * 100.0f;
	}

	public Random getRandomGenerator() {
		return randomGenerator;
	}

	public void setRandomGenerator(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public int getNumberOfRuns() {
		return numberOfRuns;
	}

	public void setNumberOfRuns(int numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public ArrayList<ArrayList<Double>> getResultsByYear() {
		return resultsByYear;
	}

	public void setResultsByYear(ArrayList<ArrayList<Double>> resultsByYear) {
		this.resultsByYear = resultsByYear;
	}

	public ArrayList<Double> getResults() {
		return results;
	}

	public void setResults(ArrayList<Double> results) {
		this.results = results;
	}

	public double getStartingValue() {
		return startingValue;
	}

	public void setStartingValue(double startingValue) {
		this.startingValue = startingValue;
		int bucketsize = (int) 250000;
		heuristic = new Heuristic(20,bucketsize);
	}

	public double getAfterTaxPercent() {
		return afterTaxPercent;
	}

	public void setAfterTaxPercent(double afterTaxPercent) {
		this.afterTaxPercent = afterTaxPercent;
	}

	public double getSecondHomeStart() {
		return secondHomeStart;
	}

	public void setSecondHomeStart(double secondHomeStart) {
		this.secondHomeStart = secondHomeStart;
	}

	public double getRealEstateAppreciation() {
		return realEstateAppreciation;
	}

	public void setRealEstateAppreciation(double realEstateAppreciation) {
		this.realEstateAppreciation = realEstateAppreciation;
	}

	public double getMortgage() {
		return mortgage;
	}

	public void setMortgage(double mortgage) {
		this.mortgage = mortgage;
	}

	public double getWipe() {
		return wipe;
	}

	public void setWipe(double wipe) {
		this.wipe = wipe;
	}

	public double getWin() {
		return win;
	}

	public void setWin(double win) {
		this.win = win;
	}


	public double getDesiredSpend() {
		return desiredSpend;
	}

	public void setDesiredSpend(double desiredSpend) {
		this.desiredSpend = desiredSpend;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public double getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(double penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAfterrate() {
		return afterrate;
	}

	public void setAfterrate(double afterrate) {
		this.afterrate = afterrate;
	}

	public double getDeviation() {
		return deviation;
	}

	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}

	public double getExcesstax() {
		return excesstax;
	}

	public void setExcesstax(double excesstax) {
		this.excesstax = excesstax;
	}

	public double getInflationrate() {
		return inflationrate;
	}

	public void setInflationrate(double inflationrate) {
		this.inflationrate = inflationrate;
	}

	public double getSpendinflate() {
		return spendinflate;
	}

	public void setSpendinflate(double spendinflate) {
		this.spendinflate = spendinflate;
	}

	public double getWageinflate() {
		return wageinflate;
	}

	public void setWageinflate(double wageinflate) {
		this.wageinflate = wageinflate;
	}

	public Heuristic getHeuristic() {
		return heuristic;
	}
	
}
