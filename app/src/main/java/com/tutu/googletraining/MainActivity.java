package com.tutu.googletraining;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View tv = findViewById(R.id.tv);
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getWindow().setExitTransition(new Explode());
				Intent intent = new Intent(MainActivity.this, OtherActivity.class);
				startActivity(intent,
					ActivityOptions
						.makeSceneTransitionAnimation(MainActivity.this).toBundle());
			}
		});


		View myView = findViewById(R.id.tv2);
		myView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int cx = v.getWidth() / 2;
				int cy = v.getHeight() / 2;

				float finalRadius = (float) Math.hypot(cx, cy);

				Animator anim =
					ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);

				v.setVisibility(View.VISIBLE);
				anim.start();
			}
		});

		View tv3 = findViewById(R.id.tv3);
		tv3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,"ss",Toast.LENGTH_SHORT).show();
			}
		});


	}
}
