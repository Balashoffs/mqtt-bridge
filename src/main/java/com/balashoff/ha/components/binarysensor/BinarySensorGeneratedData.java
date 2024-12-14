package com.balashoff.ha.components.binarysensor;

import com.balashoff.ha.components.IHADeviceClass;
import com.balashoff.util.generator.BinarySensorRandomGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BinarySensorGeneratedData implements IHADeviceClass {

    private BinarySensorRandomGenerator generator;

    public static Stream<IHADeviceClass> generateFrom(BinarySensorRandomGenerator generator) {
        return Stream.of(new BinarySensorGeneratedData(generator));
    }

    @Override
    public String generateData() {
        return generator.make();
    }

    @Override
    public String topicHa() {
        return generator.getTopic();
    }

    @Override
    public String topicKnx() {
        return null;
    }
}
