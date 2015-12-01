package com.sina.ea;

/**
 * @author biaofei
 *
 */
@Deprecated
public enum TemplateSize {
	
	WAP {
		@Override
		public String getTemplate() {
			return "31,36,37,38";
		}

		@Override
		public String getSize() {
			return "160*120";
		}
	},
	APP {
		@Override
		public String getTemplate() {
			return "61";
		}

		@Override
		public String getSize() {
			return "152*112";
		}
	};
	
	public abstract String getTemplate();
	
	public abstract String getSize();
}
