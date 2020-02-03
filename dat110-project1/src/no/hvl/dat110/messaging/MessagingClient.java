package no.hvl.dat110.messaging;

import java.io.IOException;

import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingClient {

	private String server;
	private int port;

	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}

	// connect to messaging server
	public Connection connect() {

		Socket clientSocket;
		Connection connection = null;

		// TODO
		// create TCP socket for client and connection
		// create connection object
		
		
		try {
			// Create the socket, spessiefied with server and port.
			clientSocket = new Socket(server, port);
			// Create a connection based on the Socket created above.
			connection = new Connection(clientSocket);
		} catch(IOException e) {
			e.printStackTrace();
		}
		

		return connection;
	}
}
