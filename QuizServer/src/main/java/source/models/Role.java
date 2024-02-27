package source.models;

public enum Role {
	ADMIN,
    USER;
    
    public static Role getDefault() {
		return USER;
	}
}
