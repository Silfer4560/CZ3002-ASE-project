package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class signupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail,editTextPassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        editTextEmail=findViewById( R.id.editTextEmail);
        editTextPassword=findViewById( R.id.editTextPassword );
        mAuth = FirebaseAuth.getInstance();
        findViewById( R.id.signupActivitySignupButton ).setOnClickListener( this );
    }
    private void registerUser(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(email.isEmpty()){ //email cannot be empty
            editTextEmail.setError( "Email is required" );
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ //check if email is valid
            editTextEmail.setError( "Please enter a valid email" );
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){ //password cannot be empty
            editTextPassword.setError( "Password is required" );
            editTextPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if signup is succesful, update firebase then bring user to search page
                            Toast.makeText( getApplicationContext(),"User Registration Success",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(signupActivity.this, SearchFunction.class);
                            intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        } else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "Account already registered",
                                        Toast.LENGTH_SHORT).show();
                            }
                            // If sign up fails, display a message to the user.
                            else{
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        // ...
                    }
                });

    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signupActivitySignupButton:
                registerUser();
                break;

            case R.id.signupActivityLoginButton:
                finish();
                startActivity(new Intent( this, MainActivity.class ) );
                break;
        }
    }
}
