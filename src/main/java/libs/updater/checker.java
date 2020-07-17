package libs.updater;

import com.fasterxml.jackson.databind.ObjectMapper;
import libs.coronaDb.coronaDb;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class checker {
    static ObjectMapper mapper= coronaDb.mapper;
   synchronized public static versionInfo  requestInfo(String value) throws Exception {

        HttpPost post = new HttpPost("https://quiet-dust-9740.getsandbox.com/update");
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("version", value));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            return mapper.readValue(EntityUtils.toString(response.getEntity()), versionInfo.class);

        }

    }


}
