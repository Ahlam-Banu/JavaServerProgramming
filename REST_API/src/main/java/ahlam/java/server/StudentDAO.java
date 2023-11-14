package ahlam.java.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;

public class StudentDAO {
	//this class will directly communicate with db
	private String jdbcURL = "jdbc:mariadb://mariadb.vamk.fi:3306/e2101083_restapi_db";
	private String jdbcUserName = "e2101083";
	private String jdbcPassword = "9SbjzjcK6hQ";
	
	//Constructor
	public StudentDAO() {}
	
	private static final String SELECT_ALL_STUDENTS_QUERY = "SELECT * FROM students";
	private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM students WHERE id=?";
	private static final String INSERT_STUDENT_QUERY = "INSERT INTO students (id, firstName, lastName) VALUES (?, ?, ?)";
	private static final String DELETE_STUDENT_QUERY = "DELETE FROM students WHERE id=?";
	
	
	protected Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<StudentModel> selectAllStudents() {
        List<StudentModel> students = new ArrayList<StudentModel>();
        
        try(Connection conn = getConnection();
        	PreparedStatement ps = conn.prepareStatement(SELECT_ALL_STUDENTS_QUERY)) {
            ResultSet rs = ps.executeQuery();
        		
        	while (rs.next()) {
        		int id = rs.getInt(1);
        		String firstName = rs.getString(2);
        		String lastName = rs.getString(3);
        		
        		students.add(new StudentModel(id,firstName,lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
	
	public StudentModel selectStudentByID(int id) {
		StudentModel student = null;

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_STUDENT_BY_ID);) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int studentId = rs.getInt("id");
	            String firstName = rs.getString("firstName");
	            String lastName = rs.getString("lastName");

	            student = new StudentModel(studentId, firstName, lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}
	
	public void insertStudent(StudentModel student) {
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT_QUERY);) {
			ps.setInt(1, student.getId());
			ps.setString(2, student.getFirstName());
			ps.setString(3, student.getLastName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteStudent(int id) {
		boolean rowDeleted = false;
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_STUDENT_QUERY);) {
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

}
