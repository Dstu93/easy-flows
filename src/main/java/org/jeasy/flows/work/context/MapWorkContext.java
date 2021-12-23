package org.jeasy.flows.work.context;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapWorkContext implements WorkContext {

    private final ConcurrentMap<String,Object> context;

    MapWorkContext() {
        this.context = new ConcurrentHashMap<>();
    }

    MapWorkContext(Map<String,Object> context) {
        this.context = new ConcurrentHashMap<>(context);
    }

    @Override
    public <T> Optional<T> getValue(String symbol, Class<T> type) {
        Objects.requireNonNull(type, "type cant be null");
        Object value = context.get(symbol);
        if (value == null)
            return Optional.empty();
        if (type.isInstance(value)) {
            return Optional.of(type.cast(value));
        }

        return Optional.empty();
    }

    @Override
    public Object getRawValue(String symbol) {
        if (symbol == null)
            return null;

        return context.get(symbol);
    }

    @Override
    public void addValue(String symbol, Object value) {
        Objects.requireNonNull(symbol, "symbol cant be null");
        context.putIfAbsent(symbol,value);
    }

    @Override
    public void overrideValue(String symbol, Object value) {
        Objects.requireNonNull(symbol, "symbol cant be null");
        context.put(symbol, value);
    }

    @Override
    public WorkContext getImmutableCopy() {
        return new ImmutableWorkContext(context);
    }

    @Override
    public Set<Map.Entry<String, Object>> getEntrySet() {
        return Collections.unmodifiableSet(context.entrySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapWorkContext that = (MapWorkContext) o;
        return Objects.equals(context, that.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context);
    }

    @Override
    public String toString() {
        return "MapWorkContext{" +
                "context=" + context +
                '}';
    }

    private final static class ImmutableWorkContext extends MapWorkContext{

        private ImmutableWorkContext(Map<String, Object> context) {
            super(Collections.unmodifiableMap(context));
        }

        @Override
        public void addValue(String symbol, Object value) {
            illegalOperation();
        }

        @Override
        public void overrideValue(String symbol, Object value) {
            illegalOperation();
        }

        private void illegalOperation() {
            throw new UnsupportedOperationException("cant modify immutable context");
        }
    }

}
