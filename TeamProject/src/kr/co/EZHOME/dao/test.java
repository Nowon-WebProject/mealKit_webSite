package kr.co.EZHOME.dao;

import java.util.*;

import kr.co.EZHOME.dto.UserDTO;
import kr.co.EZHOME.dto.UserVO;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserDAO udao=UserDAO.getInstance();
		Vector<UserVO> vec=udao.allSelectMember();
		
		for(int i =0;i<vec.size();i++) {
			UserVO mbean=vec.get(i);
			
			System.out.println(mbean.getName());
			
			
		}
	}

}
