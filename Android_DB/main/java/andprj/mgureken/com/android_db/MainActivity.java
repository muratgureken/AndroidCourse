package andprj.mgureken.com.android_db;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mgureken.db.Db_Islem;
import com.mgureken.db.Kullanici;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView txtUserName;
    TextView txtPassword;
    Button btnEnter;
    Intent usrAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserName = (TextView) findViewById(R.id.txtKullanici);
        txtPassword = (TextView) findViewById(R.id.txtSifre);
        btnEnter = (Button) findViewById(R.id.btnGiris);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEnter_Islem(v);
            }
        });

    }
    public void btnEnter_Islem(View view)
    {
        Db_Islem db = new Db_Islem(getApplicationContext());
        List<Kullanici> liste = db.getAllUser();
        boolean durum = false;
        //kullanici yoksa, ekle
        if(liste.size()==0)
        {
            Kullanici temp = new Kullanici();
            temp.setUname("mgureken");
            temp.setPasswd("123");
            temp.setEmail("nnn@bbb.com");
            temp.setName("Murat");
            if(db.kaydet(temp))
            {
                Toast.makeText(this,"Kullanıcı Oluşturuldu", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Kullanıcı Oluşturulamadı...", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            for(int i=0; i<liste.size(); i++)
            {
                Kullanici gelen = liste.get(i);
                System.out.println("kullanici:"+gelen.getUname()+" sifre:"+gelen.getPasswd());
                if(gelen.getUname().equals(txtUserName.getText().toString())&&gelen.getPasswd().equals(txtPassword.getText().toString()))
                {
                    usrAdd = new Intent(getApplicationContext(),UserAdd.class);
                    startActivity(usrAdd);
                    durum = true;
                }
                if(durum)
                {
                    Toast.makeText(this,"Giriş Başarılı", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}

