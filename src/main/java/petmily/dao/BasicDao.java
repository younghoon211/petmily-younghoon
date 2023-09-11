package petmily.dao;

import petmily.domain.DomainObj;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicDao {

    DomainObj findByPk(int pk);

    void insert(DomainObj obj);

    void update(DomainObj obj);

    void delete(int pk);
}
