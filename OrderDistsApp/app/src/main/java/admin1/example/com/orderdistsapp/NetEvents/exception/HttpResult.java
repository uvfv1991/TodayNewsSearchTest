package admin1.example.com.orderdistsapp.NetEvents.exception;

/**
 * @Created by admin
 * @Created on 2018/9/11.
 **/
public class HttpResult<T> {
    /*
     * {"state":"success","id":0,"username":"aa","password":"123","count":"0","alias":"11","tag":"ui","phone":"18744044479"}
     *根据state判断成功或失败，成功可以取出用户信息
     * */

    private String state;

    public T data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
