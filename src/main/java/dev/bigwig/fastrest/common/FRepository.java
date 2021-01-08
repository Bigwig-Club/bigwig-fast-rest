package dev.bigwig.fastrest.common;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FRepository<T extends Serializable, ID>
    extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

  @Override
  @NotNull
  Optional<T> findById(@NotNull ID id);

  @Override
  @NotNull
  List<T> findAllById(@NotNull Iterable<ID> iterable);

  @Override
  boolean existsById(@NotNull ID id);

  @Override
  @NotNull
  List<T> findAll();

  @Override
  @NotNull
  Page<T> findAll(@NotNull Pageable pageable);

  @Override
  <S extends T> @NotNull S saveAndFlush(@NotNull S s);

  @Override
  <S extends T> @NotNull List<S> saveAll(@NotNull Iterable<S> iterable);

  @Override
  void deleteById(@NotNull ID id);

  @Override
  void delete(@NotNull T t);

  void deleteByIdIn(Iterable<ID> ids);
}
