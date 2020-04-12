package com.fs.service;

import java.util.List;

import com.fs.model.Info;
import com.fs.model.User;
import com.fs.model.page;

public interface LoginService {

	List<User> queryUserByUsername(String username);

	List<Info> queryALLinfo( page pa,String aa);

	int querynum(String aa);

	List<Info> queryallandload(String aa);

	int pinsertdata(List<Info> list);

	int deleteById(List<Integer> list);

}
