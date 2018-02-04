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

import java.util.List;
import java.util.function.Predicate;

class Transition {
	
  final State from;
  
  final State to;
  
  final List<Predicate<Boolean>> conditions;
  
  public Transition(final State from, final State to, 
		            final List<Predicate<Boolean>> conditions)  {
	if(null == from) throw new NullPointerException(
	  "'from' state is not provided!"
	);
	if(null == to) throw new NullPointerException(
	  "'to' state is not provided!"
	);
	if(null == conditions || conditions.isEmpty()) 
	  throw new NullPointerException("Condition is not provided!");
    this.from = from;    	  
    this.to = to;
    this.conditions = conditions;
  }

  State from() { 
    return this.from;
  }
  
  State to() { 
    return this.to;
  } 

  List<Predicate<Boolean>> conditions() { 
    return this.conditions;	
  }

}
