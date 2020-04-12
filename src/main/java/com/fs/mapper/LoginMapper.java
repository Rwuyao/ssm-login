package com.fs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.model.Info;
import com.fs.model.User;
import com.fs.model.page;

public interface LoginMapper {

	List<User> queryUserByUsername(String username);

	List<Info> queryALLinfo(@Param("pa") page pa,@Param("aa")String aa);

	int querynum(@Param("aa")String aa);

	List<Info> queryallandload(@Param("aa") String aa);

	int pinsertdata(@Param("list")List<Info> list);

	int deleteById(@Param("list")List<Integer> list);

}
