// Generated by view binder compiler. Do not edit!
package com.orkestra.paycars.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.orkestra.paycars.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText editTextText1;

  @NonNull
  public final EditText editTextText102;

  @NonNull
  public final EditText editTextText12;

  @NonNull
  public final EditText editTextTextPassword;

  @NonNull
  public final Button lanjutDenganGoogle;

  @NonNull
  public final Button lanjutLogin;

  @NonNull
  public final View lineLeft;

  @NonNull
  public final View lineRight;

  @NonNull
  public final LinearLayout linearOpsiont;

  @NonNull
  public final TextView secondaryOpsiont;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final View view2;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText editTextText1, @NonNull EditText editTextText102,
      @NonNull EditText editTextText12, @NonNull EditText editTextTextPassword,
      @NonNull Button lanjutDenganGoogle, @NonNull Button lanjutLogin, @NonNull View lineLeft,
      @NonNull View lineRight, @NonNull LinearLayout linearOpsiont,
      @NonNull TextView secondaryOpsiont, @NonNull TextView textView, @NonNull TextView textView2,
      @NonNull TextView textView3, @NonNull View view2) {
    this.rootView = rootView;
    this.editTextText1 = editTextText1;
    this.editTextText102 = editTextText102;
    this.editTextText12 = editTextText12;
    this.editTextTextPassword = editTextTextPassword;
    this.lanjutDenganGoogle = lanjutDenganGoogle;
    this.lanjutLogin = lanjutLogin;
    this.lineLeft = lineLeft;
    this.lineRight = lineRight;
    this.linearOpsiont = linearOpsiont;
    this.secondaryOpsiont = secondaryOpsiont;
    this.textView = textView;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.view2 = view2;
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
      id = R.id.editTextText1;
      EditText editTextText1 = ViewBindings.findChildViewById(rootView, id);
      if (editTextText1 == null) {
        break missingId;
      }

      id = R.id.editTextText102;
      EditText editTextText102 = ViewBindings.findChildViewById(rootView, id);
      if (editTextText102 == null) {
        break missingId;
      }

      id = R.id.editTextText12;
      EditText editTextText12 = ViewBindings.findChildViewById(rootView, id);
      if (editTextText12 == null) {
        break missingId;
      }

      id = R.id.editTextTextPassword;
      EditText editTextTextPassword = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPassword == null) {
        break missingId;
      }

      id = R.id.lanjutDenganGoogle;
      Button lanjutDenganGoogle = ViewBindings.findChildViewById(rootView, id);
      if (lanjutDenganGoogle == null) {
        break missingId;
      }

      id = R.id.lanjutLogin;
      Button lanjutLogin = ViewBindings.findChildViewById(rootView, id);
      if (lanjutLogin == null) {
        break missingId;
      }

      id = R.id.lineLeft;
      View lineLeft = ViewBindings.findChildViewById(rootView, id);
      if (lineLeft == null) {
        break missingId;
      }

      id = R.id.lineRight;
      View lineRight = ViewBindings.findChildViewById(rootView, id);
      if (lineRight == null) {
        break missingId;
      }

      id = R.id.linearOpsiont;
      LinearLayout linearOpsiont = ViewBindings.findChildViewById(rootView, id);
      if (linearOpsiont == null) {
        break missingId;
      }

      id = R.id.secondaryOpsiont;
      TextView secondaryOpsiont = ViewBindings.findChildViewById(rootView, id);
      if (secondaryOpsiont == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.view2;
      View view2 = ViewBindings.findChildViewById(rootView, id);
      if (view2 == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, editTextText1,
          editTextText102, editTextText12, editTextTextPassword, lanjutDenganGoogle, lanjutLogin,
          lineLeft, lineRight, linearOpsiont, secondaryOpsiont, textView, textView2, textView3,
          view2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
