package org.rogotulka.anagram.ui;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.rogotulka.anagram.R;

public class MainActivity extends AppCompatActivity implements OnLoginListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String code = sharedPref.getString(getString(R.string.preferences_code), null);




        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if(code == null){
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setOnLoginListener(this);
            transaction.replace(R.id.instagram_fragment, loginFragment, "LOGIN");

        }else{
            transaction.replace(R.id.instagram_fragment, new LentaFragment());
        }
        //transaction.addToBackStack("LOGIN");
        transaction.commit();
    }


    @Override
    public void onSuccessLogin(String code) {
        if (code != null || code.isEmpty()) {
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.preferences_code), code);
            editor.commit();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.instagram_fragment, new LentaFragment());
            transaction.commit();
        }



    }

    @Override
    public void onErrorLogin(String message) {
        //// TODO: 12.12.15

    }
}
