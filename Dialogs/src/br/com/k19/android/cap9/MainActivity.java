package br.com.k19.android.cap9;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button progressButton = (Button) findViewById(R.id.show_progress_dialog_button);
		Button alertButton = (Button) findViewById(R.id.show_alert_dialog_button);
		Button customButton = (Button) findViewById(R.id.show_custom_dialog_button);
		
		progressButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			DialogFragment dialog = ProgressDialogFragment.newInstance();
			dialog.show(getFragmentManager(), "progress");
			}
		});
		
		alertButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogFragment dialog = AlertDialogFragment.newInstance();
				dialog.show(getFragmentManager(), "alert");
			}
		});
		
		customButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogFragment dialog = CustomDialogFragment.newInstance();
				dialog.show(getFragmentManager(), "custom");
			}
		});
	}
	
	public static class AlertDialogFragment extends DialogFragment {
		public static AlertDialogFragment newInstance() {
			AlertDialogFragment frag = new AlertDialogFragment();
			return frag;
		}
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstaceState) {
			AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
			dialog.setTitle(getActivity().getString(R.string.attention));
			dialog.setMessage(getActivity().getString(R.string.which_button_gonna_press));
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, getActivity().getString(R.string.yes),
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getActivity(), R.string.pressed_yes, Toast.LENGTH_SHORT).show();
						}
					});
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getActivity().getString(R.string.no),
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), R.string.pressed_no, Toast.LENGTH_SHORT).show();
						}
					});
			return dialog;
		}
	}
	public static class ProgressDialogFragment extends DialogFragment {
		public static ProgressDialogFragment newInstance() {
			ProgressDialogFragment frag = new ProgressDialogFragment();
			return frag;
		}
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState){
			Dialog dialog = new ProgressDialog(getActivity());
			dialog.setTitle(R.string.wait);
			return dialog;
		}
	}
	
	public static class CustomDialogFragment extends DialogFragment {
		
		public static CustomDialogFragment newInstance() {
			CustomDialogFragment frag = new CustomDialogFragment();
			return frag;
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle saverInstanceState){
			View v = inflater.inflate(R.layout.custom_dialog, container, false);
			getDialog().setTitle(R.string.k19_trainig);
			return v;
		}
	}
}
