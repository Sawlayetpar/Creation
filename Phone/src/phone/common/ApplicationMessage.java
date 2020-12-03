package phone.common;

@SuppressWarnings("serial")
public class ApplicationMessage extends RuntimeException {

	public ApplicationMessage(String msg) {
		super(msg);
	}
}
