import builder.HttpClientBuilder;
import client.HttpClient;

public class Main {

	public static void main(String[] args){
		HttpClient client = new HttpClientBuilder().build();
		String response = client
				.target("")
				.request()
				.

	}
}
