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

package io.sarl.lang.core;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.reflect.TypeToken;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

import io.sarl.lang.SARLVersion;
import io.sarl.lang.annotation.SarlSpecification;


/**
 * The definition of the notion of Agent in SARL.
 * An agent is an autonomous entity having some intrinsic skills to realize
 * the capacities it exhibits. An agent defines a context.
 *
 * @author $Author: srodriguez$
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification(SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING)
public class Agent extends AgentProtectedAPIObject implements Identifiable {

	private final UUID id;

	private final Map<Class<? extends Capacity>, Skill> skills = new ConcurrentHashMap<>();

	private final UUID parentID;

	/**
	 * Creates a new agent by parent <code>parentID</code>.
	 *
	 * @param provider - the provider of built-in capacities for this agent.
	 * @param parentID - the agent's spawner.
	 * @param agentID - the identifier of the agent, or
	 *                  <code>null</code> for computing it randomly.
	 */
	public Agent(
			BuiltinCapacitiesProvider provider,
			UUID parentID,
			UUID agentID) {
		this.parentID = parentID;
		this.id = (agentID == null) ? UUID.randomUUID() : agentID;
		if (provider != null) {
			final Map<Class<? extends Capacity>, Skill> builtinCapacities = provider.getBuiltinCapacities(this);
			if (builtinCapacities != null && !builtinCapacities.isEmpty()) {
				this.skills.putAll(builtinCapacities);
			}
		}
	}

	@Override
	protected String attributesToString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("id = "); //$NON-NLS-1$
		builder.append(this.id);
		builder.append(", parentID="); //$NON-NLS-1$
		builder.append(this.parentID);
		return builder.toString();
	}

	@Override
	@Pure
	public String toString() {
		return getClass().getSimpleName()
				+ " [" + attributesToString() //$NON-NLS-1$
				+ "]"; //$NON-NLS-1$
	}

	/**
	 * Replies the agent's spawner's ID.
	 *
	 * @return the identifier of the agent's spawner.
	 */
	@Pure
	public UUID getParentID() {
		return this.parentID;
	}

	@Override
	@Pure
	public UUID getID() {
		return this.id;
	}

	/**
	 * Set the skill for the {@link Capacity} <code>capacity</code>.
	 *
	 * @param <S> - type of the skill.
	 * @param capacity capacity to set.
	 * @param skill implementaion of <code>capacity</code>.
	 * @return the skill that was set.
	 * @deprecated since 0.4, see {@link #setSkill(Skill, Class...)}
	 */
	@Inline("setSkill($2, $1)")
	@Deprecated
	protected <S extends Skill> S setSkill(Class<? extends Capacity> capacity, S skill) {
		return setSkill(skill, capacity);
	}

	@Override
	@SafeVarargs
	@Inline("$setSkill($1, $2)")
	protected final <S extends Skill> S setSkill(S skill, Class<? extends Capacity>... capacities) {
		return $setSkill(skill, capacities);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected <S extends Skill> S $setSkill(S skill, Class<? extends Capacity>... iCapacities) {
		assert skill != null : "the skill parameter must not be null"; //$NON-NLS-1$
		skill.setOwner(this);
		if (iCapacities == null || iCapacities.length == 0) {
			runOnImplementedCapacities(skill, (capacity) -> {
				final Skill oldS = this.skills.put(capacity, skill);
				skill.registerUse();
				if (oldS != null && oldS != skill) {
					oldS.unregisterUse();
				}
			});
		} else {
			for (final Class<? extends Capacity> capacity : iCapacities) {
				assert capacity != null : "the capacity parameter must not be null"; //$NON-NLS-1$
				assert capacity.isInterface() : "the capacity parameter must be an interface"; //$NON-NLS-1$
				if (!capacity.isInstance(skill)) {
					throw new InvalidParameterException(
							"the skill must implement the given capacity " //$NON-NLS-1$
							+ capacity.getName());
				}
				final Skill oldS = this.skills.put(capacity, skill);
				skill.registerUse();
				if (oldS != null && oldS != skill) {
					oldS.unregisterUse();
				}
			}
		}
		return skill;
	}

	private static void runOnImplementedCapacities(Skill skill, Procedure1<Class<? extends Capacity>> callback) {
		TypeToken.of(skill.getClass()).getTypes().interfaces().stream().parallel().forEach((it) -> {
			final Class<?> type = it.getRawType();
			if (Capacity.class.isAssignableFrom(type) && !Capacity.class.equals(type)) {
				callback.apply(type.asSubclass(Capacity.class));
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	@Inline("$setSkill($2, $1)")
	protected <S extends Skill> void operator_mappedTo(Class<? extends Capacity> capacity, S skill) {
		$setSkill(skill, capacity);
	}

	@Override
	protected <S extends Capacity> S clearSkill(Class<S> capacity) {
		assert capacity != null;
		final Skill skill = this.skills.remove(capacity);
		if (skill != null) {
			skill.unregisterUse();
		}
		return capacity.cast(skill);
	}

	@Override
	@Pure
	protected <S extends Capacity> S getSkill(Class<S> capacity) {
		assert capacity != null;
		final S skill = capacity.cast(this.skills.get(capacity));
		if (skill == null) {
			throw new UnimplementedCapacityException(capacity, this.getID());
		}
		return skill;
	}

	@Override
	@Pure
	protected boolean hasSkill(Class<? extends Capacity> capacity) {
		assert capacity != null;
		return this.skills.containsKey(capacity);
	}

	@Override
	@Pure
	protected boolean isMe(Address address) {
		return (address != null) && (this.id.equals(address.getUUID()));
	}

	@Override
	@Pure
	protected boolean isMe(UUID uID) {
		return (uID != null) && (this.id.equals(uID));
	}

	@Override
	@Pure
	protected boolean isFromMe(Event event) {
		return (event != null) && isMe(event.getSource());
	}

}
