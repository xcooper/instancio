/*
 *  Copyright 2022 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.instancio.generator;

import org.instancio.Generator;

import java.util.Optional;

/**
 * Marker interface for configurable {@link Generator}s.
 *
 * @param <T> generated type
 */
@SuppressWarnings("unused")
public interface GeneratorSpec<T> {

    /**
     * Returns name of the spec. Will return an empty result if this instance is a lambda.
     *
     * @return spec name, if defined
     */
    default Optional<String> name() {
        return Optional.ofNullable(GeneratorSpecDescription.get(getClass()));
    }
}