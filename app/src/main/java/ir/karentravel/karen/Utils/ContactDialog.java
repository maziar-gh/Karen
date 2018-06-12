package ir.karentravel.karen.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.karentravel.karen.R;

public class ContactDialog extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    @BindView(R.id.tv_phone1)
    TextView tvPhone1;
    @BindView(R.id.tv_phone2)
    TextView tvPhone2;
    @BindView(R.id.tv_phone3)
    TextView tvPhone3;
    @BindView(R.id.tv_phone4)
    TextView tvPhone4;

    public ContactDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.contact_dialog);

        ButterKnife.bind(this);

        tvPhone1.setOnClickListener(this);
        tvPhone2.setOnClickListener(this);
        tvPhone3.setOnClickListener(this);
        tvPhone4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String phone = "";

        switch (v.getId()) {
            case R.id.tv_phone2:
                phone = "+989904356097";
                break;
            case R.id.tv_phone3:
                phone = "+989109118115";
                break;
            case R.id.tv_phone4:
                phone = "+989119376871";
                break;
            default:
                break;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        this.c.startActivity(intent);

        dismiss();
    }
}