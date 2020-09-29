package tc.dto;

public class Content {

	private int id;
	private String name;
	private String description;
	private Course course_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Course course_id) {
		this.course_id = course_id;
	}
}
