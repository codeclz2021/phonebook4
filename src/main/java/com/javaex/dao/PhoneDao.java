package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	// 전체리스트 가져오기
	public List<PersonVo> getPersonList() {
		System.out.println("PhoneDao.getPersonList()");
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");

		return personList;
	}

	// 전화번호 추가
	public int personInsert(PersonVo personVo) {
		System.out.println("PhoneDao.personInsert()");
		int count = sqlSession.insert("phonebook.insert", personVo);
		System.out.println(count + "건 저장");
		return count;
	}

	// 사람 삭제
	public int personDelete(int personId) {
		System.out.println("PhoneDao.personDelete()");
		
		int count = sqlSession.delete("phonebook.delete", personId);
		
		return count;
	}

	

}
