package com.action;
import com.biz.*;
import com.biz.imp.*;
import com.po.*;
import com.service.BizService;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
/**
 * 管理员Action类
 * @author Runewbie
 *
 */
public class AdminAction implements IAdminAction {
	private Admin admin;
	private String path;
	private Integer aid;
	
	private BizService bizs;
	
	
	public BizService getBizs() {
		return bizs;
	}

	public void setBizs(BizService bizs) {
		this.bizs = bizs;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}


	public String save() {
		boolean flag=bizs.getAbiz().save(admin);
		if(flag){
			path="findAll_Admin";
			return "ok";
		}
		
				
		return null;
	}

	public String update() {
		boolean flag=bizs.getAbiz().update(admin);
		if(flag){
			path="findAll_Admin";
			return "ok";
		}
		
		return null;
	}

	public String delById() {
		boolean flag=bizs.getAbiz().delById(aid);
		if(flag){
			path="findAll_Admin";
			return "ok";
		}
		return null;
	}

	public String findById() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		Admin oldad=bizs.getAbiz().findById(aid);
		session.setAttribute("oldad",oldad);
		path="admin.jsp";
		return "ok";
	}

	public String findAll() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		List<Admin> lsad=bizs.getAbiz().findAll();
		session.setAttribute("lsad",lsad);
		path="admin.jsp";
		return "ok";
	}

	public String check() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		Admin ad=bizs.getAbiz().check(admin);
		System.out.println("ad-->"+ad);
		if(ad!=null){
			session.setAttribute("ad", ad);
			path="main.jsp";
			
		}else{
			path="login.jsp?error=1";
			
		}
		
		
		return "ok";
	}

}
