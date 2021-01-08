package dev.bigwig.fastrest.common;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <V> VO
 * @param <D> DTO
 * @param <ID> 主键类型
 */
public interface FController<V, D, ID> {

  List<V> list();

  Page<V> list(Pageable pageable);

  V get(ID id);

  V create(D d);

  List<V> createAll(List<D> ds);

  V update(ID id, D d);

  void delete(ID id);

  void deleteAll(List<ID> ids);
}
