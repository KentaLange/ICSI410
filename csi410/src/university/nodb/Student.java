package university.nodb;

/**
 * Each {@code Student} represents a student. Each {@code Student} is affiliated with only one {@code Department}.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 * 
 * @param studentID
 *            the ID of the {@code Student}
 * @param zipCode
 *            the ZIP code of the {@code Student}
 * @param departmentName
 *            the name of the {@code Department} that the {@code Student} is affiliated with
 */
public record Student(String studentID, int zipCode, String departmentName) {

	/**
	 * Determines whether or not this {@code Student} and the specified object are equal.
	 * 
	 * @return {@code true} if this {@code Student} and the specified object are equal; {@code false} otherwise
	 */
	@Override
	public boolean equals(Object o) {
		return (o instanceof Student && studentID.equals(((Student) o).studentID));
	}

	/**
	 * Returns the hash code of this {@code Student}.
	 * 
	 * @return the hash code of this {@code Student}
	 */
	@Override
	public int hashCode() {
		return studentID.hashCode();
	}
}
