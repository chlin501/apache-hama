/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hama.bsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.Optional;
import junit.framework.TestCase;
import org.junit.Test;
import org.mortbay.log.Log;

import static org.apache.hama.bsp.State.*;

public class TestStateTransition extends TestCase {
	
  @Test
  public void test() throws Exception {
	final Predicate<Boolean> unconditional = p -> true;
    final List<Predicate<Boolean>> conditions = 
      new ArrayList<Predicate<Boolean>>(); 
    conditions.add(unconditional);
    final Transition transition1 = new Transition(
      Stopped, Initializing, conditions
    );
    final Transition transition2 = new Transition(
      Initializing, Running, conditions
    );
    final Transition transition3 = new Transition(
      Running, ShuttingDown, conditions
    );
    final Transition transition4 = new Transition(
      ShuttingDown, Stopped, conditions
    );
    Transitions.add(transition1);
    Transitions.add(transition2);
    Transitions.add(transition3);
    Transitions.add(transition4);
    final Optional<State> toInit = 
      Transitions.transit(State.Stopped, conditions);
    assertTrue(toInit.equals(Optional.of(State.Initializing)));
    final Optional<State> toRunning = 
      Transitions.transit(State.Initializing, conditions);
    assertTrue(toRunning.equals(Optional.of(State.Running)));
    final Optional<State> toShuttingDown = 
      Transitions.transit(State.Running, conditions);
    assertTrue(toShuttingDown.equals(Optional.of(State.ShuttingDown)));
    final Optional<State> toStopped = 
      Transitions.transit(State.ShuttingDown, conditions);
    assertTrue(toStopped.equals(Optional.of(State.Stopped)));
  }

}
