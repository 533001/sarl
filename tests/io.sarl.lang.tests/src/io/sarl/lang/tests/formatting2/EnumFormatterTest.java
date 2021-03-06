/*
 * Copyright (C) 2014-2016 the original authors or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sarl.lang.tests.formatting2;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.google.inject.Inject;
import junit.framework.TestSuite;
import org.eclipse.xtext.junit4.formatter.FormatterTestRequest;
import org.eclipse.xtext.junit4.formatter.FormatterTester;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Procedures;
import org.junit.Test;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.InitializationError;

import io.sarl.tests.api.AbstractSarlTest;

/** Tests for formatting enumerations.
 *
 * @author $Author: sgalland$
 * @version $Name$ $Revision$ $Date$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@RunWith(Suite.class)
@SuiteClasses({
	EnumFormatterTest.FormatterAPITest.class,
})
@SuppressWarnings("all")
public class EnumFormatterTest {

	/**
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	public static class FormatterAPITest extends AbstractFormatterTest {

		@Test
		public void empty() throws Exception {
			String source = "enum  EntityX{CONST}";
			String expected = multilineString(
					"enum EntityX {",
					"	CONST",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void modifiers() throws Exception {
			String source = "public    static    enum EntityX{CONST}";
			String expected = multilineString(
					"public static enum EntityX {",
					"	CONST",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void twoAnnotations() throws Exception {
			String source = "@Pure@Beta    enum EntityX{CONST}";
			String expected = multilineString(
					"@Pure @Beta enum EntityX {",
					"	CONST",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void threeAnnotations() throws Exception {
			String source = "@Pure@Beta\n@Hello    enum EntityX{CONST}";
			String expected = multilineString(
					"@Pure @Beta",
					"@Hello enum EntityX {",
					"	CONST",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void annotationValue() throws Exception {
			String source = "@SuppressWarnings(        value= \"name\"   )enum EntityX{CONST}";
			String expected = multilineString(
					"@SuppressWarnings(value = \"name\") enum EntityX {",
					"	CONST",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void annotationImplicitValue() throws Exception {
			String source = "@SuppressWarnings(   \"name\"   )enum EntityX{CONST}";
			String expected = multilineString(
					"@SuppressWarnings(\"name\") enum EntityX {",
					"	CONST",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void multipleLiterals() throws Exception {
			String source = "enum EntityX{CONST1,CONST2,CONST3}";
			String expected = multilineString(
					"enum EntityX {",
					"	CONST1,",
					"	CONST2,",
					"	CONST3",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void mlStandardComment1() throws Exception {
			String source = "/*Hello world.\n* That's the second line.\n*/enum A{CONST1}";
			String expected = multilineString(
					"/* Hello world.",
					" * That's the second line.",
					" */",
					"enum A {",
					"\tCONST1",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void mlStandardComment2() throws Exception {
			String source = "/*Hello world.\nThat's the second line.*/enum A{CONST1}";
			String expected = multilineString(
					"/* Hello world.",
					" * That's the second line.",
					" */",
					"enum A {",
					"\tCONST1",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void mlStandardComment3() throws Exception {
			String source = "/*      Hello world.      */enum A{CONST1}";
			String expected = multilineString(
					"/* Hello world.",
					" */",
					"enum A {",
					"\tCONST1",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void mlStandardComment4() throws Exception {
			String source = "/*      Hello world.      */enum A{/*Second comment*/CONST1}";
			String expected = multilineString(
					"/* Hello world.",
					" */",
					"enum A {",
					"\t/* Second comment",
					"\t */",
					"\tCONST1",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void mlJavaComment() throws Exception {
			String source = "/**Hello world.\nThat's the second line.*/enum A{CONST1}";
			String expected = multilineString(
					"/** Hello world.",
					" * That's the second line.",
					" */",
					"enum A {",
					"\tCONST1",
					"}",
					"");
			assertFormatted(source, expected);
		}

		@Test
		public void slComment() throws Exception {
			String source = "\n//Hello world.\nenum A{CONST1}";
			String expected = multilineString(
					"// Hello world.",
					"enum A {",
					"\tCONST1",
					"}",
					"");
			assertFormatted(source, expected);
		}

	}

}