package bussiness;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import struts.action.Action;
import struts.form.ActionForm;

public class JudgeAction implements Action{

	@Override
	public String execute(HttpServletRequest request, ActionForm form, Map<String, String> actionForward) {
		String url = "error";
		JudgeForm myform = (JudgeForm)form;
		if("admin".equals(myform.getName())){
			url = "success";
		}
		return actionForward.get(url);
	}

}
