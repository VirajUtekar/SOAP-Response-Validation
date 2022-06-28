package request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;

public class Country_Capital_Request_By_ISO_Code_Test {


	@Test
	public void verify_country_capital_request_by_iso_code() throws ClientProtocolException, IOException {
		
		File file = new File("./request/countryCapital.xml");
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpPost request = new HttpPost("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
		
		request.addHeader("Content-Type","text/xml");
		
		request.setEntity(new InputStreamEntity(new FileInputStream(file)));
		
		CloseableHttpResponse response = client.execute(request);
		
		int responseCodeVal = response.getStatusLine().getStatusCode();
		System.out.println(responseCodeVal);
		Assert.assertEquals(responseCodeVal,200);
       
		
		String responseString = EntityUtils.toString(response.getEntity());
		
		XmlPath xml = new XmlPath(responseString);
		
		String responseBodyVal = xml.getString("sCountryISOCode");
		System.out.println(responseBodyVal);
		Assert.assertEquals(responseBodyVal,"New Delhi");
	}
	
}
