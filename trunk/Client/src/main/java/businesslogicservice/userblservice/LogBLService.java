package businesslogicservice.userblservice;

import java.util.ArrayList;

import vo.LogVO;

public interface LogBLService {

		public void AddLog(LogVO vo);
		public ArrayList<LogVO> showLog();
		public ArrayList<LogVO> find(String startDate,String endDate);
}
