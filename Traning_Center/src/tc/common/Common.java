package tc.common;

import tc.dto.Class;
import tc.dto.Course;

public final class Common {

	private static Class class_name;
	private static Course course_name;

	public static Course getCourse_name() {
		return course_name;
	}

	public static void setCourse_name(Course course_name) {
		Common.course_name = course_name;
	}

	public static Class getClass_name() {
		return class_name;
	}

	public static void setClass_name(Class class_name) {
		Common.class_name = class_name;
	}

}
