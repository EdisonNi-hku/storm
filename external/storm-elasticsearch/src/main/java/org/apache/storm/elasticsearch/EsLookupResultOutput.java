/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.storm.elasticsearch;

import java.io.Serializable;
import java.util.Collection;

import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.elasticsearch.client.Response;

/**
 * The adapter to convert the results fetched from Elasticsearch to values.
 *
 * @since 0.11
 */
public interface EsLookupResultOutput extends Serializable {

    /**
     * Convert Elasticsearch response to a collection of {@link Values}.
     *
     * @return collection of values to emit.
     */
    Collection<Values> toValues(Response response);

    /**
     * {@link Fields} to be declared for output.
     *
     * @return output fields to declare.
     */
    Fields fields();
}
