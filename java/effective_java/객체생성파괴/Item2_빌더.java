
public class Item2_빌더 {

	private String name;
	private String addr;
	private Integer age;
	private String email;
	
	public static class Builder{
		
		//필수 
		private final String name;
		private final Intege age;
		
		//선택
		private String addr = "";
		private String email= "";
		
		public Builder( String name, Integer age) {
			this.name = name;
			this.age = age;
		}
		
		public Builder addr( String addr) {
			this.addr = addr;
			return this;
		}
		
		public Builder email( String email) {
			this.email = email;
			return this;
		}
		
		public Item2_빌더 build() {
			return new Item2_빌더( this);
		}
		
	}
	
	private Item2_빌더( Builder builder) {
		this.addr = builder.addr;
		this.email = buider.email;
		this.age = builder.age;
		this.name = builder.name;
	}
	
	public static void main( String[] args) {
		
		Item2_빌더 i = new Item2_빌더.Builder("han", 23)
								.addr("seoul")
								.email("gd@nam.com")
								.build();
		System.out.println(i.toString());
	}
}
