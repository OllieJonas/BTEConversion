package uk.buildtheearth.conversionplugin.job.step;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.buildtheearth.conversionplugin.util.ClassUtils;
import uk.buildtheearth.conversionplugin.util.CollectionFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Getter
public class StepContext {

    private Map<String, ContextEntry<?>> context = Maps.newConcurrentMap();

    public StepContext(ConcurrentHashMap<String, ContextEntry<?>> context) {
        this.context = context;
    }

    public StepContext(ContextEntry<?>... entries) {
        addAll(entries);
    }

    public StepContext entry(ContextEntry<?> entry) {
        context.put(entry.getId(), entry);
        return this;
    }

    public StepContext entry(String id, Class<?> expectedClass) {
        return entry(ContextEntry.of(id, expectedClass));
    }

    public <T> StepContext entry(String id, Class<T> expectedClass, T arg) {
        ContextEntry<T> entry = ContextEntry.of(id, expectedClass);
        entry.updateValue(arg);
        return entry(entry);
    }

    public <T> StepContext entry(String id, Class<T> expectedClass, T... args) {
        ContextEntry<T> entry = ContextEntry.of(id, expectedClass);
        entry.updateValue(CollectionFactory.toCollection(expectedClass, args));
        return entry(entry);
    }

    public StepContext entries(ContextEntry<?>... entries) {
        addAll(entries);
        return this;
    }

    public ContextEntry<?> getEntry(String id) {
        return Preconditions.checkNotNull(context.get(id));
    }

    @SuppressWarnings("unchecked") // Assumes you know what type you're storing...
    public <E> E getValue(String id) {
        return (E) getEntry(id).getValue();
    }

    public void updateValue(String id, Object value) {
        getEntry(id).updateValue(value);
    }

    private void addAll(ContextEntry<?>... entries) {
        context.putAll(Lists.newArrayList(entries).stream()
                .collect(Collectors.toConcurrentMap(ContextEntry::getId, e -> e)));
    }

    @Override
    public String toString() {
        return "context: " + contextString();
    }

    private String contextString() {
        return context.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().getValue()).collect(Collectors.joining());
    }

    @RequiredArgsConstructor(staticName = "of")
    public static class ContextEntry<E> {
        private final String id;
        private final Class<E> expectedClass;
        private E value;

        public void updateValue(Object value) {
            this.value = ClassUtils.cast(value, expectedClass);
        }

        public String getId() {
            return id;
        }

        public E getValue() {
            return value;
        }
    }
}
