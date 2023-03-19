package com.matrixboot.user.center.infrastructure.mapper;

import com.matrixboot.user.center.domain.entity.role.MatrixRoleEntity;
import com.matrixboot.user.center.infrastructure.common.command.RoleCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.RoleUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.result.RoleResult;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * create in 2023/3/19 22:20
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Mapper(nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMatrixRoleMapper {

    IMatrixRoleMapper INSTANCE = Mappers.getMapper(IMatrixRoleMapper.class);

    /**
     * from
     *
     * @param user MatrixUserEntity
     * @return UserResult
     */
    MatrixRoleEntity from(RoleCreateCommand user);

    /**
     * from
     *
     * @param user MatrixUserEntity
     * @return UserResult
     */
    RoleResult from(MatrixRoleEntity user);


    /**
     * update
     *
     * @param command RoleUpdateCommand
     * @param entity  MatrixRoleEntity
     */
    @InheritConfiguration
    void update(RoleUpdateCommand command, @MappingTarget MatrixRoleEntity entity);

}
