package source.models;

public enum Status {
	STARTED,
	PENDING,
	COMPLETED;
	
	public static Status getDefault() {
		return STARTED;
	}
}
