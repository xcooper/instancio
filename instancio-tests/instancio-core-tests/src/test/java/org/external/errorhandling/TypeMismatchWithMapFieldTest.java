/*
 * Copyright 2022-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.external.errorhandling;

import org.instancio.Instancio;
import org.instancio.test.support.pojo.collections.maps.TwoMapsOfIntegerItemString;

import static org.instancio.Select.field;

class TypeMismatchWithMapFieldTest extends AbstractErrorMessageTestTemplate {

    @Override
    void methodUnderTest() {
        Instancio.of(TwoMapsOfIntegerItemString.class)
                .set(field(TwoMapsOfIntegerItemString::getMap1), "invalid arg")
                .create();
    }

    @Override
    String expectedMessage() {
        return """


                Error creating an object
                 -> at org.external.errorhandling.TypeMismatchWithMapFieldTest.methodUnderTest(TypeMismatchWithMapFieldTest.java:29)

                Reason: error assigning value to: field TwoMapsOfIntegerItemString.map1 (depth=1)

                 │ Path to root:
                 │   <1:TwoMapsOfIntegerItemString: Map<Integer, Item<String>> map1>
                 │    └──<0:TwoMapsOfIntegerItemString>   <-- Root
                 │
                 │ Format: <depth:class: field>


                Type mismatch:

                 -> Target field .............: Map<Integer, Item<String>> map1 (in org.instancio.test.support.pojo.collections.maps.TwoMapsOfIntegerItemString)
                 -> Provided argument type ...: String
                 -> Provided argument value ..: "invalid arg"

                """;
    }
}
