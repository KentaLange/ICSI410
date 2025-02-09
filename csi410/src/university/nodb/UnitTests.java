package university.nodb;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * {@code UnitTests} tests the implementations in the {@code university.nodb} package.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 * 
 */
public class UnitTests {

	/**
	 * Tests the Task 1 implementation.
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	@Test
	public void task1() throws Exception {
		System.out.println("task1");
		var university = sampleUniversity(6);
		assertEquals(
				"[Student[studentID=S00, zipCode=12222, departmentName=D00], Student[studentID=S04, zipCode=12222, departmentName=D02], Student[studentID=S08, zipCode=12222, departmentName=D04], Student[studentID=S12, zipCode=12222, departmentName=D05]]",
				((Stream<?>) stream(university, "queryStudents", 12222)).toList()
						.toString());
		assertEquals(
				"[Student[studentID=S01, zipCode=12223, departmentName=D00], Student[studentID=S05, zipCode=12223, departmentName=D02], Student[studentID=S09, zipCode=12223, departmentName=D04], Student[studentID=S13, zipCode=12223, departmentName=D05]]",
				((Stream<?>) stream(university, "queryStudents", 12223)).toList()
						.toString());
		assertEquals(
				"[Student[studentID=S03, zipCode=12225, departmentName=D01], Student[studentID=S07, zipCode=12225, departmentName=D03], Student[studentID=S11, zipCode=12225, departmentName=D05]]",
				((Stream<?>) stream(university, "queryStudents", 12225)).toList()
						.toString());
		university = sampleUniversity(20);
		assertEquals(11, ((Stream<?>) stream(university, "queryStudents", 12222)).count());
		assertEquals(11, ((Stream<?>) stream(university, "queryStudents", 12223)).count());
		assertEquals(10, ((Stream<?>) stream(university, "queryStudents", 12224)).count());
		assertEquals(0, ((Stream<?>) stream(university, "queryStudents", 12226)).count());
	}

	/**
	 * Tests the Task 2 implementation.
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void task2() throws Exception {
		var university = sampleUniversity(10);
		assertEquals("[B00=4, B01=4, B02=4, B03=4, B04=6]",
				((Stream<Entry<Double, Integer>>) stream(university, "queryBuildingStudents"))
						.map(e -> "" + e).sorted().toList().toString());
		university = sampleUniversity(20);
		assertEquals("[B00=4, B01=4, B02=4, B03=4, B04=4, B05=4, B06=4, B07=4, B08=4, B09=6]",
				((Stream<Entry<Double, Integer>>) stream(university, "queryBuildingStudents"))
						.map(e -> "" + e).sorted().toList().toString());
	}

	/**
	 * Tests the Task 3 implementation.
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void task3() throws Exception {
		var university = sampleUniversity(6);
		assertEquals("[D02, D05]",
				((Stream<Department>) stream(university, "queryMaxBudgetDepartments"))
						.map(e -> e.name()).sorted().toList().toString());
		university = sampleUniversity(15);
		assertEquals("[D02, D05, D08, D11, D14]",
				((Stream<Department>) stream(university, "queryMaxBudgetDepartments"))
						.map(e -> e.name()).sorted().toList().toString());
		university = sampleUniversity(20);
		assertEquals("[D02, D05, D08, D11, D14, D17]",
				((Stream<Department>) stream(university, "queryMaxBudgetDepartments"))
						.map(e -> e.name()).sorted().toList().toString());
	}

	/**
	 * Constructs a sample {@code University}.
	 * 
	 * @param numberOfDepartments
	 *            the number of {@code Department}s of the {@code University}
	 * @return a sample {@code University}
	 */
	university.nodb.University sampleUniversity(int numberOfDepartments) {
		var u = new university.nodb.University("Sample University");
		university.nodb.University.addData(u, numberOfDepartments);
		return u;
	}

	/**
	 * Invokes a method with the given name on the specified object, passing the provided arguments, and returns the
	 * result.
	 * 
	 * @param o
	 *            the object on which the method is to be invoked
	 * @param name
	 *            the name of the method to be invoked
	 * @param args
	 *            the arguments to pass to the method
	 * @return the object returned by the invoked method
	 * @throws IllegalAccessException
	 *             if the underlying method is inaccessible
	 * @throws InvocationTargetException
	 *             if the underlying method throws an exception
	 * @throws NoSuchMethodException
	 *             if no method with the specified name is found
	 * @throws SecurityException
	 *             if a security manager denies access to the method
	 */
	Object stream(Object o, String name, Object... args) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		for (Method m : o.getClass().getMethods())
			if (m.getName().equals(name))
				return m.invoke(o, args);
		throw new NoSuchMethodException(name);
	}

}
