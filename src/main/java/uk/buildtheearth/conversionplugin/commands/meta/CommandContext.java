package uk.buildtheearth.conversionplugin.commands.meta;

import lombok.*;
import lombok.experimental.Delegate;
import org.bukkit.entity.Player;

import java.util.List;

@SuppressWarnings("unused")
@RequiredArgsConstructor @Getter @Builder
public class CommandContext {

    private String head;

    private final String name;

    @Singular
    private final List<String> aliases;

    private final String permission;
    private final boolean op;

    private final String usage;

    private final String description;
    private final String[] longDescription;

    @Delegate
    private final ArgRange range;

    @Getter @AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
    public static class ArgRange {
        private final int min;
        private final int max;

        public static ArgRange NONE = ArgRange.of(0, 0);
        public static ArgRange INFINITE = ArgRange.of(0, Integer.MAX_VALUE);

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public boolean inRange(int val) {
            return min <= val && val <= max;
        }

        private boolean equals0(ArgRange range) {
            return range.getMin() == min && range.getMax() == max;
        }

        public boolean equals(ArgRange range) {
            return equals0(range);
        }

        @Override
        public boolean equals(Object object) {
            return object instanceof ArgRange && ((ArgRange) object).equals0(this);
        }
    }
}
