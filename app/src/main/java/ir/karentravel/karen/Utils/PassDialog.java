package ir.karentravel.karen.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.karentravel.karen.R;

public class PassDialog extends Dialog implements
        View.OnClickListener {

    private boolean hide;
    public Activity c;
    public Dialog d;
    @BindView(R.id.edt_changepass)
    EditText edtChangepass;
    @BindView(R.id.btn_changepass)
    Button btnChangepass;
    @BindView(R.id.img_pass)
    ImageView imgPass;

    public PassDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pass_dialog);

        ButterKnife.bind(this);

        btnChangepass.setOnClickListener(this);
        imgPass.setOnClickListener(this);
        edtChangepass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone = "";

        switch (v.getId()) {
            case R.id.btn_changepass:
                Toast.makeText(c, "" + edtChangepass.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_pass:

                if(hide){
                    hide = !hide;
                    imgPass.setImageResource(R.drawable.eye_hide);
                    edtChangepass.setTransformationMethod(new PasswordTransformationMethod());
                }else {
                    hide = !hide;
                    imgPass.setImageResource(R.drawable.eye);
                    edtChangepass.setTransformationMethod(null);
                }

                break;
            default:
                break;
        }
        //dismiss();
    }
}