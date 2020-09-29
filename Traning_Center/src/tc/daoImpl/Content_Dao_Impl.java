package tc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tc.common.Common;
import tc.common.Connection_Manager;
import tc.dao.Content_Dao;
import tc.dto.Content;
import tc.dto.Course;

public class Content_Dao_Impl implements Content_Dao {

	@Override
	public void create(Content content) {
		String sql = "insert into content (name,description,course_id) values(?,?,?)";

		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, content.getName());
			statement.setString(2, content.getDescription());
			statement.setInt(3, Common.getCourse_name().getId());

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Content> findByCourse(Course course) {
//		String sql = "select c.id id, c.start_date sdate, c.end_date edate,crs.course crsId,crs.course crsName"
//		+ "from class c "
//		+ "join course crs on crs.course = c.id where c.id = 1 ";

		String sql = "select content.id,content.name,content.description,course.id,course.name "
				+ "from content "
				+ "inner join course "
				+ "on content.course_id = course.id ";

		StringBuilder sb = new StringBuilder(sql);
		List<Object> obj = new ArrayList<>();
		List<Content> content = new LinkedList<>();

		if (null != course) {
			sb.append("and content.course_id = ? ");
			obj.add(course.getId());
		}

		try (Connection conn = Connection_Manager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {

			for (int i = 0; i < obj.size(); i++) {
				stmt.setObject(i + 1, obj.get(i));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Course course_local = new Course();
				course_local.setId(rs.getInt("course.id"));
				course_local.setName(rs.getString("course.name"));
				
				Content content_local = new Content();
				
				content_local.setCourse_id(course_local);
				content_local.setId(rs.getInt("content.id"));
				content_local.setName(rs.getString("content.name"));
				content_local.setDescription(rs.getString("content.description"));
				
				content.add(content_local);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}

}
