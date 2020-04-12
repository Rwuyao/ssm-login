package com.fs.service.implt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.mapper.LoginMapper;
import com.fs.model.Info;
import com.fs.model.User;
import com.fs.model.page;
import com.fs.service.LoginService;

@Service

public class LoginServiceImplt implements LoginService {
	@Autowired
	private LoginMapper loginmapper;

	public List<User> queryUserByUsername(String username) {
		// TODO Auto-generated method stub
		return loginmapper.queryUserByUsername( username);
	}

	public List<Info> queryALLinfo(page pa,String aa) {
		// TODO Auto-generated method stub
		return loginmapper.queryALLinfo(pa,aa);
	}

	public int querynum(String aa) {
		// TODO Auto-generated method stub
		return loginmapper.querynum(aa);
	}

	public List<Info> queryallandload(String aa) {
		// TODO Auto-generated method stub
		return loginmapper.queryallandload( aa);
	}

	public int pinsertdata(List<Info> list) {
		// TODO Auto-generated method stub
		return loginmapper.pinsertdata( list);
	}

	public int deleteById(List<Integer> list) {
		// TODO Auto-generated method stub
		return loginmapper.deleteById( list);
	}

}
