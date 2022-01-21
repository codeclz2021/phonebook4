package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	
	//필드
	@Autowired
	private PhoneDao phoneDao;
	

	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController>list()");
		
		//다오에서 리스트를 가져온다
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList.toString());
		
		//컨트롤러-->DS데이터를 보낸다 (model)
		model.addAttribute("personList", personList);
		
		//jsp정보를 리턴한다(view)
		return "list";
	}
	
	

	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController>writeFrom()");
		
		
		return "writeForm";
	}

	
	
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST} )
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>write()");
		System.out.println(personVo);
		
		//저장
		phoneDao.personInsert(personVo);
		
		//리다이렉트
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping(value="/write2", method= {RequestMethod.GET, RequestMethod.POST} )
	public String write2(@RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company
			) {
		System.out.println("PhoneController>write2()");
		
		//저장
		phoneDao.personInsert2(name, hp, company);
		
		//리다이렉트
		return "redirect:/phone/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST} )
	public String delete(@RequestParam("personId") int personId) {
		System.out.println("PhoneController>delete()");
		
		//삭제
		phoneDao.personDelete(personId);
		
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST} )
	public String updateForm(@RequestParam("personId") int personId, Model model) {
		System.out.println("PhoneController>updateForm()");
		
		//수정폼
		PersonVo personVo = phoneDao.getPerson(personId);
		model.addAttribute("personVo", personVo);
		return "updateForm";
	}
	
	
	@RequestMapping(value="/update", method= {RequestMethod.GET, RequestMethod.POST} )
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>update()");
		System.out.println(personVo);
		
		//수정
		phoneDao.personUpdate(personVo);
		
		return "redirect:/phone/list";
	}
	
	
	
	
	
	

}
