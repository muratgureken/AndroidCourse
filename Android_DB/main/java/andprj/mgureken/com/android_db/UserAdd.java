package andprj.mgureken.com.android_db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mgureken.db.Db_Islem;
import com.mgureken.db.Kullanici;

public class UserAdd extends AppCompatActivity {
    EditText txtUName, txtPasswd, txtEmail, txtName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
        txtEmail = (EditText)findViewById(R.id.txtemail);
        txtName = (EditText)findViewById(R.id.txtname);
        txtPasswd = (EditText)findViewById(R.id.txtSifre);
        txtUName = (EditText)findViewById(R.id.txtKullanici);
        btnSave = (Button)findViewById(R.id.btnkaydet);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave_UserSave(v);
            }
        });
    }

    private void btnSave_UserSave(View v) {
        Kullanici temp = new Kullanici();
        temp.setName(txtName.getText().toString());
        temp.setEmail(txtEmail.getText().toString());
        temp.setPasswd(txtPasswd.getText().toString());
        temp.setUname(txtUName.getText().toString());
        Db_Islem db = new Db_Islem(getApplicationContext());
        //kullanici onceden var mi kontrol edelim.
        if(db.kaydet(temp))
        {
            Toast.makeText(this,"Kullanıcı Oluşturuldu", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Kullanıcı Oluşturulamadı...", Toast.LENGTH_SHORT).show();
        }
    }
}
