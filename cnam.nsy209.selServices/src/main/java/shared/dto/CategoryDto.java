package shared.dto;

import java.io.Serializable;



public class CategoryDto implements Serializable {

	/**
	 * For checking version
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;

	private String name;

	
	/* getter and setter */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
