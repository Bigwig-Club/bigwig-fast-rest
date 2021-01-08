package dev.bigwig.fastrest.common;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <E> 实体
 * @param <D> DTO
 * @param <ID> 主键类型
 */
public interface FService<E, D, ID> {

  List<E> list();

  Page<E> list(Pageable pageable);

  E get(ID id);

  boolean existsById(ID id);

  E create(D d);

  List<E> createAll(List<D> ds);

  E update(ID id, D d);

  void delete(ID id);

  void deleteAll(List<ID> ids);
}
