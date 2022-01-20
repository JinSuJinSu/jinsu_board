package action;

import mvc.Action;
import mvc.ActionFactory;

public class BoardFactory extends ActionFactory{
	
	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("insert".equals(actionName)) {
			action = new InsertAction();
		}
		else if("read".equals(actionName)){
			action = new readAction();
		}
		else if("delete".equals(actionName)){
			action = new DeleteAction();
		}
		else{
			action = new MainAction();
		}
		return action;
	}


}
