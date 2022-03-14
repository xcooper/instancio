package org.instancio.model;

import org.instancio.pojo.generics.basic.Pair;
import org.instancio.pojo.generics.container.PairContainer;
import org.instancio.testsupport.templates.ModelTestTemplate;
import org.instancio.testsupport.utils.NodeUtils;

import static org.instancio.testsupport.asserts.NodeAssert.assertNode;

class PairContainerModelTest extends ModelTestTemplate<PairContainer<Integer, String>> {

    @Override
    protected void verify(Node rootNode) {
        assertNode(rootNode)
                .hasKlass(PairContainer.class)
                .hasChildrenOfSize(1);

        // Pair<X, Y> pairValue;
        final String pairValueFieldName = "pairValue";
        final Node pairValue = assertNode(NodeUtils.getChildNode(rootNode, pairValueFieldName))
                .hasFieldName(pairValueFieldName)
                .hasKlass(Pair.class)
                .hasEffectiveClass(Pair.class)
                .hasGenericTypeName("org.instancio.pojo.generics.basic.Pair<X, Y>")
                .hasTypeMappedTo(Pair.class, "L", Integer.class)
                .hasTypeMappedTo(Pair.class, "R", String.class)
                .hasChildrenOfSize(2)
                .hasTypeMapWithSize(2)
                .getAs(ClassNode.class);

        assertNode(NodeUtils.getChildNode(pairValue, "left"))
                .hasEffectiveClass(Integer.class)
                .hasNoChildren();

        assertNode(NodeUtils.getChildNode(pairValue, "right"))
                .hasEffectiveClass(String.class)
                .hasNoChildren();
    }
}