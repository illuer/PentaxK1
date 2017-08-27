/*
 * This code is in the public domain.
 */

package piao.yezi.pentaxk1.http;

public class ApiException extends Exception {
	private static final long serialVersionUID = -6316637693779831867L;

	public ApiException(String message) {
		super(message);
	}

	ApiException(int i, String message) {
		super(message);
	}

	ApiException(Exception parent) {
		super(parent);
	}
}
