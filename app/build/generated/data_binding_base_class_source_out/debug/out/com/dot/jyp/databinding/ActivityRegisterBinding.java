// Generated by view binder compiler. Do not edit!
package com.dot.jyp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.dot.jyp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnRegisterEmailCheck;

  @NonNull
  public final LinearLayout llRegisterEmail;

  @NonNull
  public final LinearLayout llRegisterPwd;

  @NonNull
  public final Button registerBtn;

  @NonNull
  public final EditText registerEmailEdittext;

  @NonNull
  public final ImageView registerLogoImage;

  @NonNull
  public final EditText registerPwd2Edittext;

  @NonNull
  public final EditText registerPwdEdittext;

  @NonNull
  public final TextView textRegisterEmail;

  @NonNull
  public final TextView textRegisterEmailWrong;

  @NonNull
  public final TextView textRegisterPwd;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnRegisterEmailCheck, @NonNull LinearLayout llRegisterEmail,
      @NonNull LinearLayout llRegisterPwd, @NonNull Button registerBtn,
      @NonNull EditText registerEmailEdittext, @NonNull ImageView registerLogoImage,
      @NonNull EditText registerPwd2Edittext, @NonNull EditText registerPwdEdittext,
      @NonNull TextView textRegisterEmail, @NonNull TextView textRegisterEmailWrong,
      @NonNull TextView textRegisterPwd) {
    this.rootView = rootView;
    this.btnRegisterEmailCheck = btnRegisterEmailCheck;
    this.llRegisterEmail = llRegisterEmail;
    this.llRegisterPwd = llRegisterPwd;
    this.registerBtn = registerBtn;
    this.registerEmailEdittext = registerEmailEdittext;
    this.registerLogoImage = registerLogoImage;
    this.registerPwd2Edittext = registerPwd2Edittext;
    this.registerPwdEdittext = registerPwdEdittext;
    this.textRegisterEmail = textRegisterEmail;
    this.textRegisterEmailWrong = textRegisterEmailWrong;
    this.textRegisterPwd = textRegisterPwd;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_register_email_check;
      Button btnRegisterEmailCheck = rootView.findViewById(id);
      if (btnRegisterEmailCheck == null) {
        break missingId;
      }

      id = R.id.ll_register_email;
      LinearLayout llRegisterEmail = rootView.findViewById(id);
      if (llRegisterEmail == null) {
        break missingId;
      }

      id = R.id.ll_register_pwd;
      LinearLayout llRegisterPwd = rootView.findViewById(id);
      if (llRegisterPwd == null) {
        break missingId;
      }

      id = R.id.register_btn;
      Button registerBtn = rootView.findViewById(id);
      if (registerBtn == null) {
        break missingId;
      }

      id = R.id.register_email_edittext;
      EditText registerEmailEdittext = rootView.findViewById(id);
      if (registerEmailEdittext == null) {
        break missingId;
      }

      id = R.id.register_logo_image;
      ImageView registerLogoImage = rootView.findViewById(id);
      if (registerLogoImage == null) {
        break missingId;
      }

      id = R.id.register_pwd2_edittext;
      EditText registerPwd2Edittext = rootView.findViewById(id);
      if (registerPwd2Edittext == null) {
        break missingId;
      }

      id = R.id.register_pwd_edittext;
      EditText registerPwdEdittext = rootView.findViewById(id);
      if (registerPwdEdittext == null) {
        break missingId;
      }

      id = R.id.text_register_email;
      TextView textRegisterEmail = rootView.findViewById(id);
      if (textRegisterEmail == null) {
        break missingId;
      }

      id = R.id.text_register_email_wrong;
      TextView textRegisterEmailWrong = rootView.findViewById(id);
      if (textRegisterEmailWrong == null) {
        break missingId;
      }

      id = R.id.text_register_pwd;
      TextView textRegisterPwd = rootView.findViewById(id);
      if (textRegisterPwd == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, btnRegisterEmailCheck,
          llRegisterEmail, llRegisterPwd, registerBtn, registerEmailEdittext, registerLogoImage,
          registerPwd2Edittext, registerPwdEdittext, textRegisterEmail, textRegisterEmailWrong,
          textRegisterPwd);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
