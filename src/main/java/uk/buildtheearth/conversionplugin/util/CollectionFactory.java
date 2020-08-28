package uk.buildtheearth.conversionplugin.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionFactory {

    public static <E> Collection<E> toCollection(Class<E> clazz, E... args) {
        ClassUtils.checkCanCast(clazz, Collection.class);
        if (clazz == Set.class) {
            return Sets.newHashSet(args);
        } else if (clazz == List.class) {
            return Lists.newArrayList(args);
        } else {
            throw new IllegalArgumentException(String.format("%s is an invalid class!", clazz));
        }
    }
}
