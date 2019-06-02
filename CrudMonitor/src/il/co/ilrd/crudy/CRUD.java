package il.co.ilrd.crudy;

import java.io.IOException;

import com.google.gson.JsonObject;

public interface CRUD<T, ID> {
  ID create(T entity) throws IOException;

  default T read(ID id) {
       throw new UnsupportedOperationException();
  }

  default void update(ID id, T entity) {
       throw new UnsupportedOperationException();
  }

  default void delete(ID id) {
       throw new UnsupportedOperationException();

  }
  default void delete0(T id) {
      throw new UnsupportedOperationException();

 }
  default void update(JsonObject json) {
	  throw new UnsupportedOperationException();
  }

  default JsonObject read(JsonObject json) {
	  throw new UnsupportedOperationException();
  }
}