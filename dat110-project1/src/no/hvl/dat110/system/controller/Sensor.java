package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class Sensor extends RPCStub {

	private byte RPCID = 1;

	public int read() {

		int temp;

		// TODO
		// implement marshalling, call and unmarshalling for read RPC method

		byte[] marshallVoid = RPCUtils.marshallVoid(RPCID);

		byte[] tempread = rpcclient.call(marshallVoid);
		temp = RPCUtils.unmarshallInteger(tempread);

		return temp;
	}

}
