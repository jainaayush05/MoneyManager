/*Created By Aayush Jain
 * jainaayush05@gmail.com*/
package com.aayush.mfm.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


public class StockAPI {
	private static HashMap<String, StockModel> stocks = new HashMap<String, StockModel>();
	
	public static StockModel getStockData(String symbol) {
		 // Making HTTP request
		 InputStream is = null;
        try {
            // defaultHttpClient
        	
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=snl1c6ghjkj3opp2v&e=.csv");
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		try {
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				String[] yahooStockInfo = inputLine.split(",");
				StockModel stockInfo = new StockModel();
				stockInfo.setTicker(yahooStockInfo[0].replaceAll("\"", ""));
				stockInfo.setName(yahooStockInfo[1].replaceAll("\"", ""));
				stockInfo.setPrice(yahooStockInfo[2].replaceAll("\"", ""));
				stockInfo.setChange(yahooStockInfo[3].replaceAll("\"", ""));
				stockInfo.setDayLow(yahooStockInfo[4].replaceAll("\"", ""));
				stockInfo.setDayHigh(yahooStockInfo[5].replaceAll("\"", ""));
				stockInfo.setYearLow(yahooStockInfo[6].replaceAll("\"", ""));
				stockInfo.setYearHigh(yahooStockInfo[7].replaceAll("\"", ""));
				stockInfo.setMarketCap(yahooStockInfo[8].replaceAll("\"", ""));
				stockInfo.setOpen(yahooStockInfo[9].replaceAll("\"", ""));
				stockInfo.setPrevClose(yahooStockInfo[10].replaceAll("\"", ""));
				stockInfo.setChangePercent(yahooStockInfo[11].replaceAll("\"", ""));
				stockInfo.setVolume(yahooStockInfo[12].replaceAll("\"", ""));
				stockInfo.setChartUrlSmall("http://ichart.finance.yahoo.com/t?s=" + stockInfo.getTicker());
				stockInfo.setChartUrlLarge("http://chart.finance.yahoo.com/w?s=" + stockInfo.getTicker());
				stockInfo.setLastUpdated((new Date()).getTime());
				stocks.put(symbol, stockInfo);
				break;
			}
			in.close();
		} catch (Exception ex) {
			System.out.println("Unable to get stockinfo for: " + symbol + ex);
		}
		return stocks.get(symbol);
		
     }
	public static HashMap<String, StockModel> getMultipleStockPrice(ArrayList<String> symbols) {
		 
		HashMap<String, StockModel> multiplestocks = new HashMap<String, StockModel>();
		// Making HTTP request
		 InputStream is = null;
       try {
           // defaultHttpClient
       	   String symbol="";
       	   for(String s:symbols){
       		   symbol=symbol+s+",";
       	   }
           DefaultHttpClient httpClient = new DefaultHttpClient();
           HttpPost httpPost = new HttpPost("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=snl1c6ghjkj3opp2v&e=.csv");

           HttpResponse httpResponse = httpClient.execute(httpPost);
           HttpEntity httpEntity = httpResponse.getEntity();
           is = httpEntity.getContent();           

       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
		
		try {
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			int i=0;
			while ((inputLine = in.readLine()) != null) {
				String[] yahooStockInfo = inputLine.split(",");
				StockModel stockInfo = new StockModel();
				stockInfo.setTicker(yahooStockInfo[0].replaceAll("\"", ""));
				stockInfo.setName(yahooStockInfo[1].replaceAll("\"", ""));
				stockInfo.setPrice(yahooStockInfo[2].replaceAll("\"", ""));
				stockInfo.setChange(yahooStockInfo[3].replaceAll("\"", ""));
				stockInfo.setDayLow(yahooStockInfo[4].replaceAll("\"", ""));
				stockInfo.setDayHigh(yahooStockInfo[5].replaceAll("\"", ""));
				stockInfo.setYearLow(yahooStockInfo[6].replaceAll("\"", ""));
				stockInfo.setYearHigh(yahooStockInfo[7].replaceAll("\"", ""));
				stockInfo.setMarketCap(yahooStockInfo[8].replaceAll("\"", ""));
				stockInfo.setOpen(yahooStockInfo[9].replaceAll("\"", ""));
				stockInfo.setPrevClose(yahooStockInfo[10].replaceAll("\"", ""));
				stockInfo.setChangePercent(yahooStockInfo[11].replaceAll("\"", ""));
				stockInfo.setVolume(yahooStockInfo[12].replaceAll("\"", ""));
				stockInfo.setChartUrlSmall("http://ichart.finance.yahoo.com/t?s=" + stockInfo.getTicker());
				stockInfo.setChartUrlLarge("http://chart.finance.yahoo.com/w?s=" + stockInfo.getTicker());
				stockInfo.setLastUpdated((new Date()).getTime());
				multiplestocks.put(symbols.get(i), stockInfo);
				i++;
				
			}
			in.close();
		} catch (Exception ex) {
			System.out.println("Unable to get stockinfo : "+ ex);
		}
		return multiplestocks;
    }

}
