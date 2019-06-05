package il.co.ilrd.logmonitor;

import java.io.IOException;

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
}