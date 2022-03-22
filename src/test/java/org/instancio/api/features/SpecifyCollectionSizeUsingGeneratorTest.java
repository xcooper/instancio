package org.instancio.api.features;

import org.apache.commons.lang3.RandomUtils;
import org.instancio.Instancio;
import org.instancio.pojo.collections.lists.TwoListsOfItemString;
import org.instancio.pojo.collections.maps.TwoMapsOfIntegerItemString;
import org.instancio.pojo.generics.basic.Item;
import org.instancio.testsupport.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Bindings.all;
import static org.instancio.Bindings.field;

// TODO test nested lists/maps
class SpecifyCollectionSizeUsingGeneratorTest {
    private static final int EXPECTED_SIZE = RandomUtils.nextInt(0, 10);

    @Nested
    class ListTest {

        @Test
        @DisplayName("List of the target field should have expected size and be fully populated")
        void listShouldHaveExpectedSize() {
            final TwoListsOfItemString result = Instancio.of(TwoListsOfItemString.class)
                    .generate(field("list1"), gen -> gen.collection().size(EXPECTED_SIZE))
                    .create();

            assertList(result.getList1(), EXPECTED_SIZE);
            assertList(result.getList2(), Constants.COLLECTION_SIZE);
        }

        @Test
        @DisplayName("All lists should have expected size and be fully populated")
        void allListsShouldHaveExpectedSize() {
            final TwoListsOfItemString result = Instancio.of(TwoListsOfItemString.class)
                    .generate(all(List.class), gen -> gen.collection().size(EXPECTED_SIZE))
                    .create();

            assertList(result.getList1(), EXPECTED_SIZE);
            assertList(result.getList2(), EXPECTED_SIZE);
        }

        private void assertList(final List<Item<String>> list2, final int expectedSize) {
            assertThat(list2).hasSize(expectedSize)
                    .allSatisfy(item -> assertThat(item.getValue()).isInstanceOf(String.class));
        }
    }

    @Nested
    class MapTest {

        @Test
        @DisplayName("Map of the target field should have expected size and be fully populated")
        void mapShouldHaveExpectedSize() {
            final TwoMapsOfIntegerItemString result = Instancio.of(TwoMapsOfIntegerItemString.class)
                    .generate(field("map1"), gen -> gen.map().size(EXPECTED_SIZE))
                    .create();

            assertEntries(result.getMap1(), EXPECTED_SIZE);
            assertEntries(result.getMap2(), Constants.MAP_SIZE);
        }

        @Test
        @DisplayName("All maps should have expected size and be fully populated")
        void allMapsShouldHaveExpectedSize() {
            final TwoMapsOfIntegerItemString result = Instancio.of(TwoMapsOfIntegerItemString.class)
                    .generate(all(Map.class), gen -> gen.map().size(EXPECTED_SIZE))
                    .create();

            assertEntries(result.getMap1(), EXPECTED_SIZE);
            assertEntries(result.getMap2(), EXPECTED_SIZE);
        }

        private void assertEntries(final Map<Integer, Item<String>> map, final int expectedSize) {
            assertThat(map).hasSize(expectedSize);
            assertThat(map.entrySet()).allSatisfy(entry -> {
                assertThat(entry.getKey()).isInstanceOf(Integer.class);
                assertThat(entry.getValue().getValue()).isInstanceOf(String.class); // item.value
            });
        }
    }
}
