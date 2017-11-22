package struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussiness.JudgeAction;
import struts.form.ActionForm;
import struts.form.FullForm;
import struts.form.XmlBean;

public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得请求得key
		String path = this.getPath(request.getServletPath());
		Map<String,XmlBean> map = (Map<String, XmlBean>) this.getServletContext().getAttribute("struts");
		XmlBean xml = map.get(path);
		String formClass = xml.getFormClass();
		ActionForm form = FullForm.full(formClass,request);
		String actionType = xml.getActionType();
		Action action = null;
		String url = "";
		try{
			Class<?> clazz = Class.forName(actionType);
			action = (Action)clazz.newInstance();
			url = action.execute(request, form,xml.getActionForward());
		}catch(Exception e){
			System.out.println("严重:控制器异常。。。。。。");
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	private String getPath(String servletPath){
		return servletPath.split("\\.")[0];
	}
}
