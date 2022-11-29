package com.matrixboot.user.center.infrastructure.mapper;

import com.matrixboot.user.center.domain.entity.UserEntity;
import com.matrixboot.user.center.infrastructure.common.command.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.result.UserResultVO;
import org.mapstruct.Mapper;
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
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    /**
     * from
     *
     * @param user UserEntity
     * @return UserResultVO
     */
    UserResultVO from(UserEntity user);

    /**
     * from
     *
     * @param command UserCreateCommand
     * @return UserEntity
     */
    UserEntity from(UserCreateCommand command);

}
