package de.hsrm.mi.swt02.backend.domain.position;

import org.junit.jupiter.api.Test;

class RotationTest {
    Rotation rotation = new Rotation(0d, 0d, 0d, "_order");

    @Test
    void testSet_x () {
        rotation.set_x(0d);
    }

    @Test
    void testSet_y () {
        rotation.set_y(0d);
    }

    @Test
    void testSet_z () {
        rotation.set_z(0d);
    }

    @Test
    void testSet_order () {
        rotation.set_order("_order");
    }
}

