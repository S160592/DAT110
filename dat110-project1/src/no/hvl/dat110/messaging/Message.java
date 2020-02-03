package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		if(payload.length < 127) {
			this.payload = payload; // TODO: check for length within boundary
		}else {
			 throw new UnsupportedOperationException(TODO.method());
		}
		
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = null;
		
		// TODO
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format
		
		// set encoded as a byte of the size MessageConfig.semgentsize
		encoded = new byte[MessageConfig.SEGMENTSIZE];
		
		encoded[0] = Integer.valueOf(payload.length).byteValue();

		// Add value to encode
		for (int i = 0; i < payload.length; i++) {
			encoded[i+1] = payload[i];
		}
		
		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		
		payload = new byte[received[0]];
		
		for (int i = 0; i < received[0]; i++) {
			payload[i] = received[i+1];
		}
		
	}
}
