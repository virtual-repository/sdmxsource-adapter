package org.sdmx;

import org.sdmxsource.sdmx.api.model.beans.codelist.CodelistBean;
import org.sdmxsource.sdmx.api.model.mutable.base.AnnotationMutableBean;
import org.sdmxsource.sdmx.api.model.mutable.codelist.CodeMutableBean;
import org.sdmxsource.sdmx.api.model.mutable.codelist.CodelistMutableBean;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.base.AnnotationMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.codelist.CodeMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.codelist.CodelistMutableBeanImpl;

public class CodelistBuilder {

	public static CodelistBuilder list() {
		return new CodelistBuilder();
	}
	
	private CodelistMutableBean bean = new CodelistMutableBeanImpl();

	public CodelistBuilder() {
		bean.addName("en", "testlist");
		bean.setAgencyId("SDMX");
		bean.setId("testlist");
	}

	public CodelistBuilder add(CodeBuilder builder) {
		bean.addItem(builder.build());
		return this;
	}

	public CodelistBean end() {
		return bean.getImmutableInstance();
	}

	public static CodeBuilder code(String id) {
		return new CodeBuilder(id);
	}

	public static AnnotationBuilder anno() {
		return new AnnotationBuilder();
	}

	public static class CodeBuilder {

		private CodeMutableBean bean = new CodeMutableBeanImpl();

		public CodeBuilder(String id) {
			bean.setId(id);
		}

		public CodeBuilder name(String val, String lang) {
			bean.addName(lang, val);
			return this;
		}

		public CodeBuilder description(String val, String lang) {
			bean.addDescription(lang, val);
			return this;
		}

		public CodeBuilder add(AnnotationBuilder builder) {
			bean.addAnnotation(builder.build());
			return this;
		}

		public CodeMutableBean build() {
			return bean;
		}
	}

	public static class AnnotationBuilder {

		private AnnotationMutableBean bean = new AnnotationMutableBeanImpl();

		public AnnotationBuilder title(String title) {
			bean.setTitle(title);
			return this;
		}

		public AnnotationBuilder type(String type) {
			bean.setType(type);
			return this;
		}

		public AnnotationBuilder text(String val, String lang) {
			bean.addText(lang, val);
			return this;
		}

		public AnnotationMutableBean build() {
			return bean;
		}
	}

}
