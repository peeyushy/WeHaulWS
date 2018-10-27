package constants;

import java.io.Serializable;

public class AppConstants implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Status {
		ACTIVE, DISABLED
	};

	public enum ClientType {
		A, S, T
	};
}
