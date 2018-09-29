package admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mob.MobSDK;

import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.constant.ConfigConstant;
import admin1.example.com.orderdistsapp.eventbus.EventBusUtils;
import admin1.example.com.orderdistsapp.eventbus.EventContants;
import admin1.example.com.orderdistsapp.eventbus.IntentToNextFragmentEvent;
import admin1.example.com.orderdistsapp.util.ToastUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static admin1.example.com.orderdistsapp.ui.LoginActivity.checkPhoneNumber;

/**
 * @Created by admin
 * @Created on 2018/9/12.
 **/
public class VertifyPhoneFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private CountDownTimer timer;

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_vertifycode)
    EditText et_vtfcode;
    @BindView(R.id.bt_verify)
    Button bt_sendVertify;

    @BindView(R.id.btn_next)
    Button bt_next;

    private boolean isValid = true;
    private EventHandler eventHandler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MobSDK.init(getContext(), ConfigConstant.SMS_VERIFY_KEY, ConfigConstant.SMS_VERIFY_CODE);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_verify_phone, container, false);

            ButterKnife.bind(this, rootView);
            initListener();
            initSms();
            initDataAndViews();

        }
        return rootView;
    }

    private void initListener() {
        bt_sendVertify.setOnClickListener(this);
        bt_next.setOnClickListener(this);
    }

    private void initDataAndViews() {

        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_verify:
                sendVertify();
                break;
            case R.id.btn_next:
                // 正式版
                toCommitVertify();
                //EventBusUtils.postAsync(666);
                //开发版
                break;

        }
    }

    private void toCommitVertify() {
        SMSSDK.submitVerificationCode(getString(R.string.Extra_Country_Code), et_phone.getText().toString(), et_vtfcode.getText().toString()); }

    private void sendVertify() {

        if (!checkInput()) {
            isValid = true;
            return;
        }
        CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
            @SuppressLint("NewApi")
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                bt_sendVertify.setText(millisUntilFinished / 1000 + "秒后重发");
                bt_sendVertify.setClickable(false);
            }

            @Override
            public void onFinish() {
                bt_sendVertify.setText(R.string.Extra_Get_VertiCode);
                bt_sendVertify.setClickable(true);
            }
        };
        timer.start();
        SendPhoneVertify();
    }

    private void SendPhoneVertify() {
        SMSSDK.getVerificationCode(getString(R.string.Extra_Country_Code), et_phone.getText().toString()); }

    private void initSms() {
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mhandler.sendMessage(msg);
            }
        };

        SMSSDK.registerEventHandler(eventHandler);
        SMSSDK.setAskPermisionOnReadContact(true);
    }


    Handler mhandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;


            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    ToastUtils.show(getString(R.string.Vertify_Success_Code));
                } else {
                    // TODO 处理错误的结果
                    ToastUtils.show(getString(R.string.Extra_Fail_sendCode));
                    ((Throwable) data).printStackTrace();
                }
            } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证码验证通过的结果
                    ToastUtils.show(getString(R.string.Extra_Success_Vertify));
                    EventBusUtils.postAsync(new IntentToNextFragmentEvent(null, EventContants.NEXTFRAGMENT));
                    } else {
                    // TODO 处理错误的结果
                    ((Throwable) data).printStackTrace();
                    ToastUtils.show(getString(R.string.Extra_Error_Vertify));

                }
            }
        }

    };

    //private void goToNext() {

        //EventBus.getDefault().post(666);

        // EB替换
        //((RegistActivity) mContext).setNextFragment();

    //}

    private boolean checkInput() {

        if (TextUtils.isEmpty(et_phone.getText())) {

            isValid = false;
            ToastUtils.show(getString(R.string.Extra_Regist_Phone_isNull));

        }

        if (isValid && !checkPhoneNumber(et_phone.getText().toString())) {

            isValid = false;
            ToastUtils.show(getString(R.string.Extra_Regist_Phone_isErrorType));

        }

        if (isValid && TextUtils.isEmpty(et_password.getText())) {

            isValid = false;
            ToastUtils.show(getString(R.string.Extra_Pwd_isNull));

        }

        return isValid;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer.onFinish();
        }

    }
}
