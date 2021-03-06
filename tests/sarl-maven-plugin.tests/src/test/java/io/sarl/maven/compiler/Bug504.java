/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2016 the original authors or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sarl.maven.compiler;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.maven.it.Verifier;
import org.apache.maven.shared.utils.io.FileUtils;
import org.junit.Test;

import io.sarl.lang.SARLVersion;

/**
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SuppressWarnings("all")
public class Bug504 extends AbstractMojoTest {

	@Test
	public void compile() throws Exception {
		Verifier verifier = executeMojo("bug504", "compile");
		Path path = FileSystems.getDefault().getPath(
				"src", "main", "generated-sources", "sarl",
				"io", "sarl", "elevatorsim", "SimulatorInteraction.java");
		assertNotNull(path);
		verifier.assertFilePresent(path.toString());
		File file = new File(verifier.getBasedir(), path.toString());
		String fileContent = FileUtils.fileRead(file);
		assertEquals(multilineString(
				"package io.sarl.elevatorsim;",
				"",
				"import io.sarl.elevatorsim.SimulatorPush;",
				"import io.sarl.elevatorsim.events.SendCarAction;",
				"import io.sarl.lang.annotation.SarlSpecification;",
				"import io.sarl.lang.annotation.SyntheticMember;",
				"import io.sarl.lang.core.Agent;",
				"import io.sarl.lang.core.Skill;",
				"import java.io.IOException;",
				"import org.eclipse.xtext.xbase.lib.Exceptions;",
				"",
				"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
				"@SuppressWarnings(\"all\")",
				"public class SimulatorInteraction extends Skill implements SimulatorPush {",
				"  public void sendCar(final int a, final int b, final int c, final Object d, final Object e) {",
				"  }",
				"  ",
				"  @Override",
				"  public void pushSendCarAction(final SendCarAction action) {",
				"    try {",
				"      this.sendCar(action.car, action.floor, action.nextDirection, ",
				"        null, null);",
				"    } catch (final Throwable _t) {",
				"      if (_t instanceof IOException) {",
				"        final IOException e = (IOException)_t;",
				"        e.printStackTrace();",
				"      } else {",
				"        throw Exceptions.sneakyThrow(_t);",
				"      }",
				"    }",
				"  }",
				"  ",
				"  /**",
				"   * Construct a skill. The owning agent is unknown.",
				"   */",
				"  @SyntheticMember",
				"  public SimulatorInteraction() {",
				"    super();",
				"  }",
				"  ",
				"  /**",
				"   * Construct a skill.",
				"   * @param owner - agent that is owning this skill.",
				"   */",
				"  @SyntheticMember",
				"  public SimulatorInteraction(final Agent owner) {",
				"    super(owner);",
				"  }",
				"}"), fileContent);
	}

}
