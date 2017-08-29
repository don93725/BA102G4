

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coaches.model.CoachesService;
import com.coaches.model.CoachesVO;
import com.gyms.model.GymsService;
import com.gyms.model.GymsVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.students.model.StudentsService;
import com.students.model.StudentsVO;

@WebServlet("/forWV")
public class forWV extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String account = req.getParameter("acc");
		String password = req.getParameter("pwd");
		HttpSession session = req.getSession();
		MembersService membersSV = new MembersService();
		MembersVO nowLogin_VO = membersSV.select(account);
		req.getSession().setAttribute("user", nowLogin_VO);
		String rank = nowLogin_VO.getMem_rank();
		if("0".equals(rank)) {
			StudentsService studentsSV = new StudentsService();
			StudentsVO studentsVO = studentsSV.loginStudents(account, password);
			session.setAttribute("student", studentsVO);
		}
		else if("1".equals(rank)) {			
			CoachesService coachesSV = new CoachesService();
			CoachesVO coachesVO = coachesSV.loginCoaches(account, password);
			session.setAttribute("coach", coachesVO);
		}
		else if("2".equals(rank)) {			
			GymsService gymsSV = new GymsService();
			GymsVO gymsVO = gymsSV.loginGyms(account, password);
			session.setAttribute("gym", gymsVO);
		}
		req.getRequestDispatcher("message/MessageCtrl?mem_no="+nowLogin_VO.getMem_no()).forward(req, res);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
