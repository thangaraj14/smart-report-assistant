package org.dev.smartassistantreport.texttosql;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlExecutor {

  private final EntityManager entityManager;

  SqlExecutor(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<?> execute(String query) {
    List<?> result = entityManager.createNativeQuery(query).getResultList();
    if (result.isEmpty()) {
      throw new EmptyResultException("No results found for the provided query.");
    }
    return result;
  }
}
