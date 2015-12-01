package com.sina.ea;

public enum TemplatePlatform {
	
	WAP {
		@Override
		public String getTemplate() {
			return "31,36,37,38,39";
		}

		@Override
		public String getPlatform() {
			return "wap";
		}
	},APP{
		@Override
		public String getTemplate() {
			return "61";
		}

		@Override
		public String getPlatform() {
			return "app";
		}
	};
	
	public abstract String getTemplate();
	
	public abstract String getPlatform();
}
