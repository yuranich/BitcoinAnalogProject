package mipt.infosec.bitcoin.network;

public class Protocol {
	public static final int CONNECTION_PORT = 55555;

	public enum TYPES {
		NEW_TRANSACTION,
		SUCCESSFUL_TRANSACTION,
		NEW_NODE
	};
}
