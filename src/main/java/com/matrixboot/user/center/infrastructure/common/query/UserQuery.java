package com.matrixboot.user.center.infrastructure.common.query;

import com.matrixboot.user.center.domain.entity.user.MatrixUserEntity;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * create in 2022/11/28 13:24
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record UserQuery(Long id, String username) implements Serializable {

    public String getUsername() {
        return StringUtils.isBlank(username()) ? "" : username();
    }

    @Contract(pure = true)
    public @NotNull Specification<MatrixUserEntity> specification() {
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (!ObjectUtils.isEmpty(id())) {
                Predicate predicateParent = builder.ge(root.get("id").as(Integer.class), id());
                list.add(predicateParent);
            }
            if (StringUtils.isNotBlank(username())) {
                Predicate predicateParent = builder.equal(root.get("username").as(String.class), username());
                list.add(predicateParent);
            }
            Predicate[] predicates = list.toArray(new Predicate[0]);
            return query.where(predicates).getRestriction();
        };
    }

}
