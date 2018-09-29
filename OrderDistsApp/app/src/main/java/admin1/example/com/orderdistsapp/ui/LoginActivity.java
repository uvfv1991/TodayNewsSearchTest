package admin1.example.com.orderdistsapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.jude.fitsystemwindowlayout.FitSystemWindowsRelativeLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.base.BaseActivity;
import admin1.example.com.orderdistsapp.present.login.LoginPresentImpl;
import admin1.example.com.orderdistsapp.util.ToastUtils;
import admin1.example.com.orderdistsapp.view.ILoginview;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends BaseActivity implements ILoginview{

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.btn_register)
    Button btn_regist;
    @BindView(R.id.fwrl_view)
    FitSystemWindowsRelativeLayout fwrl_view;


    @BindView(R.id.progress_view)
    CircularProgressView progressView;

    private EventHandler eventHandler;
    private LoginPresentImpl  mLoginPresent;

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_pwd)
    EditText et_pwd;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initListener();
        initLoginPre();

    }

    private void initListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!doCheckLogin()){
                    return;
                }
                mLoginPresent.init(et_phone.getText(),et_username.getText(),et_pwd.getText());
                progressView.bringToFront();
                progressView.setVisibility(View.VISIBLE);
                progressView.startAnimation();

            }
        });


          btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                readyGo(RegistActivity.class);



            }
        });

    }


    /**
     *手机号格式验证
     */


    public static boolean checkPhoneNumber(String mobiles) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(mobiles);
        b = m.matches();
        return b;
    }


    // 使用完EventHandler需注销，否则可能出现内存泄漏
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }


    private boolean doCheckLogin() {

        boolean res=true;

        if (TextUtils.isEmpty(et_phone.getText())){

            res=false;
            ToastUtils.show(getString(R.string.Extra_Regist_Phone_isNull));

        }

        if (res && !checkPhoneNumber(et_phone.getText().toString()) ){

            res=false;
            ToastUtils.show(getString(R.string.Extra_Regist_Phone_isErrorType));

        }

        if (res && TextUtils.isEmpty(et_username.getText())){

            res=false;
            ToastUtils.show(getString(R.string.Extra_UserName_isNull));

        }if (res && TextUtils.isEmpty(et_pwd.getText())){

            res=false;
            ToastUtils.show(getString(R.string.Extra_Pwd_isNull));

        }


        return res;
    }

    private void initLoginPre() {
        mLoginPresent= new LoginPresentImpl(this);
    }

    private void initView() {
        et_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void startLogin() { }

    @Override
    public void sucessLogin() {

        ToastUtils.show(getString(R.string.Extra_Login_Ok));
        progressView.setVisibility(View.INVISIBLE);
        progressView.stopAnimation();
    }

    @Override
    public void failLogin() {

        ToastUtils.show(getString(R.string.Extra_Login_fail));
        progressView.setVisibility(View.INVISIBLE);
        progressView.stopAnimation();

    }
}
