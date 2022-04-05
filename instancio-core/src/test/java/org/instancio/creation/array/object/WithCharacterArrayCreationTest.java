/*
 * Copyright 2022 the original author or authors.
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
package org.instancio.creation.array.object;

import org.instancio.pojo.arrays.object.WithCharacterArray;
import org.instancio.testsupport.tags.NonDeterministicTag;
import org.instancio.testsupport.templates.ArrayCreationTestTemplate;
import org.instancio.testsupport.templates.NumberOfExecutions;
import org.instancio.testsupport.utils.ArrayUtils;

public class WithCharacterArrayCreationTest extends ArrayCreationTestTemplate<WithCharacterArray> {

    @Override
    protected int minNumberOfGeneratedValues() {
        return 10;
    }

    @Override
    @NonDeterministicTag
    @NumberOfExecutions(50)
    protected void verify(WithCharacterArray result) {
        generatedValues.addAll(ArrayUtils.toList(result.getValues()));
    }

}