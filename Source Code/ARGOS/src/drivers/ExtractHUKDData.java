package drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.JsonController;

public class ExtractHUKDData extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _response = handleRequest(request, response);
		response.setContentType("text/html");
		System.out.println("Respoonse"+_response);
		response.getWriter().write(_response);
	}
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
	{
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL("http://api.hotukdeals.com/rest_api/v2/?key=e634e57e49ca721fa6c373e7a1614190&merchant=argos&results_per_page=10");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			String result;
			while ((output = br.readLine()) != null) {
				sb.append("\n"+output);
			}
			result = sb.toString();

			conn.disconnect();
			JsonController jsonObj = new JsonController(result);
			String prodJSON = jsonObj.getTopTenDeals();
			return prodJSON;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
