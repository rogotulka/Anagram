package org.rogotulka.anagram;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity implements OnLoginListener {

    private WebView vWebWiew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String code = sharedPref.getString(getString(R.string.preferences_code), null);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if(code == null){
            transaction.replace(R.id.instagram_fragment, new LoginFragment());

        }else{
            transaction.replace(R.id.instagram_fragment, new LoginFragment());
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onSuccessLogin(String code) {
        if (code != null || code.isEmpty()) {
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.preferences_code), code);
            editor.commit();
        }

    }

    @Override
    public void onErrorLogin(String message) {
        //// TODO: 12.12.15

    }
}
