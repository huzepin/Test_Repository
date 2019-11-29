package cn.com.action;

import cn.com.bean.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
    User user=new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String execute() throws Exception {
        if (user.getUseName().equals("huzeping") && user.getPassword().length() >= 6) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }


    @Override
    public User getModel() {
        return user;
    }
}
