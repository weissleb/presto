/*
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
package io.trino.operator.aggregation.minmaxby;

import io.trino.spi.block.Block;
import io.trino.spi.block.BlockBuilder;
import io.trino.spi.type.Type;

class ObjectAndBlockPositionStateSerializer
        extends KeyAndBlockPositionValueStateSerializer<ObjectAndBlockPositionValueState>
{
    ObjectAndBlockPositionStateSerializer(Type firstType, Type secondType)
    {
        super(firstType, secondType);
    }

    @Override
    void readFirstField(Block block, int position, ObjectAndBlockPositionValueState state)
    {
        state.setFirst(firstType.getObject(block, position));
    }

    @Override
    void writeFirstField(BlockBuilder out, ObjectAndBlockPositionValueState state)
    {
        firstType.writeObject(out, state.getFirst());
    }
}
