package shared.criteria;


public class MemberCriteria {

	private String name;

	private String forname;
	
	private String town;
	

	/* constructeurs */
	public MemberCriteria(){
		
	}

	public MemberCriteria(String name){
		this.name = name;
	}
	
	public MemberCriteria(String name,String forname){
		this(name);
		this.forname = forname;
	}
	

	
	public MemberCriteria(String name,String forname,String town){
		this(name,forname);
		this.town = town;
	}
	
	/* utility method to check no attribute is null */
	public boolean isEmpty(){
		if(this.name == null && this.forname == null && this.town == null)
			return true;
		else
			return false;
	}
	
	/* getter and setter */

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForname() {
		return forname;
	}

	public void setForname(String forname) {
		this.forname = forname;
	}

	
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
	
}
