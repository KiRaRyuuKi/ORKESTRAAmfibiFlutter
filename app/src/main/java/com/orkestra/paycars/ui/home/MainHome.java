package com.orkestra.paycars.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.orkestra.paycars.R;
import com.orkestra.paycars.databinding.ActivityHomeBinding;
import com.orkestra.paycars.ui.menu.HomeFragment;
import com.orkestra.paycars.ui.menu.ProfileFragment;
import com.orkestra.paycars.ui.menu.ShowroomFragment;
import com.orkestra.paycars.ui.menu.WishListFragment;

public class MainHome extends AppCompatActivity {

    private RecyclerView.Adapter ListAdapter;

    private RecyclerView RecyclerViewAdapter;

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        replaceFragment(new HomeFragment());

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homeItem) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.profileItem) {
                replaceFragment(new ProfileFragment());
            } else if (item.getItemId() == R.id.showroomItem) {
                replaceFragment(new ShowroomFragment());
            } else if (item.getItemId() == R.id.wishListItem) {
                replaceFragment(new WishListFragment());
            }
            return true;
        });

        initRecyclerView();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void initRecyclerView() {

    }

}