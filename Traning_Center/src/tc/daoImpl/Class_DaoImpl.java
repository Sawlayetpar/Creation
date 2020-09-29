package tc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tc.common.CommonUtil;
import tc.common.Connection_Manager;
import tc.dao.Class_Dao;
import tc.dto.Class;
import tc.dto.Course;

public class Class_DaoImpl implements Class_Dao {

//	private static final String SELECT = "select * from class where 2 > 1";
//	private DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void create(Class classes) {
		String sql = "insert into class (start_date, end_date, course_id) values(? ,? ,? )";
//		java.sql.Date sDate = new java.sql.Date(classes.getStart_date().getTime());
//		java.sql.Date eDate = new java.sql.Date(classes.getEnd_date().getTime());

		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			
//			java.util.Date date = new java.util.Date();	
			statement.setDate(1, CommonUtil.setCustomDate(classes.getStart_date())); 
			statement.setDate(2, CommonUtil.setCustomDate(classes.getEnd_date()));
			statement.setInt(3, classes.getCourse().getId());
			
			statement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Class> findByName(Course course) {
//		String sql = "select c.id id, c.start_date sdate, c.end_date edate,crs.course crsId,crs.course crsName"
//				+ "from class c "
//				+ "join course crs on crs.course = c.id where c.id = 1 ";
		
		String sql = "select class.id,class.start_date,class.end_date,course.id,course.name "
				+ "from class "
				+ "inner join course "
				+ "on class.course_id = course.id ";
		
		StringBuilder sb = new StringBuilder(sql);
		List<Object> params = new ArrayList<>();
		List<Class> classes_list = new LinkedList<>();
		
		if(null != course) {
			sb.append("and class.course_id = ? ");
			params.add(course.getId());
		}
		
		
		
		try(Connection conn = Connection_Manager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())){
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Course courses = new Course();
				courses.setId(rs.getInt("course.id"));
				courses.setName(rs.getString("course.name"));
				
				Class classes = new Class();
				classes.setCourse(courses);
				classes.setId(rs.getInt("id"));
				classes.setStart_date(rs.getDate("start_date"));
				classes.setEnd_date(rs.getDate("end_date"));
				
				classes_list.add(classes);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return classes_list;
	}

}
