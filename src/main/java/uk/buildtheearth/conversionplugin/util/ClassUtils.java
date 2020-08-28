package uk.buildtheearth.conversionplugin.util;

import com.google.common.primitives.Primitives;
import org.bukkit.entity.Player;
import uk.buildtheearth.conversionplugin.constants.Messages;

public class ClassUtils {

    public static <E> E stringToClass(Player player, String value, Class<E> clazz) {
        try {
            return cast(CastFactory.stringToObject(value, clazz), clazz);
        } catch (InvalidTypeException e) {
            Messages.invalidArgument(player, value);
        }
        return null;
    }

    public static <E> E stringToClass(String value, Class<E> clazz) {
        return cast(CastFactory.stringToObject(value, clazz), clazz);
    }

    @SuppressWarnings("unchecked") // already checked with checkCanCast
    public static <E> E cast(Object value, Class<E> clazz) {
        checkCanCast(value, clazz);
        return (E) value;
    }

    public static void checkCanCast(Object value, Class<?> clazz) {
        if (!Primitives.wrap(clazz).isAssignableFrom(value.getClass())) {
            throw new InvalidTypeException(value, clazz);
        }
    }
}
