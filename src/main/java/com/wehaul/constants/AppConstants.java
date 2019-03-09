package com.wehaul.constants;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AppConstants implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Status {
		ACTIVE, DISABLED
	};

	public enum ClientType {
		A, T, S, B
	};

	public enum LoadStatus {
		ACTIVE, DISABLED, EXPIRED
	};

	public enum ReqStatus {
		NEW, OPEN, CLOSED, EXPIRED
	};

	public enum RoleNames {
		ADMIN, TWOWAY, TRANSPORTER, SUPPLER
	};
}
