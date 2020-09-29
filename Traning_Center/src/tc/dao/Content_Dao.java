package tc.dao;

import java.util.List;

import tc.daoImpl.Content_Dao_Impl;
import tc.dto.Content;
import tc.dto.Course;

public interface Content_Dao{
	
	public static Content_Dao getInstance() {
		return new Content_Dao_Impl();
	}

	void create(Content content);
	List<Content> findByCourse(Course course);
}
