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

public class AboutDialog extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;

    public AboutDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about_dialog);

        ButterKnife.bind(this);

    }

    @Override
    public void onClick(View v) {
        String phone = "";

        switch (v.getId()) {
            case R.id.tv_phone2:
                phone = "+989904356097";
                break;
            default:
                break;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        this.c.startActivity(intent);

        dismiss();
    }
}