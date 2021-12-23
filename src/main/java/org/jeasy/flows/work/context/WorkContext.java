package org.jeasy.flows.work.context;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Work execution context. This can be used to pass initial parameters to the
 * workflow and share data between work units.
 *
 * @implSpec WorkContext is always thread-safe!
 */
public interface WorkContext {

   /**
    * creates a new and empty WorkContext
    * @return WorkContext instance
    */
   static WorkContext create(){
      return new MapWorkContext();
   }

   /**
    * returns the given Value as type T. Returns {@link Optional#empty()} if the value don't match the type or is null
    * @param symbol - the symbol
    * @param type - type, non-null
    * @param <T> return type
    * @return the value as type T or {@link Optional#empty()} if the types does not match or the value is null
    *
    * @throws NullPointerException if type is null
    */
   <T>Optional<T> getValue(String symbol, Class<T> type);

   /**
    * returns the raw value from the context. Might be null
    * @param symbol - symbol name
    * @return the value, might be null
    */
   Object getRawValue(String symbol);

   /**
    * adds the given symbol to the context.
    * This method adds the value only if there is no value set for the given symbol
    * @param symbol - the symbol
    * @param value - the value, can be null
    *
    * @throws NullPointerException if symbol is null
    * @throws UnsupportedOperationException if this context is immutable
    */
   void addValue(String symbol, Object value);

   /**
    * sets the value even if its already exists in this {@link WorkContext}.
    * @param symbol - symbol, not null
    * @param value - the value
    * @throws NullPointerException if symbol is null
    */
   void overrideValue(String symbol, Object value);

   /**
    * returns an immutable copy of this {@link WorkContext}.
    * @return immutable WorkContext.
    * Immutable WorkContext always throw an {@link UnsupportedOperationException} if mutated
    */
   WorkContext getImmutableCopy();

   /**
    * returns an immutable set of Map entries of the given values
    * @return entry set or empty set if the context is empty
    */
   Set<Map.Entry<String, Object>> getEntrySet();
}
