package com.matrixboot.user.center.infrastructure.mapper;

import com.matrixboot.user.center.domain.entity.user.MatrixUserEntity;
import com.matrixboot.user.center.infrastructure.common.command.user.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.user.UserUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * create in 2022/11/28 20:16
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Mapper(nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMatrixUserMapper {

    IMatrixUserMapper INSTANCE = Mappers.getMapper(IMatrixUserMapper.class);

    /**
     * from
     *
     * @param user MatrixUserEntity
     * @return UserResult
     */
    UserResult from(MatrixUserEntity user);

    /**
     * from
     *
     * @param command UserCreateCommand
     * @return MatrixUserEntity
     */
    MatrixUserEntity from(UserCreateCommand command);

    /**
     * update
     *
     * @param command UserUpdateCommand
     * @param entity  MatrixUserEntity
     */
    @InheritConfiguration
    void update(UserUpdateCommand command, @MappingTarget MatrixUserEntity entity);
}
