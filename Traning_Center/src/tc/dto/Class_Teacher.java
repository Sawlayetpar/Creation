package tc.dto;

public class Class_Teacher {

	private Class class_id;
	private Teacher teacher_id;
	private boolean incharge;

	public Class getClass_id() {
		return class_id;
	}

	public void setClass_id(Class class_id) {
		this.class_id = class_id;
	}

	public Teacher getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Teacher teacher_id) {
		this.teacher_id = teacher_id;
	}

	public boolean isIncharge() {
		return incharge;
	}

	public void setIncharge(boolean incharge) {
		this.incharge = incharge;
	}

}
