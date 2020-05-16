package java_study.builder;

public class JinjuPizza extends Pizza {

	private final boolean souce;
	
	private JinjuPizza(Builder builder) {
		super(builder);
		this.souce = builder.souce;
	}
	
	public static class Builder extends Pizza.Builder<Builder>{
		private boolean souce = false;
		
		public Builder souce() {
			this.souce = true;
			return this;
		}

		@Override
		JinjuPizza build() {
			return new JinjuPizza(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}
}
