package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.google.gson.Gson;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {
		// TODO: implement a HTTP POST on the service to post the message
		try (Socket socket = new Socket(Configuration.host, Configuration.port)) {

			// Create GSON-object
			Gson gson = new Gson();

			// convert the message to json
			String jsonString = gson.toJson(new AccessMessage(message));

			// Create HTTP-request
			String httpRequest = "POST " + logpath + " HTTP/1.1\r\n" + "Host: " + Configuration.host + "\r\n"
					+ "Content-type: application/json\r\n" + "Content-length: " + jsonString.length() + "\r\n"
					+ "Connection: close\r\n" + "\r\n" + jsonString + "\r\n";

			// Send the request using the created socket
			OutputStream output = socket.getOutputStream();

			PrintWriter pw = new PrintWriter(output, false);
			pw.print(httpRequest);
			pw.flush();

			// Handle the response from the server
			InputStream in = socket.getInputStream();

			Scanner scanner = new Scanner(in);
			StringBuilder httpResponse = new StringBuilder();

			// keep track of the header
			boolean isHeader = true;

			while (scanner.hasNext()) {
				String nextline = scanner.nextLine();

				if (isHeader) {
					System.out.println(nextline);
				} else {
					httpResponse.append(nextline);
				}

				if (nextline.isEmpty()) {
					isHeader = false;
				}

				System.out.println("BODY:");
				System.out.println(httpResponse.toString());

				// Close the scanner
				scanner.close();
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String codepath = "/accessdevice/code";

	public AccessCode doGetAccessCode() {
		AccessCode code = null;

		// TODO: implement a HTTP GET on the service to get current access code

		
		try (Socket socket = new Socket(Configuration.host, Configuration.port)) {
			// Create the HTTPRequest
            String httpRequest = "GET " + codepath + " HTTP/1.1\r\n" + "Accept: application/json\r\n"
                    + "Host: localhost\r\n" + "Connection: close\r\n" + "\r\n";

           
            // Send the HTTP requets using the socket
            OutputStream output = socket.getOutputStream();

            PrintWriter pw = new PrintWriter(output, false);

            pw.print(httpRequest);
            pw.flush();

            // read the HTTP Response from the request
            InputStream in = socket.getInputStream();

            Scanner scanner = new Scanner(in);
            StringBuilder response = new StringBuilder();
            
            // Keep track of the header
            boolean isHeader = true;

            while (scanner.hasNext()) {
                String nextline = scanner.nextLine();

                if (!isHeader) {
                	response.append(nextline);
                }

        
                if (nextline.isEmpty()) {
                    isHeader = false;
                }

            }

            Gson gson = new Gson();
            // Create AccessCode-object from the json response
            code = gson.fromJson(response.toString(), AccessCode.class);
            // close the scanner
            scanner.close();
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}
}
