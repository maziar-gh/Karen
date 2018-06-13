package ir.karentravel.karen.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.karentravel.karen.R;

public class EditProfDialog extends Dialog implements
        View.OnClickListener {

    @BindView(R.id.edt_fullname)
    EditText edtFullname;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.btn_edit)
    Button btnEdit;
    private boolean hide;
    public Activity c;
    public Dialog d;


    public EditProfDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.editprof_dialog);

        ButterKnife.bind(this);

        edtFullname.setOnClickListener(this);
        edtPhone.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone = "";

        switch (v.getId()) {
            case R.id.btn_edit:
                Toast.makeText(c, "" + edtFullname.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        //dismiss();
    }
}