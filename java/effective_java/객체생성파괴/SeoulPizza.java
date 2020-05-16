package java_study.builder;

import java.util.Objects;

public class SeoulPizza extends Pizza {

	public enum Size { SMALL, MEDIUM, LARGE}
	private final Size size;
	
	private SeoulPizza(Builder builder) {
		super(builder);
		this.size = builder.size;
	}
	
	public static class Builder extends Pizza.Builder<Builder>{

		private final Size size;
		
		public Builder( Size size) {
			this.size = Objects.requireNonNull(size);
		}
		
		@Override
		public SeoulPizza build() {
			return new SeoulPizza(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
		
	}


}
