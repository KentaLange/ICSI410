package university.nodb;

/**
 * Each {@code Department} represents a department.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 * 
 * @param name
 *            the name of the {@code Department}
 * @param budget
 *            the budget of the {@code Department}
 * @param buildingName
 *            the name of the building that houses the {@code Department}
 */
public record Department(String name, double budget, String buildingName) {

	/**
	 * Determines whether or not this {@code Department} and the specified object are equal.
	 * 
	 * @return {@code true} if this {@code Department} and the specified object are equal; {@code false} otherwise
	 */
	@Override
	public boolean equals(Object o) {
		return (o instanceof Department && name.equals(((Department) o).name));
	}

	/**
	 * Returns the hash code of this {@code Department}.
	 * 
	 * @return the hash code of this {@code Department}
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
