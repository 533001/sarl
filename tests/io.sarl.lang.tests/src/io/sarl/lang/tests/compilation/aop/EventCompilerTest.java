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

package io.sarl.lang.tests.compilation.aop;

import static org.junit.Assert.assertEquals;

import com.google.inject.Inject;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.compiler.CompilationTestHelper;
import org.eclipse.xtext.xbase.compiler.CompilationTestHelper.Result;
import org.junit.Test;

import io.sarl.lang.SARLVersion;
import io.sarl.tests.api.AbstractSarlTest;

/**
 * @author $Author: srodriguez$
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SuppressWarnings("all")
public class EventCompilerTest extends AbstractSarlTest {
	@Inject
	private CompilationTestHelper compiler;

	@Test
	public void basicCompile_withBlock() throws Exception {
		String source = "event E1 { }";
		String expected = multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588368462L;",
				"}",
				""
				);
		this.compiler.assertCompilesTo(source, expected);
	}

	@Test
	public void basicCompile_withoutBlock() throws Exception {
		String source = "event E1";
		String expected = multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588368462L;",
				"}",
				""
				);
		this.compiler.assertCompilesTo(source, expected);
	}

	@Test
	public void withVarAttributesCompile() throws Exception {
		String source = multilineString(
				"event E1 {",
				"  var name : String",
				"}"
				);
		String expected = multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"import org.eclipse.xtext.xbase.lib.Pure;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  public String name;",
				"  ",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @Override",
				"  @Pure",
				"  @SyntheticMember",
				"  public boolean equals(final Object obj) {",
				"    if (this == obj)",
				"      return true;",
				"    if (obj == null)",
				"      return false;",
				"    if (getClass() != obj.getClass())",
				"      return false;",
				"    E1 other = (E1) obj;",
				"    if (this.name == null) {",
				"      if (other.name != null)",
				"        return false;",
				"    } else if (!this.name.equals(other.name))",
				"      return false;",
				"    return super.equals(obj);",
				"  }",
				"  ",
				"  @Override",
				"  @Pure",
				"  @SyntheticMember",
				"  public int hashCode() {",
				"    final int prime = 31;",
				"    int result = super.hashCode();",
				"    result = prime * result + ((this.name== null) ? 0 : this.name.hashCode());",
				"    return result;",
				"  }",
				"  ",
				"  /**",
				"   * Returns a String representation of the E1 event's attributes only.",
				"   */",
				"  @SyntheticMember",
				"  @Pure",
				"  protected String attributesToString() {",
				"    StringBuilder result = new StringBuilder(super.attributesToString());",
				"    result.append(\"name  = \").append(this.name);",
				"    return result.toString();",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 1787001662L;",
				"}",
				""
				);
		this.compiler.assertCompilesTo(source, expected);
	}

	@Test
	public void inheritanceCompile() throws Exception {
		String source = multilineString(
				"event E1 {",
				"	var name : String",
				"}",
				"",
				"event E2 extends E1{",
				"}"
				);
		final String expectedE2 = multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E2 extends E1 {",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E2() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E2(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 2189L;",
				"}",
				""
				);
		this.compiler.compile(source, (r) -> assertEquals(expectedE2,r.getGeneratedCode("E2")));
	}

	@Test
	public void noStaticField() throws Exception {
		String source = multilineString(
				"event E1 {",
				"	val titi : int = 4",
				"	val toto : int",
				"	new(a : int) {",
				"		this.toto = a",
				"	}",
				"}"
				);
		String expected = multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Event;",
				"import org.eclipse.xtext.xbase.lib.Pure;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  public final int titi = 4;",
				"  ",
				"  public final int toto;",
				"  ",
				"  public E1(final int a) {",
				"    this.toto = a;",
				"  }",
				"  ",
				"  @Override",
				"  @Pure",
				"  @SyntheticMember",
				"  public boolean equals(final Object obj) {",
				"    if (this == obj)",
				"      return true;",
				"    if (obj == null)",
				"      return false;",
				"    if (getClass() != obj.getClass())",
				"      return false;",
				"    E1 other = (E1) obj;",
				"    if (other.titi != this.titi)",
				"      return false;",
				"    if (other.toto != this.toto)",
				"      return false;",
				"    return super.equals(obj);",
				"  }",
				"  ",
				"  @Override",
				"  @Pure",
				"  @SyntheticMember",
				"  public int hashCode() {",
				"    final int prime = 31;",
				"    int result = super.hashCode();",
				"    result = prime * result + this.titi;",
				"    result = prime * result + this.toto;",
				"    return result;",
				"  }",
				"  ",
				"  /**",
				"   * Returns a String representation of the E1 event's attributes only.",
				"   */",
				"  @SyntheticMember",
				"  @Pure",
				"  protected String attributesToString() {",
				"    StringBuilder result = new StringBuilder(super.attributesToString());",
				"    result.append(\"titi  = \").append(this.titi);",
				"    result.append(\"toto  = \").append(this.toto);",
				"    return result.toString();",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 598944340L;",
				"}",
				""
				);
		this.compiler.assertCompilesTo(source, expected);
	}

	@Test
	public void eventmodifier_none() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"event E1"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588368462L;",
				"}",
				""));
	}

	@Test
	public void eventmodifier_public() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"public event E1"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588368462L;",
				"}",
				""));
	}

	@Test
	public void eventmodifier_package() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"package event E1"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"class E1 extends Event {",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588368462L;",
				"}",
				""));
	}

	@Test
	public void eventmodifier_final() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"final event E1"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public final class E1 extends Event {",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588368462L;",
				"}",
				""));
	}

	@Test
	public void fieldmodifier_none() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"event E1 {",
				"	var field : int",
				"}"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"import org.eclipse.xtext.xbase.lib.Pure;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  public int field;",
				"  ",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @Override",
				"  @Pure",
				"  @SyntheticMember",
				"  public boolean equals(final Object obj) {",
				"    if (this == obj)",
				"      return true;",
				"    if (obj == null)",
				"      return false;",
				"    if (getClass() != obj.getClass())",
				"      return false;",
				"    E1 other = (E1) obj;",
				"    if (other.field != this.field)",
				"      return false;",
				"    return super.equals(obj);",
				"  }",
				"  ",
				"  @Override",
				"  @Pure",
				"  @SyntheticMember",
				"  public int hashCode() {",
				"    final int prime = 31;",
				"    int result = super.hashCode();",
				"    result = prime * result + this.field;",
				"    return result;",
				"  }",
				"  ",
				"  /**",
				"   * Returns a String representation of the E1 event's attributes only.",
				"   */",
				"  @SyntheticMember",
				"  @Pure",
				"  protected String attributesToString() {",
				"    StringBuilder result = new StringBuilder(super.attributesToString());",
				"    result.append(\"field  = \").append(this.field);",
				"    return result.toString();",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 685900599L;",
				"}",
				""));
	}

	@Test
	public void fieldmodifier_public() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"event E1 {",
				"	public var field : int",
				"}"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Address;",
				"import io.sarl.lang.core.Event;",
				"import org.eclipse.xtext.xbase.lib.Pure;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  public int field;",
				"  ",
				"  /**",
				"   * Construct an event. The source of the event is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public E1() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct an event.",
				"   * @param source - address of the agent that is emitting this event.",
				"   */",
				"  @SyntheticMember",
				"  public E1(final Address source) {",
				"    super(source);",
				"  }",
				"  ",
				"  @Override",
				"  @Pure",
				"  @SyntheticMember",
				"  public boolean equals(final Object obj) {",
				"    if (this == obj)",
				"      return true;",
				"    if (obj == null)",
				"      return false;",
				"    if (getClass() != obj.getClass())",
				"      return false;",
				"    E1 other = (E1) obj;",
				"    if (other.field != this.field)",
				"      return false;",
				"    return super.equals(obj);",
				"  }",
				"  ",
				"  @Override",
				"  @Pure",
				"  @SyntheticMember",
				"  public int hashCode() {",
				"    final int prime = 31;",
				"    int result = super.hashCode();",
				"    result = prime * result + this.field;",
				"    return result;",
				"  }",
				"  ",
				"  /**",
				"   * Returns a String representation of the E1 event's attributes only.",
				"   */",
				"  @SyntheticMember",
				"  @Pure",
				"  protected String attributesToString() {",
				"    StringBuilder result = new StringBuilder(super.attributesToString());",
				"    result.append(\"field  = \").append(this.field);",
				"    return result.toString();",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 685900599L;",
				"}",
				""));
	}

	@Test
	public void constructormodifier_none() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"event E1 {",
				"	new { super(null) }",
				"}"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  public E1() {",
				"    super(null);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588370691L;",
				"}",
				""));
	}

	@Test
	public void constructormodifier_public() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"event E1 {",
				"	public new { super(null) }",
				"}"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  public E1() {",
				"    super(null);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588370691L;",
				"}",
				""));
	}

	@Test
	public void constructormodifier_private() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"event E1 {",
				"	private new { super(null) }",
				"}"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  private E1() {",
				"    super(null);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588370691L;",
				"}",
				""));
	}

	@Test
	public void constructormodifier_package() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"event E1 {",
				"	package new { super(null) }",
				"}"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  E1() {",
				"    super(null);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588370691L;",
				"}",
				""));
	}

	@Test
	public void constructormodifier_protected() throws Exception {
		this.compiler.assertCompilesTo(
			multilineString(
				"event E1 {",
				"	protected new { super(null) }",
				"}"
			),
			multilineString(
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Event;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class E1 extends Event {",
				"  protected E1() {",
				"    super(null);",
				"  }",
				"  ",
				"  @SyntheticMember",
				"  private final static long serialVersionUID = 588370691L;",
				"}",
				""));
	}

}
