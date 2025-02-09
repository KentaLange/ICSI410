package university.nodb;

import java.util.Map;

import java.util.Map.Entry;

import java.util.TreeMap;

import java.util.function.BiConsumer;

import java.util.stream.Collectors;

import java.util.stream.IntStream;

import java.util.stream.Stream;


/**

 * Each {@code University} represents a university.

 *

 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)

 */

public class University {


    /**

     * The name of this {@code University}.

     */

    protected String name;


    /**

     * The {@code Department}s of this {@code University}.

     */

    protected Map<String, Department> departments = new TreeMap<String, Department>();


    /**

     * The {@code Student}s of this {@code University}.

     */

    protected Map<String, Student> students = new TreeMap<String, Student>();


    /**

     * Constructs a {@code University}.

     *

     * @param name

     *            the name of the {@code University}

     */

    public University(String name) {

        this.name = name;

    }


    /**

     * Registers the specified {@code Department}.

     *

     * @param department

     *            a {@code Department}

     */

    public void register(Department department) {

        departments.put(department.name(), department);

    }


    /**

     * Registers the specified {@code Student}.

     *

     * @param student

     *            a {@code Student}

     */

    public void register(Student student) {

        students.put(student.studentID(), student);

    }


    /**

     * The main method of the {University} class.

     *

     * @param args

     *            the program arguments

     */

    public static void main(String[] args) {

        var university = new University("Sample");

        addData(university, 6);


        System.out.println("departments:");

        university.departments.values().stream().forEach(d -> System.out.println(d));

        System.out.println("");


        System.out.println("students:");

        university.students.values().stream().forEach(s -> System.out.println(s));

        System.out.println("");


        System.out.println("departments with budget >= 2,000,000:");

        university.queryDepartments(2000000).forEach(d -> System.out.println(d));

        System.out.println("");


        System.out

                .println("budget of the department that student S03 is affiliated with: "

                        + university.queryBudget("S03"));

        System.out

                .println("budget of the department that student S05 is affiliated with: "

                        + university.queryBudget("S05"));

        System.out

                .println("budget of the department that student S07 is affiliated with: "

                        + university.queryBudget("S07"));

        System.out.println("");


        System.out.println("student ID, department name, department budget");

        university.queryStudentDepartment().forEach(e -> System.out.println(

                e.getKey() + ", " + e.getValue().name() + ", " + e.getValue().budget()));

        System.out.println("");


        System.out.println("sum of budgets: " + university.queryTotalBudget());

        System.out.println("");


        System.out.println("maximum department budget: "

                + university.queryMaximumDepartmentBudget());

        System.out.println("");


        System.out.println("ZIP code, number of students");

        university.queryZipCodeStudents().map(e -> e.getKey() + ", " + e.getValue())

                .sorted().forEach(s -> System.out.println(s));

        System.out.println("");


        /*

         * System.out.println("students with ZIP code 12222:"); university.queryStudents(12222).forEach(e ->

         * System.out.println(e)); System.out.println("");

         *

         * System.out.println("students with ZIP code 12225:"); university.queryStudents(12225).forEach(e ->

         * System.out.println(e)); System.out.println("");

         *

         * System.out.println("building name, number of students"); university.queryBuildingStudents().map(e ->

         * e.getKey() + ", " + e.getValue()) .sorted().forEach(s -> System.out.println(s)); System.out.println("");

         *

         * System.out.println("departments with maximum budget"); university.queryMaxBudgetDepartments().forEach(d ->

         * System.out.println(d));

         */

    }


    /**

     * Returns a {@code Stream} of {@code Department}s whose balance is greater than or equal to the specified amount.

     *

     * @param amount

     *            an amount

     * @return a {@code Stream} of {@code Department}s whose balance is greater than or equal to the specified amount

     */

    public Stream<Department> queryDepartments(double amount) {

        return departments.values().stream().filter(d -> d.budget() >= amount);

    }


    /**

     * Returns the sum of the balances of the {@code Department}s of this {@code University}.

     *

     * @return the sum of the balances of the {@code Department}s of this {@code University}

     */

    public Double queryTotalBudget() {

        return departments.values().stream().mapToDouble(d -> d.budget()).sum();

    }


    /**

     * Returns the maximum of the balances of the {@code Department}s of this {@code University}.

     *

     * @return the maximum of the balances of the {@code Department}s of this {@code University}

     */

    public Double queryMaximumDepartmentBudget() {

        return departments.values().stream().mapToDouble(d -> d.budget()).max()

                .getAsDouble();

    }


    /**

     * Returns the budget of the {@code Department} that the specified {@code Student} is affiliated with.

     *

     * @param studentID

     *            the ID of a {@code Student}

     * @return the budget of the {@code Department} that the specified {@code Student} is affiliated with

     */

    public Double queryBudget(String studentID) {

        var s = students.get(studentID);

        if (s == null)

            return null;

        var d = departments.get(s.departmentName());

        return d == null ? null : d.budget();

    }


    /**

     * Returns, for each {@code Student}, the {@code Department} that the {@code Student} is affiliated with.

     *

     * @return a {@code Stream} of {@code Entry<String, Department>} instances each representing the ID of a

     *         {@code Student} and the {@code Department} that the {@code Student} is affiliated with

     */

    public Stream<Entry<String, Department>> queryStudentDepartment() {

        return students.values().stream()

                .map(s -> Map.entry(s.studentID(), departments.get(s.departmentName())));

    }

    /**

     * return a Stream of Student s that have the specified zipCode .

     * @param Zip code

     * @return return a  {@code Stream} of {@code Student} that have the specified zipCode.

     */

    public Stream<Student> queryStudents(int zipCode) {

        System.out.println("Task1 running?");

        return students.values().stream().filter(s -> s.zipCode() == zipCode);

    }

    /**

     *  return a Stream of Entry s each having a buildingName and

     *  the number of Student s whose department is in the corresponding building.

     * @param zipCode

     * @return

     */

    public Stream<Entry<String, String>> queryBuildingStudents() {

        //var s = students.get(zipCode);

        //List<Integer> list =

        //Stream<String> result=students.values().stream()

        Stream<Entry<String, String>> departmentList=

                students.values().stream().map(s -> Map.entry(s.studentID(), departments.get(s.departmentName()).buildingName()));

        

        return  null;

                //.map(s -> Map.entry(s.zipCode(), departments.get(s.departmentName())));

    }

    /** find the Department s with the maximum budget.

     *

     * @return

     */

    public Stream<Department> queryMaxBudgetDepartments() {

        return departments.values().stream().filter(d -> d.budget()==queryMaximumDepartmentBudget());

    }

    /**

     * Finds the number of {@code Student}s for each ZIP code.

     *

     * @return a {@code Stream} of {@code Entry} instances each containing a ZIP code and the number of {@code Student}s

     */

    public Stream<Entry<Integer, Long>> queryZipCodeStudents() {

        var summary = students.values().stream()

                .collect(Collectors.groupingBy(s -> s.zipCode(), Collectors.counting()));

        return summary.entrySet().stream();

    }


    /**

     * Adds data to the specified {@code University}.

     *

     * @param university

     *            a {@code University}

     * @param numberOfDepartments

     *            the number of {@code Department}s of the {@code University}

     */

    public static void addData(University university, int numberOfDepartments) {

        BiConsumer<String, Map.Entry<Double, String>> addDeparment = (s, i) -> university

                .register(new Department(s, i.getKey(), i.getValue()));

        BiConsumer<String, Map.Entry<Integer, String>> addStudent = (s, e) -> university

                .register(new Student(s, e.getKey(), e.getValue()));

        addData(numberOfDepartments, addDeparment, addStudent);

    }


    /**

     * Adds data related to {@code Department}s and {@code Student}s.

     *

     * @param numberOfDepartments

     *            the number of {@code Department}s

     * @param addDepartment

     *            a {@code BiConsumer} for adding each {@code Department}

     * @param addStudent

     *            a {@code BiConsumer} for adding each {@code Student}

     */

    public static void addData(int numberOfDepartments,

            BiConsumer<String, Map.Entry<Double, String>> addDepartment,

            BiConsumer<String, Map.Entry<Integer, String>> addStudent) {

        double[] balances = { 1000000.0, 2000000.0, 3000000.0 };

        int[] zipCodes = { 12222, 12223, 12224, 12225 };

        int n = 3;

        int digits = (int) Math.ceil(Math.log10(3 * numberOfDepartments));

        IntStream.range(0, numberOfDepartments).forEach(i -> {

            var departmentName = String.format("D%0" + digits + "d", i);

            var buildingName = String.format("B%0" + digits + "d", i / 2);

            addDepartment.accept(departmentName,

                    Map.entry(balances[i % balances.length], buildingName));

            IntStream.range(0, n).forEach(j -> addStudent.accept(

                    String.format("S%0" + digits + "d", 2 * i + j),

                    Map.entry(zipCodes[(2 * i + j) % zipCodes.length], departmentName)));

            if (i == numberOfDepartments - 1)

                addStudent.accept(String.format("S%0" + digits + "d", 2 * i + n), Map

                        .entry(zipCodes[(2 * i + n) % zipCodes.length], departmentName));

        });

    }


} 