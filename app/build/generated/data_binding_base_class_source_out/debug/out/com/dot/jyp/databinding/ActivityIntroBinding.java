// Generated by view binder compiler. Do not edit!
package com.dot.jyp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.dot.jyp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityIntroBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView introAppLogoImage;

  @NonNull
  public final ImageView introDotLogoImage;

  private ActivityIntroBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView introAppLogoImage, @NonNull ImageView introDotLogoImage) {
    this.rootView = rootView;
    this.introAppLogoImage = introAppLogoImage;
    this.introDotLogoImage = introDotLogoImage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityIntroBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityIntroBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_intro, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityIntroBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.intro_app_logo_image;
      ImageView introAppLogoImage = rootView.findViewById(id);
      if (introAppLogoImage == null) {
        break missingId;
      }

      id = R.id.intro_dot_logo_image;
      ImageView introDotLogoImage = rootView.findViewById(id);
      if (introDotLogoImage == null) {
        break missingId;
      }

      return new ActivityIntroBinding((ConstraintLayout) rootView, introAppLogoImage,
          introDotLogoImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}